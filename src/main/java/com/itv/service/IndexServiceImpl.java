package com.itv.service;

import com.itv.dao.IBaseDao;
import com.itv.util.MemcacheUtil;
import com.sina.sae.memcached.SaeMemcache;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午6:52
 */
public class IndexServiceImpl<T> implements IndexService {
    private final static Logger log = Logger.getLogger(IndexServiceImpl.class);
    private IBaseDao baseDao;
    private static SaeMemcache mc = MemcacheUtil.getClient();

    public List<T> findFocusMap() throws Exception {
        List<T> list = null;
        if (mc != null) {
            try {
                list = mc.get("focusMap");
            }catch (Exception e){
                MemcacheUtil.init();
            }

        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findMovieIndex", 0);
            if (mc != null) {
                mc.set("focusMap", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 focusMap");
        } else {
            log.info("缓存命中 focusMap");
        }
        return list;
    }

    /**
     * 获取首页热播剧
     *
     * @return
     * @throws Exception
     */
    public List<T> hotPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("hotPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findHotMovie", 1);
            if (mc != null) {
                mc.set("hotPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 hotPlay");
        } else {
            log.info("缓存命中 hotPlay");
        }
        return list;
    }

    /**
     * 热播排行榜
     *
     * @return
     * @throws Exception
     */
    public List hotTopPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("hotTopPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findHotMovie", 6);
            if (mc != null) {
                mc.set("hotTopPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 hotTopPlay");
        } else {
            log.info("缓存命中 hotTopPlay");
        }
        return list;
    }

    /**
     * 首播剧
     *
     * @return
     * @throws Exception
     */
    public List netPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("netPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findHotMovie", 2);
            if (mc != null) {
                mc.set("netPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 netPlay");
        } else {
            log.info("缓存命中 netPlay");
        }
        return list;
    }

    /**
     * 首播排行榜
     *
     * @return
     * @throws Exception
     */
    public List netTopPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("netTopPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findNetMovie", 9);
            if (mc != null) {
                mc.set("netTopPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 netTopPlay");
        } else {
            log.info("缓存命中 netTopPlay");
        }
        return list;
    }

    /**
     * 预告剧场
     *
     * @return
     * @throws Exception
     */
    public List prePlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("prePlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findPreMove", 3);
            if (mc != null) {
                mc.set("prePlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 prePlay");
        } else {
            log.info("缓存命中 prePlay");
        }
        return list;
    }

    /**
     * 轻松剧场
     *
     * @return
     * @throws Exception
     */
    public List easyPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("easyPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findEasyMovie", 4);
            if (mc != null) {
                mc.set("easyPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 easyPlay");
        } else {
            log.info("缓存命中 easyPlay");
        }
        return list;
    }

    /**
     * 轻松排行榜
     *
     * @return
     * @throws Exception
     */
    public List easyTopPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("easyTopPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findEasyTopMovie", 8);
            if (mc != null) {
                mc.set("easyTopPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 easyTopPlay");
        } else {
            log.info("缓存命中 easyTopPlay");
        }
        return list;
    }

    /**
     * 激情剧场
     *
     * @return
     * @throws Exception
     */
    public List pasPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("pasPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findEasyMovie", 5);
            if (mc != null) {
                mc.set("pasPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 pasPlay");
        } else {
            log.info("缓存命中 pasPlay");
        }
        return list;
    }

    @Override
    public List pasTopPlay() throws Exception {
        List<T> list = null;
        if (mc != null) {
            list = mc.get("pasTopPlay");
        }
        if (list == null || list.size() <= 0) {
            list = this.baseDao.find("com.itv.movie.findEasyTopMovie", 9);
            if (mc != null) {
                mc.set("pasTopPlay", list, MemcacheUtil.TIMEOUT);
            }
            log.info("缓存未命中 pasTopPlay");
        } else {
            log.info("缓存命中 pasTopPlay");
        }
        return list;
    }

    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }

}
