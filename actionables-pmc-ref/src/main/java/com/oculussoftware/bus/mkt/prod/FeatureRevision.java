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
* Filename:    FeatureRevision.java
* Date:        2/17/00
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
* ---             Saleem Shafi    2/18/00     Changed the notion of a FeatureRevision to
*                                             be a second-class notion, under a Feature object.
* ---             Saleem Shafi    2/24/00     Made this class use the base-class for
*                                             load(), delete(), get() and put() to
*                                             increase reuseability.  Also moved the IRPropertyMap
*                                             interface to the implementation object instead of the
*                                             interface to force clients to use the getProperities() method.
*                                             Note: Any overriding of protected Strings must happen in the
*                                             constructor not by defining a new variable.
* ---             Saleem Shafi    3/13/00     Added logic to getFeatureObject() to return an IFeature object
*                                             that uses the current FeatureRevision object as its current FeatureRevision.
*	BUG00597				Saleem Shafi		6/2/00			Fixed the load() method for edits.
*/
public class FeatureRevision extends BusinessObject implements IFeatureRevision, IRPropertyMap
{
  protected String COL_FEATUREID = "FEATUREID";
  protected String COL_ISCHANGEABLE = "ISCHANGEABLE";
  protected String COL_COMMENT = "USERCOMMENT";
  protected String COL_REVISIONNAME = "REVISIONNAME";
  protected String COL_AUTOREVISIONLABEL = "AUTOREVISIONLABEL";

  protected IIID _featIID;
  protected IRProperty _isChangeable,
                       _comment,
                       _revisionName,
                       _autoRevLabel; 

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public FeatureRevision() throws OculusException
  {
    super();
    TABLE = "FEATUREREVISION";
    COL_OBJECTID 				= "REV_OBJECTID";
    COL_GUID 						= "REV_GUID";
    COL_CLASSID 				= "REV_CLASSID";
    COL_STATEID 				= "REV_STATEID";
    COL_NAME 						= "NAME";
    COL_DESCRIPTION 		= "DESCRIPTION";
    COL_CREATIONDATE 		= "REV_CREATIONDATE";
    COL_CREATORID 			= "REV_CREATORID";
    COL_ACCESSID 				= "REV_ACCESSID";
    COL_MESSAGEATTACHED = "REV_DISCUSSATTACHED";
    COL_FILEATTACHED 		= "REV_FILEATTACHED";
    COL_LINKATTACHED 		= "REV_LINKATTACHED";
    COL_DELETESTATE 		= "REV_DELETESTATE";

    _description.setRequired(true);

    _isChangeable = new BMProperty(this);
    _isChangeable.setDefnObject(IDCONST.ISCHANGE.getIIDValue());
    _comment = new BMProperty(this);
    _comment.setDefnObject(IDCONST.COMMENT.getIIDValue());
    _comment.setRequired(true);
    _revisionName = new BMProperty(this);
    _revisionName.setDefnObject(IDCONST.REVISION_LABEL.getIIDValue());
    _autoRevLabel = new BMProperty(this);
    _autoRevLabel.setDefnObject(IDCONST.REVISION_LABEL.getIIDValue());
  }

  //-------------------------- Protected Methods -----------------------------
  protected String getPSPUpdateQuery()
  {
    return " UPDATE "+TABLE+
         " SET DESCRIPTION=? "+
         " WHERE OBJECTID="+getIID().getLongValue()+"";
  }    

  protected Object getPSPValue() throws ORIOException
  {
    String ret = null;
    try
    {
      ret = getDescription();
    }
    catch (OculusException ox)
    {
      throw new ORIOException(ox);
    }
    return ret;
  }

  protected PSPKind needsPreparedStatement()
  {
    return PSPKind.STRING;
  }  


  protected String getLoadPropertiesQuery()
    throws OculusException
  {
		return null;
  }
		
  
  protected String getLoadPropertiesQuery2()
    throws OculusException
	{
		return null;
	}


  protected String getLoadQuery()
    throws OculusException
  {
    return " SELECT rev.OBJECTID AS REV_OBJECTID, rev.GUID AS REV_GUID, rev.CLASSID AS REV_CLASSID, rev.STATEID AS REV_STATEID, "+
									"rev.ACCESSID AS REV_ACCESSID, rev.CREATORID AS REV_CREATORID, rev.CREATIONDATE AS REV_CREATIONDATE, rev.DELETESTATE AS REV_DELETESTATE, "+
  								"rev.FILEATTACHED AS REV_FILEATTACHED, rev.LINKATTACHED AS REV_LINKATTACHED, rev.DISCUSSATTACHED AS REV_DISCUSSATTACHED, "+
  								"rev.FEATUREID AS FEATUREID,"+
  							"rev.ISCHANGEABLE, rev.EDITDATE, rev.NAME, rev.REVISIONNAME, rev.AUTOREVISIONLABEL, rev.USERCOMMENT, rev.DESCRIPTION "+
					"FROM "+TABLE+" rev "+
           "WHERE OBJECTID="+getIID().getLongValue();
  } 
  
  protected String getUpdateQuery()
    throws OculusException
  {
    return " UPDATE "+TABLE+" "+
           " SET "+
           "   NAME='"+SQLUtil.primer(getName())+"' "+
//           " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
           " , ACCESSID= "+getAccessIID().getLongValue()+" "+
           " , DELETESTATE= "+getDeleteState().getIntValue()+" "+
           " , DISCUSSATTACHED= "+(hasMessageAttached()?"1":"0")+" "+
           " , FILEATTACHED= "+(hasFileAttached()?"1":"0")+" "+
           " , LINKATTACHED= "+(hasLinkAttached()?"1":"0")+" "+
           " , FEATUREID="+getFeatureObject().getIID().getLongValue()+" "+
           " , ISCHANGEABLE="+(isChangeable()?"1":"0")+" "+
           " , USERCOMMENT='"+SQLUtil.primer(getComment())+"' "+
           " , REVISIONNAME='"+SQLUtil.primer(getRevisionLabel())+"' "+
           " , AUTOREVISIONLABEL='"+SQLUtil.primer(getAutoRevisionLabel())+"' "+
           " WHERE OBJECTID="+getIID().getLongValue();
  }

  protected String getCreateQuery()
    throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
           " (OBJECTID, "
//             +"CLASSID, "
//             +"STATEID, "
               +"DELETESTATE, "
               +"GUID, "
               +"NAME, "
               +"CREATIONDATE, "
               +"CREATORID, "
               +"ACCESSID, "
               +"DISCUSSATTACHED, "
               +"FILEATTACHED, "
               +"LINKATTACHED, "
               +"FEATUREID, "
               +"ISCHANGEABLE, "
               +"USERCOMMENT, "
               +"REVISIONNAME, "
               +"AUTOREVISIONLABEL "
           +") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
//              +getDefnObject().getIID().getLongValue()+","
//              +getStateObject().getIID().getLongValue()+","
              +getDeleteState().getIntValue()+","
              +"'"+getGUID().toString()+"',"
              +"'"+SQLUtil.primer(getName(),249)+"',"
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
              +getAccessIID().getLongValue()+","
              +(hasMessageAttached()?"1":"0")+","
              +(hasFileAttached()?"1":"0")+","
              +(hasLinkAttached()?"1":"0")+","
              +getFeatureObject().getIID().getLongValue()+", "
              +(isChangeable()?"1":"0")+", "
              +"'"+SQLUtil.primer(getComment())+"', "
              +"'"+SQLUtil.primer(getRevisionLabel())+"', "
              +"'"+getAutoRevisionLabel()+"' "
           +") ";
  } 

  protected String getDeleteQuery()
    throws OculusException
  {
    return " DELETE FROM "+TABLE+" "+
           " WHERE OBJECTID="+getIID().getLongValue();
  } 

  protected void load(IDataSet results)
    throws OculusException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong("REV_OBJECTID"));
    setPersState(PersState.PARTIAL);
    
    super.load(results);

    setName(results.getString(COL_NAME).trim());               // get name
    String desc = results.getString(COL_DESCRIPTION);
    if (desc == null) desc = "";
    setDescription(desc.trim()); // get desc
    setIsChangeable(results.getInt(COL_ISCHANGEABLE) == 1);
    setComment(results.getString(COL_COMMENT));
    setRevisionLabel(results.getString(COL_REVISIONNAME));
    setAutoRevisionLabel(results.getString(COL_AUTOREVISIONLABEL));
    _featIID = new SequentialIID(results.getLong(COL_FEATUREID));   
  }



//----------------- IFeatureRevision Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }

  //------------------------- MUTATORS ------------------------------
  public IFeatureRevision setIsChangeable(boolean changeable)
    throws ORIOException
  {
    _isChangeable.setValue(new Boolean(changeable));
    return this;
  }

  public IFeatureRevision setComment(String comment)
    throws ORIOException
  {
    _comment.setValue(comment);
    return this;
  }

  public IFeatureRevision setRevisionLabel(String revName)
    throws ORIOException
  {
    _revisionName.setValue(revName);
    return this;
  }

  public IFeatureRevision setAutoRevisionLabel(String autoRevLabel)
    throws ORIOException
  {
    _autoRevLabel.setValue(autoRevLabel);
    return this;
  }

  public IBusinessObject setFeatureObject(IFeature feature)
    throws ORIOException
  {
    _featIID = feature.getIID();
    return this;
  }

  //------------------------- ACCESSORS -----------------------------

  public boolean isChangeable()
    throws OculusException
  {
    if (_isChangeable.getValue() != null)
      return ((Boolean)_isChangeable.getValue()).booleanValue();
    else
      return false;
  }

  public String getComment()
    throws OculusException
  {
    if (_comment.getValue() != null)
      return (String)_comment.getValue();
    else
      return null;
  }

  public String getRevisionLabel()
    throws OculusException
  {
    if (_revisionName.getValue() != null)
      return (String)_revisionName.getValue();
    else
      return null;
  }

  public String getAutoRevisionLabel()
    throws OculusException
  {
    if (_autoRevLabel.getValue() != null)
      return (String)_autoRevLabel.getValue();
    else
      return null;
  }

  public IFeature getFeatureObject()
    throws OculusException
  {
    return getFeatureObject(false);
  }
  
  public IFeature getFeatureObject(boolean editable)
    throws OculusException
  {
    if (_featIID != null)
    {
      IFeature thisFeature = (IFeature)getObjectContext().getCRM().getCompObject(getObjectContext(),"Feature",_featIID,editable);
      thisFeature.setFeatureRevisionObject(this);
      return thisFeature;
    }
    else
      return null;
  }

  public IProductVersionColl getAssociatedVersions()
    throws OculusException
  {
    return (IProductVersionColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureRevisionProductVersionColl",getIID());
  }

//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
    throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_COMMENT))
        return _comment;
      else if (key.equals(LABEL_NAME))
        return _name;
      else if (key.equals(LABEL_DESCRIPTION))
        return _description;
      else if (key.equals(LABEL_REVISIONNAME))
        return _revisionName;
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
      if (key.equals(LABEL_NAME))
        setName((String)property.getValue());
      if (key.equals(LABEL_DESCRIPTION))
        setDescription((String)property.getValue());
      if (key.equals(LABEL_COMMENT))
        setComment((String)property.getValue());
      else if (key.equals(LABEL_REVISIONNAME))
        setRevisionLabel((String)property.getValue());
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
    FeatureRevision rev = null;
      rev = new FeatureRevision();
      rev.setIID(getIID());
      rev.setObjectContext(getObjectContext());
      rev.setPersState(getPersState());
      rev._classIID = _classIID;
      rev._stateIID = _stateIID;
      rev.setDeleteState(getDeleteState());
      if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        rev.putAll(getProperties());
      rev._creatorIID = _creatorIID;
      rev._accessIID = _accessIID;
      rev.setCreationDate(getCreationDate());
      rev._messageAttached.setValue(_messageAttached.getValue());
      rev._linkAttached.setValue(_linkAttached.getValue());
      rev._fileAttached.setValue(_fileAttached.getValue());

      rev.setName(getName());
      rev.setDescription(getDescription());
      rev._featIID = _featIID;
      rev.setIsChangeable(isChangeable());
      rev.setComment(getComment());
      rev.setRevisionLabel(getRevisionLabel());
      rev.setAutoRevisionLabel(getAutoRevisionLabel());
    return rev;
  }
/********************************************


**************************/
}