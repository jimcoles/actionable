package com.oculussoftware.repos.xmeta;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.process.*;

import com.oculussoftware.repos.query.*;
import com.oculussoftware.bus.xmeta.XMen;

import com.oculussoftware.util.*;


import java.util.*;

/*
* $Workfile: XMR.java $
* Create Date:  5-12-2000
* Description: Makes up for shortcomming in the BMR and other meta objects.
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
*/

/*
* $History: XMR.java $
 * 
 * *****************  Version 20  *****************
 * User: Jcoles       Date: 9/26/00    Time: 5:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Support issue #2510 - filtering out 'extended' attrs from keword lists.
 * 
 * *****************  Version 19  *****************
 * User: Jcoles       Date: 9/09/00    Time: 3:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Issue 2091 - new logic for classAttrAsc handling.  Ordering attrs by
 * name in getDisplayAttr( ).
 * 
 * *****************  Version 18  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Issue 2120 - handling IXClassAttrs that correspond to Roles.
 * 
 * *****************  Version 17  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:13p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Getting extended attrs from BMR.getFormAttrs( ) instead of
 * IRClass.getViewableAttributeList( ).
 * 
 * *****************  Version 16  *****************
 * User: Jcoles       Date: 8/03/00    Time: 11:49a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 15  *****************
 * User: Sshafi       Date: 8/02/00    Time: 5:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 14  *****************
 * User: Sshafi       Date: 7/27/00    Time: 11:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 13  *****************
 * User: Sshafi       Date: 7/27/00    Time: 9:22a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 7/22/00    Time: 1:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added getKeywordAttrs( ) impl.
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:42p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Some stuff.
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 7/20/00    Time: 8:45a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added getX...( ) methods.  Fixed compile error in getQueryAssocChains()
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 7/19/00    Time: 6:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Now use BMR.getFormAttrs( ) instead of IRClass.getGlobalAttrs...( ).
 * Added skeleton for getQueryAssocChains( ).
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 7/18/00    Time: 9:01a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Much change in the object lookup methods.  partucularly getAttrRef( ).
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:32a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Most just added method to map primitive to table name.
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Impl'd getClassAssocs( ).
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Change id representation.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Made classes to extend appropriate interface from api.repi.xmeta.
 * Changed all (java) class references to interface refs.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Made to implement interfaces from ...api.repi.xmeta.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 6/30/00    Time: 11:43a
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
 * Backup.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Adding MI source (User) and Org attrs to list.  
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:40a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Inital create.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/14/00    Time: 10:58p
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
*/

/**
* Implements IXMR in a manner that is independent of specific business
* domain / class knowledge.  
*/

public class XMR implements IXMR
{
  private static IXMR _instance = null;
  // hashes for various types of meta objects...
  private static Map _classes = new HashMap();  
  private static Map _attrs = new HashMap();  
  private static Map _assocs = new HashMap();  
  private static Map _attrRefs = new HashMap();
  private static Map _queryChains = new HashMap();  // map of Lists (key=IXClass)
  private static Map _keywordAssocChains = new HashMap();
  private static Map _classAttrAscIndex = new HashMap();
  
  //-------------------------------------------------------------------
  // Public constants and static methods
  //-------------------------------------------------------------------
  public static final Comparator ATTRREFCOMPARATOR = new AttrRefComparator();
  
  public static IXMR getInstance()
    throws OculusException
  {
    if (_instance == null)
      _instance = new XMR( );
    
    return _instance;  
  }
  
  /***/
  public static void addQueryAssocChain(IXClass cls, IXAssoc[] assocs)
  {
    List chains = null;
    if (!_queryChains.containsKey(cls)) 
    {
      _queryChains.put(cls, new Vector());
    }
    chains = (List) _queryChains.get(cls);
    chains.add(new AssocChain(Arrays.asList(assocs)));
  }

  /***/
  public static void addKeywordAssocChain(IXClass cls, IXAssoc[] assocs)
  {
    List chains = null;
    if (!_keywordAssocChains.containsKey(cls)) 
    {
      _keywordAssocChains.put(cls, new Vector());
    }
    chains = (List) _keywordAssocChains.get(cls);
    chains.add(new AssocChain(Arrays.asList(assocs)));
  }
  
  static void addClass(IXClass cls)
  {
    _classes.put(new Long(cls.getIID().getLongValue()), cls);
  }
  static void addAttr(IXClassAttr attr)
  {
    Long key = new Long(attr.getIID().getLongValue());
    // First in wins.  if key is already there, ignore it.  This
    // will happen in the case where a CANNED attrs is statically
    // declared, but is also in the dynamic Attribute table because
    // the user must be able to control attr group and position in form.
    if (_attrs.get(key) == null)
      _attrs.put(key, attr);
  }
  static void addAssoc(IXAssoc assoc)
  {
    _assocs.put(new Long(assoc.getIID().getLongValue()), assoc);
  }
  
  // JKC 9/9/2000 Class attr assoc stuff added to hold info that is assoc-specific.
  
  static void addClassAttrAsc(ClassAttrAscMeta caa)
  {
    _classAttrAscIndex.put(classAttrKey(caa.getClassID(), caa.getAttrID()), caa);
  }
  
  public static String classAttrKey(IIID cid, IIID aid)
  {
    return cid.getLongValue() + "_" + aid.getLongValue();
  }
  
  public ClassAttrAscMeta getClassAttrAsc(IXClass cls, IXClassAttr ca)
    throws OculusException
  {
    ClassAttrAscMeta retObj = null;
    retObj = (ClassAttrAscMeta)_classAttrAscIndex.get(classAttrKey(cls.getIID(), ca.getIID()));
    if (retObj == null)
      throw new OculusException("Invalid attribute for class: " + cls.getDisplayName());
    return retObj;
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
  private static final AttributeKind _queryAttrKinds[]
   = new AttributeKind[] {AttributeKind.CUSTOM, AttributeKind.CANNED, AttributeKind.STANDARD};

  public static String _mapPrimToTable(Primitive prim)
  {
    String retVal = null;
    
    if      (prim == Primitive.BLOB)    retVal = TAB_BLOBVALUE;
    else if (prim == Primitive.BOOLEAN) retVal = TAB_BOOLEANVALUE;
    else if (prim == Primitive.CHAR)    retVal = TAB_CHARVALUE;
    else if (prim == Primitive.DECIMAL) retVal = TAB_CHARVALUE;
    else if (prim == Primitive.ENUM)    retVal = TAB_ENUMVALUE;
    else if (prim == Primitive.INTEGER) retVal = TAB_CHARVALUE;
    else if (prim == Primitive.LINK)    retVal = TAB_CHARVALUE;
    else if (prim == Primitive.LONG_CHAR) retVal = TAB_LONGCHARVALUE;
    else if (prim == Primitive.MULTICHECK) retVal = TAB_ENUMLIST;
    else if (prim == Primitive.MULTIENUM) retVal = TAB_ENUMLIST;
    else if (prim == Primitive.PASSWORD) retVal = TAB_CHARVALUE;
    else if (prim == Primitive.RADIO)   retVal = TAB_ENUMVALUE;
    else if (prim == Primitive.TIME)    retVal = TAB_TIMEVALUE;
    
    return retVal;
  }
  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------
  private XMR( ) 
    throws OculusException
  {
    try {
      Class.forName("com.oculussoftware.bus.xmeta.XMen");
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
  }
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public Collection getXClasses(){ return _classes.values(); }

  public Collection getXAttrs(){ return _attrs.values(); }

  public Collection getXAssocs(){ return _assocs.values(); }
  
  /** Gets a superset of all attributes (IQAttrRef's). */
  public List getAttrDisplayList(IObjectContext context, IXClass start, IXAssocChain chain)
    throws OculusException
  {
    MC mc = new MC(context);
    IRBusinessModel bmr = context.getRepository().getBMRepository();
    
    // include superset of all unique 
    List retList = new Vector();
    IXClass xcls = getEndClass(start, chain);
    List lattrs = xcls.getTableAttrs();
    Iterator iAttrs = lattrs.iterator();
    while (iAttrs.hasNext()) {
      IXClassAttr cattr = (IXClassAttr) iAttrs.next();
      // JKC 8/3/00 Only add attr to the list if its NOT user configurable.  If it
      // IS user configurable, it should be retrieved by the subsequent logic chunk.
      ClassAttrAscMeta caam = getClassAttrAsc(xcls, cattr);
      if (!caam.getIsUserConfigurable())
        retList.add(getAttrRef(context, start, chain, cattr));
    }
    
    //
    // JKC 8/18/00 Added logic to handle xmeta classes that map to more than one
    // bmr class.  <== Was designed to handle FEATURE mapping to Feature and FeatureRevision,
    // but ultimately we handled this with a SQL view called FEATUREFEATUREREVISION. 
    //
    IIID bmrclassid = null;
    Iterator iclassids = xcls.getAllClassIDs().iterator();
    while (iclassids.hasNext()) {
      bmrclassid = ((IIIDEnum) iclassids.next()).getIIDValue();
      // add logic to get the configurable attributes
      IRClass rclass = bmr.getClass(bmrclassid, false);
      IDataSet args = new DataSet();
      // xmeta uses class IIID, but bmr method needs corresponding interface IIID...
      args.setIID(rclass.getRootDefinition().getIID());
      args.put("ATTRKINDS", _queryAttrKinds);
      
      IRCollection ccattrs = bmr.getFormAttrs(args); //bmr.getFormAttrs(rclass.getDefinition().getIID());
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
        prc = (IRCollection) CRM.getInstance().getCompObject(context,"ProcessRoleList",IDCONST.PROCESSROLECOLL.getIIDValue());
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

	public List getKeywordAttrs(IObjectContext context, IXClass cls) throws OculusException
	{
		List retList = new Vector();
    List allattrs = null;
    
    List lst = getKeywordAssocChains(cls);
    if(lst != null)
    {
      Iterator itr = lst.iterator();
      while(itr.hasNext())
      {
        IXAssocChain chain = (IXAssocChain)itr.next();
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
        if( (aref.getAttr().getPrimType() == Primitive.CHAR ||
            aref.getAttr().getPrimType() == Primitive.LONG_CHAR) &&
            !aref.getAttr().getIsExtended())
        {
          retList.add(aref);
        }
      }
    }
    return retList;
	}
  
  private IXClassAttr _getFromAttrCache(Long lid)
  {
    IXClassAttr retObj = null;
    retObj = (IXClassAttr) _attrs.get(lid);
    return retObj;
  }
  
  public IXClassAttr getAttr(IObjectContext context, IXClass cls, long id)
    throws OculusException
  {
    MC mc = new MC(context);
    IXClassAttr retObj = null;
    Long lid = new Long(id);
    try {
      retObj = _getFromAttrCache(lid);
      if (retObj == null) {
        // see if its an IRAttribute
        IRAttribute rattr = null;
        try {
          rattr = context.getRepository().getBMRepository().getAttribute(context.getRepository().makeReposID(id), false);
        }
        catch (OculusException ex) { }
        if (rattr != null) {
          if (rattr.getAttrKind() == AttributeKind.CANNED)
            throw new OculusException("A canned attribute ("+rattr.getName()+", id = "+rattr.getIID().getLongValue()+") was not statically declared to the XMR.");
          retObj = new ClassAttrMeta(rattr);
        }
        else // see if its a Role
        if (cls.getIsRoleBased() || cls.getIsRoleParent()) {
          IProcessRole role = (IProcessRole) mc.getObj(IDCONST.PROCESSROLE, id);
          retObj = new ClassAttrMeta(role.getIID(), null, role.getName(), (role.isMultiUser()?Primitive.MULTIENUM : Primitive.ENUM), null);
          ((ClassAttrMeta) retObj).setIsRole();
        }
        else {
          throw new OculusException("Attribute id '"+id+"' not found as an Attribute or a Role.");
        }
        _attrs.put(lid, retObj);
      }
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
    if (retObj == null) throw new OculusException("Invalid Attribute ID.");
    return retObj;
  }

  private IXClassAttr getAttr(IObjectContext context, IXClass cls, IRAttribute rattr)
    throws OculusException
  {
    IXClassAttr retObj = null;
    Long lid = new Long(rattr.getIID().getLongValue());
    try {
      retObj = (IXClassAttr) _attrs.get(lid);
      if (retObj == null) {
        if (rattr != null) {
          retObj = new ClassAttrMeta(rattr);
        }
        _attrs.put(lid, retObj);
      }
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
    if (retObj == null) throw new OculusException("Invalid Attribute ID.");
    return retObj;
  }
  
  /** Follows an assoc chain to get the valid IQAttrRef.*/
  public IQAttrRef getAttrRef(IObjectContext context, IXClass start, List chain, long id)
    throws OculusException
  {
    IQAttrRef retObj = null;
    IXClassAttr attr = getAttr(context, getEndClass(start, chain), id);
    return getAttrRef(context, start, chain, attr);
  }
  
  public IQAttrRef getAttrRef(IObjectContext context, IXClass start, List chain, IXClassAttr cattr)
    throws OculusException
  {
    IQAttrRef retObj = null;
    String key = _getAttrRefKey(start, chain, cattr);
    retObj = (IQAttrRef) _attrRefs.get(key);
    if (retObj == null) {
      retObj = new QAttrRef(chain, cattr);
      _attrRefs.put(key, retObj);
    }
    return retObj;
  }
  
  public IXClass getClass(long id)
    throws OculusException
  {
    IXClass retObj = null;
    try {
      retObj = (IXClass) _classes.get(new Long(id));
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
    if (retObj == null) throw new OculusException("Invalid Class ID.");
    return retObj;
  }

  public IXAssoc getAssoc(IObjectContext context, long id)
    throws OculusException
  {
    IXAssoc retObj = null;
    try {
      retObj = (IXAssoc) _assocs.get(new Long(id));
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
    if (retObj == null) throw new OculusException("Invalid Association ID.");
    return retObj;
  }
  
  public List getClassAssocs(IXClass cls) throws OculusException
  {
  	List retList = new Vector();
    Iterator iasses = _assocs.values().iterator();
    while ( iasses.hasNext() ) {
      IXAssoc ass = (IXAssoc) iasses.next();
      if (ass.getFromClass() == cls || ass.getToClass() == cls)
      {
        retList.add(ass);
      }
    }
    return retList;
  }
  
  /** Gets all associations that originate FROM specified class. */
  public List getAssocsFrom(IXClass cls) throws OculusException
  {
  	List retList = new Vector();
    Iterator iasses = _assocs.values().iterator();
    while ( iasses.hasNext() ) {
      IXAssoc ass = (IXAssoc) iasses.next();
      if (ass.getFromClass() == cls)
      {
        retList.add(ass);
      }
    }
    return retList;
  }

  /** Gets all associations that originate FROM specified class. */
  public List getAssocsTo(IXClass cls) throws OculusException
  {
  	List retList = new Vector();
    Iterator iasses = _assocs.values().iterator();
    while ( iasses.hasNext() ) {
      IXAssoc ass = (IXAssoc) iasses.next();
      if (ass.getToClass() == cls)
      {
        retList.add(ass);
      }
    }
    return retList;
  }
  
	public IXClass getOtherEnd(IXAssoc ass, IXClass end)
	  throws OculusException
	{
	  if(ass.getFromClass() == end)
	    return ass.getToClass();
	  if(ass.getToClass() == end)
	    return ass.getFromClass();
	  
	  throw new OculusException("Specified Class <"+end.getDisplayName()+"> is not an end of the Association <"+ass.getDisplayName()+">.");  
	}
  
	public IXClass getEndClass(IXClass start, List chain)
	  throws OculusException
	{
	  IXClass current = start;
	  Iterator iAsses = null;
	  if (chain != null && (iAsses = chain.iterator()) != null) {
	    while (iAsses.hasNext()){
	      IXAssoc ass = (IXAssoc) iAsses.next();
	      IXClass next = null;
        try {
  	      next = getOtherEnd(ass, current);
	      }
	      catch (OculusException ex) {
	        throw new OculusException("Invalid association chain.");
	      }
	      current = next;
	    }
	  }
    return current;
	}

  /** Used by query and report UI to determine what fields are searchable. */
	public List getQueryAssocChains(IObjectContext context, IXClass cls)
    throws OculusException
	{
		List retVal = null;
    retVal = (List) _queryChains.get(cls);
    return retVal;
	}

  /** Used by query and report UI to determine what fields are searchable. */
	public List getKeywordAssocChains(IXClass cls)
    throws OculusException
	{
		List retVal = null;
    retVal = (List) _keywordAssocChains.get(cls);
    return retVal;
	}
	
	public String mapPrimToTable(Primitive prim)
	{
    return _mapPrimToTable(prim);
	}
  
  
	//-------------------------------------------------------------------
	// Private instance methods
	//-------------------------------------------------------------------
  
	private String _getAttrRefKey(IXClass start, List asses, IXClassAttr attr)
	  throws OculusException
	{
	  String retVal = "";
    retVal += start.getIID().getLongValue();
	  IXClass current = start;
	  Iterator iAsses = null;
	  if (asses != null && (iAsses = asses.iterator()) != null) {
	    while (iAsses.hasNext()){
	      IXAssoc ass = (IXAssoc) iAsses.next();
	      IXClass next = getOtherEnd(ass, current);
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