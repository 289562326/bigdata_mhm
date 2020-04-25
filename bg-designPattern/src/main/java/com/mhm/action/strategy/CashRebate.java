package com.mhm.action.strategy;

/**
 * 打折策略
 * @author MHm
 * @date 2020-4-19 19:46
 */
public class CashRebate implements IStrategy{

    private double rebate = 1;

    public CashRebate(double rebate) {
        this.rebate = rebate;
    }

    @Override
    public double cash(double money) {
        return money * rebate;
    }
}
