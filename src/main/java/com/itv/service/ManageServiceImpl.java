package com.itv.service;

import com.itv.dao.IBaseDao;
import com.itv.pojo.MovieBean;
import com.itv.pojo.MoviePage;
import com.itv.util.Crypt;
import com.itv.util.MemcacheUtil;
import com.itv.util.httpclient.PageStream;
import com.itv.util.httpclient.RegexUtil;
import com.sina.sae.memcached.SaeMemcache;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * User: xiajun
 * Date: 13-7-14
 * Time: 下午2:11
 */
public class ManageServiceImpl implements ManageService{
    private IBaseDao baseDao;
    private static SaeMemcache mc =MemcacheUtil.getClient();
    private final static Logger log=Logger.getLogger(ManageServiceImpl.class);

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

    /**
     * 查询残缺
     * @param mp
     * @return
     * @throws Exception
     */
    public List<MovieBean> findFillMovie(MoviePage mp) throws Exception {
        return this.baseDao.find("com.itv.manage.findFillMovie",mp);
    }

    /**
     * 残缺信息查询分页
     * @param mp
     * @return
     * @throws Exception
     */
    public Integer fillMoviePage(MoviePage mp) throws Exception{
        return (Integer)this.baseDao.findOne("com.itv.manage.findFillMoviePage",mp);
    }

    /**
     * 修补视频信息
     * @param mb
     * @throws Exception
     */
    public void updateMovie(MovieBean mb)throws  Exception{
       boolean b=this.baseDao.update("com.itv.manage.updateMovie",mb);
        if (b&&mc!=null) {
            mc.delete(mb.getId());
        }
    }

    /**
     * 插件提交过来的url
     * @param url
     * @return
     */
    public boolean pluginAdd(String url) throws Exception {
        String domain=RegexUtil.domain(url);
        boolean isUpdate=false;
        if (domain != null) {
            String info=PageStream.getPageString(url);
            MovieBean mb=RegexUtil.getMovie(info,domain,url);
            MovieBean mb2= (MovieBean) this.baseDao.findOne("com.itv.manage.pluginSelect",mb);
            if (mb2 !=null&&mb2.getSuppliesCount()>=1) {
                mb.setSupplies(mb2.getSupplies());
                mb.setSupplierUrl(mb2.getSupplierUrl());
                mb.setSuppliesCountl(mb2.getSuppliesCount());
            }
            isUpdate=this.baseDao.update("com.itv.manage.pluginUpdate",mb);
            log.info("视频修改 "+isUpdate+" 信息: "+mb.toString());
            if(!isUpdate){
                isUpdate=this.baseDao.insert("com.itv.manage.pluginInsert",mb);
                log.info("添加视频 "+isUpdate);
            }
        }
        return isUpdate;
    }
    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
