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
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    ConceptFolderColl.java
* Date:        2/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Alok Pota 
* @version 1.2



Returns the concepts for a given folder

*/


public class QuestionInputList extends MarketInputFolderList implements IBusinessObjectList
{
  
  public QuestionInputList() throws OculusException
  {
    super();
  }

  protected QuestionInputList(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }
  
 
  protected String getClassName() { return "FolderInputLink"; }


  protected String getLoadQuery()
    throws ORIOException
  { 
    
    return "SELECT "
          +" fil.OBJECTID as filOBJECTID,fil.GUID as filGUID,fil.FOLDERID as filFOLDERID,fil.MARKETINPUTID as filMARKETINPUTID, "
          +" fil.CLASSID as filCLASSID,fil.STATEID as filSTATEID, fil.CREATORID as filCREATORID,fil.ACCESSID as filACCESSID, "
          +" fil.REACTIONID as filREACTIONID,fil.CREATIONDATE as filCREATIONDATE, fil.DELETESTATE as filDELETESTATE, "
          +" fil.FILEATTACHED as filFILEATTACHED,fil.LINKATTACHED as filLINKATTACHED, fil.DISCUSSATTACHED as filDISCUSSATTACHED, "  
          +" mi.OBJECTID as miOBJECTID,mi.GUID as miGUID, mi.BASECLASSID as miBASECLASSID, "          
          +" mi.SUBJECT as miSUBJECT, mi.USERCOMMENT as miUSERCOMMENT, mi.IMPORTANCE as miIMPORTANCE, mi.SRCUSERID as miSRCUSERID, mi.CLASSID as miCLASSID,mi.STATEID as miSTATEID, "
          +" mi.CREATORID as miCREATORID,mi.ACCESSID as miACCESSID, "
          +" mi.VISIBLEID as miVISIBLEID,mi.CREATIONDATE as miCREATIONDATE, mi.DELETESTATE as miDELETESTATE, "
          +" mi.FILEATTACHED as miFILEATTACHED,mi.LINKATTACHED as miLINKATTACHED, mi.DISCUSSATTACHED as miDISCUSSATTACHED "  
          +" FROM MARKETINPUT mi LEFT OUTER JOIN FOLDERINPUTLINK fil ON fil.MARKETINPUTID=mi.OBJECTID "
          +" WHERE mi.CLASSID="+getIID().getLongValue()
          +" AND fil.DELETESTATE<>"+DeleteState.DELETED.getIntValue()+" AND mi.DELETESTATE<>"+DeleteState.DELETED.getIntValue();

    
  }
    /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
 

   public IRCollection setSort(Comparator sortCrit)
    throws OculusException
  {
    QuestionInputList sortedList = new QuestionInputList(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }

  //----------------- IFeatureColl Methods ------------------------------------
  public IPersistable load()
    throws OculusException
  {
    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
      IQueryProcessor stmt = repConn.createProcessor();
      String query = getLoadQuery();
      IDataSet results = stmt.retrieve(query);
      
      while (results.next())
      {
        
        long revID = results.getLong("filOBJECTID");        
        IIID iid = new SequentialIID(revID);
        _items.add(iid);
        results.setIID(iid);
        IFolderInputLink inplink = (IFolderInputLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderInputLink",results);       
                
        long featID = results.getLong("miOBJECTID");        
        iid = new SequentialIID(featID);
        results.setIID(iid);
        IMarketInput inp = (IMarketInput)getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInput",results);        
        inp.setFolderInputLink(inplink);
       
      }
      
      stmt.close();
//      CRM.getInstance().returnDatabaseConnection(repConn);
      reset();
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException("Error retrieving data from the database."+sqlExp.toString());
    }
    return this;
  }

  public Object get(int index)
  {
    IMarketInput inp =null;
    try
    {
       IIID catlinkIID = (IIID)_items.get(index);
       IFolderInputLink inplink = null;       
       inplink = (IFolderInputLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FolderInputLink",catlinkIID,isLocked());       
       inp = inplink.getInput();      
       inp.setFolderInputLink(inplink);
    }
    catch(Exception ex){}    
     return inp;
  }
  
  public Object dolly() throws OculusException
  {
    QuestionInputList featList = null;
      featList = new QuestionInputList();
      featList.setIID(_iid);
      featList._items = this._items;
      featList.reset();
    return featList;
  }


  
}