package com.itv.pojo.view;

import java.io.Serializable;

/**
 * 热播剧显示视图
 * User: xiajun
 * Date: 13-6-30
 * Time: 下午2:52
 */
public class MovieView implements Serializable {
    private String id;
    private String name;// 影片名称
    private String supplierUrl;// 视频所在供应商地址
    private String bigImgUrl;// 大图地址
    private String miniImgUrl;// 小图地址
    private String text;// 文本介绍
    private String mid;//movie表主键id
    private String actor;//movie 表主演
    private String asactor;//主演名称截取

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSupplierUrl() {
        return supplierUrl;
    }

    public void setSupplierUrl(String supplierUrl) {
        this.supplierUrl = supplierUrl;
    }

    public String getBigImgUrl() {
        return bigImgUrl;
    }

    public void setBigImgUrl(String bigImgUrl) {
        this.bigImgUrl = bigImgUrl;
    }

    public String getMiniImgUrl() {
        return miniImgUrl;
    }

    public void setMiniImgUrl(String miniImgUrl) {
        this.miniImgUrl = miniImgUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getAsactor() {
        return asactor;
    }

    public void setAsactor(String asactor) {
        String actor="";
        if(asactor!=null){
            String []str=asactor.split(" ");
            for(int i=0;i<str.length;i++){
                String s1=str[i];
                if(s1.indexOf("·")>=0){
                    actor+=s1.split("·")[1]+" ";
                }else{
                    actor+=s1+" ";
                }
                if(i>=1){
                    actor.trim();
                    break;
                }
            }
        }
        this.asactor = actor;
    }
}
