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

import org.jkcsoft.bogey.metamodel.DataModel;
import org.jkcsoft.bogey.metamodel.Oid;

import java.util.Observer;

public interface Repository {
    //---------------------------------------------------------------
    // Repository I/O methods
    //---------------------------------------------------------------

    void initialize() throws AppException;

    void setMaxOID() throws AppException;

    String getName() throws AppException;

    Oid genReposID() throws AppException;

    int getMajorVersion() throws AppException;

    int getMinorVersion() throws AppException;

    String getRelease() throws AppException;

    int getBuild() throws AppException;

    String getFullReleaseString() throws AppException;

    /**
     */
    Oid makeReposID(long id) throws AppException;

    IRObject getRootObject() throws AppException;

//    IRConnection getDataConnection(IObjectContext context)
//            throws AppException;

    //---------------------------------------------------------------
    // Repository analysis and validation methods
    //---------------------------------------------------------------

    /**
     * Validates the current state of the data against the current meta repos
     */
    void validate(Observer o);

//    IRBusinessModel getBMRepository() throws AppException;

//    DBVendor getVendor();

    String LOJO();

    DataModel getBMRepository();
}