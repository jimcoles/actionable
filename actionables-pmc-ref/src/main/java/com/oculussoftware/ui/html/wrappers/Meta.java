package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    Meta.java
* Date:        02.22.00
* Description: Represents an HTML Meta-Information element
*          e.g. <Meta> ... </Meta>
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
* ----            Jachin Cheng    02.25.00    Changed to reflect changes to 
*                                             interface, LangKind
*
*/

public class Meta extends GenericElement implements IMeta
{
  //----------------------------- Public Constructors -------------------------
  
  /** Basic default constructor */
  public Meta()
  {
    _elementType = "META";
    _hasElements = false;    
  }

  //-------------------------------- Mutator Methods ---------------------------    
    
  public IMeta setContent(String paramVal) {setParam("CONTENT", paramVal); return this;} 
  public IMeta setDir(String paramVal) {setParam("DIR", paramVal); return this;} 
  public IMeta setHttpequiv(String paramVal) {setParam("HTTP-EQUIV", paramVal); return this;} 
  public IMeta setLang(LangKind lk) {setParam("LANG", lk.getStringValue()); return this;} 
  public IMeta setName(String paramVal) {setParam("NAME", paramVal); return this;} 
  public IMeta setScheme(String paramVal) {setParam("SCHEME", paramVal); return this;}         
  public IMeta setURL(String paramVal) {setParam("URL", paramVal); return this;}
  
  public IMeta appendContent(String paramVal)
  {
    if (getParam("CONTENT") == null) {setParam("CONTENT", paramVal);}
    else {setParam("CONTENT", getParam("CONTENT")+" "+paramVal);}
    return this;
  }      
  
}//end Meta class def