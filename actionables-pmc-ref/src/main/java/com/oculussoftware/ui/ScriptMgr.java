package com.oculussoftware.ui;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.service.log.LogService;

import java.sql.*;
import java.io.*;
import java.util.*;

/**
* Filename:    ScriptMgr.java
* Date:       	3-16-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.1
*/
public class ScriptMgr
{
	private static ScriptMgr _instance;
	private Map _scripts;
	
  private ScriptMgr()
  {
		_scripts = new HashMap();
  }
	
	public static ScriptMgr getInstance()
	{
		if (_instance == null)
			_instance = new ScriptMgr();
		return _instance;	
	}
  
	public String getScript(String filename)
	{
		if (!_scripts.containsKey(filename))
			loadScript(filename);
		return (String)_scripts.get(filename);	
	}
  
	private void loadScript(String filename)
	{
		BufferedReader in = null;
		StringBuffer file = new StringBuffer("");
		try
		{
			in = new BufferedReader(new FileReader("."+filename));
			String thisLine = in.readLine();
			while (thisLine != null)
			{
				file.append(thisLine + "\n");
				thisLine = in.readLine();
			}
		}
		catch (IOException exp)	{}
		finally 
		{
			try
			{
				if (in != null) in.close();
			}
			catch (IOException ignore) 
			{
			}
		}
		
		_scripts.put(filename,file.toString());
	}
	
}//end class