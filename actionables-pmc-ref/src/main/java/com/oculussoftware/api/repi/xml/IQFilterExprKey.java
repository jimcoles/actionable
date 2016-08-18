package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQFilterExprKey.java $
* Description: The XML object used to persist an IQFilterExpr.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning an IQFilterExpr 
* persisted in a Custom MRD Report, or a Custom Report.
*
* @author Zain Nemazie
*/
public interface IQFilterExprKey extends IXMLable
{
  /**
  * This method returns the left hand side of the expression.  
  * Can be an IQAttrRefKey, IQObjectKey, or an IQFilterExprKey.
  * @return The left hand side of the expression.
  */
  public Object getLeft();

  /**
  * This method returns the right hand side of the expression.  
  * Can be an IQAttrRefKey, IQObjectKey, or an IQFilterExprKey.
  * @return The right hand side of the expression.
  */
  public Object getRight();

  /**
  * This method returns the IOperator for the expression.  Can either
  * be a BoolOper or a CompOper
  * @return The IOperator for the expression.
  */
  public IOperator getOper();

  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;

  /**
  * This method returns true iff the expression uses synonyms.
  * @return true iff the expression uses synonyms.
  */
  public boolean useSynonyms();

  /**
  * This method returns true iff the expression contains a Question (Input).
  * @return true iff the expression contains a question.
  */
  public boolean isQuestion();
}
