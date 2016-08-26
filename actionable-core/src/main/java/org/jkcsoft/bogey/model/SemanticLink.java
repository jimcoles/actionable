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
import org.jkcsoft.bogey.system.ObjectContext;

import java.util.Map;

public class SemanticLink extends ReposObject {

    private Oid _srcobjectiid, _destobjectiid;
    private LinkKind _linktype;
    private int _ordernum;

    public SemanticLink() throws AppException {
        super();
    }

    public Object dolly() throws AppException {
        SemanticLink ra = null;
//        ra = new SemanticLink();
//        ra.setOid(getOid());
//        ra.setObjectContext(getObjectContext());
//        ra.setPersState(getPersState());
//        ra.setSourceObjectIID(getSourceObjectIID());
//        ra.setDestObjectIID(getDestObjectIID());
//        ra.setLinkType(getLinkType());
//        ra.setOrderNum(getOrderNum());
        return ra;
    }

    //------------------------------ Element -----------------------

    public String getName()
            throws AppException {
        return null;
    }

    public String getDescription()
            throws AppException {
        return null;
    }

    public Element setName(String n)
            throws AppException {
        return null;
    }

    public Element setDescription(String d)
            throws AppException {
        return null;
    }


    public void construct(ObjectContext context, Map<String, Object> args) throws AppException {
        Oid iid = null;
        if (context == null)
            throw new AppException("Context Argument expected.");
        if (args == null) {
            iid = null; // TODO getRepository().genReposID();
            setPersState(PersState.NEW);
        } else {
            setPersState(PersState.UNINITED);
//            iid = args.getOid();
        }
        setOid(iid);
        // TODO
//        if (args != null && args.containsKey(COL_LINKTYPE))
//            load(args);
        return;
    }

    //--------------------------- IRoleAssignment --------------------

    //Accessors
    public Oid getSourceObjectIID() throws AppException {
        return _srcobjectiid;
    }

    public Oid getDestObjectIID() throws AppException {
        return _destobjectiid;
    }

    public LinkKind getLinkType() throws AppException {
        return _linktype;
    }

    public int getOrderNum() throws AppException {
        return _ordernum;
    }

    public SemanticLink setSourceObjectIID(Oid iid) throws AppException {
        _srcobjectiid = iid;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public SemanticLink setDestObjectIID(Oid iid) throws AppException {
        _destobjectiid = iid;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public SemanticLink setLinkType(LinkKind linktype) throws AppException {
        _linktype = linktype;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

    public SemanticLink setOrderNum(int num) throws AppException {
        _ordernum = num;
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        return this;
    }

}