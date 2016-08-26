/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.metamodel;

import java.util.HashMap;

/**
 * @author Jim Coles
 */
public class RepoMap extends HashMap<String, Object> {

    private Oid oid;

    public Oid getOid() {
        return oid;
    }

    public void setOid(Oid oid) {
        this.oid = oid;
    }
}
