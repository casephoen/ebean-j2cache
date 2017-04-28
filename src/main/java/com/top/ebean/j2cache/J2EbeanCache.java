package com.top.ebean.j2cache;

import com.avaje.ebean.cache.ServerCache;
import com.avaje.ebean.cache.ServerCacheOptions;
import com.avaje.ebean.cache.ServerCacheStatistics;
import com.avaje.ebean.cache.ServerCacheType;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lizhijun on 2017/4/6.
 */
public class J2EbeanCache implements ServerCache {
    private static final Logger log = LoggerFactory.getLogger(J2EbeanCache.class);
    protected ServerCacheType type;
    protected String name;
    protected ServerCacheOptions cacheOptions;
    protected CacheChannel cache;

    public J2EbeanCache(ServerCacheType type, String name, ServerCacheOptions cacheOptions, CacheChannel cache) {
        this.type = type;
        this.name = name;
        this.cacheOptions = cacheOptions;
        this.cache = cache;
    }

    @Override
    public Object get(Object id) {
        CacheObject obj = cache.get(name, id);
        log.debug("{} GET {}({}) => {}", type, name, id, obj.getValue());
        return obj.getValue();
    }

    @Override
    public Object put(Object id, Object value) {
        int maxSecsToLive = cacheOptions.getMaxSecsToLive();
        if (maxSecsToLive > 0) {
            log.debug("{} PUT {}({}) {}s", type, name, id, maxSecsToLive);
            cache.set(name, id, value, maxSecsToLive);
        } else {
            log.debug("{} PUT {}({})", type, name, id);
            cache.set(name, id, value);
        }
        return value;//be care
    }

    @Override
    public Object remove(Object id) {
        log.debug("{} DEL {}({})", type, name, id);
        cache.evict(name, id);
        return null;//be care
    }

    @Override
    public int size() {
        int size = cache.keys(name).size();
        log.debug("{} SIZE {} of LEVEL_1 ElementCountInMemory", size);
        return size;
    }

    @Override
    public void clear() {
        log.debug("{} CLEAR {}", type, name);
        cache.clear(name);
    }

    @Override
    public int getHitRatio() {
        return 0;
    }

    @Override
    public ServerCacheStatistics getStatistics(boolean reset) {
        return null;
    }

}
