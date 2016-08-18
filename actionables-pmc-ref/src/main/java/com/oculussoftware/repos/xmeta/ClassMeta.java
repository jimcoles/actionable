package com.oculussoftware.repos.xmeta;

/**
* $Workfile: ClassMeta.java $
* Create Date:  5-12-2000
* Description: Holds extended meta data on classes that supports dynamic query
*              formulation.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: ClassMeta.java $
 * 
 * *****************  Version 15  *****************
 * User: Jcoles       Date: 9/09/00    Time: 3:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * New addAttr( ) overload.
 * 
 * *****************  Version 14  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Issue 2120.  Added get/setIsRoleBased( ) and IsRoleParent( ).
 * 
 * *****************  Version 13  *****************
 * User: Jcoles       Date: 8/30/00    Time: 9:20a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 1925 and 2150: Fixing search scope problems.  Added index info
 * get/sets.
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:09p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added get/setIsView( ) and related.
 * 
 * *****************  Version 11  *****************
 * User: Sshafi       Date: 8/05/00    Time: 10:16a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 10  *****************
 * User: Jcoles       Date: 8/03/00    Time: 11:48a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Added isConfigurable boolean for attributes.
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 8  *****************
 * User: Sshafi       Date: 7/25/00    Time: 1:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 7/19/00    Time: 6:20p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * removed exception throw from addAttr( ).
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 7/18/00    Time: 9:00a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Exception throw.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Change id representation.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:23p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/xmeta
 * Made classes to extend appropriate interface from api.repi.xmeta.
 * Changed all (java) class references to interface refs.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Made to implement interfaces from ...api.repi.xmeta.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 6/30/00    Time: 11:43a
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
 * Backup.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/25/00    Time: 9:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Add 3 types of maket input classes.  Added start of User and Org
 * attributes.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/15/00    Time: 9:40a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/xmeta
 * Inital create.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 5/14/00    Time: 10:58p
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/xmeta
*/

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.util.*;

public class ClassMeta implements IXClass
{
  //-------------------------------------------------------------------
  // Private static vars
  //-------------------------------------------------------------------
  
  //-------------------------------------------------------------------
  // Public constants
  //-------------------------------------------------------------------
  
  //-------------------------------------------------------------------
  // Private instance vars
  //-------------------------------------------------------------------
  private IIIDEnum  _classid = null;
  private String    _table = null;
  private String    _syn = null;
  private String    _displayname = null;
  private IXClassAttr _primKeyAttr = null;
  private List      _attrs = new Vector();
  private List      _keywordSelectAttrs = new Vector();
  private boolean   _bCommon = false;  // true implies that item of this class can be child of any other item
  private boolean   _bRecursive = false; // true implies class has recursive association to itself
  private String    _indexTable = null;  // table name of index table for recursive classes.
  private String    _indexParCol = "PARENTID";
  private String    _indexChildCol = "CHILDID";
  private IQFilterExpr _defFilter = null;
  private boolean  _isView = false;  // true implies this xmeta class corresponds to (is a view of) multiple bmr classes.
  private List     _allClassIDs = new Vector();
  private boolean  _bIsRoleBased = false;
  private boolean  _bIsRoleParent = false;
  
  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------
  public ClassMeta(IIIDEnum classid, String table, String syn, String displayname)
  {
    _classid = classid;
    _table = table;
    _syn = syn;
    _displayname = displayname;
    _allClassIDs.add(classid);
    XMR.addClass(this);
  }

  //-------------------------------------------------------------------
  // Package methods
  //-------------------------------------------------------------------
  
  //-------------------------------------------------------------------
  // Public methods
  //-------------------------------------------------------------------
  public List getTableAttrs( ) { return _attrs; }
  public List getKeywordSelectAttrs() { return _keywordSelectAttrs; }
  public IIID getIID( ) { return _classid.getIIDValue(); }
  public String getTableName( ) { return _table; }
  public String getSyn( ) { return _syn; }
  public String getDisplayName() {return _displayname; }

  public void setPrimaryKeyAttr(IXClassAttr attr)
  {
    _primKeyAttr = attr;
  }

  public IXClassAttr getPrimaryKeyAttr()
  {
    return _primKeyAttr;
  }
  
  public void setClassDefnFilter(IQFilterExpr filter) {
    _defFilter = filter;
  }
  
  public IQFilterExpr getClassDefnFilter() {
    return _defFilter;
  }
  
  public IXClass setIsCommon(boolean bCommon) {
    _bCommon = bCommon;
    return this;
  }
  public boolean getIsCommon() {return _bCommon; }
  public IXClass setIsRecursive(boolean bRecursive) {
    _bRecursive = bRecursive;
    return this;
  }
  public boolean getIsRecursive(){
    return _bRecursive;
  }
  public IXClass setIndexTable(String tablename){
    _indexTable = tablename;
    return this;
  }
  public String getIndexTable() {
    return _indexTable;
  }
  public IXClass setIndexParentColName(String name) 
  {
    _indexParCol = name;
    return this;
  }
  public String getIndexParentColName() { return _indexParCol; }
  public IXClass setIndexChildColName(String name) 
  {
    _indexChildCol = name;
    return this;
  }
  public String getIndexChildColName() { return _indexChildCol; }
  
  public IXClassAttr addAttr(IIID attrid, String colname, String displayname, Primitive type, AttributeKind kind, boolean isConfigurable)
  {
    ClassAttrMeta cam = new ClassAttrMeta(attrid, colname, displayname, type, kind);
    addAttr(cam, isConfigurable);
    return cam;
  }
  public IXClassAttr addAttr(IXClassAttr attr, boolean isConfigurable)
  {
    if (!_attrs.contains(attr))
      _attrs.add(attr);
    XMR.addClassAttrAsc(new ClassAttrAscMeta(this, attr, isConfigurable));  
    return attr;
  }

  public IXClassAttr addKeywordSelectAttr(IIID attrid, String colname, String displayname, Primitive type, AttributeKind kind)
  {
    ClassAttrMeta cam = new ClassAttrMeta(attrid, colname, displayname, type, kind);
    _keywordSelectAttrs.add(cam);
    return cam;
  }

  public String addKeywordSelectAttr(String literal)
  {
    _keywordSelectAttrs.add(literal);
    return literal;
  }
  
  public IXClass setIsView(boolean isView) 
  { _isView = isView;
    return this;
  }
  public boolean getIsView() { return _isView; }
  
  public IXClass setExtraClassIDs(IIIDEnum[] classids)
  {
    setIsView(true);
    List xtra = Arrays.asList(classids);
    _allClassIDs.addAll(xtra);
    return this;
  }
  public List getAllClassIDs() 
  {
    return _allClassIDs;
  }

	public IXClass setIsRoleBased(boolean is)
  { 
    _bIsRoleBased = is; 
    return this;
  }
	public boolean getIsRoleBased(){ return _bIsRoleBased; }
  public IXClass setIsRoleParent(boolean is)
  {
    _bIsRoleParent = is; 
    return this;
  }
  public boolean getIsRoleParent() {return _bIsRoleParent; };
}