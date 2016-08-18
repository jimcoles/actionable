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

import org.jkcsoft.bogey.metamodel.Guid;

import java.util.Enumeration;
import java.util.Vector;

/**
 * An access control permission entity.  Maintains a static hash of all
 * Permissions by registering them at constuctor time.
 */
public abstract class Permission {
    //-----------------------------------------------------------------------------
    // Public constants
    //-----------------------------------------------------------------------------

    //-----------------------------------------------------------------------------
    // Private class variables
    //-----------------------------------------------------------------------------
    private static PermissionSet _permHash = PermissionSet.getInstance();
    private static Vector _dups = new Vector();

    //-----------------------------------------------------------------------------
    // Public class methods
    //-----------------------------------------------------------------------------
    public static void printDups() {
        System.out.println("Permission num duplicates: " + _dups.size());
        Enumeration edups = _dups.elements();
        while (edups.hasMoreElements()) {
            Permission dup = (Permission) edups.nextElement();
            System.out.println("ERROR: Look for duplicat Permission id: " + dup.getID());
        }
    }


    //-----------------------------------------------------------------------------
    // Private instance variables
    //-----------------------------------------------------------------------------
    private int _id;
    private Guid _guid = null;
    private String _name = null;
    private String _shortDesc = null;
    private boolean _isRecur = false;

    //-----------------------------------------------------------------------------
    // Private constructor
    //-----------------------------------------------------------------------------
    public Permission(int id, Guid guid, String name, String desc, boolean isRecur) {
        _id = id;
        _guid = guid;
        _name = name;
        _shortDesc = desc;
        _isRecur = isRecur;
        Object old = _permHash.put(id, this);
        if (old != null)
            _dups.add(old);
    }

    //-----------------------------------------------------------------------------
    // Public instance methods
    //-----------------------------------------------------------------------------
    public String toString() {
        return Integer.toString(_id);
    }

    public int getID() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _shortDesc;
    }

    public boolean getIsRecursive() {
        return _isRecur;
    }
}