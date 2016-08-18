package com.oculussoftware.system;

import com.oculussoftware.api.sysi.*;

import java.util.*;
import java.io.*;
import java.net.*;
import java.text.*;
import java.lang.reflect.*;

/** Concrete impl for IGUID
*/
public class GUID implements IGUID
{
  private static final int GUID_SIZE_BYTES     = 20; // bytes
  private static final int UID_SIZE_BYTES      = 16; // bytes
  private static final int INETADDR_SIZE_BYTES = 4;  // bytes

  private byte[] _baGUID = null;
  private byte[] _baUID = null;
  private byte[] _baIA = null;
	
  //---------------------------------------------------------------------
  // Constructors
  //---------------------------------------------------------------------
  public GUID ( )
	  throws OculusException
  {
	
	// use UID( ) and InetAddress( ) 
	_baGUID = new byte[GUID_SIZE_BYTES];
	
	ByteArrayOutputStream baos = null;
	try {
	  java.net.InetAddress ia = java.net.InetAddress.getLocalHost( );
	  java.rmi.server.UID u = new java.rmi.server.UID();
	  u.write( new DataOutputStream( baos = new ByteArrayOutputStream() ) );
	  _baUID = baos.toByteArray();
	  _baIA = ia.getAddress();
	  //set _baGUID to _baIA
	  for ( int idx = 0; idx < _baIA.length; idx++ ) {
	  _baGUID[idx] = _baIA[idx];
	  }
	  // set uid bytes
	  for ( int idx = 0; idx < _baUID.length; idx++ ) {
	  _baGUID[INETADDR_SIZE_BYTES + idx] = _baUID[idx];
	  }
	}
	catch (UnknownHostException ex) {
	  throw new OculusException("GUID generation process failed due to UnknownHostException.");
	}
	catch (IOException ex2) {
	  throw new OculusException("GUID generation process failed due to IOException.");
	}
  }  
  /** Constructor that takes a byte array.
  *   For use in creating a GUID from a database store byte array.
  */
  public GUID ( byte[] bytes)
	throws OculusException
  {
	if ( bytes.length != GUID_SIZE_BYTES ) {
	  throw new OculusException("Invalid byte array for GUID.  Length not equal to " + GUID_SIZE_BYTES + ".");
	}
	
	_baGUID = new byte[GUID_SIZE_BYTES];
	for ( int idx = 0; idx < GUID_SIZE_BYTES; idx++ ) {
	  _baGUID[idx] = bytes[idx];
	}
  }  
  /** Constructor that takes hex String.  
  *   For use in creating GUID's from export import files.
  */
  public GUID ( String strHex )
	  throws OculusException
  {
	_baGUID = _strHextoByteArr( strHex );
  }  
  //---------------------------------------------------------------------
  // Private methods
  //---------------------------------------------------------------------

  /** Converts byte array (that contains a multiple of 4 elements) to a hex string */
  private String _ByteArrtostrHex( byte[] byt )
  {
  StringBuffer strBuf = new StringBuffer();
  int j,k,l,m,n;
  int wipe_out = 255; //clears sign extension in byte to integer conversion
  //should throw an exception if array length not divisible by 4
  for (int i = 0 ; i < (Array.getLength(byt)/4); i++)
  {
	j=byt[i*4];j=j&wipe_out;
	k=byt[i*4+1];k=k&wipe_out;
	l=byt[i*4+2];l=l&wipe_out;
	m=byt[i*4+3];m=m&wipe_out;
	
	j=j<<24;
	k=k<<16;
	l=l<<8;
	
	n=j|k|l|m;
	
	//pad leading spaces with zeros since toHexString drops them
	StringBuffer temp = new StringBuffer(Integer.toHexString(n));
	while (temp.length() < 8 )
	temp.append("0");
  
	strBuf.append(temp.toString());
  }
  
  return strBuf.toString();
  }  
  /** Converts hex string with an even number of characters to a byte array  */
  private byte[] _strHextoByteArr( String strHex )

  {
 /**
	  String substr;
	byte[] byt = new byte[GUID_SIZE_BYTES];
	int offset = GUID_SIZE_BYTES*2 - strHex.length();
	//pad string with zeroes in front so strLen = 40
	StringBuffer strTemp = new StringBuffer(strHex);
	for (int i=0; i<offset; i++)
		strTemp.insert(0,0);
	strHex = strTemp.toString();
	//convert to byte array
	for (int i=strHex.length(); i>0; i--)
	{
		substr = strHex.substring(i*2-3,i*2-1);
		byt[strHex.length() - i] = (byte)Integer.parseInt(substr,16);	

	}
			

	return byt;	  
*/
	
//Buggy original (odd Str Len boundary problem)
  String substr;
	int strLen = strHex.length();
	int numBytes = strLen/2;
	byte[] byt = new byte[numBytes];
	
	for (int i=0; i<numBytes; i++)
	{
	  substr = strHex.substring(strLen-i*2-2,strLen-i*2);
	  byt[numBytes-i-1] = (byte)Integer.parseInt(substr,16);
	}
  
	return byt;

	/**
	Hack to fix bug
	String substr;
	StringBuffer strTemp = new StringBuffer(strHex);
	int strLen = strHex.length();
	int numBytes = strLen/2;
	byte[] byt = new byte[GUID_SIZE_BYTES];
	int offset = GUID_SIZE_BYTES*2 - strLen;
	for (int i=0; i<offset; i++)
		strTemp.insert(0,0);
	strHex = strTemp.toString();
	strLen = strHex.length();
	numBytes = strLen / 2;
	
	for (int i=0; i<numBytes; i++)
	{
	  substr = strHex.substring(strLen-i*2-2,strLen-i*2);
	  //byt[numBytes-i-1+offset] = (byte)Integer.parseInt(substr,16);
	  byt[numBytes-i-1] = (byte)Integer.parseInt(substr,16);
	}
  
	return byt; */
  }                                  
  public boolean equals(IGUID guid)
  {    
	return ((this.toString()).equals(guid.toString()));
  }  
  //---------------------------------------------------------------------
  // Public methods
  //---------------------------------------------------------------------
  public byte[] getByteArray( ) 
  {
	return _baGUID;
  }  
  public String toString()
  {
	return _ByteArrtostrHex(_baGUID);
  }  
}
