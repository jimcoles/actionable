package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: GraphSize.java $
* Description: Integer Enumeration of GraphSizes.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of GraphSizes for use with the Reports.
*
* @author Egan Royal
*/
public final class GraphSize extends IntEnum
{
  private String _name;
  private int _height;
  private int _width;
  
  /**
  * value 0
  */
  public final static GraphSize SMALL  = new GraphSize(0,"Small",400,400);
  
  /**
  * value 1
  */
  public final static GraphSize MEDIUM = new GraphSize(1,"Medium",500,500);
  
  /**
  * value 2
  */
  public final static GraphSize LARGE  = new GraphSize(2,"Large",750,750);

  /**
  * Takes an int and returns a GraphSize iff the int is valid.
  *
  * @param d the int value
  * @return The GraphSize that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid GraphSize
  */
  public static GraphSize getInstance(int d) throws OculusException
  {
    if(d == 0)
      return SMALL;
    else if(d == 1)
      return MEDIUM;
    else if(d == 2)
      return LARGE;
    else
      throw new OculusException("Invalid GraphSize.");
  }//end getInstance
  
  /** Private constructor */
  private GraphSize(int s,String name,int width,int height) 
  { 
    super(s); 
    _name = name;
    _width = width;
    _height = height;
  }
  
  /**
  * Returns the name of the GraphSize.
  * @return The name of the GraphSize.
  */
  public String getName()
  { return _name; }
  
  /**
  * Returns the width of the GraphSize.
  * @return The width of the GraphSize.
  */
  public int getWidth()
  { return _width; }
  
  /**
  * Returns the height of the GraphSize.
  * @return The height of the GraphSize.
  */
  public int getHeight()
  { return _height; }
}