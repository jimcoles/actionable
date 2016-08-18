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

import org.jkcsoft.bogey.system.AppException;

public class Action extends ModelElement {

    private int orderNum;
    private Oid transitionIid;
    private Oid attributeIid;
    private ActionKind actionkind;
    private String setValue;
    private String actionExpression;

    /**
     *
     */
    public Action() throws AppException {
        setGuid(new Guid());
    }

    //ACCESSORS
    public int getOrderNum() throws AppException {
        return orderNum;
    }

    public Oid getTransitionIID() throws AppException {
        return transitionIid;
    }

    public Oid getAttributeIID() throws AppException {
        return attributeIid;
    }

    public ActionKind getActionKind() throws AppException {
        return actionkind;
    }

    public String getSetValue() throws AppException {
        return setValue;
    }

    public String getActionExpression() throws AppException {
        return actionExpression;
    }


    //MUTATORS
    public Action setOrderNum(int o) throws AppException {
        orderNum = o;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Action setTransitionIID(Oid t) throws AppException {
        transitionIid = t;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Action setAttributeIID(Oid a) throws AppException {
        attributeIid = a;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Action setActionKind(ActionKind a) throws AppException {
        actionkind = a;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Action setSetValue(String s) throws AppException {
        setValue = s;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Action setActionExpression(String s) throws AppException {
        actionExpression = s;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }
}