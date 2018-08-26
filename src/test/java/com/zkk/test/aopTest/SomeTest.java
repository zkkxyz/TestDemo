package com.zkk.test.aopTest;

import com.zkk.test.aop.some.IOther;
import com.zkk.test.aop.some.ISome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zkk on 2018/8/26 11:27 .
 */
public class SomeTest {
    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/beans.xml");
        ISome some=(ISome)context.getBean("proxyFactoryBean");
        some.doSome();
        ((IOther)some).doOther();

    }
}
