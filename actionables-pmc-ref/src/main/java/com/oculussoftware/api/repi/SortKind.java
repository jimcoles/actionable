package com.oculussoftware.api.repi; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/**
* Filename:    SortKind.java
* Date:        4/41/00
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
* BUG01798        Cuihua Zhang    8.23.00     added SortKind.BYFEATID
*/

public final class SortKind extends IntEnum
{
  public final static String KEY = "SORTBY";
  
  public final static SortKind BYDATE = new SortKind(0);
  public final static SortKind BYNAME = new SortKind(1);
  public final static SortKind BYCREATOR = new SortKind(2);
  public final static SortKind BYPRIORITY = new SortKind(3);
  public final static SortKind BYORDER = new SortKind(4);
  public final static SortKind BYPATH = new SortKind(5);
  public final static SortKind BYSCORE = new SortKind(6);
  public final static SortKind BYSTATE = new SortKind(7); 
  public final static SortKind BYSIZE = new SortKind(8);
  public final static SortKind BYFEATID = new SortKind(9);

  public static SortKind getInstance(int d) throws OculusException
  {
    if(d == 0) return BYDATE;
    else if(d == 1) return BYNAME;
    else if(d == 2) return BYCREATOR;
    else if(d == 3) return BYPRIORITY;
    else if(d == 4) return BYORDER;
    else if(d == 5) return BYPATH;
    else if(d == 6) return BYSCORE;
    else if(d == 7) return BYSTATE;
    else if(d == 8) return BYSIZE;
    else if(d == 9) return BYFEATID;

    else
      throw new OculusException("Invalid SortKind.");
  }//end getInstance
  
  /** Private constructor */
  private SortKind(int s) { super(s); }
}