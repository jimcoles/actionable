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
 *
 */
public class Group extends BusinessObject {

    protected String LABEL_GROUPID = "Associated Group";
    protected String LABEL_Guid = "Guid";
    protected String LABEL_PARGROUPID = "Parent Group ID";
    //protected String LABEL_DELETEKIND          = "Delete Kind";
    //protected String LABEL_ISACTIVE            = "Is Active";


    protected Oid _grpIID;
    protected Oid _parGrpIID;
    //--------------------------- Public Constructors --------------------------

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public Group() {
        super();
    }

    /**
     * Returns a copy of the current Group object.
     * <p>
     * Note: The exceptions are being withheld because this method overrides
     * the one in Object().  Consider using a different method name since it
     * doesn't look like we're taking advantage of Cloneable.
     */
    public Object dolly() throws OculusException {
        Group grp = null;
        grp = new Group();
        grp.setOid(getOid());
        grp.setObjectContext(getObjectContext());
        grp.setPersState(getPersState());
        if (getDefnObject() != null)
            grp.setDefnObject(getDefnObject());
        //if (getStateObject() != null)
        //  grp.setStateObject(getStateObject());
        grp._classIID = _classIID;
        grp._stateIID = _stateIID;
        //Saleem added to this line
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            grp.putAll(getProperties());
        grp.setName(getName());
        grp.setDescription(getDescription());
        grp._creatorIID = _creatorIID;
        grp._accessIID = _accessIID;
        grp.setCreationDate(getCreationDate());
        grp.setMessageAttached(hasMessageAttached());
        grp.setLinkAttached(hasLinkAttached());
        grp.setFileAttached(hasFileAttached());

        //specific to Group
        grp.setParentGroupIID(getParentGroupIID());
        return grp;
    }

    //----------------- IRPropertyMap Methods---------------------------------
    public Object get(Object key)
            throws OculusException {
        if (key instanceof String) {
            if (key.equals(LABEL_PARGROUPID))
                return _parGrpIID;
            else if (key.equals(LABEL_GROUPID))
                return _grpIID;
                //extended attr
            else
                return super.get(key);
        } else
            return null;
    }

    public Oid getAccessorIID()
            throws QueryException {
        return getOid();
    }

    protected String getCreateQuery()
            throws OculusException {
        return "INSERT INTO " + TABLE + " " +
                " (" + COL_OBJECTID + ", "
                + COL_CLASSID + ", "
                //    +COL_STATEID+", "
                + COL_DELETESTATE + ", "
                + COL_Guid + ", "
                + COL_NAME + ", "
                + COL_DESCRIPTION + ", "
                + COL_CREATIONDATE + ", "
                + COL_CREATORID + ", "
                + COL_ACCESSID + ", "
                + COL_MESSAGEATTACHED + ", "
                + COL_FILEATTACHED + ", "
                + COL_LINKATTACHED + ", "
                //specific to Group
                + COL_PARGROUPID + " " +
                ") " +


                " VALUES " +
                " (" + getOid().getLongValue() + ","
                + getDefnObject().getIID().getLongValue() + ","
                // +getStateObject().getOid().getLongValue()+","
                + getDeleteState().getIntValue() + ","
                + "'" + getGuid().toString() + "',"
                + "'" + SQLUtil.primer(getName()) + "',"
                + "'" + getDescription() + "',"
                + "'" + getCreationDate().toString() + "',"
                + getCreatorIID().getLongValue() + ","
                + "0,"
//              +getAccessIID().getLongValue()+","
                + (hasMessageAttached() ? "1" : "0") + ","
                + (hasFileAttached() ? "1" : "0") + ","
                + (hasLinkAttached() ? "1" : "0") + ","
                //specific to Group
                + (getParentGroupIID() == null ? "null" : "" + getParentGroupIID().getLongValue()) + "" +
                ") ";
    }

    protected String getDeleteQuery()
            throws QueryException {
        return " DELETE FROM " + TABLE + " " +
                " WHERE " + COL_OBJECTID + "=" + getOid().getLongValue();
    }

    //-------------------------- Protected Methods -----------------------------
    protected String getLoadQuery()
            throws QueryException {
        return "SELECT * " +
                "FROM " + TABLE + " " +
                "WHERE " + COL_OBJECTID + "=" + getOid().getLongValue();
    }

    public Oid getParentGroupIID()
            throws QueryException {
        return _parGrpIID;
    }

    protected String getUpdateQuery()
            throws OculusException {
        return " UPDATE " + TABLE + " " +
                " SET " +
                "   " + COL_NAME + "='" + SQLUtil.primer(getName()) + "' " +
                " , " + COL_DESCRIPTION + "='" + getDescription() + "' " +
                //  " , "+COL_STATEID+"= "+getStateObject().getOid().getLongValue()+" "+
                " , " + COL_DELETESTATE + "= " + getDeleteState().getIntValue() + " " +
                " , " + COL_ACCESSID + "= " + getAccessIID().getLongValue() + " " +
                " , " + COL_MESSAGEATTACHED + "= " + (hasMessageAttached() ? "1" : "0") + " " +
                " , " + COL_FILEATTACHED + "= " + (hasFileAttached() ? "1" : "0") + " " +
                " , " + COL_LINKATTACHED + "= " + (hasLinkAttached() ? "1" : "0") + " " +
                //specific to Group
                " , " + COL_PARGROUPID + "= " + (getParentGroupIID() == null ? "null" : "" + getParentGroupIID().getLongValue()) + " " +

                " WHERE " + COL_OBJECTID + "=" + getOid().getLongValue();
    }

    public IUserColl getUsers()
            throws OculusException {
        return getUsers(false);
    }

    public IUserColl getUsers(boolean editable)
            throws OculusException {
        return (IUserColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "UserGroupColl", getOid(), editable);
    }

    protected void load(IDataSet results)
            throws OculusException {
        if (results.getIID() == null)
            results.setIID(results.getLong(COL_OBJECTID));
        super.load(results);
        //specific to Group
        setName(results.getString(COL_NAME));                // get name
        //setDescription(results.getString(COL_DESCRIPTION));  // get desc
        if (results.get(COL_PARGROUPID) != null)
            setParentGroupIID(new Oid(results.getLong(COL_PARGROUPID)));
        //setGroupIID(new SequentialIID(results.getLong(COL_STATEID)));

    }

    public void put(Object key, Object value)
            throws OculusException {
        if (key instanceof String && value instanceof IRProperty) {
            IRProperty property = (IRProperty) value;
            if (key.equals(LABEL_PARGROUPID))
                setParentGroupIID((Oid) value);
            else
                super.put(key, value);
        }
    }

    public IGroup setParentGroupIID(Oid id)
            throws QueryException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parGrpIID = id;
        return this;
    }
}