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

import java.util.Observer;

public interface Repository {
    //---------------------------------------------------------------
    // Repository I/O methods
    //---------------------------------------------------------------

    public void initialize() throws AppException;

    public void setMaxOID()
            throws AppException;

    public String getName()
            throws AppException;

    public Oid genReposID()
            throws AppException;

    public int getMajorVersion()
            throws AppException;

    public int getMinorVersion()
            throws AppException;

    public String getRelease()
            throws AppException;

    public int getBuild()
            throws AppException;

    public String getFullReleaseString()
            throws AppException;

    /**
     */
    public Oid makeReposID(long id)
            throws AppException;

    public IRObject getRootObject()
            throws AppException;

//    public IRConnection getDataConnection(IObjectContext context)
//            throws AppException;

    //---------------------------------------------------------------
    // Repository analysis and validation methods
    //---------------------------------------------------------------

    /**
     * Validates the current state of the data against the current meta repos
     */
    public void validate(Observer o);

//    public IRBusinessModel getBMRepository() throws AppException;

//    public DBVendor getVendor();

    public String LOJO();
}