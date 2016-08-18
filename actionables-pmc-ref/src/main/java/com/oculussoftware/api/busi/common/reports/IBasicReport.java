package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.api.busi.common.search.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;
import java.sql.Timestamp;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.busi.*;

/*
* $Workfile: IBasicReport.java $
* Create Date: 6/25/2000
* Description: A savable report definition that supports a tabular
*              result.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*
* $History: IBasicReport.java $
 * 
 * *****************  Version 25  *****************
 * User: Eroyal       Date: 9/22/00    Time: 9:39a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * javadoc
 * 
 * *****************  Version 24  *****************
 * User: Eroyal       Date: 8/07/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * clean up
 * 
 * *****************  Version 22  *****************
 * User: Eroyal       Date: 8/04/00    Time: 3:47p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * advsrch and custom reports
 * 
 * *****************  Version 21  *****************
 * User: Eroyal       Date: 8/03/00    Time: 6:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * Yet again, I added more functionality to adv srch and custom reports
 * 
 * *****************  Version 20  *****************
 * User: Eroyal       Date: 8/02/00    Time: 6:44p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * added more functionality to Custom Report and Adv Srch
 * 
 * *****************  Version 19  *****************
 * User: Eroyal       Date: 7/31/00    Time: 7:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * added more functionality to advanced search
 * 
 * *****************  Version 18  *****************
 * User: Eroyal       Date: 7/28/00    Time: 11:53a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * added functionality to Advanced search.
 * made IBasicReport and BasicReport implement IAdvancedSearch
 * 
 * *****************  Version 17  *****************
 * User: Eroyal       Date: 7/27/00    Time: 7:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * added more functionality to custom reports and added functionality to
 * advanced search
 * 
 * *****************  Version 16  *****************
 * User: Eroyal       Date: 7/26/00    Time: 6:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * added yet again. more functionality to the custom reports
 * 
 * *****************  Version 15  *****************
 * User: Eroyal       Date: 7/25/00    Time: 6:46p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * added more functionality to the Custom Report
 * 
 * *****************  Version 14  *****************
 * User: Eroyal       Date: 7/24/00    Time: 7:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 7-24-00
 * 
 * added more functionality to Custom Report
 * 
 * *****************  Version 13  *****************
 * User: Eroyal       Date: 7/22/00    Time: 3:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 7-22-00
 * 
 * *****************  Version 12  *****************
 * User: Eroyal       Date: 7/20/00    Time: 6:01p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 7-20-00
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 7/19/00    Time: 6:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 7-19-00
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 7/18/00    Time: 7:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 7-18-00
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 7/17/00    Time: 6:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 7-17-00
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 7/17/00    Time: 10:41a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * need new code
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 7/14/00    Time: 7:02p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 5  *****************
 * User: Znemazie     Date: 7/13/00    Time: 4:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 4  *****************
 * User: Znemazie     Date: 7/13/00    Time: 1:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * repackaging
 * 
 * *****************  Version 3  *****************
 * User: Znemazie     Date: 7/13/00    Time: 1:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:52a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/busi/reports
*/

/** 
* Defines the necessary methods that differentiate an IBasicReport from 
* an ISearch.
*
* @author Egan Royal
*/
public interface IBasicReport extends IRDataView,ISearch
{
  /**
  * This accessor method returns the ReportConfigType based on the 
  * Target Class of the Report.  The method returns null if the 
  * Target Class is not recognized.
  * @return The ReportConfigType based on the Target Class of the Report.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public ReportConfigType getReportConfigType() throws OculusException; 
}
