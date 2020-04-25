package com.mhm.action.interpreter;

/**
 * 乘法表达式
 * @author MHm
 * @date 2020-4-20 19:03
 */
public class Multiply extends Expression{
    private Expression left , right ;
    public Multiply(Expression left , Expression right)
    {
        this.left = left ;
        this.right= right ;
    }
    @Override
    public int interpret(ExpressionContext con) {
        return left.interpret(con) * right.interpret(con);
    }
}
