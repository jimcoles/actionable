package com.oculussoftware.system.license;

import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
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
public class ModuleColl implements IModuleColl
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  protected Map _coll;
  protected Iterator _itr;
  
  /**
  * package level
  */
  ModuleColl()
  {
    _coll = Collections.synchronizedMap(new HashMap());
  }
  
  public IModuleColl reset()
  {
    _itr = _coll.keySet().iterator();
    return this;
  }
  
  public IModule nextModule() throws OculusException
  {
    return (IModule)next(); 
  }
  
  public Object next()
  {
    return _coll.get(_itr.next());
  }
  
  public boolean hasMoreModules()
  {
    return hasNext();
  }
  
  public boolean hasNext()
  {
    return _itr.hasNext();
  }
  
  public IModuleColl putAll(IObjectContext systemcontext) throws OculusException
  {
    IQueryProcessor qp = null;
    try
    {
      IRConnection repConn = systemcontext.getCRM().getDatabaseConnection(systemcontext);
      qp = repConn.createProcessor();
      IDataSet results = qp.retrieve("SELECT * FROM MODULE");
      while(results.next())
      {
        ModuleKind mk = ModuleKind.getInstance(results.getInt("MODULECODE"));
        String strModuleKey = results.getString("INSTALLKEY");
        IModule module = new Module(mk,strModuleKey);
        _coll.put(mk,module); 
      }//
      reset();
      repConn.commit();
      systemcontext.getCRM().returnDatabaseConnection(repConn);
    }//end try
    finally{if(qp != null) qp.close();}
    return this;
  }//
  
  public boolean containsModule(ModuleKind mk)
  {
    return _coll.containsKey(mk); 
  }//
  
  public IModule getModule(ModuleKind mk)
  {
    return (IModule)_coll.get(mk);
  }//
  
  public IModule add(IObjectContext systemcontext, ModuleKind mk, String strKey) throws OculusException
  {
    IModule module = new Module(mk,strKey);
    _coll.put(mk,module);
    reset();
    //add to the database
    module.insert(systemcontext);
    return module;
  }//
  
  public IModule remove(IObjectContext systemcontext, ModuleKind mk) throws OculusException
  {
    IModule module = getModule(mk);
    _coll.remove(mk);
    reset();
    //remove from the database
    module.delete(systemcontext);
    return module;
  }
  
  public IModule update(IObjectContext systemcontext, ModuleKind mk, String strKey) throws OculusException
  {
    _coll.remove(mk);
    IModule module = new Module(mk,strKey);
    _coll.put(mk,module);
    reset();
    //update the database
    module.update(systemcontext);
    return module;
  }
}//