package com.oculussoftware.repos.xml;

import java.sql.*;
import java.util.*;
import com.oculussoftware.api.repi.xml.*;
import com.oculussoftware.api.repi.*;
import com.oculussoftware.util.*;
import org.w3c.dom.*;
import com.oculussoftware.api.repi.query.*;
import com.oculussoftware.api.sysi.OculusException;
/**
* Filename:    QObjectKey.java
* Date:        7/18/00
* Description: 
*
* Copyright 1-31-2000 ProductMarketing.com.  All Rights Reserved.
*
* @author Zain Nemazie
* @version 1.2
*/

/*
* Change Activity
*
* Issue number  	Programmer    	Date      	Description
*/

public class QObjectKey implements IQObjectKey
{
  private Primitive prim;
  //possible type of obj are String, Long, Array, Timestamp
  private Object obj;
  
  public QObjectKey(Primitive prim, Object obj)
  {
    this.prim = prim;
    this.obj = obj;
  }
  
  public QObjectKey(Node nd)
  throws OculusException
  {
    NamedNodeMap nm = nd.getAttributes();
    prim = Primitive.getXMLInstance(nm.getNamedItem("Primitive").getNodeValue());
    String str = nm.getNamedItem("Value").getNodeValue();
    try
    {
      if (prim==Primitive.ENUM)
      {
        obj = StringUtil.getLongArray(str);
      }
      else if (prim == Primitive.INTEGER)
      {
        obj = new Long(Long.parseLong(str));
      }
      else if (prim == Primitive.TIME)
      {
        obj = new Timestamp(Long.parseLong(str));
      }
      else //must be a string
      {
        obj = str;
      }
    }
    catch (Exception e)
    {
      throw new OculusException(e);
    }
  }
  

    
  public Primitive getPrimitive()
  {
    return prim;
  }
  
  public Object getValues()
  {
    return obj;
  }
  

  
  public Element toXML(Document doc)
  throws OculusException
  {
    Element ndIQ = doc.createElement("IQObjectKey");    
    ndIQ.setAttribute("Primitive", Primitive.getStringValue(prim));
    ndIQ.setAttribute("Value", ObjToString());
    return ndIQ;
  }
  
  private String ObjToString()
  throws OculusException
  {
    if (prim == Primitive.TIME)
    { 
      return ((Timestamp)obj).getTime()+"";
    }
    else if (prim == Primitive.CHAR)
    {
      return (String) obj;
    }
    else if (prim == Primitive.ENUM)
    {
      return StringUtil.getArray2CSV((long[])obj);
    }    
    else if (prim == Primitive.INTEGER)
    {
      return obj + "";
    }
    else 
    {
      throw new OculusException("Object not of an acceptable primitive type");
    }  
  }
}