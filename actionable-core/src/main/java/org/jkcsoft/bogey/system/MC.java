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

import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.BusinessObject;
import org.jkcsoft.bogey.model.IDCONST;
import org.jkcsoft.bogey.model.ManagedObject;

/**
 * Helper for getting objects from CRM with less syntax
 * TODO fold into SOM
 */
public class MC {
    //--------------------------------------------------------------------
    // Private instance variables
    //--------------------------------------------------------------------
    private ObjectContext _context = null;

    //--------------------------------------------------------------------
    // Public constructor(s)
    //--------------------------------------------------------------------
    public MC(ObjectContext context)
            throws AppException {
        if (context == null)
            throw new AppException("Null or invalid object context.");
        _context = context;
    }

    //--------------------------------------------------------------------
    // Public instance methods
    //--------------------------------------------------------------------

    /**
     * 'Native' object getter
     */
    public ManagedObject getObj(Oid type, Oid iid) throws AppException {
        if (type == null) throw new AppException("null type parameter.");

        return _context.getCRM().getCompObject(_context, "", iid);
    }


    public ManagedObject getObj(Oid type, long loid) throws AppException {
        return getObj(type, _context.getRepository().makeReposID(loid));
    }

    public ManagedObject getObj(String strDirName, Oid iid) throws AppException {
        if (strDirName == null) throw new AppException("null type parameter.");

        return _context.getCRM().getCompObject(_context, strDirName, iid);
    }

    public ManagedObject getObj(String strDirName, long loid) throws AppException {
        return getObj(strDirName, _context.getRepository().makeReposID(loid));
    }
}