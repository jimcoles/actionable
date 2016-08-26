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

import org.jkcsoft.bogey.model.Element;
import org.jkcsoft.bogey.model.ReposObject;
import org.jkcsoft.bogey.system.AppException;

public class ProcessRole extends ModelElement {

    private DeleteState _deletestate = DeleteState.NOT_DELETED;
    private boolean _isactive = true;
    private ConfigKind _configkind = ConfigKind.READ_ONLY;
    private String _name;
    private String _description;
    private boolean _multiple = true;
    private boolean _inhrparent = true;


    public ProcessRole() throws AppException {
        super();
    }


    public DeleteState getDeleteState()
            throws AppException {
        return _deletestate;
    }

    public ConfigKind getConfigKind()
            throws AppException {
        return _configkind;
    }


    public ModelElement setDeleteState(DeleteState d) throws AppException {
        _deletestate = d;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public ModelElement isActive(boolean a)
            throws AppException {
        _isactive = a;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public ModelElement setConfigKind(ConfigKind c)
            throws AppException {
        _configkind = c;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    //------------------------------ Element -----------------------

    public String getName()
            throws AppException {
        return _name;
    }

//    public String getDescription()
//            throws AppException {
//        return _description;
//    }

    public Element setName(String n)
            throws AppException {
        _name = n;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

//    public Element setDescription(String d)
//            throws AppException {
//        _description = d;
//        if (getPersState().equals(PersState.UNMODIFIED))
//            setPersState(PersState.MODIFIED);
//        return this;
//    }

    //-------------------------- IProcessRole ------------------------

    public ModelElement isMultiUser(boolean a)
            throws AppException {
        _multiple = a;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public ModelElement inheritParent(boolean a)
            throws AppException {
        _inhrparent = a;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public boolean isMultiUser()
            throws AppException {
        return _multiple;
    }

    public boolean inheritParent()
            throws AppException {
        return _inhrparent;
    }



//    public Object dolly() throws AppException {
//        ProcessRole pr = null;
//        pr = new ProcessRole();
//        pr.setOid(getOid());
//        pr.setObjectContext(getObjectContext());
//        pr.setPersState(getPersState());
//        pr.setDeleteState(getDeleteState());
//        pr.isActive(isActive());
//        pr.setConfigKind(getConfigKind());
//        pr.setName(getName());
//        pr.isMultiUser(isMultiUser());
//        pr.inheritParent(inheritParent());
//        pr.setDescription(getDescription());
//        return pr;
//    }

}