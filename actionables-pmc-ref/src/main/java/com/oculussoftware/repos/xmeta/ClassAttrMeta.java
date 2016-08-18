package com.oculussoftware.repos.xmeta;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.util.*;

/**
* $Workfile: ClassAttrMeta.java $
* Create Date:  5-12-2000
* Description: Holds extended meta data on class-to-attribute associations to
*              supports dynamic query formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: ClassAttrMeta.java $
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 9/09/00    Time: 3:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Related to 2091 - removed getClass( ) and getIsUserConfig..( ).  No
 * longer those things in constructor.  Overriding Object.equals( ) to use
 * the IIID.
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Issue 2120 - added isRole( ).
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 8/03/00    Time: 11:48a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added isConfigurable boolean for attributes.
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 7/19/00    Time: 6:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Removed excpetion throw from one of the  constructors so it can be use
 * in static init.
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 7/18/00    Time: 9:00a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Constructor changes.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:30a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added isExt(ended) state variable.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Made classes to extend appropriate interface from api.repi.xmeta.
 * Changed all (java) class references to interface refs.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Made to implement interfaces from ...api.repi.xmeta.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 6/30/00    Time: 11:43a
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
 * Backup.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Keeping map of instances to support lookup.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:40a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Inital create.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/14/00    Time: 10:58p
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
*/
public class ClassAttrMeta implements IXClassAttr
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
  private String        _colName = null;
  private IIID          _attrid  = null;
  private String        _displayname = null;
  private Primitive     _primtype = null;
  private AttributeKind _attrkind = null;
  private boolean       _bIsExt = false;
  private boolean       _bIsRole = false;
  
  //-------------------------------------------------------------------
  // Package-level constructor
  //-------------------------------------------------------------------
  /** Used by most static attribute declarations. */
  public ClassAttrMeta(IIID attrid, String colname, String displayname, Primitive type, AttributeKind kind)
  {
    _attrid = attrid;
    _colName = colname;
    _displayname = displayname;
    _primtype = type;
    _attrkind = kind;
    if (kind != AttributeKind.CANNED)
      _bIsExt = true;
    // add to list
    XMR.addAttr(this);
  }
  
  /** Used for custom attributes. */
  public ClassAttrMeta(IRAttribute attr)
    throws OculusException
  {
    this(attr.getIID(), null, attr.getName(), attr.getPrimitive(), attr.getAttrKind());
  }
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public String getColName( ) { return _colName;}
  public IIID getIID() { return _attrid; }
  public String getDisplayName( ) { return _displayname;}
  public Primitive getPrimType( ) { return _primtype;}
  public AttributeKind getAttrKind( ) { return _attrkind; }
  public boolean getIsExtended() { return _bIsExt; }
  public boolean getIsRole( ) { return _bIsRole; }
  /** Override Object.equals( ) */
	public boolean equals(Object obj)
	{
    boolean retVal = false;
    if(obj instanceof IXClassAttr) {
      IXClassAttr xca = (IXClassAttr) obj;
      retVal = (this.getIID().getLongValue() == xca.getIID().getLongValue());
    }
    return retVal;
	}
  //-------------------------------------------------------------------
  // Package methods
  //-------------------------------------------------------------------
  void setIsExt( ) { _bIsExt = true; }
  void setIsRole( ) { _bIsRole = true; }
  

}