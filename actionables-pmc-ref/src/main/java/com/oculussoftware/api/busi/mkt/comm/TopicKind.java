package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types of discussion topics.
*
* @author Egan Royal
*/

/*
* $Workfile: TopicKind.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class TopicKind extends IntEnum
{
  /** This value (0) represents a transaction comment. */
  public final static TopicKind TRANSACTIONCOMMENT = new TopicKind(0);
  /** This value (0) represents a normal discussion topic. */
  public final static TopicKind NORMAL             = new TopicKind(1);
  /** This value (0) represents a pro in the Pros/Cons section. */
  public final static TopicKind PRO                = new TopicKind(2);
  /** This value (0) represents a con in the Pros/Cons section. */
  public final static TopicKind CON                = new TopicKind(3);
  
	/** Returns the TopicKind instance that corresponds to the given parameter.
  *
  * @param i int value of the desired TopicKind
  * @return the TopicKind corresponding to the given parameter
  * @exception com.oculussoftware.api.sysi.OculusException if the parameter does not
  *            corespond to a valid TopicKind
  */
  public static TopicKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return TRANSACTIONCOMMENT;
    else if(d == 1)
      return NORMAL;
    else if(d == 2)
      return PRO;
    else if(d == 3)
      return CON;  
    else
      throw new OculusException("Invalid TopicKind.");
  }
  
  /** Private constructor */
  private TopicKind(int s) { super(s); }
}