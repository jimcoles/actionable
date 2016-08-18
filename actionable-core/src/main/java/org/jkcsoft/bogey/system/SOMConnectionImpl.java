/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.system;


import org.jkcsoft.bogey.metamodel.Guid;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.User;

public class SOMConnectionImpl implements SOMConnection {

    private Guid _guid;
    private Oid _userid;               // the user who holds this connections
    private ObjectContext _context;    // the connection context
    private SOM _thecrm = null;        // the SOM holding this connection


    //------------------------ Public Constructor --------------------------------

    /**
     * generates a SOMConnectionImpl for the given user
     */
    public SOMConnectionImpl(Oid userid, SOM crm) throws AppException {
        _userid = userid;
        _guid = new Guid();
        _thecrm = crm;
    }

    //------------------------- SOMConnection Methods -----------------------------

    /**
     * Returns the IID of this connection
     */
    public Oid getOid() {
        return _userid;
    }

    /**
     * Returns the ID of the user that has this IConnection
     */
    public Oid getUserOid() {
        return _userid;
    }

    /**
     * Returns the User object that has this SOMConnection
     */
    public User getUserObject() throws AppException {
        if (getObjectContext() != null && getUserOid() != null)
            return (User) getObjectContext().getCRM().getCompObject(getObjectContext(), "User", getUserOid());
        return null;
    }

    /**
     * Returns true if the IConnection is a valid IConnection
     */
    public boolean isValid()
            throws AppException {
        return _thecrm.isValid(this);
    }


    //------------------------------ BusinessObject Methods ----------------------------------

    /**
     * return the ObjectContext of this object
     */
    public ObjectContext getObjectContext() {
        return _context;
    }

    /**
     * sets the object context of this object
     */
    public SOMConnection setObjectContext(ObjectContext context) {
        _context = context;
        return this;
    }

    /**
     * returns this objects Guid
     */
    public Guid getGUID() {
        return _guid;
    }

    public SOM getCRM() {
        return _thecrm;
    }


//  public boolean equals(Object other)
// {
//   if (!(other instanceof SOMConnection))
//     return false;
//   return (((SOMConnection)other).getOid().equals(getOid()));
// }
// 
// //override Object hashcode
// public int hashCode()
// {
//   return getOid().hashCode();
// }
}