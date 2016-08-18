package com.oculussoftware.repos.xmeta;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.util.*;

/**
* $Workfile: ClassAttrAscMeta.java $
* Create Date:  9-9-2000
* Description: Holds extended meta data on class-to-attribute associations to
*              supports dynamic query formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: ClassAttrAscMeta.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 9/09/00    Time: 3:25p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Issue 2091.
*/
public class ClassAttrAscMeta
{
  //-------------------------------------------------------------------
  // Public constants and static methods
  //-------------------------------------------------------------------
  
  //-------------------------------------------------------------------
  // Private static vars
  //-------------------------------------------------------------------
    
  //-------------------------------------------------------------------
  // Private instanc vars
  //-------------------------------------------------------------------
  private IXClass     _xcls  = null;
  private IXClassAttr _xattr   = null;
  private boolean _bIsUserConfig = false;
  
  //-------------------------------------------------------------------
  // Package-level constructor
  //-------------------------------------------------------------------
  /** Used by most static attribute declarations. */
  public ClassAttrAscMeta(IXClass xcls, IXClassAttr xattr, boolean bIsUserConfig)
  {
    _xcls = xcls;
    _xattr = xattr;
    _bIsUserConfig = bIsUserConfig;
    XMR.addClassAttrAsc(this);
  }
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public boolean getIsUserConfigurable() { return _bIsUserConfig; }
  public IIID getClassID() { return _xcls.getIID(); }
  public IIID getAttrID() { return _xattr.getIID(); }
}