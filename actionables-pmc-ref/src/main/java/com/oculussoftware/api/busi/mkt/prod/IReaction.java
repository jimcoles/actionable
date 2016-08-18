package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

public interface IReaction extends IBusinessObject
{
  public IReaction createNewReaction()
    throws OculusException;

    
  //------------------------ MUTATORS -------------------------------
  public IReaction setInputLink(IFolderInputLink input)
    throws OculusException;

  public IReaction setWeight(int imp)
    throws ORIOException;
  
  
  //------------------------ ACCESSORS -------------------------------
  public IFolderInputLink getInputLink()
    throws OculusException;

 public int getWeight()
    throws OculusException;
  
  public boolean hasFiles() throws OculusException;    
  public boolean hasLinks() throws OculusException;    
  public boolean hasTopics() throws OculusException;    
   
   
}