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

/**
 * @author J. Coles
 * @version 1.0
 */
public class Generalization extends Association {

    public Generalization(Oid id, Class fromClass, Class toClass, String fromColName, String displayName) throws Exception {
        super(id, fromClass, toClass, fromColName, displayName);
    }
}
