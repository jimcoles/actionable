package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;

/**
* Filename:    PersState.java
* Date:        
* Description: Typesafe constants indicating an persitable object's
* in-memory state with respect to it's data store.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*	---							Saleem Shafi		2/2/00			Added the NEW, UNMODIFIED, MODIFIED, PARTIAL,
*																							and DELETED enumerations.
*/

public final class PersState extends IntEnum
{
	/** Indicates that the object does not exist in the repository, but is in memory */
  public final static PersState NEW = new PersState(0);
  /** Indicates that the object is the same in memory as it is in the data store. */ 
  public final static PersState UNMODIFIED = new PersState(1);
  /** Indicates that the object has been altered in memory from what is in the data store. */ 
  public final static PersState MODIFIED = new PersState(2);
  /** Indicates that the object has only been partially loaded into memory. */
  public final static PersState PARTIAL = new PersState(3);
  /** Indicates that the object is in the database, but should be deleted. */
  public final static PersState DELETED = new PersState(4);
  /** Indicates that the object has not yet been loaded from the data store */
  public final static PersState UNINITED = new PersState(5);
  
  /** Private constructor */
  private PersState(int s) { super(s); }
}