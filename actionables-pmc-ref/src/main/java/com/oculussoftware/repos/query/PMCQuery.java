package com.oculussoftware.repos.query;

/**
* $Workfile: PMCQuery.java $
* Create Date: 6/4/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: PMCQuery.java $
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 9/22/00    Time: 4:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Issue 2653 and 2656 - handle scope for std coll as target and scope.
 * 
 * *****************  Version 11  *****************
 * User: Sshafi       Date: 9/15/00    Time: 2:57p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Bug Fix: 1925
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Bug Fix: #2537
 * 
 * *****************  Version 9  *****************
 * User: Jcoles       Date: 8/30/00    Time: 9:18a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 1925 and 2150: Fixing search scope problems.  Added category recursive
 * assoc to scope chain.
 * 
 * *****************  Version 8  *****************
 * User: Sshafi       Date: 7/27/00    Time: 11:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 7  *****************
 * User: Sshafi       Date: 7/27/00    Time: 9:14a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 6  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 5  *****************
 * User: Sshafi       Date: 7/25/00    Time: 5:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 4  *****************
 * User: Sshafi       Date: 7/24/00    Time: 5:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:37p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Initialized main clause objects.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:21p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Changed to use IXClass instead of CLassMeta.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/30/00    Time: 10:53a
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/repos/query
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 6/08/00    Time: 8:35p
 * Created in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/bus/search
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.bus.xmeta.*;

import java.util.*;
import java.io.*;


/**
* Convience class for piecing together SQL statements.
* All specialized query and report class must know how to build.
* an IQuery.
*
* NOTE Renamed from SQLQuery.java.
*/
public class PMCQuery implements IQuery
{
  //----------------------------------------------------------------------
  // Private class vars
  //----------------------------------------------------------------------
  //----------------------------------------------------------------------
  // Private instance vars
  //----------------------------------------------------------------------
  private QSelect   _selNode     = new QSelect();
  private IXClass _targetClass = null;
  private QFilter   _filterNode  = new QFilter();
  private QSort     _sortNode    = new QSort();
  
  //----------------------------------------------------------------------
  // Public constructor
  //----------------------------------------------------------------------
  public PMCQuery ()
    throws OculusException
  {
  }
    
  //----------------------------------------------------------------------
  // Public methods
  //----------------------------------------------------------------------
  
	public void setTargetClass(IXClass cls)
  { 
    _targetClass = cls;
    IQFilterExpr defFilter = _targetClass.getClassDefnFilter();
    if (defFilter != null)
    {
      IQFilter filter = getFilter();
      IQFilterExpr filterExpr = filter.getExpr();
      if (filterExpr == null)
        filter.setExpr(defFilter);
      else
      {
        filterExpr = new QFilterExpr(BoolOper.BOOL_AND, filterExpr, defFilter);
        filter.setExpr(filterExpr);
      }
    }
  }
	public IXClass getTargetClass()
	{
		return _targetClass;
	}
  
	public IQSelect getSelect()
  { 
    return _selNode;
  }

	public IQFilter getFilter()
  {
    return _filterNode;
  }
  
	public IQSort getSort()
  {
    return _sortNode;
  }

  private IQFilterExpr combineFilters(BoolOper bool, IQFilterExpr one, IQFilterExpr two)
    throws OculusException
  {
    if (one == null) return two;
    if (two == null) return one;
    return new QFilterExpr(bool,one,two);
  }

  /**
  * NOTE: Ideally this method would not have to reference XMen directly, i.e., would
  * not have any knowledge of specific instances of xmeta classes.  This method
  * is essentially building a scope filter/constraint based on where the scope and
  * target classes fall relativley in the maximal 'scope chain' of the target class.  
  * It is possible for
  * the XMR to infer the maximal scope chain(s) for any given class based on the declared
  * associations.
  */
  public IQuery setScope(IObjectContext context, IXClass target, IXClass scopeType, long scopeID)
    throws OculusException
  {
    return setScope(context, target, null, scopeType, scopeID);
  }


  public IQuery setScope(IObjectContext context, IXClass target, IXClass connectToType, IXClass scopeType, long scopeID)
    throws OculusException
  {
    return setScope(context, target, connectToType, scopeType, new long[] {scopeID});
  }

  public IQuery setScope(IObjectContext context, IXClass target, IXClass connectToType, IXClass scopeType, long[] scopeIDs)
    throws OculusException
  {
    long scopeCol = scopeType.getPrimaryKeyAttr().getIID().getLongValue();
    IQAttrRef scopeAttr = null;
    int nextIndx = 0;
    IQFilterExpr scope = null;
    List assocs = new ArrayList();
    IXMR xmr = context.getRepository().getXMR();

    if (connectToType == null) connectToType = scopeType;    
    
    if (connectToType == XMen.CLS_PRODUCT)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        assocs.add(0,XMen.ASC_DISC_PROD);
      }
      else
      if (target == XMen.CLS_VERSION || target == XMen.CLS_CATEGORY || target == XMen.CLS_CATFEATLINK || target == XMen.CLS_TRANSACTIONCOMMENT)
      {
        assocs.add(0,XMen.ASC_VER_PROD);
        if (target == XMen.CLS_CATEGORY || target == XMen.CLS_CATFEATLINK || target == XMen.CLS_TRANSACTIONCOMMENT)
        {
          assocs.add(0,XMen.ASC_CAT_VERS);
          assocs.add(0,XMen.ASC_CAT_REC);
          if (target == XMen.CLS_CATFEATLINK || target == XMen.CLS_TRANSACTIONCOMMENT)
          {
            assocs.add(0,XMen.ASC_FEATLINK_CAT);
            if (target == XMen.CLS_TRANSACTIONCOMMENT)
              assocs.add(0,XMen.ASC_TRANS_FLINK);
          }
        }
      }
    }
    else
    if (connectToType == XMen.CLS_VERSION)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        if (scopeType == XMen.CLS_PRODUCT)
          assocs.add(0,XMen.ASC_VER_PROD);
        assocs.add(0,XMen.ASC_DISC_VERS);
      }
      else
      if (target == XMen.CLS_CATEGORY || target == XMen.CLS_CATFEATLINK || target == XMen.CLS_TRANSACTIONCOMMENT)
      {
        assocs.add(0,XMen.ASC_CAT_VERS);
        assocs.add(0,XMen.ASC_CAT_REC);
        if (target == XMen.CLS_CATFEATLINK || target == XMen.CLS_TRANSACTIONCOMMENT)
        {
            assocs.add(0,XMen.ASC_FEATLINK_CAT);
            if (target == XMen.CLS_TRANSACTIONCOMMENT)
              assocs.add(0,XMen.ASC_TRANS_FLINK);
        }
      }
    }
    else
    if (connectToType == XMen.CLS_CATEGORY)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        if (scopeType == XMen.CLS_PRODUCT)
          assocs.add(0,XMen.ASC_VER_PROD);
        if (scopeType == XMen.CLS_PRODUCT || scopeType == XMen.CLS_VERSION)
        {
          assocs.add(0, XMen.ASC_CAT_VERS);
          assocs.add(0,XMen.ASC_CAT_REC);
        }
        assocs.add(0,XMen.ASC_DISC_CAT);
      }
      else
      if (target == XMen.CLS_CATFEATLINK || target == XMen.CLS_TRANSACTIONCOMMENT)
      {
            assocs.add(0,XMen.ASC_FEATLINK_CAT);
            if (target == XMen.CLS_TRANSACTIONCOMMENT)
              assocs.add(0,XMen.ASC_TRANS_FLINK);
      }
    }
    if (connectToType == XMen.CLS_CATFEATLINK)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        if (scopeType == XMen.CLS_PRODUCT)
          assocs.add(0,XMen.ASC_VER_PROD);
        if (scopeType == XMen.CLS_PRODUCT || scopeType == XMen.CLS_VERSION)
          assocs.add(0, XMen.ASC_CAT_VERS);
        if (scopeType == XMen.CLS_PRODUCT || scopeType == XMen.CLS_VERSION || scopeType == XMen.CLS_CATEGORY)
          assocs.add(0, XMen.ASC_FEATLINK_CAT);
        assocs.add(0,XMen.ASC_DISC_FLINK);
      }
      if (target == XMen.CLS_TRANSACTIONCOMMENT)
        assocs.add(0,XMen.ASC_TRANS_FLINK);
    }
    else
    
    if (connectToType == XMen.CLS_STDCOLL)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        assocs.add(0,XMen.ASC_STDCOLL_REC);
        assocs.add(0,XMen.ASC_STDFEATLINK_STDCOLL);
        assocs.add(0,XMen.ASC_DISC_SFLINK);
      }
      else
      if (target == XMen.CLS_STDFEATLINK)
      {
        assocs.add(0,XMen.ASC_STDCOLL_REC);
        assocs.add(0,XMen.ASC_STDFEATLINK_STDCOLL);
      }
      else
      if (target == XMen.CLS_STDCOLL)
      {
        assocs.add(0,XMen.ASC_STDCOLL_REC);
      }
    }
    else
    if (connectToType == XMen.CLS_FOLDER)
    {
      if (target == XMen.CLS_STANDARDINPUT)
      {
            assocs.add(0,XMen.ASC_FOLDER_REC);
            assocs.add(0,XMen.ASC_FLINK_FOLDER);
            assocs.add(0,XMen.ASC_FLINK_SMI);
      }
      if (target == XMen.CLS_ARTICLEINPUT)
      {
            assocs.add(0,XMen.ASC_FOLDER_REC);
            assocs.add(0,XMen.ASC_FLINK_FOLDER);
            assocs.add(0,XMen.ASC_FLINK_AMI);
      }
      if (target == XMen.CLS_QUESTIONINPUT)
      {
            assocs.add(0,XMen.ASC_FOLDER_REC);
            assocs.add(0,XMen.ASC_FLINK_FOLDER);
            assocs.add(0,XMen.ASC_FLINK_QMI);
      }
      if (target == XMen.CLS_REACTION)
      {
            assocs.add(0,XMen.ASC_FOLDER_REC);
            assocs.add(0,XMen.ASC_FLINK_FOLDER);
            assocs.add(0,XMen.ASC_REACT_FLINK);
      }
      if (target == XMen.CLS_PROBLEMSTATEMENT)
      {
            assocs.add(0,XMen.ASC_FOLDER_REC);
            assocs.add(0,XMen.ASC_PROBSTM_FOLDER);
      }
    }
    else
    if (connectToType == XMen.CLS_FOLDERINPUTLINK)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        assocs.add(0,XMen.ASC_FOLDER_REC);
        assocs.add(0,XMen.ASC_FLINK_FOLDER);
        assocs.add(0,XMen.ASC_DISC_FOLDLINK);
      }
    }
    else
    if (connectToType == XMen.CLS_PROBLEMSTATEMENT)
    {
      if (target == XMen.CLS_DISCUSSION)
      {
        assocs.add(0,XMen.ASC_FOLDER_REC);
        assocs.add(0,XMen.ASC_PROBSTM_FOLDER);
        assocs.add(0,XMen.ASC_DISC_PS);
      }
    }

    if (assocs.size() > 0)
    {
      if (scopeAttr == null)
        scopeAttr = xmr.getAttrRef(context, target, assocs, scopeCol);
      if (scope == null)
        scope = new QFilterExpr(CompOper.COMP_ELEMENTOF, scopeAttr, scopeIDs);
      
      // JKC 8/29/2000 Limit parent cat id to the 'root' cat of the version when the REC assoc is used
      // and scope type is not CATEGORY itself.
      int catrecidx = assocs.indexOf(XMen.ASC_CAT_REC);
      if (catrecidx > -1 && scopeType != XMen.CLS_CATEGORY) {
        // NOTE: second arg is high index (exclusive)...therefore the '+ 1'...
        List catscopechain = assocs.subList(0, catrecidx+1);
        IQAttrRef catoid = xmr.getAttrRef(context, target, catscopechain, XMen.ATTR_CAT_OID.getIID().getLongValue());
        IQAttrRef catparid = xmr.getAttrRef(context, target, catscopechain, XMen.ATTR_CAT_PARENTID.getIID().getLongValue());
        scope = new QFilterExpr(BoolOper.BOOL_AND, scope, new QFilterExpr(CompOper.COMP_EQ, catoid, catparid));
      }
      
//      int foldrecidx = assocs.indexOf(XMen.ASC_FOLDER_REC);
//      if (foldrecidx > -1 && scopeType != XMen.CLS_CATEGORY) {
//        // NOTE: second arg is high index (exclusive)...therefore the '+ 1'...
//        List catscopechain = assocs.subList(0, catrecidx+1);
//        IQAttrRef catoid = xmr.getAttrRef(context, target, catscopechain, XMen.ATTR_CAT_OID.getIID().getLongValue());
//        IQAttrRef catparid = xmr.getAttrRef(context, target, catscopechain, XMen.ATTR_CAT_PARENTID.getIID().getLongValue());
//        scope = new QFilterExpr(BoolOper.BOOL_AND, scope, new QFilterExpr(CompOper.COMP_EQ, catoid, catparid));
//      }
      
      IQFilter filter = getFilter();
      IQFilterExpr filterExpr = filter.getExpr();
      if (filterExpr == null)
        filter.setExpr(scope);
      else
      {
        filterExpr = new QFilterExpr(BoolOper.BOOL_AND, filterExpr, scope);
        filter.setExpr(filterExpr);
      }
    }
    
    return this;
  }

}