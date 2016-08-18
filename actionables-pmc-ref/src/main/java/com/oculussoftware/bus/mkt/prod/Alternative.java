package com.oculussoftware.bus.mkt.prod;

import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;

/**
 * Insert the type's description here.
 * Creation date: (4/18/00 10:16:28 AM)
 * @author: 
 */
public class Alternative extends BusinessObject implements IBusinessObject, IPersistable, IAlternative {

	public static final String COL_ALTERNATIVESETID = "ALTERNATIVESETID";	
	protected IIID         _altSetIID;


/**
 * Alternative constructor comment.
 * @exception com.oculussoftware.api.sysi.OculusException The exception description.
 */
public Alternative() throws OculusException {
  super();
  TABLE                = "ALTERNATIVE";
}


public Object dolly() throws OculusException
{
  Alternative alt = null;
  alt = new Alternative();
  alt.setIID(getIID());
  alt.setObjectContext(getObjectContext());
  alt.setPersState(getPersState());
  alt._classIID = _classIID;
  alt._stateIID = _stateIID;
  //Saleem added to this line
  if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
  alt.putAll(getProperties());
  alt.setName(getName());
  alt.setDescription(getDescription());
  alt._creatorIID = _creatorIID;
  alt._accessIID = _accessIID;
  alt.setCreationDate(getCreationDate());
  alt.setMessageAttached(hasMessageAttached());
  alt.setLinkAttached(hasLinkAttached());
  alt.setFileAttached(hasFileAttached());
  //specific to alt
  alt._altSetIID = _altSetIID;
  alt.setPersState(getPersState());
  return alt;
}


public Object get(Object key)
  throws OculusException
  {
  if (key instanceof String)
  {
    return super.get(key);
  }
  else
    return null;
  }


/**
 * getAlternativeSet method comment.
 */
public IAlternativeSet getAlternativeSet() throws OculusException
{
  return getAlternativeSet(false); 

}


/**
 * getAlternativeSet method comment.
 */
public IAlternativeSet getAlternativeSet(boolean checkout) throws OculusException
{
  return (IAlternativeSet)getObjectContext().getCRM().getCompObject(getObjectContext(),"AlternativeSet",_altSetIID,checkout); 

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
             +COL_DESCRIPTION+", "
         +COL_CREATIONDATE+", "
         +COL_CREATORID+", "
         +COL_ACCESSID+", "
         +COL_MESSAGEATTACHED+", "
         +COL_FILEATTACHED+", "
         +COL_LINKATTACHED+", " 
         +COL_ALTERNATIVESETID+" " +         
       ") "+

       
       " VALUES "+
       " ("+getIID().getLongValue()+","
        +getDefnObject().getIID().getLongValue()+","
        +_stateIID+","
        +getDeleteState().getIntValue()+","
        +"'"+SQLUtil.primer(getGUID().toString())+"',"
        +"'"+SQLUtil.primer(getName())+"',"
        +"'"+SQLUtil.primer(getDescription())+"',"
        +"'"+getCreationDate().toString()+"',"
        +getCreatorIID().getLongValue()+","
        +getAccessIID().getLongValue()+","
        +(hasMessageAttached()?"1":"0")+","
        +(hasFileAttached()?"1":"0")+","
        +(hasLinkAttached()?"1":"0")+","
        +_altSetIID.getLongValue()+""+
        ") ";
  }


/**
 * getDeleteQuery method comment.
 */
protected String getDeleteQuery() throws OculusException {
  return " DELETE FROM "+TABLE+" "+
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
}


/**
 * getLoadQuery method comment.
 */
protected String getLoadQuery() throws OculusException {
  return "SELECT * "+
       "FROM "+TABLE+" "+
       "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
}


/**
 * getUpdateQuery method comment.
 */
protected String getUpdateQuery() throws OculusException {
return " UPDATE "+TABLE+" "+
       " SET "+
       "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
       " , "+COL_DESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
       " , "+COL_STATEID+"= "+_stateIID+" "+
       " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
       " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
       " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
       " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
       " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
       //specific to alt
       " , "+COL_ALTERNATIVESETID+"= "+_altSetIID.getLongValue()+" "+
        
       " WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
}


  protected void load(IDataSet results)
  throws OculusException
  {
  if (results.getIID() == null)
    results.setIID(results.getLong(COL_OBJECTID)); 
  setPersState(PersState.PARTIAL);
  
  _guid = new GUID(results.getString(COL_GUID).trim());       // get GUID
  _iid = new SequentialIID(results.getLong(COL_OBJECTID));    // get IID
  _classIID = new SequentialIID(results.getLong(COL_CLASSID)); // get class
  _stateIID = new SequentialIID(results.getLong(COL_STATEID)); // get state
  setCreatorIID(new SequentialIID(results.getLong(COL_CREATORID)));
  setAccessIID(new SequentialIID(results.getLong(COL_ACCESSID)));
  setName(results.getString(COL_NAME));                // get name
  setDescription(results.getString(COL_DESCRIPTION));  // get desc
  setCreationDate(results.getTimestamp(COL_CREATIONDATE));
  setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
  setFileAttached(results.getBoolean(COL_FILEATTACHED));
  setLinkAttached(results.getBoolean(COL_LINKATTACHED));

  
  //specific to Alternative
  _altSetIID = (new SequentialIID(results.getLong(COL_ALTERNATIVESETID)));
  
  }


public void put(Object key, Object value)
  throws OculusException
  {
  if (key instanceof String && value instanceof IRProperty)
  {
    IRProperty property = (IRProperty)value;
    super.put(key,value);
  }
  }


/**
 * setAlternativeSet method comment.
 */
public IAlternative setAlternativeSet(IAlternativeSet altSet) throws OculusException
{
  _altSetIID = altSet.getIID();
  return this;
}
}
