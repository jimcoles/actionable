package com.oculussoftware.api.repi.query;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;

/**
* Filename:    CompOper.java
* Date:        
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public final class BoolOper extends IntEnum implements IOperator
{
  private static Map _hash = new HashMap();
  
  private String _strval;
  
  public static final BoolOper BOOL_OR 			= new BoolOper(0,"or");
  public static final BoolOper BOOL_AND 		= new BoolOper(1,"and");

  /** Private constructor */
  private BoolOper(int s, String stringvalue) 
  {
    super(s);
    _strval = stringvalue;
    _hash.put(new Integer(s), this);
  }
	
  public String getDisplayString()
  { return _strval; }
  
	public static BoolOper getInstance(String type)
		throws ORIOException
	{
		int intType = Integer.parseInt(type);
		return getInstance(intType);		
	}
	
	public static BoolOper getInstance(int i)
		throws ORIOException
	{
		BoolOper retVal = null;
		try {
		  retVal = (BoolOper) _hash.get(new Integer(i));
		}
		catch (ClassCastException ex) {
		  throw new ORIOException(ex);
		}
		if (retVal == null) {
		  throw new ORIOException("Invalid id.");
		}
		return retVal;
	}  
  
  public static BoolOper getXMLInstance(String str) throws OculusException
  {
    if(str.equals("BOOL_AND"))
      return BOOL_AND;
    else if(str.equals("BOOL_OR"))
      return BOOL_OR;
    else
      throw new OculusException("Invalid BoolOper.");
  }//end getInstance  
  
  public static String getStringValue(BoolOper bo) 
  throws OculusException
  {
    if (bo == BOOL_AND)
      return "BOOL_AND";
    else if (bo == BOOL_OR)
      return "BOOL_OR";  
    else
      throw new OculusException("Invalid BoolOper.");  
  
  }
  
}