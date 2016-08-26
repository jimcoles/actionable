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

import org.jkcsoft.bogey.model.User;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.AppSystem;
import org.jkcsoft.bogey.system.ObjectContext;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ProcessChange extends ModelElement {

    private Oid _chgobjectiid;
    private Oid _reviid;
    private Oid _fromuseriid;
    private Oid _transitioniid;
    private Timestamp _changedate;
    private int _ackmask;
    private String _comment;
    private List<Oid> _users;

    /**
     *
     */
    public ProcessChange() throws AppException {
//        _guid = new GUID();
    }

    public Object dolly() throws AppException {
        ProcessChange pc = null;
//        pc = new ProcessChange();
//        pc.setIID(getOid());
//        pc.setObjectContext(getObjectContext());
//        pc.setPersState(getPersState());
//        pc.setChangeObjectIID(getChangeObjectIID());
//        pc.setRevisionIID(getRevisionIID());
//        pc.setFromUserIID(getFromUserIID());
//        pc.setTransitionIID(getTransitionIID());
//        pc.setComment(getComment());
//        pc.setAckMask(getAckMask());
//        pc.setChangeDate(getChangeDate());
//        pc._users = (UserList) getObjectContext().getCRM().getCompObject(getObjectContext(),
//                                                                          "ProcessChangeUserList", getOid());
        return pc;
    }

    //Accessors
    public Oid getChangeObjectIID() throws AppException {
        return _chgobjectiid;
    }

    public Oid getRevisionIID() throws AppException {
        return _reviid;
    }

    public Oid getFromUserIID() throws AppException {
        return _fromuseriid;
    }

    public User getFromUserObject() throws AppException {
        return (User) getSom().getCompObject(null, "User", getFromUserIID());
    }

    public Oid getTransitionIID() throws AppException {
        return _transitioniid;
    }

    public StateTransition getTransitionObject() throws AppException {
        if (getTransitionIID() == null || getTransitionIID().getLongValue() <= 1)
            return null;
        return (StateTransition) getSom().getCompObject(null, "Transition", getTransitionIID());
    }

    public String getComment() throws AppException {
        return _comment;
    }

    public Timestamp getChangeDate() throws AppException {
        return _changedate;
    }

    public int getAckMask() throws AppException {
        return _ackmask;
    }

    public List<Oid> getReceivers() throws AppException {
        return _users;
    }

//  private UserList loadReceivers() throws AppException
//  {
//    _users = (UserList)getObjectContext().getCRM().getCompObject(getObjectContext(), "ProcessChangeUserList",
// getOid());
//    return getReceivers();
//  }

    //Mutators
    public ProcessChange setChangeObjectIID(Oid i) throws AppException {
        _chgobjectiid = i;
        return this;
    }

    public ProcessChange setRevisionIID(Oid i) throws AppException {
        _reviid = i;
        return this;
    }

    public ProcessChange setFromUserIID(Oid i) throws AppException {
        _fromuseriid = i;
        return this;
    }

    public ProcessChange setFromUserObject(User user) throws AppException {
        return setFromUserIID(user.getOid());
    }

    public ProcessChange setTransitionIID(Oid i) throws AppException {
        _transitioniid = i;
        return this;
    }

    public ProcessChange setTransitionObject(StateTransition trans) throws AppException {
        return setTransitionIID(trans.getOid());
    }

    public ProcessChange setComment(String c) throws AppException {
        _comment = c;
        return this;
    }

    public ProcessChange setAckMask(int a) throws AppException {
        _ackmask = a;
        return this;
    }

    public ProcessChange setChangeDate(Timestamp t) throws AppException {
        _changedate = t;
        return this;
    }

    public ProcessChange addReceiver(User user) throws AppException {
        _users.add(user.getOid());
//    //make sure that the user is not already there
//    boolean isThere = false;
//    UserList users = getRecievers();
//    while(users.hasNext())
//      if(users.nextUser().getOid().equals(user.getOid()))
//        isThere = true;
//    if(!isThere)
//    {
//      IRConnection c = null; IQueryProcessor qp = null;
//      try
//      {
//        c = CRM.getInstance().getDatabaseConnection(_context);
//        qp = c.createProcessor();
//        qp.update("INSERT INTO PROCESSRECEIVER ("
//                  +"CHANGEID, USERID) VALUES ("
//                  +getOid()+", "+user.getOid()+")");
//      }
//      catch(Exception ex) { throw new AppException(ex); }
//      finally{if(qp!=null)qp.close();}
//    }
        return this;
    }

    public ProcessChange construct(ObjectContext context, RepoMap args) throws AppException {
        Oid iid = null;
        if (context == null)
            throw new AppException("Context Argument expected.");
//        setObjectContext(context);

        if (args == null) {
            iid = AppSystem.getRepo().genReposID();
            setPersState(PersState.NEW);
        } else {
            setPersState(PersState.UNINITED);
            iid = args.getOid();
        }
        setOid(iid);

        if (args != null && args.keySet().size() > 0) {
//  TODO            load(args);
        }

        return this;
    }

    private String getLoadPropertiesQuery()
            throws AppException {
        return null;
    }

    private String getUpdateQuery() throws AppException {
        return null;
    }

    private String getCreateQuery() throws AppException {
        return null;
    }

    public Map getProperties() {
        return null;
    }

    public String getName() throws AppException {
        return null;
    }

}