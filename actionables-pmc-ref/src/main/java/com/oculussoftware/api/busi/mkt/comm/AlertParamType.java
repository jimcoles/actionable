package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types of alert parameters.  
* It is used by the AlertThread object.
*
* @author Egan Royal
*/

/*
* $Workfile: AlertParamType.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class AlertParamType extends IntEnum
{
  /** This value (0) indicates that the alert parameter is the high extreme of a ratio. */
  public final static AlertParamType RATIO_HIGH = new AlertParamType(0);
  /** This value (1) indicates that the alert parameter is the low extreme of a ratio. */
  public final static AlertParamType RATIO_LOW  = new AlertParamType(1);
  /** This value (2) indicates that the alert parameter refers to a threshold in comparison to a baseline. */
  public final static AlertParamType BASELINE   = new AlertParamType(2);
  /** This value (3) indicates that the alert parameter is a cardinal limit. */
  public final static AlertParamType THRESHOLD  = new AlertParamType(3);
    
  /** Private constructor */
  private AlertParamType(int s) { super(s); }
  
	/** Returns the AlertParamType instance that corresponds to the given parameter.  If
  * the paramter does not correspond to an AlertParamType, then this method returns null.
  *
  * @param i int value of the desired AlertParamType
  * @return the AlertParamType corresponding to the given parameter
  */
  public static AlertParamType getInstance(int d) throws OculusException
  {
    if(d == 0)
      return RATIO_HIGH;
    else if(d == 1)
      return RATIO_LOW;
    else if(d == 2)
      return BASELINE;
    else if(d == 3)
      return THRESHOLD;
    else
      throw new OculusException("Invalid AlertParamType.");
  }
}