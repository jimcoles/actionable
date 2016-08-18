package com.oculussoftware.repos.bmr.view;
import com.oculussoftware.api.busi.mkt.prod.*;
import com.oculussoftware.api.sysi.sec.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.common.org.*;
import java.util.*;
import java.text.*;

/*
 ISSUE HISTORY:
 
 /*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description
//
 ISSUE DES00067    APota          5/15      2:30 pm    Calendar icon should appear next to any date field
                                                       at edit or create time.
                                                       
 ISSUE BUG00072    APota          5/15      3:30 pm    Date does not show up in the edit field
 ISSUE ENG00077    APota          5/15      3:59 pm    The not specified string won't appear in the edit boxes
 ISSUE BUG00228    APota          5/18      3:26 pm    General bug fix effort                                                      
 ISSUE BUG00307    APota          5/18      3:26 pm    Automatic select of notification list for MI create
 ISSUE DES00091    APota          5/26                 Removed double quote problem
 PRF00664                         6/2                  Win loss product context list 
                                                       improved usability by automatically picking up
                                                       product versions at form creation time 
 00668            Apota           6/2                  

 POST ALPHA PRE BETA
 
 BUG 2384												Problem editing winloss inputs resolved.	
		2577
		2624

* _____________________________________________________________________________
*
*
* $Workfile: BMEntryForm.java $
* Create Date: 2/22/00
*
* Description:
*
* Copyright 7-01-2000 Oculus Software.  All Rights Reserved.
*
* @author:  Jcoles
* @version: 1.2
*
* $History: BMEntryForm.java $
 * 
 * *****************  Version 141  *****************
 * User: Apota        Date: 9/18/00    Time: 3:07p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 135  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * Bug Fix: #2207
 * 
 * *****************  Version 134  *****************
 * User: Apota        Date: 9/13/00    Time: 5:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 131  *****************
 * User: Sshafi       Date: 9/12/00    Time: 11:38a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * Bug Fix: 2439
 * 
 * *****************  Version 130  *****************
 * User: Apota        Date: 9/11/00    Time: 8:53a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 129  *****************
 * User: Apota        Date: 9/07/00    Time: 5:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 127  *****************
 * User: Apota        Date: 8/30/00    Time: 6:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 125  *****************
 * User: Apota        Date: 8/29/00    Time: 3:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * Bug 01733 -
 * 
 * *****************  Version 123  *****************
 * User: Apota        Date: 8/24/00    Time: 2:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 116  *****************
 * User: Apota        Date: 8/23/00    Time: 1:45p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 115  *****************
 * User: Apota        Date: 8/18/00    Time: 11:07a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 113  *****************
 * User: Apota        Date: 8/17/00    Time: 12:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 112  *****************
 * User: Apota        Date: 8/16/00    Time: 6:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 110  *****************
 * User: Apota        Date: 8/16/00    Time: 3:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 109  *****************
 * User: Apota        Date: 8/14/00    Time: 6:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 108  *****************
 * User: Apota        Date: 8/14/00    Time: 11:29a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 106  *****************
 * User: Apota        Date: 8/14/00    Time: 8:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 105  *****************
 * User: Apota        Date: 8/11/00    Time: 5:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 104  *****************
 * User: Apota        Date: 8/10/00    Time: 10:08a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 103  *****************
 * User: Apota        Date: 8/07/00    Time: 5:07p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 102  *****************
 * User: Apota        Date: 8/04/00    Time: 2:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 101  *****************
 * User: Sshafi       Date: 8/02/00    Time: 2:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 100  *****************
 * User: Apota        Date: 7/30/00    Time: 7:54a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 99  *****************
 * User: Apota        Date: 7/28/00    Time: 3:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 98  *****************
 * User: Apota        Date: 7/26/00    Time: 5:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 97  *****************
 * User: Apota        Date: 7/26/00    Time: 4:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 96  *****************
 * User: Apota        Date: 7/24/00    Time: 9:51a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 95  *****************
 * User: Apota        Date: 7/20/00    Time: 1:28a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 94  *****************
 * User: Apota        Date: 7/19/00    Time: 10:49a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 93  *****************
 * User: Apota        Date: 7/18/00    Time: 1:46p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 92  *****************
 * User: Apota        Date: 7/13/00    Time: 2:33p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 90  *****************
 * User: Apota        Date: 7/13/00    Time: 4:22a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 88  *****************
 * User: Apota        Date: 7/10/00    Time: 7:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 87  *****************
 * User: Apota        Date: 7/05/00    Time: 7:11p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 85  *****************
 * User: Apota        Date: 6/28/00    Time: 5:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 84  *****************
 * User: Apota        Date: 6/28/00    Time: 11:48a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 83  *****************
 * User: Apota        Date: 6/26/00    Time: 9:00a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 81  *****************
 * User: Apota        Date: 6/22/00    Time: 5:48a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 80  *****************
 * User: Apota        Date: 6/22/00    Time: 1:10a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 79  *****************
 * User: Sshafi       Date: 6/21/00    Time: 5:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 78  *****************
 * User: Apota        Date: 6/21/00    Time: 3:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 75  *****************
 * User: Apota        Date: 6/18/00    Time: 1:50a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 74  *****************
 * User: Apota        Date: 6/16/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 73  *****************
 * User: Apota        Date: 6/15/00    Time: 4:46p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 72  *****************
 * User: Apota        Date: 6/15/00    Time: 11:29a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 71  *****************
 * User: Apota        Date: 6/15/00    Time: 11:18a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 70  *****************
 * User: Apota        Date: 6/14/00    Time: 7:00p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * I am trying to improve the comments on this class. In the future lot of
 * the methods on this class will be documented. 
 * 
 * *****************  Version 69  *****************
 * User: Sshafi       Date: 6/14/00    Time: 12:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 68  *****************
 * User: Apota        Date: 6/12/00    Time: 11:44a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 66  *****************
 * User: Apota        Date: 6/12/00    Time: 8:03a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 65  *****************
 * User: Apota        Date: 6/09/00    Time: 12:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 64  *****************
 * User: Apota        Date: 6/09/00    Time: 9:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 63  *****************
 * User: Apota        Date: 6/09/00    Time: 2:07a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 62  *****************
 * User: Apota        Date: 6/07/00    Time: 8:01a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 60  *****************
 * User: Apota        Date: 6/07/00    Time: 4:27a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 59  *****************
 * User: Apota        Date: 6/04/00    Time: 2:32a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 58  *****************
 * User: Apota        Date: 6/02/00    Time: 5:07p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 56  *****************
 * User: Apota        Date: 6/02/00    Time: 2:31a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 55  *****************
 * User: Apota        Date: 6/01/00    Time: 8:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 54  *****************
 * User: Apota        Date: 5/31/00    Time: 8:57a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 53  *****************
 * User: Apota        Date: 5/26/00    Time: 3:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 51  *****************
 * User: Apota        Date: 5/26/00    Time: 8:20a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 50  *****************
 * User: Apota        Date: 5/25/00    Time: 8:19a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 49  *****************
 * User: Apota        Date: 5/24/00    Time: 5:13p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 48  *****************
 * User: Apota        Date: 5/24/00    Time: 12:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 47  *****************
 * User: Apota        Date: 5/22/00    Time: 3:33p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 46  *****************
 * User: Apota        Date: 5/22/00    Time: 11:01a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 45  *****************
 * User: Apota        Date: 5/22/00    Time: 8:41a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 44  *****************
 * User: Apota        Date: 5/18/00    Time: 3:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 42  *****************
 * User: Apota        Date: 5/18/00    Time: 1:28p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 39  *****************
 * User: Apota        Date: 5/17/00    Time: 4:42p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 36  *****************
 * User: Apota        Date: 5/15/00    Time: 4:00p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 33  *****************
 * User: Apota        Date: 5/13/00    Time: 4:07p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 31  *****************
 * User: Apota        Date: 5/12/00    Time: 2:04p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 30  *****************
 * User: Apota        Date: 5/12/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 29  *****************
 * User: Apota        Date: 5/11/00    Time: 9:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 28  *****************
 * User: Apota        Date: 5/09/00    Time: 11:24a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 27  *****************
 * User: Eroyal       Date: 5/08/00    Time: 12:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 26  *****************
 * User: Apota        Date: 5/08/00    Time: 11:38a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 25  *****************
 * User: Apota        Date: 5/05/00    Time: 12:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 23  *****************
 * User: Apota        Date: 5/05/00    Time: 9:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 22  *****************
 * User: Apota        Date: 4/28/00    Time: 1:45p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 21  *****************
 * User: Apota        Date: 4/27/00    Time: 5:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 18  *****************
 * User: Apota        Date: 4/21/00    Time: 2:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 17  *****************
 * User: Eroyal       Date: 4/21/00    Time: 11:12a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 16  *****************
 * User: Apota        Date: 4/21/00    Time: 11:10a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 15  *****************
 * User: Apota        Date: 4/20/00    Time: 10:59a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 9  *****************
 * User: Apota        Date: 4/14/00    Time: 4:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 8  *****************
 * User: Apota        Date: 4/11/00    Time: 1:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 6  *****************
 * User: Apota        Date: 4/07/00    Time: 12:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 5  *****************
 * User: Apota        Date: 4/01/00    Time: 4:14p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 4  *****************
 * User: Apota        Date: 3/27/00    Time: 5:15p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 3  *****************
 * User: Apota        Date: 3/21/00    Time: 5:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 14  *****************
 * User: Alok    Date: 3/15/00    Time: 2:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/bmr/view
 * Implementation
 *  
 * *****************  Version 13  *****************
 * User: Bmakowski    Date: 3/10/00    Time: 2:21p
 * Updated in $/Unfinished code/bob/com/oculussoftware/repos/bmr/view
 * STUBBED: 
 * extends and implements were not accomplished
 * and no return's in any of the 4 methods present
 * - Mak
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 2/22/00    Time: 7:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/bmr/view
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 2/22/00    Time: 7:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/bmr/view
 * Even more.
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 2/22/00    Time: 7:13p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/bmr/view
 * More header cleaning.
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 2/22/00    Time: 7:07p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/bmr/view
 * Remove junky comments.
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 2/22/00    Time: 7:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/bmr/view
 * Using History keyword instead of Log.
 * 
*/


import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.common.process.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.ui.html.wrappers.*;
import com.oculussoftware.api.ui.html.*;
//import com.oculussoftware.api.repi.view.*;


import com.oculussoftware.repos.bmr.*;
import com.oculussoftware.util.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.system.*;
import com.oculussoftware.rdb.*;

import java.util.*;
import java.sql.*;

/**
*  Implements IREntryForm.
*/
public class BMEntryForm  extends BMModelElement  implements IREntryForm // STUBBED MAK COMPILE 
{

   long _defID;
   long _sdefID;
   long _foldID;   
   long _qaclslong;
   IIID _accessID = null;
   long _pcID;
   long _ownerid; 
   boolean _conduitaccess = true;
   String _help =null;
   java.sql.Timestamp _creationdate=null; 
   IRClass _def=null;   
   IRClass _sdef=null;   
   ILock _lock=null;
   ConfigKind configkind;   
   boolean _bypass = false;
   String entity =null;
   
   //Used for setting permissions at create time. Instead of putting permissions on a different screen
   //They are clubbed with the form creation. These permissions can always be edited later using Jims servlet.
   long[] permarr = null;
   
   
   
  public static final String TABLE_NAME="ENTRYFORM";  
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";    
  public static final String COL_DELETEKIND="DELETESTATE";
  public static final String COL_CONFIGKIND="CONFIGUREKIND";
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="SHORTDESCRIPTION";  
  public static final String COL_DEF="FORMCLASSID";  
  public static final String COL_DEF2="SCNDCLASSID";  
  public static final String COL_CONTEXT="CONTEXTOBJECTID";  
  public static final String COL_PRODLIST="PRODLISTID";  
  public static final String COL_HELPTEXT="USERHELPTEXT";  
  public static final String NULL="-not specified-";  
  public static final String SPACE=" ";  
  

  /**
  Construtor for creating new Attributes. Pass it the IRObjectContext  
  */
  
  public BMEntryForm() throws OculusException
  {
  
  guid = new GUID();  
  }
 
  /***  
  Public setter
  **/  
  public void setAccessor(IIID id) { _accessID = id;}
  public IPersistable setIID(IIID id)
  {
    iid = id;    
    return this;
  }
  //Set the FORMCLASS
  public IREntryForm setFormClass(IRClass id) throws OculusException
    
  {        
   _def = id;
   _defID = _def.getIID().getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  //Set the FORMCLASS (Overload)
  public IREntryForm setFormClass(IIID id) throws OculusException
    
  {           
   _defID = id.getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  
  public IREntryForm setProductContext(IRProductContext att) throws OculusException
  {        
    _pcID = att.getIID().getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;   
  }
  //Flag the entryform to automatically insert all dependent data.
  public IREntryForm isByPass() throws OculusException    
  {        
   _bypass = true;
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  //Set the SCNDFORMCLASS 
  public IREntryForm setFormSecondClass(IRClass id) throws OculusException
    
  {        
   _sdef = id;
   _sdefID = _sdef.getIID().getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  //Set the SCNDFORMCLASS (overload)
  public IREntryForm setFormSecondClass(IIID id) throws OculusException    
  {           
   _sdefID = id.getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  
  //Flag from servlet indicating what entity the entryform represents
   public IREntryForm setEntity(String s) throws OculusException
    
  {        
   entity = s;
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
 
 //Flag from servlet indicating what entity the entryform represents
   public IREntryForm setHelp(String s) throws OculusException
    
  {        
   _help = s;
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
 
  
  /*
  Used by inputforms serves as a context for input entry forms. Used currently
  (Build 21 by Q & A and Win/Loss)
  */
  public IREntryForm setFolder(IFolder fo) throws OculusException    
  {          
   _foldID = fo.getIID().getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  
  /*
  Do not show form on Conduit based on the bit field
  */
  public IREntryForm isConduit(boolean bit) throws OculusException    
  {          
    _conduitaccess = bit;
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  /*
  Used by inputforms serves as a context for input entry forms. Used currently
  (Build 21 by Q & A and Win/Loss)
  */  
  public IREntryForm setQAClass(IRClass cls) throws OculusException    
  {           
   _qaclslong = cls.getIID().getLongValue();
    if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  
  //Set permissions on form. Permissions on form use are department specific.
  public IREntryForm setPermissions(long[] list) throws OculusException    
  {        
   
   permarr = list;   
   if (getPersState() == PersState.UNMODIFIED)setPersState(PersState.MODIFIED);
    return this;
  }
  
    //Set Name
      public IRElement setName(String s) throws ORIOException
  {
    if (s == null) s = " ";   
     name = s;
    if (getPersState() == PersState.UNMODIFIED) setPersState(PersState.MODIFIED);
    return this;
  }
    
    //Set Description
    public IRElement setDescription(String s) throws ORIOException
    {
      if (s == null || s.equals("") || s.equals("null") || s.startsWith(" "))
          s = "-not specified-";
       description = s;
      if (getPersState() == PersState.UNMODIFIED) setPersState(PersState.MODIFIED);
      return this;
    }

    //Set isActive 
    public IRModelElement isActive(boolean id) throws ORIOException
    {
      isactive = id;
      if (getPersState() == PersState.UNMODIFIED && !isactive) setPersState(PersState.DELETED);
      return this;
    }

    //Set delete state.
    public IRModelElement setDeleteState(DeleteState id) throws ORIOException
    {
      _deletestate = id;
      if (getPersState() == PersState.UNMODIFIED) setPersState(PersState.MODIFIED);
      return this;
    }
    
    //Set config kind
    public IRModelElement setConfigKind(ConfigKind id) throws ORIOException
    {
      configkind = id;
      if (getPersState() == PersState.UNMODIFIED) setPersState(PersState.MODIFIED);
      return this;
    }

 
  /******
  Private setters (Used purely for the reasons that variables are handled thru methods
  ***/
  private void _setIID(IIID i) { iid = i;}
  private void _setGUID(IGUID i) { guid = i;}
  private void _setName(String s){name = s;}  
  private void _setDescription(String s){description = s;}
  private void _setDeleteState(DeleteState j){_deletestate = j;}  
  private void _setConfigKind(ConfigKind j){configkind = j;}  
  private void _isActive(boolean bln){isactive = bln;}
  //Main load   
  protected void load(IDataSet rs) throws ORIOException
  {
    
    setPersState(PersState.PARTIAL);
    try    
    {
         _setGUID(new GUID(rs.getString(COL_BYTEGUID).trim()));
          _setIID(getIID());
          _setName(rs.getString(COL_NAME).trim());
          _setDescription(rs.getString(COL_DESC).trim());          
          _help = rs.getString("USERHELPTEXT");          
          _setDeleteState(DeleteState.getInstance(rs.getInt(COL_DELETEKIND)));          
          _setConfigKind(ConfigKind.getInstance(rs.getInt(COL_CONFIGKIND)));          
           _isActive(rs.getBoolean(COL_ISACTIVE));             
           _defID = rs.getLong(COL_DEF);
           _sdefID = rs.getLong(COL_DEF2);           
           _creationdate = rs.getTimestamp("CREATIONDATE");           
           _ownerid = rs.getLong("OWNERID");                                            
           _conduitaccess = rs.getBoolean("ISPUBLICACCESS");                                            
           if (COL_CONTEXT != null)
            {
             _foldID = rs.getLong(COL_CONTEXT);                                              
            }
           if (COL_PRODLIST != null)
            {
             _pcID = rs.getLong(COL_PRODLIST);                                              
            }
                                   
    }
    
    catch(Exception ex) { throw new ORIOException(ex);}
  }
  
  /***
  Public getters  
  **/
  public IIID getAccessor() { return _accessID;}  
  public String getHelp() { return _help;}  
  public IRClass getFormClass() throws OculusException    
  {    

    IIID id1 = new SequentialIID(_defID);
    return (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",id1);            
  }  
  
  public IFolder getFolder() throws OculusException    
  {    
    if (_foldID == 0) _loadFolder();
    IIID id1 = new SequentialIID(_foldID);
    return (IFolder)getObjectContext().getCRM().getCompObject(getObjectContext(),"Folder",id1);            
  } 
  
  
  public IRProductContext getProductContext() throws OculusException    
  {    
    if (_pcID == 0) _loadContext();
    if (_pcID != 0)
    {    
      IIID id1 = new SequentialIID(_pcID);
      return (IRProductContext)getObjectContext().getCRM().getCompObject(getObjectContext(),"ProductContext",id1);            
    }
    else
      return (IRProductContext) null;
  } 
    
    protected void _loadFolder() throws OculusException 
  {
    
        
        IRConnection jdtC = null;
        IDataSet rs = null;
        IQueryProcessor qp = null;       
        Tuple tup = null;       
        try
        {
             jdtC = getDatabaseConnection();
             qp = jdtC.createProcessor();   
             rs = qp.retrieve("SELECT CONTEXTOBJECTID FROM FORMCONTEXT WHERE FORMID="+getIID());
             if (rs.next())
              _foldID = rs.getLong("CONTEXTOBJECTID");
        }
                
        finally{
//        returnDatabaseConnection(jdtC);
        qp.close();
        }          
   }
   
   
   protected void _loadContext() throws OculusException 
  {
    
        
        IRConnection jdtC = null;
        IDataSet rs = null;
        IQueryProcessor qp = null;       
        Tuple tup = null;       
        try
        {
             jdtC = getDatabaseConnection();
             qp = jdtC.createProcessor();   
             rs = qp.retrieve("SELECT PRODLISTID FROM IFCPRODLISTUSAGE WHERE INTERFACEID="+getFormClass().getDefinition().getIID());
             if (rs.next())
              _pcID = rs.getLong("PRODLISTID");
        }
                
        finally
        {
//        returnDatabaseConnection(jdtC);
        qp.close();
        }
   }
    
      
     
  public IRClass getFormSecondClass() throws OculusException   
  {    
    IIID id1 = new SequentialIID(_sdefID);
    return (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",id1);            
  }
  
   public boolean isActive() throws ORIOException
  {
    
    return isactive;
  }
 
  public boolean isConduit() throws ORIOException
  {    
    return _conduitaccess;
  }
 
 public String getDescription() throws ORIOException
  {
    return description;
  }
  
   public DeleteState getDeleteState() throws ORIOException
  {
    return _deletestate;
  }

   public ConfigKind getConfigKind() throws ORIOException
  {
    return configkind;
  }

  public java.sql.Timestamp getCreationDate() throws OculusException 
  {
    return _creationdate; 
  }
  
  public IUser getCreator() throws OculusException 
  {
    return (IUser)context.getCRM().getCompObject(context,"User",new SequentialIID(_ownerid));                    
  }
  
  //DEPRECATED 
   
  /***
  Private getters
  **/

 private String _getName() throws OculusException
  {    
    return name;
  }
 
 private String _getDescription() throws OculusException
  {    
    return description;
  }
 
 private int _isActive() throws OculusException
  {    
  if (isactive) return 1;
    else return 0;
    
  }
 
 private DeleteState _getDeleteState() throws OculusException
  {    
    return _deletestate;
  }

  /******************************
 Public database access
 *******************************/  

 public IPersistable load() throws OculusException
  
  {
    IRConnection jdtC = null;
    
    try {
      jdtC = getDatabaseConnection();
      _sqlSelectObject(jdtC);
      setPersState(PersState.UNMODIFIED);
  
    }
    catch (Exception ex) {
      throw new ORIOException(ex);
    }
      
//    finally{ returnDatabaseConnection(jdtC);}
    return this;
  }


  public IPersistable save( ) throws ORIOException
 
  {
    IRConnection jdtC = null;  
    try 
      {      
      jdtC = getDatabaseConnection();
      if(getPersState().equals(PersState.NEW)) {                   
        _sqlInsertObject(jdtC);              
        setPersState(PersState.UNMODIFIED);    
      }
      else if (getPersState().equals(PersState.MODIFIED)) 
        {
        _sqlUpdateObject(jdtC);        
         setPersState(PersState.UNMODIFIED);    
        }
      else if (getPersState().equals(PersState.DELETED)) _sqlWipeObject(jdtC);              
      }
    
    catch (Exception ex) {
      throw new ORIOException(ex);
    }
    
  
    return this;
  }

  public IPersistable delete( ) throws ORIOException    
  {
    setPersState(PersState.DELETED);
    return this;
  }


  public IPersistable softdelete( ) throws ORIOException    
  {
    setPersState(PersState.MODIFIED);
    _deletestate = DeleteState.DELETED;
    isactive = false;
    return this;
  }
  
  public IPersistable recover( ) throws ORIOException    
  {
    setPersState(PersState.MODIFIED);
    _deletestate = DeleteState.NOT_DELETED;
    isactive = true;
    return this;
  }

 
 /******************************
   Protected database access
 
  Execute a SQL Insert
  ********************/
  
  protected void _sqlInsertObject2(IRConnection jdtC)
    throws SQLException, ORIOException
  {
  
  
  IQueryProcessor psp = null;  
  try
  {
    psp = jdtC.createProcessor(); 
    psp.setSingleton(false);            
    long rootlong=-1;              
    long rootlong2=-1; 
    long sinattlong=-1; 
    IIID typeiid = null;
    IIID clssiid = null;
    IIID clss2iid = null;    
    IIID type2iid = null;
    IIID rootiid = null;
    IIID rootiid2 = null;
                 
    if (entity.equals("feature")) {
      rootlong = IDCONST.IFEATURE.getLongValue();
      rootiid = IDCONST.IFEATURE.getIIDValue();
      rootlong2 = IDCONST.IFEATURECATEGORYLINK.getLongValue();
      rootiid2 = IDCONST.IFEATURECATEGORYLINK.getIIDValue();
    }
    
    if (entity.equals("version")) {
      rootlong = IDCONST.IPRODUCTVERSION.getLongValue();
      rootiid = IDCONST.IPRODUCTVERSION.getIIDValue();
      rootiid2 = rootiid;
      rootlong2 = rootlong;
    }
    if (entity.equals("category")) {
      rootlong = IDCONST.ICATEGORY.getLongValue();
      rootiid = IDCONST.ICATEGORY.getIIDValue();
      rootiid2 = rootiid;
      rootlong2 = rootlong;
    }
    if (entity.equals("product")) {
      rootlong = IDCONST.IPRODUCT.getLongValue();
      rootiid = IDCONST.IPRODUCT.getIIDValue();
      rootiid2 = rootiid;
      
      rootlong2 = rootlong;
    }
    if (entity.equals("stdinput")) {
      rootlong = IDCONST.ISTANDARDINPUT.getLongValue();
      rootiid = IDCONST.ISTANDARDINPUT.getIIDValue();
      rootiid2 = rootiid;
      
      rootlong2 = rootlong;
    }
    if (entity.equals("artinput")) {
      rootlong = IDCONST.IARTICLEINPUT.getLongValue();
      rootiid = IDCONST.IARTICLEINPUT.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
    if (entity.equals("qainput")) {
      rootlong = IDCONST.IQUESTIONINPUT.getLongValue();
      rootiid = IDCONST.IQUESTIONINPUT.getIIDValue();
      rootiid2 = rootiid;
      rootlong2 = rootlong;
    }
    if (entity.equals("winlossinput")) {
      rootlong = IDCONST.IWINLOSSINPUT.getLongValue();
      rootiid = IDCONST.IWINLOSSINPUT.getIIDValue();
      rootiid2 = rootiid;
      rootlong2 = rootlong;
    }
    if (entity.equals("summaryinput")) {
      rootlong = IDCONST.ISUMMARYINPUT.getLongValue();
      rootiid = IDCONST.ISUMMARYINPUT.getIIDValue();
      rootiid2 = rootiid;
      rootlong2 = rootlong;
    }
      
    if (entity.equals("alternative")) {
      rootlong = IDCONST.IALTERNATIVE.getLongValue();
      rootiid = IDCONST.IALTERNATIVE.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
    if (entity.equals("contact")) {
      rootlong = IDCONST.IUSER.getLongValue();
      rootiid = IDCONST.IUSER.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
      
    if (entity.equals("org")) {
      rootlong = IDCONST.IORGANIZATION.getLongValue();
      rootiid = IDCONST.IORGANIZATION.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
    
    if (entity.equals("problemstmt")) {
      rootlong = IDCONST.IPROBLEMSTATEMENT.getLongValue();
      rootiid = IDCONST.IPROBLEMSTATEMENT.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
    
    if (entity.equals("reaction")) {
      rootlong = IDCONST.IREACTION.getLongValue();
      rootiid = IDCONST.IREACTION.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
    
    if (entity.equals("importedinput")) {
      rootlong = IDCONST.IIMPORTEDINPUT.getLongValue();
      rootiid = IDCONST.IIMPORTEDINPUT.getIIDValue();
      rootiid2 = rootiid;      
      rootlong2 = rootlong;
    }
      
      
    typeiid = getObjectContext().getRepository().genReposID();
    psp.update("INSERT INTO INTERFACE (OBJECTID,GUID,ISROOT,ISLEAF,ISACTIVE, DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION) VALUES ("      
      + typeiid.getLongValue()+",'"
      + genGUID().toString()+"',1,0,1,1,5,'"
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"')"
        );  
        
    psp.update("INSERT INTO EXTENDSASC (SRCINTERFACEID,DESTINTERFACEID) VALUES ("      
      + typeiid.getLongValue()+","
      +rootlong+")"
          );  
        
    
  
    clssiid = getObjectContext().getRepository().genReposID();
    _defID = clssiid.getLongValue();
    _sdefID = _defID;
    psp.update("INSERT INTO CLASS (OBJECTID,DEFINTERFACEID,GUID,ISROOT,ISLEAF,ISACTIVE, DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION) VALUES ("      
      + clssiid.getLongValue()+","
      + typeiid.getLongValue()+",'"
      + genGUID().toString()+"',1,0,1,1,5,'"
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"')"
        );  
    
   if (!entity.equals("feature"))  
    {
    _setGUID(guid);
    _isActive(true); 
    _setDeleteState(DeleteState.NOT_DELETED);
    
    _ownerid =  getObjectContext().getConnection().getUserIID().getLongValue();
    //_ownerid = 0;
    _creationdate  = new Timestamp(System.currentTimeMillis());    
    IIID frmiid = getObjectContext().getRepository().genReposID();
    long scndlong = -1;
    scndlong = clssiid.getLongValue();
    if(configkind.getIntValue() == ConfigKind.SUMMARY_ONLY.getIntValue())
      scndlong = _qaclslong;
    if (_help == null) _help = "-not specified-";  
    psp.update("INSERT INTO ENTRYFORM (OBJECTID,GUID,FORMCLASSID,SCNDCLASSID,ISACTIVE, ISPUBLICACCESS,DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION,OWNERID,USERHELPTEXT,CREATIONDATE) VALUES ("      
      + frmiid.getLongValue()+",'"
      + genGUID().toString()+"',"
      +clssiid.getLongValue()+","  
      +scndlong+","    
      +"1,1,"          
      + DeleteState.NOT_DELETED.getIntValue()+","      
      + configkind.getIntValue()+",'"        
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"',"
      +_ownerid+",'"
      +SQLUtil.primer(_help,2000)+"','"  
      +_creationdate.toString()+"')"                
        ); 
        
    if(entity.equals("qainput") ||  entity.equals("summaryinput"))
      psp.update("INSERT INTO FORMCONTEXT (FORMID,CONTEXTOBJECTID) VALUES ("
         +frmiid.getLongValue()+","+_foldID+")");
        
    
    if (permarr != null)    
      {
        
        int permsize = permarr.length;
        for(int k = 0; k < permsize; ++k)
        {
          psp.update("INSERT INTO PERMISSIONGRANT (PAROBJECTID,PERMISSIONID,ACCESSORID) VALUES ("      
         +frmiid.getLongValue()+",65,"      
         +permarr[k]+")"
           ); 
        }
      }
      
    }
    
    if (entity.equals("feature"))  
    {
    
    type2iid = getObjectContext().getRepository().genReposID();
    psp.update("INSERT INTO INTERFACE (OBJECTID,GUID,ISROOT,ISLEAF,ISACTIVE, DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION) VALUES ("      
      + type2iid.getLongValue()+",'"
      + genGUID().toString()+"',1,0,1,1,5,'"
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"')"
        );  
    
    psp.update("INSERT INTO EXTENDSASC (SRCINTERFACEID,DESTINTERFACEID) VALUES ("      
      + type2iid.getLongValue()+","
      +rootlong2+")"
          );  
     
      clss2iid = getObjectContext().getRepository().genReposID();
      _sdefID = clss2iid.getLongValue();
    psp.update("INSERT INTO CLASS (OBJECTID,DEFINTERFACEID,GUID,ISROOT,ISLEAF,ISACTIVE, DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION) VALUES ("      
      + clss2iid.getLongValue()+","
      + type2iid.getLongValue()+",'"
      + genGUID().toString()+"',1,0,1,1,5,'"
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"')"
        );  

    _setGUID(guid);
    _isActive(true); 
    _setDeleteState(DeleteState.NOT_DELETED);
    _ownerid =  getObjectContext().getConnection().getUserIID().getLongValue();
    _creationdate = new Timestamp(System.currentTimeMillis());
    psp.update("INSERT INTO ENTRYFORM (OBJECTID,GUID,FORMCLASSID,SCNDCLASSID,ISACTIVE, DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION, OWNERID, CREATIONDATE) VALUES ("      
      + getIID().getLongValue()+",'"
      + getGUID().toString()+"',"
      +clssiid.getLongValue()+","  
      +clss2iid.getLongValue()+","    
      +"1,"          
      + DeleteState.NOT_DELETED.getIntValue()+","      
      + configkind.getIntValue()+",'"        
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"',"
      +_ownerid+",'"
      +_creationdate.toString()+"')"        
        );  
      
      
    }
    
      if (!entity.equals("feature") && !entity.equals("qainput") && !entity.equals("winlossinput")&& !entity.equals("summaryinput"))
    {
      IRType type = (IRType)getObjectContext().getCRM().getCompObject(getObjectContext(),"Interface",new SequentialIID(rootlong));
      List list = type.getAttributeList();
      int size = list.size();
      for(int i =0; i < size; ++i)
      {
        long attlong = ((Tuple)list.get(i)).getEndIndex();
        int reqint = ((Tuple)list.get(i)).isRequired()?1:0;
        int order = i+1;
        psp.update("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM,CONFIGUREKIND) VALUES ("
         +clssiid.getLongValue()+","+attlong+","+IDCONST.DEFAULT_ATTR_GROUP.getLongValue()+","+order+",5)");
        
        psp.update("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES ("
         +typeiid.getLongValue()+","+attlong+","+reqint+")");  
      }
      
    }
    
      
      
      if (entity.equals("feature"))
      {
        
      IRType type = (IRType)CRM.getInstance().getCompObject(getObjectContext(),"Interface",rootiid);
      List list2 = type.getAttributeList(rootiid2);
      int size2 = list2.size();      
      for(int i =0; i < size2; ++i)
      {        
        long intlong = ((Tuple)list2.get(i)).getStartIndex();
        long attlong = ((Tuple)list2.get(i)).getEndIndex();
        long clzzlong = ((Tuple)list2.get(i)).getClazz();
        int reqint = ((Tuple)list2.get(i)).isRequired()?1:0;        
        int order = i+1;
        long classlong = -1;
        long interlong = -1;
        if (intlong == rootlong) 
        {
          classlong = clssiid.getLongValue();
          interlong = typeiid.getLongValue();
        }
        else
        {          
          classlong = clss2iid.getLongValue();
          interlong = type2iid.getLongValue();
        }
        
        psp.update("INSERT INTO CLASSATTRGROUPASC (CLASSID,ATTRIBUTEID,ATTRGROUPID,ORDERNUM,CONFIGUREKIND) VALUES ("
         +classlong+","+attlong+","+IDCONST.DEFAULT_ATTR_GROUP.getLongValue()+","+order+",5)");
        
        psp.update("INSERT INTO INTERFACEATTRASC (INTERFACEID,ATTRIBUTEID,ISREQUIRED) VALUES ("
         +interlong+","+attlong+","+reqint+")");        
      }        
    }
      //Product context list for std input & artinput
      
        if (entity.equals("stdinput")||entity.equals("artinput") || entity.equals("winlossinput"))
    {
        
        psp.update("INSERT INTO IFCPRODLISTUSAGE (INTERFACEID,PRODLISTID) VALUES ("
         +typeiid.getLongValue()+","+_pcID+")");
      
    }
      
   
  }
  catch(Exception ex) { throw new ORIOException(ex);}
  finally { psp.close();}
  
  
  }
  
  protected void _sqlInsertObject(IRConnection jdtC)
    throws SQLException, ORIOException
  {
  
  if (_bypass) _sqlInsertObject2(jdtC);
  else
    {
  IQueryProcessor psp = null;  
  try
  {
    psp = jdtC.createProcessor();              
    _setGUID(guid);
    _isActive(true); 
    _setDeleteState(DeleteState.NOT_DELETED);
    Timestamp tim = new Timestamp(System.currentTimeMillis());
    long _ownerid =  getObjectContext().getConnection().getUserIID().getLongValue();    
    psp.update("INSERT INTO ENTRYFORM (OBJECTID,GUID,FORMCLASSID,SCNDCLASSID,ISACTIVE, ISPUBLICACCESS,DELETESTATE,CONFIGUREKIND,NAME,SHORTDESCRIPTION, CREATIONDATE) VALUES ("      
      + getIID().getLongValue()+",'"
      + getGUID().toString()+"',"
      +_defID+","  
      +_sdefID+","    
      +"1,1,"          
      + DeleteState.NOT_DELETED.getIntValue()+","      
      + configkind.getIntValue()+",'"        
      + SQLUtil.primer(_getName(),250)+"','"
      + SQLUtil.primer(_getDescription(),250)+"','"            
      +tim.toString()+"')"        
        );  
      
    tim = null;    
          
  }
    catch(Exception ex) { throw new ORIOException(ex);}
  finally { psp.close();}
  
    }
  }
  
  
  /*******************  
  Execute a SQL Update
  ********************/
  
  
  protected void _sqlUpdateObject(IRConnection jdtC)
    throws SQLException, ORIOException
  {
    
  IQueryProcessor psp = null;  
  
  
  try
  {
    int j =0;
    if(isactive) j =1;
    int kkk =0;
    if(isConduit()) kkk =1;
    
    psp = jdtC.createProcessor();    
    psp.setSingleton(false);
      psp.update("UPDATE ENTRYFORM SET "       
      + COL_NAME+"='"+SQLUtil.primer(_getName(),250)+"',"        
      + COL_DESC+"='"+SQLUtil.primer(_getDescription(),250)+"',"        
      + "USERHELPTEXT='"+SQLUtil.primer(_help,2000)+"',"        
      + "ISACTIVE="+j+","        
      + "ISPUBLICACCESS="+kkk+","          
      + "SCNDCLASSID="+_sdefID+","          
      + "DELETESTATE="+_deletestate.getIntValue()  
      
      + " WHERE "
      + COL_OBJECTID+"="+getIID().getLongValue()        
        );
        
    
      psp.update("UPDATE FORMCONTEXT SET "       
      + " CONTEXTOBJECTID="+_foldID        
      + " WHERE "
      + " FORMID="+getIID().getLongValue()        
        );    
        
       psp.update("UPDATE IFCPRODLISTUSAGE SET "       
      + " PRODLISTID="+_pcID        
      + " WHERE "
      + " INTERFACEID="+getFormClass().getDefinition().getIID()
        );      

   if (permarr != null)
    {
       //Flush & fill
       psp.update("DELETE FROM PERMISSIONGRANT WHERE PAROBJECTID="+getIID());
      int permsize = permarr.length;
        for(int k = 0; k < permsize; ++k)
        {
        psp.update("INSERT INTO PERMISSIONGRANT (PAROBJECTID,PERMISSIONID,ACCESSORID) VALUES ("      
      + getIID()+",65,"      
       +permarr[k]+")"
        ); 
        }
    }
  }  
      catch(Exception ex) { throw new ORIOException(ex);}
    finally { psp.close();}
  }
    
  
  
  protected void _sqlWipeObject(IRConnection jdtC)
    throws SQLException, ORIOException
  {

  IQueryProcessor psp = null;  
  boolean success = true;
  try
  {
    
      //long rootlong = getFormClass().getDefinition().getIID().getLongValue();
      //long rootlong2 = getFormSecondClass().getDefinition().getIID().getLongValue();
      
        psp = jdtC.createProcessor();                     
        psp.setSingleton(false);
        psp.update(
        "DELETE FROM FORMCONTEXT WHERE FORMID="+getIID()
         );       
        psp.update(
        "DELETE FROM PERMISSIONGRANT WHERE PAROBJECTID="+getIID()
         );       
        psp.update(
        "DELETE FROM ENTRYFORM WHERE OBJECTID="+getIID()
         );
          
  }
   catch(Exception ex) {success = false; throw new ORIOException(ex); }
  finally {psp.close(); }

  }
  
  
  /*******************  
  Execute a SQL Select  
  ********************/
  
  protected void _sqlSelectObject(IRConnection jdtC)
    throws SQLException, ORIOException
  {    
    String query = null;    
    IDataSet rs = null;
    IDataSet rs1 = null;
    IQueryProcessor qp = null;    
    try 
    {         
         qp = jdtC.createProcessor();         
         query = "SELECT ent.*,ctxt.CONTEXTOBJECTID, ifc.PRODLISTID "+
           " FROM ENTRYFORM ent LEFT OUTER JOIN FORMCONTEXT ctxt ON ctxt.FORMID=ent.OBJECTID "+
           "  LEFT OUTER JOIN CLASS cls ON cls.OBJECTID=ent.FORMCLASSID "+ 
           "  LEFT OUTER JOIN IFCPRODLISTUSAGE ifc ON ifc.INTERFACEID=cls.DEFINTERFACEID WHERE ent.OBJECTID="+getIID()+" ORDER BY ent.NAME";
         rs = qp.retrieve(query);
         if (rs.next()) load(rs);                  
    }
    catch(Exception ex) { throw new ORIOException(ex);}
    finally { qp.close();}      
  }

  public ILock getLock() { return _lock;}
  
  ////////////////////////////////////////////////////////////    
	//
	//
	//    METHODS THAT RETURN A MAP OF MODEL ELEMENTS (ATTRIBUTES ONLY)
	//
	////////////////////////////////////////////////////////////    

 public IRPropertyMap getAttributes(IRObject obj) throws OculusException
  {
    return getFormClass().getAttributes(obj);    
  }   
 
  ////////////////////////////////////////////////////////////    
	//
	//
	//    METHODS THAT MODIFY CLASSATTRGROPASC
	//
	////////////////////////////////////////////////////////////    

 	/***
  *  Add an attribute group/attribute to the CLASSATTRGROUPASC
 	**/  
 	/***
  *  Add an attribute group/attribute to the CLASSATTRGROUPASC
 	**/  
  public void addAttribute(IRAttribute atg) throws OculusException
  {    
   IRAttrAccessGroup grp = (IRAttrAccessGroup)context.getCRM().getCompObject(context,"AttributeGroup",IDCONST.DEFAULT_ATTR_GROUP.getIIDValue());
   addAttributeGroup(grp, atg);     
  }
	public void addAttributeGroup(IRAttrAccessGroup cls, IRAttribute atg) throws OculusException
  {
    getFormClass().addAttributeGroup(cls,atg);
  }

 /***
  *  Is attribute duplicate for the class
 **/    
  public boolean isAttributeDuplicate(IRAttribute att)throws OculusException
  {
    return getFormClass().isAttributeDuplicate(att);
  }


 	///////////////////////////////////////////////////////////////////////////
	public List getGroupsAndAttributes() throws OculusException 
  {
       List list = null;      
      if(_defID != _sdefID)
    {        
        IRConnection jdtC = null;
        IDataSet rs = null;
        IQueryProcessor qp = null;       
        Tuple tup = null;       
        try
        {
             jdtC = getDatabaseConnection();
             qp = jdtC.createProcessor();            
             StringBuffer sbf = new StringBuffer();
             sbf.append("SELECT assoc.ISREQUIRED as isReq, casc.CLASSID as clssID,att.NAME as attName,grp.NAME as grpName,casc.ATTRIBUTEID as attID,casc.ATTRGROUPID as grpID,casc.CONFIGUREKIND as ck ");
             sbf.append(" FROM CLASSATTRGROUPASC casc LEFT OUTER JOIN ATTRIBUTE att ON att.OBJECTID=casc.ATTRIBUTEID ");
             sbf.append(" LEFT OUTER JOIN ATTRACCESSGROUP grp ON grp.OBJECTID=casc.ATTRGROUPID ");                                    
             sbf.append(" LEFT OUTER JOIN INTERFACEATTRASC assoc ON assoc.ATTRIBUTEID=att.OBJECTID ");                  
             sbf.append(" WHERE ");
             sbf.append("(casc.CLASSID="+_defID+" AND assoc.INTERFACEID="+getFormClass().getDefinition().getIID()+")");         
             sbf.append(" OR ");
             sbf.append("(casc.CLASSID="+_sdefID+" AND assoc.INTERFACEID="+getFormSecondClass().getDefinition().getIID()+")");         
             sbf.append(" ORDER BY casc.ORDERNUM ");
             rs = qp.retrieve(sbf.toString());
             list = new ArrayList();
                 while(rs.next())
              {
                 long l1 = rs.getLong("attID");
                 long l2 = rs.getLong("grpID");
                 long l3 = rs.getLong("clssID");
                 String s1 = rs.getString("attName");
                 String s2 = rs.getString("grpName");
                 int ck = rs.getInt("ck");
                 tup = new Tuple(l1,l2,s1,s2);
                 tup.setConfigKind(ck);
                 tup.setClazz(l3);
                 tup.isRequired(rs.getBoolean("isReq"));
                 list.add(tup);         
              }     
              tup = null;
        }
        catch(Exception ex) { throw new ORIOException(ex);}
        finally 
        {
            qp.close();
//            try {returnDatabaseConnection(jdtC);}
//            catch(Exception ex) { throw new ORIOException(ex);}
        }
    }
    else
      list = getFormClass().getGroupsAndAttributes();
    return list;   
  }



	///////////////////////////////////////////////////////////////////////////
	public void addBatchGroupsAndAttributes(List list) throws OculusException
  {
    
    if(_defID != _sdefID)
    {
      int size = list.size();
      Tuple tup;
      IRConnection jdtC = null;
      IQueryProcessor qp = null;
      StringBuffer sbf;      
      try
    {
       jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();  
       qp.setSingleton(false);                            
       for (int i = 0; i < size; ++i)
      {
        sbf = new StringBuffer();
        tup = (Tuple)list.get(i);                
        String mode = tup.getMode();
        int order = i+1;        
        if (mode.equals("Update"))
        {
        
            sbf.append("UPDATE CLASSATTRGROUPASC SET ORDERNUM= ");
            sbf.append(order);
            sbf.append(" WHERE CLASSID= ");
            sbf.append(_defID);
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(tup.getStartIndex());
            sbf.append("UPDATE CLASSATTRGROUPASC SET ORDERNUM= ");
            sbf.append(order);
            sbf.append(" WHERE CLASSID= ");
            sbf.append(_sdefID);
            sbf.append(" AND ATTRIBUTEID= ");
            sbf.append(tup.getStartIndex());
            qp.update(sbf.toString());            
        }
        
       sbf = null; 
      }               
    }
    
    catch(Exception ex) { throw new ORIOException(ex.toString());}
    finally 
      {    
        sbf = null;
        qp.close(); 
        try
          {
            CRM.getInstance().commitTransaction(getObjectContext());          
           }
         catch(Exception ocuExp){throw new ORIOException(ocuExp.toString());}  
       }    
    }
   else 
    getFormClass().addBatchGroupsAndAttributes(list);
  }

	public void addScndBatchGroupsAndAttributes(List list) throws OculusException
  {
    getFormSecondClass().addBatchGroupsAndAttributes(list);
  }
  
 /////////////////////////////////////////////////////////////////////////// 
 	public boolean isUsed(IRAttribute att) throws OculusException
  {
    
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;

    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      String query =  " SELECT * FROM CLASSATTRGROUPASC "
                     +" WHERE CLASSID <>"+_defID
                     +" AND ATTRIBUTEID="+att.getIID()+" AND CONFIGUREKIND = "+ConfigKind.FULL.getIntValue()
                     +" UNION SELECT * FROM CLASSATTRGROUPASC "
                     +" WHERE CLASSID <>"+_sdefID
                     +" AND ATTRIBUTEID="+att.getIID()+" AND CONFIGUREKIND = "+ConfigKind.FULL.getIntValue();
      rs = qp.retrieve(query);
      yes = rs.next();
      
      //If its a numerical attribute check to see if it is used as a roll-up of an aggregate attribute
      if (!yes && (att.getPrimitive().equals(Primitive.DECIMAL)||att.getPrimitive().equals(Primitive.INTEGER)))
      {
       query = "SELECT * FROM ATTRIBUTE WHERE EXPRESSION LIKE '"+att.getIID()+"'";  
       rs = qp.retrieve(query);
       yes = rs.next();         
      }  
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }      
    return yes;

    
    
  }
   
   
   
  public  boolean isUsed() throws OculusException
  { 
    /*
    Select all attributes of a class and check to see if those attributes are used by
    other classes. For a feature form select all attributes that are both feature owned
    & version specific.    
    */
    
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;

    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      String query =  " SELECT * FROM CLASSATTRGROUPASC WHERE ATTRIBUTEID IN (SELECT ATTRIBUTEID "
                       +" FROM CLASSATTRGROUPASC WHERE CLASSID = "+_defID+") AND CLASSID <>"+_defID
                       +" AND CONFIGUREKIND = "+ConfigKind.FULL.getIntValue()
                       +" UNION SELECT * FROM CLASSATTRGROUPASC WHERE ATTRIBUTEID IN (SELECT ATTRIBUTEID "
                       +" FROM CLASSATTRGROUPASC WHERE CLASSID = "+_sdefID+") AND CLASSID <>"+_sdefID
                       +" AND CONFIGUREKIND = "+ConfigKind.FULL.getIntValue();
        rs = qp.retrieve(query);
        yes = rs.next();
        
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }      
    return yes;
    
  }
 
 
   
  public  boolean isPosted() throws OculusException
  { 
    /*
    Select all attributes of a class and check to see if those attributes are used by
    other classes. For a feature form select all attributes that are both feature owned
    & version specific.    
    */
    
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    boolean yes = false;

    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      String query = " SELECT * FROM FORMPOSTING WHERE FORMID="+getIID();
      rs = qp.retrieve(query);
      yes = rs.next();      
        
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }      
    return yes;
    
  }
  
  public IMarketInput getSummaryRecord() throws OculusException
  {
    return getSummaryRecord(false);  
  }
  
  public IMarketInput getSummaryRecord(boolean ignoreDeletedOnes) throws OculusException
  {
    
    IRConnection jdtC=null;
    IQueryProcessor qp=null;
    IDataSet rs;
    
    try  
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();
      String query = "";
      if (!ignoreDeletedOnes) 
        query =  " SELECT mi.OBJECTID FROM MARKETINPUT mi "+
        " LEFT OUTER JOIN ENTRYFORM frm ON frm.FORMCLASSID=mi.CLASSID"+
        " WHERE mi.DELETESTATE=1 AND frm.CONFIGUREKIND="+ConfigKind.SUMMARY_ONLY+ " AND frm.SCNDCLASSID="+getFormClass().getIID();  
     else
         query =  " SELECT mi.OBJECTID FROM MARKETINPUT mi "+
        " LEFT OUTER JOIN ENTRYFORM frm ON frm.FORMCLASSID=mi.CLASSID"+
        " WHERE frm.CONFIGUREKIND="+ConfigKind.SUMMARY_ONLY+ " AND frm.SCNDCLASSID="+getFormClass().getIID();    
        
      rs = qp.retrieve(query);
      if (rs.next())
        return (IMarketInput) getObjectContext().getCRM().getCompObject(getObjectContext(),"MarketInput",new SequentialIID(rs.getLong("OBJECTID")));
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }          
   
    return (IMarketInput)null;
  }
  
  
  public IRModelElementList getGlobalAttributeList() throws OculusException    
  {
    IRModelElementList list = null;       
    IRConnection jdtC = null;
    IDataSet rs = null;
    IQueryProcessor qp = null;    
     try
    {
        list = new BMAttributeList();        
        list.setObjectContext(getObjectContext());
        jdtC = getDatabaseConnection();
         qp = jdtC.createProcessor();                     
         if (!getConfigKind().equals(ConfigKind.SUMMARY_ONLY))
           rs = qp.retrieve("SELECT * FROM ATTRIBUTE LEFT OUTER JOIN CLASSATTRGROUPASC ON CLASSATTRGROUPASC.ATTRIBUTEID=ATTRIBUTE.OBJECTID WHERE CLASSID="+_defID+" OR CLASSID="+_sdefID+" ORDER BY CLASSATTRGROUPASC.ORDERNUM");  
         else
           rs = qp.retrieve("SELECT * FROM ATTRIBUTE LEFT OUTER JOIN CLASSATTRGROUPASC ON CLASSATTRGROUPASC.ATTRIBUTEID=ATTRIBUTE.OBJECTID WHERE CLASSID="+_defID+" ORDER BY CLASSATTRGROUPASC.ORDERNUM");    
         while(rs.next())
        {          
           IIID id = new SequentialIID(rs.getLong("OBJECTID"));           
           //if (!list.contains(id))
             list.add(id);
        }
    }
    catch(Exception ex) { throw new ORIOException(ex);}
    finally {
        qp.close();
//        try {returnDatabaseConnection(jdtC);}
//     catch(Exception ex) { throw new ORIOException(ex);}
    } 
  return list;  

  }
  
  public IRModelElementList getEditableAttributeList() throws OculusException
  {
		IUser user = getObjectContext().getConnection().getUserObject();
    IDataSet args = new DataSet();
    args.setIID(this.getIID());
    args.put("UserID",user.getIID());
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"EditableAttributeList",args);
  }
 
  public boolean canEditAllRequiredFields() throws OculusException
  {
    boolean all = true;

    IRConnection jdtC = null;
    IDataSet rs = null;
    IQueryProcessor qp = null;    
		IUser user = getObjectContext().getConnection().getUserObject();
    try
    {
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor();                     
      rs = qp.retrieve(
       " SELECT attrib.OBJECTID "+
	    		  " FROM CLASSATTRGROUPASC caga "+
              "LEFT OUTER JOIN ENTRYFORM form ON form.FORMCLASSID=caga.CLASSID OR form.SCNDCLASSID=caga.CLASSID "+
	    			  "LEFT OUTER JOIN ATTRIBUTE attrib ON caga.ATTRIBUTEID = attrib.OBJECTID "+
              "LEFT OUTER JOIN CLASS cls ON cls.OBJECTID=caga.CLASSID "+
              "LEFT OUTER JOIN INTERFACEATTRASC iaa ON iaa.ATTRIBUTEID=attrib.OBJECTID AND iaa.INTERFACEID=cls.DEFINTERFACEID "+
	    		  " WHERE form.OBJECTID="+getIID()+" AND attrib.ATTRKIND<>"+AttributeKind.SYSTEM_GENERATED.getIntValue()+" AND iaa.ISREQUIRED=1 "+
            "  and attrib.OBJECTID NOT IN "+   
       "( SELECT attrib.OBJECTID "+
	    		  " FROM CLASSATTRGROUPASC caga "+
              "LEFT OUTER JOIN ENTRYFORM form ON form.FORMCLASSID=caga.CLASSID OR form.SCNDCLASSID=caga.CLASSID "+
	    			  "LEFT OUTER JOIN ATTRGROUPGRANT agg ON agg.ATTRGROUPID = caga.ATTRGROUPID "+
	    			  "LEFT OUTER JOIN ATTRIBUTE attrib ON caga.ATTRIBUTEID = attrib.OBJECTID "+
              "LEFT OUTER JOIN CLASS cls ON cls.OBJECTID=caga.CLASSID "+
              "LEFT OUTER JOIN INTERFACEATTRASC iaa ON iaa.ATTRIBUTEID=attrib.OBJECTID AND iaa.INTERFACEID=cls.DEFINTERFACEID "+
	    		  " WHERE form.OBJECTID="+getIID()+" AND attrib.ATTRKIND<>"+AttributeKind.SYSTEM_GENERATED.getIntValue()+" AND "+
            "  (1="+user.getIID()+" OR (agg.OPERATIONTYPE="+AttrGroupOper.EDIT+" AND (agg.ACCESSORID="+user.getIID()+" OR (agg.ACCESSORID IN (SELECT ug.GROUPID FROM USERGROUPASC ug WHERE ug.USERID="+user.getIID()+"))))) )"
      );
      all = !rs.next();
    }
    catch(Exception ex) { throw new ORIOException(ex);}
    finally { if (qp != null) qp.close(); } 

    return all;
  }
 
  public IRModelElementList getViewableAttributeList() throws OculusException
  {
		IUser user = getObjectContext().getConnection().getUserObject();
    IDataSet args = new DataSet();
    args.setIID(this.getIID());
    args.put("UserID",user.getIID());
    return (IRModelElementList)getObjectContext().getCRM().getCompObject(getObjectContext(),"ViewableAttributeList",args);
  }
  
  public void copyAttributeListFromType(IRType type)throws OculusException
  {
  	getFormClass().copyAttributeListFromType(type); 
  } 
  

// Returns the sum of the values of the given attribute for the given object
// and all of it's children.  It returns a Float instead of float to keep the  attribute
// values consistent.
// NOTE: Attempting to use instanceof instead of getRootDefinition().
private Float _calcValue(IBusinessObject obj,IRAttribute attr)
	throws OculusException
{ 
//	IIID rootIID = obj.getDefnObject().getRootDefinition().getIID();
  StringBuffer sbf=null;
  String rollupkey = "prop"+attr.getIID();
  
	if (obj instanceof ICategory)
//  if (rootIID.equals(IDCONST.ICATEGORY.getIIDValue()))
  {
    sbf = new StringBuffer();
    _doCat((ICategory)obj,rollupkey,sbf); 
  }
  else
	if (obj instanceof IProductVersion)
//  if (rootIID.equals(IDCONST.IPRODUCTVERSION.getIIDValue()))
  {
    sbf = new StringBuffer();
    IProductVersion ver = (IProductVersion)obj;
    _doCat(ver.getDefaultCategory(),rollupkey,sbf); 
  }
  
  //Use the stringbuffer to calculate the sum now
  
  String s = sbf.substring(0,sbf.length()-1);						//chop off last ,
  sbf = null;																						//gc
  StringTokenizer st = new StringTokenizer(s,",");
  float sum = 0.0f;
  while(st.hasMoreTokens())
  {
    String t = st.nextToken();
    if (StringUtil.isValidFloat(t))
      sum += Float.parseFloat(t);
		else
			throw new ORIOException("Invalid attribute value in rollup for "+obj.getName()+","+attr.getName());	
  }
  st = null;
 	return new Float(sum);
}

	// Returns a String/StringBuffer that contains a comma-separated list of all of the values
	// for the given attribute on the given category and all of it's subcategories and features.
	// It uses a String so that it can be used to roll-up any kind of attribute, not just numeric
	// ones.
 private void _doCat(ICategory cat, String key, StringBuffer sbf) throws OculusException
 {    
   IFeatureColl featurecoll = cat.getFeatures();
   while (featurecoll.hasMoreFeatures())
   {
       IFeature feat = featurecoll.nextFeature();        
       IFeatureCategoryLink featlink = feat.getFeatureCategoryLinkObject();              
       IRPropertyMap map = feat.getProperties();
       if (map.containsKey(key))
       {
          IRProperty prop = (IRProperty)map.get(key);
          Object o = prop.getValue();
          if (o != null)
          {
             sbf.append(o.toString());
             sbf.append(",");
          }          
        }
        
				map = featlink.getProperties();
        if (map.containsKey(key))
        {
          IRProperty prop = (IRProperty)map.get(key);
          Object o = prop.getValue();
          if (o != null)
          {
             sbf.append(o.toString());
             sbf.append(",");
          }          
        }
    }

    ICategoryColl catcoll = cat.getCategories();
    while (catcoll.hasMoreCategories())
	    _doCat(catcoll.nextCategory(),key,sbf);      
  }
  



 
  public  Object dolly() throws OculusException       
  { 
    BMEntryForm state = new BMEntryForm();
    state.setIID(getIID());
    state.setObjectContext(getObjectContext());
    state.setPersState(getPersState());
    state.setName(getName());
    state.setDescription(getDescription());
    state.setDeleteState(getDeleteState());
    state.setConfigKind(getConfigKind());
    state.isActive(isActive());
    state._defID= _defID;
    state._sdefID= _sdefID;   
    state._ownerid= _ownerid;   
    state._creationdate= _creationdate;   
    state._foldID= _foldID;   
    state._help= _help;   
    state._pcID= _pcID;   
    state._conduitaccess= _conduitaccess;   
    state.permarr= permarr;   
    state.setPersState(getPersState());
    return state;
  } 
 
  
  public boolean hasInstances() throws OculusException
  {
    return getFormClass().hasInstances(); 
  } 

 
	public int countInstances() throws OculusException
  {
    return getFormClass().countInstances(); 
  } 
  
	
 
  public void removeAllAttributes() throws OculusException
  {
    
   String query = null;    
    IRConnection jdtC = null;  
    IQueryProcessor qp = null;
    try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor(); 
      qp.setSingleton(false);              
      qp.update("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID="+_defID);
      qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getFormClass().getDefinition().getIID());
      if (_defID != _sdefID && !getConfigKind().equals(ConfigKind.SUMMARY_ONLY))
      {
        qp.update("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID="+_sdefID);
        qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getFormSecondClass().getDefinition().getIID());
      }
    }
    finally 
    { 
      if (qp != null) qp.close(); 
      getObjectContext().getCRM().commitTransaction(getObjectContext());          
    }     
    
    
    
  }

public void removeAttribute(IRAttribute att) throws OculusException
  {
    
    
    String query = null;    
    IRConnection jdtC = null;  
    IQueryProcessor qp = null;
    try 
    {         
      jdtC = getDatabaseConnection();
      qp = jdtC.createProcessor(); 
      qp.setSingleton(false);              
      qp.update("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID="+_defID+" AND ATTRIBUTEID="+att.getIID());
      qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getFormClass().getDefinition().getIID()+" AND ATTRIBUTEID="+att.getIID());
      if (_defID != _sdefID && !getConfigKind().equals(ConfigKind.SUMMARY_ONLY))
      {
        qp.update("DELETE FROM CLASSATTRGROUPASC WHERE CLASSID="+_sdefID+" AND ATTRIBUTEID="+att.getIID());
        qp.update("DELETE FROM INTERFACEATTRASC WHERE INTERFACEID="+getFormSecondClass().getDefinition().getIID()+" AND ATTRIBUTEID="+att.getIID());
      }
    }
    finally 
    { 
      if (qp != null) qp.close(); 
      getObjectContext().getCRM().commitTransaction(getObjectContext());          
    }     
    
   
  }

	// TODO: Try making this a MarketInputColl also.  Sort by the EnumLit so that
	// all of the mi's that have the same answer will be together.  Any sums can be done
	// while iterating through the list.
  public List getMultiEnumInputs(IRAttribute question) throws OculusException     
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;
    Primitive prim = question.getPrimitive();
    long classID = getFormClass().getIID().getLongValue();
    long attID = question.getIID().getLongValue();
    List newList = new ArrayList();
    String query = "SELECT * FROM \"ENUMSELECTION\" enumsel LEFT OUTER JOIN \"MARKETINPUT\" mkt ON mkt.OBJECTID=enumsel.PAROBJECTID WHERE mkt.DELETESTATE =1 AND enumsel.ATTRIBUTEID="+question.getIID().getLongValue();      
    try
    {
     jdtC = getDatabaseConnection();
     qp = jdtC.createProcessor();
     rs = qp.retrieve(query);
     while(rs.next()) 
      {       
       //super hack for now.
       
       long lo1 = rs.getLong("ENUMLITERALID");   
       long lo2 = rs.getLong("PAROBJECTID");   
       newList.add(lo1+","+lo2);       
      }
    }
    catch(Exception ex) {throw new ORIOException(ex); }
    finally 
    { 
      qp.close();
//      try {returnDatabaseConnection(jdtC);}
//      catch(Exception ex) { throw new ORIOException(ex);}    
    } //end finally     
    return newList;
  }
    
  //This only gets called by question & answer inputs during rollup.
	//It returns a List object containing all MI IDS for the given Question.
	// TODO: Try making this a MarketInputColl object.
  public List getAllInputs(IRAttribute question) throws OculusException
  {
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;
    Primitive prim = question.getPrimitive();
    long classID = getFormClass().getIID().getLongValue();
    long attID = question.getIID().getLongValue();
    List newList = new ArrayList();
    
		String attribTable = "";
		if (prim == Primitive.CHAR || prim == Primitive.INTEGER || prim == Primitive.DECIMAL )
			attribTable = "CHARVALUE";
    if (prim == Primitive.LONG_CHAR)
			attribTable = "LONGCHARVALUE";     
    if (prim == Primitive.TIME)
			attribTable = "TIMEVALUE";     
    if (prim == Primitive.ENUM || prim == Primitive.RADIO)
      attribTable = "ENUMVALUE";     
    if (prim == Primitive.MULTICHECK || prim == Primitive.MULTIENUM)
      attribTable = "ENUMSELECTION";     
    if (prim == Primitive.BOOLEAN)
	    attribTable = "BOOLEANVALUE";     

		String query = "SELECT mkt.OBJECTID FROM \"MARKETINPUT\" mkt LEFT OUTER JOIN \""+attribTable+"\" charval ON charval.PAROBJECTID=mkt.OBJECTID "
            +" WHERE mkt.CLASSID="+classID
            +" AND mkt.DELETESTATE =1 AND charval.ATTRIBUTEID="+attID;   
    try
    {
     jdtC = getDatabaseConnection();
     qp = jdtC.createProcessor();
     rs = qp.retrieve(query);
     while(rs.next()) 
      {       
       IIID id = new SequentialIID(rs.getLong("OBJECTID"));   
       newList.add(id);
      }
    }
    catch(Exception ex) {throw new ORIOException(ex); }
    finally 
    { 
      qp.close();
//	    try {returnDatabaseConnection(jdtC);}
//	     catch(Exception ex) { throw new ORIOException(ex);}    
    } //end finally     
    return newList;
  }
  
  
  
public void addAttributes(IRModelElementList list) throws OculusException
  {
    
   getFormClass().addAttributes(list); 
    
  }
 
  
 //////////////////////////////////////////////////////////////////////////// 
 ///////////////////////////////////////////////////////////////////////////// 
 ///////////////////////////////////////////////////////////////////////////// 
 //                           RENDER METHODS                                //
 //////////////////////////////////////////////////////////////////////////// 
 ///////////////////////////////////////////////////////////////////////////// 
 
  public void renderQAView(IRObject obj,ITable tab) throws Exception
  {
        
     IRModelElementList arrList = getGlobalAttributeList();
     IBody body = tab.getHTMLObject().getBody();
     int size = arrList.size();  
     for (int i =0; i < size; ++i)
        {
         IRAttribute attr = (IRAttribute)arrList.getModelElement(i);         
         int j = i+1;
         
         _displayQAView(obj, attr,body,tab,context, size,j);         
         
        }        
      arrList = null;   
}
  
  public void renderQAEdit(IRObject obj,ITable tab) throws Exception
  {
    
     IRModelElementList arrList = getGlobalAttributeList();
     IBody body = tab.getHTMLObject().getBody();
     int size = arrList.size();  
     for (int i =0; i < size; ++i)
        {
         IRAttribute attr = (IRAttribute)arrList.getModelElement(i);         
         int j = i+1;
         _displayQAEdit(obj,attr,body,tab,context, size,j);         
         
        }        
      arrList = null;   
  }
  
  public void renderQACreate(ITable tab) throws Exception
  {
    
     IRModelElementList arrList = getGlobalAttributeList();
     IBody body = tab.getHTMLObject().getBody();
     int size = arrList.size();  
     for (int i =0; i < size; ++i)
        {
         IRAttribute attr = (IRAttribute)arrList.getModelElement(i);         
         int j = i+1;
                  _displayQACreate(attr,body,tab,context, size,j);         
         
        }        
      arrList = null;   
  }
  
 private void _displayQAView(IRObject obj,IRAttribute att,IBody body,ITable tab,IObjectContext context, int size, int j) throws Exception
  {
   
     String fieldName = "prop"+att.getIID().toString();
     String showName = att.getName();  
         
     IRPropertyMap props = obj.getProperties();
     IRProperty prop = (IRProperty) props.get(fieldName);
     if (prop == null) {return;}
     ITableRow tr = null;
     Object oo = prop.getValue();
     String oostr = null;
     if (oo == null) 
        oostr = NULL;
     else
        oostr = oo.toString();
      if (oo != null && oo instanceof java.sql.Timestamp) 
        oostr = com.oculussoftware.ui.DateFormatter.format((java.sql.Timestamp)oo);  
      if (!StringUtil.isPrintable(oostr) || oostr.equals(SPACE)) oostr = NULL;
         
         Primitive prim = att.getPrimitive();                  
         AttributeKind ak = att.getAttrKind();                  
         ConfigKind ck = att.getConfigKind();
         IIID dtIID = att.getDataType().getIID();         
         
         tr = tab.addTableRow(); 
         ITableData td = tr.addTableData(); 
         td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.QUESTION).setStringValue(""+j+"&nbsp;&nbsp;&nbsp;&nbsp;");           
         
         if (ak != AttributeKind.PRODENUM)
        {
         
         if(ck != ConfigKind.SUMMARY_ONLY)        
           td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.LABEL).setStringValue(att.getUserPrompt());  
         else
          {
           IIID linkid = StringUtil.getIID(att.getExpression());
           IRAttribute attr = (IRAttribute)context.getCRM().getCompObject(context,"Attribute",linkid);
           td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.LABEL).setStringValue(attr.getUserPrompt());    
          }
        }

         tr = tab.addTableRow(); 
         td = tr.addTableData();
         td.addAnchor().setStringValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
         if (prim == Primitive.CHAR) 
          {
           td.addAnchor().setStringValue(oostr);           
          }
         
         if (prim == Primitive.INTEGER)
           {
           
           td.addAnchor().setStringValue(oostr);
          }
           
         if (prim == Primitive.DECIMAL) 
          {
   
	   				if (StringUtil.isValidFloat(oostr))
					  	{
					  		Double ff = new Double(Float.parseFloat(oostr));
					  		NumberFormat nf = NumberFormat.getInstance();
					  		nf.setMaximumFractionDigits(2);  		
					  		nf.setMinimumFractionDigits(2);  		
					  		oostr = nf.format(ff.doubleValue());
					  	}
           td.addAnchor().setStringValue(oostr);
          }
           
          if (prim == Primitive.TIME) 
          {
            
            if (StringUtil.isPrintable(oostr) && !oostr.equals(NULL))
               oostr = com.oculussoftware.ui.DateFormatter.format(oostr);       
             td.addAnchor().setStringValue(oostr);
          }
           
         if (prim == Primitive.LONG_CHAR)
           td.addAnchor().setStringValue(oostr);           
           
         if (prim == Primitive.BOOLEAN)
          {          
              if (oo != null)
            {
              String boostr = oo.toString();
              td.addAnchor().setStringValue("Yes");             
            }
            else
            {
               td.addAnchor().setStringValue("No");               
            }
         }
          
         if ((prim == Primitive.ENUM || prim == Primitive.RADIO) && ak != AttributeKind.PRODENUM)
          {
           
           Object o = prop.getValue();   
           String enumname = "-not specified-";
           if (o != null)
            {
               Long oo1 = (Long)o;
               IIID id =null;   
               id = new SequentialIID(oo1.longValue());
               IREnumliteral enumlite = context.getRepository().getBMRepository().getEnumliteral(id,false);      
               enumname = enumlite.getName();
            }
           
            //td = tr.addTableData();
           td.addAnchor(enumname);
          }
         
         if (prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK)
          {
           
           		 
               //td = tr.addTableData();   
               //ITable tab1 = td.addTable();   
               IIID id =null;
               Object o = prop.getValue();
               
               if (o != null)
                {
                   String s = o.toString();
                   StringTokenizer st = new StringTokenizer(s,",");            
                   while(st.hasMoreTokens())
                    {
                      String scott = st.nextToken();
                      if (scott.equals("-1"))
                        scott = IDCONST.DUMMYLITERAL.getIIDValue().toString();
                      id =StringUtil.getIID(scott);
                      IREnumliteral enumlite = context.getRepository().getBMRepository().getEnumliteral(id,false);   
                      if (!enumlite.getIID().equals(IDCONST.DUMMYLITERAL.getIIDValue()))
                      {
                          String enumname = enumlite.getName();                      
                          td.addAnchor().setStringValue(enumname);                      
                          td.addBR();
                          td.addAnchor().setStringValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                      }
                    }
                }
                else
                //tab1.addTableRow().addTableData().addAnchor("-not specified-");
                 td.addAnchor().setStringValue("-not specified-");
          }
  }
  
 private void _displayQAEdit (IRObject obj,IRAttribute att,IBody body,ITable tab,IObjectContext context, int size,int j) throws Exception
  {
     
     String fieldName = "";
			long iidlong = att.getIID().getLongValue();
	  if (iidlong < 0)
	    {
	     iidlong = iidlong*-1;
	     fieldName= "prop_"+iidlong;
	    }
	  else
	   fieldName= "prop"+att.getIID().toString();
       
     String showName = att.getName();           
     IRPropertyMap props = obj.getProperties();         
     String propkey = "prop"+att.getIID().toString();
     IRProperty prop = (IRProperty) props.get(propkey);
     if (prop == null) return;
     ITableRow tr = null;
     
     Object oo = prop.getValue();
     String oostr = null;
     if (oo == null) 
        oostr = NULL;
     else
        oostr = oo.toString();
     if (oo != null && oo instanceof java.sql.Timestamp) 
      oostr = com.oculussoftware.ui.DateFormatter.format((java.sql.Timestamp)oo);  
     if (oostr.equals(NULL)) oostr = " ";
  
         Primitive prim = att.getPrimitive();                  
         AttributeKind ak = att.getAttrKind();                  
         ConfigKind ck = att.getConfigKind();
         IIID dtIID = att.getDataType().getIID();                  
         tr = tab.addTableRow();         
         ITableData td = tr.addTableData(); 
         td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.QUESTION).setStringValue(""+j);           
         if (ak != AttributeKind.PRODENUM)
        {         
         if(ck != ConfigKind.SUMMARY_ONLY)
           td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.LABEL).setStringValue(att.getUserPrompt());  
         else
          {
           IIID linkid = StringUtil.getIID(att.getExpression());
           IRAttribute attr = (IRAttribute)context.getCRM().getCompObject(context,"Attribute",linkid);
           td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.LABEL).setStringValue(attr.getUserPrompt());    
          }

        }

         tr = tab.addTableRow();         
         tr.addTableData();
         if (prim == Primitive.CHAR) 
          {
           
           tr.addTableData().addInput().setType(InputKind.TEXT).setName(fieldName).setAlias("Question "+j).setSize(15).setValue(oostr).setValidation(ValidationKind.NOTAG1,"Question "+j);
           
          }
         
         if (prim == Primitive.INTEGER)
           {
           
           if (oostr.equals(" "))
           	tr.addTableData().addInput().setType(InputKind.TEXT).setSize(10).setMaxLength(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,"Question "+j);
           else
           	tr.addTableData().addInput().setType(InputKind.TEXT).setSize(10).setMaxLength(10).setName(fieldName).setValue(oostr).setValidation(ValidationKind.NUMERIC,"Question "+j);	
           
          }
           
         if (prim == Primitive.DECIMAL) 
          {
           
           if (oostr.equals(" "))
           	tr.addTableData().addInput().setType(InputKind.TEXT).setSize(10).setMaxLength(10).setName(fieldName).setValidation(ValidationKind.FLOAT,"Question "+j);
           else
           	tr.addTableData().addInput().setType(InputKind.TEXT).setSize(10).setMaxLength(10).setName(fieldName).setValue(oostr).setValidation(ValidationKind.FLOAT,"Question "+j);
           
          }
           
          if (prim == Primitive.TIME) 
          {
           ITableData data = tr.addTableData();
           if (StringUtil.isPrintable(oostr))
             oostr = com.oculussoftware.ui.DateFormatter.format(oostr);       
           if (oostr.equals(SPACE))
             data.addInput().setType(InputKind.TEXT).setName(fieldName).setSize(15).setValidation(ValidationKind.DATE);
           else
             data.addInput().setType(InputKind.TEXT).setSize(15).setName(fieldName).setValue(oostr).setValidation(ValidationKind.DATE,"Question "+j);
           data.addCalendar("parent.Body.document.forms[0]."+fieldName);
           
          }
           
         if (prim == Primitive.LONG_CHAR)
           tr.addTableData().addTextArea().setRows(6).setCols(40).setName(fieldName).setAlias("Question "+j).setStringValue(oostr);           
           
         if (prim == Primitive.BOOLEAN)
          {
           
           if (oo != null)
          {            
            String bo = oo.toString();
            if(bo.equals("true"))
              tr.addTableData().setWidthFixed(100).addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true").setChecked();
            else 
              tr.addTableData().setWidthFixed(100).addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true");
          }
        else
          {
             tr.addTableData().setWidthFixed(100).addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true");
          }

          }
         
         if (prim == Primitive.ENUM && ak != AttributeKind.PRODENUM)
          {
           
           String dummyliteralid = IDCONST.DUMMYLITERAL.getIIDValue().toString();
           String enumstr = "";
           Object o = prop.getValue(); 
           if (o != null)
            {
           Long oo1 = (Long)prop.getValue();                      
           enumstr = oo1.toString();
            }
           else
             enumstr = dummyliteralid;
             
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           ISelect sel = tr.addTableData().addSelect().setSize(1).setName(fieldName).setValidation(ValidationKind.NOSELECT, "Question "+j);           
           sel.addOption("-Please select one-",dummyliteralid);
           for(int i=0; i < size1;++i)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String id1 = ""+((Tuple)list.get(i)).getIndex();
              if (id1.equals(enumstr))   
                  sel.addOption(name,id1).setSelected();
              else
                    sel.addOption(name,id1);
            }
          }
         
         if (prim == Primitive.MULTIENUM)
          {
           
           Object o = prop.getValue();
           long small[] = null;
           if (o != null)
            {
           String s = o.toString();
           StringTokenizer st = new StringTokenizer(s,",");      
           small = new long[st.countTokens()];   
           int k = 0;
           while(st.hasMoreTokens())
            {
              small[k]=Long.parseLong(st.nextToken());           
              if(small[k] == -1) small[k] = IDCONST.DUMMYLITERAL.getLongValue();
              ++k;        
            }   
           Arrays.sort(small);  
            }
           
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           ISelect sel = tr.addTableData().addSelect().setSize(5).setName(fieldName).setMultiple().setValidation(ValidationKind.NOSELECT,"Question "+j);
           String dummyliteralid = IDCONST.DUMMYLITERAL.getIIDValue().toString();
           IOption first = sel.addOption("-select some-",dummyliteralid);
           boolean anysel = false;
           for(int i=0; i < size1;i++)    
            {
              String name = ((Tuple)list.get(i)).getString();
              long id = ((Tuple)list.get(i)).getIndex();            
              if (o != null)
              {
              if (Arrays.binarySearch(small,id) < 0)     //Oh Boy!
                  sel.addOption(name,""+id);
              else    
              	{
              	anysel = true;
                sel.addOption(name,""+id).setSelected();              
              	}
              }
              else
                sel.addOption(name,""+id);
            }
            
            if (!anysel) first.setSelected();
            list = null;
          }
         
         
         if (prim == Primitive.RADIO)
          {
           
           Object o = prop.getValue();
           String enumlong = "";
           if (o != null)
            {
           Long oo1 = (Long)prop.getValue();
           enumlong = oo1.toString();
            }
           else 
             enumlong = IDCONST.DUMMYLITERAL.getIIDValue().toString();
           
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           td  = tr.addTableData();
           ITable tab1 = td.addTable();          
           for(int i=0; i < size1;i++)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String id1 = ""+((Tuple)list.get(i)).getIndex();
              ITableRow tr1 = tab1.addTableRow();
              if (id1.equals(enumlong))
              {
                IInput inp = tr1.addTableData().addInput().setName(fieldName).setType(InputKind.RADIO).setValue(id1).setChecked();
                tr1.addTableData().addAnchor(name);
              }
              else
              {
                IInput inp = tr1.addTableData().addInput().setName(fieldName).setType(InputKind.RADIO).setValue(id1);
                tr1.addTableData().addAnchor(name);
              }
              
            }
            list = null;
          }
         
         
         if (prim == Primitive.MULTICHECK)
          {
           
           Object o = prop.getValue();
           long small[] = null;
           if (o != null)
            {
           String s = o.toString();
           StringTokenizer st = new StringTokenizer(s,",");      
           small = new long[st.countTokens()];   
           int k = 0;   
           while(st.hasMoreTokens())
            {
              small[k]=Long.parseLong(st.nextToken());           
              ++k;        
             }
           Arrays.sort(small);  
            }
            
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           td  = tr.addTableData();
           ITable tab1 = td.addTable();
              
           for(int i=0; i < size1;i++)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String checkname = fieldName +i;
              long id =((Tuple)list.get(i)).getIndex();
              String id1 = ""+id;
              ITableRow tr1 = tab1.addTableRow();
              if (o != null)
              {
               if (Arrays.binarySearch(small,id) < 0)     //Oh Boy!
               {      
                IInput inp = tr1.addTableData().addInput().setName(checkname).setType(InputKind.CHECKBOX).setName(checkname).setValue(id1);
                tr1.addTableData().addAnchor(name);
               }
              else
               {
                  IInput inp = tr1.addTableData().addInput().setName(checkname).setType(InputKind.CHECKBOX).setName(checkname).setValue(id1).setChecked();
                  tr1.addTableData().addAnchor(name);
               }
              }
              else
              {
                IInput inp = tr1.addTableData().addInput().setName(checkname).setType(InputKind.CHECKBOX).setName(checkname).setValue(id1);
                tr1.addTableData().addAnchor(name);
              }
            }
            list = null;
          }
    
  }
 
 private void _displayQACreate(IRAttribute att,IBody body,ITable tab,IObjectContext context,int size,int j) throws Exception         
  {

			  String fieldName = "";
    			long iidlong = att.getIID().getLongValue();
			  if (iidlong < 0)
			    {
			     iidlong = iidlong*-1;
			     fieldName= "prop_"+iidlong;
			    }
			  else
			   fieldName= "prop"+att.getIID().toString();
  
         String showName = att.getName();  
         Primitive prim = att.getPrimitive();                  
         AttributeKind ak = att.getAttrKind();                  
         IIID dtIID = att.getDataType().getIID();         
         ITableRow tr = tab.addTableRow();         
         ITableData td = tr.addTableData(); 
         td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.QUESTION).setStringValue(""+j+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");                    
         td.setVAlign(VAlignKind.TOP).addAnchor().setClass(ClassKind.LABEL).setStringValue(att.getUserPrompt());           
         tr = tab.addTableRow();
         td  = tr.addTableData();
         td.addAnchor().setStringValue("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
         if (prim == Primitive.CHAR) 
          {
           td.addInput().setType(InputKind.TEXT).setSize(15).setName(fieldName).setAlias("Question "+j).setValidation(ValidationKind.NOTAG1,"Question "+j);           
          }         
         if (prim == Primitive.INTEGER)
           {
           td.addInput().setType(InputKind.TEXT).setSize(10).setMaxLength(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,"Question "+j);           
          }           
         if (prim == Primitive.DECIMAL) 
          {
           td.addInput().setType(InputKind.TEXT).setSize(10).setMaxLength(10).setName(fieldName).setValidation(ValidationKind.FLOAT,"Question "+j);           
          }
           
          if (prim == Primitive.TIME) 
          {
           //ITableData data = tr.addTableData();
           td.addInput().setType(InputKind.TEXT).setSize(15).setName(fieldName).setValidation(ValidationKind.DATE,"Question "+j);
           td.addCalendar("parent.Body.document.forms[0]."+fieldName);           
          }
           
         if (prim == Primitive.LONG_CHAR)
           td.addTextArea().setRows(6).setCols(40).setName(fieldName).setAlias("Question "+j);           
           
         if (prim == Primitive.BOOLEAN)
              td.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true");          
         
         if (prim == Primitive.ENUM && ak != AttributeKind.PRODENUM)
          {
           
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           ISelect sel = td.addSelect().setSize(1).setName(fieldName).setValidation(ValidationKind.NOSELECT,"Question "+j);
           String dummyliteralid = IDCONST.DUMMYLITERAL.getIIDValue().toString();
           sel.addOption("-Please select one-",dummyliteralid).setSelected(); 
           for(int i=0; i < size1;++i)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String id1 = ""+((Tuple)list.get(i)).getIndex();
              sel.addOption(name,id1);
            }
          }
         
         if (prim == Primitive.MULTIENUM)
          {
           
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           ISelect sel = td.addSelect().setSize(3).setMultiple().setName(fieldName).setValidation(ValidationKind.NOSELECT, "Question "+j);
           String dummyliteralid = IDCONST.DUMMYLITERAL.getIIDValue().toString();
           sel.addOption("-Please select some-",dummyliteralid).setSelected();                       
           for(int i=0; i < size1;i++)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String id1 = ""+((Tuple)list.get(i)).getIndex();
              sel.addOption(name,id1);
            }
            list = null;
          }
         
         
         if (prim == Primitive.RADIO)
          {
           
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
          //  td  = tr.addTableData();
           ITable tab1 = td.addTable();
           
              
           for(int i=0; i < size1;i++)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String id1 = ""+((Tuple)list.get(i)).getIndex();
              ITableRow tr1 = tab1.addTableRow();
              if (i ==0)
              {
              IInput inp = tr1.addTableData().addInput().setType(InputKind.RADIO).setName(fieldName).setValue(id1).setChecked();
              tr1.addTableData().addAnchor(name);
              }
              else
              {
              IInput inp = tr1.addTableData().addInput().setType(InputKind.RADIO).setName(fieldName).setValue(id1);
              tr1.addTableData().addAnchor(name);                
              }
              
            }
            list = null;
          }
         
         if (prim == Primitive.MULTICHECK)
          {
           
           IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
           List list = renum.getValues();
           int size1 = list.size();
           //td  = tr.addTableData();
           ITable tab1 = td.addTable();
              
           
           for(int i=0; i < size1;i++)    
            {
              String name = ((Tuple)list.get(i)).getString();
              String checkname = fieldName+i;   
              String id1 = ""+((Tuple)list.get(i)).getIndex();
              ITableRow tr1 = tab1.addTableRow();
              IInput inp = tr1.addTableData().addInput().setType(InputKind.CHECKBOX).setName(checkname).setValue(id1);
              tr1.addTableData().addAnchor(name);
              
            }
            list = null;
          }
          
          if (ak == AttributeKind.PRODENUM) 
        {
      
           IRProdEnumeration renum = context.getRepository().getBMRepository().getProdEnum(dtIID,false);
           List list = renum.getValuesAndNotifiees();
           int size3 = list.size();
           ISelect sel = tr.addTableData().setWidthFixed(100).addSelect().setName(fieldName).setSize(3).setMultiple().setValidation(ValidationKind.PRODENUM);   
           sel.addOption("-Please select-","-1");
           for(int i=0; i < size3;i++)    
            {
              String name = ((Tuple)list.get(i)).getStartString();
              String id1 = ""+((Tuple)list.get(i)).getEndIndex();
              String id2 = ""+((Tuple)list.get(i)).getStartIndex();
              String id = id1+":"+id2;
               if (i ==0) 
                sel.addOption(name,id).setSelected();
              else
               sel.addOption(name,id);   
            }
          }
  }

 
 public void  renderView(IRObject obj,ITable tab) throws Exception
  { 
    _render("View",obj,tab);
  }
  
  public void  renderEdit(IRObject obj,ITable tab) throws Exception
  { 
    _render("Edit",obj,tab);
    _postprocess(obj);
  }
  
  
  // This method will add any attribute to the object that were added to the form
  // after the object was created.
  // TODO: this might make more sense as a PREprocess rather than POST.
  // TODO: PersState.NEW seems to make more sense.  See why PersState.UNMODIFIED works.
  private void _postprocess(IRObject obj) throws Exception
  {
    IRModelElementList list = getGlobalAttributeList();
    IRPropertyMap props = obj.getProperties();
    int globalSize = list.size();
    for(int i = 0; i < globalSize; ++i)
    {
      IRAttribute att  = (IRAttribute)list.getModelElement(i);
      String key ="prop"+att.getIID();
      if (!props.containsKey(key))
      {
       IRProperty newProp = new BMProperty(obj);
       newProp.setDefnObject(att);
//       newProp.setPersState(PersState.NEW);
       newProp.setPersState(PersState.UNMODIFIED);
       props.put(key,newProp);
      }
    }
  }
  
  
  public void  renderCreate(ITable tab) throws Exception
  { 
    _render("Create",(IRObject)null, tab);
  }
  
  private void  _render(String s,IRObject obj,ITable tab) throws Exception
  {
    IRModelElementList arrList =null;
    IBody body = tab.getHTMLObject().getBody();    
    IRAttribute attr=null;
    int size = -1;
    
     if (s.equals("Create"))
    {
     IObjectContext ctxt = getObjectContext();
     List createList = getGroupsAndAttributes();
     size = createList.size();
     for (int i =0; i < size; ++i)
      {
         Tuple tup = (Tuple) createList.get(i);
         long idx = tup.getStartIndex();     
         boolean isreq = tup.isRequired();     
         attr = (IRAttribute)ctxt.getCRM().getCompObject(ctxt,"Attribute",new SequentialIID(idx));            
         _displayCreate(attr,body,tab,context, i, isreq);                 
      }    
      
     }
  
    if (s.equals("Edit"))
    {
     
     
     IObjectContext ctxt = getObjectContext();
     List createList = getGroupsAndAttributes();
     size = createList.size();
     IRModelElementList editlist = getEditableAttributeList();
     for (int i =0; i < size; ++i)
      {
         Tuple tup = (Tuple) createList.get(i);
         long idx = tup.getStartIndex();     
         boolean isreq = tup.isRequired(); 
         attr = (IRAttribute)ctxt.getCRM().getCompObject(ctxt,"Attribute",new SequentialIID(idx));            
          if (!editlist.contains(attr.getIID())) 
           _displayEdit(obj,attr,body,tab,context, i, false,isreq);                 
         else  
          _displayEdit(obj,attr,body,tab,context, i, true,isreq);                  
       }    
     
     }
  
     if (s.equals("View")) 
        {                
          arrList = getGlobalAttributeList();               
          IRModelElementList viewList = getViewableAttributeList();               
          size = arrList.size();          
          for (int i =0; i < size; ++i)
           {
             attr = (IRAttribute)arrList.getModelElement(i);                                
             if (viewList.contains(attr.getIID()))           
               _displayView(obj,attr,body,tab,context, i);                      
             
            }
         }                
    arrList = null;    
  }
  
    
   private void _displayCreate(IRAttribute attr, IBody body1, ITable tab, IObjectContext context, int i, boolean isreq) throws Exception
{
 
 
 AttributeKind ak = attr.getAttrKind();
 boolean longlabel = false;
 final String NULL = "-not specified-";
 final String SPACE = " ";
 IRDataType dt = attr.getDataType(); 
 IIID dtIID = dt.getIID();
 Primitive prim = dt.getTypeKind();
 int maxlen = attr.getMaxLength();
 if (maxlen == -1) maxlen = 250;
 String unitlabel = attr.getUnitLabel();
 UnitPosition unitpos = attr.getUnitPosition();
 if (unitlabel == null || unitlabel.equals(NULL)) unitlabel = SPACE;    
 IInput inp = null;     
 String oostr = null;
 Object o = attr.getDefaultValue();
 if (o != null) oostr = o.toString();   
 if (oostr != null && (oostr.equals(NULL) || oostr.equals("") || oostr.equals("null"))) 
   oostr = null;  
   
 
  String fieldName=null;
  long iidlong = attr.getIID().getLongValue();
  if (iidlong < 0)
    {
     iidlong = iidlong*-1;
     fieldName= "prop_"+iidlong;
    }
  else
   fieldName= "prop"+attr.getIID().toString();   
  
  String showName = StringUtil.escapeQuotes(attr.getName());
   ITableRow tr = tab.addTableRow();
   ITableData  td = tr.addTableData();
   
   String s = "field"+i;   
   IAnchor labelAnchor =null;
   //First print the LHS label
   if (attr.getName().length() < 30)  
  {
    labelAnchor = td.setNowrap().addAnchor();
    if (isreq) //Visual cue to the user. Required fields need to be in a different color?
      labelAnchor.setClass(ClassKind.REQUIRED).setStringValue("*"+attr.getName()+":");                 
    else
      labelAnchor.setClass(ClassKind.LABEL).setStringValue(attr.getName()+":");                 
    td = tr.addTableData();
  }
  else
  {
   
    labelAnchor = td.setColSpan(2).addAnchor();
    if (isreq) //Visual cue to the user. Required fields need to be in a different color?
      labelAnchor.setClass(ClassKind.REQUIRED).setStringValue("*"+attr.getName()+":");                 
    else
      labelAnchor.setClass(ClassKind.LABEL).setStringValue(attr.getName()+":");                     
    tr = tab.addTableRow();  
    tr.addTableData().setNowrap();
    td = tr.addTableData();
  }
   

  //Primitive is CHAR    
  if (prim == Primitive.CHAR) 
  {    
   
   if (unitpos == UnitPosition.RIGHT)
    {     
     if (isreq)
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOBLANK,showName);
     else  
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOSPACE,showName);
     td.addAnchor().setStringValue(unitlabel);
    }
    else
    {
     td.addAnchor().setStringValue(unitlabel);
     if(!isreq)
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOSPACE,showName);
     else  
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOBLANK,showName);
   }
   if (oostr != null) inp.setValue(oostr);
  }
  
  //Primitive is LONGCHAR
  if (prim == Primitive.LONG_CHAR) 
  {   
   if (isreq)
    {
      if (oostr != null)
       td.addTextArea().setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOBLANK,showName).setStringValue(oostr);     
      else
        td.addTextArea().setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOBLANK,showName);
    }
   else  
    { if (oostr != null)     
        td.addTextArea().setName(fieldName).setAlias(showName).setStringValue(oostr);
      else
        td.addTextArea().setName(fieldName).setAlias(showName);
    }
  }
  
  
  //Primitive is INTEGER
  if (prim == Primitive.INTEGER) 
  {
   if (unitpos == UnitPosition.RIGHT)
    {  
   if (isreq)
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NOBLANK,showName).setValidation(ValidationKind.NUMERIC,showName);
   else  
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName);
   td.addAnchor().setStringValue(unitlabel);
    }
    else
    {
     td.addAnchor().setStringValue(unitlabel);
     if (isreq)
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName).setValidation(ValidationKind.NOBLANK,showName);
     else  
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName);
    }
   if (oostr != null) inp.setValue(oostr);
  }

  //Primitive is DECIMAL
  if (prim == Primitive.DECIMAL) 
  {  
  if (unitpos == UnitPosition.RIGHT)
    {  
   
     if (!isreq)
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName);
     else  
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName).setValidation(ValidationKind.NOBLANK,showName);
     td.addAnchor().setStringValue(unitlabel);
    }
    else
    {
     td.addAnchor().setStringValue(unitlabel);
     if (!isreq)
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName);
     else
       inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName).setValidation(ValidationKind.NOBLANK,showName);   
    }
  if (oostr != null) inp.setValue(oostr);
  }
  
  //Primitive is TIME
  if (prim == Primitive.TIME) 
  {
   
   if (!isreq) 
     inp = td.addInput().setType(InputKind.TEXT).setSize(10).setName(fieldName).setValidation(ValidationKind.DATE,showName);
   else
    inp = td.addInput().setType(InputKind.TEXT).setSize(10).setName(fieldName).setValidation(ValidationKind.DATE,showName).setValidation(ValidationKind.NOBLANK,showName);   
   td.addCalendar("parent.Body.document.forms[0]."+fieldName);
   if (oostr != null) inp.setValue(oostr); 
   
  }
  
  //Primitive is BLOB
  if (prim == Primitive.BLOB) 
  {      
   if (isreq)
     td.addInput().setType(InputKind.FILE).setOnKeyPress("blur();return false;").setSize(20).setName(fieldName).setValidation(ValidationKind.NOBLANK,showName);   
   else
     td.addInput().setType(InputKind.FILE).setOnKeyPress("blur();return false;").setSize(20).setName(fieldName);
  }
  
  //Primitive is LINK/HYPERLINK
  if (prim == Primitive.LINK) 
  {   
   if (isreq)
     td.addInput().setType(InputKind.TEXT).setSize(40).setName(fieldName).setValidation(ValidationKind.NOBLANK,showName).setValidation(ValidationKind.HTTP,showName);   
   else
     td.addInput().setType(InputKind.TEXT).setSize(40).setName(fieldName);
  }
  
  
  //Primitive is INTEGER
  if (prim == Primitive.BOOLEAN) 
  {  
   if (oostr != null && oostr.equals("true"))
     td.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true").setChecked();  
   else
     td.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true");
  }
  
  
  //Primitive is ENUM
  if (prim == Primitive.ENUM && ak != AttributeKind.PRODENUM) 
  {
   
       IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
       List list = renum.getValues();
       int size = list.size();
       ISelect sel = td.addSelect().setName(fieldName).setValidation(ValidationKind.NOSELECT,showName);
       if (isreq) sel.setValidation(ValidationKind.DUMMYSELECT,showName);
       String strlong = IDCONST.DUMMYLITERAL.getIIDValue().toString();
       if (oostr != null && oostr.equals(strlong))         
         sel.addOption("-Please select one-",strlong).setSelected(); 
       else  
         sel.addOption("-Please select one-",strlong);
       for(i=0; i < size;i++)    
        {
          String name = ((Tuple)list.get(i)).getString();
          String id = ""+((Tuple)list.get(i)).getIndex();
          if (oostr != null && oostr.equals(id))
            sel.addOption(name,id).setSelected();
          else          
            sel.addOption(name,id);
        }
   
  }
  
  
   //Primitive is RADIO
  if (prim == Primitive.RADIO) 
  {
  
   IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();      
   ITable tab1 = td.addTable();
   for(i=0; i < size;i++)    
    {
      String name = ((Tuple)list.get(i)).getString();
      String id = ""+((Tuple)list.get(i)).getIndex();         
      tr = tab1.addTableRow();      
      td = tr.addTableData();
      inp = td.addInput().setType(InputKind.RADIO).setName(fieldName).setValue(id);
      if (oostr != null && oostr.equals(id)) inp.setChecked();                
      if (isreq) inp.setValidation(ValidationKind.RADIO,showName);
      td.addAnchor(name);
    }    
  }
  
  
  //Primitive is MULTISELECT DROP DOWN BOX expand the box a little bit so that users can see other enteries
  //Visual cue to differentiate between single select & multiple select
  if (prim == Primitive.MULTIENUM && ak != AttributeKind.PRODENUM) 
  {     
    IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();
   ISelect sel = td.addSelect().setName(fieldName).setSize(5).setMultiple().setValidation(ValidationKind.NOSELECT,showName);
   String strlong = IDCONST.DUMMYLITERAL.getIIDValue().toString();
   sel.addOption("-Please select some-",strlong).setSelected(); 
   if (isreq) sel.setValidation(ValidationKind.DUMMYSELECT,showName);
   for(i=0; i < size;i++)    
    {
      String name = ((Tuple)list.get(i)).getString();
      String id = ""+((Tuple)list.get(i)).getIndex();
      sel.addOption(name,id);
    }

    
  }
  
   //Primitive is MULTICHECK
  if (prim == Primitive.MULTICHECK && ak != AttributeKind.PRODENUM) 
  {
  
  
   IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();
   ITable tab1 = td.addTable();
   for(i=0; i < size;i++)    
    {
      String name = ((Tuple)list.get(i)).getString();
      String id = ""+((Tuple)list.get(i)).getIndex();   
      String checkname = fieldName+i;
      tr = tab1.addTableRow();
      td = tr.addTableData();
      inp = td.addInput().setType(InputKind.CHECKBOX).setName(checkname).setValue(id);
      td.addAnchor(name);
    }
    
  }
  


   if (ak == AttributeKind.PRODENUM) 
  {
      
   IRProdEnumeration renum = context.getRepository().getBMRepository().getProdEnum(dtIID,false);
   List list = renum.getValuesAndNotifiees();
   int size = list.size();
   ISelect sel = td.addSelect().setName(fieldName).setSize(5).setMultiple().setValidation(ValidationKind.PRODENUM,showName);         
    sel.addOption("-Please select-","-1"); 
   for(i=0; i < size;i++)    
    {
     
      String name = ((Tuple)list.get(i)).getStartString();
      String id1 = ""+((Tuple)list.get(i)).getEndIndex();
      String id2 = ""+((Tuple)list.get(i)).getStartIndex();
      String id = id1+":"+id2;
      if (i ==0) 
        sel.addOption(name,id).setSelected();
      else
       sel.addOption(name,id);
    }
   
  }
  
}   
 
 
 private void _displayEdit(IRObject obj,IRAttribute attr, IBody body1, ITable tab, IObjectContext context, int i, boolean toshow, boolean isreq) throws Exception
{
 
  if (!toshow) return; 
  AttributeKind ak = attr.getAttrKind();  
  final String NULL = "-not specified-";
  final String SPACE = " ";
  IRDataType dt = attr.getDataType();
  boolean longlabel = false;
  IIID dtIID = dt.getIID();
  Primitive prim = dt.getTypeKind();
  int maxlen = attr.getMaxLength();
  if (maxlen == -1) maxlen = 250;
  String unitlabel = attr.getUnitLabel();
  UnitPosition unitpos = attr.getUnitPosition();
  if (unitlabel.equals(NULL)) unitlabel = SPACE;  
  String fieldName=null;
  long iidlong = attr.getIID().getLongValue();
  if (iidlong < 0)
    {
     iidlong = iidlong*-1;
     fieldName= "prop_"+iidlong;
    }
  else
   fieldName= "prop"+attr.getIID().toString();   
  
  //String fieldName = "prop"+attr.getIID().toString();
  String showName = StringUtil.escapeQuotes(attr.getName());  
  String propkey = "prop"+attr.getIID().toString();    
  IRPropertyMap props = obj.getProperties();
  IRProperty prop = (IRProperty) props.get(propkey);
  if (prop == null)
  {
    
    prop = new BMProperty(obj);
    prop.setDefnObject(attr);
    prop.setPersState(PersState.UNMODIFIED);
    props.put(propkey,prop);
  }
  
  
  Object oo = prop.getValue();
  String oostr = null;
  if (oo == null) 
    oostr = NULL;
  else
    oostr = oo.toString();
  if (oo != null && oo instanceof java.sql.Timestamp) 
    oostr = com.oculussoftware.ui.DateFormatter.format((java.sql.Timestamp)oo);  
  if (oostr.equals(NULL)) oostr = null;
  IInput inp = null;
  
   /****
   Special cases. 
   ***/
   String enumstr = null;   
   IBusinessObject bobj = (IBusinessObject)obj;
   if (bobj instanceof IMarketInput)
  {
     IMarketInput mi = (IMarketInput)bobj;
     if (attr.getIID().equals(IDCONST.RECORD_NAME.getIIDValue())) {oostr = mi.getSubject();}
     if (attr.getIID().equals(IDCONST.INPUT_IMPORTANCE.getIIDValue())) 
       {
         int imp = mi.getImportance();
         if (imp == 1) enumstr = IDCONST.INPUT_IMPORTANCE_ENUMLITERAL1.getIIDValue().toString();
         else if (imp == 2) enumstr = IDCONST.INPUT_IMPORTANCE_ENUMLITERAL2.getIIDValue().toString();
         else if (imp == 3) enumstr = IDCONST.INPUT_IMPORTANCE_ENUMLITERAL3.getIIDValue().toString();
         else if (imp == 4) enumstr = IDCONST.INPUT_IMPORTANCE_ENUMLITERAL4.getIIDValue().toString();
         else if (imp == 5) enumstr = IDCONST.INPUT_IMPORTANCE_ENUMLITERAL5.getIIDValue().toString();         
       }
     
  }
  if (bobj instanceof IReaction)
  {
     IReaction mi = (IReaction)bobj;
     if (attr.getIID().equals(IDCONST.WEIGHT.getIIDValue())) 
       {
         int imp = mi.getWeight();
         if (imp == 1) enumstr = IDCONST.WEIGHT_ENUMLITERAL1.getIIDValue().toString();
         else if (imp == 2) enumstr = IDCONST.WEIGHT_ENUMLITERAL2.getIIDValue().toString();
         else if (imp == 3) enumstr = IDCONST.WEIGHT_ENUMLITERAL3.getIIDValue().toString();
         else if (imp == 4) enumstr = IDCONST.WEIGHT_ENUMLITERAL4.getIIDValue().toString();
         else if (imp == 5) enumstr = IDCONST.WEIGHT_ENUMLITERAL5.getIIDValue().toString();
       }    
     
  }
  if (bobj instanceof IProblemStatement)
  {
     IProblemStatement mi = (IProblemStatement)bobj;
     if (attr.getIID().equals(IDCONST.RECORD_NAME.getIIDValue())) {oostr = mi.getName()+""; }   
     if (attr.getIID().equals(IDCONST.RECORD_DESC.getIIDValue())) {oostr = mi.getDescription()+"";}         
     
  }
  
   ITableRow tr = null;
   ITableData td = null;
   tr = tab.addTableRow();
   td = tr.addTableData();
    
    if (attr.getName().length() < 30)
  {
    if (prim != Primitive.BLOB && prim != Primitive.LINK)
    {   
       String s = "field"+i;   
       if (!isreq)
         td.setNowrap().addAnchor().setClass(ClassKind.LABEL).setStringValue(attr.getName()+":");                 
       else  
         td.setNowrap().addAnchor().setClass(ClassKind.REQUIRED).setStringValue("*"+attr.getName()+":");                 
       td = tr.addTableData();
    }
  }
  else
  {
    
    if (prim != Primitive.BLOB && prim != Primitive.LINK)
    {   
       String s = "field"+i;   
       if (!isreq)
         td.setColSpan(2).addAnchor().setClass(ClassKind.LABEL).setStringValue(attr.getName()+":");                 
       else
         td.setColSpan(2).addAnchor().setClass(ClassKind.REQUIRED).setStringValue("*"+attr.getName()+":");                   
        tr = tab.addTableRow();  
        tr.addTableData().setNowrap();
        td = tr.addTableData();  
       
    }
    
  }  
   

   if (toshow)   
  {
  //Primitive is CHAR
  if (prim == Primitive.CHAR) 
  {
      
   if (unitpos == UnitPosition.RIGHT)
    {       
   if (isreq)
    inp =  td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOBLANK,showName);
   else  
    inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOSPACE,showName);
   td.addAnchor().setStringValue(unitlabel);
          
    }
    else
    {
   td.addAnchor().setStringValue(unitlabel);
   if(!isreq)
    inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOSPACE,showName);
   else  
   inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(40).setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOTAG1,showName).setValidation(ValidationKind.NOBLANK,showName);
    }
    if (oostr != null) inp.setValue(oostr);
  }
  
  //Primitive is LONGCHAR
  if (prim == Primitive.LONG_CHAR) 
  {      
   ITextArea txt = null;
   if (!isreq)
     txt = td.addTextArea().setName(fieldName).setAlias(showName);     
   else  
     txt = td.addTextArea().setName(fieldName).setAlias(showName).setValidation(ValidationKind.NOBLANK, showName);     
     if (oostr != null) txt.setStringValue(oostr);
  }
  
  //Primitive is INTEGER
  if (prim == Primitive.INTEGER) 
  {
     
   if (unitpos == UnitPosition.RIGHT)
    {  
   if (!isreq)
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName);
   else  
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName).setValidation(ValidationKind.NOBLANK,showName).setValidation(ValidationKind.MAXINTLENGTH,showName);
   td.addAnchor().setStringValue(unitlabel);
    }
    else
    {   
   td.addAnchor().setStringValue(unitlabel);
   if (!isreq)
     td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName);
   else
     td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.NUMERIC,showName).setValidation(ValidationKind.NOBLANK,showName);  
    }
  if (oostr != null) inp.setValue(oostr);
  }

  //Primitive is DECIMAL
  if (prim == Primitive.DECIMAL) 
  {
     
  if (unitpos == UnitPosition.RIGHT)
    {  
   if (!isreq)
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName);
   else
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName).setValidation(ValidationKind.NOBLANK,showName);  
   td.addAnchor().setStringValue(unitlabel);
    }
    else
    {
   td.addAnchor().setStringValue(unitlabel);
   if (!isreq)
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName);
   else
     inp = td.addInput().setType(InputKind.TEXT).setMaxLength(maxlen).setSize(10).setName(fieldName).setValidation(ValidationKind.FLOAT,showName).setValidation(ValidationKind.NOBLANK,showName);  
    }
   if (oostr != null) inp.setValue(oostr);
  }
  
  //Primitive is TIME
  if (prim == Primitive.TIME) 
  {   
    inp = td.addInput().setType(InputKind.TEXT).setName(fieldName).setSize(10).setValidation(ValidationKind.DATE,showName);
    if (isreq) inp.setValidation(ValidationKind.NOBLANK, showName);
    if (StringUtil.isPrintable(oostr))
       oostr = com.oculussoftware.ui.DateFormatter.format(oostr);       
    if (oostr != null) inp.setValue(oostr);
    td.addCalendar("parent.Body.document.forms[0]."+fieldName);
   
  }
  
  
  //Primitive is BOOLEAN
  if (prim == Primitive.BOOLEAN) 
  {
  
   if (oo != null)
    {
        String s = oo.toString();
        if(s.equals("true"))
          td.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true").setChecked();
       else 
        td.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true");
    }
    else
    {
         td.addInput().setType(InputKind.CHECKBOX).setName(fieldName).setValue("true");
    }
  
  }
  
  
  //Primitive is ENUM
  if (prim == Primitive.ENUM && ak != AttributeKind.PRODENUM) 
  {
   
   if (ak != AttributeKind.CANNED)
    {
       Object o = prop.getValue();       
       if (o != null)
        {
           Long oo1 = (Long)o;   
           enumstr = oo1.toString();
        }
       else
         enumstr =  IDCONST.DUMMYLITERAL.getIIDValue().toString();
    }
   
   IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();   
   ISelect sel = td.addSelect().setName(fieldName).setValidation(ValidationKind.NOSELECT,showName);
   if (isreq) sel.setValidation(ValidationKind.DUMMYSELECT,showName);
   String dummyliteralid = IDCONST.DUMMYLITERAL.getIIDValue().toString();
   sel.addOption("-Please select one-",dummyliteralid); 
   if (isreq) sel.setValidation(ValidationKind.DUMMYSELECT, showName); 
   for(i=0; i < size;i++)    
    {
      String name = ((Tuple)list.get(i)).getString();
      String id1 = ""+((Tuple)list.get(i)).getIndex();
      if (id1.equals(enumstr))
          sel.addOption(name,id1).setSelected();
      else
           sel.addOption(name,id1);
     }
    
  }


//Primitive is RADIO
  if (prim == Primitive.RADIO) 
  {
   
   Object o = prop.getValue();
   String enumlong = "";
   if (o != null)
    {
     Long oo1 = (Long)o;
     enumlong = oo1.toString();
    }
    else
      enumlong = IDCONST.DUMMYLITERAL.getIIDValue().toString();
   ITable tab1 = td.addTable();
   IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();      
   
   for(i=0; i < size;i++)    
    {
      String name = ((Tuple)list.get(i)).getString();
      String id1 = ""+((Tuple)list.get(i)).getIndex();
      ITableRow tr1 = tab1.addTableRow();
      ITableData td1 = tr1.addTableData().setColSpan(2);
      if (id1.equals(enumlong))
      {
        
        inp = td1.addInput().setName(fieldName).setType(InputKind.RADIO).setValue(id1).setChecked();
        if (isreq) inp.setValidation(ValidationKind.RADIO, showName);
        td1.addAnchor(name);
      }
     else
      {        
        inp = td1.addInput().setName(fieldName).setType(InputKind.RADIO).setValue(id1);
        if (isreq) inp.setValidation(ValidationKind.RADIO, showName);
        td1.addAnchor(name);
      }   
      
    }

 }
 
 
 if (prim == Primitive.MULTIENUM) 
  {
      
   ITable tab1 = td.addTable();
   td = tab1.addTableRow().addTableData();
   long small[] = null;
   Object o = prop.getValue();
   if (o != null)
    {
   String s = o.toString();
   StringTokenizer st = new StringTokenizer(s,",");      
   small = new long[st.countTokens()];   
   int k = 0;
   while(st.hasMoreTokens())
    {
      small[k]=Long.parseLong(st.nextToken());           
     ++k;        
    }
   st = null;
   Arrays.sort(small);  
  }
   
   
   IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();   
   String dummy = IDCONST.DUMMYLITERAL.getIIDValue().toString();
   ISelect sel = td.addSelect().setName(fieldName).setSize(6).setMultiple().setValidation(ValidationKind.NOSELECT,showName);   
   sel.addOption("-Please select some-",dummy);
   for(i=0; i < size;i++)    
    {
      
      String name = ((Tuple)list.get(i)).getString();
      long id = ((Tuple)list.get(i)).getIndex();            
      if (o != null)
      {
      if (Arrays.binarySearch(small,id) < 0)     //Oh Boy!
          sel.addOption(name,""+id);
      else    
        sel.addOption(name,""+id).setSelected();
      }
      else
        sel.addOption(name,""+id);
      }
  td.addAnchor("Keep the CTRL key pressed while making your selections");
   list = null;
    
  }
  
  if (prim == Primitive.MULTICHECK) 
  {
      
   ITable tab1 = td.addTable();   
   Object o = prop.getValue();
   long[] small = null;
   if (o != null)
    {
     String s = o.toString();
   StringTokenizer st = new StringTokenizer(s,",");      
   small = new long[st.countTokens()];   
   int k = 0;   
   while(st.hasMoreTokens())
    {
      small[k]=Long.parseLong(st.nextToken());           
     ++k;        
    }
    st = null;
   Arrays.sort(small);  
    }
   
   IREnumeration renum = context.getRepository().getBMRepository().getEnum(dtIID,false);
   List list = renum.getValues();
   int size = list.size();   
   for(i=0; i < size;i++)    
    {
      
      String name = ((Tuple)list.get(i)).getString();
      long id = ((Tuple)list.get(i)).getIndex();            
      tr = tab1.addTableRow();
      String checkname = fieldName +i;
      if (o != null)
      {
      if (Arrays.binarySearch(small,id) < 0)     //Oh Boy!
      {
        ITableData td1 = tr.addTableData();
        inp = td1.addInput().setType(InputKind.CHECKBOX).setName(checkname).setValue(""+id).setName(checkname);
        td1.addAnchor(name);
      }
      else
      {
        ITableData td1 = tr.addTableData();
        inp = td1.addInput().setType(InputKind.CHECKBOX).setName(checkname).setValue(""+id).setName(checkname).setChecked();
        td1.addAnchor(name);        
      }
      }
      else
      {        
        ITableData td1 = tr.addTableData();
         inp = td1.addInput().setType(InputKind.CHECKBOX).setName(checkname).setValue(""+id).setName(checkname);
        td1.addAnchor(name);        
        
      }
    }
  
   list = null;
    
  }
  
  }
  
}
 
 private void _displayView(IRObject obj,IRAttribute attr, IBody body1, ITable tab, IObjectContext context, int i) throws Exception
{ 
 
  
  AttributeKind ak = attr.getAttrKind();  
  final String NULL = "-not specified-";
  final String SPACE = " ";
  IRDataType dt = attr.getDataType();
  
  IIID dtIID = dt.getIID();
  Primitive prim = dt.getTypeKind();
  int maxlen = attr.getMaxLength();
  if (maxlen == -1) maxlen = 250;
  String unitlabel = attr.getUnitLabel();
  UnitPosition unitpos = attr.getUnitPosition();
  if (unitlabel.equals(NULL)) unitlabel = SPACE;  
  String fieldName = "prop"+attr.getIID().toString();
  String showName = attr.getName();
   
  IRPropertyMap props = obj.getProperties();
  IRProperty prop = (IRProperty) props.get(fieldName);
  if (prop == null)
  {
    
    prop = new BMProperty(obj);
    prop.setDefnObject(attr);
    props.put(fieldName,prop);
  }
  
  Object oo = prop.getValue();  
  if (ak == AttributeKind.AGGREGATE)
    oo = _calcValue((IBusinessObject)obj,attr.getRollUpAttribute());
    
  String oostr = null;
  if (oo == null) 
    oostr = NULL;
  else
    oostr = oo.toString();  
   if (oostr.startsWith(" ") || oostr.equals("") || oostr.equals("null")) oostr = NULL;   
   String enumname = null;
   IBusinessObject bobj = (IBusinessObject)obj;
   if (bobj instanceof IMarketInput)
  {
     IMarketInput mi = (IMarketInput)bobj;
     if (attr.getIID().equals(IDCONST.RECORD_NAME.getIIDValue())) oostr = mi.getSubject();
     if (attr.getIID().equals(IDCONST.INPUT_IMPORTANCE.getIIDValue())) enumname = mi.getImportance()+"";   
  }
  if (bobj instanceof IReaction)
  {
     IReaction mi = (IReaction)bobj;
     if (attr.getIID().equals(IDCONST.WEIGHT.getIIDValue())) enumname = mi.getWeight()+"";    
     
  }
  if (bobj instanceof IProblemStatement)
  {
     IProblemStatement mi = (IProblemStatement)bobj;
     if (attr.getIID().equals(IDCONST.RECORD_NAME.getIIDValue())) oostr = mi.getName()+"";    
     if (attr.getIID().equals(IDCONST.RECORD_DESC.getIIDValue())) oostr = mi.getDescription()+"";              
  }
 
   
   ITableRow tr = tab.addTableRow();
   ITableData td = tr.addTableData();
   if (attr.getName().length() < 30)
  {    
   if (prim != Primitive.BLOB && prim != Primitive.LINK && ak != AttributeKind.PRODENUM && ak != AttributeKind.SYSTEM_GENERATED)
    {   
   String s = "field"+i;
   td.setNowrap().addAnchor().setClass(ClassKind.LABEL).setStringValue(attr.getName()+":");                 
   td = tr.addTableData();
    }
  }
   else
   {     
     if (prim != Primitive.BLOB && prim != Primitive.LINK && ak != AttributeKind.PRODENUM && ak != AttributeKind.SYSTEM_GENERATED)
      {   
       String s = "field"+i;
       td.setColSpan(2).addAnchor().setClass(ClassKind.LABEL).setStringValue(attr.getName()+":");                 
       tr = tab.addTableRow();  
       tr.addTableData().setNowrap();
       td = tr.addTableData();
     }
   }
  
  
  //Primitive is CHAR
  if (prim == Primitive.CHAR) 
  {
      
   if (unitpos == UnitPosition.RIGHT)
    {  
   
   td.addAnchor().setStringValue(oostr);
   td.addAnchor().setStringValue(unitlabel);
    }
    else
    {   
   td.addAnchor().setStringValue(unitlabel);
   td.addAnchor().setStringValue(oostr);
    }
  }
  
  //Primitive is LONGCHAR
  if (prim == Primitive.LONG_CHAR) 
  {
   td.addAnchor().setStringValue(oostr);     
  }
  
  //Primitive is INTEGER
  if (prim == Primitive.INTEGER) 
  {
  
   
   if (unitpos == UnitPosition.RIGHT)
    {  
   
   td.addAnchor().setStringValue(oostr);
   td.addAnchor().setStringValue(unitlabel);
    }
    else
    {   
   td.addAnchor().setStringValue(unitlabel);   
   td.addAnchor().setStringValue(oostr);
    }
  
  }

  //Primitive is DECIMAL
  if (prim == Primitive.DECIMAL) 
  {
  
   if (StringUtil.isValidFloat(oostr))
  	{
  		Double ff = new Double(Float.parseFloat(oostr));
  		NumberFormat nf = NumberFormat.getInstance();
  		nf.setMaximumFractionDigits(2);  		
  		nf.setMinimumFractionDigits(2);  		
  		oostr = nf.format(ff.doubleValue());
  	}
  	
  if (unitpos == UnitPosition.RIGHT)
    {  
   td.addAnchor().setStringValue(oostr);   
   td.addAnchor().setStringValue(unitlabel);
    }
    else
    {
   td.addAnchor().setStringValue(unitlabel);
   td.addAnchor().setStringValue(oostr);   
    }
  
  }
  
  //Primitive is TIME
  if (prim == Primitive.TIME) 
  {           
       if (StringUtil.isPrintable(oostr) && !oostr.equals(NULL))
         oostr = com.oculussoftware.ui.DateFormatter.format(oostr);        
       td.addAnchor().setStringValue(oostr);      
  }
  
  
  //Primitive is BOOLEAN
  if (prim == Primitive.BOOLEAN) 
  {
  
   if (oo != null)
    {
      String boostr = oo.toString();      
      td.addAnchor().setStringValue("Yes");         
    }
    else
    {
      td.addAnchor().setStringValue("No");          
    }
  }
  
  
  //Primitive is ENUM
  if (prim == Primitive.ENUM && ak != AttributeKind.PRODENUM) 
  {
   if (ak != AttributeKind.CANNED)
    {
     Object o = prop.getValue();   
     enumname = "-not specified-";
     if (o != null)
      {
         Long oo1 = (Long)o;
         IIID id =null;   
         id = new SequentialIID(oo1.longValue());
         IREnumliteral enumlite = context.getRepository().getBMRepository().getEnumliteral(id,false);      
         enumname = enumlite.getName();
      }
    }
   td.addAnchor(enumname);
  }


//Primitive is RADIO
  if (prim == Primitive.RADIO) 
  {
   
   if (ak != AttributeKind.CANNED)
    {
     Object o = prop.getValue();
     enumname = "-not specified-";   
     if (o != null)
      {
       Long oo1 = (Long)o;
     IIID id = new SequentialIID(oo1.longValue());
     IREnumliteral enumlite = context.getRepository().getBMRepository().getEnumliteral(id,false);   
     enumname = enumlite.getName();
      }
    }
   td.addAnchor(enumname);
   
 }
 
 
 if (prim == Primitive.MULTIENUM || prim == Primitive.MULTICHECK) 
  {
      
   ITable tab1 = td.addTable();   
   IIID id =null;
   Object o = prop.getValue();   
   if (o != null)
    {
   String s = o.toString();
   StringTokenizer st = new StringTokenizer(s,",");            
   while(st.hasMoreTokens())
    {
      String scott = st.nextToken();
      if (scott.equals("-1"))
        scott = IDCONST.DUMMYLITERAL.getIIDValue().toString();
      id =StringUtil.getIID(scott);
      
      IREnumliteral enumlite = context.getRepository().getBMRepository().getEnumliteral(id,false);         
      enumname = enumlite.getName();            
      if (!enumlite.getIID().equals(IDCONST.DUMMYLITERAL.getIIDValue()))
        tab1.addTableRow().addTableData().addAnchor(enumname);
     }
    }
    else
    tab1.addTableRow().addTableData().addAnchor("-not specified-");
  }
  
}   

public long[] getPermissions() throws OculusException
  {
    
    if (permarr == null)
    {
    String query = null;    
    IDataSet rs = null;    
    IRConnection jdtC = null;
    IQueryProcessor qp = null;    
    long[] arr = null;
    try 
    {      
         String s = "";
         jdtC = getDatabaseConnection();
         qp = jdtC.createProcessor();         
         query = "SELECT * FROM PERMISSIONGRANT WHERE PAROBJECTID="+getIID()+" AND PERMISSIONID="+PermEnum.FORM_USE.getID();
         rs  = qp.retrieve(query);
         while (rs.next()) 
           s += rs.getLong("ACCESSORID")+",";
         if (s.length() > 0)
        {
           s = s.substring(0,s.length()-1);                                             
           arr = StringUtil.getLongArray(s);
        }
           
    }
    catch(Exception ex) {throw new ORIOException(ex);}
    finally
    {
    permarr = arr;
    qp.close();
//    returnDatabaseConnection(jdtC);
    }          
    }
    return permarr;
  }        
  
  public boolean isQuestionnaire() throws OculusException
  {
    boolean yes = false;
    IIID id = getFormClass().getRootDefinition().getIID();
    if (id.equals(IDCONST.IQUESTIONINPUT.getIIDValue()))
      yes= true;
    else if (id.equals(IDCONST.IWINLOSSINPUT.getIIDValue()))  
      yes = true;            
   return yes;     
  }
  
  public int countResponses() throws OculusException
  {
    
    IRConnection jdtC = null;
    IQueryProcessor qp = null;
    IDataSet rs = null;
    int found = 0;
    try
    {
      jdtC = getDatabaseConnection();
       qp = jdtC.createProcessor();
       rs = qp.retrieve("SELECT COUNT(OBJECTID) as numObjects FROM MARKETINPUT WHERE DELETESTATE=1 AND CLASSID="+_defID);
      if (rs.next())
        found = rs.getInt("numObjects");
    }
    finally
    {
      if (qp != null) qp.close();
//      returnDatabaseConnection(jdtC);
    }
    return found;
  }
}