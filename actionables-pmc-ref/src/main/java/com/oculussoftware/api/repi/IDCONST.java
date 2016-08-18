package com.oculussoftware.api.repi;

/*
* $Workfile: IDCONST.java $
* Create Date: 3-01-2000 ?
* Description: Enumerates all meta objects that the system requires to exist.
*              Provides a consistent means of programmatically identifying
*              the meta objects, e.g., Entity Classes, States, Attributes.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* Author Many
* Version 1.2
*
* $History: IDCONST.java $
 * 
 * *****************  Version 149  *****************
 * User: Jcoles       Date: 9/22/00    Time: 4:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added progid string for STDFEATURELINK.
 * 
 * *****************  Version 148  *****************
 * User: Jcoles       Date: 9/22/00    Time: 9:41a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Issue 2653.   Enums std coll attrs.
 * 
 * *****************  Version 147  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Bug Fix: #2537
 * 
 * *****************  Version 146  *****************
 * User: Sshafi       Date: 9/14/00    Time: 12:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Bug Fix: 2544
 * 
 * *****************  Version 145  *****************
 * User: Jcoles       Date: 9/11/00    Time: 5:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Issue 2003 - created new idconst for Feature Revision comment.  Moved
 * COMMENT (dbloader is treating as the Reaction comment) to the rest of
 * the reaction attr id declarations.
 * 
 * *****************  Version 144  *****************
 * User: Eroyal       Date: 9/08/00    Time: 3:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 2330 I also fixed bugaboo
 * 
 * *****************  Version 143  *****************
 * User: Jcoles       Date: 9/08/00    Time: 3:18p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Issue 2091: Removed MI_SUBJECT in favor of RECORD_NAME.  Set all
 * xxx_DESC enums equal to DESC.
 * 
 * *****************  Version 142  *****************
 * User: Eroyal       Date: 9/05/00    Time: 1:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 2277 and 2267
 * 
 * *****************  Version 141  *****************
 * User: Jcoles       Date: 8/29/00    Time: 11:49a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Changed id of FILEVIEWABLE from -617 to -664 because it was a duplicate
 * id.  Added ASC_STDCOLL_REC (-2250).  Added helper methods
 * getSortedKeys() and getGaps( ).
 * 
 * *****************  Version 140  *****************
 * User: Apota        Date: 8/28/00    Time: 6:27a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 139  *****************
 * User: Znemazie     Date: 8/21/00    Time: 10:33a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * added anon user
 * 
 * *****************  Version 138  *****************
 * User: Eroyal       Date: 8/17/00    Time: 2:44p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * sizing
 * 
 * *****************  Version 137  *****************
 * User: Sshafi       Date: 8/17/00    Time: 12:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added an Attribute ID for whether or not a file is viewable on a report
 * or not.
 * 
 * *****************  Version 136  *****************
 * User: Eroyal       Date: 8/11/00    Time: 5:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * enumerating XMen
 * 
 * *****************  Version 135  *****************
 * User: Eroyal       Date: 8/11/00    Time: 2:33p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 134  *****************
 * User: Eroyal       Date: 8/10/00    Time: 6:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 133  *****************
 * User: Sshafi       Date: 8/10/00    Time: 9:52a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 132  *****************
 * User: Eroyal       Date: 8/05/00    Time: 12:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 131  *****************
 * User: Eroyal       Date: 8/02/00    Time: 5:17p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 130  *****************
 * User: Eroyal       Date: 8/01/00    Time: 1:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 129  *****************
 * User: Sshafi       Date: 7/31/00    Time: 4:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 128  *****************
 * User: Eroyal       Date: 7/28/00    Time: 4:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * adv srch
 * 
 * *****************  Version 127  *****************
 * User: Sshafi       Date: 7/28/00    Time: 8:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 126  *****************
 * User: Sshafi       Date: 7/27/00    Time: 4:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 125  *****************
 * User: Apota        Date: 7/27/00    Time: 2:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 124  *****************
 * User: Sshafi       Date: 7/27/00    Time: 11:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 123  *****************
 * User: Sshafi       Date: 7/27/00    Time: 9:14a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 122  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 121  *****************
 * User: Sshafi       Date: 7/25/00    Time: 1:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 120  *****************
 * User: Sshafi       Date: 7/24/00    Time: 2:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 119  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Changed some assoc constants.
 * 
 * *****************  Version 118  *****************
 * User: Apota        Date: 7/20/00    Time: 1:29a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 117  *****************
 * User: Jcoles       Date: 7/19/00    Time: 7:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Changed ASC_PROD_CREATEUSER to -2090 to elim dup of -2100.
 * 
 * *****************  Version 116  *****************
 * User: Jcoles       Date: 7/19/00    Time: 6:17p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 1. Added many more association ids to support search engine stuff.
 * 2. Added VERSIONSHAREASC (-1004).
 * 
 * *****************  Version 115  *****************
 * User: Znemazie     Date: 7/18/00    Time: 11:56a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * added ENGRSPECFOLDER -3002
 * 
 * *****************  Version 114  *****************
 * User: Eroyal       Date: 7/14/00    Time: 2:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Custom Reports
 * 
 * *****************  Version 113  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added the start of the Association consts (-2000 thru -2999).
 * 
 * *****************  Version 112  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:16a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 1. Added FOLDER_OID.
 * 2. Renumbered MI_*.
 * 3. Rearranged some comments for clarity.
 * 
 * *****************  Version 111  *****************
 * User: Apota        Date: 7/07/00    Time: 5:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 109  *****************
 * User: Apota        Date: 6/26/00    Time: 6:57a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 107  *****************
 * User: Apota        Date: 6/23/00    Time: 10:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 106  *****************
 * User: Apota        Date: 6/23/00    Time: 10:08a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 105  *****************
 * User: Jcoles       Date: 6/08/00    Time: 8:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Changed all 'baseline' constants to set them equal to respective
 * non-baseline constants as oppossed to just using the same long value.
 * 
 * *****************  Version 104  *****************
 * User: Apota        Date: 5/31/00    Time: 7:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 103  *****************
 * User: Eroyal       Date: 5/25/00    Time: 3:57p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * toString
 * 
 * *****************  Version 102  *****************
 * User: Znemazie     Date: 5/22/00    Time: 12:42p
 * Updated in $/Unfinished code/zain's folder/Socrates1.2.3/com/oculussoftware/api/repi
 * 041 - added LIC_COMPASS, LIC_ACCOLADES, LIC_CONDUIT to canned attrib
 * for users (to give BMProperties a defn obj)
 * 
 * *****************  Version 101  *****************
 * User: Znemazie     Date: 5/22/00    Time: 12:28p
 * Updated in $/Unfinished code/zain's folder/Socrates1.2.3/com/oculussoftware/api/repi
 * 0041- changed MODULE_CONTRIBUTOR to MODULE_CONDUIT
 * 
 * *****************  Version 100  *****************
 * User: Znemazie     Date: 5/22/00    Time: 12:21p
 * Updated in $/Unfinished code/zain's folder/Socrates1.2.3/com/oculussoftware/api/repi
 * added MODULE_ACCOLADES, MODULE_COMPASS, MODULE_CONTRIBUTOR
 * 
 * *****************  Version 99  *****************
 * User: Apota        Date: 5/22/00    Time: 10:15a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 98  *****************
 * User: Apota        Date: 5/18/00    Time: 1:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 97  *****************
 * User: Sshafi       Date: 5/16/00    Time: 3:36p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Removed the ProductBaseline references and made all of the
 * VersionBaseline references point to the same IID as the Version
 * references.
 * 
 * *****************  Version 96  *****************
 * User: Znemazie     Date: 5/16/00    Time: 11:58a
 * Updated in $/Unfinished code/zain's folder/Socrates1.2.3/com/oculussoftware/api/repi
 * Added Organization_Install and UserGoups (previous checkin)
 * 
 * *****************  Version 95  *****************
 * User: Znemazie     Date: 5/16/00    Time: 11:37a
 * Updated in $/Unfinished code/zain's folder/Socrates1.2.3/com/oculussoftware/api/repi
 * VisualAge Java SCM Bridge
 * 
 * *****************  Version 94  *****************
 * User: Apota        Date: 5/16/00    Time: 11:04a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 93  *****************
 * User: Jcoles       Date: 5/15/00    Time: 8:42a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added MARKETINPUTQUERY.  Added some market input canned attributes
 * MI_xxxx.
 * 
 * *****************  Version 92  *****************
 * User: Apota        Date: 5/12/00    Time: 3:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 91  *****************
 * User: Apota        Date: 5/12/00    Time: 3:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 90  *****************
 * User: Apota        Date: 5/11/00    Time: 8:10a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 89  *****************
 * User: Apota        Date: 5/10/00    Time: 4:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 88  *****************
 * User: Eroyal       Date: 5/10/00    Time: 11:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 87  *****************
 * User: Apota        Date: 5/08/00    Time: 5:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 86  *****************
 * User: Znemazie     Date: 5/08/00    Time: 10:41a
 * Updated in $/Unfinished code/zain's folder/Socrates1.2.2/com/oculussoftware/api/repi
 * system and user added to IDCONST
 * 
 * *****************  Version 85  *****************
 * User: Apota        Date: 5/08/00    Time: 10:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 84  *****************
 * User: Apota        Date: 5/08/00    Time: 7:52a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 83  *****************
 * User: Eroyal       Date: 5/06/00    Time: 12:09p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 82  *****************
 * User: Apota        Date: 5/05/00    Time: 1:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 81  *****************
 * User: Apota        Date: 5/03/00    Time: 6:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 80  *****************
 * User: Apota        Date: 5/03/00    Time: 3:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 77  *****************
 * User: Jcoles       Date: 5/01/00    Time: 2:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added more directory names to business object IDCONSTs.
 * 
 * *****************  Version 76  *****************
 * User: Apota        Date: 4/28/00    Time: 6:14p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 75  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:18p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added directory names for User and UserCollections.
 * 
 * *****************  Version 74  *****************
 * User: Czhang       Date: 4/27/00    Time: 12:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * added StdFeatureColl
 * 
 * *****************  Version 73  *****************
 * User: Apota        Date: 4/27/00    Time: 10:41a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 72  *****************
 * User: Jcoles       Date: 4/26/00    Time: 2:10a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added User directory name.  Fixed Version dir name to "ProductVersion".
 * 
 * *****************  Version 71  *****************
 * User: Jcoles       Date: 4/25/00    Time: 10:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Add alternate constructor that maintains mapping from IDCONST to CRM
 * directory name of the class.
 * 
 * *****************  Version 70  *****************
 * User: Sshafi       Date: 4/25/00    Time: 11:56a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 69  *****************
 * User: Jcoles       Date: 4/24/00    Time: 9:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added OCUREPOS (650).  Bump initial hash size.
 * 
 * *****************  Version 68  *****************
 * User: Apota        Date: 4/24/00    Time: 2:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 66  *****************
 * User: Apota        Date: 4/21/00    Time: 4:05p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 65  *****************
 * User: Eroyal       Date: 4/21/00    Time: 3:40p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 64  *****************
 * User: Apota        Date: 4/21/00    Time: 3:37p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 63  *****************
 * User: Eroyal       Date: 4/21/00    Time: 3:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 62  *****************
 * User: Apota        Date: 4/21/00    Time: 9:58a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * 
 * *****************  Version 61  *****************
 * User: Jcoles       Date: 4/19/00    Time: 10:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi
 * Added dup test.  Found #408 to be a duplicate => changed id of
 * PROBLEMSTATMENTOPER to -571.
 * 
 * 
*/

/*
ISSUE HISTORY (Prior to use of VSS history mechanism)

/*
* Change Activity
*
* Issue number    Programmer      Date      Time       Description

 ISSUE BUG00118    APota          5/16      11:17 am   A canned folder need to be added. This is the unfiled folder that 
							 will be used by the conduit.
 ISSUE PRF00271    APota          5/22      11:17 am   A canned folder need to be added. This is the filed folder that 
							 will be used by the conduit.
 ISSUE PRF00292    APota          5/22      11:17 am   Contact profile form canned form.
							 
*/							


import com.oculussoftware.util.IIIDEnum;
import com.oculussoftware.util.Comparators;
import com.oculussoftware.api.sysi.OculusException;
import com.oculussoftware.repos.util.SequentialIID;

import java.util.*;

/**
* Enumerates all meta objects that the system requires to exist.
* Provides a consistent means of programmatically identifying
* the meta objects, e.g., Entity Classes, States, Attributes.
*
*/
public class IDCONST extends IIIDEnum
{
	//------------------------------------------------------------
	// Private static vars
	//------------------------------------------------------------
	private static Map _hash = new HashMap(400);
	private static Vector _dups = new Vector();
	
	//------------------------------------------------------------
	// Public constants
	//------------------------------------------------------------
	/******
	J.Coles 7/11/2000 Introduced notion of reserving number ranges for 
										particular types.  We may want to go back and renumber 
										existing consts in this manner, e.g.,
										"0 thru -200 for IRClass's", etc.
	
	Reserved -1000 thru -1999 for Attribute ids.  These will identify 
	IRAttributes (and IXClassAttrs) corresponding to fixed class attributes.
	
	Reserved -2000 thru -2900 for Association ids.  These will identify 
	IXAssocs corresponding to fixed class-to-class associations.
	
	Reserved -2901 thru -2999 for meta classes ids.  These will identify 
	meta classes such as 'Class', 'State', 'Attribute'.
	
	The next IDCONST will start at -3014. Please increment this number if you have
	added new things to this file
	
	***/
	//users
	public static final IDCONST USER_SYSTEM = new IDCONST(0);
	public static final IDCONST USER_ADMIN = new IDCONST(1);
	public static final IDCONST USER_ANON = new IDCONST(-3007);
		
	public static final IDCONST ACCESS_PUBLIC = new IDCONST(-3000);
	public static final IDCONST ACCESS_PRIVATE = new IDCONST(-3001);

	//groups
	public static final IDCONST USER_GROUP1 = new IDCONST(-801);
	public static final IDCONST USER_GROUP2 = new IDCONST(-802);
	public static final IDCONST USER_GROUP3 = new IDCONST(-803);
	public static final IDCONST USER_GROUP4 = new IDCONST(-804);
	public static final IDCONST USER_GROUP5 = new IDCONST(-805);
	public static final IDCONST USER_GROUP6 = new IDCONST(-806);
	public static final IDCONST USER_GROUP7 = new IDCONST(-807);

	//organization
	public static final IDCONST ORGANIZATION_INSTALL = new IDCONST(-808);
	
	//modules
	public static final IDCONST MODULE_ACCOLADES	 = new IDCONST(-813);
	public static final IDCONST MODULE_COMPASS  	 = new IDCONST(-814);
	public static final IDCONST MODULE_CONDUIT = new IDCONST(-815);
	
	public static final IDCONST REPOSITORY = new IDCONST(-570);
	public static final IDCONST OCUREPOS = new IDCONST(-650);
	
	public static final IDCONST COM = new IDCONST(-197);
	public static final IDCONST OCULUSSOFTWARE = new IDCONST(-198);
	public static final IDCONST API = new IDCONST(-199);
	public static final IDCONST BUSI = new IDCONST(-200);
	public static final IDCONST IBUSINESSOBJECTCOLL = new IDCONST(-102);
	public static final IDCONST IBUSINESSOBJECTLIST = new IDCONST(-103);
	public static final IDCONST IBUSINESSOBJECT = new IDCONST(-104);
	public static final IDCONST COMMON = new IDCONST(-201);
	public static final IDCONST ORG = new IDCONST(-202);
	public static final IDCONST ICOMPANY = new IDCONST(-16);
	public static final IDCONST ICOMPANYCOLL = new IDCONST(-17);
	public static final IDCONST ICOMPANYLIST = new IDCONST(-18);
	public static final IDCONST IGROUP = new IDCONST(-19);
	public static final IDCONST IGROUPLIST = new IDCONST(-20);
	public static final IDCONST IUSER = new IDCONST(-21);
	public static final IDCONST IUSERCOLL = new IDCONST(-22);
	public static final IDCONST IUSERLIST = new IDCONST(-23);
	public static final IDCONST IGROUPCOLL = new IDCONST(-24);
	public static final IDCONST IORGANIZATION = new IDCONST(-25);
	public static final IDCONST IORGANIZATIONCOLL = new IDCONST(-26);
	public static final IDCONST IORGANIZATIONLIST = new IDCONST(-27);
	public static final IDCONST PROCESS = new IDCONST(-203);
	public static final IDCONST IPROCESSCHANGELIST = new IDCONST(-29);
	public static final IDCONST IPROCESSCHANGE = new IDCONST(-30);
	public static final IDCONST IPROCESSCHANGECOLL = new IDCONST(-31);
	public static final IDCONST IPROCESSROLECOLL = new IDCONST(-32);
	public static final IDCONST IROLEASSIGNMENTCOLL = new IDCONST(-33);
	public static final IDCONST IPROCESSROLE = new IDCONST(-34);
	public static final IDCONST IROLEASSIGNMENT = new IDCONST(-35);
	public static final IDCONST MKT = new IDCONST(-205);
	public static final IDCONST COMM = new IDCONST(-206);
	public static final IDCONST ALERTTYPE = new IDCONST(-59);
	public static final IDCONST TOPICKIND = new IDCONST(-62);
	public static final IDCONST NOTIFICATIONKIND = new IDCONST(-65);
	public static final IDCONST IATTACHMENT = new IDCONST(-43);
	public static final IDCONST IENGRSPECCOLL = new IDCONST(-44);
	public static final IDCONST IENGRSPECLIST = new IDCONST(-45);
	public static final IDCONST IFILECOLL = new IDCONST(-46);
	public static final IDCONST IFILELIST = new IDCONST(-47);
	public static final IDCONST IHYPERLINK = new IDCONST(-48);
	public static final IDCONST IHYPERLINKCOLL = new IDCONST(-49);
	public static final IDCONST IHYPERLINKLIST = new IDCONST(-50);
	public static final IDCONST INOTE = new IDCONST(-51);
	public static final IDCONST IFILE = new IDCONST(-52);
	public static final IDCONST IENGRSPEC = new IDCONST(-53);
	public static final IDCONST IDISCUSSIONTOPIC = new IDCONST(-54);
	public static final IDCONST IDISCUSSIONTOPICCOLL = new IDCONST(-55);
	public static final IDCONST IDISCUSSIONTOPICLIST = new IDCONST(-56);
	public static final IDCONST INOTIFICATION = new IDCONST(-57);
	public static final IDCONST INOTIFICATIONCOLL = new IDCONST(-58);
	public static final IDCONST IALERTCONFIG = new IDCONST(-60);
	public static final IDCONST IALERTCONFIGCOLL = new IDCONST(-61);
	public static final IDCONST ISEMANTICLINKCOLL = new IDCONST(-63);
	public static final IDCONST ISEMANTICLINK = new IDCONST(-64);
	public static final IDCONST PROD = new IDCONST(-207);
	public static final IDCONST IBASELINECATEGORYCOLL = new IDCONST(-68);
	public static final IDCONST ICATEGORY = new IDCONST(-67);
	public static final IDCONST IBASELINECATEGORY = ICATEGORY;
	public static final IDCONST ICATEGORYCOLL = new IDCONST(-70);
	public static final IDCONST ICATEGORYLIST = new IDCONST(-71);
	public static final IDCONST IFEATURE = new IDCONST(-72);
	public static final IDCONST IFEATUREREVISION = new IDCONST(-73);
	public static final IDCONST IFEATURECOLL = new IDCONST(-74);
	public static final IDCONST IFEATURELIST = new IDCONST(-75);
	public static final IDCONST IFEATUREREVISIONCOLL = new IDCONST(-76);
	public static final IDCONST IFEATUREREVISIONLIST = new IDCONST(-77);
	public static final IDCONST IPRODUCTCOLL = new IDCONST(-79);
	public static final IDCONST IPRODUCTLIST = new IDCONST(-80);
	public static final IDCONST IPRODUCTVERSION = new IDCONST(-81);
	public static final IDCONST IPRODUCTVERSIONCOLL = new IDCONST(-82);
	public static final IDCONST IPRODUCTVERSIONLIST = new IDCONST(-83);
	public static final IDCONST IPROJECT = new IDCONST(-84);
	public static final IDCONST IPRODUCT = new IDCONST(-85);
	public static final IDCONST IFEATURECATEGORYLINK = new IDCONST(-86);
	public static final IDCONST IASSIGNEDFEATURECOLL = new IDCONST(-87);
	public static final IDCONST IBASELINEFEATURECATEGORYLINK = IFEATURECATEGORYLINK;
	public static final IDCONST IVERSIONBASELINE = IPRODUCTVERSION;
	public static final IDCONST IVERSIONBASELINECOLL = new IDCONST(-90);
	public static final IDCONST STDCOLL = new IDCONST(-208);
	public static final IDCONST ISTANDARDSCOLLECTION = new IDCONST(-92);
	public static final IDCONST ISTANDARDSCOLLECTIONCOLL = new IDCONST(-93);
	public static final IDCONST ISTANDARDSCOLLECTIONLIST = new IDCONST(-94);
	public static final IDCONST ISTDFEATURELINK = new IDCONST(-385);
	public static final IDCONST IPARENTSTANDARDSCOLLECTIONCOLL = new IDCONST(-389);
	public static final IDCONST ISTDFEATURELINKCOLL = new IDCONST(-513);
	public static final IDCONST ISTDFEATURELINKLIST = new IDCONST(-519);

	
	
	public static final IDCONST BUS = new IDCONST(-211);
	public static final IDCONST BUSINESSOBJECT = new IDCONST(-194);
	public static final IDCONST BUSINESSOBJECTCOLL = new IDCONST(-195);
	public static final IDCONST BUSINESSOBJECTLIST = new IDCONST(-196);
//  public static final IDCONST COMMON = new IDCONST(-212);
//  public static final IDCONST ORG = new IDCONST(-213);
	public static final IDCONST COMPANYLIST = new IDCONST(-108);
	public static final IDCONST GROUP = new IDCONST(-109, "Group");
	public static final IDCONST GROUPLIST = new IDCONST(-110);
	public static final IDCONST USERLIST = new IDCONST(-111);
	public static final IDCONST COMPANY = new IDCONST(-112);
	public static final IDCONST COMPANYCOLL = new IDCONST(-113);
	public static final IDCONST USER = new IDCONST(-114, "User");
	public static final IDCONST USERCOLL = new IDCONST(-115);
	public static final IDCONST GROUPCOLL = new IDCONST(-116);
	public static final IDCONST ORGANIZATION = new IDCONST(-117, "Organization");
	public static final IDCONST ORGANIZATIONCOLL = new IDCONST(-118);
	public static final IDCONST USERGROUPCOLL = new IDCONST(-119, "UserGroupColl");
	public static final IDCONST USERORGCOLL = new IDCONST(-120);
	public static final IDCONST GROUPGROUPCOLL = new IDCONST(-121);
	public static final IDCONST HASUSERSGROUPCOLL = new IDCONST(-122);
	public static final IDCONST USERXMLCOLL = new IDCONST(-123);
//  public static final IDCONST PROCESS = new IDCONST(-236);
	public static final IDCONST PROCESSCHANGE = new IDCONST(-125);
	public static final IDCONST PROCESSCHANGELIST = new IDCONST(-126);
	public static final IDCONST PROCESSCHANGECOLL = new IDCONST(-127);
	public static final IDCONST PROCESSROLE = new IDCONST(-128, "Role");
	public static final IDCONST PROCESSROLECOLL = new IDCONST(-129);
	public static final IDCONST ROLEASSIGNMENT = new IDCONST(-130);
	public static final IDCONST ROLEASSIGNMENTCOLL = new IDCONST(-131);
//  public static final IDCONST MKT = new IDCONST(-251);
//  public static final IDCONST COMM = new IDCONST(-252);
	public static final IDCONST ENGRSPEC = new IDCONST(-134, "EngrSpecFile");
	public static final IDCONST ENGRSPECFOLDER = new IDCONST(-3002, "EngrSpecFolder");  
	public static final IDCONST ATTACHMENT = new IDCONST(-135);
	public static final IDCONST FILE = new IDCONST(-136, "File");
	public static final IDCONST ENGRSPECCOLL = new IDCONST(-137);
	public static final IDCONST ENGRSPECLIST = new IDCONST(-138);
	public static final IDCONST FILECOLL = new IDCONST(-139);
	public static final IDCONST FILELIST = new IDCONST(-140);
	public static final IDCONST HYPERLINK = new IDCONST(-141, "HyperLink");
	public static final IDCONST HYPERLINKCOLL = new IDCONST(-142);
	public static final IDCONST HYPERLINKLIST = new IDCONST(-143);
	public static final IDCONST NOTE = new IDCONST(-144);
	public static final IDCONST DISCUSSIONTOPIC = new IDCONST(-145, "DiscussionTopic");
	public static final IDCONST DISCUSSIONTOPICCOLL = new IDCONST(-146);
	public static final IDCONST DISCUSSIONTOPICLIST = new IDCONST(-147);
	public static final IDCONST NOTIFICATION = new IDCONST(-148, "Notification");
	public static final IDCONST NOTIFICATIONCOLL = new IDCONST(-149);
	public static final IDCONST ALERTCONFIG = new IDCONST(-150);
	public static final IDCONST ALERTCONFIGCOLL = new IDCONST(-151);
	public static final IDCONST SEMANTICLINK = new IDCONST(-152, "SemanticLink");  
	public static final IDCONST SEMANTICLINKCOLL = new IDCONST(-153);
	public static final IDCONST OBJECTNOTIFICATIONCOLL = new IDCONST(-154);
//  public static final IDCONST PROD = new IDCONST(-293);
	public static final IDCONST CATEGORY = new IDCONST(-158, "Category");
	public static final IDCONST CATEGORYCOLL = new IDCONST(-159);
	public static final IDCONST BASELINECATEGORY = CATEGORY;
	public static final IDCONST BASELINECATEGORYCOLL = new IDCONST(-157);
	public static final IDCONST CATEGORYFEATURECOLL = new IDCONST(-160);
	public static final IDCONST FEATURECOLL = new IDCONST(-161);
	public static final IDCONST CATEGORYFEATURELIST = new IDCONST(-162);
	public static final IDCONST FEATURELIST = new IDCONST(-163);
	public static final IDCONST CATEGORYLIST = new IDCONST(-164);
	public static final IDCONST FEATURE = new IDCONST(-165, "Feature");
	public static final IDCONST FEATUREREVISION = new IDCONST(-166);
	public static final IDCONST FEATUREREVISIONCOLL = new IDCONST(-167);
	public static final IDCONST FEATUREREVISIONLIST = new IDCONST(-168);
	public static final IDCONST PRODUCT = new IDCONST(-169, "Product");
	public static final IDCONST PRODUCTCOLL = new IDCONST(-171);
	public static final IDCONST PRODUCTLIST = new IDCONST(-172);
	public static final IDCONST PRODUCTVERSION = new IDCONST(-173, "ProductVersion");
	public static final IDCONST VERSIONSHAREASC = new IDCONST(-3004);
	public static final IDCONST PRODUCTVERSIONCOLL = new IDCONST(-174);
	public static final IDCONST PRODUCTVERSIONLIST = new IDCONST(-175);
	public static final IDCONST FEATURECATEGORYLINK = new IDCONST(-176, "FeatureCategoryLink");
	public static final IDCONST ASSIGNEDFEATURECOLL = new IDCONST(-177);
	public static final IDCONST BASELINECATEGORYFEATURECOLL = new IDCONST(-178);
	public static final IDCONST BASELINEFEATURECATEGORYLINK = FEATURECATEGORYLINK;
	public static final IDCONST SUBPRODUCTVERSIONCOLL = new IDCONST(-180);
	public static final IDCONST VERSIONBASELINE = PRODUCTVERSION;
	public static final IDCONST VERSIONBASELINECOLL = new IDCONST(-182);
//  public static final IDCONST STDCOLL = new IDCONST(-340);
	public static final IDCONST STANDARDSCOLLECTION = new IDCONST(-184, "StandardsCollection");
	public static final IDCONST STANDARDSCOLLECTIONCOLL = new IDCONST(-185);
	public static final IDCONST STANDARDSCOLLECTIONLIST = new IDCONST(-186);
	public static final IDCONST STDFEATURELINK = new IDCONST(-386, "StdFeatureLink");
	public static final IDCONST PARENTSTANDARDSCOLLECTIONCOLL = new IDCONST(-390);  
	public static final IDCONST STDFEATURELINKCOLL = new IDCONST(-514);
	public static final IDCONST STDFEATURELINKLIST = new IDCONST(-520);
	public static final IDCONST STDFEATURECOLL = new IDCONST(-678);
	

	
	
	public static final IDCONST IDISCUSSIONREPLYCOLL = new IDCONST(-408);
	public static final IDCONST DISCUSSIONREPLYCOLL = new IDCONST(-409);
	public static final IDCONST COMMCONFIG = new IDCONST(-509);
	
	//StateMachines
	public static final IDCONST ALTERNATIVESTATEMACHINE = new IDCONST(-528); 
	public static final IDCONST ATTACHMENTSTATEMACHINE = new IDCONST(-255);
	public static final IDCONST CATEGORYSTATEMACHINE = new IDCONST(-298);
	public static final IDCONST DISCUSSIONTOPICSTATEMACHINE = new IDCONST(-275);
	public static final IDCONST FEATURECATEGORYLINKSTATEMACHINE = new IDCONST(-330);
	public static final IDCONST FOLDERSTATEMACHINE = new IDCONST(-350);
	public static final IDCONST FOLDERINPUTLINKSTATEMACHINE = new IDCONST(-531);
	public static final IDCONST GROUPSTATEMACHINE = new IDCONST(-216);
	public static final IDCONST HYPERLINKSTATEMACHINE = new IDCONST(-267);
	
	public static final IDCONST NOTIFICATIONSTATEMACHINE = new IDCONST(-281);
	public static final IDCONST ORGANIZATIONSTATEMACHINE = new IDCONST(-232);
	public static final IDCONST PROBLEMSTATEMENTSTATEMACHINE = new IDCONST(-406);
	public static final IDCONST PRODUCTSTATEMACHINE = new IDCONST(-316);
	public static final IDCONST PRODUCTVERSIONSTATEMACHINE = new IDCONST(-324);
	public static final IDCONST REACTIONSTATEMACHINE = new IDCONST(-405);
	public static final IDCONST STANDARDSCOLLECTIONSTATEMACHINE = new IDCONST(-341);
	public static final IDCONST STDFEATURELINKSTATEMACHINE = new IDCONST(-387);
	public static final IDCONST USERSTATEMACHINE = new IDCONST(-226);
	
	
	/*
	**** MARKETINPUT STATE RELATED STUFF
	Egan:
	
	Although the term MARKETINPUT was spawned as a generic way to refer to an input
	there are infact 3 very distinct types of input which are treated differently 
	by me these are STANDARD INPUT, ARTICLE INPUT & QUESTION INPUT. 
	These all are first class entities in themselves and therefore will have  
	1 statemachine associated with each of them and the states OPEN & CLOSE
	will be the same for these STATEMACHINES.
	
	It will be my reponsibility to check for Saleem's tree filter to work 
	to see whether the object is open or closed based on the 
	statemachine its using. I will put that code on IMarketInput
	
	The only DB changes I foresee are addition of 3 statemachines
	
	with controlclassids as -393,-394,-395 respectively for 
	STANDARD, ARTICLE & QUESTION INPUT. There will be correspnding OPEN & CLOSE
	states for each of these inputs.
	
	For safety sake I will keep the MARKETINPUT STATEMACHINE as it is and won't 
	touch it. Its just that instead of referering to it directly you will have 
	to go through the object to find out if it is open or closed
	*/
	
	public static final IDCONST STANDARDINPUTSTATEMACHINE = new IDCONST(-655);
	public static final IDCONST STANDARDINPUTOPEN = new IDCONST(-658);
	public static final IDCONST STANDARDINPUTCLOSED = new IDCONST(-659);
	
	public static final IDCONST ARTICLEINPUTSTATEMACHINE = new IDCONST(-656);
	public static final IDCONST ARTICLEINPUTOPEN = new IDCONST(-660);
	public static final IDCONST ARTICLEINPUTCLOSED = new IDCONST(-661);
	
	public static final IDCONST QUESTIONINPUTSTATEMACHINE = new IDCONST(-657);
	public static final IDCONST QUESTIONINPUTOPEN = new IDCONST(-662);
	public static final IDCONST QUESTIONINPUTCLOSED = new IDCONST(-663);  
	
	public static final IDCONST MARKETINPUTSTATEMACHINE = new IDCONST(-348);
	public static final IDCONST MARKETINPUTOPEN = new IDCONST(-349);
	public static final IDCONST MARKETINPUTCLOSED = new IDCONST(-530);
	
  //DefaultStates
  public static final IDCONST ALTERNATIVEDEFAULTSTATE = new IDCONST(-742);
  public static final IDCONST ATTACHMENTDEFAULTSTATE = new IDCONST(-741);
  public static final IDCONST DISCUSSIONDEFAULTSTATE = new IDCONST(-740);
  public static final IDCONST FOLDERDEFAULTSTATE = new IDCONST(-739);
  public static final IDCONST FOLDERINPUTLINKDEFAULTSTATE = new IDCONST(-738);
  public static final IDCONST GROUPDEFAULTSTATE = new IDCONST(-737);
  public static final IDCONST HYPERLINKDEFAULTSTATE = new IDCONST(-736);
  public static final IDCONST NOTIFICATIONDEFAULTSTATE = new IDCONST(-735);
  public static final IDCONST ORGANIZATIONDEFAULTSTATE = new IDCONST(-734);
  public static final IDCONST REACTIONDEFAULTSTATE = new IDCONST(-733);
  public static final IDCONST STANDARDSCOLLECTIONDEFAULTSTATE = new IDCONST(-732);
  public static final IDCONST STDFEATURELINKDEFAULTSTATE = new IDCONST(-731);
  public static final IDCONST USERDEFAULTSTATE = new IDCONST(-730);
  
	
	//ProblemStatement States
	public static final IDCONST PROBLEMSTATEMENTOPEN = new IDCONST(-683);
	public static final IDCONST PROBLEMSTATEMENTCLOSED = new IDCONST(-527);

	//Product States
	public static final IDCONST PRODUCTCOMPASS = new IDCONST(-317);
	public static final IDCONST PRODUCTACTIVE = new IDCONST(-496);
	public static final IDCONST PRODUCTARCHIVED = new IDCONST(-497);
	
	//Product Transitions
	public static final IDCONST PRODUCTCOMPASS_PRODUCTACTIVE = new IDCONST(-501);
	public static final IDCONST PRODUCTACTIVE_PRODUCTARCHIVED = new IDCONST(-502);
	public static final IDCONST PRODUCTARCHIVED_PRODUCTACTIVE = new IDCONST(-503);
	
	//Version States
	public static final IDCONST VERSIONCOMPASS = new IDCONST(-325);
	public static final IDCONST VERSIONUNDERCONST = new IDCONST(-498);
	public static final IDCONST VERSIONCOMPLETE = new IDCONST(-499);
	public static final IDCONST VERSIONACTIVE = new IDCONST(-500);
	
	//Version Transitions
	public static final IDCONST VERSIONCOMPASS_VERSIONUNDERCONST = new IDCONST(-504);
	public static final IDCONST VERSIONUNDERCONST_VERSIONACTIVE = new IDCONST(-505);
	public static final IDCONST VERSIONACTIVE_VERSIONCOMPLETE = new IDCONST(-506);
	
	//Category States
	public static final IDCONST CATEGORYCOMPASS = new IDCONST(-299);
	public static final IDCONST CATEGORYACTIVE = new IDCONST(-525);
	
	//Category Transition
	public static final IDCONST CATEGORYCOMPASS_CATEGORYACTIVE = new IDCONST(-434);
	
	//FeatureCategoryLink States
	public static final IDCONST MYCONCEPTS = new IDCONST(-507);
	public static final IDCONST DEFINPROGRESSACCEPTED = new IDCONST(-331);
	public static final IDCONST DEFINPROGRESSREVIEW = new IDCONST(-526);
	public static final IDCONST SUBTOEM = new IDCONST(-412);
	public static final IDCONST EMTOMM  = new IDCONST(-413);
	public static final IDCONST ASSIGNENG = new IDCONST(-414);
	public static final IDCONST ASSIGNTEST = new IDCONST(-415);
	public static final IDCONST ENGTOEM = new IDCONST(-416);
	public static final IDCONST DEVINPROGRESS = new IDCONST(-418);
	public static final IDCONST TESTINPROGRESS = new IDCONST(-419);
	public static final IDCONST ENGREQASSISTANCE = new IDCONST(-420);
	public static final IDCONST TESTREQASSISTANCE = new IDCONST(-421);
	public static final IDCONST DEVCOMPLETE = new IDCONST(-422);
	public static final IDCONST TESTPASSED = new IDCONST(-423);
	public static final IDCONST TESTFAILED = new IDCONST(-424);
	public static final IDCONST ASSIGNQAM = new IDCONST(-425);
	public static final IDCONST TESTTOQAM = new IDCONST(-427);
	public static final IDCONST QAMTOEM = new IDCONST(-508);
	public static final IDCONST QAMTOMM = new IDCONST(-431);
	public static final IDCONST QAMVERIFIED = new IDCONST(-432);
	public static final IDCONST MMTOQAM = new IDCONST(-433);
	public static final IDCONST MMVERIFIED = new IDCONST(-426);
	public static final IDCONST COMPASS = new IDCONST(-428);
	public static final IDCONST DEFERRED = new IDCONST(-429);
	
	//FeatureCategoryLink Transitions
  public static final IDCONST MYCONCEPTS_DEFERRED = new IDCONST(-729);
  public static final IDCONST DEFERRED_COMPASS = new IDCONST(-728);
  public static final IDCONST DEFERRED_DEFINPROGRESSREVIEW = new IDCONST(-727);
  public static final IDCONST DEFERRED_DEFINPROGRESSACCEPTED = new IDCONST(-726);
  public static final IDCONST COMPASS_DEFERRED = new IDCONST(-725);
	public static final IDCONST COMPASS_DEFINPROGRESSREVIEW = new IDCONST(-532);
	public static final IDCONST COMPASS_DEFINPROGRESSACCEPTED = new IDCONST(-724);
  public static final IDCONST DEFINPROGRESSREVIEW_DEFERRED = new IDCONST(-723);
  public static final IDCONST DEFINPROGRESSREVIEW_COMPASS = new IDCONST(-722);
  public static final IDCONST DEFINPROGRESSREVIEW_DEFINPROGRESSACCEPTED = new IDCONST(-721);
  public static final IDCONST DEFINPROGRESSACCEPTED_DEFERRED = new IDCONST(-720);
  public static final IDCONST DEFINPROGRESSACCEPTED_COMPASS = new IDCONST(-719);
  public static final IDCONST DEFINPROGRESSACCEPTED_DEFINPROGRESSREVIEW = new IDCONST(-718);
  public static final IDCONST DEFINPROGRESSACCEPTED_MMVERIFIED = new IDCONST(-717);
  public static final IDCONST EMTOMM_MMVERIFIED = new IDCONST(-716);
  public static final IDCONST QAMTOMM_MMVERIFIED = new IDCONST(-715);
  public static final IDCONST QAMVERIFIED_MMVERIFIED = new IDCONST(-714);
  public static final IDCONST MMVERIFIED_DEFERRED = new IDCONST(-713);
  public static final IDCONST MMVERIFIED_COMPASS = new IDCONST(-712);
  public static final IDCONST MMVERIFIED_DEFINPROGRESSREVIEW = new IDCONST(-711);
  public static final IDCONST MMVERIFIED_DEFINPROGRESSACCEPTED = new IDCONST(-710);

  
	
 //Alok  
	public static final IDCONST IFOLDER = new IDCONST(-96);
	public static final IDCONST IFOLDERINPUTLINK = new IDCONST(-494);
	public static final IDCONST IFOLDERCOLL = new IDCONST(-97);  
	public static final IDCONST IMARKETINPUT = new IDCONST(-38);
	public static final IDCONST IMARKETINPUTCOLL = new IDCONST(-39);
	public static final IDCONST IMARKETINPUTLIST = new IDCONST(-40);
	public static final IDCONST IREACTION = new IDCONST(-363);
	public static final IDCONST IREACTIONCOLL = new IDCONST(-364);
	public static final IDCONST IPROBLEMSTATEMENT = new IDCONST(-99);
	public static final IDCONST IPROBLEMSTATEMENTCOLL = new IDCONST(-100);
	public static final IDCONST IPROBLEMSTATEMENTLIST = new IDCONST(-101);
	public static final IDCONST IALTERNATIVE = new IDCONST(-362);
	public static final IDCONST IALTERNATIVECOLL = new IDCONST(-365);  
	public static final IDCONST ISTANDARDINPUT = new IDCONST(-396);  
	public static final IDCONST IARTICLEINPUT = new IDCONST(-397);  
	public static final IDCONST IQUESTIONINPUT = new IDCONST(-398);  
	
	
	public static final IDCONST FOLDER = new IDCONST(-189, "Folder");
	public static final IDCONST FOLDERINPUTLINK = new IDCONST(-495,"FolderInputLink");
	public static final IDCONST FOLDERCOLL = new IDCONST(-190);  
	public static final IDCONST MARKETINPUT = new IDCONST(-188, "MarketInput");  
	public static final IDCONST MARKETINPUTCOLL = new IDCONST(-192);  
	public static final IDCONST REACTION = new IDCONST(-366, "Reaction");  
	public static final IDCONST REACTIONCOLL = new IDCONST(-368);  
	public static final IDCONST PROBLEMSTATEMENT = new IDCONST(-210,"ProblemStatement");  
	public static final IDCONST PROBLEMSTATEMENTCOLL = new IDCONST(-372);  
	public static final IDCONST ALTERNATIVE = new IDCONST(-367);  
	public static final IDCONST ALTERNATIVECOLL = new IDCONST(-369);
	public static final IDCONST MARKETINPUTQUERY = new IDCONST(-770, "MktInputQuery");
	
	public static final IDCONST STANDARDINPUT = new IDCONST(-393,"MarketInput");  
	public static final IDCONST ARTICLEINPUT = new IDCONST(-394,"MarketInput");  
	public static final IDCONST QUESTIONINPUT = new IDCONST(-395,"MarketInput");  
	
	public static final IDCONST CHAR = new IDCONST(-373);  
	public static final IDCONST LONGCHAR = new IDCONST(-374);  
	public static final IDCONST INTEGER = new IDCONST(-375);  
	public static final IDCONST DECIMAL = new IDCONST(-376);  
	public static final IDCONST BOOLEAN = new IDCONST(-377);  
	public static final IDCONST TIME = new IDCONST(-378);  
	public static final IDCONST PASSWORD = new IDCONST(-379);  
	public static final IDCONST DUMMYENUM = new IDCONST(-596);  
	public static final IDCONST DUMMYLITERAL = new IDCONST(-597);  
	
	public static final IDCONST MKTMGRROLE = new IDCONST(-380);
	public static final IDCONST ENGMGRROLE = new IDCONST(-381);
	public static final IDCONST QAMGRROLE = new IDCONST(-430);
	public static final IDCONST ENGROLE   = new IDCONST(-382);
	public static final IDCONST TESTROLE   = new IDCONST(-383);
	public static final IDCONST TECHWRITERROLE   = new IDCONST(-684);
	public static final IDCONST VERSIONTEAMROLE = new IDCONST(-687);
	
	//ACCOLADES CANNED ATTRIBUTES
	public static final IDCONST FIRSTNAME = new IDCONST(-575);  
	public static final IDCONST LASTNAME = new IDCONST(-576);  
	public static final IDCONST EMAIL = new IDCONST(-577);  
	public static final IDCONST ACTIVE = new IDCONST(-578);  
	public static final IDCONST AUTOLOGOUT = new IDCONST(-579);  
	public static final IDCONST NUMTRIALS = new IDCONST(-580);  
	public static final IDCONST EMAILSTATUS = new IDCONST(-581);  
	public static final IDCONST LOGINID = new IDCONST(-582);  
	public static final IDCONST USERPASSWORD = new IDCONST(-583);  
	public static final IDCONST PHONE = new IDCONST(-584);
	public static final IDCONST LIC_COMPASS = new IDCONST(-816);
	public static final IDCONST LIC_ACCOLADES = new IDCONST(-817);
	public static final IDCONST LIC_CONDUIT = new IDCONST(-818);
	public static final IDCONST BUSADDR1 = new IDCONST(-585);  
	public static final IDCONST BUSADDR2 = new IDCONST(-586);  
	public static final IDCONST CITY = new IDCONST(-587);  
	public static final IDCONST USERSTATE = new IDCONST(-588);  
	public static final IDCONST COUNTRY = new IDCONST(-589);  
	public static final IDCONST ZIPCODE = new IDCONST(-590);  
	public static final IDCONST ISINSTALLOWNER = new IDCONST(-591);  
	public static final IDCONST ORDERNUM = new IDCONST(-606);  
	public static final IDCONST AUTOREV = new IDCONST(-605);  
	public static final IDCONST MESSAGE_ATTACHED = new IDCONST(-572);
	public static final IDCONST FILE_ATTACHED = new IDCONST(-573);
	public static final IDCONST LINK_ATTACHED = new IDCONST(-574);  
	public static final IDCONST SPEC_ATTACHED = new IDCONST(-607);  
	public static final IDCONST NAME = new IDCONST(-533);  
	public static final IDCONST DESC = new IDCONST(-534);
	public static final IDCONST SUMMARY = new IDCONST(-654);
	public static final IDCONST CREATOR = new IDCONST(-535);
	public static final IDCONST CREATION_DATE = new IDCONST(-536);
	public static final IDCONST STATE = new IDCONST(-537);
	public static final IDCONST CLASS = new IDCONST(-538);
//  public static final IDCONST PRODUCT_NAME = new IDCONST(-539);
	public static final IDCONST TARGET_RELEASE_DATE = new IDCONST(-540);
	public static final IDCONST ESTIMATED_RELEASE_DATE = new IDCONST(-541);
	public static final IDCONST ACTUAL_RELEASE_DATE = new IDCONST(-542);  
	public static final IDCONST DEV_START_DATE = new IDCONST(-552);
	public static final IDCONST DEV_END_DATE = new IDCONST(-553);
	public static final IDCONST EST_DEV_TIME = new IDCONST(-554);
	public static final IDCONST ACTUAL_DEV_TIME = new IDCONST(-555);
	public static final IDCONST ACTUAL_TEST_TIME = new IDCONST(-556);
	public static final IDCONST ASSIGNED_DEVELOPER= new IDCONST(-557);
	public static final IDCONST ASSIGNED_TESTER = new IDCONST(-558);
	public static final IDCONST PERCENT_COMPLETE= new IDCONST(-559);    
	public static final IDCONST MMGR = new IDCONST(-543);
	public static final IDCONST EMGR = new IDCONST(-544);
	public static final IDCONST EDIT_DATE = new IDCONST(-545);
	public static final IDCONST PATH = new IDCONST(-546);
	public static final IDCONST REVISION_LABEL = new IDCONST(-547);
	public static final IDCONST FEATREV_COMMENT = new IDCONST(-571);  
	public static final IDCONST FEAT_TEST = new IDCONST(-549);
	public static final IDCONST FEAT_DIFFICULTY = new IDCONST(-550);  
	public static final IDCONST FEAT_PRIORITY = new IDCONST(-551);
	public static final IDCONST ACKMASK = new IDCONST(-592);
	public static final IDCONST SUBJECT = new IDCONST(-593);
	public static final IDCONST BODY = new IDCONST(-594);
	public static final IDCONST NOTEKIND = new IDCONST(-595);
	public static final IDCONST FILENAME = new IDCONST(-651);
	public static final IDCONST FILESIZE = new IDCONST(-652);
	public static final IDCONST FILEVIEWABLE = new IDCONST(-664);
	public static final IDCONST URL = new IDCONST(-653);
	public static final IDCONST FEAT_TEST_ENUM = new IDCONST(-567);
	public static final IDCONST FEAT_DIFFICULTY_ENUM = new IDCONST(-568);  
	public static final IDCONST FEAT_PRIORITY_ENUM = new IDCONST(-569);
	public static final IDCONST FEAT_TYPE = new IDCONST(-685);
	public static final IDCONST FEAT_TYPE_ENUM = new IDCONST(-686);
	public static final IDCONST ORIGINATOR = new IDCONST(-743);
	public static final IDCONST ORIGINATOR_ENUM = new IDCONST(-744);
	
	
	// --------------- CANNED ATTRIBUTES Start -----------------------
	// problem statement attrs
	public static final IDCONST PS_PRIORITY = new IDCONST(-681);
	public static final IDCONST PS_PRIORITY_ENUM = new IDCONST(-682);
	
	//User
	public static final IDCONST USER_OID        = new IDCONST(-1136);
	
	//Org
	public static final IDCONST ORG_OID         = new IDCONST(-1137);
	
	 //Class
	public static final IDCONST CLASS_OID       = new IDCONST(-1142);
	
	//State
	public static final IDCONST STATE_OID       = new IDCONST(-1140);
	public static final IDCONST STATE_NAME      = new IDCONST(-1139);
	
	// Market Input attrs
	public static final IDCONST MI_OID          = new IDCONST(-1000);
	public static final IDCONST MI_CLASSID      = new IDCONST(-1001);
	public static final IDCONST MI_VISIBLEID    = new IDCONST(-1002);
	public static final IDCONST MI_CREATIONDATE = new IDCONST(-1003);
	public static final IDCONST MI_COMMENT      = new IDCONST(-1005);
	public static final IDCONST MI_BASECLASSID  = new IDCONST(-1006);
	public static final IDCONST MI_STATEID      = new IDCONST(-1138);
	
	// ps
	public static final IDCONST PS_OID          = new IDCONST(-1010);
	public static final IDCONST PS_CLASSID      = new IDCONST(-1011);
	public static final IDCONST PS_CREATIONDATE = new IDCONST(-1012);
	public static final IDCONST PS_NAME         = new IDCONST(-1013);
	public static final IDCONST PS_DESCRIPTION  = new IDCONST(-1014);
	// IRAttributes for Folders attrs
	public static final IDCONST FOLDER_OID      = new IDCONST(-1030);
	public static final IDCONST FOLDER_CLASSID      = new IDCONST(-1031);
	// Market Input attrs
	public static final IDCONST REACTION_OID          = new IDCONST(-1020);
	public static final IDCONST REACTION_CLASSID      = new IDCONST(-1021);
	public static final IDCONST REACTION_CREATIONDATE = new IDCONST(-1022);
	public static final IDCONST REACTION_WEIGHT       = new IDCONST(-1141);
  // reaction 'comment' is not in the Reaction table.  Its is treated as an extended attr.
	public static final IDCONST COMMENT               = new IDCONST(-548);  
	
	
	// product
	public static final IDCONST PRODUCT_OID      = new IDCONST(-1040);
	public static final IDCONST PRODUCT_CLASSID  = new IDCONST(-1041);
	public static final IDCONST PRODUCT_NAME     = NAME;
	public static final IDCONST PRODUCT_DESC     = DESC;
	public static final IDCONST PRODUCT_STATEID  = new IDCONST(-1044);
	// version
	public static final IDCONST VERSION_OID      = new IDCONST(-1050);
	public static final IDCONST VERSION_CLASSID  = new IDCONST(-1051);
	public static final IDCONST VERSION_NAME     = NAME;
	public static final IDCONST VERSION_DESC     = DESC;
	public static final IDCONST VERSION_STATEID  = new IDCONST(-1054);
		// category
	public static final IDCONST CATEGORY_OID      = new IDCONST(-1060);
	public static final IDCONST CATEGORY_CLASSID  = new IDCONST(-1061);
	public static final IDCONST CATEGORY_NAME     = NAME;
	public static final IDCONST CATEGORY_DESC     = DESC;
	public static final IDCONST CATEGORY_PARENTID      = new IDCONST(-1064);
	public static final IDCONST CATEGORY_VERSIONID  = new IDCONST(-1065);
	public static final IDCONST CATEGORY_STATEID    = new IDCONST(-1066);
	public static final IDCONST CATEGORY_VISIBLEID    = new IDCONST(-1067);
	// feature
	public static final IDCONST FEATURE_OID      = new IDCONST(-1070);
	public static final IDCONST FEATURE_CLASSID  = new IDCONST(-1071);
	public static final IDCONST FEATURE_LATESTREVID  = new IDCONST(-1072);
	public static final IDCONST FEATURE_VISIBLEID   = new IDCONST(-1073);
	public static final IDCONST FEATURE_ISSTANDARD  = new IDCONST(-1143);
	// feature revision
	public static final IDCONST FEATUREREVISION_OID      = new IDCONST(-1080);
	public static final IDCONST FEATUREREVISION_CLASSID  = new IDCONST(-1081);
	// feature category link
	public static final IDCONST FEATURECATEGORYLINK_OID      = new IDCONST(-1090);
	public static final IDCONST FEATURECATEGORYLINK_CLASSID  = new IDCONST(-1091);
	public static final IDCONST FEATURECATEGORYLINK_PINNEDREVID  = new IDCONST(-1092);
	public static final IDCONST FEATURECATEGORYLINK_STATEID    = new IDCONST(-1093);
	public static final IDCONST FEATURECATEGORYLINK_PERCENTCOMPLETE = PERCENT_COMPLETE;
	public static final IDCONST FEATURECATEGORYLINK_ESTDEVDUR = new IDCONST(-1094);
	public static final IDCONST FEATURECATEGORYLINK_ESTTESTDUR = new IDCONST(-1095);
	public static final IDCONST FEATURECATEGORYLINK_DEVSTARTDATE = new IDCONST(-1096);
	public static final IDCONST FEATURECATEGORYLINK_DEVENDDATE = new IDCONST(-1097);
	public static final IDCONST FEATURECATEGORYLINK_TESTENDDATE = new IDCONST(-1098);
	public static final IDCONST FEATURECATEGORYLINK_ACTDUR = new IDCONST(-1099);
	// standards coll
	public static final IDCONST STDCOLL_OID      = new IDCONST(-1100);
	public static final IDCONST STDCOLL_CLASSID  = new IDCONST(-1101);
	public static final IDCONST STDCOLL_NAME     = new IDCONST(-1102);
	public static final IDCONST STDCOLL_DESC     = new IDCONST(-1103);
  
	// feature standard link
	public static final IDCONST STDFEATLINK_OID      = new IDCONST(-1110);
	public static final IDCONST STDFEATLINK_CLASSID  = new IDCONST(-1111);
	// feature standard link
	public static final IDCONST DISCTOPIC_OID      = new IDCONST(-1120);
	public static final IDCONST DISCTOPIC_CLASSID  = new IDCONST(-1121);
	public static final IDCONST DISCTOPIC_PAROBJECTID  = new IDCONST(-1122);
	public static final IDCONST DISCTOPIC_SUBJECT  = new IDCONST(-1123);
	public static final IDCONST DISCTOPIC_BODY  = new IDCONST(-1124);
	public static final IDCONST DISCTOPIC_TOPICKIND  = new IDCONST(-1125);
	// feature standard link
	public static final IDCONST TRANSCOMMENT_OID      = new IDCONST(-1130);
	public static final IDCONST TRANSCOMMENT_CLASSID  = new IDCONST(-1131);
	public static final IDCONST TRANSCOMMENT_PAROBJECTID  = new IDCONST(-1132);
	public static final IDCONST TRANSCOMMENT_SUBJECT  = new IDCONST(-1133);
	public static final IDCONST TRANSCOMMENT_BODY  = new IDCONST(-1134);
	public static final IDCONST TRANSCOMMENT_TOPICKIND  = new IDCONST(-1135);
	
	// --------------- CANNED ATTRIBUTES Stop -----------------------
	
	// --------------- ASSOCIATIONS Start -----------------------
	// question input
	public static final IDCONST ASC_QMKTINP_SRCUSER = new IDCONST(-2000);
	public static final IDCONST ASC_QMKTINP_CREATEUSER = new IDCONST(-2002);
	public static final IDCONST ASC_QMKTINP_STATE = new IDCONST(-2003);
	public static final IDCONST ASC_QMKTINP_CLASS = new IDCONST(-2004);
	// standard input
	public static final IDCONST ASC_SMKTINP_SRCUSER = new IDCONST(-2010);
	public static final IDCONST ASC_SMKTINP_CREATEUSER = new IDCONST(-2012);
	public static final IDCONST ASC_SMKTINP_STATE = new IDCONST(-2013);
	public static final IDCONST ASC_SMKTINP_CLASS = new IDCONST(-2014);
	// article input
	public static final IDCONST ASC_AMKTINP_SRCUSER = new IDCONST(-2020);
	public static final IDCONST ASC_AMKTINP_CREATEUSER = new IDCONST(-2022);
	public static final IDCONST ASC_AMKTINP_STATE = new IDCONST(-2023);
	public static final IDCONST ASC_AMKTINP_CLASS = new IDCONST(-2032);
	// winloss input
	public static final IDCONST ASC_WMKTINP_SRCUSER = new IDCONST(-2015);
	public static final IDCONST ASC_WMKTINP_CREATEUSER = new IDCONST(-2016);
	public static final IDCONST ASC_WMKTINP_STATE = new IDCONST(-2017);
	public static final IDCONST ASC_WMKTINP_CLASS = new IDCONST(-2018);
	// summary input
	public static final IDCONST ASC_SUMMKTINP_SRCUSER = new IDCONST(-2005);
	public static final IDCONST ASC_SUMMKTINP_CREATEUSER = new IDCONST(-2006);
	public static final IDCONST ASC_SUMMKTINP_STATE = new IDCONST(-2007);
	public static final IDCONST ASC_SUMMKTINP_CLASS = new IDCONST(-2008);
	// folder link
	public static final IDCONST ASC_FLINK_FOLDER  = new IDCONST(-2025);
	public static final IDCONST ASC_FLINK_SMKTINP  = new IDCONST(-2026);
	public static final IDCONST ASC_FLINK_QMKTINP  = new IDCONST(-2027);
	public static final IDCONST ASC_FLINK_AMKTINP  = new IDCONST(-2028);
	public static final IDCONST ASC_FLINK_WMKTINP  = new IDCONST(-2024);
	public static final IDCONST ASC_FLINK_SUMMKTINP  = new IDCONST(-2029);
	
	// reaction
	public static final IDCONST ASC_REACT_FLINK = new IDCONST(-2030);
	public static final IDCONST ASC_REACT_CREATEUSER = new IDCONST(-2031);
	// problem statement
	public static final IDCONST ASC_PROBSTM_FOLDER = new IDCONST(-2040);
	public static final IDCONST ASC_PROBSTM_CREATEUSER = new IDCONST(-2041);
	public static final IDCONST ASC_PROBSTM_STATE = new IDCONST(-2061);
	// folder self reference
	public static final IDCONST ASC_FOLDER_REC     = new IDCONST(-2050);
	// user org
	public static final IDCONST ASC_USER_ORG       = new IDCONST(-2060);

	// product
	public static final IDCONST ASC_PROD_CREATEUSER = new IDCONST(-2090);
	public static final IDCONST ASC_PROD_STATE      = new IDCONST(-2209);
	public static final IDCONST ASC_PROD_CLASS      = new IDCONST(-2211);
	// version
	public static final IDCONST ASC_VER_CREATEUSER = new IDCONST(-2100);
	public static final IDCONST ASC_VER_PROD = new IDCONST(-2101);
	public static final IDCONST ASC_VER_SHARE = new IDCONST(-2102);
	public static final IDCONST ASC_VER_STATE      = new IDCONST(-2210);
	public static final IDCONST ASC_VER_CLASS      = new IDCONST(-2212);
	// category
	public static final IDCONST ASC_CAT_CREATEUSER = new IDCONST(-2110);
	public static final IDCONST ASC_CAT_VERS = new IDCONST(-2111);
	public static final IDCONST ASC_CAT_REC = new IDCONST(-2112);
	public static final IDCONST ASC_CAT_STATE = new IDCONST(-2154);
	public static final IDCONST ASC_CAT_CLASS      = new IDCONST(-2213);
	// feature
	public static final IDCONST ASC_FEAT_CREATEUSER = new IDCONST(-2120);
	public static final IDCONST ASC_FEAT_CLASS      = new IDCONST(-2214);
	
	// feature link
	public static final IDCONST ASC_FEATLINK_CREATEUSER = new IDCONST(-2130);
	public static final IDCONST ASC_FEATLINK_STATE = new IDCONST(-2131);
	public static final IDCONST ASC_FEATLINK_FEAT = new IDCONST(-2132);
	public static final IDCONST ASC_FEATLINK_CAT = new IDCONST(-2133);
	// feature revision
	public static final IDCONST ASC_FEATREV_CREATEUSER = new IDCONST(-2140);
	public static final IDCONST ASC_FEATREV_FEAT = new IDCONST(-2141);
	// std feature link
	public static final IDCONST ASC_STDFEATLINK_FEAT = new IDCONST(-2152);
	public static final IDCONST ASC_STDFEATLINK_STDCOLL = new IDCONST(-2153);
  // std collections
	public static final IDCONST ASC_STDCOLL_REC = new IDCONST(-2250);
	// discussions
	public static final IDCONST ASC_DISC_PROD = new IDCONST(-2200);
	public static final IDCONST ASC_DISC_VERS = new IDCONST(-2201);
	public static final IDCONST ASC_DISC_CAT  = new IDCONST(-2202);
	public static final IDCONST ASC_DISC_FLINK = new IDCONST(-2203);
	public static final IDCONST ASC_DISC_SFLINK = new IDCONST(-2203888);
	public static final IDCONST ASC_DISC_FEAT = new IDCONST(-2204);
	public static final IDCONST ASC_DISC_WMKTINP = new IDCONST(-2206);
	public static final IDCONST ASC_DISC_SMKTINP = new IDCONST(-2207);
	public static final IDCONST ASC_DISC_AMKTINP = new IDCONST(-2208);
	public static final IDCONST ASC_DISC_FOLDLINK = new IDCONST(-2203777);
  public static final IDCONST ASC_DISC_PS = new IDCONST(-2203999);

  // transaction comments
	public static final IDCONST ASC_TRANS_FLINK = new IDCONST(-2215);

	// --------------- ASSOCIATIONS End -----------------------
	
	// --------------- META CLASSES Start -----------------------
	public static final IDCONST IMCLS_STATE = new IDCONST(-2912);
	public static final IDCONST MCLS_STATE = new IDCONST(-2900);
	public static final IDCONST IMCLS_CLASS = new IDCONST(-2913);
	public static final IDCONST MCLS_CLASS = new IDCONST(-2901);
	
	// --------------- META CLASSES End -----------------------
	
	
	//Compass enumliterals (need to be there at ship time)
	public static final IDCONST INPUT_IMPORTANCE= new IDCONST(-560);
	public static final IDCONST INPUT_IMPORTANCE_ENUM= new IDCONST(-599);  
	public static final IDCONST SAMPLE_ENTRY= new IDCONST(-746);  
	
	
	
	//Accolades & Compass enumliterals (need to be there at ship time)  
	/*
	
	Please do not code to these literals
	Use the enumeration and the getEnumliterals on it to get to these.
	
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL1= new IDCONST(-600);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL2= new IDCONST(-601);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL3= new IDCONST(-602);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL4= new IDCONST(-603);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL5= new IDCONST(-604);  
	
	public static final IDCONST FEAT_PRIORITY_HIGHEST = new IDCONST(-709);  
	public static final IDCONST FEAT_PRIORITY_HIGH = new IDCONST(-710);  
	public static final IDCONST FEAT_PRIORITY_MEDIUM = new IDCONST(-711);  
	public static final IDCONST FEAT_PRIORITY_LOW = new IDCONST(-712);  
	public static final IDCONST FEAT_PRIORITY_LOWEST = new IDCONST(-713);    
	public static final IDCONST FEAT_TEST_REQUIRED = new IDCONST(-714);  
	public static final IDCONST FEAT_TEST_RECOMMENDED = new IDCONST(-715);  
	public static final IDCONST FEAT_TEST_NOTNEEDED = new IDCONST(-716);    
	public static final IDCONST FEAT_DIFFICULTY_VERY_DIFFICULT = new IDCONST(-717);  
	public static final IDCONST FEAT_DIFFICULTY_DIFFICULT = new IDCONST(-718);  
	public static final IDCONST FEAT_DIFFICULTY_MEDIUM = new IDCONST(-719);  
	public static final IDCONST FEAT_DIFFICULTY_EASY = new IDCONST(-720);  
	public static final IDCONST FEAT_DIFFICULTY_VERY_EASY = new IDCONST(-721);    
	public static final IDCONST FEAT_TYPE_FUNCTIONAL = new IDCONST(-722);  
	public static final IDCONST FEAT_TYPE_NONFUNCTIONAL = new IDCONST(-723);  
	public static final IDCONST FEAT_TYPE_COMPONENT = new IDCONST(-724);  
	public static final IDCONST FEAT_TYPE_INTERFACE = new IDCONST(-725);  
	public static final IDCONST FEAT_TYPE_INDUSTRYSTANDARD = new IDCONST(-726);  
	public static final IDCONST FEAT_TYPE_CONSTRAINT = new IDCONST(-727);  
	public static final IDCONST FEAT_TYPE_PERFORMANCE = new IDCONST(-728);  
	public static final IDCONST FEAT_TYPE_OPTION = new IDCONST(-729);
	public static final IDCONST INPUT_TYPE_ENUMLITERAL1 = new IDCONST(-730);  
	public static final IDCONST INPUT_TYPE_ENUMLITERAL2 = new IDCONST(-731);  
	public static final IDCONST INPUT_TYPE_ENUMLITERAL3 = new IDCONST(-732);  
	public static final IDCONST INPUT_TYPE_ENUMLITERAL4 = new IDCONST(-733);  
	public static final IDCONST PS_PRIORITY_HIGHEST = new IDCONST(-734);  
	public static final IDCONST PS_PRIORITY_HIGH = new IDCONST(-735);  
	public static final IDCONST PS_PRIORITY_MEDIUM = new IDCONST(-736);  
	public static final IDCONST PS_PRIORITY_LOW = new IDCONST(-737);  
	public static final IDCONST PS_PRIORITY_LOWEST = new IDCONST(-738);      
	public static final IDCONST IMPACT_MAJOR = new IDCONST(-739);  
	public static final IDCONST IMPACT_MINOR = new IDCONST(-740);  
	public static final IDCONST IMPACT_MODERATE = new IDCONST(-741);    
	public static final IDCONST IMPACT_UNKNOWN = new IDCONST(-742);    
	*/
	
	public static final IDCONST RANK = new IDCONST(-561);
	public static final IDCONST WEIGHT = new IDCONST(-562);
	public static final IDCONST WEIGHT_ENUM = new IDCONST(-819);
	public static final IDCONST MAX_SCORE = new IDCONST(-563);
	public static final IDCONST MIN_SCORE = new IDCONST(-564);
	public static final IDCONST MEAN_SCORE = new IDCONST(-565);
	public static final IDCONST MEDIAN_SCORE = new IDCONST(-566);
	
	public static final IDCONST TEMPLATE_NAME = new IDCONST(-670);
	public static final IDCONST TEMPLATE_DESC = new IDCONST(-671);  
	public static final IDCONST TEMPLATE_CREATOR = new IDCONST(-672);
	public static final IDCONST TEMPLATE_CREATION_DATE = new IDCONST(-673);  
	public static final IDCONST RECORD_NAME = new IDCONST(-674);
	public static final IDCONST RECORD_DESC = new IDCONST(-675);  
	public static final IDCONST RECORD_CREATOR = new IDCONST(-676);
	public static final IDCONST RECORD_CREATION_DATE = new IDCONST(-677);
	
	public static final IDCONST TITLE = new IDCONST(-689);
	public static final IDCONST INPUT_DESC = new IDCONST(-745);
	//public static final IDCONST ORIGINATOR = new IDCONST(-690);
	public static final IDCONST OK_TO_CONTACT = new IDCONST(-691);
	public static final IDCONST INPUT_TYPE = new IDCONST(-692);
	public static final IDCONST INPUT_TYPE_ENUM = new IDCONST(-693);
	public static final IDCONST EXAMPLE_USE_CASE = new IDCONST(-694);
	public static final IDCONST CUSTOMER_BENEFIT = new IDCONST(-695);
	public static final IDCONST ATTACHMENT_AUTHOR = new IDCONST(-696);
	public static final IDCONST ATTACHMENT_SOURCE = new IDCONST(-697);
	public static final IDCONST ATTACHMENT_DATE = new IDCONST(-698);
	public static final IDCONST ARTICLE_TYPE = new IDCONST(-699);
	public static final IDCONST ARTICLE_TYPE_ENUM = new IDCONST(-704);  
	//Standard Input Notification (SIN)
	public static final IDCONST SIN = new IDCONST(-700);
	public static final IDCONST SIN_ENUM = new IDCONST(-703);
	public static final IDCONST IMPACT = new IDCONST(-701);
	public static final IDCONST IMPACT_ENUM = new IDCONST(-702);
	public static final IDCONST DEPARTMENT = new IDCONST(-705);
	public static final IDCONST DEPARTMENT_ENUM = new IDCONST(-706);
	public static final IDCONST INDUSTRY = new IDCONST(-707);
	public static final IDCONST INDUSTRY_ENUM = new IDCONST(-708);
	
	//CANNED FORMS  
	public static final IDCONST DEFAULT_FEATURE_FORM = new IDCONST(-608);  
	public static final IDCONST DEFAULT_CATEGORY_FORM = new IDCONST(-609);  
	public static final IDCONST DEFAULT_PRODUCT_FORM = new IDCONST(-610);  
	public static final IDCONST DEFAULT_VERSION_FORM = new IDCONST(-611);  
	public static final IDCONST DEFAULT_STDINPUT_FORM = new IDCONST(-612);  
	public static final IDCONST DEFAULT_ARTICLEINPUT_FORM = new IDCONST(-613);  
	public static final IDCONST DEFAULT_REACTION_FORM = new IDCONST(-614);  
	public static final IDCONST DEFAULT_PS_FORM = new IDCONST(-615);  
	public static final IDCONST DEFAULT_CONTACT_FORM = new IDCONST(-679);  
	public static final IDCONST DEFAULT_ORGANIZATION_FORM = new IDCONST(-680);  
  
  public static final IDCONST DEFAULT_WINLOSS_FORM = new IDCONST(-3008);  
  public static final IDCONST REASON_FOR_DECISION = new IDCONST(-3009);  
  public static final IDCONST COMPETING_PRODUCTS = new IDCONST(-3010);  
  public static final IDCONST OTHER_COMMENTS = new IDCONST(-3011);  
  public static final IDCONST WINLOSS_TYPE = new IDCONST(-3012);  
  public static final IDCONST WINLOSS_TYPE_ENUM = new IDCONST(-3013);  
	
	
	public static final IDCONST DEPENDENCIES = new IDCONST(-616);
	public static final IDCONST ISSTANDARD = new IDCONST(-617);
	public static final IDCONST ISCHANGE = new IDCONST(-618);
	public static final IDCONST REVNAME = new IDCONST(-619);
	public static final IDCONST TEST_END_DATE = new IDCONST(-620);
	public static final IDCONST EST_TEST_TIME = new IDCONST(-621);
	
	public static final IDCONST DEFAULT_ATTR_GROUP = new IDCONST(-688);
	public static final IDCONST UNFILED_FOLDER = new IDCONST(-800);
	
	//ISSUE PRF00271
	public static final IDCONST FILED_FOLDER = new IDCONST(-809);
	public static final IDCONST CONTACT_TYPE= new IDCONST(-810);
	public static final IDCONST CONTACT_TYPE_ENUM= new IDCONST(-811);
	public static final IDCONST MAIL_DROP= new IDCONST(-812);
	
	//PRF00527
	public static final IDCONST ATTACHMENT_FILE = new IDCONST(-820);
	public static final IDCONST ATTACHMENT_LINK= new IDCONST(-821);
	public static final IDCONST BLOB= new IDCONST(-822);
	public static final IDCONST LINK= new IDCONST(-823);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL1= new IDCONST(-824);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL2= new IDCONST(-825);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL3= new IDCONST(-826);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL4= new IDCONST(-827);
	public static final IDCONST INPUT_IMPORTANCE_ENUMLITERAL5= new IDCONST(-828);
	public static final IDCONST WEIGHT_ENUMLITERAL1= new IDCONST(-829);
	public static final IDCONST WEIGHT_ENUMLITERAL2= new IDCONST(-830);
	public static final IDCONST WEIGHT_ENUMLITERAL3= new IDCONST(-831);
	public static final IDCONST WEIGHT_ENUMLITERAL4 =new IDCONST(-832);
	public static final IDCONST WEIGHT_ENUMLITERAL5= new IDCONST(-833);
	public static final IDCONST IMPORTEDINPUT = new IDCONST(-834);
	public static final IDCONST IIMPORTEDINPUT = new IDCONST(-835);
	public static final IDCONST IMPORTEDINPUTSTATEMACHINE = new IDCONST(-836);
	public static final IDCONST IMPORTEDINPUTOPEN = new IDCONST(-837);
	public static final IDCONST IMPORTEDINPUTCLOSED = new IDCONST(-838);
	
	public static final IDCONST SAMPLE_CONTEXT = new IDCONST(-3005);
	public static final IDCONST SAMPLE_DISPOSITION = new IDCONST(-3006);
	
	
	//Making WinLoss & Summary actual classes & not hacks
	public static final IDCONST IWINLOSSINPUT = new IDCONST(-2902);
	public static final IDCONST WINLOSSINPUT = new IDCONST(-2903, "MarketInput");
	public static final IDCONST WINLOSSINPUTSTATEMACHINE = new IDCONST(-2904);
	public static final IDCONST WINLOSSINPUTOPEN = new IDCONST(-2905);
	public static final IDCONST WINLOSSINPUTCLOSED = new IDCONST(-2906);   
	public static final IDCONST ISUMMARYINPUT = new IDCONST(-2907);
	public static final IDCONST SUMMARYINPUT = new IDCONST(-2908, "MarketInput");
	public static final IDCONST SUMMARYINPUTSTATEMACHINE = new IDCONST(-2909);
	public static final IDCONST SUMMARYINPUTOPEN = new IDCONST(-2910);
	public static final IDCONST SUMMARYINPUTCLOSED = new IDCONST(-2911);
	 
	//------------------------------------------------------------
	// Private instance variables
	//------------------------------------------------------------
	private String _dirName = null;
	
  //------------------------------------------------------------
  // Private constructor(s)
  //------------------------------------------------------------
  
  private IDCONST(long i) 
  { 
   super(i);
   // keep track of all IDCONSTs via hash...
   Object old = _hash.put(new Long(i), this);
   if (old != null)
     _dups.add(old);
  }

  private IDCONST(long i, String dirName) 
  {
  this(i);
  _dirName = dirName;
  }

  //------------------------------------------------------------
  // Public instance methods
  //------------------------------------------------------------
  public String getDirName( )
  {
  return _dirName;
  }

   public String toString()
   {
     return getIIDValue().toString();
   }

  //------------------------------------------------------------
  // Public static methods
  //------------------------------------------------------------
  
  public static Vector getDups()
  {
    return _dups;
  }

  public static List getSortedKeys()
  {
    List sortedKeys = Arrays.asList(_hash.keySet().toArray());
    Collections.sort(sortedKeys, Comparators.COMP_LONGS);
    return sortedKeys;
  }
  
  /** Returns all gaps in terms of a list of Long[2]. */
  public static List getGaps()
  {
    Long pair[] = null;
    List retList = new Vector();
    List sortedKeys = getSortedKeys();

    Iterator ikeys = sortedKeys.iterator();
    Long prevKey = null;
    while(ikeys.hasNext()) {
      Long Lkey = (Long) ikeys.next();
      if (prevKey != null) {
        long lprev = prevKey.longValue();
        long lkey = Lkey.longValue();
        if ((lkey - lprev) > 1) {
          pair = new Long[2];
          pair[0] = new Long(lprev+1);
          pair[1] = new Long(lkey-1);
          retList.add(pair);
        }
      }
      prevKey = Lkey;
    }
    return retList;
  }

  /** Decodes long id to valid IDCONST.
  * NOTE: do we really need to throw exception?
  */
  public static IDCONST getInstance(long i) throws OculusException
  {
  IDCONST retVal = null;
  try {
    retVal = (IDCONST) _hash.get(new Long(i));
  }
  catch (ClassCastException ex) {
    throw new OculusException(ex);
  }
  if (retVal == null) {
    throw new OculusException("Invalid long value for IDCONST ("+i+")");
  }
  return retVal;
  }

  /** Overload */
  public static IDCONST getInstance(IIID id) throws OculusException
  {
  return getInstance(id.getLongValue());
  }//end getInstance            
  
}