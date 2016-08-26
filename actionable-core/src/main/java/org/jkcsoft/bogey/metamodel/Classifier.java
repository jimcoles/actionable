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
 * UML: core.Classifier.
 *
 * @author J. Coles
 * @version 1.0
 */
public abstract class Classifier extends Namespace {
    //----------------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------------


    //----------------------------------------------------------------------------
    // Constructor(s) (private, package, protected, public)
    //----------------------------------------------------------------------------

    /**
     * Creates new Connector
     */
    public Classifier() {
    }

    public Classifier(Namespace namespace, DataModel dataModel, Guid guid, Oid oid, String displayName, String
            codeName, String description, ConfigKind configkind) {
        super(namespace, dataModel, guid, oid, displayName, codeName, description, configkind);
    }
//----------------------------------------------------------------------------
    // Instance methods
    //----------------------------------------------------------------------------


    //----------------------------------------------------------------------------
    // Private methods
    //----------------------------------------------------------------------------

}
