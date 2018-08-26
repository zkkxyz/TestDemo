package com.zkk.test.aop;

/**
 * Created by zkk on 2018/8/25 15:55 .
 */
public class LogS {
    public void before(){
        System.out.println("方法执行前");
    }
    public void after(){
        System.out.println("方法执行后");
    }
}
