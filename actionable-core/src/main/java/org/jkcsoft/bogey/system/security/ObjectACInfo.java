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

import org.jkcsoft.bogey.metamodel.Class;
import org.jkcsoft.bogey.metamodel.ModelElement;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.metamodel.ProcessRole;
import org.jkcsoft.bogey.model.BusinessObject;
import org.jkcsoft.bogey.model.Element;
import org.jkcsoft.bogey.model.IDCONST;
import org.jkcsoft.bogey.model.ReposObject;
import org.jkcsoft.bogey.system.AppException;

import java.util.*;

/**
 * The access control info for a given object.
 */
public class ObjectACInfo {
    //-----------------------------------------------------------------------------
    // Private static variables
    //-----------------------------------------------------------------------------
    private static final String COL_PAROBJECTID = "PAROBJECTID";
    private static final String COL_PERMID = "PERMISSIONID";

    // Table: ObjectRoleAssign
    private static final String COL_USERID = "USERID";
    private static final String COL_ROLEID = "ROLEID";

    //-----------------------------------------------------------------------------
    // Private instance variables
    //-----------------------------------------------------------------------------
    private AccessMgr _acm = null;
    private Element _obj = null;
    private Class _objClass = null;
    private int _objType = 0;

    private static final int BUSOBJ = 1;
    private static final int MODELELEMENT = 2;
    private static final int CLASS = 3;

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
    public ObjectACInfo(AccessMgr acm, Element obj)
            throws AppException {
        _acm = acm;
        _obj = obj;  // if obj == null, it represent the repository
        if (obj == null) {
            // IMPORTANT: if you change the line below, make sure _isRepos( ) is adjusted.
            // Currenty (4/30), and _oid equal to IDCONST.OCUREPOS is our way of knowing
            // we're at the repository level.
            _oid = IDCONST.OCUREPOS.getLongValue();
        } else {
            if (obj instanceof BusinessObject) {
                _objClass = ((BusinessObject) obj).getDefnObject();
                _objType = BUSOBJ;
            } else if (obj instanceof Class) {
                _objType = CLASS;
            } else if (obj instanceof ModelElement) {
                _objType = MODELELEMENT;
            }
        }
    }

    /**
     * Use this when getting direct permissions only.
     */
    public ObjectACInfo(AccessMgr acm, long oid) {
        _acm = acm;
        _oid = oid;
    }
    //-----------------------------------------------------------------------------
    // Public instance methods
    //-----------------------------------------------------------------------------

    public long getObjectId() throws AppException {
        long retVal = 0;
        if (_obj != null) {
            retVal = _obj.getOid().getLongValue();
        } else {
            retVal = _oid;
        }
        return retVal;
    }

    public long getClassId()
            throws AppException {
        long retVal;
        if (_objClass == null && _oid != IDCONST.OCUREPOS.getLongValue())
            throw new AppException("Attempt to get Class id when Class has not been initialized.");

        if (_oid == IDCONST.OCUREPOS.getLongValue())
            retVal = IDCONST.REPOSITORY.getLongValue();
        else
            retVal = _objClass.getOid().getLongValue();
        return retVal;
    }

    /**
     * Recursively check parent objects and fill in permissions.  Do so for current
     * user and check all Groups and Roles.
     */
    public void fillInhrPermissions(GrantSet permSet, Permission perms[])
            throws AppException {
        fillInhrPermissions(new Long(_acm.getUserId()), _buildGroupList(_acm.getUserId()), permSet, perms);
    }

    /**
     * Fill all permissions including inherited for the specified accessor. Complicated since
     * the accessor may be a User or Group.
     * NOTE: Under currnet business rules (5/21/2000) does not make sense to use recursion on Roles.
     */
    public void fillAccessorInhrPermissions(long accId, Oid accTypeID, GrantSet permSet, Permission perms[])
            throws AppException {
        // delegate to common method
        if (accTypeID.getLongValue() == IDCONST.USER.getIIDValue()) {
            fillInhrPermissions(new Long(accId), _buildGroupList(accId), permSet, perms);
        } else if (accTypeID.getLongValue() == IDCONST.GROUP.getIIDValue()) {
            fillInhrPermissions(null, Long.toString(accId), permSet, perms);
        } else {
            throw new AppException("Attempt to get recursive permission grants for invalid accessor type.  Method " +
                                           "only supports Users and Groups.");
        }
    }

    /**
     * Fill only the permissions direclty associated with the specified accessor. Doesn't care
     * what type (User, Group, Role) the accessor is because it doesn't take into
     * account associated Groups and Roles nor does it check permissions on parent objects.
     */
    public void fillAccessorDirectPermissions(long accId, GrantSet permSet, Permission perms[])
            throws AppException {
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
    void fillInhrPermissions(Long lUserId, String groups, GrantSet permSet, Permission perms[])
            throws AppException {
        String all = "";
        if (lUserId != null)
            all = lUserId.toString();
        if (groups != null && groups.trim().length() > 0) {
            if (all.length() > 0)
                all = all + ", " + groups;
            else
                all = groups;
        }
        _sqlFillPermissions(all, getObjectId(), permSet, perms);
        if (lUserId != null) {
            String roles = _buildRoleList(lUserId.longValue());
            if (roles != null && roles.trim().length() > 0)
                _sqlFillPermissions(roles, getClassId(), permSet, perms);
        }
        // recursively check parents unless this is the root repository object
        // JKC 8/15/00 Only do parent check one permissions that are recursive in nature.
        Permission ngrPerms[] = _getAllNonGrantedRecs(permSet, perms);
        if (ngrPerms != null && ngrPerms.length > 0 && !_isRepos()) {
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
            throws AppException {
        Set retSet = null;
        if (_roleSet != null) {
            retSet = _roleSet;
        } else {
            retSet = new HashSet();

            // TODO db query user roles

//            IQueryProcessor stmt = null;
//            try {
//                IRConnection repConn = _acm.getCRM().getDatabaseConnection(_acm.getObjectContext());
//                stmt = repConn.createProcessor();
//                String query = "SELECT DISTINCT " + COL_ROLEID
//                        + " FROM OBJECTROLEASSIGN "
//                        + " WHERE " + COL_PAROBJECTID + " IN " + _getParChildList()
//                        + " AND " + COL_USERID + " = " + userid;
//                IDataSet results = stmt.retrieve(query);
//                while (results.next()) {
//                    long roleid = results.getLong(COL_ROLEID);
//                    retSet.add(_acm.getMC().getObj(IDCONST.PROCESSROLE, roleid));
//                }
//            } catch (QueryException sqlExp) {
//                throw new AppException(sqlExp);
//            } finally {
//                if (stmt != null)
//                    stmt.close();
//            }
        }
        return retSet;
    }


    //-----------------------------------------------------------------------------
    // Private instance methods
    //-----------------------------------------------------------------------------

    /**
     * Low-level guy takes accessor list and sees if specified permissions are granted to one of
     * the accessors for this object.
     */
    private void _sqlFillPermissions(String accessorSQLList, long objectID, GrantSet permSet, Permission perms[])
            throws AppException {
        String permIdSQL = "";

        int i = 0;
        for (i = 0; i < perms.length; i++) {
            if (i > 0)
                permIdSQL += ", ";

            permIdSQL += perms[i].getID();
        }

        // TODO replace db query logic ...

//        IQueryProcessor stmt = null;
//        try {
//            IRConnection repConn = _acm.getCRM().getDatabaseConnection(_acm.getObjectContext());
//            stmt = repConn.createProcessor();
//            String query = "SELECT permissionId "
//                    + " FROM PermissionGrant "
//                    + " WHERE accessorId IN (" + accessorSQLList + ")"
//                    + " AND " + COL_PAROBJECTID + " = " + objectID
//                    + " AND " + COL_PERMID + " IN (" + permIdSQL + ")";
//            IDataSet results = stmt.retrieve(query);
//            while (results.next()) {
//                int permid = results.getInt(COL_PERMID);
//                Permission key = _acm.getPermSetInfo().getPermForId(permid);
//                permSet.add(key);
//            }
//        } catch (QueryException sqlExp) {
//            throw new AppException(sqlExp);
//        } finally {
//            if (stmt != null)
//                stmt.close();
//        }
    }

    private Permission[] _getAllNonGrantedRecs(GrantSet gs, Permission perms[]) {
        Permission retVal[] = null;
        List temp = new Vector();
        for (int idx = 0; idx < perms.length; idx++) {
            Permission perm = perms[idx];
            if (perm.getIsRecursive() && !gs.contains(perm)) {
                temp.add(perm);
            }
        }
        retVal = new Permission[temp.size()];
        temp.toArray(retVal);
        return retVal;
    }

    private boolean _isRepos() {
        return (_oid == IDCONST.OCUREPOS.getLongValue());
    }

    /**
     * Return full list containing specified user.and all of user's groups
     */
    private String _buildGroupList(long userId)
            throws AppException {
        String retString = _acm.getGroupStringList(userId);
        return retString;
    }

    private String _buildRoleList(long userid)
            throws AppException {
        String retString = "";
        Set sRoles = getRolesOfUser(userid);
        if (sRoles != null) {
            Iterator iRoles = sRoles.iterator();
            int i = 0;
            while (iRoles.hasNext()) {
                if (i > 0)
                    retString += ", ";

                ProcessRole role = (ProcessRole) iRoles.next();
                retString += role.getOid().getLongValue();
                i++;
            }
        }
        return retString;
    }

    private ObjectACInfo _getParentNode()
            throws AppException {
        ObjectACInfo retObj = null;
        if (_obj == null)
            throw new AppException("Attempt to get parent of repository object.");

        if (_obj instanceof ReposObject) {
            Element pobj = ((ReposObject) _obj).getParentObject(false);
            if (pobj == null ||
                    (pobj.getOid().getLongValue() == _obj.getOid().getLongValue())) {
                retObj = new ObjectACInfo(_acm, null);
            } else {
                retObj = new ObjectACInfo(_acm, pobj);
            }
        } else {
            retObj = new ObjectACInfo(_acm, null);
        }
        return retObj;
    }

    private Element _getObj()
            throws AppException {
        return _obj;
    }

}