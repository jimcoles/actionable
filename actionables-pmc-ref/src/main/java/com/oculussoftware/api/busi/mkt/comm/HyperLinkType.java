package com.oculussoftware.api.busi.mkt.comm;

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.sysi.OculusException;

/** This class is an enumeration of the different types of URL attachments that this
* system supports.  One type is NORMAL, which is just a general URL.  Another type is
* an ENGRSPEC, which marks the attachment as being a reference to an engineering
* specification.  The DOCUMENT type refers to a URL that links to a file on an internal
* network, not the internet.
*
* @author Saleem Shafi
*/

/*
* $Workfile: HyperLinkType.java $
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public final class HyperLinkType extends IntEnum
{
  /** This value (0) refers to an attachment to an internal document. */
  public final static HyperLinkType DOCUMENT = new HyperLinkType(0);
  /** This value (1) refers to a normal URL attachment. */
  public final static HyperLinkType NORMAL   = new HyperLinkType(1);
  /** This value (0) refers to an engineering specification URL attachment. */
  public final static HyperLinkType ENGRSPEC = new HyperLinkType(2);
  
  /** Private constructor */
  private HyperLinkType(int s) { super(s); }
  
	/** Returns the HyperLinkType instance that corresponds to the given parameter.
  *
  * @param i int value of the desired HyperLinkType
  * @return the AttachmentType corresponding to the given parameter
  * @exception com.oculussoftware.api.sysi.OculusException if the parameter does not
  *            corespond to a valid HyperLinkType
  */
  public static HyperLinkType getInstance(int d) throws OculusException
  {
    if(d == 0)
      return DOCUMENT;
    else if(d == 1)
      return NORMAL;
    else if(d == 2)
      return ENGRSPEC;
    else
      throw new OculusException("Invalid HyperLinkType.");
  } 
}