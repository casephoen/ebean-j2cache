package com.top.ebean.j2cache;

import com.avaje.ebean.cache.ServerCache;
import com.avaje.ebean.cache.ServerCacheFactory;
import com.avaje.ebean.cache.ServerCacheOptions;
import com.avaje.ebean.cache.ServerCacheType;
import net.oschina.j2cache.J2Cache;

/**
 * Created by lizhijun on 2017/4/6.
 */
public class J2EbeanCacheFactory implements ServerCacheFactory {

    @Override
    public ServerCache createCache(ServerCacheType type, String cacheKey, ServerCacheOptions cacheOptions) {
        return new J2EbeanCache(type, cacheKey, cacheOptions, J2Cache.getChannel());
    }
}
