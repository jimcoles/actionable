package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    BR.java
* Date:        02.22.00
* Description: Represents a generic Linebreak element: <BR>
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jachin Cheng
* @version 1.0
*/
public class BR extends GenericElement implements IBR
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  * ----            Jachin Cheng    02.25.00    Changed from Br to BR
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public BR()
  {
    _elementType = "BR";
    _hasElements = false; 
    _isClosable = false;
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
  
  public IBR setClass(String paramVal) { setParam("CLASS",paramVal); return this; }
  public IBR setClear(String paramVal) { setParam("CLEAR", paramVal); return this; }
  public IBR setID(String paramVal)    { setParam("ID",paramVal); return this; }
  public IBR setStyle(String paramVal) { setParam("STYLE",paramVal); return this; }
  public IBR setTitle(String paramVal) { setParam("TITLE",paramVal); return this; }
  
}//end Br class def