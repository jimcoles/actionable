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

import java.util.Arrays;
import java.util.List;
import java.util.Vector;

/**
 * The Class meta element, i.e., a Table, Entity, etc.
 *
 * @author J. Coles
 * @version 1.0
 */
public class Class extends Classifier {

    private boolean isLeaf = false;
    private boolean isRoot = false;

    private List attributeList = null;
    private String syn = null;
    private Attribute primKeyAttr = null;
    private List attrs = new Vector();
    private List keywordSelectAttrs = new Vector();
    private boolean bCommon = false;  // true implies that item of this class can be child of any other item
    private boolean bRecursive = false; // true implies class has recursive association to itself
    private String indexTable = null;  // table name of index table for recursive classes.
    //    private IQFilterExpr defFilter = null;
    private boolean isView = false;  // true implies this xmeta class corresponds to (is a view of) multiple bmr
    // classes.
    private List<Oid> allClassIDs = new Vector();
    private boolean bIsRoleBased = false;
    private boolean bIsRoleParent = false;

    //-------------------------------------------------------------------
    // Constructor(s)
    //-------------------------------------------------------------------
    public Class(Oid classid, String table, String codeName, String displayname) throws Exception {
        super(null, null, null, classid, displayname, codeName, null, ConfigKind.DELETEABLE);
        //
        this.syn = codeName;  // TODO possibly munge
        allClassIDs.add(classid);
        DataModel.getInstance().addClass(this);
    }


    public List getTableAttrs() {
        return attrs;
    }

    public List getKeywordSelectAttrs() {
        return keywordSelectAttrs;
    }

    public String getSyn() {
        return syn;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        isLeaf = isLeaf;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public List getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(List attributeList) {
        attributeList = attributeList;
    }

    /**
     * Setter for internal list.
     */
    public void addAttribute(Attribute attrib) {
        attributeList.add(attrib);
    }

    //---- </Accessors and Mutators> ----------------------------------------------
    public void setPrimaryKeyAttr(Attribute attr) {
        primKeyAttr = attr;
    }

    public Attribute getPrimaryKeyAttr() {
        return primKeyAttr;
    }

//    public void setClassDefnFilter(IQFilterExpr filter) {
//        defFilter = filter;
//    }

//    public IQFilterExpr getClassDefnFilter() {
//        return defFilter;
//    }

    public Class setIsCommon(boolean bCommon) {
        bCommon = bCommon;
        return this;
    }

    public boolean getIsCommon() {
        return bCommon;
    }

    public Class setIsRecursive(boolean bRecursive) {
        bRecursive = bRecursive;
        return this;
    }

    public boolean getIsRecursive() {
        return bRecursive;
    }

    public Class setIndexTable(String tablename) {
        indexTable = tablename;
        return this;
    }

    public String getIndexTable() {
        return indexTable;
    }

    public Attribute addAttr(Oid attrid, String colname, String displayname, PrimitiveDataType type, AttributeKind
            kind, boolean isConfigurable) {
        Attribute attribute = new Attribute(this, attrid, colname, displayname, type.getType(), kind);
        addAttr(attribute, isConfigurable);
        return attribute;
    }

    public Attribute addAttr(Attribute attr, boolean isConfigurable) {
        if (!attrs.contains(attr))
            attrs.add(attr);
//        XMR.addAttributeAsc(this, attr, isConfigurable));
        return attr;
    }

    public Attribute addKeywordSelectAttr(Oid attrid, String colname, String displayname, PrimitiveDataType type,
                                          AttributeKind kind) {
        Attribute cam = new Attribute(this, attrid, colname, displayname, type.getType(), kind);
        keywordSelectAttrs.add(cam);
        return cam;
    }

    public String addKeywordSelectAttr(String literal) {
        keywordSelectAttrs.add(literal);
        return literal;
    }

    public Class setIsView(boolean isView) {
        isView = isView;
        return this;
    }

    public boolean getIsView() {
        return isView;
    }

    public Class setExtraClassIDs(Oid[] classids) {
        setIsView(true);
        List xtra = Arrays.asList(classids);
        allClassIDs.addAll(xtra);
        return this;
    }

    public List<Oid> getAllClassIDs() {
        return allClassIDs;
    }

    public Class setIsRoleBased(boolean is) {
        bIsRoleBased = is;
        return this;
    }

    public boolean getIsRoleBased() {
        return bIsRoleBased;
    }

    public Class setIsRoleParent(boolean is) {
        bIsRoleParent = is;
        return this;
    }

    public boolean getIsRoleParent() {
        return bIsRoleParent;
    }


    public StateMachine getStateMachine() {
        return null;  // TODO
    }

    public boolean hasStateMachine() {
        return false;  // TODO
    }

    public Class getBaseClass() {
        return null;
    }
}