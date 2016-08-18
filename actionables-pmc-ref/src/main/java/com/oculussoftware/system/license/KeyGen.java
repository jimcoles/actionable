package com.oculussoftware.system.license;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.sysi.license.*;
/**
* Filename:    KeyGen.java
* Date:        12-7-1999
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.1
*/
public final class KeyGen implements IKeyGen
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */
  
	//Indices
	private static final int idx_Product   = 5;
	private static final int idx_Version   = 12;
	private static final int idx_Shifter   = 1;
	private static final int idx_CustID1   = 7;
	private static final int idx_CustID2   = 10;
	private static final int idx_CustID3   = 15;
	private static final int idx_CustID4   = 0;
	private static final int idx_CustID5   = 4;
	private static final int idx_NumUsers1 = 14;
	private static final int idx_NumUsers2 = 9;
	private static final int idx_ExpMonth  = 2;
	private static final int idx_ExpDay    = 6;
	private static final int idx_ExpYear1  = 8;
	private static final int idx_ExpYear2  = 3;
	private static final int idx_CheckSum1 = 11;
	private static final int idx_CheckSum2 = 13;
	
  private static KeyGen _gen;
  
  /**
  * private constructor
  */
  private KeyGen()
  {
  }
  
  /**
  * package level for a reason
  */
  static IKeyGen getInstance()
  {
    if(_gen == null)
      _gen = new KeyGen();
    return _gen;
  }
  
	//Accessors
	
	public ModuleKind getModuleKind(String strShiftedKey) throws OculusException
	{
    validate(strShiftedKey);
		return ModuleKind.getInstance(decodeProduct(unshift(strShiftedKey, idx_Shifter)));
	}//end getProduct
	
	public int getVersion(String strShiftedKey) throws InvalidKeyException
	{
    validate(strShiftedKey);
		return decodeVersion(unshift(strShiftedKey, idx_Shifter));
	}//end getVersion 
	
	public long getCustomerID(String strShiftedKey) throws InvalidKeyException
	{
    validate(strShiftedKey);
		return decodeCustomerID(unshift(strShiftedKey, idx_Shifter));
	}//end getCustomerID
	
	public int getNumberOfUsers(String strShiftedKey) throws InvalidKeyException
	{
    validate(strShiftedKey);
		return decodeNumberOfUsers(unshift(strShiftedKey, idx_Shifter));
	}//end getNumberOfUsers
	
	public int getExpirationMonth(String strShiftedKey) throws InvalidKeyException
	{
    validate(strShiftedKey);
		return decodeExpirationMonth(unshift(strShiftedKey, idx_Shifter));
	}//end getExpirationMonth
	
	public int getExpirationDay(String strShiftedKey) throws InvalidKeyException
	{
    validate(strShiftedKey);
		return decodeExpirationDay(unshift(strShiftedKey, idx_Shifter));
	}//end getExpirationDay
	
	public int getExpirationYear(String strShiftedKey) throws InvalidKeyException
	{
    validate(strShiftedKey);
		return decodeExpirationYear(unshift(strShiftedKey, idx_Shifter));
	}//end getExpirationYear
	
	//Validation
	
	public synchronized boolean validate(String strShiftedKey) throws InvalidKeyException
	{
		boolean blnRV = true;
		String strUnshiftedKey = unshift(strShiftedKey, idx_Shifter);
		if(!verifyCheckSum(strUnshiftedKey))
			throw new InvalidKeyException("Invalid checksum");
		else if(!verifyProduct(strUnshiftedKey))
			throw new InvalidKeyException("Invalid ModuleKind");
		else if(!verifyVersion(strUnshiftedKey))
			throw new InvalidKeyException("Invalid Version");
		else if(!verifyCustomerID(strUnshiftedKey))
			throw new InvalidKeyException("Invalid CustomerID");
		else if(!verifyNumUsers(strUnshiftedKey))
			throw new InvalidKeyException("Invalid Number of Users");
    else if(!verifyDate(strUnshiftedKey))
			throw new InvalidKeyException("Invalid Date");
		return blnRV;
	}//end validate
	
	//Creation
	
	public String createKey(ModuleKind mk, int intVersion, int intShifter, long lngCustID, int intNumUsers, int intMonth, int intDay, int intYear)
	   throws InvalidKeyException
  {
		StringBuffer strbufKey = new StringBuffer("****************");
		strbufKey.setCharAt(idx_Product,toBase62(mk.getIntValue()));
		strbufKey.setCharAt(idx_Version,toBase62(intVersion));
		strbufKey.setCharAt(idx_Shifter,toBase62(intShifter));
		String strCustID = toBase62(lngCustID, 5);
		strbufKey.setCharAt(idx_CustID1, strCustID.charAt(0));
		strbufKey.setCharAt(idx_CustID2, strCustID.charAt(1));
		strbufKey.setCharAt(idx_CustID3, strCustID.charAt(2));
		strbufKey.setCharAt(idx_CustID4, strCustID.charAt(3));
		strbufKey.setCharAt(idx_CustID5, strCustID.charAt(4));
		String strNumUsers = toBase62(intNumUsers, 2);
		strbufKey.setCharAt(idx_NumUsers1, strNumUsers.charAt(0));
		strbufKey.setCharAt(idx_NumUsers2, strNumUsers.charAt(1));
		strbufKey.setCharAt(idx_ExpMonth,toBase62(intMonth));
		strbufKey.setCharAt(idx_ExpDay,toBase62(intDay));
		String strYear = toBase62(intYear, 2);
		strbufKey.setCharAt(idx_ExpYear1, strYear.charAt(0));
		strbufKey.setCharAt(idx_ExpYear2, strYear.charAt(1));
		String strCheckSum = toBase62(calculateCheckSum(strbufKey.toString()) ,2);
		strbufKey.setCharAt(idx_CheckSum1, strCheckSum.charAt(0));
		strbufKey.setCharAt(idx_CheckSum2, strCheckSum.charAt(1));
		String strNewKey = shift(strbufKey.toString(), idx_Shifter);
    validate(strNewKey);
    return strNewKey;
	}//end createKey
	
	//Private Parts
	
	private boolean verifyCheckSum(String strUnshiftedKey)
	{
		return (strUnshiftedKey.length() == 16 && calculateCheckSum(strUnshiftedKey) == decodeCheckSum(strUnshiftedKey));
	}//end verifyCheckSum
	
	private boolean verifyProduct(String strUnshiftedKey)
	{
	 	return (decodeProduct(strUnshiftedKey) <= 30);
	}//end verifyProduct
	
	private boolean verifyVersion(String strUnshiftedKey)
	{
	 	return (decodeVersion(strUnshiftedKey) <= 30);
	}//end verifyVersion
	
	private boolean verifyCustomerID(String strUnshiftedKey)
	{
	 	long lngCustID = decodeCustomerID(strUnshiftedKey);
		long lngTemp = lngCustID / 7;
		lngTemp = lngTemp * 7;
		return (lngTemp == lngCustID);
	}//end verifyCustomerID
	
	private boolean verifyNumUsers(String strUnshiftedKey)
	{
	 	return (decodeNumberOfUsers(strUnshiftedKey) <= 1001);
	}//end verifyNumUsers
	
	private boolean verifyDate(String strUnshiftedKey)
	{
	 	int intMonth = decodeExpirationMonth(strUnshiftedKey);
		int intDay = decodeExpirationDay(strUnshiftedKey);
		int intYear = decodeExpirationYear(strUnshiftedKey);
		boolean blnYear = (intYear >= 1970 && intYear <= 2100);
		boolean blnDay = (intDay >= 1 && intDay <= 31);
		boolean blnMonth = (intMonth >= 1 && intMonth <= 12);
		return (blnYear && blnDay && blnMonth);
	}//end verifyDate
	
	private int decodeProduct(String strUnshiftedKey)
	{
		return toBase10(strUnshiftedKey.charAt(idx_Product));
	}//end getProduct
	
	private int decodeVersion(String strUnshiftedKey)
	{
		return toBase10(strUnshiftedKey.charAt(idx_Version));
	}//end getVersion 
	
	private long decodeCustomerID(String strUnshiftedKey)
	{
		return toBase10(""+strUnshiftedKey.charAt(idx_CustID1)+strUnshiftedKey.charAt(idx_CustID2)+strUnshiftedKey.charAt(idx_CustID3)+strUnshiftedKey.charAt(idx_CustID4)+strUnshiftedKey.charAt(idx_CustID5));
	}//end getCustomerID
	
	private int decodeNumberOfUsers(String strUnshiftedKey)
	{
		return (int)toBase10(""+strUnshiftedKey.charAt(idx_NumUsers1)+strUnshiftedKey.charAt(idx_NumUsers2));
	}//end getNumberOfUsers
	
	private int decodeExpirationMonth(String strUnshiftedKey)
	{
		return toBase10(strUnshiftedKey.charAt(idx_ExpMonth));
	}//end getExpirationMonth
	
	private int decodeExpirationDay(String strUnshiftedKey)
	{
		return toBase10(strUnshiftedKey.charAt(idx_ExpDay));
	}//end getExpirationDay
	
	private int decodeExpirationYear(String strUnshiftedKey)
	{
		return (int)toBase10(""+strUnshiftedKey.charAt(idx_ExpYear1)+strUnshiftedKey.charAt(idx_ExpYear2));
	}//end getExpirationYear
	
	private int decodeCheckSum(String strUnshiftedKey)
	{
		return (int)toBase10(""+strUnshiftedKey.charAt(idx_CheckSum1)+strUnshiftedKey.charAt(idx_CheckSum2));
	}//end getExpirationYear
	
	private int calculateCheckSum(String strUnshiftedKey)
	{
		int intRV = toBase10(strUnshiftedKey.charAt(idx_Product));
		intRV += toBase10(strUnshiftedKey.charAt(idx_Version));
		intRV += toBase10(strUnshiftedKey.charAt(idx_Shifter));
		intRV += toBase10(strUnshiftedKey.charAt(idx_CustID1));
		intRV += toBase10(strUnshiftedKey.charAt(idx_CustID2));
		intRV += toBase10(strUnshiftedKey.charAt(idx_CustID3));
		intRV += toBase10(strUnshiftedKey.charAt(idx_CustID4));
		intRV += toBase10(strUnshiftedKey.charAt(idx_CustID5));
		intRV += toBase10(strUnshiftedKey.charAt(idx_NumUsers1));
		intRV += toBase10(strUnshiftedKey.charAt(idx_NumUsers2));
		intRV += toBase10(strUnshiftedKey.charAt(idx_ExpMonth));
		intRV += toBase10(strUnshiftedKey.charAt(idx_ExpDay));
		intRV += toBase10(strUnshiftedKey.charAt(idx_ExpYear1));
		intRV += toBase10(strUnshiftedKey.charAt(idx_ExpYear2));
		return intRV;
	}//end calculateCheckSum
	
	private String shift(String str, int intShiftKeyIdx)
	{
		String strRV = "";
		int intShiftKey = toBase10(str.charAt(intShiftKeyIdx));
		for(int i = str.length()-1; i >= 0; --i)
		{
			if(i != intShiftKeyIdx)
				strRV = toBase62((toBase10(str.charAt(i)) + intShiftKey) % 62) + strRV;
			else
				strRV = str.charAt(i) + strRV;
		}//end for
		return strRV;
	}//end shift
	
	private String unshift(String str, int intShiftKeyIdx)
	{
		String strRV = "";
		int intShiftKey = toBase10(str.charAt(intShiftKeyIdx));
		intShiftKey = 62 - intShiftKey;
		for(int i = str.length()-1; i >= 0; --i)
		{
			if(i != intShiftKeyIdx)
				strRV = toBase62((toBase10(str.charAt(i)) + intShiftKey) % 62) + strRV;
			else
				strRV = str.charAt(i) + strRV;
		}//end for
		return strRV;
	}//end unshift
	
	private String toBase62(long lngL, int intTargetLength)
	{
		String strRV = "";
		while(lngL != 0)
		{
			strRV = toBase62((int)(lngL % 62)) + strRV;
			lngL = lngL / 62;
		}//end while
		while(strRV.length() < intTargetLength)
			strRV = "0" + strRV;
		return strRV;
	}//end toBase62
	
	private long toBase10(String str)
	{
		int lngRV = 0, mult = 1;
		for(int i = str.length()-1; i >= 0; --i)
		{
			lngRV = lngRV + mult * toBase10(str.charAt(i));
			mult = mult * 62;
		}//end for
		return lngRV;
	}//end toBase10
	
	private char toBase62(int intI)
	{
		char chrRV = '0';
		if(intI == 0)
			chrRV = '0';
		else if(intI == 1)
			chrRV = '1';
		else if(intI == 2)
			chrRV = '2';
		else if(intI == 3)
			chrRV = '3';
		else if(intI == 4)
			chrRV = '4';
		else if(intI == 5)
			chrRV = '5';
		else if(intI == 6)
			chrRV = '6';
		else if(intI == 7)
			chrRV = '7';
		else if(intI == 8)
			chrRV = '8';
		else if(intI == 9)
			chrRV = '9';
		else if(intI == 10)
			chrRV = 'a';
		else if(intI == 11)
			chrRV = 'b';
		else if(intI == 12)
			chrRV = 'c';
		else if(intI == 13)
			chrRV = 'd';
		else if(intI == 14)
			chrRV = 'e';
		else if(intI == 15)
			chrRV = 'f';
		else if(intI == 16)
			chrRV = 'g';
		else if(intI == 17)
			chrRV = 'h';
		else if(intI == 18)
			chrRV = 'i';
		else if(intI == 19)
			chrRV = 'j';
		else if(intI == 20)
			chrRV = 'k';
		else if(intI == 21)
			chrRV = 'l';
		else if(intI == 22)
			chrRV = 'm';
		else if(intI == 23)
			chrRV = 'n';
		else if(intI == 24)
			chrRV = 'o';
		else if(intI == 25)
			chrRV = 'p';
		else if(intI == 26)
			chrRV = 'q';
		else if(intI == 27)
			chrRV = 'r';
		else if(intI == 28)
			chrRV = 's';
		else if(intI == 29)
			chrRV = 't';
		else if(intI == 30)
			chrRV = 'u';
		else if(intI == 31)
			chrRV = 'v';
		else if(intI == 32)
			chrRV = 'w';
		else if(intI == 33)
			chrRV = 'x';
		else if(intI == 34)
			chrRV = 'y';
		else if(intI == 35)
			chrRV = 'z';
		else if(intI == 36)
			chrRV = 'A';
		else if(intI == 37)
			chrRV = 'B';
		else if(intI == 38)
			chrRV = 'C';
		else if(intI == 39)
			chrRV = 'D';
		else if(intI == 40)
			chrRV = 'E';
		else if(intI == 41)
			chrRV = 'F';
		else if(intI == 42)
			chrRV = 'G';
		else if(intI == 43)
			chrRV = 'H';
		else if(intI == 44)
			chrRV = 'I';
		else if(intI == 45)
			chrRV = 'J';
		else if(intI == 46)
			chrRV = 'K';
		else if(intI == 47)
			chrRV = 'L';
		else if(intI == 48)
			chrRV = 'M';
		else if(intI == 49)
			chrRV = 'N';
		else if(intI == 50)
			chrRV = 'O';
		else if(intI == 51)
			chrRV = 'P';
		else if(intI == 52)
			chrRV = 'Q';
		else if(intI == 53)
			chrRV = 'R';
		else if(intI == 54)
			chrRV = 'S';
		else if(intI == 55)
			chrRV = 'T';
		else if(intI == 56)
			chrRV = 'U';
		else if(intI == 57)
			chrRV = 'V';
		else if(intI == 58)
			chrRV = 'W';
		else if(intI == 59)
			chrRV = 'X';
		else if(intI == 60)
			chrRV = 'Y';
		else if(intI == 61)
			chrRV = 'Z';	
		return chrRV;
	}//end toBase62
	
	private int toBase10(char chr)
	{
		int intRV = 0;
		if(chr == '0')
			intRV = 0;
		else if(chr == '1')
			intRV = 1;
		else if(chr == '2')
			intRV = 2;
		else if(chr == '3')
			intRV = 3;
		else if(chr == '4')
			intRV = 4;
		else if(chr == '5')
			intRV = 5;
		else if(chr == '6')
			intRV = 6;
		else if(chr == '7')
			intRV = 7;
		else if(chr == '8')
			intRV = 8;
		else if(chr == '9')
			intRV = 9;
		else if(chr == 'a')
			intRV = 10;
		else if(chr == 'b')
			intRV = 11;
		else if(chr == 'c')
			intRV = 12;
		else if(chr == 'd')
			intRV = 13;
		else if(chr == 'e')
			intRV = 14;
		else if(chr == 'f')
			intRV = 15;
		else if(chr == 'g')
			intRV = 16;
		else if(chr == 'h')
			intRV = 17;
		else if(chr == 'i')
			intRV = 18;
		else if(chr == 'j')
			intRV = 19;
		else if(chr == 'k')
			intRV = 20;
		else if(chr == 'l')
			intRV = 21;
		else if(chr == 'm')
			intRV = 22;
		else if(chr == 'n')
			intRV = 23;
		else if(chr == 'o')
			intRV = 24;
		else if(chr == 'p')
			intRV = 25;
		else if(chr == 'q')
			intRV = 26;
		else if(chr == 'r')
			intRV = 27;
		else if(chr == 's')
			intRV = 28;
		else if(chr == 't')
			intRV = 29;
		else if(chr == 'u')
			intRV = 30;
		else if(chr == 'v')
			intRV = 31;
		else if(chr == 'w')
			intRV = 32;
		else if(chr == 'x')
			intRV = 33;
		else if(chr == 'y')
			intRV = 34;
		else if(chr == 'z')
			intRV = 35;
		else if(chr == 'A')
			intRV = 36;
		else if(chr == 'B')
			intRV = 37;
		else if(chr == 'C')
			intRV = 38;
		else if(chr == 'D')
			intRV = 39;
		else if(chr == 'E')
			intRV = 40;
		else if(chr == 'F')
			intRV = 41;
		else if(chr == 'G')
			intRV = 42;
		else if(chr == 'H')
			intRV = 43;
		else if(chr == 'I')
			intRV = 44;
		else if(chr == 'J')
			intRV = 45;
		else if(chr == 'K')
			intRV = 46;
		else if(chr == 'L')
			intRV = 47;
		else if(chr == 'M')
			intRV = 48;
		else if(chr == 'N')
			intRV = 49;
		else if(chr == 'O')
			intRV = 50;
		else if(chr == 'P')
			intRV = 51;
		else if(chr == 'Q')
			intRV = 52;
		else if(chr == 'R')
			intRV = 53;
		else if(chr == 'S')
			intRV = 54;
		else if(chr == 'T')
			intRV = 55;
		else if(chr == 'U')
			intRV = 56;
		else if(chr == 'V')
			intRV = 57;
		else if(chr == 'W')
			intRV = 58;
		else if(chr == 'X')
			intRV = 59;
		else if(chr == 'Y')
			intRV = 60;
		else if(chr == 'Z')
			intRV = 61;
		return intRV; 	
	}//end toBase62
}//end KeyUtil