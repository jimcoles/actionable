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

import org.jkcsoft.bogey.metamodel.Class;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.metamodel.PersState;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.system.AppSystem;

public class HyperLink extends BusinessObject {

    private Oid _parentObjIID;
    private BMProperty _url;

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public HyperLink() throws AppException {
        super();
        _url = new BMProperty(this);
        _url.setDefnObject(IDCONST.URL);
    }

    public HyperLink setParentObject(BusinessObject parent) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parentObjIID = parent.getOid();
        return this;
    }

    public HyperLink setURL(String url)
            throws AppException {
        _url.setValue(url);
        return this;
    }

    public String getURL()
            throws AppException {
        if (_url.getValue() != null)
            return (String) _url.getValue();
        else
            return null;
    }

    public HyperLink createCopy() throws AppException {
        Oid classIID = IDCONST.HYPERLINK;
        HyperLink newLink = (HyperLink) getSom().getCompObject(getObjectContext(), "HyperLink",
                                                               AppSystem.getRepo().genReposID(), true);
        newLink.setDefnObject((Class) getSom().getCompObject(getObjectContext(), "Class", classIID, false));

        newLink.setName(getName());
        newLink.setDescription(getDescription());
        newLink.setMessageAttached(hasMessageAttached());
        newLink.setLinkAttached(hasLinkAttached());
        newLink.setFileAttached(hasFileAttached());
        newLink.setURL(getURL());

        return newLink;
    }

    public Object dolly() throws AppException {
        HyperLink link = null;
        link = new HyperLink();
        link.setOid(getOid());
        link.setObjectContext(getObjectContext());
        link.setPersState(getPersState());
        link.setDefnObject(this.getDefnObject());
        link.setStateObject(this.getStateObject());
        link.setDeleteState(getDeleteState());
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            link.putAll(getProperties());
        link.setName(getName());
        link.setDescription(getDescription());
        link.setCreatorIID(this.getCreatorIID());
        link.setAccessIID(this.getAccessIID());
        link.setCreationDate(getCreationDate());
        link.setMessageAttached(hasMessageAttached());
        link.setLinkAttached(hasLinkAttached());
        link.setFileAttached(hasFileAttached());
        link._parentObjIID = _parentObjIID;
        link.setURL(getURL());
        return link;
    }

}