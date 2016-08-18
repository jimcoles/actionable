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
 * UML 'ScopeKind' enumeration.
 */
public enum Multiplicity {
    M0_1(true, false),
    M1_1(false, false),
    M0_M(true, true),
    M1_M(false, true);

    private boolean optional;
    private boolean many;

    private Multiplicity(boolean opt, boolean many) {
        optional = opt;
        many = many;
    }

    public boolean isOptional() {
        return optional;
    }

    public boolean isMany() {
        return many;
    }
}