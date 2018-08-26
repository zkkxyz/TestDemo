package com.zkk.test.aop;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

/**
 * Created by zkk on 2018/8/25 15:52 .
 */
public class ExceptionLog  implements ThrowsAdvice {
    public void afterThrowing(Method method, Exception ex) throws Throwable {
    }
}
