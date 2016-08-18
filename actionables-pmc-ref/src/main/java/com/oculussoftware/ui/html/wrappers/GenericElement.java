package com.oculussoftware.ui.html.wrappers;

import com.oculussoftware.api.ui.html.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.ui.html.wrappers.*;

import java.lang.*;
import java.util.*;

/**
* Filename:    GenericElement.java
* Date:        02.11.00
* Description: Represents a generic HTML element
*              Handles start and end tags,
*              start tag parameters, and a list of
*              generic elements
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.0
*/
public class GenericElement implements IGenericElement
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * ---             Jachin Cheng    02.15.00    Added addElements(...)
  * ---             Egan Royal      02.22.00    Added BrowserKind
  * ---             Jachin Cheng    02.24.00    Changed to implement interface
  * ---             Egan Royal      03.16.00    Changed HashMap to Vector to maintain the order of the params
  * ---             Saleem Shafi    03/26/00    Added getHTMLObject, getParentObject methods.
  *
  */
  protected String       _elementType    = null;                 // element type/name
  protected boolean      _hasElements    = true;                 // default true
  private   Vector       _params         = new Vector();         // vector of parameters
  private   ArrayList    _elementList    = new ArrayList();      // list of elements
  protected char         _quoteType      = '\"';                 // default quotation char
  protected BrowserKind  _bkind          = BrowserKind.ALL;      // default ALL 
  protected boolean      _hasStringValue = false;                // default false
  protected StringBuffer _stringvalue    = new StringBuffer(""); // default empty-string
  protected boolean      _isClosable     = true;                 // default true
  
  protected IGenericElement _parent     = null;
  protected IHTML       _html           = null;
    
  private static final String EXC_ELEMENT  = "This element cannot contain elements";  
  
  
  //----------------------------- Public Constructors -------------------------
  /** Basic default constructor */
  GenericElement()
  {
  }  
    
  //-------------------------------- Mutator Methods ---------------------------    
    
  /** 
   * Sets a single parameter given the parameter's name and value 
   * param value may be "", e.g. NORESIZE
   */
  protected void setParam(String paramName, String paramVal)
  {
    boolean found = false;
    for(int i = 0; i < _params.size(); i++)
    {
     if(((ParamNode)_params.elementAt(i)).name.equals(paramName))
     {
       ((ParamNode)_params.elementAt(i)).value = paramVal;
       found = true;
       break;
     }
    }
    if(!found)
      _params.addElement(new ParamNode(paramName, paramVal));
  }
  
  /** Returns parameter value given parameter name */    
  protected String getParam(String paramName)
  {
    for(int i = 0; i < _params.size(); i++)
    {
     if(((ParamNode)_params.elementAt(i)).name.equals(paramName))
       return ((ParamNode)_params.elementAt(i)).value;
    }
    return null;
  }// end getParam()
  
  private class ParamNode
  {
    String name;
    String value;
    public ParamNode(String n, String v)
    { name = n; value = v; }
  }
  
  /** 
   * Sets a single parameter given the parameter's name and value 
   * param value may be "", e.g. NORESIZE
   */
  protected void appendParam(String paramName, String paramVal)
  {
    boolean found = false;
    for(int i = 0; i < _params.size(); i++)
    {
     if(((ParamNode)_params.elementAt(i)).name.equals(paramName))
     {
       ((ParamNode)_params.elementAt(i)).value += paramVal;
       found = true;
       break;
     }//end if
    }//end for
    if(!found)
      _params.addElement(new ParamNode(paramName, paramVal));
  }
  
  public void init() { ; }
    
  /** Adds a single GenericElement to _elementVector */
  protected void addElement(IGenericElement ge)
  {
    ge.setHTMLObject(getHTMLObject());
    ge.setParentObject(this);
		ge.setBrowserKind(getBrowserKind());
    ge.init();
    _elementList.add(ge);
    
    getHTMLObject().added(ge);
  }
    
  /** Adds multiple GenericElements to _elementVector */
  protected void addElements(ArrayList newElementsList) throws OculusException
  {   
    if(_hasElements)
    {
      // iterates through new element list, adds to _elementList
      Iterator itr = newElementsList.iterator();
      while (itr.hasNext())
        addElement((IGenericElement)itr.next());
    }//end if
    else throw new OculusException(EXC_ELEMENT);
  }  
  
  /**
  *
  */
  public IGenericElement setBrowserKind(BrowserKind bk) { _bkind = bk; return this; }
 
  public void setHTMLObject(IHTML obj) { _html = obj; }
  public IHTML getHTMLObject() { return _html; }
  
  public void setParentObject(IGenericElement obj) { _parent = obj; }
  public IGenericElement getParentObject() { return _parent; }

 
   //------------------------------- Accessor Methods --------------------------- 
    
  /**
   * Returns the HTML tags for this element, including start tag with parameters, 
   * contained elements, and end tag.
   */
  public String toString()
  {
    StringBuffer sbuff = new StringBuffer();
    sbuff.append(this.getStartTag());
    if (_hasElements)   
      sbuff.append(this.getElements());
    if(_hasStringValue)
      sbuff.append(this.getStringValue());
    if(_isClosable)
      sbuff.append(this.getEndTag());
    return sbuff.toString(); 
  }// 
    
  /** Returns entire HTML start tag */  
  public String getStartTag()
  {
   StringBuffer sbuff = new StringBuffer();
   
   sbuff.append("\n<");                       // builds element name
   sbuff.append(_elementType); 
   sbuff.append(" "); 
   sbuff.append(this.getParams());          // inserts tag params
   sbuff.append(">");                   // closes tag
   if(_elementType.equals("SCRIPT"))
     sbuff.append("<!--\n");
   return sbuff.toString();
  }// end getStartTag()

  /** Returns entire HTML end tag */     
  public String getEndTag()
  {
    StringBuffer sbuff = new StringBuffer();
    if(_elementType.equals("SCRIPT"))
      sbuff.append("\n// -->");
    sbuff.append("\n</"); 
    sbuff.append(_elementType); 
    sbuff.append(">"); 
    return sbuff.toString();
  }// end getEndTag()

  /** Returns HTML for all params */  
  public String getParams()
  {    
    StringBuffer sbuff = new StringBuffer();
    // iterate through params
    for(int i = 0; i < _params.size(); i++)
    {
      String paramName = ((ParamNode)_params.elementAt(i)).name;
      sbuff.append(paramName);
      String paramValue = ((ParamNode)_params.elementAt(i)).value;
      if (paramValue != null && paramValue.length() != 0) 
      {
        sbuff.append("=");
        sbuff.append(_quoteType);     
        sbuff.append(paramValue);
        sbuff.append(_quoteType);  
        sbuff.append(" ");
      }//end if
      else
        sbuff.append(" ");    
    }//end while
    return sbuff.toString();   
  }//end getParams() 

  /** Returns HTML for all elements.  note: recursive call to toString() */ 
  public String getElements()
  {
    StringBuffer sbuff = new StringBuffer(); 
    // iterate through element list
    Iterator itr = _elementList.iterator();
    while (itr.hasNext())
    {     
      sbuff.append(((GenericElement)itr.next()).toString());
      // selects next element, casts to Generic Element, gets HTML string
//       sbuff.append((((GenericElement)itr.next()).setBrowserKind(_bkind)).toString());
    }
    return sbuff.toString();    
  }// end getElements
  
  public void clear()
  { _elementList.clear(); }
  
  /**
  *
  */
  public BrowserKind getBrowserKind() { return _bkind; }
  
  /**
  *
  */
  public String getStringValue() { return _stringvalue.toString(); }

  
}//end GenericElement class definition