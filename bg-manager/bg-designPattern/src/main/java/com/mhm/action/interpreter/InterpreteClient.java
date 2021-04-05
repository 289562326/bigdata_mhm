package com.mhm.action.interpreter;

/**
 * 解释器模式
 * 场景：
 * 优点：
 * 缺点：执行效率较低、会引起类膨胀
 *
 * @author MHm
 * @date 2020-4-20 19:05
 */
public class InterpreteClient {
    private static Expression ex;
    private static ExpressionContext con;

    public static void main(String[] args) {
        con = new ExpressionContext();
        //设置变量、常量
        Variable a = new Variable();
        Variable b = new Variable();

        //为变量赋值
        con.addValue(a, 5);
        con.addValue(b, 7);
        //5*7/((5-7)+7)
        ex = new Division(new Multiply(a, b), new Add(new Subtract(a, b), b));
        System.out.println("运算结果为：" + ex.interpret(con));
    }
}
