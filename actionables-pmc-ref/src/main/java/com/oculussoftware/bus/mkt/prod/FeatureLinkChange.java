package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.system.*;

import java.sql.*;
import java.util.*;

public class FeatureLinkChange extends ReposObject implements IFeatureLinkChange
{
  
  public class AttribChange
  {
    private IIID attribiid;
    private Object oldvalue;
    private Object newvalue;
        
    public AttribChange() {}
    public AttribChange(IIID attribIID, Object oldValue, Object newValue)
    {
      attribiid = attribIID;
      oldvalue = oldValue;
      newvalue = newValue;
    }
    
    public IIID getAttribIID() { return attribiid; }
    public Object getOldValue() { return oldvalue; }
    public Object getNewValue() { return newvalue; }
  }
  
  
  public static final String COL_FEATCATLINKID = "FEATURELINKID";
  public static final String COL_CHANGEUSERID = "CHANGEUSERID";
  public static final String COL_CHANGEDATE = "CHANGEDATE";
  public static final String COL_CHANGECOMMENT = "CHANGECOMMENT";
  public final static String COL_OBJECTID     = "OBJECTID";
  public final static String COL_GUID         = "GUID";
  public static final String TABLE = "FEATURELINKCHANGE";


  protected List _changes;
  protected IIID _featcatlinkid,
                 _userid;
  protected IRProperty _date,
                       _comment;
  
  
  public FeatureLinkChange()
    throws OculusException
  {
    super();
    _changes = new Vector();
    _date = new BMProperty(this);
    _comment = new BMProperty(this);
    _date.setDefnObject(IDCONST.EDIT_DATE.getIIDValue());
    _comment.setDefnObject(IDCONST.COMMENT.getIIDValue());
  }
  

  public java.util.List getChanges()
    throws OculusException
  {
    return _changes;
  }
  


  //Accessors
  public IFeatureCategoryLink getFeatureCategoryLinkObject()
    throws OculusException
  {
    if (_featcatlinkid == null)
      return null;
    return (IFeatureCategoryLink)getObjectContext().getCRM().getCompObject(getObjectContext(),"FeatureCategoryLink",_featcatlinkid);
  }

  public String getChangeComment()
    throws OculusException
  {
    if (_comment == null)
      return null;
    return (String)_comment.getValue();
  }

  public Timestamp getChangeDate()
    throws OculusException
  {
    if (_date == null)
      return null;
    return (Timestamp)_date.getValue();
  }

  public IUser getChangeUserObject()
    throws OculusException
  {
    if (_userid == null)
      return null;
    return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",_userid);
  }

  public String getName()
  {
    return null;
  }
  
  public String getDescription()
  {
    return null;
  }

  public IRPropertyMap getProperties()
  {
    return null;
  }

  public void record(IIID attribID, Object oldvalue, Object newvalue)
    throws OculusException
  {
    _changes.add(new AttribChange(attribID, oldvalue, newvalue));
  }


  //Mutators
  public IFeatureLinkChange setFeatureCategoryLinkObject(IFeatureCategoryLink featcatlink)
    throws ORIOException
  {
    if (featcatlink != null)
      _featcatlinkid = featcatlink.getIID();
    return this;
  }

  public IFeatureLinkChange setChangeComment(String comment)
    throws ORIOException
  {
    _comment.setValue(comment);
    return this;
  }

  public IFeatureLinkChange setChangeDate(Timestamp time)
    throws ORIOException
  {
    _date.setValue(time);
    return this;
  }

  public IFeatureLinkChange setChangeUserObject(IUser user)
    throws ORIOException
  {
    if (user != null)
      _userid = user.getIID();
    return this;
  }

  public IRElement setName(String name)
  {
    return this;
  }

  public IRElement setDescription(String description)
  {
    return this;
  }
  
  
  protected String getLoadQuery()
    throws OculusException
  { 
    return "SELECT * FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }
      
  protected String getLoadPropertiesQuery()
    throws OculusException
  { return null; }

  protected String getUpdateQuery()
    throws OculusException
  { return null; }

  protected String getCreateQuery()
    throws OculusException
  { 
    setChangeDate(new Timestamp(System.currentTimeMillis()));
    IUser thisUser = (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",getObjectContext().getConnection().getUserIID());
    setChangeUserObject(thisUser);
    return "INSERT INTO "+TABLE+
      "( "+
            COL_OBJECTID+", "+
            COL_GUID+", "+
            COL_FEATCATLINKID+", "+
            COL_CHANGEUSERID+", "+
            COL_CHANGEDATE+", "+
            COL_CHANGECOMMENT+" "+
            ") VALUES ("+
            _iid.getLongValue()+", "+
            "'"+_guid.toString()+"', "+
            _featcatlinkid.getLongValue()+", "+
            _userid.getLongValue()+", "+
            "'"+getChangeDate()+"', "+
            "'"+SQLUtil.primer(getChangeComment())+"' "+
            ")";
  }

  protected String getDeleteQuery()
    throws OculusException
  { 
    return "DELETE FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }




  /** Saves any changes to this bo to the data store. */
  public IPersistable save()
    throws OculusException
  {
    try
    {
			if (!_changes.isEmpty())
			{
	      super.save();
	      
	      IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
	      IQueryProcessor stmt = repConn.createProcessor();
	      
	      for (Iterator i = _changes.iterator(); i.hasNext(); )
	      {
	        AttribChange thisChange = (AttribChange)i.next();
	        String query = " INSERT INTO ATTRCHANGE (CHANGEID,ATTRIBUTEID,FROMVALUE,TOVALUE) VALUES ("+getIID()+","+thisChange.getAttribIID()+",'"+SQLUtil.primer(thisChange.getOldValue().toString())+"','"+SQLUtil.primer(thisChange.getNewValue().toString())+"')";
	        stmt.update(query);
	      }
			}
    }
    catch (Exception exp)
    {
      throw new OculusException(exp);
    }
    return this;
  }







  protected void load(IDataSet results)
    throws OculusException
  { 
    IRepository repos = getObjectContext().getRepository();
    _guid = new GUID(results.getString(COL_GUID).trim()); 
    _iid = results.getIID();
    
    _featcatlinkid = repos.makeReposID(results.getLong(COL_FEATCATLINKID));
    _userid = repos.makeReposID(results.getLong(COL_CHANGEUSERID));
    setChangeDate(results.getTimestamp(COL_CHANGEDATE));
    setChangeComment(results.getString(COL_CHANGECOMMENT));
    setPersState(PersState.UNMODIFIED);

    try
    {
      IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
      IQueryProcessor stmt = repConn.createProcessor();
      
      _changes = new Vector();
      String query = "SELECT * FROM ATTRCHANGE WHERE CHANGEID="+getIID();
      IDataSet rs = stmt.retrieve(query);
      while (rs.next())
      {
        long attribiid = rs.getLong("ATTRIBUTEID");
        String oldvalue = rs.getString("FROMVALUE");
        String newvalue = rs.getString("TOVALUE");
        IIID attribIID = getObjectContext().getRepository().makeReposID(attribiid);
        record(attribIID,oldvalue,newvalue);
      }
    }
    catch (Exception exp)
    {
      throw new OculusException(exp);
    }
  }
  
  public Object dolly() throws OculusException
  {
    FeatureLinkChange ra = null;
      ra = new FeatureLinkChange();
      ra.setIID(getIID());
      ra.setObjectContext(getObjectContext());
      ra.setPersState(getPersState());
      ra.setFeatureCategoryLinkObject(getFeatureCategoryLinkObject());
      ra.setChangeUserObject(getChangeUserObject());
      ra.setChangeDate(getChangeDate());
      ra.setChangeComment(getChangeComment());
      ra._changes = _changes;
    return ra; 
  }//

  public IPoolable construct(IObjectContext context, IDataSet args) throws OculusException
  {
    IIID iid = null;
    if (context == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(context);
    if (args == null)
    {
      iid = getObjectContext().getRepository().genReposID();
      setPersState(PersState.NEW);
    }
    else
    {
      setPersState(PersState.UNINITED);
      iid = args.getIID();
    }
    setIID(iid);
    if (args != null && args.containsKey(COL_FEATCATLINKID))
      load(args);
    return this; 
  }
  

}