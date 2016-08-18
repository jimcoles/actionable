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

import org.jkcsoft.bogey.metamodel.Guid;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.BusinessObject;
import org.jkcsoft.bogey.model.ManagedObject;
import org.jkcsoft.bogey.system.security.AccessMgr;

/**
 * The SOM or Service Objbect Manager is the entry point API for accessing application services.  It serves as the
 * factory for all service objects in this system.  It is the portal through which all clients should go
 * to retrieve any object.  Provides authentication and access control and handles transactions.
 *
 * @author Jim Coles
 *         <p>
 *         TODO: Rename to AppSysManager
 */
public interface SOM {

    /**
     * Validates the given username and password.  If the name and password are valid,
     * a SOMImpl connection is created and returned for that user.
     *
     * @param username the login id for the user
     * @param password the password for the user
     * @return a valid SOMConnection
     * @throws AppException
     */
    SOMConnection connect(String username, String password)
            throws AppException;

    /**
     * Returns a valid anonymous connection to the SOMImpl.
     *
     * @return a valid anonymous connection to the SOMImpl
     * @throws AppException
     */
    SOMConnection anonymousConnect()
            throws AppException;

    /**
     * Returns an access manager for the given object context.
     *
     * @param ctxt the object context to return the access manager for
     * @return the access manager for the context
     * @throws AppException
     */
    AccessMgr getAccessMgr(ObjectContext ctxt)
            throws AppException;

    /**
     * Removes the access manager for the given context
     *
     * @param context the object context to remove the access manager for
     * @throws AppException
     */
    void removeAccessMgr(ObjectContext context) throws AppException;

    /**
     * Removes the access manager for the user Oid
     *
     * @param iid the user IID to remove the access manager for
     * @throws AppException
     */
    void removeAccessMgr(Oid iid)
            throws AppException;

    /**
     * Returns an object in the class with the given name.
     *
     * @param classname the pseudo-name of the class being requested
     * @return the object requested
     */
    ManagedObject getCompObject(ObjectContext callContext, String classname) throws AppException;

    /**
     * Returns an object in the class with the given class GUID.  Note this method is not
     * being used.
     *
     * @param classGuid the GUID of the object being requested
     * @return the object requested
     * @throws AppException
     */
    ManagedObject getCompObject(ObjectContext callContext, Guid classGuid) throws AppException;

    /**
     * Returns an object in the class with the given name and object IID.
     *
     * @param name   the pseudo-name of the class being requested
     * @param objIID the Oid of the object being requested
     * @return the object requested
     * @throws AppException
     */
    ManagedObject getCompObject(ObjectContext callContext, String name, Oid objIID) throws AppException;

    /**
     * Returns an object in the class with the given class GUID and object IID.
     *
     * @param classGuid the GUID of the class being requested
     * @param objID     the Oid of the object being requested
     * @return the object requested
     * @throws AppException
     */
    ManagedObject getCompObject(ObjectContext callContext, Guid classGuid, Oid objID) throws AppException;

    /**
     * Returns an object in the class with the given name and object IID.
     *
     * @param name     the pseudo-name of the class being requested
     * @param objID    the Oid of the object being requested
     * @param editable true if the object should be locked, false otherwise
     * @return the object requested
     * @throws AppException
     */
    ManagedObject getCompObject(ObjectContext callContext, String name, Oid objID, boolean editable)
            throws AppException;

    /**
     * Returns an object in the class with the given class GUID and object IID.
     *
     * @param callContext the context of the requesting user
     * @param guid        the GUID of the class being requested
     * @param objID       the Oid of the object being requested
     * @param editable    true if the object should be locked, false otherwise
     * @return the object requested
     * @throws AppException
     */
    ManagedObject getCompObject(ObjectContext callContext, Guid guid, Oid objID, boolean editable)
            throws AppException;

//    /**
//     * Returns a reference to the system's license manager.
//     *
//     * @throws AppException
//     */
//    license.ILicenseMgr getLicenseMgr()
//            throws AppException;

    /**
     * Removes the connection for the given user Oid from the list of valid SOMImpl connections.
     *
     * @param conn_userIID the user Oid whose connection is being removed
     * @throws AppException
     */
    void removeCRMConnection(Oid conn_userIID)
            throws AppException;

    /**
     * Commits the transaction for the given context.
     *
     * @param context the context whose transaction is being commited
     * @throws AppException
     */
    boolean commitTransaction(ObjectContext context)
            throws AppException;

    /**
     * Rolls back the transaction for the given context.
     *
     * @param context the context whose transaction is being rolled back
     * @throws AppException
     */
    void rollbackTransaction(ObjectContext context)
            throws AppException;

    /**
     * Returns whether or not the given SOMConnection is a valid connection.
     *
     * @param conn the connection whose validity is being checked
     * @return true if the connection is valid, false otherwise
     * @com.oculussoftware.api.repi.AppException
     */
    boolean isValid(SOMConnection conn)
            throws AppException;

    /**
     * Returns a new globally unique ID.
     *
     * @return the new globally unique ID
     * @throws AppException
     */
    Guid genGUID() throws AppException;

    /**
     * Returns whether or not the user of the given object context is currently logged in.
     *
     * @param context the object context for the user
     * @return true if the user is logged in, false otherwise
     * @throws AppException
     */
    boolean isLoggedIn(ObjectContext context)
            throws AppException;

    /**
     * Returns whether or not the user for the given SOMImpl connection is currently logged in.
     *
     * @param conn the SOMImpl connection for the user
     * @return true if the user is logged in, false otherwise
     * @throws AppException
     */
    boolean isLoggedIn(SOMConnection conn)
            throws AppException;
}