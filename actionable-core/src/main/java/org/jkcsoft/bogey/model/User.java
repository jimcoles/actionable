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

import org.jkcsoft.bogey.metamodel.Oid;

/**
 * Provides the functionality for a basic version for a User.
 * <p>
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


    //--------------------------- Public Constructors --------------------------

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public User() throws OculusException {
        super();
        COL_Guid = "Guid";
        TABLE = "APPUSER";
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


        _loginId.setDefnObject(IDCONST.LOGINID.getIIDValue());
        _password.setDefnObject(IDCONST.USERPASSWORD.getIIDValue());
        _emailStatus.setDefnObject(IDCONST.EMAILSTATUS.getIIDValue());
        _numberOfTrials.setDefnObject(IDCONST.NUMTRIALS.getIIDValue());
        _autoLogout.setDefnObject(IDCONST.AUTOLOGOUT.getIIDValue());
        //_active.setDefnObject(IDCONST.ACTIVE.getIIDValue());
        _lastName.setDefnObject(IDCONST.LASTNAME.getIIDValue());
        _phone.setDefnObject(IDCONST.PHONE.getIIDValue());
        _emailAddr.setDefnObject(IDCONST.EMAIL.getIIDValue());
        _firstName.setDefnObject(IDCONST.FIRSTNAME.getIIDValue());
        _isAccolades.setDefnObject(IDCONST.LIC_ACCOLADES.getIIDValue());
        _isCompass.setDefnObject(IDCONST.LIC_COMPASS.getIIDValue());
        _isConduit.setDefnObject(IDCONST.LIC_CONDUIT.getIIDValue());


    }


    public IUser associateGroup(Oid grp)
            throws OculusException {
        //ensure there are no duplicates
        disassociateGroup(grp);
        IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
        conn.createProcessor().update("INSERT INTO USERGROUPASC (GROUPID, USERID) VALUES (" + grp.getLongValue() + "," +
                                              "" + getOid().getLongValue() + ") ");
        return this;
    }


    /**
     * Override any attempt to mark a User as PersState.DELETED.
     * UI can only edit the user to be ACTIVE = 0
     */
    public IPersistable delete()
            throws OculusException {
        if (!isLocked())  // If the bo isn't locked, throw an exception
            throw new QueryException("This object cannot be deleted because it is not locked.");

        setPersState(PersState.UNMODIFIED);            // set the persistent state to unmodifed to ensure no attempt
        // to alter object via delete
        return this;
    }


    public IUser disassociateGroup(Oid grp)
            throws OculusException {
        IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
        conn.createProcessor().update("DELETE FROM USERGROUPASC WHERE USERID=" + getOid().getLongValue() + " AND " +
                                              "GROUPID=" + grp.getLongValue() + " ");
        return this;
    }


    //----------------- IPoolable Methods ------------------------------------

    /**
     * Returns a copy of the current Company object.
     * <p>
     * Note: The exceptions are being withheld because this method overrides
     * the one in Object().  Consider using a different method name since it
     * doesn't look like we're taking advantage of Cloneable.
     */
    public Object dolly() throws OculusException {
        User usr = null;
        usr = new User();
        usr.setOid(getOid());
        usr.setObjectContext(getObjectContext());
        usr.setPersState(getPersState());
        //causes exception
        //if (getDefnObject() != null)
        //  usr.setDefnObject(getDefnObject());
        //if (getStateObject() != null)
        //  usr.setStateObject(getStateObject());
        usr._classIID = _classIID;
        //usr._stateIID = _stateIID;
        //Saleem added to this line
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            usr.putAll(getProperties());
        //usr.setName(getName());
        usr.setDescription(getDescription());
        usr._creatorIID = _creatorIID;
        usr._accessIID = _accessIID;
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


    //----------------- BMPropertyMap Methods---------------------------------
    public Object get(Object key)
            throws OculusException {
        if (key instanceof String) {
            if (key.equals(LABEL_ACTIVE))
                return _active;
            else if (key.equals(LABEL_AUTOLOGOUT))
                return _autoLogout;
            else if (key.equals(LABEL_EMAILADDR))
                return _emailAddr;
            else if (key.equals(LABEL_EMAILSTATUS))
                return _emailStatus;
            else if (key.equals(LABEL_FIRSTNAME))
                return _firstName;
            else if (key.equals(LABEL_LASTNAME))
                return _lastName;
            else if (key.equals(LABEL_ORGID))
                return _OrgIID;
            else if (key.equals(LABEL_PASSWORD))
                return _password;
            else if (key.equals(LABEL_PHONE))
                return _phone;
                //extended attr
            else
                return super.get(key);
        } else
            return null;
    }


    //------------------------ ACCESSORS -------------------------------------

    public Oid getAccessorIID()
            throws QueryException {
        return getOid();
    }


    public ActiveKind getActive()
            throws OculusException {
        return _active;
    }


    private String getCreateQuery()
            throws OculusException {
        //on Create the number of failed logins is reset
        setNumberOfTrials(0);
        String query = " INSERT INTO " + TABLE + " " +
                " (" + COL_OBJECTID +
                ", " + COL_CLASSID +
                //   +COL_STATEID+", "
                ", " + COL_Guid +
                //        +COL_NAME+", "
                //        +COL_DESCRIPTION+", "
                ", " + COL_CREATIONDATE +
                ", " + COL_CREATORID +
                ", " + COL_ACCESSID +
                ", " + COL_MESSAGEATTACHED +
                ", " + COL_FILEATTACHED +
                ", " + COL_LINKATTACHED +
                ", " + COL_DELETESTATE +
                //specific to user
                ", " + COL_ISTEMPORARY +
                ", " + COL_ISINTERNAL +
                (getActive() != null ? "," + COL_ACTIVE : "") +
                //+COL_AUTOLOGOUT+", "
                //       +COL_EMAILSTATUS+", "
                (getNumberOfTrials() != -1 ? "," + COL_NUMBEROFTRIALS : "") +
                (getEmailAddr() != null ? "," + COL_EMAILADDR : "") +
                (getFirstName() != null ? "," + COL_FIRSTNAME : "") +
                (getLastName() != null ? "," + COL_LASTNAME : "") +
                (getLoginId() != null ? "," + COL_LOGINID : "") +
                (getPassword() != null ? "," + COL_PASSWORD : "") +
                (getPhone() != null ? "," + COL_PHONE : "") +
                (getOrgIID() != null ? "," + COL_ORGID : "") +

                ") " +


                " VALUES " +
                " (" + getOid().getLongValue() +
                "," + getDefnObject().getIID().getLongValue() +
                "," + "'" + getGuid().toString() + "'" +
                "," + "'" + getCreationDate().toString() + "'" +
                "," + getCreatorIID().getLongValue() +
                //        +"0,"
                "," + getAccessIID().getLongValue() +
                "," + (hasMessageAttached() ? "1" : "0") +
                "," + (hasFileAttached() ? "1" : "0") +
                "," + (hasLinkAttached() ? "1" : "0") +
                "," + getDeleteState().getIntValue() +
                //specific to user
                "," + (isTemporary() ? "1" : "0") +
                "," + (isCustomer() ? "0" : "1") +
                (getActive() != null ? "," + getActive().getIntValue() : "") +
                (getNumberOfTrials() != -1 ? "," + getNumberOfTrials() : "") +
                (getEmailAddr() != null ? "," + "'" + SQLUtil.primer(getEmailAddr()) + "'" : "") +
                (getFirstName() != null ? "," + "'" + SQLUtil.primer(getFirstName()) + "'" : "") +
                (getLastName() != null ? "," + "'" + SQLUtil.primer(getLastName()) + "'" : "") +
                (getLoginId() != null ? "," + "'" + SQLUtil.primer(getLoginId()) + "'" : "") +
                (getPassword() != null ? "," + "'" + SQLUtil.primer(Security.encrypt(getPassword())) + "'" : "") +
                (getPhone() != null ? "," + "'" + SQLUtil.primer(getPhone()) + "'" : "") +
                (getOrgIID() != null ? "," + getOrgIID() : "") +
                ");";

        //licensing add
        //delete all user licensing
        query = query + " DELETE FROM USERMODULEGRANT " +
                " WHERE USERID =" + getOid() + ";";
        //add user licenses
        if (isAccolades()) {
            query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES (" + ModuleKind.ACCOLADES
                    .getIntValue() + "," + getOid().getLongValue() + ");";
        }
        if (isCompass()) {
            query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES (" + ModuleKind.COMPASS
                    .getIntValue() + "," + getOid().getLongValue() + ");";
        }
        if (isConduit()) {
            query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES (" + ModuleKind.CONDUIT
                    .getIntValue() + "," + getOid().getLongValue() + ");";
        }

        return query;
    }


    private String getDeleteQuery()
            throws QueryException {
        return " UPDATE " + TABLE +
                " SET " +
                COL_DELETESTATE + "= " + getDeleteState().getIntValue() + " " +
                " WHERE " + COL_OBJECTID + "=" + getOid().getLongValue();
    }


    public Oid getDepartmentIID()
            throws QueryException {
        Oid id = null;
        try {
            BMPropertyMap props = getProperties();
            if (props != null) {
                BMProperty prop = (BMProperty) props.get("prop" + IDCONST.DEPARTMENT.getIIDValue().toString());
                Object o = prop.getValue();
                if (o != null && o instanceof Long)
                    id = new Oid(((Long) o).longValue());
            }
        } catch (Exception ex) {
            throw new QueryException(ex);
        }
        return id;
    }


    public String getEmailAddr()
            throws OculusException {
        return (String) _emailAddr.getValue();

    }


    public int getEmailStatus()
            throws OculusException {
        if (_autoLogout.getValue() != null)
            return ((Integer) _autoLogout.getValue()).intValue();
        else
            return -1;
    }


    public String getFirstName()
            throws OculusException {
        return (String) _firstName.getValue();

    }


    public String getFullName(boolean hasComma)
            throws OculusException {
        if (hasComma)
            return ((String) _lastName.getValue() + ", " + (String) _firstName.getValue());
        else
            return ((String) _firstName.getValue() + " " + (String) _lastName.getValue());
    }


    public IGroupColl getGroups() throws OculusException {
        return (IGroupColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "GroupUserColl", getOid());
    }


    public String getLastName()
            throws OculusException {
        return (String) _lastName.getValue();

    }


    //-------------------------- Protected Methods -----------------------------
    private String getLoadQuery()
            throws QueryException {

        return " SELECT appuser.*, usergroupasc.GROUPID, " +
                "mod_accolades.modulecode AS " + COL_ACCOLADES + "," +
                "mod_compass.modulecode AS " + COL_COMPASS + "," +
                "mod_conduit.modulecode AS " + COL_CONDUIT + " " +
                "FROM ((((" + TABLE + " LEFT OUTER JOIN " +
                "USERMODULEGRANT AS mod_accolades ON " +
                "APPUSER.OBJECTID = mod_accolades.USERID AND " +
                "mod_accolades.MODULECODE = " + ModuleKind.ACCOLADES.getIntValue() + ") LEFT OUTER JOIN " +
                "USERMODULEGRANT AS mod_compass ON " +
                "objectid = mod_compass.userid AND " +
                "mod_compass.modulecode = " + ModuleKind.COMPASS.getIntValue() + ") LEFT OUTER JOIN " +
                "USERMODULEGRANT AS mod_conduit ON " +
                "objectid = mod_conduit.userid AND " +
                "mod_conduit.modulecode = " + ModuleKind.CONDUIT.getIntValue() + ") LEFT OUTER JOIN " +
                "USERGROUPASC ON OBJECTID = USERGROUPASC.USERID) " +
                "WHERE objectid = " + getOid().getLongValue();

    }


    public String getLoginId()
            throws OculusException {
        return (String) _loginId.getValue();
    }


    public String getName()
            throws OculusException {
        return getFullName(false);
    }


    public int getNumberOfTrials()
            throws OculusException {
        if (_numberOfTrials.getValue() != null)
            return ((Integer) _numberOfTrials.getValue()).intValue();
        else
            return -1;
    }


    public IOrganization getOrganizationObject()
            throws OculusException {
        return getOrganizationObject(false);
    }


    public IOrganization getOrganizationObject(boolean edit)
            throws OculusException {
        if (_OrgIID != null) {
            return (IOrganization) getObjectContext().getCRM().getCompObject(getObjectContext(), "Organization",
                                                                             _OrgIID, edit);
        } else {
            return null;
        }
    }


    //------------------------ ACCESSORS -------------------------------------

    public Oid getOrgIID()
            throws QueryException {
        return _OrgIID;
    }


    public String getPassword()
            throws OculusException {
        return (String) _password.getValue();
    }


    public String getPhone()
            throws OculusException {
        return (String) _phone.getValue();

    }


    private String getUpdateQuery()
            throws OculusException {
        //on edit the number of failed logins is reset
        setNumberOfTrials(0);
        //user update
        String query = " UPDATE " + TABLE + " " +
                " SET " +
                COL_ACCESSID + "= " + getAccessIID().getLongValue() + " " +
                " , " + COL_MESSAGEATTACHED + "= " + (hasMessageAttached() ? "1" : "0") + " " +
                " , " + COL_FILEATTACHED + "= " + (hasFileAttached() ? "1" : "0") + " " +
                " , " + COL_LINKATTACHED + "= " + (hasLinkAttached() ? "1" : "0") + " " +
                " , " + COL_DELETESTATE + " = " + getDeleteState().getIntValue() + " " +
                //specific to user
                " , " + COL_ISTEMPORARY + "= " + (isTemporary() ? "1" : "0") + " " +
                " , " + COL_ISINTERNAL + "= " + (isCustomer() ? "0" : "1") + " " +
                (getActive() != null ? " , " + COL_ACTIVE + "= " + getActive().getIntValue() + " " : " ") +
                (getNumberOfTrials() != -1 ? " , " + COL_NUMBEROFTRIALS + "= " + getNumberOfTrials() + " " : " ") +
                (getEmailAddr() != null ? " ,  " + COL_EMAILADDR + "='" + SQLUtil.primer(getEmailAddr()) + "' " : " ") +
                (getFirstName() != null ? " ,  " + COL_FIRSTNAME + "='" + SQLUtil.primer(getFirstName()) + "' " : " ") +
                (getLastName() != null ? " ,  " + COL_LASTNAME + "='" + SQLUtil.primer(getLastName()) + "' " : " ") +
                (getLoginId() != null ? " ,  " + COL_LOGINID + "='" + SQLUtil.primer(getLoginId()) + "' " : " ") +
                (getPassword() != null ? " ,  " + COL_PASSWORD + "='" + SQLUtil.primer(Security.encrypt(getPassword()
                )) + "' " : " ") +
                (getPhone() != null ? " ,  " + COL_PHONE + "='" + getPhone() + "' " : " ") +
                (getOrgIID() != null ? " ,  " + COL_ORGID + "=" + getOrgIID() + " " : " ") +
                " WHERE " + COL_OBJECTID + "=" + getOid().getLongValue() + ";";

        //licensing add
        //delete all user licensing
        query = query + " DELETE FROM USERMODULEGRANT " +
                " WHERE USERID =" + getOid() + ";";
        //add user licenses
        if (isAccolades()) {
            query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES (" + ModuleKind.ACCOLADES
                    .getIntValue() + "," + getOid().getLongValue() + ");";
        }
        if (isCompass()) {
            query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES (" + ModuleKind.COMPASS
                    .getIntValue() + "," + getOid().getLongValue() + ");";
        }
        if (isConduit()) {
            query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES (" + ModuleKind.CONDUIT
                    .getIntValue() + "," + getOid().getLongValue() + ");";
        }


        return query;
    }


    /**
     * isAccolades method comment.
     */
    public boolean isAccolades() throws OculusException {
        if (_isAccolades.getValue() != null)
            return ((Boolean) _isAccolades.getValue()).booleanValue();
        else
            return false;
    }


    /**
     * isAdmin method comment.
     */
    public boolean isAdmin() throws com.oculussoftware.api.repi.QueryException {
        return (getOid().getLongValue() == IDCONST.USER_ADMIN.getLongValue() ? true : false);
    }


    /**
     * isCompass method comment.
     */
    public boolean isCompass() throws OculusException {
        if (_isCompass.getValue() != null)
            return ((Boolean) _isCompass.getValue()).booleanValue();
        else
            return false;
    }


    /**
     * isConduit method comment.
     */
    public boolean isConduit() throws OculusException {
        if (_isConduit.getValue() != null)
            return ((Boolean) _isConduit.getValue()).booleanValue();
        else
            return false;
    }


    public boolean isCustomer()
            throws OculusException {
        return !_isInternal;
    }


    public IUser isCustomer(boolean bool)
            throws OculusException {
        if (_perState == PersState.UNMODIFIED)
            _perState = PersState.MODIFIED;
        _isInternal = !bool;
        return this;
    }


    /**
     * isSysUser method comment.
     */
    public boolean isSysUser() throws OculusException {
        return (getActive() == ActiveKind.ACTIVE ? true : false);
    }


    /**
     * isAdmin method comment.
     */
    public boolean isTheSysUser() throws com.oculussoftware.api.repi.QueryException {
        return (getOid().getLongValue() == IDCONST.USER_SYSTEM.getLongValue() ? true : false);
    }


    private void load(IDataSet results) throws OculusException {
        IRepository rep = getObjectContext().getRepository();
        if (results.getIID() == null)
            results.setIID(results.getLong(COL_OBJECTID));
        setPersState(PersState.PARTIAL);
        _guid = new Guid(results.getString(COL_Guid).trim()); // get Guid
        _iid = rep.makeReposID(results.getLong(COL_OBJECTID)); // get IID
        _classIID = new Oid(results.getLong(COL_CLASSID)); // get class
        //    if (getDefnObject().hasStateMachine())
        _stateIID = rep.makeReposID(results.getLong(COL_STATEID)); // get state
        setCreatorIID(rep.makeReposID(results.getLong(COL_CREATORID)));
        setAccessIID(rep.makeReposID(results.getLong(COL_ACCESSID)));
        setCreationDate(results.getTimestamp(COL_CREATIONDATE));
        setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
        setFileAttached(results.getBoolean(COL_FILEATTACHED));
        setLinkAttached(results.getBoolean(COL_LINKATTACHED));
        setDeleteState(DeleteState.getInstance(results.getInt(COL_DELETESTATE)));
        //specific to user
        setNumberOfTrials(results.getInt(COL_NUMBEROFTRIALS));
        setEmailAddr(results.getString(COL_EMAILADDR));
        setFirstName(results.getString(COL_FIRSTNAME));
        setLastName(results.getString(COL_LASTNAME));
        if (results.getString(COL_PASSWORD) != null)
            setPassword(Security.decrypt((results.getString(COL_PASSWORD))));
        setLoginId(results.getString(COL_LOGINID));
        setPhone(results.getString(COL_PHONE));
        if (results.get(COL_ORGID) != null)
            setOrgIID(rep.makeReposID(results.getLong(COL_ORGID)));
        setActive(ActiveKind.getInstance(results.getInt(COL_ACTIVE)));
        isCustomer(!results.getBoolean(COL_ISINTERNAL));
        isTemporary(results.getBoolean(COL_ISTEMPORARY));
        //if ((results.containsKey(COL_GROUPID)) && (results.get(COL_GROUPID)!=null))
        //    setGroupIID(rep.makeReposID(results.getLong(COL_GROUPID)));
        if ((results.containsKey(COL_ACCOLADES)) && (results.get(COL_ACCOLADES) != null))
            setAccolades(true);
        if ((results.containsKey(COL_COMPASS)) && (results.get(COL_COMPASS) != null))
            setCompass(true);

        if ((results.containsKey(COL_CONDUIT)) && (results.get(COL_CONDUIT) != null))
            setConduit(true);


    }


    public void put(Object key, Object value)
            throws OculusException {
        if (key instanceof String && value instanceof BMProperty) {
            BMProperty property = (BMProperty) value;
            if (key.equals(LABEL_AUTOLOGOUT))
                setEmailStatus(((Integer) property.getValue()).intValue());
            else if (key.equals(LABEL_EMAILADDR))
                setEmailAddr((String) property.getValue());
            else if (key.equals(LABEL_FIRSTNAME))
                setFirstName((String) property.getValue());
            else if (key.equals(LABEL_LASTNAME))
                setLastName((String) property.getValue());
            else if (key.equals(LABEL_LOGINID))
                setLoginId((String) property.getValue());
            else if (key.equals(LABEL_ORGID))
                setOrgIID((Oid) value);
            else if (key.equals(LABEL_PASSWORD))
                setPassword((String) value);
            else if (key.equals(LABEL_PHONE))
                setPhone((String) value);
            else
                super.put(key, value);
        }
    }


    /**
     * Checks for Unique LoginID
     */

    public IPersistable save() throws OculusException {
        IQueryProcessor stmt = null;
        try {
            IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
            stmt = repConn.createProcessor();
            String query;
            if (isCustomer())
                query = "Select " + COL_LOGINID + ", " + COL_EMAILADDR + " from " + TABLE + " where " + COL_OBJECTID
                        + " <> " + _iid.getLongValue() + " AND " + COL_DELETESTATE + "<>" + DeleteState.DELETED
                        .getIntValue() + " AND " + COL_ISINTERNAL + "=" + (isCustomer() ? "0" : "1");// + " AND " +
                // COL_LOGINID + " IS NOT NULL";
            else
                query = "Select " + COL_LOGINID + ", " + COL_EMAILADDR + " from " + TABLE + " where " + COL_OBJECTID
                        + " <> " + _iid.getLongValue() + " AND " + COL_DELETESTATE + "<>" + DeleteState.DELETED
                        .getIntValue() + " AND " + COL_ISINTERNAL + "=" + (isCustomer() ? "0" : "1");// + " AND " +
            // COL_LOGINID + " IS NOT NULL";

            IDataSet ds = stmt.retrieve(query);
            while (ds.next()) {
                if ((ds.getString(COL_LOGINID) != null) && (ds.getString(COL_LOGINID).equals(getLoginId()))) {
                    throw new OculusException("DuplicateLoginID", ErrorType.UNIQUE_CONSTRAINT_VIOLATION);
                }
                if ((ds.getString(COL_EMAILADDR) != null) && (ds.getString(COL_EMAILADDR).equals(getEmailAddr()))) {
                    throw new OculusException("DuplicateEmail", ErrorType.UNIQUE_CONSTRAINT_VIOLATION);
                }
            }
            if (stmt != null)
                stmt.close();
            super.save();
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return this;
    }


    /**
     * setAccolades method comment.
     */
    public IUser setAccolades(boolean bool) throws QueryException {
        _isAccolades.setValue(new Boolean(bool));
        return this;
    }


    public IUser setActive(ActiveKind active)
            throws QueryException {
        _active = active;
        return this;
    }


    /**
     * setCompass method comment.
     */
    public IUser setCompass(boolean bool) throws QueryException {
        _isCompass.setValue(new Boolean(bool));
        return this;
    }


    /**
     * setCompass method comment.
     */
    public IUser setConduit(boolean bool) throws QueryException {
        _isConduit.setValue(new Boolean(bool));
        return this;
    }


    public IUser setEmailAddr(String emailAddr)
            throws QueryException {
        _emailAddr.setValue(emailAddr);
        return this;
    }


    public IUser setEmailStatus(int emailStatus)
            throws QueryException {
        _autoLogout.setValue(new Integer(emailStatus));
        return this;
    }


    public IUser setFirstName(String firstName)
            throws QueryException {
        _firstName.setValue(firstName);
        return this;
    }


    public IUser setLastName(String lastName)
            throws QueryException {
        _lastName.setValue(lastName);
        return this;
    }


    public IUser setLoginId(String loginId)
            throws QueryException {
        _loginId.setValue(loginId);
        return this;
    }


    //expect lastname,firstname else throw exception
    public Element setName(String name)
            throws OculusException {
        int iOffset;
        if ((iOffset = name.indexOf(",")) < 0)
            throw new OculusException("Expected name in form FirstName,LastName");
        setLastName(name.substring(0, iOffset).trim());
        setFirstName(name.substring(iOffset + 1, name.length()).trim());
        return this;
    }


    private IUser setNumberOfTrials(int numberOfTrials)
            throws QueryException {
        _numberOfTrials.setValue(new Integer(numberOfTrials));
        return this;
    }


    //------------------------ MUTATORS -------------------------------------
    public IUser setOrgIID(Oid id)
            throws QueryException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _OrgIID = id;
        return this;
    }


    public IUser setPassword(String password)
            throws QueryException {
        _password.setValue(password);
        return this;
    }


    public IUser setPhone(String phone)
            throws QueryException {
        _phone.setValue(phone);
        return this;
    }


    public IBusinessObject softDelete()
            throws OculusException {
        setDeleteState(DeleteState.DELETED);
        setAccolades(false);
        setCompass(false);
        setConduit(false);
        return this;
    }


    public boolean isTemporary()
            throws OculusException {
        return _isTemporary;
    }


    public IUser isTemporary(boolean bool)
            throws OculusException {
        if (_perState == PersState.UNMODIFIED)
            _perState = PersState.MODIFIED;
        _isTemporary = bool;
        return this;
    }


    public static IUser getUserViaEmail(String EmailAddress, boolean isInternal, boolean edit, ObjectContext context)
            throws OculusException {

        IQueryProcessor stmt = null;
        Oid userID = null;
        try {
            IRConnection repConn = context.getRepository().getDataConnection(context);
            stmt = repConn.createProcessor();
            String query;


            query = "SELECT *" + " FROM " + "APPUSER" + " WHERE " + " EMAILADDR " + " = '" + EmailAddress + "' AND "
                    + " ISINTERNAL " + " = " + (isInternal ? "1" : "0");
            IDataSet ds = stmt.retrieve(query);
            while (ds.next()) {
                //we must have an iid that points to a user with a matching email address
                userID = new Oid(ds.getLong("OBJECTID"));
            }

            if (userID != null) {
                return (IUser) context.getCRM().getCompObject(context, "User", userID, edit);
            } else {
                return null;
            }

        } finally {
            if (stmt != null)
                stmt.close();
        }

    }
}
