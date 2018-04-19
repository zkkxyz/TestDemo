package com.zkk.test.statePattern;

/**
 * Created by zhangkaikai on 2018/4/19 019 14:11 .
 * 抽象状态类
 */
abstract class OrderState {
    abstract void confirm(OrderContext orderContext);

    abstract void modify(OrderContext orderContext);

    abstract void pay(OrderContext orderContext);
}