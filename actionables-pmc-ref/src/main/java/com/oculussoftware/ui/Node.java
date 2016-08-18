package com.oculussoftware.ui;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.system.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;

/**
* Filename:    SvtAllUpdateTree.java
* Date:        10-23-1999
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.1
*/
public final class Node
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  * DES00347        Zain Nemazie	  5/23/00			Added Report tree nodes
  * --              djr             6/23/00     Modified Report tree nodes for new GUI
  */
  public static final String ROOT = "root";
  public static final String TASKS = "tasks";
  public static final String INBOX = "inbox";
  public static final String OUTBOX = "outbox";
  public static final String ACTIVE = "active";
  public static final String STANDARDS = "standards";
  public static final String REVIEW = "review";
  public static final String TOPICS = "topics";
  public static final String ONESPEC = "onespec";
  public static final String COMM = "comm";
	public static final String REPORTS = "reports";
  public static final String TRASH = "trash";
  public static final String ADMIN = "admin";
	public static final String HARD_DRIVE_SPACE = "harddrivespace";
	public static final String ENG_SPEC_FOLDERS = "engspecfolder";
	public static final String MAIL_SERVER = "mailserver";
	public static final String LOGIN_LOG = "loginlog";
	public static final String USER_LICENSES = "userlicenses";
	public static final String LICENSE_KEY = "licensekey";
	public static final String EXTRANET_URL = "extraneturl";  
  public static final String CONFIG = "config";
  public static final String PUBLICFOLDER = "sharedfolder";
  public static final String FOLDER = "folder";
  public static final String SEARCHFOLDER = "searchfolder";
  public static final String STDCOLL = "stdcoll";
  public static final String COMPASSTREE = "comptree";
	public static final String REPORTTREE = "reporttree";
	public static final String REPORTS_COMPASS = "reportscompass";
	public static final String REPORTS_ACCOLADES = "reportsaccolades";
	// djr 06/23/00
  public static final String REPORTS_COMPASS_STANDARD = "reportscompassstandard";
  public static final String REPORTS_COMPASS_CUSTOM = "resportscompasscustom";
  public static final String REPORTS_COMPASS_MRD = "reportscompassmrd";
  public static final String REPORTS_ACCOLADES_STANDARD = "reportsaccoladesstandard";
  public static final String REPORTS_ACCOLADES_CUSTOM = "reportsaccoladescustom";
  public static final String REPORTS_ACCOLADES_MRD = "reportsaccoladesmrd";
  /* djr 06/23/00
  public static final String REPORTS_BASELINECOMPARISON = "baselinecomparison";
	public static final String REPORTS_BASICSEARCH = "basicsearch";
	public static final String REPORTS_STANDARD = "standard";
	public static final String REPORTS_PERCENTCOMPLETE = "percentcomplete";
	public static final String REPORTS_FEATURE = "feature";
	public static final String REPORTS_INPUTREACTIONS = "inputreactions";
	public static final String REPORTS_QASUMMARY = "qasummary";
	public static final String REPORTS_TRACE = "trace";
	public static final String REPORTS_WINLOSS = "winloss";
	public static final String REPORTS_IGNOREPREVFEATURES = "ignoreprevfeatures";
	public static final String REPORTS_BASELINEDISPLAY = "baselinedisplay";
	public static final String REPORTS_MRD = "mrd";
  */
  public static final String PRODUCT = "prod";
  public static final String VERSION = "ver";
  public static final String CATEGORY = "cat";
  public static final String BASELINE = "base";
  public static final String BASECATEGORY = "basecat";

  public static final String USER = "user";
  public static final String USERGROUP = "usergroup";
  public static final String USERORGANIZATION = "userorganization";
  public static final String SETTINGS = "settings";
  public static final String PERMISSIONS = "permissions";
 
  public static final String ATTRIBUTE = "attribute";
  public static final String PROCESSES = "processes";
  public static final String WORKFLOW = "workflow";
  
  
  public static final String STATEMACHINE = "statemachine";
  public static final String PROCESSROLE = "processrole";
  public static final String TERM = "term";
  public static final String QAINP = "qainp";
  public static final String ORGPROF = "orgprof";
  public static final String USERPROF = "userprof";
  
  public static final String FORMS = "forms";  
  public static final String MARKETINPUTFORMS = "mktinpforms";
  public static final String STANDARDINPUTFORM= "stdinp";
  public static final String ARTICLEINPUTFORM = "artinp";    
  public static final String QUESTIONFORM = "qainp";    
  public static final String WINLOSSFORM = "winqainp";    
  public static final String REACTIONFORM = "reactform";  
  public static final String PROBLEMSTATEMENTFORM = "prbform";
  
  public static final String OTHERCOMPASSFORMS = "othercompassforms";
  public static final String PROFILEFORMS = "profileforms";
  public static final String SPECIFICATIONFORMS = "specforms";
  public static final String PRODUCTFORM = "prodform";
  public static final String VERSIONFORM = "verform";  
  public static final String CATEGORYFORM = "catform";
  public static final String FEATUREFORM = "featform";  
  public static final String ALTERNATIVEFORM = "altform";
  
  public static final String ATTRIBUTEGROUP = "attributegroup";
  public static final String FORMROUTING = "formrouting";
  public static final String CONTEXTLIST = "contextlist";
  public static final String DISPOSITION = "disposition";
  
  public static final String delimiter = "_";

  private String _name, _parentID, _parentNodeID, _nodeID, _fullNodeID, _id, _img;
  private String _onclick, _onselect;
  private boolean _hasChildren = true;
	private boolean _loaded = false;
  private String _type;
  private ListMap _children;

  public Node()
  {
	_name = "Root Node";
	_id = "1";
	_img = "";
	_parentID = null;
	_parentNodeID = null;
	_nodeID = "root";
	_fullNodeID = "root";
	_onclick = "parent.parent.Attrib.location='/system/OculusHtml/Selection.htm';";
  _onselect = "";
	_type = Node.ROOT;
  }  
  public Node(IBusinessObject busi, String type)
	throws OculusException
  {
	_name = busi.getName();
	_id = busi.getIID().getLongValue()+"";
	_nodeID = delimiter+_id;
	_type = type;
	_img = "";
	_onclick = "parent.parent.Attrib.location='/system/OculusHtml/Selection.htm';";
  _onselect = "";
  }  
  public Node(String nodeID, String name, String type)
  {
	_name = name;
	_img = "";
	_id = nodeID;
	_nodeID = delimiter+_id;
	_type = type;
	_onclick = "parent.parent.Attrib.location='/system/OculusHtml/Selection.htm';";
  _onselect = "";
  }  
  public void copy(Node node2)
  {
	_name = node2._name;
	_id = node2._id;
	_parentID = node2._parentID;
	_parentNodeID = node2._parentNodeID;
	_type = node2._type;
	_img = node2._img;
	_children.putAll(node2._children);
	_onclick = node2._onclick;
  _onselect = node2._onselect;
  }  
  public ListMap getChildren() { return _children; }  
  public String getFullNodeID() { return _fullNodeID; }  
  public String getID() { return _id; }  
  public String getImg() { return _img; }  
  public String getName() { return _name; }  
  public String getNodeID() { return _nodeID; }  
  public String getParentID() { return _parentID; }  
  public String getParentNodeID() { return _parentNodeID; }  
  public String getType() { return _type; }  
  public boolean hasChildren() { return _hasChildren; }  
	public void hasChildren(boolean has) { _hasChildren = has; }
  public void putChild(Node child)
  {
	_children.add(child);
  }  
  public void putChildren(ListMap children)
  {
	if (children != null && children.size() > 0)
	  _hasChildren = true;
	else
	  _hasChildren = false;
	_children = children;
  }  
  public void setID(String id) { _id = id; }  
  public void setImg(String filename) { _img = filename; }  
  public void setName(String name) { _name = name; }  
  public void setNodeID(String nodeid) { _nodeID = nodeid; }  
  public void setOnClick(String onclick) { _onclick = onclick; }  
  public void setOnSelect(String onselect) { _onselect = onselect; }  
  public void setParentID(String parentID) { _parentID = parentID; }  
  public void setParentNodeID(String parentNodeID)
  {
	_parentNodeID = parentNodeID;
	_fullNodeID = _parentNodeID+_nodeID;
  }
  
  
  public void isLoaded(boolean load) { _loaded = load; }
  public boolean isLoaded() { return _loaded; }
  
  public String toString()
  {
	return  "ocuTree.add(\""+getParentNodeID()+"\",\""+getNodeID()+"\",\"/system/OculusImages/Tree/Icons/"+_img+"\",\""+_name+"\",\""+_onclick+"\", \""+_onselect+"\",\" \" "+ ( hasChildren() ? ",\"saveNodeState(false, '"+getFullNodeID()+"', '"+getFullNodeID()+"');\"" : "")+" ); ";
  }  
}