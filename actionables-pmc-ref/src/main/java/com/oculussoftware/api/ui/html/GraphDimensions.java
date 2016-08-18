package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: GraphDimensions.java $
* Description: Integer Enumeration of GraphDimensions.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of Graph Dimension Schemes for use with the Reports.
*
* @author Egan Royal
*/
public final class GraphDimensions extends IntEnum
{
  private String _name;
  
  /**
  * value 0
  */
  public final static GraphDimensions TWO_D  = new GraphDimensions(0,"2D");
  
  /**
  * value 1
  */
  public final static GraphDimensions THREE_D = new GraphDimensions(1,"3D");

  /**
  * Takes an int and returns a GraphDimensions iff the int is valid.
  *
  * @param d the int value
  * @return The GraphDimensions that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid GraphDimensions
  */
  public static GraphDimensions getInstance(int d) throws OculusException
  {
    if(d == 0)
      return TWO_D;
    else if(d == 1)
      return THREE_D;
    else
      throw new OculusException("Invalid GraphDimensions.");
  }//end getInstance
  
  /** Private constructor */
  private GraphDimensions(int s,String name) 
  { 
    super(s); 
    _name = name;
  }
  
  /**
  * Returns the name of the GraphDimensions.
  * @return The name of the GraphDimensions.
  */
  public String getName()
  { return _name; }
}