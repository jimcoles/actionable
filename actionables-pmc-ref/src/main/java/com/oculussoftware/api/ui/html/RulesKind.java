package com.oculussoftware.api.ui.html; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: RulesKind.java $
* Description: String Enumeration of HTML Rule Types.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of HTML Rule Types.
*
* @author Egan Royal
*/
public final class RulesKind extends StringEnum
{
  /**
  * value "ALL"
  */
  public final static RulesKind ALL    = new RulesKind("ALL");
  
  /**
  * value "COLS"
  */
  public final static RulesKind COLS   = new RulesKind("COLS");
  
  /**
  * value "GROUPS"
  */
  public final static RulesKind GROUPS = new RulesKind("GROUPS");
  
  /**
  * value "NONE"
  */
  public final static RulesKind NONE   = new RulesKind("NONE");
  
  /**
  * value "ROWS"
  */
  public final static RulesKind ROWS   = new RulesKind("ROWS");
  
  /** Private constructor */
  private RulesKind(String s) { super(s); }
}