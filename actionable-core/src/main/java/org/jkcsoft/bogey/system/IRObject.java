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

import org.jkcsoft.bogey.metamodel.Attribute;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.BMProperty;

import java.util.Collection;
import java.util.Map;

/**
 * An IRObject represents a user data object, or an instance
 * of an IRClass.  It is the entry point for
 * obtaining and setting state information for an object of a
 * registered IRClass. It is inherently persistable.
 * <p>
 * Using the RDBMS analogy:
 * <pre>
 * repi           RDBMS
 * --------------------
 * DynaClass      Table defn
 * IRAttribute    Column defn (non-FK or PK, definition)
 * IRAssoc        Foreign Key defn
 * IRType         A set of common Column definitions
 *
 * IRObject       Row
 * IRProperty     Data Column (value)
 * IRLink         Foreign Key Column (value)
 * </pre>
 */
interface IRObject {

    IRObject grantPermissions(Oid useriid, java.util.Set gs) throws AppException;

    /**
     * Get the 'owning' parent object which may be the repository root object
     */
    IRObject getParentObject()
            throws AppException;

    IRObject getParentObject(boolean editable)
            throws AppException;

    /**
     * Get any (weak) aggregator objects
     */
    Collection<IRObject> getParentContainers() throws AppException;

    /** Get the  */
//  IRObject getLockParent();


    /**
     * Returns all IProperties as a map keyed by Oid's
     */
    Map<String, BMProperty> getProperties()
            throws AppException;

    IRObject setProperty(Attribute metaprop, Object value)
            throws AppException;


    boolean childExists(Oid iid)
            throws AppException;


    /**
     * Get non-aggregate links
     * IRLinkCollMap  getAllLinksFrom()
     * throws AppException;
     * Collection<IRObject>   getAllLinksFrom(IRAssoc assoc)
     * throws AppException;
     * <p>
     * IRLinkCollMap   getAllLinksTo()
     * throws AppException;
     * Collection<IRObject>    getLinksTo(IRAssoc assoc)
     * throws AppException;
     * <p>
     * IRObject setChildObject(IRAssoc metaassoc, IRObject obj)
     * throws AppException;
     */

    Collection<IRObject> getChildCollection()
            throws AppException;

    Collection<IRObject> getChildCollection(boolean editable)
            throws AppException;


    /** Like opening a file in a file system. */
//  IRObject getChildObject(Oid pID)
//    throws ResourceNotFoundException, ResourceBusyException;

//  IRObject  getChildObjectByName(String name)
//    throws ResourceNotFoundException, ResourceBusyException;

    /** Like opening a file in a file system for update. */
//  IRObject  getChildObjectForUpdate(Oid pID)
//    throws ResourceNotFoundException, ResourceBusyException,
//           ResourceLockedException;

    /** Gets all items recursively */
//  IRMap      getAllChildren(IFilter filt, ISort sort, IGroupBy grp )
//    throws AccessException, AppException;

//  void setFilter(IFilter);
//  void setGroupBy(IGroupBy);
//  void setGroupBy(IGroupBy);

    //------------------------------------------------------------
    //
    //------------------------------------------------------------

    /** Gets lock for current repository object */
//  void setUpdateLock(ITransaction trans)
//    throws ResourceLockedException, AccessException;

    /** Unlocks the current object, e.g., if user cancels an update. */
//  void returnUpdateLock();

    /** Creates or re-sets a strongly contained object of the specified class wrt the specified
     association.  The class must be a valid destination class for the association.
     The association must be defined for at least one of the IRType's implemented
     by the current (this) object's class.
     To use this method, the association must be have a max multiplicity of one.
     To set an object of a collection, first get the collection (IRSet, IRList)
     and use the setter methods of the collection.
     */

//  IRObject setObject(IRAssoc metaassoc, IRObject obj)
//    throws AppException;

    /** Create or re-set a property entry of the specified property meta type.  The property
     must be a member of at least one IRType for the current (this) object's class.
     */
    /** Like creating a file in a file system. */
//  void deleteObject(IRObject obj)
//    throws AppException;

}