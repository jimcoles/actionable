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
 * UML: core.Attribute
 * UML semantics deviations:
 * UML has Attribute -> StructuralFeature -> Feature, which has visibility
 * and ownerScope.  Our scope is always 'instance'-level.  Our visibility
 * is always 'public'.  Therefore, we have Attribute -> ModelElement.
 * <p>
 * An Attribute is inherently scoped to (owned by) one and only one Class.  Could call this a
 * ClassAttribute, but the 'Class' part is implied.
 * <p>
 * Note, this means that an Attribute is NOT a Classifier, but rather a usage
 * of a Classifier by a Class.
 *
 * @author J. Coles
 * @version 1.0
 */
public class Attribute extends ModelElement {
    //----------------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------------

//  private String initValue;  // Expression (per UML doc)

    private Classifier          type;
    private Class               owner;
    private PrimitiveEnum       primitiveDataType = null;
    private AttributeKind       attributeKind = null;
    private boolean             bIsExt = false;
    private boolean             bIsRole = false;

    //----------------------------------------------------------------------------
    // Constructors
    //----------------------------------------------------------------------------

    public Attribute() {
    }

    public Attribute(Class owner, Oid attrid, String colname, String displayname, PrimitiveEnum type,
                     AttributeKind kind)
    {
        super(null, null, null, attrid, displayname, colname, null, null);
        this.owner = owner;
        this.primitiveDataType = type;
        this.attributeKind = kind;
    }

    //----------------------------------------------------------------------------
    // Instance methods
    //----------------------------------------------------------------------------

    public Class getOwner() {
        return owner;
    }

    public void setOwner(Class owner) {
        this.owner = owner;
    }

    public Classifier getType() {
        return type;
    }

    public void setType(Classifier type) {
        this.type = type;
    }

    public PrimitiveEnum getPrimitiveDataType() {
        return primitiveDataType;
    }

    public AttributeKind getAttributeKind() {
        return attributeKind;
    }

    public boolean isbIsExt() {
        return bIsExt;
    }

    public boolean isbIsRole() {
        return bIsRole;
    }

    /**
     * @return Always returns true for now.
     * @deprecated
     */
    public boolean getIsExtended() {
        return true;
    }

}