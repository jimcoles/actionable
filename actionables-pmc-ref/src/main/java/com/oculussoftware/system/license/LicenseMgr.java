package com.oculussoftware.system.license;

import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;

/**
* Filename:    LicenseMgr.java
* Date:        5-10-00
* Description: .
*
* Copyright 1-31-2000 productmarketing.com.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class LicenseMgr implements ILicenseMgr
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * BUG102					Zain Nemazie		5/24/00			Added getActualNumUsers for Admin/Settings
  */
  
  private static LicenseMgr _mgr;
  private static ModuleColl _modulecoll;
  
  /**
  * private constructor
  */
  private LicenseMgr() throws OculusException
  {
	_modulecoll = new ModuleColl();
	_modulecoll.putAll(getSystemContext());
  }  
  private ILicenseMgr addConduitModule(IModule mod) throws OculusException
  {
	IKeyGen gen = getKeyGen();
	String strTime = ""+System.currentTimeMillis();
	strTime = strTime.substring(strTime.length()-3, strTime.length());
	String key = gen.createKey(ModuleKind.CONDUIT, mod.getModuleVersion(), (Integer.parseInt(strTime) % 62),
							   mod.getCustomerID(), 1001, mod.getExpirationMonth(), mod.getExpirationDay(), mod.getExpirationYear());
	return addModule(ModuleKind.CONDUIT,key);
  }  
  public ILicenseMgr addModule(ModuleKind mk, String key) throws OculusException
  {
	if(hasModules())
	{
	  _modulecoll.reset();
	  IModule module = _modulecoll.nextModule();
	  _modulecoll.reset();
	  if(module.getCustomerID() != getKeyGen().getCustomerID(key))
		throw new InvalidKeyException("Invalid customerid");
	}//end if
	IModule mod = _modulecoll.add(getSystemContext(),mk,key);
	_modulecoll.reset();
	if(mk.equals(ModuleKind.COMPASS))
	  addConduitModule(mod);
	return this;
  }  
  public int getActualNumUsers(ModuleKind mk) throws OculusException
  {
	  IRConnection repConn = getSystemContext().getRepository().getDataConnection(getSystemContext());
	  IQueryProcessor stmt = repConn.createProcessor();
	  
		String query = "SELECT COUNT(userid) AS CNT FROM USERMODULEGRANT WHERE modulecode = "+ mk.getIntValue(); 
		IDataSet ds = stmt.retrieve(query);

		int count = 0;
		if (ds.next())
		{
			count =  ds.getInt("CNT");
		}
	  if (stmt != null) stmt.close();
 		return count;
	}            
  public static ILicenseMgr getInstance() throws OculusException
  {
	if(_mgr == null)
	  _mgr = new LicenseMgr();
	_modulecoll.reset();
	return _mgr;
  }  
  public IKeyGen getKeyGen()
  {
	return KeyGen.getInstance();
  }  
  public IModule getModule(ModuleKind mk)
  {
	return _modulecoll.getModule(mk);
  }  
  public IModuleColl getModuleColl()
  {
	return _modulecoll.reset();
  }  
  public int getNumUsers(ModuleKind mk) throws OculusException
  {
	  return getModule(mk).getNumUsers();
  }  
  /**
  * private 
  */
  private IObjectContext getSystemContext() throws OculusException
  {
	IObjectContext context = null;
	try
	{
	  context = new ObjectContext();
	  ICRMConnection conn = context.getCRM().connect("system","system");//throws Exception?
	  context.setConnection(conn);
	}//
	catch(OculusException exc) { throw exc; }
	catch(Throwable ex) { throw new OculusException((Exception)ex); }
	return context;
  }  
  public boolean hasModules()
  {
	return _modulecoll.reset().hasMoreModules();
  }  
  public boolean isLicensed(ModuleKind mk) throws OculusException
  {
	boolean blnRV = false;
	if(_modulecoll.containsModule(mk) && !getModule(mk).isExpired())
	  blnRV = true;
	  return blnRV;
  }  
  public ILicenseMgr removeModule(ModuleKind mk) throws OculusException
  {
	_modulecoll.remove(getSystemContext(),mk);
	_modulecoll.reset();
	return this;
  }  
  private ILicenseMgr updateConduitModule(IModule mod) throws OculusException
  {
	IKeyGen gen = getKeyGen();
	String strTime = ""+System.currentTimeMillis();
	strTime = strTime.substring(strTime.length()-3, strTime.length());
	String key = gen.createKey(ModuleKind.CONDUIT, mod.getModuleVersion(), (Integer.parseInt(strTime) % 62),
							   mod.getCustomerID(), 1001, mod.getExpirationMonth(), mod.getExpirationDay(), mod.getExpirationYear());
	return updateModule(ModuleKind.CONDUIT,key);
  }  
  public ILicenseMgr updateModule(ModuleKind mk, String key) throws OculusException
  {
	IModule module = getModule(mk);
	if(module.getCustomerID() != getKeyGen().getCustomerID(key))
	  throw new InvalidKeyException("Invalid customerid");
	IModule mod = _modulecoll.update(getSystemContext(),mk,key);
	_modulecoll.reset();
	if(mk.equals(ModuleKind.COMPASS))
	  updateConduitModule(mod);
	return this;
  }  
}//
