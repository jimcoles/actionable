package com.oculussoftware.api.busi.common.reports;

import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.busi.mkt.comm.IAttachment;
import com.oculussoftware.api.ui.html.AlignKind;
import java.awt.Image;
/**
* $Workfile: IDocumentReport.java $
* Create Date: 6/25/2000
* Description: A savable report definition that supports a tabular
*              result.
*
* Copyright 7-31-2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: IDocumentReport.java $
 * 
 * *****************  Version 14  *****************
 * User: Eroyal       Date: 9/22/00    Time: 9:39a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * javadoc
 * 
 * *****************  Version 13  *****************
 * User: Eroyal       Date: 8/28/00    Time: 12:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * changes for Dan R.
 * 
 * *****************  Version 12  *****************
 * User: Eroyal       Date: 8/22/00    Time: 3:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 11  *****************
 * User: Znemazie     Date: 8/22/00    Time: 10:36a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * now is an XMLTree object
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 8/21/00    Time: 5:53p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * document report
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 8/19/00    Time: 12:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 8  *****************
 * User: Eroyal       Date: 8/18/00    Time: 6:00p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * doc report
 * 
 * *****************  Version 7  *****************
 * User: Eroyal       Date: 8/18/00    Time: 2:32p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 6  *****************
 * User: Eroyal       Date: 8/17/00    Time: 5:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * doc report
 * 
 * *****************  Version 5  *****************
 * User: Eroyal       Date: 8/16/00    Time: 6:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 8/11/00    Time: 11:55a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * 
 * *****************  Version 3  *****************
 * User: Eroyal       Date: 8/08/00    Time: 5:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * setting up docreport
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 12:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/common/reports
 * Repackaged.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:33a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/busi/reports
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:52a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/api/busi/reports
*/

/** 
* Defines the subclass interface necessary to persist a Custom MRD report.
*
* @author Egan Royal
*/
public interface IDocumentReport extends IRDataView, java.io.Serializable, IXMLTree
{
  //Accessors
  /**
  * This accessor method returns an array of IQAttrRefs that are the
  * Product Display Attributes.
  * @return An array of IQAttrRefs that are the Product Display Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IQAttrRef[] getProductDisplayAttrs() throws OculusException;
  
  /**
  * This accessor method returns an array of IQAttrRefs that are the
  * ProductVersion Display Attributes.
  * @return An array of IQAttrRefs that are the ProductVersion Display 
  * Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IQAttrRef[] getVersionDisplayAttrs() throws OculusException;
  
  /**
  * This accessor method returns an array of IQAttrRefs that are the
  * Category Display Attributes.
  * @return An array of IQAttrRefs that are the Category Display 
  * Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IQAttrRef[] getCategoryDisplayAttrs() throws OculusException;
  
  /**
  * This accessor method returns an array of IQAttrRefs that are the
  * FeatureCategoryLink Display Attributes.
  * @return An array of IQAttrRefs that are the FeatureCategoryLink Display 
  * Attributes.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IQAttrRef[] getFeatureDisplayAttrs() throws OculusException;
  
  /**
  * This accessor method takes an IXClass and returns an array of IQAttrRefs 
  * for that class.  This method delegates to the more one of the other four
  * "get__DisplayAttrs()" methods.  The method returns null if the IXClass is 
  * not one of the four (CLS_PRODUCT, CLS_VERSION, CLS_CATEGORY, CLS_CATFEATLINK)
  * @param xcls The IXClass. 
  * @return An array of IQAttrRefs that are the Display Attributes for that class.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IQAttrRef[] getDisplayAttrs(IXClass xcls) throws OculusException;

  /**
  * This accessor method returns true iff the Report has a printable 
  * Title Page.
  * @return true iff the Report has a printable Title Page.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasTitlePage() throws OculusException;
  
  /**
  * This accessor method returns true iff the Report has a printable 
  * name.
  * @return true iff the Report has a printable name.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasName() throws OculusException;
  
  /**
  * This accessor method returns true iff the Report has a printable 
  * date.
  * @return true iff the Report has a printable date.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasDate() throws OculusException;
  
  /**
  * This accessor method returns true iff the Report has a printable 
  * creator (IUser).
  * @return true iff the Report has a printable creator (IUser).
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasCreator() throws OculusException;
  
  /**
  * This accessor method returns true iff the Report has a printable 
  * image.
  * @return true iff the Report has a printable image.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasImage() throws OculusException;
  
  /**
  * This accessor method returns an Image to be printed on the 
  * Title Page.  This method simply converts the IAttachment to
  * an Image.
  * @return The image to be printed on the Title Page.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public Image getImage() throws OculusException;
  
  /**
  * This accessor method returns an IAttachment (Image) to be printed 
  * on the Title Page.
  * @return The IAttachment (image) to be printed on the Title Page.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IAttachment getImageAttachment() throws OculusException;

  /**
  * This accessor method returns the Title of the Report.
  * @return The Title of the Report.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getTitle() throws OculusException;
  
  /**
  * This accessor method returns the Subtitle of the Report.
  * @return The Subtitle of the Report.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getSubtitle() throws OculusException;
  
  /**
  * This accessor method returns true iff the Report has a printable
  * Table of Contents.
  * @return true iff the Report has a Table of Contents.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasTOC() throws OculusException;
  
  /**
  * This accessor method returns true iff the Report has a printable
  * Approval Table.
  * @return true iff the Report has a Approval Table.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public boolean hasApprovalTable() throws OculusException;
  
  /**
  * This accessor method returns the nuber of rows in the Approval Table.
  * @return The number of rows in the Approval Table.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public int getNumberOfApprovalTableRows() throws OculusException;
  
  /**
  * This accessor method returns the HeaderKind given an AlignKind.
  * @param ak The AlignKind for which the HeaderKind is desired.
  * @return The HeaderKind given the AlignKind.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public HeaderKind getHeaderKind(AlignKind ak) throws OculusException;
  
  /**
  * This accessor method returns the Header text given an AlignKind.
  * @param ak The AlignKind for which the Header text is desired.
  * @return The Header text given the AlignKind.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getHeaderText(AlignKind ak) throws OculusException;
  
  /**
  * This accessor method returns the FooterKind given an AlignKind.
  * @param ak The AlignKind for which the FooterKind is desired.
  * @return The FooterKind given the AlignKind.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public FooterKind getFooterKind(AlignKind ak) throws OculusException;
  
  /**
  * This accessor method returns the Footer text given an AlignKind.
  * @param ak The AlignKind for which the Footer text is desired.
  * @return The Footer text given the AlignKind.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public String getFooterText(AlignKind ak) throws OculusException;
  
  /**
  * This accessor method returns an array of SupportDataKinds for the
  * body or the appendix.  The method returns an array of SupportDataKinds
  * for Products.
  * @param body true for body, false for appendix.
  * @return An array of SupportDataKinds for the Report for Products.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public SupportDataKind[] getProductSuppData(boolean body) throws OculusException;
  
  /**
  * This accessor method returns an array of SupportDataKinds for the
  * body or the appendix.  The method returns an array of SupportDataKinds
  * for ProductVersions.
  * @param body true for body, false for appendix.
  * @return An array of SupportDataKinds for the Report for ProductVersions.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public SupportDataKind[] getVersionSuppData(boolean body) throws OculusException;
  
  /**
  * This accessor method returns an array of SupportDataKinds for the
  * body or the appendix.  The method returns an array of SupportDataKinds
  * for Categories.
  * @param body true for body, false for appendix.
  * @return An array of SupportDataKinds for the Report for Catagories.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public SupportDataKind[] getCategorySuppData(boolean body) throws OculusException;
  
  /**
  * This accessor method returns an array of SupportDataKinds for the
  * body or the appendix.  The method returns an array of SupportDataKinds
  * for FeatureCategoryLinks.
  * @param body true for body, false for appendix.
  * @return An array of SupportDataKinds for the Report for FeatureCategoryLinks.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public SupportDataKind[] getFeatureSuppData(boolean body) throws OculusException;
  
  //Mutators
  
  /**
  * This mutator method attaches and returns an IAttachment.
  * @return An empty IAttachment.
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IAttachment attachImage() throws OculusException;
  
  /**
  * This mutator method adds a Product Display Attribute.
  * @param assocs An array of longs that are ids of IXAssocs.
  * @param attr A long that is the id of the Attribute.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addProductDisplayAttr(long[] assocs, long attr) throws OculusException;
  
  /**
  * This mutator method adds a Product Display Attribute.
  * @param attrref The IQAttrRef that is to be added to the list of 
  * Product Display Attributes.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addProductDisplayAttr(IQAttrRef attrref) throws OculusException;
  
  /**
  * This mutator method adds a ProductVersion Display Attribute.
  * @param assocs An array of longs that are ids of IXAssocs.
  * @param attr A long that is the id of the Attribute.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addVersionDisplayAttr(long[] assocs, long attr) throws OculusException;
  
  /**
  * This mutator method adds a ProductVersion Display Attribute.
  * @param attrref The IQAttrRef that is to be added to the list of 
  * ProductVersion Display Attributes.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addVersionDisplayAttr(IQAttrRef attrref) throws OculusException;
  
  /**
  * This mutator method adds a Category Display Attribute.
  * @param assocs An array of longs that are ids of IXAssocs.
  * @param attr A long that is the id of the Attribute.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addCategoryDisplayAttr(long[] assocs, long attr) throws OculusException;
  
  /**
  * This mutator method adds a Category Display Attribute.
  * @param attrref The IQAttrRef that is to be added to the list of 
  * Category Display Attributes.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addCategoryDisplayAttr(IQAttrRef attrref) throws OculusException;
  
  /**
  * This mutator method adds a FeatureCategoryLink Display Attribute.
  * @param assocs An array of longs that are ids of IXAssocs.
  * @param attr A long that is the id of the Attribute.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addFeatureDisplayAttr(long[] assocs, long attr) throws OculusException;
  
  /**
  * This mutator method adds a FeatureCategoryLink Display Attribute.
  * @param attrref The IQAttrRef that is to be added to the list of 
  * FeatureCategoryLink Display Attributes.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addFeatureDisplayAttr(IQAttrRef attrref) throws OculusException;
  
  /**
  * This mutator method sets whether the Report has a printable Title Page or not.
  * @param hasTitlePage true if the Report has a printable Title Page, false otherwise.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport hasTitlePage(boolean hasTitlePage) throws OculusException;
  
  /**
  * This mutator method sets whether the Report has a printable name or not.
  * @param hasName true if the Report has a printable name, false otherwise.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport hasName(boolean hasName) throws OculusException;
  
  /**
  * This mutator method sets whether the Report has a printable date or not.
  * @param hasDate true if the Report has a printable date, false otherwise.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport hasDate(boolean hasDate) throws OculusException;
  
  /**
  * This mutator method sets whether the Report has a printable creator (IUser) or not.
  * @param hasCreator true if the Report has a printable creator (IUser), false otherwise.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport hasCreator(boolean hasCreator) throws OculusException;
  
  /**
  * This mutator method sets the printable Title of the Report.
  * @param title The printable Title of the Report.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setTitle(String title) throws OculusException;
  
  /**
  * This mutator method sets the printable Subtitle of the Report.
  * @param title The printable Subtitle of the Report.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setSubtitle(String subtitle) throws OculusException;
  
  /**
  * This mutator method sets whether the Report has a printable Table of Contents or not.
  * @param hasTOC true if the Report has a printable Table of Contents, false otherwise.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport hasTOC(boolean hasTOC) throws OculusException;
  
  /**
  * This mutator method sets whether the Report has a printable ApprovalTable or not.
  * @param hasApprovalTable true if the Report has a printable Approval Table, false otherwise.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport hasApprovalTable(boolean hasApprovalTable) throws OculusException;
  
  /**
  * This mutator method sets the number of Approval Table rows.
  * @param num The number of Approval Table rows.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setNumberOfApprovalTableRows(int num) throws OculusException;

  /**
  * This mutator method sets the HeaderKind for the given AlignKind.
  * @param ak The AlignKind of the Header to be set.
  * @param hk The value to be set.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setHeaderKind(AlignKind ak, HeaderKind hk) throws OculusException;
  
  /**
  * This mutator method sets the Header text for the given AlignKind.
  * @param ak The AlignKind of the Header to be set.
  * @param text The value to be set.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setHeaderText(AlignKind ak, String text) throws OculusException;
  
  /**
  * This mutator method sets the FooterKind for the given AlignKind.
  * @param ak The AlignKind of the Footer to be set.
  * @param fk The value to be set.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setFooterKind(AlignKind ak, FooterKind fk) throws OculusException;
  
  /**
  * This mutator method sets the Footer text for the given AlignKind.
  * @param ak The AlignKind of the Footer to be set.
  * @param text The value to be set.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport setFooterText(AlignKind ak, String text) throws OculusException;
  
  /**
  * This mutator method adds a SupportDataKind for the body or the appendix
  * to the list of Product Support Data.
  * @param body true for body false for appendix.
  * @param sdk The SupportDataKind to be added.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addProductSuppData(boolean body, SupportDataKind sdk) throws OculusException;
  
  /**
  * This mutator method adds a SupportDataKind for the body or the appendix
  * to the list of ProductVersion Support Data.
  * @param body true for body false for appendix.
  * @param sdk The SupportDataKind to be added.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addVersionSuppData(boolean body, SupportDataKind sdk) throws OculusException;
  
  /**
  * This mutator method adds a SupportDataKind for the body or the appendix
  * to the list of Category Support Data.
  * @param body true for body false for appendix.
  * @param sdk The SupportDataKind to be added.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addCategorySuppData(boolean body, SupportDataKind sdk) throws OculusException;
  
  /**
  * This mutator method adds a SupportDataKind for the body or the appendix
  * to the list of FeatureCategoryLink Support Data.
  * @param body true for body false for appendix.
  * @param sdk The SupportDataKind to be added.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport addFeatureSuppData(boolean body, SupportDataKind sdk) throws OculusException;

  /**
  * The method clears out the object.  All lists are emptied and the XML
  * is reset to nothing.  The method is used when editing a DocumentReport.
  * @return this
  * @exception com.oculussoftware.api.sysi.OculusException  
  */
  public IDocumentReport clear() throws OculusException;
}
