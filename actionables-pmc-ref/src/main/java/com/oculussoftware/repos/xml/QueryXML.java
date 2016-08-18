package com.oculussoftware.repos.xml;

import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.util.*;
import java.util.*;
import java.sql.Timestamp;
import java.io.*;
import java.text.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.*;

public class QueryXML implements IQueryXML
{

	/**  */
 //----------------------------------------------------------------------
	// Private class vars
	//----------------------------------------------------------------------
	//----------------------------------------------------------------------
	// Private instance vars
	//----------------------------------------------------------------------
	private IQSelectKey   _selNode     = new QSelectKey();
	private long          _targetClass = 0;
	private IQFilterKey   _filterNode  = new QFilterKey();
	private IQSortKey     _sortNode    = new QSortKey();
	private List _lstStates = new LinkedList();
	private Timestamp tsFromDate = null;
	private Timestamp tsToDate = null;  


/*
  List lstScopeItems = new LinkedList();
  List lstQueryClasses = new LinkedList();
  List lstDisplayAttrs = new LinkedList();
  List lstTreeSortAttrs = new LinkedList();
  List lstFilters = new LinkedList();
*/
  /** 
  Public Constructors 
  */
  public QueryXML(InputStream is) 
  throws OculusException
  {
    parseXML(is);
  }


  public QueryXML()
  throws OculusException
  {
  }


  //----------------------------------------------------------------------
  // Public methods
  //----------------------------------------------------------------------
  
  public void setTargetClass(long cls)
  { 
    _targetClass = cls;
  }


  public long getTargetClass()
  {
    return _targetClass;
  }


  public IQSelectKey getSelect()
  { 
    return _selNode;
  }


  public IQFilterKey getFilter()
  {
    return _filterNode;
  }


  public IQSortKey getSort()
  {
    return _sortNode;
  }


  public List getStates()
  {
    return _lstStates;
  }


  public void setFromDate(Timestamp date)
  {
    tsFromDate = date;
  }


  public Timestamp getFromDate()
  {
    return tsFromDate;
  }


  public void setToDate(Timestamp date)
  {
    tsToDate = date;
  }


  public Timestamp getToDate()
  {
    return tsToDate;
  }


  public String toXML()
  throws ParserConfigurationException,OculusException
  {
    
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = db.newDocument();
    Element ndRoot = doc.createElement("XMLSearcher");
    doc.appendChild(ndRoot);
    Element ndBasic = doc.createElement("BasicReport");
    ndRoot.appendChild(ndBasic);
    
    //target classs
    Element ndTarget = doc.createElement("Target");
    ndTarget.setAttribute("ID",_targetClass+"");
    ndBasic.appendChild(ndTarget);
    //IQSelectKey
    ndBasic.appendChild(_selNode.toXML(doc));  
    //IQSortKey
    ndBasic.appendChild(_sortNode.toXML(doc));
    //IQFilterKey
    if (_filterNode.toXML(doc)!=null)
    {
      ndBasic.appendChild(_filterNode.toXML(doc));
    }
    //DateRange
    if (tsFromDate != null || tsToDate != null)
    {        
      Element ndDateRange = doc.createElement("DateRange");
      if (tsFromDate!=null)
        ndDateRange.setAttribute("From", tsFromDate.getTime()+"");
      if (tsToDate!=null)
        ndDateRange.setAttribute("To", tsToDate.getTime()+"");    
      ndBasic.appendChild(ndDateRange);   
    }
    //state list
    ndBasic.setAttribute("StateList", StringUtil.buildCommaDelList(_lstStates));
    /*
    addDislayAttrs(doc, ndBasic);
    addSortAttrs(doc, ndBasic);
    addFilters(doc, ndBasic);
    addStates(doc, ndBasic);
    addDates(doc, ndBasic);
    */
    return ndRoot.toString();
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
        if (nd.getNodeName().equals("BasicReport"))
        {
          basicReport(nd);
          //throw new OculusException(new Exception());
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


  private void basicReport(Node nd) throws OculusException, ParseException
  {
    NodeList nl = nd.getChildNodes();
    //for all if basicreport then call basic report
    for (int i=0; i<nl.getLength(); i++)
    {
      nd = nl.item(i);
      if (nd.getNodeName().equals("Target"))
      {
        _targetClass = Long.parseLong(nd.getAttributes().getNamedItem("ID").getNodeValue());
      }
      if (nd.getNodeName().equals("IQSelectKey"))
      {
        _selNode = new QSelectKey(nd);
      }
      if (nd.getNodeName().equals("IQSortKey"))
      {
        _sortNode = new QSortKey(nd);
      }     
      if (nd.getNodeName().equals("IQFilterKey"))
      {
        _filterNode = new QFilterKey(nd);
      }         

      if (nd.getNodeName().equals("StateList"))
      {
        NamedNodeMap nm = nd.getAttributes();
        String strCommaDelList = nm.getNamedItem("IDS").getNodeValue();
        _lstStates = com.oculussoftware.util.StringUtil.getList(strCommaDelList);
        
      }
    
      if (nd.getNodeName().equals("DateRange"))
      {
        NamedNodeMap nm = nd.getAttributes();
        Node ndFrom = nm.getNamedItem("From");
        if (ndFrom!=null)
          tsFromDate = new Timestamp( Long.parseLong(nm.getNamedItem("From").getNodeValue()));
       
        Node ndTo = nm.getNamedItem("To");
        if (ndTo!=null)        
          tsToDate = new Timestamp( Long.parseLong(nm.getNamedItem("To").getNodeValue()));       
      }                        

    }
  }


  public void setState(long l)
  {
    _lstStates.add(new Long(l));
  }
}
