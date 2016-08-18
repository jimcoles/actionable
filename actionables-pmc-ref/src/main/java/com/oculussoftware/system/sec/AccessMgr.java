package com.oculussoftware.system.sec;
/**
* $Workfile: AccessMgr.java $
* Create Date: 3-19-2000
* Description: Concrete implementation for IAccessMgr.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: AccessMgr.java $
 * 
 * *****************  Version 35  *****************
 * User: Apota        Date: 9/18/00    Time: 1:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 31  *****************
 * User: Jcoles       Date: 9/15/00    Time: 1:47p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 2122 - did some method call re-plumbing to support ability to get the
 * parent of the object to which permissions are being assigned.
 * 
 * *****************  Version 30  *****************
 * User: Jcoles       Date: 9/14/00    Time: 7:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue 2207 - removed calls that were returning connections prematurely.
 * 
 * *****************  Version 29  *****************
 * User: Apota        Date: 9/12/00    Time: 12:05a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 28  *****************
 * User: Jcoles       Date: 8/24/00    Time: 12:01p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue 2051.  Group list build logic was bad.  Re-coded to use
 * StringUtil method.
 * 
 * *****************  Version 27  *****************
 * User: Jcoles       Date: 8/21/00    Time: 10:15a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Updated user group logic to reflect many groups per user requirement.
 * 
 * *****************  Version 26  *****************
 * User: Apota        Date: 7/06/00    Time: 8:30a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 25  *****************
 * User: Apota        Date: 7/05/00    Time: 2:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 24  *****************
 * User: Apota        Date: 7/05/00    Time: 2:15p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * setSingleton(false) on all my queries because I use a flush & fill,
 * updating
 * multiple rows.
 * 
 * *****************  Version 22  *****************
 * User: Jcoles       Date: 6/22/00    Time: 8:34a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue #1083 - Had to call setSingleton( ) in _sqlDeleteAll( ).
 * 
 * *****************  Version 21  *****************
 * User: Jcoles       Date: 6/02/00    Time: 11:49a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue #638.  Added checking for 'null' user group.
 * 
 * *****************  Version 20  *****************
 * User: Apota        Date: 6/01/00    Time: 5:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Removed the getDepartmentForms method
 * 
 * *****************  Version 19  *****************
 * User: Apota        Date: 6/01/00    Time: 9:54a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 18  *****************
 * User: Jcoles       Date: 5/31/00    Time: 8:13p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Added cache of Group string list to avoid extra calls to database.
 * Removed check for Admin/OWNER in setAllPerm(userid, ...).
 * _checkReposPerms( ) now calls ObjectACInfo.fillInhrPermissions( )
 * instead of .fillAccessorDirect...( ).  Modified group set load to take
 * advantage of 1 group-per-user rule.
 * 
 * *****************  Version 17  *****************
 * User: Jcoles       Date: 5/30/00    Time: 9:17p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Database blocking when permission grant enabled.  Related to iissue of
 * feature create failing when permission grant logic is enabled. 
 * Removed calls that force calling user to be of proper authority to
 * grant permissions.  That logic must be moved into the UI layer.  This
 * is due to fact that some grants must be done even when the current user
 * does not have admin authority, e.g., the OWNER grant when creating an
 * object. 
 * Removed calls that commit transactions.  Puts responsibliity in
 * consuming objects to call commit.  Re-routed some methods to pass a
 * single IRConnection instead of getting it each time.
 * 
 * *****************  Version 16  *****************
 * User: Jcoles       Date: 5/23/00    Time: 4:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Not allowing OWNER to do all.  BUG00086.  Changed all public checkXXX (
 * ) methods to delegate to an internal version of the method _checkXXXX(
 * ) that do not check for admin or OWNER permission.  Also routed the
 * convenience methods _checkXXXPerm( ) (that return booleans for a
 * singular permission) to the new internal methods.  This helped divert
 * an endless loop problem in that when checking a list of permissions I
 * must first check for OWNER.
 * 
 * *****************  Version 15  *****************
 * User: Jcoles       Date: 5/22/00    Time: 6:49p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 - Allow the 'system' user to grant permissions.
 * 
 * *****************  Version 14  *****************
 * User: Jcoles       Date: 5/22/00    Time: 8:26a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 - Just privatized some methods that didn't need to be public.
 * 
 * *****************  Version 13  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:37p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 - Removed Transition methods since obsolete.  Changed
 * semantics of grantPerm( ) methods to do incremental grants (not flush
 * and fill).  Added setAllPerms( ) method to do flush and fill.  Expanded
 * checkPerm( ) methods that take accessorids to use one method per
 * accessor type, checkRolePerm( ), checkGroupPerm( ), etc.
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.sec.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.PermEnum;
import com.oculussoftware.api.busi.IBusinessObject;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

/** 
* Central implementation of user access control logic.
*
* General approach:
*
*   One Access Control Manager (ACM) instance per user.  
*   
*   AccessMgr uses ObjectACInfo objects to help determine permissions for a specific
*   business object.  A dinstint ObjectACInfo is instantiated for each object.  If one or
*   more of the permissions being determined are 'recursive' (inheritable from parent)
*   then the ObjectACInfo knows how to create another ObjectACInfo corresponding to the
*   parent business object.
*
*/
public class AccessMgr implements IAccessMgr
{
  //-----------------------------------------------------------------------------
  // Private static variables
  //-----------------------------------------------------------------------------
  private static final String COL_OBJECTID = "OBJECTID";
  private static final String COL_PAROBJID = "PAROBJECTID";
  private static final String COL_PERMID   = "PERMISSIONID";
  private static final String COL_ACCESSORID   = "ACCESSORID";
  
  private static final String MSG_NOT_OWNER = "You must have the OWNER permission on an item or be the Admin user in order to set the item's permissions.";
  
  //-----------------------------------------------------------------------------
  // Private static methods
  //-----------------------------------------------------------------------------
  private static void _stdValidate()
    throws OculusException
  {
    if (PermEnum.getInitErr() != null)
      throw new OculusException("Initialization of the Access Control subsystem failed a static initialization test.  Error messages: \n" + PermEnum.getInitErr());
  }
  
  //-----------------------------------------------------------------------------
  // Private instance variables
  //-----------------------------------------------------------------------------
  private Map            _objContextTable     = new HashMap();
  private IObjectContext _context             = null;
  private long           _userId;
  private MC             _MC                  = null;
  private ObjectACInfo   _rootObject          = null;
  private IPermSet       _permSet             = null;
  private String         _strGroups           = null;
  
  //-----------------------------------------------------------------------------
  // Public constructor(s)
  //-----------------------------------------------------------------------------
  public AccessMgr(IObjectContext context, IPermSet ps) 
    throws OculusException
  {
    _context = context;
    _permSet = ps;
    _MC = new MC(context);
    try {
      _userId = _context.getConnection().getUserIID().getLongValue();
    }
    catch (ORIOException ex) {
      throw new OculusException(ex);
    }
  }
  
  //-----------------------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------------------

  public IPermSet getPermSetInfo()
  {
    return _permSet;
  }
  
  /** User id accessor */
  public long getUserId()
  {
    return _userId;
  }
  
  public IGrantSet fillDirectPermissions(long accessorid, long objId, IPermission perms[])
    throws OculusException
  {
    IGrantSet retSet = _initPermSet(perms);
    
    ObjectACInfo oai = new ObjectACInfo(this, objId);
    oai.fillAccessorDirectPermissions(accessorid, retSet, perms);
    return retSet;
  }

  public IGrantSet checkReposPerms(IPermission perms[])
    throws OculusException
  {
    _stdValidate();
    IGrantSet retSet = _initPermSet(perms);
    if (_isTheAdmin() || _checkReposPerm(PermEnum.OWNER)) {
      retSet = _initPermSet(perms);
      retSet.addAll(perms);
    }
    else {
      retSet = _checkReposPerms(perms);
    }
    return retSet;
  }

  
  /**
  * Checks for 'current' user with recursion to parent objects.  This is the method
  * most heavily used by UI or BO instrumentation checking for access.
  */
  public IGrantSet checkPerms(IRElement obj, IPermission perms[])
    throws OculusException
  {
    _stdValidate();
    if ( obj == null ) throw new OculusException("Null object.");
    
    IGrantSet retSet = _initPermSet(perms);
    if (_isTheAdmin() || _checkPerm(obj, PermEnum.OWNER)) {
      retSet = _initPermSet(perms);
      retSet.addAll(perms);
    }
    else {
      retSet = _checkPerms(obj, perms);
    }
    return retSet;
  }

  /**
  * Similar to checkPerms( ) except applies to specified user.  Used by admin screens to show
  * inherited permissions.
  */
  public IGrantSet checkUserPerms(long userid, IRElement obj, IPermission perms[])
    throws OculusException
  {
    _stdValidate();
    IGrantSet retSet = _initPermSet(perms);
    if (_isTheAdmin(userid) || _checkUserPerm(userid, obj, PermEnum.OWNER)) {
      retSet = _initPermSet(perms);
      retSet.addAll(perms);
    }
    else {
      retSet = _checkUserPerms(userid, obj, perms);
    }
    return retSet;
  }
  
  public IGrantSet checkGroupPerms(long groupid, IRElement obj, IPermission perms[])
    throws OculusException
  {
    _stdValidate();
    IGrantSet retSet = _initPermSet(perms);
    if (_checkGroupPerm(groupid, obj, PermEnum.OWNER)) {
      retSet.addAll(perms);
    }
    else {
      retSet = _checkGroupPerms(groupid, obj, perms);
    }
    return retSet;
  }

  public boolean hasAttrAccess(IRObject obj, IRAttrAccessGroup attrGroup, AttrGroupOper oper)
    throws OculusException
  {
    _stdValidate();
    boolean retVal = false;
    
    return retVal;
  }

  /** Does flush and fill of permission grant set. */
  public void setAllReposPermissions(long accId, Set perms)
    throws OculusException
  {
    _stdValidate();

    IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
    // flush...
    _sqlDeleteAll(repConn, accId, IDCONST.OCUREPOS.getLongValue());
    // fill...
    _grantReposPermissions(repConn, accId, perms);
  }

  /** Does flush and fill of permission grant set. */
  public void setAllPermissions(long accId, IRElement obj, Set perms)
    throws OculusException
  {
    _stdValidate();
      
    IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
    // flush...
    _sqlDeleteAll(repConn, accId, obj.getIID().getLongValue());
    // fill...
    _grantObjPermissions(repConn, accId, obj, perms);
  }

  /** Adds permission grant set.  Does not delete any existin perm grants. */
  public void grantReposPermissions(long accId, Set perms)
    throws OculusException
  {
    _stdValidate();
    // only allow grant if current user has admin permission for the object
//    if (!_checkReposPerm(PermEnum.OWNER) && !_isTheAdmin())
//      throw new OculusException(MSG_NOT_OWNER);
    
    IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
    _grantReposPermissions(repConn, accId, perms);
  }

  /** Adds permission grant set.  Does not delete any existin perm grants. */
  public void grantPermissions(long accId, IRElement obj, Set perms)
    throws OculusException
  {
    _stdValidate();
    // only allow grant if current user has admin permission for the object
//    if (!_checkPerm(obj, PermEnum.OWNER) && !_isTheAdmin() && !_isTheSys())
//      throw new OculusException(MSG_NOT_OWNER);
      
    IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
    _grantObjPermissions(repConn, accId, obj, perms);
  }


  public void setUserAttrGroupGrants(List list, IIID id) throws OculusException
	{	
		setAttrGroupGrants(list,id,"AppUser");
	}
  
  public void setGroupAttrGroupGrants(List list, IIID id) throws OculusException
	{	
		setAttrGroupGrants(list,id,"UserGroup");
	}
  
  
  protected void setAttrGroupGrants(List list, IIID id, String tableName) throws OculusException
  {
    IQueryProcessor qp = null;
    IRConnection jdtC = null;
    int size = list.size();
    
    try
    {
      jdtC = getCRM().getDatabaseConnection(getObjectContext());
      qp = jdtC.createProcessor();
      qp.setSingleton(false);
      //First flush (accessor specific)
      String query = "DELETE FROM ATTRGROUPGRANT " 
                   + "WHERE ATTRGROUPID="+id.getLongValue()
                   + " AND ACCESSORID IN (SELECT OBJECTID FROM "+tableName+")";	                                     
      qp.update(query);       
      String str = IDCONST.OCUREPOS.getIIDValue().toString();
      //Now insert
      for(int i =0; i < size; ++i)
      {
        AttrGroupGrantAsc asc = (AttrGroupGrantAsc)list.get(i);                 
        query = "INSERT INTO ATTRGROUPGRANT (PAROBJECTID,ATTRGROUPID,OPERATIONTYPE,ACCESSORID) VALUES ("
                +str+"," 
                +id.getLongValue()+","   
                +asc.getOperation().getIntValue()+","                                      
                +asc.getAccessorLng()+")";       
         qp.update(query);         
      }
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error deleting permissions from database: "+sqlExp.toString());
    }
    
    finally {
      if (qp != null) qp.close();
      //getObjectContext().getCRM().commitTransaction(getObjectContext());
    }
  }
  
  public List getAttrGroupGrants(IIID id) throws OculusException
  {
    IQueryProcessor qp = null;
    IRConnection jdtC = null;
    IDataSet rs = null;
    List list = new ArrayList();
    //First flush
    try
    {
      jdtC = getCRM().getDatabaseConnection(getObjectContext());
      qp = jdtC.createProcessor();
      String query = "SELECT * FROM ATTRGROUPGRANT " 
                   + "WHERE ATTRGROUPID="+id.getLongValue();                                      
      rs = qp.retrieve(query);
      while(rs.next())
      {
        AttrGroupGrantAsc asc = new AttrGroupGrantAsc();
        asc.setAccessor(rs.getLong("ACCESSORID"));
        asc.setOperation(AttrGroupOper.getInstance(rs.getInt("OPERATIONTYPE")));
        asc.setAttrGroup(id);
        list.add(asc);
      }
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error deleting permissions from database: "+sqlExp.toString());
    }
    
    finally {
      if (qp != null) qp.close();      
    }
    
    return list;
  }
  
  
  
  //-----------------------------------------------------------------------------
  // Package instance methods
  //-----------------------------------------------------------------------------
  
  /** Helper accessor method */
  ICRM getCRM() throws OculusException
  { return _context.getCRM(); };
  
  /** Helper accessor method */
  IObjectContext getObjectContext() 
  { return _context; };

  /** Helper accessor method */
  IUser getUser() throws OculusException
  {
    IUser retVal = null;
    retVal = _getUser(getUserId());
    return retVal;
  }

  IUser getUser(long userId) throws OculusException
  {
    return _getUser(userId);
  }
  
  /** */
  MC getMC() { return _MC; }
    
  
  //-----------------------------------------------------------------------------
  // Private instance methods
  //-----------------------------------------------------------------------------
  
  /** Returns set of group IIDs.  
  * NOTE: Might not need to be a set if we continue with rule that a User has
  *       only one Group, but that rule might change to MANY Groups.
  */
  
  private Set _getGroupsOfUser(long userId)
    throws OculusException
  {
    Set retSet = new HashSet();
    IRCollection groups = getUser(userId).getGroups();
    if (groups != null) {
      while (groups.hasNext()) {
        IGroup group = (IGroup) groups.next();
        if (group != null) {
          IIID id = group.getIID();
          if (id != null)
            retSet.add(id);
        }
        else {
          throw new OculusException("Null IGroup");
        }  
      }
    }
    return retSet;
  }
  
  /** Internal method does not check for admin or ownership. */
  private IGrantSet _checkPerms(IRElement obj, IPermission perms[])
    throws OculusException
  {
    if ( obj == null ) throw new OculusException("Null object.");
    
    IGrantSet retSet = _initPermSet(perms);
    ObjectACInfo oai = new ObjectACInfo(this, obj);
    oai.fillInhrPermissions(retSet, perms);
    return retSet;
  }
  
  /** Internal method does not check for admin or ownership. */
  private IGrantSet _checkReposPerms(IPermission perms[])
    throws OculusException
  {
    IGrantSet retSet = _initPermSet(perms);
    ObjectACInfo oai = new ObjectACInfo(this, null);
    oai.fillInhrPermissions(retSet, perms);
    return retSet;
  }

  /** Internal method does not check for admin or ownership. */
  public IGrantSet _checkUserPerms(long userid, IRElement obj, IPermission perms[])
    throws OculusException
  {
    IGrantSet retSet = _initPermSet(perms);
    ObjectACInfo oai = new ObjectACInfo(this, obj);
    oai.fillAccessorInhrPermissions(userid, IDCONST.USER.getIIDValue(), retSet, perms);
    return retSet;
  }
  
  /** Internal method does not check for admin or ownership. */
  public IGrantSet _checkGroupPerms(long groupid, IRElement obj, IPermission perms[])
    throws OculusException
  {
    IGrantSet retSet = _initPermSet(perms);
    ObjectACInfo oai = new ObjectACInfo(this, obj);
    oai.fillAccessorInhrPermissions(groupid, IDCONST.GROUP.getIIDValue(), retSet, perms);
    return retSet;
  }
  
  /** Checks single permission */
  private boolean _checkUserPerm(long userid, IRElement obj, IPermission perm)
    throws OculusException 
  {
    boolean retVal = false;
    IGrantSet gs = _checkUserPerms(userid, obj, new IPermission[] {perm} );
    if (gs != null) {
      retVal = gs.contains(perm);
    }
    return retVal;
  }
  
  private boolean _checkGroupPerm(long groupid, IRElement obj, IPermission perm)
    throws OculusException 
  {
    boolean retVal = false;
    IGrantSet gs = _checkGroupPerms(groupid, obj, new IPermission[] {perm} );
    if (gs != null) {
      retVal = gs.contains(perm);
    }
    return retVal;
  }
  
  /** Determines access to singular permission / object. */
  private boolean _checkPerm(IRElement obj, IPermission perm)
    throws OculusException
  {
    _stdValidate();
    boolean retVal = false;
    IGrantSet gs = _checkPerms(obj, new IPermission[] {perm});
    if (gs != null) {
      retVal = gs.contains(perm);
    }
    return retVal;
  }

  private boolean _checkReposPerm(IPermission perm)
    throws OculusException
  {
    _stdValidate();
    boolean retVal = false;
    IGrantSet gs = _checkReposPerms(new IPermission[] {perm});
    if (gs != null) {
      retVal = gs.contains(perm);
    }
    return retVal;
  }
  
  String getGroupStringList(long userId)
    throws OculusException
  {
    String retString = "";
    // if userid is that of 'current' user, try to use cached group list
    if (userId == getUserId() && _strGroups != null) {
        retString = _strGroups;
    }
    else {
      Set sGroups = _getGroupsOfUser(userId);
      retString = StringUtil.buildCommaDelList(sGroups);
      // cache away the group list...
      if (userId == getUserId()) {
        _strGroups = retString;
      }
    }
    return retString;
  }

  
  private IObject _getObj(IDCONST type, IIID iid)
    throws OculusException
  {
    return (IObject) _context.getCRM().getCompObject(_context, type.getDirName(), iid);
  }
  
  private IGrantSet _initPermSet(IPermission perms[])
  {
    IGrantSet retSet = new GrantSet(perms.length);
    return retSet;
  }

  private static Set _autoGrants = new HashSet();
  static {
    _autoGrants.add(PermEnum.ITEM_VIEW);
  }
  
  /** Fill only... Calling method must do any flushing. */
  private void _grantReposPermissions(IRConnection repConn, long accId, Set perms)
    throws OculusException
  {
    Iterator iperms = perms.iterator();
    while ( iperms.hasNext()) {
      IPermission perm = (IPermission) iperms.next();
      _sqlEnsurePermGrant(repConn, IDCONST.OCUREPOS.getLongValue(), perm.getID(), accId);
    }
  }
  
  /** Fill only... Calling method must do any flushing. */
  private void _grantObjPermissions(IRConnection repConn, long accId, IRElement elem, Set perms)
    throws OculusException
  {
    Iterator iperms = perms.iterator();
    while ( iperms.hasNext()) {
      IPermission perm = (IPermission) iperms.next();
      _ensureObjPermGrant(repConn, elem, perm, accId);
    }
  }

  /** This method will apply a permission to a parent object if needed.*/
  private void _ensureObjPermGrant(IRConnection repConn, IRElement elem, IPermission perm, long accid)
    throws OculusException
  {
    _sqlEnsurePermGrant(repConn, elem.getIID().getLongValue(), perm.getID(), accid);
    // JKC 9/15/00: Issue 2122. If granting an auto-grant recursive permission (e.g., ITEM_VIEW),
    // auto-grant to the parent object recursively.
    if (_autoGrants.contains(perm)) {
      if (elem instanceof IBusinessObject) {
        IBusinessObject parobj = (IBusinessObject) ((IBusinessObject) elem).getParentObject();
        long paroid = Long.MIN_VALUE;
        IDCONST idcClassid = null;
        if (parobj != null) {
          idcClassid = IDCONST.getInstance(parobj.getDefnObject().getIID().getLongValue());
        }
        else {
          idcClassid = IDCONST.REPOSITORY;
        }
        if (PermEnum.isValidPermission(perm, idcClassid)) {
          _ensureObjPermGrant(repConn, parobj, perm, accid);
        }
      }
    }
  }
  
  /**
  * SQL Insert for permissions
  */
  private void _sqlEnsurePermGrant(IRConnection repConn, long oid, int permid, long accid)
    throws OculusException
  {
    IQueryProcessor stmt = null;
    try
    {
      stmt = repConn.createProcessor();
      
      // see if its there...
      String query = "SELECT * FROM PERMISSIONGRANT WHERE " 
                   + COL_PAROBJID   + " = " + oid    + " AND "
                   + COL_PERMID     + " = " + permid + " AND "
                   + COL_ACCESSORID + " = " + accid  + " ";
      
      IDataSet data = stmt.retrieve(query);
      if (!data.next()) {
        // if not there, insert it...
        query = "INSERT INTO PERMISSIONGRANT (" 
                     + COL_PAROBJID + ", " 
                     + COL_PERMID + ", " 
                     + COL_ACCESSORID
                     + ") VALUES ("
                     + oid + ", " 
                     + permid + ", " 
                     + accid + " )";
        
        stmt.update(query);
      }
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException(sqlExp);
    }
    finally {
      if (stmt != null) {
        stmt.close();
      }
    }
  }
  
  private void _sqlDeleteAll(IRConnection repConn, long accid, long oid)
    throws OculusException
  {
    IQueryProcessor stmt = null;
    try
    {
      stmt = repConn.createProcessor();
      stmt.setSingleton(false);
      String query = "DELETE FROM PermissionGrant " 
                   + "WHERE " + COL_ACCESSORID + " = " + accid 
                   + " AND " + COL_PAROBJID + "=" + oid ;
      
      stmt.update(query);
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error deleting permissions from database: "+sqlExp.toString());
    }
    finally {
      if (stmt != null) {
        stmt.close();
      }
    }
  }  
  
  private boolean _isTheAdmin(long accessorid)
    throws OculusException
  { 
    boolean retVal = false;
    // the accessor might not be a valid user
    try {
      retVal = _getUser(accessorid).isAdmin();
    }
    catch(ObjectNotFoundException invalidUser) {
      // ignore for now.
      // NOTE: a bit kludgy singce we don't know that the accessor is supposed to be a user.
      // ALSO: it'd be nice if the CRM threw an explicit ObjectNotFound exception.
    }
    return retVal;
  }
  
  private IUser _getUser(long userid)
    throws OculusException
  {
    IUser user = null;
    try {
      user = (IUser) getMC().getObj(IDCONST.USER, userid);
    }
    catch (OculusException ex) {
      throw new ObjectNotFoundException("Invalid User Id sent to Access Manager.  User not found.");
    }
    if (user == null) throw new ObjectNotFoundException("Invalid User Id sent to Access Manager.  User not found.");
//    if (!user.isSysUser()) throw new OculusException("Specified User is not a valid system User.");
    return user;
  }
  
  private Boolean _isAdm = null;
  private boolean _isTheAdmin()
    throws OculusException
  { 
    if (_isAdm == null)
      _isAdm = new Boolean(getUser().isAdmin());
    return _isAdm.booleanValue();
  }
  
  private Boolean _isSys = null;
  private boolean _isTheSys()
    throws OculusException
  { 
    if (_isSys == null) {
      IIID uid = getUser().getIID();
      if ( uid.getLongValue() == IDCONST.USER_SYSTEM.getIIDValue().getLongValue())
        _isSys =  new Boolean(true);
      else
        _isSys = new Boolean(false);
    }
    return _isSys.booleanValue();
  }    
  
  /*
	  	Add the grant for an individual User Group, the list will contain only 1 item.
	  This is used to automatically grant a new user group with view/edit permission
	  on the public/DEFAULT_ATTR_GROUP at the time of creating the new user group.
  */

  public void addGroupAttrGroupGrant(List list, IIID id) throws OculusException
	{
		
		IQueryProcessor qp = null;
    IRConnection jdtC = null;
    IDataSet rs = null;
    com.oculussoftware.system.sec.AttrGroupGrantAsc attsec = (com.oculussoftware.system.sec.AttrGroupGrantAsc)list.get(0);    
    try
    {
	      jdtC = getCRM().getDatabaseConnection(getObjectContext());
	      qp = jdtC.createProcessor();
	      String query = "SELECT * FROM ATTRGROUPGRANT " 
	                   + "WHERE ACCESSORID="+attsec.getAccessorLng()+" AND ATTRGROUPID="+id.getLongValue();                                      
	      rs = qp.retrieve(query);
	      if (!rs.next())
		    	{    	
		    		qp.close();
		    		qp = jdtC.createProcessor();
		    		String str = IDCONST.OCUREPOS.getIIDValue().toString();
		    		query = "INSERT INTO ATTRGROUPGRANT (PAROBJECTID,ATTRGROUPID,OPERATIONTYPE,ACCESSORID) VALUES ("
		                +str+"," 
		                +id.getLongValue()+","   
		                +attsec.getOperation().getIntValue()+","                                      
		                +attsec.getAccessorLng()+")";       
		       qp.update(query);        
		        
		    }
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error adding permission to database: "+sqlExp.toString());
    }
    
    finally {
      if (qp != null) qp.close();      
    }
    
	}
  
  /*
	  	Remove the grant for an individual User Group, the list will contain only 1 item.
	  This is used to automatically remove a grant for a deleted user or user group
  */

  public void removeAttrGroupGrant(List list) throws OculusException
	{
  
  	  IQueryProcessor qp = null;
    IRConnection jdtC = null;    
    com.oculussoftware.system.sec.AttrGroupGrantAsc attsec = (com.oculussoftware.system.sec.AttrGroupGrantAsc)list.get(0);    
    try
    {
	      jdtC = getCRM().getDatabaseConnection(getObjectContext());
	      qp = jdtC.createProcessor();
	      String query = "DELETE FROM ATTRGROUPGRANT " 
	                   + "WHERE ACCESSORID="+attsec.getAccessorLng();                                      
	      qp.update(query);        
		    
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error deleting permission from database: "+sqlExp.toString());
    }
    
    finally {
      if (qp != null) qp.close();      
    }

  
	}
}