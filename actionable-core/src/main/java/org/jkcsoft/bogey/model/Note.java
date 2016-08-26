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

public class Note extends BusinessObject {

    private Oid _parentObjIID;
    private Oid _noteTypeIID;

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public Note() throws AppException {
        super();
    }

    public Note setParentObject(BusinessObject parent) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parentObjIID = parent.getOid();
        return this;
    }

    public Note setNoteType(Oid noteType) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _noteTypeIID = noteType;
        return this;
    }

    public Oid getNoteTypeIID() throws AppException {
        return _noteTypeIID;
    }

    public Object dolly() throws AppException {
        Note note = null;
        note = new Note();
        note.setOid(getOid());
        note.setObjectContext(getObjectContext());
        note.setPersState(getPersState());
        note.setDefnObject(this.getDefnObject());
        note.setStateObject(this.getStateObject());
        note.setDeleteState(getDeleteState());
        if (getProperties() != null)
            note.putAll(getProperties());
        note.setName(getName());
        note.setDescription(getDescription());
        note.setCreatorIID(this.getCreatorIID());
        note.setAccessIID(this.getAccessIID());
        note.setCreationDate(getCreationDate());
        note.setMessageAttached(hasMessageAttached());
        note.setLinkAttached(hasLinkAttached());
        note.setFileAttached(hasFileAttached());

        note._parentObjIID = _parentObjIID;
        note._noteTypeIID = _noteTypeIID;
        return note;
    }

}