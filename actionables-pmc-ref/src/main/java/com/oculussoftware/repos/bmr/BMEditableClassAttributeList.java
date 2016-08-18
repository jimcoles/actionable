package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.util.*;

import java.util.*;
import java.sql.*;

public class BMEditableClassAttributeList extends BMModelElementList 
{
  private IIID _user = null;
  protected ArrayList _perms;
  protected Iterator _permIds;
   
 public BMEditableClassAttributeList() throws OculusException
  {
    _guid = new GUID();
    _coll = new ArrayList();
    _perms = new ArrayList();
  }
  
  //-------------------------- IRModelElement -------------------------
  public String getClassName() { return "Attribute";}
  public String getLoadQuery()
    throws OculusException
  {
    return	" SELECT attrib.OBJECTID,caga.ORDERNUM,MIN(agg.OPERATIONTYPE) AS OPERATIONTYPE"+
	    		  " FROM CLASSATTRGROUPASC caga "+
	    			  "LEFT OUTER JOIN ATTRGROUPGRANT agg ON agg.ATTRGROUPID = caga.ATTRGROUPID "+
	    			  "LEFT OUTER JOIN ATTRIBUTE attrib ON caga.ATTRIBUTEID = attrib.OBJECTID "+
	    		  " WHERE caga.CLASSID="+getIID()+" AND attrib.ATTRKIND<>"+AttributeKind.SYSTEM_GENERATED.getIntValue()+" AND "+
            " (1="+_user+" (agg.OPERATIONTYPE="+AttrGroupOper.EDIT+" AND (agg.ACCESSORID = "+_user+" OR agg.ACCESSORID IN (SELECT ug.GROUPID FROM USERGROUPASC ug WHERE ug.USERID="+_user+")))) "+
            " GROUP BY attrib.OBJECTID, caga.ORDERNUM "+
					  " ORDER BY 2 ";
  } 
    
  public Object dolly() throws OculusException 
  { 
    BMEditableClassAttributeList gColl = new BMEditableClassAttributeList();
    gColl.setIID(_iid);
    gColl._coll.addAll(this._coll);
    gColl._perms.addAll(this._perms);
    gColl.reset();
    return gColl;      
  }    
  


  public IPersistable load( ) throws OculusException
  { 
    IQueryProcessor qp = null;
    try
    {
      
      IRConnection repConn = getObjectContext().getCRM().getDatabaseConnection(getObjectContext());
      qp = repConn.createProcessor();
      IDataSet results = qp.retrieve(getLoadQuery());
      while (results.next())
      {
        IIID id = new SequentialIID(results.getLong("OBJECTID"));
        _coll.add(id);
        
        long perm = results.getLong("OPERATIONTYPE");
        if (perm == 0) perm = AttrGroupOper.EDIT.getIntValue();
        _perms.add(""+perm);

        results.setIID(id);
        getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),results);
      }//end while
//      CRM.getInstance().returnDatabaseConnection(repConn);
      reset();
    }//end try
    catch (ORIOException sqlExp)
    { throw new OculusException("Error retrieving data from the database."+sqlExp.toString()); }
    finally { if(qp!=null) qp.close(); }
    return this;  
  }//end load


  public IRModelElement getModelElement(int index) throws ORIOException
  {
    Object o = null;
    IRAttribute m=null;
    
   try
    {
     o = _coll.get(index);
    if (o instanceof IIID) 
    {
      IIID IID = (IIID)o; 
      o = null;
      m = (IRAttribute)getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),IID,isLocked());
      
      String perm = (String)_perms.get(index);
      int pm = Integer.parseInt(perm);
      m.setUserPermission(AttrGroupOper.getInstance(pm));
    }
    }
    catch(Exception ex) { throw new ORIOException(ex);} 
    return m;
  }


  public IRCollection reset()
  { 
    _ids = _coll.iterator();
    _permIds = _perms.iterator();
    return this; 
  }//

  public Object next() throws OculusException
  { 
    IRAttribute nextObject = null;
    while (nextObject == null && hasNext()) 
    {
      IIID IID = (IIID)_ids.next();  
      nextObject = (IRAttribute)getObjectContext().getCRM().getCompObject(getObjectContext(),getClassName(),IID,isLocked());

      String perm = (String)_permIds.next();
      int pm = Integer.parseInt(perm);
      nextObject.setUserPermission(AttrGroupOper.getInstance(pm));

    }//end while
    return nextObject; 
  }//
  



 public IPoolable construct(IObjectContext context, IDataSet args)
    throws OculusException
  {
    IIID iid = null;
    if(args != null)
      iid = args.getIID();

    if (iid == null)
      throw new OculusException("Object ID expected.");
    setIID(iid);

    if (context == null)
      throw new OculusException("Object Context expected.");
    setObjectContext(context);

    _user = context.getRepository().makeReposID(args.getInt("UserID"));
    
    return this;
  }

}