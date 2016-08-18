package com.oculussoftware.bus.common.search;

import com.oculussoftware.api.busi.*;
import com.oculussoftware.repos.xml.*;
/**
* $Workfile: SortedSearch.java $
* Create Date:  5-10-2000
* Description: Hold info need to define a compass sorted tree query.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: SortedSearch.java $
 * 
 * *****************  Version 10  *****************
 * User: Eroyal       Date: 8/08/00    Time: 5:14p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * compile
 * 
 * *****************  Version 9  *****************
 * User: Znemazie     Date: 8/08/00    Time: 2:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 7  *****************
 * User: Znemazie     Date: 8/08/00    Time: 10:43a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 6  *****************
 * User: Znemazie     Date: 7/30/00    Time: 3:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/26/00    Time: 5:57p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/bus/common/search
 * Added getTargetClass(), retrieve(), getQuery().
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
*/
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.busi.common.search.*;

import com.oculussoftware.system.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.bus.xmeta.*;
import com.oculussoftware.repos.bmr.view.*;
import com.oculussoftware.repos.query.*;

import java.util.*;
import java.sql.*;

/**
*/
public class SortedSearch extends Search implements ISortedSearch
{
	//-------------------------------------------------------------------
	//-------------------------------------------------------------------

	
  //-------------------------------------------------------------------
  // Private instance methods
  //-------------------------------------------------------------------
  
  public ViewType getViewType() throws OculusException
  { return ViewType.SORTTREEQUERY; }

  //-------------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------------
  public SortedSearch() throws OculusException
  {
    super();
  }

  public IQuery getQuery() throws OculusException
  {
    Iterator itr;
    IXMR xmr = getObjectContext().getRepository().getXMR();
    IQuery q = new PMCQuery();
    //Target Class
    IXClass targetclass = xmr.getClass(((IQueryXML)_xml).getTargetClass());
    q.setTargetClass(targetclass);
    //Select
    /*
    IQSelect qselect = q.getSelect();
    IQSelectKey qsk = _xml.getSelect();
    List attrs = qsk.getAttrs();
    Iterator itr = attrs.iterator();
    while(itr.hasNext())
    {
      IQAttrRefKey qark = (IQAttrRefKey)itr.next();
      IQAttrRef qattrref = convertQAttrRef(xmr,targetclass,qark);
      qselect.addAttr(qattrref);
    }//end while
    */
    //set corresponding selects
    IObjectContext _context = getObjectContext();
    List selectAttrs = targetclass.getKeywordSelectAttrs();
    for (Iterator j = selectAttrs.iterator(); j.hasNext(); )
    {
      Object attr = j.next();
      if (attr instanceof IXClassAttr)
      {
        IQAttrRef attrRef = xmr.getAttrRef(_context, targetclass, Arrays.asList(new IXAssoc[] {}), ((IXClassAttr)attr).getIID().getLongValue());
        q.getSelect().addAttr(attrRef);
      }//end if
      else
        q.getSelect().addLiteral(attr.toString(),"CLASSID");
    }//end for    
      
    //Filters (filters,date)
    IQFilter qfilter = q.getFilter();
    IQFilterKey qfk = ((IQueryXML)_xml).getFilter();
    IQFilterExprKey qfek = qfk.getExpr();
    IQFilterExpr qfilterexpr = buildFilterExpr(qfek,null,xmr,targetclass);
    
    //Dates
    qfilterexpr = getDateExpr(qfilterexpr);
        
    //add the filter
    qfilter.setExpr(qfilterexpr);

    //Sort (and selec the sorts)
    IQSort qsort = q.getSort();
    IQSortKey qsortk = ((IQueryXML)_xml).getSort();
    List sorts = qsortk.getSortItems();
    itr = sorts.iterator();
    while(itr.hasNext())
    {
      IQSortItemKey qsik = (IQSortItemKey)itr.next();
      IQAttrRefKey qark = qsik.getAttrRef();
      SortDir sd = qsik.getDir();
      IQAttrRef qattrref = convertQAttrRef(xmr,targetclass,qark);
      qsort.addSortItem(qattrref,sd);
      //SELECT
      q.getSelect().addAttr(qattrref);
    }//end while 
    
    //Scope
    if(_scopeclassiid != null && _scopeobjectiid != null)
    {
      IXClass scopeclass = xmr.getClass(_scopeclassiid.getLongValue());
      q.setScope(getObjectContext(),targetclass,scopeclass,_scopeobjectiid.getLongValue());
    }
    
    return q;
  }

//    public IDataSet retrieve() throws OculusException{ }

  public ISortTree getSortTree(IObjectContext context)
    throws OculusException
  {
    ISortTree retObj = new SortTree(context, retrieve(), this);
    //set backreference
    retObj.setSortedSearch(this);
    return retObj;
  }

  public Object dolly() throws OculusException
  {
    ISortedSearch obj = new SortedSearch();
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
    obj.setXML(getXML());
    obj.setModuleKind(getModuleKind());
    return obj;
  }
}
