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
import org.jkcsoft.bogey.system.SOM;
import org.jkcsoft.java.beans.BeanDispatcher;

/**
 * UML Analogy: core.ModelElement.
 * <p>
 * Base class for all DataModel elements such as Class, Attr, etc.
 * <p>
 * By declaring ModelElement to implement IObjectRecord we are stating that
 * all SSC metamodel elements are instances of Class, even the Class itself.
 *
 * @author Jim Coles
 * @version 1.0
 */

public abstract class ModelElement implements Element {
    //----------------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------------

    // delegate object(s)
    private static BeanDispatcher beaner;

    // primitive state properties
    private Guid guid;
    private Oid oid;
    private String displayName;
    private String codeName;
    private String description;
    private PersState persState;
    private DeleteState deletestate = DeleteState.NOT_DELETED;
    private boolean isActive = true;
    private ConfigKind configkind = ConfigKind.READ_ONLY;

    // associations
    private Namespace namespace;
    private DataModel dataModel;

    //----------------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------------
    public ModelElement() {

    }

    public ModelElement(Namespace namespace, DataModel dataModel, Guid guid, Oid oid, String displayName,
                        String codeName, String description, ConfigKind configkind)
    {
        this.guid = guid;
        this.oid = oid;
        this.displayName = displayName;
        this.codeName = codeName;
        this.description = description;
        this.configkind = configkind;
        this.namespace = namespace;
        this.dataModel = dataModel;
    }

    //----------------------------------------------------------------------------
    // Instance methods
    //----------------------------------------------------------------------------

    public Guid getGuid() {
        return guid;
    }

    @Override
    public Oid getOid() {
        return oid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getDescription() {
        return description;
    }

    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public void setOid(Oid oid) throws Exception {
        this.oid = oid;
    }

    public void setDisplayName(String displayName) {
        displayName = displayName;
    }

    public void setCodeName(String codeName) {
        codeName = codeName;
    }

    public Element setDescription(String description) {
        this.description = description;
        return this;
    }

    public PersState getPersState() {
        return persState;
    }

    public void setPersState(PersState persState) {
        this.persState = persState;
    }

    public void setDeletestate(DeleteState deletestate) {
        this.deletestate = deletestate;
    }

    public void setIsActive(boolean active) {
        this.isActive = active;
    }

    public DeleteState getDeletestate() {
        return deletestate;
    }

    public boolean isActive() {
        return isActive;
    }

    public ConfigKind getConfigkind() {
        return configkind;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    /**
     * Sets AttributeValue associated with attrCodeName.
     */
    public void set(String attrCodeName, Object value)
            throws Exception {
        beaner.set(this, attrCodeName, value);
    }

    /**
     * Gets AttributeValue associated with attrCodeName.
     */
    public Object get(String attrCodeName) throws Exception {
        return beaner.get(this, attrCodeName);
    }

    /**
     * Non-primitive attributes will be retrieved by id.  Consuming object
     * can dereference into an object.
     */
    public Identifier getAssocRef(java.lang.String str) throws Exception {
        return null;
    }

    public void setParentObject(ModelElement parent) throws Exception {
        if (!(parent instanceof DataModel))
            throw new Exception("Wrong data type in setParentObject().  Requires " + DataModel.class.getName());
        dataModel = (DataModel) parent;
    }

    public ModelElement getParentObject() {
        return dataModel;
    }

    public SOM getSom() {
        return null; // TODO
    }

}