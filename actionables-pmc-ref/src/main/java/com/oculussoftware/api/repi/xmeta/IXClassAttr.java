package com.oculussoftware.api.repi.xmeta;

/*
* $Workfile: IXClassAttr.java $
* Create Date:  5-12-2000
* Description: Holds extended meta data on class-to-attribute associations to
*              supports dynamic query formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IXClassAttr.java $
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 9/09/00    Time: 2:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Removed getClassMeta( ) and getIsUserConfigurable( ).  This class is
 * now an independent attibute which does not know about which class(es)
 * are using it.  User configurability is now an attribute of
 * ClassAttrAsc.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Issue 2120 - added isRole( ).
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 8/03/00    Time: 11:46a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added isConfigurable boolean for attributes.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:26a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added accessor for isExtended, i.e., is this an extended attribute.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:16a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
*/

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;


/**
* Holds extended meta data on class-to-attribute associations to
* supports dynamic query formulation.
*/

public interface IXClassAttr
{
  public String getColName( );
  public IIID getIID();
  public String getDisplayName( );
  public Primitive getPrimType( );
  public AttributeKind getAttrKind( );
  public boolean getIsExtended();
  public boolean getIsRole();
}