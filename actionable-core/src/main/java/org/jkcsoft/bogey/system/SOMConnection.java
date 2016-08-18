/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.system;


import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.User;

/**
 * A connection object that an accessor must obtain and pass as an argument
 * for subsequent app-sys operations.
 *
 * @author Jim Coles
 */

interface SOMConnection {

    Oid getOid();

    /**
     * Returns the Oid of the user using the SOMConnection.
     *
     * @return The Oid of the user for this connection
     * @throws AppException
     */
    Oid getUserOid() throws AppException;

    /**
     * Returns the user using the SOMConnection.
     *
     * @return the user for this connection
     * @throws AppException
     */
    User getUserObject() throws AppException;

    /**
     * Returns whether or not this connection is a valid one.
     *
     * @return true if this is a valid connection, false otherwise
     * @throws AppException
     */
    boolean isValid() throws AppException;

    /**
     * Returns a reference to the SOMImpl that this connection is connected to.
     *
     * @return a reference to the SOMImpl being connected to
     */
    SOM getCRM();
}