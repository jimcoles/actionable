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

/**
 * Encapsulates an object identifier.
 *
 * @author Jim Coles
 * @version 1.0
 */
public class Oid extends Identifier {
    private long longValue;

    public Oid(long longValue) {
        this.longValue = longValue;
    }

    public long getLongValue() {
        return longValue;
    }

}