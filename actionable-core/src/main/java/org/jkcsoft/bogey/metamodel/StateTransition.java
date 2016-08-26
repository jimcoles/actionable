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

import org.jkcsoft.bogey.model.BusinessObject;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.AppSystem;
import org.jkcsoft.bogey.system.Repository;

import java.util.Collection;

public class StateTransition extends ModelElement {

    private Oid _fromstateiid;
    private Oid _tostateiid;
    private Oid _statemachineiid;

    /**
     *
     */
    public StateTransition() throws AppException {
        setGuid(new Guid());
    }

    public ActionSequence getActionSequence() throws AppException {
        return getActionSequence(false);
    }

    /**
     *
     */
    public ActionSequence getActionSequence(boolean edit) throws AppException {
        return (ActionSequence) null; // getSom().getCompObject(null, "ActionSequence", iid, edit);
    }

    public Collection<Guard> getGuardColl() throws AppException {
        return getGuardColl(false);
    }

    /**
     *
     */
    public Collection<Guard> getGuardColl(boolean edit) throws AppException {
        return null; // getSom().getCompObject(null, "GuardColl", iid, edit);
    }

    //ACCESSORS
    public Oid getFromStateIID() throws AppException {
        return _fromstateiid;
    }

    public Oid getToStateIID() throws AppException {
        return _tostateiid;
    }

    public State getFromStateObject() throws AppException {
        return getFromStateObject(false);
    }

    public State getFromStateObject(boolean edit) throws AppException {
        return (State) null; // TODO getSom().getCompObject(null, "State", _fromstateiid, edit);
    }

    public State getToStateObject(boolean edit) throws AppException {
        return (State) null; // TODO getSom().getCompObject(null, "State", _tostateiid, edit);
    }

    public Action getActionObject(Oid aIID) throws AppException {
        return getActionObject(aIID, false);
    }

    public Action getActionObject(Oid aIID, boolean edit) throws AppException {
        return (Action) getSom().getCompObject(null, "Action", aIID, edit);
    }

    public Guard getGuardObject(Oid gIID) throws AppException {
        return getGuardObject(gIID, false);
    }

    public Guard getGuardObject(Oid gIID, boolean edit) throws AppException {
        return (Guard) getSom().getCompObject(null, "Guard", gIID, edit);
    }

    //MUTATORS
    public StateTransition setFromStateIID(Oid f) throws AppException {
        _fromstateiid = f;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public StateTransition setStateMachineIID(Oid smiid) throws AppException {
        _statemachineiid = smiid;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public StateTransition setToStateIID(Oid t) throws AppException {
        _tostateiid = t;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    //FUNCTIONAL METHODS

    /**
     * returns true iff the transition isEnabled
     */
    public boolean isEnabled(BusinessObject bo) throws AppException {
        return (isEnabledOid(bo).getLongValue() == -1);
    }

    /**
     * returns -1 if isEnabled
     */
    private Oid isEnabledOid(BusinessObject bo) throws AppException {
        Oid rv = new Oid(-1);
        Collection<Guard> guards = getGuardColl();
        try {
            for (Guard guard : guards) {
                rv = guard.evaluate(bo);
                if (rv.getLongValue() != -1)
                    break;
            }
        } catch (AppException ex) {
            throw ex;
        }
        return rv;
    }

    public Action createAction() throws AppException {
        Action action = (Action) getSom().getCompObject(null, "Action", null, true);
        action.setTransitionIID(getOid());
        return action;
    }

    public Guard createGuard() throws AppException {
        Guard guard = (Guard) getSom().getCompObject(null, "Guard", null, true);
        guard.setTransitionIID(getOid());
        return guard;
    }

    /**
     *
     */
    public Oid execute(BusinessObject bo, String processChangeComment) throws AppException {
        Oid rv = isEnabledOid(bo);
        ActionSequence actions = getActionSequence();
        if (rv.getLongValue() == -1) {
            for (Action action : actions) {
                // TODO action.execute(bo);
            }
            //change the state of the object
            try {
                bo.setStateObject(getToStateObject(false));
                bo.addProcessChange(this, processChangeComment);
            } catch (Exception ex) {
                throw new AppException(ex);
            }
        } else
            throw new AppException("Transition not enabled");
        return rv;
    }

    /**
     *
     */
    private void load(RepoMap rs) throws AppException {
// TODO        super.load(rs);
        Repository rep = AppSystem.getRepo();
        _fromstateiid = rep.makeReposID((Long) rs.get("?"));
        _tostateiid = rep.makeReposID((Long) rs.get("?"));
    }

    /**
     *
     */
    public Object dolly() throws AppException {
        StateTransition transition = new StateTransition();
        transition.setOid(getOid());
//        transition.setObjectContext(getObjectContext());
        transition.setPersState(getPersState());
        transition.setDisplayName(getDisplayName());
        transition.setDescription(getDescription());
//        transition.setDeleteState(getDeleteState());
//        transition.setConfigKind(getConfigKind());
        transition.setIsActive(isActive());
        transition.setFromStateIID(getFromStateIID());
        transition.setToStateIID(getToStateIID());
        return transition;
    }
}