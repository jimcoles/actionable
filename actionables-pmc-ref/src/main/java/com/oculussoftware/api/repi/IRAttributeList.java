package com.oculussoftware.api.repi;

public interface IRAttributeList extends IRList
{
  public  IRList getFullList(AttributeKind ak) throws ORIOException;
  
}