/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.expr;

import org.jkcsoft.bogey.system.AppException;

public class InvalidExpressionException extends AppException {

    public InvalidExpressionException() {
        super("Invalid Expression Exception");
    }

    public InvalidExpressionException(String msg) {
        super(msg);
    }

    public InvalidExpressionException(Exception ex) {
        super(ex.getClass().getName() + "==>" + ex.getMessage());
    }
}