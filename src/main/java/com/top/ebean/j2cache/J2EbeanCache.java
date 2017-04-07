package com.top.ebean.j2cache;

import com.avaje.ebean.EbeanServer;
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
    private final static Logger log = LoggerFactory.getLogger(J2EbeanCache.class);
    protected ServerCacheType type;
    protected String name;
    protected CacheChannel cache;

    public J2EbeanCache(ServerCacheType type, String name, CacheChannel cache) {
        this.type = type;
        switch (this.type) {
            case NATURAL_KEY:
                this.name = name + "-naturalKey";
                break;
            case QUERY:
                this.name = name + "-query";
                break;
            default:
                this.name = name;
        }
        this.cache = cache;
    }

    @Override
    public Object get(Object id) {
        CacheObject obj = cache.get(name, id);
        if (log.isDebugEnabled()) {
            log.debug("get value for j2cache which region:{} key:{} value:{}", name, id, obj.getValue());
        }
        return obj.getValue();
    }

    @Override
    public Object put(Object id, Object value) {
        if (log.isDebugEnabled()) {
            log.debug("set put the key to j2cache which region:{} key:{} type:{}", name, id, type);
        }
        if (ServerCacheType.QUERY == type)
            cache.set(name, id, value, 600);
        else
            cache.set(name, id, value);
        return value;//be care
    }

    @Override
    public Object remove(Object id) {
        log.info("remove key from j2cache which region:{} key:{}", name, id);
        cache.evict(name, id);
        return null;//be care
    }

    @Override
    public int size() {
        int size = cache.keys(name).size();
        log.info("get LEVEL_1 ElementCountInMemory which size" + size);
        return size;
    }

    @Override
    public void clear() {
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

    @Override
    public void init(EbeanServer ebeanServer) {

    }

    @Override
    public ServerCacheOptions getOptions() {
        return null;
    }

    @Override
    public void setOptions(ServerCacheOptions options) {

    }
}
