/*
 * Copyright (c) Jim Coles (jameskcoles@gmail.com) 2016. through present.
 *
 * Licensed under the following license agreement:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Also see the LICENSE file in the repository root directory.
 */

package org.jkcsoft.bogey.rdb;

/**
 * @author Jim Coles
 * @version 1.0
 */
public final class SQLUtil {
    /**
     *
     */
    public static String primer(String str) {
        return fixSingleQuotes(str);
    }

    /**
     *
     */
    public static String primer(String str, int length) {
        return fixSingleQuotes(trunc(str, length));
    }

    /**
     *
     */
    private static String fixQuotes(String str) {
        if (str == null)
            return null;
        StringBuffer jdtSB = new StringBuffer(str);
        for (int i = jdtSB.length() - 1; i >= 0; --i)
            if (jdtSB.charAt(i) == '"')
                jdtSB.insert(i, '\\');
            else if (jdtSB.charAt(i) == '\'')
                jdtSB.insert(i, '\'');
        return jdtSB.toString();
    }

    /**
     * fixDoubleQuotes takes a String and if it has a double quote in it, inserts a \
     */
    private static String fixDoubleQuotes(String str) {
        if (str == null)
            return null;
        StringBuffer jdtSB = new StringBuffer(str);
        for (int i = jdtSB.length() - 1; i >= 0; --i)
            if (jdtSB.charAt(i) == '"')
                jdtSB.insert(i, '\\');
        return jdtSB.toString();
    }

    /**
     * fixSingleQuotes takes a String and if it has an apostrophe in it, inserts another
     * apostrophe.  It doubles all apostrophes.
     */
    private static String fixSingleQuotes(String str) {
        if (str == null)
            return null;
        StringBuffer jdtSB = new StringBuffer(str);
        for (int i = jdtSB.length() - 1; i >= 0; --i)
            if (jdtSB.charAt(i) == '\'')
                jdtSB.insert(i, '\'');
        return jdtSB.toString();
    }

    /**
     * truncate a String to the desired length
     */
    private static String trunc(String str, int length) {
        if (str == null) str = "Error";
        else if (str.length() >= length) str = str.substring(0, length);
        return str;
    }
}