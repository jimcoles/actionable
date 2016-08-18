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

import java.sql.*;
import java.util.*;

/**
* Filename:    FeatureCategoryLink.java
* Date:        3/10/00
* Description: Provides the functionality for a basic feature revision.
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
* ---             Saleem Shafi    3/13/00     Added getPinnedFeatureRevisionObject() method and added logic
*                                             to getFeatureObject() that sets the current FeatCatLink object
*                                             as the IFeature's FeatCatLink.
* BUG00061        Saleem Shafi    5/15/00     Uncommented out the lines in the getCreateQuery() for most
*                                             of the link attributes.
* BUG00062        Saleem Shafi    5/15/00     Changed the entries for priority,test,diff in the getCreateQuery()
*                                             to return the iid iff it isn't null.
*/

public class BaselineFeatureCategoryLink extends FeatureCategoryLink implements IBaselineFeatureCategoryLink, IRPropertyMap
{
  protected String COL_REVISIONID = "REVISIONID";

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public BaselineFeatureCategoryLink() throws OculusException
  {
    super();
    TABLE = "BCATFEATURELINK";

    COL_OBJECTID 				= "LINK_OBJECTID";
    COL_GUID 						= "LINK_GUID";
    COL_CLASSID 				= "LINK_CLASSID";
    COL_STATEID 				= "LINK_STATEID";
    COL_NAME 						= "LINK_NAME";
    COL_DESCRIPTION 		= "LINK_DESCRIPTION";
    COL_CREATIONDATE 		= "LINK_CREATIONDATE";
    COL_CREATORID 			= "LINK_CREATORID";
    COL_ACCESSID 				= "LINK_ACCESSID";
    COL_MESSAGEATTACHED = "LINK_DISCUSSATTACHED";
    COL_FILEATTACHED 		= "LINK_FILEATTACHED";
    COL_LINKATTACHED 		= "LINK_LINKATTACHED";
    COL_DELETESTATE 		= "LINK_DELETESTATE";
//    COL_PINNEDREVID = "REVISIONID";
    COL_FEATUREID = null;
  }


//----------------- IFeatureRevision Methods ------------------------------------

  public void define(IFeatureCategoryLink featcatlink)
    throws OculusException
  {
    setDefnObject(featcatlink.getDefnObject());
    setStateObject(featcatlink.getStateObject());
    setPriorityLevel(featcatlink.getPriorityIID());
    setTestLevel(featcatlink.getTestIID());
    setDifficultyLevel(featcatlink.getDifficultyIID());
    setOrderNum(featcatlink.getOrderNum());
    setPercentComplete(featcatlink.getPercentComplete());
    setEstimatedDevTime(featcatlink.getEstimatedDevTime());
    setActualDevTime(featcatlink.getActualDevTime());
    setEstimatedTestTime(featcatlink.getEstimatedTestTime());
    setDevStartDate(featcatlink.getDevStartDate());
    setDevEndDate(featcatlink.getDevEndDate());
    setTestEndDate(featcatlink.getTestEndDate());
    IFeature thisFeature = featcatlink.getFeatureObject();
    setFeatureObject(thisFeature);
    setFeatureRevisionObject(thisFeature.getFeatureRevisionObject());
  }
  
  protected String getUpdateQuery()
    throws OculusException
  {
    return " UPDATE "+TABLE+" "+
           " SET "+
           "   STATEID= "+getStateObject().getIID().getLongValue()+" "+
           " , DELETESTATE= "+getDeleteState().getIntValue()+" "+
           " , ACCESSID= "+getAccessIID().getLongValue()+" "+
           " , DISCUSSATTACHED= "+(hasMessageAttached()?"1":"0")+" "+
           " , FILEATTACHED= "+(hasFileAttached()?"1":"0")+" "+
           " , LINKATTACHED= "+(hasLinkAttached()?"1":"0")+" "+
           " , "+(getPriorityLevel() != null ? " , "+COL_PRIORITYID+"="+_priority.getValue()+" ":"")+
           " , "+(getTestLevel() != null ? " , "+COL_TESTLEVELID+"="+_test.getValue()+" ":"")+
           " , "+(getDifficultyLevel() != null ? " , "+COL_DIFFLEVELID+"="+_difficulty.getValue()+" ":"")+
           " , "+COL_ORDERNUM+"="+getOrderNum()+" "+
           " , "+COL_PERCENTCOMPLETED+"="+getPercentComplete()+" "+
           " , "+COL_ESTDEVTIME+"="+getEstimatedDevTime()+" "+
           " , "+COL_ACTUALDEVTIME+"="+getActualDevTime()+" "+
//           " , "+COL_DEVSTARTDATE+"='"+getDevStartDate()+"' "+
//           " , "+COL_DEVENDDATE+"='"+getDevEndDate()+"' "+
           " , "+COL_ESTTESTTIME+"="+getEstimatedTestTime()+" "+
//           " , "+COL_TESTENDDATE+"='"+getTestEndDate()+"' "+
           " , "+COL_REVISIONID+"="+getFeatureRevisionObject().getIID().getLongValue()+" "+
           " , "+COL_CATEGORYID+"="+getCategoryObject().getIID().getLongValue()+" "+
           " WHERE OBJECTID="+getIID().getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
           " (OBJECTID, "
               +"CLASSID, "
               +"STATEID, "
               +"DELETESTATE, "
               +"GUID, "
               +"CREATIONDATE, "
               +"CREATORID, "
               +"ACCESSID, "
               +"DISCUSSATTACHED, "
               +"FILEATTACHED, "
               +"LINKATTACHED, "
               +COL_REVISIONID+", "
               +(getPriorityLevel() != null ? COL_PRIORITYID+", ":"")
               +(getTestLevel() != null ? COL_TESTLEVELID+", ":"")
               +(getDifficultyLevel() != null ? COL_DIFFLEVELID+", ":"")
               +COL_ORDERNUM+", "
               +COL_PERCENTCOMPLETED+", "
               +COL_ESTDEVTIME+", "
               +COL_ACTUALDEVTIME+", "
               +COL_ESTTESTTIME+", "
               +(getDevStartDate() == null?"":COL_DEVSTARTDATE+", ")
               +(getDevEndDate() == null?"":COL_DEVENDDATE+", ")
               +(getTestEndDate() == null?"":COL_TESTENDDATE+", ")
               +COL_CATEGORYID+" "
           +") "+
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
              +getFeatureRevisionObject().getIID().getLongValue()+", "
              +(getPriorityLevel() != null ? _priority.getValue()+", ":"")
              +(getTestLevel() != null ? _test.getValue()+", ":"")
              +(getDifficultyLevel() != null ? _difficulty.getValue()+", ":"")
              +getOrderNum()+", "
              +getPercentComplete()+", "
              +getEstimatedDevTime()+", "
              +getActualDevTime()+", "
              +getEstimatedTestTime()+", "
              +(getDevStartDate() == null?"":"'"+getDevStartDate()+"', ")
              +(getDevEndDate() == null?"":"'"+getDevEndDate()+"', ")
              +(getTestEndDate() == null?"":"'"+getTestEndDate()+"', ")
              +getCategoryObject().getIID().getLongValue()+" "
           +") ";
  } 

  protected String getLoadQuery()
    throws OculusException
  {
    return " SELECT  link.OBJECTID AS LINK_OBJECTID, link.GUID AS LINK_GUID, link.CLASSID AS LINK_CLASSID, link.STATEID AS LINK_STATEID, "+
  								"link.ACCESSID AS LINK_ACCESSID, link.CREATORID AS LINK_CREATORID, link.CREATIONDATE AS LINK_CREATIONDATE, link.DELETESTATE AS LINK_DELETESTATE, "+
  								"link.FILEATTACHED AS LINK_FILEATTACHED, link.LINKATTACHED AS LINK_LINKATTACHED, link.DISCUSSATTACHED AS LINK_DISCUSSATTACHED, "+
  								"NULL AS LINK_FEATUREID, "+
  							"link.REVISIONID AS PINNEDREVID, link.ORDERNUM, link.DEVSTARTDATE, link.DEVENDDATE, link.ESTDEVTIME, link.ACTUALDEVTIME, link.ESTTESTTIME, link.ESTIMATEDRELEASEDATE, "+
  							"link.ACTUALRELEASEDATE, link.TESTENDDATE, link.PERCENTCOMPLETED, link.CATEGORYID, link.PRIORITYID, link.TESTLEVELID, link.DIFFLEVELID "+
           "FROM "+TABLE+" link "+
           "WHERE OBJECTID="+getIID();
  } 

  //------------------------- MUTATORS ------------------------------
  public IBusinessObject setFeatureRevisionObject(IFeatureRevision rev)
    throws ORIOException
  {
    return setPinnedFeatureRevisionObject(rev);
  }

  //------------------------- ACCESSORS -----------------------------
  public ICategory getCategoryObject(boolean editable)
    throws OculusException
  {
    if (_originalCatIID != null)
      return (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineCategory",_originalCatIID,editable);
    else
      return null;
  }

  public IFeatureRevision getFeatureRevisionObject()
    throws OculusException
  {
    return getPinnedFeatureRevisionObject();
  }

  public IFeature getFeatureObject()
    throws OculusException
  {
    IFeatureRevision rev = getFeatureRevisionObject();
    if (rev == null)
      return null;      
    IFeature feat = rev.getFeatureObject();
    feat.setFeatureCategoryLinkObject(this);
    return feat;
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
    BaselineFeatureCategoryLink rev = null;
      rev = new BaselineFeatureCategoryLink();
      rev.setIID(getIID());
      rev.setObjectContext(getObjectContext());
      rev.setPersState(getPersState());
      rev._classIID = _classIID;
      rev._stateIID = _stateIID;
      rev.setDeleteState(getDeleteState());
  //Saleem added to this line
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

      rev._pinnedRevIID = _pinnedRevIID;
      rev.setPriorityLevel(getPriorityIID());
      rev.setTestLevel(getTestIID());
      rev.setDifficultyLevel(getDifficultyIID());
      rev.setOrderNum(getOrderNum());
      rev.setPercentComplete(getPercentComplete());
      rev.setEstimatedDevTime(getEstimatedDevTime());
      rev.setActualDevTime(getActualDevTime());
      rev.setEstimatedTestTime(getEstimatedTestTime());
      rev.setDevStartDate(getDevStartDate());
      rev.setDevEndDate(getDevEndDate());
      rev.setTestEndDate(getTestEndDate());
      rev._originalCatIID = _originalCatIID;
    return rev;
  }
/********************************************


**************************/
}