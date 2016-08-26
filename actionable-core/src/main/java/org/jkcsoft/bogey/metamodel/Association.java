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

/**
 * @author J. Coles
 * @version 1.0
 */
public class Association extends Relationship {

    private Oid _iid;
    private String _name = null;
    private Class _fromClass = null;
    private Class _toClass = null;
    private Class _assocClass = null;
    private String _fromColName = null;
    private String _toColName = null;
    private boolean _m2m = false;
    private boolean _rec = false;
    private String _syn = "";


    /**
     * M-2-1 association.
     */
    public Association(Oid id, Class fromClass, Class toClass, String fromColName, String displayName)
            throws Exception {
        _iid = id;
        _name = displayName;
        setFromClass(fromClass);
        setToClass(toClass);
        _fromColName = fromColName;
        DataModel.getInstance().addAssoc(this);
    }

    /**
     * M-2-M association with a related class.
     */
    public Association(Oid id, Class fromClass, Class toClass, Class assocClass, String fromColName, String
            toColName, String displayName)
            throws Exception {
        this(id, fromClass, toClass, fromColName, displayName);
        setAssocClass(assocClass);
        _toColName = toColName;
        _m2m = true;
    }

    /**
     * Recursive associations.
     */
    public Association(Oid id, Class theClass, String parentColName, String displayName) throws Exception {
        this(id, theClass, theClass, parentColName, displayName);
        _rec = true;
        _syn = "parrec";

    }

    public Oid getIID() {
        return _iid;
    }

    public String getDisplayName() {
        return _name;
    }

    public Association setFromClass(Class cls) {
        _fromClass = cls;
        return this;
    }

    public Association setToClass(Class cls) {
        _toClass = cls;
        return this;
    }

    public Association setAssocClass(Class assocClass) {
        _assocClass = assocClass;
        return this;
    }

    public Class getAssocClass() {
        return _assocClass;
    }

    public boolean isM2M() {
        return _m2m;
    }

    public boolean isRec() {
        return _rec;
    }

    public Class getFromClass() {
        return _fromClass;
    }

    public Class getToClass() {
        return _toClass;
    }

    public String getSyn() {
        return _syn;
    }

    public String getFromColName() {
        return _fromColName;
    }

    public String getToColName() {
        return _toColName;
    }

    public boolean equals(Object obj) {
        boolean retVal = false;
        if (obj instanceof Association) {
            retVal = (((Association) obj).getIID().getLongValue() == getIID().getLongValue());
        }
        return retVal;
    }

}
