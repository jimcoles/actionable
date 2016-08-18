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

public class QuerySelectItem {
    //-----------------------------------------------------------------
    // Public instance methods
    //-----------------------------------------------------------------
    private Object attr = null;
    private String alias = null;

    //-----------------------------------------------------------------
    // Constructor(s)
    //-----------------------------------------------------------------
    QuerySelectItem(QueryAttrRef attr, String alias) {
        attr = attr;
        alias = alias;
    }

    QuerySelectItem(Object attr, String alias) {
        attr = attr;
        alias = alias;
    }

    //-----------------------------------------------------------------
    // Public instance methods
    //-----------------------------------------------------------------

    public Object getAttr() {
        return attr;
    }

    public String getAlias() {
        return alias;
    }
}
