package com.oculussoftware.api.repi.query;

/*
* $Workfile: IQAttrRef.java $
* Create Date: 6/26/2000
* Description: 
*
* Copyright 7/01/2000 Oculus Software.  All Rights Reserved.
*
* Author J. Coles
* Version 1.2
*
* $History: IQAttrRef.java $
 * 
 * *****************  Version 1  *****************
 * User: Jcoles       Date: 7/13/00    Time: 11:17a
 * Created in $/Project - Socrates/DevSource1.2/Framework.Java/com/oculussoftware/api/repi/query
*/

import com.oculussoftware.api.sysi.*;
import com.oculussoftware.api.repi.xmeta.*;

import java.util.*;
import java.io.*;


/**
* Represents a fully qualified reference from an object of a given class
* to a single attribute of an associated class.
*/
public interface IQAttrRef
{
  public IXAssocChain getAssocs();
  public IXClassAttr getAttr();
}