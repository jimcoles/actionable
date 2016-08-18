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

import org.jkcsoft.bogey.metamodel.Oid;

/**
 *
 */
public class PermissionGrant {
    //-----------------------------------------------------------------------------
    // Private instance variables
    //-----------------------------------------------------------------------------
    private Oid _parObjectId = null;
    private Permission _perm = null;
    private Oid _accessorId = null;
    private boolean _isFixed = false;

    //-----------------------------------------------------------------------------
    // Public constructor(s)
    //-----------------------------------------------------------------------------
    public PermissionGrant(Oid contextObjId, Permission perm, Oid accessorId, boolean isFixed) {
        _parObjectId = contextObjId;
        _perm = perm;
        _accessorId = accessorId;
        _isFixed = isFixed;
    }

    //-----------------------------------------------------------------------------
    // Public instance methods
    //-----------------------------------------------------------------------------

    public boolean isFixed() {
        return _isFixed;
    }

    public Permission getPermission() {
        return _perm;
    }

    public Oid getContextObjID() {
        return _parObjectId;
    }

    public Oid getAccessorID() {
        return _accessorId;
    }

    //-----------------------------------------------------------------------------
    // Private instance methods
    //-----------------------------------------------------------------------------
}