/*******

This is a helper class for MarketInput. It is used to display a list if marketinputs
Holds all the relevant info required for the display. This makes access to the list
a *LOT* faster. 

*/

package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;

import java.sql.*;
import java.util.*;

public class InputRow
{
  
 long iid; 
 long vid; 
 long baseid; 
 long stateid; 
 long linkid; 
 String name;
 boolean file;
 boolean link;
 boolean disc;
 java.sql.Timestamp date;
 
 public boolean hasFileAttached() { return file;}
 public boolean hasLinkAttached() { return link;}
 public boolean hasMessageAttached() { return disc;}
 public long getIIDLong() {return iid; }
 public IIID getIID() {return new SequentialIID(iid); }
 public long getVisibleID() {return vid; }
 public long getLinkIDLong() {return linkid; }
 public IIID getLinkIID() throws OculusException {return new SequentialIID(linkid); }
 public String getName() {return name; }
 public String getNature()
  {
    if (baseid == IDCONST.STANDARDINPUT.getLongValue()) 
      return "Standard";      
    else if (baseid == IDCONST.ARTICLEINPUT.getLongValue()) 
      return "Article";
    else if (baseid == IDCONST.QUESTIONINPUT.getLongValue()) 
      return "Q & A";
    else if (baseid == IDCONST.WINLOSSINPUT.getLongValue()) 
      return "Win/Loss";    
    else if (baseid == IDCONST.IMPORTEDINPUT.getLongValue()) 
      return "Imported";
    else if (baseid == IDCONST.SUMMARYINPUT.getLongValue()) 
      return "Summary";    
    else
      return ""; 
  }
  
  public InputRow(long l1,long l2,long l3,long l4,long l5,String name, boolean b1, boolean b2, boolean b3, java.sql.Timestamp date)
  {
    this.iid = l1; 
    this.vid = l2; 
    this.baseid = l3; 
    this.linkid = l4; 
    this.stateid= l5;    
    this.name = name; 
    this.file = b1;
    this.link = b2;
    this.disc = b3;    
    this.date= date;    
    
  }

  public InputRow(){}
  
  public InputRow setIID(IIID id) {iid = id.getLongValue(); return this;}
  public InputRow setName(String name) {this.name = name; return this;}
  public InputRow setVisibleID(long id) {vid = id; return this;}
  public InputRow setLinkID(long id) {linkid = id; return this;}
  public InputRow setBaseClassID(long id) {baseid = id; return this;}
  public InputRow setStateID(long id) {stateid = id; return this;}
  public InputRow setCreationDate(java.sql.Timestamp date) {this.date = date; return this;}
  public InputRow hasFileAttached(boolean id) {file = id; return this;}
  public InputRow hasLinkAttached(boolean id) {link = id; return this;}
  public InputRow hasMessageAttached(boolean id) {disc = id; return this;}
  
  public boolean equals(Object o)
  {
    if (o == null) 
      return false; 
    if (toString().equals(o.toString()))
      return true;
    else
      return false;              
  }
  
  public String toString() { return ""+iid;}
  
  public boolean isWinLoss() throws OculusException
   
  {
    
    if (baseid == IDCONST.WINLOSSINPUT.getLongValue()) 
        return true;
     else
       return false;   
  
  }
   
  public boolean isArticleInput() 
   throws OculusException
  {
    if (baseid == IDCONST.ARTICLEINPUT.getLongValue()) 
        return true;
     else
       return false;   
  }    
  
  public boolean isStandardInput() 
   throws OculusException
  {
    if (baseid == IDCONST.STANDARDINPUT.getLongValue()) 
        return true;
     else
       return false;   
  }    
  
   public boolean isSummaryInput() 
   throws OculusException
  {
      if (baseid == IDCONST.SUMMARYINPUT.getLongValue()) 
        return true;
     else
       return false;   
  }    
  
  public boolean isImportedInput() 
   throws OculusException
  {
   
   if (baseid == IDCONST.IMPORTEDINPUT.getLongValue()) 
        return true;
     else
       return false;   
    
  }    
  
  public boolean isQuestionInput() 
   throws OculusException
  {
   
   if (baseid == IDCONST.QUESTIONINPUT.getLongValue()) 
        return true;
     else
       return false;   
    
  }    

   public String getSubject() { return name;}
   public java.sql.Timestamp getCreationDate() {return date;} 
     public boolean isOpen() throws OculusException
  {
   
   if (isClosed()) 
     return false; 
   else
     return true;
       
  }
  
  public boolean isClosed() throws OculusException
  {
   boolean ret = false;
   if (isArticleInput())
    {
       if (stateid == IDCONST.ARTICLEINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
     
   if (isStandardInput())
    {
       if (stateid == IDCONST.STANDARDINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
     
   if (isQuestionInput())
    {
       if (stateid == IDCONST.QUESTIONINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
    if (isWinLoss())
    {
       if (stateid == IDCONST.WINLOSSINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
    if (isImportedInput())
    {
       if (stateid == IDCONST.IMPORTEDINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
       if (isSummaryInput())
    {
       if (stateid == IDCONST.SUMMARYINPUTCLOSED.getLongValue()) 
         ret = true;
       else
         ret = false;
    }
    
    return ret;
  }
}