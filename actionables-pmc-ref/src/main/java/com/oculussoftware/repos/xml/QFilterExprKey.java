package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/**
* Filename:    QFilterExprKey.java
* Date:        7/18/00
* Description: 
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

public class QFilterExprKey implements IQFilterExprKey
{
	//----------------------------------------------------------------------
	// Private class vars
	//----------------------------------------------------------------------
	
	//----------------------------------------------------------------------
	// Private instance vars
	//----------------------------------------------------------------------
	private IOperator _oper = null;
	private Object _leftArg = null;
	private Object _rightArg = null;
	private boolean _useSyn = false;
	private boolean 	_isQuest = false;
	
  //----------------------------------------------------------------------
  // Constructor(s)
  //----------------------------------------------------------------------
  public QFilterExprKey(IOperator oper, Object left, Object right)
  {
    _oper = oper;
    _leftArg = left;
    _rightArg = right;
  }

  public QFilterExprKey(Node nd)
  throws OculusException
  {
    String strUseSyn = nd.getAttributes().getNamedItem("useSyn").getNodeValue();
    if (strUseSyn!=null)
    {
      if (strUseSyn.equals("true"))
        _useSyn = true;
      else
        _useSyn = false;
    }
    String strIsQuest = nd.getAttributes().getNamedItem("isQuest").getNodeValue();
    if (strIsQuest!=null)
    {
      if (strIsQuest.equals("true"))
        _isQuest = true;
      else
        _isQuest = false;
    }    
    NodeList nl = nd.getChildNodes();
    for (int i=0; i < nl.getLength(); i++)
    {
      Node child = nl.item(i);     
      //if operator
      if (child.getNodeName().equals("Operator"))
      {
        NodeList opNL = child.getChildNodes();
        for (int j=0; j< opNL.getLength(); j++)
        {
          Node opChild = opNL.item(j);
          //if bool
          if (opChild.getNodeName().equals("BoolOper"))
          {

            _oper = BoolOper.getXMLInstance(opChild.getAttributes().getNamedItem("Value").getNodeValue());

          }  
          //if comp
          if (opChild.getNodeName().equals("CompOper"))
          {
            _oper = CompOper.getXMLInstance(opChild.getAttributes().getNamedItem("Value").getNodeValue());
          }
        }
      }
      
      else if (child.getNodeName().equals("LHS"))
      {

        _leftArg = processOperand(child);
      }
      else if (child.getNodeName().equals("RHS"))
      {
        _rightArg = processOperand(child);
      }
    
    }
  }

  //----------------------------------------------------------------------
  // Public Methods
  //----------------------------------------------------------------------

  public Object getLeft() { return _leftArg; }

  public Object getRight() { return _rightArg; }

  public IOperator getOper() { return _oper; }

  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQFilterExprKey");    

    ndIQ.setAttribute("useSyn", _useSyn+"");
    ndIQ.setAttribute("isQuest", _isQuest+"");    
    
    //operator
    Element ndoper = doc.createElement("Operator");
    //if compoper
    if ( _oper instanceof CompOper)
    {
      CompOper oper = (CompOper) _oper;
      Element ndCompoper = doc.createElement("CompOper");
      ndCompoper.setAttribute("Value", CompOper.getStringValue(oper));
      ndoper.appendChild(ndCompoper);
    }
    //else booloper  
    else if ( _oper instanceof BoolOper)
    {
      BoolOper oper = (BoolOper) _oper;
      Element ndBooloper = doc.createElement("BoolOper");
      ndBooloper.setAttribute("Value", BoolOper.getStringValue(oper));   
      ndoper.appendChild(ndBooloper);       
    }    
    
    ndIQ.appendChild(ndoper);
    

    //LHS
    Element ndLeft = doc.createElement("LHS");
    IXMLable left = (IXMLable) _leftArg;
    ndLeft.appendChild(left.toXML(doc));
    ndIQ.appendChild(ndLeft);
    
    //RHS
    Element ndRight = doc.createElement("RHS");
    IXMLable right = (IXMLable) _rightArg;
    ndRight.appendChild(right.toXML(doc));
    ndIQ.appendChild(ndRight);

    return ndIQ;
  }

  //----------------------------------------------------------------------
  // Private Methods
  //----------------------------------------------------------------------
  
  private Object processOperand(Node child)
  throws OculusException
  {
    Object obj = null;
    NodeList nl = child.getChildNodes();
    for (int i=0; i < nl.getLength(); i++)
    {
      Node curr = nl.item(i);
      if (curr.getNodeName().equals("IQFilterExprKey"))
      {
        obj = new QFilterExprKey(curr);
      }
      else if (curr.getNodeName().equals("IQAttrRefKey"))
      {
        obj = new QAttrRefKey(curr);
      }
      else if (curr.getNodeName().equals("IQObjectKey"))
      {
        obj = new QObjectKey(curr);
      }      
    }
    return obj;
  }

  //----------------------------------------------------------------------
  // Constructor(s)
  //----------------------------------------------------------------------
  public QFilterExprKey(IOperator oper, Object left, Object right, boolean useSyn)
  {
    this(oper,left,right);
    _useSyn = useSyn;
  }

  public boolean useSynonyms()
  {
    return _useSyn;
  }

  //----------------------------------------------------------------------
  // Constructor(s)
  //----------------------------------------------------------------------
  public QFilterExprKey(IOperator oper, Object left, Object right, boolean useSyn, boolean isQuest)
  {
    this(oper,left,right);
    _useSyn = useSyn;
    _isQuest = isQuest;
  }

  public boolean isQuestion()
  {
    return _isQuest;
  }
}
