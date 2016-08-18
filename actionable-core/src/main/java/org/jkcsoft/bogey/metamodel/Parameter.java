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
 * UML: core.Parameter
 * UML Deviation: Our Parameters can be either ordered or named, but not both,
 * with respect to an Operation call.
 *
 * @author J. Coles
 * @version 1.0
 */
public class Parameter {
    //----------------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------------

    private Operation parentOperation;

    //----------------------------------------------------------------------------
    // Constructor(s) (private, package, protected, public)
    //----------------------------------------------------------------------------

    /**
     * Creates new Connector
     */
    public Parameter() {
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
