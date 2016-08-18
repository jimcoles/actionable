package com.oculussoftware.repos.bmr;

import com.oculussoftware.api.repi.*;
import com.oculussoftware.api.sysi.*;

import java.util.*;

/**  
*
*/
public class BMPropertyMap implements IRPropertyMap
{
  private Map h;
  
  public BMPropertyMap() { h = Collections.synchronizedMap(new HashMap()); }
  
  
  public void put(Object key, Object val)
  {   

   h.put(key,val); 
  }
  
  public void remove(Object key)
  {   

   h.remove(key); 
  }
  
  public Object get(Object key)
  {    
   return h.get(key);
   
  }
  
  public Collection values()
  {
    return h.values();
  } 
  
  public Set entries()
  {
    return h.entrySet();
  } 
  
  public Set keys()
  {
    return h.keySet();
  } 
  
  public boolean containsKey(Object key)
  {    
    return h.containsKey(key);
  }
  
  
/*  public void putAll(Map mp1)
  {
    
    h.putAll(mp1);
  } */
  
  public Map getMap()
  {
    return h;
  }
  
  public void putAll(IRPropertyMap mp1)
  {
    h.putAll(mp1.getMap());
  }
  
  public void putAll(IRPropertyMap t, IRObject obj) throws OculusException
  {
     
    Set keyset = t.getMap().keySet();
    Iterator itr = keyset.iterator();
    while(itr.hasNext())
    {
      String key = (String)itr.next();
      IRProperty prop = (IRProperty)t.get(key);            
      IRProperty prop1 = (IRProperty)prop.dolly();
      prop1.setOwnerObject(obj);
      h.put(key,prop1);
    }
    
  }
  
  
  public int size()
  {
    return h.size();
  }
  
}//end BMPropertyMap