package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

  public interface IRUserGroup extends IRAccessor
{
    public IRUserGroup getParentGroup() throws OculusException;
    public IRUserGroup setParentGroup(IRUserGroup grp);

  public static final String TABLE_NAME="USERGROUP";  
  public static final String TABLE_ASC="USERGROUPASC";  
  
  public static final String COL_OBJECTID="OBJECTID";  
  public static final String COL_BYTEGUID="GUID";  
  public static final String COL_PARPACK="PARGROUPID";  
  public static final String COL_DELETEKIND="DELETEKIND";    
  public static final String COL_ISACTIVE="ISACTIVE";    
  public static final String COL_NAME="NAME";  
  public static final String COL_DESC="DESCRIPTION";    
  public static final String COL_USERID="USERID";  
  public static final String COL_GROUPID="GROUPID";  
  
  //Methods that access the USERGROUPASC TABLE
  
  public void addUser(IRUser use) throws ORIOException;
  public void removeUser(IRUser use) throws ORIOException;
  

}