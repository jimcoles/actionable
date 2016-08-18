package com.oculussoftware.api.repi;

/*
* $Workfile: IRDataView.java $
* Create Date: 5/11/00
*
* Description: Represents a stored query.
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author:  J. Coles
* @version: 1.2
*
* $History: IRDataView.java $
 * 
 * *****************  Version 11  *****************
 * User: Eroyal       Date: 8/08/00    Time: 5:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * setting up docreport
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 7/31/00    Time: 11:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * commented out the retrieve and the getSQL methods
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 7/26/00    Time: 6:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * added yet again. more functionality to the custom reports
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 7/24/00    Time: 7:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 7-24-00
 * 
 * added more functionality to Custom Report
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 7/22/00    Time: 3:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 7-22-00
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 7/19/00    Time: 6:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 7-19-00
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 7/17/00    Time: 6:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 7-17-00
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 7/14/00    Time: 7:02p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 7/14/00    Time: 2:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Custom Reports
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Only commented out methods.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:39a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Initial create.
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.license.ModuleKind;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.repi.xml.*;
//import java.util.*;

/** 
*/
public interface IRDataView extends IRModelElement
{  
  public IUser getOwner() throws OculusException;
  public IIID getOwnerIID() throws OculusException;
  public IXMLTree getXML( ) throws OculusException;
  public ViewType getViewType( ) throws OculusException;
  public AccessKind getAccessKind() throws OculusException;
//  public IDCONST getScopeType() throws OculusException;
  public ModuleKind getModuleKind() throws OculusException;
  
//  public IRDataView setScopeType(IDCONST idc) throws OculusException;
  public IRDataView setXML(IXMLTree xml) throws OculusException;
  public IRDataView setOwner(IUser user) throws OculusException;
  public IRDataView setOwnerIID(IIID owneriid) throws OculusException;
  public IRDataView setAccessKind(AccessKind ak) throws OculusException;
  public IRDataView setModuleKind(ModuleKind mk) throws OculusException;
  
//  public IDataSet retrieve() throws OculusException;
//  public String getSQL() throws OculusException; 
}