package com.oculussoftware.api.ui.html;

import com.oculussoftware.util.StringEnum;
import com.oculussoftware.api.sysi.OculusException;

/*
* $Workfile: ValidationKind.java $
* Description: String Enumeration of Validation Kinds.
*                                                      
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* 
*/

/** 
* String Enumeration of Validation Kinds.
*
* @author Egan Royal
*/
public final class ValidationKind extends StringEnum
{
	/**
  * value "NOBLANK"
  */
	public final static ValidationKind NOBLANK    = new ValidationKind("NOBLANK");
	
  /**
  * value "NOSELECT"
  */
  public final static ValidationKind NOSELECT   = new ValidationKind("NOSELECT");
	
  /**
  * value "ALPHANUM"
  */
  public final static ValidationKind ALPHANUM   = new ValidationKind("ALPHANUM");
	
  /**
  * value "ALPHA"
  */
  public final static ValidationKind ALPHA   = new ValidationKind("ALPHA");	
	
  /**
  * value "NUMERIC"
  */
  public final static ValidationKind NUMERIC    = new ValidationKind("NUMERIC");
	
  /**
  * value "EMAIL"
  */
  public final static ValidationKind EMAIL      = new ValidationKind("EMAIL");
	
  /**
  * value "DATE"
  */
  public final static ValidationKind DATE       = new ValidationKind("DATE");
	
  /**
  * value "NOTAG"
  */
  public final static ValidationKind NOTAG      = new ValidationKind("NOTAG");
	
  /**
  * value "FLOAT"
  */
  public final static ValidationKind FLOAT      = new ValidationKind("FLOAT");
	
  /**
  * value "NOTAG1"
  */
  public final static ValidationKind NOTAG1     = new ValidationKind("NOTAG1");
	
  /**
  * value "MAXINTLENGTH"
  */
  public final static ValidationKind MAXINTLENGTH = new ValidationKind("MAXINTLENGTH");
	
  /**
  * value "NOSPACE"
  */
  public final static ValidationKind NOSPACE    = new ValidationKind("NOSPACE");
	
  /**
  * value "PRODENUM"
  */
  public final static ValidationKind PRODENUM    = new ValidationKind("PRODENUM");
	
  /**
  * value "BIGTEXT"
  */
  public final static ValidationKind BIGTEXT    = new ValidationKind("BIGTEXT");
	
  /**
  * value "DUMMYSELECT"
  */
  public final static ValidationKind DUMMYSELECT    = new ValidationKind("DUMMYSELECT");
	
  /**
  * value "RADIO"
  */
  public final static ValidationKind RADIO    = new ValidationKind("RADIO");
	
  /**
  * value "HTTP"
  */
  public final static ValidationKind HTTP    = new ValidationKind("HTTP");
	
  /**
  * value "NOZERO"
  */
  public final static ValidationKind NOZERO    = new ValidationKind("NOZERO");
	
  /**
  * value "NOQUOTES"
  */
  public final static ValidationKind NOQUOTES    = new ValidationKind("NOQUOTES");
  
  /**
  * value "LARGETEXT"
  */
  public final static ValidationKind LARGETEXT    = new ValidationKind("LARGETEXT");

	
  /** Private constructor */
  private ValidationKind(String s) { super(s); }
}