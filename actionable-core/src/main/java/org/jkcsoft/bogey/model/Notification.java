/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.model;

import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.metamodel.PersState;
import org.jkcsoft.bogey.system.AppException;

public class Notification extends BusinessObject {

    private Oid _parobjectiid;
    private Oid _recipientiid;

    private BMProperty _ackmask;
    private BMProperty _body;
    private BMProperty _subject;
    private BMProperty _notekind;

    /**
     *
     */
    public Notification() throws AppException {
        setPersState(PersState.NEW);
        _ackmask = new BMProperty(this);
        _subject = new BMProperty(this);
        _body = new BMProperty(this);
        _notekind = new BMProperty(this);

//    _ackmask.setDefnObject(IDCONST.ACKMASK.getIIDValue());
//    _subject.setDefnObject(IDCONST.SUBJECT.getIIDValue());
//    _body.setDefnObject(IDCONST.BODY.getIIDValue());
//    _notekind.setDefnObject(IDCONST.NOTEKIND.getIIDValue());

    }

    private Object getPSPValue() throws AppException {
        return _body.getValue();
    }

    public Object dolly() throws AppException {
        Notification notif = new Notification();
//        notif.setOid(getOid());
//        notif.setObjectContext(getObjectContext());
//        notif.setPersState(getPersState());
//
//        notif._classIID = _classIID;
//        notif._stateIID = _stateIID;
//        notif.setDeleteState(getDeleteState());
//        notif._creatorIID = _creatorIID;
//        notif._accessIID = _accessIID;
//        notif.setCreationDate(getCreationDate());
//        notif.setMessageAttached(hasMessageAttached());
//        notif.setLinkAttached(hasLinkAttached());
//        notif.setFileAttached(hasFileAttached());
//
//        notif.setParObjectIID(getParObjectIID());
//        notif.setAckMask(getAckMask());
//        notif.setRecipientIID(getRecipientIID());
//        notif.setBody(getBody());
//        notif.setSubject(getSubject());
//        notif.setNotificationKind(getNotificationKind());
        return notif;
    }


    public Notification setAckMask(int mask) throws AppException {
        _ackmask.setValue(new Integer(mask));
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Notification setParObjectIID(Oid iid) throws AppException {
        _parobjectiid = iid;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Notification setRecipientIID(Oid iid) throws AppException {
        _recipientiid = iid;
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Notification setRecipientObject(User user) throws AppException {
        return setRecipientIID(user.getOid());
    }

    public Notification setBody(String body) throws AppException {
        _body.setValue(body);
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public Notification setSubject(String subject) throws AppException {
        _subject.setValue(subject);
        if (getPersState() == PersState.UNMODIFIED)
            setPersState(PersState.MODIFIED);
        return this;
    }

    public int getAckMask() throws AppException {
        if (_ackmask.getValue() != null)
            return ((Integer) _ackmask.getValue()).intValue();
        else
            return 0;
    }

    public Oid getParObjectIID() throws AppException {
        return _parobjectiid;
    }

    public Oid getRecipientIID() throws AppException {
        return _recipientiid;
    }

    public User getRecipientObject() throws AppException {
        return (User) getObjectContext().getCRM().getCompObject(getObjectContext(), "User", _recipientiid);
    }

    public String getBody() throws AppException {
        return (String) _body.getValue();
    }

    public String getSubject() throws AppException {
        return (String) _subject.getValue();
    }

}