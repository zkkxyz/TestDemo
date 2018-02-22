package com.zkk.test.util;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by zhangkaikai on 2018/2/11 011 11:21 .
 * 缓存管理器，这个管理器负责实现缓存逻辑，
 * 支持对象的增加、修改和删除，支持值对象的泛型。
 */
@Component
public class CacheContext<T> {
    private Map<String, T> cache = Maps.newConcurrentMap();

    public T get(String key){
        return  cache.get(key);
    }

    public void addOrUpdateCache(String key,T value) {
        cache.put(key, value);
    }

    // 根据 key 来删除缓存中的一条记录
    public void evictCache(String key) {
        if(cache.containsKey(key)) {
            cache.remove(key);
        }
    }

    // 清空缓存中的所有记录
    public void evictCache() {
        cache.clear();
    }
}
