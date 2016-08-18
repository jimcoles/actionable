/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.model;

/**
 * This is an enumeration of values that describe the active-ness of a User.
 * The ACTIVE value represents an active user who can log-in to the system and
 * function normally.  The INACTIVE value represents a user that is not deleted
 * from the system, but is still not able to log in.
 */
public enum ActiveKind {
    /**
     * the value (1) representing an active user
     */
    ACTIVE,
    /**
     * the value (2) representing an inactive user
     */
    INACTIVE
}
