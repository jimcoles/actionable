package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.service.*;
import java.sql.*;
import java.util.*;
import java.io.*;

/**
* Filename:    EngrSpecFolder.java
* Date:        2/15/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/
//extends BusinessObject
public class EngrSpecFolder extends ReposObject implements IEngrSpecFolder
{
  protected String COL_OBJECTID = "OBJECTID";
  protected String COL_FOLDERNETPATH = "FOLDERNETPATH";
  protected String COL_INDEXFILE = "INDEXFILE";
  //protected String COL_NAME = "NAME";
  //protected String COL_DESCRIPTION = "DESCRIPTION";
  protected String TABLE;
  

  protected IRProperty _strFolderNetPath, _strIndexFile;//, _description;

  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a list of empty properties */
  public EngrSpecFolder() throws OculusException
  {
    super();
    TABLE = "ENGRDOCCONFIG";
    _strFolderNetPath = new BMProperty(this);
    _strIndexFile = new BMProperty(this);

    //create new IDCONST Values?  just need type info so...    
    _strFolderNetPath.setDefnObject(IDCONST.NAME.getIIDValue());
    _strIndexFile.setDefnObject(IDCONST.NAME.getIIDValue());

    
  }


  //------------------------ ACCESSORS -------------------------------

  protected String getLoadQuery()
  throws ORIOException
  {
    return "SELECT * "+
      "FROM "+TABLE+" "+
      "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 

  protected String getLoadPropertiesQuery()
  throws ORIOException
  {
    //return null since no extended attributes
    return null;
  } 

  protected String getUpdateQuery()
  throws OculusException
  {
    return " UPDATE "+TABLE+" "+
      " SET "+
//      "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
//      " ,  "+COL_DESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
      "   " + COL_FOLDERNETPATH+"='"+SQLUtil.primer(getFolderNetPath())+"' "+
      " , "+COL_INDEXFILE+"='"+SQLUtil.primer(getIndexFile())+"' "+
//      " , "+COL_VERSIONID+"= "+_verIID.getLongValue()+" "+
      " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 

  protected String getCreateQuery()
  throws OculusException
  {
    return "INSERT INTO "+TABLE+" "+
      " ("+COL_OBJECTID+", "
//      +COL_NAME+", "
//      +COL_DESCRIPTION+", " +
      +COL_FOLDERNETPATH+", "
      +COL_INDEXFILE+" " +
//      +COL_VERSIONID+" "+
      ") "+
      " VALUES "+
      " ("+getIID().getLongValue()+","
//      +"'"+SQLUtil.primer(getName())+"',"
//      +"'"+SQLUtil.primer(getDescription())+"'," +
      +"'"+SQLUtil.primer(getFolderNetPath())+"',"
      +"'"+SQLUtil.primer(getIndexFile())+"'" +
//      + _verIID.getLongValue()+"" +
      ") ";
  } 

  protected String getDeleteQuery()
  throws ORIOException
  {
    return " DELETE FROM "+TABLE+" "+
      " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  } 

  protected void load(IDataSet results)
  throws OculusException
  {
    if (results.getIID() == null)
      results.setIID(results.getLong(COL_OBJECTID));
 //   setName(results.getString(COL_NAME));
    //setDescription(results.getString(COL_DESCRIPTION));
    setFolderNetPath(results.getString(COL_FOLDERNETPATH));
    setIndexFile(results.getString(COL_INDEXFILE));
//    _verIID = getObjectContext().getRepository().makeReposID(results.getLong(COL_VERSIONID));
    
  }


//----------------- IRObject Methods ------------------------------------


//------------------------ MUTATORS -------------------------------



	
	/** Sets the name attribute of this bo */
	public IRElement setName(String name)
    throws OculusException
	{
	    if ((getPersState().equals(PersState.NEW)))    
      {
        ICommConfig commconfig = (ICommConfig)getObjectContext().getCRM().getCompObject(getObjectContext(),"CommConfig",IDCONST.COMMCONFIG.getIIDValue());
        String path = commconfig.getDocNetworkPath() + "\\" + name+ "\\";
        setFolderNetPath(path);
        //create the folder
        (new OSFileAccess()).mkdir(path);
      }
      else
        throw new OculusException("You cannot edit the folder name after creation");     
    return this;
	}

	
	/** Sets the description attribute of this bo */
	public IRElement setDescription(String description)
    throws OculusException
	{
    //_description.setValue(description);
		//return this;
    return null;
	}


  public IEngrSpecFolder setIndexFile(String str)
  throws OculusException
  {
    _strIndexFile.setValue(str);  
    return this;
  }

/*
  public IEngrSpecFolder setVersionID(IIID id)
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _verIID = id;
    return this;
  }
*/  
  //this method is private to ensure it only gets called by the dolly 
  //method
  private IEngrSpecFolder setFolderNetPath(String str)
  throws OculusException
  {
    _strFolderNetPath.setValue(str);
    return this;
  }
  

  //------------------------ ACCESSORS -------------------------------
	/** Returns the name attribute of this bo. */
  public String getName()
    throws OculusException
	{
      String str = getFolderNetPath();
      //remove trailing \
      str = str.substring(0, str.length()-1);
      return str.substring(str.lastIndexOf("\\")+1,str.length()); 
	}

	/** Returns the description attribute of this bo */
	public String getDescription()
		throws OculusException
	{
		//return (String)_description.getValue();
	  return null;
  }
  
  public String getFolderNetPath()
  throws OculusException
  {
    return (String)_strFolderNetPath.getValue();
  }
  
  public boolean hasAutocreateIndex()
  throws OculusException
  {
    if (getIndexFile().length()==0)
      return true;
    else 
      return false;  
  }    

  public String getIndexFile()
  throws OculusException
  {
		return (String)_strIndexFile.getValue();
  }

  public File[] getFilesInFolder()
  throws OculusException
  {
    OSFileAccess osf = new OSFileAccess();
    File f = osf.getFile(getFolderNetPath());
    return osf.getFiles(f);
  }  



  //----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
  throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_FOLDERNETPATH))
        return _strFolderNetPath;
 //     if (key.equals(LABEL_DESCRIPTION))
 //       return _description;
      if (key.equals(LABEL_INDEXFILE))
        return _strIndexFile;
    }
    return null;
  }

  public void put(Object key, Object value)
  throws OculusException
  {
    if (key instanceof String && value instanceof IRProperty)
    {
      IRProperty property = (IRProperty)value;
      if (key.equals(LABEL_FOLDERNETPATH))
        setFolderNetPath((String)property.getValue());
   //   if (key.equals(LABEL_DESCRIPTION));
   //     setDescription((String)property.getValue());
      if (key.equals(LABEL_INDEXFILE));
        setIndexFile((String)property.getValue());

    }
  }
  public IRPropertyMap getProperties()
  throws OculusException
  {
    return null;
  }
  //----------------- IPoolable Methods ------------------------------------
  /**
  *  Returns a copy of the current product object.
  *
  * Note: The exceptions are being withheld because this method overrides
  * the one in Object().  Consider using a different method name since it
  * doesn't look like we're taking advantage of Cloneable.
  */
  public Object dolly() throws OculusException
  {
    EngrSpecFolder attach = null;
    attach = new EngrSpecFolder();
    attach.setIID(getIID());
    attach.setObjectContext(getObjectContext());
    attach.setPersState(getPersState());
    //attach.setName(getName());
    attach.setDescription(getDescription());
    //specific to EngSpecFolder
  //  attach._verIID = _verIID;
    attach.setFolderNetPath(getFolderNetPath());
    attach.setIndexFile(getIndexFile()); 


    return attach;
  } 

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
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

    return this;
  }

  
}
