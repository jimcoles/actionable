package com.oculussoftware.ui;

import javax.servlet.http.*;
import java.util.*;


/**
* Filename:    SessionListener.java
* Date:        8-25-00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public class SessionListener implements HttpSessionBindingListener
{

  /*
  * Change Activity
  *
  * Issue number  	Programmer    	Date      	Description
  */
  
  /**
  *
  */
  public SessionListener()
  {}
  
  /**
  *
  */
  public void valueBound(javax.servlet.http.HttpSessionBindingEvent e) 
  {
	// we don't care about the binding event
  }  
  
  /**
  *
  */
  public void valueUnbound(javax.servlet.http.HttpSessionBindingEvent e) 
  {
	  SessionSrvc.invalidate(e.getSession().getId());
  }  
}