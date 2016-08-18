package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.IObject;
import com.oculussoftware.api.sysi.IPoolable;
import com.oculussoftware.api.sysi.OculusException;

/** An IRObject represents a user data object, or an instance
    of an IRClass.  It is the entry point for
    obtaining and setting state information for an object of a 
    registered IRClass. It is inherently persistable.
    
    Using the RDBMS analogy:
    
    repi           RDBMS
    --------------------
    IRClass        Table defn
    IRAttribute    Column defn (non-FK or PK, definition)
    IRAssoc        Foreign Key defn
    IRType         A set of common Column definitions
    
    IRObject       Row
    IRProperty     Data Column (value)
    IRLink         Foreign Key Column (value)
*/
public interface IRObject extends IRDataElement, IRPersistable
{
//  public IRElement    setParentElement(IRElement parent)
//    throws ORIOException;


/*  ---- moved to IRPersistable

  public void load()
    throws ORIOException;
    
  public void save( )
    throws ORIOException;
*/  

  public IRObject grantPermissions(IIID useriid, java.util.Set gs) throws OculusException;
	 
  /** Get the 'owning' parent object which may be the repository root object */
  public IRObject getParentObject()
    throws OculusException;
  
  public IRObject getParentObject(boolean editable)
    throws OculusException;
  
  /** Get any (weak) aggregator objects */
  public IRCollection  getParentContainers()
    throws OculusException;
  
  /** Get the  */
//  public IRObject getLockParent();


  /** Returns all IProperties as a map keyed by IIID's*/
  public IRPropertyMap getProperties( )
    throws OculusException;
   
  public IRObject setProperty(IRAttribute metaprop, Object value)
   throws OculusException;
   

  public boolean childExists(IIID iid)
    throws ORIOException;
    
  /** Gets ALL strongly aggregated child objects (IRObject, IRSet, IRList) as a map keyed by IRElements's
      as broken down by IRAssoc.
   */
//  public Object   getChildCollection(long assocId)
//    throws ORIOException;
    
  /**
  * Gets ALL weakly aggregated child objects (IRObject, IRSet, IRList) as a map keyed by IRElements's.
  */
//  public IRAssocColl  getSharedObjects(long assocId)
//    throws ORIOException;

  /** Get non-aggregate links 
  public IRLinkCollMap  getAllLinksFrom()
    throws OculusException;
  public IRCollection   getAllLinksFrom(IRAssoc assoc)
    throws OculusException;
  
  public IRLinkCollMap   getAllLinksTo()
    throws OculusException;
  public IRCollection    getLinksTo(IRAssoc assoc)
    throws OculusException;
  
  public IRObject setChildObject(IRAssoc metaassoc, IRObject obj)
    throws ORIOException;
*/

  public IRCollection getChildCollection()
    throws OculusException;

  public IRCollection getChildCollection(boolean editable)
    throws OculusException;


/*    
  public IRObject setChildList(IRAssoc metaassoc, IRList list)
    throws ORIOException;
    
  public IRObject setChildSet(IRAssoc metaassoc, IRSet set)
    throws ORIOException;
*/
    
  /** Like openning a file in a file system. */
//  public IRObject getChildObject(IIID pID)
//    throws ResourceNotFoundException, ResourceBusyException;

//  public IRObject  getChildObjectByName(String name)
//    throws ResourceNotFoundException, ResourceBusyException;

  /** Like openning a file in a file system for update. */
//  public IRObject  getChildObjectForUpdate(IIID pID)
//    throws ResourceNotFoundException, ResourceBusyException,
//           ResourceLockedException;

  /** Gets all items recursively */
//  public IRMap      getAllChildren(IFilter filt, ISort sort, IGroupBy grp )
//    throws AccessException, ORIOException;

//  public void setFilter(IFilter);
//  public void setGroupBy(IGroupBy);
//  public void setGroupBy(IGroupBy);
  
  //------------------------------------------------------------
  // 
  //------------------------------------------------------------

  /** Gets lock for current repository object */
//  public void setUpdateLock(ITransaction trans)
//    throws ResourceLockedException, AccessException;
    
  /** Unlocks the current object, e.g., if user cancels an update. */
//  public void returnUpdateLock();

  /** Creates or re-sets a strongly contained object of the specified class wrt the specified
      association.  The class must be a valid destination class for the association.
      The association must be defined for at least one of the IRType's implemented
      by the current (this) object's class.  
      To use this method, the association must be have a max multiplicity of one.
      To set an object of a collection, first get the collection (IRSet, IRList)
      and use the setter methods of the collection.
  */
  
//  public IRObject setObject(IRAssoc metaassoc, IRObject obj)
//    throws ORIOException;
    
  /** Create or re-set a property entry of the specified property meta type.  The property
      must be a member of at least one IRType for the current (this) object's class.
      */
  /** Like creating a file in a file system. */
//  public void deleteObject(IRObject obj)
//    throws ORIOException;
  
}