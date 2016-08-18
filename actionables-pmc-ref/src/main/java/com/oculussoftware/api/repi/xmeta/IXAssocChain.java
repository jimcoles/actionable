package com.oculussoftware.api.repi.xmeta;

import java.util.*;
/**
* $Workfile: IXAssocChain.java $
* Create Date:  7-11-2000
* Description: A continuous sequence of associations in the class association
*              graph.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IXAssocChain.java $
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:25a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Made this extend java.util.List.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:36p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added removeLast( ).
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:16a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
*/
public interface IXAssocChain extends List
{
  public void add(IXAssoc assoc);
  public void removeLast();
}