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
public class HyperLink extends BusinessObject implements IHyperLink
{
  private String COL_PAROBJECTID = "PAROBJECTID";
  private String COL_URL = "URL";
  private String COL_TYPE = "TYPE";
  public static final String COL_LABEL = "LABEL";
  public static final String COL_SHORTDESCRIPTION = "SHORTDESCRIPTION";

  private Oid _parentObjIID;
  private BMProperty _url;
  private HyperLinkType _type;



  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */  
  public HyperLink() throws AppException
  {
    super();
    TABLE = "EXTHYPERLINK";
    COL_Guid = "Guid";
    _url = new BMProperty(this);
    _url.setDefnObject(IDCONST.URL.getIIDValue());
    _type = HyperLinkType.NORMAL;
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
           " WHERE "+COL_OBJECTID+"="+ getOid().getLongValue();
  }

  private String getCreateQuery()
    throws AppException
  {
    return "INSERT INTO "+TABLE+" "+
           " ("+COL_OBJECTID+", "
               +COL_CLASSID+", "
               +(!getDefnObject().hasStateMachine()?"":COL_STATEID+", ")
               +COL_PAROBJECTID+", "
               +COL_DELETESTATE+", "
               +COL_Guid+", "
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
           " ("+ getOid().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +(!getDefnObject().hasStateMachine()?"":getStateObject().getIID().getLongValue()+",")
              +_parentObjIID.getLongValue()+","
              +getDeleteState().getIntValue()+","
              +"'"+getGuid().toString()+"',"
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
        
    setURL(results.getString(COL_URL));
    setName(results.getString(COL_LABEL));
    setDescription(results.getString(COL_SHORTDESCRIPTION));
    _parentObjIID = new Oid(results.getLong(COL_PAROBJECTID));
    _type = HyperLinkType.getInstance(results.getInt(COL_TYPE));
  }



//----------------- ILink Methods ------------------------------------
  /** I don't know what this does */
  public ISet getBranches()
   {
     return null;
  }

  //------------------------ MUTATORS -------------------------------
  public IHyperLink setParentObject(BusinessObject parent)
    throws AppException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _parentObjIID = parent.getOid();
    return this;
  }
    
  public IHyperLink setURL(String url)
    throws AppException
  {
    _url.setValue(url);
    return this;
  }

  public IHyperLink setLinkType(HyperLinkType hlt) 
    throws AppException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _type = hlt;
    return this;
  }
  
  //------------------------ ACCESSORS -------------------------------

  public HyperLinkType getLinkType() 
    throws AppException
  { return _type; }

  
  public String getURL()
    throws AppException
  {
    if (_url.getValue() != null)
      return (String)_url.getValue();
    else
      return null;
  }

  public IHyperLink createCopy()
    throws AppException
  {
    Oid classIID = IDCONST.HYPERLINK.getIIDValue();
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
    throws AppException
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
    throws AppException
  {
    if (key instanceof String && value instanceof BMProperty)
    {
      BMProperty property = (BMProperty)value;
      if (key.equals(LABEL_URL))
        setURL((String)property.getValue());
      else
        super.put(key,value);
    }
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
    HyperLink link = null;
      link = new HyperLink();
      link.setOid(getOid());
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