/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */
package org.jkcsoft.bogey.metamodel;

import org.jkcsoft.bogey.model.BMProperty;

/**
 * @author J. Coles
 * @version 1.0
 */
public class EnumeratedClass extends Class {

    private BMProperty values;

    public EnumeratedClass(Oid classid, String table, String syn, String displayname) throws Exception {
        super(classid, table, syn, displayname);
    }

    public BMProperty getValues() {
        return values;
    }

    public void setValues(BMProperty values) {
        this.values = values;
    }
}
