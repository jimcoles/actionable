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

public class State extends ModelElement {

    //CONSTANTS
    public static final String TABLE = "STATE";
    public static final String COL_STATEMACHINEID = "STATEMACHINEID";
    public static final String COL_ROLEID = "ROLEID";
    public static final String COL_STATEKIND = "STATEKIND";


    private Oid _statemachineiid;
    private Oid _roleiid;
    private StateKind _statekind;

    /**
     *
     */
    public State() throws AppException {
        setGuid(new Guid());
    }//end constructor

    //ACCESSORS
    public Oid getRoleIID() throws AppException {
        return _roleiid;
    }

    public ProcessRole getRoleObject() throws AppException {
        return getRoleObject(false);
    }

    public ProcessRole getRoleObject(boolean edit) throws AppException {
//        return (ProcessRole) getObjectContext().getCRM().getCompObject(getObjectContext(), "Role", _roleiid, edit);
        return null;
    }

    public Oid getStateMachineIID() throws AppException {
        return _statemachineiid;
    }

    public StateKind getStateKind() throws AppException {
        return _statekind;
    }

    public StateMachine getStateMachineObject() throws AppException {
        return getStateMachineObject(false);
    }

    public StateMachine getStateMachineObject(boolean edit) throws AppException {
//        return (IRStateMachine) context.getCRM().getCompObject(context, "StateMachine", _statemachineiid, edit);
        return null;
    }

    public StateTransition getTransitionObject(Oid transIID) throws AppException {
        return getTransitionObject(transIID, false);
    }

    public StateTransition getTransitionObject(Oid transIID, boolean edit) throws AppException {
        if (transIID == null)
//            return (IRTransition) context.getCRM().getCompObject(context, "Transition", (RepoMap) null, edit);
            return null;
        else
//            return (IRTransition) context.getCRM().getCompObject(context, "Transition", transIID, edit);
            return null;
    }//end getTransitionObject

    //MUTATORS
    public State setRoleIID(Oid r) throws AppException {
        _roleiid = r;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }//end setRoleIID

    public State setStateMachineIID(Oid s) throws AppException {
        _statemachineiid = s;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }//end setStateMachineIID

    public State setStateKind(StateKind s) throws AppException {
        _statekind = s;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }//end setStateKind


    public boolean isFinal() throws AppException {
        return (_statekind == StateKind.FINAL || _statekind == StateKind.START_FINAL);
    }

    public boolean isStart() throws AppException {
        return (_statekind == StateKind.START || _statekind == StateKind.START_FINAL);
    }

    public StateTransition createTransition() throws AppException {
        StateTransition trans = null; // (IRTransition) context.getCRM().getCompObject(context, "Transition", (RepoMap) null, true);
        trans.setFromStateIID(getOid());
        return trans;
    }

    /**
     *
     */
//    public Object dolly() throws AppException {
//        State state = new State();
//        state.setOid(getOid());
//        state.setObjectContext(getObjectContext());
//        state.setPersState(getPersState());
//        state.setName(getName());
//        state.setDescription(getDescription());
//        state.setDeleteState(getDeleteState());
//        state.setConfigKind(getConfigKind());
//        state.isActive(isActive());
//        state.setStateMachineIID(getStateMachineIID());
//        state.setRoleIID(getRoleIID());
//        state.setStateKind(getStateKind());
//        return state;
//    }//
}