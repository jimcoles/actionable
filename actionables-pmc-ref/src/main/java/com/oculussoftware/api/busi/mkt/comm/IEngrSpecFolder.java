package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.io.*;

/** This interface represents a operating system folder that contains engineering specifications.
*
* @author Zain Nemazie
*/

/*
* $Workfile: IEngrSpecFolder.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

public interface IEngrSpecFolder extends IRObject
{
  /** The value is the value to use with IRPropertMap.get() to retrieve the index file property. */
  public static final String LABEL_INDEXFILE = "INDEXFILE";
  /** The value is the value to use with IRPropertMap.get() to retrieve the name property. */
  public static final String LABEL_NAME = "NAME";
  /** The value is the value to use with IRPropertMap.get() to retrieve the folder path property. */
  public static final String LABEL_FOLDERNETPATH = "FOLDERNETPATH";  
  /** The value is the value to use with IRPropertMap.get() to retrieve the description property. */
  public static final String LABEL_DESCRIPTION = "DESCRIPTION";

  /** Sets the name of the index file for this folder.
  *
  * @param str the name of the index file
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IEngrSpecFolder setIndexFile(String str)
    throws OculusException;

  /** Returns whether or not this folder has an auto-generated index.
  *
  * @return true if this folder has an auto-generated index, false otherwise.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean hasAutocreateIndex()
    throws OculusException;    
  
  /** Returns the name of the index file for this folder.
  *
  * @return the name of the index file
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getIndexFile()
    throws OculusException;
  
  /** Returns a list of Files in this folder.
  *
  * @return array of Files
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public File[] getFilesInFolder()
    throws OculusException;  

  /** Returns the path of the folder system on the network.
  *
  * @return the path of the network folder
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFolderNetPath()    
    throws OculusException;
}