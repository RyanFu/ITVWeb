package com.itv.pojo;

import java.io.Serializable;

/**
 * 第三方资源
 * User: xiajun
 * Date: 13-6-29
 * Time: 下午8:50
 */
public class Supplie implements Serializable {
    private String name;
    private String link;
    private String className;//样式名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        String cname=null;
        if(className.indexOf("乐视")>=0){
            cname="go_leshi";
        }else if(className.indexOf("土豆")>=0){
            cname="go_tudou";
        }else if(className.indexOf("爱奇艺")>=0||className.indexOf("奇艺")>=0){
            cname="go_qiyi";
        }else if(className.indexOf("迅雷")>=0){
            cname="go_xunlei";
        }else if(className.indexOf("华数")>=0){
            cname="go_huashu";
        }else if(className.indexOf("PPS")>=0||className.indexOf("pps")>=0){
            cname="go_pps";
        }else if(className.indexOf("电影网")>=0||className.indexOf("m1905")>=0){
            cname="go_m1905";
        }else if(className.indexOf("风行")>=0){
            cname="go_fengxing";
        }else if(className.indexOf("PPTV")>=0||className.indexOf("pptv")>=0){
            cname="go_pptv";
        }else if(className.indexOf("搜狐")>=0){
            cname="go_sohu";
        }else{
            cname="go_youku";
        }
        this.className = cname;
    }
}
