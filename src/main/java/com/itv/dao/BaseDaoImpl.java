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

    public List<T> find(String sqlName,Object obj)throws Exception{
        return  getSqlSession().selectList(sqlName,obj);
    }

    public Object findOne(String sqlName, Object obj) throws Exception {
        return getSqlSession().selectOne(sqlName,obj);
    }
}
