package com.oculussoftware.bus.xmeta;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.busi.mkt.comm.*;

import com.oculussoftware.system.*;
import com.oculussoftware.repos.xmeta.*;
import com.oculussoftware.repos.query.*;

import com.oculussoftware.util.*;

import java.util.*;

/*
* $Workfile: XMen.java $
* Create Date:  5-12-2000
* Description: Enumerates the 'extended' meta model as per the
*              xmeta framework for the Accolades and Compass
*              class model.
*
* Author J. Coles
* Version 1.2
*
*/

/*
* $History: XMen.java $
 * 
 * *****************  Version 40  *****************
 * User: Jcoles       Date: 9/22/00    Time: 9:42a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Issue 2653.   Enums std coll attrs.
 * 
 * *****************  Version 39  *****************
 * User: Jcoles       Date: 9/18/00    Time: 5:42p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Added remaining canned attrs for user and org.  Fixed Primitive decls
 * for some attrs including 'description' attrs to LONG_CHARs (issue 2637)
 * to support filtering out of 'sort by' lists.
 * 
 * *****************  Version 38  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Bug Fix: #2537
 * 
 * *****************  Version 37  *****************
 * User: Sshafi       Date: 9/14/00    Time: 12:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Bug Fix: 2544
 * 
 * *****************  Version 36  *****************
 * User: Jcoles       Date: 9/12/00    Time: 5:47p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * mini-bugs.
 * 
 * *****************  Version 35  *****************
 * User: Jcoles       Date: 9/11/00    Time: 5:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Issue 2003 - Changed ATTR_FEATREV_COMMENT to use new
 * IDCONST.FEATREV_COMMENT instead of .COMMENT (meant for Reaction
 * comment.)
 * 
 * *****************  Version 34  *****************
 * User: Jcoles       Date: 9/11/00    Time: 2:32p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 2091 - added 'creator' associations to the query assoc chains for all
 * market input types.
 * 
 * *****************  Version 33  *****************
 * User: Jcoles       Date: 9/09/00    Time: 2:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Issue 2091:  Big changes: 
 * 1. market input attr assocs are made using new .addAttr( ) which takes
 * existing IXClassAttr.
 * 2. The boolean isUserConfigurable is now assigned to the class-to-attr
 * assoc, not to the attr itself.
 * 3. Using a const LIST_DEFER and LIST_ALWAYS as arg to addAttr( ) to
 * make things more readable.
 * 
 * *****************  Version 32  *****************
 * User: Jcoles       Date: 9/08/00    Time: 3:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Significant change for issue 2091 (getting attributes to show up
 * properly in search attr lists.)
 * 
 * *****************  Version 31  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Issue 2120 - added calls to set certain IXClasses as role-based.
 * 
 * *****************  Version 30  *****************
 * User: Eroyal       Date: 8/30/00    Time: 2:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * flipped those damn bits again
 * 
 * *****************  Version 29  *****************
 * User: Eroyal       Date: 8/30/00    Time: 9:45a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * working on 2091
 * 
 * *****************  Version 28  *****************
 * User: Jcoles       Date: 8/29/00    Time: 3:11p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Support search scoping issues 1925, 2150.  Added ASC_STDCOLL_REC.
 * Added static index table settings for CLS_CATEGORY and CLS_STDCOLL.
 * 
 * *****************  Version 27  *****************
 * User: Sshafi       Date: 8/28/00    Time: 2:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 26  *****************
 * User: Eroyal       Date: 8/24/00    Time: 3:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * flip the bit again
 * 
 * *****************  Version 25  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:09p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Removed SMI_RECORDNAME.
 * 
 * *****************  Version 24  *****************
 * User: Eroyal       Date: 8/17/00    Time: 2:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * sizing
 * 
 * *****************  Version 23  *****************
 * User: Jcoles       Date: 8/17/00    Time: 2:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 22  *****************
 * User: Eroyal       Date: 8/11/00    Time: 5:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * enumerating XMen
 * 
 * *****************  Version 21  *****************
 * User: Eroyal       Date: 8/10/00    Time: 6:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 20  *****************
 * User: Sshafi       Date: 8/10/00    Time: 9:52a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 19  *****************
 * User: Eroyal       Date: 8/05/00    Time: 12:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 18  *****************
 * User: Sshafi       Date: 8/05/00    Time: 10:15a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * i'm a slacker and i always forget to check in code so that my friend
 * egan can work
 * 
 * *****************  Version 17  *****************
 * User: Sshafi       Date: 8/03/00    Time: 3:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Stupid OcuRepos mistake
 * 
 * *****************  Version 16  *****************
 * User: Jcoles       Date: 8/03/00    Time: 11:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 1. Added isConfigurable boolean for attributes decls.
 * 2. Revived NAME and DESC fields for Feature, Cat, Prod, Vers but
 * setting isConfigurable=true.
 * 3. Added Feature priority, testleve, diff level.
 * 
 * *****************  Version 15  *****************
 * User: Sshafi       Date: 8/02/00    Time: 5:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 14  *****************
 * User: Eroyal       Date: 8/02/00    Time: 5:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 13  *****************
 * User: Eroyal       Date: 8/02/00    Time: 10:28a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Changed to correct column name for state oid
 * 
 * *****************  Version 12  *****************
 * User: Eroyal       Date: 8/01/00    Time: 1:58p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 11  *****************
 * User: Sshafi       Date: 7/31/00    Time: 4:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 7/28/00    Time: 8:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 7/27/00    Time: 4:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 8  *****************
 * User: Sshafi       Date: 7/27/00    Time: 11:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 7  *****************
 * User: Sshafi       Date: 7/27/00    Time: 9:14a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 6  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 5  *****************
 * User: Sshafi       Date: 7/25/00    Time: 1:48p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 7/24/00    Time: 2:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Modified the assoc enum list.  Added assoc chain enumeration.  Add
 * assoc display name.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/20/00    Time: 9:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:26p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
*/

/**
* Enumerates the 'extended' meta model as per the
* xmeta framework for the Accolades and Compass
* class model.
*
* Developer Notes:
*
* NOTE 1: As of 9/9/2000 it is now possible to create an attribute independently of associating the attr 
*         with a class.  This manifests by a new overload of IXClass.addAttr( ) that takes an IXClassAttr.
*         Here are some guidelines:
*
*   1. If an attribute of a given IIID is associated with more than one IXClass,
*      create the IXClassAttr first and then use IXClass.addAttr( ) to assoc the attr
*      with the IXClasses, e.g., 
*
*         ATTR1 = new ClassAttrMeta(  );
*         ...
*         CLASS1.addAttr(ATTR1, ...);
*         CLASS2.addAttr(ATTR1, ...);
*
*   2. Two attributes are the 'same' attribute if they have the same IIID.getLongValue().  Therefore, you should
*      never have two IXClass.addAttr(IIID, ..., ...,  ) calls with same IIID.  If you find this below, correct
*      the situation using guideline 1, above.
*
* NOTE 2: Some slight differences have arises between the semantics of xmeta elements (e.g., IXClass, IXClassAttr)
*         and corresponding bmr elements (e.g, IRClass, IRAttribute).
*
*   1. xmeta attributes can be 'references', i.e., foreign keys, to other objects whereas bmr attribute are purely
*      primitive data types (e.g., CHAR, INT, DATE).
*
* NOTE 3: Neither the xmeta layer nor the bmr layer have the concept of true class inheritence.  It would be nice
*         in both layers if we could define base classes such as "BusinessObject" where we could define common
*         attributes and associations that get inherited by sub-classes.  Since we don't have inheritence, 
*         we have to (in both layers) exhastively associate all common attributes and all class-to-class 
*         associations with all classes.  E.g., you will see the OBJECTID, CLASSID, STATEID and corresponding
*         associations applied to each class.
*         
*         On a related note, since we did not enumerate 'reference' attributes or the oids in the bmr layer (i.e.,
*         they don't manifest in the Attribute table), we sometime have difference IIIDs for attributes or associations
*         that are logically the same.  E.g., there are MI_OID and FEATURE_OID declarations with differing IIIDs.
*         This doesn't necessarily hurt us, it has resulted in some redudancy in the enums below and might make
*         data migration a bit more difficult.
*/

public class XMen
{
  //-------------------------------------------------------------------
  // Public constants and static methods
  //-------------------------------------------------------------------
  
  // all the classes we need for search...
  public static final IXClass CLS_PRODUCT           = new ClassMeta(IDCONST.PRODUCT, "PRODUCT", "prd", "Product");
  public static final IXClass CLS_VERSION           = new ClassMeta(IDCONST.PRODUCTVERSION, "PRODUCTVERSION", "ver", "Version");
  public static final IXClass CLS_VERSHAREASC       = new ClassMeta(IDCONST.VERSIONSHAREASC, "VERSHARELINK", "vshr", "Version Share Link");
  public static final IXClass CLS_CATEGORY          = new ClassMeta(IDCONST.CATEGORY, "CATEGORY", "cat", "Category");
  public static final IXClass CLS_CATFEATLINK       = new ClassMeta(IDCONST.FEATURECATEGORYLINK, "CATFEATURELINK", "cfl", "Version Feature Link");
  public static final IXClass CLS_FEATURE           = new ClassMeta(IDCONST.FEATURE, "FEATUREFEATUREREVISION", "ftr", "Feature");
//  public static final IXClass CLS_FEATUREREVISION   = new ClassMeta(IDCONST.FEATUREREVISION, "FEATUREREVISION", "fr", "Feature Revision");
  public static final IXClass CLS_STDCOLL           = new ClassMeta(IDCONST.STANDARDSCOLLECTION, "STANDARDSCOLLECTION", "std", "Standards Collection");
  public static final IXClass CLS_STDFEATLINK       = new ClassMeta(IDCONST.STDFEATURELINK, "STDFEATURELINK", "stdlink", "Standards Feature Link");

  public static final IXClass CLS_FOLDER            = new ClassMeta(IDCONST.FOLDER, "FOLDER", "fld", "Folder");
  public static final IXClass CLS_FOLDERINPUTLINK   = new ClassMeta(IDCONST.FOLDERINPUTLINK, "FOLDERINPUTLINK", "flnk", "Market Input Link");
  //
  public static final IXClass CLS_STANDARDINPUT     = new ClassMeta(IDCONST.STANDARDINPUT, "MARKETINPUT", "mi", "Standard Input");
  public static final IXClass CLS_QUESTIONINPUT     = new ClassMeta(IDCONST.QUESTIONINPUT, "MARKETINPUT", "mi", "Question & Answer Input");
  public static final IXClass CLS_ARTICLEINPUT      = new ClassMeta(IDCONST.ARTICLEINPUT, "MARKETINPUT", "mi", "Article Input");
  public static final IXClass CLS_WINLOSSINPUT      = new ClassMeta(IDCONST.WINLOSSINPUT, "MARKETINPUT", "mi", "Win/Loss Input");
  public static final IXClass CLS_SUMMARYINPUT      = new ClassMeta(IDCONST.SUMMARYINPUT, "MARKETINPUT", "mi", "Summary Input");
  //
  public static final IXClass CLS_PROBLEMSTATEMENT  = new ClassMeta(IDCONST.PROBLEMSTATEMENT, "PROBLEMSTATEMENT", "ps", "Problem Statement");
  public static final IXClass CLS_REACTION          = new ClassMeta(IDCONST.REACTION, "REACTION", "rct", "Reaction");
  
  public static final IXClass CLS_USER              = new ClassMeta(IDCONST.USER, "APPUSER", "au", "User");
  public static final IXClass CLS_ORGANIZATION      = new ClassMeta(IDCONST.ORGANIZATION, "ORGANIZATION", "org", "Organization");
  public static final IXClass CLS_DISCUSSION        = new ClassMeta(IDCONST.DISCUSSIONTOPIC, "DISCUSSIONTOPIC", "dt", "Discussion Topic");
  public static final IXClass CLS_TRANSACTIONCOMMENT = new ClassMeta(IDCONST.DISCUSSIONTOPIC, "DISCUSSIONTOPIC", "tc", "Transaction Comment");
  
  public static final IXClass CLS_STATE        = new ClassMeta(IDCONST.MCLS_STATE, "STATE", "sta", "State");
  
  public static final IXClass CLS_CLASS         = new ClassMeta(IDCONST.MCLS_CLASS, "CLASS", "cls", "Class");
  
  static {
    CLS_FOLDER.setIsRecursive(true);
    CLS_FOLDER.setIndexTable("FOLDERCHILDINDEX");
    CLS_DISCUSSION.setIsCommon(true);
    CLS_CATEGORY.setIsRecursive(true);
    CLS_CATEGORY.setIndexTable("CATCHILDINDEX");
    CLS_CATEGORY.setIndexParentColName("PARENTCATID");
    CLS_CATEGORY.setIndexChildColName("CHILDCATID");
    CLS_STDCOLL.setIsRecursive(true);
    CLS_STDCOLL.setIndexTable("STDCOLLCHILDINDEX");
    
    CLS_VERSION.setIsRoleParent(true);
    CLS_CATEGORY.setIsRoleParent(true);
    CLS_CATFEATLINK.setIsRoleBased(true);
  }
  
  private static final boolean LIST_DEFER = true;  // if TRUE, XMR will not include the attr in a user-selectable list unless the attr is associated with the class via BMR tables.
  private static final boolean LIST_ALWAYS = false;  // if FALSE, XMR will always include the attr in user select lists.
//  private static final boolean ISREFERENCE = true;
  
  // Folder
  public static final IXClassAttr ATTR_FOLDER_OID    = CLS_FOLDER.addAttr(IDCONST.FOLDER_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);

  // Common Market Input attrs
  public static final IXClassAttr ATTR_MI_OID          = new ClassAttrMeta(IDCONST.MI_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_CLASSID      = new ClassAttrMeta(IDCONST.MI_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_VISIBLEID    = new ClassAttrMeta(IDCONST.MI_VISIBLEID.getIIDValue(), "VISIBLEID", "ID", Primitive.INTEGER, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_CREATIONDATE = new ClassAttrMeta(IDCONST.MI_CREATIONDATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_FILE         = new ClassAttrMeta(IDCONST.ATTACHMENT_FILE.getIIDValue(), "FILEATTACHED", "File Attached", Primitive.BOOLEAN, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_LINK         = new ClassAttrMeta(IDCONST.ATTACHMENT_LINK.getIIDValue(), "LINKATTACHED", "Link Attached", Primitive.BOOLEAN, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_STATEID      = new ClassAttrMeta(IDCONST.MI_STATEID.getIIDValue(), "STATEID", "State ID", Primitive.INTEGER, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_SUBJECT      = new ClassAttrMeta(IDCONST.RECORD_NAME.getIIDValue(), "SUBJECT", "Record Name", Primitive.CHAR, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_COMMENT      = new ClassAttrMeta(IDCONST.MI_COMMENT.getIIDValue(), "USERCOMMENT", "Comment", Primitive.LONG_CHAR, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_BASECLASSID  = new ClassAttrMeta(IDCONST.MI_BASECLASSID.getIIDValue(), "BASECLASSID", "Base Class ID", Primitive.INTEGER, AttributeKind.CANNED);
  public static final IXClassAttr ATTR_MI_IMPORTANCE   = new ClassAttrMeta(IDCONST.INPUT_IMPORTANCE.getIIDValue(), "IMPORTANCE", "Input Importance", Primitive.ENUM, AttributeKind.CANNED);
  // Q&A Input
  public static final IXClassAttr ATTR_QMI_OID          = CLS_QUESTIONINPUT.addAttr(ATTR_MI_OID, LIST_DEFER);
  public static final IXClassAttr ATTR_QMI_CLASSID      = CLS_QUESTIONINPUT.addAttr(ATTR_MI_CLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_QMI_VISIBLEID    = CLS_QUESTIONINPUT.addAttr(ATTR_MI_VISIBLEID, LIST_ALWAYS);
  public static final IXClassAttr ATTR_QMI_CREATIONDATE = CLS_QUESTIONINPUT.addAttr(ATTR_MI_CREATIONDATE, LIST_ALWAYS);
  public static final IXClassAttr ATTR_QMI_SUBJECT      = CLS_QUESTIONINPUT.addAttr(ATTR_MI_SUBJECT, LIST_ALWAYS);
  public static final IXClassAttr ATTR_QMI_COMMENT      = CLS_QUESTIONINPUT.addAttr(ATTR_MI_COMMENT, LIST_DEFER);
  public static final IXClassAttr ATTR_QMI_BASECLASSID  = CLS_QUESTIONINPUT.addAttr(ATTR_MI_BASECLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_QMI_STATEID      = CLS_QUESTIONINPUT.addAttr(ATTR_MI_STATEID, LIST_DEFER);
  public static final IXClassAttr ATTR_QMI_FILE         = CLS_QUESTIONINPUT.addAttr(ATTR_MI_FILE, LIST_DEFER);
  public static final IXClassAttr ATTR_QMI_LINK         = CLS_QUESTIONINPUT.addAttr(ATTR_MI_LINK, LIST_DEFER);
  // Standard Input
  public static final IXClassAttr ATTR_SMI_OID          = CLS_STANDARDINPUT.addAttr(ATTR_MI_OID, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_CLASSID      = CLS_STANDARDINPUT.addAttr(ATTR_MI_CLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_VISIBLEID    = CLS_STANDARDINPUT.addAttr(ATTR_MI_VISIBLEID, LIST_ALWAYS);
  public static final IXClassAttr ATTR_SMI_CREATIONDATE = CLS_STANDARDINPUT.addAttr(ATTR_MI_CREATIONDATE, LIST_ALWAYS);
  public static final IXClassAttr ATTR_SMI_SUBJECT      = CLS_STANDARDINPUT.addAttr(ATTR_MI_SUBJECT, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_COMMENT      = CLS_STANDARDINPUT.addAttr(ATTR_MI_COMMENT, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_BASECLASSID  = CLS_STANDARDINPUT.addAttr(ATTR_MI_BASECLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_STATEID      = CLS_STANDARDINPUT.addAttr(ATTR_MI_STATEID, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_IMPORTANCE   = CLS_STANDARDINPUT.addAttr(ATTR_MI_IMPORTANCE, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_FILE         = CLS_STANDARDINPUT.addAttr(ATTR_MI_FILE, LIST_DEFER);
  public static final IXClassAttr ATTR_SMI_LINK         = CLS_STANDARDINPUT.addAttr(ATTR_MI_LINK, LIST_DEFER);
  // Article Input
  public static final IXClassAttr ATTR_AMI_OID          = CLS_ARTICLEINPUT.addAttr(ATTR_MI_OID, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_CLASSID      = CLS_ARTICLEINPUT.addAttr(ATTR_MI_CLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_VISIBLEID    = CLS_ARTICLEINPUT.addAttr(ATTR_MI_VISIBLEID, LIST_ALWAYS);
  public static final IXClassAttr ATTR_AMI_CREATIONDATE = CLS_ARTICLEINPUT.addAttr(ATTR_MI_CREATIONDATE, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_SUBJECT      = CLS_ARTICLEINPUT.addAttr(ATTR_MI_SUBJECT, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_COMMENT      = CLS_ARTICLEINPUT.addAttr(ATTR_MI_COMMENT, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_BASECLASSID  = CLS_ARTICLEINPUT.addAttr(ATTR_MI_BASECLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_STATEID      = CLS_ARTICLEINPUT.addAttr(ATTR_MI_STATEID, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_IMPORTANCE   = CLS_ARTICLEINPUT.addAttr(ATTR_MI_IMPORTANCE, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_FILE         = CLS_ARTICLEINPUT.addAttr(ATTR_MI_FILE, LIST_DEFER);
  public static final IXClassAttr ATTR_AMI_LINK         = CLS_ARTICLEINPUT.addAttr(ATTR_MI_LINK, LIST_DEFER);
  // Win/Loss Input
  public static final IXClassAttr ATTR_WMI_OID          = CLS_WINLOSSINPUT.addAttr(ATTR_MI_OID, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_CLASSID      = CLS_WINLOSSINPUT.addAttr(ATTR_MI_CLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_VISIBLEID    = CLS_WINLOSSINPUT.addAttr(ATTR_MI_VISIBLEID, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_CREATIONDATE = CLS_WINLOSSINPUT.addAttr(ATTR_MI_CREATIONDATE, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_SUBJECT      = CLS_WINLOSSINPUT.addAttr(ATTR_MI_SUBJECT, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_COMMENT      = CLS_WINLOSSINPUT.addAttr(ATTR_MI_COMMENT, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_BASECLASSID  = CLS_WINLOSSINPUT.addAttr(ATTR_MI_BASECLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_STATEID      = CLS_WINLOSSINPUT.addAttr(ATTR_MI_STATEID, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_FILE         = CLS_WINLOSSINPUT.addAttr(ATTR_MI_FILE, LIST_DEFER);
  public static final IXClassAttr ATTR_WMI_LINK         = CLS_WINLOSSINPUT.addAttr(ATTR_MI_LINK, LIST_DEFER);
  // Summary Input
  public static final IXClassAttr ATTR_SUMMI_OID          = CLS_SUMMARYINPUT.addAttr(ATTR_MI_OID, LIST_DEFER);
  public static final IXClassAttr ATTR_SUMMI_CLASSID      = CLS_SUMMARYINPUT.addAttr(ATTR_MI_CLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_SUMMI_VISIBLEID    = CLS_SUMMARYINPUT.addAttr(ATTR_MI_VISIBLEID, LIST_ALWAYS);
  public static final IXClassAttr ATTR_SUMMI_CREATIONDATE = CLS_SUMMARYINPUT.addAttr(ATTR_MI_CREATIONDATE, LIST_ALWAYS);
  public static final IXClassAttr ATTR_SUMMI_SUBJECT      = CLS_SUMMARYINPUT.addAttr(ATTR_MI_SUBJECT, LIST_ALWAYS);
  public static final IXClassAttr ATTR_SUMMI_COMMENT      = CLS_SUMMARYINPUT.addAttr(ATTR_MI_COMMENT, LIST_DEFER);
  public static final IXClassAttr ATTR_SUMMI_BASECLASSID  = CLS_SUMMARYINPUT.addAttr(ATTR_MI_BASECLASSID, LIST_DEFER);
  public static final IXClassAttr ATTR_SUMMI_STATEID      = CLS_SUMMARYINPUT.addAttr(ATTR_MI_STATEID, LIST_DEFER);
  // Reaction
  public static final IXClassAttr ATTR_REACT_OID          = CLS_REACTION.addAttr(IDCONST.REACTION_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_REACT_CLASSID      = CLS_REACTION.addAttr(IDCONST.REACTION_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_REACT_CREATIONDATE = CLS_REACTION.addAttr(IDCONST.REACTION_CREATIONDATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_REACT_WEIGHT       = CLS_REACTION.addAttr(IDCONST.WEIGHT.getIIDValue(), "WEIGHT", "Weight", Primitive.ENUM, AttributeKind.CANNED, LIST_DEFER);//
  // Problem Statement
  public static final IXClassAttr ATTR_PS_OID          = CLS_PROBLEMSTATEMENT.addAttr(IDCONST.PS_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PS_CLASSID      = CLS_PROBLEMSTATEMENT.addAttr(IDCONST.PS_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PS_CREATIONDATE = CLS_PROBLEMSTATEMENT.addAttr(IDCONST.PS_CREATIONDATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_PS_NAME         = CLS_PROBLEMSTATEMENT.addAttr(IDCONST.RECORD_NAME.getIIDValue(), "SUBJECT", "Record Name", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PS_DESCRIPTION  = CLS_PROBLEMSTATEMENT.addAttr(IDCONST.RECORD_DESC.getIIDValue(), "DESCRIPTION", "Description", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_DEFER);
  //class
  public static final IXClassAttr ATTR_CLASS_OID       = CLS_CLASS.addAttr(IDCONST.CLASS_OID.getIIDValue(),  "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  //state
  public static final IXClassAttr ATTR_STATE_OID       = CLS_STATE.addAttr(IDCONST.STATE_OID.getIIDValue(),  "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_STATE_NAME      = CLS_STATE.addAttr(IDCONST.STATE_NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  // user
  public static final IXClassAttr ATTR_USER_OID        = CLS_USER.addAttr(IDCONST.USER_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_USER_LASTNAME   = CLS_USER.addAttr(IDCONST.LASTNAME.getIIDValue(), "LASTNAME", "Last Name", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_USER_FIRSTNAME  = CLS_USER.addAttr(IDCONST.FIRSTNAME.getIIDValue(), "FIRSTNAME", "First Name", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_USER_EMAIL      = CLS_USER.addAttr(IDCONST.EMAIL.getIIDValue(), "EMAILADDR", "E-Mail Address", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_USER_PHONE      = CLS_USER.addAttr(IDCONST.PHONE.getIIDValue(), "PHONE", "Phone Number", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  // org
  public static final IXClassAttr ATTR_ORG_OID         = CLS_ORGANIZATION.addAttr(IDCONST.ORG_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER); 
  public static final IXClassAttr ATTR_ORG_NAME        = CLS_ORGANIZATION.addAttr(IDCONST.NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_ORG_BUSADDR1    = CLS_ORGANIZATION.addAttr(IDCONST.BUSADDR1.getIIDValue(), "BUSADDR1", "Address (line 1)", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_ORG_BUSADDR2    = CLS_ORGANIZATION.addAttr(IDCONST.BUSADDR2.getIIDValue(), "BUSADDR2", "Address (line 2)", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_ORG_CITY        = CLS_ORGANIZATION.addAttr(IDCONST.CITY.getIIDValue(), "CITY", "City", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_ORG_STATE       = CLS_ORGANIZATION.addAttr(IDCONST.STATE.getIIDValue(), "BUSADDR1", "State", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_ORG_ZIPCODE     = CLS_ORGANIZATION.addAttr(IDCONST.ZIPCODE.getIIDValue(), "ZIPCODE", "Zip Code", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_ORG_COUNTRY     = CLS_ORGANIZATION.addAttr(IDCONST.COUNTRY.getIIDValue(), "COUNTRY", "Country", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  
  // discussion topics
  public static final IXClassAttr ATTR_DISCTOPIC_OID          = CLS_DISCUSSION.addAttr(IDCONST.DISCTOPIC_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_DISCTOPIC_CLASSID      = CLS_DISCUSSION.addAttr(IDCONST.DISCTOPIC_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_DISCTOPIC_TOPICKIND      = CLS_DISCUSSION.addAttr(IDCONST.DISCTOPIC_TOPICKIND.getIIDValue(), "TOPICKIND", "Topic Kind", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_DISCTOPIC_SUBJECT      = CLS_DISCUSSION.addAttr(IDCONST.DISCTOPIC_SUBJECT.getIIDValue(), "SUBJECT", "Subject", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_DISCTOPIC_BODY         = CLS_DISCUSSION.addAttr(IDCONST.DISCTOPIC_BODY.getIIDValue(), "BODY", "Body", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  // transaction comment
  public static final IXClassAttr ATTR_TRANSCOMMENT_OID          = CLS_TRANSACTIONCOMMENT.addAttr(IDCONST.TRANSCOMMENT_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_TRANSCOMMENT_CLASSID      = CLS_TRANSACTIONCOMMENT.addAttr(IDCONST.TRANSCOMMENT_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_TRANSCOMMENT_TOPICKIND      = CLS_TRANSACTIONCOMMENT.addAttr(IDCONST.TRANSCOMMENT_TOPICKIND.getIIDValue(), "TOPICKIND", "Topic Kind", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_TRANSCOMMENT_SUBJECT      = CLS_TRANSACTIONCOMMENT.addAttr(IDCONST.TRANSCOMMENT_SUBJECT.getIIDValue(), "SUBJECT", "Subject", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_TRANSCOMMENT_BODY         = CLS_TRANSACTIONCOMMENT.addAttr(IDCONST.TRANSCOMMENT_BODY.getIIDValue(), "BODY", "Body", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  // std coll
  public static final IXClassAttr ATTR_STDCOLL_OID          = CLS_STDCOLL.addAttr(IDCONST.STDCOLL_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_STDCOLL_CLASSID      = CLS_STDCOLL.addAttr(IDCONST.STDCOLL_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_STDCOLL_NAME         = CLS_STDCOLL.addAttr(IDCONST.STDCOLL_NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_STDCOLL_DESC         = CLS_STDCOLL.addAttr(IDCONST.STDCOLL_DESC.getIIDValue(), "DESCRIPTION", "Description", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  // product
  public static final IXClassAttr ATTR_PROD_OID          = CLS_PRODUCT.addAttr(IDCONST.PRODUCT_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PROD_CLASSID      = CLS_PRODUCT.addAttr(IDCONST.PRODUCT_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PROD_NAME         = CLS_PRODUCT.addAttr(IDCONST.PRODUCT_NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PROD_DESC         = CLS_PRODUCT.addAttr(IDCONST.PRODUCT_DESC.getIIDValue(), "DESCRIPTION", "Description", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PROD_STATEID      = CLS_PRODUCT.addAttr(IDCONST.PRODUCT_STATEID.getIIDValue(), "STATEID", "State ID", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_PROD_CREATIONDATE = CLS_PRODUCT.addAttr(IDCONST.CREATION_DATE.getIIDValue(),"CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED,LIST_ALWAYS);
  // version
  public static final IXClassAttr ATTR_VER_OID          = CLS_VERSION.addAttr(IDCONST.VERSION_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_VER_CLASSID      = CLS_VERSION.addAttr(IDCONST.VERSION_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_VER_NAME         = CLS_VERSION.addAttr(IDCONST.VERSION_NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_VER_DESC         = CLS_VERSION.addAttr(IDCONST.VERSION_DESC.getIIDValue(), "DESCRIPTION", "Description", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_VER_STATEID      = CLS_VERSION.addAttr(IDCONST.VERSION_STATEID.getIIDValue(), "STATEID", "State ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_VER_CREATIONDATE = CLS_VERSION.addAttr(IDCONST.CREATION_DATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_VER_TARGETDATE   = CLS_VERSION.addAttr(IDCONST.TARGET_RELEASE_DATE.getIIDValue(), "TARGETRELEASEDATE", "Target Release Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_VER_ACTUALDATE   = CLS_VERSION.addAttr(IDCONST.ACTUAL_RELEASE_DATE.getIIDValue(), "ACTUALRELEASEDATE", "Actual Release Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  // category
  public static final IXClassAttr ATTR_CAT_OID          = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_CAT_CLASSID      = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_CAT_NAME         = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_CAT_DESC         = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_DESC.getIIDValue(), "DESCRIPTION", "Description", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_CAT_PARENTID     = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_PARENTID.getIIDValue(), "PARENTCATID", "Parent Category ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_CAT_VERSIONID    = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_VERSIONID.getIIDValue(), "VERSIONID", "Version ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_CAT_STATEID      = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_STATEID.getIIDValue(), "STATEID", "State ID", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_CAT_VISIBLEID    = CLS_CATEGORY.addAttr(IDCONST.CATEGORY_VISIBLEID.getIIDValue(), "VISIBLEID", "ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_CAT_CREATIONDATE = CLS_CATEGORY.addAttr(IDCONST.CREATION_DATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  // feature
  public static final IXClassAttr ATTR_FEAT_OID          = CLS_FEATURE.addAttr(IDCONST.FEATURE_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEAT_CLASSID      = CLS_FEATURE.addAttr(IDCONST.FEATURE_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEAT_LATESTREVID  = CLS_FEATURE.addAttr(IDCONST.FEATURE_LATESTREVID.getIIDValue(), "LATESTREVISIONID", "Latest Revision ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEAT_VISIBLEID    = CLS_FEATURE.addAttr(IDCONST.FEATURE_VISIBLEID.getIIDValue(), "VISIBLEID", "ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEAT_ISSTANDARD   = CLS_FEATURE.addAttr(IDCONST.FEATURE_ISSTANDARD.getIIDValue(), "ISSTANDARD", "Is Standard", Primitive.BOOLEAN, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEAT_CREATIONDATE = CLS_FEATURE.addAttr(IDCONST.CREATION_DATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEAT_TYPE         = CLS_FEATURE.addAttr(IDCONST.FEAT_TYPE.getIIDValue(), "FEATURETYPEID", "Feature Type", Primitive.ENUM, AttributeKind.CANNED, LIST_DEFER);
  // feature link
  public static final IXClassAttr ATTR_FEATLINK_OID          = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_CLASSID      = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_PINNEDREVID  = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_PINNEDREVID.getIIDValue(), "PINNEDREVID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_STATEID      = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_STATEID.getIIDValue(), "STATEID", "State ID", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_PRIORITY      = CLS_CATFEATLINK.addAttr(IDCONST.FEAT_PRIORITY.getIIDValue(), "PRIORITYID", "Priority", Primitive.ENUM, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_TESTLEVEL      = CLS_CATFEATLINK.addAttr(IDCONST.FEAT_TEST.getIIDValue(), "TESTLEVELID", "Test Level", Primitive.ENUM, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_DIFFLEVEL      = CLS_CATFEATLINK.addAttr(IDCONST.FEAT_DIFFICULTY.getIIDValue(), "DIFFLEVELID", "Difficulty", Primitive.ENUM, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATLINK_CREATIONDATE = CLS_CATFEATLINK.addAttr(IDCONST.CREATION_DATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_PERCENTCOMPLETE = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_PERCENTCOMPLETE.getIIDValue(), "PERCENTCOMPLETED", "Percent Complete", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_ESTDEVDUR = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_ESTDEVDUR.getIIDValue(), "ESTDEVTIME", "Estimated Development Duration", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_ESTTESTDUR = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_ESTTESTDUR.getIIDValue(), "ESTTESTTIME", "Estimated Test Duration", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_DEVSTARTDATE = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_DEVSTARTDATE.getIIDValue(), "DEVSTARTDATE", "Development Start Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_DEVENDDATE = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_DEVENDDATE.getIIDValue(), "DEVENDDATE", "Development End Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_TESTENDDATE = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_TESTENDDATE.getIIDValue(), "TESTENDDATE", "Test End Date", Primitive.TIME, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATLINK_ACTDUR = CLS_CATFEATLINK.addAttr(IDCONST.FEATURECATEGORYLINK_ACTDUR.getIIDValue(), "ACTUALDEVTIME", "Actual Duration", Primitive.INTEGER, AttributeKind.CANNED, LIST_ALWAYS);

  // standard link
  public static final IXClassAttr ATTR_STDFEATLINK_OID        = CLS_STDFEATLINK.addAttr(IDCONST.STDFEATLINK_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_STDFEATLINK_CLASSID      = CLS_STDFEATLINK.addAttr(IDCONST.STDFEATLINK_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  // feature revision attrs
  public static final IXClassAttr ATTR_FEATREV_OID         = CLS_FEATURE.addAttr(IDCONST.FEATUREREVISION_OID.getIIDValue(), "REVISIONID", "System ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
//  public static final IXClassAttr ATTR_FEATREV_CLASSID     = CLS_FEATURE.addAttr(IDCONST.FEATUREREVISION_CLASSID.getIIDValue(), "CLASSID", "Class ID", Primitive.INTEGER, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATREV_NAME        = CLS_FEATURE.addAttr(IDCONST.NAME.getIIDValue(), "NAME", "Name", Primitive.CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATREV_DESC        = CLS_FEATURE.addAttr(IDCONST.DESC.getIIDValue(), "DESCRIPTION", "Description", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_DEFER);
  public static final IXClassAttr ATTR_FEATREV_COMMENT     = CLS_FEATURE.addAttr(IDCONST.FEATREV_COMMENT.getIIDValue(), "USERCOMMENT", "Revision Comment", Primitive.LONG_CHAR, AttributeKind.CANNED, LIST_ALWAYS);
  public static final IXClassAttr ATTR_FEATREV_CREATIONDATE = CLS_FEATURE.addAttr(IDCONST.CREATION_DATE.getIIDValue(), "CREATIONDATE", "Creation Date", Primitive.TIME, AttributeKind.CANNED, LIST_DEFER);
   

  static {
    CLS_QUESTIONINPUT.addKeywordSelectAttr(IDCONST.MI_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_QUESTIONINPUT.addKeywordSelectAttr(IDCONST.FOLDERINPUTLINK.getIIDValue().toString());

    CLS_STANDARDINPUT.addKeywordSelectAttr(IDCONST.MI_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_STANDARDINPUT.addKeywordSelectAttr(IDCONST.FOLDERINPUTLINK.getIIDValue().toString());

    CLS_ARTICLEINPUT.addKeywordSelectAttr(IDCONST.MI_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_ARTICLEINPUT.addKeywordSelectAttr(IDCONST.FOLDERINPUTLINK.getIIDValue().toString());

    CLS_WINLOSSINPUT.addKeywordSelectAttr(IDCONST.MI_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_WINLOSSINPUT.addKeywordSelectAttr(IDCONST.FOLDERINPUTLINK.getIIDValue().toString());

    CLS_SUMMARYINPUT.addKeywordSelectAttr(IDCONST.MI_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_SUMMARYINPUT.addKeywordSelectAttr(IDCONST.FOLDERINPUTLINK.getIIDValue().toString());

    CLS_REACTION.addKeywordSelectAttr(IDCONST.REACTION_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_REACTION.addKeywordSelectAttr(IDCONST.REACTION.getIIDValue().toString());

    CLS_PROBLEMSTATEMENT.addKeywordSelectAttr(IDCONST.PS_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_PROBLEMSTATEMENT.addKeywordSelectAttr(IDCONST.PROBLEMSTATEMENT.getIIDValue().toString());

    CLS_STDCOLL.addKeywordSelectAttr(IDCONST.STDCOLL_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_STDCOLL.addKeywordSelectAttr(IDCONST.STANDARDSCOLLECTION.getIIDValue().toString());

    CLS_PRODUCT.addKeywordSelectAttr(IDCONST.PRODUCT_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_PRODUCT.addKeywordSelectAttr(IDCONST.PRODUCT.getIIDValue().toString());

    CLS_VERSION.addKeywordSelectAttr(IDCONST.VERSION_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_VERSION.addKeywordSelectAttr(IDCONST.PRODUCTVERSION.getIIDValue().toString());

    CLS_CATEGORY.addKeywordSelectAttr(IDCONST.CATEGORY_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_CATEGORY.addKeywordSelectAttr(IDCONST.CATEGORY.getIIDValue().toString());

    CLS_FEATURE.addKeywordSelectAttr(IDCONST.FEATURE_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_FEATURE.addKeywordSelectAttr(IDCONST.FEATURE.getIIDValue().toString());

    CLS_FEATURE.addKeywordSelectAttr(IDCONST.FEATUREREVISION_OID.getIIDValue(), "REVISIONID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
//    CLS_FEATUREREVISION.addKeywordSelectAttr(IDCONST.FEATUREREVISION.getIIDValue().toString());

    CLS_CATFEATLINK.addKeywordSelectAttr(IDCONST.FEATURECATEGORYLINK_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_CATFEATLINK.addKeywordSelectAttr(IDCONST.FEATURECATEGORYLINK.getIIDValue().toString());

    CLS_STDFEATLINK.addKeywordSelectAttr(IDCONST.STDFEATLINK_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_STDFEATLINK.addKeywordSelectAttr(IDCONST.STDFEATURELINK.getIIDValue().toString());

    CLS_DISCUSSION.addKeywordSelectAttr(IDCONST.DISCTOPIC_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_DISCUSSION.addKeywordSelectAttr(IDCONST.DISCUSSIONTOPIC.getIIDValue().toString());

    CLS_TRANSACTIONCOMMENT.addKeywordSelectAttr(IDCONST.TRANSCOMMENT_OID.getIIDValue(), "OBJECTID", "System ID", Primitive.INTEGER, AttributeKind.CANNED);
    CLS_TRANSACTIONCOMMENT.addKeywordSelectAttr(IDCONST.DISCUSSIONTOPIC.getIIDValue().toString());
    
    CLS_CATEGORY.setClassDefnFilter(new QFilterExpr(CompOper.COMP_NOTEQ,ATTR_CAT_PARENTID,ATTR_CAT_OID));
    CLS_TRANSACTIONCOMMENT.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_TRANSCOMMENT_TOPICKIND,TopicKind.TRANSACTIONCOMMENT));
    CLS_DISCUSSION.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_DISCTOPIC_TOPICKIND,TopicKind.NORMAL));
    CLS_STANDARDINPUT.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_SMI_BASECLASSID,IDCONST.STANDARDINPUT.getIIDValue()));
    CLS_ARTICLEINPUT.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_AMI_BASECLASSID,IDCONST.ARTICLEINPUT.getIIDValue()));
    CLS_QUESTIONINPUT.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_QMI_BASECLASSID,IDCONST.QUESTIONINPUT.getIIDValue()));
    CLS_WINLOSSINPUT.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_WMI_BASECLASSID,IDCONST.WINLOSSINPUT.getIIDValue()));
    CLS_SUMMARYINPUT.setClassDefnFilter(new QFilterExpr(CompOper.COMP_EQ,ATTR_SUMMI_BASECLASSID,IDCONST.SUMMARYINPUT.getIIDValue()));

    CLS_PRODUCT.setPrimaryKeyAttr(XMen.ATTR_PROD_OID);
    CLS_VERSION.setPrimaryKeyAttr(XMen.ATTR_VER_OID);
    CLS_CATEGORY.setPrimaryKeyAttr(XMen.ATTR_CAT_OID);
    CLS_CATFEATLINK.setPrimaryKeyAttr(XMen.ATTR_FEATLINK_OID);
    CLS_FEATURE.setPrimaryKeyAttr(XMen.ATTR_FEAT_OID);
    CLS_STDCOLL.setPrimaryKeyAttr(XMen.ATTR_STDCOLL_OID);
    CLS_STDFEATLINK.setPrimaryKeyAttr(XMen.ATTR_STDFEATLINK_OID);
    CLS_FOLDER.setPrimaryKeyAttr(XMen.ATTR_FOLDER_OID);
    CLS_STANDARDINPUT.setPrimaryKeyAttr(XMen.ATTR_SMI_OID);
    CLS_QUESTIONINPUT.setPrimaryKeyAttr(XMen.ATTR_QMI_OID);
    CLS_ARTICLEINPUT.setPrimaryKeyAttr(XMen.ATTR_AMI_OID);
    CLS_WINLOSSINPUT.setPrimaryKeyAttr(XMen.ATTR_WMI_OID);
    CLS_SUMMARYINPUT.setPrimaryKeyAttr(XMen.ATTR_SUMMI_OID);
    CLS_PROBLEMSTATEMENT.setPrimaryKeyAttr(XMen.ATTR_PS_OID);
    CLS_REACTION.setPrimaryKeyAttr(XMen.ATTR_REACT_OID);
    CLS_USER.setPrimaryKeyAttr(XMen.ATTR_USER_OID);
    CLS_ORGANIZATION.setPrimaryKeyAttr(XMen.ATTR_ORG_OID);
    CLS_DISCUSSION.setPrimaryKeyAttr(XMen.ATTR_DISCTOPIC_OID);
    CLS_TRANSACTIONCOMMENT.setPrimaryKeyAttr(XMen.ATTR_TRANSCOMMENT_OID);
  }
  
  // question input associations
  public static final IXAssoc ASC_QMI_SRCUSER  = new AssocMeta(IDCONST.ASC_QMKTINP_SRCUSER.getIIDValue(), CLS_QUESTIONINPUT, CLS_USER, "SRCUSERID", "Source Contact");
  public static final IXAssoc ASC_QMI_CREATEUSER   = new AssocMeta(IDCONST.ASC_QMKTINP_CREATEUSER.getIIDValue(), CLS_QUESTIONINPUT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_QMI_STATE   = new AssocMeta(IDCONST.ASC_QMKTINP_STATE.getIIDValue(), CLS_QUESTIONINPUT, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_QMI_CLASS   = new AssocMeta(IDCONST.ASC_QMKTINP_CLASS.getIIDValue(), CLS_QUESTIONINPUT, CLS_CLASS, "CLASSID", "Form Type");
  // standard input associations
  public static final IXAssoc ASC_SMI_SRCUSER  = new AssocMeta(IDCONST.ASC_SMKTINP_SRCUSER.getIIDValue(), CLS_STANDARDINPUT, CLS_USER, "SRCUSERID", "Source Contact");
  public static final IXAssoc ASC_SMI_CREATEUSER   = new AssocMeta(IDCONST.ASC_SMKTINP_CREATEUSER.getIIDValue(), CLS_STANDARDINPUT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_SMI_STATE   = new AssocMeta(IDCONST.ASC_SMKTINP_STATE.getIIDValue(), CLS_STANDARDINPUT, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_SMI_CLASS   = new AssocMeta(IDCONST.ASC_SMKTINP_CLASS.getIIDValue(), CLS_STANDARDINPUT, CLS_CLASS, "CLASSID", "Form Type");
  // article input associations
  public static final IXAssoc ASC_AMI_SRCUSER  = new AssocMeta(IDCONST.ASC_AMKTINP_SRCUSER.getIIDValue(), CLS_ARTICLEINPUT, CLS_USER, "SRCUSERID", "Source Contact");
  public static final IXAssoc ASC_AMI_CREATEUSER   = new AssocMeta(IDCONST.ASC_AMKTINP_CREATEUSER.getIIDValue(), CLS_ARTICLEINPUT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_AMI_STATE   = new AssocMeta(IDCONST.ASC_AMKTINP_STATE.getIIDValue(), CLS_ARTICLEINPUT, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_AMI_CLASS   = new AssocMeta(IDCONST.ASC_AMKTINP_CLASS.getIIDValue(), CLS_ARTICLEINPUT, CLS_CLASS, "CLASSID", "Form Type");
  // win/loss input associations
  public static final IXAssoc ASC_WMI_SRCUSER  = new AssocMeta(IDCONST.ASC_WMKTINP_SRCUSER.getIIDValue(), CLS_WINLOSSINPUT, CLS_USER, "SRCUSERID", "Source Contact");
  public static final IXAssoc ASC_WMI_CREATEUSER   = new AssocMeta(IDCONST.ASC_WMKTINP_CREATEUSER.getIIDValue(), CLS_WINLOSSINPUT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_WMI_STATE   = new AssocMeta(IDCONST.ASC_WMKTINP_STATE.getIIDValue(), CLS_WINLOSSINPUT, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_WMI_CLASS   = new AssocMeta(IDCONST.ASC_WMKTINP_CLASS.getIIDValue(), CLS_WINLOSSINPUT, CLS_CLASS, "CLASSID", "Form Type");
  // summary input associations
  public static final IXAssoc ASC_SUMMI_SRCUSER  = new AssocMeta(IDCONST.ASC_SUMMKTINP_SRCUSER.getIIDValue(), CLS_SUMMARYINPUT, CLS_USER, "SRCUSERID", "Source Contact");
  public static final IXAssoc ASC_SUMMI_CREATEUSER   = new AssocMeta(IDCONST.ASC_SUMMKTINP_CREATEUSER.getIIDValue(), CLS_SUMMARYINPUT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_SUMMI_STATE   = new AssocMeta(IDCONST.ASC_SUMMKTINP_STATE.getIIDValue(), CLS_SUMMARYINPUT, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_SUMMI_CLASS   = new AssocMeta(IDCONST.ASC_SUMMKTINP_CLASS.getIIDValue(), CLS_SUMMARYINPUT, CLS_CLASS, "CLASSID", "Form Type");
  // folder link assocs
  public static final IXAssoc ASC_FLINK_FOLDER   = new AssocMeta(IDCONST.ASC_FLINK_FOLDER.getIIDValue(), CLS_FOLDERINPUTLINK, CLS_FOLDER, "FOLDERID", "Folder");
  public static final IXAssoc ASC_FLINK_QMI   = new AssocMeta(IDCONST.ASC_FLINK_QMKTINP.getIIDValue(), CLS_FOLDERINPUTLINK, CLS_QUESTIONINPUT, "MARKETINPUTID", "Q&A Input");
  public static final IXAssoc ASC_FLINK_SMI   = new AssocMeta(IDCONST.ASC_FLINK_SMKTINP.getIIDValue(), CLS_FOLDERINPUTLINK, CLS_STANDARDINPUT, "MARKETINPUTID", "Standard Input");
  public static final IXAssoc ASC_FLINK_AMI   = new AssocMeta(IDCONST.ASC_FLINK_AMKTINP.getIIDValue(), CLS_FOLDERINPUTLINK, CLS_ARTICLEINPUT, "MARKETINPUTID", "Article Input");
  public static final IXAssoc ASC_FLINK_WMI   = new AssocMeta(IDCONST.ASC_FLINK_WMKTINP.getIIDValue(), CLS_FOLDERINPUTLINK, CLS_WINLOSSINPUT, "MARKETINPUTID", "Win/Loss Input");
  public static final IXAssoc ASC_FLINK_SUMMI   = new AssocMeta(IDCONST.ASC_FLINK_SUMMKTINP.getIIDValue(), CLS_FOLDERINPUTLINK, CLS_SUMMARYINPUT, "MARKETINPUTID", "Summary Input");
  // reaction assocs
  public static final IXAssoc ASC_REACT_FLINK  = new AssocMeta(IDCONST.ASC_REACT_FLINK.getIIDValue(), CLS_REACTION, CLS_FOLDERINPUTLINK, "INPUTLINKID", "Reaction");
  public static final IXAssoc ASC_REACT_CREATEUSER  = new AssocMeta(IDCONST.ASC_REACT_CREATEUSER.getIIDValue(), CLS_REACTION, CLS_USER, "CREATORID", "Creator");
  // problem statement assocs
  public static final IXAssoc ASC_PROBSTM_CREATEUSER  = new AssocMeta(IDCONST.ASC_PROBSTM_CREATEUSER.getIIDValue(), CLS_PROBLEMSTATEMENT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_PROBSTM_FOLDER  = new AssocMeta(IDCONST.ASC_PROBSTM_FOLDER.getIIDValue(), CLS_PROBLEMSTATEMENT, CLS_FOLDER, "FOLDERID", "Folder");
  public static final IXAssoc ASC_PROBSTM_STATE   = new AssocMeta(IDCONST.ASC_PROBSTM_STATE.getIIDValue(), CLS_PROBLEMSTATEMENT, CLS_STATE, "STATEID", "State");
  // user and org assocs
  public static final IXAssoc ASC_FOLDER_REC      = new AssocMeta(IDCONST.ASC_FOLDER_REC.getIIDValue(), CLS_FOLDER,  "FOLDERID", "Parent Folder");
  public static final IXAssoc ASC_USER_ORG        = new AssocMeta(IDCONST.ASC_USER_ORG.getIIDValue(), CLS_USER, CLS_ORGANIZATION, "ORGID", "Organization");
  // product assocs
  public static final IXAssoc ASC_PROD_CREATEUSER  = new AssocMeta(IDCONST.ASC_PROD_CREATEUSER.getIIDValue(), CLS_PRODUCT, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_PROD_STATE  = new AssocMeta(IDCONST.ASC_PROD_STATE.getIIDValue(), CLS_PRODUCT, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_PROD_CLASS  = new AssocMeta(IDCONST.ASC_PROD_CLASS.getIIDValue(), CLS_PRODUCT, CLS_CLASS, "CLASSID", "Form Type");
  // version assocs
  public static final IXAssoc ASC_VER_CREATEUSER  = new AssocMeta(IDCONST.ASC_VER_CREATEUSER.getIIDValue(), CLS_VERSION, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_VER_PROD   = new AssocMeta(IDCONST.ASC_VER_PROD.getIIDValue(), CLS_VERSION, CLS_PRODUCT, "PRODUCTID", "Product");
  public static final IXAssoc ASC_VER_SHARE  = new AssocMeta(IDCONST.ASC_VER_SHARE.getIIDValue(), CLS_VERSION, CLS_VERSION, CLS_VERSHAREASC, "PARVERSIONID", "CHILDVERSIONID", "Version Share");
  public static final IXAssoc ASC_VER_STATE  = new AssocMeta(IDCONST.ASC_VER_STATE.getIIDValue(), CLS_VERSION, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_VER_CLASS  = new AssocMeta(IDCONST.ASC_VER_CLASS.getIIDValue(), CLS_VERSION, CLS_CLASS, "CLASSID", "Form Type"); 
  // category assocs
  public static final IXAssoc ASC_CAT_CREATEUSER  = new AssocMeta(IDCONST.ASC_CAT_CREATEUSER.getIIDValue(), CLS_CATEGORY, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_CAT_VERS  = new AssocMeta(IDCONST.ASC_CAT_VERS.getIIDValue(), CLS_CATEGORY, CLS_VERSION, "VERSIONID", "Version");
  public static final IXAssoc ASC_CAT_REC   = new AssocMeta(IDCONST.ASC_CAT_REC.getIIDValue(), CLS_CATEGORY, "PARENTCATID", "Parent Category");
  public static final IXAssoc ASC_CAT_STATE = new AssocMeta(IDCONST.ASC_CAT_STATE.getIIDValue(), CLS_CATEGORY,CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_CAT_CLASS = new AssocMeta(IDCONST.ASC_CAT_CLASS.getIIDValue(), CLS_CATEGORY, CLS_CLASS, "CLASSID", "Form Type"); 
  // feature assocs
  public static final IXAssoc ASC_FEAT_CREATEUSER  = new AssocMeta(IDCONST.ASC_FEAT_CREATEUSER.getIIDValue(), CLS_FEATURE, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_FEAT_CLASS  = new AssocMeta(IDCONST.ASC_FEAT_CLASS.getIIDValue(), CLS_FEATURE, CLS_CLASS, "CLASSID", "Form Type"); 
  // feature link assocs
  public static final IXAssoc ASC_FEATLINK_CREATEUSER  = new AssocMeta(IDCONST.ASC_FEATLINK_CREATEUSER.getIIDValue(), CLS_CATFEATLINK, CLS_USER, "CREATORID", "Creator");
  public static final IXAssoc ASC_FEATLINK_STATE  = new AssocMeta(IDCONST.ASC_FEATLINK_STATE.getIIDValue(), CLS_CATFEATLINK, CLS_STATE, "STATEID", "State");
  public static final IXAssoc ASC_FEATLINK_FEAT  = new AssocMeta(IDCONST.ASC_FEATLINK_FEAT.getIIDValue(), CLS_CATFEATLINK, CLS_FEATURE, "FEATUREID", "Feature");
  public static final IXAssoc ASC_FEATLINK_CAT  = new AssocMeta(IDCONST.ASC_FEATLINK_CAT.getIIDValue(), CLS_CATFEATLINK, CLS_CATEGORY, "CATEGORYID", "Category");
  // standard feature assocs
  public static final IXAssoc ASC_STDFEATLINK_FEAT  = new AssocMeta(IDCONST.ASC_STDFEATLINK_FEAT.getIIDValue(), CLS_STDFEATLINK, CLS_FEATURE, "FEATUREID", "Feature");
  public static final IXAssoc ASC_STDFEATLINK_STDCOLL  = new AssocMeta(IDCONST.ASC_STDFEATLINK_STDCOLL.getIIDValue(), CLS_STDFEATLINK, CLS_STDCOLL, "STDCOLLECTIONID", "Standards Collection");
  // standards collections
  public static final IXAssoc ASC_STDCOLL_REC  = new AssocMeta(IDCONST.ASC_STDCOLL_REC.getIIDValue(), CLS_STDCOLL, "PARENTCOLLID", "Parent Collection");
  // feature revision assocs
//  public static final IXAssoc ASC_FEATREV_CREATEUSER  = new AssocMeta(IDCONST.ASC_FEATREV_CREATEUSER.getIIDValue(), CLS_FEATUREREVISION, CLS_USER, "CREATORID", "Creator");
//  public static final IXAssoc ASC_FEATREV_FEAT  = new AssocMeta(IDCONST.ASC_FEATREV_FEAT.getIIDValue(), CLS_FEATUREREVISION, CLS_FEATURE, "FEATUREID", "Feature");
  // discussion assocs
  public static final IXAssoc ASC_DISC_PROD  = new AssocMeta(IDCONST.ASC_DISC_PROD.getIIDValue(), CLS_DISCUSSION, CLS_PRODUCT, "PAROBJECTID", "Parent Product");
  public static final IXAssoc ASC_DISC_VERS  = new AssocMeta(IDCONST.ASC_DISC_VERS.getIIDValue(), CLS_DISCUSSION, CLS_VERSION, "PAROBJECTID", "Parent Version");
  public static final IXAssoc ASC_DISC_CAT  = new AssocMeta(IDCONST.ASC_DISC_CAT.getIIDValue(), CLS_DISCUSSION, CLS_CATEGORY, "PAROBJECTID", "Parent Category");
  public static final IXAssoc ASC_DISC_FLINK  = new AssocMeta(IDCONST.ASC_DISC_FLINK.getIIDValue(), CLS_DISCUSSION, CLS_CATFEATLINK, "PAROBJECTID", "Parent Feature Link");
  public static final IXAssoc ASC_DISC_SFLINK  = new AssocMeta(IDCONST.ASC_DISC_SFLINK.getIIDValue(), CLS_DISCUSSION, CLS_STDFEATLINK, "PAROBJECTID", "Parent Feature Link");
  public static final IXAssoc ASC_DISC_FEAT  = new AssocMeta(IDCONST.ASC_DISC_FEAT.getIIDValue(), CLS_DISCUSSION, CLS_FEATURE, "PAROBJECTID", "Parent Feature");
  public static final IXAssoc ASC_DISC_WMKTINP  = new AssocMeta(IDCONST.ASC_DISC_WMKTINP.getIIDValue(), CLS_DISCUSSION, CLS_WINLOSSINPUT, "PAROBJECTID", "Parent Win/Loss Input");
  public static final IXAssoc ASC_DISC_SMKTINP  = new AssocMeta(IDCONST.ASC_DISC_SMKTINP.getIIDValue(), CLS_DISCUSSION, CLS_STANDARDINPUT, "PAROBJECTID", "Parent Standard Input");
  public static final IXAssoc ASC_DISC_AMKTINP  = new AssocMeta(IDCONST.ASC_DISC_AMKTINP.getIIDValue(), CLS_DISCUSSION, CLS_ARTICLEINPUT, "PAROBJECTID", "Parent Article Input");
  public static final IXAssoc ASC_DISC_FOLDLINK  = new AssocMeta(IDCONST.ASC_DISC_FOLDLINK.getIIDValue(), CLS_DISCUSSION, CLS_FOLDERINPUTLINK, "PAROBJECTID", "Parent Input Link");
  public static final IXAssoc ASC_DISC_PS  = new AssocMeta(IDCONST.ASC_DISC_PS.getIIDValue(), CLS_DISCUSSION, CLS_PROBLEMSTATEMENT, "PAROBJECTID", "Parent Problem Statement");
  // transaction comment assocs
  public static final IXAssoc ASC_TRANS_FLINK  = new AssocMeta(IDCONST.ASC_TRANS_FLINK.getIIDValue(), CLS_TRANSACTIONCOMMENT, CLS_CATFEATLINK, "PAROBJECTID", "Parent Feature Link");
  
  public static IQFilterExpr FILTER_CATFEATLINK_FEAT_REV = null;
  public static IQFilterExpr FILTER_STDFEATLINK_FEAT_REV = null;
  
  public static IQFilterExpr FILTER_FEAT_PROD_COMPASS = null;
  public static IQFilterExpr FILTER_FEAT_PROD_ACCOLADES = null;
  public static IQFilterExpr FILTER_FEAT_VER_COMPASS = null;
  public static IQFilterExpr FILTER_FEAT_VER_ACCOLADES = null;
  public static IQFilterExpr FILTER_FEAT_CAT_COMPASS = null;
  public static IQFilterExpr FILTER_FEAT_CAT_ACCOLADES = null;
  
  public static IQFilterExpr FILTER_CAT_PROD_COMPASS = null;
  public static IQFilterExpr FILTER_CAT_PROD_ACCOLADES = null;
  public static IQFilterExpr FILTER_CAT_VER_COMPASS = null;
  public static IQFilterExpr FILTER_CAT_VER_ACCOLADES = null;
  public static IQFilterExpr FILTER_CAT_COMPASS = null;
  public static IQFilterExpr FILTER_CAT_ACCOLADES = null;

  public static IQFilterExpr FILTER_VER_PROD_COMPASS = null;
  public static IQFilterExpr FILTER_VER_PROD_ACCOLADES = null;
  public static IQFilterExpr FILTER_VER_COMPASS = null;
  public static IQFilterExpr FILTER_VER_ACCOLADES = null;

  public static IQFilterExpr FILTER_PROD_COMPASS = null;
  public static IQFilterExpr FILTER_PROD_ACCOLADES = null;

  // declare valid assoc chains for user-specified queries.
  static 
  {
    try
    {
      FILTER_CATFEATLINK_FEAT_REV = new QFilterExpr(BoolOper.BOOL_OR, 
        new QFilterExpr(BoolOper.BOOL_AND, 
            new QFilterExpr(CompOper.COMP_ISNULL,ATTR_FEATLINK_PINNEDREVID,null), 
            new QFilterExpr(CompOper.COMP_EQ,XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_FEAT}),ATTR_FEAT_LATESTREVID),XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_FEAT}),ATTR_FEATREV_OID))),
        new QFilterExpr(BoolOper.BOOL_AND, 
            new QFilterExpr(CompOper.COMP_ISNOTNULL,ATTR_FEATLINK_PINNEDREVID,null), 
            new QFilterExpr(CompOper.COMP_EQ,ATTR_FEATLINK_PINNEDREVID,XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_FEAT}),ATTR_FEATREV_OID)))
         );
      FILTER_STDFEATLINK_FEAT_REV = new QFilterExpr(CompOper.COMP_EQ,XMR.getInstance().getAttrRef(null,CLS_STDFEATLINK,Arrays.asList(new IXAssoc[] {ASC_STDFEATLINK_FEAT}),ATTR_FEAT_LATESTREVID),XMR.getInstance().getAttrRef(null,CLS_STDFEATLINK,Arrays.asList(new IXAssoc[] {ASC_STDFEATLINK_FEAT}),ATTR_FEATREV_OID));
      

      FILTER_FEAT_CAT_COMPASS = new QFilterExpr(BoolOper.BOOL_AND,
        new QFilterExpr(CompOper.COMP_EQ, ATTR_FEATLINK_OID, IDCONST.COMPASS.getIIDValue()),
        new QFilterExpr(CompOper.COMP_EQ,
            XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_CAT}),ATTR_CAT_OID),
            IDCONST.CATEGORYCOMPASS.getIIDValue()
        )
      );
      FILTER_FEAT_VER_COMPASS = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_FEAT_CAT_COMPASS,
        new QFilterExpr(CompOper.COMP_EQ,
            XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_CAT, ASC_CAT_VERS}),ATTR_VER_OID),
            IDCONST.VERSIONCOMPASS.getIIDValue()
        )
      );
      FILTER_FEAT_PROD_COMPASS = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_FEAT_VER_COMPASS,
        new QFilterExpr(CompOper.COMP_EQ,
            XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_CAT, ASC_CAT_VERS, ASC_VER_PROD}),ATTR_PROD_OID),
            IDCONST.PRODUCTCOMPASS.getIIDValue())
      );
      FILTER_FEAT_CAT_ACCOLADES = new QFilterExpr(BoolOper.BOOL_AND,
        new QFilterExpr(CompOper.COMP_NOTEQ, ATTR_FEATLINK_OID, IDCONST.COMPASS.getIIDValue()),
        new QFilterExpr(CompOper.COMP_NOTEQ,
            XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_CAT}),ATTR_CAT_OID),
            IDCONST.CATEGORYCOMPASS.getIIDValue()
        )
      );
      FILTER_FEAT_VER_ACCOLADES = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_FEAT_CAT_ACCOLADES,
        new QFilterExpr(CompOper.COMP_NOTEQ,
            XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_CAT, ASC_CAT_VERS}),ATTR_VER_OID),
            IDCONST.VERSIONCOMPASS.getIIDValue()
        )
      );
      FILTER_FEAT_PROD_ACCOLADES = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_FEAT_VER_ACCOLADES,
        new QFilterExpr(CompOper.COMP_NOTEQ,
            XMR.getInstance().getAttrRef(null,CLS_CATFEATLINK,Arrays.asList(new IXAssoc[] {ASC_FEATLINK_CAT, ASC_CAT_VERS, ASC_VER_PROD}),ATTR_PROD_OID),
            IDCONST.PRODUCTCOMPASS.getIIDValue())
      );

      FILTER_CAT_COMPASS = new QFilterExpr(CompOper.COMP_EQ, ATTR_CAT_OID, IDCONST.CATEGORYCOMPASS.getIIDValue());
      FILTER_CAT_ACCOLADES = new QFilterExpr(CompOper.COMP_NOTEQ, ATTR_CAT_OID, IDCONST.CATEGORYCOMPASS.getIIDValue());
      FILTER_CAT_VER_COMPASS = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_CAT_COMPASS,
        new QFilterExpr(CompOper.COMP_EQ,
            XMR.getInstance().getAttrRef(null,CLS_CATEGORY,Arrays.asList(new IXAssoc[] {ASC_CAT_VERS}),ATTR_VER_OID),
            IDCONST.VERSIONCOMPASS.getIIDValue()
        )
      );
      FILTER_CAT_VER_ACCOLADES = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_CAT_ACCOLADES,
        new QFilterExpr(CompOper.COMP_NOTEQ,
            XMR.getInstance().getAttrRef(null,CLS_CATEGORY,Arrays.asList(new IXAssoc[] {ASC_CAT_VERS}),ATTR_VER_OID),
            IDCONST.VERSIONCOMPASS.getIIDValue()
        )
      );
      FILTER_CAT_PROD_COMPASS = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_CAT_VER_COMPASS,
        new QFilterExpr(CompOper.COMP_EQ,
            XMR.getInstance().getAttrRef(null,CLS_CATEGORY,Arrays.asList(new IXAssoc[] {ASC_CAT_VERS,ASC_VER_PROD}),ATTR_PROD_OID),
            IDCONST.PRODUCTCOMPASS.getIIDValue()
        )
      );
      FILTER_CAT_PROD_ACCOLADES = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_CAT_VER_ACCOLADES,
        new QFilterExpr(CompOper.COMP_NOTEQ,
            XMR.getInstance().getAttrRef(null,CLS_CATEGORY,Arrays.asList(new IXAssoc[] {ASC_CAT_VERS,ASC_VER_PROD}),ATTR_PROD_OID),
            IDCONST.PRODUCTCOMPASS.getIIDValue()
        )
      );

      FILTER_VER_COMPASS = new QFilterExpr(CompOper.COMP_EQ, ATTR_VER_OID, IDCONST.VERSIONCOMPASS.getIIDValue());
      FILTER_VER_ACCOLADES = new QFilterExpr(CompOper.COMP_NOTEQ, ATTR_VER_OID, IDCONST.VERSIONCOMPASS.getIIDValue());
      FILTER_VER_PROD_COMPASS = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_VER_COMPASS,
        new QFilterExpr(CompOper.COMP_EQ,
            XMR.getInstance().getAttrRef(null,CLS_VERSION,Arrays.asList(new IXAssoc[] {ASC_VER_PROD}),ATTR_PROD_OID),
            IDCONST.PRODUCTCOMPASS.getIIDValue()
        )
      );
      FILTER_VER_PROD_ACCOLADES = new QFilterExpr(BoolOper.BOOL_AND,
        FILTER_VER_ACCOLADES,
        new QFilterExpr(CompOper.COMP_NOTEQ,
            XMR.getInstance().getAttrRef(null,CLS_VERSION,Arrays.asList(new IXAssoc[] {ASC_VER_PROD}),ATTR_PROD_OID),
            IDCONST.PRODUCTCOMPASS.getIIDValue()
        )
      );

      FILTER_PROD_COMPASS = new QFilterExpr(CompOper.COMP_EQ, ATTR_PROD_OID, IDCONST.PRODUCTCOMPASS.getIIDValue());
      FILTER_PROD_ACCOLADES = new QFilterExpr(CompOper.COMP_NOTEQ, ATTR_PROD_OID, IDCONST.PRODUCTCOMPASS.getIIDValue());

    }
    catch (Exception ignore) {}


    // question input
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {ASC_QMI_SRCUSER});
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {ASC_QMI_SRCUSER, ASC_USER_ORG});
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {ASC_QMI_CREATEUSER});
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {ASC_FLINK_QMI, ASC_REACT_FLINK});
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {ASC_QMI_STATE});
    XMR.addQueryAssocChain(CLS_QUESTIONINPUT, new IXAssoc[] {ASC_QMI_CLASS});
    // standard input
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {ASC_SMI_SRCUSER});
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {ASC_SMI_SRCUSER, ASC_USER_ORG});
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {ASC_SMI_CREATEUSER});
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {ASC_FLINK_SMI, ASC_REACT_FLINK});
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {ASC_SMI_STATE});
    XMR.addQueryAssocChain(CLS_STANDARDINPUT, new IXAssoc[] {ASC_SMI_CLASS});
    // article input
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {ASC_AMI_SRCUSER});
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {ASC_AMI_SRCUSER, ASC_USER_ORG});
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {ASC_AMI_CREATEUSER});
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {ASC_FLINK_AMI, ASC_REACT_FLINK});
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {ASC_AMI_STATE});
    XMR.addQueryAssocChain(CLS_ARTICLEINPUT, new IXAssoc[] {ASC_AMI_CLASS});
    // win/loss input
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {ASC_WMI_SRCUSER});
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {ASC_WMI_SRCUSER, ASC_USER_ORG});
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {ASC_WMI_CREATEUSER});
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {ASC_FLINK_WMI, ASC_REACT_FLINK});
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {ASC_WMI_STATE});
    XMR.addQueryAssocChain(CLS_WINLOSSINPUT, new IXAssoc[] {ASC_WMI_CLASS});
    // summary input
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {ASC_SUMMI_SRCUSER});
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {ASC_SUMMI_SRCUSER, ASC_USER_ORG});
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {ASC_SUMMI_CREATEUSER});
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {ASC_FLINK_SUMMI, ASC_REACT_FLINK});
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {ASC_SUMMI_STATE});
    XMR.addQueryAssocChain(CLS_SUMMARYINPUT, new IXAssoc[] {ASC_SUMMI_CLASS});
    // reaction
    XMR.addQueryAssocChain(CLS_REACTION, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_REACTION, new IXAssoc[] {ASC_REACT_CREATEUSER});
    // problem statement
    XMR.addQueryAssocChain(CLS_PROBLEMSTATEMENT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_PROBLEMSTATEMENT, new IXAssoc[] {ASC_PROBSTM_STATE});
    XMR.addQueryAssocChain(CLS_PROBLEMSTATEMENT, new IXAssoc[] {ASC_PROBSTM_CREATEUSER});
    // product
    XMR.addQueryAssocChain(CLS_PRODUCT, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_PRODUCT, new IXAssoc[] {ASC_PROD_STATE});
    XMR.addQueryAssocChain(CLS_PRODUCT, new IXAssoc[] {ASC_PROD_CLASS});
    XMR.addQueryAssocChain(CLS_PRODUCT, new IXAssoc[] {ASC_PROD_CREATEUSER});
    // version
    XMR.addQueryAssocChain(CLS_VERSION, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_VERSION, new IXAssoc[] {ASC_VER_STATE});
    XMR.addQueryAssocChain(CLS_VERSION, new IXAssoc[] {ASC_VER_CLASS});
    XMR.addQueryAssocChain(CLS_VERSION, new IXAssoc[] {ASC_VER_CREATEUSER});
    // category
    XMR.addQueryAssocChain(CLS_CATEGORY, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_CATEGORY, new IXAssoc[] {ASC_CAT_STATE});
    XMR.addQueryAssocChain(CLS_CATEGORY, new IXAssoc[] {ASC_CAT_CLASS});
    XMR.addQueryAssocChain(CLS_CATEGORY, new IXAssoc[] {ASC_CAT_CREATEUSER});
    // feature
    XMR.addQueryAssocChain(CLS_FEATURE, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_FEATURE, new IXAssoc[] {ASC_FEAT_CREATEUSER});
    XMR.addQueryAssocChain(CLS_FEATURE, new IXAssoc[] {ASC_FEAT_CLASS});
    // feature link
    XMR.addQueryAssocChain(CLS_CATFEATLINK, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_CATFEATLINK, new IXAssoc[] {ASC_FEATLINK_FEAT});
    XMR.addQueryAssocChain(CLS_CATFEATLINK, new IXAssoc[] {ASC_FEATLINK_FEAT, ASC_FEAT_CLASS});
    XMR.addQueryAssocChain(CLS_CATFEATLINK, new IXAssoc[] {ASC_FEATLINK_CREATEUSER});
    XMR.addQueryAssocChain(CLS_CATFEATLINK, new IXAssoc[] {ASC_FEATLINK_STATE});
    
    XMR.addKeywordAssocChain(CLS_CATFEATLINK, new IXAssoc[] {});
    XMR.addKeywordAssocChain(CLS_CATFEATLINK, new IXAssoc[] {ASC_FEATLINK_FEAT});
//    XMR.addKeywordAssocChain(CLS_CATFEATLINK, new IXAssoc[] {ASC_FEATLINK_FEAT, ASC_FEATREV_FEAT});

    // std feature link
    XMR.addQueryAssocChain(CLS_STDFEATLINK, new IXAssoc[] {});
    XMR.addQueryAssocChain(CLS_STDFEATLINK, new IXAssoc[] {ASC_STDFEATLINK_FEAT});
//    XMR.addQueryAssocChain(CLS_STDFEATLINK, new IXAssoc[] {ASC_STDFEATLINK_FEAT, ASC_FEATREV_FEAT});
    // discussion
    XMR.addQueryAssocChain(CLS_DISCUSSION, new IXAssoc[] {});
    
    
  }
}