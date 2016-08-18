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

import org.jkcsoft.bogey.metamodel.*;
import org.jkcsoft.bogey.metamodel.Class;
import org.jkcsoft.bogey.system.AppException;

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
        _guid = new Guid();
        _name = new BMProperty(this);
        _description = new BMProperty(this);
        _creationDate = new BMProperty(this);
        _messageAttached = new BMProperty(this);
        _linkAttached = new BMProperty(this);
        _fileAttached = new BMProperty(this);
        _class = new BMProperty(this);
        _accessIID = new Oid(0);

        _name.setDefnObject(IDCONST.NAME.getIIDValue());
        _name.setRequired(true);
        _description.setDefnObject(IDCONST.DESC.getIIDValue());
        _creationDate.setDefnObject(IDCONST.CREATION_DATE.getIIDValue());
        _messageAttached.setDefnObject(IDCONST.MESSAGE_ATTACHED.getIIDValue());
        _fileAttached.setDefnObject(IDCONST.FILE_ATTACHED.getIIDValue());
        _linkAttached.setDefnObject(IDCONST.LINK_ATTACHED.getIIDValue());
        _class.setDefnObject(IDCONST.CLASS.getIIDValue());
    }

    //-------------------------- Protected Methods -----------------------------

    private String getLoadPropertiesQuery()
            throws AppException {
        return "SELECT attr.OBJECTID as ATTRIBUTEID, " +
                "		 assoc.ISREQUIRED as ISREQUIRED, " +
                "    blobVal.VALUE as blobValue, " +
                "    boolVal.VALUE as boolValue, " +
                "    charVal.VALUE as charValue, " +
                "    longCharVal.VALUE as longCharValue, " +
                "    timeVal.VALUE as timeValue, " +
                "    enumVal.VALUE as enumValue " +
                " FROM ((\"ATTRIBUTE\" attr LEFT OUTER JOIN INTERFACEATTRASC assoc ON attr.OBJECTID=assoc.ATTRIBUTEID)" +
                " 				LEFT OUTER JOIN \"CLASS\" cls ON assoc.INTERFACEID=cls.DEFINTERFACEID AND cls.OBJECTID=" + getDefnObject().getIID() + ") " +
                "  LEFT OUTER JOIN \"BLOBVALUE\" blobVal ON attr.OBJECTID=blobVal.ATTRIBUTEID " +
                "  LEFT OUTER JOIN \"BOOLEANVALUE\" boolVal ON attr.OBJECTID=boolVal.ATTRIBUTEID " +
                "  LEFT OUTER JOIN \"CHARVALUE\" charVal ON attr.OBJECTID=charVal.ATTRIBUTEID " +
                "  LEFT OUTER JOIN \"ENUMVALUE\" enumVal ON attr.OBJECTID=enumVal.ATTRIBUTEID " +
                "  LEFT OUTER JOIN \"LONGCHARVALUE\" longCharVal ON attr.OBJECTID=longCharVal.ATTRIBUTEID " +
                "  LEFT OUTER JOIN \"TIMEVALUE\" timeVal ON attr.OBJECTID=timeVal.ATTRIBUTEID " +
                "WHERE blobVal.PAROBJECTID = " + getOid() +
                "  OR boolVal.PAROBJECTID = " + getOid() +
                "  OR charVal.PAROBJECTID = " + getOid() +
                "  OR enumVal.PAROBJECTID = " + getOid() +
                "  OR longCharVal.PAROBJECTID = " + getOid() +
                "  OR timeVal.PAROBJECTID = " + getOid();
    }


    private String getLoadPropertiesQuery2()
            throws AppException {
        return " SELECT attr.OBJECTID as ATTRIBUTEID, " +
                "    enumselVal.ENUMLITERALID as enumselValue " +
                " FROM \"ATTRIBUTE\" attr " +
                "  		LEFT OUTER JOIN \"ENUMSELECTION\" enumselVal ON attr.OBJECTID=enumselVal.ATTRIBUTEID " +
                " WHERE enumselVal.PAROBJECTID = " + getOid();
    }


    private void load(IDataSet results)
            throws AppException {
        setPersState(PersState.PARTIAL);
        IRepository repos = getObjectContext().getRepository();
        _guid = new Guid(results.getString(COL_Guid).trim());       // get Guid
        _iid = results.getIID();    // get IID
        _classIID = repos.makeReposID(results.getLong(COL_CLASSID)); // get class
        if (_classIID.getLongValue() != 0)
            _class.setValue(getDefnObject().getName());
//    if (getDefnObject().hasStateMachine())
        _stateIID = repos.makeReposID(results.getLong(COL_STATEID)); // get state
        setDeleteState(DeleteState.getInstance(results.getInt(COL_DELETESTATE))); // get state
        setCreatorIID(repos.makeReposID(results.getLong(COL_CREATORID)));
        setAccessIID(repos.makeReposID(results.getLong(COL_ACCESSID)));
//    setName(results.getString(COL_NAME));                // get name
//    setDescription(results.getString(COL_DESCRIPTION));  // get desc
        setCreationDate(results.getTimestamp(COL_CREATIONDATE));
        setMessageAttached(results.getBoolean(COL_MESSAGEATTACHED));
        setFileAttached(results.getBoolean(COL_FILEATTACHED));
        setLinkAttached(results.getBoolean(COL_LINKATTACHED));

        _deleteState = DeleteState.getInstance(results.getInt(COL_DELETESTATE));
    }


//------------------------ MUTATORS -------------------------------------

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

    public BusinessObject setCreator(IUser creator)
            throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _creatorIID = creator.getIID();
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

    public boolean canEditAllRequiredFields()
            throws AppException {
        return getDefnObject().getEntryForm().canEditAllRequiredFields();
    }

//----------------------- ACCESSORS -------------------------------------

    public Timestamp getCreationDate()
            throws AppException {
        return (Timestamp) _creationDate.getValue();
    }

    public Oid getCreatorIID()
            throws AppException {
        return _creatorIID;
    }

    public IUser getCreatorObject()
            throws AppException {
        return (IUser) getObjectContext().getCRM().getCompObject(getObjectContext(), "User", _creatorIID);
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

    public IFileColl getAttachedFiles()
            throws AppException {
        return getAttachedFiles(false);
    }

    public IFileColl getAttachedFiles(boolean edit)
            throws AppException {
        IDataSet args = new DataSet();
        return getAttachedFiles(args, edit);
    }

    public IHyperLinkColl getAttachedLinks()
            throws AppException {
        return getAttachedLinks(false);
    }

    public IHyperLinkColl getAttachedLinks(boolean edit)
            throws AppException {
        IDataSet args = new DataSet();
        return getAttachedLinks(args, edit);
    }

    public IFileColl getViewableAttachedFiles()
            throws AppException {
        return getViewableAttachedFiles(false);
    }

    public IFileColl getViewableAttachedFiles(boolean edit)
            throws AppException {
        IDataSet args = new DataSet();
        return getViewableAttachedFiles(args, edit);
    }

    public IFileColl getViewableAttachedFiles(IDataSet args)
            throws AppException {
        return getViewableAttachedFiles(args, false);
    }

    public IFileColl getViewableAttachedFiles(IDataSet args, boolean edit)
            throws AppException {
        if (hasFileAttached()) {
            args.setIID(getOid());
            return (IFileColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "ViewableFileList", args, edit);
        } else
            return null;
    }

    public IFileColl getAttachedFiles(IDataSet args)
            throws AppException {
        return getAttachedFiles(args, false);
    }

    public IFileColl getAttachedFiles(IDataSet args, boolean edit)
            throws AppException {
        if (hasFileAttached()) {
            args.setIID(getOid());
            return (IFileColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "FileList", args, edit);
        } else
            return null;
    }

    public IHyperLinkColl getAttachedLinks(IDataSet args)
            throws AppException {
        return getAttachedLinks(args, false);
    }

    public IHyperLinkColl getAttachedLinks(IDataSet args, boolean edit)
            throws AppException {
        if (hasLinkAttached()) {
            args.setIID(getOid());
            return (IHyperLinkColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "HyperLinkList", args, edit);
        } else
            return null;
    }

    public IDiscussionTopicList getAttachedDiscussionTopics()
            throws AppException {
        return getAttachedDiscussionTopics(false);
    }

    public IDiscussionTopicList getAttachedDiscussionTopics(boolean edit)
            throws AppException {
        IDataSet args = new DataSet();
        return getAttachedDiscussionTopics(args, edit);
    }

    public IDiscussionTopicList getAttachedDiscussionTopics(IDataSet args)
            throws AppException {
        return getAttachedDiscussionTopics(args, false);
    }


    public IDiscussionTopicList getAttachedDiscussionTopics(IDataSet args, boolean edit)
            throws AppException {
        if (hasMessageAttached()) {
            args.setIID(getOid());
            return (IDiscussionTopicList) getObjectContext().getCRM().getCompObject(getObjectContext(), "DiscussionTopicList", args, edit);
        } else
            return null;
    }

    public BusinessObject copyAttachmentsOf(BusinessObject source)
            throws AppException {
        IFileColl files = source.getAttachedFiles();
        while (files != null && files.hasMoreFiles()) {
            IAttachment newFile = files.nextFile().createCopy();
            newFile.setParentObject(this);
        }
        setFileAttached(source.hasFileAttached());
        return this;
    }

    public BusinessObject copyHyperLinksOf(BusinessObject source)
            throws AppException {
        IHyperLinkColl links = source.getAttachedLinks();
        while (links != null && links.hasMoreHyperLinks()) {
            IHyperLink newLink = links.nextHyperLink().createCopy();
            newLink.setParentObject(this);
        }
        setLinkAttached(source.hasLinkAttached());
        return this;
    }

    public BusinessObject copyDiscussionTopicsOf(BusinessObject source)
            throws AppException {

        IDiscussionTopicList topics = source.getAttachedDiscussionTopics();
        System.out.println("got into business");
        IDCONST idconst = null;
        long rootlong = getDefnObject().getRootDefinition().getIID().getLongValue();
        if (rootlong == IDCONST.ISTANDARDINPUT.getLongValue())
            idconst = IDCONST.STANDARDINPUT;
        if (rootlong == IDCONST.IARTICLEINPUT.getLongValue())
            idconst = IDCONST.ARTICLEINPUT;
        if (rootlong == IDCONST.IQUESTIONINPUT.getLongValue())
            idconst = IDCONST.QUESTIONINPUT;
        if (rootlong == IDCONST.IPROBLEMSTATEMENT.getLongValue())
            idconst = IDCONST.PROBLEMSTATEMENT;
        if (rootlong == IDCONST.IFEATURE.getLongValue())
            idconst = IDCONST.FEATURE;
        if (rootlong == IDCONST.IPRODUCTVERSION.getLongValue())
            idconst = IDCONST.PRODUCTVERSION;
        if (rootlong == IDCONST.IPRODUCT.getLongValue())
            idconst = IDCONST.PRODUCT;
        if (rootlong == IDCONST.ICATEGORY.getLongValue())
            idconst = IDCONST.CATEGORY;
        if (rootlong == IDCONST.IFEATURECATEGORYLINK.getLongValue())
            idconst = IDCONST.FEATURECATEGORYLINK;
        if (rootlong == IDCONST.ISTDFEATURELINK.getLongValue())
            idconst = IDCONST.STDFEATURELINK;
        if (rootlong == IDCONST.IALTERNATIVE.getLongValue())
            idconst = IDCONST.ALTERNATIVE;
        if (rootlong == IDCONST.IREACTION.getLongValue())
            idconst = IDCONST.REACTION;

        while (topics != null && topics.hasMoreDiscussionTopics()) {
            IDiscussionTopic newTopic = topics.nextDiscussionTopic().createCopy();
            newTopic.setParObjectIID(getOid());
            newTopic.setParObjectType(idconst);
        }
        setMessageAttached(source.hasMessageAttached());
        return this;
    }


    public ISemanticLinkColl getChildSemanticLinks()
            throws AppException {
        return getChildSemanticLinks(null, false);
    }

    public ISemanticLinkColl getChildSemanticLinks(boolean edit)
            throws AppException {
        return getChildSemanticLinks(null, edit);
    }

    public ISemanticLinkColl getChildSemanticLinks(LinkKind type)
            throws AppException {
        return getChildSemanticLinks(type, false);
    }

    public ISemanticLinkColl getChildSemanticLinks(LinkKind type, boolean edit)
            throws AppException {
        IDataSet args = new DataSet();
        args.setIID(getOid());
        args.put("PARENTCOL", "SRCOBJECTID");
        if (type != null)
            args.put("LINKTYPE", type);
        return (ISemanticLinkColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "SemanticLinkColl", args, edit);
    }

    public ISemanticLinkColl getParentSemanticLinks()
            throws AppException {
        return getParentSemanticLinks(null, false);
    }

    public ISemanticLinkColl getParentSemanticLinks(boolean edit)
            throws AppException {
        return getParentSemanticLinks(null, edit);
    }

    public ISemanticLinkColl getParentSemanticLinks(LinkKind type)
            throws AppException {
        return getParentSemanticLinks(type, false);
    }

    public ISemanticLinkColl getParentSemanticLinks(LinkKind type, boolean edit)
            throws AppException {
        IDataSet args = new DataSet();
        args.setIID(getOid());
        args.put("PARENTCOL", "DESTOBJECTID");
        if (type != null)
            args.put("LINKTYPE", type);
        return (ISemanticLinkColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "SemanticLinkColl", args, edit);
    }

    public INotificationColl getNotifications()
            throws AppException {
        return getNotifications(false);
    }

    public INotificationColl getNotifications(boolean edit)
            throws AppException {
        return (INotificationColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "ObjectNotificationColl", getOid(), edit);
    }

    public IRoleAssignmentColl getRoleAssignments()
            throws AppException {
        return getRoleAssignments(false);
    }

    public IRoleAssignmentColl getRoleAssignments(boolean edit)
            throws AppException {
        return (IRoleAssignmentColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "RoleAssignmentColl", getOid(), edit);
    }

    public IUser getSingularRoleAssignedUser(Oid roleiid)
            throws AppException {
        IProcessRole role = (IProcessRole) getObjectContext().getCRM().getCompObject(getObjectContext(), "Role", roleiid);
        if (role.isMultiUser()) throw new AppException("This role (" + role.getIID() + ") is not a Singular Role.");
        IUser user = null;
        IDataSet args = new DataSet();
        args.setIID(getOid());
        args.put("roleiid", role.getIID());
        IRoleAssignmentColl rac = (IRoleAssignmentColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "RoleAssignmentRoleColl", args);
        if (rac.hasNext())
            user = rac.nextRoleAssignment().getUserObject();
        return user;
    }


//----------------- BusinessObject Methods ------------------------------------

    /**
     * Returns this product's state machine object
     */
    public IRStateMachine getStateMachine()
            throws AppException {
        return getDefnObject().getStateMachine();
    }

    /**
     * Returns the transition objects that represent valid transitions for the current state of the object
     */
    public IRTransitionColl getEnabledTransitions()
            throws AppException {
        if (getDefnObject().hasStateMachine())
            return getStateMachine().getEnabledTransitions(this);
        else
            return null;
    }

    public BusinessObject doTransition(IRTransition trans)
            throws AppException {
        return doTransition(trans, "");
    }

    public BusinessObject doTransition(IRTransition trans, String strProcessChangeComment)
            throws AppException {
        getStateMachine().doTransition(this, trans, strProcessChangeComment);
        return this;
    }

    /**
     * Returns the file object to be attached to the bo
     */
    public IAttachment attachFile()
            throws AppException {
        IAttachment newAttachment = (IAttachment) getObjectContext().getCRM().getCompObject(getObjectContext(), "File", (IDataSet) null, true);
        newAttachment.setDefnObject((Class) getObjectContext().getCRM().getCompObject(getObjectContext(), "Class", IDCONST.FILE.getIIDValue()));
        newAttachment.setParentObject(this);
        if (this instanceof IFeatureCategoryLink)
            ((IFeatureCategoryLink) this).getFeatureObject(true).setFileAttached(true);
        else
            setFileAttached(true);
        return newAttachment;
    }

    /**
     * Returns the hyperlink object to be attached to the bo
     */
    public IHyperLink attachLink()
            throws AppException {
        Oid classIID = IDCONST.HYPERLINK.getIIDValue();
        IHyperLink newLink = (IHyperLink) getObjectContext().getCRM().getCompObject(getObjectContext(), "HyperLink", (IDataSet) null, true);
        newLink.setDefnObject((Class) getObjectContext().getCRM().getCompObject(getObjectContext(), "Class", classIID));
        newLink.setParentObject(this);
        if (this instanceof IFeatureCategoryLink)
            ((IFeatureCategoryLink) this).getFeatureObject(true).setLinkAttached(true);
        else
            setLinkAttached(true);
        return newLink;
    }

    /**
     * Returns the hyperlink object to be attached to the bo
     */
    public IDiscussionTopic attachDiscussionTopic()
            throws AppException {
        return attachDiscussionTopic(true);
    }


    private IDiscussionTopic attachDiscussionTopic(boolean setMessageAttached)
            throws AppException {
        Oid classIID = IDCONST.DISCUSSIONTOPIC.getIIDValue();
        IDiscussionTopic newDT = (IDiscussionTopic) getObjectContext().getCRM().getCompObject(getObjectContext(), "DiscussionTopic", (IDataSet) null, true);
        newDT.setDefnObject((Class) getObjectContext().getCRM().getCompObject(getObjectContext(), "Class", classIID));
        newDT.setParObjectIID(getOid());

        IDCONST idconst = null;
        long rootlong = getDefnObject().getRootDefinition().getIID().getLongValue();
        if (rootlong == IDCONST.ISTANDARDINPUT.getLongValue())
            idconst = IDCONST.STANDARDINPUT;
        if (rootlong == IDCONST.IARTICLEINPUT.getLongValue())
            idconst = IDCONST.ARTICLEINPUT;
        if (rootlong == IDCONST.IQUESTIONINPUT.getLongValue())
            idconst = IDCONST.QUESTIONINPUT;
        if (rootlong == IDCONST.IPROBLEMSTATEMENT.getLongValue())
            idconst = IDCONST.PROBLEMSTATEMENT;
        if (rootlong == IDCONST.IFEATURE.getLongValue())
            idconst = IDCONST.FEATURE;
        if (rootlong == IDCONST.IPRODUCTVERSION.getLongValue())
            idconst = IDCONST.PRODUCTVERSION;
        if (rootlong == IDCONST.IPRODUCT.getLongValue())
            idconst = IDCONST.PRODUCT;
        if (rootlong == IDCONST.ICATEGORY.getLongValue())
            idconst = IDCONST.CATEGORY;
        if (rootlong == IDCONST.IFEATURECATEGORYLINK.getLongValue())
            idconst = IDCONST.FEATURECATEGORYLINK;
        if (rootlong == IDCONST.ISTDFEATURELINK.getLongValue())
            idconst = IDCONST.STDFEATURELINK;
        if (rootlong == IDCONST.IALTERNATIVE.getLongValue())
            idconst = IDCONST.ALTERNATIVE;
        if (rootlong == IDCONST.IREACTION.getLongValue())
            idconst = IDCONST.REACTION;

        newDT.setParObjectType(idconst);
        //newDT.setParObjectType(IDCONST.getInstance(getDefnObject().getOid()));
        if (setMessageAttached) {
            if (this instanceof IFeatureCategoryLink)
                ((IFeatureCategoryLink) this).getFeatureObject(true).setMessageAttached(true);
            else
                setMessageAttached(true);
        }
        return newDT;
    }

    public void deleteFile(IAttachment file)
            throws AppException {
        file.delete();

        IFileColl files = getAttachedFiles();
        if (files == null || !files.hasNext())
            setFileAttached(false);
    }

    public void deleteLink(IHyperLink link)
            throws AppException {
        link.delete();

        IHyperLinkColl links = getAttachedLinks();
        if (links == null || !links.hasNext())
            setLinkAttached(false);
    }

    public void deleteDiscussionTopic(IDiscussionTopic topic)
            throws AppException {
        topic.delete();

        IDiscussionTopicList topics = getAttachedDiscussionTopics();
        if (topics == null || !topics.hasNext())
            setMessageAttached(false);
    }


    public INotification createNotification()
            throws AppException {
        Oid classIID = IDCONST.NOTIFICATION.getIIDValue();
        INotification newNT = (INotification) getObjectContext().getCRM().getCompObject(getObjectContext(), "Notification", (IDataSet) null, true);
        newNT.setDefnObject((Class) getObjectContext().getCRM().getCompObject(getObjectContext(), "Class", classIID));
        newNT.setParObjectIID(getOid());
        return newNT;
    }

    public ISemanticLink createSemanticLink(BusinessObject dest, LinkKind type)
            throws AppException {
        ISemanticLink newLink = (ISemanticLink) getObjectContext().getCRM().getCompObject(getObjectContext(), "SemanticLink", (IDataSet) null, true);
        newLink.setSourceObjectIID(this.getOid());
        newLink.setDestObjectIID(dest.getOid());
        newLink.setLinkType(type);
        return newLink;
    }

    public void removeSemanticLink(BusinessObject dest, LinkKind type)
            throws AppException {
        ISemanticLinkColl children = getChildSemanticLinks(type, true);
        while (children.hasMoreSemanticLinks()) {
            ISemanticLink child = children.nextSemanticLink();
            if (child.getDestObjectIID().equals(dest.getOid()))
                child.delete();
        }
    }

    public IPersistable delete()
            throws AppException {
        //delete attachments
        IFileColl files = getAttachedFiles(true);
        while (files != null && files.hasNext())
            files.nextFile().delete();

        IHyperLinkColl links = getAttachedLinks(true);
        while (links != null && links.hasNext())
            links.nextHyperLink().delete();

        INotificationColl notes = getNotifications(true);
        while (notes != null && notes.hasNext())
            notes.nextNotification().delete();

        IRoleAssignmentColl roles = getRoleAssignments(true);
        while (roles != null && roles.hasNext())
            roles.nextRoleAssignment().delete();

        ISemanticLinkColl parlinks = getParentSemanticLinks(true);
        while (parlinks != null && parlinks.hasNext())
            parlinks.nextSemanticLink().delete();

        ISemanticLinkColl childlinks = getChildSemanticLinks(true);
        while (childlinks != null && childlinks.hasNext())
            childlinks.nextSemanticLink().delete();

        IDiscussionTopicColl topics = (IDiscussionTopicColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "DiscussionTopicColl", getOid(), true);
        while (topics != null && topics.hasNext())
            topics.nextDiscussionTopic().delete();

        IProcessChangeList changes = (IProcessChangeList) getObjectContext().getCRM().getCompObject(getObjectContext(), "ProcessChangeList", getOid(), true);
        while (changes != null && changes.hasNext())
            changes.nextProcessChange().delete();

        super.delete();
        return this;
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

    public IProcessChange addProcessChange(IRTransition t)
            throws AppException {
        return addProcessChange(t, Comment);
    }//

    public IProcessChange addProcessChange(IRTransition t, String comment)
            throws AppException {
        IRepository rep = getObjectContext().getRepository();
        //write the process change
        IProcessChange pc = (IProcessChange) getObjectContext().getCRM().getCompObject(getObjectContext(), "ProcessChange", (IDataSet) null, true);
        pc.setChangeObjectIID(getOid());
        boolean isFeature = true;
        if (this instanceof IFeatureCategoryLink)
            pc.setRevisionIID(((IFeatureCategoryLink) this).getFeatureObject().getFeatureRevisionObject().getIID());
        else
            isFeature = false;
        pc.setFromUserIID(getObjectContext().getConnection().getUserIID());
        if (t != null)
            pc.setTransitionIID(t.getIID());
        pc.setAckMask(0);
        pc.setComment(comment);
        //write the discussiontopic
        IDiscussionTopic dt = attachDiscussionTopic(false);
        dt.setNumChildren(0);
        dt.setEditedDate(new Timestamp(System.currentTimeMillis()));
        dt.isRoot(true);
        dt.setTreeLevel(0);
        dt.setTopicKind(TopicKind.TRANSACTIONCOMMENT);//
        String strComment = "" + getName();
        if (t != null)
            strComment += " changed from " + t.getFromStateObject(false).getName() + " to " + t.getToStateObject(false).getName();
        dt.setSubject((t != null ? "" + t.getToStateObject(false).getName() : strComment));
        dt.setBody(comment);
        if (isFeature) {
            updateInbox(pc, strComment);
        }//end if
        return pc;
    }//

    private void updateInbox(IProcessChange pc, String strComment) throws AppException {
        //delete the old notifications
        INotificationColl ntc = getNotifications();
        ntc.setAsLocked();
        while (ntc.hasNext())
            ntc.nextNotification().delete();
        //write the notifications
        Oid stateiid = getStateObject().getIID();
        IRoleAssignmentColl rac = (IRoleAssignmentColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "RoleAssignmentColl", getOid());
        while (rac.hasNext()) {
            IRoleAssignment ra = rac.nextRoleAssignment();
            if (ra.getRoleIID().equals(getStateObject().getRoleIID())) {
                if (!stateiid.equals(IDCONST.COMPASS.getIIDValue())
                        && !stateiid.equals(IDCONST.MYCONCEPTS.getIIDValue())
                        && !stateiid.equals(IDCONST.DEFERRED.getIIDValue())
                        && !stateiid.equals(IDCONST.DEFINPROGRESSREVIEW.getIIDValue())
                        && !stateiid.equals(IDCONST.MMVERIFIED.getIIDValue())) {
                    INotification nt = createNotification();
                    nt.setAckMask(0);
                    nt.setRecipientIID(ra.getUserIID());
                    nt.setBody(strComment);
                    nt.setSubject(strComment);
                    nt.setNotificationKind(NotificationKind.WORKFLOW);
                    pc.addReceiver(ra.getUserObject());
                }//end if
            }//end if
        }//end while
    }

    public BusinessObject interruptProcess(State s, String comment)
            throws AppException {
        IRStateMachine machine = getStateMachine();
        if (machine.getIID().equals(s.getStateMachineIID())) {
            //write the process change
            IProcessChange pc = (IProcessChange) CRM.getInstance().getCompObject(getObjectContext(), "ProcessChange", (IDataSet) null, true);
            pc.setChangeObjectIID(getOid());
            if (this instanceof IFeatureCategoryLink)
                pc.setRevisionIID(((IFeatureCategoryLink) this).getFeatureObject().getFeatureRevisionObject().getIID());
            pc.setFromUserIID(getObjectContext().getConnection().getUserIID());
            pc.setAckMask(0);
            pc.setComment(comment);

            IDataSet args = new DataSet();
            args.setIID(getOid());
            args.put("roleiid", s.getRoleIID());
            IRoleAssignmentColl rac = (IRoleAssignmentColl) getObjectContext().getCRM().getCompObject(getObjectContext(), "RoleAssignmentRoleColl", args);
            while (rac.hasNext())
                pc.addReceiver(rac.nextRoleAssignment().getUserObject());


            //write the discussiontopic
            IDiscussionTopic dt = attachDiscussionTopic(false);
            dt.setNumChildren(0);
            dt.setEditedDate(new Timestamp(System.currentTimeMillis()));
            dt.isRoot(true);
            dt.setTreeLevel(0);
            dt.setTopicKind(TopicKind.TRANSACTIONCOMMENT);//
            String strComment = "" + getName();
            strComment += " changed from " + getStateObject().getName() + " to " + s.getName();
            dt.setSubject(strComment);
            dt.setBody(comment);

            //delete the old notifications
            INotificationColl ntc = getNotifications();
            ntc.setAsLocked();
            while (ntc.hasNext())
                ntc.nextNotification().delete();

            setStateObject(s);
        } else
            throw new AppException("Not a valid state for this statemachine.");

        return this;
    }

    //----------------- IRPropertyMap Methods -------------------------------
    public Object get(Object key)
            throws AppException {
        if (key instanceof String) {
            if (key.equals(LABEL_NAME))
                return _name;
            else if (key.equals(LABEL_DESCRIPTION))
                return _description;
            else if (key.equals(LABEL_CREATIONDATE))
                return _creationDate;
            else if (key.equals(LABEL_MESSAGEATTACHED))
                return _messageAttached;
            else if (key.equals(LABEL_FILEATTACHED))
                return _fileAttached;
            else if (key.equals(LABEL_LINKATTACHED))
                return _linkAttached;
            else
                return _attributes.get(key);
        } else
            return null;
    }

    public void put(Object key, Object value)
            throws AppException {
        if (key instanceof String && value instanceof BMProperty) {
            BMProperty property = (BMProperty) value;
            if (key.equals(LABEL_NAME))
                setName((String) property.getValue());
            else if (key.equals(LABEL_DESCRIPTION))
                setDescription((String) property.getValue());
            else if (key.equals(LABEL_CREATIONDATE))
                setCreationDate((Timestamp) property.getValue());
            else if (key.equals(LABEL_MESSAGEATTACHED))
                setMessageAttached(((Boolean) property.getValue()).booleanValue());
            else if (key.equals(LABEL_FILEATTACHED))
                setFileAttached(((Boolean) property.getValue()).booleanValue());
            else if (key.equals(LABEL_LINKATTACHED))
                setLinkAttached(((Boolean) property.getValue()).booleanValue());
            else
                _attributes.put(key, value);
        }
    }


    /**
     * Copys the given list of properties to this product
     */
    public void putAll(IRPropertyMap props) throws AppException {
        _attributes.putAll(props, this);
    }

    /**
     * Copys the given list of properties to this product
     */
    public void putAll(IRPropertyMap props, IRObject obj) throws AppException {
        _attributes.putAll(props, obj);
    }

    /**
     * Returns a Collection object representing the values for this product
     */
    public Collection values()
            throws AppException {
        return _attributes.values();
    }

    /**
     * Returns a Set of property keys
     */
    public Set keys()
            throws AppException {
        return _attributes.keys();
    }

    /**
     * Returns a Set of property values
     */
    public Set entries()
            throws AppException {
        return _attributes.entries();
    }

    /**
     * Returns true if this product contains an attribute with the given key
     */
    public boolean containsKey(Object key) {
        return _attributes.containsKey(key);
    }

    /**
     * Returns the number of attributes for this bo
     */
    public int size() {
        return _attributes.size();
    }

    /**
     * Returns the Map object that represents the data in the IRPropertyMap
     */
    public Map getMap() {
        return _attributes.getMap();
    }


//----------------- IRObject Methods ------------------------------------

    /**
     * Returns the name attribute of this bo.
     */
    public String getName()
            throws AppException {
        return (String) _name.getValue();
    }

    /**
     * Sets the name attribute of this bo
     */
    public Element setName(String name)
            throws AppException {
        _name.setValue(name);
        return this;
    }

    /**
     * Returns the description attribute of this bo
     */
    public String getDescription()
            throws AppException {
        return (String) _description.getValue();
    }

    /**
     * Sets the description attribute of this bo
     */
    public Element setDescription(String description)
            throws AppException {
        _description.setValue(description);
        return this;
    }

    /**
     * Sets the specific class of this bo.
     */
    public BusinessObject setDefnObject(Class newClass)
            throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _classIID = newClass.getIID();
        _class.setValue(newClass.getName());
        // start at the default start state for the class
        if (getDefnObject().hasStateMachine())
            setStateObject(getStateMachine().getDefaultStartStateObject());
        // get the attributes that correspond to this class
        _attributes = newClass.getAttributes(this);
        return this;
    }

    /**
     * Returns the class object that defines this product.
     */
    public Class getDefnObject()
            throws AppException {
        if (_classIID != null)
            return (Class) getObjectContext().getCRM().getCompObject(getObjectContext(), "Class", _classIID);
        else
            return null;
    }

    /**
     * Returns the state object for the current state of this product.
     */
    public State getStateObject()
            throws AppException {
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
            _stateIID = state.getIID();
        }
        return this;
    }

    /**
     * This returns an IRPropertyMap for this product
     */
    public IRPropertyMap getProperties()
            throws AppException {
        if (getPersState().equals(PersState.PARTIAL)) {
            loadProperties();
            PoolMgr.getInstance().replace(this);
        }
        return this;
    }

//----------------- IRPersistable Methods ------------------------------------

    public IPersistable save()
            throws AppException {
        if (getPersState().equals(PersState.NEW)) {
            setCreationDate(new Timestamp(System.currentTimeMillis()));
            setCreatorIID(getObjectContext().getConnection().getUserIID());

            // create the grant set
            Set gs = new HashSet();
            gs.add(PermEnum.OWNER);
            // request the actual grant
            grantPermissions(getCreatorIID(), gs);
        }
        return super.save();
    }

//----------------- IPoolable Methods ------------------------------------

    /**
     * The concrete bo must implement this
     */
    abstract public Object dolly() throws AppException;

    /**
     * Pseudo-constructor that expects the Oid of the object and the ObjectContext as args
     */
    public IPoolable construct(ObjectContext context, IDataSet args)
            throws AppException {
        Oid iid = null;

        if (context == null)
            throw new AppException("Context Argument expected.");
        setObjectContext(context);

        if (args == null) {
            iid = getObjectContext().getRepository().genReposID();
            setPersState(PersState.NEW);
        } else {
            setPersState(PersState.UNINITED);
            iid = args.getIID();
        }
        setOid(iid);

        if (args != null && args.containsKey(COL_CLASSID))
            load(args);

        return this;
    }

    // This method will add any attribute to the object that were added to the form
    // after the object was created.
    private void _resyncProps(IRModelElementList list) throws AppException {
        IRPropertyMap props = this.getProperties();
        IQueryProcessor stmt = null;
        IRConnection jdtC = null;
        try {
            jdtC = getObjectContext().getRepository().getDataConnection(getObjectContext());
            stmt = jdtC.createProcessor();

            IDataSet results = stmt.retrieve("SELECT attr.OBJECTID as ATTRIBUTEID, " +
                    "		 assoc.ISREQUIRED as ISREQUIRED " +
                    " FROM (\"ATTRIBUTE\" attr LEFT OUTER JOIN INTERFACEATTRASC assoc ON attr.OBJECTID=assoc.ATTRIBUTEID)" +
                    " 				LEFT OUTER JOIN \"CLASS\" cls ON assoc.INTERFACEID=cls.DEFINTERFACEID " +
                    "WHERE cls.OBJECTID = " + getDefnObject().getIID());
            while (results.next()) {
                BMProperty thisProperty = new BMProperty(this);
                //Saleem: I added the next two lines
                Oid id1 = new Oid(results.getLong("ATTRIBUTEID"));
                String key = "prop" + id1;
                if (props.get(key) == null) {
                    BMProperty newProp = new BMProperty(this);
                    newProp.setDefnObject(id1);
                    newProp.load(results);
                    newProp.setPersState(PersState.NEW);
                    props.put(key, newProp);
                }
            }
        } finally {
            if (stmt != null) stmt.close();
//	  	getObjectContext().getCRM().returnDatabaseConnection(jdtC);
        }
    }


    public void renderCustomView(IAttributeTable table)
            throws AppException {
        Class cls = this.getDefnObject();
        if (cls == null) throw new AppException("Object does not have a corresponding Class object.");
        IREntryForm frm = cls.getEntryForm();
        if (frm == null) throw new AppException("Class does not have a corresponding EntryForm object.");
        IRModelElementList customs = frm.getViewableAttributeList();
        _resyncProps(customs);
        IRPropertyMap props = this.getProperties();
        customs.reset();
        if (customs.hasNext()) {
            while (customs.hasNext()) {
                IRAttribute custom = (IRAttribute) customs.next();
                String key = "prop" + custom.getIID();
                BMProperty prop = (BMProperty) props.get(key);
                if (prop != null)
                    prop.renderView(table);
                else
                    table.addAttribute(custom.getName() + ":", "(undefined)");
            }
        }
    }

    public void renderCustomEdit(IAttributeTable table)
            throws AppException {
        Class cls = this.getDefnObject();
        if (cls == null) throw new AppException("Object does not have a corresponding Class object.");
        IREntryForm frm = cls.getEntryForm();
        if (frm == null) throw new AppException("Class does not have a corresponding EntryForm object.");
        IRModelElementList customs = frm.getViewableAttributeList();
        _resyncProps(customs);
        IRPropertyMap props = this.getProperties();
        customs.reset();
        if (customs.hasNext()) {
            while (customs.hasNext()) {
                IRAttribute custom = (IRAttribute) customs.next();
                String key = "prop" + custom.getIID();
                BMProperty prop = (BMProperty) props.get(key);
                if (prop != null) {
                    AttrGroupOper oper = custom.getUserPermission();
                    if (oper != null && oper.equals(AttrGroupOper.EDIT))
                        prop.renderEdit(table);
                    else if (oper != null && oper.equals(AttrGroupOper.VIEW))
                        prop.renderView(table);
                } else
                    table.addAttribute(custom.getName() + ":", "(undefined)");
            }
        }
    }

    public IRObject grantPermissions(Oid useriid, Set gs) throws AppException {
        try {
            // get a 'system' user context
//        ObjectContext systemContext = new ObjectContext();
//        ICRMConnection conn = getObjectContext().getCRM().connect("system", "system");
//        systemContext.setConnection(conn);
            // get the ACM for the system context
//        IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(systemContext);

            // JKC 5/30/00: try using normal context instead of 'system'...
            com.oculussoftware.api.sysi.sec.IAccessMgr acm = getObjectContext().getCRM().getAccessMgr(getObjectContext());

            // request the actual grant
            Class cls = this.getDefnObject();
            if (cls != null
                    && PermEnum.areAllValidPermissions(gs, IDCONST.getInstance(cls.getBaseClass().getIID())))
                acm.grantPermissions(useriid.getLongValue(), this, gs);
        }//end try
        catch (AppException ocu) {
            throw ocu;
        } catch (Exception exp) {
            throw new AppException(exp);
        }
        return this;
    }

    public String getFullTreePathString()
            throws AppException {
        return "";
    }

    public IGrantSet getPermissions()
            throws AppException {
        return null;
    }

    public boolean isVisible(ModuleKind module, SettingsMgr settings, IGrantSet grant)
            throws AppException {
        return false;
    }

    public void remove(Object key) {
    }
}