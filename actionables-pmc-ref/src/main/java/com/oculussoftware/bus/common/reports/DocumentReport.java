package com.oculussoftware.bus.common.reports;

import com.oculussoftware.api.busi.common.reports.*;
import com.oculussoftware.api.busi.common.search.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import java.util.*;
import java.sql.Timestamp;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.repos.query.*;
import com.oculussoftware.repos.xml.*;
import com.oculussoftware.bus.xmeta.XMen;
import com.oculussoftware.util.*;
import com.oculussoftware.bus.common.search.*;
import com.oculussoftware.api.ui.html.AlignKind;
import com.oculussoftware.api.busi.mkt.comm.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.text.*;
import java.awt.Image;
import java.awt.Toolkit;
/**
* Filename:    DocumentReport.java
* Date:        8/8/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class DocumentReport extends BMDataView implements IDocumentReport, IXMLTree
{
	protected IQAttrRefKeyList _lstProd = new QAttrRefKeyList();
	protected IQAttrRefKeyList _lstCat = new QAttrRefKeyList();
	protected IQAttrRefKeyList _lstFeat = new QAttrRefKeyList();
	protected IQAttrRefKeyList _lstVer = new QAttrRefKeyList();

	//style information
	//Title
	protected boolean _hasTitlePage;
	protected boolean _hasName;
	protected boolean _hasDate;
	protected boolean _hasCreator;
	protected String _title;
	protected String _subtitle;
	protected boolean _hasTOC;
	protected boolean _hasApprovalTable;
	protected int _approvalTableNumRows;
	
	//style information
	//Document
	protected HeaderKind _leftHeader = HeaderKind.NONE;
	protected HeaderKind _centerHeader = HeaderKind.NONE;
	protected HeaderKind _rightHeader = HeaderKind.NONE;

	protected FooterKind _leftFooter = FooterKind.NONE;
	protected FooterKind _centerFooter = FooterKind.NONE;
	protected FooterKind _rightFooter = FooterKind.NONE;

	protected String _leftHeaderText;
	protected String _centerHeaderText;
	protected String _rightHeaderText;

	protected String _leftFooterText;
	protected String _centerFooterText;
	protected String _rightFooterText;

	protected List _prodBodySuppData = new Vector();
	protected List _prodAppdxSuppData = new Vector();

	protected List _verBodySuppData = new Vector();
	protected List _verAppdxSuppData = new Vector();

	protected List _catBodySuppData = new Vector();
	protected List _catAppdxSuppData = new Vector();

	protected List _featBodySuppData = new Vector();
	protected List _featAppdxSuppData = new Vector();	

  public ViewType getViewType() throws OculusException
  { return ViewType.DOCUMENTREPORT; }

  public Object dolly() throws OculusException
  {
    IDocumentReport obj = new DocumentReport();
    obj.setIID(getIID());
    obj.setObjectContext(getObjectContext());
    obj.setPersState(getPersState());
    obj.setName(getName());
    obj.setDescription(getDescription());
    obj.setDeleteState(getDeleteState());
    obj.setConfigKind(getConfigKind());
    obj.isActive(isActive());
    obj.setOwnerIID(getOwnerIID());
    obj.setAccessKind(getAccessKind());
    obj.setModuleKind(getModuleKind());
    //dolly the xml
    try
    {
      obj.parseXML(new ByteArrayInputStream(toXML().getBytes()));
    }
    catch(Exception e)
    {
      throw new OculusException(e);
    }
    return obj;
  }

  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository repos = getObjectContext().getRepository();
    if(rs.getBinaryStream(COL_QUERYEXPR) != null)
      parseXML(rs.getBinaryStream(COL_QUERYEXPR));
  }

  public DocumentReport() throws OculusException
  { _xml = this; }

  public DocumentReport(InputStream is) throws OculusException
  { parseXML(is); }

  public IQAttrRef[] getProductDisplayAttrs() throws OculusException
  { return convertQAttrRefs(getProductAttrList(),XMen.CLS_PRODUCT); }

  public IQAttrRef[] getVersionDisplayAttrs() throws OculusException
  { return convertQAttrRefs(getVersionAttrList(),XMen.CLS_VERSION); }

  public IQAttrRef[] getCategoryDisplayAttrs() throws OculusException
  { return convertQAttrRefs(getCategoryAttrList(),XMen.CLS_CATEGORY); }

  public IQAttrRef[] getFeatureDisplayAttrs() throws OculusException
  { return convertQAttrRefs(getFeatureAttrList(),XMen.CLS_CATFEATLINK); }

  public IQAttrRef[] getDisplayAttrs(IXClass xcls) throws OculusException
  {
    if(xcls.getIID().equals(XMen.CLS_PRODUCT.getIID()))
      return getProductDisplayAttrs();
    else if(xcls.getIID().equals(XMen.CLS_VERSION.getIID())) 
      return getVersionDisplayAttrs();
    else if(xcls.getIID().equals(XMen.CLS_CATEGORY.getIID())) 
      return getCategoryDisplayAttrs();
    else if(xcls.getIID().equals(XMen.CLS_CATFEATLINK.getIID())) 
      return getFeatureDisplayAttrs();
    else
      return null;  
  }

  protected IQAttrRef[] convertQAttrRefs(IQAttrRefKeyList list, IXClass xcls) throws OculusException
  {
    IQAttrRef[] attrs = new IQAttrRef[list.size()];
    IXMR xmr = getObjectContext().getRepository().getXMR();
    list.reset();
    for(int i = 0; list.hasMoreAttrRefs(); i++)
      attrs[i] = convertQAttrRef(xmr,xcls,list.nextAttr());
    return attrs;
  }

  protected IQAttrRef convertQAttrRef(IXMR xmr, IXClass targetclass, IQAttrRefKey qark) throws OculusException
  {
    List assocs = new Vector();
    long[] assockeys = qark.getAssocs();
    for(int j = 0; assockeys != null && j < assockeys.length; j++)
      assocs.add(xmr.getAssoc(context,assockeys[j]));
    if(qark.getAttr() == -1)
      return new QAttrRef(assocs,null);
    return xmr.getAttrRef(context, targetclass, assocs, qark.getAttr());
  }

  protected IQAttrRefKey convertQAttrRefKey(IQAttrRef attrref)
  {
    long attrid = -1;
    if(attrref.getAttr() != null)
      attrid = attrref.getAttr().getIID().getLongValue();
    IXAssocChain chain = attrref.getAssocs();
    long[] assocs = new long[chain.size()];
    Iterator itr = chain.iterator();
    for(int i = 0; itr.hasNext(); i++)
    {
      assocs[i] = ((IXAssoc)itr.next()).getIID().getLongValue();
    }  
    return new QAttrRefKey(assocs,attrid);
  }

  protected IQAttrRef getQAttrRef(IXClass xcls, long[] assocs, long attr) throws OculusException
  {
    
    IXMR xmr = getObjectContext().getRepository().getXMR();
    
    List list = new Vector();
    for(int i = 0; i < assocs.length; i++)
      list.add(xmr.getAssoc(getObjectContext(), assocs[i]));
    
    if(attr == -1)//this happens when the display attr is really an assoc (i.e. State...)
      return new QAttrRef(list,null);
    
    return xmr.getAttrRef(getObjectContext(), xcls, list, attr); 
  }

  public boolean hasTitlePage() throws OculusException
  { return _hasTitlePage; }

  public boolean hasName() throws OculusException
  { return _hasName; }

  public boolean hasDate() throws OculusException
  { return _hasDate; }

  public boolean hasCreator() throws OculusException
  { return _hasCreator; }

  public String getTitle() throws OculusException
  { return _title; }

  /**
   * getProductAttrList method comment.
   */
  public IQAttrRefKeyList  getProductAttrList() throws com.oculussoftware.api.sysi.OculusException 
  {
    _lstProd.reset();
    return _lstProd;
  }

  /**
   * getVersionAttrList method comment.
   */
  public IQAttrRefKeyList  getVersionAttrList() throws com.oculussoftware.api.sysi.OculusException 
  {
    _lstVer.reset();
    return _lstVer;
  }

  /**
   * getCategoryAttrList method comment.
   */
  public   IQAttrRefKeyList  getCategoryAttrList() throws com.oculussoftware.api.sysi.OculusException 
  {
    _lstCat.reset();
    return _lstCat;
  }
  
  /**
   * getFeatureAttrList method comment.
   */
  public   IQAttrRefKeyList  getFeatureAttrList() throws com.oculussoftware.api.sysi.OculusException 
  {
    _lstFeat.reset();
    return _lstFeat;
  }

  public String getSubtitle() throws OculusException
  { return _subtitle; }

  public boolean hasTOC() throws OculusException
  { return _hasTOC; }

  public boolean hasApprovalTable() throws OculusException
  { return _hasApprovalTable; }

  public int getNumberOfApprovalTableRows() throws OculusException
  { return _approvalTableNumRows; }

  public HeaderKind getHeaderKind(AlignKind ak) throws OculusException
  { 
    if(AlignKind.LEFT.equals(ak))
      return _leftHeader;
    else if(AlignKind.CENTER.equals(ak))
      return _centerHeader; 
    else if(AlignKind.RIGHT.equals(ak))
      return _rightHeader;
    return HeaderKind.NONE; 
  }

  public String getHeaderText(AlignKind ak) throws OculusException
  { 
    if(AlignKind.LEFT.equals(ak))
      return _leftHeaderText;
    else if(AlignKind.CENTER.equals(ak))
      return _centerHeaderText; 
    else if(AlignKind.RIGHT.equals(ak))
      return _rightHeaderText;
    return null; 
  }

  public FooterKind getFooterKind(AlignKind ak) throws OculusException
  {
    if(AlignKind.LEFT.equals(ak))
      return _leftFooter;
    else if(AlignKind.CENTER.equals(ak))
      return _centerFooter; 
    else if(AlignKind.RIGHT.equals(ak))
      return _rightFooter;
    return FooterKind.NONE;
  }

  public String getFooterText(AlignKind ak) throws OculusException
  { 
    if(AlignKind.LEFT.equals(ak))
      return _leftFooterText;
    else if(AlignKind.CENTER.equals(ak))
      return _centerFooterText; 
    else if(AlignKind.RIGHT.equals(ak))
      return _rightFooterText;
    return null; 
  }

  public SupportDataKind[] getProductSuppData(boolean body) throws OculusException
  { 
    if (body)
      return getSDKArray(_prodBodySuppData);
    else
      return getSDKArray(_prodAppdxSuppData); 
  }

  public SupportDataKind[] getVersionSuppData(boolean body) throws OculusException
  { 
    if (body)
      return getSDKArray(_verBodySuppData);
    else
      return getSDKArray(_verAppdxSuppData); 
  }

  public SupportDataKind[] getCategorySuppData(boolean body) throws OculusException
  { 
    if (body)
      return getSDKArray(_catBodySuppData);
    else
      return getSDKArray(_catAppdxSuppData); 
  }

  public SupportDataKind[] getFeatureSuppData(boolean body) throws OculusException
  { 
    if (body)
      return getSDKArray(_featBodySuppData);
    else
      return getSDKArray(_featAppdxSuppData); 
  }

  public IDocumentReport addProductDisplayAttr(long[] assocs, long attr) throws OculusException
  { return addProductDisplayAttr(getQAttrRef(XMen.CLS_PRODUCT,assocs,attr)); }

  public IDocumentReport addProductDisplayAttr(IQAttrRef attrref) throws OculusException
  { getProductAttrList().addAttr(convertQAttrRefKey(attrref)); return this; }

  public IDocumentReport addVersionDisplayAttr(long[] assocs, long attr) throws OculusException
  { return addVersionDisplayAttr(getQAttrRef(XMen.CLS_VERSION,assocs,attr)); }

  public IDocumentReport addVersionDisplayAttr(IQAttrRef attrref) throws OculusException
  { getVersionAttrList().addAttr(convertQAttrRefKey(attrref)); return this; }

  public IDocumentReport addCategoryDisplayAttr(long[] assocs, long attr) throws OculusException
  { return addCategoryDisplayAttr(getQAttrRef(XMen.CLS_CATEGORY,assocs,attr)); }

  public IDocumentReport addCategoryDisplayAttr(IQAttrRef attrref) throws OculusException
  { getCategoryAttrList().addAttr(convertQAttrRefKey(attrref)); return this; }

  public IDocumentReport addFeatureDisplayAttr(long[] assocs, long attr) throws OculusException
  { return addFeatureDisplayAttr(getQAttrRef(XMen.CLS_CATFEATLINK,assocs,attr)); }

  public IDocumentReport addFeatureDisplayAttr(IQAttrRef attrref) throws OculusException
  { getFeatureAttrList().addAttr(convertQAttrRefKey(attrref)); return this; }
  
  public boolean hasImage() throws OculusException
  {
    IFileList list = (IFileList)getObjectContext().getCRM().getCompObject(getObjectContext(),"FileList",getIID());
    return (list.size() > 0);
  }
  
  public Image getImage() throws OculusException
  {
    return Toolkit.getDefaultToolkit().createImage(getImageAttachment().getContent());
  }
  
  public IAttachment getImageAttachment() throws OculusException
  {
    //should only ever have one
    IFileList files = (IFileList)getObjectContext().getCRM().getCompObject(getObjectContext(),"FileList",getIID());
    return files.nextFile();
  }
  
  public IAttachment attachImage() throws OculusException
  {
    //try to make sure that any old images are deleted
    IFileList list = (IFileList)getObjectContext().getCRM().getCompObject(getObjectContext(),"FileList",getIID(), true);
    while(list.hasNext())
      list.nextFile().delete();
    IAttachment thisFile = (IAttachment)getObjectContext().getCRM().getCompObject(getObjectContext(),"File",(IDataSet)null,true);
    thisFile.setDefnObject((IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",IDCONST.FILE.getIIDValue()));
    thisFile.setParentObjectIID(getIID());
    return thisFile;
  }
  
  public IDocumentReport hasTitlePage(boolean hasTitlePage) throws OculusException
  { _hasTitlePage = hasTitlePage; return this; }

  public IDocumentReport hasName(boolean hasName) throws OculusException
  { _hasName = hasName; return this; }

  public IDocumentReport hasDate(boolean hasDate) throws OculusException
  { _hasDate = hasDate; return this; }

  public IDocumentReport hasCreator(boolean hasCreator) throws OculusException
  { _hasCreator = hasCreator; return this; }

  public IDocumentReport setTitle(String title) throws OculusException
  { _title = title; return this; }

  public IDocumentReport setSubtitle(String subtitle) throws OculusException
  { _subtitle = subtitle; return this; }

  public IDocumentReport hasTOC(boolean hastoc) throws OculusException
  { _hasTOC = hastoc; return this; }

  public IDocumentReport hasApprovalTable(boolean hasApprovalTable) throws OculusException
  { _hasApprovalTable = hasApprovalTable; return this; }

  public IDocumentReport setNumberOfApprovalTableRows(int num) throws OculusException
  { _approvalTableNumRows = num; return this; }

  public IDocumentReport setHeaderKind(AlignKind ak, HeaderKind hk) throws OculusException
  { 
    if (ak == AlignKind.LEFT)
      _leftHeader = hk;
    else if (ak == AlignKind.CENTER)
      _centerHeader = hk;
    else if (ak == AlignKind.RIGHT)
      _rightHeader = hk;  
    return this; 
  }

  public IDocumentReport setHeaderText(AlignKind ak, String text) throws OculusException
  { 
    if (ak == AlignKind.LEFT)
      _leftHeaderText = text;
    else if (ak == AlignKind.CENTER)
      _centerHeaderText = text;
    else if (ak == AlignKind.RIGHT)
      _rightHeaderText = text; 
    return this; 
  }

  public IDocumentReport setFooterKind(AlignKind ak, FooterKind fk) throws OculusException
  { 
    if (ak == AlignKind.LEFT)
      _leftFooter = fk;
    else if (ak == AlignKind.CENTER)
      _centerFooter = fk;
    else if (ak == AlignKind.RIGHT)
      _rightFooter = fk; 
    return this; 
  }

  public IDocumentReport setFooterText(AlignKind ak, String text) throws OculusException
  { 
    if (ak == AlignKind.LEFT)
      _leftFooterText = text;
    else if (ak == AlignKind.CENTER)
      _centerFooterText = text;
    else if (ak == AlignKind.RIGHT)
      _rightFooterText = text;  
    return this; 
  }

  public IDocumentReport addProductSuppData(boolean body, SupportDataKind sdk) throws OculusException
  { 
    if(body)
      _prodBodySuppData.add(sdk);
    else
      _prodAppdxSuppData.add(sdk);
    return this; 
  }

  public IDocumentReport addVersionSuppData(boolean body, SupportDataKind sdk) throws OculusException
  { 
    if(body)
      _verBodySuppData.add(sdk);
    else
      _verAppdxSuppData.add(sdk);
    return this; 
  }

  public IDocumentReport addCategorySuppData(boolean body, SupportDataKind sdk) throws OculusException
  { 
    if(body)
      _catBodySuppData.add(sdk);
    else
      _catAppdxSuppData.add(sdk);
    return this; 
  }

  public IDocumentReport addFeatureSuppData(boolean body, SupportDataKind sdk) throws OculusException
  { 
    if(body)
      _featBodySuppData.add(sdk);
    else
      _featAppdxSuppData.add(sdk);
    return this; 
  }

  public IDocumentReport clear() throws OculusException
  {
    if(getPersState() == PersState.UNMODIFIED)
      setPersState(PersState.MODIFIED);
    getProductAttrList().clear();
    getVersionAttrList().clear();
    getCategoryAttrList().clear(); 
    getFeatureAttrList().clear();
    return this; 
  }
  
  /**
   * toXML method comment.
   */
  public String toXML() throws com.oculussoftware.api.sysi.OculusException, javax.xml.parsers.ParserConfigurationException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.newDocument();
    Element ndRoot = doc.createElement("XMLSearcher");
    doc.appendChild(ndRoot);
    Element ndBasic = doc.createElement("DocReport");
    ndRoot.appendChild(ndBasic);
    
    //categories
    Element ndCat = doc.createElement("CategoryAttrList");
    ndCat.appendChild(_lstCat.toXML(doc));
    ndBasic.appendChild(ndCat);
    //feats
    Element ndFeat = doc.createElement("FeatureAttrList");
    ndFeat.appendChild(_lstFeat.toXML(doc));
    ndBasic.appendChild(ndFeat);
    //vers
    Element ndVer = doc.createElement("VersionAttrList");
    ndVer.appendChild(_lstVer.toXML(doc));
    ndBasic.appendChild(ndVer);
    //prods
    Element ndProd = doc.createElement("ProductAttrList");
    ndProd.appendChild(_lstProd.toXML(doc));
    ndBasic.appendChild(ndProd);

    //style information
    //Element ndStyle = doc.createElement("Style");
    //ndStyle.appendChild(getStyleXML(doc));
    ndBasic.appendChild(getStyleXML(doc));
    
    return ndRoot.toString();
  }
  
  private void DocReport(Node nd) throws OculusException, ParseException
  {
    NodeList nl = nd.getChildNodes();
    //for all if basicreport then call basic report
    for (int i=0; i<nl.getLength(); i++)
    {
      nd = nl.item(i);
      if (nd.getNodeName().equals("CategoryAttrList"))
        _lstCat = new QAttrRefKeyList(nd);
      if (nd.getNodeName().equals("FeatureAttrList"))
        _lstFeat = new QAttrRefKeyList(nd);
      if (nd.getNodeName().equals("VersionAttrList"))
        _lstVer = new QAttrRefKeyList(nd);
      if (nd.getNodeName().equals("ProductAttrList"))
        _lstProd = new QAttrRefKeyList(nd);
      if (nd.getNodeName().equals("Style"))
        readStyleXML(nd);
    }
  }
  
  public void parseXML(InputStream is) 
  throws OculusException
  {
    try
    {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
      DocumentBuilder db = dbf.newDocumentBuilder();
      Document doc = db.parse(is);

      //root node
      Node nd = doc.getFirstChild(); 
      NodeList nl = nd.getChildNodes();
      //for all if basicreport then call basic report
      for (int i=0; i<nl.getLength(); i++)
      {
        nd = nl.item(i);
        if (nd.getNodeName().equals("DocReport"))
        {
          DocReport(nd);
        }
      }    
    }
    catch(OculusException e)
    {
      throw e;
    }
    catch(Exception ex)
    {
      throw(new OculusException(ex));
    }

  }
  
  private Element getStyleXML(Document doc) throws OculusException
  {
    Element ndStyle = doc.createElement("Style");

    //Title Page
    Element ndTitle = doc.createElement("Title");

    ndTitle.setAttribute("hasTitlePage", _hasTitlePage+"");
    ndTitle.setAttribute("hasName", _hasName+"");
    ndTitle.setAttribute("hasDate", _hasDate+"");
    ndTitle.setAttribute("hasCreator", _hasCreator+"");
    if (_title != null)
      ndTitle.setAttribute("title", _title);
    if (_subtitle != null)
      ndTitle.setAttribute("subtitle", _subtitle);
    ndTitle.setAttribute("hasTOC", _hasTOC+"");
    ndTitle.setAttribute("hasApprovalTable", _hasApprovalTable+"");
    ndTitle.setAttribute("approvalTableNumRows", _approvalTableNumRows+"");
    
    ndStyle.appendChild(ndTitle);      
    //Document info
    Element ndDoc = doc.createElement("DocumentInfo");

    ndDoc.setAttribute("leftHeader", _leftHeader+"");
    ndDoc.setAttribute("centerHeader", _centerHeader+"");
    ndDoc.setAttribute("rightHeader", _rightHeader+"");
    
    ndDoc.setAttribute("leftFooter", _leftFooter+"");
    ndDoc.setAttribute("centerFooter", _centerFooter+"");
    ndDoc.setAttribute("rightFooter", _rightFooter+"");

    if (_leftHeaderText != null)
      ndDoc.setAttribute("leftHeaderText", _leftHeaderText+"");
    if (_centerHeaderText != null)
      ndDoc.setAttribute("centerHeaderText", _centerHeaderText+"");
    if (_rightHeaderText != null)
      ndDoc.setAttribute("rightHeaderText", _rightHeaderText+"");

    if (_leftFooterText != null)
      ndDoc.setAttribute("leftFooterText", _leftFooterText+"");
    if (_centerFooterText != null)
      ndDoc.setAttribute("centerFooterText", _centerFooterText+"");
    if (_rightFooterText != null)
      ndDoc.setAttribute("rightFooterText", _rightFooterText+"");        

  
    if (_prodBodySuppData != null)
      ndDoc.setAttribute("prodBodySuppData", SupportDatatoXML(getSDKArray(_prodBodySuppData)));
    if (_prodAppdxSuppData != null)
      ndDoc.setAttribute("prodAppdxSuppData", SupportDatatoXML(getSDKArray(_prodAppdxSuppData)));            
    
    if (_verBodySuppData != null)
      ndDoc.setAttribute("verBodySuppData", SupportDatatoXML(getSDKArray(_verBodySuppData)));            
    if (_verAppdxSuppData != null)
      ndDoc.setAttribute("verAppdxSuppData", SupportDatatoXML(getSDKArray(_verAppdxSuppData)));            
    
    if (_catBodySuppData != null)
      ndDoc.setAttribute("catBodySuppData", SupportDatatoXML(getSDKArray(_catBodySuppData)));            
    if (_catAppdxSuppData != null)
      ndDoc.setAttribute("catAppdxSuppData", SupportDatatoXML(getSDKArray(_catAppdxSuppData)));            
    
    if (_featBodySuppData != null)
      ndDoc.setAttribute("featBodySuppData", SupportDatatoXML(getSDKArray(_featBodySuppData)));            
    if (_featAppdxSuppData != null)
      ndDoc.setAttribute("featAppdxSuppData", SupportDatatoXML(getSDKArray(_featAppdxSuppData)));                
      
    ndStyle.appendChild(ndDoc);
    
    return ndStyle;
  }

  private void readStyleXML(Node nd) throws OculusException
  {

    Document doc = nd.getOwnerDocument();

    //get named Title Page element
    //get attribues on that elem
    Node ndTitle = (doc.getElementsByTagName("Title")).item(0);
    NamedNodeMap titNM = ndTitle.getAttributes();

    
    _hasTitlePage = (titNM.getNamedItem("hasTitlePage")).getNodeValue().equals("true")?true:false;
    _hasName = (titNM.getNamedItem("hasName")).getNodeValue().equals("true")?true:false;
    _hasDate = (titNM.getNamedItem("hasDate")).getNodeValue().equals("true")?true:false;
    _hasCreator = (titNM.getNamedItem("hasCreator")).getNodeValue().equals("true")?true:false;    
    _hasTOC = (titNM.getNamedItem("hasTOC")).getNodeValue().equals("true")?true:false;
    _hasApprovalTable = (titNM.getNamedItem("hasApprovalTable")).getNodeValue().equals("true")?true:false;  
    if (_hasApprovalTable)
    {
      _approvalTableNumRows = Integer.parseInt(titNM.getNamedItem("approvalTableNumRows").getNodeValue());
    }
    else
    {
      _approvalTableNumRows = 0;
    }
    if(titNM.getNamedItem("title") != null)
      _title = titNM.getNamedItem("title").getNodeValue();
    if(titNM.getNamedItem("subtitle") != null)
      _subtitle = titNM.getNamedItem("subtitle").getNodeValue();    

    Node ndDoc = (doc.getElementsByTagName("DocumentInfo")).item(0);
    NamedNodeMap docNM = ndDoc.getAttributes();

    //no need to check null guaranteed to be set to Kind.NONE
    _leftHeader = HeaderKind.getInstance(Integer.parseInt(docNM.getNamedItem("leftHeader").getNodeValue()));//.equals("true")?true:false;
    _centerHeader = HeaderKind.getInstance(Integer.parseInt(docNM.getNamedItem("centerHeader").getNodeValue()));//.equals("true")?true:false;
    _rightHeader = HeaderKind.getInstance(Integer.parseInt(docNM.getNamedItem("rightHeader").getNodeValue()));//.equals("true")?true:false;

    _leftFooter = FooterKind.getInstance(Integer.parseInt(docNM.getNamedItem("leftFooter").getNodeValue()));//.equals("true")?true:false;
    _centerFooter = FooterKind.getInstance(Integer.parseInt(docNM.getNamedItem("centerFooter").getNodeValue()));//.equals("true")?true:false;
    _rightFooter = FooterKind.getInstance(Integer.parseInt(docNM.getNamedItem("rightFooter").getNodeValue()));//.equals("true")?true:false;

    if (docNM.getNamedItem("leftHeaderText") != null)
      _leftHeaderText = docNM.getNamedItem("leftHeaderText").getNodeValue();
    if (docNM.getNamedItem("centerHeaderText") != null)
      _centerHeaderText = docNM.getNamedItem("centerHeaderText").getNodeValue();
    if (docNM.getNamedItem("rightHeaderText") != null)
      _rightHeaderText = docNM.getNamedItem("rightHeaderText").getNodeValue();
      
    if (docNM.getNamedItem("leftFooterText") != null)
      _leftFooterText = docNM.getNamedItem("leftFooterText").getNodeValue();
    if (docNM.getNamedItem("centerFooterText") != null)
      _centerFooterText = docNM.getNamedItem("centerFooterText").getNodeValue();
    if (docNM.getNamedItem("rightFooterText") != null)
      _rightFooterText = docNM.getNamedItem("rightFooterText").getNodeValue();        
    
    if (docNM.getNamedItem("prodBodySuppData") != null)
      _prodBodySuppData = XMLtoSupportData(docNM.getNamedItem("prodBodySuppData").getNodeValue());
    if (docNM.getNamedItem("verBodySuppData") != null)
      _verBodySuppData = XMLtoSupportData(docNM.getNamedItem("verBodySuppData").getNodeValue());
    if (docNM.getNamedItem("catBodySuppData") != null)
      _catBodySuppData = XMLtoSupportData(docNM.getNamedItem("catBodySuppData").getNodeValue());
    if (docNM.getNamedItem("featBodySuppData") != null)
      _featBodySuppData = XMLtoSupportData(docNM.getNamedItem("featBodySuppData").getNodeValue());

    if (docNM.getNamedItem("prodAppdxSuppData") != null)        
      _prodAppdxSuppData = XMLtoSupportData(docNM.getNamedItem("prodAppdxSuppData").getNodeValue());
    if (docNM.getNamedItem("verAppdxSuppData") != null)      
      _verAppdxSuppData = XMLtoSupportData(docNM.getNamedItem("verAppdxSuppData").getNodeValue());
    if (docNM.getNamedItem("catAppdxSuppData") != null)
      _catAppdxSuppData = XMLtoSupportData(docNM.getNamedItem("catAppdxSuppData").getNodeValue());
    if (docNM.getNamedItem("featAppdxSuppData") != null)      
      _featAppdxSuppData = XMLtoSupportData(docNM.getNamedItem("featAppdxSuppData").getNodeValue());  

    /*
    _isBodyProdSuppData = (titNM.getNamedItem("isBodyProdSuppData")).getNodeValue().equals("true")?true:false;
    _isBodyVerSuppData = (titNM.getNamedItem("isBodyVerSuppData")).getNodeValue().equals("true")?true:false;
    _isBodyCatSuppData = (titNM.getNamedItem("isBodyCatSuppData")).getNodeValue().equals("true")?true:false;      
    _isBodyFeatSuppData = (titNM.getNamedItem("isBodyFeatSuppData")).getNodeValue().equals("true")?true:false;
    */  
  }
  
  protected SupportDataKind[] getSDKArray(List lst) throws OculusException
  {
    SupportDataKind[] sdks = new SupportDataKind[lst.size()];
    Iterator itr = lst.iterator();
    for(int i = 0; itr.hasNext(); i++)
      sdks[i] = (SupportDataKind)itr.next();
    return sdks;  
  }
  
  protected List getList(long[] g)
  {
    List q = new Vector();
    for(int i = 0; i < g.length; i++)
      q.add(new Long(g[i]));
    return q;  
  }
  
  private String SupportDatatoXML(SupportDataKind[] SuppData)
  {
    long[] l = new long[SuppData.length];
    for (int i=0; i < SuppData.length; i++)
    {
      l[i] = SuppData[i].getIntValue();
    }
    return StringUtil.getArray2CSV(l);
  }

  private List XMLtoSupportData(String s) throws OculusException
  {
    List lst = new Vector();
    if(s == null || s.equals("")) return lst;
    int[] l = StringUtil.getIntArray(s);
    for(int i = 0; i < l.length; i++)
      lst.add(SupportDataKind.getInstance(l[i]));
    return lst;
  }
}
