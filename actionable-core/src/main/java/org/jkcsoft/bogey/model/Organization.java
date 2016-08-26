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

import java.util.Collection;

public class Organization extends BusinessObject {

    private Oid _parentOrgIID;
    private BMProperty _isInstallOwner,         // boolean type
            _busAddrLine1,           // string type
            _busAddrLine2,           // string type
            _city,                   // string type
            _state,                  // string type
            _country,                // string type
            _zipcode;                // string type

    /**
     * Default constructor just initializes the Organization list
     */
    public Organization() throws AppException {
        super();
        _parentOrgIID = null;
        _isInstallOwner = new BMProperty(this);
        _busAddrLine1 = new BMProperty(this);
        _busAddrLine2 = new BMProperty(this);
        _city = new BMProperty(this);
        _state = new BMProperty(this);
        _country = new BMProperty(this);
        _zipcode = new BMProperty(this);


        // TODO prolly do this via annotations
//        _isInstallOwner.setDefnObject(IDCONST.ISINSTALLOWNER.getIIDValue());
//        _busAddrLine1.setDefnObject(IDCONST.BUSADDR1.getIIDValue());
//        _busAddrLine2.setDefnObject(IDCONST.BUSADDR2.getIIDValue());
//        _city.setDefnObject(IDCONST.CITY.getIIDValue());
//        _state.setDefnObject(IDCONST.USERSTATE.getIIDValue());
//        _country.setDefnObject(IDCONST.COUNTRY.getIIDValue());
//        _zipcode.setDefnObject(IDCONST.ZIPCODE.getIIDValue());

    }

    public Object dolly() throws AppException {
        Organization org = null;
        org = new Organization();
        org.setOid(getOid());
        org.setObjectContext(getObjectContext());
        org.setPersState(getPersState());
        org.setDefnObject(this.getDefnObject());
        if (!getPersState().equals(PersState.PARTIAL) && getProperties() != null)
            org.putAll(getProperties());
        org.setName(getName());
        // org.setDescription(getDescription());
        org.setCreatorIID(this.getCreatorIID());
        org.setAccessIID(this.getAccessIID());
        org.setCreationDate(getCreationDate());
        org.setMessageAttached(hasMessageAttached());
        org.setLinkAttached(hasLinkAttached());
        org.setFileAttached(hasFileAttached());
        org.setDeleteState(getDeleteState());

        //specific to organy
        org.setIsInstallOwner(getIsInstallOwner());
        org.setBusAddressLine1(getBusAddressLine1());
        org.setBusAddressLine2(getBusAddressLine2());
        org.setCity(getCity());
        org.setState(getState());
        org.setCountry(getCountry());
        org.setZipcode(getZipcode());
        org._parentOrgIID = _parentOrgIID;
        org.setPersState(getPersState());
        return org;
    }

    public String getBusAddressLine1()
            throws AppException {
        return (String) _busAddrLine1.getValue();
    }

    public String getBusAddressLine2()
            throws AppException {
        return (String) _busAddrLine2.getValue();
    }

    public String getCity()
            throws AppException {
        return (String) _city.getValue();
    }

    public String getCountry()
            throws AppException {
        return (String) _country.getValue();
    }


    public boolean getIsInstallOwner()
            throws AppException {
        if (_isInstallOwner.getValue() != null)
            return ((Boolean) _isInstallOwner.getValue()).booleanValue();
        else
            return false;
    }


    public Oid getParentOrgIID() {
        return _parentOrgIID;
    }

    public String getState() throws AppException {
        return (String) _state.getValue();
    }

    public Collection<User> getUsers(boolean editable) throws AppException {
        return (Collection<User>) getObjectContext().getCRM().getCompObject(getObjectContext(), "UserColl", getOid(),
                                                                            editable);
    }

    public String getZipcode() throws AppException {
        return (String) _zipcode.getValue();
    }

    public Organization setBusAddressLine1(String busAddrLine1) throws AppException {
        _busAddrLine1.setValue(busAddrLine1);
        return this;
    }

    public Organization setBusAddressLine2(String busAddrLine2) throws AppException {
        _busAddrLine2.setValue(busAddrLine2);
        return this;
    }

    public Organization setCity(String city) throws AppException {
        _city.setValue(city);
        return this;
    }

    public Organization setCountry(String country) throws AppException {
        _country.setValue(country);
        return this;
    }

    public Organization setIsInstallOwner(boolean isInstallOwner) throws AppException {
        _isInstallOwner.setValue(new Boolean(isInstallOwner));
        return this;
    }

    public Organization setParentOrgIID(Oid id) throws AppException {
        if (getPersState().equals(PersState.UNMODIFIED))
            setPersState(PersState.MODIFIED);
        _parentOrgIID = id;
        return this;
    }

    public Organization setState(String state) throws AppException {
        _state.setValue(state);
        return this;
    }

    public Organization setZipcode(String zipcode) throws AppException {
        _zipcode.setValue(zipcode);
        return this;
    }


    public static Oid getIIDForName(ObjectContext context, String name) throws AppException {
        Oid orgIID = null;

        // TODO supplant this logic ...
//        IRConnection conn = context.getRepository().getDataConnection(context);
//        RepoMap result = conn.createProcessor().retrieve("SELECT OBJECTID FROM ORGANIZATION WHERE NAME='" + SQLUtil
// .primer(name) + "'");
//        if (result.next())
//            orgIID = context.getRepository().makeReposID(result.getLong("OBJECTID"));

        return orgIID;
    }
}