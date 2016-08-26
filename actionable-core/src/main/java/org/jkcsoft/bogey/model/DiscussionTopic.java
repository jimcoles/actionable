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

import java.sql.Timestamp;

public class DiscussionTopic extends BusinessObject {

    private Oid _parobjectiid;
    private Oid _pardiscussiid;
    private Oid _parobjecttype;

    private int _numchildren;
    private BMProperty _subject;
    private BMProperty _editeddate;
    private boolean _isroot;
    private int _treelevel;
    private BMProperty _body;

    /**
     *
     */
    public DiscussionTopic() throws AppException {
        super();
        _subject = new BMProperty(this);
        _editeddate = new BMProperty(this);
        _body = new BMProperty(this);

    }

    private Object getPSPValue() throws AppException {
        return _body.getValue();
    }


    public DiscussionTopic setNumChildren(int num) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _numchildren = num;
        return this;
    }

    public DiscussionTopic setSubject(String sub) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _subject.setValue(sub);
        return this;
    }

    public DiscussionTopic setEditedDate(Timestamp date) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _editeddate.setValue(date);
        return this;
    }


    public DiscussionTopic isRoot(boolean r) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _isroot = r;
        return this;
    }

    public DiscussionTopic setTreeLevel(int tl) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _treelevel = tl;
        return this;
    }

    public DiscussionTopic setParObjectIID(Oid piid) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parobjectiid = piid;
        return this;
    }

    public DiscussionTopic setParDiscussObjectIID(Oid diid) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _pardiscussiid = diid;
        return this;
    }

    public DiscussionTopic setParObjectType(Oid classId) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parobjecttype = classId;
        return this;
    }

    public DiscussionTopic setBody(String body) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _body.setValue(body);
        return this;
    }

    //------------------------ ACCESSORS ------------------------------

    public int getNumChildren() throws AppException {
        return _numchildren;
    }

    public String getSubject() throws AppException {
        return (String) _subject.getValue();
    }

    public Timestamp getEditedDate() throws AppException {
        return (Timestamp) _editeddate.getValue();
    }

    public boolean isRoot() throws AppException {
        return _isroot;
    }

    public int getTreeLevel() throws AppException {
        return _treelevel;
    }

    public Oid getParObjectIID() throws AppException {
        return _parobjectiid;
    }

    public Oid getParDiscussObjectIID() throws AppException {
        return _pardiscussiid;
    }

    public Oid getParObjectType() throws AppException {
        return _parobjecttype;
    }

    public String getBody() throws AppException {
        return (String) _body.getValue();
    }


    public DiscussionTopic createCopy()
            throws AppException {
        Oid classIID = IDCONST.DISCUSSIONTOPIC;
        DiscussionTopic newTopic = (DiscussionTopic)
                getObjectContext().getCRM().getCompObject(getObjectContext(), "DiscussionTopic", null, true);

        newTopic.setSubject(getSubject());
        newTopic.setLinkAttached(hasLinkAttached());
        newTopic.setFileAttached(hasFileAttached());
        newTopic.setEditedDate(getEditedDate());
        newTopic.setDeleteState(getDeleteState());
        newTopic.setNumChildren(getNumChildren());
        newTopic.isRoot(isRoot());
        newTopic.setTreeLevel(getTreeLevel());
        newTopic.setParObjectIID(getParObjectIID());
        newTopic.setParDiscussObjectIID(getParDiscussObjectIID());
        newTopic.setBody(getBody());


        return newTopic;
    }


    //----------------- IPoolable Methods -----------------------------

    public Object dolly() throws AppException {
        DiscussionTopic dt = null;
//        dt = new DiscussionTopic();
//        dt.setOid(getOid());
//        dt.setPersState(getPersState());
//        dt._classIID = _classIID;
//        dt._stateIID = _stateIID;
//
//        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
//            dt.putAll(getProperties());
//
//        dt._creatorIID = _creatorIID;
//        dt._accessIID = _accessIID;
//        dt.setCreationDate(getCreationDate());
//        dt.setLinkAttached(hasLinkAttached());
//        dt.setFileAttached(hasFileAttached());
//        dt.setMessageAttached(hasMessageAttached());
//        dt.setSubject(getSubject());
//        dt.setEditedDate(getEditedDate());
//        dt.setTopicKind(getTopicKind());
//        dt.setDeleteState(getDeleteState());
//        dt.setNumChildren(getNumChildren());
//        dt.isRoot(isRoot());
//        dt.setTreeLevel(getTreeLevel());
//        dt.setParObjectIID(getParObjectIID());
//        dt.setParDiscussObjectIID(getParDiscussObjectIID());
//        dt.setParObjectType(getParObjectType());
//        dt.setBody(getBody());
        return dt;
    }

}