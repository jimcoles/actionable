package com.oculussoftware.api.sysi.sec;
/**
* $Workfile: AttrGroupOper.java $
* Create Date: 3-20-2000
* Description: Represents the two types of access to attribute groups: view or edit
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.util.IntEnum;

import java.util.*;

/** 
* 
*/
public class AttrGroupOper
{
  //-----------------------------------------------------------------------------
  // Public constants
  //-----------------------------------------------------------------------------
  public static final AttrGroupOper EDIT = new AttrGroupOper(1, "Edit", "Allows edit");
  public static final AttrGroupOper VIEW = new AttrGroupOper(2, "View", "Allows viewing only");
  public static final AttrGroupOper NO_VIEW = new AttrGroupOper(3, "No View", "No viewing or editing ");

  //-----------------------------------------------------------------------------
  // Private instance variables
  //-----------------------------------------------------------------------------
  private int     _id;
  private String  _name = null;
  private String  _shortDesc = null;
  
  //-----------------------------------------------------------------------------
  // Private constructor
  //-----------------------------------------------------------------------------
  private AttrGroupOper(int id, String name, String desc)
  {
    _id = id;
    _name = name;
    _shortDesc = desc;
  }

  //-----------------------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------------------
  public String toString()
  {
    return Integer.toString(_id);
  }
  
  public int getID()
  {
    return _id;
  }
  
  public int getIntValue()
  {
    return _id;
  }
  
  
  
  public String getName()
  {
    return _name;
  }
  
  public String getDescription()
  {
    return _shortDesc;
  }
  
  static public AttrGroupOper getInstance(int i)
  {
    
    if (i ==1) return AttrGroupOper.EDIT;
    if (i ==2) return AttrGroupOper.VIEW;
    if (i ==3) return AttrGroupOper.NO_VIEW;
    return null;
  }
}