package com.oculussoftware.util;

import java.util.*;

/**
* Filename:    BitMaskUtil.java
* Date:        5-8-00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public final class BitMaskUtil
{	
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
	/**
	*
	*/
	public static int getIntValue(boolean blnArray[])
	{
		if(blnArray.length > 31) return -1;
		int intPlace = 1, intValue = 0;
		for(int i = 0; i < blnArray.length; ++i)
		{
			if(blnArray[i]) intValue = intValue + intPlace;
			intPlace = intPlace * 2;
		}//end for
		return intValue;
	}//end getIntValue
			
	/**
	*
	*/
	public static int getIntValue(int intOldValue, int intIndex, boolean blnValue)
	{
		boolean blnArray[] = getAllBitValues(intOldValue);
		blnArray[intIndex] = blnValue;
		return getIntValue(blnArray);
	}//end getIntValue
		
	/**
	*
	*/
	public static boolean getBitValue(int intValue, int intIndex)
	{
		boolean blnArray[] = getAllBitValues(intValue);
		if(blnArray.length > intIndex) return blnArray[intIndex];
		else return false;
	}//end getBitValue
		
	/**
	*
	*/
	public static boolean[] getAllBitValues(int intValue)
	{
		boolean blnArray[] = new boolean[31];
		for(int i = 0; i < blnArray.length; ++i)
		{
			if(intValue % 2 == 1) blnArray[i] = true;
			intValue = intValue / 2;
		}//end for
		return blnArray;
	}//end getAllBitValues
}//