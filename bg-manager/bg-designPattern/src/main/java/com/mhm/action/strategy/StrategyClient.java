package com.mhm.action.strategy;

/**
 * 策略模式
 *
 * @author MHm
 * @date 2020-4-26 21:57
 */
public class StrategyClient {
    public static void main(String[] args) {
        double total = 0;
        Context context = new Context("normal");
        total+= context.sale(100);
        context = new Context("return");
        total+=context.sale(1000);
        context = new Context("rebate");
        total+=context.sale(1000);
        System.out.println(total);
    }
}
