/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.model;

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* ---             Zain Nemazie    2/29/00     Commented out super.load(results) because state is not supported.
*/

public class Organization extends BusinessObject implements IOrganization
{
  

  protected static String COL_PARENTORGID      = "PARENTORGID";
  protected static String COL_ISINSTALLOWNER   = "ISINSTALLOWNER";
  protected static String COL_BUSADDR1         = "BUSADDR1";
  protected static String COL_BUSADDR2         = "BUSADDR2";
  protected static String COL_CITY             = "CITY";
  protected static String COL_STATE            = "STATE";
  protected static String COL_COUNTRY          = "COUNTRY";
  protected static String COL_ZIPCODE          = "ZIPCODE";
  
  private static String PROP = "prop";
  protected static String LABEL_PARENTORGID      = "Parent Organization";
  protected static String LABEL_ISINSTALLOWNER   = "Is an internal user";
  protected static String LABEL_BUSADDR1         = PROP+IDCONST.BUSADDR1.getIIDValue();
  protected static String LABEL_BUSADDR2         = PROP+IDCONST.BUSADDR2.getIIDValue();
  protected static String LABEL_CITY             = PROP+IDCONST.CITY.getIIDValue();
  protected static String LABEL_STATE            = PROP+IDCONST.STATE.getIIDValue();
  protected static String LABEL_COUNTRY          = PROP+IDCONST.COUNTRY.getIIDValue();
  protected static String LABEL_ZIPCODE          = PROP+IDCONST.ZIPCODE.getIIDValue();

  protected Oid         _parentOrgIID;
  protected IRProperty   _isInstallOwner,         // boolean type
						 _busAddrLine1,           // string type
						 _busAddrLine2,           // string type
						 _city,                   // string type                 
						 _state,                  // string type
						 _country,                // string type
						 _zipcode;                // string type
					  
  //----------------------------- Public Constructor -------------------------
  /** Default constructor just initializes the Organization list */  
  
  public Organization() throws OculusException
  {
	super();
	TABLE                = "ORGANIZATION";
  COL_Guid             = "Guid";
	_parentOrgIID        = null;	
	_isInstallOwner   = new BMProperty(this);
  _busAddrLine1     = new BMProperty(this);
  _busAddrLine2     = new BMProperty(this);
  _city             = new BMProperty(this);                 
  _state            = new BMProperty(this);
  _country          = new BMProperty(this);
  _zipcode          = new BMProperty(this);
  
  
  _isInstallOwner.setDefnObject(IDCONST.ISINSTALLOWNER.getIIDValue());
  _busAddrLine1.setDefnObject(IDCONST.BUSADDR1.getIIDValue());
  _busAddrLine2.setDefnObject(IDCONST.BUSADDR2.getIIDValue());
  _city.setDefnObject(IDCONST.CITY.getIIDValue());
  _state.setDefnObject(IDCONST.USERSTATE.getIIDValue());
  _country.setDefnObject(IDCONST.COUNTRY.getIIDValue());
  _zipcode.setDefnObject(IDCONST.ZIPCODE.getIIDValue());
  
  }    
//----------------- IPoolable Methods ------------------------------------
/**
  *  Returns a copy of the current Organization object.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
public Object dolly() throws OculusException
{
	Organization org = null;
	org = new Organization();
	org.setOid(getOid());
	org.setObjectContext(getObjectContext());
	org.setPersState(getPersState());
	//if (getDefnObject() != null)
	//  org.setDefnObject(getDefnObject());
	//if (getStateObject() != null)
	//  org.setStateObject(getStateObject());
	org._classIID = _classIID;
	//org._stateIID = _stateIID;
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
		org.putAll(getProperties());
	org.setName(getName());
	// org.setDescription(getDescription());
	org._creatorIID = _creatorIID;
	org._accessIID = _accessIID;
	org.setCreationDate(getCreationDate());
	org.setMessageAttached(hasMessageAttached());
	org.setLinkAttached(hasLinkAttached());
	org.setFileAttached(hasFileAttached());
  org.setDeleteState(getDeleteState());
  
	//specific to organy
	org.setIsInstallOwner(getIsInstallOwner());
	org.setBusAddressLine1(getBusAddressLine1());
	org.setBusAddressLine2(getBusAddressLine2());
	org.setCity(getCity());
	org.setState(getState());
	org.setCountry(getCountry());
	org.setZipcode(getZipcode());
	org._parentOrgIID = _parentOrgIID;
	org.setPersState(getPersState());
	return org;
}
  //----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
	throws OculusException
  {
	if (key instanceof String)
	{
	  if (key.equals(LABEL_BUSADDR1))
		return _busAddrLine1;
	  else if (key.equals(LABEL_BUSADDR2))
		return _busAddrLine2;
	  else if (key.equals(LABEL_CITY))
		return _city;
	  else if (key.equals(LABEL_STATE))
		return _state;
	  else if (key.equals(LABEL_COUNTRY))
		return _country;
	  else if (key.equals(LABEL_ZIPCODE))
		return _zipcode;
	  else if (key.equals(LABEL_PARENTORGID))
		return _parentOrgIID;
	  else if (key.equals(LABEL_ISINSTALLOWNER))
		return _isInstallOwner;
	  else
		return super.get(key);
	}
	else
	  return null;
  }  
  public String getBusAddressLine1()
	throws OculusException
  {
	return (String)_busAddrLine1.getValue();
  }  
  public String getBusAddressLine2()
	throws OculusException
  {
	return (String)_busAddrLine2.getValue();
  }  
  public String getCity()
	throws OculusException
  {
	return (String)_city.getValue();
  }  
  public String getCountry()
	throws OculusException
  {
	return (String)_country.getValue();
  }  
  protected String getCreateQuery()
	throws OculusException
  {
	return "INSERT INTO "+TABLE+" "+
		   " ("+COL_OBJECTID+", "
			   +COL_CLASSID+", "
			 //  +COL_STATEID+", "
			 	 +COL_DELETESTATE+", "
			   +COL_Guid+", "
			   +COL_NAME+", "
	   //        +COL_DESCRIPTION+", "
			   +COL_CREATIONDATE+", "
			   +COL_CREATORID+", "
			   +COL_ACCESSID+", "
			  // +COL_DISCUSSATTACHED+", "
			   +COL_FILEATTACHED+", "
			   +COL_LINKATTACHED+", "
			   //specific to organy
			   +COL_ISINSTALLOWNER+", "
			   +COL_BUSADDR1+", "
			   +COL_BUSADDR2+", "
			   +COL_CITY+", "
			   +COL_STATE+", "
			   +COL_COUNTRY+", "
			   +COL_ZIPCODE+", "
			   +COL_PARENTORGID+" " +
		   ") "+

		   
		   " VALUES "+
		   " ("+ getOid().getLongValue()+","
			  +getDefnObject().getIID().getLongValue()+","
			  //+getStateObject().getOid().getLongValue()+","
			  +getDeleteState().getIntValue()+","
			  +"'"+getGuid().toString()+"',"
			  +"'"+SQLUtil.primer(getName())+"',"
		 //     +"'"+getDescription()+"',"
			  +"'"+getCreationDate().toString()+"',"
			  +getCreatorIID().getLongValue()+","
			  +"0,"
 //             +getAccessIID().getLongValue()+","
//			  +(hasDiscussAttached()?"1":"0")+","
			  +(hasFileAttached()?"1":"0")+","
			  +(hasLinkAttached()?"1":"0")+","
			  //specific to organy
			  +(getIsInstallOwner()?"1":"0")+", "
			  +"'"+SQLUtil.primer(getBusAddressLine1())+"',"
			  +"'"+SQLUtil.primer(getBusAddressLine1())+"',"
			  +"'"+SQLUtil.primer(getCity())+"',"
			  +"'"+SQLUtil.primer(getState())+"',"
			  +"'"+SQLUtil.primer(getCountry())+"',"
			  +"'"+SQLUtil.primer(getZipcode())+"',"
			  +(getParentOrgIID()==null?"null":""+getParentOrgIID().getLongValue())+" "+
		   ") ";
  }      
  protected String getDeleteQuery()
	throws QueryException
  {
	return " DELETE FROM "+TABLE+" "+
		   " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }  
  public boolean getIsInstallOwner()
	throws OculusException
  {
	if (_isInstallOwner.getValue()!=null)
		return ((Boolean)_isInstallOwner.getValue()).booleanValue();  
 	else
	  return false;
	}
  //-------------------------- Protected Methods -----------------------------
  protected String getLoadQuery()
	throws QueryException
  {
	return "SELECT * "+
		   "FROM "+TABLE+" "+
		   "WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }  
  //------------------------ ACCESSORS -------------------------------------
  
  public Oid getParentOrgIID()
	throws QueryException
  {  
	 return _parentOrgIID;
  }  
  public String getState()
	throws OculusException
  {
	return (String)_state.getValue();
  }  
  protected String getUpdateQuery()
	throws OculusException
  {
	return " UPDATE "+TABLE+" "+
		   " SET "+
		   "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
		  // " , "+COL_DESCRIPTION+"='"+getDescription()+"' "+
		   //" , "+COL_STATEID+"= "+getStateObject().getOid().getLongValue()+" "+
		   " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		   " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
		   " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		   " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		   " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		   //specific to organy
		   " , "+COL_ISINSTALLOWNER+"= "+(getIsInstallOwner()?"1":"0")+" "+
		   " ,  "+COL_BUSADDR1+"='"+SQLUtil.primer(getBusAddressLine1())+"' "+
		   " ,  "+COL_BUSADDR2+"='"+SQLUtil.primer(getBusAddressLine2())+"' "+
		   " ,  "+COL_CITY+"='"+SQLUtil.primer(getCity())+"' "+
		   " ,  "+COL_STATE+"='"+SQLUtil.primer(getState())+"' "+
		   " ,  "+COL_COUNTRY+"='"+SQLUtil.primer(getCountry())+"' "+
		   " ,  "+COL_ZIPCODE+"='"+SQLUtil.primer(getZipcode())+"' "+
		   " , "+COL_PARENTORGID+"= "+(getParentOrgIID()==null?"null":""+getParentOrgIID().getLongValue())+" "+


		   " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }      
  public IUserColl getUsers()
	throws OculusException
  {
	return getUsers(false);
  }  
  public IUserColl getUsers(boolean editable)
	throws OculusException
  {
	return (IUserColl)getObjectContext().getCRM().getCompObject(getObjectContext(), "UserColl", getOid(), editable);
  }  
  public String getZipcode()
	throws OculusException
  {
	return (String)_zipcode.getValue();
  }  
  protected void load(IDataSet results)
	throws OculusException
  {
	if (results.getIID() == null)
	  results.setIID(results.getLong(COL_OBJECTID)); 
	setPersState(PersState.PARTIAL);
	
	_guid = new Guid(results.getString(COL_Guid).trim());       // get Guid
	_iid = new Oid(results.getLong(COL_OBJECTID));    // get IID
	_classIID = new Oid(results.getLong(COL_CLASSID)); // get class
//    if (getDefnObject().hasStateMachine())
	_stateIID = new Oid(results.getLong(COL_STATEID)); // get state
	setCreatorIID(new Oid(results.getLong(COL_CREATORID)));
	setAccessIID(new Oid(results.getLong(COL_ACCESSID)));
	setName(results.getString(COL_NAME));                // get name
	//setDescription(results.getString(COL_DESCRIPTION));  // get desc
	setCreationDate(results.getTimestamp(COL_CREATIONDATE));
	setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
	setFileAttached(results.getBoolean(COL_FILEATTACHED));
	setLinkAttached(results.getBoolean(COL_LINKATTACHED));
	
	//specific to org
	if (results.get(COL_PARENTORGID)!=null)
		_parentOrgIID = new Oid(results.getLong(COL_PARENTORGID));
	setBusAddressLine1(results.getString(COL_BUSADDR1).trim());
	setBusAddressLine2(results.getString(COL_BUSADDR2).trim());
	setCity(results.getString(COL_CITY).trim());
	setState(results.getString(COL_STATE).trim());
	setCountry(results.getString(COL_COUNTRY).trim());
	setZipcode(results.getString(COL_ZIPCODE).trim());
	setIsInstallOwner(results.getInt(COL_ISINSTALLOWNER) == 1); 
	
  }        
  public void put(Object key, Object value)
	throws OculusException
  {
	if (key instanceof String && value instanceof IRProperty)
	{
	  IRProperty property = (IRProperty)value;
	  if (key.equals(LABEL_BUSADDR1))
		setBusAddressLine1((String)property.getValue());
	  else if (key.equals(LABEL_BUSADDR2))
		setBusAddressLine2((String)property.getValue());
	  else if (key.equals(LABEL_CITY))
		setCity((String)property.getValue());
	  else if (key.equals(LABEL_STATE))
		setState((String)property.getValue());
	  else if (key.equals(LABEL_COUNTRY))
		setCountry((String)property.getValue());
	  else if (key.equals(LABEL_ZIPCODE))
		setZipcode((String)property.getValue());
	  else if (key.equals(LABEL_PARENTORGID))
		setParentOrgIID((Oid)value);
	  else if (key.equals(LABEL_ISINSTALLOWNER))
		setIsInstallOwner(((Boolean)property.getValue()).booleanValue());
	  //extended attr
	  else
		super.put(key,value);
	}
  }  
  public IOrganization setBusAddressLine1(String busAddrLine1)
	throws QueryException
  {
	_busAddrLine1.setValue(busAddrLine1);
	return this;
  }  
  public IOrganization setBusAddressLine2(String busAddrLine2)
	throws QueryException
  {
	_busAddrLine2.setValue(busAddrLine2);
	return this;
  }  
  public IOrganization setCity(String city)
	throws QueryException
  {
	_city.setValue(city);
	return this;
  }  
  public IOrganization setCountry(String country)
	throws QueryException
  {
	_country.setValue(country);
	return this;
  }  
  //should there be no set method since it creates a potential security
  //hole?
  public IOrganization setIsInstallOwner(boolean isInstallOwner)
	throws QueryException
  {
	_isInstallOwner.setValue(new Boolean(isInstallOwner));
	return this;
  }  
  //------------------------ MUTATORS -------------------------------------
  public IOrganization setParentOrgIID(Oid id)
	throws QueryException
  {  
	 if (getPersState().equals(PersState.UNMODIFIED))
	  setPersState(PersState.MODIFIED);
	 _parentOrgIID = id;
	 return this;
  }  
  public IOrganization setState(String state)
	throws QueryException
  {
	_state.setValue(state);
	return this;
  }  
  public IOrganization setZipcode(String zipcode)
	throws QueryException
  {
	_zipcode.setValue(zipcode);
	return this;
  }
  
  
  
  public static Oid getIIDForName(ObjectContext context, String name)
    throws OculusException
  {
    Oid orgIID = null;
    
    IRConnection conn = context.getRepository().getDataConnection(context);
    IDataSet result = conn.createProcessor().retrieve("SELECT OBJECTID FROM ORGANIZATION WHERE NAME='"+SQLUtil.primer(name)+"'");
    if (result.next())
      orgIID = context.getRepository().makeReposID(result.getLong("OBJECTID"));
    
    return orgIID;
  }
}