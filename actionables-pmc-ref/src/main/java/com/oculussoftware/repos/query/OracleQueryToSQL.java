package com.oculussoftware.repos.query;

/**
* $Workfile: OracleQueryToSQL.java $
* Create Date: 8/17/2000
* Description: Translates an IQuery to Oracle sql.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: OracleQueryToSQL.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:14p
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;

import com.oculussoftware.repos.xmeta.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;

import java.util.*;

/** Translates and IQuery to SQL. */
public class OracleQueryToSQL extends QueryToSQL
{
  //-------------------------------------------------------------
  // Private instance vars
  //-------------------------------------------------------------
  //-------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------
  public OracleQueryToSQL()
  {
  }
  
	//-------------------------------------------------------------
  // Public instance methods
	//-------------------------------------------------------------
  /**
  * Creates a union of all queries.  Consuming object must ensure
  * that queries are compatible for UNION.
  * @ param sortdirs Must contain one entry for each item in the
  * select since all sorting is done by number.
  */
  
  private void _buildListsFromSelect(List attrs, List alist, List extAttrs)
  {
    Iterator iattrs = attrs.iterator();
    while (iattrs.hasNext()){
      IQSelectItem selitem = (IQSelectItem) iattrs.next();
      if (selitem.getAttr() instanceof IQAttrRef) {
        IQAttrRef attrref = (IQAttrRef) selitem.getAttr();
        _addChainToList(attrref.getAssocs(), alist);
        if (attrref.getAttr().getIsExtended() && !extAttrs.contains(attrref)) 
        {
          extAttrs.add(attrref);
        }
      }
    }
  }
  
  private void _buildListsFromSort(List sorts, List alist, List extAttrs)
  {
    Iterator isorts = sorts.iterator();
    while (isorts.hasNext()){
      IQSortItem sortitem = (IQSortItem) isorts.next();
      _addChainToList(sortitem.getAttrRef().getAssocs(), alist);
      if (sortitem.getAttrRef().getAttr().getIsExtended() && !extAttrs.contains(sortitem.getAttrRef()))
       {
        extAttrs.add(sortitem.getAttrRef());
      }
    }
  }
  
  private void _buildListsFromFilter(Object arg, List alist, List extAttrs)
  {
    if (arg instanceof IQFilterExpr) {
      IQFilterExpr fex = (IQFilterExpr) arg;
      _buildListsFromFilter(fex.getLeft(), alist, extAttrs);
      _buildListsFromFilter(fex.getRight(), alist, extAttrs);
    }
    else if (arg instanceof IQAttrRef) {
      IQAttrRef attrref = (IQAttrRef) arg;
      _addChainToList(attrref.getAssocs(), alist);
      if ( attrref.getAttr().getIsExtended() && !extAttrs.contains(attrref) ) {
        extAttrs.add(attrref);
      }
    }
  }

  
  private void _addChainToList(IXAssocChain chain, List alist)
  {
    Iterator ichain = chain.iterator();
    while (ichain.hasNext()){
      IXAssoc ass = (IXAssoc) ichain.next();
      if (!alist.contains(ass)) {
        alist.add(ass);
      }
    }
  }
  
  private void _doSelect(StringBuffer sbSql, IQuery query, List extAttrs)
    throws QueryException
  {
    Vector sqlitems = new Vector();
    IQSelect select = query.getSelect();
    if (select != null)
    {
      sbSql.append("SELECT ");
      List items = select.getAttrs();
      if (items != null )
      {
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
          IQSelectItem selitem = (IQSelectItem) iter.next();
          String strItem = "";
          if (selitem.getAttr() == null)
            throw new QueryException("null valued select item");
            
          if (selitem.getAttr() instanceof IQAttrRef) {
            IQAttrRef ar = (IQAttrRef) selitem.getAttr();
            if (ar.getAttr().getIsExtended()) {
              strItem = _getXColRef(query.getTargetClass(), ar, extAttrs);
            }
            else {
              strItem = _getColRef(query.getTargetClass(), ar);
            }
          }
          else 
//          if (selitem.getAttr() instanceof String)
          {
            strItem = selitem.getAttr().toString();
          }
          // add the alias
          if (selitem.getAlias() != null) {
            strItem += " AS " + selitem.getAlias() + " ";
          }
          sqlitems.add(strItem);
        }
        sbSql.append(StringUtil.buildCommaDelList(sqlitems));
      }
      else {
        sbSql.append(" * ");
      }
    }
  }
  private static final char C_SPC = ' ';
  
  /** Builds the from clause. */
  private void _doJoins(StringBuffer sbSql, IQFilter joinFilter, IQuery query, List assocs, List extAttrs)
    throws QueryException
  {
    sbSql.append(" FROM ");
    IXClass target = query.getTargetClass();
    
    // central table of the join
    sbSql.append(target.getTableName());
    sbSql.append(C_SPC);
    sbSql.append(_getTableSyn(target, null));
    sbSql.append(C_SPC);
    
    IXAssocChain chain = new AssocChain();
    List visits = new Vector();
    // join in all tables corresponding to referenced classes
    _doJoinsRec(sbSql, joinFilter, target, chain, target, assocs, visits);
    
    // join in extended attr tables as needed
    Iterator iAttrs = extAttrs.iterator();
    int idx = 0;
    while (iAttrs.hasNext()) {
      idx++;
      IQAttrRef attrref = (IQAttrRef) iAttrs.next();
      _addExtAttrJoin(sbSql, joinFilter, target, attrref, extAttrs);
    }
  }
  
  /** Does depth-first build of SQL joins. */
  private void _doJoinsRec(StringBuffer sbSql, IQFilter joinFilter, IXClass start, IXAssocChain chain, IXClass left, List assocs, List visits)
    throws QueryException
  {
    List classAssocs = null;
    try {
      classAssocs = _getXMR().getClassAssocs(left);
    }
    catch (OculusException ex) { throw new QueryException(ex); }
    
    Iterator icas = classAssocs.iterator();
    while (icas.hasNext()){
      IXAssoc xas = (IXAssoc) icas.next();
      if ( _isNewAndNeeded(xas, assocs, visits) ) {
        visits.add(xas);
        IXClass right = _getOtherClass(xas, left);
        _buildJoin(sbSql, joinFilter, start, chain, xas, left, right);
        chain.add(xas);
        _doJoinsRec(sbSql, joinFilter, start, chain, right, assocs, visits);
        chain.removeLast();
      }
    }
  }
  
  private IXClass _getOtherClass(IXAssoc ass, IXClass end)
    throws QueryException
  {
    IXClass retObj = null;
    try {
      retObj = _getXMR().getOtherEnd(ass, end);
    }
    catch(OculusException ex) {
      throw new QueryException(ex);
    }
    return retObj;
  }
  
  /**
  * Builds the SQL join need to traverse the specified association (xas).
  * 
  * @param start The class with respect to which the end-chain class is being joined.
  * @param leftchain The chain of associations leading up to the left class.  Does not include xas.
  * @param xas The new IXAssoc being joined in.
  * @param left The left class side of the association.  This has already been added
  *             to the join.
  * @param right The right side of the association.  This is the class we are join into the
  *              overall join clause.
  */
  private void _buildJoin(StringBuffer sbSql, IQFilter joinFilter, IXClass start, IXAssocChain leftchain, IXAssoc xas, IXClass left, IXClass right)
    throws QueryException
  {
    String childSyn = null;
    String parentSyn = null;
    String leftCol = null; String rightCol = null;
    IXAssocChain rightchain = new AssocChain(leftchain);
    rightchain.add(xas);
    String rightSyn = _getTableSyn(start, rightchain);
    if ( xas.isM2M() )
    {
      // leftTable LEFT OUTER JOIN assocTable LEFT OUTER JOIN rightTable
      if ( left == xas.getFromClass() )
      {
        childSyn = _getTableSyn(start, leftchain);
        parentSyn = _getTableSyn(start, rightchain);
        leftCol = xas.getFromColName();
        rightCol = xas.getToColName();
      }
      else {
        childSyn = _getTableSyn(start, rightchain);
        parentSyn = _getTableSyn(start, leftchain);
        leftCol = xas.getToColName();
        rightCol = xas.getFromColName();
      }
      sbSql.append(", ");
      sbSql.append(xas.getAssocClass().getTableName());
      sbSql.append(C_SPC);
      sbSql.append(xas.getAssocClass().getSyn());
      joinFilter.setExpr(new QFilterExpr(BoolOper.BOOL_AND,
                                         joinFilter.getExpr(),
                                         new QFilterExpr(CompOper.COMP_EQLEFTOUTERJOIN,
                                                         xas.getAssocClass().getSyn()+"."+leftCol,
                                                         childSyn+".OBJECTID")
                                         )
                         );
      sbSql.append(", ");
      sbSql.append(right.getTableName());
      sbSql.append(C_SPC);
      sbSql.append(rightSyn);
      joinFilter.setExpr(new QFilterExpr(BoolOper.BOOL_AND,
                                         joinFilter.getExpr(),
                                         new QFilterExpr(CompOper.COMP_EQ,
                                                         xas.getAssocClass().getSyn()+"."+rightCol,
                                                         parentSyn+".OBJECTID")
                                         )
                         );
    }
    else {
      if ( left == xas.getFromClass() )
      {
        // left table is the child
        childSyn = _getTableSyn(start, leftchain);
        parentSyn = _getTableSyn(start, rightchain);
      }
      else {
        // right table is the child ???
        // Is this valid?  --> yes.
        childSyn = _getTableSyn(start, rightchain);
        parentSyn = _getTableSyn(start, leftchain);
      }
      sbSql.append(", ");
      sbSql.append(right.getTableName());
      sbSql.append(C_SPC);
      sbSql.append(rightSyn);
      joinFilter.setExpr(new QFilterExpr(BoolOper.BOOL_AND,
                                         joinFilter.getExpr(),
                                         new QFilterExpr(CompOper.COMP_EQLEFTOUTERJOIN,
                                                         new SqlAttrRef(childSyn + "." + xas.getFromColName()),
                                                         new SqlAttrRef(parentSyn + "." + "OBJECTID "))
                                         )
                         );
    }
    
  }
  
  private void _addExtAttrJoin(StringBuffer sbSql, IQFilter joinFilter, IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    String parsyn = _getTableSyn(start, attrref.getAssocs());
    String xsyn = _getXTableSyn(start, attrref, extAttrs);
    //
    sbSql.append(" LEFT OUTER JOIN ");
    sbSql.append(_getXMR().mapPrimToTable(attrref.getAttr().getPrimType()));
    sbSql.append(C_SPC);
    sbSql.append(xsyn);
    sbSql.append(C_SPC);
    joinFilter.setExpr(new QFilterExpr(BoolOper.BOOL_AND,
                                       joinFilter.getExpr(),
                                       new QFilterExpr(BoolOper.BOOL_AND,
                                               new QFilterExpr(CompOper.COMP_EQLEFTOUTERJOIN,
                                                               new SqlAttrRef(xsyn + ".PAROBJECTID"),
                                                               new SqlAttrRef(parsyn + ".OBJECTID")),
                                               new QFilterExpr(CompOper.COMP_EQLEFTOUTERJOIN,
                                                               new SqlAttrRef(xsyn + ".ATTRIBUTEID"),
                                                               new Long(attrref.getAttr().getIID().getLongValue()))
                                                      )
                                       )
                       );
  }
  
  /** Gets a column ref for an extended attribute. */
  private String _getXColRef(IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    // add normal table ref
    String retVal = _getXTableSyn(start, attrref, extAttrs);
    // add index of ext attr based on provided list
    retVal += ".";
    if ( attrref.getAttr().getPrimType() == Primitive.MULTIENUM ) {
      retVal += "ENUMLITERALID";
    }
    else {
      retVal += "VALUE";
    }
    return retVal;
  }
  
  private String _getColRef(IXClass start, IQAttrRef attrref)
    throws QueryException
  {
    // add normal table ref
    String retVal = _getColRef(start, attrref.getAssocs(), attrref.getAttr());
    return retVal;
  }
  /** Builds an unambiguous SQL column reference for an attr/column reference. */
  private String _getColRef(IXClass start, IXAssocChain asses, IXClassAttr attr)
    throws QueryException
  {
    String retVal = _getTableSyn(start, asses);
    retVal += "." + attr.getColName();
    return retVal;
  }

  /** Builds an unambiguous synonym for a class / table reference. */
  private String _getTableSyn(IXClass start, IXAssocChain asses)
    throws QueryException
  {
    String retVal = start.getSyn();
    IXClass current = start;
    Iterator iAsses = null;
    if (asses != null && (iAsses = asses.iterator()) != null) {
      while (iAsses.hasNext()){
        IXAssoc ass = (IXAssoc) iAsses.next();
        IXClass next = _getOtherClass(ass, current);
        retVal += "_"+ass.getSyn();
        retVal += "_"+next.getSyn();
        current = next;
      }
    }
    return retVal;
  }
  
  private String _getXTableSyn(IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    IXAssocChain asses = attrref.getAssocs();
    String retVal = _getTableSyn(start, asses);
    retVal += "_" + extAttrs.indexOf(attrref);
    return retVal;
  }
  
  private boolean _isNewAndNeeded(IXAssoc xas, List assocs, List visits)
  {
    boolean retVal = false;
    if ( assocs.contains(xas) && !visits.contains(xas) )
    {
      retVal = true;
    }
    return retVal;
  }
  
  
  private void _doFilter(StringBuffer sbSql, IQuery query, IQFilterExpr fex, List extAttrs)
    throws OculusException, QueryException
  {
    if ( fex != null ) {
      sbSql.append(" WHERE ");
      _buildWhere(sbSql, null, query.getTargetClass(), extAttrs, fex);
    }
  }
  
  private void _buildWhere(StringBuffer sbSql, IQFilterExpr parExpr, IXClass start, List extAttrs, Object arg)
    throws OculusException,QueryException
  {
    if (arg == null) return;
    
    if (arg instanceof IQFilterExpr) {
      IQFilterExpr fex = (IQFilterExpr) arg;
      IOperator oper = fex.getOper();
      sbSql.append("(");
      _buildWhere(sbSql, fex, start, extAttrs, fex.getLeft());
      sbSql.append(C_SPC);
      _buildOperClause(sbSql, fex);
      sbSql.append(C_SPC);
      _buildWhere(sbSql, fex, start, extAttrs, fex.getRight());
      sbSql.append(")");
    }
    else if (arg instanceof IQAttrRef) {
      IQAttrRef attrref = (IQAttrRef) arg;
      IXClassAttr attr = attrref.getAttr();
      if ( attr.getIsExtended() ) {
        sbSql.append(_getXColRef(start, attrref, extAttrs));
      }
      else {
        sbSql.append(_getColRef(start, attrref));
      }
    }
    else if (arg instanceof IXClassAttr) {
      IQAttrRef attrref = XMR.getInstance().getAttrRef(null,start,null,(IXClassAttr)arg);
      IXClassAttr attr = attrref.getAttr();
      if ( attr.getIsExtended() ) {
        sbSql.append(_getXColRef(start, attrref, extAttrs));
      }
      else {
        sbSql.append(_getColRef(start, attrref));
      }
    }
    else {
      // handle user entered literals
      // ASSERTION: parent operator must be a CompOper
      CompOper cop = (CompOper) parExpr.getOper();
      if (arg instanceof String) {
        if (cop == CompOper.COMP_LIKE || cop == CompOper.COMP_NOTLIKE) {
          sbSql.append("'%"+SQLUtil.primer(arg.toString())+"%'");
        }
        else {
          sbSql.append("'"+SQLUtil.primer(arg.toString())+"'");
        }
      }
      else if (arg instanceof java.sql.Timestamp) {
        sbSql.append("'"+arg.toString()+"'");
      }
      else if (arg instanceof Object[]) {
        sbSql.append('(');
        sbSql.append(StringUtil.buildCommaDelList(Arrays.asList((Object[]) arg)));
        sbSql.append(')');
      }
      else if (arg instanceof long[]) {
        sbSql.append('(');
        sbSql.append(StringUtil.buildCommaDelList((long[]) arg));
        sbSql.append(')');
      }
      else if (arg instanceof Long) {
        sbSql.append(arg.toString());
      }
      else if (arg instanceof SqlAttrRef) {
        sbSql.append(arg.toString());
      }
      else {
        sbSql.append(SQLUtil.primer(arg+""));
      }
    } // end literals
  }
  
  /** Implement rules for mapping IOperator's to SQL operators. */
  private void _buildOperClause(StringBuffer sbSql, IQFilterExpr fex)
    throws OculusException
  {
    if (fex.getLeft() == null || fex.getRight() == null)
      return;
      
    IOperator oper = fex.getOper();
    if (oper instanceof CompOper) {
      CompOper cop = (CompOper) oper;
      // use default mapping
      sbSql.append(mapOperToSQL(oper));
      if (oper == CompOper.COMP_CONTAINS ||
          oper == CompOper.COMP_NOTCONTAINS ||
          oper == CompOper.COMP_CONTAINSALL)
      {
        throw new OculusException("Query translator does not yet handle operator '"+oper.getDisplayString()+"'.");
      }
    }
    else {
      // use default mapping
      sbSql.append(mapOperToSQL(oper));
    }
  }
  
  private boolean _hasPrimArg(IQFilterExpr fex, Primitive prim)
  {
    boolean retVal = false;
    retVal = (_isPrimType(fex.getLeft(), prim) || _isPrimType(fex.getRight(), prim));
    return retVal;
  }
  
  private boolean _isPrimType(Object arg, Primitive prim)
  {
    boolean retVal = false;
    if (arg instanceof IQAttrRef) {
      IQAttrRef attrref = (IQAttrRef) arg;
      retVal = (attrref.getAttr().getPrimType() == prim);
    }
    return retVal;
  }
  
  private Primitive _getExprPrimType(IQFilterExpr fex)
  {
    Primitive retVal = null;
    if ((retVal = _getPrimType(fex.getLeft())) == null) {
      retVal = _getPrimType(fex.getRight());
    }
    return retVal;
  }
  
  private Primitive _getPrimType(Object arg)
  {
    Primitive retVal = null;
    if (arg instanceof IQAttrRef) {
      IQAttrRef attrref = (IQAttrRef) arg;
      retVal = attrref.getAttr().getPrimType();
    }
    return retVal;
  }

  private void _doOrderBy(StringBuffer sbSql, IQuery query, List extAttrs)
    throws OculusException
  {
    Vector sqlitems = new Vector();
    IQSort sort = query.getSort();
    if (sort != null)
    {
      List sorts = sort.getSortItems();
      if (sorts != null && sorts.size() > 0 )
      {
        sbSql.append(" ORDER BY ");
        Iterator isort = sorts.iterator();
        while (isort.hasNext()) {
          IQSortItem si = (IQSortItem) isort.next();
          if (si.getAttrRef().getAttr().getIsExtended()) {
            sqlitems.add(_getXColRef(query.getTargetClass(), si.getAttrRef(), extAttrs));
          }
          else {
            sqlitems.add(_getColRef(query.getTargetClass(), si.getAttrRef()));
          }
        }
        sbSql.append(StringUtil.buildCommaDelList(sqlitems));
      }
    }
  }
  
  private static class SqlAttrRef {
    public String ref = null;
    public SqlAttrRef(String aref) { ref = aref; }
    public String toString() { return ref; }
  }
}
