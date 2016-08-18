package com.oculussoftware.system;

/**
* Filename:    Security.java
* Date:        7-30-1999
* Description: 
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*
*/
public class Security 
{
	private static int[] key;
	
	/**
	* valid int keys are from -31 to +1
	*/
	static
	{
		key = new int[33]; int j = 0;
		for(int i = -31; i < 2; ++i)
		{ key[j] = i; ++j; }
	}//end static
	
	/**
	* decrypt
	*/
	public static String decrypt(String str)
	{
		char[] chars = getChars(str);
		for(int i = 0; i < chars.length; ++i)
			if(chars[i] != ' ')
				chars[i] = (char)((int)chars[i] - key[i % 33]);
		return new String(chars);
	}//end decrypt
	/**
	* encrypt
	*/
	public static String encrypt(String str)
	{
		char[] chars = getChars(str);
		for(int i = 0; i < chars.length; ++i)			
			if(chars[i] != ' ')			
				chars[i] = (char)((int)chars[i] + key[i % 33]);
		return new String(chars);
	}//end encrypt
	/**
	* getChars takes a String and returns a char[]
	*/
	private static char[] getChars(String str)
	{
		char[] chars = new char[str.length()];
		for(int i = 0; i < chars.length; ++i)
			chars[i] = str.charAt(i);
		return chars;
	}//end getChars	
}//end class Security
