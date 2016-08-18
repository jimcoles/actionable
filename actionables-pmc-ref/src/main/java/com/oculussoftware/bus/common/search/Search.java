package com.oculussoftware.bus.common.search;

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

/**
* Filename:    Search.java
* Date:        8/7/00
* Description: .
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
abstract public class Search extends BMDataView implements ISearch, java.io.Serializable
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  */
  protected IIID    _scopeclassiid;
  protected IIID    _scopeobjectiid;
 
  protected List         _qfltrattrs = new Vector();
  protected List         _compopers  = new Vector();
  protected List         _operands   = new Vector();
  protected List         _boolopers  = new Vector();
  protected List         _syns       = new Vector();
  protected List         _quests     = new Vector();
  protected IQFilterExpr _qfe;

  /** 
  * Public Constructor 
  */
  public Search() throws OculusException
  { 
    super();
    _xml = new QueryXML();
  }

  public IQuery getQuery() throws OculusException
  {
    IXMR xmr = getObjectContext().getRepository().getXMR();
    IQuery q = new PMCQuery();
    //Target Class
    IXClass targetclass = xmr.getClass(((IQueryXML)_xml).getTargetClass());
    q.setTargetClass(targetclass);
    //Select
    IQSelect qselect = q.getSelect();
    IQSelectKey qsk = ((IQueryXML)_xml).getSelect();
    List attrs = qsk.getAttrs();
    Iterator itr = attrs.iterator();
    while(itr.hasNext())
    {
      IQSelectItemKey qsik = (IQSelectItemKey)itr.next();
      if(qsik.getAttr() instanceof IQAttrRefKey)
      {
        IQAttrRefKey qark = (IQAttrRefKey)qsik.getAttr();
        IQAttrRef qattrref = convertQAttrRef(xmr,targetclass,qark);
        if(qsik.getAlias() != null && !qsik.getAlias().equals(""))
          qselect.addAttr(qattrref,qsik.getAlias());
        else
          qselect.addAttr(qattrref);  
      }//end if
      else
        qselect.addLiteral((String)((IQObjectKey)qsik.getAttr()).getValues(),qsik.getAlias());
    }//end while
     
    //Filters (filters,date)
    IQFilter qfilter = q.getFilter();
    IQFilterKey qfk = ((IQueryXML)_xml).getFilter();
    IQFilterExprKey qfek = qfk.getExpr();
    if(_qfe == null)
      _qfe = buildFilterExpr(qfek,null,xmr,targetclass);
    //Dates
    IQFilterExpr qfilterexpr = getDateExpr(_qfe);
    
    //States
    qfilterexpr = getStateExpr(qfilterexpr);
        
    //add the filter
    qfilter.setExpr(qfilterexpr);

    //Sort
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
    }//end while 
    
    //Scope
    if(_scopeclassiid != null && _scopeobjectiid != null)
    {
      IXClass scopeclass = xmr.getClass(_scopeclassiid.getLongValue());
      q.setScope(getObjectContext(),targetclass,scopeclass,_scopeobjectiid.getLongValue());
    }
    
    return q;
  }
  
  public IQAttrRef[] getFilterAttrs() throws OculusException
  {    
    if(_qfe == null) buildFilterExpr();
    Iterator itr = _qfltrattrs.iterator();
    IQAttrRef[] qattrrefs = new IQAttrRef[_qfltrattrs.size()];
    for(int i = 0; i < qattrrefs.length; i++)
      qattrrefs[i] = (IQAttrRef)itr.next();
    return qattrrefs;
  }
    
  public CompOper[] getCompOpers() throws OculusException
  {    
    if(_qfe == null) buildFilterExpr();
    Iterator itr = _compopers.iterator();
    CompOper[] compops = new CompOper[_compopers.size()];
    for(int i = 0; i < compops.length; i++)
      compops[i] = (CompOper)itr.next();
    return compops;
  }
  
  public String[] getOperands() throws OculusException
  {   
    if(_qfe == null) buildFilterExpr(); 
    Iterator itr = _operands.iterator();
    String[] operands = new String[_operands.size()];
    for(int i = 0; i < operands.length; i++)
      operands[i] = (String)itr.next();
    return operands;
  }
  
  public BoolOper[] getBoolOpers() throws OculusException
  {    
    if(_qfe == null) buildFilterExpr();
    Iterator itr = _boolopers.iterator();
    BoolOper[] boolops = new BoolOper[_boolopers.size()];
    for(int i = 0; i < boolops.length; i++)
      boolops[i] = (BoolOper)itr.next();
    return boolops;
  }
  
  public boolean[] getSyns() throws OculusException
  {
    if(_qfe == null) buildFilterExpr();
    Iterator itr = _syns.iterator();
    boolean[] syns = new boolean[_syns.size()];
    for(int i = 0; i < syns.length; i++)
      syns[i] = ((Boolean)itr.next()).booleanValue();
    return syns;
  }
  
  public boolean[] getQuests() throws OculusException
  {
    if(_qfe == null) buildFilterExpr();
    Iterator itr = _quests.iterator();
    boolean[] quests = new boolean[_quests.size()];
    for(int i = 0; i < quests.length; i++)
      quests[i] = ((Boolean)itr.next()).booleanValue();
    return quests;
  }
  
  protected void buildFilterExpr() throws OculusException
  {
    IXMR xmr = getObjectContext().getRepository().getXMR();
    IXClass targetclass = xmr.getClass(((IQueryXML)_xml).getTargetClass());
    IQFilterKey qfk = ((IQueryXML)_xml).getFilter();
    IQFilterExprKey qfek = qfk.getExpr();
    if(_qfe == null)
      _qfe = buildFilterExpr(qfek,null,xmr,targetclass);
  }
  
  protected IQFilterExpr getStateExpr(IQFilterExpr qfilterexpr) throws OculusException
  {
    IIID[] states = getStates();
    if(states.length == 0)
      return qfilterexpr;   
    IXClass xcls = getTargetClass();
    if(xcls == null)
      throw new OculusException("The TargetClass must be set first."); 
    if(!SearchUtil.isMarketInput(xcls.getIID().getLongValue()))
      throw new OculusException("The TargetClass must be a MarketInput. id=("+xcls.getIID()+")");
    long classid = xcls.getIID().getLongValue();
    IXClassAttr xclsattr = null;
    if(classid == IDCONST.STANDARDINPUT.getLongValue())
      xclsattr = XMen.ATTR_SMI_STATEID;  
    else if(classid == IDCONST.ARTICLEINPUT.getLongValue())
      xclsattr = XMen.ATTR_AMI_STATEID;
    else if(classid == IDCONST.WINLOSSINPUT.getLongValue())
      xclsattr = XMen.ATTR_WMI_STATEID;
    else if(classid == IDCONST.SUMMARYINPUT.getLongValue())
      xclsattr = XMen.ATTR_SUMMI_STATEID;
    else if(classid == IDCONST.IMPORTEDINPUT.getLongValue())
      xclsattr = null;//XMen.ATTR_IMI_STATEID;
    else if(classid == IDCONST.QUESTIONINPUT.getLongValue())
      xclsattr = XMen.ATTR_QMI_STATEID;
    if(xclsattr != null)
    {
      for(int i = 0; i < states.length; i++)
      {  
        IQAttrRef qattrref = getObjectContext().getRepository().getXMR().getAttrRef(getObjectContext(), xcls, null, xclsattr.getIID().getLongValue());
        qfilterexpr = new QFilterExpr(BoolOper.BOOL_AND,qfilterexpr,new QFilterExpr(CompOper.COMP_EQ,qattrref,new Long(states[i].getLongValue())));
      }//end for
    }//end if
    return qfilterexpr;
  }
  
  /**
  * take an expr and add the date filters to it if they are set
  */
  protected IQFilterExpr getDateExpr(IQFilterExpr qfilterexpr) throws OculusException
  {
    IXMR xmr = getObjectContext().getRepository().getXMR();
    IXClass targetclass = xmr.getClass(((IQueryXML)_xml).getTargetClass());
    Timestamp fromdate = ((IQueryXML)_xml).getFromDate();
    Timestamp todate = ((IQueryXML)_xml).getToDate();
    IXClassAttr xclsattr = getDateClassAttr(targetclass);
    long attrid = -1;
    List chain = null;
    if(fromdate != null && xclsattr != null)
    {
      IQAttrRef attrref = xmr.getAttrRef(getObjectContext(), targetclass, chain, xclsattr.getIID().getLongValue());
      qfilterexpr = new QFilterExpr(BoolOper.BOOL_AND,qfilterexpr,new QFilterExpr(CompOper.COMP_ONORAFTER, attrref, fromdate));
    }//end if
    if(todate != null && xclsattr != null)
    {
      IQAttrRef attrref = xmr.getAttrRef(getObjectContext(), targetclass, chain, xclsattr.getIID().getLongValue());
      qfilterexpr = new QFilterExpr(BoolOper.BOOL_AND,qfilterexpr,new QFilterExpr(CompOper.COMP_ONORBEFORE, attrref, todate));
    }//end if
    return qfilterexpr;
  }
  
  protected IXClassAttr getDateClassAttr(IXClass xcls) throws OculusException
  {
    if(xcls == null) return null;
    else if(xcls.getIID().equals(IDCONST.STANDARDINPUT.getIIDValue())) return XMen.ATTR_SMI_CREATIONDATE;
    else if(xcls.getIID().equals(IDCONST.ARTICLEINPUT.getIIDValue())) return XMen.ATTR_AMI_CREATIONDATE;
    else if(xcls.getIID().equals(IDCONST.QUESTIONINPUT.getIIDValue())) return XMen.ATTR_QMI_CREATIONDATE;
    else if(xcls.getIID().equals(IDCONST.WINLOSSINPUT.getIIDValue())) return XMen.ATTR_WMI_CREATIONDATE;
    else if(xcls.getIID().equals(IDCONST.SUMMARYINPUT.getIIDValue())) return XMen.ATTR_SUMMI_CREATIONDATE;
    return null;    
  }
    
  protected IQAttrRef convertQAttrRef(IXMR xmr, IXClass targetclass, IQAttrRefKey qark) throws OculusException
  {
    List assocs = new Vector();
    long[] assockeys = qark.getAssocs();
    for(int j = 0; assockeys != null && j < assockeys.length; j++)
      assocs.add(xmr.getAssoc(context,assockeys[j]));
    return xmr.getAttrRef(context, targetclass, assocs, qark.getAttr());
  }
  
  protected IQFilterExpr buildFilterExpr(IQFilterExprKey qfek, IQFilterExpr qfe, IXMR xmr, IXClass targetclass) throws OculusException
  {
    if(qfek == null) return null;
    Object leftk  = qfek.getLeft();
    Object rightk = qfek.getRight();
    IOperator op = qfek.getOper();
    boolean syn = qfek.useSynonyms();
    Object left = null; Object right = null;  
    //the left
    if(leftk instanceof IQAttrRefKey)
    {
      left = convertQAttrRef(xmr,targetclass,(IQAttrRefKey)leftk);
      _qfltrattrs.add(left);
    }//end if
    else if(leftk instanceof IQFilterExprKey)
    {
      left = buildFilterExpr((IQFilterExprKey)leftk,qfe,xmr,targetclass);
    }//end else if
    else if(leftk instanceof IQObjectKey)
    {
      _syns.add(new Boolean(qfek.useSynonyms()));//shouldn't happen
      _quests.add(new Boolean(qfek.isQuestion()));
      IQObjectKey key = (IQObjectKey)leftk;
      Primitive p = key.getPrimitive();
      if(Primitive.INTEGER.equals(p))
      {
        left = (Long)key.getValues();
        _operands.add(""+left);
      }//end if
      else if(Primitive.CHAR.equals(p))
      {
        left = (String)key.getValues();
        _operands.add(""+left);
        
      }//end else if
      else if(Primitive.ENUM.equals(p))
      {
        left = (long[])key.getValues();
        _operands.add(StringUtil.buildCommaDelList((long[])left));
      }//end else if
      else if(Primitive.TIME.equals(p))
      {
        left = (Timestamp)key.getValues();
        _operands.add(""+com.oculussoftware.ui.DateFormatter.format((Timestamp)left));  
      }//end else if
    }//end else if
    //the right
    if(rightk instanceof IQAttrRefKey)
    {
      right = convertQAttrRef(xmr,targetclass,(IQAttrRefKey)rightk);
      _qfltrattrs.add(right);
    }//end if
    else if(rightk instanceof IQFilterExprKey)
    {
      right = buildFilterExpr((IQFilterExprKey)rightk,qfe,xmr,targetclass);
    }//end else if
    else if(rightk instanceof IQObjectKey)
    {
      _syns.add(new Boolean(qfek.useSynonyms()));
      _quests.add(new Boolean(qfek.isQuestion()));
      IQObjectKey key = (IQObjectKey)rightk;
      Primitive p = key.getPrimitive();
      if(Primitive.INTEGER.equals(p))
      {
        right = (Long)key.getValues();
        _operands.add(""+right);
      }//end if
      else if(Primitive.CHAR.equals(p))
      {
        right = (String)key.getValues();
        _operands.add(""+right);
        
      }//end else if
      else if(Primitive.ENUM.equals(p))
      {
        right = (long[])key.getValues();
        _operands.add(StringUtil.buildCommaDelList((long[])right));
      }//end else if
      else if(Primitive.TIME.equals(p))
      {
        right = (Timestamp)key.getValues();
        _operands.add(""+com.oculussoftware.ui.DateFormatter.format((Timestamp)right));  
      }//end else if
    }//end else if
    if(op instanceof BoolOper)
      _boolopers.add(op);
    else if(op instanceof CompOper)
      _compopers.add(op);  

    qfe = new QFilterExpr(op,left,right,syn);
    return qfe;
  }
  
  //Scope
  public ISearch setScope(long classid, long objectid) throws OculusException
  { 
    IRepository repos = getObjectContext().getRepository();
    IIID classiid = repos.makeReposID(classid);
    IIID objectiid = repos.makeReposID(objectid);
    return setScope(classiid, objectiid);
  }
  
  public ISearch setScope(IIID classiid, IIID objectiid) throws OculusException
  { _scopeclassiid = classiid; _scopeobjectiid = objectiid; return this; }
  
  public ISearch setScope(IBusinessObject bo) throws OculusException
  { return setScope(bo.getDefnObject().getIID(), bo.getIID()); }
  
  //Target Class
  public ISearch setTargetClass(IIID classiid) throws OculusException
  { return setTargetClass(classiid.getLongValue()); }
  
  public ISearch setTargetClass(long classid) throws OculusException
  { ((IQueryXML)_xml).setTargetClass(classid); return this; }
  
  public ISearch setTargetClass(IXClass cls) throws OculusException
  { return setTargetClass(cls.getIID()); }

  public IXClass getTargetClass() throws OculusException
  { return getObjectContext().getRepository().getXMR().getClass(((IQueryXML)_xml).getTargetClass()); }
  
  public IIID getTargetClassIID() throws OculusException
  { return getTargetClass().getIID(); }
  
  public IRClass getTargetClassObject() throws OculusException
  { return (IRClass)getObjectContext().getCRM().getCompObject(getObjectContext(),"Class",getTargetClassIID()); }
  
  //Dates
  public ISearch setFromDate(Timestamp date)
  { ((IQueryXML)_xml).setFromDate(date); return this; }
  
  public Timestamp getFromDate()
  { return ((IQueryXML)_xml).getFromDate(); }
  
  public ISearch setToDate(Timestamp date)
  { ((IQueryXML)_xml).setToDate(date); return this; }
  
  public Timestamp getToDate()
  { return ((IQueryXML)_xml).getToDate(); }
  
  //Display Attr
  public ISearch addDisplayAttr(long[] assocs, long attr)
  {
    ((IQueryXML)_xml).getSelect().addAttr(new QAttrRefKey(assocs,attr));
    return this;
  }
  
  public ISearch addDisplayAttr(long[] assocs, long attr, String alias)
  {
    ((IQueryXML)_xml).getSelect().addAttr(new QAttrRefKey(assocs,attr), alias);
    return this;
  }
  
  public ISearch addDisplayLiteral(String value, String alias)
  {
    ((IQueryXML)_xml).getSelect().addLiteral(new QObjectKey(Primitive.CHAR,value),alias);
    return this;
  }
  
  public int getNumDisplayAttrs()
  {
    return ((IQueryXML)_xml).getSelect().getDisplayAttrs().size();
  }
  
  public IQAttrRef[] getDisplayAttrs() throws OculusException
  {
    IXMR xmr = getObjectContext().getRepository().getXMR();
    IXClass targetclass = xmr.getClass(((IQueryXML)_xml).getTargetClass());
    IQSelectKey qsk = ((IQueryXML)_xml).getSelect();
    List attrs = qsk.getDisplayAttrs();
    IQAttrRef[] rtnVal = new IQAttrRef[attrs.size()];
    Iterator itr = attrs.iterator();
    for(int i = 0; itr.hasNext(); i++)
    {
      IQAttrRefKey qark = (IQAttrRefKey)itr.next();
      IQAttrRef qattrref = convertQAttrRef(xmr,targetclass,qark);
      rtnVal[i] = qattrref;
    }//end while
    return rtnVal;
  }
  
  //Sort 
  public ISearch addSort(long[] assocs, long attr, SortDir dir)
  {
    ((IQueryXML)_xml).getSort().addSortItem(new QAttrRefKey(assocs,attr),dir);
    return this;
  }
  
  public IQSortItemKey[] getSortItemKeys() throws OculusException
  {
    List sortitems = ((IQueryXML)_xml).getSort().getSortItems();
    IQSortItemKey[] items = new IQSortItemKey[sortitems.size()];
    Iterator itr = sortitems.iterator();
    for(int i = 0; itr.hasNext(); i++)
      items[i] = (IQSortItemKey)itr.next();
    return items;
  }
  
  //Filter
  public ISearch addFilter(long[] assocs, long attr, IOperator op, String value)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.CHAR,value),false,false); }

  public ISearch addFilter(long[] assocs, long attr, IOperator op, String value, boolean syn)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.CHAR,value),syn,false); }
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long value)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.INTEGER,new Long(value)),false,false); }
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long[] value)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.ENUM,value),false,false); }
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, Timestamp value)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.TIME,value),false,false); }
  
  
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, String value, boolean syn, boolean quest)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.CHAR,value),syn,quest); }
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long value, boolean quest)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.INTEGER,new Long(value)),false,quest); }
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, long[] value, boolean quest)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.ENUM,value),false,quest); }
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, Timestamp value, boolean quest)
  { return addFilter(assocs,attr,op,new QObjectKey(Primitive.TIME,value),false,quest); }
  
  
  
  public ISearch addFilter(long[] assocs, long attr, IOperator op, IQObjectKey value, boolean syn, boolean quest)
  {
    IQFilterKey qfk = ((IQueryXML)_xml).getFilter();
    IQFilterExprKey qfek = qfk.getExpr();
    if(qfek == null)
      qfk.setExpr(new QFilterExprKey(op, new QAttrRefKey(assocs,attr), value, syn, quest));
    else
      return addFilter(BoolOper.BOOL_AND,assocs,attr,op,value,syn,quest);
    return this;
  }
    
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, String value)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.CHAR,value),false,false); }
 
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, String value, boolean syn)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.CHAR,value),syn,false); }
 
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long value)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.INTEGER,new Long(value)),false,false); }
  
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long[] value)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.ENUM,value),false,false); }
  
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, Timestamp value)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.TIME,value),false,false); }


 
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, String value, boolean syn, boolean quest)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.CHAR,value),syn,quest); }
 
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long value, boolean quest)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.INTEGER,new Long(value)),false,quest); }
  
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, long[] value, boolean quest)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.ENUM,value),false,quest); }
  
  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, Timestamp value, boolean quest)
  { return addFilter(bo,assocs,attr,op,new QObjectKey(Primitive.TIME,value),false,quest); }



  public ISearch addFilter(BoolOper bo, long[] assocs, long attr, IOperator op, IQObjectKey value, boolean syn, boolean quest)
  { 
    IQFilterKey qfk = ((IQueryXML)_xml).getFilter();
    IQFilterExprKey old_qfek = qfk.getExpr();
    IQFilterExprKey new_qfek = new QFilterExprKey(op, new QAttrRefKey(assocs,attr), value, syn, quest);
    qfk.setExpr(new QFilterExprKey(bo,old_qfek,new_qfek,syn,quest));
    return this;
  }
  
  public ISearch addMarketInputState(boolean open) throws OculusException
  {
    IXClass xcls = getTargetClass();
    if(xcls == null)
      throw new OculusException("The TargetClass must be set first."); 
    if(!SearchUtil.isMarketInput(xcls.getIID().getLongValue()))
      throw new OculusException("The TargetClass must be a MarketInput. id=("+xcls.getIID()+")");
    long classid = xcls.getIID().getLongValue();//IDCONST idccls = IDCONST.getInstance(xcls.getIID());
    if(classid == IDCONST.STANDARDINPUT.getLongValue())
      return addState((open?IDCONST.STANDARDINPUTOPEN:IDCONST.STANDARDINPUTCLOSED));
    else if(classid == IDCONST.ARTICLEINPUT.getLongValue())
      return addState((open?IDCONST.ARTICLEINPUTOPEN:IDCONST.ARTICLEINPUTCLOSED));
    else if(classid == IDCONST.WINLOSSINPUT.getLongValue())
      return addState((open?IDCONST.WINLOSSINPUTOPEN:IDCONST.WINLOSSINPUTCLOSED));
    else if(classid == IDCONST.SUMMARYINPUT.getLongValue())
      return addState((open?IDCONST.SUMMARYINPUTOPEN:IDCONST.SUMMARYINPUTCLOSED));
    else if(classid == IDCONST.IMPORTEDINPUT.getLongValue())
      return addState((open?IDCONST.IMPORTEDINPUTOPEN:IDCONST.IMPORTEDINPUTCLOSED));
    else if(classid == IDCONST.QUESTIONINPUT.getLongValue())
      return addState((open?IDCONST.QUESTIONINPUTOPEN:IDCONST.QUESTIONINPUTCLOSED));
    return this;
  }
  
  public ISearch addState(IDCONST idc) throws OculusException
  {
    ((IQueryXML)_xml).setState(idc.getLongValue());
    return this;
  }
    
  public boolean isMarketInputOpen() throws OculusException
  {
    IXClass xcls = getTargetClass();
    if(xcls == null)
      throw new OculusException("The TargetClass must be set first."); 
    if(!SearchUtil.isMarketInput(xcls.getIID().getLongValue()))
      throw new OculusException("The TargetClass must be a MarketInput. id=("+xcls.getIID()+")");
    long classid = xcls.getIID().getLongValue();//IDCONST idccls = IDCONST.getInstance(xcls.getIID());
    if(classid == IDCONST.STANDARDINPUT.getLongValue())
      return containsState(IDCONST.STANDARDINPUTOPEN.getIIDValue());
    else if(classid == IDCONST.ARTICLEINPUT.getLongValue())
      return containsState(IDCONST.ARTICLEINPUTOPEN.getIIDValue());
    else if(classid == IDCONST.WINLOSSINPUT.getLongValue())
      return containsState(IDCONST.WINLOSSINPUTOPEN.getIIDValue());
    else if(classid == IDCONST.SUMMARYINPUT.getLongValue())
      return containsState(IDCONST.SUMMARYINPUTOPEN.getIIDValue());
    else if(classid == IDCONST.IMPORTEDINPUT.getLongValue())
      return containsState(IDCONST.IMPORTEDINPUTOPEN.getIIDValue());
    else if(classid == IDCONST.QUESTIONINPUT.getLongValue())
      return containsState(IDCONST.QUESTIONINPUTOPEN.getIIDValue());
    return false; 
  }
  
  public boolean isMarketInputClosed() throws OculusException
  {
    IXClass xcls = getTargetClass();
    if(xcls == null)
      throw new OculusException("The TargetClass must be set first."); 
    if(!SearchUtil.isMarketInput(xcls.getIID().getLongValue()))
      throw new OculusException("The TargetClass must be a MarketInput. id=("+xcls.getIID()+")");
    long classid = xcls.getIID().getLongValue();
    if(classid == IDCONST.STANDARDINPUT.getLongValue())
      return containsState(IDCONST.STANDARDINPUTCLOSED.getIIDValue());
    else if(classid == IDCONST.ARTICLEINPUT.getLongValue())
      return containsState(IDCONST.ARTICLEINPUTCLOSED.getIIDValue());
    else if(classid == IDCONST.WINLOSSINPUT.getLongValue())
      return containsState(IDCONST.WINLOSSINPUTCLOSED.getIIDValue());
    else if(classid == IDCONST.SUMMARYINPUT.getLongValue())
      return containsState(IDCONST.SUMMARYINPUTCLOSED.getIIDValue());
    else if(classid == IDCONST.IMPORTEDINPUT.getLongValue())
      return containsState(IDCONST.IMPORTEDINPUTCLOSED.getIIDValue());
    else if(classid == IDCONST.QUESTIONINPUT.getLongValue())
      return containsState(IDCONST.QUESTIONINPUTCLOSED.getIIDValue());
    return false; 
  }
  
  protected boolean containsState(IIID stateiid) throws OculusException
  {
    IIID[] states = getStates();
    for(int i = 0; i < states.length; i++)
      if(states[i].equals(stateiid))
        return true;
    return false;  
  }
  
  public IIID[] getStates() throws OculusException
  { 
    List statelst = ((IQueryXML)_xml).getStates();
    IIID[] states = new IIID[statelst.size()];
    Iterator itr = statelst.iterator();
    IRepository repos = getObjectContext().getRepository();
    for(int i = 0; itr.hasNext(); i++)
      states[i] = repos.makeReposID(((Long)itr.next()).longValue());
    return states; 
  }
  
  /**
  * Used for edit.
  */
  public ISearch clear() throws OculusException
  {
    if (!isLocked())
      throw new OculusException("The object must be checked out for edit to call clear.");
    ((IQueryXML)_xml).getSelect().clear();
    ((IQueryXML)_xml).getSort().clear();
    ((IQueryXML)_xml).getFilter().setExpr(null);
    ((IQueryXML)_xml).getStates().clear();
    _qfe = null;
    setPersState(PersState.MODIFIED);
    return this;  
  }
  
  public IDataSet retrieve() throws OculusException
  {
    ReposSearcher searcher = new ReposSearcher(getObjectContext(), getQuery());
    return searcher.doQuery(); 
  }
    
  public String getSQL() throws OculusException
  {
    ReposSearcher searcher = new ReposSearcher(getObjectContext(), getQuery());
    return searcher.getSQL();
  }
  
  protected void load(IDataSet rs) throws OculusException
  {
    super.load(rs);
    IRepository repos = getObjectContext().getRepository();
    if(rs.getBinaryStream(COL_QUERYEXPR) != null)
      _xml = new QueryXML(rs.getBinaryStream(COL_QUERYEXPR));
  }
  
}