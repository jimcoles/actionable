package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

import java.io.*;

/** This interface represents a file attached to a business object.  This file can
* be uploaded and associated with the business object and later downloaded.  This
* interface should be able to handle any type of file, both ascii and binary.
*
* @author Saleem Shafi
*/

/*
* $Workfile: IAttachment.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
* ---             Saleem Shafi    3/21/00     Added the download() method.
*/

public interface IAttachment extends IBusinessObject
{
  /** This value can be used to retrieve the file size property from an IRPropertyMap */
  public static final String LABEL_FILESIZEBYTES = "prod"+IDCONST.FILESIZE.getIIDValue();
  /** This value can be used to retrieve the file name property from an IRPropertyMap */
  public static final String LABEL_FILENAME = "prod"+IDCONST.NAME.getIIDValue();

  /** Writes the contents of the file to a physical file on the operating system.
  * This method is needed whenever the user wants to download a file.
  *
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public void download()
    throws OculusException;

  /** Creates an exact copy of this attachment.  This is used primarily when business
  * objects are copied and their attachments need to be copied as well.
  *
  * @return the new copy of this attachment
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment createCopy()
    throws OculusException;
    
  /** Sets the category of this attachment to the category specified by the given IIID.
  * Note: This method is not being used, but was intended for better attachment organization.
  *
  * @param categoryCode the IIID of the category for this attachment
  * @return this attachment
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAttachment setCategoryCode(IIID categoryCode)
    throws ORIOException;
    
  /** Sets the size of the file in bytes.
  *
  * @param bytes the number of bytes in this attachment
  * @return this attachment
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment setFileSize(int bytes)
    throws OculusException;

  /** Sets the content of the attachment.
  *
  * @param content the binary content of the file
  * @return this attachment
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment setContent(byte[] content)
    throws OculusException;
    
  /** Sets the content of the attachment.
  *
  * @param content the InputStream containing the content of the file
  * @return this attachment
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment setContent(InputStream content)
    throws OculusException;
    
  /** Sets the name of the file.
  *
  * @param filename the name of the file
  * @return this attachment
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment setFilename(String filename)
    throws OculusException;
    
  /** Sets the business object that this file is attached to.
  *
  * @param parentBObj the business object to attach to
  * @return this attachment
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAttachment setParentObject(IBusinessObject parentBObj)
    throws ORIOException;

  /** Sets the business object that this file is attached to based on the IIID given.
  *
  * @param parobjiid the IIID of the business object to attach to
  * @return this attachment
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAttachment setParentObjectIID(IIID parobjiid)
    throws ORIOException;

  /** Sets type of this attachment.
  *
  * @param type the type of attachment
  * @return this attachment
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IAttachment setAttachmentType(AttachmentType type)
    throws ORIOException;
    
  /** Sets the viewability of this attachment in reports.
  *
  * @param type the type of attachment
  * @return this attachment
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IAttachment setViewable(boolean viewable)
    throws OculusException;

  /** Returns the IIID of the category that this attachment is in.
  * Note: this method is not being used.
  *
  * @return the IIID of the category
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public IIID getCategoryCode()
    throws ORIOException;
    
  /** Returns the size of the file in bytes.
  *
  * @return the size of the file in bytes
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public int getFileSize()
    throws OculusException;

  /** Returns the content of the file in byte[].
  *
  * @return the content of the attachment
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public byte[] getContent()
    throws ORIOException;
    
  /** Returns the name of the file.
  *
  * @return the name of the file
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public String getFilename()
    throws OculusException;
  
  /** Returns the AttachmentType for this attachment.
  *
  * @return the type of this attachment
  * @exception com.oculussoftware.api.repi.ORIOException
  */
  public AttachmentType getAttachmentType()
    throws ORIOException;
    
  /** Returns whether or not this attachment is viewable in reports.
  *
  * @return true if the file is viewable, false otherwise.
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public boolean getViewable()
    throws OculusException;
}