package com.itv.service;

import com.itv.dao.IBaseDao;
import com.itv.pojo.MovieBean;
import com.itv.pojo.MoviePage;
import com.itv.pojo.Supplie;
import com.itv.util.JsonUtil;
import com.itv.util.MemcacheUtil;
import com.sina.sae.memcached.SaeMemcache;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午8:00
 */
public class PlayServiceImpl<T> implements PlayService{
    private final static Logger log= Logger.getLogger(PlayServiceImpl.class);
    private IBaseDao baseDao;
    private static SaeMemcache mc = MemcacheUtil.getClient();
    public MovieBean findMovieInfo(String id)throws Exception{
        MovieBean mb=null;
        if(mc!=null){
            mb=mc.get(id);
        }
        if(mb==null){
            mb=(MovieBean)this.baseDao.findOne("com.itv.movie.findMovieInfo", id);
            List<Supplie> supplieList=null;
            if(mb!=null){
                List<LinkedHashMap<String,String>> list= JsonUtil.parseSupplie(mb.getSupplies());
                if(list!=null){
                    supplieList=new ArrayList<Supplie>();
                    for(int i=0;i<list.size();i++){
                        LinkedHashMap<String,String> map=list.get(i);
                        Supplie s=new Supplie();
                        String name=map.get("name");
                        s.setName(name);
                        s.setLink(map.get("link"));
                        s.setClassName(name);
                        supplieList.add(s);
                    }
                }
            }
            mb.setSupList(supplieList);
            if(mc!=null){
                mc.set(id,mb,MemcacheUtil.TIMEOUT);
            }
        }
        return mb;
    }

    /**
     * 各种条件查询
     * @param mp
     * @return
     * @throws Exception
     */
    public List type(MoviePage mp) throws Exception {
        List list=null;
        if(mc!=null){
           list= mc.get(mp.getSearchParam());
        }
        if(list==null||list.size()<=0){
            list=this.baseDao.find("com.itv.movie.type",mp);
            if(mc!=null){
                mc.set(mp.getSearchParam(),list,MemcacheUtil.TIMEOUT);
            }
        }
        return list;
    }

    /**
     * 获取总页数
     * @param mp
     * @return
     * @throws Exception
     */
    public int type_page(MoviePage mp)throws Exception{
        return (Integer)this.baseDao.findOne("com.itv.movie.typePage",mp);
    }

    /**
     * 搜索
     * @param name
     * @return
     * @throws Exception
     */
    public List<T> search(String name) throws Exception {
        return this.baseDao.find("com.itv.movie.search",name);
    }

    /**
     * ajax搜索提示
     * @param name
     * @return
     * @throws Exception
     */
    public List<String> searchAjax(String name) throws Exception {
        return this.baseDao.find("com.itv.movie.searchAjax",name);
    }
    public void setBaseDao(IBaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
