package com.itv.pojo;

/**
 * User: xiajun
 * Date: 13-7-7
 * Time: 下午2:04
 */
public class MoviePage {
    public static int page_size=30;
    public int page_num;//总页数
    private String id;// 主键id
    private String name;// 影视名称
    private String director;// 导演
    private String actor;// 主演
    private String area;// 地区
    private String year;// 年代
    private int page;//当前页面
    private String duration;
    private String value;
    private String imgUrl;
    private int page_cur;//当前条数

    public String getSearchParam(){
        return actor+area+year+page;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage_cur() {
        return page>=1?(page-1)*30:0;
    }
}
