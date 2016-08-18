package com.oculussoftware.api.repi;

import com.oculussoftware.system.*;

/** UML 'Generalizable Element'
*/
public interface IRGenElement extends IRModelElement
{
  public boolean isRoot();
  public boolean isLeaf();
  public boolean isAbstract();
}