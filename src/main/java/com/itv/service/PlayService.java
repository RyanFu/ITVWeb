package com.itv.service;

import com.itv.pojo.MovieBean;
import com.itv.pojo.MoviePage;

import java.util.List;

/**
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午8:00
 */
public interface PlayService<T> {
    public T findMovieInfo(String id)throws Exception;
    public List<T> type(MoviePage mp)throws Exception;
    public int type_page(MoviePage mp)throws Exception;
    public List<T> search(String name) throws Exception;
    public List<String> searchAjax(String name) throws Exception;
}
