package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import com.oculussoftware.api.busi.common.search.*;
import com.oculussoftware.bus.common.search.*;
import com.oculussoftware.bus.xmeta.XMen;
import com.oculussoftware.api.busi.mkt.comm.*;
import com.oculussoftware.util.*;
import com.oculussoftware.api.service.mail.*;
import com.oculussoftware.service.mail.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.ui.*;

import java.sql.*;
import java.util.*;

/**
* Filename:    ProductVersion.java
* Date:        2/14/00
* Description: Provides the functionality for a basic version for a product.
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
* ---             Saleem Shafi    2/24/00     Added createNewBaseline() method.
* ---             Saleem Shafi    3/3/00      Started using the new method of creating new objects (null IID).
* BUG00076        Saleem Shafi    5/15/00     Changed createCopy to use the defaultCategory
*/

public class ProductVersion extends BusinessObject implements IProductVersion
{
  static public long CLASSID = Long.parseLong("62397249798880782");
  
  protected String COL_PRODUCTID = "PRODUCTID";
  protected String COL_ORDERNUM = "ORDERNUM";
  protected String COL_TARGETRELEASEDATE = "TARGETRELEASEDATE";
  protected String COL_ESTIMATEDRELEASEDATE = "ESTIMATEDRELEASEDATE";
  protected String COL_ACTUALRELEASEDATE = "ACTUALRELEASEDATE";

  protected IIID _productIID, _defCatIID;
  protected IRProperty _orderNum,                // integer type
					   _targetDate,
					   _estimatedDate,
					   _actualDate;

/********************************************
			
**************************/
	//--------------------------- Public Constructors --------------------------
	/** Default constructor set the state to NEW and gets a list of empty properties */	
	public ProductVersion() throws OculusException
	{
	super();
	TABLE = "PRODUCTVERSION";
	COL_GUID = "GUID";
	_orderNum = new BMProperty(this);
  _targetDate = new BMProperty(this);
  _estimatedDate = new BMProperty(this);
  _actualDate = new BMProperty(this);
  _orderNum.setDefnObject(IDCONST.ORDERNUM.getIIDValue());
  _targetDate.setDefnObject(IDCONST.TARGET_RELEASE_DATE.getIIDValue());
  _estimatedDate.setDefnObject(IDCONST.ESTIMATED_RELEASE_DATE.getIIDValue());
  _actualDate.setDefnObject(IDCONST.ACTUAL_RELEASE_DATE.getIIDValue());
  
  }
//----------------- IPoolable Methods ------------------------------------
	/**
	*	Returns a copy of the current product object.
	*
	* Note: The exceptions are being withheld because this method overrides
	* the one in Object().  Consider using a different method name since it
	* doesn't look like we're taking advantage of Cloneable.
	*/
	public Object dolly() throws OculusException
	{
		ProductVersion ver = null;
			ver = new ProductVersion();
			ver.setIID(getIID());
	  ver.setObjectContext(getObjectContext());
	  ver.setPersState(getPersState());
	  ver._classIID = _classIID;
	  ver._stateIID = _stateIID;
    ver._deleteState = _deleteState;
			ver.setName(getName());
			ver.setDescription(getDescription());
	  ver._creatorIID = _creatorIID;
	  ver._accessIID = _accessIID;
	  ver.setCreationDate(getCreationDate());
	  ver.setMessageAttached(hasMessageAttached());
	  ver.setLinkAttached(hasLinkAttached());
	  ver.setFileAttached(hasFileAttached());
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
		ver.putAll(getProperties());

	  ver._productIID = _productIID;
    ver._defCatIID = _defCatIID;
	  ver.setOrderNum(getOrderNum());
	  ver.setTargetReleaseDate(getTargetReleaseDate());
	  ver.setEstimatedReleaseDate(getEstimatedReleaseDate());
	  ver.setActualReleaseDate(getActualReleaseDate());
		return ver;
	}

  public IProductVersion copyStructureOf(IProductVersion source)
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    ICategory sourceCat = source.getDefaultCategory();
    ICategory defaultCat = getDefaultCategory();

    ICategoryColl subcats = sourceCat.getCategories();
    while (subcats.hasMoreCategories())
    {
      ICategory subcat = subcats.nextCategory().createCopyFor(this);
      subcat.setParentObject(defaultCat);
      subcat.setVersionObject(this);
    }

    IFeatureColl feats = sourceCat.getFeatures();
    while (feats.hasMoreFeatures())
    {
      IFeature feat = feats.nextFeature();
      defaultCat.associateFeature(feat);
    }

    // Copy the dependencies over
    IRConnection conn = context.getRepository().getDataConnection(context);
    IQueryProcessor qp = conn.createProcessor();
    IDataSet rs = qp.retrieve(
      "      SELECT newlink.OBJECTID AS SOURCE, newlink2.OBJECTID AS DEST "+
      "      FROM CATFEATURELINK link LEFT OUTER JOIN SEMANTICLINK sem ON link.OBJECTID = sem.SRCOBJECTID "+
      "          LEFT OUTER JOIN CATEGORY cat ON link.CATEGORYID = cat.OBJECTID "+
      "          LEFT OUTER JOIN CATFEATURELINK link2 ON link2.OBJECTID = sem.DESTOBJECTID "+
      "          LEFT OUTER JOIN CATEGORY cat2 ON link2.CATEGORYID = cat2.OBJECTID "+
      "          LEFT OUTER JOIN CATFEATURELINK newlink ON newlink.FEATUREID = link.FEATUREID "+
      "          LEFT OUTER JOIN CATEGORY newcat ON newlink.CATEGORYID = newcat.OBJECTID "+
      "          LEFT OUTER JOIN CATFEATURELINK newlink2 ON newlink2.FEATUREID = link2.FEATUREID "+
      "          LEFT OUTER JOIN CATEGORY newcat2 ON newlink2.CATEGORYID = newcat2.OBJECTID "+
      "      WHERE sem.LINKTYPE = "+LinkKind.FUNCTIONAL_DEP+" AND cat.VERSIONID = "+source.getIID()+" AND "+
      "          cat2.VERSIONID = "+source.getIID()+" AND newcat.VERSIONID = "+getIID()+" AND "+
      "          newcat2.VERSIONID = "+getIID()+" "
      );

    while (rs.next())
    {
      long sourceLinkID = rs.getLong("SOURCE");
      long destLinkID = rs.getLong("DEST");
      IIID sourceIID = context.getRepository().makeReposID(sourceLinkID);
      IIID destIID = context.getRepository().makeReposID(destLinkID);
      IFeatureCategoryLink sourceLink = (IFeatureCategoryLink)context.getCRM().getCompObject(context,"FeatureCategoryLink",sourceIID);
      IFeatureCategoryLink destLink = (IFeatureCategoryLink)context.getCRM().getCompObject(context,"FeatureCategoryLink",destIID);
      destLink.addFunctionalDependency(sourceLink);
    }    
    return this;
  }
  
  public String getFullTreePathString()
    throws OculusException
  {
    return getProductObject().getFullTreePathString()+"/"+getName();
  }//

  public boolean isParentOf(IProductVersion child)
    throws OculusException
  {
    boolean answer = false;
    
    if (this.getIID().equals(child.getIID()))
      answer = true;
    if (!answer)
    {
      IProductVersionColl vers = getVersions(false);
      while (!answer && vers.hasMoreProductVersions())
        if (vers.nextProductVersion().isParentOf(child))
          answer = true;
    }
    
    return answer;
  }

  public boolean isChildOf(IProductVersion parent)
    throws OculusException
  {
    boolean answer = false;
    
    if (this.getIID().equals(parent.getIID()))
      answer = true;
    if (!answer)
    {
      IProductVersionColl vers = getParentVersions(false);
      while (!answer && vers.hasMoreProductVersions())
        if (vers.nextProductVersion().isChildOf(parent))
          answer = true;
    }
    
    return answer;
  }

  public IProductVersion copyTeamOf(IProductVersion source)
    throws OculusException
  {
    IObjectContext context = getObjectContext();
		IRoleAssignmentColl rac = (IRoleAssignmentColl)context.getCRM().getCompObject(context,"RoleAssignmentColl",source.getIID());
		while (rac.hasNext())
		{
		  IRoleAssignment ra = rac.nextRoleAssignment(); 
			IRoleAssignment newRA = (IRoleAssignment)context.getCRM().getCompObject(context,"RoleAssignment",(IDataSet)null, true);
      newRA.setParObjectIID(this.getIID());
      newRA.setRoleIID(ra.getRoleIID());
      newRA.setUserIID(ra.getUserIID());
      newRA.setOrderNum(ra.getOrderNum());
			newRA.isController(ra.isController());
		}
    return this;
  }
  

  public IProductVersion createCopy()
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    IIID classIID = IDCONST.PRODUCTVERSION.getIIDValue();
    IProductVersion newVer = (IProductVersion)context.getCRM().getCompObject(context,"ProductVersion",(IDataSet)null,true);
    IRClass versionClass = (IRClass)context.getCRM().getCompObject(context,"Class",classIID);
    newVer.setDefnObject(versionClass);
    
    newVer.setName(getName());
    newVer.setDescription(getDescription());
    newVer.setAccessIID(getAccessIID());
    newVer.setOrderNum(getOrderNum());
    newVer.setTargetReleaseDate(getTargetReleaseDate());
    newVer.setEstimatedReleaseDate(getEstimatedReleaseDate());
    newVer.setActualReleaseDate(getActualReleaseDate());
    newVer.setProductObject(getProductObject());
    newVer.createDefaultCategory();
    
    return newVer;
  }
  
  
  public IVersionBaseline createNewBaseline()
	throws OculusException
  {
	IVersionBaseline newBaseline = null;
	IIID classIID = IDCONST.VERSIONBASELINE.getIIDValue();

	newBaseline = (IVersionBaseline)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductBaseline",(IDataSet)null,true);
	newBaseline.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
  newBaseline.define(this);
	return newBaseline;
  }  
  public ICategory createNewCategory()
  throws OculusException
  {
    return getDefaultCategory().createNewCategory();
  }  
  public ICategory createDefaultCategory()
  throws OculusException
  {
  ICategory newCat;
  IIID classIID = IDCONST.CATEGORY.getIIDValue();

  newCat = (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",(IDataSet)null,true);
  newCat.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
  newCat.setParentObject(newCat);
  newCat.setVersionObject(this);
  newCat.setName("<Default Category>");
  newCat.setDescription("<This is a default category.>");
  
  _defCatIID = newCat.getIID();
  
  return newCat;
  }

  public IProductVersion associateVersion(IProductVersion newVer)
	throws OculusException
  {
  IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
  conn.createProcessor().update("INSERT INTO VERSSHARELINK (PARVERSIONID, CHILDVERSIONID) VALUES ("+getIID().getLongValue()+","+newVer.getIID().getLongValue()+") ");
	return this;
  }  

  public IProductVersion disAssociateVersion(IProductVersion oldVer)
    throws OculusException
  {
  IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
  conn.createProcessor().update("DELETE FROM VERSSHARELINK WHERE PARVERSIONID="+getIID().getLongValue()+" AND CHILDVERSIONID="+oldVer.getIID().getLongValue()+" ");
  return this;
  }
  
  public boolean isAssociatedWithAVersion()
    throws OculusException
  {
    IRConnection conn = getObjectContext().getRepository().getDataConnection(getObjectContext());
    IQueryProcessor qp = conn.createProcessor();
    IDataSet results = qp.retrieve("SELECT PARVERSIONID FROM VERSSHARELINK WHERE CHILDVERSIONID="+getIID()+" ");
    return results.next();
  }
    

  public IAlertConfig createAlertConfig()
    throws OculusException
  {
//    IIID classIID = IDCONST.ALERTCONFIG.getIIDValue();
    IAlertConfig alert = (IAlertConfig)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlertConfig",(IDataSet)null,true);
  //  alert.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",classIID));
    alert.setParObjectIID(getIID()); 
    return alert;
  }//

  public void pinAllFeatureLinks()
    throws OculusException
  {
    IProductVersionColl vers = getVersions(false);
    while (vers.hasMoreProductVersions())
      vers.nextProductVersion().pinAllFeatureLinks();

    ICategoryColl cats = getCategories();
    while (cats.hasMoreCategories())
      cats.nextCategory().pinAllFeatureLinks();
  }
        
  public IProductVersion moveToAccolades(String strComment, boolean recurse, IIID transIID)
    throws OculusException
  {
    IRState state = getStateObject();
    if (state.getIID().equals(IDCONST.VERSIONCOMPASS.getIIDValue()))
    {
      interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.VERSIONUNDERCONST.getIIDValue()),strComment);
    }//end if
    if (recurse)
      getDefaultCategory().moveToAccolades(strComment,true,transIID);
    getProductObject(true).moveToAccolades(strComment,false,transIID);  
    return this;
  }

  public IProductVersion moveToCompass(String strComment, boolean recurse)
    throws OculusException
  {
    return (IProductVersion)interruptProcess((IRState)getObjectContext().getCRM().getCompObject(getObjectContext(),"State",IDCONST.VERSIONCOMPASS.getIIDValue()),strComment);
  }

//----------------- IPersistable Methods ------------------------------------

  /**
  * Marks the bo as deleted.
  */
  public IPersistable delete()
	throws OculusException
  {
    ICategory cat =	getDefaultCategory();
		ICategory defcat = (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",cat.getIID(),true);
		defcat.delete();
		//delete self	
	  super.delete();	
	  return this;
  }  


  public IBusinessObject softDelete()
    throws OculusException
  { 
	  super.softDelete();
		
		ICategory cat = getDefaultCategory();
		ICategory defcat = (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",cat.getIID(),true);
		defcat.softDelete();
		  
    return this;
  }

  public IBusinessObject recover()
    throws OculusException
  {
    super.recover();
    
    ICategory cat = getDefaultCategory();
		ICategory defcat = (ICategory)getObjectContext().getCRM().getCompObject(getObjectContext(),"Category",cat.getIID(),true);
		defcat.recover();
  
    return this;
  }



//----------------- IRPropertyMap Methods---------------------------------
  public Object get(Object key)
	throws OculusException
  {
	if (key instanceof String)
	{
	  if (key.equals(LABEL_ORDERNUM))
		return _orderNum;
	  else if (key.equals(LABEL_TARGETRELEASEDATE))
		return _targetDate;
	  else if (key.equals(LABEL_ESTIMATEDRELEASEDATE))
		return _estimatedDate;
	  else if (key.equals(LABEL_ACTUALRELEASEDATE))
		return _actualDate;
	  else
		return super.get(key);
	}
	else
	  return null;
  }  
  public Timestamp getActualReleaseDate()
	throws OculusException
  {
	if (_actualDate.getValue() != null)
	  return (Timestamp)_actualDate.getValue();
	else
	  return null;
  }  
//----------------- IProduct Methods ------------------------------------
	/** I don't know what this does */
  public IVersionBaselineColl getBaselines()
    throws OculusException
  {
    IDataSet args = new DataSet();
    return getBaselines(args);
  }
  
  public IVersionBaselineColl getBaselines(IDataSet args)
    throws OculusException
  {
    args.setIID(getIID());
    return (IVersionBaselineColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"BaselineColl",args, false);
  }

  public ISet getBranches()
 	{
 		return null;
	}
  public ICategoryColl getCategories()
  throws OculusException
  {
  return getCategories(false);
  }  
  public ICategoryColl getCategories(boolean editable)
  throws OculusException
  {
  return (ICategoryColl)getChildCollection(editable);
  }  
  protected String getCreateQuery()
	throws OculusException
  {
	return "INSERT INTO "+TABLE+" "+
		   " ("+COL_OBJECTID+", "
			   +COL_CLASSID+", "
			   +COL_STATEID+", "
         +COL_DELETESTATE+", "
			   +COL_GUID+", "
			   +COL_NAME+", "
			   +COL_PRODUCTID+", "
			   +COL_ORDERNUM+", "
			   +COL_CREATIONDATE+", "
			   +COL_CREATORID+", "
         +COL_ACCESSID+", "
         +(getTargetReleaseDate() == null? "" : COL_TARGETRELEASEDATE+", ")
         +(getEstimatedReleaseDate() == null? "" : COL_ESTIMATEDRELEASEDATE+", ")
         +(getActualReleaseDate() == null? "" : COL_ACTUALRELEASEDATE+", ")
			   +COL_MESSAGEATTACHED+", "
			   +COL_FILEATTACHED+", "
			   +COL_LINKATTACHED+
		   ") "+
		   " VALUES "+
		   " ("+getIID().getLongValue()+","
			  +getDefnObject().getIID().getLongValue()+","
			  +getStateObject().getIID().getLongValue()+","
        +getDeleteState().getIntValue()+", "
			  +"'"+getGUID().toString()+"',"
			  +"'"+SQLUtil.primer(getName())+"',"
			  +getProductObject().getIID().getLongValue()+","
			  +getOrderNum()+","
			  +"'"+getCreationDate().toString()+"',"
			  +getCreatorIID().getLongValue()+","
        +getAccessIID().getLongValue()+","
        +(getTargetReleaseDate() == null? "" : "'"+getTargetReleaseDate()+"', ")
        +(getEstimatedReleaseDate() == null? "" : "'"+getEstimatedReleaseDate()+"', ")
        +(getActualReleaseDate() == null? "" : "'"+getActualReleaseDate()+"', ")
			  +(hasMessageAttached()?"1":"0")+","
			  +(hasFileAttached()?"1":"0")+","
			  +(hasLinkAttached()?"1":"0")+
		   ") ";
  }  
  protected String getDeleteQuery()
	throws ORIOException
  {
	return " DELETE FROM "+TABLE+" "+
		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  public Timestamp getEstimatedReleaseDate()
	throws OculusException
  {
	if (_estimatedDate.getValue() != null)
	  return (Timestamp)_estimatedDate.getValue();
	else
	  return null;
  }  
  protected String getLoadQuery()
	throws ORIOException
  {
	return "SELECT * "+
		   "FROM "+TABLE+" "+
		   "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  public int getOrderNum()
	throws OculusException
  {      
	if (_orderNum.getValue() != null)
	  return ((Integer)_orderNum.getValue()).intValue();
	else
	  return 0;
  }  
  
  public ICategory getDefaultCategory()
    throws OculusException
  {
    IObjectContext context = getObjectContext();
    if (_defCatIID == null)
    {
      IRConnection conn = context.getRepository().getDataConnection(context);
      IQueryProcessor qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT OBJECTID FROM CATEGORY WHERE PARENTCATID=OBJECTID AND VERSIONID="+getIID());
      if (rs.next())
      {
        long catid = rs.getLong("OBJECTID");
        _defCatIID = context.getRepository().makeReposID(catid);
      }
    }
    return (ICategory)context.getCRM().getCompObject(context,"Category",_defCatIID);
  }
  
  public IBusinessObject addToWorkforce(IIID useriid,int ordernum)
    throws OculusException
  {
    return addToWorkforce(useriid,ordernum,true);
  }//
  
  private IBusinessObject addToWorkforce(IIID useriid,int ordernum,boolean sendmail)
    throws OculusException
  {
    if(sendmail)
    {
      String strMsg =
      "You have been added to the Version Workforce for Version ("+getName()+").";
      IUser touser = (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",useriid);
      IUser fromuser = (IUser)getObjectContext().getCRM().getCompObject(getObjectContext(),"User",getObjectContext().getConnection().getUserIID());
      MailService.getInstance().sendMail(EmailKind.VERSIONWORKFORCE,touser,fromuser,strMsg);
    }//end if
    
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getIID());
    ra.setRoleIID(IDCONST.VERSIONTEAMROLE.getIIDValue());
    ra.setUserIID(useriid);
    ra.setOrderNum(ordernum);
    
    if(getDefaultCategory() != null)
    {
      ICategoryColl cats = getCategories();
      while (cats.hasMoreCategories())
        cats.nextCategory().addToWorkforce(useriid,ordernum);
    }//end if
    // create the grant set
    Set gs = new HashSet();
    gs.add(PermEnum.ITEM_VIEW);
    // request the actual grant
    getProductObject().grantPermissions(useriid, gs);
    return this;
  }//
  
  public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum, boolean recurse)
    throws OculusException
  {
    //if the role is singular, add someone is already assigned, remove then add
    IProcessRole role = (IProcessRole)getObjectContext().getCRM().getCompObject(getObjectContext(),"Role",roleiid);
    if(role != null && !role.isMultiUser())
    {
      //first add them to the team if they are not already there
      addToWorkforce(useriid,ordernum,false);
      
      IDataSet args = new DataSet();
      args.setIID(getIID());
      args.put("roleiid",roleiid);
      IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentRoleColl",args);
      while (rac.hasNext())
        removeFromRole(rac.nextRoleAssignment().getUserObject().getIID(),roleiid);
      if(getDefaultCategory() != null)
			  getDefaultCategory().addToRole(useriid,roleiid,ordernum,recurse);
		}//end if
    
    //now add specific role
    IRoleAssignment ra = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment",(IDataSet)null, true);
    ra.setParObjectIID(getIID());
    ra.setRoleIID(roleiid);
    ra.setUserIID(useriid);
    ra.setOrderNum(ordernum);
    
    
    return this;
  }//
  
	public IBusinessObject addToRole(IIID useriid, IIID roleiid, int ordernum)
    throws OculusException
  {
	  return addToRole(useriid,roleiid,ordernum,false);
  }
	
  public IBusinessObject removeFromWorkforce(IIID useriid)
    throws OculusException
  { 
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentColl",getIID());
    while(rac.hasNext())
    {
      IRoleAssignment ra = rac.nextRoleAssignment();
      if (ra.getUserIID().equals(useriid))
	     {
			   IRoleAssignment rad = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment", ra.getIID(),true);
        rad.delete();
	     }
    }//end while
    if(getDefaultCategory() != null)
      getDefaultCategory().removeFromWorkforce(useriid);
    return this;
  }
  
  public IBusinessObject removeFromRole(IIID useriid, IIID roleiid)
    throws OculusException
  { 
    IRoleAssignmentColl rac = (IRoleAssignmentColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignmentColl",getIID());
    while(rac.hasNext())
    {
      IRoleAssignment ra = rac.nextRoleAssignment();
      if (ra.getRoleIID().equals(roleiid) && ra.getUserIID().equals(useriid))
	     {
	       IRoleAssignment rad = (IRoleAssignment)getObjectContext().getCRM().getCompObject(getObjectContext(),"RoleAssignment", ra.getIID(),true);
        rad.delete();
	     }//end if
    }//end while
    if(getDefaultCategory() != null)
      getDefaultCategory().removeFromRole(useriid,roleiid);
    return this;
  }
  //------------------------ ACCESSORS -------------------------------
  
  public IAlertConfigColl getAlerts()
    throws OculusException
  {
    return getAlerts(false); 
  }
  
  public IAlertConfigColl getAlerts(boolean edit)
    throws OculusException
  {
    return (IAlertConfigColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlertConfigColl",getIID(),edit); 
  }
    
  public IProduct getProductObject(boolean edit)
	  throws OculusException
  {
	  return (IProduct)getParentObject(edit);
  }  
  
  public IProduct getProductObject()
    throws OculusException
  { return getProductObject(false); }
  
  protected String getPSPUpdateQuery()
	//throws OculusException	// STUBBED MAK THROWS?
  {
	return " UPDATE "+TABLE+
		   " SET "+COL_DESCRIPTION+"=? "+
		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue()+"";
  }    
protected Object getPSPValue() throws ORIOException
{
	String ret = null;
	try
	{
		ret = getDescription();
	}
	catch (OculusException ox)
	{
		throw new ORIOException(ox);
	}
	return ret;
}
  public Timestamp getTargetReleaseDate()
	throws OculusException
  {
	if (_targetDate.getValue() != null)
	  return (Timestamp)_targetDate.getValue();
	else
	  return null;
  }  
  protected String getUpdateQuery()
	throws OculusException
  {
	return " UPDATE "+TABLE+" "+
		   " SET "+
		   "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
		   " , "+COL_STATEID+"= "+getStateObject().getIID().getLongValue()+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		   " , "+COL_PRODUCTID+"= "+getProductObject().getIID().getLongValue()+" "+
		   " , "+COL_ORDERNUM+"= "+getOrderNum()+" "+
		   " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
		   (getTargetReleaseDate() == null? "" : " , "+COL_TARGETRELEASEDATE+"='"+getTargetReleaseDate()+"' ")+
		   (getEstimatedReleaseDate() == null? "" : " , "+COL_ESTIMATEDRELEASEDATE+"='"+getEstimatedReleaseDate()+"' ")+
		   (getActualReleaseDate() == null? "" : " , "+COL_ACTUALRELEASEDATE+"='"+getActualReleaseDate()+"' ")+
		   " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		   " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		   " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		   " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
  }  
  public IProductVersionColl getVersions(boolean editable)
	throws OculusException
  {
	return (IProductVersionColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"SubProductVersionColl",getIID(), editable);
  } 
  
  public IProductVersionColl getParentVersions(boolean editable)
    throws OculusException
  {
	return (IProductVersionColl)getObjectContext().getCRM().getCompObject(getObjectContext(),"ParentProductVersionColl",getIID(), editable);
  }

  
  public IFeatureList getFeatures()
  throws OculusException
  {
  return getFeatures(false);
  }
  public IFeatureList getFeatures(IDataSet args)
    throws OculusException
  {
    return getFeatures(args, false);
  }
  public IFeatureList getFeatures(boolean editable)
  throws OculusException
  {
    IDataSet args = new DataSet();
    return getFeatures(args,editable);
  }  
  public IFeatureList getFeatures(IDataSet args, boolean editable)
    throws OculusException
  {
    args.setIID(getIID());
    return (IFeatureList)getObjectContext().getCRM().getCompObject(getObjectContext(),"VersionFeatureList",args, editable);
  }

  
  
  
  
  
  
  protected void load(IDataSet results)
	throws OculusException
  {
	if (results.getIID() == null)
	  results.setIID(results.getLong(COL_OBJECTID));
	super.load(results);
	
	if (COL_PRODUCTID != null)
    _productIID = new SequentialIID(results.getLong(COL_PRODUCTID));
  if (COL_ORDERNUM != null)
    setOrderNum(results.getInt(COL_ORDERNUM));
	setName(results.getString(COL_NAME));
	setDescription(results.getString(COL_DESCRIPTION));
	setTargetReleaseDate(results.getTimestamp(COL_TARGETRELEASEDATE));
	setEstimatedReleaseDate(results.getTimestamp(COL_ESTIMATEDRELEASEDATE));
	setActualReleaseDate(results.getTimestamp(COL_ACTUALRELEASEDATE));
  }  

  public IPersistable save()
    throws OculusException
  {
    if (getPersState().equals(PersState.NEW) || getOrderNum()==0)
    {
      IRObject parent = getParentObject();
      if (parent != null && parent instanceof IProduct)
      {
        IProductVersionColl vers = ((IProduct)parent).getVersions();
        setOrderNum(vers.size()+1);
      }
    }
    return super.save();
  }


  //-------------------------- Protected Methods -----------------------------
  protected PSPKind needsPreparedStatement()
  {
	return PSPKind.STRING;
  }  
  public void put(Object key, Object value)
	throws OculusException
  {
	if (key instanceof String && value instanceof IRProperty)
	{
	  IRProperty property = (IRProperty)value;
	  if (key.equals(LABEL_ORDERNUM))
		setOrderNum(((Integer)property.getValue()).intValue());
	  else if (key.equals(LABEL_TARGETRELEASEDATE))
		setTargetReleaseDate((Timestamp)property.getValue());
	  else if (key.equals(LABEL_ESTIMATEDRELEASEDATE))
		setEstimatedReleaseDate((Timestamp)property.getValue());
	  else if (key.equals(LABEL_ACTUALRELEASEDATE))
		setActualReleaseDate((Timestamp)property.getValue());
	  else
		super.put(key,value);
	}
  }  
  public IProductVersion setActualReleaseDate(Timestamp actual)
	throws ORIOException
  {
	_actualDate.setValue(actual);
	return this;
  }  
  public IProductVersion setEstimatedReleaseDate(Timestamp estimated)
	throws ORIOException
  {
	_estimatedDate.setValue(estimated);
	return this;
  }  
  public IProductVersion setOrderNum(int order)
	throws ORIOException
  {
	_orderNum.setValue(new Integer(order));
	return this;
  }  
  //------------------------ MUTATORS -------------------------------
  public IProductVersion setProductObject(IProduct product)
	throws ORIOException
  {
  if (getPersState().equals(PersState.UNMODIFIED))
    setPersState(PersState.MODIFIED);
  _productIID = product.getIID();
 return this;
  }  
  public IProductVersion setTargetReleaseDate(Timestamp target)
	throws ORIOException
  {
	_targetDate.setValue(target);
	return this;
  }  
  
  
  public IRObject getParentObject(boolean editable)
    throws OculusException
  {
    if (_productIID == null)
      return null;
    return (IRObject)getObjectContext().getCRM().getCompObject(getObjectContext(),"Product",_productIID,editable);
  }
  
  public IRCollection getChildCollection(boolean editable)
    throws OculusException
  {
    return (IRCollection)getObjectContext().getCRM().getCompObject(getObjectContext(),"CategoryList",getDefaultCategory().getIID(), editable);
  }
  
  
  public IRDisposition getDispositionObject()
    throws OculusException
  {
      IQueryProcessor qp =null;
    try
    {
    IIID dispIID = null;
    IObjectContext context = getObjectContext();
    IRConnection conn = context.getRepository().getDataConnection(context);
    qp = conn.createProcessor();
      IDataSet rs = qp.retrieve("SELECT OBJECTID FROM DISPOSITION WHERE CONTEXTOBJID="+getIID());
      if (rs.next())
        dispIID = context.getRepository().makeReposID(rs.getLong("OBJECTID"));
    if (dispIID == null || (dispIID != null && dispIID.getLongValue() == 0))
      return (IRDisposition) null;
    else      
      return (IRDisposition)context.getCRM().getCompObject(context,"Disposition",dispIID);
    }
    finally { qp.close();}
    
  }
  
  public IGrantSet getPermissions()
    throws OculusException
  {
    IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
    IGrantSet gsProd = acm.checkPerms(getProductObject(),new IPermission[]
      {PermEnum.VERSION_VIEW,PermEnum.VERSION_ADD,PermEnum.VERSION_REORDER});
    IGrantSet gs = acm.checkPerms(this, new IPermission[]
      {PermEnum.ITEM_VIEW,PermEnum.ITEM_EDIT,PermEnum.SPEC_EDIT,PermEnum.OWNER,PermEnum.EXPORT_TO_PROJ,PermEnum.SPEC_VIEW,PermEnum.BSLN_ADD});
    gs.addAll(gsProd);

    return gs;
  }


  public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
    throws OculusException
  {
    boolean visible = false;
    
    IIID stateIID = getStateObject().getIID();
    if ((module.equals(ModuleKind.COMPASS) && !stateIID.equals(IDCONST.VERSIONCOMPLETE.getIIDValue())) 
        || (module.equals(ModuleKind.ACCOLADES) && !stateIID.equals(IDCONST.VERSIONCOMPASS.getIIDValue())))
    {
      if (grant == null)
      {
        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());
        grant = acm.checkPerms(getProductObject(),new IPermission[] {PermEnum.VERSION_VIEW});
        grant.addAll(acm.checkPerms(this, new IPermission[] {PermEnum.ITEM_VIEW}));
      }
      
      if (grant.contains(PermEnum.ITEM_VIEW) || grant.contains(PermEnum.VERSION_VIEW))
        visible = true;
    }
    return visible;
  }

  // ------------------ IProjectParent methods --------------------------
  public List getAllChildOIDs( )
    throws OculusException
  {
    List retList = new Vector();
    
    IKeywordSearch srch = new KeywordSearch();
    ((KeywordSearch) srch).setObjectContext(getObjectContext());
    srch.setKeywords("");
    srch.setSearchForAllKeywords(false);
    srch.setSearchAllFields(false);
    srch.setQueryClasses(Arrays.asList(new IXClass[] {XMen.CLS_CATEGORY, XMen.CLS_CATFEATLINK}));
    srch.setScopeObject(XMen.CLS_VERSION, getIID().getLongValue());
    IDataSet results = srch.getSearcher().doQueries();
    while (results != null && results.next()) {
      long l = results.getLong("OBJECTID");
      retList.add(new Long(l));
    }
    return retList;
  }
}

  
  