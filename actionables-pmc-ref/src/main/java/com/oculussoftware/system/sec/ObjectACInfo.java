package com.oculussoftware.system.sec;
/**
* $Workfile: ObjectACInfo.java $
* Create Date: 3-19-2000
* Description: Holds access control info for a given object context.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
* 
* $History: ObjectACInfo.java $
 * 
 * *****************  Version 14  *****************
 * User: Jcoles       Date: 9/14/00    Time: 7:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue 2207 - removed calls that were returning connections prematurely.
 * 
 * *****************  Version 13  *****************
 * User: Jcoles       Date: 9/12/00    Time: 5:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue 2189 support.
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 8/16/00    Time: 3:01p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Quick fix to _buildRoleList( ) to include enclosing parentheses in
 * String.
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 8/16/00    Time: 11:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Issue #1797 - fixed logic for building roles list.  Also added logic to
 * only recurse to parent object when permissions list includes a
 * recursive permission.
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 5/31/00    Time: 8:11p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Eliminated redunct _getGroupStringList method.
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 5/22/00    Time: 8:25a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 - completed adding logic to evaluate Roles of user.  Now
 * evaluates a Role's permissions on the class of the object.
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 - Significant changes since Roles are now treated differently.
 * Added special handling for Roles versus Users versus Groups.
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 5/09/00    Time: 4:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Using IRElement instead of IRObject in constructor.  Hangle IRElement
 * in _getParent( ) by returning repository ObjectACInfo object.
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 5/01/00    Time: 2:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Fleshed out getRoles( ) method.  Added support for getting permissions
 * of a specified user instead of the ACM context user.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:14p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Added recurision to getPerms( ).  Added constructor that takes
 * IRObject.  Added current User's Groups and Roles to accessor list when
 * check permissions.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 4/24/00    Time: 11:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Bug in insert logic.  Was skipping every other permission.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 4/23/00    Time: 10:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Added SQL logic to delete, insert.  Tweaked method syntax sematics.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/19/00    Time: 8:39p
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/system/sec
 * backup checkin.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/14/00    Time: 11:34a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Col names changes.
 * Exception throw clause change.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:51a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Initial creation.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/06/00    Time: 8:34a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/system/sec
*
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.common.process.*;

import com.oculussoftware.util.*;

import java.util.*;

/**
* The access control info for a given object.
*/
public class ObjectACInfo
{
  //-----------------------------------------------------------------------------
  // Private static variables
  //-----------------------------------------------------------------------------
  private static final String COL_PAROBJECTID = "PAROBJECTID";
  private static final String COL_PERMID      = "PERMISSIONID";

  // Table: ObjectRoleAssign
  private static final String COL_USERID = "USERID";
  private static final String COL_ROLEID = "ROLEID";
  
  //-----------------------------------------------------------------------------
  // Private instance variables
  //-----------------------------------------------------------------------------
  private AccessMgr _acm = null;
  private IRElement _obj = null;
  private IRClass   _objClass = null;
  private int       _objType = 0;
  
  private static final int BUSOBJ       = 1;
  private static final int MODELELEMENT = 2;
  private static final int CLASS        = 3;
  
  private long _oid;
  private Set _roleSet = null;
  
  //-----------------------------------------------------------------------------
  // Public constructor(s)
  //-----------------------------------------------------------------------------
  
  /**
  * Obj is the central object for which we are determining permissions.  It can
  * be a Product, Version, Folder, etc.  Can also be a Form.  Can also be a Class in the
  * case of managing a Role's permissions at a Class level.
  */
  public ObjectACInfo(AccessMgr acm, IRElement obj)
    throws OculusException
  {
    _acm = acm;
    _obj = obj;  // if obj == null, it represent the repository
    if ( obj == null ) {
      // IMPORTANT: if you change the line below, make sure _isRepos( ) is adjusted.
      // Currenty (4/30), and _oid equal to IDCONST.OCUREPOS is our way of knowing
      // we're at the repository level.
      _oid = IDCONST.OCUREPOS.getLongValue();
    }
    else {
      if (obj instanceof IBusinessObject) {
        _objClass = ((IBusinessObject) obj).getDefnObject().getBaseClass();
        _objType = BUSOBJ;
      }
      else if (obj instanceof IRClass) {
        _objType = CLASS;
      }
      else if (obj instanceof IRModelElement) {
        _objType = MODELELEMENT;
      }
    }
  }
  
  /** Use this when getting direct permissions only. */
  public ObjectACInfo(AccessMgr acm, long oid)
  {
    _acm = acm;
    _oid = oid;
  }
  //-----------------------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------------------
  
  public long getObjectId() 
    throws OculusException
  { 
    long retVal = 0;
    if (_obj != null) {
      retVal = _obj.getIID().getLongValue(); 
    }
    else {
      retVal = _oid;
    }
    return retVal;
  }
  
  public long getClassId()
    throws OculusException
  {
    long retVal;
    if (_objClass == null && _oid != IDCONST.OCUREPOS.getLongValue())
      throw new OculusException("Attempt to get Class id when Class has not been initialized.");
    
    if (_oid == IDCONST.OCUREPOS.getLongValue())
      retVal = IDCONST.REPOSITORY.getLongValue();
    else 
      retVal = _objClass.getIID().getLongValue();
    return retVal;
  }
  
  /**
  * Recursively check parent objects and fill in permissions.  Do so for current
  * user and check all Groups and Roles.
  */
  public void fillInhrPermissions(IGrantSet permSet, IPermission perms[])
    throws OculusException
  {
    fillInhrPermissions(new Long(_acm.getUserId()), _buildGroupList(_acm.getUserId()), permSet, perms);
  }

  /**
  * Fill all permissions including inherited for the specified accessor. Complicated since
  * the accessor may be a User or Group.
  * NOTE: Under currnet business rules (5/21/2000) does not make sense to use recursion on Roles.
  */
  public void fillAccessorInhrPermissions(long accId, IIID accTypeID, IGrantSet permSet, IPermission perms[])
    throws OculusException
  {
    // delegate to common method
    if (accTypeID == IDCONST.USER.getIIDValue()) {
      fillInhrPermissions(new Long(accId), _buildGroupList(accId), permSet, perms);
    }
    else if (accTypeID == IDCONST.GROUP.getIIDValue()) {
      fillInhrPermissions(null, Long.toString(accId), permSet, perms);
    }
    else {
      throw new OculusException("Attempt to get recursive permission grants for invalid accessor type.  Method only supports Users and Groups.");
    }
  }
  
  /**
  * Fill only the permissions direclty associated with the specified accessor. Doesn't care
  * what type (User, Group, Role) the accessor is because it doesn't take into
  * account associated Groups and Roles nor does it check permissions on parent objects.
  */
  public void fillAccessorDirectPermissions(long accId, IGrantSet permSet, IPermission perms[])
    throws OculusException
  {
    _sqlFillPermissions(Long.toString(accId), getObjectId(), permSet, perms);
  }
  
  //-----------------------------------------------------------------------------
  // Package instance methods
  //-----------------------------------------------------------------------------
  /**
  * Recursively looks for permissions in parent objects if needed, by creating a new
  * ObjectACInfo instance.  Used by other 'inherited' methods.
  * This is a package-level class so that other ObjectACInfos can call it.
  */
  void fillInhrPermissions(Long lUserId, String groups, IGrantSet permSet, IPermission perms[])
    throws OculusException
  {
    String all = "";
    if( lUserId != null )
      all = lUserId.toString();
    if( groups != null && groups.trim().length() > 0 ) {
      if ( all.length() > 0 )
        all = all + ", " + groups;
      else
        all = groups;
    }
    _sqlFillPermissions(all, getObjectId(), permSet, perms);
    if (lUserId != null)
    {
      String roles = _buildRoleList(lUserId.longValue());
      if (roles != null && roles.trim().length() > 0)
        _sqlFillPermissions(roles, getClassId(), permSet, perms );
    }
    // recursively check parents unless this is the root repository object
    // JKC 8/15/00 Only do parent check one permissions that are recursive in nature.
    IPermission ngrPerms[] = _getAllNonGrantedRecs(permSet, perms);
    if ( ngrPerms != null && ngrPerms.length > 0 && !_isRepos()) 
    {
      ObjectACInfo paci = _getParentNode();
      if (paci != null) {
        paci.fillInhrPermissions(lUserId, groups, permSet, ngrPerms);
      }
    }
  }
  
  /**
  * Get the Roles assigned to user for this object context.  Method
  * placed here versus AccessMgr since roles assignment is object context specific.
  */
  Set getRolesOfUser(long userid)
    throws OculusException
  {
    Set retSet = null;
    if ( _roleSet != null ) {
      retSet = _roleSet;
    }
    else {
      retSet = new HashSet();
      IQueryProcessor stmt = null;
      try
      {
        IRConnection repConn = _acm.getCRM().getDatabaseConnection(_acm.getObjectContext());
        stmt = repConn.createProcessor();
        String query = "SELECT DISTINCT " + COL_ROLEID
                      +" FROM OBJECTROLEASSIGN "
                      +" WHERE " + COL_PAROBJECTID + " IN " + _getParChildList( )
                      +" AND "+COL_USERID+" = " + userid;
        IDataSet results = stmt.retrieve(query);
        while (results.next())
        {
          long roleid = results.getLong(COL_ROLEID);
          retSet.add(_acm.getMC().getObj(IDCONST.PROCESSROLE, roleid));
        }
      }
      catch (ORIOException sqlExp)
      {
        throw new OculusException(sqlExp);
      }
      finally {
        if (stmt != null) 
          stmt.close();
      }
    }
    return retSet;
  }

  
  //-----------------------------------------------------------------------------
  // Private instance methods
  //-----------------------------------------------------------------------------

  private String _getParChildList( )
    throws OculusException
  {
    List all = new Vector();
    all.add(new Long(getObjectId()));
    IRElement elem = _getObj();
    if (elem != null && elem instanceof IProjectParent) 
    {
      IProjectParent pp = (IProjectParent) elem;
      List childs = pp.getAllChildOIDs();
      all.addAll(childs);
    }
    return "("+StringUtil.buildCommaDelList(all)+")";
  }
  
  /** Low-level guy takes accessor list and sees if specified permissions are granted to one of
  * the accessors for this object.
  */
  private void _sqlFillPermissions(String accessorSQLList, long objectID, IGrantSet permSet, IPermission perms[])
    throws OculusException
  {
    String permIdSQL = "";

    int i = 0;
    for( i=0; i < perms.length; i++)
    {
      if ( i > 0 )
        permIdSQL += ", ";
        
      permIdSQL += perms[i].getID();
    }
    
    IQueryProcessor stmt = null;
    try
    {
      IRConnection repConn = _acm.getCRM().getDatabaseConnection(_acm.getObjectContext());
      stmt = repConn.createProcessor();
      String query = "SELECT permissionId "
                    +" FROM PermissionGrant "
                    +" WHERE accessorId IN (" + accessorSQLList + ")"
                    +" AND " + COL_PAROBJECTID + " = " + objectID
                    +" AND " + COL_PERMID + " IN (" + permIdSQL + ")";
      IDataSet results = stmt.retrieve(query);
      while (results.next())
      {
        int permid = results.getInt(COL_PERMID);
        IPermission key = _acm.getPermSetInfo().getPermForId(permid);
        permSet.add(key);
      }
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException(sqlExp);
    }
    finally {
      if (stmt != null) 
        stmt.close();
    }
  }

  private IPermission[] _getAllNonGrantedRecs(IGrantSet gs, IPermission perms[])
  {
    IPermission retVal[] = null;
    List temp = new Vector();
    for( int idx = 0; idx < perms.length; idx ++) {
      IPermission perm = perms[idx];
      if(perm.getIsRecursive() && !gs.contains(perm)) {
        temp.add(perm);
      }
    }
    retVal = new IPermission[temp.size()];
    temp.toArray(retVal);
    return retVal;
  }
  
  private boolean _isRepos()
  {
    return (_oid == IDCONST.OCUREPOS.getLongValue());
  }

  /** Return full list containing specified user.and all of user's groups */
  private String _buildGroupList(long userId)
    throws OculusException
  {
    String retString = _acm.getGroupStringList(userId);
    return retString;
  }

  private String _buildRoleList(long userid)
    throws OculusException
  {
    String retString = "";
    Set sRoles = getRolesOfUser(userid);
    if (sRoles != null) {
      Iterator iRoles = sRoles.iterator();
      int i = 0;
      while (iRoles.hasNext()) {
        if (i > 0 )
          retString += ", ";
          
        IProcessRole role = (IProcessRole) iRoles.next();
        retString += role.getIID().getLongValue();
        i++;
      }
    }
    return retString;
  }
  
  private ObjectACInfo _getParentNode()
    throws OculusException
  {
    ObjectACInfo retObj = null;
    if (_obj == null)
      throw new OculusException("Attempt to get parent of repository object.");
      
    if (_obj instanceof IRObject) {
      IRElement pobj = ((IRObject) _obj).getParentObject(false);
      if (pobj == null || 
          (pobj.getIID().getLongValue() == _obj.getIID().getLongValue())) 
      {
        retObj = new ObjectACInfo(_acm, null);
      }
      else {
        retObj = new ObjectACInfo(_acm, pobj);
      }
    }
    else {
      retObj = new ObjectACInfo(_acm, null);
    }
    return retObj;
  }
  
  private IRElement _getObj()
    throws OculusException
  {
    return _obj;
  }
  
}