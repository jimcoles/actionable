package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: GraphColor.java $
* Description: Integer Enumeration of GraphColors.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of Graph Color Schemes for use with the Reports.
*
* @author Egan Royal
*/
public final class GraphColor extends IntEnum
{
  private String _name;
  
  /**
  * value 0.
  */
  public final static GraphColor COLOR     = new GraphColor(0,"Color");
  
  /**
  * value 1
  */
  public final static GraphColor GRAYSCALE = new GraphColor(1,"Grayscale");

  /**
  * Takes an int and returns a GraphColor iff the int is valid.
  *
  * @param d the int value
  * @return The GraphColor that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid GraphColor
  */
  public static GraphColor getInstance(int d) throws OculusException
  {
    if(d == 0)
      return COLOR;
    else if(d == 1)
      return GRAYSCALE;
    else
      throw new OculusException("Invalid GraphColor.");
  }//end getInstance
  
  /** Private constructor */
  private GraphColor(int s,String name)
  { 
    super(s); 
    _name = name;
  }
  
  /**
  * Returns the name of the GraphColor.
  * @return The name of the GraphColor.
  */
  public String getName()
  { return _name; }
}