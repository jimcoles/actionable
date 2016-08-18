/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.system.security;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class PermissionSet {
    //
    private static final int EST_NUM_PERMS = 100;
    // singleton reference...
    private static PermissionSet _instance = new PermissionSet();
    //
    public static Map _set = new HashMap(EST_NUM_PERMS);

    // singleton method...
    public static PermissionSet getInstance() {
        return _instance;
    }

    // private constructor to support singleton.
    private PermissionSet() {
    }

    public Permission getPermForId(int id) {
        Permission retVal = null;

        retVal = (Permission) _set.get(new Integer(id));

        return retVal;
    }

    protected Object put(int ikey, Permission value) {
        return _set.put(new Integer(ikey), value);
    }
}