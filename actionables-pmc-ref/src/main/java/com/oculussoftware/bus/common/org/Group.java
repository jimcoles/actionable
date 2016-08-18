package com.oculussoftware.bus.common.org;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Group.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a Group.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.1
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class Group extends BusinessObject implements IGroup
{

  protected String COL_GROUPID              = "GROUPID";
  protected String COL_PARGROUPID           = "PARGROUPID";

  //protected String COL_DELETEKIND          = "DELETEKIND";
  //protected String COL_ISACTIVE            = "ISACTIVE";


  protected String LABEL_GROUPID             = "Associated Group";
  protected String LABEL_GUID                = "Guid";
  protected String LABEL_PARGROUPID          = "Parent Group ID";
  //protected String LABEL_DELETEKIND          = "Delete Kind";
  //protected String LABEL_ISACTIVE            = "Is Active";



  protected IIID         _grpIID;
  protected IIID         _parGrpIID;
  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public Group() throws OculusException
  {
	super();
	COL_GUID                = "GUID";
	TABLE                   = "USERGROUP";

  }  
//----------------- IPoolable Methods ------------------------------------
/**
  *  Returns a copy of the current Group object.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
public Object dolly() throws OculusException
{
	Group grp = null;
	grp = new Group();
	grp.setIID(getIID());
	grp.setObjectContext(getObjectContext());
	grp.setPersState(getPersState());
	if (getDefnObject() != null)
		grp.setDefnObject(getDefnObject());
	//if (getStateObject() != null)
	//  grp.setStateObject(getStateObject());
	grp._classIID = _classIID;
	grp._stateIID = _stateIID;
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
		grp.putAll(getProperties());
	grp.setName(getName());
	grp.setDescription(getDescription());
	grp._creatorIID = _creatorIID;
	grp._accessIID = _accessIID;
	grp.setCreationDate(getCreationDate());
	grp.setMessageAttached(hasMessageAttached());
	grp.setLinkAttached(hasLinkAttached());
	grp.setFileAttached(hasFileAttached());

	//specific to Group
	grp.setParentGroupIID(getParentGroupIID());
	return grp;
}
  //----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
	throws OculusException
  {
	if (key instanceof String)
	{
	  if (key.equals(LABEL_PARGROUPID))
		return _parGrpIID;
	  else if (key.equals(LABEL_GROUPID))
		return _grpIID;
	  //extended attr
	  else
		return super.get(key);
	}
	else
	  return null;
  }  
  public IIID getAccessorIID()
	throws ORIOException
  {  
	 return getIID();
  }  
   protected String getCreateQuery()
	throws OculusException
  {
	return "INSERT INTO "+TABLE+" "+
		   " ("+COL_OBJECTID+", "
			   +COL_CLASSID+", "
		   //    +COL_STATEID+", "
		   	 +COL_DELETESTATE+", "
			   +COL_GUID+", "
			   +COL_NAME+", "
			   +COL_DESCRIPTION+", "
			   +COL_CREATIONDATE+", "
			   +COL_CREATORID+", "
			   +COL_ACCESSID+", "
			   +COL_MESSAGEATTACHED+", "
			   +COL_FILEATTACHED+", "
			   +COL_LINKATTACHED+", "
			   //specific to Group
			   +COL_PARGROUPID+" " +
		   ") "+

		   
		   " VALUES "+
		   " ("+getIID().getLongValue()+","
			  +getDefnObject().getIID().getLongValue()+","
			 // +getStateObject().getIID().getLongValue()+","
			  +getDeleteState().getIntValue()+","
			  +"'"+getGUID().toString()+"',"
			  +"'"+SQLUtil.primer(getName())+"',"
			  +"'"+getDescription()+"',"
			  +"'"+getCreationDate().toString()+"',"
			  +getCreatorIID().getLongValue()+","
			  +"0,"
//              +getAccessIID().getLongValue()+","
			  +(hasMessageAttached()?"1":"0")+","
			  +(hasFileAttached()?"1":"0")+","
			  +(hasLinkAttached()?"1":"0")+","
			  //specific to Group
			  +(getParentGroupIID()==null?"null":""+getParentGroupIID().getLongValue())+""+
				   ") ";
  }    
  protected String getDeleteQuery()
	throws ORIOException
  {
	return " DELETE FROM "+TABLE+" "+
		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  //-------------------------- Protected Methods -----------------------------
  protected String getLoadQuery()
	throws ORIOException
  {
	return "SELECT * "+
		   "FROM "+TABLE+" "+
		   "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  public IIID getParentGroupIID()
	throws ORIOException
  {  
	 return _parGrpIID;
  }  
  protected String getUpdateQuery()
	throws OculusException
  {
	return " UPDATE "+TABLE+" "+
		   " SET "+
		   "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
		   " , "+COL_DESCRIPTION+"='"+getDescription()+"' "+
		 //  " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
		   " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		   " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
		   " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		   " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		   " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		   //specific to Group
		   " , "+COL_PARGROUPID+"= "+(getParentGroupIID()==null?"null":""+getParentGroupIID().getLongValue())+" "+

		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }    
  public IUserColl getUsers()
	throws OculusException
  {
	return getUsers(false);
  }  
  public IUserColl getUsers(boolean editable)
	throws OculusException
  {
	return (IUserColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"UserGroupColl",getIID(),editable);
  }  
  protected void load(IDataSet results)
	throws OculusException
	{
	if (results.getIID() == null)
	  results.setIID(results.getLong(COL_OBJECTID));    
	super.load(results);
	//specific to Group   
	setName(results.getString(COL_NAME));                // get name
	//setDescription(results.getString(COL_DESCRIPTION));  // get desc
	if (results.get(COL_PARGROUPID)!=null)
		setParentGroupIID(new SequentialIID(results.getLong(COL_PARGROUPID)));
	//setGroupIID(new SequentialIID(results.getLong(COL_STATEID)));
	
 }  
  public void put(Object key, Object value)
	throws OculusException
  {
	if (key instanceof String && value instanceof IRProperty)
	{
	  IRProperty property = (IRProperty)value;
	  if (key.equals(LABEL_PARGROUPID))
		setParentGroupIID((IIID)value);
	  else
		super.put(key,value);
	}
  }  
  public IGroup setParentGroupIID(IIID id)
	throws ORIOException
  {  
	if (getPersState().equals(PersState.UNMODIFIED))
	  setPersState(PersState.MODIFIED);
	_parGrpIID = id;
	return this;
  }  
}