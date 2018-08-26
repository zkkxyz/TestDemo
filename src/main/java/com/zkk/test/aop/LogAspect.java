package com.zkk.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * Created by zkk on 2018/8/25 15:58 .
 */
@Aspect
public class LogAspect {
    @Pointcut("execution(* com.zkk.test.aop.impl.*.*(..))")
    public void pcExpression(){}

    @Before("pcExpression()")
    public void before(){
        System.out.println("方法执行前");
    }
    @After("pcExpression()")
    public void after(JoinPoint jp){
        System.out.println("方法执行后");
        System.out.println("方法执行后"+jp.getTarget());
    }
    @Around("pcExpression()")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        System.out.println("环绕前");
        System.out.println("方法"+jp.getSignature());
        Object result=jp.proceed();
        System.out.println("环绕后");
        return result;
    }
}
