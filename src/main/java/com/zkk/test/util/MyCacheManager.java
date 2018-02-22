package com.zkk.test.util;

import org.springframework.cache.support.AbstractCacheManager;

import java.util.Collection;

/**
 * Created by zhangkaikai on 2018/2/22 022 14:57 .
 */
public class MyCacheManager extends AbstractCacheManager {
    private Collection<? extends MyCache> caches;

    /**
     * Specify the collection of Cache instances to use for this CacheManager.
     */
    public void setCaches(Collection<? extends MyCache> caches) {
        this.caches = caches;
    }

    @Override
    protected Collection<? extends MyCache> loadCaches() {
        return this.caches;
    }

}
