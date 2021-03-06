package com.itv.action;

import com.itv.pojo.MovieBean;
import com.itv.pojo.MoviePage;
import com.itv.service.ManageService;
import com.itv.util.CheckUtil;
import com.itv.util.Crypt;
import com.itv.util.httpclient.PageStream;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * User: xiajun
 * Date: 13-7-14
 * Time: 下午2:06
 */
public class ManageAction {
    private final static Logger log=Logger.getLogger(ManageAction.class);
    private String name;
    private String pwd;
    private ManageService manageService;
    private MovieBean mb;
    private MoviePage mp;
    private String url;
    private List<MovieBean> list;

    /**
     * 管理员登录
     * @return
     */
    public String login(){
        this.name=CheckUtil.replaceAll(name);
        this.pwd=CheckUtil.replaceAll(pwd);
        try {
            boolean islogin=this.manageService.login(this.name,this.pwd);
            if(islogin){
                Long time= new Date().getTime();
                String msg=Crypt.parseByte2HexStr(Crypt.encrypt(name+":"+pwd+":"+time, "1234567887654321"));
                Cookie user = new Cookie("user",msg);
                user.setMaxAge(-1);//当浏览器关闭时清除
                ServletActionContext.getResponse().addCookie(user);
                return "login";
            }
        } catch (Exception e) {
            log.error("",e);
        }
        return "reLogin";
    }

    /**
     * 查询残缺视频
     * @return
     */
    public String findFillMovie(){
        try {
            if (this.mp == null) {
                this.mp=new MoviePage();
            }
            this.mp.setPage_size(10);
            if(this.name!=null&&!"".equals(name.trim())){
                this.mp.setName(this.name);
            }
            int count=this.manageService.fillMoviePage(this.mp);
            if (count > 0) {
                this.mp.setPage_num(count / 10 + (count % 10 == 0 ? 0 : 1));
            }
            this.list=this.manageService.findFillMovie(this.mp);
        } catch (Exception e) {
            log.error("",e);
        }
        return "manage";
    }

    /**
     * 插件发过来的添加
     */
    public void pluginAdd(){
        HttpServletResponse res= ServletActionContext.getResponse();
        boolean isadd=false;
        try {
            log.info("url: "+this.url);
            isadd = manageService.pluginAdd(this.url);
        } catch (Exception e) {
            log.error("",e);
        }
        res.setContentType("text/hthl;charset=utf-8");
        PrintWriter pw= null;
        try {
            pw = res.getWriter();
        } catch (IOException e) {
            log.error("",e);
        }
        pw.write(Boolean.toString(isadd));
        pw.close();
    }
    public String updateMovie() throws Exception {
        this.manageService.updateMovie(this.mb);
        return "updateMovie";
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setManageService(ManageService manageService) {
        this.manageService = manageService;
    }

    public void setMb(MovieBean mb) {
        this.mb = mb;
    }

    public MovieBean getMb() {
        return mb;
    }

    public void setList(List<MovieBean> list) {
        this.list = list;
    }

    public List<MovieBean> getList() {
        return list;
    }

    public MoviePage getMp() {
        return mp;
    }

    public void setMp(MoviePage mp) {
        this.mp = mp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
