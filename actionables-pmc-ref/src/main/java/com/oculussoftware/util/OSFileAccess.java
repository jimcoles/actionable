package com.oculussoftware.util;

/**
* Filename:    OSFileAccess.java
* Date:        
* Description: Wraps the file creation/directory access process.
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/
import java.io.*;
import com.oculussoftware.api.sysi.OculusException;

public class OSFileAccess
{


	
  public OSFileAccess() throws OculusException
  {

  }

  public void mkdir(String directoryPath) throws OculusException
  {
    try
    { 
      File f = new File(directoryPath);
      if (! f.exists())
      {
        f.mkdir();
        if (! f.exists())
          throw new OculusException("Directory creation failed.  Please ensure appropriate permissions");
      }
      else
        throw new OculusException("A folder with that name already exists.");
    }
    catch (OculusException e)
    {
      throw e;
    }    
    catch (Exception e)
    {
      throw new OculusException(e);
    }
    
  }

  public File getFile(String FilePath) throws OculusException
  {
    File file = new File(FilePath);  
    if (file.isFile())
      return file;
    else
      throw new OculusException("The path did not point to a normal file");  
  }

  public File getDirectory(String FilePath) throws OculusException
  {
    File file = new File(FilePath);  
    if (!file.isFile())
      return file;
    else
      throw new OculusException("The path did not point to a directory");  
  }

  public File[] getFiles(File file) throws OculusException
  {
    if (file.isDirectory())
      return file.listFiles();
    else
      throw new OculusException("The path did not point to a directory");  
  }
}
