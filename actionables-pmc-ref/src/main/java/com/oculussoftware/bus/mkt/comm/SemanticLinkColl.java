package com.oculussoftware.bus.mkt.comm;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.api.busi.mkt.comm.*;

import java.util.*;

/**
* Filename:    ProcessRoleColl.java
* Date:        3/14/00
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class SemanticLinkColl extends ReposObjectColl implements ISemanticLinkColl, IPersistable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */

  protected String TABLE = "SEMANTICLINK";
  protected String COL_PARENT = "SRCOBJECTID";
  protected String COL_LINKTYPE = "LINKTYPE";
  
  protected LinkKind _linktype = null;
  
	//----------------------------- Public Constructor -------------------------
	/** Default constructor just initializes the product list */
	public SemanticLinkColl() throws OculusException
	{
    super();
	}//

  protected SemanticLinkColl(Comparator sortCrit) throws OculusException
  {
    super (sortCrit);
  }//
  
  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery() throws ORIOException
  {
    return " SELECT * FROM "+TABLE+
           " WHERE "+COL_PARENT+"="+getIID().getLongValue()+" "
             +(_linktype == null?"":" AND "+COL_LINKTYPE+"="+_linktype.getIntValue());
  }//

  protected String getClassName () { return "SemanticLink"; }
	//----------------- IHyperLinkList Methods ------------------------------------
	/**
	*
 	*/
  public ISemanticLink nextSemanticLink() throws OculusException
	{
		return (ISemanticLink)next();
	}//
	
	/**
	*	
	*/
  public boolean hasMoreSemanticLinks()
	{
		return hasNext();
	}//
  
//------------------- IBusinessObjectList Methods --------------------------
  public IRCollection setSort(Comparator sortCrit) throws OculusException
  {
    SemanticLinkColl sortedList = new SemanticLinkColl(sortCrit);
    sortedList._items.addAll(this._items);
    return sortedList;
  }//
  
	
//----------------- IPoolable Methods ------------------------------------

  /** Pseudo-constructor that expects the IIID of the object and the ObjectContext as args */
  public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    super.construct(context, args);
    
    if (args.containsKey("PARENTCOL"))
      COL_PARENT = (String)args.get("PARENTCOL");
    if (args.containsKey(COL_LINKTYPE))
      _linktype = (LinkKind)args.get(COL_LINKTYPE);
    
    return this;
  }



	public Object dolly() throws OculusException
	{
	  SemanticLinkColl roleAssList = null;
			roleAssList = new SemanticLinkColl();
			roleAssList.setIID(_iid);
			roleAssList._items = this._items;
			roleAssList.reset();
		return roleAssList;
	}//
}//end class