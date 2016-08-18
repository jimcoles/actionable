package org.jkcsoft.bogey.metamodel;

import org.jkcsoft.bogey.model.IDCONST;

import java.util.*;

/**
* Implements IXMR in a manner that is independent of specific business
* domain / class knowledge.  
*/

public class XMR
{
  private static XMR instance = null;
  // hashes for various types of meta objects...
  private static Map classes = new HashMap();
  private static Map attrs = new HashMap();
  private static Map assocs = new HashMap();
  private static Map attrRefs = new HashMap();
  private static Map queryChains = new HashMap();  // map of Lists (key=Class)
  private static Map keywordAssocChains = new HashMap();
  private static Map AttributeAscIndex = new HashMap();
  
  //-------------------------------------------------------------------
  // Public constants and static methods
  //-------------------------------------------------------------------
  public static final Comparator ATTRREFCOMPARATOR = new AttrRefComparator();
  
  public static XMR getInstance()
    throws Exception
  {
    if (instance == null)
      instance = new XMR( );
    
    return instance;
  }
  
  /***/
  public static void addQueryAssocChain(Class cls, Association[] assocs)
  {
    List chains = null;
    if (!queryChains.containsKey(cls)) 
    {
      queryChains.put(cls, new Vector());
    }
    chains = (List) queryChains.get(cls);
    chains.add(new AssocChain(Arrays.asList(assocs)));
  }

  /***/
  public static void addKeywordAssocChain(Class cls, Association[] assocs)
  {
    List chains = null;
    if (!keywordAssocChains.containsKey(cls)) 
    {
      keywordAssocChains.put(cls, new Vector());
    }
    chains = (List) keywordAssocChains.get(cls);
    chains.add(new AssocChain(Arrays.asList(assocs)));
  }
  
  static void addClass(Class cls)
  {
    classes.put(new Long(cls.getIID().getLongValue()), cls);
  }
  static void addAttr(Attribute attr)
  {
    Long key = new Long(attr.getOid().getLongValue());
    // First in wins.  if key is already there, ignore it.  This
    // will happen in the case where a CANNED attrs is statically
    // declared, but is also in the dynamic Attribute table because
    // the user must be able to control attr group and position in form.
    if (attrs.get(key) == null)
      attrs.put(key, attr);
  }
  static void addAssoc(Association assoc)
  {
    assocs.put(new Long(assoc.getOid().getLongValue()), assoc);
  }
  
  // JKC 9/9/2000 Class attr assoc stuff added to hold info that is assoc-specific.
  
//  static void addAttributeAsc(AttributeAscMeta caa)
//  {
//    AttributeAscIndex.put(attributeKey(caa.getClassID(), caa.getAttrID()), caa);
//  }
  
  public static String attributeKey(Oid cid, Oid aid)
  {
    return cid.getLongValue() + "_" + aid.getLongValue();
  }

  private static final String TAB_CHARVALUE = "CHARVALUE";
  private static final String TAB_TIMEVALUE = "TIMEVALUE";
  private static final String TAB_LONGCHARVALUE = "LONGCHARVALUE";
  private static final String TAB_BOOLEANVALUE = "BOOLEANVALUE";
  private static final String TAB_BLOBVALUE = "BLOBVALUE";
  private static final String TAB_ENUMVALUE = "ENUMVALUE";
  private static final String TAB_ENUMLIST = "ENUMSELECTION";
  private static final String TAB_ENUMLITERAL = "ENUMLITERAL";

  // these are the attribute kinds we want for a given base class.
  private static final AttributeKind queryAttrKinds[]
   = new AttributeKind[] {AttributeKind.CUSTOM, AttributeKind.CANNED, AttributeKind.STANDARD};

  public static String mapPrimToTable(PrimitiveDataType prim)
  {
    String retVal = null;
    
    if      (prim == PrimitiveDataType.BLOB)    retVal = TAB_BLOBVALUE;
    else if (prim == PrimitiveDataType.BOOLEAN) retVal = TAB_BOOLEANVALUE;
    else if (prim == PrimitiveDataType.CHAR)    retVal = TAB_CHARVALUE;
    else if (prim == PrimitiveDataType.DECIMAL) retVal = TAB_CHARVALUE;
    else if (prim == PrimitiveDataType.ENUM)    retVal = TAB_ENUMVALUE;
    else if (prim == PrimitiveDataType.INTEGER) retVal = TAB_CHARVALUE;
    else if (prim == PrimitiveDataType.LINK)    retVal = TAB_CHARVALUE;
    else if (prim == PrimitiveDataType.LONG_CHAR) retVal = TAB_LONGCHARVALUE;
    else if (prim == PrimitiveDataType.MULTICHECK) retVal = TAB_ENUMLIST;
    else if (prim == PrimitiveDataType.MULTIENUM) retVal = TAB_ENUMLIST;
    else if (prim == PrimitiveDataType.PASSWORD) retVal = TAB_CHARVALUE;
    else if (prim == PrimitiveDataType.RADIO)   retVal = TAB_ENUMVALUE;
    else if (prim == PrimitiveDataType.TIME)    retVal = TAB_TIMEVALUE;
    
    return retVal;
  }
  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------
  private XMR( ) 
    throws Exception
  {
    try {
//      Class.forName("XMen");
    }
    catch (Exception ex) {
      throw new Exception(ex);
    }
  }
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public Collection getXClasses(){ return classes.values(); }

  public Collection getXAttrs(){ return attrs.values(); }

  public Collection getXAssocs(){ return assocs.values(); }
  
  /** Gets a superset of all attributes (IQAttrRef's). */
  public List getAttrDisplayList(ObjectContext context, Class start, AssocChain chain)
    throws Exception
  {
    IRBusinessModel bmr = context.getRepository().getBMRepository();
    
    // include superset of all unique 
    List retList = new Vector();
    Class xcls = getEndClass(start, chain);
    List lattrs = xcls.getTableAttrs();
    Iterator iAttrs = lattrs.iterator();
    while (iAttrs.hasNext()) {
      Attribute cattr = (Attribute) iAttrs.next();
      // JKC 8/3/00 Only add attr to the list if its NOT user configurable.  If it
      // IS user configurable, it should be retrieved by the subsequent logic chunk.
      AttributeAscMeta caam = getAttributeAsc(xcls, cattr);
      if (!caam.getIsUserConfigurable())
        retList.add(getAttrRef(context, start, chain, cattr));
    }
    
    //
    // JKC 8/18/00 Added logic to handle xmeta classes that map to more than one
    // bmr class.  <== Was designed to handle FEATURE mapping to Feature and FeatureRevision,
    // but ultimately we handled this with a SQL view called FEATUREFEATUREREVISION. 
    //
    Oid bmrclassid = null;
    Iterator iclassids = xcls.getAllClassIDs().iterator();
    while (iclassids.hasNext()) {
      bmrclassid = ((OidEnum) iclassids.next()).getIIDValue();
      // add logic to get the configurable attributes
      IRClass rclass = bmr.getClass(bmrclassid, false);
      IDataSet args = new DataSet();
      // xmeta uses class Oid, but bmr method needs corresponding interface Oid...
      args.setIID(rclass.getRootDefinition().getIID());
      args.put("ATTRKINDS", queryAttrKinds);
      
      IRCollection ccattrs = bmr.getFormAttrs(args); //bmr.getFormAttrs(rclass.getDefinition().getOid());
      if (ccattrs != null) {
        ccattrs.reset();
        while (ccattrs.hasNext()) {
          IRAttribute irattr = (IRAttribute) ccattrs.next();
            retList.add(getAttrRef(context, start, chain, irattr.getIID().getLongValue()));
        }
      }
    }
    if (xcls.getIsRoleBased() || xcls.getIsRoleParent()) {
      IRCollection prc = null;
      if (xcls.getIsRoleBased()) {
        prc = (IRCollection) CRM.getInstance().getCompObject(context, "ProcessRoleList", IDCONST.PROCESSROLECOLL.getIIDValue());
      }
      else {
        prc = (IRCollection) CRM.getInstance().getCompObject(context,"ProcessRoleSingleList",IDCONST.PROCESSROLECOLL.getIIDValue());
      }
      while(prc.hasNext())
      {
        IProcessRole pr = (IProcessRole) prc.next();
        if ( pr != null) 
        {
          retList.add(getAttrRef(context, start, chain, pr.getIID().getLongValue()));
        }
      }
    }
    Collections.sort(retList, ATTRREFCOMPARATOR);
    return retList;
  }

	public List getKeywordAttrs(ObjectContext context, Class cls) throws Exception
	{
		List retList = new Vector();
    List allattrs = null;
    
    List lst = getKeywordAssocChains(cls);
    if(lst != null)
    {
      Iterator itr = lst.iterator();
      while(itr.hasNext())
      {
        AssocChain chain = (AssocChain)itr.next();
        if(allattrs == null)
          allattrs = getAttrDisplayList(context,cls,chain);
        else  
          allattrs.addAll(getAttrDisplayList(context,cls,chain));
      }
    }
    else
      allattrs = getAttrDisplayList(context,cls,null);


    if (allattrs != null) {
      Iterator iter = allattrs.iterator();
      while (iter.hasNext()) {
        IQAttrRef aref = (IQAttrRef) iter.next();
        if( (aref.getAttr().getPrimType() == PrimitiveDataType.CHAR ||
            aref.getAttr().getPrimType() == PrimitiveDataType.LONG_CHAR) &&
            !aref.getAttr().getIsExtended())
        {
          retList.add(aref);
        }
      }
    }
    return retList;
	}
  
  private Attribute getFromAttrCache(Long lid)
  {
    Attribute retObj = null;
    retObj = (Attribute) attrs.get(lid);
    return retObj;
  }
  
  public Attribute getAttr(ObjectContext context, Class cls, long id)
    throws Exception
  {
    MC mc = new MC(context);
    Attribute retObj = null;
    Long lid = new Long(id);
    try {
      retObj = getFromAttrCache(lid);
      if (retObj == null) {
        // see if its an IRAttribute
        IRAttribute rattr = null;
        try {
          rattr = context.getRepository().getBMRepository().getAttribute(context.getRepository().makeReposID(id), false);
        }
        catch (Exception ex) { }
        if (rattr != null) {
          if (rattr.getAttrKind() == AttributeKind.CANNED)
            throw new Exception("A canned attribute ("+rattr.getName()+", id = "+rattr.getIID().getLongValue()+") was not statically declared to the XMR.");
          retObj = new AttributeMeta(rattr);
        }
        else // see if its a Role
        if (cls.getIsRoleBased() || cls.getIsRoleParent()) {
          IProcessRole role = (IProcessRole) mc.getObj(IDCONST.PROCESSROLE, id);
          retObj = new AttributeMeta(role.getIID(), null, role.getName(), (role.isMultiUser()?PrimitiveDataType.MULTIENUM : PrimitiveDataType.ENUM), null);
          ((AttributeMeta) retObj).setIsRole();
        }
        else {
          throw new Exception("Attribute id '"+id+"' not found as an Attribute or a Role.");
        }
        attrs.put(lid, retObj);
      }
    }
    catch (Exception ex) {
      throw new Exception(ex);
    }
    if (retObj == null) throw new Exception("Invalid Attribute ID.");
    return retObj;
  }

  private Attribute getAttr(ObjectContext context, Class cls, IRAttribute rattr)
    throws Exception
  {
    Attribute retObj = null;
    Long lid = new Long(rattr.getIID().getLongValue());
    try {
      retObj = (Attribute) attrs.get(lid);
      if (retObj == null) {
        if (rattr != null) {
          retObj = new AttributeMeta(rattr);
        }
        attrs.put(lid, retObj);
      }
    }
    catch (Exception ex) {
      throw new Exception(ex);
    }
    if (retObj == null) throw new Exception("Invalid Attribute ID.");
    return retObj;
  }
  
  /** Follows an assoc chain to get the valid IQAttrRef.*/
  public IQAttrRef getAttrRef(ObjectContext context, Class start, List chain, long id)
    throws Exception
  {
    IQAttrRef retObj = null;
    Attribute attr = getAttr(context, getEndClass(start, chain), id);
    return getAttrRef(context, start, chain, attr);
  }
  
  public IQAttrRef getAttrRef(ObjectContext context, Class start, List chain, Attribute cattr)
    throws Exception
  {
    IQAttrRef retObj = null;
    String key = getAttrRefKey(start, chain, cattr);
    retObj = (IQAttrRef) attrRefs.get(key);
    if (retObj == null) {
      retObj = new QAttrRef(chain, cattr);
      attrRefs.put(key, retObj);
    }
    return retObj;
  }
  
  public Class getClass(long id)
    throws Exception
  {
    Class retObj = null;
    try {
      retObj = (Class) classes.get(new Long(id));
    }
    catch (Exception ex) {
      throw new Exception(ex);
    }
    if (retObj == null) throw new Exception("Invalid Class ID.");
    return retObj;
  }

  public Association getAssoc(ObjectContext context, long id)
    throws Exception
  {
    Association retObj = null;
    try {
      retObj = (Association) assocs.get(new Long(id));
    }
    catch (Exception ex) {
      throw new Exception(ex);
    }
    if (retObj == null) throw new Exception("Invalid Association ID.");
    return retObj;
  }
  
  public List getClassAssocs(Class cls) throws Exception
  {
  	List retList = new Vector();
    Iterator iasses = assocs.values().iterator();
    while ( iasses.hasNext() ) {
      Association ass = (Association) iasses.next();
      if (ass.getFromClass() == cls || ass.getToClass() == cls)
      {
        retList.add(ass);
      }
    }
    return retList;
  }
  
  /** Gets all associations that originate FROM specified class. */
  public List getAssocsFrom(Class cls) throws Exception
  {
  	List retList = new Vector();
    Iterator iasses = assocs.values().iterator();
    while ( iasses.hasNext() ) {
      Association ass = (Association) iasses.next();
      if (ass.getFromClass() == cls)
      {
        retList.add(ass);
      }
    }
    return retList;
  }

  /** Gets all associations that originate FROM specified class. */
  public List getAssocsTo(Class cls) throws Exception
  {
  	List retList = new Vector();
    Iterator iasses = assocs.values().iterator();
    while ( iasses.hasNext() ) {
      Association ass = (Association) iasses.next();
      if (ass.getToClass() == cls)
      {
        retList.add(ass);
      }
    }
    return retList;
  }
  
	public Class getOtherEnd(Association ass, Class end)
	  throws Exception
	{
	  if(ass.getFromClass() == end)
	    return ass.getToClass();
	  if(ass.getToClass() == end)
	    return ass.getFromClass();
	  
	  throw new Exception("Specified Class <"+end.getDisplayName()+"> is not an end of the Association <"+ass.getDisplayName()+">.");  
	}
  
	public Class getEndClass(Class start, List chain)
	  throws Exception
	{
	  Class current = start;
	  Iterator iAsses = null;
	  if (chain != null && (iAsses = chain.iterator()) != null) {
	    while (iAsses.hasNext()){
	      Association ass = (Association) iAsses.next();
	      Class next = null;
        try {
  	      next = getOtherEnd(ass, current);
	      }
	      catch (Exception ex) {
	        throw new Exception("Invalid association chain.");
	      }
	      current = next;
	    }
	  }
    return current;
	}

  /** Used by query and report UI to determine what fields are searchable. */
	public List getQueryAssocChains(ObjectContext context, Class cls)
    throws Exception
	{
		List retVal = null;
    retVal = (List) queryChains.get(cls);
    return retVal;
	}

  /** Used by query and report UI to determine what fields are searchable. */
	public List getKeywordAssocChains(Class cls)
    throws Exception
	{
		List retVal = null;
    retVal = (List) keywordAssocChains.get(cls);
    return retVal;
	}
	
	public String mapPrimToTable(PrimitiveDataType prim)
	{
    return mapPrimToTable(prim);
	}
  
  
	//-------------------------------------------------------------------
	// Private instance methods
	//-------------------------------------------------------------------
  
	private String getAttrRefKey(Class start, List asses, Attribute attr)
	  throws Exception
	{
	  String retVal = "";
    retVal += start.getIID().getLongValue();
	  Class current = start;
	  Iterator iAsses = null;
	  if (asses != null && (iAsses = asses.iterator()) != null) {
	    while (iAsses.hasNext()){
	      Association ass = (Association) iAsses.next();
	      Class next = getOtherEnd(ass, current);
	      retVal += "_"+ass.getIID().getLongValue();
	      retVal += "_"+next.getIID().getLongValue();
	      current = next;
	    }
	  }
    retVal += "_"+attr.getIID().getLongValue();
	  return retVal;
	}
  
  private static class AttrRefComparator implements Comparator
  {
    // -1 ==> o1 is less than o2
    //  0 ==> equal
    //  1 ==> o1 is greater than o2
    public int compare(Object o1, Object o2)
    {
      int retVal = 0;
      if (o1 instanceof IQAttrRef & o2 instanceof IQAttrRef) {
        IQAttrRef a1, a2;
        a1 = (IQAttrRef) o1;  
        a2 = (IQAttrRef) o2;
        retVal = a1.getAttr().getDisplayName().compareTo(a2.getAttr().getDisplayName());
      }
      return retVal;
    }
  }
}