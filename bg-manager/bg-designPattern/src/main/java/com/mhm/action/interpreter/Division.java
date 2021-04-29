package com.mhm.action.interpreter;

/**
 * 除法表达式
 *
 * @author MHm
 * @date 2020-4-20 19:04
 */
public class Division extends Expression {

    private Expression left, right;

    public Division(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret(ExpressionContext con) {
        try {
            return left.interpret(con) / right.interpret(con);
        } catch (ArithmeticException ae) {
            System.out.println("被除数为0！");
            return 0;
        }
    }
}
