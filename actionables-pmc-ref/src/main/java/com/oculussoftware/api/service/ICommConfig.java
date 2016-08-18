package com.oculussoftware.api.service;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

/*
* $Workfile: ICommConfig.java $
* Description: The methods necessary to maintain the CommmConfig
* settings in the CommConfig table.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* The methods necessary to maintain the CommmConfig
* settings in the CommConfig table.  The CommConfig table
* at this time only has one row. 
*
* @author Egan Royal
*/
public interface ICommConfig extends IRObject
{

  // --------------------------- Accessors -------------------------
  /**
  * This accessor method returns the mail server port.
  * @return The mail server port.
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public int getMailServerPort() throws ORIOException;
  
  /**
  * This accessor method returns the mail server URL.
  * @return The mail server URL.
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public String getMailServer() throws ORIOException;
  
  /**
  * This accessor method returns true iff the MailService is enabled.
  * @return true iff the MailService is enabled.
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public boolean isEnabled() throws ORIOException;
  
  /**
  * This accessor method returns the Document Network Path.
  * @return The Document Network Path.
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public String getDocNetworkPath() throws ORIOException;
  
  /**
  * This accessor method returns the Extranet URL.
  * @return The Extranet URL.
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public String getExtranetURL() throws ORIOException;
  
  // ---------------------------- Mutators -------------------------
  /**
  * This mutator method sets the mail server port.
  * @param port The port number.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public ICommConfig setMailServerPort(int port) throws ORIOException;
  
  /**
  * This mutator method sets the mail server URL.
  * @param server The URL.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public ICommConfig setMailServer(String server) throws ORIOException;
  
  /**
  * This mutator method enables/disables the MailService.
  * @param enabled true enable, false disable.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public ICommConfig isEnabled(boolean enabled) throws ORIOException;
  
  /**
  * This mutator method sets the Document Network Path.
  * @param path The path.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public ICommConfig setDocNetworkPath(String path) throws ORIOException;
  
  /**
  * This mutator method sets the Extranet URL.
  * @param url The URL.
  * @return this
  * @exception com.oculussoftware.api.repi.ORIOException 
  */
  public ICommConfig setExtranetURL(String url) throws ORIOException;
  
}//end ICommConfig