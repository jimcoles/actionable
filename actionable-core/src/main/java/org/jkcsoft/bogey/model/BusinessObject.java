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
import org.jkcsoft.bogey.metamodel.*;
import org.jkcsoft.bogey.system.*;
import org.jkcsoft.bogey.system.security.AccessMgr;
import org.jkcsoft.bogey.system.security.GrantSet;
import org.jkcsoft.bogey.system.security.PermEnum;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

abstract public class BusinessObject extends ReposObject {
    public static final String Comment = "No Comment Entered";

    private Oid _stateIID;                                                // the BO's current state Oid
    private Oid _classIID;                                              // the BO's class Oid
    private Oid _creatorIID;
    private Oid _accessIID;
    private DeleteState _deleteState = DeleteState.NOT_DELETED;
    private BMProperty _name,                         // string type
            _description,                  // string type
            _creationDate,           // Timestamp type
            _messageAttached,        // boolean type
            _linkAttached,           // boolean type
            _fileAttached,           // boolean type
            _class;


    //--------------------------- Public Constructors --------------------------

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public BusinessObject() throws AppException {
        _name = new BMProperty(this);
        _description = new BMProperty(this);
        _creationDate = new BMProperty(this);
        _messageAttached = new BMProperty(this);
        _linkAttached = new BMProperty(this);
        _fileAttached = new BMProperty(this);
        _class = new BMProperty(this);
        _accessIID = new Oid(0);

        _name.setDefnObject(IDCONST.NAME);
        _name.setRequired(true);
        _description.setDefnObject(IDCONST.DESC);
        _creationDate.setDefnObject(IDCONST.CREATION_DATE);
        _messageAttached.setDefnObject(IDCONST.MESSAGE_ATTACHED);
        _fileAttached.setDefnObject(IDCONST.FILE_ATTACHED);
        _linkAttached.setDefnObject(IDCONST.LINK_ATTACHED);
        _class.setDefnObject(IDCONST.CLASS);
    }

    public BusinessObject setObjectContext(ObjectContext context) {
        return this;
    }

    public ObjectContext getObjectContext() {
        return null; // TODO how to get?  should we use this concept?
    }

    public BusinessObject setCreationDate(Timestamp creationDate)
            throws AppException {
        _creationDate.setValue(creationDate);
        return this;
    }

    public BusinessObject setCreatorIID(Oid creator)
            throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _creatorIID = creator;
        return this;
    }

    public BusinessObject setCreator(User creator)
            throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _creatorIID = creator.getOid();
        return this;
    }

    public BusinessObject setDeleteState(DeleteState ds)
            throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED) && !_deleteState.equals(ds))
            setPersState(PersState.MODIFIED);
        _deleteState = ds;
        return this;
    }

    public BusinessObject setAccessIID(Oid access)
            throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED) && !_accessIID.equals(access))
            setPersState(PersState.MODIFIED);
        _accessIID = access;
        return this;
    }

    public BusinessObject setMessageAttached(boolean messageAttached)
            throws AppException {
        _messageAttached.setValue(new Boolean(messageAttached));
        return this;
    }

    public BusinessObject setFileAttached(boolean fileAttached)
            throws AppException {
        _fileAttached.setValue(new Boolean(fileAttached));
        return this;
    }

    public BusinessObject setLinkAttached(boolean linkAttached)
            throws AppException {
        _linkAttached.setValue(new Boolean(linkAttached));
        return this;
    }

    //the subclass must implement this method if needed
    public BusinessObject addToWorkforce(Oid useriid, int ordernum)
            throws AppException {
        return null;
    }

    //the subclass must implement this method if needed
    public BusinessObject addToRole(Oid useriid, Oid roleiid, int ordernum)
            throws AppException {
        return null;
    }

    //the subclass must implement this method if needed
    public BusinessObject addToRole(Oid useriid, Oid roleiid, int ordernum, boolean recurse)
            throws AppException {
        return null;
    }

    //the subclass must implement this method if needed
    public BusinessObject removeFromWorkforce(Oid useriid)
            throws AppException {
        return null;
    }

    //the subclass must implement this method if needed
    public BusinessObject removeFromRole(Oid useriid, Oid roleiid)
            throws AppException {
        return null;
    }

    //the subclass must implement this method if needed
    public BusinessObject removeFromRole(Oid useriid, Oid roleiid, boolean recurse)
            throws AppException {
        return null;
    }

//    public boolean canEditAllRequiredFields()
//            throws AppException {
//        return getDefnObject().getEntryForm().canEditAllRequiredFields();
//    }

//----------------------- ACCESSORS -------------------------------------

    public Timestamp getCreationDate()
            throws AppException {
        return (Timestamp) _creationDate.getValue();
    }

    public Oid getCreatorIID()
            throws AppException {
        return _creatorIID;
    }

    public User getCreatorObject()
            throws AppException {
        return (User) getSom().getCompObject(getObjectContext(), "User", _creatorIID);
    }

    public Oid getAccessIID()
            throws AppException {
        return _accessIID;
    }

    public DeleteState getDeleteState()
            throws AppException {
        return _deleteState;
    }

    public boolean hasMessageAttached()
            throws AppException {
        if (_messageAttached.getValue() != null)
            return ((Boolean) _messageAttached.getValue()).booleanValue();
        else
            return false;
    }

    public boolean hasFileAttached()
            throws AppException {
        if (_fileAttached.getValue() != null)
            return ((Boolean) _fileAttached.getValue()).booleanValue();
        else
            return false;
    }

    public boolean hasLinkAttached()
            throws AppException {
        if (_linkAttached.getValue() != null)
            return ((Boolean) _linkAttached.getValue()).booleanValue();
        else
            return false;
    }


    public Collection<HyperLink> getAttachedLinks(boolean edit)
            throws AppException {
        if (hasLinkAttached()) {
            return (Collection<HyperLink>) getSom().getCompObject(getObjectContext(), "HyperLinkList", this.getOid(),
                                                                  edit);
        } else
            return null;
    }

    public SOM getSom() {
        return AppSystem.getCrm();
    }

    public List<DiscussionTopic> getAttachedDiscussionTopics(boolean edit)
            throws AppException {
        if (hasMessageAttached()) {
            return (List<DiscussionTopic>) getSom().getCompObject(getObjectContext(), "DiscussionTopicList", getOid()
                    , edit);
        } else
            return null;
    }

    public Collection<SemanticLink> getChildSemanticLinks(LinkKind type, boolean edit)
            throws AppException {
//        if (type != null)
////            args.put("LINKTYPE", type);
        return (Collection<SemanticLink>) getSom().getCompObject(getObjectContext(), "SemanticLinkColl", getOid(),
                                                                 edit);
    }

    public Collection<SemanticLink> getParentSemanticLinks(LinkKind type, boolean edit)
            throws AppException {
//        RepoMap args = new DataSet();
//        args.setOid(getOid());
//        args.put("PARENTCOL", "DESTOBJECTID");
//        if (type != null)
//            args.put("LINKTYPE", type);
        return (Collection<SemanticLink>) getSom().getCompObject(getObjectContext(), "SemanticLinkColl", getOid(),
                                                                 edit);
    }

    public Collection<Notification> getNotifications(boolean edit)
            throws AppException {
        return (Collection<Notification>) getSom().getCompObject(getObjectContext(), "ObjectNotificationColl", getOid
                (), edit);
    }


    public Collection<RoleAssignment> getRoleAssignments(boolean edit)
            throws AppException {
        return (Collection<RoleAssignment>) getSom().getCompObject(getObjectContext(), "RoleAssignmentColl", getOid()
                , edit);
    }

    public User getSingularRoleAssignedUser(Oid roleiid)
            throws AppException {
        ProcessRole role = (ProcessRole) getSom().getCompObject(getObjectContext(), "Role", roleiid);
        if (role.isMultiUser()) throw new AppException("This role (" + role.getOid() + ") is not a Singular Role.");
        User user = null;
//        RepoMap args = new DataSet();
//        args.setOid(getOid());
//        args.put("roleiid", role.getOid());
//        Collection<RoleAssignment> rac = (Collection<RoleAssignment>) AppSystem.getCrm().getCompObject
// (getObjectContext(), "RoleAssignmentRoleColl", args);
//        if (rac.hasNext())
//            user = rac.nextRoleAssignment().getUserObject();
        return user;
    }


//----------------- BusinessObject Methods ------------------------------------

    /**
     * Returns this product's state machine object
     */
    public StateMachine getStateMachine()
            throws AppException {
        return getDefnObject().getStateMachine();
    }

    /**
     * Returns the transition objects that represent valid transitions for the current state of the object
     */
    public Collection<StateTransition> getEnabledTransitions() throws AppException {
        if (getDefnObject().hasStateMachine())
            return getStateMachine().getEnabledTransitions(this);
        else
            return null;
    }

    public BusinessObject doTransition(StateTransition trans)
            throws AppException {
        return doTransition(trans, "");
    }

    public BusinessObject doTransition(StateTransition trans, String strProcessChangeComment)
            throws AppException {
        getStateMachine().doTransition(this, trans, strProcessChangeComment);
        return this;
    }

    /**
     * Returns the file object to be attached to the bo
     */
    public Attachment attachFile() throws AppException {
        Attachment newAttachment = (Attachment) getSom().getCompObject(getObjectContext(), "File", null, true);
        newAttachment.setDefnObject((Class) getSom().getCompObject(getObjectContext(), "Class", IDCONST.FILE_ATTACHED));
        newAttachment.setParentObject(this);
//        if (this instanceof IFeatureCategoryLink)
//            ((IFeatureCategoryLink) this).getFeatureObject(true).setFileAttached(true);
//        else
        setFileAttached(true);
        return newAttachment;
    }

    /**
     * Returns the hyperlink object to be attached to the bo
     */
    public HyperLink attachLink() throws AppException {
        Oid classIID = IDCONST.HYPERLINK;
        HyperLink newLink = (HyperLink) getSom().getCompObject(getObjectContext(), "HyperLink", null, true);
        newLink.setDefnObject((Class) getSom().getCompObject(getObjectContext(), "Class", classIID));
        newLink.setParentObject(this);
//        if (this instanceof IFeatureCategoryLink)
//            ((IFeatureCategoryLink) this).getFeatureObject(true).setLinkAttached(true);
//        else
        setLinkAttached(true);
        return newLink;
    }

    /**
     * Returns the hyperlink object to be attached to the bo
     */
    public DiscussionTopic attachDiscussionTopic() throws AppException {
        return attachDiscussionTopic(true);
    }


    private DiscussionTopic attachDiscussionTopic(boolean setMessageAttached) throws AppException {
        Oid classIID = IDCONST.DISCUSSIONTOPIC;
        DiscussionTopic newDT = (DiscussionTopic) getSom().getCompObject(getObjectContext(),
                                                                         "DiscussionTopic", null, true);
        newDT.setDefnObject((Class) getSom().getCompObject(getObjectContext(), "Class", classIID));
        newDT.setParObjectIID(getOid());

        newDT.setParObjectType(getDefnObject().getOid());
        //newDT.setParObjectType(IDCONST.getInstance(getDefnObject().getOid()));
        if (setMessageAttached) {
//            if (this instanceof IFeatureCategoryLink)
//                ((IFeatureCategoryLink) this).getFeatureObject(true).setMessageAttached(true);
//            else
            setMessageAttached(true);
        }
        return newDT;
    }

    public Collection<Attachment> getAttachedFiles(boolean edit) throws AppException {
        if (hasFileAttached()) {
//            args.setOid(getIID());
            return (Collection<Attachment>) getSom().getCompObject(getObjectContext(), "FileList", null, edit);
        } else
            return null;
    }


    public void deleteFile(Attachment file) throws AppException {
        file.delete();
        Collection<Attachment> files = getAttachedFiles(false);
        if (files == null || files.size() == 0)
            setFileAttached(false);
    }

    public void deleteLink(HyperLink link) throws AppException {
        link.delete();
        Collection<HyperLink> links = getAttachedLinks(false);
        if (links == null || links.size() == 0)
            setLinkAttached(false);
    }

    public void deleteDiscussionTopic(DiscussionTopic topic) throws AppException {
        topic.delete();

        List<DiscussionTopic> topics = getAttachedDiscussionTopics(false);
        if (topics == null || topics.size() == 0)
            setMessageAttached(false);
    }

    public Notification createNotification() throws AppException {
        Oid classIID = IDCONST.NOTIFICATION;
        Notification newNT = (Notification) getSom().getCompObject(getObjectContext(), "Notification", null, true);
        newNT.setDefnObject((Class) getSom().getCompObject(getObjectContext(), "Class", classIID));
        newNT.setParObjectIID(getOid());
        return newNT;
    }

    public SemanticLink createSemanticLink(BusinessObject dest, LinkKind type) throws AppException {
        SemanticLink newLink = (SemanticLink) getSom().getCompObject(getObjectContext(), "SemanticLink", null, true);
        newLink.setSourceObjectIID(this.getOid());
        newLink.setDestObjectIID(dest.getOid());
        newLink.setLinkType(type);
        return newLink;
    }

    public void removeSemanticLink(BusinessObject dest, LinkKind type) throws AppException {
        Collection<SemanticLink> children = getChildSemanticLinks(type, true);
        for (SemanticLink child : children) {
            if (child.getDestObjectIID().equals(dest.getOid())) {
                // TODO child.delete();
            }
        }
    }

    public void delete() throws AppException {
        //delete attachments
        Collection<Attachment> files = getAttachedFiles(true);
        if (files != null)
            for (Attachment file : files) {
                file.delete();
            }

        // TODO ...

//        IHyperLinkColl links = getAttachedLinks(true);
//        while (links != null && links.hasNext())
//            links.nextHyperLink().delete();
//
//        INotificationColl notes = getNotifications(true);
//        while (notes != null && notes.hasNext())
//            notes.nextNotification().delete();
//
//        Collection<RoleAssignment> roles = getRoleAssignments(true);
//        while (roles != null && roles.hasNext())
//            roles.nextRoleAssignment().delete();
//
//        ISemanticLinkColl parlinks = getParentSemanticLinks(true);
//        while (parlinks != null && parlinks.hasNext())
//            parlinks.nextSemanticLink().delete();
//
//        ISemanticLinkColl childlinks = getChildSemanticLinks(true);
//        while (childlinks != null && childlinks.hasNext())
//            childlinks.nextSemanticLink().delete();
//
//        IDiscussionTopicColl topics = (IDiscussionTopicColl) getSom().getCompObject(getObjectContext(),
// "DiscussionTopicColl", getOid(), true);
//        while (topics != null && topics.hasNext())
//            topics.nextDiscussionTopic().delete();
//
//        IProcessChangeList changes = (IProcessChangeList) getSom().getCompObject(getObjectContext(),
// "ProcessChangeList", getOid(), true);
//        while (changes != null && changes.hasNext())
//            changes.nextProcessChange().delete();

        return;
    }

    public BusinessObject softDelete()
            throws AppException {
        setDeleteState(DeleteState.DELETED);
        return this;
    }

    public BusinessObject recover()
            throws AppException {
        setDeleteState(DeleteState.NOT_DELETED);
        return this;
    }

    public ProcessChange addProcessChange(StateTransition t)
            throws AppException {
        return addProcessChange(t, Comment);
    }

    public ProcessChange addProcessChange(StateTransition t, String comment) throws AppException {
        Repository rep = AppSystem.getRepo();
        //write the process change
        ProcessChange pc = (ProcessChange) getSom().getCompObject(getObjectContext(), "ProcessChange", null, true);
        pc.setChangeObjectIID(getOid());
//        boolean isFeature = true;
//        if (this instanceof IFeatureCategoryLink)
//            pc.setRevisionIID(((IFeatureCategoryLink) this).getFeatureObject().getFeatureRevisionObject().getOid());
//        else
//            isFeature = false;
        pc.setFromUserIID(getObjectContext().getConnection().getUserOid());
        if (t != null)
            pc.setTransitionIID(t.getOid());
        pc.setAckMask(0);
        pc.setComment(comment);
        //write the discussiontopic

        // TODO JC Why do we attache a discussion to an object just cus its going through process change?

        DiscussionTopic dt = attachDiscussionTopic(false);
        dt.setNumChildren(0);
        dt.setEditedDate(new Timestamp(System.currentTimeMillis()));
        dt.isRoot(true);
        dt.setTreeLevel(0);
        String strComment = "" + getName();
        if (t != null)
            strComment += " changed from " + t.getFromStateObject(false).getCodeName() +
                    " to " + t.getToStateObject(false).getCodeName();
        dt.setSubject((t != null ? "" + t.getToStateObject(false).getCodeName() : strComment));
        dt.setBody(comment);

        // TODO Generalize behavior of updating inbox
        boolean updateInbox = true;

        if (updateInbox) {
            updateInbox(pc, strComment);
        }
        return pc;
    }

    private void updateInbox(ProcessChange pc, String strComment) throws AppException {
        //delete the old notifications
        Collection<Notification> ntc = getNotifications(false);
//        ntc.setAsLocked();
        for (Notification notification : ntc) {
            // TODO ntc.delete();
        }
        //write the notifications
        Oid stateiid = getStateObject().getOid();
        Collection<RoleAssignment> rac = (Collection<RoleAssignment>) getSom().getCompObject(getObjectContext(),
                                                                                             "RoleAssignmentColl",
                                                                                             getOid());
        for (RoleAssignment ra : rac) {
            if (ra.getRoleIID().equals(getStateObject().getRoleIID())) {
                if (shouldNotify(stateiid)) {
                    Notification nt = createNotification();
                    nt.setAckMask(0);
                    nt.setRecipientIID(ra.getUserIID());
                    nt.setBody(strComment);
                    nt.setSubject(strComment);
                    pc.addReceiver(ra.getUserObject());
                }
            }
        }
    }

    private boolean shouldNotify(Oid stateiid) {
        // TODO ...
//        return !stateiid.equals(IDCONST.COMPASS.getIIDValue())
//                && !stateiid.equals(IDCONST.MYCONCEPTS.getIIDValue())
//                && !stateiid.equals(IDCONST.DEFERRED.getIIDValue())
//                && !stateiid.equals(IDCONST.DEFINPROGRESSREVIEW.getIIDValue())
//                && !stateiid.equals(IDCONST.MMVERIFIED.getIIDValue());
        return true;
    }

    public BusinessObject interruptProcess(State s, String comment)
            throws AppException {
        StateMachine machine = getStateMachine();
        if (machine.getOid().equals(s.getStateMachineIID())) {
            //write the process change
            ProcessChange pc = (ProcessChange) AppSystem.getCrm().getCompObject(getObjectContext(), "ProcessChange",
                                                                                null, true);
            pc.setChangeObjectIID(getOid());
            if (isVersionableEntity()) {
// TODO                pc.setRevisionIID(((IFeatureCategoryLink) this).getFeatureObject().getFeatureRevisionObject()
// .getOid());
            }
            pc.setFromUserIID(getObjectContext().getConnection().getUserOid());
            pc.setAckMask(0);
            pc.setComment(comment);

            RepoMap args = new RepoMap();
            args.setOid(getOid());
            args.put("roleiid", s.getRoleIID());
            Collection<RoleAssignment> rac =
                    (Collection<RoleAssignment>) getSom().getCompObject(getObjectContext(), "RoleAssignmentRoleColl",
                                                                        null);
            for (RoleAssignment roleAssignment : rac) {
                pc.addReceiver(roleAssignment.getUserObject());
            }

            //write the discussiontopic
            DiscussionTopic dt = attachDiscussionTopic(false);
            dt.setNumChildren(0);
            dt.setEditedDate(new Timestamp(System.currentTimeMillis()));
            dt.isRoot(true);
            dt.setTreeLevel(0);
            String strComment = "" + getName();
            strComment += " changed from " + getStateObject().getCodeName() + " to " + s.getCodeName();
            dt.setSubject(strComment);
            dt.setBody(comment);

            //delete the old notifications
            Collection<Notification> ntc = getNotifications(true);
            for (Notification notification : ntc) {
                notification.delete();
            }

            setStateObject(s);
        } else
            throw new AppException("Not a valid state for this statemachine.");

        return this;
    }

    private boolean isVersionableEntity() {
        // TODO
        return false;
    }

    public Object get(Object key) throws AppException {

        // TODO replace with reflection?

//        if (key instanceof String) {
//            if (key.equals(LABEL_NAME))
//                return _name;
//            else if (key.equals(LABEL_DESCRIPTION))
//                return _description;
//            else if (key.equals(LABEL_CREATIONDATE))
//                return _creationDate;
//            else if (key.equals(LABEL_MESSAGEATTACHED))
//                return _messageAttached;
//            else if (key.equals(LABEL_FILEATTACHED))
//                return _fileAttached;
//            else if (key.equals(LABEL_LINKATTACHED))
//                return _linkAttached;
//            else
//                return _attributes.get(key);
//        } else
        return null;
    }

    public void put(Object key, Object value) throws AppException {

        // TODO replace with reflection?

//        if (key instanceof String && value instanceof BMProperty) {
//            BMProperty property = (BMProperty) value;
//            if (key.equals(LABEL_NAME))
//                setName((String) property.getValue());
//            else if (key.equals(LABEL_DESCRIPTION))
//                setDescription((String) property.getValue());
//            else if (key.equals(LABEL_CREATIONDATE))
//                setCreationDate((Timestamp) property.getValue());
//            else if (key.equals(LABEL_MESSAGEATTACHED))
//                setMessageAttached(((Boolean) property.getValue()).booleanValue());
//            else if (key.equals(LABEL_FILEATTACHED))
//                setFileAttached(((Boolean) property.getValue()).booleanValue());
//            else if (key.equals(LABEL_LINKATTACHED))
//                setLinkAttached(((Boolean) property.getValue()).booleanValue());
//            else
//                _attributes.put(key, value);
//        }
    }


    /**
     * Copies the given list of properties to this product
     */
    public void putAll(Map<Oid, BMProperty> props) throws AppException {
        getAttributes().putAll(props);
    }

    /**
     * Returns a Collection object representing the values for this object
     */
    public Collection<BMProperty> values() throws AppException {
        return getAttributes().values();
    }

    /**
     * Returns a Set of property keys
     */
    public Set<Oid> keySet() throws AppException {
        return getAttributes().keySet();
    }

    /**
     * Returns a Set of property values
     */
    public Set<Map.Entry<Oid, BMProperty>> entries() throws AppException {
        return getAttributes().entrySet();
    }

    /**
     * Returns the Map object that represents the data in the IRPropertyMap
     */
    public Map getMap() {
        return getAttributes();
    }

    /**
     * Returns the name attribute of this bo.
     */
    public String getName() throws AppException {
        return (String) _name.getValue();
    }

    /**
     * Sets the name attribute of this bo
     */
    public Element setName(String name) throws AppException {
        _name.setValue(name);
        return this;
    }

    /**
     * Returns the description attribute of this bo
     */
    public String getDescription() throws AppException {
        return (String) _description.getValue();
    }

    /**
     * Sets the description attribute of this bo
     */
    public Element setDescription(String description) throws AppException {
        _description.setValue(description);
        return this;
    }

    /**
     * Sets the specific class of this bo.
     */
    public BusinessObject setDefnObject(Class newClass) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _classIID = newClass.getOid();
        _class.setValue(newClass.getCodeName());
        // start at the default start state for the class
        if (getDefnObject().hasStateMachine())
            setStateObject(getStateMachine().getDefaultStartStateObject());
        return this;
    }

    /**
     * Returns the class object that defines this product.
     */
    public Class getDefnObject() throws AppException {
        if (_classIID != null)
            return (Class) getSom().getCompObject(getObjectContext(), "Class", _classIID);
        else
            return null;
    }

    /**
     * Returns the state object for the current state of this product.
     */
    public State getStateObject() throws AppException {
        if (getDefnObject().hasStateMachine() && _stateIID != null)
            return getStateMachine().getStateObject(_stateIID, false);
        else
            return null;
    }

    /**
     * Sets the current state of this object.
     */
    public BusinessObject setStateObject(State state)
            throws AppException {
        if (getDefnObject().hasStateMachine()) {
            if (getPersState().equals(PersState.UNMODIFIED))
                setPersState(PersState.MODIFIED);
            _stateIID = state.getOid();
        }
        return this;
    }

    public Map<Oid, BMProperty> getProperties() throws AppException {
        if (getPersState().equals(PersState.PARTIAL)) {
            // TODO ...
//            loadProperties();
//            PoolMgr.getInstance().replace(this);
        }
        return this.getProperties();
    }

    /**
     * The concrete bo must implement this
     */
    abstract public Object dolly() throws AppException;

    /**
     * Pseudo-constructor that expects the Oid of the object and the ObjectContext as args
     */
    public void construct(ObjectContext context, RepoMap args)
            throws AppException {
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

//        if (args != null && args.containsKey(COL_CLASSID))
//            load(args);

        return;
    }

    public BusinessObject grantPermissions(Oid useriid, Set gs) throws AppException {
        try {
            // get a 'system' user context
//        ObjectContext systemContext = new ObjectContext();
//        ICRMConnection conn = AppSystem.getCrm().connect("system", "system");
//        systemContext.setConnection(conn);
            // get the ACM for the system context
//        IAccessMgr acm = AppSystem.getCrm().getAccessMgr(systemContext);

            // JKC 5/30/00: try using normal context instead of 'system'...
            AccessMgr acm = getSom().getAccessMgr(getObjectContext());

            // request the actual grant
            Class cls = this.getDefnObject();
            if (cls != null
                    && PermEnum.areAllValidPermissions(gs, IDCONST.getInstance(cls.getBaseClass().getOid())))
                acm.grantPermissions(useriid.getLongValue(), this, gs);
        } catch (AppException ocu) {
            throw ocu;
        } catch (Exception exp) {
            throw new AppException(exp);
        }
        return this;
    }

    public String getFullTreePathString() throws AppException {
        return "";
    }

    public GrantSet getPermissions() throws AppException {
        return null;
    }

    public void remove(Object key) {

    }

}