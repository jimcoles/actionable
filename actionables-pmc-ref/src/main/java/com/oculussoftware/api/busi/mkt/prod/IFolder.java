package com.oculussoftware.api.busi.mkt.prod;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.busi.*;

/** This interface represents a folder containing market input data and problem statements.
* It contains no real data of its own, except for a name.
*
* @author Alok Pota
*/

/*
* $Workfile: IFolder.java $
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* Change Activity
* Issue number  	Programmer    	Date      	Description
* ------------    ----------      ----        -----------
*/
public interface IFolder extends IBusinessObject
{
  /** Creates a new folder under the current folder.
  *
  * @return the newly created sub-folder
  * @exception com.oculussoftware.api.sysi.OculusException
  */
 public IFolder createNewFolder()
    throws OculusException;

  /** Makes an exact copy of this folder.
  *
  * @return the newly created copy
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolder createCopy()
    throws OculusException;
       
  /** Sets this folder's parent to the given folder.
  *
  * @param folder the parent folder
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolder setParentFolder(IFolder folder)
    throws OculusException;

  /** Sets this folder's parent to the folder for the given IIID.
  *
  * @param folderid the IIID of the parent folder
  * @return this object
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolder setParentFolder(IIID folderid)
    throws OculusException;

  /** Returns this folder's parent folder.
  *
  * @return this folder's parent folder
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolder getParentFolder()
    throws OculusException;

  /** Returns a collection of child folders.  Only folders directly beneath this folder are
  * in the list.
  *
  * @return the collection of child folders
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolderColl getFolders()
    throws OculusException;   
 
  /** Returns a collection of folder-input links for this folder.
  *
  * @return the collection of folder-input links for this folder
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IFolderInputLinkColl getFolderInputLinks()
    throws OculusException;   
 
  /** Returns a collection of market inputs associated with this folder.
  *
  * @return the collection of market inputs associated with this folder
  * @exception com.oculussoftware.api.sysi.OculusException
  */
  public IMarketInputColl getMarketInputs()
    throws OculusException;      
 
 public IBusinessObjectList getMarketInputList()
    throws OculusException;      
    
 //Get the input link for this folder and a specific input
 public IFolderInputLink getInputLink(IMarketInput inp)
    throws OculusException;         

 //Get problem statements under this folder   
 public IProblemStatementColl getProblemStatements()
    throws OculusException;        
 
 //Get problem statements under this folder   
 public IRModelElementList getForms()
    throws OculusException;        
  
 public boolean isRoot();
 
 public IMarketInputColl getSummaryInputs()
     throws OculusException;        
 
  public String getLocation() throws OculusException; 
  public IMarketInput getSummaryRecord() throws OculusException;  
  public IREntryForm getForm() throws OculusException;
  public void fixDispositions() throws OculusException;
  public void preDelete() throws OculusException;
  public void batchMove(long[] inps, long targetfolderid) throws OculusException;
  
  //Display methods 
  public java.util.List getInputRows() throws OculusException;      
  public IFolder setInputRows(java.util.List list) throws OculusException;      
  public void addInputRow(IMarketInput inp) throws OculusException;      
  public void removeInputRow(IMarketInput inp) throws OculusException;      
  public void updateInputRow(IMarketInput inp) throws OculusException;      
   
}