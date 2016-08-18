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


import org.jkcsoft.bogey.expr.ExprEvaluator;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.model.BusinessObject;

public class Guard extends ModelElement {

    private Oid _transitioniid;
    private GuardKind _guardkind;
    private String _guardexpr;

    /**
     *
     */
    public Guard() throws AppException {
        setGuid(new Guid());
    }

    public Oid getTransitionIID() throws AppException {
        return _transitioniid;
    }

    public GuardKind getGuardKind() throws AppException {
        return _guardkind;
    }

    public String getGuardExpression() throws AppException {
        return _guardexpr;
    }

    public Guard setTransitionIID(Oid t) throws AppException {
        _transitioniid = t;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Guard setGuardKind(GuardKind g) throws AppException {
        _guardkind = g;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Guard setGuardExpression(String s) throws AppException {
        _guardexpr = s;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    //FUNCTIONAL METHODS
    public Oid evaluate(BusinessObject bo) throws AppException {
        ExprEvaluator e = new ExprEvaluator(bo, _guardexpr);
        float flt = e.evaluate();
        if (flt != ExprEvaluator.FALSE)
            return new Oid(-1); //true
        else
            return getOid();
    }

//    /**
//     *
//     */
//    public Object dolly() throws AppException {
//        Guard guard = new Guard();
//        guard.setOid(getOid());
//        guard.setObjectContext(getObjectContext());
//        guard.setPersState(getPersState());
//        guard.setName(getName());
//        guard.setDescription(getDescription());
//        guard.setDeleteState(getDeleteState());
//        guard.setConfigKind(getConfigKind());
//        guard.isActive(isActive());
//        guard.setTransitionIID(getTransitionIID());
//        guard.setGuardKind(getGuardKind());
//        guard.setGuardExpression(getGuardExpression());
//        return guard;
//    }
}