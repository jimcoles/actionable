/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */
package org.jkcsoft.bogey.metamodel;

import org.jkcsoft.bogey.model.BMProperty;
import org.jkcsoft.bogey.model.IDCONST;
import org.jkcsoft.bogey.system.MC;
import org.jkcsoft.bogey.system.ObjectContext;

import java.util.*;

/**
 * Top level node in the meta data model for a DataSystem.
 *
 * @author Jim Coles
 * @version 1.0
 */
public class DataModel extends ModelElement {

    private static DataModel instance;

    public static DataModel getInstance() throws Exception {
        if (instance == null)
            instance = new DataModel();
        return instance;
    }

    //-------------------------------------------------------------------
    // Static members

    public static final Comparator ATTRREFCOMPARATOR = new AttrRefComparator();
    // these are the attribute kinds we want for a given base class.

    private static final AttributeKind queryAttrKinds[]
            = new AttributeKind[]{AttributeKind.CUSTOM, AttributeKind.CANNED, AttributeKind.STANDARD};

    //--------------------------------------------------------------------------
    // Instance-level members

    // hashes for various types of meta objects...
    private Map classes = new HashMap();
    private Map attrs = new HashMap();
    private Map assocs = new HashMap();
    private Map attrRefs = new HashMap();
    private Map queryChains = new HashMap();  // map of Lists (key=Class)
    private Map keywordAssocChains = new HashMap();
    private Map AttributeAscIndex = new HashMap();

    /**
     *
     */
    public void addQueryAssocChain(Class cls, Association[] assocs) {
        List chains = null;
        if (!queryChains.containsKey(cls)) {
            queryChains.put(cls, new Vector());
        }
        chains = (List) queryChains.get(cls);
        chains.add(new AssocChain(Arrays.asList(assocs)));
    }

    /**
     *
     */
    public void addKeywordAssocChain(Class cls, Association[] assocs) {
        List chains = null;
        if (!keywordAssocChains.containsKey(cls)) {
            keywordAssocChains.put(cls, new Vector());
        }
        chains = (List) keywordAssocChains.get(cls);
        chains.add(new AssocChain(Arrays.asList(assocs)));
    }

    public void addClass(Class cls) {
        classes.put(new Long(cls.getOid().getLongValue()), cls);
    }

    public void addAttr(Attribute attr) {
        Long key = new Long(attr.getOid().getLongValue());
        // First in wins.  if key is already there, ignore it.  This
        // will happen in the case where a CANNED attrs is statically
        // declared, but is also in the dynamic Attribute table because
        // the user must be able to control attr group and position in form.
        if (attrs.get(key) == null)
            attrs.put(key, attr);
    }

    public void addAssoc(Association assoc) {
        assocs.put(new Long(assoc.getOid().getLongValue()), assoc);
    }

    // JKC 9/9/2000 Class attr assoc stuff added to hold info that is assoc-specific.

//  static void addAttributeAsc(Association caa)
//  {
//    AttributeAscIndex.put(attributeKey(caa.getClassID(), caa.getAttrID()), caa);
//  }

    public String attributeKey(Oid cid, Oid aid) {
        return cid.getLongValue() + "_" + aid.getLongValue();
    }


    //-------------------------------------------------------------------
    // Constructor(s)
    //-------------------------------------------------------------------
    private DataModel() throws Exception {
        try {
//      Class.forName("XMen");
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    //-------------------------------------------------------------------
    // Public methods
    //-------------------------------------------------------------------
    public Collection getXClasses() {
        return classes.values();
    }

    public Collection getXAttrs() {
        return attrs.values();
    }

    public Collection getXAssocs() {
        return assocs.values();
    }

    /**
     * Gets a superset of all attributes (QueryAttrRef's).
     */
    public List<QueryAttrRef> getAttrDisplayList(ObjectContext context, Class start, AssocChain chain)
            throws Exception {
        // include superset of all unique
        List retList = new LinkedList();
        Class xcls = getEndClass(start, chain);
        List lattrs = xcls.getTableAttrs();
        Iterator iAttrs = lattrs.iterator();
        while (iAttrs.hasNext()) {
            Attribute cattr = (Attribute) iAttrs.next();
            Association caam = getAssoc(context, cattr.getOid().getLongValue());
            retList.add(getAttrRef(context, start, chain, cattr.getOid().getLongValue()));
        }

        Oid bmrclassid = null;
        Iterator<Oid> iclassids = xcls.getAllClassIDs().iterator();
        while (iclassids.hasNext()) {
            bmrclassid = iclassids.next();
            // add logic to get the configurable attributes
            Class rclass = this.getClass(bmrclassid.getLongValue());
//            RepoMap args = new RepoMap();
//            // xmeta uses class Oid, but bmr method needs corresponding interface Oid...
//            args.setOid(rclass.getOid());
//            args.put("ATTRKINDS", queryAttrKinds);

//            IRCollection ccattrs = this.getC(args); //this.getFormAttrs(rclass.getDefinition().getOid());
//            if (ccattrs != null) {
//                ccattrs.reset();
//                while (ccattrs.hasNext()) {
//                    Attribute irattr = (Attribute) ccattrs.next();
//                    retList.add(getAttrRef(context, start, chain, irattr.getOid().getLongValue()));
//                }
//            }
        }
        if (xcls.getIsRoleBased() || xcls.getIsRoleParent()) {
            Collection<ProcessRole> prc = null;
            if (xcls.getIsRoleBased()) {
                prc = (Collection<ProcessRole>) null;

                // TODO translate to preferred collection query
//                  prc = AppSystem.getCrm().getCompObject(context, "ProcessRoleList",
//                                                         IDCONST.PROCESSROLECOLL.getIIDValue());
            } else {
                // TODO translate to preferred collection query
//                prc = (IRCollection) AppSystem.getCrm().getCompObject(context, "ProcessRoleSingleList", IDCONST
//                        .PROCESSROLECOLL.getIIDValue());
            }
            for (ProcessRole processRole : prc) {
                if (processRole != null) {
                    retList.add(getAttrRef(context, start, chain, processRole.getOid().getLongValue()));
                }
            }
        }
        Collections.sort(retList, ATTRREFCOMPARATOR);
        return retList;
    }

    public List getKeywordAttrs(ObjectContext context, Class cls) throws Exception {
        List retList = new Vector();
        List allattrs = null;

        List lst = getKeywordAssocChains(cls);
        if (lst != null) {
            Iterator itr = lst.iterator();
            while (itr.hasNext()) {
                AssocChain chain = (AssocChain) itr.next();
                if (allattrs == null)
                    allattrs = getAttrDisplayList(context, cls, chain);
                else
                    allattrs.addAll(getAttrDisplayList(context, cls, chain));
            }
        } else
            allattrs = getAttrDisplayList(context, cls, null);


        if (allattrs != null) {
            Iterator iter = allattrs.iterator();
            while (iter.hasNext()) {
                QueryAttrRef aref = (QueryAttrRef) iter.next();
                if ((aref.getAttr().getPrimitiveDataType() == PrimitiveEnum.CHAR ||
                        aref.getAttr().getPrimitiveDataType() == PrimitiveEnum.LONG_CHAR) &&
                        !aref.getAttr().getIsExtended()) {
                    retList.add(aref);
                }
            }
        }
        return retList;
    }

    private Attribute getFromAttrCache(Long lid) {
        Attribute retObj = null;
        retObj = (Attribute) attrs.get(lid);
        return retObj;
    }

    public Attribute getAttr(ObjectContext context, Class cls, long id) throws Exception {
        MC mc = new MC(context);
        Attribute retObj = null;
        Long lid = new Long(id);
        try {
            retObj = getFromAttrCache(lid);
            if (retObj == null) {
                // see if its an Attribute
                Attribute rattr = null;
                try {
                    rattr = context.getRepository().getBMRepository().getAttribute(context.getRepository()
                                                                                           .makeReposID(id), false);
                } catch (Exception ex) {
                }
                if (rattr != null) {
                    if (rattr.getAttributeKind() == AttributeKind.CANNED)
                        throw new Exception("A canned attribute (" + rattr.getCodeName() + ", id = " + rattr.getOid()
                                .getLongValue() + ") was not statically declared to the XMR.");
                    retObj = rattr;
                } else // see if its a Role
                    if (cls.getIsRoleBased() || cls.getIsRoleParent()) {
                        ProcessRole role = (ProcessRole) mc.getObj(IDCONST.PROCESSROLE, id);
                        retObj = new Attribute(cls, role.getOid(), null, role.getDisplayName(), (role.isMultiUser() ?
                                PrimitiveEnum.MULTIENUM : PrimitiveEnum.ENUM), null);
                        // TODO Why did we have isRole boolean?
//                        retObj.setIsRole();
                    } else {
                        throw new Exception("Attribute id '" + id + "' not found as an Attribute or a Role.");
                    }
                attrs.put(lid, retObj);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (retObj == null) throw new Exception("Invalid Attribute ID.");
        return retObj;
    }

    // TODO ...
    private Attribute getAttribute(Oid oid, boolean edit) {
        return null;
    }

    private Attribute getAttr(ObjectContext context, Class cls, Attribute rattr)
            throws Exception {
        Attribute retObj = null;
        Long lid = new Long(rattr.getOid().getLongValue());
        try {
            retObj = (Attribute) attrs.get(lid);
            if (retObj == null) {
                if (rattr != null) {
                    retObj = rattr;
                }
                attrs.put(lid, retObj);
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (retObj == null) throw new Exception("Invalid Attribute ID.");
        return retObj;
    }

    /**
     * Follows an assoc chain to get the valid QueryAttrRef.
     */
    public QueryAttrRef getAttrRef(ObjectContext context, Class start, List chain, long id)
            throws Exception {
        QueryAttrRef retObj = null;
        Attribute attr = getAttr(context, getEndClass(start, chain), id);
        return getAttrRef(context, start, chain, attr);
    }

    public QueryAttrRef getAttrRef(ObjectContext context, Class start, List chain, Attribute cattr)
            throws Exception {
        QueryAttrRef retObj = null;
        String key = getAttrRefKey(start, chain, cattr);
        retObj = (QueryAttrRef) attrRefs.get(key);
        if (retObj == null) {
            retObj = new QueryAttrRef(chain, cattr);
            attrRefs.put(key, retObj);
        }
        return retObj;
    }

    public Class getClass(long id) throws Exception {
        Class retObj = null;
        try {
            retObj = (Class) classes.get(new Long(id));
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (retObj == null) throw new Exception("Invalid Class ID.");
        return retObj;
    }

    public Association getAssoc(ObjectContext context, long id) throws Exception {
        Association retObj = null;
        try {
            retObj = (Association) assocs.get(new Long(id));
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        if (retObj == null) throw new Exception("Invalid Association ID.");
        return retObj;
    }

    public List getClassAssocs(Class cls) throws Exception {
        List retList = new Vector();
        Iterator iasses = assocs.values().iterator();
        while (iasses.hasNext()) {
            Association ass = (Association) iasses.next();
            if (ass.getFromClass() == cls || ass.getToClass() == cls) {
                retList.add(ass);
            }
        }
        return retList;
    }

    /**
     * Gets all associations that originate FROM specified class.
     */
    public List getAssocsFrom(Class cls) throws Exception {
        List retList = new Vector();
        Iterator iasses = assocs.values().iterator();
        while (iasses.hasNext()) {
            Association ass = (Association) iasses.next();
            if (ass.getFromClass() == cls) {
                retList.add(ass);
            }
        }
        return retList;
    }

    /**
     * Gets all associations that originate FROM specified class.
     */
    public List getAssocsTo(Class cls) throws Exception {
        List retList = new Vector();
        Iterator iasses = assocs.values().iterator();
        while (iasses.hasNext()) {
            Association ass = (Association) iasses.next();
            if (ass.getToClass() == cls) {
                retList.add(ass);
            }
        }
        return retList;
    }

    public Class getOtherEnd(Association ass, Class end)
            throws Exception {
        if (ass.getFromClass() == end)
            return ass.getToClass();
        if (ass.getToClass() == end)
            return ass.getFromClass();

        throw new Exception("Specified Class <" + end.getDisplayName() + "> is not an end of the Association <" + ass
                .getDisplayName() + ">.");
    }

    public Class getEndClass(Class start, List chain)
            throws Exception {
        Class current = start;
        Iterator iAsses = null;
        if (chain != null && (iAsses = chain.iterator()) != null) {
            while (iAsses.hasNext()) {
                Association ass = (Association) iAsses.next();
                Class next = null;
                try {
                    next = getOtherEnd(ass, current);
                } catch (Exception ex) {
                    throw new Exception("Invalid association chain.");
                }
                current = next;
            }
        }
        return current;
    }

    /**
     * Used by query and report UI to determine what fields are searchable.
     */
    public List getQueryAssocChains(ObjectContext context, Class cls)
            throws Exception {
        List retVal = null;
        retVal = (List) queryChains.get(cls);
        return retVal;
    }

    /**
     * Used by query and report UI to determine what fields are searchable.
     */
    public List getKeywordAssocChains(Class cls)
            throws Exception {
        List retVal = null;
        retVal = (List) keywordAssocChains.get(cls);
        return retVal;
    }

    public String mapPrimToTable(PrimitiveDataType prim) {
        return mapPrimToTable(prim);
    }


    //-------------------------------------------------------------------
    // Private instance methods
    //-------------------------------------------------------------------

    private String getAttrRefKey(Class start, List asses, Attribute attr)
            throws Exception {
        String retVal = "";
        retVal += start.getOid().getLongValue();
        Class current = start;
        Iterator iAsses = null;
        if (asses != null && (iAsses = asses.iterator()) != null) {
            while (iAsses.hasNext()) {
                Association ass = (Association) iAsses.next();
                Class next = getOtherEnd(ass, current);
                retVal += "_" + ass.getOid().getLongValue();
                retVal += "_" + next.getOid().getLongValue();
                current = next;
            }
        }
        retVal += "_" + attr.getOid().getLongValue();
        return retVal;
    }

    public BMProperty getEnumliteral(Oid enumLitIID, boolean b) {
        return null;
    }

    private static class AttrRefComparator implements Comparator {
        // -1 ==> o1 is less than o2
        //  0 ==> equal
        //  1 ==> o1 is greater than o2
        public int compare(Object o1, Object o2) {
            int retVal = 0;
            if (o1 instanceof QueryAttrRef & o2 instanceof QueryAttrRef) {
                QueryAttrRef a1, a2;
                a1 = (QueryAttrRef) o1;
                a2 = (QueryAttrRef) o2;
                retVal = a1.getAttr().getDisplayName().compareTo(a2.getAttr().getDisplayName());
            }
            return retVal;
        }
    }
}