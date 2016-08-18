package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.xmeta.*;
import java.util.*;

public interface IRepository extends IRObjectContext
{
  //---------------------------------------------------------------
  // Repository I/O methods
  //---------------------------------------------------------------

  public void initialize() throws OculusException;
  public void setMaxOID()
    throws OculusException;
    
  public String getName()
    throws ORIOException;
    
  public IIID genReposID()
    throws OculusException;
    
  public int getMajorVersion() 
    throws OculusException;

  public int getMinorVersion() 
    throws OculusException;

  public String getRelease()
    throws OculusException;
    
  public int getBuild()
    throws OculusException;
    
  public String getFullReleaseString()
    throws OculusException;
    
  /**
  */
  public IIID makeReposID(long id)
    throws ORIOException;
    
  public IRObject getRootObject()
    throws ORIOException;

  public IRConnection getDataConnection(IObjectContext context)
    throws OculusException;
    
  //---------------------------------------------------------------
  // Repository analysis and validation methods
  //---------------------------------------------------------------
  /** Validates the current state of the data against the current meta repos */
  public void validate(Observer o);
  
  public IRBusinessModel getBMRepository() throws OculusException;
  public IXMR getXMR( ) throws OculusException;
  public DBVendor getVendor();
  public String LOJO();
}