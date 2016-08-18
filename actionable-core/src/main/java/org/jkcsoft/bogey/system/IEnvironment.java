/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.system;

/** This interface was intended to describe the environment in which the application
* was running.  There currently is obsolete and has no implementation.  The only reason
* it hasn't been deleted is that other interfaces had planned on having references to
* an object of this type and have accessor and mutators for this interface.
*
* @author Jim Coles
*/
public interface IEnvironment
{
  public void getTempDir();
  public void getCurrentWorkingDir();
  public void setCurrentWorkingDir();
}