package com.oculussoftware.api.repi;
/**
* $Workfile: ViewType.java $
* Create Date: 5/11/2000
* Description: Enumerates types of views for DataView
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*/

/* $History: ViewType.java $
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 8/08/00    Time: 5:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * setting up docreport
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 8/07/00    Time: 11:36a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * rework the inheritance
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 7/14/00    Time: 2:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Custom Reports
 * 
 * *****************  Version 2  *****************
 * User: Znemazie     Date: 5/30/00    Time: 10:04a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * * ------------    Zain Nemazie    5/30/00     Adding getInstance for
 * creation 
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:39a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Inital create.
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* ------------    Zain Nemazie    5/30/00     Adding getInstance for creation                
* 
*/

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

public final class ViewType extends IntEnum
{
  public final static ViewType SORTTREEQUERY   = new ViewType(0); 
  public final static ViewType SEARCHQUERY     = new ViewType(1);
  public final static ViewType CUSTOMREPORT    = new ViewType(2);
  public final static ViewType ADVANCEDSEARCH  = new ViewType(3);
  public final static ViewType DOCUMENTREPORT  = new ViewType(4);
  /** Private constructor */
  private ViewType(int s) { super(s); }

  
  public static ViewType getInstance(int d) throws OculusException
  {
    if(d == 0)
      return SORTTREEQUERY;
    else if(d == 1)
      return SEARCHQUERY;
    else if(d == 2)
      return CUSTOMREPORT;  
    else if(d == 3)
      return ADVANCEDSEARCH; 
    else if(d == 4)
      return DOCUMENTREPORT; 
    else
      throw new OculusException("Invalid ViewType.");
  }//end getInstance
   
}