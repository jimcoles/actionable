package com.oculussoftware.api.sysi.license;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ModuleKind.java $
* Description: Integer Enumeration of ModuleKinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* Integer Enumeration of ModuleKinds.
*
* @author Egan Royal
*/
public final class ModuleKind extends IntEnum
{
  /**
  * Accolades value 1.
  */
  public final static ModuleKind ACCOLADES = new ModuleKind(1);
  /**
  * Compass value 1.
  */ 
  public final static ModuleKind COMPASS = new ModuleKind(2);
  /**
  * Conduit value 1.
  */
  public final static ModuleKind CONDUIT = new ModuleKind(3);  
  
  /** Private constructor */
  private ModuleKind(int s) { super(s); } 
     
  /**
  * Takes an int and returns a ModuleKind iff the int is valid.
  *
  * @param val the int value
  * @return The ModuleKind that corresponds to the int value.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid ModuleKind
  */
  public static ModuleKind getInstance(int d) throws OculusException
  {
	  if(d == 1)
	    return ACCOLADES;
	  else if(d == 2)
	    return COMPASS;
	  else if(d == 3)
	 	  return CONDUIT;  
	  else
	    throw new OculusException("Invalid ModuleKind.");
  }//end getInstance 
  
  /**
  * Takes a ModuleKind and returns a String name.
  * @param mk The ModuleKind.
  * @return The name.
  */
  public static String getName(ModuleKind mk)
  {
    if(mk.equals(ACCOLADES))
      return "Accolades";
    else if(mk.equals(COMPASS))
      return "Compass";
    else if(mk.equals(CONDUIT))
      return "Conduit";
    return null;
  }
  
  /**
  * Takes an int and returns a String name.  The method calls getInstance
  * and then getName.
  * @param i The int value of the ModuleKind.
  * @return The name.
  * @exception com.oculussoftware.api.sysi.OculusException 
  * If the int value does not correspond to a valid ModuleKind
  */
  public static String getName(int i) throws OculusException
  {
    return getName(getInstance(i));
  }
}
