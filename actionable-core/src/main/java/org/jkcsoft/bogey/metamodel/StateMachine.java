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
import org.jkcsoft.bogey.system.ObjectContext;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Borrowed from UML StateMachine but used herein to mean "Business Process".
 * This is the top-level notion that enabled data-driven orchestration of business
 * objects through a process, e.g., "Purchase Order" processing.
 */
public class StateMachine extends ModelElement {

    private Oid _classiid;

    public StateMachine() throws AppException {
        setGuid(new Guid());
    }

    public Oid getClassIID() throws AppException {
        return _classiid;
    }

    public State getStateObject(Oid id) throws AppException {
        return getStateObject(id, false);
    }

    public State getStateObject(Oid id, boolean edit) throws AppException {
        if (id == null)
            return null; // (State)context.getCRM().getCompObject(context,"State",(RepoMap)null, edit);
        else
            return (State) null; //context.getCRM().getCompObject(context,"State",id, edit);
    }

    public StateMachine setClassIID(Oid c) throws AppException {
        _classiid = c;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public State getDefaultStartStateObject() throws AppException {
        return getDefaultStartStateObject(false);
    }

    public State getDefaultStartStateObject(boolean edit) throws AppException {
        State s = null;
        List<State> states = null; // getStateColl(edit);
        Iterator<State> iStates = states.iterator();
        while (iStates.hasNext()) {
            s = iStates.next();
            if (s.getStateKind() == StateKind.START)
                break;
        }
        return s;
    }

    public Oid doTransition(BusinessObject o, StateTransition t, String processChangeComment) throws AppException {
        if (t.getFromStateIID().getLongValue() != o.getStateObject().getOid().getLongValue())
            throw new AppException("This transition does not go with this state.");
        else
            return t.execute(o, processChangeComment);
    }

    public State createState() throws AppException {
        State state = (State) getSom().getCompObject((ObjectContext) null, "State", null, true);
        state.setStateMachineIID(getOid());
        return state;
    }

    public Collection<StateTransition> getEnabledTransitions(BusinessObject businessObject) {
        return null;  // TODO
    }

//    /**
//     *
//     */
//    public Object dolly() throws AppException {
//        StateMachine statemachine = new StateMachine();
//        statemachine.setOid(getOid());
//        statemachine.setObjectContext(getObjectContext());
//        statemachine.setPersState(getPersState());
//        statemachine.setName(getName());
//        statemachine.setDescription(getDescription());
//        statemachine.setDeleteState(getDeleteState());
//        statemachine.setConfigKind(getConfigKind());
//        statemachine.isActive(isActive());
//        statemachine.setClassIID(getClassIID());
//        return statemachine;
//    }
}