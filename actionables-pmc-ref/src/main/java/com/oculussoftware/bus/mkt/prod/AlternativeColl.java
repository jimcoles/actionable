package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    Alternative.java
* Date:        2/17/00
* Description: Provides the functionality for a basic version for a Alternative.
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number    Programmer      Date        Description
*/

public class AlternativeColl extends BusinessObjectColl implements IAlternativeColl
{
	protected String TABLE      = "ALTERNATIVE";
	protected String COL_ALTERNATIVESETID      = "ALTERNATIVESETID";


  //--------------------------- Public Constructors --------------------------
  /** Default constructor set the state to NEW and gets a Coll of empty properties */  
  public AlternativeColl() throws OculusException
  {
  super();

  }


  protected AlternativeColl(Comparator sortCrit) throws OculusException
  {
  super (sortCrit);
  }


//----------------- IPoolable Methods ------------------------------------
  /** Returns a duplicate IProductColl object, but without the ObjectContext */
  public Object clone()
  {
  AlternativeColl altColl = null;
  try
  {
    altColl = new AlternativeColl();
    altColl.setIID(_iid);
    altColl._items = this._items;
    altColl.reset();
  }
  catch (OculusException orioExp) {}
  return altColl;
  }


//----------------- IPoolable Methods ------------------------------------
/** Returns a duplicate IProductColl object, but without the ObjectContext */
public Object dolly() throws OculusException
{
  AlternativeColl altColl = null;
  altColl = new AlternativeColl();
  altColl.setIID(_iid);
  altColl._items = this._items;
  altColl.reset();
  return altColl;
}


  protected String getClassName() { return "Alternative"; }


  //------------------------ Protected Methods --------------------------------
  protected String getLoadQuery()
  throws ORIOException
  {
  return " SELECT *"+
       " FROM "+TABLE + " WHERE " + COL_ALTERNATIVESETID + "=" + getIID();
  }


  /**
  *  Returns true if there are more IProducts in the Coll
  */
  public boolean hasMoreAlternatives()
  {
    //return false;
    return hasNext();
  }


/**
 * Test Stub
 * Creation date: (4/18/00 1:43:03 PM)
 * @param args java.lang.String[]
 */
public static void main(String[] args) throws Exception
{
  ObjectContext context = new ObjectContext();
  ICRMConnection conn = context.getCRM().connect("system","system");
  context.setConnection(conn);
  IIID id = new SequentialIID(955577574454L);
  //get an alternatives coll  
  IAlternativeColl obj = (IAlternativeColl) context.getCRM().getCompObject(context, "AlternativeColl", id, false);
  IRClass usrClass = (IRClass) context.getCRM().getCompObject(context, "Class", IDCONST.ALTERNATIVECOLL.getIIDValue());
  while (obj.hasMoreAlternatives())
  {
    IAlternative alt = obj.nextAlternative();
    System.out.println(alt.getName());
  }

}


  //----------------- IProductColl Methods ------------------------------------
  /**
  * Returns the next available IProduct in the Coll.  If the next IProduct turns
  * out to be null, just go to the next one.
   */
  public IAlternative nextAlternative()
  throws OculusException
  {
  return (IAlternative)next();
  }


//------------------- IBusinessObjectColl Methods --------------------------
  public IRCollection setSort(Comparator sortCrit)
  throws OculusException
  {
  AlternativeColl sortedColl = new AlternativeColl(sortCrit);
  sortedColl._items.addAll(this._items);
  return sortedColl;
  }
}
