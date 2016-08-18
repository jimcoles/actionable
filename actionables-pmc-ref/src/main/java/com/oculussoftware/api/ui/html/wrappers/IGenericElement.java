package com.oculussoftware.api.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.OculusException;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IGenericElement.java $
* Description: Represents a generic HTML interface.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Represents a generic HTML interface.  IGenericElement is the superclass 
* for all HTML Elements.  It is responsible for maintaining the information
* used to generate the final HTML String.
*
* @author Egan Royal
*/
public interface IGenericElement
{
  /**
  * Sets the BrowserKind of the Element.
  * @parma bk The BrowserKind.
  * @return this
  */
  public IGenericElement setBrowserKind(BrowserKind bk);
      
  /**
  * Returns the HTML tags for this Element, including start tag with parameters, 
  * contained elements, and end tag.
  * @return The HTML String for this Element.
  */
  public String toString();
    
  /** 
  * Returns entire HTML start tag.
  * @return The HTML start tag for this Element.
  */  
  public String getStartTag();

  /**
  * Returns entire HTML end tag.
  * @return The HTML end tag for this Element
  */     
  public String getEndTag();

  /**
  * Returns HTML String for all params.
  * @return The String that contains the parameters for this tag.
  */  
  public String getParams();
  
  /** 
  * Returns HTML for all Elements contained by this Element.  
  * note: recursive call to toString() 
  * @return HTML for all Elements contained by this Element.
  */ 
  public String getElements();
  
  /** 
  * Removes all Elements contained by this Element. 
  */
  public void clear();
  
  /**
  * Init the Element.  This method should be overridden by the subclass.
  * The method is called by addElement after the HTML, Parent, and Browser 
  * are set but just before the Element is actually added to the List.  The 
  * method has to be in the iterface because the addElement takes an 
  * IGenericElement on which the init method is called. The init method 
  * may be used to set values based on the BrowserKind, ParentObject, or
  * the HTMLObject. 
  */
  public void init();
  
  /**
  * Returns the BrowserKind for this Element.  The BrowserKind is the 
  * same for all Elements contained by the Parent HTML Object.
  * @return The BrowserKind for this Element.
  */
  public BrowserKind getBrowserKind();
  
  /**
  * Sets the HTML Object for this Element.  Provides a reference back to the
  * HTML Object for this Element.
  * @param obj The HTML Object.
  */
  public void setHTMLObject(IHTML obj);
  
  /**
  * Returns the HTML Object for this Element.
  * @return The HTML Object fro this Element.
  */
  public IHTML getHTMLObject();
  
  /**
  * Sets the Parent Object for this Element.  Provides a reference back to the
  * Parent Object for this Element.
  * @param obj The Parent Object.
  */
  public void setParentObject(IGenericElement obj);
  
  /**
  * Returns the Parent Object for this Element.
  * @return The Parent Object fro this Element.
  */
  public IGenericElement getParentObject();
  
  /**
  * Returns the String Value for this Element (empty string if one does not exist).
  * @return The String Value for this Element. 
  */
  public String getStringValue();
}//end IGenericElement interface definition