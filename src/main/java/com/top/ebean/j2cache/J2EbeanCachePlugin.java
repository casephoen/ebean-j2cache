package com.top.ebean.j2cache;

import com.avaje.ebean.BackgroundExecutor;
import com.avaje.ebean.cache.ServerCacheFactory;
import com.avaje.ebean.cache.ServerCachePlugin;
import com.avaje.ebean.config.ServerConfig;

/**
 * Created by lizhijun on 2017/4/6.
 */
public class J2EbeanCachePlugin implements ServerCachePlugin {

    @Override
    public ServerCacheFactory create(ServerConfig config, BackgroundExecutor executor) {
        return new J2EbeanCacheFactory();
    }
}
