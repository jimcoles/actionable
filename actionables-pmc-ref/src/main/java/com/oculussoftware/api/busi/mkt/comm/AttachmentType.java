package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types of file attachments that this
* system supports.  One type is NORMAL, which is just a general attachment.  The other
* type is an ENGRSPEC, which marks the attachment as being an engineering understanding
* of the feature to which it is attached.
*
* @author Saleem Shafi
*/

/*
* $Workfile: AttachmentType.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class AttachmentType extends IntEnum
{
  /** This value (1) refers to a normal attachment. */
  public final static AttachmentType NORMAL   = new AttachmentType(1);
  /** This value (2) refers to an attachment that acts as an Engineering Specification. */
  public final static AttachmentType ENGRSPEC = new AttachmentType(2);
  
  /** Private constructor */
  private AttachmentType(int s) { super(s); }
  
	/** Returns the AttachmentType instance that corresponds to the given parameter.
  *
  * @param i int value of the desired AttachmentType
  * @return the AttachmentType corresponding to the given parameter
  * @exception com.oculussoftware.api.sysi.OculusException if the parameter does not
  *            corespond to a valid AttachmentType
  */
  public static AttachmentType getInstance(int d) throws OculusException
  {
    if(d == 1)
      return NORMAL;
    else if(d == 2)
      return ENGRSPEC;
    else
      throw new OculusException("Invalid HyperLinkType.");
  }
}