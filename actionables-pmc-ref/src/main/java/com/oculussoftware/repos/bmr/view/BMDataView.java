package com.oculussoftware.repos.bmr.view;

/*
* $Workfile: BMDataView.java $
* Create Date: 5/11/00
*
* Description: Represents a stored query.
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author:  Jcoles
* @version: 1.2
*
* $History: BMDataView.java $
 * 
 * *****************  Version 14  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * Bug Fix: #2207
 * 
 * *****************  Version 13  *****************
 * User: Eroyal       Date: 8/08/00    Time: 5:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * setting up docreport
 * 
 * *****************  Version 12  *****************
 * User: Eroyal       Date: 8/02/00    Time: 6:44p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * added more functionality to Custom Report and Adv Srch
 * 
 * *****************  Version 11  *****************
 * User: Eroyal       Date: 7/26/00    Time: 6:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * added yet again. more functionality to the custom reports
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 7/25/00    Time: 4:49p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * ConfigKind = FULL
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 7/24/00    Time: 7:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 7-24-00
 * 
 * added more functionality to Custom Report
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 7/22/00    Time: 3:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 7-22-00
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 7/20/00    Time: 6:01p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 7-20-00
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 7/19/00    Time: 6:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 7-19-00
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 7/18/00    Time: 7:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 7-18-00
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 7/17/00    Time: 6:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 7-17-00
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 7/14/00    Time: 7:02p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 2  *****************
 * User: Eroyal       Date: 7/14/00    Time: 2:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * Custom Reports
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:41a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * Inital create.
*/


import com.oculussoftware.api.busi.mkt.prod.*;
import java.util.*;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.license.ModuleKind;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.repos.query.*;
import com.oculussoftware.repos.xml.*;

import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.util.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.rdb.*;

import java.util.*;
import java.sql.*;

/**
*/
public abstract class BMDataView extends BMModelElement  implements IRDataView
{
  //------------------------------------------------------------------
  // Public constants
  //------------------------------------------------------------------
  public static final String TABLE         = "DATAVIEW";
  public static final String COL_QUERYEXPR = "QUERYEXPRESSION";
  public static final String COL_OWNERID   = "OWNERID";
  public static final String COL_VIEWTYPE  = "VIEWTYPE";
  public static final String COL_ACCESSID  = "ACCESSID";
  public static final String COL_MODULE    = "MODULECODE";
//  public static final String COL_SCOPETYPE = "SCOPETYPE";
  
  //------------------------------------------------------------------
  // Private instance variables
  //------------------------------------------------------------------
  protected IXMLTree   _xml;
  protected IIID       _owneriid;
  protected AccessKind _ak = AccessKind.PUBLIC;
//  protected IDCONST    _scopetype;
  protected ModuleKind _mk;
    
  //------------------------------------------------------------------
  // Public constructor(s)
  //------------------------------------------------------------------
  public BMDataView() throws OculusException
  {
    guid = new GUID(); 
    _configkind = ConfigKind.FULL;//dunno why we need this
  }
  
  //------------------------------------------------------------------
  // Public methods
  //------------------------------------------------------------------
  public IUser getOwner() throws OculusException
  { return (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(), "User", _owneriid); }
  
  public IIID getOwnerIID() throws OculusException
  { return _owneriid; }
  
  public IXMLTree getXML( ) throws OculusException
  { return _xml; }
  
  public AccessKind getAccessKind() throws OculusException
  { return _ak; }  
   
//  public IDCONST getScopeType() throws OculusException
//  { return _scopetype; }
  
  public ModuleKind getModuleKind() throws OculusException
  { return _mk; }
  
  
  /**
  * make the subclass return its type.  I don't want people instantiating
  * this class.
  */
  public abstract ViewType getViewType() throws OculusException;
  
  
  public IRDataView setXML(IXMLTree xml) throws OculusException
  { 
    _xml = xml;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
  public IRDataView setOwner(IUser user) throws OculusException
  { 
    _owneriid = user.getIID();
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
  public IRDataView setOwnerIID(IIID owneriid) throws OculusException
  { 
    _owneriid = owneriid;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
  public IRDataView setAccessKind(AccessKind ak) throws OculusException
  { 
    _ak = ak;
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
//  public IRDataView setScopeType(IDCONST idc) throws OculusException
//  {
//    if(IDCONST.PRODUCTVERSION.equals(idc) || IDCONST.PRODUCT.equals(idc) || IDCONST.FOLDER.equals(idc))
//      _scopetype = idc;
//    else
//      throw new OculusException("Invalid ScopeType (only Products, Versions or Folders are valid)");
//    if(getPersState().equals(PersState.UNMODIFIED))
//      setPersState(PersState.MODIFIED);
//    return this;
//  }
  
  public IRDataView setModuleKind(ModuleKind mk) throws OculusException
  {
//    if(ModuleKind.ACCOLADES.equals(mk) || ModuleKind.COMPASS.equals(mk))
      _mk = mk;
//    else
//      throw new OculusException("Invalid ModuleKind (only Accolades or Compass are valid)");
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    return this; 
  }
  
    
  /**
  *
  */
  public IPersistable save() throws ORIOException
  {
    if (!isLocked() && !getPersState().equals(PersState.NEW))  // If the bo isn't locked, throw an exception
      throw new ORIOException("This object ("+this.getClass().getName()+":"+this.getIID().getLongValue()+") cannot be saved because it is not locked.");

    IRConnection c = null; IPreparedStatementProcessor psp = null; 
    try
    {
      c = getDatabaseConnection();
      if(getPersState() == PersState.NEW)
      {
        //INSERT
        psp = c.prepareProcessor("INSERT INTO "+TABLE+
                   " ("+COL_OBJECTID+", "
                       +COL_BYTEGUID+", "
                       +COL_VIEWTYPE+", "
                       +COL_NAME+", "
                       +COL_OWNERID+", "
                       +COL_QUERYEXPR+", "
                       +COL_ACCESSID+", "
                       +COL_DELETESTATE+", "
                       +COL_ISACTIVE+", "
                       +COL_CONFIGKIND+", "
                       +COL_DESCRIPTION+", "
                       +COL_MODULE+") VALUES ("
                       +iid.getLongValue()+", "
                       +"'"+guid.toString()+"', "
                       +getViewType()+", "
                       +"?, "
                       +_owneriid+", "
                       +"?, "
                       +_ak+", "
                       +_deletestate+", "
                       +(isactive?"1":"0")+", "
                       +_configkind+", "
                       +"?, "
                       +_mk+")");
        psp.setString(1, name);
        psp.setString(2, _xml.toXML());
        psp.setString(3, description);
        psp.update();
        setPersState(PersState.UNMODIFIED);
      }
      else if(getPersState() == PersState.MODIFIED)
      {
        //UPDATE
        psp = c.prepareProcessor("UPDATE "+TABLE+" SET "+
                  COL_NAME+"='"+SQLUtil.primer(name)+"', "+
                  COL_OWNERID+"="+_owneriid+", "+
                  COL_QUERYEXPR+"=?, "+
                  COL_DELETESTATE+"="+_deletestate+", "+
                  COL_ISACTIVE+"="+(isactive?"1":"0")+", "+
                  COL_ACCESSID+"="+_ak+", "+
                  COL_MODULE+"="+_mk+", "+
//                  COL_SCOPETYPE+"="+_scopetype+", "+
                  COL_CONFIGKIND+"="+_configkind+", "+
                  COL_DESCRIPTION+"=?"+
                  " WHERE "+COL_OBJECTID+"="+iid.getLongValue());
        psp.setString(1, _xml.toXML());
        psp.setString(2, description);
        psp.update();
        setPersState(PersState.UNMODIFIED);
      }
      else if(getPersState() == PersState.DELETED)
      {
        //DELETE
        psp = c.prepareProcessor("DELETE FROM "+TABLE+" WHERE OBJECTID = "+getIID());
        psp.update();
      }
    }
    catch(Exception ex) { throw new ORIOException(ex); }
    finally{ if(psp!=null) psp.close(); }
    return this; 
  }
  
  public IPersistable load() throws OculusException
  {
    if(getPersState() != PersState.NEW)
    {
      if(iid == null)
        throw new OculusException("Null IID on load.");
      IRConnection c = null; 
      IQueryProcessor qp = null;
      try
      {
        c = getDatabaseConnection();
        qp = c.createProcessor();
        IDataSet rs = qp.retrieve("SELECT * FROM "+TABLE
                     +" WHERE "+COL_OBJECTID+"="+getIID());
        if(rs.next())
        {
          load(rs);
          setPersState(PersState.UNMODIFIED);
        }
        else
          throw new ORIOException("Object not found on load.");
      }
      catch(Exception e) { throw new OculusException(e); }
      finally { 
        if(qp!=null) qp.close(); 
//        if(c!=null) returnDatabaseConnection(c);
      }
    }
    return this; 
  }
  
  
  //------------------------------------------------------------------
  // Protected methods
  //------------------------------------------------------------------
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository repos = getObjectContext().getRepository();
    _owneriid  = repos.makeReposID(rs.getLong(COL_OWNERID));
//    if(rs.getBinaryStream(COL_QUERYEXPR) != null)
//      _xml = new QueryXML(rs.getBinaryStream(COL_QUERYEXPR));
    _ak = AccessKind.getInstance(repos.makeReposID(rs.getLong(COL_ACCESSID)));
    _mk = ModuleKind.getInstance(rs.getInt(COL_MODULE));
//    _scopetype = IDCONST.getInstance(rs.getLong(COL_SCOPETYPE));
  }
}