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

import com.jkc.util.IntEnum;

/**
 * UML 'ScopeKind' enumeration.
 */
public class ScopeKind extends IntEnum {
    public static final ScopeKind STATIC = new ScopeKind(1);
    public static final ScopeKind INSTANCE = new ScopeKind(2);

    private ScopeKind(int i) {
        super(i);
    }
}