package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Header.java
* Date:        02.22.00
* Description: Represents an HTML Header element
*          e.g. <H1> ... <H6>
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
* ----            Jachin Cheng    02.23.00    Added setStringValue()
* ----            Jachin Cheng    02.24.00    Changed to implement interface
*/

public class Header extends GenericElement implements IHeader
{

  private static final String WRONG_SIZE = "Headers must be of size 1-6";

  //----------------------------- Public Constructors -------------------------
  
  /** Private basic default constructor: Headers must be instantiated w/ a size*/
  private Header()
  {
    _hasElements = false;  
  }
  
  /** Constructor: Sets size */  
  public Header(int headerSize) throws OculusException
  {
    if (headerSize < 1 || headerSize > 6) throw new OculusException(WRONG_SIZE);
    _elementType = "H" + headerSize;      
    _hasElements = false;    
    _hasStringValue = true; 
  } 
    
  //-------------------------------- Mutator Methods ---------------------------     
  
  /** Sets the string bounded by the header tag */
  public IHeader setStringValue(String value) { _stringvalue = new StringBuffer(""+value); return this; }

}//end Header class def