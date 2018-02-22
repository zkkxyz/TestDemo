package com.zkk.test.cacheTest;

import com.zkk.test.domain.entity.Account;
import com.zkk.test.service.AccountService3;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhangkaikai on 2018/2/11 011 16:27 .
 */
public class AccountService3Test {
    private AccountService3 accountService3;

    private final Logger logger = LoggerFactory.getLogger(AccountService3Test.class);

    @Before
    public void setUp() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-config.xml");
        accountService3 = context.getBean("accountService3", AccountService3.class);
    }

    @Test
    public void testGetAccountByName() throws Exception {

        logger.info("first query.....");
        accountService3.getAccountByName("accountName");

        logger.info("second query....");
        accountService3.getAccountByName("accountName");

    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account1 = accountService3.getAccountByName("accountName1");
        logger.info(account1.toString());
        Account account2 = accountService3.getAccountByName("accountName2");
        logger.info(account2.toString());

        account2.setId(121212);
        accountService3.updateAccount(account2);

        // account1会走缓存
        account1 = accountService3.getAccountByName("accountName1");
        logger.info(account1.toString());
        // account2会查询db
        account2 = accountService3.getAccountByName("accountName2");
        logger.info(account2.toString());

    }

    @Test
    public void testReload() throws Exception {
        accountService3.reload();
        // 这2行查询数据库
        accountService3.getAccountByName("somebody1");
        accountService3.getAccountByName("somebody2");

        // 这两行走缓存
        accountService3.getAccountByName("somebody1");
        accountService3.getAccountByName("somebody2");
    }

    @Test
    public void testMyCache(){
        Account account = accountService3.getAccountByName("someone");
        logger.info("passwd={}", account.getPassword());
        account = accountService3.getAccountByName("someone");
        logger.info("passwd={}", account.getPassword());
    }
}
