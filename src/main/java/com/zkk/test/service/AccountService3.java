package com.zkk.test.service;

import com.google.common.base.Optional;
import com.zkk.test.domain.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by zhangkaikai on 2018/2/11 011 16:26 .
 * @Cacheable、@CachePut、@CacheEvict 注释介绍

 @Cacheable 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存
 @CachePut 主要针对方法配置，能够根据方法的请求参数对其结果进行缓存，和 @Cacheable 不同的是，它每次都会触发真实方法的调用
 -@CachEvict 主要针对方法配置，能够根据一定的条件对缓存进行清空
 */
@Service
public class AccountService3 {
    private final Logger logger = LoggerFactory.getLogger(AccountService3.class);

    // 使用了一个缓存名叫 accountCache
    //    用 @CachePut 注释，这个注释可以确保方法被执行，同时方法的返回值也被记录到缓存
    @Cacheable(value="accountCache")
    public Account getAccountByName(String accountName) {

        // 方法内部实现不考虑缓存逻辑，直接实现业务
        logger.info("real querying account... {}", accountName);
        Optional<Account> accountOptional = getFromDB(accountName);
        if (!accountOptional.isPresent()) {
            throw new IllegalStateException(String.format("can not find account by account name : [%s]", accountName));
        }

        return accountOptional.get();
    }

    /**
     * @CacheEvict 注释有一个属性 beforeInvocation，缺省为 false，即缺省情况下，都是在实际的方法执行完成后，
     * 才对缓存进行清空操作。期间如果执行方法出现异常，则会导致缓存清空不被执行。
     * @param account
     */
    @CacheEvict(value="accountCache",key="#account.getName()")
    public void updateAccount(Account account) {
        updateDB(account);
    }

    @CacheEvict(value="accountCache",allEntries=true)
    public void reload() {
    }

    private void updateDB(Account account) {
        logger.info("real update db...{}", account.getName());
    }

    private Optional<Account> getFromDB(String accountName) {
        logger.info("real querying db... {}", accountName);
        //Todo query data from database
        return Optional.fromNullable(new Account(accountName));
    }
}

/*
      基于 proxy 的 spring aop 带来的内部调用问题
     上面介绍过 spring cache 的原理，即它是基于动态生成的 proxy 代理机制来对方法的调用进行切面，这里关键点是对象的引用问题.
     如果对象的方法是内部调用（即 this 引用）而不是外部引用，则会导致 proxy 失效，那么我们的切面就失效，也就是说上面定义的各种注释包括
     @Cacheable、@CachePut 和 @CacheEvict 都会失效，我们来演示一下。
     public Account getAccountByName2(String accountName) {
     return this.getAccountByName(accountName);
     }
     @Cacheable(value="accountCache")// 使用了一个缓存名叫 accountCache
     public Account getAccountByName(String accountName) {
     // 方法内部实现不考虑缓存逻辑，直接实现业务
     return getFromDB(accountName);
     }
     上面我们定义了一个新的方法 getAccountByName2，其自身调用了 getAccountByName 方法，这个时候，发生的是内部调用（this），
     所以没有走 proxy，导致 spring cache 失效
     要避免这个问题，就是要避免对缓存方法的内部调用，或者避免使用基于 proxy 的 AOP 模式，可以使用基于 aspectJ 的 AOP 模式来解决这个问题。
 */

/*
    非 public 方法问题
    和内部调用问题类似，非 public 方法如果想实现基于注释的缓存，必须采用基于 AspectJ 的 AOP 机制
 */