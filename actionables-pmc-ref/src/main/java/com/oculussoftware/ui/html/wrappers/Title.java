package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Title.java
* Date:        02.22.00
* Description: Represents an HTML Document Title element
*          e.g. <Title> ... </TITLE>
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* ----            Jachin Cheng    02.24.00    Changed to implement interface
*
*/

public class Title extends GenericElement implements ITitle
{
  //----------------------------- Public Constructors -------------------------
  
  /** Basic default constructor */
  public Title()
  {
    _elementType = "TITLE";
    _hasStringValue = true;
    _hasElements = false;    
  }
  
  //-------------------------------- Mutator Methods ---------------------------     
  
  /** Sets the string bounded by the anchor tag */
  public ITitle setStringValue(String value) { _stringvalue = new StringBuffer(""+value); return this; }

    
  
}//end Title class def