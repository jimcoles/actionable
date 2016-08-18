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
 * Top level node in the meta data model for a DataSystem.
 * <br>
 * Using analogies: <br>
 * <pre>
 * DataModel         RDBMS                   XML
 * ------------------------------------------------------------
 * Package           Schema/Namespace        Schema Namespace
 * Class             Table defn              Entity (defn)
 * Attribute         Column defn             Attribute (defn)
 * Association       Foreign Key defn
 * (I)ObjectRecord   Row                     Element (-> Node)
 * AttributeValue    Column value            Attribute value or Element TextNode or CDataNode Value
 * ObjectReference   FKey Column value(s)    XLink???, XPointer
 *
 * DataModel         java.lang               java.lang.reflect
 * ------------------------------------------------------------
 * Package           java.lang.Package
 * Class             java.lang.Class
 * Attribute                                 java.lang.reflect.Field
 * Association
 * (I)ObjectRecord   java.lang.Object
 * AttributeValue
 * ObjectReference
 *
 * DataModel         java.bean
 * ------------------------------------------------------------
 * Package
 * Class             java.bean.BeanInfo
 * Attribute         java.bean.PropertyDescriptor
 * Association
 * (I)ObjectRecord   java.lang.Object (conforming to JavaBean stds)
 * AttributeValue    Java primitive, String, stream,
 * ObjectReference
 * dms.Persister     java.bean.XMLDecoder/XMLEncoder
 *
 * DataModel         Sybase Data Architect
 * (conceptual data model)  (physical data model)
 * ------------------------------------------------------------
 * Model             Model/Submodel
 * Package
 * Class             Entity                   Table
 * Attribute         Attribute                Column
 * Association       Relationship             FK Constraint
 * Generalization    Inheritence              Parent/Child Tables, Single Table, Child Only
 * (I)ObjectRecord
 * AttributeValue
 * ObjectReference
 * dms.Persister
 * </pre>
 * <br>
 * ISSUE: Do we need a physical data model since there is in general more
 * than one mapping from DataModel to physical for virtually all data
 * systems.
 * <br><br>
 * Possibly just use extended attributes of the DataModel framework to
 * communicate the physical mappings, e.g.,
 *
 * @author Jim Coles
 * @version 1.0
 */
public class DataModel extends ModelElement {
    //----------------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------------
//  private DataSystem    dataSystem;

    //----------------------------------------------------------------------------
    // Constructor(s)
    //----------------------------------------------------------------------------

    /**
     * Basic constructor
     */
    public DataModel() {
    }

    //----------------------------------------------------------------------------
    // Instance methods
    //----------------------------------------------------------------------------

    //---- <Accessors and Mutators>

//  public DataSystem getDataSystem() { return dataSystem; }

    //---- </Accessors and Mutators>

    //---- <Builder methods>

    public void addClass() {
    }

    public void addAssoc() {
    }

    //---- </Builder methods>

    //----------------------------------------------------------------------------
    //----------------------------------------------------------------------------

}