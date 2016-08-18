package com.oculussoftware.repos.xmeta;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.util.*;

import java.util.*;
/**
* $Workfile: AssocChain.java $
* Create Date:  7-11-2000
* Description: A continuous sequence of associations in the class association
*              graph.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: AssocChain.java $
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:28a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Made to extend Vector.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Made classes to extend appropriate interface from api.repi.xmeta.
 * Changed all (java) class references to interface refs.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:26a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
*/
public class AssocChain extends Vector implements IXAssocChain
{
  //-------------------------------------------------------------------
  // Private static vars
  //-------------------------------------------------------------------

  //-------------------------------------------------------------------
  // Private instance vars
  //-------------------------------------------------------------------
  
  //-------------------------------------------------------------------
  // Private constructor(s)
  //-------------------------------------------------------------------
  
  
  public AssocChain()
  {
    super();
  }
  
  public AssocChain(List list)
  {
    super(list);
  }
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public void add(IXAssoc assoc) {
    super.add(assoc);
  }
  public void removeLast()
  {
  	remove(size()-1);
  }
}