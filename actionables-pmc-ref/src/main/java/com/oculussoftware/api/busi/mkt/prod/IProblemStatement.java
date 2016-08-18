package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

public interface IProblemStatement extends IBusinessObject
{
  public IFeature createNewFeature()
    throws OculusException;

  public IFeature associateFeature(IFeature feat)
    throws OculusException;
    
  public IFeature disAssociateFeature(IFeature feat)
    throws OculusException;
    
  public IMarketInput associateInput(IMarketInput input)
    throws OculusException;
  
  public IFolderInputLink associateInput(IFolderInputLink inputlink)
    throws OculusException;
    
  public IMarketInput disAssociateInput(IMarketInput input)
    throws OculusException;
    
  public IFeatureColl getFeatures()
    throws OculusException;
    
  public IFeatureColl getFeatures(boolean editable)
    throws OculusException;

  public IMarketInputColl getInputs()
    throws OculusException;
  
  public IFolder getFolder()
    throws OculusException;
    
  public IMarketInputColl getInputs(boolean editable)
    throws OculusException;

  public int numInputs()
    throws OculusException;

 //------------------------ MUTATORS -------------------------------
  public IProblemStatement setFolderObject(IFolder folder)
    throws OculusException;

  //------------------------ ACCESSORS -------------------------------
  public int getMinScore()
    throws OculusException;
    
  public int getMaxScore()
    throws OculusException;
    
  public float getMeanScore()
    throws OculusException;

  public float getMedianScore()
    throws OculusException;

  public int numScoredInputs()
    throws OculusException;
    
  public int getTotalScore()
    throws OculusException;
        
  public IFolder getFolderObject()
    throws OculusException;

   public boolean hasFiles() throws OculusException;    
   public boolean hasLinks() throws OculusException;    
   public boolean hasTopics() throws OculusException;    
   public String getPriorityLevel() throws OculusException;
   public IIID getPriorityIID() throws OculusException;
 
}