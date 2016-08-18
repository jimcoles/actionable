package com.oculussoftware.util;

import java.io.*;
import java.math.*;
import com.oculussoftware.*;

/**
* Filename:    DriveStatus.java
* Date:        8-17-1999
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*
*/
public final class DriveStatus
{
 	/**
 	* delete the file
 	*/
 	private static void deleteHack(String strPath)
	{
		File jdtF = new File(strPath);
		if(jdtF.isFile())
			jdtF.delete();
	}//end deleteHack
	/**
	* hack, calls a DOS process and runs a DIR putting the output into a file
	* the file is then read until the string "bytes free" is found.  Finally the 
	* method reads the number in as a String
	*/
	public synchronized static long freeSpace()
 	{
  	String[] cmd = new String[] {"cmd.exe"};
  	String strFree = null;
 	  try 
 		{
 			Runtime jdtR = Runtime.getRuntime();
  		Process jdtP = jdtR.exec(cmd);
  		OutputStream jdtOS = jdtP.getOutputStream();
  		jdtOS.write((new String("dir > temp.fl \r\n")).getBytes());	
  		jdtOS.write((new String("exit\r\n")).getBytes());
  		jdtOS.close();
  		jdtP.waitFor();
  		BufferedReader jdtBR = new BufferedReader(new FileReader("temp.fl"));
  		String str = jdtBR.readLine();	
  		while (str != null)
  		{
   			int i = str.indexOf("bytes free");
   			if (i != -1)
				strFree = str.substring(0, i-1).trim();
   			str = jdtBR.readLine();
  		}//end while
   		jdtBR.close();
 		}//end try
  	catch(InterruptedException ignored) 
    { 
  	  com.oculussoftware.service.log.LogService.getInstance().write(ignored); 
    }
  	catch(IOException ignored) 
    {
      com.oculussoftware.service.log.LogService.getInstance().write(ignored);   
    }
  	deleteHack("temp.fl");
  	return lngVal(strFree.trim());
 	}//end freeSpace   
 	/**
 	* convert bytes to megabytes
 	*/
 	public static long getMegabytes(long lngBytes) { return lngBytes/1048576; } 
	/**
	* takes a String that is a number and contains commas and 
	* returns a long value.
	*/
	private static long lngVal(String strFree)
	{
		int i = strFree.indexOf(",");
   	while (i != -1)
   	{
   		strFree = strFree.substring(0, i) + strFree.substring(i+1, strFree.length());
   		i = strFree.indexOf(",");
	}//end while
	BigInteger bi = new BigInteger(strFree);
	return bi.longValue();
	}//end lngVal
}//end DriveStatus
