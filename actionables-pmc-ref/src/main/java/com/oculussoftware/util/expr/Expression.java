package com.oculussoftware.util.expr;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;
import com.oculussoftware.repos.util.*;

import java.util.*;

/**
* Filename:    Expression.java
* Date:        2-15-2000
* Description: 
*
* Copyright 1-31-2000 Oculus Software.  All Rights Reserved.
*
* @author Egan Royal
* @version 1.2
*/
public class Expression
{
  /*
  * Change Activity
  *
  * Issue number    Programmer      Date        Description
  *
  */

  private Vector _v;
  private Enumeration _e;
  private IRObject _o;
  
  private static final String PAREN_EXC1 = "Invalid expression.  Mismatched parenthesis.";
  private static final String ATT_EXC1   = "Invalid expression.  Incorrect attribute type.";
  
  public Expression(IRObject o, String s) throws OculusException
  {
    _v = new Vector();
    _o = o;
    parse(s); 
  }//end constructor
  
  public int size()
  { return _v.size(); }
  
  public boolean hasMoreElements()
  { return _e.hasMoreElements(); }
  
  public String nextElement() 
  { return (String)_e.nextElement(); }
  
  public void reset()
  { _e = _v.elements(); }
  
  //private parts
  
  private void addElement(String s)
  { _v.addElement(s); }
  
  private void removeAllElements()
  { _v.removeAllElements(); }
  
  /**
  * populate the vector
  */
  private void parse(String s) throws OculusException
  {
    if(s.indexOf(";",0) != -1)
      s=s.substring(0,s.indexOf(";",0));
    checkParens(s);
    //s = s.toLowerCase();//shouldn't need this
    s = s.trim();
    s = filter(s);
    StringTokenizer st = new StringTokenizer(s,") (",true);
    while(st.hasMoreTokens()) 
    {
      String str = st.nextToken();
      if(str.startsWith("[") && str.endsWith("]"))
      {
        //take out the brackets
        str = str.substring(1,str.length());
        str = str.substring(0,str.length()-1);
        //get the value from the database
        IIID attiid = new SequentialIID(Long.parseLong(str));
        IObjectContext context = _o.getObjectContext();
        IRAttribute att = (IRAttribute)context.getCRM().getCompObject(context, "Attribute", attiid);
        IRPropertyMap props = _o.getProperties();
        Primitive prim = att.getDataType().getTypeKind();
        if (prim == Primitive.INTEGER || prim == Primitive.DECIMAL)
        {
          str = (String)props.get(att.getName());
          addElement(str);
        }//end if
        else
          throw new InvalidExpressionException(ATT_EXC1);
      }//end if
      if(!str.equals(" "))
        addElement(str);
    }//end while
    reset();
  }//end parse
  
  /**
  * be sure that the parenthesis are balanced
  */
  private boolean checkParens(String s) throws InvalidExpressionException
  {
    int numopen = 0, numclosed = 0;
    for(int i = 0; i < s.length(); i++)
    {
      if(s.charAt(i) == '(') numopen++;
      else if(s.charAt(i) == ')') numclosed++;
    }//end for
    if(numopen == numclosed) return true;
    else throw new InvalidExpressionException(PAREN_EXC1);
  }//end checkParens
  
  /**
  * change all whitespace to spaces.
  */
  private String filter(String s) throws InvalidExpressionException
  {
    StringBuffer sb = new StringBuffer();
    for(int i = 0; i < s.length(); i++)
    {
      if(Character.isWhitespace(s.charAt(i))) sb.append(" ");
      else sb.append(s.charAt(i));
    }//end for
    return sb.toString();
  }//end filter
}