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
  private String COL_PAROBJECTID = "PAROBJECTID";
  private String COL_NOTETYPEID = "NOTETYPEID";
  private String COL_DESCRIPTION = "BODY";

  private Oid _parentObjIID;
  private Oid _noteTypeIID;



  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public Note() throws AppException
  {
    super();
    TABLE = "EXTHYPERLINK";
    COL_Guid = "Guid";
  }

  //-------------------------- Protected Methods -----------------------------
  private String getLoadQuery()
    throws AppException
  {
    return "SELECT * "+
           "FROM "+TABLE+" "+
           "WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  } 
  
  private String getUpdateQuery()
    throws AppException
  {
    return " UPDATE "+TABLE+" "+
           " SET "+
           "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
           " , "+COL_DESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
           (!getDefnObject().hasStateMachine()?"":" , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" ")+
           " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
           " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
           " , "+COL_PAROBJECTID+"= "+getParentObject().getOid().getLongValue()+" "+
//           " , "+COL_NOTETYPEID+"="+getNoteTypeIID().getLongValue()+" "+
           " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
           " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
           " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
           " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }

  private String getCreateQuery()
    throws AppException
  {
    return "INSERT INTO "+TABLE+" "+
           " ("+COL_OBJECTID+", "
               +COL_CLASSID+", "
               +(!getDefnObject().hasStateMachine()?"":COL_STATEID+", ")
               +COL_DELETESTATE+", "
               +COL_PAROBJECTID+", "
               +COL_Guid+", "
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
           " ("+ getOid().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +(!getDefnObject().hasStateMachine()?"":getStateObject().getIID().getLongValue()+",")
              +getDeleteState().getIntValue()+","
              +_parentObjIID.getLongValue()+","
              +"'"+getGuid().toString()+"',"
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

  private String getDeleteQuery()
    throws AppException
  {
    return " DELETE FROM "+TABLE+" "+
           " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  } 

  private void load(IDataSet results)
    throws AppException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));
    super.load(results);

    _noteTypeIID = new Oid(results.getLong(COL_NOTETYPEID));
    _parentObjIID = new Oid(results.getLong(COL_PAROBJECTID));
  }



//----------------- ILink Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }

  //------------------------ MUTATORS -------------------------------
  public INote setParentObject(BusinessObject parent)
    throws AppException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parentObjIID = parent.getOid();
    return this;
  }
    
  public INote setNoteType(Oid noteType)
    throws AppException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _noteTypeIID = noteType;
    return this;
  }

  //------------------------ ACCESSORS -------------------------------

  public Oid getNoteTypeIID()
    throws AppException
  {
    return _noteTypeIID;
  }

//----------------- IPoolable Methods ------------------------------------
  /**
  *  Returns a copy of the current product object.  It does not copy the
  * ObjectContext.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object dolly() throws AppException
  {
    Note note = null;
      note = new Note();
      note.setOid(getOid());
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