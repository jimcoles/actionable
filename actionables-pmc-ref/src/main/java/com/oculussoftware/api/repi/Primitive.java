package com.oculussoftware.api.repi;

import com.oculussoftware.util.*;
import com.oculussoftware.api.sysi.OculusException;

import java.util.*;

/** An  enumeration.
*/
public class Primitive extends IntEnum
{
  //------------------------------------------------------------------
  // Static variables
  //------------------------------------------------------------------
  private static Map _itemsByInt = new HashMap();
  private static Map _itemsByCode = new HashMap();
  
  public static final Primitive CHAR      = new Primitive(1, "Short Text", "CHAR");
  public static final Primitive LONG_CHAR = new Primitive(2, "Long Text", "LONG_CHAR");
  public static final Primitive PASSWORD  = new Primitive(3, "Password", "PASSWORD");

  public static final Primitive INTEGER   = new Primitive(4, "Integer", "INTEGER");
  public static final Primitive DECIMAL   = new Primitive(5, "Decimal", "DECIMAL");

  public static final Primitive TIME      = new Primitive(6, "Date", "TIME");
  public static final Primitive BOOLEAN   = new Primitive(7, "Yes/No", "BOOLEAN");
  public static final Primitive BLOB      = new Primitive(8, "Binary Data", "BLOB");
  public static final Primitive ENUM      = new Primitive(9, "List Box (Single Select)", "ENUM");
  public static final Primitive RADIO      = new Primitive(11, "Option (Radio) Buttons ", "RADIO");
  public static final Primitive MULTIENUM  = new Primitive(12, "List Box (Multi Select)", "MULTIENUM");
  public static final Primitive MULTICHECK = new Primitive(13, "Check Box (Multi Select)", "MULTICHECK");
  //Patrick wants to be able treat link and files as attribute on Article inputs.
  public static final Primitive LINK = new Primitive(14, "Hyperlink", "LINK");

  //------------------------------------------------------------------
  // Static methods
  //------------------------------------------------------------------
  public static Primitive getInstance(int i)
  {
    return (Primitive) _itemsByInt.get(new Integer(i));
  }

  public static String getStringValue(Primitive so) throws OculusException
  {
    return so.getXMLCode();
  }
  
  public static Primitive getXMLInstance(String str) throws OculusException
  {
    return (Primitive) _itemsByCode.get(str);
  }
  
  public static Collection getAllPrims()
  {
    return _itemsByInt.values();
  }
  
  //------------------------------------------------------------------
  // Private instance variables
  //------------------------------------------------------------------
  private String _displayName = null;
  private String _code = null;
  
  //------------------------------------------------------------------
  // Constructor(s)
  //------------------------------------------------------------------
  private Primitive(int i, String displayName, String code)
  {
    super(i);
    _displayName = displayName;
    _code = code;
    _itemsByInt.put(new Integer(i), this);
    _itemsByCode.put(code, this);
  }    

  /**
  We need a toString() so that Primitive types can be displayed to the user
  (case in point: viewing attribute meta data thru a servlet)  

  Alok  
  */

  //------------------------------------------------------------------
  // Public instance methods
  //------------------------------------------------------------------
  public String toString()
  {
    String s = null;
    int i = getIntValue();
    switch (i)
    {
    } 
    return s;
  }
  
  public String getDisplayName() { return _displayName; }
  public String getXMLCode() { return _code; }
  public boolean isMultiValued() 
  { 
    return (this == MULTICHECK || this == MULTIENUM);
  }

  public boolean isEnum()
  {
    return (this == MULTICHECK || this == MULTIENUM ||
            this == ENUM  || this == RADIO);
  }  
}