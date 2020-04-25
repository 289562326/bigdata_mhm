package com.mhm.action.interpreter;

/**
 * 减法表达式
 * @author MHm
 * @date 2020-4-20 19:03
 */
public class Subtract extends Expression{
    @Override
    public int interpret(ExpressionContext con) {
        return left.interpret(con) - right.interpret(con);
    }

    private Expression left , right ;
    public Subtract(Expression left , Expression right)
    {
        this.left = left ;
        this.right= right ;
    }

}
