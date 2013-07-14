package com.itv.action;

import com.itv.pojo.MovieBean;
import com.itv.service.PlayService;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午7:55
 */
public class PlayAction {
    private final static Logger log=Logger.getLogger(PlayAction.class);
    private PlayService<MovieBean> playService;
    private String id;//视频主键id
    private MovieBean mb;
    public String play(){
        try {
           this.mb= this.playService.findMovieInfo(this.id);
        } catch (Exception e) {
            log.error("PlayAction ",e);
        }
        return "play";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPlayService(PlayService playService) {
        this.playService = playService;
    }

    public MovieBean getMb() {
        return mb;
    }
}
