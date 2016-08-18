package com.oculussoftware.repos.query;

/*
* $Workfile: QueryToSQL.java $
* Create Date: 6/4/2000
* Description: Represents a query filter.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: QueryToSQL.java $
 * 
 * *****************  Version 26  *****************
 * User: Jcoles       Date: 9/26/00    Time: 5:59p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Support issue #2510 - Added DISTINCT clause logic.  Handle special
 * CompOper.COMP_EXT_KEYWORD_LIKE in FROM and WHERE clause logic.  Also
 * remove unneeded passing of 'extAttrs' collection in favor of private
 * accessor method.
 * 
 * *****************  Version 25  *****************
 * User: Jcoles       Date: 9/11/00    Time: 9:00p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 2286 - boolean type handling.
 * 
 * *****************  Version 24  *****************
 * User: Sshafi       Date: 9/09/00    Time: 5:09p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Bug Fix: 2474
 * 
 * *****************  Version 23  *****************
 * User: Jcoles       Date: 9/08/00    Time: 9:40a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Issue 2408.   Handle null args in _doUserArg( ).
 * 
 * *****************  Version 22  *****************
 * User: Jcoles       Date: 8/31/00    Time: 7:29p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 21  *****************
 * User: Jcoles       Date: 8/30/00    Time: 9:19a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 1925 and 2150: Fixing search scope problems.  Added support for
 * recursive associations.
 * 
 * *****************  Version 20  *****************
 * User: Jcoles       Date: 8/28/00    Time: 3:37p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Handle case where date is comming in as a formatted string
 * 'yyyy-MM-dd'.
 * 
 * *****************  Version 19  *****************
 * User: Jcoles       Date: 8/27/00    Time: 6:12p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Added major comments.  Issue #2059: handling enum data types and dates
 * properly.
 * 
 * *****************  Version 18  *****************
 * User: Jcoles       Date: 8/23/00    Time: 7:08p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Added support for sorting and '>' for enum literals.  Added support for
 * CONTAINS any and CONTAINS all logic.
 * 
 * *****************  Version 17  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 16  *****************
 * User: Eroyal       Date: 8/07/00    Time: 8:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * SQLUtil 
 * 
 * *****************  Version 15  *****************
 * User: Sshafi       Date: 8/03/00    Time: 5:03p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 14  *****************
 * User: Jcoles       Date: 8/02/00    Time: 8:50p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Changed the sql semantics of some CompOpers.  COMP_LIKE only applies to
 * CHAR's.  COMP_CONTAINS has a complex mapping not reflected in
 * mapOperToSQL( ).  Added COMP_LIKE.
 * 
 * Added special handling for MULTIENUM prims to use column name of
 * ENUMLITERALID instead of VALUE.
 * 
 * Changed _buildWhere( ) signficantly.  Run diff to see.
 * 
 * *****************  Version 13  *****************
 * User: Eroyal       Date: 8/01/00    Time: 10:30a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * added long[] to _buildwhere
 * 
 * *****************  Version 12  *****************
 * User: Sshafi       Date: 7/28/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 11  *****************
 * User: Sshafi       Date: 7/27/00    Time: 11:09a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 9  *****************
 * User: Eroyal       Date: 7/25/00    Time: 6:46p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * added more functionality to the Custom Report
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 7/22/00    Time: 1:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Mod'd to handle IQSelectItems as return from IQSelect.getAttrs( ).
 * Fixed bug when no sort is specified.
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 7/22/00    Time: 11:45a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * quick fix to compile bug.
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:41p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Added logic handle unioned queries.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/18/00    Time: 8:59a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Various regarding how we get an IQAttrRef from the xmr.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:27a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * First version that generates valid sql for non-extended attributes.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:38p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Added much of the guts.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:27p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Adding guts.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:31a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.repi.xmeta.*;

import com.oculussoftware.repos.xmeta.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.util.*;
import com.oculussoftware.ui.DateFormatter;

import java.util.*;

/**
* Translates and IQuery to SQL using ANSI sql.  This is an abstract class that
* supplies the algorithm and default ANSI sql implementations.  This class 
* should be extended for any db vendor we want to support.  The class is designed
* to enable extension by overriding protected methods where a db vendor's sql
* differs from ANSI standard.  The primary override is for db vendors (Oracle) 
* that don't support the "JOIN" clauses in the FROM clause.
*
* Various algorithms and special rules:
*
* 1 - The Basics
* The primary algorithm is as follows:
* 1) Evaluate the IQuery select, filter and sort nodes to determing all needed joins.
*    This is possible because we use IQAttrRefs which specify association chains
*    with respect to the target class of the IQuery.  Each IXAssoc in a
*    chain corresponds to a required join.
* 2) Build the select, from, where, and order by clauses of the sql using the
*    OO-RDB mapping info embedded in the xmeta IXClass, IXAssoc, IXClassAttrs.
* 
* 2 - Enum Literal handling
* When an IQuery select, sort or filter references an enumerated data type, (i.e.,
* IXClassAttr.getPrimType.isEnum() == TRUE) some special rules are applied.
* For a select, dereference the EnumLiteral.OBJECTID to the EnumLiteral.NAME.
* For an inequality filter (i.e., ">", "<", ">=", etc) dereference to the 
* EnumLiteral.ORDERNUM.
* For a sort by, dereference to the ENUMLITERAL.ORDERNUM.
*
* 3 - Multi-valued Enums
* A multi-valued Enum data type (MULTIENUM, MULTICHECK, etc) is really a value
* that is a set as opposed to a sinlge (scalar) value.  Comparison operators are
* are applying to the set.  
*
* 4 - Dates
* The dates stored in the database are accurate to the millisecond, however
* when users specifies filter criteria they are interested in the 'day'.  
* Therefore the query engine must apply the following interpretations to achieve the
* proper comparison operator sematics:
*
* NOTE: C1 is the column / object state variable.  D2 is user-specified filter criteria.
*
* Comparison                 ==>  Interpretation
* ----------------------------------------------
  * C1 = D2                  => C1 >= D2 and C1 < (D2 + 1 day)
  * C1 != D2                 => C1 <  D2 or  C1 >= (D2 + 1 day)
  * C1 'on or before'  D2    => C1 < (D2 + 1 day)
  * C1 'on or after' D2      => C1 >= D2
*
* 5 - Roles
* Roles are treated in a manner analogous to enumerated attributes.  The following
* analogous between tables hold:
*
*   Roles               Enum Attrs
*   ---------------------------------
*   ProcessRole       = Atribute
*   ObjectRoleAssign  = EnumSelection
*   User              = EnumLiteral
*
* For a select, dereference the User.OBJECTID to the User.NAME (first last)
* For an inequality filter (i.e., ">", "<", ">=", etc) (not applicable)
* For a sort by, dereference to the User.LASTNAME.
*/
public abstract class QueryToSQL
{
  public static QueryToSQL getSQLWriter(DBVendor vendor, IXMR xmr) 
    throws OculusException
  {
    QueryToSQL retObj = null;
    
    if (vendor == DBVendor.ORACLE) {
      retObj = new OracleQueryToSQL();
    }
    else if (vendor == DBVendor.SQL_SERVER) {
      retObj = new SqlServerQueryToSQL();
    }
    retObj.setXMR(xmr);
    
    return retObj;
  }

  //-------------------------------------------------------------
  // Static methods
  //-------------------------------------------------------------
  /**
  * These are the default mappings of IOperator to SQL operator.  
  * Method _buildOperClause( ) might override these depending on 
  * the argument types.
  */
  public static String mapOperToSQL(IOperator oper)
  {
    String retVal = null;
    if      ( oper == CompOper.COMP_LIKE ) retVal = "LIKE";
    else if ( oper == CompOper.COMP_NOTLIKE ) retVal = "NOT LIKE";
    else if ( oper == CompOper.COMP_ELEMENTOF ) retVal = "IN";
    else if ( oper == CompOper.COMP_NOTELEMENTOF ) retVal = "NOT IN";
    else if ( oper == CompOper.COMP_EQ ) retVal = "=";
    else if ( oper == CompOper.COMP_NOTEQ ) retVal = "<>";
    else if ( oper == CompOper.COMP_GT ) retVal = ">";
    else if ( oper == CompOper.COMP_GTEQ ) retVal = ">=";
    else if ( oper == CompOper.COMP_LT ) retVal = "<";
    else if ( oper == CompOper.COMP_LTEQ ) retVal = "<=";
    else if ( oper == CompOper.COMP_ONORAFTER ) retVal = ">=";
    else if ( oper == CompOper.COMP_ONORBEFORE ) retVal = "<=";
    else if ( oper == CompOper.COMP_ISNULL ) retVal = "IS";
    else if ( oper == CompOper.COMP_ISNOTNULL ) retVal = "IS NOT";
    else if ( oper == BoolOper.BOOL_AND ) retVal = "AND";
    else if ( oper == BoolOper.BOOL_OR ) retVal = "OR";
    
    return retVal;
  }
  
  public static String mapDirToSQL(SortDir dir)
  {
    String retVal = null;
    if      ( dir == SortDir.ASC ) retVal = "ASC";
    else if ( dir == SortDir.DESC ) retVal = "DESC";
    return retVal;
  }
  
  //-------------------------------------------------------------
  // Private instance vars
  //-------------------------------------------------------------
  
  private IXMR _xmr = null;
  private List _allAssocs = new Vector();
  private List _allExtAttrs = new Vector();
  private List _allEnumLitAttrs = new Vector();
  private List _allRoleAttrs = new Vector();
  private boolean _extKeywordSearch = false;
  
  //-------------------------------------------------------------
  // Constructor(s)
  //-------------------------------------------------------------
  public QueryToSQL()
  {
  }
  
	//-------------------------------------------------------------
  // Public instance methods
	//-------------------------------------------------------------
  public void setXMR(IXMR xmr) { _xmr = xmr; }
  public List getAssocs() { return _allAssocs; }
  public List getExtAttrs() { return _allExtAttrs; }
  public List getEnumLitAttrs() { return _allEnumLitAttrs; }
  public boolean getIsExtKeywordSearch() { return _extKeywordSearch; }
  
  /**
  * Creates a union of all queries.  Consuming object must ensure
  * that queries are compatible for UNION.  
  * @ param sortdirs Must contain one SortDir for each item in the
  * select since all sorting is done by number.
  */
	public String translate(List queries, List sortdirs)
  	throws OculusException
	{
    StringBuffer sbSql = new StringBuffer(1000);
    
    Iterator iter = null;
    if(queries != null && (iter=queries.iterator()) != null)
    {
      while (iter.hasNext()) {
        IQuery query = (IQuery) iter.next();
        _translate(sbSql, query);
        if (queries.indexOf(query) < (queries.size()-1))
          sbSql.append(" UNION ");
      }  
    }
    if(sortdirs != null && (iter=sortdirs.iterator()) != null)
    {
      sbSql.append(" ORDER BY ");
      int num = 0;
      while (iter.hasNext()) {
        num++;
        SortDir dir = (SortDir) iter.next();
        sbSql.append(num + mapDirToSQL(dir));
        if (num < sortdirs.size())
          sbSql.append(", ");
      }
    }
    // This is baaaadddddd....
    String retVal = sbSql.toString();
    if (retVal.length() < 10)
      retVal = null;
    return retVal;
  }
  
	public String translate(IQuery query)
  	throws OculusException
  {
		String retSql = "";
    StringBuffer sbSql = new StringBuffer(1000);
    _translate(sbSql, query);
    return sbSql.toString();
  }
  
  //-------------------------------------------------------------
  // Private instance methods
  //-------------------------------------------------------------
  
	private void _translate(StringBuffer sbSql, IQuery query)
  	throws OculusException
	{
    _allAssocs = new Vector();
    _allExtAttrs = new Vector();
    _allEnumLitAttrs = new Vector();
    _allRoleAttrs = new Vector();
    _extKeywordSearch = false;
    
    // pre-evaluate IQuery to determine needed associations / joins...
    _preEvalQuery(query);
    
    _doSelect(sbSql, query);
    _doFrom(sbSql, query);
    _doWhere(sbSql, query);
    _doOrderBy(sbSql, query);
	}
  
  /** Traverse attrRefs to get superset of associations needed. */
  private void _preEvalQuery(IQuery query)
  {
    // eval select
    _buildListsFromSelect(query.getSelect().getAttrs());
    
    // eval filter
    _buildListsFromFilter(query.getFilter().getExpr());
    
    // eval sort
    _buildListsFromSort(query.getSort().getSortItems());
    
  }
  
  private void _buildListsFromSelect(List attrs)
  {
    List alist = getAssocs();
    
    Iterator iattrs = attrs.iterator();
    while (iattrs.hasNext()){
      IQSelectItem selitem = (IQSelectItem) iattrs.next();
      if (selitem.getAttr() instanceof IQAttrRef) {
        IQAttrRef attrref = (IQAttrRef) selitem.getAttr();
        _addChainToList(attrref.getAssocs(), alist);
        _addAttrToLists(attrref);
      }
    }
  }
  
  /**
  * Adds the IQAttrRef to the proper pre-eval list(s). 
  * NOTE: An IXClassAttr is a role (IXClassAttr.getIsRole() == TRUE) has a
  * Primitive type of Primitive.ENUM or MULTIENUM, but is not a true
  * enum literal; thus, the special handling below.
  */
  private void _addAttrToLists(IQAttrRef attrref)
  {
    List extAttrs = getExtAttrs();
    List enumLitAttrs = getEnumLitAttrs();
    if (attrref.getAttr().getIsExtended() && !extAttrs.contains(attrref)) 
    {
      extAttrs.add(attrref);
    }
    if (attrref.getAttr().getPrimType().isEnum() && !enumLitAttrs.contains(attrref)) {
      enumLitAttrs.add(attrref);
    }
  }
  
  private void _buildListsFromSort(List sorts)
  {
    List alist = getAssocs();
    Iterator isorts = sorts.iterator();
    while (isorts.hasNext()){
      IQSortItem sortitem = (IQSortItem) isorts.next();
      _addChainToList(sortitem.getAttrRef().getAssocs(), alist);
      IQAttrRef attrref = sortitem.getAttrRef();
      _addAttrToLists(attrref);
    }
  }
  
  private void _buildListsFromFilter(Object arg)
  {
    List alist = getAssocs();
    if (arg instanceof IQFilterExpr) {
      IQFilterExpr fex = (IQFilterExpr) arg;
      
      // Track whether query is searching extended keywords attritbutes
      if (((IQFilterExpr) arg).getOper() == CompOper.COMP_EXT_KEYWORD_LIKE)
        _extKeywordSearch = true;
        
      _buildListsFromFilter(fex.getLeft());
      _buildListsFromFilter(fex.getRight());
    }
    else if (arg instanceof IQAttrRef) {
      IQAttrRef attrref = (IQAttrRef) arg;
      _addChainToList(attrref.getAssocs(), alist);
      _addAttrToLists(attrref);
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
  
  private void _doSelect(StringBuffer sbSql, IQuery query)
    throws QueryException
  {
    List extAttrs = getExtAttrs();
  
    Vector sqlitems = new Vector();
    IQSelect select = query.getSelect();
    if (select != null)
    {
      sbSql.append("SELECT ");
      if(select.getUseDistinct())
        sbSql.append(" DISTINCT ");
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
            if (ar.getAttr().getIsRole()) {
              strItem = _getEnumTableSyn(query.getTargetClass(), ar, extAttrs) + ".LASTNAME";
            }
            else if (ar.getAttr().getPrimType().isEnum()) {
              strItem = _getEnumTableSyn(query.getTargetClass(), ar, extAttrs) + ".NAME";
            }
            else {
              strItem = _getAnyColRef(query.getTargetClass(), ar, extAttrs);
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
  private void _doFrom(StringBuffer sbSql, IQuery query)
    throws QueryException
  {
    List assocs = getAssocs();
    List extAttrs = getExtAttrs();
    List enumLitAttrs = getEnumLitAttrs();
    
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
    _doFromRec(sbSql, target, chain, target, assocs, visits);
    
    // join in extended attr tables as needed
    Iterator iAttrs = extAttrs.iterator();
    while (iAttrs.hasNext()) {
      IQAttrRef attrref = (IQAttrRef) iAttrs.next();
      _addExtAttrJoin(sbSql, target, attrref, extAttrs);
    }
    
    // join in enumliteral table as needed
    iAttrs = enumLitAttrs.iterator();
    while (iAttrs.hasNext()) {
      IQAttrRef attrref = (IQAttrRef) iAttrs.next();
      _addEnumLitAttrJoin(sbSql, target, attrref, extAttrs);
    }
    
    // add in ext attr tables (CHAR, and LONG_CHAR) as needed
    if (getIsExtKeywordSearch()) {
      String parsyn = _getTableSyn(target, null);
      sbSql.append(" LEFT OUTER JOIN ");
      sbSql.append(_xmr.mapPrimToTable(Primitive.CHAR));
      sbSql.append(C_SPC);
      sbSql.append(SYN_EXCHAR);
      sbSql.append(C_SPC);
      sbSql.append(" ON " + SYN_EXCHAR + ".PAROBJECTID = " + parsyn + ".OBJECTID");
      sbSql.append(" LEFT OUTER JOIN ");
      sbSql.append(_xmr.mapPrimToTable(Primitive.LONG_CHAR));
      sbSql.append(C_SPC);
      sbSql.append(SYN_EXLCHAR);
      sbSql.append(C_SPC);
      sbSql.append(" ON " + SYN_EXLCHAR + ".PAROBJECTID = " + parsyn + ".OBJECTID");
    }
  }
  
  /** Does depth-first build of SQL joins. */
  private void _doFromRec(StringBuffer sbSql, IXClass start, IXAssocChain chain, IXClass left, List assocs, List visits)
    throws QueryException
  {
    List classAssocs = null;
    try {
      classAssocs = _xmr.getClassAssocs(left);
    }
    catch (OculusException ex) { throw new QueryException(ex); }
    
    Iterator icas = classAssocs.iterator();
    while (icas.hasNext()){
      IXAssoc xas = (IXAssoc) icas.next();
      if ( _isNewAndNeeded(xas, assocs, visits) ) {
        visits.add(xas);
        IXClass right = _getOtherClass(xas, left);
        _buildFrom(sbSql, start, chain, xas, left, right);
        chain.add(xas);
        _doFromRec(sbSql, start, chain, right, assocs, visits);
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
  
  protected IXMR _getXMR() { return _xmr; }
  
  /**
  * Builds the SQL join need to traverse the specified association (xas).
  * 
  * @param left The left class side of the association.  This has already been added
  *             to the join.
  * @param right The  side of the association.  This is the class we are join into the
  *              overall join clause.
  * @param leftchain The chain of associations leading up to the left class.  Does not include xas.
  */
  private void _buildFrom(StringBuffer sbSql, IXClass start, IXAssocChain leftchain, IXAssoc xas, IXClass left, IXClass right)
    throws QueryException
  {
    String childSyn = null;
    String parentSyn = null;
    String leftCol = null; String rightCol = null;
    IXAssocChain rightchain = new AssocChain(leftchain);
    rightchain.add(xas);
    String rightSyn = _getTableSyn(start, rightchain);
    String leftSyn = _getTableSyn(start, leftchain);
    // treat m2m and rec assocs kind of the same.
    if ( xas.isM2M() || xas.isRec() )
    {
      String assocTableName = null;
      String assocTableSyn = null;
      if (xas.isM2M()) {
        // leftTable LEFT OUTER JOIN assocTable LEFT OUTER JOIN rightTable
        assocTableName = xas.getAssocClass().getTableName();
        assocTableSyn = leftSyn + xas.getSyn() + xas.getAssocClass().getSyn();
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
      }
      else if (xas.isRec()) {
        // theTable LEFT OUTER JOIN indexTable LEFT OUTER JOIN theTable
        // NOTE: for recursive assoc, getFromClass( ) == getToClass( )
        if (xas.getFromClass( ) != xas.getToClass( ))
          throw new QueryException("A recursive association ["+xas.getSyn()+","+xas.getIID().getLongValue()+"] was found in which the from class does not equal the to class.");
          
        assocTableName = xas.getFromClass().getIndexTable();
        assocTableSyn = leftSyn + xas.getSyn();
        childSyn = _getTableSyn(start, leftchain);
        parentSyn = _getTableSyn(start, rightchain);
        leftCol = xas.getFromClass().getIndexChildColName();
        rightCol = xas.getFromClass().getIndexParentColName();
      }
      sbSql.append(" LEFT OUTER JOIN ");
      sbSql.append(assocTableName);
      sbSql.append(C_SPC);
      sbSql.append(assocTableSyn);
      sbSql.append(" ON ");
      sbSql.append(assocTableSyn+"."+leftCol+"="+childSyn+".OBJECTID");
      sbSql.append(" LEFT OUTER JOIN ");
      sbSql.append(right.getTableName());
      sbSql.append(C_SPC);
      sbSql.append(rightSyn);
      sbSql.append(" ON ");
      sbSql.append(assocTableSyn+"."+rightCol+"="+parentSyn+".OBJECTID");
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
      sbSql.append(" LEFT OUTER JOIN ");
      sbSql.append(right.getTableName());
      sbSql.append(C_SPC);
      sbSql.append(rightSyn);
      sbSql.append(" ON ");
      sbSql.append(childSyn + "." + xas.getFromColName());
      sbSql.append(" = ");
      sbSql.append(parentSyn + "." + "OBJECTID ");
    }
  }
  
  private void _addExtAttrJoin(StringBuffer sbSql, IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    String parsyn = _getTableSyn(start, attrref.getAssocs());
    String xsyn = _getXTableSyn(start, attrref, extAttrs);
    //
    if (!attrref.getAttr().getIsRole()) {
      sbSql.append(" LEFT OUTER JOIN ");
      sbSql.append(_xmr.mapPrimToTable(attrref.getAttr().getPrimType()));
      sbSql.append(C_SPC);
      sbSql.append(xsyn);
      sbSql.append(C_SPC);
      sbSql.append(" ON (" + xsyn + ".PAROBJECTID = " + parsyn + ".OBJECTID");
      sbSql.append(" AND " + xsyn + ".ATTRIBUTEID = " + attrref.getAttr().getIID().getLongValue() + ") ");
    }
    else {
      sbSql.append(" LEFT OUTER JOIN OBJECTROLEASSIGN ");
      sbSql.append(C_SPC);
      sbSql.append(xsyn);
      sbSql.append(C_SPC);
      sbSql.append(" ON (" + xsyn + ".PAROBJECTID = " + parsyn + ".OBJECTID");
      sbSql.append(" AND " + xsyn + ".ROLEID = " + attrref.getAttr().getIID().getLongValue() + ") ");
    }
  }
  
  /** Adds join to ENUMLITERAL table to get order num or other columns. */
  private void _addEnumLitAttrJoin(StringBuffer sbSql, IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    String enumref = _getAnyColRef(start, attrref, extAttrs);
    String enumtabsyn = _getEnumTableSyn(start, attrref, extAttrs);
    //
    String table = "ENUMLITERAL";
    if (attrref.getAttr().getIsRole())
      table = "APPUSER";
      
    sbSql.append(" LEFT OUTER JOIN " + table);
    sbSql.append(C_SPC);
    sbSql.append(enumtabsyn);
    sbSql.append(C_SPC);
    sbSql.append(" ON ( " + enumref + "=" + enumtabsyn + ".OBJECTID)");
  }
  
  private String _getAnyColRef(IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    String retVal = null;
    IXClassAttr attr = attrref.getAttr();
    if ( attr.getIsExtended() ) {
      retVal = _getXColRef(start, attrref, extAttrs);
    }
    else {
      retVal = _getColRef(start, attrref);
    }
    return retVal;
  }
  
  /** Gets a column ref for an extended attribute. */
  private String _getXColRef(IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    // add normal table ref
    String retVal = _getXTableSyn(start, attrref, extAttrs);
    // add index of ext attr based on provided list
    retVal += ".";
    if (attrref.getAttr().getIsRole()) {
      retVal += "USERID";
    }
    else if (attrref.getAttr().getPrimType().isMultiValued()) {
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

  private String _getEnumTableSyn(IXClass start, IQAttrRef attrref, List extAttrs)
    throws QueryException
  {
    return _getAttrTableSyn(start, attrref, extAttrs) 
        + (attrref.getAttr().getIsRole()? "_usr":"_enlit");
  }
  
  private String _getAttrTableSyn(IXClass start, IQAttrRef attrref, List attrs)
    throws QueryException
  {
    if (attrref.getAttr().getIsRole())
      return _getXTableSyn(start, attrref, attrs);
    else if (attrref.getAttr().getIsExtended())
      return _getXTableSyn(start, attrref, attrs);
    else
      return _getTableSyn(start, attrref.getAssocs());  
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
    String retVal = _getTableSyn(start, attrref.getAssocs());
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
  
  
  private void _doWhere(StringBuffer sbSql, IQuery query)
    throws OculusException, QueryException
  {
    IQFilter filter = query.getFilter();
    if ( filter.getExpr() != null ) {
      sbSql.append(" WHERE ");
      _buildWhere(sbSql, null, query.getTargetClass(), query.getFilter().getExpr());
    }
  }
  
  private void _buildWhere(StringBuffer sbSql, IQFilterExpr parExpr, IXClass start, Object arg)
    throws OculusException,QueryException
  {
    boolean bIsParentEnumInequality = _isEnumInequality(parExpr);
    
    if (arg instanceof IQFilterExpr) {
      // check for special cases first.
      IQFilterExpr fex = (IQFilterExpr) arg;
      boolean bIsDateExpr = _isDateExpr(fex);
      IOperator oper = fex.getOper();
      sbSql.append("(");
      if (oper == CompOper.COMP_CONTAINS) {
        _doOper_ContainsAny(sbSql, fex, start);
      }
      else if (oper == CompOper.COMP_NOTCONTAINS) {
        _doOper_NotContainsAny(sbSql, fex, start);
      }
      else if (oper == CompOper.COMP_CONTAINSALL) {
        _doOper_ContainsAll(sbSql, fex, start);
      }
      else if (bIsDateExpr) {
        _doOper_Date(sbSql, fex, start);
      }
      else if (oper == CompOper.COMP_TRUE || oper == CompOper.COMP_FALSE) {
        _doOper_Boolean(sbSql, fex, start);
      }
      else if (oper == CompOper.COMP_EXT_KEYWORD_LIKE) {
        _doOper_ExtKeywordLike(sbSql, fex, start);
      }
      else {
        // do the 'simple' operators that map directly to sql
        _buildWhere(sbSql, fex, start, fex.getLeft());
        _buildOperClause(sbSql, fex);
        _buildWhere(sbSql, fex, start, fex.getRight());
      }
      sbSql.append(")");
    }
    // handle a class attribute reference
    else if (arg instanceof IQAttrRef || arg instanceof IXClassAttr) {
      _doAttrSQL(arg, sbSql, start, bIsParentEnumInequality);
    }
    // handle user entered literals
    else {
      if (bIsParentEnumInequality) {
        sbSql.append(_doEnumLitSubSelect(parExpr, arg));
      }
      else {
        // ASSERTION: parent operator must be a CompOper
        sbSql.append(_doUserArg(parExpr, arg));
      }
    } 
  }
  private String _doUserArg(IQFilterExpr parExpr, Object arg)
    throws QueryException
  {
    String retVal = null;
    if (arg != null) {
      CompOper cop = (CompOper) parExpr.getOper();
      if (arg instanceof String) {
        if (cop == CompOper.COMP_LIKE || cop == CompOper.COMP_NOTLIKE || cop == CompOper.COMP_EXT_KEYWORD_LIKE) {
          retVal = "'%"+SQLUtil.primer(arg.toString())+"%'";
        }
        else {
          retVal = "'"+SQLUtil.primer(arg.toString())+"'";
        }
      }
      else if (arg instanceof java.sql.Timestamp) {
        retVal = "'"+DateUtil.truncToDayStart((java.sql.Timestamp)arg)+"'";
      }
      else if (arg instanceof Boolean) {
        retVal = _getBooleanLiteral(arg);
      }
      else if (arg instanceof Object[]) {
        retVal = '(' +  StringUtil.buildCommaDelList(Arrays.asList((Object[]) arg))+ ')';
      }
      else if (arg instanceof long[]) {
        retVal = '(' + StringUtil.buildCommaDelList((long[]) arg) + ')';
      }
      else {
        retVal = SQLUtil.primer(arg.toString());
      }
    }
    return retVal;
  }

  private String _getBooleanLiteral(Object arg)
  {
    String retVal = "";
    if (arg instanceof Boolean) {
      Boolean barg = (Boolean) arg;
      if (barg == Boolean.TRUE) 
        retVal = "1";
      else
        retVal = "0";  
    }
    return retVal;
  }
  /** Build SQL for and attr/column reference. */
  private void _doAttrSQL(Object arg, StringBuffer sbSql, IXClass start, boolean bIsParentEnumInequality)
    throws OculusException
  {
    List extAttrs = getExtAttrs();
    IQAttrRef attrref = null;
    if (arg instanceof IQAttrRef) {
      attrref = (IQAttrRef) arg;
    }
    else if (arg instanceof IXClassAttr) {
      attrref = XMR.getInstance().getAttrRef(null,start,null,(IXClassAttr)arg);
    }
    // if refering to an enum literal as part of an inequality, dereference to the 
    // ENUMLITERAL.ORDERNUM
    if (bIsParentEnumInequality) {
      sbSql.append(_getEnumTableSyn(start, attrref, extAttrs) + ".ORDERNUM");
    }
    else {
      sbSql.append(_getAnyColRef(start, attrref, extAttrs));
    }
  }
  
  public static final String SYN_EXCHAR = "xk_char";
  public static final String SYN_EXLCHAR = "xk_lchar";
  
  private void _doOper_ExtKeywordLike(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    String right = ".VALUE LIKE " + _doUserArg(fex, fex.getRight());
    sbSql.append(SYN_EXCHAR);
    sbSql.append(right);
    sbSql.append(" OR ");
    sbSql.append(SYN_EXLCHAR);
    sbSql.append(right);
  }
  
  private void _doOper_Boolean(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    // do the 'simple' operators that map directly to sql
    _buildWhere(sbSql, fex, start, fex.getLeft());
    sbSql.append(" = ");
    Boolean bval = ((fex.getOper() == CompOper.COMP_TRUE)? Boolean.TRUE : Boolean.FALSE);
    sbSql.append(_doUserArg(fex, bval));
  }
  
  /**
  * Comparison                 ==>  Interpretation
  * ----------------------------------------------
  * C1 = D2                  => C1 >= D2 and C1 < (D2 + 1 day)
  * C1 != D2                 => C1 < D2 or  C1 >= (D2 + 1 day)
  * C1 'on or before'  D2    => C1 < (D2 + 1 day)
  * C1 'on or after' D2      => C1 >= D2
  */
  private void _doOper_Date(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    List extAttrs = getExtAttrs();
    CompOper cop = (CompOper) fex.getOper();
    java.sql.Timestamp arg = null;
    if (fex.getRight() instanceof java.sql.Timestamp) {
      arg = (java.sql.Timestamp) fex.getRight();
    }
    else {
      arg = DateFormatter.getDateTimestamp(fex.getRight().toString());
    }
    
    if (cop == CompOper.COMP_EQ) {
      _doAttrSQL(fex.getLeft(), sbSql, start, false);
      sbSql.append(" >= ");
      sbSql.append("'"+arg+"'");
      sbSql.append(" AND ");
      _doAttrSQL(fex.getLeft(), sbSql, start, false);
      sbSql.append(" < ");
      sbSql.append("'"+DateUtil.add(arg, Calendar.DATE, 1)+"'");
    }
    else if (cop == CompOper.COMP_NOTEQ) {
      _doAttrSQL(fex.getLeft(), sbSql, start, false);
      sbSql.append(" < ");
      sbSql.append("'"+arg+"'");
      sbSql.append(" OR ");
      _doAttrSQL(fex.getLeft(), sbSql, start, false);
      sbSql.append(" >= ");
      sbSql.append("'"+DateUtil.add(arg, Calendar.DATE, 1)+"'");
    }
    else if (cop == CompOper.COMP_ONORBEFORE) {
      _doAttrSQL(fex.getLeft(), sbSql, start, false);
      sbSql.append(" < ");
      sbSql.append("'"+DateUtil.add(arg, Calendar.DATE, 1)+"'");
    }
    else if (cop == CompOper.COMP_ONORAFTER) {
      _doAttrSQL(fex.getLeft(), sbSql, start, false);
      sbSql.append(" >= ");
      sbSql.append("'"+arg+"'");
    }
    else {
    }
  }
  
  /** Implement rules for mapping IOperator's to SQL operators. */
  private void _buildOperClause(StringBuffer sbSql, IQFilterExpr fex)
    throws OculusException
  {
    IOperator oper = fex.getOper();
    // use default mapping
    sbSql.append(C_SPC);
    sbSql.append(mapOperToSQL(oper));
    sbSql.append(C_SPC);
  }
  
  private boolean _isEnumInequality(IQFilterExpr fex)
  {
    boolean retVal = false;
    if (   fex != null 
        && fex.getOper() instanceof CompOper 
        && fex.getLeft() instanceof IQAttrRef) 
    {
      CompOper oper = (CompOper) fex.getOper();
      IQAttrRef attrref = (IQAttrRef) fex.getLeft();
      retVal = (oper.isInequality() && attrref.getAttr().getPrimType().isEnum());
    }
    return retVal;
  }
  
  private boolean _isDateExpr(IQFilterExpr fex)
  {
    boolean retVal = false;
    if (   fex != null 
        && fex.getOper() instanceof CompOper 
        && fex.getLeft() instanceof IQAttrRef) 
    {
      CompOper oper = (CompOper) fex.getOper();
      IQAttrRef attrref = (IQAttrRef) fex.getLeft();
      Primitive primtype = attrref.getAttr().getPrimType();
      retVal = (primtype == Primitive.TIME);
    }
    return retVal;
  }

  private void _doOper_ContainsAny(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    _doOper_ContainsXXX(sbSql, fex, BoolOper.BOOL_OR, start);
  }
  
  private void _doOper_ContainsXXX(StringBuffer sbSql, IQFilterExpr fex, BoolOper bop, IXClass start)
    throws OculusException
  {
    try {
      IQAttrRef aref = (IQAttrRef) fex.getLeft();
      String subselect = " in " + _doEnumSubSelect(aref, start);
      long ids[] = (long[]) fex.getRight();
      for(int idx = 0; idx < ids.length; idx++) {
        sbSql.append("(" + ids[idx] + subselect + ")");
        if (idx < (ids.length - 1))
          sbSql.append(mapOperToSQL(bop));
      }
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
  }

  private String _doEnumLitSubSelect(IQFilterExpr parExpr, Object arg)
    throws QueryException
  {
    return "(SELECT ORDERNUM FROM ENUMLITERAL EL WHERE OBJECTID = " + _doUserArg(parExpr, arg) + ")";
  }
  
  private String _doEnumSubSelect(IQAttrRef aref, IXClass start)
    throws QueryException
  {
    String retVal = null;
    if (aref.getAttr().getIsRole()) {
      retVal = "(SELECT USERID FROM OBJECTROLEASSIGN ORA WHERE PAROBJECTID = "
             + _getTableSyn(start, aref.getAssocs()) + ".OBJECTID "
             + " AND ROLEID = " + aref.getAttr().getIID().getLongValue() + ")";
    }
    else {
      retVal = "(SELECT ENUMLITERALID FROM ENUMSELECTION ES WHERE PAROBJECTID = "
             + _getTableSyn(start, aref.getAssocs()) + ".OBJECTID "
             + " AND ATTRIBUTEID = " + aref.getAttr().getIID().getLongValue() + ")";
    }
    return retVal;
  }
  
  private void _doOper_NotContainsAny(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    sbSql.append("NOT (");
    _doOper_ContainsAny(sbSql, fex, start);
    sbSql.append(")");
  }
  
  private void _doOper_ContainsAll(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    _doOper_ContainsXXX(sbSql, fex, BoolOper.BOOL_AND, start);
  }
  
  private void _doOper_NotContainsAll(StringBuffer sbSql, IQFilterExpr fex, IXClass start)
    throws OculusException
  {
    sbSql.append("NOT (");
    _doOper_ContainsAll(sbSql, fex, start);
    sbSql.append(")");
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
  
  private void _doOrderBy(StringBuffer sbSql, IQuery query)
    throws OculusException
  {
    List extAttrs = getExtAttrs();
  
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
          String colref = null;
          if (si.getAttrRef().getAttr().getPrimType().isEnum()) {
            colref = _getEnumTableSyn(query.getTargetClass(), si.getAttrRef(), extAttrs) + ".ORDERNUM";
          }
          else {
            colref = _getAnyColRef(query.getTargetClass(), si.getAttrRef(), extAttrs);
          }
          sqlitems.add(colref + " " + mapDirToSQL(si.getDir()));
        }
        sbSql.append(StringUtil.buildCommaDelList(sqlitems));
      }
    }
  }
}
