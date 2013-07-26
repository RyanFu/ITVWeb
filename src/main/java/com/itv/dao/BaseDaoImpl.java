package com.itv.dao;

import org.apache.log4j.Logger;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午6:09
 */
public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements IBaseDao {
    private final static Logger log=Logger.getLogger(BaseDaoImpl.class);

    /**
     *
     * @param sqlName sql语句名称(包括命名空间)
     * @param obj sql语句查询条件(数组，集合...)
     * @return
     * @throws Exception
     */
    public List<T> find(String sqlName,Object obj)throws Exception{
        return  getSqlSession().selectList(sqlName,obj);
    }

    /**
     *
     * @param sqlName sql语句名称(包括命名空间)
     * @param obj sql语句查询条件(数组，集合...)
     * @return
     * @throws Exception
     */
    public Object findOne(String sqlName, Object obj) throws Exception {
        return getSqlSession().selectOne(sqlName,obj);
    }

    /**
     * 修改
     * @param sqlName sql语句名称
     * @param obj 被修改对象
     * @return boolean
     * @throws Exception
     */
    public boolean update(String sqlName,Object obj) throws Exception {
        return getSqlSession().update(sqlName,obj)>0?true:false;
    }
}
