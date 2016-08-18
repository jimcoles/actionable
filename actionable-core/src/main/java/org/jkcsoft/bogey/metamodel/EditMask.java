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
 * Represents the completed edit state of an item at a given
 * instance.
 */
public final class EditMask {
    private int mask = 0;

    /**
     * Private constructor
     */
    public EditMask() {

    }

    public EditMask setValue(int value) {
        mask = value;
        return this;
    }

    public boolean exactly(int value) {
        return (mask == value);
    }

    public boolean allTrue(int value) {
        return ((mask & value) == value);
    }

    public boolean oneTrue(int value) {
        return (mask & value) > 0;
    }

    public boolean inSync() {
        if (!oneTrue(EditState.NEW | EditState.DELETED | EditState.MODIFIED))
            return true;
        else
            return false;
    }
}