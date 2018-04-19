package com.zkk.test.statePattern;

/**
 * Created by zhangkaikai on 2018/4/19 019 14:10 .
 * 背景类
 */
public class OrderContext {
    OrderState state = null;
    //新建订单设为已预定状态
    public OrderContext() {
        this.state = new OrderedState();
    }

    void setState(OrderState state) {
        this.state = state;
    }

    public void confirm() {
        state.confirm(this);
    }

    public void modify() {
        state.modify(this);
    }

    public void pay() {
        state.pay(this);
    }
}
