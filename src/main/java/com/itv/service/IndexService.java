package com.itv.service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午6:52
 */
public interface IndexService<T> {
    /**
     * 获取首页焦点图
     * @return
     * @throws Exception
     */
    public List<T> findFocusMap() throws Exception;

    /**
     * 获取首页热播剧
     * @return
     * @throws Exception
     */
    public List<T> hotPlay()throws  Exception;

    /**
     * 热播排行
     * @return
     * @throws Exception
     */
    public List<T> hotTopPlay()throws Exception;

    /**
     * 首播剧
     * @return
     * @throws Exception
     */
    public List<T> netPlay()throws Exception;

    /**
     * 首播排行榜
     * @return
     * @throws Exception
     */
    public List<T> netTopPlay()throws Exception;

    /**
     * 预告剧场
     * @return
     * @throws Exception
     */
    public List<T> prePlay() throws Exception;

    /**
     * 轻松剧场
     * @return
     * @throws Exception
     */
    public List<T> easyPlay() throws Exception;

    /**
     * 轻松排行榜
     * @return
     * @throws Exception
     */
    public List<T> easyTopPlay()throws Exception;

    /**
     * 激情剧场
     * @return
     * @throws Exception
     */
    public List<T> pasPlay()throws Exception;

    /**
     * 激情排行榜
     * @return
     * @throws Exception
     */
    public List<T> pasTopPlay()throws Exception;
}
