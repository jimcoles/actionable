package com.oculussoftware.api.busi.common.search;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.api.sysi.license.ModuleKind;
import java.util.*;
import java.sql.Timestamp;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.sysi.*;
/**
* $Workfile: IAdvancedSearch.java $
* Create Date: 6/25/2000
* Description: Supports advanced search.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: IAdvancedSearch.java $
 * 
 * *****************  Version 11  *****************
 * User: Eroyal       Date: 9/22/00    Time: 3:26p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * javadoc
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 8/07/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * clean up
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 8/07/00    Time: 11:36a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * rework the inheritance
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 8/04/00    Time: 3:47p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * advsrch and custom reports
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 8/02/00    Time: 6:44p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * added more functionality to Custom Report and Adv Srch
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 7/28/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * adv srch
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 7/28/00    Time: 11:53a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * added functionality to Advanced search.
 * made IBasicReport and BasicReport implement IAdvancedSearch
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 7/27/00    Time: 7:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * added more functionality to custom reports and added functionality to
 * advanced search
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 7/13/00    Time: 2:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/search
 * ya gotta have import statements to compile.
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
* Defines the necessary methods that differentiate an IAdvancedSearch from 
* an ISearch.  An IAdvancedSearch is not persisted to the database.
*
* @author Egan Royal
*/
public interface IAdvancedSearch extends ISearch
{
  /**
  * This method returns an IGenericObjectCollection that will be used to 
  * display the results of the AdvancedSearch.
  * @return The IGenereicObjectCollection.
  * @exception com.oculussoftware.api.sysi.OculusException 
  */
  public IGenericObjectCollection getObjects() throws OculusException;
}