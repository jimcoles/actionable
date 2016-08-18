package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Style.java
* Date:        03.07.00
* Description: Represents a generic Style element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/
public class Style extends GenericElement implements IStyle
{
  /*
  * Change Activity
  *
  *
  */
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Style()
  {
    _hasStringValue = true;
    _elementType = "STYLE";
    _hasElements = false;
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
  
  public IStyle setType(String paramVal) { setParam("TYPE",paramVal); return this; }
  public IStyle setStringValue(String sval) { _stringvalue = new StringBuffer(""+sval); return this; }
  public IStyle appendStringValue(String sval) { _stringvalue.append(sval); return this; }//
  
}//end Style class def