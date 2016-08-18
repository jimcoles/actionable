package com.oculussoftware.bus.common.search;

/**
* $Workfile: KeywordSearch.java $
* Create Date:  6/4/2000
* Description: Represents a simple but general business object query.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: KeywordSearch.java $
 * 
 * *****************  Version 16  *****************
 * User: Jcoles       Date: 9/26/00    Time: 5:56p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Support issue #2510 - Use keyword filter.  Add 'distinct' to select.
 * 
 * *****************  Version 15  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Bug Fix: #2537
 * 
 * *****************  Version 14  *****************
 * User: Sshafi       Date: 9/14/00    Time: 12:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Bug Fix: 2544
 * 
 * *****************  Version 13  *****************
 * User: Sshafi       Date: 8/28/00    Time: 3:05p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Bug Fix: 2135
 * 
 * *****************  Version 12  *****************
 * User: Sshafi       Date: 8/10/00    Time: 9:53a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 11  *****************
 * User: Jcoles       Date: 8/02/00    Time: 8:46p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Using COMP_LIKE instead of COMP_CONTAINS for string comparisons.
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 8/02/00    Time: 12:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 7/31/00    Time: 4:24p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 8  *****************
 * User: Sshafi       Date: 7/28/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 7  *****************
 * User: Sshafi       Date: 7/27/00    Time: 4:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 6  *****************
 * User: Sshafi       Date: 7/27/00    Time: 11:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 5  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 7/25/00    Time: 1:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 3  *****************
 * User: Sshafi       Date: 7/24/00    Time: 5:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 2  *****************
 * User: Eroyal       Date: 7/13/00    Time: 3:49p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * tried to get it to compile
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:59a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/search
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:53a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/search
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/08/00    Time: 8:35p
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/search
*/
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.api.sysi.license.*;

import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.repos.query.*;

import com.oculussoftware.system.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.bus.xmeta.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.api.busi.common.search.*;

import java.util.*;
import java.sql.*;

/**
*
* NOTE: Renamed from SimpleQuery.java.
*/
public class KeywordSearch implements IKeywordSearch
{
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  public static final int TYP_ALL     = 1;
  public static final int TYP_PROD    = 2;
  public static final int TYP_VER     = 3;
  public static final int TYP_CAT     = 4;
  public static final int TYP_FEAT    = 5;
  public static final int TYP_DISC    = 6;
  public static final int TYP_TRANCOM = 7;
  
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  private List      _classIDs   = null;
  private String    _searchText = null;
  private String    _keywords = null;
  private boolean   _allWords = false;
  private boolean   _allFields = true;
  private boolean   _syn = true;
  private Timestamp _fromDate = null;
  private Timestamp _toDate = null;
  
  private ReposSearcher _rs = null;
  protected IDCONST    _scopetype;
  protected ModuleKind _mk;
  private List    _queries = null;
  private long _lScopeObjID;
  private IXClass _scopeClass = null;
  private boolean _bDTs = false;
  private IObjectContext _context = null;
  
  //-------------------------------------------------------------------
  //-------------------------------------------------------------------
  public KeywordSearch() throws OculusException
  {
    super();
  }

  //-------------------------------------------------------------------
  // Public instance methods
  //-------------------------------------------------------------------

  public List getQueries()
    throws OculusException
  {
    if (_queries == null) genQuery();
    return _queries;
  }

  public ReposSearcher getSearcher()
    throws OculusException
  {
    if (_rs == null) _rs = new ReposSearcher(getObjectContext(), getQueries());
    return _rs;
  }

  public String getSQL() throws OculusException
  {
    return getSearcher().getSQL();
  }

  public void setIncludeDTs() { _bDTs = true; }
  public boolean getIncludeDTs() { return _bDTs; }

  public void setFilter(String searchText) { _searchText = searchText; }
  public String getFilter(String searchText) { return _searchText; }
  
  public void setScopeObject(IXClass classID, long loid)
  {
    _scopeClass = classID;
    _lScopeObjID = loid;
  }
//  public ClassMeta getScopeClass()
//    throws OculusException
//  {
//    return ClassMeta.getClassMeta(_scopeClassID.getLongValue());
//  }
  public long getScopeID()  { return _lScopeObjID; }
  
  
  /** Set target classes of the search. */
  public void setQueryClasses(List classSpec)
  {
    _classIDs = new Vector();
    _classIDs.addAll(classSpec);
  }
  /** Get target classes of the search. */
  public List getQueryClasses() { return _classIDs; }
  
  
  
  public ViewType getViewType() throws OculusException
  {
    return ViewType.SEARCHQUERY;
  }
  
  public void setKeywords(String keywords)
    throws OculusException
  {
    _keywords = keywords;
  }

  public String getKeywords()
    throws OculusException
  {
    return _keywords;
  }
    
  public void setSearchForAllKeywords(boolean all)
    throws OculusException
  {
    _allWords = all;
  }
    
  public void setSearchAllFields(boolean all)
    throws OculusException
  {
    _allFields = all;
  }

  public boolean getSearchForAllKeywords()
    throws OculusException
  {
    return _allWords;
  }

  public boolean getSearchAllFields()
    throws OculusException
  {
    return _allFields;
  }


  private IQFilterExpr addKeywordFilters(IQAttrRef attrRef, BoolOper bool)
    throws OculusException
  {
    IQFilterExpr attribFilter = null;
    StringTokenizer words = new StringTokenizer(getKeywords()," ");
    while (words.hasMoreTokens())
    {
      String keyword = words.nextToken();
      attribFilter = combineFilters(bool, attribFilter, new QFilterExpr(CompOper.COMP_LIKE, attrRef, keyword, usesSynonyms()));
    }

    return attribFilter;
  }

  /** Adds the 'extended' keywords filter. */
  private IQFilterExpr addExtKeywordFilters(BoolOper bool)
    throws OculusException
  {
    IQFilterExpr attribFilter = null;
    StringTokenizer words = new StringTokenizer(getKeywords()," ");
    while (words.hasMoreTokens())
    {
      String keyword = words.nextToken();
      attribFilter = combineFilters(bool, attribFilter, new QFilterExpr(CompOper.COMP_EXT_KEYWORD_LIKE, null, keyword, usesSynonyms()));
    }

    return attribFilter;
  }
  
  private IQFilterExpr addIDFilters(IQAttrRef attrRef, BoolOper bool)
    throws OculusException
  {
    IQFilterExpr attribFilter = null;
    StringTokenizer words = new StringTokenizer(getKeywords()," ");
    while (words.hasMoreTokens())
    {
      String keyword = words.nextToken();
      attribFilter = combineFilters(bool, attribFilter, new QFilterExpr(CompOper.COMP_EQ, attrRef, new Long(keyword)));
    }

    return attribFilter;
  }


  private IQFilterExpr combineFilters(BoolOper bool, IQFilterExpr one, IQFilterExpr two)
    throws OculusException
  {
    if (one == null) return two;
    if (two == null) return one;
    return new QFilterExpr(bool,one,two);
  }


  private void setScope(IQuery query, IXClass target, IXClass connectToClass, IXClass scopeClass)
    throws OculusException
  {
    boolean scope = false;
    if (scopeClass != null)
      query.setScope(getObjectContext(),target,connectToClass,scopeClass,_lScopeObjID);

  }
  
  private void setFilters(IQuery query, IXClass target)
    throws OculusException
  {
    BoolOper bool = (getSearchForAllKeywords() ? BoolOper.BOOL_AND : BoolOper.BOOL_OR);
    IXMR xmr = getObjectContext().getRepository().getXMR();
    IQFilterExpr totalFilter = query.getFilter().getExpr();
    IQFilterExpr filter = null;
    if (getSearchAllFields())
    {
      List attributes = xmr.getKeywordAttrs(getObjectContext(),target);
      for (Iterator atts = attributes.iterator(); atts.hasNext(); )
      {
        IQAttrRef attrRef = (IQAttrRef)atts.next();
        filter = combineFilters(BoolOper.BOOL_OR,filter,addKeywordFilters(attrRef, bool));
      }
      // JKC 9/26/00 #2651
      filter = combineFilters(BoolOper.BOOL_OR,filter,addExtKeywordFilters(bool));
    }
    else
    {
      IQAttrRef idField = null;
      if (target == XMen.CLS_CATFEATLINK)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_CATFEATLINK, Arrays.asList(new IXAssoc[] {XMen.ASC_FEATLINK_FEAT}), XMen.ATTR_FEAT_VISIBLEID);
      else if (target == XMen.CLS_CATEGORY)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_CATEGORY, Arrays.asList(new IXAssoc[] {}), XMen.ATTR_CAT_VISIBLEID);
      else if (target == XMen.CLS_QUESTIONINPUT)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_QUESTIONINPUT, Arrays.asList(new IXAssoc[] {}), XMen.ATTR_QMI_VISIBLEID);
      else if (target == XMen.CLS_STANDARDINPUT)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_STANDARDINPUT, Arrays.asList(new IXAssoc[] {}), XMen.ATTR_SMI_VISIBLEID);
      else if (target == XMen.CLS_ARTICLEINPUT)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_ARTICLEINPUT, Arrays.asList(new IXAssoc[] {}), XMen.ATTR_AMI_VISIBLEID);
      else if (target == XMen.CLS_WINLOSSINPUT)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_WINLOSSINPUT, Arrays.asList(new IXAssoc[] {}), XMen.ATTR_WMI_VISIBLEID);
      else if (target == XMen.CLS_SUMMARYINPUT)
        idField = xmr.getAttrRef(getObjectContext(), XMen.CLS_SUMMARYINPUT, Arrays.asList(new IXAssoc[] {}), XMen.ATTR_SUMMI_VISIBLEID);
      
      filter = combineFilters(BoolOper.BOOL_OR,filter,addIDFilters(idField, bool));
    }
      
    totalFilter = combineFilters(BoolOper.BOOL_AND, totalFilter, filter);
    filter = null;
    // special filters for features
    if (target == XMen.CLS_CATFEATLINK) filter = XMen.FILTER_CATFEATLINK_FEAT_REV;
    else if (target == XMen.CLS_STDFEATLINK) filter = XMen.FILTER_STDFEATLINK_FEAT_REV;
    totalFilter = combineFilters(BoolOper.BOOL_AND, totalFilter, filter);
    // add permissions filters
    totalFilter = combineFilters(BoolOper.BOOL_AND, totalFilter, getPermissionFilter(target));
    // add deletion filters
    totalFilter = combineFilters(BoolOper.BOOL_AND, totalFilter, getDeletionFilter(target));
    query.getFilter().setExpr(totalFilter);
  }

  public void genQuery()
    throws OculusException
  {
    _queries = new Vector();
    IXClass target = null;
    
    for (Iterator i = _classIDs.iterator(); i.hasNext(); )
    {
      target = (IXClass)i.next();
      
      if (target == XMen.CLS_DISCUSSION)
      {
        IXClass tempScope = _scopeClass;
        boolean done = false;
        while (!done)
        {
          if (tempScope != XMen.CLS_FOLDER)
          {
            IQuery query = new PMCQuery();
            query.setTargetClass(target);
          
            setScope(query, target, tempScope, _scopeClass);
          
            setFilters(query,target);
            setDisplayAttrs(query,target);
          
            _queries.add(query);
          }
                    
          if (tempScope == XMen.CLS_PRODUCT)
            tempScope = XMen.CLS_VERSION;
          else if (tempScope == XMen.CLS_VERSION)
            tempScope = XMen.CLS_CATEGORY;
          else if (tempScope == XMen.CLS_CATEGORY)
            tempScope = XMen.CLS_CATFEATLINK;
          else if (tempScope == XMen.CLS_FOLDER)
            tempScope = XMen.CLS_FOLDERINPUTLINK;
          else if (tempScope == XMen.CLS_FOLDERINPUTLINK)
            tempScope = XMen.CLS_PROBLEMSTATEMENT;
          else
            done = true;
        }
      }
      else
      {
        IQuery query = new PMCQuery();
        query.setTargetClass(target);

        setScope(query, target, _scopeClass, _scopeClass);
        setFilters(query, target);
        setDisplayAttrs(query, target);

        _queries.add(query);
      }
    }
  }


  private void setDisplayAttrs(IQuery query, IXClass target)
    throws OculusException
  {
    query.getSelect().setUseDistinct(true);
    IXMR xmr = getObjectContext().getRepository().getXMR();
    List selectAttrs = target.getKeywordSelectAttrs();
    for (Iterator j = selectAttrs.iterator(); j.hasNext(); )
    {
      Object attr = j.next();
      if (attr instanceof IXClassAttr)
      {
        IXClass newTarget = target;
        List assoc = Arrays.asList(new IXAssoc[] {});
        
        if (target.equals(XMen.CLS_ARTICLEINPUT) ||
            target.equals(XMen.CLS_QUESTIONINPUT) ||
            target.equals(XMen.CLS_STANDARDINPUT) ||
            target.equals(XMen.CLS_SUMMARYINPUT) ||
            target.equals(XMen.CLS_WINLOSSINPUT))
        {
          newTarget = XMen.CLS_FOLDERINPUTLINK;
          if (target.equals(XMen.CLS_ARTICLEINPUT))
            assoc = Arrays.asList(new IXAssoc[] {XMen.ASC_FLINK_AMI});
          else if (target.equals(XMen.CLS_QUESTIONINPUT))
            assoc = Arrays.asList(new IXAssoc[] {XMen.ASC_FLINK_QMI});
          else if (target.equals(XMen.CLS_STANDARDINPUT))
            assoc = Arrays.asList(new IXAssoc[] {XMen.ASC_FLINK_SMI});
          else if (target.equals(XMen.CLS_SUMMARYINPUT))
            assoc = Arrays.asList(new IXAssoc[] {XMen.ASC_FLINK_SUMMI});
          else if (target.equals(XMen.CLS_WINLOSSINPUT))
            assoc = Arrays.asList(new IXAssoc[] {XMen.ASC_FLINK_WMI});
        }
        IQAttrRef attrRef = xmr.getAttrRef(getObjectContext(), newTarget, assoc, ((IXClassAttr)attr).getIID().getLongValue());
        query.getSelect().addAttr(attrRef);
      }
      else
        query.getSelect().addLiteral(attr.toString(),"CLASSID");
    }    
  }

  private IQFilterExpr getPermissionFilter(IXClass target)
    throws OculusException
  {
    IQFilterExpr permFilter = null;
    return permFilter;
  }

  private IQFilterExpr getDeletionFilter(IXClass target)
    throws OculusException
  {
    IQFilterExpr delFilter = null;
    return delFilter;
  }


  private IQFilterExpr getScopeFilter(IXClass target)
    throws OculusException
  {
    IQFilterExpr scopeFilter = null;
  
    if (target == XMen.CLS_CATFEATLINK && _scopeClass != null)
    {
      if (_scopeClass == XMen.CLS_PRODUCT)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_FEAT_PROD_COMPASS;
        else
          scopeFilter = XMen.FILTER_FEAT_PROD_ACCOLADES;
      }
      else if (_scopeClass == XMen.CLS_VERSION)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_FEAT_VER_COMPASS;
        else
          scopeFilter = XMen.FILTER_FEAT_VER_ACCOLADES;
      }
      else if (_scopeClass == XMen.CLS_CATEGORY)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_FEAT_CAT_COMPASS;
        else
          scopeFilter = XMen.FILTER_FEAT_CAT_ACCOLADES;
      }
    }
    else if (target == XMen.CLS_CATEGORY && _scopeClass != null)
    {
      if (_scopeClass == XMen.CLS_PRODUCT)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_CAT_PROD_COMPASS;
        else
          scopeFilter = XMen.FILTER_CAT_PROD_ACCOLADES;
      }
      else if (_scopeClass == XMen.CLS_VERSION)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_CAT_VER_COMPASS;
        else
          scopeFilter = XMen.FILTER_CAT_VER_ACCOLADES;
      }
      else if (_scopeClass == XMen.CLS_CATEGORY)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_CAT_COMPASS;
        else
          scopeFilter = XMen.FILTER_CAT_ACCOLADES;
      }
    }
    else if (target == XMen.CLS_VERSION && _scopeClass != null)
    {
      if (_scopeClass == XMen.CLS_PRODUCT)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_VER_PROD_COMPASS;
        else
          scopeFilter = XMen.FILTER_VER_PROD_ACCOLADES;
      }
      else if (_scopeClass == XMen.CLS_VERSION)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_VER_COMPASS;
        else
          scopeFilter = XMen.FILTER_VER_ACCOLADES;
      }
    }
    else if (target == XMen.CLS_PRODUCT && _scopeClass != null)
    {
      if (_scopeClass == XMen.CLS_PRODUCT)
      {
        if (getSearchModule().equals(ModuleKind.COMPASS))
          scopeFilter = XMen.FILTER_PROD_COMPASS;
        else
          scopeFilter = XMen.FILTER_PROD_ACCOLADES;
      }
    }
    else if (target == XMen.CLS_STANDARDINPUT)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_SMI_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_SMI_CREATIONDATE,from);
    }
    else if (target == XMen.CLS_ARTICLEINPUT)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_AMI_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_AMI_CREATIONDATE,from);
    }
    else if (target == XMen.CLS_QUESTIONINPUT)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_QMI_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_QMI_CREATIONDATE,from);
    }
    else if (target == XMen.CLS_WINLOSSINPUT)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_WMI_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_WMI_CREATIONDATE,from);
    }
    else if (target == XMen.CLS_SUMMARYINPUT)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_SUMMI_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_SUMMI_CREATIONDATE,from);
    }
    else if (target == XMen.CLS_REACTION)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_REACT_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_REACT_CREATIONDATE,from);
    }
    else if (target == XMen.CLS_PROBLEMSTATEMENT)
    {
      Timestamp to = getToDate();
      Timestamp from = getFromDate();
      if (to != null) scopeFilter = new QFilterExpr(CompOper.COMP_LT,XMen.ATTR_PS_CREATIONDATE,to);
      if (from != null) scopeFilter = new QFilterExpr(CompOper.COMP_GTEQ,XMen.ATTR_PS_CREATIONDATE,from);
    }
    
    return scopeFilter;
  }


	public IKeywordSearch setObjectContext(IObjectContext context)
	{
		_context = context;
		return this;
	}
	
	public IObjectContext getObjectContext()
	{
		return _context;
	}

  public ModuleKind getSearchModule()
    throws OculusException
  {
    return _mk;
  }
      
  public void setSearchModule(ModuleKind mod)
    throws OculusException
  {
    _mk = mod;
  }

  public void useSynonyms(boolean syn)
    throws OculusException
  {
    _syn = syn;
  }
    
  public boolean usesSynonyms()
    throws OculusException
  {
    return _syn;
  }

  public void setFromDate(Timestamp date)
    throws OculusException
  {
    _fromDate = date;
  }
    
  public void setToDate(Timestamp date)
    throws OculusException
  {
    _toDate = date;
  }
  
  public Timestamp getFromDate()
    throws OculusException
  {
    return _fromDate;
  }
    
  public Timestamp getToDate()
    throws OculusException
  {
    return _toDate;
  }

}