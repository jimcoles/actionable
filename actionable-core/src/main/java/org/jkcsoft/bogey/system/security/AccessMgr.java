/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.system.security;

import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.BusinessObject;
import org.jkcsoft.bogey.model.Element;
import org.jkcsoft.bogey.model.IDCONST;
import org.jkcsoft.bogey.model.User;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.MC;
import org.jkcsoft.bogey.system.SOM;
import org.jkcsoft.java.util.Strings;

import java.util.*;

/**
 * Central implementation of user access control logic.
 * <p>
 * General approach:
 * <p>
 * One Access Control Manager (ACM) instance per user.
 * <p>
 * AccessMgr uses ObjectACInfo objects to help determine permissions for a specific
 * business object. A distinct ObjectACInfo is instantiated for each object.  If one or
 * more of the permissions being determined are 'recursive' (inheritable from parent)
 * then the ObjectACInfo knows how to create another ObjectACInfo corresponding to the
 * parent business object.
 */
public class AccessMgr {
    //-----------------------------------------------------------------------------
    // Private static variables
    //-----------------------------------------------------------------------------
//    private static final String COL_OBJECTID = "OBJECTID";
//    private static final String COL_PAROBJID = "PAROBJECTID";
//    private static final String COL_PERMID = "PERMISSIONID";
//    private static final String COL_ACCESSORID = "ACCESSORID";

    private static final String MSG_NOT_OWNER = "You must have the OWNER permission on an item or be " +
            "the Admin user in order to set the item's permissions.";
    private static Set _autoGrants = new HashSet();

    static {
        _autoGrants.add(PermEnum.ITEM_VIEW);
    }

    //-----------------------------------------------------------------------------
    // Private instance variables
    //-----------------------------------------------------------------------------
    private SOM som;
    private MC _MC;
    private Map _objContextTable = new HashMap();
    private Oid userOid;
    private ObjectACInfo _rootObject;
    private PermissionSet _permSet;
    private String _strGroups;

    //-----------------------------------------------------------------------------
    // Public instance methods
    //-----------------------------------------------------------------------------
    private Boolean _isAdm;
    private Boolean _isSys;

    //-----------------------------------------------------------------------------
    // Public constructor(s)
    //-----------------------------------------------------------------------------
    public AccessMgr(Oid userOid, PermissionSet ps) throws AppException {
        this._permSet = ps;
        this.userOid = userOid;
    }

    //-----------------------------------------------------------------------------
    // Private static methods
    //-----------------------------------------------------------------------------
    private static void _stdValidate() throws AppException {
        if (PermEnum.getInitErr() != null)
            throw new AppException("Initialization of the Access Control subsystem failed a static initialization " +
                                           "test." +
                                           "  Error messages: \n" + PermEnum.getInitErr());
    }

    public PermissionSet getPermSetInfo() {
        return _permSet;
    }

    /**
     * User id accessor
     */
    public long getUserId() {
        return userOid.getLongValue();
    }

    public GrantSet fillDirectPermissions(long accessorid, long objId, Permission perms[])
            throws AppException {
        GrantSet retSet = _initPermSet(perms);

        ObjectACInfo oai = new ObjectACInfo(this, objId);
        oai.fillAccessorDirectPermissions(accessorid, retSet, perms);
        return retSet;
    }

    public GrantSet checkReposPerms(Permission perms[])
            throws AppException {
        _stdValidate();
        GrantSet retSet = _initPermSet(perms);
        if (_isTheAdmin() || _checkReposPerm(PermEnum.OWNER)) {
            retSet = _initPermSet(perms);
            retSet.addAll(Arrays.asList(perms));
        } else {
            retSet = _checkReposPerms(perms);
        }
        return retSet;
    }

    /**
     * Checks for 'current' user with recursion to parent objects.  This is the method
     * most heavily used by UI or BO instrumentation checking for access.
     */
    public GrantSet checkPerms(Element obj, Permission perms[])
            throws AppException {
        _stdValidate();
        if (obj == null) throw new AppException("Null object.");

        GrantSet retSet = _initPermSet(perms);
        if (_isTheAdmin() || _checkPerm(obj, PermEnum.OWNER)) {
            retSet = _initPermSet(perms);
            retSet.addAll(Arrays.asList(perms));
        } else {
            retSet = _checkPerms(obj, perms);
        }
        return retSet;
    }

    /**
     * Similar to checkPerms( ) except applies to specified user.  Used by admin screens to show
     * inherited permissions.
     */
    public GrantSet checkUserPerms(long userid, Element obj, Permission perms[])
            throws AppException {
        _stdValidate();
        GrantSet retSet = _initPermSet(perms);
        if (_isTheAdmin(userid) || _checkUserPerm(userid, obj, PermEnum.OWNER)) {
            retSet = _initPermSet(perms);
            retSet.addAll(Arrays.asList(perms));
        } else {
            retSet = _checkUserPerms(userid, obj, perms);
        }
        return retSet;
    }

    public GrantSet checkGroupPerms(long groupid, Element obj, Permission perms[])
            throws AppException {
        _stdValidate();
        GrantSet retSet = _initPermSet(perms);
        if (_checkGroupPerm(groupid, obj, PermEnum.OWNER)) {
            retSet.addAll(Arrays.asList(perms));
        } else {
            retSet = _checkGroupPerms(groupid, obj, perms);
        }
        return retSet;
    }

    /**
     * Does flush and fill of permission grant set.
     */
    public void setAllReposPermissions(long accId, Set perms)
            throws AppException {
        _stdValidate();

//        IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
//         flush...
//        _sqlDeleteAll(repConn, accId, IDCONST.OCUREPOS.getLongValue());
//         fill...
//        _grantReposPermissions(repConn, accId, perms);
    }

    /**
     * Does flush and fill of permission grant set.
     */
    public void setAllPermissions(long accId, Element obj, Set perms)
            throws AppException {
        _stdValidate();

//  //      IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
//        // flush...
//        _sqlDeleteAll(repConn, accId, obj.getOid().getLongValue());
//        // fill...
//        _grantObjPermissions(repConn, accId, obj, perms);
    }

    /**
     * Adds permission grant set.  Does not delete any existin perm grants.
     */
    public void grantReposPermissions(long accId, Set perms)
            throws AppException {
        _stdValidate();
        // only allow grant if current user has admin permission for the object
//    if (!_checkReposPerm(PermEnum.OWNER) && !_isTheAdmin())
//      throw new AppException(MSG_NOT_OWNER);

//        IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
//        _grantReposPermissions(repConn, accId, perms);
    }

    /**
     * Adds permission grant set.  Does not delete any existing perm grants.
     */
    public void grantPermissions(long accId, Element obj, Set perms)
            throws AppException {
        _stdValidate();
        // only allow grant if current user has admin permission for the object
//    if (!_checkPerm(obj, PermEnum.OWNER) && !_isTheAdmin() && !_isTheSys())
//      throw new AppException(MSG_NOT_OWNER);

//        IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
//        _grantObjPermissions(repConn, accId, obj, perms);
    }


    //-----------------------------------------------------------------------------
    // Package instance methods
    //-----------------------------------------------------------------------------

    public void setUserAttrGroupGrants(List list, Oid id) throws AppException {
        setAttrGroupGrants(list, id, "AppUser");
    }

    ;

    public void setGroupAttrGroupGrants(List list, Oid id) throws AppException {
        setAttrGroupGrants(list, id, "UserGroup");
    }

    ;

    protected void setAttrGroupGrants(List list, Oid id, String tableName) throws AppException {
        // TODO db save
    }

    public List getAttrGroupGrants(Oid id) throws AppException {
        List list = new ArrayList();
        // TODO db query
        return list;
    }


    //-----------------------------------------------------------------------------
    // Private instance methods
    //-----------------------------------------------------------------------------


    /**
     * Helper accessor method
     */
    User getUser() throws AppException {
        User retVal = null;
        retVal = _getUser(getUserId());
        return retVal;
    }

    User getUser(long userId) throws AppException {
        return _getUser(userId);
    }

    /** */
    MC getMC() {
        return _MC;
    }

    /**
     * Returns set of group IIDs.
     * NOTE: Might not need to be a set if we continue with rule that a User has
     * only one Group, but that rule might change to MANY Groups.
     */

    private Set _getGroupsOfUser(long userId) throws AppException {
        Set retSet = new HashSet();
        // TODO db query group ids
        return retSet;
    }

    /**
     * Internal method does not check for admin or ownership.
     */
    private GrantSet _checkPerms(Element obj, Permission perms[])
            throws AppException {
        if (obj == null) throw new AppException("Null object.");

        GrantSet retSet = _initPermSet(perms);
        ObjectACInfo oai = new ObjectACInfo(this, obj);
        oai.fillInhrPermissions(retSet, perms);
        return retSet;
    }

    /**
     * Internal method does not check for admin or ownership.
     */
    private GrantSet _checkReposPerms(Permission perms[])
            throws AppException {
        GrantSet retSet = _initPermSet(perms);
        ObjectACInfo oai = new ObjectACInfo(this, null);
        oai.fillInhrPermissions(retSet, perms);
        return retSet;
    }

    /**
     * Internal method does not check for admin or ownership.
     */
    public GrantSet _checkUserPerms(long userid, Element obj, Permission perms[])
            throws AppException {
        GrantSet retSet = _initPermSet(perms);
        ObjectACInfo oai = new ObjectACInfo(this, obj);
        oai.fillAccessorInhrPermissions(userid, IDCONST.USER, retSet, perms);
        return retSet;
    }

    /**
     * Internal method does not check for admin or ownership.
     */
    public GrantSet _checkGroupPerms(long groupid, Element obj, Permission perms[])
            throws AppException {
        GrantSet retSet = _initPermSet(perms);
        ObjectACInfo oai = new ObjectACInfo(this, obj);
        oai.fillAccessorInhrPermissions(groupid, IDCONST.GROUP, retSet, perms);
        return retSet;
    }

    /**
     * Checks single permission
     */
    private boolean _checkUserPerm(long userid, Element obj, Permission perm)
            throws AppException {
        boolean retVal = false;
        GrantSet gs = _checkUserPerms(userid, obj, new Permission[]{perm});
        if (gs != null) {
            retVal = gs.contains(perm);
        }
        return retVal;
    }

    private boolean _checkGroupPerm(long groupid, Element obj, Permission perm)
            throws AppException {
        boolean retVal = false;
        GrantSet gs = _checkGroupPerms(groupid, obj, new Permission[]{perm});
        if (gs != null) {
            retVal = gs.contains(perm);
        }
        return retVal;
    }

    /**
     * Determines access to singular permission / object.
     */
    private boolean _checkPerm(Element obj, Permission perm)
            throws AppException {
        _stdValidate();
        boolean retVal = false;
        GrantSet gs = _checkPerms(obj, new Permission[]{perm});
        if (gs != null) {
            retVal = gs.contains(perm);
        }
        return retVal;
    }

    private boolean _checkReposPerm(Permission perm)
            throws AppException {
        _stdValidate();
        boolean retVal = false;
        GrantSet gs = _checkReposPerms(new Permission[]{perm});
        if (gs != null) {
            retVal = gs.contains(perm);
        }
        return retVal;
    }

    String getGroupStringList(long userId)
            throws AppException {
        String retString = "";
        // if userid is that of 'current' user, try to use cached group list
        if (userId == getUserId() && _strGroups != null) {
            retString = _strGroups;
        } else {
            Set sGroups = _getGroupsOfUser(userId);
            retString = Strings.buildCommaDelList(sGroups);
            // cache away the group list...
            if (userId == getUserId()) {
                _strGroups = retString;
            }
        }
        return retString;
    }

    private BusinessObject _getObj(IDCONST type, Oid iid) throws AppException {
        return (BusinessObject) som.getCompObject(null, type.getDirName(), iid);
    }

    private GrantSet _initPermSet(Permission perms[]) {
        GrantSet retSet = new GrantSet();
        return retSet;
    }

    /**
     * Fill only... Calling method must do any flushing.
     */
//    private void _grantReposPermissions(IRConnection repConn, long accId, Set perms)
//            throws AppException {
//        Iterator iperms = perms.iterator();
//        while (iperms.hasNext()) {
//            Permission perm = (Permission) iperms.next();
//            _sqlEnsurePermGrant(repConn, IDCONST.OCUREPOS.getLongValue(), perm.getID(), accId);
//        }
//    }

    /**
     * Fill only... Calling method must do any flushing.
     */
//    private void _grantObjPermissions(IRConnection repConn, long accId, Element elem, Set perms)
//            throws AppException {
//        Iterator iperms = perms.iterator();
//        while (iperms.hasNext()) {
//            Permission perm = (Permission) iperms.next();
//            _ensureObjPermGrant(repConn, elem, perm, accId);
//        }
//    }

    /**
     * This method will apply a permission to a parent object if needed.
     */
//    private void _ensureObjPermGrant(IRConnection repConn, Element elem, Permission perm, long accid)
//            throws AppException {
//        _sqlEnsurePermGrant(repConn, elem.getOid().getLongValue(), perm.getID(), accid);
//        // JKC 9/15/00: Issue 2122. If granting an auto-grant recursive permission (e.g., ITEM_VIEW),
//        // auto-grant to the parent object recursively.
//        if (_autoGrants.contains(perm)) {
//            if (elem instanceof IBusinessObject) {
//                IBusinessObject parobj = (IBusinessObject) ((IBusinessObject) elem).getParentObject();
//                long paroid = Long.MIN_VALUE;
//                IDCONST idcClassid = null;
//                if (parobj != null) {
//                    idcClassid = IDCONST.getInstance(parobj.getDefnObject().getOid().getLongValue());
//                } else {
//                    idcClassid = IDCONST.REPOSITORY;
//                }
//                if (PermEnum.isValidPermission(perm, idcClassid)) {
//                    _ensureObjPermGrant(repConn, parobj, perm, accid);
//                }
//            }
//        }
//    }

    /**
     * SQL Insert for permissions
     */
//    private void _sqlEnsurePermGrant(IRConnection repConn, long oid, int permid, long accid)
//            throws AppException {
//        IQueryProcessor stmt = null;
//        try {
//            stmt = repConn.createProcessor();
//
//            // see if its there...
//            String query = "SELECT * FROM PERMISSIONGRANT WHERE "
//                    + COL_PAROBJID + " = " + oid + " AND "
//                    + COL_PERMID + " = " + permid + " AND "
//                    + COL_ACCESSORID + " = " + accid + " ";
//
//            IDataSet data = stmt.retrieve(query);
//            if (!data.next()) {
//                // if not there, insert it...
//                query = "INSERT INTO PERMISSIONGRANT ("
//                        + COL_PAROBJID + ", "
//                        + COL_PERMID + ", "
//                        + COL_ACCESSORID
//                        + ") VALUES ("
//                        + oid + ", "
//                        + permid + ", "
//                        + accid + " )";
//
//                stmt.update(query);
//            }
//        } catch (QueryException sqlExp) {
//            throw new AppException(sqlExp);
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//        }
//    }

//    private void _sqlDeleteAll(IRConnection repConn, long accid, long oid)
//            throws AppException {
//        IQueryProcessor stmt = null;
//        try {
//            stmt = repConn.createProcessor();
//            stmt.setSingleton(false);
//            String query = "DELETE FROM PermissionGrant "
//                    + "WHERE " + COL_ACCESSORID + " = " + accid
//                    + " AND " + COL_PAROBJID + "=" + oid;
//
//            stmt.update(query);
//        } catch (QueryException sqlExp) {
//            throw new AppException("Error deleting permissions from database: " + sqlExp.toString());
//        } finally {
//            if (stmt != null) {
//                stmt.close();
//            }
//        }
//    }
    private boolean _isTheAdmin(long accessorid)
            throws AppException {
        boolean retVal = false;
        // the accessor might not be a valid user
        try {
            retVal = _getUser(accessorid).isAdmin();
        } catch (AppException invalidUser) {
            // ignore for now.
            // NOTE: a bit kludgy singce we don't know that the accessor is supposed to be a user.
            // ALSO: it'd be nice if the SOMImpl threw an explicit ObjectNotFound exception.
        }
        return retVal;
    }

    private User _getUser(long userid)
            throws AppException {
        User user = null;
        try {
            user = (User) getMC().getObj(IDCONST.USER, userid);
        } catch (AppException ex) {
            throw new AppException("Invalid User Id sent to Access Manager.  User not found.", ex);
        }
        if (user == null) throw new AppException("Invalid User Id sent to Access Manager.  User not found.");
        return user;
    }

    private boolean _isTheAdmin()
            throws AppException {
        if (_isAdm == null)
            _isAdm = new Boolean(getUser().isAdmin());
        return _isAdm.booleanValue();
    }

    private boolean _isTheSys()
            throws AppException {
        if (_isSys == null) {
            Oid uid = getUser().getOid();
            if (uid.getLongValue() == IDCONST.USER_SYSTEM.getLongValue())
                _isSys = new Boolean(true);
            else
                _isSys = new Boolean(false);
        }
        return _isSys.booleanValue();
    }

    /**
     * Add the grant for an individual User Group, the list will contain only 1 item.
     * This is used to automatically grant a new user group with view/edit permission
     * on the public/DEFAULT_ATTR_GROUP at the time of creating the new user group.
     */
    public void addGroupAttrGroupGrant(List list, Oid id) throws AppException {

        // TODO db insert

//        IQueryProcessor qp = null;
//        IRConnection jdtC = null;
//        IDataSet rs = null;
//        com.oculussoftware.system.sec.AttrGroupGrantAsc attsec = (com.oculussoftware.system.sec.AttrGroupGrantAsc)
//                list.get(0);
//        try {
//            jdtC = getCRM().getDatabaseConnection(getObjectContext());
//            qp = jdtC.createProcessor();
//            String query = "SELECT * FROM ATTRGROUPGRANT "
//                    + "WHERE ACCESSORID=" + attsec.getAccessorLng() + " AND ATTRGROUPID=" + id.getLongValue();
//            rs = qp.retrieve(query);
//            if (!rs.next()) {
//                qp.close();
//                qp = jdtC.createProcessor();
//                String str = IDCONST.OCUREPOS.getIIDValue().toString();
//                query = "INSERT INTO ATTRGROUPGRANT (PAROBJECTID,ATTRGROUPID,OPERATIONTYPE,ACCESSORID) VALUES ("
//                        + str + ","
//                        + id.getLongValue() + ","
//                        + attsec.getOperation().getIntValue() + ","
//                        + attsec.getAccessorLng() + ")";
//                qp.update(query);
//
//            }
//        } catch (QueryException sqlExp) {
//            throw new AppException("Error adding permission to database: " + sqlExp.toString());
//        } finally {
//            if (qp != null) qp.close();
//        }

    }
  
  /*
          Remove the grant for an individual User Group, the list will contain only 1 item.
	  This is used to automatically remove a grant for a deleted user or user group
  */

    public void removeAttrGroupGrant(List list) throws AppException {

        // TODO db delete ...

//        IQueryProcessor qp = null;
//        IRConnection jdtC = null;
//        com.oculussoftware.system.sec.AttrGroupGrantAsc attsec = (com.oculussoftware.system.sec.AttrGroupGrantAsc)
//                list.get(0);
//        try {
//            jdtC = getCRM().getDatabaseConnection(getObjectContext());
//            qp = jdtC.createProcessor();
//            String query = "DELETE FROM ATTRGROUPGRANT "
//                    + "WHERE ACCESSORID=" + attsec.getAccessorLng();
//            qp.update(query);
//
//        } catch (QueryException sqlExp) {
//            throw new AppException("Error deleting permission from database: " + sqlExp.toString());
//        } finally {
//            if (qp != null) qp.close();
//        }


    }
}