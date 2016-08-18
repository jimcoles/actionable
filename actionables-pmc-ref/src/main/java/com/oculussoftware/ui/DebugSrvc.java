package com.oculussoftware.ui;

import java.text.*;
import java.util.*;
import java.io.*;
import javax.servlet.http.*;

import com.oculussoftware.*;
import com.oculussoftware.util.*;


/**
* Filename:    DebugSrvc.java
* Date:        11-061999
* Description: Provides programmers with an interface to place objects in a place  
* where they can then can then be viewed from the SvtAllDebugger servlet.
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
*
*/
public class DebugSrvc
{
  //---------------------------------
  // Public constants
  //---------------------------------
  // trace and watch sensitivity
  public static final int DBG_OFF  = 0;
  public static final int DBG_LOW  = 1;
  public static final int DBG_MED  = 2;
  public static final int DBG_HIGH = 3;
  // seesion prefix for debug vars.  make sure this is unique.
  public static final String SESS_DEBUG = "DEBUG";
  
  private static final boolean Environmentdebug = true;
  
  //---------------------------------
  // Package-level Factory method
  //---------------------------------
  static DebugSrvc getService(SessionSrvc ss)
  {
    return new DebugSrvc(ss);
  }
  
  
  //---------------------------------
  // Private Class variables
  //---------------------------------
  
  //---------------------------------
  // Private Instance variables
  //---------------------------------
  private SessionSrvc _sess  = null;
  private TimeKeeper  _tkeep = null;
  
  //---------------------------------
  // Constructors
  //---------------------------------
  private DebugSrvc(SessionSrvc ss)
  {
    _sess = ss;
  }
  
  //---------------------------------
  // Public Instance methods
  //---------------------------------

  public void reset()
  {
    Enumeration evars = getSessionDebugVars();
    while (evars.hasMoreElements()) {
      String vname = (String) evars.nextElement();
      getSessionSrvc().removeGlobalValue(vname);
    }
  }  
  // put'ers
  
  public void putWatchData(String label, Object data)
  {
    if (Environmentdebug) {
      _sess.putGlobalValue(SESS_DEBUG + "." + label, data);
    }
  }

  public void putWatchData(String label, boolean data)
  {
    if (Environmentdebug) {
      putWatchData(label, new Boolean(data));
    }
  }

  public void putWatchData(String label, long data)
  {
    if (Environmentdebug) {
      putWatchData(label, new Long(data));
    }
  }

  public void putWatchData(String label, int data)
  {
    if (Environmentdebug) {
      putWatchData(label, new Integer(data));
    }
  }
  
  public long putTrace(String strEvent)
  {
    if (Environmentdebug) {
      return getTimeKeeper().logEvent(strEvent);
    }
    else
      return 0;
  }

  public long putTrace(String strEvent, int level)
  {
    if (Environmentdebug /*&& Environment.getDebugLevel() >= level*/) {
      return getTimeKeeper().logEvent(strEvent);
    }
    else
      return 0;
  }

  // get'ers
  public Enumeration getSessionDebugVars()
  {
    Vector vars = new Vector();
    if (Environmentdebug) {
      String aVars[] = _sess.getValueNames();
      for(int idx = 0; idx < aVars.length; idx++ ) {
        if( aVars[idx].startsWith(SESS_DEBUG)) {
          vars.addElement(aVars[idx]);
        }
      }
    }
    return vars.elements();
  }
  
  public Enumeration getOtherSessionVars()
  {
    Vector vars = new Vector();
    if (Environmentdebug) {
      String aVars[] = _sess.getValueNames();
      for(int idx = 0; idx < aVars.length; idx++ ) {
        if( !aVars[idx].startsWith(SESS_DEBUG)) {
          vars.addElement(aVars[idx]);
        }
      }
    }
    return vars.elements();
  }
  
  public SessionSrvc getSessionSrvc()
  {
    if (Environmentdebug) {
      return _sess;
    }
    else {
      return null;
    }
  }
  
  public TimeKeeper getTimeKeeper()
  {
    if (Environmentdebug) {
      if (_tkeep == null)
        return (_tkeep = new TimeKeeper());
      else
        return _tkeep;
    }
    else
      return null;
  }
  
}