package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.mkt.prod.*;

import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.common.org.*;

public interface IMarketInput extends IBusinessObject
{


  public IMarketInput createCopy()
    throws OculusException;

  public IIID getLinkIID()
    throws OculusException;
        
  public IFeature associateFeature(IFeature feat)
    throws OculusException;
    
  public IFolderInputLink associateFolder(IFolder folder)
    throws OculusException;  
    
  

  public IFeature disAssociateFeature(IFeature feat)
    throws OculusException;
    
  public IProblemStatement associateProblemStatement(IProblemStatement ps)
    throws OculusException;
  
  public IProblemStatement disAssociateProblemStatement(IProblemStatement ps)
    throws OculusException;

  public IFeature createNewFeature()
    throws OculusException;

    
  //------------------------ MUTATORS -------------------------------
    public long getVisibleID()
    throws ORIOException;

    public IMarketInput setVisibleID(long id)
    throws ORIOException;

    
  public IMarketInput setSourceUserObject(IUser user)
    throws OculusException;
  
  public IMarketInput setBaseClassLong(long id)
    throws OculusException;
  
  public IMarketInput setImportance(int imp)
    throws ORIOException;
  
  public IMarketInput setUserComment(String imp)
    throws ORIOException;
  
  public IMarketInput setSubject(String imp)
    throws ORIOException;
    
  public IMarketInput setFolderInputLink(IFolderInputLink fil)
    throws ORIOException;
  
  public IMarketInput setExternal()
    throws ORIOException;
  
  public IMarketInput setAnonymousCreator()
    throws ORIOException;
  
  
  //------------------------ ACCESSORS -------------------------------
  public int getImportance()
    throws OculusException;
  
  public String getUserComment()
    throws OculusException;
  
  public String getSubject()
    throws OculusException;
  
  public IFolderInputLink getFolderInputLink()
    throws OculusException;
  
  //Should be deprecated very soon. Use the next method instead
  public IFolderInputLink getFolderInputLink(IIID folderIID)
    throws OculusException;
  
  public IFolderInputLink getFolderInputLink(IFolder fold)
    throws OculusException;
  
  public IFolderInputLinkColl getFolderInputLinks()
    throws OculusException;
  
  public IRClass getBaseClass()
    throws OculusException;  
    
  public int getScore()
    throws ORIOException;
    
  public IUser getSourceUserObject()
    throws OculusException;

  public IProblemStatementColl getProblemStatements()
    throws OculusException;
  
  public IProblemStatementColl getProblemStatements(boolean editable)
    throws OculusException;
    
  public IFeatureColl getFeatures()
    throws OculusException;
    
  public IFeatureColl getFeatures(boolean editable)
    throws OculusException;
 
 public IReaction getReaction()
    throws OculusException;
    
 
// Alok : we still need to be able to get all of the reactions for an input.
 public IReactionColl getReactions()
   throws OculusException;
   
 public boolean isStandardInput() 
   throws OculusException;
      
 public boolean isQuestionInput() 
   throws OculusException;
   
   
  public boolean isArticleInput() 
   throws OculusException;    
  
  public boolean isWinLoss() 
   throws OculusException;    
  
  public boolean isImportedInput() 
   throws OculusException;    
   
   
   public boolean isSummaryInput() 
   throws OculusException;
  
   
   public boolean isOpen() throws OculusException;    
   public boolean isClosed() throws OculusException;    
   public void setAsOpen() throws OculusException;    
   public void setAsClosed() throws OculusException;    
   
   public boolean hasFiles() throws OculusException;    
   public boolean hasLinks() throws OculusException;    
   public boolean hasTopics() throws OculusException;    
   
   public String getEmailString() throws OculusException;    
    
   public void move(IFolder fromFolder, IFolder toFolder) throws OculusException;  
  
}