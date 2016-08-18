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
 *
 */
public class Query {
    //----------------------------------------------------------------------
    // Private class vars
    //----------------------------------------------------------------------
    //----------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------
    private QuerySelect selNode = new QuerySelect();
    private Class targetClass = null;
    private QueryFilter filterNode = new QueryFilter();
    private QuerySort sortNode = new QuerySort();

    //----------------------------------------------------------------------
    // Public constructor
    //----------------------------------------------------------------------
    public Query() {
    }

    //----------------------------------------------------------------------
    // Instance methods
    //----------------------------------------------------------------------

    public void setTargetClass(Class cls) {
        targetClass = cls;
        QueryFilterExpr defFilter = getClassDefnFilter(_targetClass);
        if (defFilter != null) {
            QueryFilter filter = getFilter();
            QueryFilterExpr filterExpr = filter.getExpr();
            if (filterExpr == null)
                filter.setExpr(defFilter);
            else {
                filterExpr = new QueryFilterExpr(BoolOper.BOOL_AND, filterExpr, defFilter);
                filter.setExpr(filterExpr);
            }
        }
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public QuerySelect getSelect() {
        return selNode;
    }

    public QueryFilter getFilter() {
        return filterNode;
    }

    public QuerySort getSort() {
        return sortNode;
    }

    private QueryFilterExpr combineFilters(BoolOper bool, QueryFilterExpr one, QueryFilterExpr two)
            throws Exception {
        if (one == null) return two;
        if (two == null) return one;
        return new QueryFilterExpr(bool, one, two);
    }


    public Query setScope(Class target, Class scopeType, long scopeID)
            throws Exception {
        return setScope(target, null, scopeType, scopeID);
    }

    public Query setScope(Class target, Class connectToType, Class scopeType, long scopeID)
            throws Exception {
        return setScope(target, connectToType, scopeType, new long[]{scopeID});
    }

    public Query setScope(Class target, Class connectToType, Class scopeType, long[] scopeIDs)
            throws Exception {
        return null;
    }

    private QueryFilterExpr getClassDefnFilter(Class cls) {
        return null;
    }
}