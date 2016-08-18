package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.util.DataSet;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.*;

/**
* <B>Filename:</B> PoolMgr.java<BR>
* <B>Date:</B> <BR>
* <B>Description:</B> <P>Thread that tells the PoolMgr to clean itself out, 
* every now and then.  It uses the <code>obj.PoolCleaner.cycleTime</code> value in the 
* directory to determine the number of milliseconds between cleanups.</P>
*
* <P>Copyright 1-31-2000 Oculus Software.  All Rights Reserved.</P>
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*	---							Saleem Shafi		2/4/00			Changed initialization because the initializer
*																							can't throw exceptions.
* ---				      Bob Makowski		2000-03-21	split PoolCleaner from inside PoolMgr.java
*/

public class PoolCleaner extends Thread
{
  /** The number of milliseconds between cleanups */
	private static long cycle = -1;

  /**
  Executes the thread.  If this is the first cycle, the user-defined cycle time is
  read from the directory.  Then the Pool is cleaned every <code>obj.PoolCleaner.cycleTime</code> milliseconds.
  */
  public void run()
  {
  	if (cycle == -1)
  	{
			try
  		{
  			// get the amount of time between pool cleanups
				cycle = Long.parseLong((String)SimpleDirectory.getInstance().getValue("cycleTime","PoolCleaner"));	
  		}
  		catch (OculusException ignore)
  		{
  		}
  	}
  	
	while(true)
	{
	   if (PoolMgr.getInstance() != null)
		 PoolMgr.getInstance().clean();      // as long as there is a pool, clean it out         
		try { sleep(cycle); }	catch(InterruptedException ignored) {}
																					// wait for a while.
  	}//end while
 	}//end run 
}