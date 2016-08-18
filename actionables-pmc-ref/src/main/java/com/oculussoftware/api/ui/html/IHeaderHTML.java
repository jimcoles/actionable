package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IHeaderHTML.java $
* Description: Smart IHTML object that knows how to display captions, menus,
* and toolbars.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Smart IHTML object that knows how to display captions, menus,
* and toolbars.
*
* @author Saleem Shafi
*/
public interface IHeaderHTML extends IHTML
{   
  /**
  * Sets the caption for the header frame.
  * @param caption The caption for the header frame.
  * @return this
  */
  public IHeaderHTML setCaption(String caption);
  
  /**
  * Adds a menu to the header frame (the old way).  This method is not currently 
  * being used.
  * @param menuname The menu name.
  * @param isToolbar true if the menu has a toolbar.
  */
  public void addMenu(String menuname, boolean isToolbar);
  
  /**
  * Adds a menu to the header frame. (the new way)
  * @param menuname The menu name.
  * @param isToolbar true if the menu has a toolbar.
  */
  public void addNewMenu(String menuname, boolean isToolbar);
  
  /**
  * Takes the number of icons and creates a shell for a toolbar.
  * @param numIcons The number of icons for the toolbar
  */
  public void addToolbar(int numIcons);
  public ITableData getLeftTableData();
  public ITableData getRightTableData();
  public boolean hasSubTab();
  public void addSubTab(boolean subtab);
  
}