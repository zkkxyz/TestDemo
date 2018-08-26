package com.zkk.test.aop.car;

/**
 * Created by zkk on 2018/8/25 23:44 .
 */
public class MyCar implements Auto {
    @Override
    public void driving() {
        System.out.println("开车了");
    }
}
