package com.oculussoftware.util;

/**
* $Workfile: MC.java $
* Date:        4/25/00
* Description: MC (mini-CRM) helps get objects with less typing.
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

/*
* $History: MC.java $
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 5/09/00    Time: 4:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/util
 * Added getObj( ) overloads that take string for type instead of IDCONST
 * of the class.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/01/00    Time: 2:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/util
 * getObj( ) now throws exception if type is null.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:31p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/util
 * Mini-CRM: this class reduces syntax for getting objects.
*
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.ui.*;

import java.util.*;

/**
* Helper for getting objects from CRM with less syntax
*/
public class MC
{
  //--------------------------------------------------------------------
  // Private instance variables
  //--------------------------------------------------------------------
  private IObjectContext _context = null;
  
  //--------------------------------------------------------------------
  // Public constructor(s)
  //--------------------------------------------------------------------
  public MC(IObjectContext context)
    throws OculusException
  {
    if (context == null) 
      throw new OculusException("Null or invalid object context.");
    _context = context;
  }
  
  //--------------------------------------------------------------------
  // Public instance methods
  //--------------------------------------------------------------------
  /** 'Native' object getter */
  public IObject getObj(IDCONST type, IIID iid)
    throws OculusException
  {
    if (type == null) throw new OculusException("null type parameter.");
    
    return _context.getCRM().getCompObject(_context,type.getDirName(), iid);
  }
  
  
  public IObject getObj(IDCONST type, long loid)
    throws OculusException
  {
    return getObj(type, _context.getRepository().makeReposID(loid));
  }
  
  public IObject getObj(String strDirName, IIID iid)
    throws OculusException
  {
    if (strDirName == null) throw new OculusException("null type parameter.");
    
    return _context.getCRM().getCompObject(_context, strDirName, iid);
  }

  public IObject getObj(String strDirName, long loid)
    throws OculusException
  {
    return getObj(strDirName, _context.getRepository().makeReposID(loid));
  }
}