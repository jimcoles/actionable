package com.oculussoftware.system.sec;
/**
* $Workfile: Permission.java $
* Create Date: 3-24-2000
* Description: An access control permission entity.  As convenience, provides an enum
*              of valid permissions.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: Permission.java $
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 5/20/00    Time: 2:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * BUG00086 Added printDups( ) for easy testing.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 5/09/00    Time: 4:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Added _isRecur instance variable.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:14p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Added duplicate test: getDups( ).
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/23/00    Time: 10:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Now holds a PermissionSet of all permissions constructed.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 4/19/00    Time: 8:39p
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/system/sec
 * backup checkin.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/07/00    Time: 10:51a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/system/sec
 * Initial creation.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/02/00    Time: 3:18p
 * Created in $/Unfinished code/Jim's folder/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/system/sec
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;

import java.util.*;

/** 
* An access control permission entity.  Maintains a static hash of all
* IPermissions by registering them at constuctor time.
*/
public abstract class Permission implements IPermission
{
  //-----------------------------------------------------------------------------
  // Public constants
  //-----------------------------------------------------------------------------

  //-----------------------------------------------------------------------------
  // Private class variables
  //-----------------------------------------------------------------------------
  private static PermissionSet _permHash = PermissionSet.getInstance();
  private static Vector _dups = new Vector();
  
  //-----------------------------------------------------------------------------
  // Public class methods
  //-----------------------------------------------------------------------------
  public static void printDups()
  {
    System.out.println("Permission num duplicates: " + _dups.size());
    Enumeration edups = _dups.elements();
    while (edups.hasMoreElements()) {
      IPermission dup = (IPermission) edups.nextElement();
      System.out.println("ERROR: Look for duplicat Permission id: " + dup.getID());
    }
  }

      
  //-----------------------------------------------------------------------------
  // Private instance variables
  //-----------------------------------------------------------------------------
  private int     _id;
  private IGUID   _guid = null;
  private String  _name = null;
  private String  _shortDesc = null;
  private boolean _isRecur = false;
  
  //-----------------------------------------------------------------------------
  // Private constructor
  //-----------------------------------------------------------------------------
  public Permission(int id, IGUID guid, String name, String desc, boolean isRecur)
  {
    _id = id;
    _guid = guid;
    _name = name;
    _shortDesc = desc;
    _isRecur = isRecur;
    Object old = _permHash.put(id, this);
    if (old != null)
      _dups.add(old);
  }
  
  //-----------------------------------------------------------------------------
  // Public instance methods
  //-----------------------------------------------------------------------------
  public String toString()
  {
    return Integer.toString(_id);
  }
  
  public int getID()
  {
    return _id;
  }
  
  public String getName()
  {
    return _name;
  }
  
  public String getDescription()
  {
    return _shortDesc;
  }
  
  public boolean getIsRecursive() { return _isRecur; }
}