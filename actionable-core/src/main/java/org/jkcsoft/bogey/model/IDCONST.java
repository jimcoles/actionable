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

import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.system.AppException;

import java.util.*;

/**
 * Enumerates all meta objects that the system requires to exist.
 * Provides a consistent means of programmatically identifying
 * the meta objects, e.g., Entity Classes, States, Attributes.
 *
 * TODO: Rename this class
 */
public class IDCONST {

    public static final Oid OCUREPOS = null;
    public static final Oid REPOSITORY = null;
    public static final Oid USER = null;
    public static final Oid GROUP = null;
    public static final Oid USER_SYSTEM = null;

    //------------------------------------------------------------
    // Private static vars
    //------------------------------------------------------------
    private static Map _hash = new HashMap(400);
    private static Vector _dups = new Vector();

    //------------------------------------------------------------
    // Private instance variables
    //------------------------------------------------------------
    private long id;
    private String _dirName = null;

    //------------------------------------------------------------
    // Private constructor(s)
    //------------------------------------------------------------

    private IDCONST(long i) {
        this.id = i;
        // keep track of all IDCONSTs via hash...
        Object old = _hash.put(new Long(i), this);
        if (old != null)
            _dups.add(old);
    }

    private IDCONST(long i, String dirName) {
        this(i);
        _dirName = dirName;
    }

    //------------------------------------------------------------
    // Public instance methods
    //------------------------------------------------------------
    public long getIIDValue() {
        return id;
    }
    public String getDirName() {
        return _dirName;
    }

    public String toString() {
        return "" + getIIDValue();
    }

    //------------------------------------------------------------
    // Public static methods
    //------------------------------------------------------------

    public static Vector getDups() {
        return _dups;
    }

    public static List getSortedKeys() {
        List sortedKeys = Arrays.asList(_hash.keySet().toArray());
        Collections.sort(sortedKeys);
        return sortedKeys;
    }

    /**
     * Returns all gaps in terms of a list of Long[2].
     */
    public static List getGaps() {
        Long pair[] = null;
        List retList = new Vector();
        List sortedKeys = getSortedKeys();

        Iterator ikeys = sortedKeys.iterator();
        Long prevKey = null;
        while (ikeys.hasNext()) {
            Long Lkey = (Long) ikeys.next();
            if (prevKey != null) {
                long lprev = prevKey.longValue();
                long lkey = Lkey.longValue();
                if ((lkey - lprev) > 1) {
                    pair = new Long[2];
                    pair[0] = new Long(lprev + 1);
                    pair[1] = new Long(lkey - 1);
                    retList.add(pair);
                }
            }
            prevKey = Lkey;
        }
        return retList;
    }

    /**
     * Decodes long id to valid IDCONST.
     * NOTE: do we really need to throw exception?
     */
    public static IDCONST getInstance(long i) throws AppException {
        IDCONST retVal = null;
        try {
            retVal = (IDCONST) _hash.get(new Long(i));
        } catch (ClassCastException ex) {
            throw new AppException(ex);
        }
        if (retVal == null) {
            throw new AppException("Invalid long value for IDCONST (" + i + ")");
        }
        return retVal;
    }

    /**
     * Overload
     */
    public static IDCONST getInstance(Oid id) throws AppException {
        return getInstance(id.getLongValue());
    }

}