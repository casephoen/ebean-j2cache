package com.top.ebean.j2cache;

import io.ebean.BackgroundExecutor;
import io.ebean.cache.ServerCacheFactory;
import io.ebean.cache.ServerCachePlugin;
import io.ebean.config.ServerConfig;

/**
 * Created by lizhijun on 2017/4/6.
 */
public class J2EbeanCachePlugin implements ServerCachePlugin {

    @Override
    public ServerCacheFactory create(ServerConfig config, BackgroundExecutor executor) {
        return new J2EbeanCacheFactory();
    }
}
