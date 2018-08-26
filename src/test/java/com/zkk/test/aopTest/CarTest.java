package com.zkk.test.aopTest;

import com.zkk.test.aop.car.Auto;
import com.zkk.test.aop.car.Intelligent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zkk on 2018/8/25 23:47 .
 */
public class CarTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beans.xml");

        Auto car = (Auto)context.getBean("myCar");
        car.driving();
        Intelligent intelligentCar = (Intelligent)car;
        intelligentCar.selfDriving();

    }
}
