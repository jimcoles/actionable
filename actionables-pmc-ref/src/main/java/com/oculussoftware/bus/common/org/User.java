package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import java.sql.*;
import java.util.*;

/**
* Filename:    User.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a User.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.1
*
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* Bug86						Zain Nemazie	  5/17/00     added isSysUser, isAdmin for Jim
* DES00099 		    Zain Nemazie    5/17/00			per Egan's Request # failed logins reset to zero on edit or create
* DES00216        Zain Nemazie    5/18/00     Adding set/getGroup -removing associate, disassociate since 1:1 user:group
* DES00216        Zain Nemazie    5/18/00     Changed set/get group to take iid not IGroups to avoid object creation overhead
* DES00216        Zain Nemazie    5/18/00     Changed update + create queries to not try and encrypt null strings (throws exception)
* DES00099        Zain Nemazie    5/17/00     Cleaed up failed login request to ensure the new failed logins reset is reflected in cached objects
* 041							Zain Nemazie		5/22/00			Adding licensing methods
* 311				      Zain Nemazie		5/23/00			Removed cloning of BMProperty directly - MUST USE ACCESSOR/MUTATOR (big No-No)
* BUG86						Zain Nemazie 		5/23/00			Added isTheSys user.
* BUG00385        Zain Nemazie	  5/23/00			SQL script missing comma is orgid is set.
* ENG00598        Zain Nemazie	  6/01/00			modified load(IDataSet) to check whether col groupid is returned at all
* BUG01010        Zain Nemazie    6/14/00     changed isAccol etc to BMProperties in hope of fixing unrecreatable errors with them 
* BUG1194         Zain Nemazie    7/05/00     Added convenience method get Org Object.
* BUG01441        Zain Nemazie    7/13/00     Overrode soft delete to enable removing licenses from deleted user
*/

public class User extends BusinessObject implements IUser
{
	protected static String COL_ORGID = "ORGID";
	protected static String COL_LOGINID = "LOGINID";
	protected static String COL_PASSWORD = "PASSWORD";
	//protected String COL_EMAILSTATUS				= "EMAILSTATUS";
	protected static String COL_NUMBEROFTRIALS = "FAILEDLOGINS";
	protected static String COL_AUTOLOGOUT = "AUTOLOGOUT";
	//protected String COL_DELETEKIND					= "DELETEKIND";
	//protected String COL_ISACTIVE						= "ISACTIVE";
	protected static String COL_ISTEMPORARY = "ISTEMPORARY";
	protected static String COL_ISINTERNAL = "ISINTERNAL";
	protected static String COL_ACTIVE = "ACTIVE";
	protected static String COL_COMPASS = "COMPASS";
	protected static String COL_CONDUIT = "CONDUIT";
	protected static String COL_ACCOLADES = "ACCOLADES";
	protected static String COL_LASTNAME = "LASTNAME";
	protected static String COL_PHONE = "PHONE";
	protected static String COL_EMAILADDR = "EMAILADDR";
	protected static String COL_FIRSTNAME = "FIRSTNAME";
	protected static String COL_GROUPID = "GROUPID";
  
  private static String PROP = "prop";
	protected static String LABEL_ORGID = "Associated Organization";
	protected static String LABEL_GUID = "Guid";
	protected static String LABEL_LOGINID = "Login Name";
	protected static String LABEL_PASSWORD = "Password";
	protected static String LABEL_EMAILSTATUS = "Email Status";
	protected static String LABEL_NUMBEROFTRIALS = "Number of Failed Logins";
	protected static String LABEL_AUTOLOGOUT = "Auto Logout";
	protected static String LABEL_ACTIVE = "Active";
	protected static String LABEL_FIRSTNAME = PROP+IDCONST.FIRSTNAME.getIIDValue();
	protected static String LABEL_LASTNAME = PROP+IDCONST.LASTNAME.getIIDValue();
	protected static String LABEL_PHONE = PROP+IDCONST.PHONE.getIIDValue();
	protected static String LABEL_EMAILADDR = PROP+IDCONST.EMAIL.getIIDValue();
  
	protected IIID _OrgIID;
	protected ActiveKind _active = null;
	protected boolean _isInternal = false;
	protected boolean _isTemporary = false;
	protected IRProperty _loginId; // string type
	protected IRProperty _password; // string type
	protected IRProperty _emailStatus; // int type
	protected IRProperty _numberOfTrials; // int type                 
	protected IRProperty _autoLogout; // int type
	protected IRProperty _lastName; // string type	
	protected IRProperty _phone; // string type	
	protected IRProperty _emailAddr; // string type	
	protected IRProperty _firstName; // string type	  	 
	protected IRProperty _isAccolades; //bool type
	protected IRProperty _isCompass; //bool type
	protected IRProperty _isConduit; //booltype


  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */
  public User() throws OculusException
  {
  super();
  COL_GUID                = "GUID";
  TABLE                   = "APPUSER";
  _loginId                 = new BMProperty(this);                 // string type
  _password               = new BMProperty(this);                // string type
  _emailStatus             = new BMProperty(this);            // int type
  _numberOfTrials         = new BMProperty(this);         // int type                 
  _autoLogout             = new BMProperty(this);             // int type
  //_active                 = new BMProperty(this);                 // short type
  _lastName               = new BMProperty(this);               // string type  
  _phone                   = new BMProperty(this);                  // string type  
  _emailAddr               = new BMProperty(this);              // string type  
  _firstName               = new BMProperty(this);              // string type       
  _isAccolades             = new BMProperty(this); //bool type
  _isCompass              = new BMProperty(this); //bool type
  _isConduit              = new BMProperty(this); //booltype 

  

  _loginId.setDefnObject(IDCONST.LOGINID.getIIDValue());  
  _password.setDefnObject(IDCONST.USERPASSWORD.getIIDValue());  
  _emailStatus.setDefnObject(IDCONST.EMAILSTATUS.getIIDValue());  
  _numberOfTrials.setDefnObject(IDCONST.NUMTRIALS.getIIDValue());  
  _autoLogout.setDefnObject(IDCONST.AUTOLOGOUT.getIIDValue());  
  //_active.setDefnObject(IDCONST.ACTIVE.getIIDValue());  
  _lastName.setDefnObject(IDCONST.LASTNAME.getIIDValue());  
  _phone.setDefnObject(IDCONST.PHONE.getIIDValue());  
  _emailAddr.setDefnObject(IDCONST.EMAIL.getIIDValue());  
  _firstName.setDefnObject(IDCONST.FIRSTNAME.getIIDValue());
  _isAccolades.setDefnObject(IDCONST.LIC_ACCOLADES.getIIDValue());
  _isCompass.setDefnObject(IDCONST.LIC_COMPASS.getIIDValue());
  _isConduit.setDefnObject(IDCONST.LIC_CONDUIT.getIIDValue());



  }  


  public IUser associateGroup(IIID grp)
  throws OculusException
  {
  //ensure there are no duplicates
  disassociateGroup(grp);
  IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
  conn.createProcessor().update("INSERT INTO USERGROUPASC (GROUPID, USERID) VALUES ("+grp.getLongValue()+","+getIID().getLongValue()+") ");
  return this;
  }  


  /**
  * Override any attempt to mark a User as PersState.DELETED.
  * UI can only edit the user to be ACTIVE = 0
  */
  public IPersistable delete()
  throws OculusException
  {
  if (!isLocked())  // If the bo isn't locked, throw an exception
    throw new ORIOException("This object cannot be deleted because it is not locked.");

  setPersState(PersState.UNMODIFIED);            // set the persistent state to unmodifed to ensure no attempt to alter object via delete
  return this;
  }  


  public IUser disassociateGroup(IIID grp)
  throws OculusException
  {
  IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
  conn.createProcessor().update("DELETE FROM USERGROUPASC WHERE USERID="+getIID().getLongValue()+" AND GROUPID="+grp.getLongValue()+" ");
  return this;
  }  


  //----------------- IPoolable Methods ------------------------------------
  /**
  *  Returns a copy of the current Company object.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object dolly() throws OculusException
  {
  User usr = null;
  usr = new User();
  usr.setIID(getIID());
  usr.setObjectContext(getObjectContext());
  usr.setPersState(getPersState());
  //causes exception
  //if (getDefnObject() != null)
  //  usr.setDefnObject(getDefnObject());
  //if (getStateObject() != null)
  //  usr.setStateObject(getStateObject());
  usr._classIID = _classIID;
  //usr._stateIID = _stateIID;
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
    usr.putAll(getProperties());
  //usr.setName(getName());
  usr.setDescription(getDescription());
  usr._creatorIID = _creatorIID;
  usr._accessIID = _accessIID;
  usr.setCreationDate(getCreationDate());
  usr.setMessageAttached(hasMessageAttached());
  usr.setLinkAttached(hasLinkAttached());
  usr.setFileAttached(hasFileAttached());
  usr.setDeleteState(getDeleteState());
  //specific to user
  usr.isTemporary(isTemporary());
  usr.isCustomer(isCustomer());
  usr.setActive(getActive());
  //usr.setEmailStatus(getEmailStatus());
  // usr.setEmailStatus(getEmailStatus());
  usr.setNumberOfTrials(getNumberOfTrials());
  usr.setEmailAddr(getEmailAddr());
  usr.setFirstName(getFirstName());
  usr.setLastName(getLastName());
  usr.setLoginId(getLoginId());
  usr.setPassword(getPassword());
  usr.setPhone(getPhone());
  usr.setOrgIID(getOrgIID());
  usr.setAccolades(isAccolades());
  usr.setCompass(isCompass());
  usr.setConduit(isConduit());
  return usr;
  }    


  //----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
  throws OculusException
  {
  if (key instanceof String)
  {
    if (key.equals(LABEL_ACTIVE))
    return _active;
    else if (key.equals(LABEL_AUTOLOGOUT))
    return _autoLogout;
    else if (key.equals(LABEL_EMAILADDR))
    return _emailAddr;
    else if (key.equals(LABEL_EMAILSTATUS))
    return _emailStatus;
    else if (key.equals(LABEL_FIRSTNAME))
    return _firstName;
    else if (key.equals(LABEL_LASTNAME))
    return _lastName;
    else if (key.equals(LABEL_ORGID))
    return _OrgIID;
    else if (key.equals(LABEL_PASSWORD))
    return _password;
    else if (key.equals(LABEL_PHONE))
    return _phone;
    //extended attr
    else
    return super.get(key);
  }
  else
    return null;
  }  


  //------------------------ ACCESSORS -------------------------------------

  public IIID getAccessorIID()
  throws ORIOException
  {  
  return getIID();
  }  


  public ActiveKind getActive()
  throws OculusException
  {
  return _active;
  }  


  protected String getCreateQuery()
  throws OculusException
  {
  //on Create the number of failed logins is reset  
  setNumberOfTrials(0);       
  String query = " INSERT INTO "+TABLE+" "+
    " ("+COL_OBJECTID+
    ", "+COL_CLASSID+
    //   +COL_STATEID+", "
    ", "+COL_GUID+
    //        +COL_NAME+", "
    //        +COL_DESCRIPTION+", "
    ", "+COL_CREATIONDATE+
    ", "+COL_CREATORID+
    ", "+COL_ACCESSID+
    ", "+COL_MESSAGEATTACHED+
    ", "+COL_FILEATTACHED+
    ", "+COL_LINKATTACHED+
    ", "+COL_DELETESTATE+
    //specific to user
    ", "+COL_ISTEMPORARY+    
    ", "+COL_ISINTERNAL+      
    (getActive()!=null?","+COL_ACTIVE:"")+      
    //+COL_AUTOLOGOUT+", "
    //       +COL_EMAILSTATUS+", "
    (getNumberOfTrials()!=-1?"," + COL_NUMBEROFTRIALS:"")+            
    (getEmailAddr()!=null?","+COL_EMAILADDR:"")+
    (getFirstName()!=null?","+COL_FIRSTNAME:"")+
    (getLastName()!=null?","+COL_LASTNAME:"")+
    (getLoginId()!=null?","+COL_LOGINID:"")+
    (getPassword()!=null?","+COL_PASSWORD:"")+
    (getPhone()!=null?","+COL_PHONE:"")+
    (getOrgIID()!=null?","+COL_ORGID:"")+

    ") "+


    " VALUES "+
    " ("+getIID().getLongValue()+
    ","+getDefnObject().getIID().getLongValue()+
    ","+"'"+getGUID().toString()+"'" +
    ","+"'"+getCreationDate().toString()+"'" +
    ","+getCreatorIID().getLongValue()+
    //        +"0,"
    ","+getAccessIID().getLongValue()+
    ","+(hasMessageAttached()?"1":"0")+
    ","+(hasFileAttached()?"1":"0")+
    ","+(hasLinkAttached()?"1":"0")+
    ","+getDeleteState().getIntValue() + 
    //specific to user
    ","+(isTemporary()?"1":"0")+       
    ","+(isCustomer()?"0":"1")+      
    (getActive()!=null?","+getActive().getIntValue():"")+
    (getNumberOfTrials()!=-1?","+getNumberOfTrials():"")+  
    (getEmailAddr()!=null?","+"'"+SQLUtil.primer(getEmailAddr())+"'":"")+
    (getFirstName()!=null?","+"'"+SQLUtil.primer(getFirstName())+"'":"")+      
    (getLastName()!=null?","+"'"+SQLUtil.primer(getLastName())+"'":"")+      
    (getLoginId()!=null?","+"'"+SQLUtil.primer(getLoginId())+"'":"")+ 
    (getPassword()!=null?","+"'" + SQLUtil.primer(Security.encrypt(getPassword()))+ "'":"")+        
    (getPhone()!=null?","+"'"+SQLUtil.primer(getPhone())+"'":"")+
    (getOrgIID()!=null?","+getOrgIID():"") +
    ");";

  //licensing add
  //delete all user licensing
  query  = query + " DELETE FROM USERMODULEGRANT "+
    " WHERE USERID ="+ getIID()+";";
  //add user licenses
  if (isAccolades())
  {
    query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES ("+ModuleKind.ACCOLADES.getIntValue()+","+getIID().getLongValue()+");";        
  }
  if (isCompass())
  {
    query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES ("+ModuleKind.COMPASS.getIntValue()+","+getIID().getLongValue()+");";        
  }
  if (isConduit())
  {
    query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES ("+ModuleKind.CONDUIT.getIntValue()+","+getIID().getLongValue()+");";        
  }  

  return query;  
  }    


  protected String getDeleteQuery()
  throws ORIOException
  {
  return " UPDATE " + TABLE +
    " SET "+
    COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
    " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  


  public IIID getDepartmentIID()
  throws ORIOException
  {
  IIID id = null;
   try
  {   
   IRPropertyMap props = getProperties();
    if (props != null)
    {
    IRProperty prop = (IRProperty)props.get("prop"+IDCONST.DEPARTMENT.getIIDValue().toString());
    Object o = prop.getValue();
    if (o != null && o instanceof java.lang.Long)
      id = new SequentialIID(((Long)o).longValue());
    }
    }
  catch(Exception ex) { throw new ORIOException(ex);}
  return id;
  }  


  public String getEmailAddr()
  throws OculusException
  {
  return (String)_emailAddr.getValue();

  }  


  public int getEmailStatus()
  throws OculusException
  {
  if (_autoLogout.getValue() != null)
    return ((Integer)_autoLogout.getValue()).intValue();
  else
    return -1;
  }  


  public String getFirstName()
  throws OculusException
  {
  return (String)_firstName.getValue();

  }  


  public String getFullName(boolean hasComma)
  throws OculusException
  {
  if (hasComma)    
    return ((String)_lastName.getValue() + ", " + (String)_firstName.getValue());
  else
    return ((String)_firstName.getValue() + " " + (String)_lastName.getValue());
  }  


  public IGroupColl getGroups() throws OculusException
  {
    return (IGroupColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"GroupUserColl",getIID()); 
  }  


  public String getLastName()
  throws OculusException
  {
  return (String)_lastName.getValue();

  }  


  //-------------------------- Protected Methods -----------------------------
  protected String getLoadQuery()
  throws ORIOException
  {

  return " SELECT appuser.*, usergroupasc.GROUPID, " + 
    "mod_accolades.modulecode AS " + COL_ACCOLADES +"," + 
    "mod_compass.modulecode AS " + COL_COMPASS+"," + 
    "mod_conduit.modulecode AS " + COL_CONDUIT+" " + 
    "FROM ((((" + TABLE+" LEFT OUTER JOIN "+
    "USERMODULEGRANT AS mod_accolades ON " +
    "APPUSER.OBJECTID = mod_accolades.USERID AND " + 
    "mod_accolades.MODULECODE = "+ ModuleKind.ACCOLADES.getIntValue() +") LEFT OUTER JOIN " +
    "USERMODULEGRANT AS mod_compass ON " + 
    "objectid = mod_compass.userid AND " + 
    "mod_compass.modulecode = "+ ModuleKind.COMPASS.getIntValue()+") LEFT OUTER JOIN " +
    "USERMODULEGRANT AS mod_conduit ON " +
    "objectid = mod_conduit.userid AND " +  
    "mod_conduit.modulecode = "+ModuleKind.CONDUIT.getIntValue()+") LEFT OUTER JOIN " +
    "USERGROUPASC ON OBJECTID = USERGROUPASC.USERID) " +
    "WHERE objectid = " + getIID().getLongValue();

  }  


  public String getLoginId()
  throws OculusException
  {
  return (String)_loginId.getValue();
  }  


  public String getName()
  throws OculusException
  {
  return getFullName(false);   
  }  


  public int getNumberOfTrials()
  throws OculusException
  {
  if (_numberOfTrials.getValue() != null)
    return ((Integer)_numberOfTrials.getValue()).intValue();
  else
    return -1;
  }  


  public IOrganization getOrganizationObject()
  throws OculusException
  {
  return getOrganizationObject(false);
  }  


  public IOrganization getOrganizationObject(boolean edit)
  throws OculusException
  {
  if (_OrgIID!=null)
  {
    return (IOrganization)getObjectContext().getCRM().getCompObject(getObjectContext(),"Organization",_OrgIID,edit); 
  }
  else
  {
    return null;
  }
  }  


  //------------------------ ACCESSORS -------------------------------------

  public IIID getOrgIID()
  throws ORIOException
  {  
  return _OrgIID;
  }  


  public String getPassword()
  throws OculusException
  {
  return (String)_password.getValue();
  }  


  public String getPhone()
  throws OculusException
  {
  return (String)_phone.getValue();

  }  


  protected String getUpdateQuery()
  throws OculusException
  {
  //on edit the number of failed logins is reset  
  setNumberOfTrials(0);
  //user update 
  String query =   " UPDATE "+TABLE+" "+
    " SET "+
    COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
    " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
    " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
    " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
    " , "+COL_DELETESTATE+" = "+getDeleteState().getIntValue() + " " +
    //specific to user
    " , "+COL_ISTEMPORARY+"= "+(isTemporary()?"1":"0")+" "+     
    " , "+COL_ISINTERNAL+"= "+(isCustomer()?"0":"1")+" "+    
    (getActive()!=null?" , "+COL_ACTIVE+"= "+getActive().getIntValue()+" ":" ")+
    (getNumberOfTrials()!=-1?" , "+COL_NUMBEROFTRIALS+"= "+getNumberOfTrials()+" ":" ")+
    (getEmailAddr()!=null?" ,  "+COL_EMAILADDR+"='"+SQLUtil.primer(getEmailAddr())+"' ":" ")+
    (getFirstName()!=null?" ,  "+COL_FIRSTNAME+"='"+SQLUtil.primer(getFirstName())+"' ":" ")+
    (getLastName()!=null?" ,  "+COL_LASTNAME+"='"+SQLUtil.primer(getLastName())+"' ":" ")+
    (getLoginId()!=null?" ,  "+COL_LOGINID+"='"+SQLUtil.primer(getLoginId())+"' ":" ")+
    (getPassword()!=null?" ,  "+COL_PASSWORD+"='"+SQLUtil.primer(Security.encrypt(getPassword()))+"' ":" ")+
    (getPhone()!=null?" ,  "+COL_PHONE+"='"+getPhone()+"' ":" ")+
    (getOrgIID()!=null?" ,  "+COL_ORGID+"="+getOrgIID()+" ":" ")+
    " WHERE "+COL_OBJECTID+"="+getIID().getLongValue()+";";

  //licensing add
  //delete all user licensing
  query  = query + " DELETE FROM USERMODULEGRANT "+
    " WHERE USERID ="+ getIID()+";";
  //add user licenses
  if (isAccolades())
  {
    query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES ("+ModuleKind.ACCOLADES.getIntValue()+","+getIID().getLongValue()+");";        
  }
  if (isCompass())
  {
    query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES ("+ModuleKind.COMPASS.getIntValue()+","+getIID().getLongValue()+");";        
  }
  if (isConduit())
  {
    query = query + "INSERT INTO USERMODULEGRANT (MODULECODE, USERID) VALUES ("+ModuleKind.CONDUIT.getIntValue()+","+getIID().getLongValue()+");";        
  }  


  return query;
  }    


  /**
  * isAccolades method comment.
  */
  public boolean isAccolades() throws OculusException
  {
  if (_isAccolades.getValue() != null)
    return ((Boolean)_isAccolades.getValue()).booleanValue();
  else
    return false;
  }  


  /**
  * isAdmin method comment.
  */
  public boolean isAdmin() throws com.oculussoftware.api.repi.ORIOException
  {
  return (getIID().getLongValue()==IDCONST.USER_ADMIN.getLongValue()?true:false);
  }  


  /**
  * isCompass method comment.
  */
  public boolean isCompass() throws OculusException
  {
  if (_isCompass.getValue() != null)
    return ((Boolean)_isCompass.getValue()).booleanValue();
  else
    return false;
  }  


  /**
  * isConduit method comment.
  */
  public boolean isConduit() throws OculusException
  {
  if (_isConduit.getValue() != null)
    return ((Boolean)_isConduit.getValue()).booleanValue();
  else
    return false;
  }  


  public boolean isCustomer()
  throws OculusException
  {
  return !_isInternal;
  }  


  public IUser isCustomer(boolean bool)
  throws OculusException
  {
  if (_perState == PersState.UNMODIFIED)
    _perState = PersState.MODIFIED;      
  _isInternal = !bool;
  return this;
  }    


  /**
  * isSysUser method comment.
  */
  public boolean isSysUser() throws OculusException
  {
  return (getActive()==ActiveKind.ACTIVE?true:false);
  }  


  /**
  * isAdmin method comment.
  */
  public boolean isTheSysUser() throws com.oculussoftware.api.repi.ORIOException
  {
  return (getIID().getLongValue()==IDCONST.USER_SYSTEM.getLongValue()?true:false);
  }  


  protected void load(IDataSet results) throws OculusException
  {
  IRepository rep = getObjectContext().getRepository();
  if (results.getIID() == null)
    results.setIID(results.getLong(COL_OBJECTID));
  setPersState(PersState.PARTIAL);
  _guid = new GUID(results.getString(COL_GUID).trim()); // get GUID
  _iid = rep.makeReposID(results.getLong(COL_OBJECTID)); // get IID
  _classIID = new SequentialIID(results.getLong(COL_CLASSID)); // get class
  //    if (getDefnObject().hasStateMachine())
  _stateIID = rep.makeReposID(results.getLong(COL_STATEID)); // get state
  setCreatorIID(rep.makeReposID(results.getLong(COL_CREATORID)));
  setAccessIID(rep.makeReposID(results.getLong(COL_ACCESSID)));
  setCreationDate(results.getTimestamp(COL_CREATIONDATE));
  setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
  setFileAttached(results.getBoolean(COL_FILEATTACHED));
  setLinkAttached(results.getBoolean(COL_LINKATTACHED));
  setDeleteState(DeleteState.getInstance(results.getInt(COL_DELETESTATE)));
  //specific to user   
  setNumberOfTrials(results.getInt(COL_NUMBEROFTRIALS));
  setEmailAddr(results.getString(COL_EMAILADDR));
  setFirstName(results.getString(COL_FIRSTNAME));
  setLastName(results.getString(COL_LASTNAME));
  if (results.getString(COL_PASSWORD)!=null)
    setPassword(Security.decrypt((results.getString(COL_PASSWORD))));
  setLoginId(results.getString(COL_LOGINID));
  setPhone(results.getString(COL_PHONE));
  if (results.get(COL_ORGID)!=null)  
    setOrgIID(rep.makeReposID(results.getLong(COL_ORGID)));
  setActive(ActiveKind.getInstance(results.getInt(COL_ACTIVE)));
  isCustomer(!results.getBoolean(COL_ISINTERNAL));
  isTemporary(results.getBoolean(COL_ISTEMPORARY));
  //if ((results.containsKey(COL_GROUPID)) && (results.get(COL_GROUPID)!=null))   
  //    setGroupIID(rep.makeReposID(results.getLong(COL_GROUPID)));
  if ((results.containsKey(COL_ACCOLADES)) && (results.get(COL_ACCOLADES)!=null))
    setAccolades(true); 
  if ((results.containsKey(COL_COMPASS)) && (results.get(COL_COMPASS)!=null))
    setCompass(true);

  if ((results.containsKey(COL_CONDUIT)) && (results.get(COL_CONDUIT)!=null))
    setConduit(true);
   

  }    


  public void put(Object key, Object value)
  throws OculusException
  {
  if (key instanceof String && value instanceof IRProperty)
  {
    IRProperty property = (IRProperty)value;
    if (key.equals(LABEL_AUTOLOGOUT))
    setEmailStatus(((Integer)property.getValue()).intValue());
    else if (key.equals(LABEL_EMAILADDR))
    setEmailAddr((String)property.getValue());
    else if (key.equals(LABEL_FIRSTNAME))
    setFirstName((String)property.getValue());
    else if (key.equals(LABEL_LASTNAME))
    setLastName((String)property.getValue());
    else if (key.equals(LABEL_LOGINID))
    setLoginId((String)property.getValue());
    else if (key.equals(LABEL_ORGID))
    setOrgIID((IIID)value);
    else if (key.equals(LABEL_PASSWORD))
    setPassword((String)value);
    else if (key.equals(LABEL_PHONE))
    setPhone((String)value);      
    else
    super.put(key,value);
  }
  }  


  /** Checks for Unique LoginID */

  public IPersistable save() throws OculusException
  {
  IQueryProcessor stmt = null;
  try
  {
    IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
    stmt = repConn.createProcessor();
    String query;
    if (isCustomer())
    query = "Select " + COL_LOGINID + ", " + COL_EMAILADDR + " from " + TABLE + " where " + COL_OBJECTID + " <> " + _iid.getLongValue() + " AND " + COL_DELETESTATE + "<>" + DeleteState.DELETED.getIntValue() + " AND " + COL_ISINTERNAL + "=" + (isCustomer() ? "0" : "1");// + " AND " + COL_LOGINID + " IS NOT NULL";
    else
    query = "Select " + COL_LOGINID + ", " + COL_EMAILADDR + " from " + TABLE + " where " + COL_OBJECTID + " <> " + _iid.getLongValue() + " AND " + COL_DELETESTATE + "<>" + DeleteState.DELETED.getIntValue() + " AND " + COL_ISINTERNAL + "=" + (isCustomer() ? "0" : "1");// + " AND " + COL_LOGINID + " IS NOT NULL";
    
    IDataSet ds = stmt.retrieve(query);
    while (ds.next())
    {
    if ((ds.getString(COL_LOGINID)!=null) && (ds.getString(COL_LOGINID).equals(getLoginId())))
    {
      throw new OculusException("DuplicateLoginID", ErrorType.UNIQUE_CONSTRAINT_VIOLATION);
    }
    if ((ds.getString(COL_EMAILADDR)!=null) && (ds.getString(COL_EMAILADDR).equals(getEmailAddr())))
    {
      throw new OculusException("DuplicateEmail", ErrorType.UNIQUE_CONSTRAINT_VIOLATION);
    }
    }
    if (stmt != null)
    stmt.close();
    super.save();
  }
  finally
  {
    if (stmt != null)
    stmt.close();
  }
  return this;
  }  


  /**
  * setAccolades method comment.
  */
  public IUser setAccolades(boolean bool) throws ORIOException
  {
  _isAccolades.setValue(new Boolean(bool));
  return this;
  }  


  public IUser setActive(ActiveKind active)
  throws ORIOException
  {
  _active=active;
  return this;
  }  


  /**
  * setCompass method comment.
  */
  public IUser setCompass(boolean bool) throws ORIOException
  {
  _isCompass.setValue(new Boolean( bool ));
  return this;
  }  


  /**
  * setCompass method comment.
  */
  public IUser setConduit(boolean bool) throws ORIOException
  {
  _isConduit.setValue(new Boolean(bool));
  return this;
  }  


  public IUser setEmailAddr(String emailAddr)
  throws ORIOException
  {
  _emailAddr.setValue(emailAddr);
  return this;
  }  


  public IUser setEmailStatus(int emailStatus)
  throws ORIOException
  {
  _autoLogout.setValue(new Integer(emailStatus));
  return this;
  }  


  public IUser setFirstName(String firstName)
  throws ORIOException
  {
  _firstName.setValue(firstName);
  return this;
  }  


  public IUser setLastName(String lastName)
  throws ORIOException
  {
  _lastName.setValue(lastName);
  return this;
  }  


  public IUser setLoginId(String loginId)
  throws ORIOException
  {
  _loginId.setValue(loginId);
  return this;
  }  


  //expect lastname,firstname else throw exception
  public IRElement setName(String name)
  throws OculusException
  {
  int iOffset;
  if ((iOffset = name.indexOf(",")) < 0)
    throw new OculusException("Expected name in form FirstName,LastName");
  setLastName(name.substring(0,iOffset).trim());
  setFirstName(name.substring(iOffset + 1, name.length()).trim());
  return this;
  }  


  private IUser setNumberOfTrials(int numberOfTrials)
  throws ORIOException
  {
  _numberOfTrials.setValue(new Integer(numberOfTrials));
  return this;
  }  


  //------------------------ MUTATORS -------------------------------------
  public IUser setOrgIID(IIID id)
  throws ORIOException
  {  
  if (getPersState().equals(PersState.UNMODIFIED))
    setPersState(PersState.MODIFIED);
  _OrgIID = id;
  return this;
  }  


  public IUser setPassword(String password)
  throws ORIOException
  {
  _password.setValue(password);
  return this;
  }  


  public IUser setPhone(String phone)
  throws ORIOException
  {
  _phone.setValue(phone);
  return this;
  }  


  public IBusinessObject softDelete()
  throws OculusException
  {
  setDeleteState(DeleteState.DELETED);
  setAccolades(false);
  setCompass(false);
  setConduit(false);
  return this;
  }  


  public boolean isTemporary()
  throws OculusException
  {
    return _isTemporary;
  }      


  public IUser isTemporary(boolean bool)
  throws OculusException
  {
  if (_perState == PersState.UNMODIFIED)
    _perState = PersState.MODIFIED;      
  _isTemporary = bool;
  return this;
  }      


  public static IUser getUserViaEmail(String EmailAddress,  boolean isInternal, boolean edit, IObjectContext context)
  throws OculusException
  {

    IQueryProcessor stmt = null;
    IIID userID = null;    
    try
    {
      IRConnection repConn = context.getRepository().getDataConnection(context);
      stmt = repConn.createProcessor();
      String query;

      
      query =  "SELECT *" + " FROM " + "APPUSER" + " WHERE " + " EMAILADDR " +" = '"+ EmailAddress +"' AND "
        + " ISINTERNAL "+" = " + (isInternal?"1":"0");
      IDataSet ds = stmt.retrieve(query);
      while (ds.next())
      {
        //we must have an iid that points to a user with a matching email address
        userID = new SequentialIID(ds.getLong("OBJECTID"));         
      }

      if (userID!=null)
      {
        return (IUser) context.getCRM().getCompObject(context, "User", userID, edit);        
      }
      else
      {
        return null;
      }
      
    }
    finally
    {
      if (stmt != null)
      stmt.close();
    }        
      
  }
}
