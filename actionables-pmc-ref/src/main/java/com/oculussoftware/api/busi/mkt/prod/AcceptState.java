package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types acceptance states of a specification
* object.
*
* @author Egan Royal
*/

/*
* $Workfile: AcceptState.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class AcceptState extends IntEnum
{
  /** This value (0) represents a "no comment" or unitialized acceptance state.  This is the default. */
  public final static AcceptState NOT_ENTERED   = new AcceptState(0); 
  /** This value (1) means that the specification object has been rejected. */
  public final static AcceptState REJECTED      = new AcceptState(1);
  /** This value (1) means that the specification object has been accepted. */
  public final static AcceptState ACCEPTED      = new AcceptState(2);
  /** This value (1) means that the specification object has been accepted, but with some conditions. */
  public final static AcceptState ACCEPTED_COND = new AcceptState(3);
  
	/** Returns the AcceptState instance that corresponds to the given parameter.  If
  * the paramter does not correspond to an AcceptState, then this method returns null.
  *
  * @param i int value of the desired AcceptState
  * @return the AcceptState corresponding to the given parameter
  */
  public static AcceptState getInstance(int d) throws OculusException
  {
    if(d == 0)
      return NOT_ENTERED;
    else if(d == 1)
      return REJECTED;
    else if(d == 2)
      return ACCEPTED;
    else if(d == 3)
      return ACCEPTED_COND;
    else
      throw new OculusException("Invalid AcceptState.");
  }
  
  /** Private constructor */
  private AcceptState(int s) { super(s); }
}