package com.oculussoftware.system.sec;


import com.oculussoftware.api.repi.*;
import com.oculussoftware.repos.util.*;
import com.oculussoftware.api.sysi.sec.*;
import java.util.*;
public class AttrGroupGrantAsc 

{
 
 IIID _groupIID = null;
 IIID _accIID = null;
 AttrGroupOper _oper = null;
 long _acclong;
  
 public void setAttrGroup(IIID id){_groupIID = id;} 
 public void setAccessor(IIID id){_accIID = id;} 
 public void setAccessor(long id){_acclong = id; _accIID = new SequentialIID(id);} 
 public void setOperation(AttrGroupOper groupoper) {_oper = groupoper;} 
  
 public IIID getAttrGroup() { return _groupIID;} 
 public IIID getAccessor()  { return _accIID;} 
 public long getAccessorLng()  { return _acclong;} 
 public AttrGroupOper getOperation()  { return _oper;} 
  
 public static AttrGroupGrantAsc findAsc(List list, IIID grpID)
  {
   
   int size = list.size();
   for(int i =0; i < size; ++i)
    {
      AttrGroupGrantAsc asc =  (AttrGroupGrantAsc)list.get(i);
      if (asc.getAttrGroup().getLongValue() == grpID.getLongValue())
        return asc;
    }  
   return null; 
  }
 
  
}