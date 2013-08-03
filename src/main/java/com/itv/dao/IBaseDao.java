package com.itv.dao;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午6:09
 * To change this template use File | Settings | File Templates.
 */
public interface IBaseDao<T> {
    /**
     * 查询数据
     * @param sqlName sql语句名称(包括命名空间)
     * @param obj sql语句查询条件(数组，集合...)
     * @return 查询的list结果集
     * @throws Exception
     */
    public List<T> find(String sqlName,Object obj)throws Exception;

    /**
     * 根据主键查询对象
     * @param sqlName sql语句名称(包括命名空间)
     * @param obj sql语句查询条件(数组，集合...)
     * @return 查询结果
     * @throws Exception
     */
    public T findOne(String sqlName,Object obj)throws Exception;

    /**
     * 修改对象 where条件是对象的属性
     * @param obj 被修改对象
     * @param sqlName sql语句名称
     * @return boolean
     * @throws Exception
     */
    public boolean update(String sqlName,Object obj) throws Exception;
    public boolean insert(String sqlName,Object obj) throws Exception;
}
