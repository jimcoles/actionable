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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Attachment extends BusinessObject {

    private Oid _categoryIID, _parentObjIID;
    private byte[] _content;
    private BMProperty _filesize, _filename, _viewable;

    //--------------------------- Public Constructors --------------------------

    /**
     * Default constructor set the state to NEW and gets a list of empty properties
     */
    public Attachment() throws AppException {
        super();
        _filesize = new BMProperty(this);
        _filename = new BMProperty(this);
        _viewable = new BMProperty(this);

//        _filesize.setDefnObject(IDCONST.FILESIZE.getIIDValue());
//        _filename.setDefnObject(IDCONST.FILENAME.getIIDValue());
//        _viewable.setDefnObject(IDCONST.FILEVIEWABLE.getIIDValue());
        _categoryIID = new Oid(1);
    }


    public Attachment setViewable(boolean viewable) throws AppException {
        _viewable.setValue(new Boolean(viewable));
        return this;
    }

    public boolean getViewable() throws AppException {
        if (_viewable != null && _viewable.getValue() != null)
            return ((Boolean) _viewable.getValue()).booleanValue();
        else
            return false;
    }

    public void download() throws AppException {
        try {
            byte[] data = getContent();
            String dest = ".\\public_html\\files\\" + getFilename();
            OutputStream osFileOutput = new FileOutputStream(dest);
            // Read-Write the file byte by byte
            int filesize = getFileSize();
            for (int i = 0; i < filesize; i++)
                if (osFileOutput != null)
                    osFileOutput.write(data[i]);
            if (osFileOutput != null)
                osFileOutput.close();
        } catch (IOException exp) {
            throw new AppException(exp);
        }
    }


    //------------------------ MUTATORS -------------------------------
    public Attachment setCategoryCode(Oid categoryCode)
            throws AppException {
        _categoryIID = categoryCode;
        return this;
    }

    public Attachment setFileSize(int bytes)
            throws AppException {
        _filesize.setValue(new Integer(bytes));
        return this;
    }

    public Attachment setContent(byte[] content)
            throws AppException {
        _content = content;
        return this;
    }

    public Attachment setContent(InputStream content)
            throws AppException {
        try {
            content.reset();
//      setFileSize(content.available());
            _content = new byte[getFileSize()];
            int i = 0;
            for (int result = content.read(); i < getFileSize(); result = content.read())
                _content[i++] = (byte) result;
            content.close();
        } catch (IOException exp) {
            throw new AppException(exp);
        }
        return this;
    }

    public Attachment setFilename(String filename)
            throws AppException {
        _filename.setValue(filename);
        return this;
    }

    public Attachment setParentObject(BusinessObject parentBObj)
            throws AppException {
        return setParentObjectIID(parentBObj.getOid());
    }

    public Attachment setParentObjectIID(Oid parobjiid)
            throws AppException {
        _parentObjIID = parobjiid;
        return this;
    }

    public Oid getCategoryCode() throws AppException {
        return _categoryIID;
    }

    public int getFileSize() throws AppException {
        if (_filesize.getValue() != null)
            return ((Integer) _filesize.getValue()).intValue();
        else
            return -1;
    }

    public byte[] getContent() throws AppException {
        return _content;
    }

    public String getFilename() throws AppException {
        return (String) _filename.getValue();
    }


    public Attachment createCopy() throws AppException {
        Attachment newAttachment = (Attachment)
                getObjectContext().getCRM().getCompObject(getObjectContext(), "File", null, true);
        newAttachment.setDefnObject((Class)
                getObjectContext().getCRM().getCompObject(getObjectContext(), "Class", IDCONST.FILE));

        newAttachment.setFilename(getFilename());
        newAttachment.setDescription(getDescription());
        newAttachment.setContent(getContent());
        newAttachment.setMessageAttached(hasMessageAttached());
        newAttachment.setLinkAttached(hasLinkAttached());
        newAttachment.setFileAttached(hasFileAttached());
        newAttachment.setCategoryCode(getCategoryCode());
        newAttachment.setFileSize(getFileSize());

        return newAttachment;
    }

    public Object get(Object key)
            throws AppException {
        if (key instanceof String) {
            if (key.equals(""))
                return _filesize;
            if (key.equals(""))
                return _filesize;
            else
                return super.get(key);
        }
        return null;
    }

    public void put(Object key, Object value)
            throws AppException {
        if (key instanceof String && value instanceof BMProperty) {
            BMProperty property = (BMProperty) value;
            if (key.equals(""))
                setFileSize(((Integer) property.getValue()).intValue());
            if (key.equals(""))
                setFilename((String) property.getValue());
            else
                super.put(key, value);
        }
    }

//----------------- IPoolable Methods ------------------------------------

    /**
     * Returns a copy of the current product object.
     * <p>
     * Note: The exceptions are being withheld because this method overrides
     * the one in Object().  Consider using a different method name since it
     * doesn't look like we're taking advantage of Cloneable.
     */
    public Object dolly() throws AppException {
        Attachment attach = null;
        attach = new Attachment();
        attach.setOid(getOid());
//        attach.setObjectContext(getObjectContext());
        attach.setPersState(getPersState());
//        attach._classIID = _classIID;
//        attach._stateIID = _stateIID;
        attach.setDeleteState(getDeleteState());
        //Saleem added to this line
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            attach.putAll(getProperties());
//      attach.setName(getName());
        attach.setFilename(getFilename());
        attach.setDescription(getDescription());
        attach._content = _content;
        attach.setCreatorIID(this.getCreatorIID());
        attach.setAccessIID(this.getAccessIID());
        attach.setCreationDate(getCreationDate());
        attach.setMessageAttached(hasMessageAttached());
        attach.setLinkAttached(hasLinkAttached());
        attach.setFileAttached(hasFileAttached());

        attach._parentObjIID = _parentObjIID;
        attach.setCategoryCode(getCategoryCode());
        attach.setFileSize(getFileSize());
        attach.setViewable(getViewable());
        return attach;
    }
}