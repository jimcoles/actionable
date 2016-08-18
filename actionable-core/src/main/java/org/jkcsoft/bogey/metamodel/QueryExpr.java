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
 * @author J. Coles
 * @version 1.0
 */
public class QueryExpr {
    //----------------------------------------------------------------------
    // Private class vars
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // Private instance vars
    //----------------------------------------------------------------------
    private Operation oper = null;
    private Object leftArg = null;
    private Object rightArg = null;

    //----------------------------------------------------------------------
    // Constructor(s)
    //----------------------------------------------------------------------
    public QueryExpr() {
    }

    public QueryExpr(Operation oper, Object left, Object right) {
        setOper(oper);
        setLeft(left);
        setRight(right);
    }

    //----------------------------------------------------------------------
    // Public Methods
    //----------------------------------------------------------------------

    public Object getLeft() {
        return leftArg;
    }

    public Object getRight() {
        return rightArg;
    }

    public Operation getOper() {
        return oper;
    }

    public void setOper(Operation oper) {
        oper = oper;
    }

    public void setLeft(Object left) {
        leftArg = left;
    }

    public void setRight(Object right) {
        rightArg = right;
    }
}
