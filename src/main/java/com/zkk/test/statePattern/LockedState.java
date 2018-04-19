package com.zkk.test.statePattern;

/**
 * Created by zhangkaikai on 2018/4/19 019 14:12 .
 */
public class LockedState extends OrderState {
    @Override
    void confirm(OrderContext orderContext) {
        System.out.println("订单已锁定");
    }

    @Override
    void modify(OrderContext orderContext) {
        System.out.println("订单已锁定");
    }

    @Override
    void pay(OrderContext orderContext) {
        System.out.println("订单已锁定");
    }
}
