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
 * UML: core.Namespace.
 *
 * @author J. Coles
 * @version 1.0
 */
public abstract class Namespace extends ModelElement {
    //----------------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------------

    //----------------------------------------------------------------------------
    // Constructor(s) (private, package, protected, public)
    //----------------------------------------------------------------------------

    /**
     * Creates new Connector
     */
    public Namespace() {
    }

    public Namespace(Namespace namespace, DataModel dataModel, Guid guid, Oid oid, String displayName, String
            codeName, String description, ConfigKind configkind) {
        super(namespace, dataModel, guid, oid, displayName, codeName, description, configkind);
    }
//----------------------------------------------------------------------------
    // Instance methods
    //----------------------------------------------------------------------------

    //---- <Accessors and Mutators> ----------------------------------------------

    //---- </Accessors and Mutators> ----------------------------------------------

    //----------------------------------------------------------------------------
    // Private methods
    //----------------------------------------------------------------------------

}
