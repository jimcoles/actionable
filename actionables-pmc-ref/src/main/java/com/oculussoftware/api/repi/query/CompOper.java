package com.oculussoftware.api.repi.query; 

import com.oculussoftware.util.IntEnum;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.OculusException;
import java.util.*;

/**
* Filename:    CompOper.java
* Date:        
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Saleem Shafi
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public final class CompOper extends IntEnum implements IOperator
{
  //------------------------------------------------------------
  // Private class vars
  //------------------------------------------------------------
  private static Map _hash = new HashMap();
  private static Map _indexByXMLCode = new HashMap();
  private static Map _opersByPrim = new HashMap();
  private static Map _primsByOper = new HashMap();
  private static Map _primArrayByOper = new HashMap();
  private static List _multiValued = new Vector();
  
  //------------------------------------------------------------
  // Public class vars (CONSTANTS)
  //------------------------------------------------------------
  public static final CompOper COMP_EQ          =     new CompOper(2  ,"is (exactly)" , "COMP_EQ", false);
  public static final CompOper COMP_NOTEQ       =     new CompOper(7  ,"not equal"    , "COMP_NOTEQ", false);
  public static final CompOper COMP_LT          =     new CompOper(3  ,"<"            , "COMP_LT", true);
  public static final CompOper COMP_GT          =     new CompOper(4  ,">"            , "COMP_GT", true);
  public static final CompOper COMP_LTEQ        =     new CompOper(5  ,"<="           , "COMP_LTEQ", true);
  public static final CompOper COMP_GTEQ        =     new CompOper(6  ,">="           , "COMP_GTEQ", true);
  // JKC 8/2/00 Use LIKE and NOTLIKE for string attributes instead of overloading CONTAINS and NOTCONTAINS.
  public static final CompOper COMP_LIKE            =     new CompOper(20 ,"contains text"        , "COMP_LIKE", false);
  public static final CompOper COMP_NOTLIKE         =     new CompOper(9  ,"doesn't contain text" , "COMP_NOTLIKE", false);
  public static final CompOper COMP_CONTAINSALL     =     new CompOper(8  ,"contains all"         , "COMP_CONTAINSALL", false);
  public static final CompOper COMP_CONTAINS/*ANY*/ =     new CompOper(10 ,"contains any"         , "COMP_CONTAINS", false);
  public static final CompOper COMP_NOTCONTAINS     =     new CompOper(11 ,"doesn't contain any"  , "COMP_NOTCONTAINS", false);
  public static final CompOper COMP_ONORAFTER   =     new CompOper(14 ,"on or after"  , "COMP_ONORAFTER", true);
  public static final CompOper COMP_ONORBEFORE  =     new CompOper(15 ,"on or before" , "COMP_ONORBEFORE", true);
  public static final CompOper COMP_ELEMENTOF   =     new CompOper(16 ,"element of"   , "COMP_ELEMENTOF", true);
  public static final CompOper COMP_NOTELEMENTOF   =     new CompOper(21 ,"not element of"   , "COMP_NOTELEMENTOF", false);
  public static final CompOper COMP_ISNULL      =     new CompOper(17 ,"is null"      , "COMP_ISNULL", false);
  public static final CompOper COMP_ISNOTNULL   =     new CompOper(18 ,"is not null"  , "COMP_ISNOTNULL", false);
  public static final CompOper COMP_TRUE        =     new CompOper(25 ,"true/yes"  , "COMP_TRUE", false);
  public static final CompOper COMP_FALSE       =     new CompOper(26 ,"false/no"  , "COMP_FALSE", false);
  public static final CompOper COMP_EXT_KEYWORD_LIKE = new CompOper(30 ,"contains keyword text", null, false);
  // COMP_EQJOIN is not for public consumption.  Its used by query translator to create the 'join'
  // portion of a filter expression.  Needed since Oracle does not support 'LEFT OUTER JOIN' operator
  // in the FROM clause.
  public static final CompOper COMP_EQLEFTOUTERJOIN   =     new CompOper(100 ,"left outer join (not user selectable)", "COMP_EQLEFTOUTERJOIN", false);

  //
  // JKC 8/2/00 Moved this logic here to get control of the order of operators for a given type.
  // Statically declare the associations of operators to primitive types.
  // 
  // NOTE: These should match with Table 2 of the 'Socrates EDS - Search.doc' document.
  //
  static {
    Primitive prim = null;
    
    prim = Primitive.LONG_CHAR;
    _addPrimOperAsc(prim, COMP_LIKE, false);
    _addPrimOperAsc(prim, COMP_NOTLIKE, false);
    
    prim = Primitive.CHAR;
    _addPrimOperAsc(prim, COMP_LIKE, false);
    _addPrimOperAsc(prim, COMP_NOTLIKE, false);
    _addPrimOperAsc(prim, COMP_EQ, false);
    _addPrimOperAsc(prim, COMP_NOTEQ, false);
    
    prim = Primitive.ENUM;
    _addPrimOperAsc(prim, COMP_EQ, false);
    _addPrimOperAsc(prim, COMP_NOTEQ, false);
    _addPrimOperAsc(prim, COMP_ELEMENTOF, true);
    _addPrimOperAsc(prim, COMP_NOTELEMENTOF, true);
    _addPrimOperAsc(prim, COMP_GTEQ, false);
    _addPrimOperAsc(prim, COMP_LTEQ, false);
    
    prim = Primitive.RADIO;
    _addPrimOperAsc(prim, COMP_EQ, false);
    _addPrimOperAsc(prim, COMP_NOTEQ, false);
    _addPrimOperAsc(prim, COMP_ELEMENTOF, true);
    _addPrimOperAsc(prim, COMP_NOTELEMENTOF, true);
    _addPrimOperAsc(prim, COMP_GTEQ, false);
    _addPrimOperAsc(prim, COMP_LTEQ, false);
    
    prim = Primitive.MULTIENUM;
    _addPrimOperAsc(prim, COMP_EQ, true);
    _addPrimOperAsc(prim, COMP_NOTEQ, true);
    _addPrimOperAsc(prim, COMP_CONTAINS, true);
    _addPrimOperAsc(prim, COMP_NOTCONTAINS, true);
    _addPrimOperAsc(prim, COMP_CONTAINSALL, true);
    _addPrimOperAsc(prim, COMP_GTEQ, true);
    _addPrimOperAsc(prim, COMP_LTEQ, true);
    
    prim = Primitive.MULTICHECK;
    _addPrimOperAsc(prim, COMP_EQ, true);
    _addPrimOperAsc(prim, COMP_NOTEQ, true);
    _addPrimOperAsc(prim, COMP_CONTAINS, true);
    _addPrimOperAsc(prim, COMP_NOTCONTAINS, true);
    _addPrimOperAsc(prim, COMP_CONTAINSALL, true);
    _addPrimOperAsc(prim, COMP_GTEQ, true);
    _addPrimOperAsc(prim, COMP_LTEQ, true);
    
    prim = Primitive.INTEGER;
    _addPrimOperAsc(prim, COMP_EQ, false);
    _addPrimOperAsc(prim, COMP_NOTEQ, false);
    _addPrimOperAsc(prim, COMP_GT, false);
    _addPrimOperAsc(prim, COMP_LT, false);
    _addPrimOperAsc(prim, COMP_GTEQ, false);
    _addPrimOperAsc(prim, COMP_LTEQ, false);
    
    prim = Primitive.DECIMAL;
    _addPrimOperAsc(prim, COMP_NOTEQ, false);
    _addPrimOperAsc(prim, COMP_GT, false);
    _addPrimOperAsc(prim, COMP_LT, false);
    _addPrimOperAsc(prim, COMP_GTEQ, false);
    _addPrimOperAsc(prim, COMP_LTEQ, false);
    
    prim = Primitive.TIME;
    _addPrimOperAsc(prim, COMP_EQ, false);
    _addPrimOperAsc(prim, COMP_NOTEQ, false);
    _addPrimOperAsc(prim, COMP_ONORAFTER, false);
    _addPrimOperAsc(prim, COMP_ONORBEFORE, false);
    
    prim = Primitive.BOOLEAN;
    _addPrimOperAsc(prim, COMP_TRUE, false);
    _addPrimOperAsc(prim, COMP_FALSE, false);
  }

  public static boolean isOperandMulti(Primitive leftSide, CompOper oper)
    throws OculusException
  {
    return _multiValued.contains(_primOperKey(leftSide, oper));
  }
    
  private static void _addPrimOperAsc(Primitive prim, CompOper oper, boolean isOperandMulti)
  {
    List alist = null;
    // do operators by prim
    alist = (List) _opersByPrim.get(prim);
    if (alist == null) {
      alist = new Vector();
      _opersByPrim.put(prim, alist);
    }
    alist.add(oper);
    
    // do prims by oper
    alist = null;
    alist = (List) _primsByOper.get(oper);
    if (alist == null) {
      alist = new Vector();
      _primsByOper.put(oper, alist);
    }
    alist.add(prim);
    
    // bulid the 'isMulti' list
    String prim_oper_key = null;
    try {
      prim_oper_key = _primOperKey(prim, oper);
    } catch (OculusException ignore) { }  // assertion: key cannot be invalid in a private static method
    
    if (isOperandMulti)
      _multiValued.add(prim_oper_key);
    
  }
  
  private static String _primOperKey(Primitive prim, CompOper oper)
    throws OculusException
  {
    if (prim == null | oper == null)
      throw new OculusException("Null args");
      
    return prim.getIntValue() + "_" + oper.getIntValue();
  }
  
  //------------------------------------------------------------
  // Private instance vars
  //------------------------------------------------------------
  private String  _strval = null;
  private String  _xmlcode = null;
  private boolean _isInequality = false;
  
  //------------------------------------------------------------
  // Constructor(s)
  //------------------------------------------------------------
  /** Private constructor */
  private CompOper(int s, String stringvalue, String xmlcode, boolean isInequality) 
  {
    super(s);
    _strval = stringvalue;
    _xmlcode = xmlcode;
    _isInequality = isInequality;
    
    _hash.put(new Integer(s), this);
    _indexByXMLCode.put(xmlcode, this);
  }
  
  //------------------------------------------------------------
  // Public instance methods
  //------------------------------------------------------------
  public String getDisplayString()
  { return _strval; }
  
  public List getValidPrimList()
  {
    return (List) _primsByOper.get(this);
  }
  
  public Primitive[] getValidPrimitives()
  {  
    Primitive[] aprims = null;
    // build array the first time its ask for, but afterward just get from hash...
    if ((aprims = (Primitive[]) _primArrayByOper.get(this)) == null ) 
    {
      List lprims = getValidPrimList();
      if (lprims != null) {
        aprims = new Primitive[lprims.size()];
        for(int i = 0; i < aprims.length; i++)
          aprims[i] = (Primitive) lprims.get(i);  
      }
      else {
        aprims = new Primitive[0];
      }  
      _primArrayByOper.put(this, aprims);  
    }
    return aprims; 
  }
  
  public String getXMLCode() { return _xmlcode; }
  public boolean isInequality() { return _isInequality; }
  
  //------------------------------------------------------------
  // Public class methods
  //------------------------------------------------------------
  public static Iterator getCompOpers()
  {
    return _hash.values().iterator();
  }
	
	public static CompOper getInstance(String type)
		throws ORIOException
	{
		int intType = Integer.parseInt(type);
		return getInstance(intType);		
	}
	
	public static CompOper getInstance(int i)
		throws ORIOException
	{
		CompOper retVal = null;
		try {
		  retVal = (CompOper) _hash.get(new Integer(i));
		}
		catch (ClassCastException ex) {
		  throw new ORIOException(ex);
		}
		if (retVal == null) {
		  throw new ORIOException("Invalid id.");
		}
		return retVal;
	}  
  
  public static String getStringValue(CompOper co) 
  throws OculusException
  {
    return co.getXMLCode();  
  }
  
  public static CompOper getXMLInstance(String str) throws OculusException
  {
    CompOper retObj = null;
    
    retObj = (CompOper) _indexByXMLCode.get(str);
    
    if (retObj == null)
      throw new OculusException("Invalid XML code = '"+str+"' for Comparison Operator (CompOper).");
    
    return retObj;
  }//end getInstance  

  public static List getValidCompOperList(Primitive p)
  {
    return (List) _opersByPrim.get(p);
  }
  public static CompOper[] getValidCompOpers(Primitive p)
  {
    if(p == null)
      return null;

    CompOper[] ops = null;
    List lopers = getValidCompOperList(p);  
    if (lopers != null) {
      ops = new CompOper[lopers.size()];
      for(int i = 0; i < ops.length; i++)
        ops[i] = (CompOper)lopers.get(i);  
    }
    else {
      ops = new CompOper[0];
    }  
    return ops; 
  }  
  
}