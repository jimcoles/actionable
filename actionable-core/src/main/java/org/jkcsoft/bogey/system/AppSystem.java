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

import org.jkcsoft.bogey.system.security.AccessMgr;

/**
 * @author Jim Coles
 */
public class AppSystem {

    private static SOM crm;

    public static SOMConnection getSOMConnection(String user, String password) {
        SOMConnection conn = null;
        try {
            conn = crm.connect(user, password);
//            accessMgr = new AccessMgr(conn.getUserOid(), null);
            crm = null; // TODO -- SOMImpl.getInstance();
        } catch (AppException e) {
            // TODO -- route to system logger
            e.printStackTrace();
        }
        return conn;
    }

    public static SOM getCrm() {
        return crm;
    }
}
