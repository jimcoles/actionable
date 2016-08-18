package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

import com.oculussoftware.system.*;
import java.util.*;

/** MModel acts as the Java binding of the abstract component model
*   to which all business objects in the system adhere. 
*
*   NOTE: This data in this file could conceivably be moved to a properties file
*         or other flat file; or, this Java file could be generated at build time
*         so as not to let end-users tamper with the properties file.
*/
public class MModel //ER implements IRModel
{

  //----------------------------------------------------------------
  // Public class constants
  //----------------------------------------------------------------
  public static final MModel OMM = new MModel();
  
  //-----------------------------------------------------  
  //-----------------------------------------------------  
  private static IGUID _GUID = _doGUID("0000000000000000000000000000000000000000");
  private static IIID  _IID  = new MIID(0);

  //-----------------------------------------------------  
  //-----------------------------------------------------  
  private MModel()
  {
    
  }
  
  //-----------------------------------------------------  
  //-----------------------------------------------------  
  private static IGUID _doGUID(String strGUID)
  {
    try {
      return new GUID(strGUID);
    }
    catch (OculusException ex) {
      throw new OculusError("Static init of meta repository failed in doGUID().");
    }
  }

  public IGUID getGUID()
  {
    return _GUID;
  }
  
  public String getName() throws ORIOException{
    return "The Oculus Modeling Language";
  }

  public void validate(Observer o) {  }
  public void registerClass(IRClass c) throws ORIOException{
    throw new ORIOException("registerClass() not implemented for " + getName() + ".");
  }
  public IRTransaction getTransaction() throws ORIOException{
    return null;
  }
  public IQueryExpr createQuery() throws ORIOException{
    throw new ORIOException("createQuery() not implemented for " + getName() + ".");
  }

  public IIID genReposID() throws ORIOException
  {
    throw new ORIOException("Meta repository does not create ID's.");
  }


  public IIID        getIID() throws ORIOException {return _IID;}
  public void        save() throws ORIOException {}
  public void        load() throws ORIOException {}
//  public IRepository getRepository() {return null;}

  public IObjectContext getObjectContext()
  {
    return null;
  }

  //
  // ------------------- IRObject methods ----------------------------------
  //
  public IRepository  getMetaRepository(){ return null; }

  public IRObject     getParentObject() throws ORIOException {  return null; }
//  public IRObject     setParentObject(IRObject parent) throws ORIOException { return this;}
  public IRCollection getParentContainers() throws ORIOException {return null;}
  public IRObject     setName(String name) throws ORIOException {return (IRObject)this;}
  public IRClass      getDefnObject() throws ORIOException {return null;}
//  public IRObject     setDefnObject(IRClass cls) throws ORIOException {return this;}
  public IRState      getStateObject() throws ORIOException {return null;}
  public IRObject     setStateObject(IRState state) throws ORIOException {return (IRObject)this;}
  public boolean      childExists(IIID iid) throws ORIOException {return false;}
  public IRLinkCollMap        getChildObjects() throws ORIOException {return null;}
  public IRPropertyMap        getProperties( ) throws ORIOException {return null;}
  //public IRObject     setChildObject(IRAssoc metaassoc, IRObject obj) throws ORIOException {return (IRObject)this;}
//  public IRObject     setChildList(IRAssoc metaassoc, IRList list) throws ORIOException {return this;}
//  public IRObject     setChildSet(IRAssoc metaassoc, IRSet set) throws ORIOException {return this;}

  public IRObject setProperty(IRAttribute metaprop, Object value) 
    throws ORIOException
  {
    return (IRObject)this;
  }
  
  public IRLinkCollMap getSharedObjects() throws ORIOException {return null;}
  
  /*
  public IRCollection getLinksTo(IRAssoc assoc) 
    throws ORIOException { return null;}
  public IRLinkCollMap getAllLinksTo() 
    throws ORIOException{return null; }
  public IRLinkCollMap getAllLinksFrom() 
    throws ORIOException{return null; }
  public IRCollection getAllLinksFrom(IRAssoc assoc) 
    throws ORIOException{return null; }
 */
  
  //----------------------------------------------------------------
  // Inner classes
  //----------------------------------------------------------------
  /** MClass (Meta Class) provides an enumeration of the terminals of the 
  *   Oculus UML in the form of Java language bindings.  These elements
  *   cannot change in semantics or be extended by the end user.
  *   This implementations supports IRClass, but unlike SRClass, it does not
  *   have to go to a data store for its definition; instead it goes to statically
  *   initialized data since we know these things will be fixed for a given 
  *   release of our product.
  */
  public static class MClass //ERimplements IRClass
  {
    private IGUID  _guid = null;
    private IIID   _iid  = null;
    private String _name = null;

    // MClass( guid, iid, name )
    public static final MClass CLASS        = new MClass("1",  1 , "class");
    public static final MClass TYPE         = new MClass("2",  2 , "type");
    public static final MClass ATTRIBUTE    = new MClass("3",  3 , "attribute");
    public static final MClass DATATYPE     = new MClass("4",  4 , "datatype");
    public static final MClass PACKAGE      = new MClass("5",  5 , "package");
    public static final MClass ASSOCIATION  = new MClass("6",  6 , "association");
    public static final MClass RELATIONSHIP = new MClass("7",  7 , "relationship");
    public static final MClass STATEMACHINE = new MClass("8",  8 , "statemachine");
    public static final MClass STATE        = new MClass("9",  9 , "state");
    public static final MClass TRANSITION   = new MClass("10", 10, "transition");
  
    /// Private constructor 
    private MClass (String strGuid, long liid, String name)
    {
      try {
        _guid = new GUID(strGuid);
        _iid  = new MIID(liid);
        _name = name;
      }
      catch (OculusException ex) {
        throw new OculusError("Error initializing an MClass: " + ex.getMessage());
      }
    }
    
    public IGUID getGUID()
    {
      return _guid;
    }
    
    public IIID getIID() 
      throws ORIOException
    {
      return _iid;
    }

    // IRObject methods 
    public IRObject setStateObject(IRState state) throws ORIOException{ return (IRObject)this; }
    public IRObject setParentObject(IRObject parent) throws ORIOException{ return (IRObject)this; }
    public IRObject setName(String name) throws ORIOException{ return (IRObject)this; }
    public IRObject setLockTrans(ITransaction trans){ return (IRObject)this; }
    public IRObject setDefnObject(IRClass cls) throws ORIOException{ return (IRObject)this;}
    //public IRObject setChildSet(IRAssoc metaassoc, IRSet set) throws ORIOException{return (IRObject)this; }
    //public IRObject setChildObject(IRAssoc metaassoc, IRObject obj) throws ORIOException{return (IRObject)this; }
    //public IRObject setChildList(IRAssoc metaassoc, IRList list) throws ORIOException{return (IRObject)this; }
    public IRState       getStateObject() throws ORIOException{return null;}
    public IRPropertyMap getProperties() throws ORIOException{return null;}
    public IRObject      getParentObject() throws ORIOException{return null;}
    public IRCollection  getParentContainers() throws ORIOException{return null;}
    public String        getName() throws ORIOException{return "mclass"; }
    public ITransaction  getLockTrans(){return null;}
    public IRClass       getDefnObject() throws ORIOException{return null;}
    public IRLinkCollMap getChildObjects() throws ORIOException{return null;}
    
    public boolean childExists(IIID iid) throws ORIOException{return false;}


    public void save( ) throws ORIOException{ }
    public void load() throws ORIOException{ }
    public IRepository getRepository() {return null;}
    public IRElement getParentElement() throws ORIOException{return null;}

    /** IRTypeInfo methods */
    public IRSet /*of IRTypes*/ getSpecs( )
      throws ORIOException
    {
      throw new ORIOException("getSpecs() not implemented for " + getName() + ".");
    }
    
    
    public IRList /* of IRAttribute */ getAllAttrs( )
      throws ORIOException
    {
      throw new ORIOException("getAllAttrs() not implemented for " + getName() + ".");
    }
    public IRList /* of IRAssoc  */    getAllCompAssocs( )
      throws ORIOException
    {
      throw new ORIOException("getAllCompAssocs() not implemented for " + getName() + ".");
    }
    public IRList /* of IRAssoc  */    getAllAggrAssocs( )
      throws ORIOException
    {
      throw new ORIOException("getAllAggrAssocs() not implemented for " + getName() + ".");
    }

    public IRList /* of IRAssoc  */    getAllGenAssocs( )
      throws ORIOException
    {
      throw new ORIOException("getAllAggrAssocs() not implemented for " + getName() + ".");
    }

    public IRObject setProperty(IRAttribute metaprop, Object value) 
      throws ORIOException{return (IRObject)this;}
    public IRLinkCollMap getSharedObjects() throws ORIOException{return null; }
    //public IRCollection  getLinksTo(IRAssoc assoc) throws ORIOException{return null; }
    //public IRLinkCollMap getAllLinksTo() throws ORIOException{return null; }
    //public IRLinkCollMap getAllLinksFrom() throws ORIOException{return null; }
    //public IRCollection  getAllLinksFrom(IRAssoc assoc) throws ORIOException{return null; }

    public IObjectContext getObjectContext()
    {
      return null;
    }
  }
  
  /** 
  */  
  public static class MIID implements IIID
  {
    private long _id;

    public MIID(long iid)
    {
      _id = iid;
    }
    
    public long getLongValue()
    {
      return _id;
    }
    
    public String toString()
    {
      return Long.toString(_id);
    }
  }

}