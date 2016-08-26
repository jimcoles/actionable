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

import org.jkcsoft.bogey.model.ReposObject;
import org.jkcsoft.bogey.model.User;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.AppSystem;
import org.jkcsoft.bogey.system.ObjectContext;

public class RoleAssignment extends ReposObject {

    private Oid _parobjectiid;
    private Oid _useriid;
    private Oid _roleiid;
    private int _ordernum;
    private boolean _iscontroller = true;

    public RoleAssignment() throws AppException {
        super();
    }

    public Object dolly() throws AppException {
        RoleAssignment ra = new RoleAssignment();
        ra.setOid(getOid());
//        ra.setObjectContext(getObjectContext());
        ra.setPersState(getPersState());
        ra.setParObjectIID(getParObjectIID());
        ra.setRoleIID(getRoleIID());
        ra.setUserIID(getUserIID());
        ra.setOrderNum(getOrderNum());
        ra.isController(isController());
        return ra;
    }

    public RoleAssignment construct(ObjectContext context, RepoMap args) throws AppException {
        Oid iid = null;
        if (context == null)
            throw new AppException("Context Argument expected.");
//        setObjectContext(context);
        if (args == null) {
            iid = AppSystem.getRepo().genReposID();
            setPersState(PersState.NEW);
        } else {
            setPersState(PersState.UNINITED);
            iid = args.getOid();
        }
        setOid(iid);
        if (args != null && args.keySet().size() > 0) {
///            load(args);
        }
        return this;
    }

    public boolean isDuplicate() throws AppException {
        boolean blnRV = false;
        // TODO ...
        return blnRV;
    }

    public Oid getParObjectIID() throws AppException {
        return _parobjectiid;
    }

    public Oid getRoleIID() throws AppException {
        return _roleiid;
    }

    public Oid getUserIID() throws AppException {
        return _useriid;
    }

    public User getUserObject() throws AppException {
        return (User) AppSystem.getCrm().getCompObject(null, "User", _useriid);
    }


    public int getOrderNum() throws AppException {
        return _ordernum;
    }

    public boolean isController() throws AppException {
        return _iscontroller;
    }

    public RoleAssignment setParObjectIID(Oid iid) throws AppException {
        _parobjectiid = iid;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public RoleAssignment setRoleIID(Oid iid) throws AppException {
        _roleiid = iid;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public RoleAssignment setUserIID(Oid iid) throws AppException {
        _useriid = iid;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public RoleAssignment setOrderNum(int num) throws AppException {
        _ordernum = num;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public RoleAssignment isController(boolean c) throws AppException {
        _iscontroller = c;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

}