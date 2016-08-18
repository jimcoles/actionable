package com.oculussoftware.repos.query;

/**
* $Workfile: ReposSearcher.java $
* Create Date: 6/4/2000
* Description: Search engine for finding Products, Versions, Categories and
*              Features in the Accolades tree of information.  Knows how to
*              do the basic joins for this search.
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: ReposSearcher.java $
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 9/15/00    Time: 12:25p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Bug Fix: #2537
 * 
 * *****************  Version 8  *****************
 * User: Sshafi       Date: 8/28/00    Time: 3:05p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Added toString() to help in debugging.
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 8/19/00    Time: 2:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Now get QueryToSQL from a factory method instead of direct constructor.
 * 
 * *****************  Version 6  *****************
 * User: Sshafi       Date: 7/24/00    Time: 2:10p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/22/00    Time: 1:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Mod'd to return IDataSet instead of IRCollection.
 * 
 * *****************  Version 4  *****************
 * User: Eroyal       Date: 7/22/00    Time: 12:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * made sure that the query string is not null before the query is run for
 * debugging purposes.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:28a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Some signature change when invoking translator.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/14/00    Time: 6:39p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/query
 * Changed call to translator.
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

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.*;

import com.oculussoftware.system.*;
import com.oculussoftware.repos.*;
import com.oculussoftware.bus.*;
import com.oculussoftware.bus.xmeta.*;
import com.oculussoftware.rdb.*;
import com.oculussoftware.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.io.*;

/**
*/
public class ReposSearcher
{
  //----------------------------------------------------------------------
  // Private class vars
  //----------------------------------------------------------------------
  
  //----------------------------------------------------------------------
  // Private instance vars
  //----------------------------------------------------------------------
  private IObjectContext  _context = null;
//  private IIID _queryid = null;
  private IQuery          _query = null;
  private List            _queries = null;
  private MC              _mc = null;
  private String          _sql = null;
  
  //----------------------------------------------------------------------
  // Public constructor
  //----------------------------------------------------------------------
  public ReposSearcher(IObjectContext context, IQuery query)
    throws OculusException
  {
    if (query == null) throw new OculusException("null query");
    if (context == null) throw new OculusException("null context");
    _context = context;
    _query = query;
    _sql = _buildSqlQuery(getQuery()); 
    try {
      _mc = new MC(_context);
    } catch (OculusException ignore) {}
  }
    
  public ReposSearcher(IObjectContext context, List queries)
    throws OculusException
  {
    if (queries == null) throw new OculusException("null list of queries");
    if (context == null) throw new OculusException("null context");
    _context = context;
    _queries = queries;
    _sql = _buildSqlQuery(_queries); 
    try {
      _mc = new MC(_context);
    } catch (OculusException ignore) {}
  }



  //----------------------------------------------------------------------
  // Public methods
  //----------------------------------------------------------------------
  public IDataSet doQuery()
    throws OculusException
  {
    if (_query == null) throw new OculusException("null query");
    return _doQuery(); 
  }

  public IDataSet doQueries()
    throws OculusException
  {
    if (_queries == null) throw new OculusException("null query");
    return _doQueries(); 
  }

  
  public String getSQL()
  {
    
    return _sql;
  }
  
  public IQuery getQuery()
    throws OculusException
  {
    return _query;
  }

  public List getQueries()
    throws OculusException
  {
    return _queries;
  }
  
  public IObjectContext getContext() { return _context; }
  
  //----------------------------------------------------------------------
  // Private methods
  //----------------------------------------------------------------------
  private IDataSet _doQuery()
    throws OculusException
  {
    _sql   = _buildSqlQuery(getQuery());
    return _loadSqlSearch(_sql);
  }

  private IDataSet _doQueries()
    throws OculusException
  {
    _sql   = _buildSqlQuery(getQueries());
    return _loadSqlSearch(_sql);
  }
  
  /**
  * For each 'target' class, build a select statement to add to a UNION
  * query.  
  */
  private String _buildSqlQuery(IQuery query)
    throws OculusException
  {
    QueryToSQL sqler = QueryToSQL.getSQLWriter(_context.getRepository().getVendor(), _context.getRepository().getXMR());
    String retSQL = sqler.translate(query);
    return retSQL;
  }
  
  private String _buildSqlQuery(List queries)
    throws OculusException
  {
    QueryToSQL sqler = QueryToSQL.getSQLWriter(_context.getRepository().getVendor(), _context.getRepository().getXMR());
    String retSQL = sqler.translate(queries,null);
    return retSQL;
  }

  private IDataSet _loadSqlSearch(String sql)
    throws OculusException
  {
    IDataSet data = null;
    IQueryProcessor stmt = null;
    IRConnection repConn = _context.getCRM().getDatabaseConnection(_context);
    try {
      stmt = repConn.createProcessor();
      try {
        data = stmt.retrieve(sql);
      }
      catch (ORIOException sqlExp) {
        throw new OculusException(sqlExp);
      }
    }
    finally {
//      _context.getCRM().returnDatabaseConnection(repConn);
    }
    return data;
  }


  public String toString()
  {
    try {
    return _buildSqlQuery(getQueries());
    }catch (OculusException exp) { return exp.toString(); }
  }
  
}