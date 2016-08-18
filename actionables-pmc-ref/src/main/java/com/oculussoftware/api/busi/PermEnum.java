package com.oculussoftware.api.busi;
/**
* $Workfile: PermEnum.java $
* Create Date: 4-06-2000
* Description: Provides an enum of valid permissions for current set of business 
*              objects.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: PermEnum.java $
 * 
 * *****************  Version 24  *****************
 * User: Jcoles       Date: 9/22/00    Time: 4:11p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 2604 - rpt privs for mkt group.
 * 
 * *****************  Version 23  *****************
 * User: Isyed        Date: 9/12/00    Time: 11:26a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 02493  "Destroy Unshared Features"--global permissions to 
 *  "Delete Unshared Features"
 * 
 * *****************  Version 22  *****************
 * User: Jcoles       Date: 9/12/00    Time: 11:23a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 2173.  Reaction permissions.
 * 
 * *****************  Version 21  *****************
 * User: Jcoles       Date: 9/11/00    Time: 8:33p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Isseu 2428  - terminology change for my concepts.
 * 
 * *****************  Version 20  *****************
 * User: Jcoles       Date: 9/11/00    Time: 8:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Issue 2465 - name change.  Also started on issue 2173 but waiting on
 * further clarification.
 * 
 * *****************  Version 19  *****************
 * User: Isyed        Date: 9/11/00    Time: 2:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * BUG02459  config. Specification forms needs to be config. MRD forms 
 * 
 * *****************  Version 18  *****************
 * User: Jcoles       Date: 8/16/00    Time: 11:12a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 
 * *****************  Version 17  *****************
 * User: Jcoles       Date: 8/11/00    Time: 4:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * New report permissions.  Feature id #1628.
 * 
 * *****************  Version 16  *****************
 * User: Jcoles       Date: 8/11/00    Time: 10:56a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Added new permission SIZING_EDIT for Versions.  Made default grant to
 * Market Mgr and Engr Mgr.
 * 
 * *****************  Version 15  *****************
 * User: Znemazie     Date: 7/05/00    Time: 11:53a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 1252 + 1253 : changed spelling of Alternatives and Interrupt
 * 
 * *****************  Version 14  *****************
 * User: Jcoles       Date: 6/15/00    Time: 9:28a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Per issue #804, added grant to CONFIG_MARKET_INPUT to 'Super' and
 * 'Power' groups.
 * 
 * *****************  Version 13  *****************
 * User: Jcoles       Date: 5/31/00    Time: 5:51p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Added VERSION_REORDER as a Product-level permission to support basic
 * permission config feature.
 * 
 * *****************  Version 12  *****************
 * User: Sshafi       Date: 5/26/00    Time: 11:21a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 5/26/00    Time: 10:56a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 5/22/00    Time: 6:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Feature #2589.  Synced the enums with 5/20/2000 document from
 * Marketing.
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 5/22/00    Time: 8:16a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * BUG00086 - changed method getFactoryRoleGrantsForClass to
 * getFactoryPermissionsForRoleClass( ).  It now returns set of
 * IPermissions, not IPermissionGrants.
 * 
 * Made Groups active for Product-level permissions.
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * BUG00086 - Rearrange much for readability.  Changed the permission set.
 * Added logic for enumerating the pre-configured / factory settings for
 * permissions on Groups and Roles.
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 5/09/00    Time: 4:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Many changes reflecting refactoring of permissions.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:18p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Many changes to this list of permissions for each class of item.  Added
 * logic to push permissions up to 'parent' container classes to support
 * inheriting permissions from parent.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 4/24/00    Time: 11:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Slight change in static init of hashes.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 4/23/00    Time: 10:45p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Refined perm list.  Added class-specific perm lists.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/14/00    Time: 11:40a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Refined permissions enums.
 * Added method to get enum of permissions.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:59a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi
 * Initial creation.
 * 
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;

import com.oculussoftware.system.sec.*;

import java.util.*;

/** 
* Provides an enum of valid permissions for current set of business 
* objects.
*/
public class PermEnum extends Permission
{
  private static List _reposPerms = null;
  private static List _productPerms = null;
  private static List _versionPerms = null;
  private static List _categoryPerms = null;
  private static List _featurePerms = null;
  private static List _featureLinkPerms = null;
  private static List _stdCollPerms = null;
  private static List _compassFolderPerms = null;
  private static List _mktInputPerms = null;
  //-----------------------------------------------------------------------------
  // Public constants
  //-----------------------------------------------------------------------------
  
  // These constants represent a superset of all permissions for all the various types
  // of objects in the system.
  
  // next number to start with: 140
  
  // common
  public static final IPermission OWNER        = new PermEnum(5, null, "Owner", "", true);
  public static final IPermission LINK_ADD      = new PermEnum(115, null, "Create Hyperlinks and Attachments", "", true);
  public static final IPermission LINK_DESTROY  = new PermEnum(116, null, "Destroy Hyperlinks and Attachments", "", true);
  public static final IPermission DISC_ADD      = new PermEnum(120, null, "Create Discussions", "", true);
  
  // item-level: these apply directly to the item they are granted upon
  public static final IPermission ITEM_VIEW      = new PermEnum(101, null, "View", "");
  public static final IPermission ITEM_EDIT      = new PermEnum(102, null, "Edit", "");

  // data repository level...
  public static final IPermission PROD_ADD     = new PermEnum(1, null, "Create Products", "");
  public static final IPermission PROD_VIEW    = new PermEnum(2, null, "View Products", "");
  public static final IPermission SCOLL_ADD    = new PermEnum(10, null, "Create Standards Collections", "");
  public static final IPermission SCOLL_VIEW   = new PermEnum(12, null, "View Standards Collections", "");
  public static final IPermission FOLD_ADD     = new PermEnum(21, null, "Create Compass Folders", "");
  public static final IPermission FOLD_VIEW    = new PermEnum(22, null, "View Compass Folders", "");
  public static final IPermission REACTION_EDIT    = new PermEnum(140, null, "Create and Edit Reactions to Market Inputs", "");
  public static final IPermission REACTION_VIEW    = new PermEnum(141, null, "View Reactions to Market Inputs", "");
  public static final IPermission CONFIGURE_ALL    = new PermEnum(23, null, "Configure All Forms and Workflows", "");
  public static final IPermission CONFIG_SPEC    = new PermEnum(24, null, "Configure MRD Forms", "");
  public static final IPermission CONFIG_MARKET_INPUT    = new PermEnum(25, null, "Configure Market Input Forms", "");
  public static final IPermission CONFIG_OTHER_COMPASS    = new PermEnum(26, null, "Configure non-Input Compass Forms", "");
  public static final IPermission CONFIG_PROFILE    = new PermEnum(27, null, "Configure Profile Forms", "");
  public static final IPermission CONFIG_ATTR_GRPS    = new PermEnum(28, null, "Configure Attribute Groups", "");
  public static final IPermission CONFIG_PROCESS    = new PermEnum(29, null, "Configure Processes", "");
  public static final IPermission ADMIN_SIMPLE    = new PermEnum(40, null, "Maintain Groups, Organizations, Settings, and Global Permissions", "");
  public static final IPermission USER_MAINT      = new PermEnum(41, null, "Maintain System Users", "");
  public static final IPermission FEAT_DESTROY_UNSHARED = new PermEnum(53, null, "Delete Unshared Features", "");
  public static final IPermission RPT_VIEWSTD = new PermEnum(130, null, "View Standard Reports", "");
  public static final IPermission RPT_VIEWCUSTOM = new PermEnum(131, null, "View Custom Reports", "");
  public static final IPermission RPT_DESIGNCUSTOM = new PermEnum(132, null, "Design Custom Reports", "");
  static {
    // global repository permissions
    _reposPerms = new MyList();
    _reposPerms.add(OWNER);
    _reposPerms.add(CONFIGURE_ALL);
    _reposPerms.add(CONFIG_SPEC);
    _reposPerms.add(CONFIG_MARKET_INPUT);
    _reposPerms.add(CONFIG_OTHER_COMPASS);
    _reposPerms.add(CONFIG_PROFILE);
    _reposPerms.add(CONFIG_ATTR_GRPS);
    _reposPerms.add(CONFIG_PROCESS);
    _reposPerms.add(USER_MAINT);
    _reposPerms.add(ADMIN_SIMPLE);
    _reposPerms.add(PROD_ADD);
    _reposPerms.add(PROD_VIEW);
    _reposPerms.add(FEAT_DESTROY_UNSHARED);
    _reposPerms.add(SCOLL_ADD);
    _reposPerms.add(SCOLL_VIEW);
    _reposPerms.add(FOLD_ADD);
    _reposPerms.add(FOLD_VIEW);
    _reposPerms.add(REACTION_EDIT);
    _reposPerms.add(REACTION_VIEW);
    _reposPerms.add(RPT_VIEWSTD);
    _reposPerms.add(RPT_VIEWCUSTOM);
    _reposPerms.add(RPT_DESIGNCUSTOM);
  }
  
  // product-specific
  public static final IPermission VERSION_ADD     = new PermEnum(30, null, "Create Versions", "");
  public static final IPermission VERSION_VIEW    = new PermEnum(31, null, "View Versions", "");
  public static final IPermission VERSION_REORDER = new PermEnum(32, null, "Re-Order Versions", "");
  static {
    // product perms...
    _productPerms  = new MyList();
    _productPerms.add(OWNER);
    _productPerms.add(ITEM_VIEW);
    _productPerms.add(ITEM_EDIT);
    _productPerms.add(VERSION_ADD);
    _productPerms.add(VERSION_VIEW);
    _productPerms.add(VERSION_REORDER);
    _productPerms.add(LINK_ADD);
    _productPerms.add(LINK_DESTROY);
    _productPerms.add(DISC_ADD);
  }

  // version-specific
  public static final IPermission SPEC_EDIT    = new PermEnum(51, null, "Create and Edit Content", "");
  public static final IPermission SPEC_VIEW    = new PermEnum(52, null, "View Content", "");
  public static final IPermission ENGSPEC_EDIT      = new PermEnum(200, null, "Edit Engineering Links, Attachments, and Alternatives", "");
  public static final IPermission POT_SPEC_VIEW    = new PermEnum(70, null, "View My Concepts", "");
  public static final IPermission POT_SPEC_EDIT    = new PermEnum(71, null, "Edit My Concepts", "");
  public static final IPermission BSLN_ADD      = new PermEnum(35, null, "Create Baselines", "");
  public static final IPermission INTERUPT_PROC = new PermEnum(36, null, "Interrupt Process", "");
  public static final IPermission VERTEAM_EDIT  = new PermEnum(37, null, "Edit Version Team", "");
  public static final IPermission EXPORT_TO_PROJ  = new PermEnum(38, null, "Export to MS Project", "");
  public static final IPermission SIZING_EDIT  = new PermEnum(39, null, "Edit Sizing Estimate", "");
  static {
    // version perms...
    _versionPerms = new MyList();
    _versionPerms.add(OWNER);
    _versionPerms.add(ITEM_VIEW);
    _versionPerms.add(ITEM_EDIT);
    _versionPerms.add(SPEC_EDIT);
    _versionPerms.add(SPEC_VIEW);
    _versionPerms.add(POT_SPEC_EDIT);
    _versionPerms.add(POT_SPEC_VIEW);
    _versionPerms.add(BSLN_ADD);
    _versionPerms.add(LINK_ADD);
    _versionPerms.add(LINK_DESTROY);
    _versionPerms.add(ENGSPEC_EDIT);
    _versionPerms.add(DISC_ADD);
    _versionPerms.add(VERTEAM_EDIT);
    _versionPerms.add(SIZING_EDIT);
    _versionPerms.add(INTERUPT_PROC);
    _versionPerms.add(EXPORT_TO_PROJ);
  }

  static {
    // category perms...
    _categoryPerms = new MyList();
    _categoryPerms.add(OWNER);
    
    // feature perms...
    _featurePerms = new MyList();
    _featurePerms.add(OWNER);

    // feature link perms...
    _featureLinkPerms = new MyList();
    _featureLinkPerms.add(OWNER);
    
  }
   
  // compass folder-specific
  public static final IPermission CONTENT_ADD    = new PermEnum(55, null, "Create Content", "");
  public static final IPermission CONTENT_EDIT_OWN = new PermEnum(56, null, "Edit Own Content", "");
  public static final IPermission CONTENT_EDIT_ALL = new PermEnum(57, null, "Edit All Content", "");
  public static final IPermission CONTENT_VIEW    = new PermEnum(58, null, "View Content", "");
  public static final IPermission SUBFOLD_ADD     = new PermEnum(59, null, "Add Sub-Folders", "");
  static {
    // compass folder perms...
    _compassFolderPerms  = new MyList();
    _compassFolderPerms.add(OWNER);
    _compassFolderPerms.add(CONTENT_VIEW);
    _compassFolderPerms.add(CONTENT_EDIT_ALL);
    _compassFolderPerms.add(CONTENT_EDIT_OWN);
    _compassFolderPerms.add(CONTENT_ADD);
    _compassFolderPerms.add(SUBFOLD_ADD);
  }

  // standard collection specific
  public static final IPermission SUBCOLL_ADD     = new PermEnum(60, null, "Add Sub-Collections", "");
  public static final IPermission COLL_VIEW      = new PermEnum(105, null, "View Contents", "Enables viewing of all Collection and sub-Collection content.", true);
  public static final IPermission COLL_EDIT      = new PermEnum(106, null, "Edit Contents", "Enables editting of all Collection and sub-Collection content.", true);
  static {
    // std coll perms...
    _stdCollPerms  = new MyList();
    _stdCollPerms.add(OWNER);
    _stdCollPerms.add(COLL_VIEW);
    _stdCollPerms.add(COLL_EDIT);
    _stdCollPerms.add(SUBCOLL_ADD);
  }

  // form-specific
  public static final IPermission FORM_USE     = new PermEnum(65, null, "Enter Input with Form", "");
  
  //-----------------------------------------------------------------------------
  // Private class methods and variables
  //-----------------------------------------------------------------------------
  private static String _initErrMsg = null;
  private static void _initErr(String msg) {
    if (_initErrMsg == null) _initErrMsg = "";
    _initErrMsg = _initErrMsg.concat(msg);
  }
  
  /**
  * Here we statically enumerate the permissions that the pre-configured roles have.
  * Role access is defined on the 'class' of object, not on the instances of the 
  * objects like other permissions.  Therefore the 'parObjectId' for a Role permission
  * grant is a base class.
  *
  * NOTE: Data initialization should use this permission set to load data.  This 
  *       Perm set will also enable admins to 'restore defaults'.
  */
  private static Set _factoryRoleGrants = new HashSet( );
  static {
    IDCONST cls = IDCONST.PRODUCTVERSION;
    IDCONST role;
    // MM
    role = IDCONST.MKTMGRROLE;
    addRoleGrant(cls, ITEM_VIEW, role);
    addRoleGrant(cls, ITEM_EDIT, role);
    addRoleGrant(cls, SPEC_EDIT, role);
    addRoleGrant(cls, SPEC_VIEW, role);
    addRoleGrant(cls, POT_SPEC_EDIT, role);
    addRoleGrant(cls, POT_SPEC_VIEW, role);
    addRoleGrant(cls, BSLN_ADD, role);
    addRoleGrant(cls, INTERUPT_PROC, role);
    addRoleGrant(cls, VERTEAM_EDIT, role);
    addRoleGrant(cls, SIZING_EDIT, role);
    addRoleGrant(cls, EXPORT_TO_PROJ, role);
    addRoleGrant(cls, LINK_ADD, role);
    addRoleGrant(cls, DISC_ADD, role);
    // EM
    role = IDCONST.ENGMGRROLE;
    addRoleGrant(cls, ITEM_VIEW, role);
    addRoleGrant(cls, SPEC_VIEW, role);
    addRoleGrant(cls, BSLN_ADD, role);
    addRoleGrant(cls, INTERUPT_PROC, role);
    addRoleGrant(cls, VERTEAM_EDIT, role);
    addRoleGrant(cls, SIZING_EDIT, role);
    addRoleGrant(cls, EXPORT_TO_PROJ, role);
    addRoleGrant(cls, ENGSPEC_EDIT, role);
    addRoleGrant(cls, LINK_ADD, role);
    addRoleGrant(cls, DISC_ADD, role);
    // Engr
    role = IDCONST.ENGROLE;
    addRoleGrant(cls, ITEM_VIEW, role);
    addRoleGrant(cls, SPEC_VIEW, role);
    addRoleGrant(cls, ENGSPEC_EDIT, role);
    addRoleGrant(cls, LINK_ADD, role);
    addRoleGrant(cls, DISC_ADD, role);
    // VWF reviewer
    role = IDCONST.VERSIONTEAMROLE;
    addRoleGrant(cls, ITEM_VIEW, role);
    addRoleGrant(cls, SPEC_VIEW, role);
    addRoleGrant(cls, LINK_ADD, role);
    addRoleGrant(cls, DISC_ADD, role);
  }
  private static void addRoleGrant(IDCONST cls, IPermission perm, IDCONST role) 
//    throws OculusException
  {
    List valid = getValidPermissions(cls);
    if ( ! valid.contains(perm) ) _initErr("Permission <"+perm.getName()+"> not valid for class id <"+cls.getLongValue()+">.");
    _factoryRoleGrants.add(new PermissionGrant(cls.getIIDValue(), perm, role.getIIDValue(), false));
  }
    
  /**
  * Here we statically enumerate the permissions that the pre-configured Groups have
  * at the repository level.
  *
  * NOTE: Data initialization should use this permission set to load data.  This 
  *       Perm set will also enable admins to 'restore defaults'.
  */
  private static Set _factoryGroupPerms = new HashSet( );
/*
  addGroup(context, IDCONST.USER_GROUP1, IDCONST.GROUP, "Admin", "");
  addGroup(context, IDCONST.USER_GROUP2, IDCONST.GROUP,"Super", "");
  addGroup(context, IDCONST.USER_GROUP3, IDCONST.GROUP,"Power", "");
  addGroup(context, IDCONST.USER_GROUP4, IDCONST.GROUP,"Standard", "");
  addGroup(context, IDCONST.USER_GROUP5, IDCONST.GROUP,"Limited", "");
*/
  static {
    IDCONST group;
    group = IDCONST.USER_GROUP1;      // admin
    addGroupGrant(OWNER, group);
    addGroupGrant(USER_MAINT, group);
    addGroupGrant(CONFIG_PROCESS, group);
    addGroupGrant(CONFIG_MARKET_INPUT, group);
    addGroupGrant(CONFIG_OTHER_COMPASS, group);
    addGroupGrant(CONFIG_SPEC, group);
    addGroupGrant(CONFIG_ATTR_GRPS, group);
    addGroupGrant(CONFIG_PROFILE, group);
    addGroupGrant(PROD_ADD, group);
    addGroupGrant(PROD_VIEW, group);
    addGroupGrant(FOLD_ADD, group);
    addGroupGrant(FOLD_VIEW, group);
    addGroupGrant(SCOLL_ADD, group);
    addGroupGrant(SCOLL_VIEW, group);

    group = IDCONST.USER_GROUP2;  // power
    addGroupGrant(USER_MAINT, group);
    addGroupGrant(CONFIG_MARKET_INPUT, group);
    addGroupGrant(CONFIG_SPEC, group);
    addGroupGrant(CONFIG_PROFILE, group);
    addGroupGrant(PROD_ADD, group);
    addGroupGrant(PROD_VIEW, group);
    addGroupGrant(FOLD_ADD, group);
    addGroupGrant(FOLD_VIEW, group);
    addGroupGrant(SCOLL_ADD, group);
    addGroupGrant(SCOLL_VIEW, group);
    addGroupGrant(RPT_VIEWSTD, group);
    addGroupGrant(RPT_VIEWCUSTOM, group);
    addGroupGrant(RPT_DESIGNCUSTOM, group);
    
    group = IDCONST.USER_GROUP3;  // general
    addGroupGrant(CONFIG_MARKET_INPUT, group);
    addGroupGrant(PROD_VIEW, group);
    addGroupGrant(FOLD_VIEW, group);
    addGroupGrant(SCOLL_VIEW, group);
    addGroupGrant(RPT_VIEWSTD, group);
    addGroupGrant(RPT_VIEWCUSTOM, group);
    addGroupGrant(RPT_DESIGNCUSTOM, group);
    
    group = IDCONST.USER_GROUP4;  // mkt
    addGroupGrant(PROD_VIEW, group);
    addGroupGrant(FOLD_VIEW, group);
    addGroupGrant(REACTION_VIEW, group);
    addGroupGrant(REACTION_EDIT, group);
    addGroupGrant(SCOLL_VIEW, group);
    addGroupGrant(RPT_VIEWSTD, group);
    addGroupGrant(RPT_VIEWCUSTOM, group);
    addGroupGrant(RPT_DESIGNCUSTOM, group);
    
    group = IDCONST.USER_GROUP5;  // engr
    addGroupGrant(REACTION_VIEW, group);
    
    group = IDCONST.USER_GROUP6;  // external
    // ??
  }
  private static void addGroupGrant(IPermission perm, IDCONST group) 
  {
    List valid = getValidPermissions(IDCONST.REPOSITORY);
    if ( ! valid.contains(perm) ) _initErr("Permission <"+perm.getName()+"> not valid for for Repository level.");
    _factoryGroupPerms.add(new PermissionGrant(IDCONST.OCUREPOS.getIIDValue(), perm, group.getIIDValue(), false));
  }
  
  //-----------------------------------------------------------------------------
  // Public class methods
  //-----------------------------------------------------------------------------
  public static boolean isValidPermission(IPermission perm, IDCONST cls)
  {
	  List valid = getValidPermissions(cls);
      if (valid == null)
      	return false;
	  return valid.contains(perm);
  }
  
  public static boolean areAllValidPermissions(Set gs, IDCONST cls)
  {
  	boolean allOK = true;
    Iterator it = null;
    
    if (gs != null)
    {
	  it = gs.iterator();
      if (it != null)
      {
        while (it != null && it.hasNext() && allOK)
        {
          IPermission perm = (IPermission)it.next();
		  if (!isValidPermission(perm,cls))
            allOK = false;
        }    
      }    
    }  
    return allOK;
  }
  
  public static String getInitErr() { return _initErrMsg; }
  public static List getValidPermissions(IDCONST classIID)
  {
    List retList = null;
    if (classIID == IDCONST.REPOSITORY) {
      retList = _reposPerms;
    }
    else if (classIID == IDCONST.PRODUCT) {
      retList = _productPerms;
    }
    else if (classIID == IDCONST.PRODUCTVERSION) {
      retList = _versionPerms;
    }
    else if (classIID == IDCONST.CATEGORY) {
      retList = _categoryPerms;
    }
    else if (classIID == IDCONST.FEATURECATEGORYLINK) {
      retList = _featureLinkPerms;
    }
    else if (classIID == IDCONST.FEATURE) {
      retList = _featurePerms;
    }
    else if (classIID == IDCONST.STANDARDSCOLLECTION) {
      retList = _stdCollPerms;
    }
    else if (classIID == IDCONST.FOLDER) {
      retList = _compassFolderPerms;
    }
    return retList;
  }
  
  /** Returns the set of permissions for a Role/Class */
  public static Set getFactoryPermissionsForRoleClass(IDCONST roleId, IDCONST cls)
  {
    Set retSet = new HashSet();
    Iterator iAllGrants = _factoryRoleGrants.iterator();
    while (iAllGrants.hasNext()) {
      IPermissionGrant grant = (IPermissionGrant) iAllGrants.next();
      if (grant.getAccessorID() == roleId.getIIDValue() &&
          grant.getContextObjID() == cls.getIIDValue())
      {
        retSet.add(grant.getPermission());
      }
    }
    return retSet;
  }

  public static Set getAllFactoryRoleGrants() 
  {
    Set retSet = new HashSet();
    // don't jack with my sets...
    retSet.addAll(_factoryRoleGrants);
    return retSet;
  }
  public static Set getAllFactoryGroupGrants() 
  {
    Set retSet = new HashSet();
    // don't jack with my sets...
    retSet.addAll(_factoryGroupPerms);
    return retSet;
  }

  
  /**
  * isConfigAccessor( ) provides the definitive mapping of which types of accessors
  * have configurable permissions for which class of items.  
  *
  * Returns true if accessor class has configurable permissions for context class.
  */
  public static boolean isConfigAccessor(IDCONST accessorClassId, IDCONST contextClassId)
  {
    boolean retVal = false;
    if (accessorClassId == IDCONST.USER)
    {
      retVal = true;
    }
    else if (accessorClassId == IDCONST.GROUP) {
      if(contextClassId == IDCONST.REPOSITORY ||
        contextClassId == IDCONST.FOLDER ||
        contextClassId == IDCONST.PRODUCT
      )
      { retVal = true; }
    }
    else if (accessorClassId == IDCONST.PROCESSROLE) {
      // As of 5/19/2000, Role permissions are not set directly
      // on objects (of any class).
    }
    return retVal;
  }
  
  
  //-----------------------------------------------------------------------------
  // Private instance variables
  //-----------------------------------------------------------------------------
  
  //-----------------------------------------------------------------------------
  // Private constructor
  //-----------------------------------------------------------------------------
  private PermEnum(int id, IGUID guid, String name, String desc, boolean isRecur)
  {
    super(id, guid, name, desc, isRecur);
  }
  
  private PermEnum(int id, IGUID guid, String name, String desc)  {
    this(id, guid, name, desc, false);
  }
  
  /** Wraps ArrayList to prevent duplicates */
  private static class MyList extends ArrayList
  {
    public boolean add(Object obj)
    {
      if (!this.contains(obj)) {
        return super.add(obj);
      }
      else 
      return false;
    }
    public boolean addAll(Collection coll)
    {
      Iterator it = coll.iterator();
      while (it.hasNext()) {
        add(it.next());
      }
      return true;
    }
  }
}