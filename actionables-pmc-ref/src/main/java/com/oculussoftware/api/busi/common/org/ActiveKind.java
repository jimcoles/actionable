package com.oculussoftware.api.busi.common.org;

import com.oculussoftware.util.*;

/** This is an enumeration of values that describe the active-ness of an IUser.
* The ACTIVE value represents an active user who can log-in to the system and
* function normally.  The INACTIVE value represents a user that is not deleted
* from the system, but is still not able to log in.  This might be useful for
* people that are on vacation.
*
* @author Zain Nemazie
*/

/*
* $Workfile: ActiveKind.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public class ActiveKind extends IntEnum
{
  /** the value (1) representing an active user */
	public static final ActiveKind ACTIVE   = new ActiveKind(1);
  /** the value (2) representing an inactive user */
	public static final ActiveKind INACTIVE   = new ActiveKind(2);
	
	/**
  * Private constructor
  */
  private ActiveKind(int i)
  {
    super(i);
  }

	/** Returns the ActiveKind instance that corresponds to the given parameter.  If
  * the paramter does not correspond to an ActiveKind, then this method returns null.
  *
  * @param i int value of the desired ActiveKind
  * @return the ActiveKind corresponding to the given parameter
  */
  public static ActiveKind getInstance(int i)
  {
    ActiveKind prim = null;
    switch (i)
    {
      case 1:prim=ActiveKind.ACTIVE;break;
      case 2:prim=ActiveKind.INACTIVE;break;
    }
    return prim;
  }

}
