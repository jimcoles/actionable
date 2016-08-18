package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.MC;

import java.util.*;

/**
* Filename:    BMModelElement.java
* Date:        2/16/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*
* $History: BMModelElement.java $
 * 
 * *****************  Version 48  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * Bug Fix: #2207
 * 
 * *****************  Version 47  *****************
 * User: Apota        Date: 8/07/00    Time: 6:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * 
 * *****************  Version 46  *****************
 * User: Apota        Date: 6/27/00    Time: 5:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * 
 * *****************  Version 45  *****************
 * User: Sshafi       Date: 6/21/00    Time: 2:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * General Cleanup
 * 
 * *****************  Version 44  *****************
 * User: Sshafi       Date: 6/19/00    Time: 1:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * 
 * *****************  Version 43  *****************
 * User: Sshafi       Date: 6/15/00    Time: 12:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * Added implementations to load() and save() that mirror what's going on
 * in ReposObject, only here we don't have to worry about extended
 * attributes.
 * 
 * *****************  Version 42  *****************
 * User: Sshafi       Date: 6/14/00    Time: 12:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * 
 * *****************  Version 41  *****************
 * User: Eroyal       Date: 5/17/00    Time: 11:35a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * cleaned up contruct method related to Issue # 94
 * 
 * *****************  Version 40  *****************
 * User: Jcoles       Date: 5/15/00    Time: 8:43a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr
 * Adding convenience method to get repos objects.
*/
public abstract class BMModelElement implements IRModelElement, Comparable
{
  private MC _mc = null;
  
  public static final String COL_OBJECTID      = "OBJECTID";
  public static final String COL_BYTEGUID      = "GUID";
  public static final String COL_NAME          = "NAME";
  public static final String COL_DESCRIPTION   = "SHORTDESCRIPTION";
  public static final String COL_DELETESTATE   = "DELETESTATE";
  public static final String COL_ISACTIVE      = "ISACTIVE";
  public static final String COL_CONFIGKIND    = "CONFIGUREKIND";
  
  protected IObjectContext context;
  private PersState pstate;
  protected IGUID guid;
  protected IIID iid;
  protected String name;
  protected String description; 
  protected DeleteState _deletestate = DeleteState.NOT_DELETED;
  protected boolean isactive = true;
  protected ConfigKind _configkind = ConfigKind.READ_ONLY;
  
  //accessors
  public IGUID getGUID() { return guid; }
  public IObjectContext getObjectContext() { return context; }
  public PersState getPersState() { return pstate; }
  public IIID getIID() throws ORIOException { return iid; }
  public String getName() throws ORIOException { return name; }
  public String getDescription() throws ORIOException { return description; }
  public DeleteState getDeleteState() throws ORIOException { return _deletestate; }
  public boolean isActive() throws ORIOException { return isactive; }
  public ConfigKind getConfigKind() throws ORIOException { return _configkind; }


  //mutators
	public IObject setGUID(IGUID g) { guid = g;	return this;}	
  public IObject setObjectContext(IObjectContext c) 
  { 
    context = c; 
    try {
      _mc = new MC(context);
    } catch (OculusException ignore) {}; // method doesn't care
    return this; 
  }
  public IPersistable setPersState(PersState state) { pstate = state; return this; }
  public IPersistable setIID(IIID i) throws ORIOException { iid = i; return this; }
  public IRElement setName(String n) throws ORIOException 
  { 
    name = n;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  public IRElement setDescription(String d) throws ORIOException 
  { 
    description = d; 
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  public IRModelElement setDeleteState(DeleteState d) throws ORIOException 
  { 
    _deletestate = d; 
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  public IRModelElement setConfigKind(ConfigKind c) throws ORIOException 
  { 
    _configkind = c; 
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }
  public IRModelElement isActive(boolean a) throws ORIOException 
  { 
    isactive = a;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this; 
  }

  //IPoolable
  public boolean isLocked() { return PoolMgr.getInstance().isLocked(this); }
  public boolean isRemoveable() { return false; }
  
  
  //Database Connections
  protected IRConnection getDatabaseConnection() throws OculusException
  {
    return getObjectContext().getCRM().getDatabaseConnection(getObjectContext());
  }//end getDatabaseConnection
  
//  protected void returnDatabaseConnection(IRConnection conn) throws OculusException
//  {
//    getObjectContext().getCRM().returnDatabaseConnection(conn);
//  }//end returnDatabaseConnection
  

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext ctext, IDataSet args)
    throws OculusException
  {
    IIID iid = null;
    if (ctext == null)
      throw new OculusException("Context Argument expected.");
    setObjectContext(ctext);

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

    if (args != null && args.containsKey(COL_NAME))
      load(args);

    return this;
  }//
 
  //genGUID can only be called by the subclasses
  protected IGUID genGUID() throws OculusException
  {

//  Saleem changed this because at the time of the call, context may or may not have
//  been initialized.
//  return context.getCRM().genGUID(); 
    return getObjectContext().getCRM().genGUID();

  }//end genGUID
    

  public int compareTo(Object o) { return name.compareTo(o.toString()); }  
  public String toString() { return name;}
  
  /**
  *
  */
  protected void load(IDataSet rs) throws OculusException
  {
    setPersState(PersState.PARTIAL);
    guid         = new GUID(rs.getString(COL_BYTEGUID).trim());
    setName(rs.getString(COL_NAME).trim());
		setDescription(rs.getString(COL_DESCRIPTION).trim());
    setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETESTATE)));
    isActive(rs.getBoolean(COL_ISACTIVE));
    setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));
  }//end load
  
  /**
  *
  */
  public IPersistable delete() throws ORIOException
  {
    if(getConfigKind().equals(ConfigKind.DELETEABLE) || getConfigKind().equals(ConfigKind.FULL))
      setPersState(PersState.DELETED);
    else
      throw new ORIOException("Invalid ConfigKind");
    return this;
  }//end delete
  
  public IPersistable softdelete()
    throws OculusException
  {
    return null;
  }
  public IPersistable recover()
    throws OculusException
  {
    return null;
  }
  
  public boolean isUsed() throws OculusException { return false; } 
    
  /** Returns the MC (mini-CRM) service object as a convenience */
  protected MC getMC() { return _mc; }

	protected String getLoadQuery() throws OculusException { return null; }
	protected String getCreateQuery() throws OculusException { return null; }
	protected String getUpdateQuery() throws OculusException { return null; }
	protected String getDeleteQuery() throws OculusException { return null; }
	protected String getLoadPropertiesQuery() throws OculusException { return null; }
	protected PSPKind needsPreparedStatement() { return PSPKind.NONE; }
	protected int getPSPLength() throws ORIOException { return 0;	}
	protected String getPSPUpdateQuery() throws OculusException	{ return null; }
	protected Object getPSPValue() throws OculusException { return null; }

  /** Loads a generic bo */
  public IPersistable load()
    throws OculusException
  {
    if (!getPersState().equals(PersState.NEW))          // If we know that we are creating a new one, we don't need to go to the database.
    {
        IRConnection repConn = getObjectContext().getRepository().getDataConnection(getObjectContext());
        IQueryProcessor stmt = repConn.createProcessor();
        String query = getLoadQuery();                // gets bo specific query
        IDataSet results = stmt.retrieve(query);
        if (results.next())
          load(results);                              // initializes the bo with the data          
        else            // Saleem added this because we need to know whether we actually got something, or not.
          throw new ORIOException("Object not found in database. Class="+this.getClass().getName()+", IID="+this.getIID().getLongValue());
        stmt.close();
     
//        getObjectContext().getCRM().returnDatabaseConnection(repConn);
        setPersState(PersState.UNMODIFIED);
      }

    return this;
  }


	protected boolean _issingle = true;
	protected void isSingleton(boolean single) { _issingle = single; }
	protected boolean isSingleton() { return _issingle;	}

  /** Saves any changes to this bo to the data store. */
	protected void insert()
		throws OculusException
	{
		_update(getCreateQuery());
	}

	protected void update()
    throws OculusException
  {
    _update(getUpdateQuery());
  }
  
  protected void harddelete()
    throws OculusException
  {
    _update(getDeleteQuery());
  }

	protected void _update(String query)
		throws OculusException
	{
	  IQueryProcessor stmt = null;
	  try
	  {
		  IRConnection repConn = getObjectContext().getRepository().getDataConnection(getObjectContext());
		  stmt = repConn.createProcessor();

		  stmt.setSingleton(isSingleton());
		  stmt.update(query);

		  if (!needsPreparedStatement().equals(PSPKind.NONE))
		  {
		    IPreparedStatementProcessor psp = repConn.prepareProcessor(getPSPUpdateQuery());
		    if (needsPreparedStatement().equals(PSPKind.STRING))
		      psp.setString(1,(String)getPSPValue());
		    else
		    if (needsPreparedStatement().equals(PSPKind.INPUTSTREAM))
		    {
		      psp.setBinaryStream(1,(java.io.InputStream)getPSPValue(),getPSPLength());
		      psp.setInt(2,getPSPLength());
		    }
		    psp.update();
		  }
		  setPersState(PersState.UNMODIFIED);
		}
		finally
		{
		  if (stmt != null) stmt.close();
		}
	}

	
  public IPersistable save()
    throws OculusException
  {
    if (!isLocked() && !getPersState().equals(PersState.NEW))  // If the bo isn't locked, throw an exception
      throw new ORIOException("This object ("+this.getClass().getName()+":"+this.getIID().getLongValue()+") cannot be saved because it is not locked.");
    
    if (getPersState().equals(PersState.MODIFIED)) {update();setPersState(PersState.UNMODIFIED);}
    if (getPersState().equals(PersState.NEW))  {insert();setPersState(PersState.UNMODIFIED);}
    if (getPersState().equals(PersState.DELETED))  harddelete();

    return this;
  }



  
}//end BMModelElement