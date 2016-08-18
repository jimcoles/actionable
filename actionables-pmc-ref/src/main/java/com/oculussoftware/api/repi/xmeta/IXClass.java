package com.oculussoftware.api.repi.xmeta;

/**
* $Workfile: IXClass.java $
* Create Date:  5-12-2000
* Description: Holds extended meta data on classes that supports dynamic query
*              formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IXClass.java $
 * 
 * *****************  Version 13  *****************
 * User: Jcoles       Date: 9/09/00    Time: 2:57p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added new overload addAttr(IXClassAttr, ..)
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Issue 2120.  Added get/setIsRoleBased( ) and IsRoleParent( ).
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 8/30/00    Time: 9:16a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 1925 and 2150: Fixing search scope problems.  Added more 'index' table
 * get/sets.
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added get/setIsView( ) and related.
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 8/05/00    Time: 10:16a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 8/03/00    Time: 11:46a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added isConfigurable boolean for attributes.
 * 
 * *****************  Version 7  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 
 * *****************  Version 6  *****************
 * User: Sshafi       Date: 7/25/00    Time: 1:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/19/00    Time: 6:18p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Removed exception throw from addAttr( ) so it can be called from static
 * initializer.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/18/00    Time: 8:56a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Exception throw.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Changed getClassID() to getIID()
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added addAttr( ) method.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:16a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
*/

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;

import com.oculussoftware.util.*;
import java.util.*;

public interface IXClass
{
  public IIID   getIID( );
  public String getTableName( );
  public String getSyn( );
  public String getDisplayName();
  
  public IXClass setIsCommon(boolean bCommon);
  public boolean getIsCommon();
  public IXClass setIsRecursive(boolean bRecursive);
  public boolean getIsRecursive();
  
  /** IndexTable applies to recursive */
  public IXClass setIndexTable(String tablename);
  public String getIndexTable();
  public IXClass setIndexChildColName(String name);
  public String  getIndexChildColName();
  public IXClass setIndexParentColName(String name);
  public String  getIndexParentColName();
  
  public void setClassDefnFilter(IQFilterExpr filter);
  public IQFilterExpr getClassDefnFilter();
  
  /** Retrieves the canned attributes. */
  public List getTableAttrs( );
  public IXClassAttr addAttr(IIID attrid, String colname, String displayname, Primitive type, AttributeKind kind, boolean isConfigurable);
  public IXClassAttr addAttr(IXClassAttr attr, boolean isConfigurable);
  
  public List getKeywordSelectAttrs( );
  public void setPrimaryKeyAttr(IXClassAttr attr);
  public IXClassAttr getPrimaryKeyAttr();
  public IXClassAttr addKeywordSelectAttr(IIID attrid, String colname, String displayname, Primitive type, AttributeKind kind);
  public String addKeywordSelectAttr(String literal);
  
  public IXClass setIsView(boolean isView);
  public boolean getIsView();
  
  public IXClass setExtraClassIDs(IIIDEnum[] classids);
  public List getAllClassIDs();
  
  public IXClass setIsRoleBased(boolean is);
  public boolean getIsRoleBased();
  public IXClass setIsRoleParent(boolean is);
  public boolean getIsRoleParent();
}