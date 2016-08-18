package com.oculussoftware.api.repi.xml;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.bus.xmeta.*;

import java.util.*;
import java.io.*;

/**
* $Workfile: IQSelectItemKey.java $
* Create Date: 6/26/2000
* Description: An element of an IQSelect clause of an IQuery.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQSelectItemKey.java $
 * 
 * *****************  Version 2  *****************
 * User: Eroyal       Date: 9/25/00    Time: 2:33p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xml
 * javadoc
 * 
 * *****************  Version 1  *****************
 * User: Znemazie     Date: 8/08/00    Time: 9:19a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xml
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/24/00    Time: 9:02a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
*/

/** 
* An element of an IQSelect clause of an IQuery.
*
* @author Zain Nemazie
*/
public interface IQSelectItemKey extends IXMLable
{

  /** 
  * This method returns either a String or an IQAttrRefKey. 
  * @return Either a String or an IQAttrRefKey.
  */
  public Object getAttr();

  /** 
  * This method return the String alias for the Display Attribute. 
  * @return The String alias for the Display Attribute if specified, null otherwise.
  */
  public String getAlias();
}
