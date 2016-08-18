package com.oculussoftware.ui.html;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    PMHeader.java
* Date:        03.22.00
* Description: Represents an HTML element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.0
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class ImageRegisterScript extends com.oculussoftware.ui.html.wrappers.Script implements IImageRegisterScript
{
  protected String _script = null;
  protected int _numImages = 0;
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public ImageRegisterScript()
  {
    _script = "function registerImages() { }";
    setStringValue(_script);
  }

  public int registerImage(IImg img, String unselectName, String selectName, String rollOverName)
  {
    if (unselectName == null && selectName == null)
      return registerImage(img);
      
    int insertPoint = _script.length()-2;
    String newLine = "makeSelectable(document.images["+img.getIndex()+"],'"+unselectName+"','"+selectName+"','"+rollOverName+"'); ";
    _script = _script.substring(0,insertPoint)+newLine+" }";
    setStringValue(_script);
    
    return _numImages++;
  }

  public int registerImage(IImg img)
  {
    int insertPoint = _script.length()-2;
    String newLine = "makeSelectable(document.images["+img.getIndex()+"]); ";
    _script = _script.substring(0,insertPoint)+newLine+" }";
    setStringValue(_script);
    
    return _numImages++;
  }


}