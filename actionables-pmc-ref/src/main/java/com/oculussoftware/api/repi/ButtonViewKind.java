package com.oculussoftware.api.repi; 

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    ButtonViewKind.java
* Date:        03.15.00
* Description: Typesafe constants indicating a SvtProcessHeader's
*                button view.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.2
*/
public final class ButtonViewKind extends StringEnum
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  public final static ButtonViewKind STATEEDIT        = new ButtonViewKind("STATEEDIT");
  public final static ButtonViewKind STATEVIEW      = new ButtonViewKind("STATEVIEW");
  public final static ButtonViewKind TRANSITIONEDIT = new ButtonViewKind("TRANSITIONEDIT");
  public final static ButtonViewKind TRANSITIONVIEW = new ButtonViewKind("TRANSITIONVIEW");
  public final static ButtonViewKind ACTIONEDIT     = new ButtonViewKind("ACTIONEDIT");
  public final static ButtonViewKind ACTIONVIEW     = new ButtonViewKind("ACTIONVIEW");
  public final static ButtonViewKind GUARDEDIT      = new ButtonViewKind("GUARDEDIT");
  public final static ButtonViewKind GUARDVIEW      = new ButtonViewKind("GUARDVIEW");
  
  /** Private constructor */
  private ButtonViewKind(String s) { super(s); }
}