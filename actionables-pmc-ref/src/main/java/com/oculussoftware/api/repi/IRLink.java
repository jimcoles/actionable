package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

/** Low-level repository notion roughly equates to an instance of a
*   UML IRAssociation or IRRelationship.  IRLink extedns IRObject
*   because IRLink's can behave like IRObjects, i.e., can have
*   properties and other associations of their own.
*/
public interface IRLink
{
  // don't think a Link needs and id...
//  public IIID     getIID();
  
  public IRLink   setOrigin(IRObject obj);
  public IRObject getOrigin();
  
  public IRLink   setDest(IRObject obj);
  public IRObject getDest();
}