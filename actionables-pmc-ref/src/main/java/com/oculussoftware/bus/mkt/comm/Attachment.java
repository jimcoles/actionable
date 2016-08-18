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
import java.io.*;

/**
* Filename:    Attachment.java
* Date:        2/15/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
* ---             Saleem Shafi    2/24/00     Made this class use the base-class for
*                                             load(), delete(), get() and put() to
*                                             increase reuseability.  Also moved the IRPropertyMap
*                                             interface to the implementation object instead of the
*                                             interface to force clients to use the getProperities() method.
*                                             Note: Any overriding of protected Strings must happen in the
*                                             constructor not by defining a new variable.
* ---             Saleem Shafi    3/21/00     Added the download() method that is responsible for reading in
*                                             the file from the harddrive.
* BUG00215        Saleem Shafi    5/18/00     Commented out the Name information because we weren't using it.
* BUG00411		  Saleem Shafi	  5/24/00     Changed the \\JavaWebServer2.0\\ to .\\
*/

public class Attachment extends BusinessObject implements IAttachment
{
  protected String COL_TYPE = "TYPE";
  protected String COL_FILENAME = "FILENAME";
  protected String COL_FILECATEGORYCODE = "FILECATEGORYCODE";
  protected String COL_FILECONTENT = "FILECONTENT";
  protected String COL_FILESIZEBYTES = "FILESIZEBYTES";
  protected String COL_PARENTOBJECTID = "PAROBJECTID";
  protected String COL_VIEWABLE = "INCLUDEINDOCRPTS";

  protected IIID _categoryIID,
                 _parentObjIID;
  protected byte[] _content;
  protected AttachmentType _type;
  protected IRProperty _filesize, _filename, _viewable;

	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public Attachment() throws OculusException
	{
    super();
    TABLE = "ATTACHMENT";
    COL_GUID = "GUID";
    _filesize = new BMProperty(this);
    _filename = new BMProperty(this);
    _viewable = new BMProperty(this);
    
    _filesize.setDefnObject(IDCONST.FILESIZE.getIIDValue());
    _filename.setDefnObject(IDCONST.FILENAME.getIIDValue());
    _viewable.setDefnObject(IDCONST.FILEVIEWABLE.getIIDValue());
    _categoryIID = new SequentialIID(1);
    _type = AttachmentType.NORMAL;
}

  //-------------------------- Protected Methods -----------------------------
  public IAttachment setAttachmentType(AttachmentType type)
    throws ORIOException
  {
    if (getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    _type = type;
    return this;
  }
  
  public IAttachment setViewable(boolean viewable)
    throws ORIOException
  {
    _viewable.setValue(new Boolean(viewable));
    return this;
  }


 //------------------------ ACCESSORS -------------------------------

  public boolean getViewable()
    throws OculusException
  {
    if (_viewable != null && _viewable.getValue() != null)
      return ((Boolean)_viewable.getValue()).booleanValue();
    else
      return false;
  }


  public AttachmentType getAttachmentType()
  { return _type; }
  
  protected String getLoadQuery()
    throws ORIOException
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
           "   "+COL_DESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
//           " , "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
           " , "+COL_FILENAME+"='"+SQLUtil.primer(getFilename())+"' "+
           " , "+COL_TYPE+"= "+getAttachmentType().getIntValue()+" "+
           " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
           " , "+COL_VIEWABLE+"= "+(getViewable()?"1":"0")+" "+
           " , "+COL_FILECATEGORYCODE+"= "+getCategoryCode().getLongValue()+" "+
           " , "+COL_FILESIZEBYTES+"= "+getFileSize()+" "+
           (!getDefnObject().hasStateMachine()?"":" , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" ")+
           " , "+COL_PARENTOBJECTID+"= "+_parentObjIID.getLongValue()+" "+
           " , "+COL_ACCESSID+"= "+getAccessIID()+" "+
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
               +COL_GUID+", "
//               +COL_NAME+", "
               +COL_FILENAME+", "
               +COL_DESCRIPTION+", "
               +COL_DELETESTATE+", "
               +COL_PARENTOBJECTID+", "
               +COL_CREATIONDATE+", "
               +COL_CREATORID+", "
               +COL_ACCESSID+", "
               +COL_MESSAGEATTACHED+", "
               +COL_VIEWABLE+", "
               +COL_FILEATTACHED+", "
               +COL_LINKATTACHED+", "
               +COL_FILECATEGORYCODE+", "
               +COL_FILESIZEBYTES+", "
               +COL_TYPE+
           ") "+
           " VALUES "+
           " ("+getIID().getLongValue()+","
              +getDefnObject().getIID().getLongValue()+","
              +(!getDefnObject().hasStateMachine()?"":getStateObject().getIID().getLongValue()+",")
              +"'"+getGUID().toString()+"',"
//              +"'"+SQLUtil.primer(getName())+"',"
              +"'"+SQLUtil.primer(getFilename())+"',"
              +"'"+SQLUtil.primer(getDescription())+"',"
              +getDeleteState().getIntValue()+","
              +_parentObjIID.getLongValue()+","
              +"'"+getCreationDate().toString()+"',"
              +getCreatorIID().getLongValue()+","
              +getAccessIID().getLongValue()+","
              +(hasMessageAttached()?"1":"0")+","
              +(getViewable()?"1":"0")+","
              +(hasFileAttached()?"1":"0")+","
              +(hasLinkAttached()?"1":"0")+","
              +getCategoryCode().getLongValue()+","
              +getFileSize()+","
              +getAttachmentType().getIntValue()+
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
    super.load(results);
    
    setFileSize(results.getInt(COL_FILESIZEBYTES));
//    setName(results.getString(COL_NAME));
    setFilename(results.getString(COL_FILENAME));
    setDescription(results.getString(COL_DESCRIPTION));
    setContent(results.getBinaryStream(COL_FILECONTENT));
    setAttachmentType(AttachmentType.getInstance(results.getInt(COL_TYPE)));
    setViewable(results.getInt(COL_VIEWABLE) == 1);
    _parentObjIID = new SequentialIID(results.getLong(COL_PARENTOBJECTID));
    setCategoryCode(new SequentialIID(results.getLong(COL_FILECATEGORYCODE)));
  }

  protected PSPKind needsPreparedStatement() { return PSPKind.INPUTSTREAM; }
  
  protected String getPSPUpdateQuery()
    throws OculusException
  {
    return " UPDATE "+TABLE+
           " SET "+COL_FILECONTENT+"=?, "+
           " "+COL_FILESIZEBYTES+"=? "+
           " WHERE "+COL_OBJECTID+"="+getIID().getLongValue()+"";
  }
  
  protected Object getPSPValue()
    throws OculusException
  {
    try
    {
      if (getContent() == null || getContent().length == 0)
      {
        java.io.File fileInfo = new java.io.File(".\\"+getFilename());
        setFileSize((int)fileInfo.length());
      
        FileInputStream thisFile = new FileInputStream(".\\"+getFilename());                        
        if (getFileSize() != 0)
          return thisFile;
        else
        {
          byte[] temp = new byte[] {20};
          return new ByteArrayInputStream(temp);
        }
      }
      return new ByteArrayInputStream(getContent());
    }
    catch (FileNotFoundException exp) { throw new OculusException(exp); }
    catch (IOException exp) { throw new OculusException(exp); }
  }

  protected int getPSPLength()
    throws OculusException
  {
    return getFileSize();
  }

//----------------- IProduct Methods ------------------------------------
	/** I don't know what this does */
  public ISet getBranches()
 	{
 		return null;
	}



  public void download()
    throws OculusException
  {
    try
    {
      byte[] data = getContent();
      String dest = ".\\public_html\\files\\"+getFilename();
      OutputStream osFileOutput = new FileOutputStream(dest);
      // Read-Write the file byte by byte
      int filesize = getFileSize();
      for (int i=0 ; i < filesize; i++)
        if (osFileOutput != null)
          osFileOutput.write(data[i]);
      if (osFileOutput != null)
        osFileOutput.close();
    }
    catch (IOException exp) { throw new OculusException(exp); }
  }


  //------------------------ MUTATORS -------------------------------
  public IAttachment setCategoryCode(IIID categoryCode)
    throws ORIOException
  {
    _categoryIID = categoryCode;
    return this;
  }
    
  public IAttachment setFileSize(int bytes)
    throws ORIOException
  {
    _filesize.setValue(new Integer(bytes));
    return this;
  }

  public IAttachment setContent(byte[] content)
    throws OculusException
  {
    _content = content;
    return this;
  }

  public IAttachment setContent(InputStream content)
    throws OculusException
  {
    try
    {
      content.reset();
//      setFileSize(content.available());
      _content = new byte[getFileSize()];
      int i = 0;
      for (int result = content.read(); i < getFileSize(); result = content.read())
        _content[i++] = (byte)result;
      content.close();
    }
    catch (IOException exp) { throw new ORIOException(exp); }
    return this;
  }
    
  public IAttachment setFilename(String filename)
    throws OculusException
  {
    _filename.setValue(filename);
    return this;
  }
    
  public IAttachment setParentObject(IBusinessObject parentBObj)
    throws ORIOException
  {
    return setParentObjectIID(parentBObj.getIID());
  }

  public IAttachment setParentObjectIID(IIID parobjiid)
    throws ORIOException
  {
    _parentObjIID = parobjiid;
    return this;
  }

  //------------------------ ACCESSORS -------------------------------

  public IIID getCategoryCode()
    throws ORIOException
  {
    return _categoryIID;
  }
    
  public int getFileSize()
    throws OculusException
  {
    if (_filesize.getValue() != null)
      return ((Integer)_filesize.getValue()).intValue();
    else
      return -1;
  }

  public byte[] getContent()
    throws ORIOException
  {
    return _content;
  }
    
  public String getFilename()
    throws OculusException
  {
    return (String)_filename.getValue();
  }
    
  
  public IAttachment createCopy()
    throws OculusException
  {
    IAttachment newAttachment = (IAttachment)getObjectContext().getCRM().getCompObject(getObjectContext(),"File",(IDataSet)null,true);
    newAttachment.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",IDCONST.FILE.getIIDValue()));

    newAttachment.setFilename(getFilename());
    newAttachment.setDescription(getDescription());
    newAttachment.setContent(getContent());
    newAttachment.setMessageAttached(hasMessageAttached());
    newAttachment.setLinkAttached(hasLinkAttached());
    newAttachment.setFileAttached(hasFileAttached());
    newAttachment.setCategoryCode(getCategoryCode());
    newAttachment.setAttachmentType(getAttachmentType());
    newAttachment.setFileSize(getFileSize());

    return newAttachment;
  }

//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
    throws OculusException
  {
    if (key instanceof String)
    {
      if (key.equals(LABEL_FILESIZEBYTES))
        return _filesize;
      if (key.equals(LABEL_FILENAME))
        return _filesize;
      else
        return super.get(key);
    }
    return null;
  }
  
  public void put(Object key, Object value)
    throws OculusException
  {
    if (key instanceof String && value instanceof IRProperty)
    {
      IRProperty property = (IRProperty)value;
      if (key.equals(LABEL_FILESIZEBYTES))
        setFileSize(((Integer)property.getValue()).intValue());
      if (key.equals(LABEL_FILENAME))
        setFilename((String)property.getValue());
      else
        super.put(key,value);
    }
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
    Attachment attach = null;
      attach = new Attachment();
      attach.setIID(getIID());
      attach.setObjectContext(getObjectContext());
      attach.setPersState(getPersState());
      attach._classIID = _classIID;
      attach._stateIID = _stateIID;
      attach.setDeleteState(getDeleteState());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
        attach.putAll(getProperties());
//      attach.setName(getName());
      attach.setFilename(getFilename());
      attach.setDescription(getDescription());
      attach._content = _content;
      attach._creatorIID = _creatorIID;
      attach._accessIID = _accessIID;
      attach.setCreationDate(getCreationDate());
      attach.setMessageAttached(hasMessageAttached());
      attach.setLinkAttached(hasLinkAttached());
      attach.setFileAttached(hasFileAttached());

      attach._parentObjIID = _parentObjIID;
      attach.setCategoryCode(getCategoryCode());
      attach.setAttachmentType(getAttachmentType());
      attach.setFileSize(getFileSize());
      attach.setViewable(getViewable());
    return attach;
  } 
}