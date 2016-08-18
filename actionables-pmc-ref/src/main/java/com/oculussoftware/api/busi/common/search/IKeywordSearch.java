package com.oculussoftware.api.busi.common.search;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.sysi.license.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.repos.query.*;
import java.util.*;
import java.sql.*;

/**
* $Workfile: IKeywordSearch.java $
* Create Date: 6/25/2000
* Description: Supports definition of a simple keyword search.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: IKeywordSearch.java $
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 9/22/00    Time: 3:26p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * javadoc
 * 
 * *****************  Version 7  *****************
 * User: Sshafi       Date: 7/31/00    Time: 4:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 6  *****************
 * User: Sshafi       Date: 7/28/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 5  *****************
 * User: Sshafi       Date: 7/27/00    Time: 4:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 7/27/00    Time: 10:01a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 3  *****************
 * User: Sshafi       Date: 7/24/00    Time: 5:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 12:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * Repackaged.  Still very much broken though.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:33a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/search
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:52a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/busi/search
*/


/** 
* Defines the necessary methods to create and run a KeywordSearch.
*
* @author Saleem Shafi
*/
public interface IKeywordSearch
{
  /**
  * This method returns the actual SQL String that will be executed.
  * @return The SQL String.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public String getSQL() throws OculusException;

  /**
  * This method returns the ReposSearcher that will be used to run 
  * the KeywordSearch.
  * @return The ReposSearcher.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ReposSearcher getSearcher() throws OculusException;
  
  /**
  * This method returns the List of IQuery objects.
  * @return The List of IQuery objects
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public List getQueries() throws OculusException;
  
  /**
  * This method returns sets the scope object of the KeywordSearch.
  * @param classID The IXClass of the scope object.
  * @param loid The long Object ID of the scope object.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void setScopeObject(IXClass classID, long loid)
    throws OculusException;

  /**
  * This method sets the target classes of the KeywordSearch.
  * @param classSpec A List of target classes.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void setQueryClasses(List classSpec)
    throws OculusException;

  /**
  * This method sets the List of Keywords for the KeywordSearch.
  * @param keywords A space delimited list of keywords.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void setKeywords(String keywords)
    throws OculusException;
    
  /**
  * This method sets the value that tells the KeywordSearch whether to use
  * an AND or an OR in the query.
  * @param all false OR, true AND.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void setSearchForAllKeywords(boolean all)
    throws OculusException;
   
  /**
  * This method sets the value that tells the KeywordSearch whether to 
  * search all fields or not.
  * @param all false do not search all fields, true do search all fields.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */ 
  public void setSearchAllFields(boolean all)
    throws OculusException;

  /**
  * This accessor method returns the ModuleKind of the KeywordSearch.
  * @return The ModulueKind of the KeywordSearch.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public ModuleKind getSearchModule()
    throws OculusException;
  
  /**
  * This mutator method sets the ModulueKind of the KeywordSearch.
  * @param mod The ModulueKind.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */ 
  public void setSearchModule(ModuleKind mod)
    throws OculusException;
  
  /**
  * This mutator method set the value that tells the KeywordSearch whether or not 
  * to use the synonym library.
  * @param syn true use synonyms, false do not.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void useSynonyms(boolean syn)
    throws OculusException;
  
  /**
  * This accessor method returns whether or not the KeywordSearch uses
  * synonyms.
  * @return true iff the KeywordSearch uses sysnonyms.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public boolean usesSynonyms()
    throws OculusException;

  /**
  * This mutator method sets the value that specifies the From Date in the
  * date range for the KeywordSearch.
  * @param date The From Date for the KeywordSearch.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public void setFromDate(Timestamp date)
    throws OculusException;
  
  /**
  * This mutator method sets the value that specifies the To Date in the
  * date range for the KeywordSearch.
  * @param date The To Date for the KeywordSearch.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */  
  public void setToDate(Timestamp date)
    throws OculusException;

  /**
  * This accessor method returns the From Date for the date range of the
  * KeywordSearch.
  * @return The From Date.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Timestamp getFromDate()
    throws OculusException;
  
  /**
  * This accessor method returns the To Date for the date range of the
  * KeywordSearch.
  * @return The To Date.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public Timestamp getToDate()
    throws OculusException;
}