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
 * ConfigKind has the following meanings...
 * <p>
 * <p>
 * FULL:       Created by end user. Can be viewed & edited & deleted by end user
 * READ_ONLY:  System level. Can be viewed by end user but Cannot be edited or deleted by end user.
 * DELETEABLE: Created by end user. Cannot be viewed & edited by end user. Can be deleted from a call
 * from within an external class i.e., a user created interface (IRType).
 * It can be deleted but it will not be shown and edited.
 * NO_CONFIG:  System level. End user cannot view, end user cannot delete,edit. i.e.,
 * IFeature
 * EDIT_ONLY:  System level. Can be viewed & edited by the end user. Cannot be deleted
 * by the end user i.e., "Standard input form"
 */
public enum ConfigKind {
    READ_ONLY,
    FULL,
    RENAMEABLE,
    DELETEABLE,
    NO_CONFIG,
    EDIT_ONLY,
    WINLOSS_ONLY,
    SUMMARY_ONLY
}