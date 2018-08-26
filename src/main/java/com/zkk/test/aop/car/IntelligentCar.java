package com.zkk.test.aop.car;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

/**
 * Created by zkk on 2018/8/25 23:44 .
 */
public class IntelligentCar extends DelegatingIntroductionInterceptor implements Intelligent {

    @Override
    public void selfDriving() {
        System.out.println("开启无人驾驶了, 别挡路");
    }

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object obj = super.invoke(invocation);
        return obj;
    }
}
