package com.oculussoftware.util;

import java.util.*;

public interface ListMap
{
  public Map map();
  // Map methods
  public boolean containsKey(Object key);
  public boolean containsValue(Object value);
  public Set entrySet();
  public Object get(Object key);
  public Set keySet();
  public Collection values();
  
  // List methods
  public boolean containsAll(Collection c);
  public Object get(int index);
  public int indexOf(Object o);
  public Iterator iterator();
  public int lastIndexOf(Object o);
  public ListIterator listIterator();
  public ListIterator listIterator(int index);
  public List subList(int fromIndex, int toIndex);
  public Object[] toArray();
  public Object[] toArray(Object[] a);
  
  // Common methods
  public void add(int index, Object element);
  public boolean add(Object o);
  public void clear();
  public boolean equals(Object o);
  public int hashCode();
  public boolean isEmpty();
  public Object put(Object key, Object value);
  public void putAll(Map t);
  public void putAll(ListMap t);
  public Object remove(Object key);
  public Object remove(int index);
  public int size();
}