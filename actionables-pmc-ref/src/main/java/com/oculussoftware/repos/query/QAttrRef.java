package com.oculussoftware.repos.query;

/**
* $Workfile: QAttrRef.java $
* Create Date: 6/4/2000
* Description: Represents a query filter.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QAttrRef.java $
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/18/00    Time: 8:58a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Change constructor to take a List instead of array.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Using interfaces instead of class references.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;

import com.oculussoftware.repos.xmeta.*;

import java.util.*;

/** Implements IQAttrRef for the PMC query subsystem. */
public class QAttrRef implements IQAttrRef
{
  //-------------------------------------------------------------------
  // Private instance methods
  //-------------------------------------------------------------------
  private IXClassAttr _attr = null;
  private IXAssocChain _assocs = new AssocChain();
  
  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------
  QAttrRef(IXAssoc[] aAssocs, IXClassAttr attr)
  {
    for(int idx=0; idx < aAssocs.length; idx++ ) {
      _assocs.add(aAssocs[idx]);
    }
    _attr = attr;
  }
  
  public QAttrRef(List lAssocs, IXClassAttr attr)
  {
    if (lAssocs != null) {
      _assocs = new AssocChain(lAssocs);
    }
    _attr = attr;
  }

	//-------------------------------------------------------------------
	// Public instance methods
	//-------------------------------------------------------------------
	public IXClassAttr getAttr()
  { 
    return _attr;
  }

	public IXAssocChain getAssocs()
  { 
    return _assocs;
  }
  
/* 7/17/00 JKC Might need to override equals( ) if we let consuming objects
               create their own IQAttrRef's.  If we force them thru a factory
               that uses grabs from a common pool with no dupicates, etc. we 
               might not need this.
               
  public boolean equals(Object obj)
  {
    boolean retVal = false;
  	if (obj instanceof IQAttrRef) {
      retVal = true;
      List lasstest = ((IQAttrRef)obj).getAssocs();
      List lassthis = getAssocs();
      if (lasstest.size() != ) 
      {
        iass = lass.iterator();
        while (iass.hasNext()) {
          
        }
      }
      retVal = ( ((IXAssoc) obj).getIID().getLongValue() == getIID().getLongValue() );
    }
    return retVal;
  }
*/  
}
