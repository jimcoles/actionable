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
import org.jkcsoft.bogey.metamodel.PersState;
import org.jkcsoft.bogey.metamodel.RepoMap;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.QueryException;

import java.util.Collection;

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
    public Group() throws AppException {
        super();
    }

    public Group setParentGroupIID(Oid id) {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parGrpIID = id;
        return this;
    }

    /**
     * Returns a copy of the current Group object.
     * <p>
     * Note: The exceptions are being withheld because this method overrides
     * the one in Object().  Consider using a different method name since it
     * doesn't look like we're taking advantage of Cloneable.
     */
    public Object dolly() throws AppException {
        Group grp = new Group();
        grp.setOid(getOid());
        grp.setObjectContext(getObjectContext());
        grp.setPersState(getPersState());
        if (getDefnObject() != null)
            grp.setDefnObject(getDefnObject());
        //if (getStateObject() != null)
        //  grp.setStateObject(getStateObject());
        grp.setOid(this.getOid());
//        grp.setS = _stateIID;
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            grp.putAll(getProperties());
        grp.setName(getName());
        grp.setDescription(getDescription());
        grp.setCreatorIID(this.getCreatorIID());
        grp.setAccessIID(this.getAccessIID());
        grp.setCreationDate(getCreationDate());
        grp.setMessageAttached(hasMessageAttached());
        grp.setLinkAttached(hasLinkAttached());
        grp.setFileAttached(hasFileAttached());

        //specific to Group
        grp.setParentGroupIID(this.getParentGroupIID());
        return grp;
    }

    //----------------- IRPropertyMap Methods---------------------------------
    public Object get(Object key)
            throws AppException {
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

    public Oid getAccessorIID() throws QueryException {
        return getOid();
    }

    public Oid getParentGroupIID() {
        return _parGrpIID;
    }

    public Collection<User> getUsers() throws AppException {
        return getUsers(false);
    }

    public Collection<User> getUsers(boolean editable) throws AppException {
        return (Collection<User>) getObjectContext().getCRM().getCompObject(getObjectContext(), "UserGroupColl",
                                                                            getOid(), editable);
    }

    protected void load(RepoMap results) throws AppException {
//        if (results.getOid() == null)
//            results.setIID(results.getLong(COL_OBJECTID));
//        super.load(results);
//        //specific to Group
//        setName(results.getString(COL_NAME));                // get name
//        //setDescription(results.getString(COL_DESCRIPTION));  // get desc
//        if (results.get(COL_PARGROUPID) != null)
//            setParentGroupIID(new Oid(results.getLong(COL_PARGROUPID)));
//        //setGroupIID(new SequentialIID(results.getLong(COL_STATEID)));
    }


}