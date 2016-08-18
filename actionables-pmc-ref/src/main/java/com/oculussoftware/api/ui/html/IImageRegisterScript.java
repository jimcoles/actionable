package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IImageRegisterScript.java $
* Description: Smart IHTML object that knows how to register images for
* a script to activate/deactivate.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Smart IHTML object that knows how to register images for
* a script to activate/deactivate.  In order for the script to
* work correctly, the IImgs have to be put in the IHTML document
* in the correct (top down) order.
*
* @author Saleem Shafi
*/
public interface IImageRegisterScript extends IScript
{   
  /**
  * This method registers an IImg to activate/deactivate for javascript events.
  * @param img The IImg.
  * @return The index of the IImg in the javascript images array. 
  */
  public int registerImage(IImg img);
  
  /**
  * This method registers an IImg to activate/deactivate for javascript events.
  * @param img The IImg
  * @param unselectName The path to the unselected image.
  * @param selectName The path to the selected image.
  * @param rollOverName The path to the rollover image.
  * @return The index of the IImg in the javascript images array.
  */
  public int registerImage(IImg img, String unselectName, String selectName, String rollOverName);
  
}