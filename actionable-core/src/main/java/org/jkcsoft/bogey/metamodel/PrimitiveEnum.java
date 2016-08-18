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
 * UML: core.Primitive -> DataType -> Classifer -> etc.
 *
 * @author J. Coles
 * @version 1.0
 */
public enum PrimitiveEnum {

    CHAR("Short Text", "CHAR"),
    LONG_CHAR("Long Text", "LONG_CHAR"),
    PASSWORD("Password", "PASSWORD"),
    INTEGER("Integer", "INTEGER"),
    DECIMAL("Decimal", "DECIMAL"),
    TIME("Date", "TIME"),
    BOOLEAN("Yes/No", "BOOLEAN"),
    BLOB("Binary Data", "BLOB"),
    ENUM("List Box (Single Select)", "ENUM"),
    RADIO("Option (Radio) Buttons ", "RADIO"),
    MULTIENUM("List Box (Multi Select)", "MULTIENUM"),
    MULTICHECK("Check Box (Multi Select)", "MULTICHECK"),
    LINK("Hyperlink", "LINK");

    private String displayName;
    private String codeName;

    PrimitiveEnum(String displayName, String codeName) {
        this.displayName = displayName;
        this.codeName = codeName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCodeName() {
        return codeName;
    }
}