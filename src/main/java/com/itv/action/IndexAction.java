package com.itv.action;

import com.itv.pojo.MovieFocusMap;
import com.itv.pojo.view.MovieView;
import com.itv.service.IndexService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * User: xiajun
 * Date: 13-6-26
 * Time: 下午9:11
 */
public class IndexAction {
    private final static Logger log=Logger.getLogger(IndexAction.class);
    private IndexService indexService;
    private List<MovieFocusMap> list;//焦点图
    private List<MovieView> hotList;//热播
    private List<MovieView> hotTopList;//热播排行
    private List<MovieView> netList;//首播
    private List<MovieView> netTopList;//首播排行榜
    private List<MovieView> preList;//预告剧场
    private List<MovieView> easyList;//轻松剧场
    private List<MovieView> easyTopList;//轻松排行榜
    private List<MovieView> pasList;//激情剧场
    private List<MovieView> pasTopList;//激情排行榜
    public String index(){
        try {
            this.list=this.indexService.findFocusMap();//焦点图
            this.hotList=this.indexService.hotPlay();//热播剧
            this.hotTopList=this.indexService.hotTopPlay();
            this.netList=this.indexService.netPlay();
            this.netTopList=this.indexService.netTopPlay();
            this.preList=this.indexService.prePlay();
            this.easyList=this.indexService.easyPlay();
            this.easyTopList=this.indexService.easyTopPlay();
            this.pasList=this.indexService.pasPlay();
            this.pasTopList=this.indexService.pasTopPlay();
        } catch (Exception e) {
            log.error("indexAction ",e);
        }
        return "index";
    }

    public List getList() {
        return list;
    }
    public List<MovieView> getHotList() {
        return hotList;
    }

    public List<MovieView> getHotTopList() {
        return hotTopList;
    }

    public List<MovieView> getNetList() {
        return netList;
    }

    public List<MovieView> getNetTopList() {
        return netTopList;
    }

    public List<MovieView> getPreList() {
        return preList;
    }

    public List<MovieView> getEasyList() {
        return easyList;
    }

    public List<MovieView> getEasyTopList() {
        return easyTopList;
    }

    public List<MovieView> getPasTopList() {
        return pasTopList;
    }

    public List<MovieView> getPasList() {
        return pasList;
    }

    public void setIndexService(IndexService indexService) {
        this.indexService = indexService;
    }
}
