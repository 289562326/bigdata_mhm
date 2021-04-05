package com.mhm.action.interpreter;

/**
 * 加法表达式
 * @author MHm
 * @date 2020-4-20 19:02
 */
public class Add extends Expression {
    private Expression left ,right ;
    public Add(Expression left , Expression right)
    {
        this.left = left ;
        this.right= right ;
    }

    @Override
    public int interpret(ExpressionContext con) {
        return left.interpret(con) + right.interpret(con);
    }
}
