package com.oculussoftware.api.sysi.sec;
/*
* $Workfile: IAccessMgr.java $
* Create Date:  3-19-2000
* Description: Jave interface for an access control manager.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IAccessMgr.java $
 * 
 * *****************  Version 17  *****************
 * User: Apota        Date: 9/18/00    Time: 12:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 16  *****************
 * User: Apota        Date: 9/18/00    Time: 12:51p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Part of the Bug 2616 fix effort. Ability to remove indiviual grants for
 * any accessor
 * 
 * *****************  Version 14  *****************
 * User: Apota        Date: 9/12/00    Time: 12:05a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Refined AttrGroupGrant Methods to be user & group specific.
 * *****************  Version 13  *****************
 * User: Apota        Date: 6/01/00    Time: 5:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Removed the getDepartmentforms method
 * 
 * *****************  Version 12  *****************
 * User: Apota        Date: 6/01/00    Time: 9:54a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:33p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * BUG00086 - Broke out some checkPerms into accessor type specific ones.
 * Renamed grantPermission( ) to setAllPermission( ).  Changed semantics
 * of grantPermission( ) to do incremental grant instead of flush and
 * fill.
 * 
 * *****************  Version 10  *****************
 * User: Apota        Date: 5/12/00    Time: 11:05a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 9  *****************
 * User: Apota        Date: 5/12/00    Time: 9:41a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 8  *****************
 * User: Apota        Date: 5/10/00    Time: 10:04a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 5/09/00    Time: 4:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Sending IRElements instead of IRObjects to many of the methods.
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 5/01/00    Time: 2:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Added a checkPerms( ) that takes a userid instead of working on
 * 'current' user.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * checkPerms( ) and grantPermissions( ) now take an IRObject instead of
 * just and id.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 4/24/00    Time: 11:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Renamed methods.  Changed some param types.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 4/23/00    Time: 10:46p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Tweaks to method signatures to support 'current' user and specified
 * user methods.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/14/00    Time: 11:39a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * comments.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:47a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/sysi/sec
 * Initial creation.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/06/00    Time: 8:33a
 * Updated in $/Unfinished code/Jim's folder/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/sysi/sec
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/02/00    Time: 3:18p
 * Created in $/Unfinished code/Jim's folder/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/sysi/sec
 * backup
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.common.org.*;

import java.util.*;

/**
* An access control manager interface.  Concrete object is responsible for 
* setting and getting access control information for the applications.  UI and 
* business objects will call an IAccessMgr to determine the privileges of the
* login user.
*  
* NOTE: This interface is highly slanted towards the current access control scheme. 
*       Probably not possible to convert this to JNDI pass through in the future.
*
*/
public interface IAccessMgr
{
  public IPermSet getPermSetInfo( );
  
//  public void grantAttrGroupAccess(IRObject obj, IRAttributeGroup attrGroup, IAccessor accessor, boolean recursive) 
//    throws AccessException, OculusException;
  
  /**
  * Sets permissions for accessor, object.  Does a flush and fill on the permission
  * set for the accessor/object combination.
  */
  public void setAllPermissions(long accId, IRElement obj, Set grants)
    throws OculusException;
    
  public void setAllReposPermissions(long accId, Set grants)
    throws OculusException;

  public void grantPermissions(long accId, IRElement obj, Set grants)
    throws OculusException;
    
  public void grantReposPermissions(long accId, Set grants)
    throws OculusException;

  /*
  * 5/18/2000 JKC:
  *
  * Had to break out all methods that take an accessorid
  * from checkXXX( ) to checkUserXXX( ), checkRoleXXX( ), checkGroupXXX( )
  * because the rules are different for each accessor type, e.g., for Users,
  * must get list of groups; for Roles must check access to the object's class.
  *
  */
  
    
  /**
  * Load permissions granted to current user for specified object.  This method
  * heavily used by UI instrumentation.
  */
  public IGrantSet checkPerms(IRElement obj, IPermission perm[])
    throws OculusException;
    
  /** Load permissions granted to current user at the specified object */
  public IGrantSet checkReposPerms(IPermission perm[])
    throws OculusException;
    
  /**
  * Gets permissions for specified user, taking into account inherited permissions.
  * Used for ACL maintenance screens.
  */
  public IGrantSet checkUserPerms(long userid, IRElement obj, IPermission perm[])
    throws OculusException;
    
  /**
  * Gets permissions for specified group, taking into account inherited permissions.
  * Used for ACL maintenance screens.
  */
  public IGrantSet checkGroupPerms(long groupid, IRElement obj, IPermission perm[])
    throws OculusException;
    
  /** Used mostly for ACL maintenance. */
  public IGrantSet fillDirectPermissions(long userid, long objid, IPermission perm[])
    throws OculusException;
    
  public boolean hasAttrAccess(IRObject obj, IRAttrAccessGroup attrGroup, AttrGroupOper oper)
    throws OculusException;
  
  
  public void setUserAttrGroupGrants(List list, IIID id) throws OculusException;
  public void setGroupAttrGroupGrants(List list, IIID id) throws OculusException;
  /*
  	Add the grant for an individual User Group, the list will contain only 1 item.
  This is used to automatically grant a new user group with view/edit permission
  on the public/DEFAULT_ATTR_GROUP at the time of creating the new user group.  
  */
  public void addGroupAttrGroupGrant(List list, IIID id) throws OculusException;  
  /*
  	Remove the grant for an individual User Group, the list will contain only 1 item.
  This is used to automatically remove a grant for a deleted user or user group   
  */
  public void removeAttrGroupGrant(List list) throws OculusException;
  public List getAttrGroupGrants(IIID id) throws OculusException;
  
}