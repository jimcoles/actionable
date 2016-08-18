package com.oculussoftware.api.repi.xmeta;

import com.oculussoftware.system.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.busi.*;
import com.oculussoftware.util.*;

import java.util.*;

/*
* $Workfile: IXMR.java $
* Create Date:  5-12-2000
* Description: Makes up for shortcomming in the BMR and other meta objects.
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
*/

/*
* $History: IXMR.java $
 * 
 * *****************  Version 12  *****************
 * User: Jcoles       Date: 8/30/00    Time: 9:17a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added convenience methods for getting assoc info.
 * 
 * *****************  Version 11  *****************
 * User: Sshafi       Date: 7/27/00    Time: 9:22a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 
 * *****************  Version 10  *****************
 * User: Sshafi       Date: 7/27/00    Time: 9:14a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 
 * *****************  Version 9  *****************
 * User: Sshafi       Date: 7/26/00    Time: 1:06p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * 
 * *****************  Version 8  *****************
 * User: Jcoles       Date: 7/22/00    Time: 1:37p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added  public List getKeywordAttrs( ).
 * 
 * *****************  Version 7  *****************
 * User: Jcoles       Date: 7/21/00    Time: 5:22p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added getEndClass().
 * 
 * *****************  Version 6  *****************
 * User: Jcoles       Date: 7/20/00    Time: 8:46a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added getX...( ) methods.  Chainged return of getQueryAssocChains( ) to
 * List.
 * 
 * *****************  Version 5  *****************
 * User: Jcoles       Date: 7/19/00    Time: 6:19p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added exceptoin throw to getAssoc.  Added method getQueryAssocChain( )
 * thought not yet implemented in concrete class.
 * 
 * *****************  Version 4  *****************
 * User: Jcoles       Date: 7/18/00    Time: 8:57a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Changed getAttr( ) etal signatures.
 * 
 * *****************  Version 3  *****************
 * User: Jcoles       Date: 7/16/00    Time: 11:26a
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added mapPrimToTable( ) as a way of determining which table to join in
 * for an extended attr.
 * 
 * *****************  Version 2  *****************
 * User: Jcoles       Date: 7/13/00    Time: 7:26p
 * Updated in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
 * Added throws clauses.
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:16a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/xmeta
*/

/**
* The purpose of an IXMR and releted meta objects is to provide a full graph of classes
* and associations and provide the oo to relational mapping required to build SQL
* queries.
*/
public interface IXMR
{
  /** Gets an alphabetal superset of all attributes. */
  public List getAttrDisplayList(IObjectContext context, IXClass xcls, IXAssocChain chain)
    throws OculusException;
  
  public IXClassAttr getAttr(IObjectContext context, IXClass cls, long id)
    throws OculusException;
  public IQAttrRef getAttrRef(IObjectContext context, IXClass start, List chain, long id)
    throws OculusException;
  public IQAttrRef getAttrRef(IObjectContext context, IXClass start, List chain, IXClassAttr attr)
    throws OculusException;
  public IXClass getClass(long id)
    throws OculusException;
    
  public IXAssoc getAssoc(IObjectContext context, long id)
    throws OculusException;
  
  /** Retrieves all assoc chains that must be traversable for a user-defined query. */
  public List getQueryAssocChains(IObjectContext context, IXClass cls)
    throws OculusException;
    
  /** Retrieves all IQAttrRefs corresponding to keyword searchable fields for a class. */  
  public List getKeywordAttrs(IObjectContext context, IXClass cls)
    throws OculusException;

  public List getKeywordAssocChains(IXClass start)
    throws OculusException;
    
  public List getClassAssocs(IXClass cls)
    throws OculusException;
  public List getAssocsFrom(IXClass cls)
    throws OculusException;
  public List getAssocsTo(IXClass cls)
    throws OculusException;
  
  public Collection getXClasses();  
  public Collection getXAssocs();  
  public Collection getXAttrs();  
    
	public IXClass getOtherEnd(IXAssoc ass, IXClass end)
	  throws OculusException;
  
  public IXClass getEndClass(IXClass start, List chain)
	  throws OculusException;
  
	public String mapPrimToTable(Primitive prim);
}