package com.zkk.test.cacheTest;

import com.alibaba.fastjson.JSON;
import com.zkk.test.service.AccountService2;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * Created by zhangkaikai on 2018/2/11 011 16:25 .
 */
public class AccountService2Test {
    private AccountService2 accountService2;

    private final Logger logger = LoggerFactory.getLogger(AccountService2Test.class);

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        accountService2 = context.getBean("accountService2", AccountService2.class);
    }

    @Test
    public void testInject(){
        assertNotNull(accountService2);
    }

    @Test
    public void testGetAccountByName() throws Exception {
        logger.info("first query...");
//        accountService2.getAccountByName("accountName");
        System.out.println (JSON.toJSONString (accountService2.getAccountByName("accountName")));

        logger.info("second query...");
        System.out.println (JSON.toJSONString (accountService2.getAccountByName("accountName")));
    }
}
