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

import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.metamodel.*;

import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
        return (Attribute) null; // getObjectContext().getCRM().getCompObject(getObjectContext(), "Attribute", _attribIID);
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
        return getDefnObject().getAttrKind();
    }


    public BMProperty setValue(Object value)
            throws AppException {

        String strValue = null;
        if (value != null && !value.toString().equals(""))
            strValue = value.toString();

        try {
            PrimitiveDataType prim = getPrimitiveDataType();
            AttributeKind ak = getAttributeKind();

            if (prim.equals(PrimitiveDataType.BOOLEAN))
                _value = strValue == null ? null : new Boolean(strValue.equals("true"));
            else if (prim.equals(PrimitiveDataType.TIME)) {
                if (strValue != null) strValue = StringUtil.removeLeadingSpaces(strValue);
                _value = strValue == null ? null : com.oculussoftware.ui.DateFormatter.getDateTimestamp(strValue);
            } else if (prim.equals(PrimitiveDataType.INTEGER)) {
                _value = StringUtil.isValidIntOrLong(strValue) ? new Integer(strValue) : null;
            } else if (prim.equals(PrimitiveDataType.DECIMAL)) {
                _value = StringUtil.isValidFloat(strValue) ? new Float(strValue) : null;
            } else if ((prim.equals(PrimitiveDataType.ENUM) || prim.equals(PrimitiveDataType.RADIO)))
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

    public Object getValue()
            throws AppException {
        Object value = null;
        Attribute attr = getDefnObject();

        if (attr.getAttrKind().equals(AttributeKind.AGGREGATE)) {
            ReposObject owner = getOwnerObject();
            if (owner instanceof BusinessObject)
                value = _getRollUpValue((BusinessObject) owner, attr.getRollUpAttribute());
            else
                throw new AppException("Aggregate attributes can only be assigned to business objects.");
        } else
            value = _value;

        return value;
    }

    // Returns the sum of the values of the given attribute for the given object
    // and all of it's children.  It returns a Float instead of float to keep the  attribute
    // values consistent.
    // NOTE: Attempting to use instanceof instead of getRootDefinition().
    private Float _getRollUpValue(BusinessObject obj, Attribute attr)
            throws AppException {
        StringBuffer sbf = null;
        String rollupkey = "prop" + attr.getIID();


        if (obj instanceof IProductVersion)
            obj = ((IProductVersion) obj).getDefaultCategory();
        sbf = new StringBuffer();
        _rollUpCat((ICategory) obj, rollupkey, sbf);

        //Use the stringbuffer to calculate the sum now
        //String s = sbf.substring(0,sbf.length()-1);            //chop off last ,
        String s = sbf.toString();
        sbf = null;                                                                                        //gc
        StringTokenizer st = new StringTokenizer(s, ",");
        float sum = 0.0f;
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (StringUtil.isValidFloat(t))
                sum += Float.parseFloat(t);
            else
                throw new AppException("Invalid attribute value in rollup for " + obj.getName() + "," + attr.getName());
        }
        st = null;
        return new Float(sum);
    }

    // Returns a String/StringBuffer that contains a comma-separated list of all of the values
    // for the given attribute on the given category and all of it's subcategories and features.
    // It uses a String so that it can be used to roll-up any kind of attribute, not just numeric
    // ones.
    private void _rollUpCat(ICategory cat, String key, StringBuffer sbf) throws AppException {
        IFeatureColl featurecoll = cat.getFeatures();
        while (featurecoll.hasMoreFeatures()) {

            IFeature feat = featurecoll.nextFeature();

            IFeatureCategoryLink featlink = feat.getFeatureCategoryLinkObject();

            BMPropertyMap map = feat.getProperties();
            if (map.containsKey(key)) {
                BMProperty prop = (BMProperty) map.get(key);
                Object o = prop.getValue();

                if (o != null)
                    sbf.append(o + ",");
            }
            map = featlink.getProperties();
            if (map.containsKey(key)) {
                BMProperty prop = (BMProperty) map.get(key);
                Object o = prop.getValue();

                if (o != null)
                    sbf.append(o + ",");
            }
        }

        ICategoryColl catcoll = cat.getCategories();
        while (catcoll.hasMoreCategories())
            _rollUpCat(catcoll.nextCategory(), key, sbf);
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
            AttributeKind ak = attr.getAttrKind();
            IRDataType dt = attr.getDataType();
            PrimitiveDataType prim = dt.getTypeKind();
            String unitlabel = attr.getUnitLabel();
            UnitPosition unitpos = attr.getUnitPosition();
            if (unitlabel == null) unitlabel = " ";

            Object value = getValue();

            if (value != null) {
                strValue = value.toString();
                if (!unitlabel.equals("") && (prim.equals(PrimitiveDataType.CHAR) || prim.equals(PrimitiveDataType.INTEGER) || prim.equals(PrimitiveDataType.DECIMAL))) {
                    if (unitpos.equals(UnitPosition.RIGHT))
                        strValue = strValue + " " + unitlabel;
                    else
                        strValue = unitlabel + " " + strValue;
                } else if (prim.equals(PrimitiveDataType.TIME)) {
                    strValue = com.oculussoftware.ui.DateFormatter.format(strValue);
                } else if ((prim.equals(PrimitiveDataType.ENUM) && !ak.equals(AttributeKind.PRODENUM)) || (prim.equals(PrimitiveDataType.RADIO))) {
                    String enumLit = null;
                    if (strValue != null) {
                        long enumLitID = Long.parseLong(strValue);
                        Oid enumLitIID = getObjectContext().getRepository().makeReposID(enumLitID);
                        IREnumliteral enumlite = getObjectContext().getRepository().getBMRepository().getEnumliteral(enumLitIID, false);
                        enumLit = enumlite.getName();
                    }
                    strValue = enumLit;
                } else if (prim == PrimitiveDataType.MULTIENUM || prim == PrimitiveDataType.MULTICHECK) {
                    String enumLit = "";
                    if (strValue != null) {
                        StringTokenizer allValues = new StringTokenizer(strValue, ",");
                        while (allValues.hasMoreTokens()) {
                            long enumLitID = Long.parseLong(allValues.nextToken());
                            Oid enumLitIID = getObjectContext().getRepository().makeReposID(enumLitID);
                            IREnumliteral enumlite = getObjectContext().getRepository().getBMRepository().getEnumliteral(enumLitIID, false);
                            enumLit += enumlite.getName();
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

    public void renderView(IAttributeTable table)
            throws AppException {
        Attribute attr = getDefnObject();
        IRDataType dt = attr.getDataType();
        PrimitiveDataType prim = dt.getTypeKind();
        String dispValue = this.toString();
        if (!StringUtil.isPrintable(dispValue)) dispValue = "-not specified-";
        table.addAttribute(getName() + ":", dispValue);
    }

    public void renderEdit(IAttributeTable table)
            throws AppException {
        Attribute attr = getDefnObject();
        AttributeKind ak = attr.getAttrKind();
        if (ak.equals(AttributeKind.SYSTEM_GENERATED) || ak.equals(AttributeKind.AGGREGATE)) {
            renderView(table);
            return;
        }
        IRDataType dt = attr.getDataType();
        PrimitiveDataType prim = dt.getTypeKind();

        if (ak.equals(AttributeKind.SYSTEM_GENERATED))
            return;

        String label = this.getDefnObject().getName();
        if (isRequired()) label = "*" + label;
        ITableData data = null;
        if (label.length() < 30 && !prim.equals(PrimitiveDataType.LONG_CHAR))
            data = table.addSingleLabel(label + ":");
        else
            data = table.addDoubleLabel(label + ":");
        renderEdit(data, label);
    }

    public void renderEdit(ITableData data)
            throws AppException {
        renderEdit(data, this.getDefnObject().getName());
    }

    public void renderEdit(ITableData data, String name)
            throws AppException {
        Attribute attr = getDefnObject();
        AttributeKind ak = attr.getAttrKind();
        IRDataType dt = attr.getDataType();
        Oid dtIID = dt.getIID();
        PrimitiveDataType prim = dt.getTypeKind();
        int maxlen = attr.getMaxLength();
        if (maxlen == -1) maxlen = 250;
        String unitlabel = attr.getUnitLabel();
        UnitPosition unitpos = attr.getUnitPosition();
        String showName = attr.getName();
        String fieldName = "prop" + attr.getIID();
        fieldName = fieldName.replace('-', '_');
        Object defVal = attr.getDefaultValue();

        if (attr.getIID().equals(IDCONST.FILENAME.getIIDValue())) {
        } else
            // ONLY USED FOR CREATE, NOT EDIT, SO NO NEED TO REMEMBER WHATS SELECTED

            if (prim.equals(PrimitiveDataType.CHAR) || prim.equals(PrimitiveDataType.INTEGER) || prim.equals(PrimitiveDataType.DECIMAL)) {
                int size = 10;
                if (prim.equals(PrimitiveDataType.CHAR)) size = 40;
                String value = "";
                IInput input = null;
                if (_parent.getPersState().equals(PersState.NEW)) {
                    if (defVal != null) value = defVal.toString();
                } else {
                    if (getValue() != null) value = getValue() + "";
                }

                if (unitpos.equals(UnitPosition.RIGHT)) {
                    input = data.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(size).setName(fieldName).setAlias(showName).setValue(value);
                    data.addAnchor().setStringValue(unitlabel);
                } else {
                    data.addAnchor().setStringValue(unitlabel);
                    input = data.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(size).setName(fieldName).setAlias(showName).setValue(value);
                }
                if (prim.equals(PrimitiveDataType.INTEGER) || prim.equals(PrimitiveDataType.DECIMAL))
                    input.excludeSpellCheck();
                input.setValidation(ValidationKind.NOTAG1, showName);
                if (prim.equals(PrimitiveDataType.INTEGER)) input.setValidation(ValidationKind.NUMERIC, showName);
                if (prim.equals(PrimitiveDataType.DECIMAL)) input.setValidation(ValidationKind.FLOAT, showName);
                if (isRequired()) input.setValidation(ValidationKind.NOBLANK, showName);
            } else if (prim.equals(PrimitiveDataType.LONG_CHAR)) {
                String value = "";
                ITextArea input = null;
                if (_parent.getPersState().equals(PersState.NEW)) {
                    if (defVal != null) value = defVal.toString();
                } else {
                    if (getValue() != null) value = getValue() + "";
                }
                input = data.addTextArea().setName(fieldName).setAlias(showName).setStringValue(value);
                if (isRequired()) input.setValidation(ValidationKind.NOBLANK, showName);
            } else if (prim.equals(PrimitiveDataType.TIME)) {
                String value = "";
                IInput input = null;
                if (_parent.getPersState().equals(PersState.NEW)) {
                    if (defVal != null) value = com.oculussoftware.ui.DateFormatter.format(defVal.toString());
                } else {
                    if (getValue() != null) value = com.oculussoftware.ui.DateFormatter.format(getValue() + "");
                }
                input = data.addInput().setType(InputKind.TEXT).setSize(10).setName(fieldName).setAlias(showName).setValue(value).excludeSpellCheck();
                data.addCalendar("parent.Body.document.forms[0]." + fieldName);
                input.setValidation(ValidationKind.DATE, showName);
                if (isRequired()) input.setValidation(ValidationKind.NOBLANK, showName);
            } else if (prim.equals(PrimitiveDataType.BOOLEAN)) {
                Boolean value = null;
                if (_parent.getPersState().equals(PersState.NEW)) {
                    if (defVal != null) value = (Boolean) defVal;
                } else {
                    if (getValue() != null) value = (Boolean) getValue();
                }

                IInput input = data.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setAlias(showName).setValue("true");
                if (value != null && value.booleanValue())
                    input.setChecked();
            } else if (prim.equals(PrimitiveDataType.ENUM)) {
                IREnumeration renum =getObjectContext().getRepository().getBMRepository().getEnum(dtIID, false);
                ISelect sel = data.addSelect().setSize(1).setName(fieldName);
                Object realValue = null;
                if (_parent.getPersState().equals(PersState.NEW)) {
                    if (defVal != null) realValue = defVal;
                } else
                    realValue = getValue();
                String strValue = "";
                if (realValue != null)
                    strValue = realValue.toString();
                else {
                    if (!isRequired())
                        sel.addOption().setStringValue(NULL_VALUE).setValue(IDCONST.DUMMYLITERAL.getIIDValue().toString()).setSelected();
                    else
                        sel.addOption().setStringValue(NULL_VALUE).setValue("-1").setSelected();
                }

                IRModelElementList list = renum.getEnumLiterals();
                while (list.hasNext()) {
                    IREnumliteral enumLit = (IREnumliteral) list.next();
                    String value = enumLit.getIID() + "";
                    String enumName = enumLit.getName();
                    IOption option = sel.addOption().setStringValue(enumName).setValue(value);
                    if (value.equals(strValue))
                        option.setSelected();
                }
                sel.setValidation(ValidationKind.NOSELECT, showName);
            } else if (prim.equals(PrimitiveDataType.RADIO)) {
                String strValue = getValue() + "";
                IREnumeration renum =getObjectContext().getRepository().getBMRepository().getEnum(dtIID, false);
                IRModelElementList list = renum.getEnumLiterals();
                IInput inp = null;
                while (list.hasNext()) {
                    IREnumliteral enumLit = (IREnumliteral) list.next();
                    String value = enumLit.getIID() + "";
                    String enumName = enumLit.getName();
                    inp = data.addInput().setType(InputKind.RADIO).setName(fieldName).setAlias(showName).setValue(value);
                    if (value.equals(strValue))
                        inp.setChecked();
                    data.addAnchor(enumName);
                    data.addBR();
                }
            } else {
                Set selectedValues = new TreeSet();
                if (prim.equals(PrimitiveDataType.MULTIENUM) || prim.equals(PrimitiveDataType.MULTICHECK)) {
                    String strValue = getValue() + "";
                    StringTokenizer values = new StringTokenizer(strValue, ",");
                    while (values.hasMoreTokens())
                        selectedValues.add(values.nextToken());
                }

                if (prim.equals(PrimitiveDataType.MULTIENUM)) {
                    IREnumeration renum =getObjectContext().getRepository().getBMRepository().getEnum(dtIID, false);
                    ISelect sel = data.addSelect().setName(fieldName).setSize(4).setMultiple();
                    IRModelElementList list = renum.getEnumLiterals();
                    while (list.hasNext()) {
                        IREnumliteral enumLit = (IREnumliteral) list.next();
                        String value = enumLit.getIID() + "";
                        String enumName = enumLit.getName();
                        IOption option = sel.addOption().setStringValue(enumName).setValue(value);
                        if (selectedValues.contains(value))
                            option.setSelected();
                    }
                    if (isRequired()) sel.setValidation(ValidationKind.NOSELECT, showName);
                } else if (prim.equals(PrimitiveDataType.MULTICHECK)) {
                    IREnumeration renum =getObjectContext().getRepository().getBMRepository().getEnum(dtIID, false);
                    IRModelElementList list = renum.getEnumLiterals();
                    while (list.hasNext()) {
                        IREnumliteral enumLit = (IREnumliteral) list.next();
                        String value = enumLit.getIID() + "";
                        String enumName = enumLit.getName();
                        IInput inp = data.addInput().setType(InputKind.CHECKBOX).setName(value).setAlias(showName).setValue(value);
                        if (selectedValues.contains(value))
                            inp.setChecked();
                        data.addAnchor(enumName);
                        data.addBR();
                    }
                }
            }
    }

}