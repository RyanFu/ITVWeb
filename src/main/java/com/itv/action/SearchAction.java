package com.itv.action;

import com.itv.pojo.MovieBean;
import com.itv.pojo.MoviePage;
import com.itv.service.PlayService;
import com.itv.util.CheckUtil;
import com.itv.util.JsonUtil;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * User: xiajun
 * Date: 13-7-6
 * Time: 下午11:06
 */
public class SearchAction {
    private final static Logger log = Logger.getLogger(SearchAction.class);
    private MovieBean mb;
    private MoviePage mp;
    private PlayService<MovieBean> playService;
    private List<MovieBean> list;
    private List pageList;
    private String name;

    /**
     * 分类导航
     *
     * @return
     */
    public String type() {
        try {
            this.list = this.playService.type(this.mp);
            int page_count = this.playService.type_page(this.mp);
            this.mp.setPage(mp.getPage() <= 0 ? 1 : mp.getPage());
            if (page_count > 0) {
                this.mp.setPage_num(page_count / 30 + (page_count % 30 == 0 ? 0 : 1));
            }
            pageList = new ArrayList();
            if (mp.getPage() >= 10) {
                if (mp.getPage() % 10 == 0) {
                    for (int i = 4; i > 0; i--) {
                        pageList.add(mp.getPage() - i);
                    }
                    pageList.add(mp.getPage());
                    for (int i = 1; i < 6; i++) {
                        if ((mp.getPage() + i) <= mp.getPage_num()) {
                            pageList.add(mp.getPage() + i);
                        }
                    }
                } else {
                    int j = mp.getPage() / 10;
                    for (int i = j * 10 + 1; i < j * 10 + 11; i++) {
                        if (i <= mp.getPage_num()) {
                            pageList.add(i);
                        }
                    }
                }
            } else {
                for (int i = 1; i < 11; i++) {
                    if (i <= mp.getPage_num()) {
                        pageList.add(i);
                    }
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return "type";
    }

    /**
     * 搜索
     */
    public String search() {
        try {
            name = CheckUtil.replaceAll(name);
            if (name == null) {
                return "index_";
            }
            this.list = this.playService.search(this.name);
        } catch (Exception e) {
            log.error("", e);
        }
        return "search";
    }

    /**
     * ajax搜索
     */
    public void searchAjax() {
        name = CheckUtil.replaceAll(name);
        if (name == null) {
            return;
        }
        try {
            List<String> ajaxList=this.playService.searchAjax(name);
            String s=JsonUtil.parseStr(ajaxList);
            HttpServletResponse res= ServletActionContext.getResponse();
            res.setContentType("text/hthl;charset=utf-8");
            PrintWriter pw= res.getWriter();
            pw.write(s);
            pw.close();
        } catch (Exception e) {
            log.error("ajax搜索异常",e);
        }
    }

    public void setPlayService(PlayService<MovieBean> playService) {
        this.playService = playService;
    }

    public MovieBean getMb() {
        return mb;
    }

    public void setMb(MovieBean mb) {
        this.mb = mb;
    }

    public List<MovieBean> getList() {
        return list;
    }

    public void setList(List<MovieBean> list) {
        this.list = list;
    }

    public MoviePage getMp() {
        return mp;
    }

    public void setMp(MoviePage mp) {
        this.mp = mp;
    }

    public List getPageList() {
        return pageList;
    }

    public void setPageList(List pageList) {
        this.pageList = pageList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
