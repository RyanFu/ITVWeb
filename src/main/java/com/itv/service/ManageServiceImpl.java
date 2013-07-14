package com.itv.service;

import com.itv.dao.IBaseDao;
import com.itv.util.Crypt;
import com.itv.util.MemcacheUtil;
import com.sina.sae.memcached.SaeMemcache;

/**
 * User: xiajun
 * Date: 13-7-14
 * Time: 下午2:11
 */
public class ManageServiceImpl implements ManageService{
    private IBaseDao baseDao;
    private static SaeMemcache mc =MemcacheUtil.getClient();

    /**
     * 用户登录
     * @param name 用户名
     * @param pwd  密码(name+pwd 后md5)
     * @return
     * @throws Exception
     */
    public boolean login(String name, String pwd) throws Exception {
        String dbpwd=null;
        if(mc!=null){
            dbpwd=mc.get(name);
        }
        if(dbpwd==null){
            dbpwd=(String)this.baseDao.findOne("com.itv.manage.login",name);
            dbpwd= Crypt.MD5(name + dbpwd);
            if(mc!=null){
                mc.set(name,dbpwd,MemcacheUtil.TIMEOUT);
            }
        }
        if(dbpwd.equalsIgnoreCase(pwd)){
            return true;
        }
        return false;
    }

    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
