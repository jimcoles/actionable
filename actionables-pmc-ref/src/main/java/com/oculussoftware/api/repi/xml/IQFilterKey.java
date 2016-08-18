package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.repi.xml.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/*
* $Workfile: IQFilterKey.java $
* Description: The XML object used to persist an IQFilter.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/

/** 
* Defines the methods necessary for returning an IQFilter 
* persisted in a Custom MRD Report, or a Custom Report.
*
* @author Zain Nemazie
*/
public interface IQFilterKey extends IXMLable
{
  /**
  * This method takes an IQFilterExprKey and sets it as the expression.
  * @param expr The IQFilterExprKey.
  */
  public void setExpr(IQFilterExprKey expr);
  
  /**
  * This method returns the IQFilterExprKey.
  * @return The IQFilterExprKey.
  */
  public IQFilterExprKey getExpr();
  
  /**
  * This method XMLizes and returns this XML Element.
  * @return The XML Element.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Element toXML(Document doc)
  throws OculusException;    
  
}