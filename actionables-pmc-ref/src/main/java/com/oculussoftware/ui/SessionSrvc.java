package com.oculussoftware.ui;

import javax.servlet.http.*;
import java.util.*;


/**
* Filename:    SessionSrvc.java
* Date:        9-09-1999
* Description: Provides abstraction layer for accessing session from a given
* servlet/page context for a given http request.  It is a good place
* to put any services objects that are need on a per request basis, 
* e.g., debug.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Jim Coles
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* BUG00076        Saleem Shafi    5/19/00     The session handling wasn't thread-safe so
*                                             i made one SessionSrvc object for each user per servlet.
* ---             Egan Royal      7/12/00     Made a couple of changes to getSessionSrvc
*                                             in a flailing attempt to debug a Netscape problem
*/
public class SessionSrvc// implements HttpSessionBindingListener
{
  //-----------------------------------------------------------
  // Public Constants
  //-----------------------------------------------------------
  // http request parm name indicating requesting page/servlet
  public static final String CALLER_PAGE = "REQUESTING_PAGE";

  //-----------------------------------------------------------
  // Private Class variables
  //-----------------------------------------------------------
  private static Hashtable _instances = new Hashtable();
  
  //-----------------------------------------------------------
  // Private Instance variables
  //-----------------------------------------------------------
  HttpSession        _session  = null;
  HttpServletRequest _req      = null;
  String             _caller   = null;
  String             _this     = null;
  String             _target   = null;
  String             _targetFrame = null;
  boolean            _reloadTree = false;
//  DebugSrvc          _debug    = null;

  //-----------------------------------------------------------
  // Private Constructors
  //-----------------------------------------------------------
  private SessionSrvc(HttpSession sess)
  {
	_session  = sess;
  }  
  private void destroyPageContext()
  {
	
  }  
  /** Accessor */
  public String getCaller()
  {
	return _caller;
  }  
  /** Accessor */
  public HttpServletRequest getCurrentRequest()
  {
	return _req;
  }  
  public Object getGlobalValue(String key)
  {
	Object retVal = _session.getValue( key );
	return retVal;
  }  
  
  // getters of session
  
  public int getMaxInactiveInterval()
  {
    return _session.getMaxInactiveInterval();
  }//
  
  public Object getGlobalValueDestroy(String key)
  {
	Object retVal = getGlobalValue( key );
	removeGlobalValue( key );
	return retVal;
  }  
  /** Scope value by current servlet */
  public Object getLocalValue(String key)
  {
	return getGlobalValue( _this + "." + key);
  }  
  public Object getLocalValueDestroy(String key)
  {
    return getGlobalValueDestroy(_this+"."+key);
  }  
  /***/
  public static int getNumSrvcObjects()
  {
	return _instances.size();
  }  
  //-----------------------------------------------------------
  // Public Instance methods
  //-----------------------------------------------------------
 
  /** Accessor */
  public String getSessionId()
  {
	return _session.getId();
  }  
  //-----------------------------------------------------------
  // Public class methods
  //-----------------------------------------------------------
  /** Instance factory method.  Controls instances so there is one per
  *   session id.  Puts HttpSessionBindingListener in place so that the
  *   instance is removed when the session ends. */
  public static final SessionSrvc getSessionSrvc(HttpServletRequest req)
  {
	  SessionSrvc retVal = null;
    HttpSession session = req.getSession(false);//why is this null the first time Netscape calls after a redirect?
    if(session != null)
    {
	    String sid = session.getId()+"."+req.getServletPath();
	    if ( (retVal = (SessionSrvc) _instances.get(sid)) == null ) 
      {
	      SessionSrvc ss = new SessionSrvc(session);
	      _instances.put(sid, ss);
//	      session.putValue("SESSIONSRVC.SELF", ss);
	      retVal = ss;
	    }//end if
	    // always set the current request context..
	    retVal.setCurrentPage(req.getServletPath());
      //retVal.setTargetPage(req.getServletPath());
	    retVal.setCallingPage(req.getParameter(CALLER_PAGE));
	    retVal.setCurrentRequest(req);
    }//end if
	  return retVal;
  }  
  /** Accessor */
  public String getTargetPage()
  {
	return _target;
  }  
  
  public String getTargetFrame()
  {
    return _targetFrame;
  }
  /** Accessor */
  public String getThis()
  {
	return _this;
  }  
  public String[] getValueNames()
  {
	return _session.getValueNames();
  }  
  private void loadCallerSession(HttpServletRequest req)
  {
	
  }  
  /** Scope value globally */
  public void putGlobalValue(String key, Object value)
  {
	if (value != null) {
	  _session.putValue( key, value );
	}
	else {
	  _session.putValue( key, "(null)" );
	}
  }  
  /** Scope value by current servlet */
  public void putLocalValue(String key, Object value)
  {
	putGlobalValue( _this + "." + key, value );
  }  
  /** Scope value by target servlet */
  public void putTargetValue(String key, Object value)
  {
	putGlobalValue( _target + "." + key, value );
  }
  public void removeGlobalValue(String key) 
  {
	_session.removeValue( key );
  }  
  public void removeLocalValue(String key) 
  {
	removeGlobalValue( _this + "." + key );
  }    
  
  public synchronized static void invalidate(String sessionid)
  {
    //gotta remove all of the values
	  Enumeration renum = _instances.keys();
    while(renum.hasMoreElements())
    {
      String key = (String)renum.nextElement();
      if(key.startsWith(sessionid))
        _instances.remove(key);
    }
  }  
  
  //-----------------------------------------------------------
  // Private class methods
  //-----------------------------------------------------------
  private synchronized static void removeMe(SessionSrvc ss)
  {
    invalidate(ss.getSessionId());
//  _instances.remove(ss.getSessionId());
  }  
  /** Accessor */
  public void reset()
  {
//    if (_debug != null)
//      _debug.reset();
  }  
  /** */
  private void setCallingPage(String page)
  {
	_caller = page;
  }  
  //-----------------------------------------------------------
  // Private Instance methods
  //-----------------------------------------------------------
  /** */
  private void setCurrentPage(String page)
  {
	_this = page;
  }  
  /** */
  private void setCurrentRequest(HttpServletRequest req)
  {
	_req = req;
  }  
/*
  public DebugSrvc getDebugSrvc()
  {
	if (_debug == null)
	  return (_debug = DebugSrvc.getService(this));
	else
	  return _debug;
  }
*/
  
  // setters for session
  
  /** */
  public void setTargetPage(String page)
  {
	_target = page;
  }  
  public void setTargetFrame(String frame)
  {
  _targetFrame = frame;
  }  
  public void setTreeReload(boolean reload)
  {
    _reloadTree = reload;
  }
  public boolean reloadTree()
  {
    return _reloadTree;
  }
  
//  // Impls for HttpSessionBinding Listener
//
//  public void valueBound(javax.servlet.http.HttpSessionBindingEvent e) 
//  {
//	// we don't care about the binding event
//  }  
//  public void valueUnbound(javax.servlet.http.HttpSessionBindingEvent e) 
//  {
//	removeMe(this);
//  }  
}