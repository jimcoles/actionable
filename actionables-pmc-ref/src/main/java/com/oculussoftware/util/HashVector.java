package com.oculussoftware.util;

import java.util.*;

public class HashVector implements ListMap
{

  private List _list;
  private Map _map;
  
  public HashVector()
  {
    clear();
  }

  public Map map() { return _map; }
  // Map methods
  public boolean containsKey(Object key) { return _map.containsKey(key); }
  public boolean containsValue(Object value) { return _map.containsValue(value); }
  public Set entrySet() { return _map.entrySet(); }
  public Object get(Object key) { return _map.get(key); }
  public Set keySet() { return _map.keySet(); }
  public Collection values() { return _map.values(); }


  // List methods
  public boolean containsAll(Collection c) { return _list.containsAll(c); }
  public Object get(int index) { return _list.get(index); }
  public int indexOf(Object o) { return _list.indexOf(o); }
  public Iterator iterator() { return _list.iterator(); }
  public int lastIndexOf(Object o) { return _list.lastIndexOf(o); }
  public ListIterator listIterator() { return _list.listIterator(); }
  public ListIterator listIterator(int index) { return _list.listIterator(index); }
  public List subList(int fromIndex, int toIndex) { return _list.subList(fromIndex,toIndex); }
  public Object[] toArray() { return _list.toArray(); }
  public Object[] toArray(Object[] a) { return _list.toArray(a); }
  
  // Common methods
  
  public void add(int index, Object element)
  {
    _list.add(index,element);
    _map.put(element,element);
  }
  
  public boolean add(Object o)
  {
    _map.put(o,o);
    return _list.add(o);
  }
  
  public void clear()
  {
    _list = new Vector();
    _map = new HashMap();
  }
  
  public boolean equals(Object o)
  {
    return false;  // FIXME
  }
  
  public int hashCode()
  {
    return _list.hashCode();
  }
  
  public boolean isEmpty()
  {
    return _list.isEmpty();
  }
  
  public Object put(Object key, Object value)
  {
    _list.add(value);
    return _map.put(key,value);
  }
  
  public void putAll(Map t)
  {
    _map.putAll(t);
    _list.addAll(t.values());
  }

  public void putAll(ListMap t)
  {
    _map.putAll(t.map());
    for (Iterator i = t.iterator(); i.hasNext(); )
      _list.add(i.next());
  }

  public Object remove(Object key)
  {
    _list.remove(_list.indexOf(_map.get(key)));
    return _map.remove(key);
  }
  
  public Object remove(int index)
  {
    _map.remove(_list.get(index));
    return _list.remove(index);
  }
  
  public int size()
  {
    return _list.size();
  }
}