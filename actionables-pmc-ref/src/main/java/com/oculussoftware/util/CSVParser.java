package com.oculussoftware.util;

import java.io.*;
import java.util.*;

/**
* Filename:    CSVParser.java
* Date:        5-19-00
* Description: 
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Dan Roberts
* @version 1.2
*/
public final class CSVParser
{	
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */

  public CSVParser()
  {
  }  
  
  //CHECKS TO MAKE SURE THAT EACH ROW HAS THE SAME NUMBER
  //OF COLUMNS AS THE FIRST ROW (LABELS)...
  public static boolean checkData(Vector vData)
  {
    int iSize = 0;
    Vector v = new Vector();
    for(int ndx = 0; ndx < vData.size(); ndx++) {
      v = (Vector)vData.elementAt(ndx);
      if(ndx == 0) iSize = v.size();
      if(v.size() != iSize) {
        v = null;
        vData = null;
        return false;
      }//end if
    }//end for
    v = null;
    vData = null;
    return true;
  }//end checkData

  
  //RETURNS THE FILE DATA AS A VECTOR OF STRINGS...
  public static Vector getDataStrings(InputStreamReader isr)
  throws Exception
  {
    boolean bEOF = false;
    Vector vData = new Vector();
    String s = null;
    
    while(!bEOF) {
      s = getLine(isr);
      if(s.length() == 0) bEOF = true;
      else vData.addElement(s);
    }//end while
    return vData;
  }//end getDataStrings
  
  
  //RETURNS ONE ROW OF THE DATA FILE AS A CSV STRING...
  public static String getLine(InputStreamReader isr)
  throws Exception
  {
    boolean bEOF = false;
    boolean bHR = false;
    StringBuffer sb = null;
    int temp = 0;
    char ch;
    final int EOF = -1;
    final char HR = '\n';
    
    sb = new StringBuffer();
    try {
      do {
        temp = isr.read();
        if(temp == EOF) bEOF = true;
        ch = (char)temp;
        if(ch == HR) bHR = true;
        if(!bHR && !bEOF) sb.append(ch);
      } while(!bHR && !bEOF);
    }//end try
    catch(IOException ioe) {
      throw new Exception("IOException:SvtMIImportMapping.getLine()");
    }//end catch
    if(bEOF) 
      sb.setLength(0);
    return sb.toString().trim();
  }//end getLine
  
  
  //PARSES THE CSV DATA STRINGS, RETURNING A VECTOR OF VECTORS.
  public static Vector parseData(Vector vDataStrings)
  {
    boolean bQuoted = false;
    boolean bIgnoreNextChar = false;
    final char quote = '"';
    final char comma = ',';
    int quoteCount = 0;
    int columnCount = 0;
    String sDirtyRow = null;
    Vector v = new Vector();
    Vector vReturn = new Vector();
    String str = null;
    
    for(int ndx = 0; ndx < vDataStrings.size(); ndx++) {
      sDirtyRow = (String)vDataStrings.elementAt(ndx);
      while(sDirtyRow.length() > 0) { 
        str = "";
        if(sDirtyRow.charAt(0) == quote) 
          bQuoted = true;
        else
          bQuoted = false;
        if(bQuoted) { //string has comma or inner quote(s)
          for(int ndx2 = 1; ndx2 < sDirtyRow.length(); ndx2++) {
            if(bIgnoreNextChar) {
              bIgnoreNextChar = false;
              continue;
            }//end if
            if(sDirtyRow.charAt(ndx2) == quote && ndx2 == sDirtyRow.length() - 1) {
              v.addElement(str);
              if(ndx == 0) columnCount++;
              str = "";
              sDirtyRow = ""; 
              break;
            }//end if
            else if(sDirtyRow.charAt(ndx2) == quote && 
                    sDirtyRow.charAt(ndx2 + 1) == quote) {
              str += quote;
              bIgnoreNextChar = true;
            }//end if
            else if(sDirtyRow.charAt(ndx2) == quote && 
                    sDirtyRow.charAt(ndx2 + 1) == comma) {
              v.addElement(str);
              if(ndx == 0) columnCount++;
              str = "";
              sDirtyRow = sDirtyRow.substring(ndx2 + 2, sDirtyRow.length());
              break;
            }//end else if
            else {
              if(ndx2 == sDirtyRow.length() - 1) {
                v.addElement(str);
                if(ndx == 0) columnCount++;
                str = "";
                sDirtyRow = "";
              }//end if
              else {
                str += sDirtyRow.charAt(ndx2);
              }//end else
            }//end else
          }//end for
        }//end if
        else { //string has no comma or inner quote(s)... just look for delimiter
          for(int ndx3 = 0; ndx3 < sDirtyRow.length(); ndx3++) {
            if(sDirtyRow.charAt(ndx3) == comma) {
              v.addElement(str);
              if(ndx == 0) columnCount++;
              str = "";
              sDirtyRow = sDirtyRow.substring(ndx3 + 1, sDirtyRow.length());
              break;
            }//end if
            else {
              if(ndx3 == sDirtyRow.length() -1) {
                str += sDirtyRow.charAt(ndx3);
                v.addElement(str);
                if(ndx == 0) columnCount++;
                str = "";
                sDirtyRow = "";
                break;
              }//end if
              else {
                str += sDirtyRow.charAt(ndx3);
              }//end else
            }//end else 
          }//end for
        }//end else
      }//end while
      while(v.size() < columnCount) v.addElement("");
      vReturn.addElement(v.clone());
      v.setSize(0);
    }//end for
    v = null;
    return vReturn;
  }//end parseData




}//
