package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.common.reports.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.util.*;
import java.util.*;
import java.sql.Timestamp;
import java.io.*;
import java.text.*;
import com.oculussoftware.api.ui.html.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.*;
/**
 * Insert the type's description here.
 * Creation date: (8/8/00 3:58:28 PM)
 * @author: 
 */
public class DocXML implements IDocXML 
{
	private IQAttrRefKeyList _lstProd = new QAttrRefKeyList();
	private IQAttrRefKeyList _lstCat = new QAttrRefKeyList();
	private IQAttrRefKeyList _lstFeat = new QAttrRefKeyList();
	private IQAttrRefKeyList _lstVer = new QAttrRefKeyList();

	//style information
	//Title
	boolean _hasTitlePage;
	boolean _hasName;
	boolean _hasDate;
	boolean _hasCreator;
	String _title;
	String _subtitle;
	boolean _hasTOC;
	boolean _hasApprovalTable;
	int _approvalTableNumRows;
	
	//style information
	//Document
	HeaderKind _leftHeader = HeaderKind.NONE;
	HeaderKind _centerHeader = HeaderKind.NONE;
	HeaderKind _rightHeader = HeaderKind.NONE;

	FooterKind _leftFooter = FooterKind.NONE;
	FooterKind _centerFooter = FooterKind.NONE;
	FooterKind _rightFooter = FooterKind.NONE;

	String _leftHeaderText;
	String _centerHeaderText;
	String _rightHeaderText;

	String _leftFooterText;
	String _centerFooterText;
	String _rightFooterText;

	SupportDataKind[] _prodSuppData;
	boolean _isBodyProdSuppData;

	SupportDataKind[] _verSuppData;
	boolean _isBodyVerSuppData;

	SupportDataKind[] _catSuppData;
	boolean _isBodyCatSuppData;

	SupportDataKind[] _featSuppData;
	boolean _isBodyFeatSuppData;

  public DocXML()
  {
  }

  public DocXML(InputStream is) throws OculusException
  {
    parseXML(is);
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
    
    return doc.toString();
  }

  private void DocReport(Node nd) throws OculusException, ParseException
  {
    NodeList nl = nd.getChildNodes();
    //for all if basicreport then call basic report
    for (int i=0; i<nl.getLength(); i++)
    {
      nd = nl.item(i);
      if (nd.getNodeName().equals("CategoryAttrList"))
      {
        _lstCat = new QAttrRefKeyList(nd);
      }
      if (nd.getNodeName().equals("FeatureAttrList"))
      {
        _lstFeat = new QAttrRefKeyList(nd);
      }
      if (nd.getNodeName().equals("VersionAttrList"))
      {
        _lstVer = new QAttrRefKeyList(nd);
      }
      if (nd.getNodeName().equals("ProductAttrList"))
      {
        _lstProd = new QAttrRefKeyList(nd);
      }
      if (nd.getNodeName().equals("Style"))
      {
        readStyleXML(nd);
      }
      

    }
  }

  /**
   * getCategoryAttrList method comment.
   */
  public   IQAttrRefKeyList  getCategoryAttrList() throws com.oculussoftware.api.sysi.OculusException {
    return _lstCat;
  }

  /**
   * getFeatureAttrList method comment.
   */
  public   IQAttrRefKeyList  getFeatureAttrList() throws com.oculussoftware.api.sysi.OculusException {
    return _lstFeat;
  }

  /**
   * getProductAttrList method comment.
   */
  public   IQAttrRefKeyList  getProductAttrList() throws com.oculussoftware.api.sysi.OculusException {
    return _lstProd;
  }

  /**
   * getVersionAttrList method comment.
   */
  public   IQAttrRefKeyList  getVersionAttrList() throws com.oculussoftware.api.sysi.OculusException {
    return _lstVer;
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

  private Element getStyleXML(Document doc)
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

  
    if (_prodSuppData != null)
    {
      ndDoc.setAttribute("prodSuppData", SupportDatatoXML(_prodSuppData));        
      ndDoc.setAttribute("isBodyProdSuppData", _isBodyProdSuppData+"");        
      
    }
    if (_verSuppData != null)
    {
      ndDoc.setAttribute("verSuppData", SupportDatatoXML(_verSuppData));        
      ndDoc.setAttribute("isBodyVerSuppData", _isBodyVerSuppData+"");        
      
    }
    if (_catSuppData != null)
    {
      ndDoc.setAttribute("catSuppData", SupportDatatoXML(_catSuppData));        
      ndDoc.setAttribute("isBodyCatSuppData", _isBodyCatSuppData+"");        
      
    }
    if (_featSuppData != null)
    {
      ndDoc.setAttribute("featSuppData", SupportDatatoXML(_featSuppData));        
      ndDoc.setAttribute("isBodyFeatSuppData", _isBodyFeatSuppData+"");        
      
    }      
      
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
    _title = titNM.getNamedItem("title").getNodeValue();
    _subtitle = titNM.getNamedItem("subtitle").getNodeValue();    

    Node ndDoc = (doc.getElementsByTagName("DocumentInfo")).item(0);
    NamedNodeMap docNM = ndDoc.getAttributes();

    _leftHeader = HeaderKind.getInstance(Integer.parseInt(docNM.getNamedItem("leftHeader").getNodeValue()));//.equals("true")?true:false;
    _centerHeader = HeaderKind.getInstance(Integer.parseInt(docNM.getNamedItem("centerHeader").getNodeValue()));//.equals("true")?true:false;
    _rightHeader = HeaderKind.getInstance(Integer.parseInt(docNM.getNamedItem("rightHeader").getNodeValue()));//.equals("true")?true:false;

    _leftFooter = FooterKind.getInstance(Integer.parseInt(docNM.getNamedItem("leftFooter").getNodeValue()));//.equals("true")?true:false;
    _centerFooter = FooterKind.getInstance(Integer.parseInt(docNM.getNamedItem("centerFooter").getNodeValue()));//.equals("true")?true:false;
    _rightFooter = FooterKind.getInstance(Integer.parseInt(docNM.getNamedItem("rightFooter").getNodeValue()));//.equals("true")?true:false;
    
    _prodSuppData = XMLtoSupportData(docNM.getNamedItem("prodSuppData").getNodeValue());
    _verSuppData = XMLtoSupportData(docNM.getNamedItem("verSuppData").getNodeValue());
    _catSuppData = XMLtoSupportData(docNM.getNamedItem("catSuppData").getNodeValue());
    _featSuppData = XMLtoSupportData(docNM.getNamedItem("featSuppData").getNodeValue());  
    
    _isBodyProdSuppData = (titNM.getNamedItem("isBodyProdSuppData")).getNodeValue().equals("true")?true:false;
    _isBodyVerSuppData = (titNM.getNamedItem("isBodyVerSuppData")).getNodeValue().equals("true")?true:false;
    _isBodyCatSuppData = (titNM.getNamedItem("isBodyCatSuppData")).getNodeValue().equals("true")?true:false;      
    _isBodyFeatSuppData = (titNM.getNamedItem("isBodyFeatSuppData")).getNodeValue().equals("true")?true:false;
      
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

  private SupportDataKind[] XMLtoSupportData(String s) throws OculusException
  {

    int[] l = StringUtil.getIntArray(s);
    SupportDataKind[] suppData = new SupportDataKind[l.length];
    for (int i=0; i < l.length; i++)
    {
      suppData[i] = SupportDataKind.getInstance(l[i]);
    }
    return null;
  }
}
