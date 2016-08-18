package com.oculussoftware.repos;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.bus.mkt.comm.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.api.busi.common.org.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    ReposObject.java
* Date:        3/14/00
* Description: Provides the functionality for a basic IRObject
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* $History: ReposObject.java $
 * 
 * *****************  Version 39  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Bug Fix: #2207
 * 
 * *****************  Version 37  *****************
 * User: Apota        Date: 8/25/00    Time: 3:02p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 36  *****************
 * User: Eroyal       Date: 8/14/00    Time: 6:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 35  *****************
 * User: Sshafi       Date: 6/23/00    Time: 6:04p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Added Attribute Group Permissions.
 * 
 * *****************  Version 33  *****************
 * User: Sshafi       Date: 6/17/00    Time: 12:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 32  *****************
 * User: Apota        Date: 6/14/00    Time: 1:51p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 31  *****************
 * User: Eroyal       Date: 6/05/00    Time: 6:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Working on Issue # 628
 * 
 * *****************  Version 30  *****************
 * User: Eroyal       Date: 5/26/00    Time: 11:42a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 29  *****************
 * User: Sshafi       Date: 5/26/00    Time: 10:55a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * 
 * *****************  Version 28  *****************
 * User: Eroyal       Date: 5/24/00    Time: 6:02p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Issue # 304
 * 
 * *****************  Version 27  *****************
 * User: Jcoles       Date: 5/15/00    Time: 8:43a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos
 * Adding convenience method to get repos objects.
*/

abstract public class ReposObject implements IRObject
{
  //--------------------------- Private variables --------------------------
  private com.oculussoftware.util.MC _mc = null;
  
  //--------------------------- Protected variables --------------------------
	protected IObjectContext _context;									// the BO's ObjectContext
	protected PersState _perState;											// the BO's persistant state
	protected IRPropertyMap _attributes;			          // the BO's attributes

	protected IGUID _guid;															// the BO's GUID
	protected IIID _iid;																// the BO's IID
  

	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public ReposObject() throws OculusException
	{
		_guid = new GUID();
    _attributes = new BMPropertyMap();
	}


  //--------------------------- Protected Methods --------------------------
  protected PSPKind needsPreparedStatement() { return PSPKind.NONE; }
  
  protected int getPSPLength() 
    throws OculusException
  {
    return 0;
  }
  
  protected String getPSPUpdateQuery()
    throws OculusException
  {
    return null;
  }
  
  protected Object getPSPValue()
    throws OculusException
  {
    return null;
  }

  
  protected com.oculussoftware.util.MC getMC() { return _mc; };
  
  abstract protected String getLoadQuery()
    throws OculusException;
      
  abstract protected String getLoadPropertiesQuery()
    throws OculusException;
  
   protected String getLoadPropertiesQuery2()
    throws OculusException { return null;}

  abstract protected String getUpdateQuery()
    throws OculusException;

  abstract protected String getCreateQuery()
    throws OculusException;

  abstract protected String getDeleteQuery()
    throws OculusException;

  abstract protected void load(IDataSet results)
    throws OculusException;

//----------------- IRObject Methods ------------------------------------
  /** This method needs to be implemented by the concrete object. */
  public IRCollection  getParentContainers()
    throws ORIOException
	{
		return null;
	}
	
  public IRObject getParentObject()
    throws OculusException
  {
    return getParentObject(false);
  }
  
  public IRObject getParentObject(boolean editable)
    throws OculusException
  {
    return null;
  }
  
  public IRCollection getChildCollection()
    throws OculusException
  {
    return getChildCollection(false);
  }
  
  public IRCollection getChildCollection(boolean editable)
    throws OculusException
  {
    return null;
  }
  
  /** This method needs to be implemented by the concrete object. */
  public boolean childExists(IIID iid)
    throws ORIOException
	{
		return false;
	}
	
  /*
  
  public IRLinkCollMap  getAllLinksFrom()
    throws ORIOException
	{
		return null;
	}
	
  
  public IRCollection   getAllLinksFrom(IRAssoc assoc)
    throws ORIOException
	{
		return null;
	}
	
  
  public IRLinkCollMap   getAllLinksTo()
    throws ORIOException
	{
		return null;
	}
	
  
  public IRCollection    getLinksTo(IRAssoc assoc)
    throws ORIOException
	{
		return null;
	}
	
  
  public IRObject setChildObject(IRAssoc metaassoc, IRObject obj)
    throws ORIOException
	{
		return null;
	}
  */

  /** Sets a property of this bo using the metadata and a value. */
  public IRObject setProperty(IRAttribute metaprop, Object value)
    throws OculusException
  {
    IRProperty newProp = new BMProperty(this);
    newProp.setDefnObject(metaprop);
    newProp.setValue(value);
    //getProperties().put(metaprop.getName(), newProp);
    getProperties().put(metaprop.getIID().toString(), newProp);
    return this;
  }
  
//----------------- IRPersistable Methods ------------------------------------
  /**
  *  Returns the IID of the object.
  */
  public IIID getIID()
  {
    return _iid;
  }
  
  
  protected void loadProperties()
    throws OculusException
  {
        IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
        IQueryProcessor stmt = repConn.createProcessor();
        IDataSet results = null;

      // Gets custom attributes
        if(getLoadPropertiesQuery() != null)
        {
          _attributes = new BMPropertyMap();
					IRPropertyMap props = null;
					if (this instanceof IRPropertyMap) props = (IRPropertyMap)this;
          stmt = repConn.createProcessor();
          results = stmt.retrieve(getLoadPropertiesQuery());
          while (results.next())
          {
            IIID id1 = new SequentialIID(results.getLong("ATTRIBUTEID"));

						String key = "prop"+id1;
            IRProperty thisProperty = null;
						if (props != null) thisProperty = (IRProperty)props.get(key);
						if (thisProperty == null)
						{
							thisProperty = new BMProperty(this);
	            thisProperty.setDefnObject(id1);                                    
	            thisProperty.load(results);
						}
						
            _attributes.put(key,thisProperty);
            
            //Nursing the JVM?
            id1 = null;
            thisProperty = null;
          }
          stmt.close();
          results = null;
        }//end if                    
        
        
        
        // Gets custom attributes for multiselected lists
        if(getLoadPropertiesQuery2() != null)
        {
          
          stmt = repConn.createProcessor();
          results = stmt.retrieve(getLoadPropertiesQuery2());
          //Define only one property for the multiselect list
          Map attmap = Collections.synchronizedMap(new HashMap());
          ArrayList selList = null;
          while (results.next())
          {                       
          
            Long id1 = new Long(results.getLong("ATTRIBUTEID"));
            Long id2 = new Long(results.getLong("enumselValue"));
            selList = (ArrayList)attmap.get(id1);
            if (selList == null)
            {
              selList = new ArrayList();
              selList.add(id2);  
              attmap.put(id1,selList);
            }
            else
            {
              selList.add(id2);  
              attmap.put(id1,selList);
            }
            
          }                       
           
           stmt.close();           
          if (!attmap.isEmpty())
          {
            Iterator keys = attmap.keySet().iterator();
            while(keys.hasNext())
            {
              Long key = (Long) keys.next();              
              IIID id1 = new SequentialIID(key.longValue());
              IRProperty thisProperty = new BMProperty(this);            
              thisProperty.setDefnObject(id1); 
              List list = (ArrayList)attmap.get(key);
              StringBuffer sbf = new StringBuffer();
              for(int i =0; i < list.size(); ++i)
              {                
                sbf.append(list.get(i).toString());
                sbf.append(",");
              }
              
              String s = sbf.substring(0,sbf.length()-1);                                             
              thisProperty.setValue(s);              
              thisProperty.setPersState(PersState.UNMODIFIED);                
             _attributes.put("prop"+thisProperty.getDefnObject().getIID().toString(),thisProperty);        
              sbf = null;
              id1 = null;
              thisProperty = null;          
            }
          }            
        }//end if
        setPersState(PersState.UNMODIFIED);
//        getObjectContext().getCRM().returnDatabaseConnection(repConn);
  }
  
  
  
  /** Loads a generic bo */
  public IPersistable load()
    throws OculusException
  {
    if (!getPersState().equals(PersState.NEW))          // If we know that we are creating a new one, we don't need to go to the database.
    {
        IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
        IQueryProcessor stmt = repConn.createProcessor();
        String query = getLoadQuery();                // gets bo specific query
        IDataSet results = stmt.retrieve(query);
        if (results.next())
          load(results);                              // initializes the bo with the data          
        else            // Saleem added this because we need to know whether we actually got something, or not.
          throw new ORIOException("Object not found in database. Class="+this.getClass().getName()+", IID="+this.getIID().getLongValue());
        stmt.close();
     
        loadProperties();

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

  protected void _update(String query)
  	throws OculusException
  {
    IQueryProcessor stmt = null;
		IPreparedStatementProcessor psp = null;
    try
    {
  	  IRConnection repConn = getObjectContext().getRepository().getDataConnection(getObjectContext());
  	  stmt = repConn.createProcessor();

  	  stmt.setSingleton(isSingleton());
  	  stmt.update(query);

  	  IRPropertyMap props = getProperties();
  	  if(props != null)
  	    for (Iterator keys = props.keys().iterator(); keys.hasNext(); )
  	    {
  	      String key = (String)keys.next();
  	      IRProperty attrib = (IRProperty)props.get(key);
  	      AttributeKind ak = attrib.getAttributeKind();
  	      if (ak != AttributeKind.SYSTEM_GENERATED && ak != AttributeKind.CANNED && ak != AttributeKind.AGGREGATE)
  	        attrib.save();
  	    }//end for

  	  if (!needsPreparedStatement().equals(PSPKind.NONE))
  	  {
  	    psp = repConn.prepareProcessor(getPSPUpdateQuery());
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
  	  if (psp != null) psp.close();
  	}
  }


  public IPersistable save()
    throws OculusException
  {
    if (!isLocked() && !getPersState().equals(PersState.NEW))  // If the bo isn't locked, throw an exception
      throw new ORIOException("This object ("+this.getClass().getName()+":"+this.getIID().getLongValue()+") cannot be saved because it is not locked.");
    
    if (getPersState().equals(PersState.MODIFIED)) update();
    if (getPersState().equals(PersState.NEW))	insert();

    return this;
  }

	/**
	* Marks the bo as deleted.
	*/
	public IPersistable delete()
    throws OculusException
	{
		if (!isLocked())	// If the bo isn't locked, throw an exception
			throw new ORIOException("This object cannot be deleted because it is not locked. ("+getName()+":"+getIID()+")");
		
    setPersState(PersState.DELETED);						// set the persistant state to deleted
    IQueryProcessor stmt = null;
		try
    {
		  IRConnection repConn = getObjectContext().getRepository().getDataConnection(_context);
      stmt = repConn.createProcessor();
			String query = getDeleteQuery();                        // get bo specific delete query
      stmt.update(query);
      IRPropertyMap props = getProperties();
      if(props != null)
      {
        for (Iterator keys = props.keys().iterator(); keys.hasNext(); )
        {
          String key = (String)keys.next();
          IRProperty attrib = (IRProperty)props.get(key);
          AttributeKind ak = attrib.getAttributeKind();
          if (ak != AttributeKind.SYSTEM_GENERATED && ak != AttributeKind.CANNED) attrib.save();
        }
      }//end if
    }//end try
		finally
    { if (stmt != null) stmt.close(); }
		return this;
	}

	/**
	*	Sets the bo's persistant state to the given state.
	*/
  public IPersistable setPersState(PersState state)
	{
		_perState = state;
		return this;
	}
	
	/**
	* Returns the bo's persistant state.
	*/
  public PersState getPersState()
	{
		return _perState;
	}
	
	/**
	*	Set the bo's IID to the given IID.
	*/
	public IPersistable setIID(IIID iid)
	{
		_iid = iid;
		return this;
	}
	
//----------------- IPoolable Methods ------------------------------------
	/**
	*	Returns true if the bo is locked.
	*/
	public boolean isLocked()
	{
		return PoolMgr.getInstance().isLocked(this);
	}
	
//  /** The concrete bo must implement this */
//  abstract public Object clone();

	public boolean isRemoveable()
  {
    return true;
  }
//----------------- IObject Methods ------------------------------------
	/** Returns the GUID of the bo */
	public IGUID getGUID()
	{
		return _guid;
	}
	
	/** Sets the bo's objectContext */
	public IObject setObjectContext(IObjectContext context)
	{
		_context = context;
    try {
      _mc = new com.oculussoftware.util.MC(_context);
    } catch (OculusException ignore) {}  // 
		return this;
	}
	
	/** Returns the bo ObjectContext */
	public IObjectContext getObjectContext()
	{
		return _context;
	}
  
  public IRObject grantPermissions(IIID useriid, Set gs) throws OculusException
  {
    try
    {
      // get a 'system' user context
      IObjectContext systemContext = new ObjectContext();
      ICRMConnection conn = getObjectContext().getCRM().connect("system", "system");
      systemContext.setConnection(conn);
      // get the ACM for the system context
      com.oculussoftware.api.sysi.sec.IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(systemContext);
      // request the actual grant
		acm.grantPermissions(useriid.getLongValue(), this, gs);
    }//end try
    catch (OculusException ocu)
    { throw ocu; }
    catch (Exception exp)
    { throw new OculusException(exp); }
    return this;
  }
  
}