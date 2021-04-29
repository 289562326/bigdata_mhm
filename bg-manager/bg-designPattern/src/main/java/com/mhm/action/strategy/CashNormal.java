package com.mhm.action.strategy;

/**
 * @author MHm
 * @date 2020-4-19 19:45
 */
public class CashNormal implements IStrategy {
    @Override
    public double cash(double money) {
        return money;
    }
}
