package com.oculussoftware.api.ui.html;

import com.oculussoftware.api.ui.html.wrappers.*;
import java.lang.*;
import java.util.*;

/*
* $Workfile: IPageFrameset.java $
* Description: Smart IFrameset object that knows how to set up the
* default frameset.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Smart IFrameset object that knows how to set up the
* default frameset.
*
* @author Saleem Shafi
*/
public interface IPageFrameset extends IFrameset
{   
	/**
  * Returns the IFrame object that is the Header Frame.
  * @return The Header IFrame object.
  */
  public IFrame getHeaderFrame();
  
  /**
  * Returns the IFrame object that is the Body Frame.
  * @return The Body IFrame object.
  */
  public IFrame getBodyFrame();
  
  /**
  * Returns the IFrame object that is the Footer Frame.
  * @return The Footer IFrame object.
  */
  public IFrame getFooterFrame();

  /**
  * Returns the size of the Header Frame.
  * @return The size of the Header Frame.
  */
  public int getHeaderSize();
  
  /**
  * Returns the size of the Footer Frame.
  * @return The size of the Footer Frame.
  */
  public int getFooterSize();

  /**
  * Sets the size of the Header Frame.
  * @param size The size in pixels.
  * @return The Header IFrame object.
  */
  public IFrame setHeaderSize(int size);
  
  /**
  * Sets the size of the Footer Frame.
  * @param size The size in pixels.
  * @return The Footer IFrame object.
  */
  public IFrame setFooterSize(int size);

  /**
  * Returns true iff the IPageFrameset needs a "please wait ... loading" indicator.
  * @return true iff the IPageFrameset needs a "please wait ... loading" indicator.
  */
	public boolean needsHourGlass();
  
  /**
  * Sets the IPageFrameset value that indicates whether or not it needs
  * a "please wait ... loading" indicator.
  * @param need true if the IPageFrameset needs an indicator
  * @return this
  */
	public IPageFrameset needsHourGlass(boolean need);

}