/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.model;

public class SemanticLink extends ReposObject implements ISemanticLink
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  
  public final static String TABLE            = "SEMANTICLINK";
  public final static String COL_OBJECTID     = "OBJECTID";
  public final static String COL_Guid         = "Guid";
  public final static String COL_SRCOBJECTID  = "SRCOBJECTID";
  public final static String COL_DESTOBJECTID = "DESTOBJECTID";
  public final static String COL_LINKTYPE     = "LINKTYPE";
  public final static String COL_ORDERNUM     = "ORDERNUM";
  
  private Oid    _srcobjectiid, _destobjectiid;
  private LinkKind _linktype;
  private int     _ordernum;
  
  public SemanticLink() throws AppException
  {
    super();
  }
  
  //---------------------------- ReposObject -----------------------
  
  private String getLoadQuery()
    throws AppException
  { 
    return "SELECT * FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }
      
  private String getLoadPropertiesQuery()
    throws AppException
  { return null; }

  private String getUpdateQuery()
    throws AppException
  { 
    return "UPDATE "+TABLE+" SET "+
           COL_ORDERNUM+"="+_ordernum+" "+
           " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  private String getCreateQuery()
    throws AppException
  { 
    return "INSERT INTO "+TABLE+
      "( "+
            COL_OBJECTID+", "+
            COL_Guid+", "+
            COL_SRCOBJECTID+", "+
            COL_DESTOBJECTID+", "+
            COL_LINKTYPE+", "+
            COL_ORDERNUM+" "+
            ") VALUES ("+
            _iid.getLongValue()+", "+
            "'"+_guid.toString()+"', "+
            _srcobjectiid.getLongValue()+", "+
            _destobjectiid.getLongValue()+", "+
            _linktype.getIntValue()+", "+
            _ordernum+" "+
            ")";
  }

  private String getDeleteQuery()
    throws AppException
  { 
    return "DELETE FROM "+TABLE+
            " WHERE "+COL_OBJECTID+"="+_iid.getLongValue(); 
  }

  private void load(IDataSet results)
    throws AppException
  { 
    IRepository repos = getObjectContext().getRepository();
    _guid = new Guid(results.getString(COL_Guid).trim());
    _iid = results.getIID();
    _srcobjectiid = repos.makeReposID(results.getLong(COL_SRCOBJECTID));
    _destobjectiid = repos.makeReposID(results.getLong(COL_DESTOBJECTID));
    _linktype = LinkKind.getInstance(results.getInt(COL_LINKTYPE));
    _ordernum = results.getInt(COL_ORDERNUM);
    setPersState(PersState.UNMODIFIED);
  }
  
  public Object dolly() throws AppException
  {
    SemanticLink ra = null;
      ra = new SemanticLink();
      ra.setOid(getOid());
      ra.setObjectContext(getObjectContext());
      ra.setPersState(getPersState());
      ra.setSourceObjectIID(getSourceObjectIID());
      ra.setDestObjectIID(getDestObjectIID());
      ra.setLinkType(getLinkType());
      ra.setOrderNum(getOrderNum());
    return ra; 
  }//
  
  //------------------------------ Element -----------------------
  
  public String getName()  
    throws AppException
  { return null; }

  public String getDescription()
    throws AppException
  { return null; }

  public Element setName(String n)
    throws AppException
  { return null; }
 
  public Element setDescription(String d)
    throws AppException
  { return null; }
  
  //----------------------------------------------------------------
  
  public IPoolable construct(ObjectContext context, IDataSet args) throws AppException
  {
    Oid iid = null;
    if (context == null)
      throw new AppException("Context Argument expected.");
    setObjectContext(context);
    if (args == null)
    {
      iid = getObjectContext().getRepository().genReposID();
      setPersState(PersState.NEW);
    }
    else
    {
      setPersState(PersState.UNINITED);
      iid = args.getIID();
    }
    setOid(iid);
    if (args != null && args.containsKey(COL_LINKTYPE))
      load(args);
    return this; 
  }
  
  public IRPropertyMap getProperties()
  {
    return null; 
  }
  
  //--------------------------- IRoleAssignment --------------------
  
  //Accessors
  public Oid getSourceObjectIID() throws AppException
  { return _srcobjectiid; }
  
  public Oid getDestObjectIID() throws AppException
  { return _destobjectiid; }
  
  public LinkKind getLinkType() throws AppException
  { return _linktype; }
  
  public int getOrderNum() throws AppException
  { return _ordernum; }
  
  //Mutators
  public ISemanticLink setSourceObjectIID(Oid iid) throws AppException
  {
    _srcobjectiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public ISemanticLink setDestObjectIID(Oid iid) throws AppException
  {
    _destobjectiid = iid;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public ISemanticLink setLinkType(LinkKind linktype) throws AppException
  {
    _linktype = linktype;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
  public ISemanticLink setOrderNum(int num) throws AppException
  {
    _ordernum = num;
    if(getPersState().equals(PersState.UNMODIFIED))
      setPersState(PersState.MODIFIED);
    return this;  
  }//
  
}