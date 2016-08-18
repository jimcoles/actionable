package com.oculussoftware.system.sec;
/**
* $Workfile: PermissionGrant.java $
* Create Date: 3-20-2000
* Description: Represents a grant of a permission to an accessor.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import java.util.*;

/** 
* 
*/
public class PermissionGrant implements IPermissionGrant
{
  //-----------------------------------------------------------------------------
  // Private instance variables
  //-----------------------------------------------------------------------------
  private IIID _parObjectId  = null;
  private IPermission  _perm = null;
  private IIID _accessorId   = null;
  private boolean _isFixed = false;
  
  //-----------------------------------------------------------------------------
  // Public constructor(s)
  //-----------------------------------------------------------------------------
  public PermissionGrant(IIID contextObjId, IPermission perm, IIID accessorId, boolean isFixed)
  {
    _parObjectId = contextObjId;
    _perm = perm;
    _accessorId = accessorId;
    _isFixed = isFixed;
  }
  
  //-----------------------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------------------

  public boolean isFixed(){ return _isFixed;}
  public IPermission getPermission(){ return _perm;}
  public IIID getContextObjID(){return _parObjectId; }
  public IIID getAccessorID(){return _accessorId;}

  //-----------------------------------------------------------------------------
  // Private instance methods
  //-----------------------------------------------------------------------------
}