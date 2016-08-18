package com.oculussoftware.api.ui.html.wrappers;

import java.lang.*;
import java.util.*;

/*
* $Workfile: IHTML.java $
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
* Represents a generic HTML interface.
*
* @author Egan Royal
*/
public interface IHTML extends IGenericElement
{   
  /**
  * Adds and returns an IHead.
  * @return The IHead. 
  */
  public IHead addHead();
 
  /**
  * Adds and returns an IBody.
  * @return The IBody. 
  */       
  public IBody addBody();
  
	/**
  * Adds and returns an IPageFrameset. Default has a Footer, no Toolbar, and no Subtabs.
  * @return The IPageFrameset. 
  */
  public com.oculussoftware.api.ui.html.IPageFrameset addPageFrameset();
  
  /**
  * Adds and returns an IPageFrameset. No Toolbar and no Subtabs
  * @param hasFooter true if the IPageFrameset has a footer.
  * @return The IPageFrameset. 
  */
  public com.oculussoftware.api.ui.html.IPageFrameset addPageFrameset(boolean hasFooter);
	
  /**
  * Adds and returns an IPageFrameset. No Subtabs.
  * @param hasFooter true if the IPageFrameset has a footer.
  * @param hasToolbar true if the IPageFrameset has a toolbar.
  * @return The IPageFrameset. 
  */
  public com.oculussoftware.api.ui.html.IPageFrameset addPageFrameset(boolean hasFooter, boolean hasToolbar);
	
  /**
  * Adds and returns an IPageFrameset.
  * @param hasFooter true if the IPageFrameset has a footer.
  * @param hasToolbar true if the IPageFrameset has a toolbar.
  * @param hasSubTab true if the IPageFrameset has a subtabs.
  * @return The IPageFrameset. 
  */
  public com.oculussoftware.api.ui.html.IPageFrameset addPageFrameset(boolean hasFooter, boolean hasToolbar, boolean hasSubTab);
		
  /**
  * Adds and returns an IFrameset.
  * @return The IFrameset. 
  */
  public IFrameset addFrameset();
  
  /**
  * Returns the IHead Object for this IHTML Object. (null if an IHead has not been added).
  * @return The IHead Object for this IHTML Object.
  */
  public IHead getHead();
  
  /**
  * Returns the IBody Object for this IHTML Object. (null if an IBody has not been added).
  * @return The IBody Object for this IHTML Object.
  */
  public IBody getBody();
  
  /**
  * Returns the IFrameset Object for this IHTML Object. (null if an IFrameset has not been added).
  * @return The IFrameset Object for this IHTML Object.
  */
  public IFrameset getFrameset();
  
  /**
  * If the passed in IGenericElement is a instance of IImg the image count
  * is incremented.
  */
  public void added(IGenericElement ge);
}//end IHtml interface def