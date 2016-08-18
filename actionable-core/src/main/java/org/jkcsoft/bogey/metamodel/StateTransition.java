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
        return (ActionSequence) null; // getSom().getCompObject(context, "ActionSequence", iid, edit);
    }

    public Collection<Guard> getGuardColl() throws AppException {
        return getGuardColl(false);
    }

    /**
     *
     */
    public Collection<Guard> getGuardColl(boolean edit) throws AppException {
        return null; // getSom().getCompObject(context, "GuardColl", iid, edit);
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
        return (State) null; // TODO getSom().getCompObject(context, "State", _fromstateiid, edit);
    }

    public State getToStateObject(ObjectContext context) throws AppException {
        return getToStateObject(false, context);
    }

    public State getToStateObject(boolean edit, ObjectContext context) throws AppException {
        return (State) getSom().getCompObject(context, "State", _tostateiid, edit);
    }

    public Action getActionObject(Oid aIID, ObjectContext context) throws AppException {
        return getActionObject(aIID, false, context);
    }

    public Action getActionObject(Oid aIID, boolean edit, ObjectContext context) throws AppException {
        return (Action) getSom().getCompObject(context, "Action", aIID, edit);
    }

    public Guard getGuardObject(ObjectContext context, Oid gIID) throws AppException {
        return getGuardObject(gIID, false, context);
    }

    public Guard getGuardObject(Oid gIID, boolean edit, ObjectContext context) throws AppException {
        return (Guard) getSom().getCompObject(context, "Guard", gIID, edit);
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
        IRGuardColl guards = getGuardColl();
        try {
            while (guards.hasMoreGuards()) {
                rv = guards.nextGuard().evaluate(bo);
                if (rv.getLongValue() != -1)
                    break;
            }
        }
        catch (AppException ex) {
            throw ex;
        }
        return rv;
    }

    public Action createAction() throws AppException {
        Action action = (Action) getSom().getCompObject(context, "Action", (IDataSet) null, true);
        action.setTransitionIID(getIID());
        return action;
    }

    public Guard createGuard() throws AppException {
        Guard guard = (Guard) getSom().getCompObject(context, "Guard", (IDataSet) null, true);
        guard.setTransitionIID(getIID());
        return guard;
    }

    /**
     *
     */
    public Oid execute(BusinessObject bo, String processChangeComment) throws AppException {
        Oid rv = isEnabledOid(bo);
        ActionSequence actions = getActionSequence();
        if (rv.getLongValue() == -1) {
            while (actions.hasMoreActions())
                actions.nextAction().execute(bo);
            //change the state of the object
            try {
                IRepository rep = getObjectContext().getRepository();
                bo.setStateObject(getToStateObject(false, context));
                bo.addProcessChange(this, processChangeComment);
            }
            catch (Exception ex) {
                throw new AppException(ex);
            }
        }
        else
            throw new AppException("Transition not enabled");
        return rv;
    }

    /**
     *
     */
    private void load(IDataSet rs) throws AppException {
        super.load(rs);
        IRepository rep = getObjectContext().getRepository();
        _fromstateiid = rep.makeReposID(rs.getLong(COL_FROMSTATEID));
        _tostateiid = rep.makeReposID(rs.getLong(COL_TOSTATEID));
    }

    /**
     *
     */
    public IPersistable load() throws AppException {
        if (getPersState() != PersState.NEW) {
            if (iid == null)
                throw new AppException("Null IID");
            IRConnection c = null;
            IQueryProcessor qp = null;
            try {
                c = getDatabaseConnection();
                qp = c.createProcessor();
                IDataSet rs = qp.retrieve("SELECT * FROM " + TABLE + " WHERE " + COL_OBJECTID + "=" + iid.getLongValue());
                if (rs.next()) {
                    load(rs);
                    setPersState(PersState.UNMODIFIED);
                }
                else
                    throw new AppException("Transition object not found.");
            }
            catch (Exception e) {
                throw new AppException(e);
            } finally {
                if (qp != null) qp.close();
//      if(c!=null) returnDatabaseConnection(c);
            }

        }
        return this;
    }

    /**
     *
     */
    public IPersistable save() throws AppException {
        if (!isLocked() && !getPersState().equals(PersState.NEW))  // If the bo isn't locked, throw an exception
            throw new AppException("This object (" + this.getClass().getName() + ":" + this.getIID().getLongValue() + ") cannot be saved because it is not locked.");

        IRConnection c = null;
        IQueryProcessor qp = null;
        try {
            c = getDatabaseConnection();
            qp = c.createProcessor();
            if (getPersState() == PersState.NEW) {
                qp.update("INSERT INTO " + TABLE +
                        " (" + COL_OBJECTID + ", " + COL_BYTEGuid + ", " + COL_NAME + ", " +
                        COL_FROMSTATEID + ", " + COL_TOSTATEID + ", " + COL_ISACTIVE + ", " + COL_CONFIGKIND + ", " + COL_DELETESTATE + ", " + COL_DESCRIPTION + ") VALUES (" +
                        iid.getLongValue() + ", '" + guid.toString() + "', '" + SQLUtil.primer(name) + "', " +
                        _fromstateiid.getLongValue() + ", " + _tostateiid.getLongValue() + ", " + (isactive ? "1" : "0") + ", " +
                        _configkind.getIntValue() + ", " + _deletestate.getIntValue() + ", '" + SQLUtil.primer(description) + "')");
                setPersState(PersState.UNMODIFIED);
            }
            else if (getPersState() == PersState.MODIFIED) {
                //UPDATE
                qp.update("UPDATE " + TABLE + " SET " +
                        COL_NAME + "='" + SQLUtil.primer(name) + "', " +
                        COL_FROMSTATEID + "=" + _fromstateiid.getLongValue() + ", " +
                        COL_TOSTATEID + "=" + _tostateiid.getLongValue() + ", " +
                        COL_ISACTIVE + "=" + (isactive ? "1" : "0") + ", " +
                        COL_CONFIGKIND + "=" + _configkind.getIntValue() + ", " +
                        COL_DELETESTATE + "=" + _deletestate.getIntValue() + ", " +
                        COL_DESCRIPTION + "='" + SQLUtil.primer(description) + "'" +
                        " WHERE " + COL_OBJECTID + "=" + iid.getLongValue());
                setPersState(PersState.UNMODIFIED);
            }
            else if (getPersState() == PersState.DELETED) {
                //DELETE
                qp.update("UPDATE " + TABLE + " SET " + COL_DELETESTATE + "=" + DeleteState.DELETED.getIntValue() + " WHERE " + COL_OBJECTID + "=" + iid.getLongValue());
            }
        }
        catch (Exception ex) {
            throw new AppException(ex);
        } finally {
            if (qp != null) qp.close();
        }
        return this;
    }

    /**
     *
     */
    public Object dolly() throws AppException {
        StateTransition transition = new StateTransition();
        transition.setIID(getIID());
        transition.setObjectContext(getObjectContext());
        transition.setPersState(getPersState());
        transition.setName(getName());
        transition.setDescription(getDescription());
        transition.setDeleteState(getDeleteState());
        transition.setConfigKind(getConfigKind());
        transition.isActive(isActive());
        transition.setFromStateIID(getFromStateIID());
        transition.setToStateIID(getToStateIID());
        return transition;
    }
}