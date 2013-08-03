package com.itv.service;

import com.itv.pojo.MovieBean;
import com.itv.pojo.MoviePage;

import java.util.List;

/**
 * User: xiajun
 * Date: 13-7-14
 * Time: 下午2:10
 */
public interface ManageService {
    /**
     * 登录处理
     * @param name 用户名
     * @param pwd  md5(用户名+密码)
     * @return boolean
     * @throws Exception
     */
    public boolean login(String name,String pwd)throws Exception;

    /**
     * 查询残缺视频
     * @return
     * @throws Exception
     */
    public List<MovieBean> findFillMovie(MoviePage mp)throws Exception;
    public Integer fillMoviePage(MoviePage mp) throws Exception;

    /**
     * 修补残缺视频信息
     * @param mb
     * @throws Exception
     */
    public void updateMovie(MovieBean mb)throws  Exception;

    /**
     * 根据插件提交过来的地址进行修改视频信息
     * @param url
     * @return
     * @throws Exception
     */
    public boolean pluginAdd(String url) throws Exception;
}
