package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    HyperLink.java
* Date:        2/16/00
* Description: Provides the functionality for a basic hyperlink attachment.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* ---             Saleem Shafi    2/24/00     Made this class use the base-class for
*                                             load(), delete(), get() and put() to
*                                             increase reuseability.  Also moved the IRPropertyMap
*                                             interface to the implementation object instead of the
*                                             interface to force clients to use the getProperities() method.
*                                             Note: Any overriding of protected Strings must happen in the
*                                             constructor not by defining a new variable.
*/
public class HyperLink extends BusinessObject implements IHyperLink
{
  protected String COL_PAROBJECTID = "PAROBJECTID";
  protected String COL_URL = "URL";
  protected String COL_TYPE = "TYPE";
  public static final String COL_LABEL = "LABEL";
  public static final String COL_SHORTDESCRIPTION = "SHORTDESCRIPTION";

  protected IIID _parentObjIID;
  protected IRProperty _url;
  protected HyperLinkType _type;



  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public HyperLink() throws OculusException
  {
    super();
    TABLE = "EXTHYPERLINK";
    COL_GUID = "GUID";
    _url = new BMProperty(this);
    _url.setDefnObject(IDCONST.URL.getIIDValue());
    _type = HyperLinkType.NORMAL;
  }

  //-------------------------- Protected Methods -----------------------------
  protected String getLoadQuery()
    throws OculusException
  {
    return "SELECT * "+
           "FROM "+TABLE+" "+
           "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 
  
  protected String getUpdateQuery()
    throws OculusException
  {
    return " UPDATE "+TABLE+" "+
           " SET "+
           "   "+COL_LABEL+"='"+SQLUtil.primer(getName())+"' "+
           " , "+COL_SHORTDESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
           " , "+COL_DELETESTATE+"="+getDeleteState().getIntValue()+" "+
           (!getDefnObject().hasStateMachine()?"":" , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" ")+
           " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
           " , "+COL_PAROBJECTID+"= "+_parentObjIID.getLongValue()+" "+
           " , "+COL_URL+"='"+SQLUtil.primer(getURL())+"' "+
           " , "+COL_TYPE+"="+getLinkType().getIntValue()+" "+
           " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
           " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
           " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
           " ("+COL_OBJECTID+", "
               +COL_CLASSID+", "
               +(!getDefnObject().hasStateMachine()?"":COL_STATEID+", ")
               +COL_PAROBJECTID+", "
               +COL_DELETESTATE+", "
               +COL_GUID+", "
               +COL_LABEL+", "
               +COL_SHORTDESCRIPTION+", "
               +COL_URL+", "
               +COL_TYPE+", "
               +COL_CREATIONDATE+", "
               +COL_CREATORID+", "
               +COL_ACCESSID+", "
               +COL_MESSAGEATTACHED+", "
               +COL_FILEATTACHED+", "
               +COL_LINKATTACHED+
           ") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +(!getDefnObject().hasStateMachine()?"":getStateObject().getIID().getLongValue()+",")
              +_parentObjIID.getLongValue()+","
              +getDeleteState().getIntValue()+","
              +"'"+getGUID().toString()+"',"
              +"'"+SQLUtil.primer(getName())+"',"
              +"'"+SQLUtil.primer(getDescription())+"',"
              +"'"+SQLUtil.primer(getURL())+"',"
              +getLinkType().getIntValue()+", "
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
              +getAccessIID().getLongValue()+","
              +(hasMessageAttached()?"1":"0")+","
              +(hasFileAttached()?"1":"0")+","
              +(hasLinkAttached()?"1":"0")+
           ") ";
  } 

  protected String getDeleteQuery()
    throws OculusException
  {
    return " DELETE FROM "+TABLE+" "+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 

  protected void load(IDataSet results)
    throws OculusException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));
    super.load(results);
        
    setURL(results.getString(COL_URL));
    setName(results.getString(COL_LABEL));
    setDescription(results.getString(COL_SHORTDESCRIPTION));
    _parentObjIID = new SequentialIID(results.getLong(COL_PAROBJECTID));
    _type = HyperLinkType.getInstance(results.getInt(COL_TYPE));
  }



//----------------- ILink Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }

  //------------------------ MUTATORS -------------------------------
  public IHyperLink setParentObject(IBusinessObject parent)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parentObjIID = parent.getIID();
    return this;
  }
    
  public IHyperLink setURL(String url)
    throws ORIOException
  {
    _url.setValue(url);
    return this;
  }

  public IHyperLink setLinkType(HyperLinkType hlt) 
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _type = hlt;
    return this;
  }
  
  //------------------------ ACCESSORS -------------------------------

  public HyperLinkType getLinkType() 
    throws ORIOException
  { return _type; }

  
  public String getURL()
    throws OculusException
  {
    if (_url.getValue() != null)
      return (String)_url.getValue();
    else
      return null;
  }

  public IHyperLink createCopy()
    throws OculusException
  {
    IIID classIID = IDCONST.HYPERLINK.getIIDValue();
    IHyperLink newLink = (IHyperLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"HyperLink",(IDataSet)null,true);
    newLink.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));

    newLink.setName(getName());
    newLink.setDescription(getDescription());
    newLink.setMessageAttached(hasMessageAttached());
    newLink.setLinkAttached(hasLinkAttached());
    newLink.setFileAttached(hasFileAttached());
    newLink.setLinkType(getLinkType());
    newLink.setURL(getURL());

    return newLink;
  }

//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
    throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_URL))
        return _url;
      else
        return super.get(key);
    }
    else
      return null;
  }
  
  public void put(Object key, Object value)
    throws OculusException
  {
    if (key instanceof String && value instanceof IRProperty)
    {
      IRProperty property = (IRProperty)value;
      if (key.equals(LABEL_URL))
        setURL((String)property.getValue());
      else
        super.put(key,value);
    }
  }

 
//----------------- IPoolable Methods ------------------------------------
  /**
  *  Returns a copy of the current product object.  It does not copy the
  * IObjectContext.  
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object dolly() throws OculusException
  {
    HyperLink link = null;
      link = new HyperLink();
      link.setIID(getIID());
      link.setObjectContext(getObjectContext());
      link.setPersState(getPersState());
      link._classIID = _classIID;
      link._stateIID = _stateIID;
      link.setDeleteState(getDeleteState());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        link.putAll(getProperties());
      link.setName(getName());
      link.setDescription(getDescription());
      link._creatorIID = _creatorIID;
      link._accessIID = _accessIID;
      link.setCreationDate(getCreationDate());
      link.setMessageAttached(hasMessageAttached());
      link.setLinkAttached(hasLinkAttached());
      link.setFileAttached(hasFileAttached());
      link._type = _type;
      link._parentObjIID = _parentObjIID;
      link.setURL(getURL());
    return link;
  }

/********************************************


**************************/
}