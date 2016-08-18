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

import org.jkcsoft.bogey.metamodel.Attribute;
import org.jkcsoft.bogey.metamodel.Guid;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.metamodel.PersState;
import org.jkcsoft.bogey.system.AppException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A persistent object.
 */
abstract public class ReposObject implements Element {

    private Guid guid;
    private Oid oid;
    private PersState persState;
    private boolean single = true;
    private Map<Oid, BMProperty> attributes;

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public ReposObject() throws AppException {
        this.guid = new Guid();
        this.attributes = new HashMap<>();
    }

    public Guid getGuid() {
        return guid;
    }

    public ReposObject setOid(Oid oid) {
        this.oid = oid;
        return this;
    }

    public Oid getOid() {
        return oid;
    }

    /**
     * This method needs to be implemented by the concrete object.
     */
    public Collection<ReposObject> getParentContainers() throws AppException {
        return null;
    }

    public ReposObject getParentObject() throws AppException {
        return getParentObject(false);
    }

    public ReposObject getParentObject(boolean editable) throws AppException {
        return null;
    }

    public Collection<ReposObject> getChildCollection() throws AppException {
        return getChildCollection(false);
    }

    public Collection<ReposObject> getChildCollection(boolean editable) throws AppException {
        return null;
    }

    /**
     * This method needs to be implemented by the concrete object.
     */
    public boolean childExists(Oid iid) throws AppException {
        return false;
    }

    /**
     * Sets a property of this bo using the metadata and a value.
     */
    public ReposObject setProperty(Attribute metaprop, Object value) throws AppException {
        BMProperty newProp = new BMProperty(this);
        newProp.setDefnObject(metaprop);
        newProp.setValue(value);
        //getProperties().put(metaprop.getName(), newProp);
        attributes.put(metaprop.getOid(), newProp);
        return this;
    }

    public void setIsSingleton(boolean single) {
        this.single = single;
    }

    private boolean isSingleton() {
        return single;
    }

    /**
     * Sets the bo's persistant state to the given state.
     */
    public ReposObject setPersState(PersState state) {
        persState = state;
        return this;
    }

    /**
     * Returns the bo's persistent state.
     */
    public PersState getPersState() {
        return persState;
    }

    public Map<Oid, BMProperty> getAttributes() {
        return attributes;
    }

    // TODO might need to add this method back; how to get the AccessMgr object?
//    public ReposObject grantPermissions(Oid useriid, Set gs) throws AppException {
//        try {
//            AccessMgr acm = AppSystem.getAccessMgr();
//            // request the actual grant
//            acm.grantPermissions(useriid.getLongValue(), this, gs);
//        }
//        catch (AppException ocu) {
//            throw ocu;
//        } catch (Exception exp) {
//            throw new AppException(exp);
//        }
//        return this;
//    }

}