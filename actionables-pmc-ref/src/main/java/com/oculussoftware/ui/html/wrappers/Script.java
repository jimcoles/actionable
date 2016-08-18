package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.ui.*;
import java.lang.*;
import java.util.*;

/**
* Filename:    Script.java
* Date:        02.22.00
* Description: Represents a generic Script element
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class Script extends GenericElement implements IScript
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ----            Jachin Cheng    02.24.00    Changed to implement interface
  *
  */
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  public Script()
  {
    _hasStringValue = true;
    _elementType = "SCRIPT";
    _hasElements = false;
  }
  
  //-------------------------------- Mutator Methods ---------------------------    
    
  public IScript setCharset(String paramVal) { setParam("CHARSET", paramVal); return this; }
  public IScript setDefer() { setParam("DEFER",""); return this; }  
  public IScript setEvent(String paramVal) { setParam("EVENT",paramVal); return this; }
  public IScript setFor(String paramVal) { setParam("FOR", paramVal); return this; }
  public IScript setLanguage(String paramVal) { setParam("LANGUAGE",paramVal); return this; }
  public IScript setSrc(String paramVal)
	{
		if (getHTMLObject().getBrowserKind().equals(BrowserKind.NETSCAPE))
		{
			setStringValue(ScriptMgr.getInstance().getScript(paramVal));
		}
		else
			setParam("SRC",paramVal);
		return this;
	}
  public IScript setType(String paramVal) { setParam("TYPE",paramVal); return this; }
  public IScript setStringValue(String sval) { _stringvalue = new StringBuffer(""+sval); return this; }
  public IScript appendStringValue(String sval) { _stringvalue.append(sval).append("\n"); return this; }//
  
  
  /** Returns entire HTML start tag */  
  public String getStartTag()
  {
   StringBuffer sbuff = new StringBuffer();
   
   sbuff.append("\n<");                       // builds element name
   sbuff.append(_elementType); 
   sbuff.append(" "); 
   sbuff.append(this.getParams());          // inserts tag params
   sbuff.append(">");                   // closes tag
   sbuff.append("<!--\n");
   return sbuff.toString();
  }// end getStartTag()

  /** Returns entire HTML end tag */     
  public String getEndTag()
  {
    StringBuffer sbuff = new StringBuffer();
    sbuff.append("\n// -->");
    sbuff.append("\n</"); 
    sbuff.append(_elementType); 
    sbuff.append(">"); 
    return sbuff.toString();
  }// end getEndTag()
  
}//end Script class def