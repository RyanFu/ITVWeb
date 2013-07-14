package com.itv.util;

import com.sina.sae.memcached.SaeMemcache;
import org.apache.log4j.Logger;

/**
 * User: xiajun
 * Date: 13-7-3
 * Time: 下午8:35
 */
public class MemcacheUtil {
    private final static Logger log=Logger.getLogger(MemcacheUtil.class);
    private static SaeMemcache mc = null;
    public final static int TIMEOUT=60*60;

    static {
        try {
            mc = new SaeMemcache("192.168.28.128", 11211);
            mc.init();
            log.info("memcache 初始化成功.");
        } catch (Exception e) {
            log.error("memchace 初始化失败",e);
        }

    }
    public static SaeMemcache getClient() {
        return mc;
    }
}
