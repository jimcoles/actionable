package com.oculussoftware.api.repi.xmeta;

/**
* $Workfile: IXAssoc.java $
* Create Date:  5-12-2000
* Description: Holds extended meta data on class-to-class associations to
*              support dynamic query formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IXAssoc.java $
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added getDisplayName().
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:24a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added accessors for fromColName, toColName, and isRec(ursive).
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:36p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Renamed some methods.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added getIID( ) to support persistence.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:16a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
*/

import com.oculussoftware.api.repi.*;

public interface IXAssoc
{
  public IIID    getIID();
  public String  getDisplayName();
  
  public IXAssoc setFromClass(IXClass cls);
  public IXClass getFromClass( );
  
  public IXAssoc setToClass(IXClass cls);
  public IXClass getToClass();
  
  public IXAssoc setAssocClass(IXClass assocClass);
  public IXClass getAssocClass();
  
  public boolean isM2M();
  public boolean isRec();
  public String  getSyn();
  public String  getFromColName();
	public String  getToColName();
}