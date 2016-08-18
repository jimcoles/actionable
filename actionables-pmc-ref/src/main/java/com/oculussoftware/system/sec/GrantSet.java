package com.oculussoftware.system.sec;
/**
* $Workfile: GrantSet.java $
* Create Date: 4-24-2000
* Description: 
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: GrantSet.java $
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 8/02/00    Time: 12:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 - Added addAll( ) method.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Added size() impl.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/24/00    Time: 11:23p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;

import java.util.*;

/** 
*/
public class GrantSet implements IGrantSet
{
  //------------------------------------------------------------------  
  // Private instance variables
  //------------------------------------------------------------------  
  private Set _theSet = null;

  //------------------------------------------------------------------  
  // Public constructor(s)
  //------------------------------------------------------------------  
  GrantSet(int initsize)
  {
    _theSet = new HashSet(initsize);
  }
    
  //------------------------------------------------------------------  
  // Public instance methods
  //------------------------------------------------------------------  
  public boolean contains(IPermission perm)
  {
    boolean retVal = false;
    if ( _theSet.contains(perm) )
      retVal = true;
    
    return retVal;
  }
  
  public void add(IPermission perm)
  {
    _theSet.add(perm);
  }
  
  public void addAll(IPermission[] perms)
  {
    for(int idx =0; idx < perms.length; idx++ ) { add(perms[idx]); }
  }
  
  public void addAll(IGrantSet grant)
  {
    _theSet.addAll(((GrantSet)grant)._theSet);
  }

  public Iterator iterator()
  {
    return _theSet.iterator();
  }
  
  public int size() { return _theSet.size(); }

}