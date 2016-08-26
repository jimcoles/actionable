/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.model;

import org.jkcsoft.bogey.metamodel.DeleteState;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.metamodel.PersState;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.AppSystem;
import org.jkcsoft.bogey.system.ObjectContext;
import org.jkcsoft.bogey.system.QueryException;

import java.util.Collection;
import java.util.Map;

/**
 * Provides the functionality for a basic version for a User.
 */

public class User extends BusinessObject {

    private Oid _OrgIID;
    private ActiveKind _active = null;
    private boolean _isInternal = false;
    private boolean _isTemporary = false;
    private BMProperty _loginId; // string type
    private BMProperty _password; // string type
    private BMProperty _emailStatus; // int type
    private BMProperty _numberOfTrials; // int type
    private BMProperty _autoLogout; // int type
    private BMProperty _lastName; // string type
    private BMProperty _phone; // string type
    private BMProperty _emailAddr; // string type
    private BMProperty _firstName; // string type
    private BMProperty _isAccolades; //bool type
    private BMProperty _isCompass; //bool type
    private BMProperty _isConduit; //booltype

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public User() throws AppException {
        super();
        _loginId = new BMProperty(this);                 // string type
        _password = new BMProperty(this);                // string type
        _emailStatus = new BMProperty(this);            // int type
        _numberOfTrials = new BMProperty(this);         // int type
        _autoLogout = new BMProperty(this);             // int type
        //_active                 = new BMProperty(this);                 // short type
        _lastName = new BMProperty(this);               // string type
        _phone = new BMProperty(this);                  // string type
        _emailAddr = new BMProperty(this);              // string type
        _firstName = new BMProperty(this);              // string type
        _isAccolades = new BMProperty(this); //bool type
        _isCompass = new BMProperty(this); //bool type
        _isConduit = new BMProperty(this); //booltype


        // TODO replace with annotations ...
//        _loginId.setDefnObject(IDCONST.LOGINID.getIIDValue());
//        _password.setDefnObject(IDCONST.USERPASSWORD.getIIDValue());
//        _emailStatus.setDefnObject(IDCONST.EMAILSTATUS.getIIDValue());
//        _numberOfTrials.setDefnObject(IDCONST.NUMTRIALS.getIIDValue());
//        _autoLogout.setDefnObject(IDCONST.AUTOLOGOUT.getIIDValue());
//        //_active.setDefnObject(IDCONST.ACTIVE.getIIDValue());
//        _lastName.setDefnObject(IDCONST.LASTNAME.getIIDValue());
//        _phone.setDefnObject(IDCONST.PHONE.getIIDValue());
//        _emailAddr.setDefnObject(IDCONST.EMAIL.getIIDValue());
//        _firstName.setDefnObject(IDCONST.FIRSTNAME.getIIDValue());
//        _isAccolades.setDefnObject(IDCONST.LIC_ACCOLADES.getIIDValue());
//        _isCompass.setDefnObject(IDCONST.LIC_COMPASS.getIIDValue());
//        _isConduit.setDefnObject(IDCONST.LIC_CONDUIT.getIIDValue());
//

    }


    public User associateGroup(Oid grp) throws AppException {
        //ensure there are no duplicates
        disassociateGroup(grp);

        // TODO supplant ...
//        IRConnection conn = AppSystem.getRepo().getDataConnection(getObjectContext());
//        conn.createProcessor().update("INSERT INTO USERGROUPASC (GROUPID, USERID) VALUES (" + grp.getLongValue() + "," +
//                                              "" + getOid().getLongValue() + ") ");
        return this;
    }

    public User disassociateGroup(Oid grp) throws AppException {

        // TODO supplant ...
//        IRConnection conn = AppSystem.getRepo().getDataConnection(getObjectContext());
//        conn.createProcessor().update("DELETE FROM USERGROUPASC WHERE USERID=" + getOid().getLongValue() + " AND " +
//                                              "GROUPID=" + grp.getLongValue() + " ");
        return this;
    }

    public Object dolly() throws AppException {
        User usr = null;
        usr = new User();
        usr.setOid(getOid());
        usr.setObjectContext(getObjectContext());
        usr.setPersState(getPersState());
//        usr._classIID = _classIID;
        //usr._stateIID = _stateIID;
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            usr.putAll(getProperties());
        //usr.setName(getName());
        usr.setDescription(getDescription());
        usr.setCreatorIID(this.getCreatorIID());
        usr.setAccessIID(this.getAccessIID());
        usr.setCreationDate(getCreationDate());
        usr.setMessageAttached(hasMessageAttached());
        usr.setLinkAttached(hasLinkAttached());
        usr.setFileAttached(hasFileAttached());
        usr.setDeleteState(getDeleteState());
        //specific to user
        usr.isTemporary(isTemporary());
        usr.isCustomer(isCustomer());
        usr.setActive(getActive());
        //usr.setEmailStatus(getEmailStatus());
        // usr.setEmailStatus(getEmailStatus());
        usr.setNumberOfTrials(getNumberOfTrials());
        usr.setEmailAddr(getEmailAddr());
        usr.setFirstName(getFirstName());
        usr.setLastName(getLastName());
        usr.setLoginId(getLoginId());
        usr.setPassword(getPassword());
        usr.setPhone(getPhone());
        usr.setOrgIID(getOrgIID());
        usr.setAccolades(isAccolades());
        usr.setCompass(isCompass());
        usr.setConduit(isConduit());
        return usr;
    }

    public Oid getAccessorIID() throws QueryException {
        return getOid();
    }


    public ActiveKind getActive()
            throws AppException {
        return _active;
    }

    public Oid getDepartmentIID() throws QueryException {
        Oid id = null;
        try {
            Map<Oid, BMProperty> properties = getProperties();
            if (properties != null) {
                BMProperty prop = properties.get("??");
                Object o = prop.getValue();
                if (o != null && o instanceof Long)
                    id = new Oid(((Long) o).longValue());
            }
        } catch (Exception ex) {
            throw new QueryException(ex);
        }
        return id;
    }

    public String getEmailAddr() throws AppException {
        return (String) _emailAddr.getValue();

    }


    public int getEmailStatus()
            throws AppException {
        if (_autoLogout.getValue() != null)
            return ((Integer) _autoLogout.getValue()).intValue();
        else
            return -1;
    }


    public String getFirstName()
            throws AppException {
        return (String) _firstName.getValue();

    }


    public String getFullName(boolean hasComma)
            throws AppException {
        if (hasComma)
            return ((String) _lastName.getValue() + ", " + (String) _firstName.getValue());
        else
            return ((String) _firstName.getValue() + " " + (String) _lastName.getValue());
    }


    public Collection<Group> getGroups() throws AppException {
        return (Collection<Group>) getObjectContext().getCRM().getCompObject(getObjectContext(), "GroupUserColl", getOid());
    }


    public String getLastName()
            throws AppException {
        return (String) _lastName.getValue();

    }

    public String getLoginId()
            throws AppException {
        return (String) _loginId.getValue();
    }

    public String getName() throws AppException {
        return getFullName(false);
    }


    public int getNumberOfTrials() throws AppException {
        if (_numberOfTrials.getValue() != null)
            return ((Integer) _numberOfTrials.getValue()).intValue();
        else
            return -1;
    }

    public Organization getOrganizationObject() throws AppException {
        return getOrganizationObject(false);
    }

    public Organization getOrganizationObject(boolean edit) throws AppException {
        if (_OrgIID != null) {
            return (Organization) getObjectContext().getCRM().getCompObject(getObjectContext(), "Organization",
                                                                             _OrgIID, edit);
        } else {
            return null;
        }
    }

    public Oid getOrgIID() throws AppException {
        return _OrgIID;
    }

    public String getPassword()
            throws AppException {
        return (String) _password.getValue();
    }

    public String getPhone()
            throws AppException {
        return (String) _phone.getValue();

    }

    /**
     * isAccolades method comment.
     */
    public boolean isAccolades() throws AppException {
        if (_isAccolades.getValue() != null)
            return ((Boolean) _isAccolades.getValue()).booleanValue();
        else
            return false;
    }

    /**
     * isAdmin method comment.
     */
    public boolean isAdmin() throws AppException {
        return (getOid().getLongValue() == IDCONST.USER_ADMIN.getLongValue() ? true : false);
    }


    /**
     * isCompass method comment.
     */
    public boolean isCompass() throws AppException {
        if (_isCompass.getValue() != null)
            return ((Boolean) _isCompass.getValue()).booleanValue();
        else
            return false;
    }


    /**
     * isConduit method comment.
     */
    public boolean isConduit() throws AppException {
        if (_isConduit.getValue() != null)
            return ((Boolean) _isConduit.getValue()).booleanValue();
        else
            return false;
    }


    public boolean isCustomer()
            throws AppException {
        return !_isInternal;
    }


    public User isCustomer(boolean bool) throws AppException {
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        _isInternal = !bool;
        return this;
    }


    /**
     * isSysUser method comment.
     */
    public boolean isSysUser() throws AppException {
        return (getActive() == ActiveKind.ACTIVE ? true : false);
    }


    /**
     * isAdmin method comment.
     */
    public boolean isTheSysUser() throws AppException {
        return (getOid().getLongValue() == IDCONST.USER_SYSTEM.getLongValue() ? true : false);
    }

    /**
     * setAccolades method comment.
     */
    public User setAccolades(boolean bool) throws AppException {
        _isAccolades.setValue(new Boolean(bool));
        return this;
    }


    public User setActive(ActiveKind active) throws AppException {
        _active = active;
        return this;
    }


    /**
     * setCompass method comment.
     */
    public User setCompass(boolean bool) throws AppException {
        _isCompass.setValue(new Boolean(bool));
        return this;
    }


    /**
     * setCompass method comment.
     */
    public User setConduit(boolean bool) throws AppException {
        _isConduit.setValue(new Boolean(bool));
        return this;
    }


    public User setEmailAddr(String emailAddr) throws AppException {
        _emailAddr.setValue(emailAddr);
        return this;
    }


    public User setEmailStatus(int emailStatus) throws AppException {
        _autoLogout.setValue(new Integer(emailStatus));
        return this;
    }


    public User setFirstName(String firstName) throws AppException {
        _firstName.setValue(firstName);
        return this;
    }


    public User setLastName(String lastName) throws AppException {
        _lastName.setValue(lastName);
        return this;
    }


    public User setLoginId(String loginId) throws AppException {
        _loginId.setValue(loginId);
        return this;
    }


    //expect lastname,firstname else throw exception
    public Element setName(String name) throws AppException {
        int iOffset;
        if ((iOffset = name.indexOf(",")) < 0)
            throw new AppException("Expected name in form FirstName,LastName");
        setLastName(name.substring(0, iOffset).trim());
        setFirstName(name.substring(iOffset + 1, name.length()).trim());
        return this;
    }


    private User setNumberOfTrials(int numberOfTrials) throws AppException {
        _numberOfTrials.setValue(new Integer(numberOfTrials));
        return this;
    }


    //------------------------ MUTATORS -------------------------------------
    public User setOrgIID(Oid id) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _OrgIID = id;
        return this;
    }


    public User setPassword(String password) throws AppException {
        _password.setValue(password);
        return this;
    }

    public User setPhone(String phone) throws AppException {
        _phone.setValue(phone);
        return this;
    }


    public BusinessObject softDelete() throws AppException {
        setDeleteState(DeleteState.DELETED);
        setAccolades(false);
        setCompass(false);
        setConduit(false);
        return this;
    }


    public boolean isTemporary() throws AppException {
        return _isTemporary;
    }


    public User isTemporary(boolean bool) throws AppException {
        _isTemporary = bool;
        return this;
    }


    public static User getUserViaEmail(String EmailAddress, boolean isInternal, boolean edit, ObjectContext context)
            throws AppException {

        // TODO query; move to service object

        return null;
    }
}
