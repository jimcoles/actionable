package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

import java.io.*;

/** This interface represents a URL attached to a business object.  It only contains the actual
* URL and not the contents of the page.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IHyperLink.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IHyperLink extends IBusinessObject
{
  /** This value is the value to be used when trying to retrieve the label property through IRPropertyMap.get(). */
  public static final String LABEL_URL = "prod"+IDCONST.URL.getIIDValue();

  /** Creates an exact copy of this hyperlink object.
  *
  * @return the newly created copy of this hyperlink object
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IHyperLink createCopy()
    throws OculusException;
    
  /** Sets the parent business object that this hyperlink object is attached to.
  *
  * @param parentBObj the business object that this hyperlink object is attached to
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public IHyperLink setParentObject(IBusinessObject parentBObj)
    throws ORIOException;

  /** Sets the URL that this hyperlink object refers to.
  *
  * @param url the URL that this hyperlink object refers to
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public IHyperLink setURL(String url)
    throws ORIOException;  

  /** Sets the type of hyperlink object this is.
  *
  * @param hlt the type of hyperlink object
  * @return this object
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public IHyperLink setLinkType(HyperLinkType hlt)
    throws ORIOException;

  /** Returns the URL that this hyperlink object refers to.
  *
  * @return the URL of this hyperlink object
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public String getURL()
    throws OculusException;
    
  /** Returns the type of hyperlink attachment this is.
  *
  * @return the type of this hyperlink object
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public HyperLinkType getLinkType()
    throws ORIOException;
}