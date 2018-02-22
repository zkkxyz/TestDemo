package com.zkk.test.service;

import com.google.common.base.Optional;
import com.zkk.test.domain.entity.Account;
import com.zkk.test.util.CacheContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by zhangkaikai on 2018/2/11 011 16:11 .
 * 1.缓存代码和业务代码耦合度太高，如上面的例子，
     AccountService 中的 getAccountByName（）方法中有了太多缓存的逻辑，不便于维护和变更
    2.不灵活，这种缓存方案不支持按照某种条件的缓存，
    比如只有某种类型的账号才需要缓存，这种需求会导致代码的变更
    3.缓存的存储这块写的比较死，不能灵活的切换为使用第三方的缓存模块
 */
@Service
public class AccountService1 {
    private final Logger logger = LoggerFactory.getLogger(AccountService1.class);

    @Resource
    private CacheContext<Account> accountCacheContext;

    public Account getAccountByName(String accountName) {
        Account result = accountCacheContext.get(accountName);
        if (result != null) {
            logger.info("get from cache... {}", accountName);
            return result;
        }

        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        Account account = accountOptional.get();
        accountCacheContext.addOrUpdateCache(accountName, account);
        return account;
    }

    public void reload() {
        accountCacheContext.evictCache();
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}
