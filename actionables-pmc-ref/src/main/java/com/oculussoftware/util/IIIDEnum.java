package com.oculussoftware.util;

import com.oculussoftware.api.repi.IIID;
import com.oculussoftware.repos.util.SequentialIID;

/** Base class for type-safe ints
*/
public class IIIDEnum
{
  private IIID _safeiid;
  
  protected IIIDEnum(IIID s)
  {
    _safeiid = s;
  }

  protected IIIDEnum(long s)
  {
    _safeiid = new SequentialIID(s); 
  }
  
  public long getLongValue()
  {
    return _safeiid.getLongValue();
  }
  
  public IIID getIIDValue()
  {
    return _safeiid; 
  }
  
  public boolean equals(Object obj)
  {
    if(obj instanceof IIIDEnum)
      return (((IIIDEnum) obj).getLongValue() == _safeiid.getLongValue());
    else if(obj instanceof IIID)
      return (((IIID) obj).getLongValue() == _safeiid.getLongValue());
    else
      return false;
  }
  
  public String toString()
  {
    return getIIDValue().toString();
  }
}