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

public class QuerySortItem {
    private SortDir dir = null;
    private QueryAttrRef attr = null;

    QuerySortItem(QueryAttrRef attr, SortDir dir) {
        attr = attr;
        dir = dir;
    }

    public SortDir getDir() {
        return dir;
    }

    public QueryAttrRef getAttrRef() {
        return attr;
    }
}
