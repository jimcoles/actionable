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
* Filename:    Note.java
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
public class Note extends BusinessObject implements INote
{
  protected String COL_PAROBJECTID = "PAROBJECTID";
  protected String COL_NOTETYPEID = "NOTETYPEID";
  protected String COL_DESCRIPTION = "BODY";

  protected IIID _parentObjIID;
  protected IIID _noteTypeIID;



  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public Note() throws OculusException
  {
    super();
    TABLE = "EXTHYPERLINK";
    COL_GUID = "GUID";
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
           "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
           " , "+COL_DESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
           (!getDefnObject().hasStateMachine()?"":" , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" ")+
           " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
           " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
           " , "+COL_PAROBJECTID+"= "+getParentObject().getIID().getLongValue()+" "+
//           " , "+COL_NOTETYPEID+"="+getNoteTypeIID().getLongValue()+" "+
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
               +COL_DELETESTATE+", "
               +COL_PAROBJECTID+", "
               +COL_GUID+", "
               +COL_NAME+", "
               +COL_DESCRIPTION+", "
//               +COL_NOTETYPEID+", "
               +COL_CREATIONDATE+", "
               +COL_CREATORID+", "
//               +COL_ACCESSID+", "
               +COL_MESSAGEATTACHED+", "
               +COL_FILEATTACHED+", "
               +COL_LINKATTACHED+
           ") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +(!getDefnObject().hasStateMachine()?"":getStateObject().getIID().getLongValue()+",")
              +getDeleteState().getIntValue()+","
              +_parentObjIID.getLongValue()+","
              +"'"+getGUID().toString()+"',"
              +"'"+SQLUtil.primer(getName())+"',"
              +"'"+SQLUtil.primer(getDescription())+"',"
//              +getNoteTypeIID().getLongValue()+","
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
//              +getAccessIID().getLongValue()+","
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

    _noteTypeIID = new SequentialIID(results.getLong(COL_NOTETYPEID));
    _parentObjIID = new SequentialIID(results.getLong(COL_PAROBJECTID));
  }



//----------------- ILink Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }

  //------------------------ MUTATORS -------------------------------
  public INote setParentObject(IBusinessObject parent)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parentObjIID = parent.getIID();
    return this;
  }
    
  public INote setNoteType(IIID noteType)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _noteTypeIID = noteType;
    return this;
  }

  //------------------------ ACCESSORS -------------------------------

  public IIID getNoteTypeIID()
    throws ORIOException
  {
    return _noteTypeIID;
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
    Note note = null;
      note = new Note();
      note.setIID(getIID());
      note.setObjectContext(getObjectContext());
      note.setPersState(getPersState());
      note._classIID = _classIID;
      note._stateIID = _stateIID;
      note.setDeleteState(getDeleteState());
      if (getProperties() != null)
        note.putAll(getProperties());
      note.setName(getName());
      note.setDescription(getDescription());
      note._creatorIID = _creatorIID;
      note._accessIID = _accessIID;
      note.setCreationDate(getCreationDate());
      note.setMessageAttached(hasMessageAttached());
      note.setLinkAttached(hasLinkAttached());
      note.setFileAttached(hasFileAttached());

      note._parentObjIID = _parentObjIID;
      note._noteTypeIID = _noteTypeIID;
    return note;
  }

/********************************************


**************************/
}