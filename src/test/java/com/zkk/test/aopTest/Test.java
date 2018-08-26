package com.zkk.test.aopTest;

import com.zkk.test.aop.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zkk on 2018/8/25 15:29 .
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        UserService userService = (UserService)ac.getBean("userService");
        userService.update(2);
        userService.add();
    }
}
