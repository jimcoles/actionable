package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;


public interface IFolderInputLink extends IBusinessObject
{

 //Creates a new folder under the current folder
 public IFolderInputLink createNewFolderInputLink()
    throws OculusException;

//Creates a shallow copy
 public IFolderInputLink createCopy()
    throws OculusException;
       
 public IFolderInputLink setFolder(IFolder folder)
    throws OculusException;

 public IFolder getFolder()
    throws OculusException;

 public IFolderInputLink setInput(IMarketInput inp)
    throws OculusException;

 public IMarketInput getInput()
    throws OculusException;
 
 public IMarketInput getInput(boolean edit)
    throws OculusException;
    
 public IIID getInputIID()
    throws OculusException;   

 public IFolderInputLink setReaction(IReaction inp)
    throws OculusException;

 public IFolderInputLink move(IFolder source, IFolder target)
   throws OculusException;

 public IReaction getReaction()
   throws OculusException;
}