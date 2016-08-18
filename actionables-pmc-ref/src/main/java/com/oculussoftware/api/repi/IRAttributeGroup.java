/****
The interface to group similar attributes or attributes that co-exist
as a logical/business group. For example Zip code makes sense
with the U.S. State attribute.Therefore Zip Code and U.S. state form a 
group. The user is supposed to define these groups at configuration time
 
*/

package com.oculussoftware.api.repi;

import com.oculussoftware.api.sysi.*;

public interface IRAttributeGroup extends IRModelElement
{
  public IRPropertyMap getAttributeMap(AttributeKind ak)
    throws ORIOException;
 
 	public IRAttributeGroup setPackage(IRPackage id);
 	public IRPackage getPackage();
 	
   public boolean isLeaf();
   public IRAttributeGroup isLeaf(boolean bln);
   
   public boolean isRoot();
   public IRAttributeGroup isRoot(boolean bln);
   
   public boolean isAbstract();
   public IRAttributeGroup isAbstract(boolean bln);
   
   
  public static final String TABLE_NAME="INTERFACE";  
  
	public static final String COL_OBJECTID="OBJECTID";  
	public static final String COL_BYTEGUID="GUID";    
	public static final String COL_PACK="PACKAGEID";    
  public static final String COL_DELETEKIND="DELETEKIND";  
  public static final String COL_ISACTIVE="ISACTIVE";  
  public static final String COL_ISROOT="ISROOT";  
  public static final String COL_ISLEAF="ISLEAF";  
  public static final String COL_ISABSTRACT="ISABSTRACT";  
	public static final String COL_NAME="NAME";  
	public static final String COL_DESC="DESCRIPTION";  
	  

}