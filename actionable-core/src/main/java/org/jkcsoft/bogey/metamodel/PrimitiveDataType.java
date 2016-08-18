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
 * Wraps a PrimitiveEnum
 *
 * @author J. Coles
 */
public class PrimitiveDataType extends Classifier {

    private PrimitiveEnum type;

    public PrimitiveDataType(PrimitiveEnum type) {
        this.type = type;
    }

    public PrimitiveEnum getType() {
        return type;
    }
}