package com.mhm.action.strategy;

/**
 * 策略模式
 *
 * @author MHm
 * @date 2020-4-19 19:52
 */
public class Context {
    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    //配合工厂方法
    public Context(String type) {
        if ("normal".equals(type)) {
            strategy = new CashNormal();
        } else if ("rebate".equals(type)) {
            strategy = new CashRebate(0.7);
        } else if ("return".equals(type)) {
            strategy = new CashReturn(500, 100);
        }
    }

    public double sale(double money) {
        return strategy.cash(money);
    }

}
