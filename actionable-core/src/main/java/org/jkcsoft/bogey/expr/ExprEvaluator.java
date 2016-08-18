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
import org.jkcsoft.bogey.model.BusinessObject;

import java.util.Stack;

public class ExprEvaluator {

    private Expression _expr;
    private Stack _callstack;
    private Stack _opstack;
    private Stack _tempstack;

    private static final String ARITH_EXC1 = "Invalid arithmetic expression.  Expression needs at least two values.";
    private static final String LOGIC_EXC1 = "Invalid logic expression.  Expression must have two values.";
    private static final String COMP_EXC1 = "Invalid comparison expression.  Expression must have two values.";

    public static final float TRUE = 1;
    public static final float FALSE = 0;

    public static final int i_ADD = 1;
    public static final int i_SUB = 2;
    public static final int i_MUL = 3;
    public static final int i_DIV = 4;
    public static final int i_AND = 5;
    public static final int i_OR = 6;
    public static final int i_NOT = 7;
    public static final int i_LT = 8;
    public static final int i_GT = 9;
    public static final int i_LTE = 10;
    public static final int i_GTE = 11;
    public static final int i_EQ = 12;
    public static final int i_NEQ = 13;

    public static final String s_ADD = "+";
    public static final String s_SUB = "-";
    public static final String s_MUL = "*";
    public static final String s_DIV = "/";
    public static final String s_AND = "&&";
    public static final String s_OR = "||";
    public static final String s_NOT = "!";
    public static final String s_LT = "<";
    public static final String s_GT = ">";
    public static final String s_LTE = "<=";
    public static final String s_GTE = ">=";
    public static final String s_EQ = "==";
    public static final String s_NEQ = "!=";

    /**
     *
     */
    public ExprEvaluator(Expression e) {
        _expr = e;
        _callstack = new Stack();
        _opstack = new Stack();
        _tempstack = new Stack();
    }//end Constructor

    /**
     *
     */
    public ExprEvaluator(BusinessObject o, String e) throws AppException {
        _expr = new Expression(o, e);
        _callstack = new Stack();
        _opstack = new Stack();
        _tempstack = new Stack();
    }//end Constructor

    /**
     *
     */
    public float evaluate() throws InvalidExpressionException {
        float answer = FALSE;
        if (_expr == null)
            return answer;
        while (_expr.hasMoreElements()) {
            String elem = _expr.nextElement();
            if (elem.equals("("))
                _callstack.push(elem);
            else if (elem.equals(")")) {
                if (!_opstack.isEmpty()) {
                    int op = ((Integer) _opstack.pop()).intValue();
                    if (op == i_ADD || op == i_SUB || op == i_MUL || op == i_DIV)
                        answer = arithmetic(op);
                    else if (op == i_AND || op == i_OR || op == i_NOT)
                        answer = logic(op);
                    else if (op == i_GT || op == i_LT || op == i_GTE || op == i_LTE || op == i_EQ || op == i_NEQ)
                        answer = comparison(op);
                    //push the answer
                    if (_expr.hasMoreElements() || !_callstack.isEmpty() || !_opstack.isEmpty())
                        _callstack.push("" + answer);
                }//end if
            }//end else if
            else if (elem.equals(s_ADD))
                _opstack.push(new Integer(i_ADD));
            else if (elem.equals(s_SUB))
                _opstack.push(new Integer(i_SUB));
            else if (elem.equals(s_MUL))
                _opstack.push(new Integer(i_MUL));
            else if (elem.equals(s_DIV))
                _opstack.push(new Integer(i_DIV));
            else if (elem.equals(s_AND))
                _opstack.push(new Integer(i_AND));
            else if (elem.equals(s_OR))
                _opstack.push(new Integer(i_OR));
            else if (elem.equals(s_NOT))
                _opstack.push(new Integer(i_NOT));
            else if (elem.equals(s_LT))
                _opstack.push(new Integer(i_LT));
            else if (elem.equals(s_GT))
                _opstack.push(new Integer(i_GT));
            else if (elem.equals(s_LTE))
                _opstack.push(new Integer(i_LTE));
            else if (elem.equals(s_GTE))
                _opstack.push(new Integer(i_GTE));
            else if (elem.equals(s_EQ))
                _opstack.push(new Integer(i_EQ));
            else if (elem.equals(s_NEQ))
                _opstack.push(new Integer(i_NEQ));
            else
                _callstack.push(elem);
        }//end while
        return answer;
    }//end evaluate

    /**
     * evaluates arithmetic operations
     */
    private float arithmetic(int type) throws InvalidExpressionException {
        float fltRV = FALSE;
        //push 'em into the temp stack
        String str = "";
        while (!_callstack.isEmpty() && !str.equals("(")) {
            str = (String) _callstack.pop();
            if (!str.equals("("))
                _tempstack.push(str);
        }//end while
        //get the first value
        if (!_tempstack.isEmpty())
            fltRV = Float.parseFloat((String) _tempstack.pop());
        //do op
        if (type == i_ADD) {
            if (_tempstack.isEmpty()) throw new InvalidExpressionException(ARITH_EXC1);
            while (!_tempstack.isEmpty())
                fltRV += Float.parseFloat((String) _tempstack.pop());
        }//end if
        else if (type == i_SUB) {
            if (_tempstack.isEmpty()) throw new InvalidExpressionException(ARITH_EXC1);
            while (!_tempstack.isEmpty())
                fltRV -= Float.parseFloat((String) _tempstack.pop());
        }//end else if
        else if (type == i_MUL) {
            if (_tempstack.isEmpty()) throw new InvalidExpressionException(ARITH_EXC1);
            while (!_tempstack.isEmpty())
                fltRV *= Float.parseFloat((String) _tempstack.pop());
        }//end else if
        else if (type == i_DIV) {
            if (_tempstack.isEmpty()) throw new InvalidExpressionException(ARITH_EXC1);
            while (!_tempstack.isEmpty())
                fltRV /= Float.parseFloat((String) _tempstack.pop());
        }//end else if
        return fltRV;
    }//end arithmetic

    /**
     * evaluates logic operations
     */
    private float logic(int type) throws InvalidExpressionException {
        float fltRV = FALSE, val2 = FALSE;
        //push 'em into the temp stack
        String str = "";
        while (!_callstack.isEmpty() && !str.equals("(")) {
            str = (String) _callstack.pop();
            if (!str.equals("("))
                _tempstack.push(str);
        }//end while
        //get the first value
        if (!_tempstack.isEmpty())
            fltRV = Float.parseFloat((String) _tempstack.pop());
        else throw new InvalidExpressionException(LOGIC_EXC1);
        //do op
        if (type == i_AND) {
            if (!_tempstack.isEmpty()) {
                val2 = Float.parseFloat((String) _tempstack.pop());
                if (fltRV != FALSE && val2 != FALSE) fltRV = TRUE;
                if (fltRV == FALSE || val2 == FALSE) fltRV = FALSE;
            }//end while
            else throw new InvalidExpressionException(LOGIC_EXC1);
            if (!_tempstack.isEmpty()) throw new InvalidExpressionException(LOGIC_EXC1);
        }//end if
        else if (type == i_OR) {
            if (!_tempstack.isEmpty()) {
                val2 = Float.parseFloat((String) _tempstack.pop());
                if (fltRV != FALSE || val2 != FALSE) fltRV = TRUE;
                if (fltRV == FALSE && val2 == FALSE) fltRV = FALSE;
            }//end while
            else throw new InvalidExpressionException(LOGIC_EXC1);
            if (!_tempstack.isEmpty()) throw new InvalidExpressionException(LOGIC_EXC1);
        }//end else if
        else if (type == i_NOT) {
            if (!_tempstack.isEmpty()) throw new InvalidExpressionException(LOGIC_EXC1);
            if (fltRV != FALSE) fltRV = FALSE;
            else fltRV = TRUE;
        }//end else if
        return fltRV;
    }//end logic

    /**
     * evaluates comparisons
     */
    private float comparison(int type) throws InvalidExpressionException {
        float fltRV = FALSE, val2 = FALSE;
        //push 'em into the temp stack
        String str = "";
        while (!_callstack.isEmpty() && !str.equals("(")) {
            str = (String) _callstack.pop();
            if (!str.equals("("))
                _tempstack.push(str);
        }//end while
        //get the first value
        if (!_tempstack.isEmpty())
            fltRV = Float.parseFloat((String) _tempstack.pop());
        else throw new InvalidExpressionException(COMP_EXC1);

        if (!_tempstack.isEmpty()) {
            val2 = Float.parseFloat((String) _tempstack.pop());
            if (type == i_LT) {
                if (fltRV < val2) fltRV = TRUE;
                else fltRV = FALSE;
            } else if (type == i_GT) {
                if (fltRV > val2) fltRV = TRUE;
                else fltRV = FALSE;
            } else if (type == i_LTE) {
                if (fltRV <= val2) fltRV = TRUE;
                else fltRV = FALSE;
            } else if (type == i_GTE) {
                if (fltRV >= val2) fltRV = TRUE;
                else fltRV = FALSE;
            } else if (type == i_EQ) {
                if (fltRV == val2) fltRV = TRUE;
                else fltRV = FALSE;
            } else if (type == i_NEQ) {
                if (fltRV != val2) fltRV = TRUE;
                else fltRV = FALSE;
            }
        }//end while
        else throw new InvalidExpressionException(COMP_EXC1);
        if (!_tempstack.isEmpty()) throw new InvalidExpressionException(COMP_EXC1);
        return fltRV;
    }//end comparison
}