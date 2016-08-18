package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types of alerts invoked by this
* system.  It is used by the AlertThread object.
*
* @author Egan Royal
*/

/*
* $Workfile: AlertType.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class AlertType extends IntEnum
{
  /** this value (0) refers to alerts that are based on the ratio of highest priority complete features and lowest priority complete features. */
  public final static AlertType RATIO_1          = new AlertType(0);
  /** this value (1) refers to alerts that are based on the ratio of highest and high priority complete features and lowest and low priority complete features. */
  public final static AlertType RATIO_2          = new AlertType(1);
  /** this value (2) refers to alerts that are based on the number of features added since a baseline. */
  public final static AlertType BASELINE_ADDED   = new AlertType(2);
  /** this value (3) refers to alerts that are based on the number of features removed since a baseline. */
  public final static AlertType BASELINE_REMOVED = new AlertType(3);
  /** this value (4) refers to alerts that are based on the number of features edited since a baseline. */
  public final static AlertType BASELINE_EDITED  = new AlertType(4);
  /** this value (5) refers to alerts that are based on whether a feature is not moving through the process efficiently. */
  public final static AlertType NEGOTIATION      = new AlertType(5);
  
  private final static String RATIO_1_NAME          = "Highest and Lowest";
  private final static String RATIO_2_NAME          = "Highest,High and Lowest,Low";
  private final static String BASELINE_ADDED_NAME   = "Added Since Baseline";
  private final static String BASELINE_REMOVED_NAME = "Removed Since Baseline";
  private final static String BASELINE_EDITED_NAME  = "Edited Since Baseline";
  private final static String NEGOTIATION_NAME      = "Stalled Negotiation";
  
  /** Private constructor */
  private AlertType(int s) { super(s); }
  
	/** Returns the AlertType instance that corresponds to the given parameter.  If
  * the paramter does not correspond to an AlertType, then this method returns null.
  *
  * @param i int value of the desired AlertType
  * @return the AlertType corresponding to the given parameter
  */
  public static AlertType getInstance(int d) throws OculusException
  {
    if(d == 0)
      return RATIO_1;
    else if(d == 1)
      return RATIO_2;
    else if(d == 2)
      return BASELINE_ADDED;
    else if(d == 3)
      return BASELINE_REMOVED;
    else if(d == 4)
      return BASELINE_EDITED;
    else if(d == 5)
      return NEGOTIATION;
    else
      throw new OculusException("Invalid AlertType.");
  }
  
	/** Returns the String representation of the AlertType given.  If the paramter does 
  * not correspond to an AlertType, then this method returns an empty string.
  *
  * @param at the AlertType to get the name for
  * @return the AlertType corresponding to the given parameter
  */
  public static String getName(AlertType at)
  {
    String name = "";
    if (at == null) return "";
    if(at.getIntValue() == RATIO_1.getIntValue())
      name = RATIO_1_NAME;
    else if(at.getIntValue() == RATIO_2.getIntValue())
      name = RATIO_2_NAME;
    else if(at.getIntValue() == BASELINE_ADDED.getIntValue())
      name = BASELINE_ADDED_NAME;
    else if(at.getIntValue() == BASELINE_REMOVED.getIntValue())
      name = BASELINE_REMOVED_NAME;
    else if(at.getIntValue() == BASELINE_EDITED.getIntValue())
      name = BASELINE_EDITED_NAME;
    else if(at.getIntValue() == NEGOTIATION.getIntValue())
      name = NEGOTIATION_NAME;
    return name;
  }
  
	/** Returns the String representation of the AlertType for the given int value.  If 
  * the paramter does not correspond to an AlertType, then this method returns an empty
  * string.
  *
  * @param at the AlertType to get the name for
  * @return the AlertType corresponding to the given parameter
  */
  public static String getName(int at) throws OculusException
  {
    return getName(getInstance(at));
  }
 
}