package com.zkk.test.cacheTest;

/**
 * Created by zhangkaikai on 2018/1/25 025 16:58 .
 */
public class cacheTest {
    /**
     * 注：@Cacheable注解里面指定了value=cacheName，这个注解的其他主要参数：
     cacheManager：指定由哪个cacheManager（比如可以指定为"redisCacheManager"）来管理这个cache，不指定的话使用spring注解@Primary的那个；
     key：指定key的SpEL表达式，不指定的话，使用方法的参数作为key；
     GuavaCache需要依赖Guava的jar包：
     * @param mType
     * @return
     */
//    @Cacheable("getCommonAds")
//    public List<Advert> getCommonAds(int mType) {
//        return advertDAO.getAdvertsByModuleType(mType);
//    }
}
