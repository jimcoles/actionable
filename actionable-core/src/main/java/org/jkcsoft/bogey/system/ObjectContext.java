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

public class ObjectContext implements java.io.Serializable {
    /**
     * Handle to the IEnvironment of this object
     */
    private IEnvironment _environment;
    /**
     * Handle to the SOMConnection for this object
     */
    private SOMConnection _connection;
    /**
     * Guid of this object.  Since this object does not persist, consider removing this value.
     */
    private Guid _guid;
    /**
     * The Oid of the repository in which this object persists.  Consider making this a direct
     * reference to the Repository instead of the Oid
     */
    private Oid _reposIID;

//----------------------------- Public Constructors ----------------------------

    /**
     * Default constructor that generates a default object context.  When this system handles
     * multiple repositories, this constructor will need an Repository object, or Oid to refer to.
     *
     * @throws AppException This exception is thrown if there is an error connecting to the
     *                      SOMImpl.
     */
    public ObjectContext() throws AppException {
        // can't gen guid from SOMImpl because it creates a loop.
        _guid = new Guid();
        if (_guid == null) throw new AppException("Null Guid returned from SOMImpl in ObjectContext constructor.");
        // FIXME : This obviously won't work if we've got multiple repositories.
        _reposIID = new Oid(1);
    }

//----------------------------- ObjectContext Methods ------------------------

    /**
     * Returns the IEnvironment object for this object.
     *
     * @return The IEnvironment object for this object.
     */
    public IEnvironment getEnvironment() {
        return _environment;
    }

    /**
     * Returns the SOMConnection object for this object.
     *
     * @return the SOMConnection object for this object.
     */
    public SOMConnection getConnection() {
        return _connection;
    }

    /**
     * Returns the singleton SOM object for this VM.  Note: This method should be the only
     * place where we take advantage of the fact that the SOMImpl is a singleton.
     *
     * @return A handle to the SOM object.
     * @throws AppException This exception is thrown only if there is an error in generating
     *                      the initial instance of the SOMImpl.  The exception is originally thrown from SOMImpl().
     */
    public SOM getCRM()
            throws AppException {
        return null; // TODO SOMImpl.getInstance();
    }

    /**
     * Returns the Repository in which the object persists.  An exception is thrown if a
     * connection cannot be made to the repository.
     *
     * @return The Repository object in which the object persists.
     * @throws AppException This exception is thrown if there is an error in connecting to
     *                      the repository.
     */
    public Repository getRepository()
            throws AppException {
        // FIXME: I think the Repository object should be a member variable of the ObjectContext
        // instead of just the Oid of the Repository.  That way we won't have to go to the
        // SOMImpl everytime we want a handle to the repository.
        return (Repository) getCRM().getCompObject(this, "Repository");
    }

    /**
     * Sets the SOMConnection for this object.  The SOMConnection can not be null.
     *
     * @return A reference to this object.
     * @throws AppException This exception is thrown if the SOMConnection parameter is null.
     */
    public ObjectContext setConnection(SOMConnection conn)
            throws AppException {
//    if (conn == null) throw new AppException("Cannot set the SOMImpl connection of this object to null.");
//        conn.setObjectContext(this);
        _connection = conn;
        return this;
    }

//----------------------------- ILockHolder Methods ----------------------------

    /**
     * Returns the IID of this object's user.  Semantically this means the the user for this
     * ObjectContext is holding the lock.
     *
     * @return The IID of the user holding the lock on the object.
     * @throws AppException This exception is thrown if there is an error connection to the
     *                      SOMImpl.
     */
    public Oid getLockHolderIID()
            throws AppException {
        SOMConnection conn = getConnection();
        if (conn == null)
            throw new AppException("Error connecting to the SOMImpl while returning the lock holder for this " +
                                           "object.");
        return conn.getUserOid();
    }

    /**
     * Invalidates the locks that this lockholder is holding.
     */
    public void invalidate() throws AppException {
        SOM crm = getCRM();
// TODO        ITransaction t = TransactionMgr.getInstance().getTransaction(this);
//        if (t != null)
//            crm.rollbackTransaction(this);
        crm.removeAccessMgr(this);
        crm.removeCRMConnection(this.getConnection().getOid());
    }

//----------------------------- IObject Methods ----------------------------

    /**
     * Returns itself indicating that an ObjectContext is it's own ObjectContext.
     *
     * @return A reference to this object.
     */
    public ObjectContext getObjectContext() {
        return this;
    }

    /**
     * This method will always throw an exception since it does not make sense to set the
     * ObjectContext of the ObjectContext.
     *
     * @throws AppException This exception is throw whenever this method is called.
     */
    public ObjectContext setObjectContext(ObjectContext context)
            throws AppException {
        throw new AppException("Cannot set the ObjectContext of the ObjectContext.");
    }

    /**
     * Returns this object's Guid.
     *
     * @return This object's Guid.
     */
    public Guid getGUID() {
        return _guid;
    }
}