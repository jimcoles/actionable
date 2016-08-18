package com.oculussoftware.repos.repsimplerdb;

/*
* $Workfile: SimpleRepository.java $
* Create Date:        2-29-2000
* Description:
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* @author J. Coles
* @version 1.2
*
* $History: SimpleRepository.java $
 * 
 * *****************  Version 41  *****************
 * User: Jcoles       Date: 8/15/00    Time: 3:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Issue 1842: plumbing to support vendor-specific join syntax.
 * 
 * *****************  Version 40  *****************
 * User: Jcoles       Date: 8/11/00    Time: 6:54p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Replace Class.forName( ) with DriverManager.registerDriver( ) in
 * _initDBConnParms().  Added _ensureReposRow() to negate need for db init
 * process to add the row.  Funneling genReposID( ) to a new static sync'd
 * method for better syncing.  Added _getSysConn( ) for getting a
 * singleton-like 'system' db connection.  This was need because oracle
 * connection were too slow to get each time in genReposID( ).
 * 
 * *****************  Version 39  *****************
 * User: Jcoles       Date: 8/09/00    Time: 5:35p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Removed private method _initDB( ) since it had no value add.  Replaced
 * _initDBTables( ) with _setMaxOID( ) which now is called by both public
 * methods initialize( ) and setMaxOID( ).  Moved logic that was in a
 * stored proc into _setMaxOID( ).
 * 
 * *****************  Version 38  *****************
 * User: Apota        Date: 8/01/00    Time: 4:57p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 37  *****************
 * User: Jcoles       Date: 8/01/00    Time: 11:49a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Added conn.commit( ) to setMaxOID( ).
 * 
 * *****************  Version 36  *****************
 * User: Jcoles       Date: 7/29/00    Time: 12:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Added setMaxOID( ).  Recoded some existing logic to reference static
 * consts.
 * 
 * *****************  Version 35  *****************
 * User: Czhang       Date: 7/27/00    Time: 11:37a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 33  *****************
 * User: Czhang       Date: 7/26/00    Time: 10:02a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * added properties folder to the file name in getFullReleaseString()
 * 
 * *****************  Version 32  *****************
 * User: Czhang       Date: 7/26/00    Time: 9:03a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * changed key to suite.major, suite.minor etc in the getFullReleaseString
 * method.
 * 
 * *****************  Version 31  *****************
 * User: Czhang       Date: 7/26/00    Time: 8:33a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 29  *****************
 * User: Czhang       Date: 7/25/00    Time: 5:09p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * added accessor methods
 * getBuild(),getRelease(),getFullReleaseString(),getMajor(), getMinor().
 * 
 * *****************  Version 28  *****************
 * User: Jcoles       Date: 7/25/00    Time: 11:15a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Added exception throw to getXMR( ) per interface change.
 * 
 * *****************  Version 27  *****************
 * User: Jcoles       Date: 7/13/00    Time: 4:16p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Added getXMR( ) in support of search and reports.
 * 
 * *****************  Version 26  *****************
 * User: Jcoles       Date: 5/22/00    Time: 4:34p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * BUG00317 (database locking)
 * - no longer initialize the MAXCLASSID everytime the class is
 * initialized.  Now does that only if initialize( ) is called.
 * 
 * *****************  Version 25  *****************
 * User: Eroyal       Date: 5/18/00    Time: 10:15a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * debuggin trying to solve the db locking problem.
 * added some LogService calls
 * checked for null the the SQLUtil
 * 
 * *****************  Version 24  *****************
 * User: Eroyal       Date: 5/17/00    Time: 9:44a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * didn't really do anything, I added a few lines of code that I commented
 * out.  
 * 
 * *****************  Version 23  *****************
 * User: Jcoles       Date: 5/11/00    Time: 1:45p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Re-applying changes to use stored procedures for genReposID( ).
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 5/08/00    Time: 8:38a
 * Updated in $/Unfinished code/JKC Work/Project - Socrates/DevSource1.2/Framework.java/com/oculussoftware/repos/repsimplerdb
 * Archival checkin.
 * 
 * *****************  Version 21  *****************
 * User: Jcoles       Date: 5/02/00    Time: 6:31p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Fixed genReposID( ) to use sp_nextid SP.  Removed old logic related to
 * finding max/next id.  Removed obsolete registerClass(),
 * getTransaction(), createQuery(), etc.
 * 
 * *****************  Version 20  *****************
 * User: Jcoles       Date: 4/28/00    Time: 1:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * Removed some commented out obsolete code.
 * 
 * *****************  Version 19  *****************
 * User: Eroyal       Date: 4/21/00    Time: 10:58a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * dolly
 * 
 * *****************  Version 18  *****************
 * User: Sshafi       Date: 4/04/00    Time: 11:07a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 17  *****************
 * User: Sshafi       Date: 4/03/00    Time: 7:47a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 16  *****************
 * User: Sshafi       Date: 3/30/00    Time: 10:08a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 15  *****************
 * User: Czhang       Date: 3/26/00    Time: 3:43p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 14  *****************
 * User: Sshafi       Date: 3/21/00    Time: 3:30p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 13  *****************
 * User: Eroyal       Date: 3/08/00    Time: 9:20a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 12  *****************
 * User: Sshafi       Date: 3/06/00    Time: 4:52p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 11  *****************
 * User: Eroyal       Date: 3/03/00    Time: 4:55p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 3/03/00    Time: 3:11p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 3/03/00    Time: 11:42a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/repsimplerdb
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 3/03/00    Time: 11:20a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/oculussoftware/repos/repsimplerdb
*
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.xmeta.*;
import com.oculussoftware.api.busi.*;

import com.oculussoftware.system.*;
import com.oculussoftware.repos.xmeta.*;
import com.oculussoftware.repos.util.SequentialIID;
import com.oculussoftware.util.*;
import com.oculussoftware.service.log.LogService;
  
//import com.oculussoftware.system.*;

import java.sql.*;
import java.util.*;
import java.io.*;

/**
* SimpleRepository is a concrete implementation of IRespository defined in
* .api.repi.  It uses a fixed JDBC database schema.
*/
public class SimpleRepository implements IRepository, IPoolable
{
  public static final IDCONST ROOT_OBJECT_ID = IDCONST.OCUREPOS;
  //---------------------------------------------------------------
  // Private constants
  //---------------------------------------------------------------
  
  //---------------------------------------------------------------
  // Private class methods
  //---------------------------------------------------------------
  private static IDirectory _directory = null;
  private static String _user   = null;
  private static String _pass   = null;
  private static String _dsn    = null;
  private static String _driver = null;
  private static DBVendor _vendor = null;
  private static boolean _initworked = false;
  
  //added by Cuihua
  private static int _major = 0;
  private static int _minor = 0;
  private static String _release = null;
  private static int _build = 0;
  private static String _fullReleaseString = "";
  
  static {
    try {
      _initDBConnParms();
      _initworked = true;
    }
    catch (Exception ex) {
      LogService.getInstance().write(ex);
    }
  }
  
  private static void _initDBConnParms()
    throws Exception
  {
    _directory = SimpleDirectory.getInstance();
    _pass = "eclatdba";
    _user = (String) _directory.getValue("username", "Connection");
    _dsn = (String) _directory.getValue("dsn", "Connection");
    _driver = (String) _directory.getValue("driver", "Connection");
    if (_driver.indexOf("oracle") != -1) {
      _vendor = DBVendor.ORACLE;
    }
    else {
      _vendor = DBVendor.SQL_SERVER;
    }
    // Oracle seems to want this step.  Should be ok for other vendors as well.
    DriverManager.registerDriver((java.sql.Driver)Class.forName(_driver).newInstance());
  }
  
  private static final String KEY_REPOS = "REPOS";
  private static final String COL_MAXID = "MAXID";
  private static final String COL_CLASSCODE = "CLASSCODE";
  private static final long MIN_USER_OID = 10000L;
  
  public String getFullReleaseString()
  {
    String strTemp = "";
    
    try 
    {
      FileInputStream fis = new FileInputStream("..\\JavaWebServer2.0\\properties\\build.properties");
      Properties p = new Properties();
      p.load(fis);

      _major = Integer.parseInt(p.getProperty("suite.major"));
      _fullReleaseString += _major;
      
      _minor = Integer.parseInt(p.getProperty("suite.minor"));
      _fullReleaseString += "." + _minor;
      
      _release = p.getProperty("suite.release");
      _fullReleaseString += "." + _release;
      
      _build = Integer.parseInt(p.getProperty("suite.build"));
      _fullReleaseString += " " + _build;
    }
    catch (IOException ioe)
    {
      ioe.printStackTrace();
    }
      
    return _fullReleaseString;
      
  }
  
//  private int parse (String s)
//  {
//    int size = s.length()-1;
//    int i = 0, j = 0, t = 1;
//    int version = 0;
//    char c;
//    
//    for (i = size; i >= 0; i--)
//    {  c = s.charAt(i);
//        j = (int)c-48;
//        version = j * t + version;
//        t *= 10;
//    }
//    return version;
//  }

  
  public int getMajorVersion() { return _major; }

  public int getMinorVersion() { return _minor; }
  
  public String getRelease() { return _release; }
  
  public int getBuild() { return _build; }


  //---------------------------------------------------------------
  // Private instance variables
  //---------------------------------------------------------------
  private IRObject      _dataRoot    = null;
  private IRObject      _modelRoot   = null;
  private IRepository   _metaRepos   = null;
  
  private IObjectContext _context = null;

  
  //---------------------------------------------------------------
  // Public constructor
  //---------------------------------------------------------------

  public SimpleRepository()
  {
    
  }

  //---------------------------------------------------------------
  // Public instance methods
  //---------------------------------------------------------------

  public void initialize() throws OculusException
  {
    try {
      _ensureReposRow();
      _setMaxOID(false);
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
  }
  
  /**
  * Sets the max OID in the database to a fixed number MIN_USER_OID.  This allows us to 
  * easily identify which objects are created by our end customers versus those created
  * as part of our dbloader canned data set.
  */
  public synchronized void setMaxOID()
    throws OculusException
  {
    _setMaxOID(true);
  }
  
  // the 'system' db connection used for system purposes
  private static Connection _conn = null;
  
  /**
  * This is the public instance method.  It funnels down to a static sync'd method to avoid
  * multi-instance clobbering.
  */
  public IIID genReposID() 
    throws OculusException
  {
    return sgenReposID();
  }
  
  /** Important method called by all object creation logic to get next available oid. */
  public static synchronized IIID sgenReposID() 
    throws OculusException
  {
    if (!_initworked) throw new OculusException("Repository init failed.  See log file.");
    IIID retVal = null;
//    IRConnection repConn = getCRM().getDatabaseConnection(getObjectContext());
    
    // Use new connection since using current IObjectContext would hold a lock
    // on the MAXCLASSID table until transaction was commited.
    Connection conn = null;
    try {
      conn = _getSysConn();
      Statement stmt = conn.createStatement();
      try {
        String query = "SELECT "+COL_MAXID+" FROM MAXCLASSID WHERE "+COL_CLASSCODE+" = '"+KEY_REPOS+"'";
        ResultSet results = stmt.executeQuery(query);
        if (!results.next())
          throw new OculusException("Could not find row for CLASSCODE='"+KEY_REPOS+"' in MAXCLASSID table.");
          
        long currmax = results.getLong(COL_MAXID);
        currmax++;
        query = "UPDATE MAXCLASSID SET "+COL_MAXID+" = " + currmax + " WHERE "+COL_CLASSCODE+" = '"+KEY_REPOS+"'";
        
        if (stmt.executeUpdate(query) != 1 )
          throw new OculusException("genReposID() failed to update 1 and only 1 row.");
        retVal = new SequentialIID(currmax);
        conn.commit();
//        conn.close();
      }
      finally {
        if (stmt != null) 
          stmt.close();
      }
    }
    catch (ORIOException sqlExp)
    {
      throw new OculusException(sqlExp);
    }
    catch(SQLException ex) {
      throw new OculusException(ex);
    }
    return retVal;
  }
  
  public IIID makeReposID(long id) throws ORIOException
  {
    return new SequentialIID(id);
  }

  public IGUID /*IObject.*/getGUID()
  {
    return _dataRoot.getGUID();
  }

  public String getName() throws ORIOException
  {
    return "productmarketing.com Repository";
  }

  public IObjectContext getObjectContext() 
  {
    return _context;
  }
  
  public IRObject getRootObject()
    throws ORIOException
  {
//    if (_dataRoot == null ) {
//      _dataRoot = new SRRootObject(this);
//      _dataRoot.load();
//    }
//    
    return _dataRoot;
  }


  public void validate(Observer o) 
  { 
  }

  public IIID getIID() 
    throws ORIOException
  {
    return _dataRoot.getIID();
  }
  
  
  public IEnvironment getEnvironment()
  {
    return _context.getEnvironment();
  }
  public ICRM getCRM()
    throws OculusException
  {
    return _context.getCRM();
  }


  public IRepository /*IRObjectContext.*/getRepository()
  {
    return this;
  }

  public IRObject getParent()
  {
    return null;
  }

  public IObject setObjectContext(IObjectContext context)
  {
    _context = context;
    return this;
  }


  public boolean isLocked() { return false; }
  public boolean isRemoveable() { return false; }
  
  public Object dolly() throws OculusException
  {
    return this; 
  }
  
  public IPoolable construct(IObjectContext context, IDataSet args)
  {
    setObjectContext(context);
    return this;
  }

  public void invalidate(){ }
  public IIID getLockHolderIID() throws ORIOException{ return null; }
  public IObjectContext setConnection(ICRMConnection conn){ return this; }
  public IRConnection getDataConnection(IObjectContext context)
    throws OculusException
  {
     return getCRM().getDatabaseConnection(context);
  }
  public ICRMConnection getConnection() { return null; }
  
	public DBVendor getVendor()
	{
		return _vendor;
	}
  
  private static final String OPER_STD_LEFTOUTERJOIN = "*=";
  private static final String OPER_ORA_LEFTOUTERJOIN = "(+)=";
  
  public String LOJO()
  {
    if(_vendor == DBVendor.ORACLE)
      return OPER_ORA_LEFTOUTERJOIN;
    else
      return OPER_STD_LEFTOUTERJOIN;  
  }
  
  //---------------------------------------------------------------
  // Package methods (implementation-specific methods)
  //---------------------------------------------------------------

  public IRBusinessModel getBMRepository() throws OculusException
  {
    return (IRBusinessModel)getCRM().getCompObject(_context,"BMR", new SequentialIID(123));
  }
  
  //---------------------------------------------------------------
  // Private methods
  //---------------------------------------------------------------


	public IXMR getXMR() 
    throws OculusException
	{
		return XMR.getInstance();
	}
  
  /** This method is a java version of the old sp_initmaxid stored procedure.  It is 
  * used to set the max object id.  If bSetToUserStart is TRUE, the max id
  * is set to MAX_USER_OID.  If FALSE, the max oid is set to 1 more than the
  * maximum oid currently in use.  The latter value is obtained by taking the
  * max of the objectid column in the AllPMCObjects view, which unions all
  * tables that have ObjectIds.
  */
  private synchronized void _setMaxOID(boolean bSetToUserStart)
    throws OculusException
  {
    Statement stmt = null;
    long lIdToUse;
    try {
      Connection conn = _getSysConn();
      try
      {
        stmt = conn.createStatement();
        try
        {
          String query = null;
          if (bSetToUserStart) {
            lIdToUse = MIN_USER_OID;
          }
          else {
            lIdToUse = _getMaxIDInUse(conn);
          }
          query = "UPDATE MAXCLASSID SET "+COL_MAXID+" = " + lIdToUse + " WHERE "+COL_CLASSCODE+" = '"+KEY_REPOS+"'";
          if (stmt.executeUpdate(query) != 1) {
            conn.rollback();
            throw new OculusException("setMaxOID() failed to update 1 and only 1 row.  Query="+query+"");
          }
          conn.commit();
        }
        finally {
          stmt.close();
        }
      }
      finally {
//        conn.close();
      }
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
  }
  
  private void _ensureReposRow()
    throws OculusException
  {
    try
    {
      Statement stmt = null;
      Connection conn = _getSysConn();
      try
      {
        stmt = conn.createStatement();
        try
        {
          String query = null;
          query = "SELECT * FROM MAXCLASSID WHERE "+COL_CLASSCODE+" = '"+KEY_REPOS+"'";
          ResultSet rs = stmt.executeQuery(query);
          if (!rs.next()) {
            query = "INSERT INTO MAXCLASSID ("+COL_CLASSCODE+", "+COL_MAXID+") VALUES ('"+KEY_REPOS+"', NULL)";
            if(stmt.executeUpdate(query)!=1) 
              throw new OculusException("Failed to initialize row in MAXCLASSID table.");
          }
          conn.commit();
        }
        finally {
          stmt.close();
        }
      }
      finally {
//        conn.close();
      }
    }
    catch (Exception ex) {
      throw new OculusException(ex);
    }
  }
  
  private long _getMaxIDInUse(Connection conn) 
    throws Exception
  {
    long retVal = 1;
    Statement stmt = null;
    stmt = conn.createStatement();
    try {
      // first see what current maxid is set to...
      String query = "SELECT MAX(OBJECTID) FROM ALLPMCOBJECTS";
      ResultSet results = stmt.executeQuery(query);
      if (results.next()) {
        retVal = results.getLong(1);
      }
    }
    finally {
      stmt.close();
    }
    return retVal;
  }
  
  private static Connection _getSysConn()
    throws SQLException
  {
    if (_conn == null ) {
      _conn = DriverManager.getConnection(_dsn, _user, _pass);
      _conn.setAutoCommit(false);
    } 
    return _conn;
  }
}