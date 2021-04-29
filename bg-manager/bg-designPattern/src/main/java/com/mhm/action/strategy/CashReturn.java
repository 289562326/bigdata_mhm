package com.mhm.action.strategy;

/**
 * 满返策略
 *
 * @author MHm
 * @date 2020-4-19 19:48
 */
public class CashReturn implements IStrategy {
    private double moneyFull;
    private double moneyReturn;

    public CashReturn(double moneyFull, double moneyReturn) {
        this.moneyFull = moneyFull;
        this.moneyReturn = moneyReturn;
    }

    @Override
    public double cash(double money) {
        if (money >= moneyFull) {
            return money - money / moneyFull * moneyReturn;
        }
        return money;
    }
}
