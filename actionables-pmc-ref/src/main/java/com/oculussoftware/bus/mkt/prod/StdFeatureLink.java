package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    StdFeatureLink.java
* Date:        4/5/00
* Description: Provides the functionality for a basic Standard revision.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Cuihua Zhang
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
* BUG01936        Cuihua Zhang    8/21/2000    override getName()        
*/

public class StdFeatureLink extends BusinessObject implements IStdFeatureLink
{
  protected String COL_FEATUREID = "FEATUREID";
  protected String COL_STDCOLLECTIONID = "STDCOLLECTIONID";
  

  protected IIID _featIID,
                 _stdIID;

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public StdFeatureLink() throws OculusException
  {
    super();
    TABLE = "STDFEATURELINK";
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
           "  " +COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
           " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
           " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
           " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
           " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
           " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
           " , "+COL_FEATUREID+"="+getFeatureObject().getIID().getLongValue()+" "+
           " , "+COL_STDCOLLECTIONID+"="+getStdCollectionObject().getIID().getLongValue()+" "+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
           " ("+COL_OBJECTID+", "
               +COL_CLASSID+", "
               +COL_STATEID+", "
               +COL_DELETESTATE+", "
               +COL_GUID+", "
               +COL_CREATIONDATE+", "
               +COL_CREATORID+", "
               +COL_ACCESSID+", "
               +COL_MESSAGEATTACHED+", "
               +COL_FILEATTACHED+", "
               +COL_LINKATTACHED+", "
               +COL_FEATUREID+", "
               
               +COL_STDCOLLECTIONID+
          ") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +getStateObject().getIID().getLongValue()+","
              +getDeleteState().getIntValue()+","
              +"'"+getGUID().toString()+"',"
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
              +getAccessIID().getLongValue()+","
              +(hasMessageAttached()?"1":"0")+","
              +(hasFileAttached()?"1":"0")+","
              +(hasLinkAttached()?"1":"0")+","
              +getFeatureObject().getIID().getLongValue()+", "
              +getStdCollectionObject().getIID().getLongValue()+" "
           +") ";
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
    setPersState(PersState.PARTIAL);
    
    super.load(results);
 
    if (COL_FEATUREID != null)
      _featIID = new SequentialIID(results.getLong(COL_FEATUREID));
    
    if (COL_STDCOLLECTIONID != null)
      _stdIID = new SequentialIID(results.getLong(COL_STDCOLLECTIONID));
  }


  public IBusinessObject setMessageAttached(boolean messageAttached)
    throws OculusException
  {
    IFeature feat = getFeatureObject();
    if (feat != null)
     feat.setMessageAttached(messageAttached);
    return this;
  }
    
  public IBusinessObject setFileAttached(boolean fileAttached)
    throws OculusException
  {
    IFeature feat = getFeatureObject();
    if (feat != null)
     feat.setFileAttached(fileAttached);
    return this;
  }
    
  public IBusinessObject setLinkAttached(boolean linkAttached)
    throws OculusException
  {
    IFeature feat = getFeatureObject();
    if (feat != null)
     feat.setLinkAttached(linkAttached);
    return this;
  }



//----------------- IFeatureRevision Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }
  
  public IStdFeatureLink setStdCollectionObject(IStandardsCollection std)
    throws ORIOException
  {
    _stdIID = std.getIID();
    return this;
  }

  //public IBusinessObject setFeatureObject(IFeature feature)
  public IStdFeatureLink setFeatureObject(IFeature feature)
  
  	throws ORIOException
  {
    _featIID = feature.getIID();
    return this;
  }

  
  //------------------------- ACCESSORS -----------------------------

  public String getName()
    throws OculusException
  {
    if (getFeatureObject() == null)
      return null;
    return getFeatureObject().getName();
  }

  public IStandardsCollection getStdCollectionObject()
    throws OculusException
  {
    if (_stdIID != null)
      return (IStandardsCollection)getObjectContext().getCRM().getCompObject(getObjectContext(),"StandardsCollection",_stdIID,isLocked());
    else
      return null;
  }

  public IFeature getFeatureObject()
    throws OculusException
  {
    if (_featIID != null)
    {
      IFeature feat = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",_featIID,isLocked());
      feat.setStandardFeatureLinkObject(this);
      return feat;
    }
    else
    {
      return null;
    }
  }
  
  public String getFeatureName()
    throws OculusException
  {
    if (getFeatureObject() == null)
      return null;
    return getFeatureObject().getName();
  }

  public String getStdCollectionName()
    throws OculusException
  {
    if (getStdCollectionObject() == null)
      return null;
    return getStdCollectionObject().getName();
  }
  
  public String getStdCollectionDescription()
    throws OculusException
  {
    if (getStdCollectionObject() == null)
      return null;
    return getStdCollectionObject().getDescription();
  }
  
  public String getFeatureDescription()
    throws OculusException
  {
    if (getStdCollectionObject() == null)
      return null;
    return getFeatureObject().getDescription();
  }





//----------------- IRPropertyMap Methods---------------------------------
 
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
    StdFeatureLink rev = null;
      rev = new StdFeatureLink();
      rev.setIID(getIID());
      rev.setObjectContext(getObjectContext());
      rev.setPersState(getPersState());
      rev._classIID = _classIID;
      rev._stateIID = _stateIID;
      rev.setDeleteState(getDeleteState());
      
      if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
         rev.putAll(getProperties());
      
      rev.setName(getName());
      rev.setDescription(getDescription());
      rev._creatorIID = _creatorIID;
      rev._accessIID = _accessIID;
      rev.setCreationDate(getCreationDate());
      rev.setMessageAttached(hasMessageAttached());
      rev.setLinkAttached(hasLinkAttached());
      rev.setFileAttached(hasFileAttached());

      rev._featIID = _featIID;
      
      rev._stdIID = _stdIID;
    return rev;
  }
/********************************************


**************************/
}