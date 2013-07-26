package com.itv.pojo;

/**
 * User: xiajun
 * Date: 13-7-7
 * Time: 下午2:04
 */
public class MoviePage extends MovieBean{
    public int page_size=30;
    public int page_num;//总页数
    private int page=1;//当前页面
    private int page_cur;//当前条数
    public String getSearchParam(){
        return getTypeName()+getActor()+getArea()+getYear()+page;
    }

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage_cur() {
        return page>=1?(page-1)*page_size:0;
    }

    public void setPage_cur(int page_cur) {
        this.page_cur = page_cur;
    }

    public int getPage_size() {
        return page_size;
    }

    public void setPage_size(int page_size) {
        this.page_size = page_size;
    }
}
