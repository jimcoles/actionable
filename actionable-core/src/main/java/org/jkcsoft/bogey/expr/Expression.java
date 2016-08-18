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

import org.jkcsoft.bogey.metamodel.PrimitiveDataType;
import org.jkcsoft.bogey.metamodel.PrimitiveEnum;
import org.jkcsoft.bogey.model.BMProperty;
import org.jkcsoft.bogey.system.AppException;
import org.jkcsoft.bogey.metamodel.Attribute;
import org.jkcsoft.bogey.metamodel.Oid;
import org.jkcsoft.bogey.model.ReposObject;

import java.util.*;

public class Expression {

    private List<String> _v;
    private Iterator<String> _e;
    private ReposObject _o;

    private static final String PAREN_EXC1 = "Invalid expression.  Mismatched parenthesis.";
    private static final String ATT_EXC1 = "Invalid expression.  Incorrect attribute type.";

    public Expression(ReposObject o, String s) throws AppException {
        _v = new LinkedList();
        _o = o;
        parse(s);
    }//end constructor

    public int size() {
        return _v.size();
    }

    public boolean hasMoreElements() {
        return _e.hasNext();
    }

    public String nextElement() {
        return (String) _e.next();
    }

    public void reset() {
        _e = _v.iterator();
    }

    //private parts

    private void addElement(String s) {
        _v.add(s);
    }

    private void removeAllElements() {
        _v.clear();
    }

    /**
     * populate the vector
     */
    private void parse(String s) throws AppException {
        if (s.indexOf(";", 0) != -1)
            s = s.substring(0, s.indexOf(";", 0));
        checkParens(s);
        //s = s.toLowerCase();//shouldn't need this
        s = s.trim();
        s = filter(s);
        StringTokenizer st = new StringTokenizer(s, ") (", true);
        while (st.hasMoreTokens()) {
            String str = st.nextToken();
            if (str.startsWith("[") && str.endsWith("]")) {
                //take out the brackets
                str = str.substring(1, str.length());
                str = str.substring(0, str.length() - 1);
                //get the value from the database
                Oid attiid = new Oid(Long.parseLong(str));
//                ObjectContext context = _o.getObjectContext();
                Attribute att = null; //(Attribute) context.getCRM().getCompObject(context, "Attribute", attiid);
                Map<Oid, BMProperty> props = _o.getAttributes();
                PrimitiveEnum prim = att.getPrimitiveDataType().getType();
                if (prim == PrimitiveEnum.INTEGER || prim == PrimitiveEnum.DECIMAL) {
                    str = props.get(att.getCodeName()).toString();
                    addElement(str);
                }
                else
                    throw new InvalidExpressionException(ATT_EXC1);
            }
            if (!str.equals(" "))
                addElement(str);
        }
        reset();
    }

    /**
     * be sure that the parenthesis are balanced
     */
    private boolean checkParens(String s) throws InvalidExpressionException {
        int numopen = 0, numclosed = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') numopen++;
            else if (s.charAt(i) == ')') numclosed++;
        }//end for
        if (numopen == numclosed) return true;
        else throw new InvalidExpressionException(PAREN_EXC1);
    }//end checkParens

    /**
     * change all whitespace to spaces.
     */
    private String filter(String s) throws InvalidExpressionException {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) sb.append(" ");
            else sb.append(s.charAt(i));
        }//end for
        return sb.toString();
    }//end filter
}