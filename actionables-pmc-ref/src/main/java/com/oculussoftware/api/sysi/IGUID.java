package com.oculussoftware.api.sysi;

/** An external identifier for refering to objects from outside of a repository
* or when exporting an object.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IGUID.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IGUID 
{
  /** Returns whether or not this GUID and the given GUID are equivalent.
  *
  * @param guid the GUID to compare
  */
  public boolean equals(IGUID guid);

  /** Returns the String equivalent of this GUID.
  *
  * @return the String equivalent of this GUID
  */
  public String  toString();
  
  /** Returns the byte[] equivalent of this GUID.
  *
  * @return the byte[] equivalent of this GUID
  */
  public byte[]  getByteArray();
}

