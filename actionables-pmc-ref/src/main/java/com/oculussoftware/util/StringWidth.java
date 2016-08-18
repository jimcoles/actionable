//StringWidth

package com.oculussoftware.util;

/**
* Filename:    StringWidth.java
* Date:        8-20-1999
* Description: 
*
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

public class StringWidth
{
	private static int[] intArray = new int[130];
	private static int space = 1;//number of units between characters

	static
	{
		intArray[Character.getNumericValue('a')] = 5;
		intArray[Character.getNumericValue('A')] = 7;
		intArray[Character.getNumericValue('b')] = 5;
		intArray[Character.getNumericValue('B')] = 6;
		intArray[Character.getNumericValue('c')] = 5;
		intArray[Character.getNumericValue('C')] = 7;
		intArray[Character.getNumericValue('d')] = 5;
		intArray[Character.getNumericValue('D')] = 7;
		intArray[Character.getNumericValue('e')] = 5;
		intArray[Character.getNumericValue('E')] = 6;
		intArray[Character.getNumericValue('f')] = 3;
		intArray[Character.getNumericValue('F')] = 6;
		intArray[Character.getNumericValue('g')] = 5;
		intArray[Character.getNumericValue('G')] = 7;
		intArray[Character.getNumericValue('h')] = 5;
		intArray[Character.getNumericValue('H')] = 6;
		intArray[Character.getNumericValue('i')] = 2;
		intArray[Character.getNumericValue('I')] = 2;
		intArray[Character.getNumericValue('j')] = 3;
		intArray[Character.getNumericValue('J')] = 4;
		intArray[Character.getNumericValue('k')] = 5;
		intArray[Character.getNumericValue('K')] = 5;
		intArray[Character.getNumericValue('l')] = 2;
		intArray[Character.getNumericValue('L')] = 5;
		intArray[Character.getNumericValue('m')] = 7;
		intArray[Character.getNumericValue('M')] = 7;
		intArray[Character.getNumericValue('n')] = 5;
		intArray[Character.getNumericValue('N')] = 6;
		intArray[Character.getNumericValue('o')] = 5;
		intArray[Character.getNumericValue('O')] = 7;
		intArray[Character.getNumericValue('p')] = 5;
		intArray[Character.getNumericValue('P')] = 6;
		intArray[Character.getNumericValue('q')] = 5;
		intArray[Character.getNumericValue('Q')] = 7;
		intArray[Character.getNumericValue('r')] = 4;
		intArray[Character.getNumericValue('R')] = 7;
		intArray[Character.getNumericValue('s')] = 5;
		intArray[Character.getNumericValue('S')] = 6;
		intArray[Character.getNumericValue('t')] = 3;
		intArray[Character.getNumericValue('T')] = 6;
		intArray[Character.getNumericValue('u')] = 5;
		intArray[Character.getNumericValue('U')] = 6;
		intArray[Character.getNumericValue('v')] = 5;
		intArray[Character.getNumericValue('V')] = 6;
		intArray[Character.getNumericValue('w')] = 7;
		intArray[Character.getNumericValue('W')] = 9;
		intArray[Character.getNumericValue('x')] = 5;
		intArray[Character.getNumericValue('X')] = 6;
		intArray[Character.getNumericValue('y')] = 5;
		intArray[Character.getNumericValue('Y')] = 6;
		intArray[Character.getNumericValue('z')] = 5;
		intArray[Character.getNumericValue('Z')] = 6;
		intArray[Character.getNumericValue('0')] = 5;
		intArray[Character.getNumericValue('1')] = 4;
		intArray[Character.getNumericValue('2')] = 5;
		intArray[Character.getNumericValue('3')] = 5;
		intArray[Character.getNumericValue('4')] = 5;
		intArray[Character.getNumericValue('5')] = 5;
		intArray[Character.getNumericValue('6')] = 5;
		intArray[Character.getNumericValue('7')] = 5;
		intArray[Character.getNumericValue('8')] = 5;
		intArray[Character.getNumericValue('9')] = 5;
	}//end static

	/**
	*
	*/
	public static int getWidth(String str)
	{
		int intWidth = 0;
		for(int i = 0; i < str.length(); ++i)
		{
			int intUnits = 5;
			if(Character.getNumericValue(str.charAt(i)) != -1 && Character.isLetterOrDigit(str.charAt(i)))
				intUnits = intArray[Character.getNumericValue(str.charAt(i))];
			intWidth = intWidth + space + intUnits;
		}//end for
		return intWidth;
	}//end getLength
	
	/**
	*
	*/	
	public static String truncate(String str, int intTargetWidth)
	{
		int intWidth = 0, j = 0, intUnits = 5; 
		intTargetWidth = intTargetWidth - intUnits - 8;//width of elipsis
		for(int i = 0; i < str.length() && intWidth < intTargetWidth; ++i)
		{
			intUnits = 5;//if the character is not registered
			if(Character.isLetterOrDigit(str.charAt(i)) && Character.getNumericValue(str.charAt(i)) != -1)
				intUnits = intArray[Character.getNumericValue(str.charAt(i))];
			intWidth = intWidth + space + intUnits;
			j=i;
		}//end for
		if(str.length() != j+1)
			return str.substring(0, j+1)+"...";
		else
			return str;
	}//end truncate
		
	/**
	*
	*/
	public static String getHTMLSpaces(double intWidth)
	{
		StringBuffer strbufRV = new StringBuffer("");
		while(intWidth > 3)
		{
			strbufRV.append("&nbsp;");
			intWidth = intWidth - 4;
		}//end while
		return strbufRV.toString();
	}//end getHTMLSpaces
	
	/**
	*
	*/
	public static String getSpaces(double intWidth)
	{
		StringBuffer strbufRV = new StringBuffer("");
		while(intWidth > 3)
		{
			strbufRV.append(" ");
			intWidth = intWidth - 4;
		}//end while
		return strbufRV.toString();
	}//end getSpaces
}//end StringLength 