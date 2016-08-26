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

import org.jkcsoft.bogey.metamodel.*;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.AppSystem;
import org.jkcsoft.java.util.Strings;

import java.util.StringTokenizer;

public class BMProperty {
    //
    public static final String TABLE_CHAR = "CHARVALUE";
    public static final String TABLE_LONGCHAR = "LONGCHARVALUE";
    public static final String TABLE_BOOLEAN = "BOOLEANVALUE";
    public static final String TABLE_TIME = "TIMEVALUE";
    public static final String TABLE_BLOB = "BLOBVALUE";
    public static final String TABLE_ENUM = "ENUMVALUE";
    public static final String TABLE_MULTIENUM = "ENUMSELECTION";
    private static final String NULL_VALUE = "(none)";

    //
    private ReposObject _parent;
    private Oid _attribIID;
    private boolean _required = false;
    private Object _value = null;
    private PersState _perState;


    /**
     * Constructor for creating new Attributes.
     */
    public BMProperty() {
        setPersState(PersState.UNINITED);
    }

    public BMProperty(ReposObject parentObject) throws AppException {
        _parent = parentObject;
        setPersState(PersState.UNINITED);
    }

    public BMProperty setRequired(boolean req) {
        _required = req;
        return this;
    }

    public boolean isRequired() {
        return _required;
    }

    public Attribute getDefnObject() throws AppException {
        return (Attribute) null; // getObjectContext().getCRM().getCompObject(getObjectContext(), "Attribute",
        // _attribIID);
    }

    public ReposObject getOwnerObject() {
        return _parent;
    }

    public PersState getPersState() {
        return _perState;
    }

    public void setDefnObject(Oid id) throws AppException {
        _attribIID = id;
    }

    public void setDefnObject(Attribute attrib) throws AppException {
        setDefnObject(attrib.getOid());
    }

    public void setOwnerObject(ReposObject obj) throws AppException {
        _parent = obj;
    }

    public BMProperty setPersState(PersState state) {
        _perState = state;
        if ((getPersState().equals(PersState.MODIFIED) ||
                getPersState().equals(PersState.NEW) ||
                getPersState().equals(PersState.DELETED)) && getOwnerObject().getPersState().equals(PersState.UNMODIFIED))
            getOwnerObject().setPersState(PersState.MODIFIED);
        return this;
    }

    public PrimitiveDataType getPrimitiveDataType() throws AppException {
        return (PrimitiveDataType) getDefnObject().getType();
    }

    public String getName() throws AppException {
        return getDefnObject().getCodeName();
    }

    public AttributeKind getAttributeKind() throws AppException {
        return getDefnObject().getAttributeKind();
    }

    public BMProperty setValue(Object value) throws AppException {

        String strValue = null;
        if (value != null && !value.toString().equals(""))
            strValue = value.toString();

        try {
            PrimitiveDataType prim = getPrimitiveDataType();
            AttributeKind ak = getAttributeKind();

            if (prim.equals(PrimitiveEnum.BOOLEAN))
                _value = strValue == null ? null : new Boolean(strValue.equals("true"));
            else if (prim.equals(PrimitiveEnum.TIME)) {
                if (strValue != null) strValue = Strings.trimSafe(strValue);
// TODO                _value = strValue == null ? null : com.oculussoftware.ui.DateFormatter.getDateTimestamp
// (strValue);
            } else if (prim.equals(PrimitiveEnum.INTEGER)) {
                _value = Strings.isValidIntOrLong(strValue) ? new Integer(strValue) : null;
            } else if (prim.equals(PrimitiveEnum.DECIMAL)) {
// TODO                _value = Strings.isValidFloat(strValue) ? new Float(strValue) : null;
            } else if ((prim.equals(PrimitiveEnum.ENUM) || prim.equals(PrimitiveEnum.RADIO)))
                _value = (strValue == null || strValue.equals("0")) ? null : new Long(strValue);
            else
                _value = strValue;

            if (getPersState().equals(PersState.UNMODIFIED))
                setPersState(PersState.MODIFIED);
            if (getPersState().equals(PersState.UNINITED))
                setPersState(PersState.UNMODIFIED);
        } catch (AppException ex) {
            throw new AppException(ex);
        }
        return this;
    }

    public Object getValue() throws AppException {
        Object value = null;
        Attribute attr = getDefnObject();

        if (attr.getAttributeKind().equals(AttributeKind.AGGREGATE)) {
            ReposObject owner = getOwnerObject();
            if (owner instanceof BusinessObject) {
// TODO                value = _getRollUpValue((BusinessObject) owner, attr.getRollUpAttribute());
            } else
                throw new AppException("Aggregate attributes can only be assigned to business objects.");
        } else
            value = _value;

        return value;
    }


    public Object dolly() throws AppException {
        BMProperty state = new BMProperty();
        state.setOwnerObject(getOwnerObject());
        state._attribIID = _attribIID;
        state._value = _value;
        state.setRequired(isRequired());
        state.setPersState(getPersState());
        return state;
    }

    public String toString() {
        String strValue = NULL_VALUE;
        try {
            Attribute attr = getDefnObject();
            PrimitiveEnum prim = attr.getPrimitiveDataType();

            Object value = getValue();

            if (value != null) {
                strValue = value.toString();
                if (prim.equals(PrimitiveEnum.TIME)) {
                    // TODO strValue = Dates.toDateString(strValue);
                } else if (prim.equals(PrimitiveEnum.ENUM)) {
                    if (strValue != null) {
                        long enumLitID = Long.parseLong(strValue);
                        Oid enumLitIID = AppSystem.getRepo().makeReposID(enumLitID);
                        BMProperty enumliteral = AppSystem.getRepo().getBMRepository().getEnumliteral(enumLitIID,
                                                                                                      false);
                        strValue = enumliteral.toString();
                    }
                } else if (prim == PrimitiveEnum.MULTIENUM || prim == PrimitiveEnum.MULTICHECK) {
                    String enumLit = "";
                    if (strValue != null) {
                        StringTokenizer allValues = new StringTokenizer(strValue, ",");
                        while (allValues.hasMoreTokens()) {
                            long enumLitID = Long.parseLong(allValues.nextToken());
                            Oid enumLitIID = AppSystem.getRepo().makeReposID(enumLitID);
                            BMProperty enumliteral = AppSystem.getRepo().getBMRepository().getEnumliteral(enumLitIID,
                                                                                                          false);
                            enumLit += enumliteral.getName();
                            if (allValues.hasMoreTokens())
                                enumLit += "<BR>";
                        }
                        strValue = enumLit;
                    }
                }
            }
        } catch (AppException exp) {
            strValue = exp.toString();
        }
        return strValue;
    }

}