package com.oculussoftware.system.license;

import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import java.sql.Timestamp;
import java.util.*;
/**
* Filename:    Module.java
* Date:        5-10-00
* Description: .
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class Module implements IModule
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  private String     _shiftedkey;
  private ModuleKind _modulekind;
  private long       _customerid;
  private int        _numusers;
  private int        _expmonth;
  private int        _expday;
  private int        _expyear;
  private Timestamp  _expdate;
  private int        _modver;
  
  /**
  * package level
  */
  Module(ModuleKind mk, String key) throws OculusException
  {
    _shiftedkey = key;
    _modulekind = mk;
    IKeyGen gen = KeyGen.getInstance();
    _customerid = gen.getCustomerID(key);
    _numusers   = gen.getNumberOfUsers(key);
    _modver     = gen.getVersion(key);
    _expmonth   = gen.getExpirationMonth(key);
    _expday     = gen.getExpirationDay(key);
    _expyear    = gen.getExpirationYear(key);
    Calendar cal = Calendar.getInstance();
    cal.set(_expyear,_expmonth-1,_expday);
    _expdate    = new Timestamp(cal.getTime().getTime());
  }
  
  //---------------------- accessors -------------------------------
  public String getKey() throws OculusException
  {
    return _shiftedkey;
  }//
  
  public long getCustomerID() throws OculusException
  {
    return _customerid;
  }//
  
  public ModuleKind getModuleKind() throws OculusException
  {
    return _modulekind;
  }//
  
  public int getNumUsers() throws OculusException
  {
    return _numusers;
  }//
  
  public Timestamp getExpirationDate() throws OculusException
  {
    return _expdate;
  }//
  
  public int getExpirationMonth() throws OculusException
  {
    return _expmonth;
  }//
  
  public int getExpirationDay() throws OculusException
  {
    return _expday;
  }//
  
  public int getExpirationYear() throws OculusException
  {
    return _expyear;
  }//
  
  public int getModuleVersion() throws OculusException
  {
    return _modver;
  }//
  
  public boolean isExpired() throws OculusException
  {
    if(noExpirationDate())
      return false;
    else if(_expdate.getTime() > System.currentTimeMillis())
      return false;
    return true;
  }//
  
  public boolean noExpirationDate() throws OculusException
  {
    if(_expmonth == 8 && _expday == 30 && _expyear == 1973)
      return true;
    return false;
  }//
  
  public IModule insert(IObjectContext systemcontext) throws OculusException
  {
    IQueryProcessor qp = null, qp2 = null;
    try
    {
      IRConnection repConn = systemcontext.getCRM().getDatabaseConnection(systemcontext);
      qp = repConn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT * FROM MODULE WHERE MODULECODE = "+_modulekind.getIntValue());
      if(!rs.next())
      {
        qp2 = repConn.createProcessor();
        qp2.update("INSERT INTO MODULE (MODULECODE, INSTALLKEY)"+
                    " VALUES ("+_modulekind.getIntValue()+",'"+_shiftedkey+"')");
        repConn.commit();
      }//end if
      systemcontext.getCRM().returnDatabaseConnection(repConn);
    }//end try
    finally{if(qp != null) qp.close();if(qp2 != null) qp2.close();}
    return this;
  }
  
  public IModule update(IObjectContext systemcontext) throws OculusException
  {
    IQueryProcessor qp = null;
    try
    {
      IRConnection repConn = systemcontext.getCRM().getDatabaseConnection(systemcontext);
      qp = repConn.createProcessor();
      qp.update("UPDATE MODULE SET INSTALLKEY='"+_shiftedkey+"'"+
                  " WHERE MODULECODE="+_modulekind.getIntValue());
      repConn.commit();
      systemcontext.getCRM().returnDatabaseConnection(repConn);
    }//end try
    finally{if(qp != null) qp.close();}
    return this;
  }
  
  public IModule delete(IObjectContext systemcontext) throws OculusException
  {
    IQueryProcessor qp = null;
    try
    {
      IRConnection repConn = systemcontext.getCRM().getDatabaseConnection(systemcontext);
      qp = repConn.createProcessor();
      qp.update("DELETE FROM MODULE"+
                  " WHERE MODULECODE="+_modulekind.getIntValue());
      repConn.commit();
      systemcontext.getCRM().returnDatabaseConnection(repConn);
    }//end try
    finally{if(qp != null) qp.close();}
    return this;
  }
  
}//