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
public class AlternativeSet extends BusinessObject implements IBusinessObject, IPersistable, IAlternativeSet {	public static final String COL_FEATUREID = "FEATURELINKID";
	public static final String COL_SELALTERNATIVEID = "SELALTERNATIVEID";	
	public static final String COL_SELECTIONCOMMENT = "SELECTIONCOMMENT";
		
	protected IIID         _featureIID;
	protected String 			_selectionComment;
	protected IIID 				_selectedAltIID;


/**
 * Alternative constructor comment.
 * @exception com.oculussoftware.api.sysi.OculusException The exception description.
 */
public AlternativeSet() throws OculusException {
	super();
	TABLE                = "ALTERNATIVESET";
}


public IAlternativeColl getAlternatives() throws OculusException
{
	
	return (IAlternativeColl)getObjectContext().getCRM().getCompObject(getObjectContext(), "AlternativeColl", getIID());      

}


public Object dolly() throws OculusException
{
	AlternativeSet alt = null;
	alt = new AlternativeSet();
	alt.setIID(getIID());
	alt.setObjectContext(getObjectContext());
	alt.setPersState(getPersState());
	alt._classIID = _classIID;
	alt._stateIID = _stateIID;
	//Saleem added to this line
	if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
	alt.putAll(getProperties());
	alt.setName(getName());
	//alt.setDescription(getDescription());
	alt._creatorIID = _creatorIID;
	alt._accessIID = _accessIID;
	alt.setCreationDate(getCreationDate());
	alt.setMessageAttached(hasMessageAttached());
	alt.setLinkAttached(hasLinkAttached());
	alt.setFileAttached(hasFileAttached());
	//specific to altset
	alt._featureIID = _featureIID;
	alt._selectedAltIID = _selectedAltIID;
	alt.setPersState(getPersState());
	alt._selectionComment = _selectionComment;
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
 * getChosenAlternative method comment.
 */
public IAlternative getChosenAlternative() throws OculusException
{
	if (_selectedAltIID!=null)
	{
	return (IAlternative)getObjectContext().getCRM().getCompObject(getObjectContext(),"Alternative",_selectedAltIID); 
	}
	else
	{
	return null;
	}
}


	protected String getCreateQuery()
	throws OculusException
	{
	return "INSERT INTO "+TABLE+" "+
		 " ("+COL_OBJECTID+", "
		 //+COL_CLASSID+", "
		 +COL_STATEID+", "
		 +COL_DELETESTATE+", "
		 +COL_GUID+", "
//         +COL_NAME+", "
//             +COL_DESCRIPTION+", "
		 +COL_CREATIONDATE+", "
		 +COL_CREATORID+", "
		 +COL_ACCESSID+", "
		 +COL_MESSAGEATTACHED+", "
		 +COL_FILEATTACHED+", "
		 +COL_LINKATTACHED+", " 
		 +(getSelectionComment()!=null?COL_SELECTIONCOMMENT+",":"")
		 +COL_FEATUREID+" " +         
		 ") "+

		 
		 " VALUES "+
		 " ("+getIID().getLongValue()+","
		//+getDefnObject().getIID().getLongValue()+","
		+_stateIID+","
		+getDeleteState().getIntValue()+","
		+"'"+SQLUtil.primer(getGUID().toString())+"',"
//        +"'"+SQLUtil.primer(getName())+"',"
//        +"'"+SQLUtil.primer(getDescription())+"',"
		+"'"+getCreationDate().toString()+"',"
		+getCreatorIID().getLongValue()+","
		+getAccessIID().getLongValue()+","
		+(hasMessageAttached()?"1":"0")+","
		+(hasFileAttached()?"1":"0")+","
		+(hasLinkAttached()?"1":"0")+","
		+(getSelectionComment()!=null?"'"+SQLUtil.primer(getSelectionComment())+"',":"")
		+getFeatureIID().getLongValue()+""+
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
 * getFeatureCategoryLinkObject method comment.
 */
public com.oculussoftware.api.repi.IIID getFeatureCategoryLinkObject() throws OculusException
{
	return null;
}


public IIID getFeatureIID() throws OculusException
{
	return _featureIID;
}


/**
 * expected parent pointer is
 */
protected String getLoadQuery() throws OculusException {
	return "SELECT * "+
		 "FROM "+TABLE+" "+
		 "WHERE "+COL_OBJECTID+"="+getIID().getLongValue();
}


/**
 * getSelectionComment method comment.
 */
public java.lang.String getSelectionComment() throws OculusException
{
	return _selectionComment;
}


/**
 * getUpdateQuery method comment.
 */
protected String getUpdateQuery() throws OculusException {
return " UPDATE "+TABLE+" "+
		 " SET "+
//       "   "+COL_NAME+"='"+SQLUtil.primer(getName())+"' "+
//       " , "+COL_DESCRIPTION+"='"+SQLUtil.primer(getDescription())+"' "+
		 "  "+COL_STATEID+"= "+_stateIID+" "+
		 " , "+COL_DELETESTATE+"= "+getDeleteState().getIntValue()+" "+
		 " , "+COL_ACCESSID+"= "+getAccessIID().getLongValue()+" "+
		 " , "+COL_MESSAGEATTACHED+"= "+(hasMessageAttached()?"1":"0")+" "+
		 " , "+COL_FILEATTACHED+"= "+(hasFileAttached()?"1":"0")+" "+
		 " , "+COL_LINKATTACHED+"= "+(hasLinkAttached()?"1":"0")+" "+
		 //specific to alt
		(getSelectionComment()!=null?" ,  "+COL_SELECTIONCOMMENT+"='"+SQLUtil.primer(getSelectionComment())+"' ":" ")+       
		(_selectedAltIID!=null?" , "+COL_SELALTERNATIVEID+"= "+_selectedAltIID.getLongValue():" ")+
		" , "+COL_FEATUREID+"= "+getFeatureIID().getLongValue()+" "+
		
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
	//setName(results.getString(COL_NAME));                // get name
	//setDescription(results.getString(COL_DESCRIPTION));  // get desc
	setCreationDate(results.getTimestamp(COL_CREATIONDATE));
	setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
	setFileAttached(results.getBoolean(COL_FILEATTACHED));
	setLinkAttached(results.getBoolean(COL_LINKATTACHED));

	
	//specific to AlternativeSet
	if (results.getLong(COL_SELALTERNATIVEID)!=0)
	{
		_selectedAltIID = (new SequentialIID(results.getLong(COL_SELALTERNATIVEID)));
	}
	_featureIID = (new SequentialIID(results.getLong(COL_FEATUREID)));
	_selectionComment = (results.getString(COL_SELECTIONCOMMENT));
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
 * setChosenAlterative method comment.
 */
public IAlternativeSet setChosenAlterative(IAlternative alt) throws OculusException
{
	return setChosenAlterative(alt, null);
}


/**
 * setChosenAlterative method comment.
 */
public IAlternativeSet setChosenAlterative(IAlternative alt, String selectionComment) throws OculusException
{
	if (_perState == PersState.UNMODIFIED)
	_perState = PersState.MODIFIED;  
	_selectedAltIID = alt.getIID();
	_selectionComment = selectionComment;
	return this;
}


/**
 * setFeatureCategoryLinkObject method comment.
 */
public IAlternativeSet setFeatureCategoryLinkObject(IFeatureCategoryLink cl) throws OculusException
{
	if (_perState == PersState.UNMODIFIED)
	_perState = PersState.MODIFIED;  
	_featureIID = cl.getIID();
	return this;
}


	protected void loadProperties()
	throws OculusException
	{
	}}