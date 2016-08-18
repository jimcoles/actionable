package com.oculussoftware.repos.xmeta;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.util.*;
/**
* $Workfile: AssocMeta.java $
* Create Date:  5-12-2000
* Description: Holds extended meta data on class-to-class associations to
*              support dynamic query formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: AssocMeta.java $
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 8/30/00    Time: 9:19a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 1925 and 2150: Fixing search scope problems
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added display name.
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 7/18/00    Time: 9:00a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Overrided Object.equals( ).
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:29a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added isRec, from and to Col names; rearranged constructors to funnel
 * through base constructor.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * added getSyn( ); call to register assoc with xmr.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Change id representation.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Made classes to extend appropriate interface from api.repi.xmeta.
 * Changed all (java) class references to interface refs.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Made to implement interfaces from ...api.repi.xmeta.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 6/30/00    Time: 11:43a
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
 * Backup.
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
public class AssocMeta implements IXAssoc
{
  //-------------------------------------------------------------------
  // Private static vars
  //-------------------------------------------------------------------

  //-------------------------------------------------------------------
  // Private instanc vars
  //-------------------------------------------------------------------
  private IIID _iid;
  private String _name = null;
  private IXClass _fromClass  = null;
  private IXClass _toClass  = null;
  private IXClass _assocClass  = null;
  private String _fromColName = null;
  private String _toColName = null;
  private boolean _m2m = false;
  private boolean _rec = false;
  private String _syn = "";
  
  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------
  
  
  /** M-2-1 association. */
  public AssocMeta(IIID id, IXClass fromClass, IXClass toClass, String fromColName, String displayName)
  {
    _iid = id;
    _name = displayName;
    setFromClass(fromClass);
    setToClass(toClass);
    _fromColName = fromColName;
    XMR.addAssoc(this);
  }
  
  /** M-2-M association with a related class. */
  public AssocMeta(IIID id, IXClass fromClass, IXClass toClass, IXClass assocClass, String fromColName, String toColName, String displayName)
  {
    this(id, fromClass, toClass, fromColName, displayName);
    setAssocClass(assocClass);
    _toColName = toColName;
    _m2m = true;
  }
  
  /** Recursive associations. */
  public AssocMeta(IIID id, IXClass theClass, String parentColName, String displayName)
  {
    this(id, theClass, theClass, parentColName, displayName);
    _rec = true;
    _syn = "parrec";
    
  }
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public IIID getIID() {	return _iid; }
  public String getDisplayName() { return _name; }
  
  public IXAssoc setFromClass(IXClass cls)
  {
    _fromClass = cls;
    return this;
  }
  public IXAssoc setToClass(IXClass cls)
  {
    _toClass = cls;
    return this;
  }
  public IXAssoc setAssocClass(IXClass assocClass)
  {
    _assocClass = assocClass;
    return this;
  }
  
  public IXClass getAssocClass() { return _assocClass; }
  
  
  public boolean isM2M() { return _m2m; }
  public boolean isRec() { return _rec; }
  public IXClass getFromClass( ) { return _fromClass; }
  public IXClass getToClass() { return _toClass; }
	public String getSyn()
	{
		return _syn;
	}

	public String getFromColName() {return _fromColName;}
	public String getToColName() {return _toColName;}

	public boolean equals(Object obj)
	{
    boolean retVal = false;
		if (obj instanceof IXAssoc) {
      retVal = ( ((IXAssoc) obj).getIID().getLongValue() == getIID().getLongValue() );
    }
    return retVal;
	}
}