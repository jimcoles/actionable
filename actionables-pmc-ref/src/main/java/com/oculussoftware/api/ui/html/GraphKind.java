package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: GraphKind.java $
* Description: Integer Enumeration of GraphKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of GraphKinds for use with the Reports.
*
* @author Egan Royal
*/
public final class GraphKind extends IntEnum
{
  private String _name;
  
  /**
  * value 0
  */
  public final static GraphKind HORIZONTAL_BAR = new GraphKind(0,"Horizontal Bar Graph");
  
  /**
  * value 1
  */
  public final static GraphKind VERTICAL_BAR   = new GraphKind(1,"Vertical Bar Graph");
  
  /**
  * value 2
  */
  public final static GraphKind PIE            = new GraphKind(2,"Pie Chart");

  /**
  * Takes an int and returns a GraphKind iff the int is valid.
  *
  * @param d the int value
  * @return The GraphKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid GraphKind
  */
  public static GraphKind getInstance(int d) throws OculusException
  {
    if(d == 0)
      return HORIZONTAL_BAR;
    else if(d == 1)
      return VERTICAL_BAR;
    else if(d == 2)
      return PIE;
    else
      throw new OculusException("Invalid GraphKind.");
  }//end getInstance
  
  /** Private constructor */
  private GraphKind(int s, String name)
  { 
    super(s);
    _name = name; 
  }
  
  /**
  * Returns the name of the GraphKind.
  * @return The name of the GraphKind.
  */
  public String getName()
  { return _name; }
}