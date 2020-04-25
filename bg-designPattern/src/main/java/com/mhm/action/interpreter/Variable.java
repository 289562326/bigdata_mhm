package com.mhm.action.interpreter;

/**
 * @author MHm
 * @date 2020-4-20 18:59
 */
public class Variable extends Expression {
    @Override
    public int interpret(ExpressionContext con) {
        return con.lookupValue(this);
    }
}