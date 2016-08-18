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
import org.jkcsoft.bogey.system.AppException;

/**
 *
 */
public interface Element extends ManagedObject {

    /**
     * The most basic identifier within this application framework.
     */
    Oid getOid();


} 