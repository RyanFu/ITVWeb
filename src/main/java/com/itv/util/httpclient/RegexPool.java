package com.itv.util.httpclient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: xiajun
 * Date: 13-7-31
 * Time: 下午2:04
 */
public class RegexPool {
    public static Map<String, Map<String, String>> REGEX_POOL = new HashMap<String, Map<String, String>>();

    static {
        Map<String, String> r_360 = new HashMap<String, String>();
        String main_regex = "<div[^>]+?id=\"main\">[^}]+?<a[^>]+?class=\"pic[^}]+?<img[^>]+?src=\"([^\"]+?)\"" +
                "[^>]+?>[^}]+?<h1[^>]+?id=\"film_name\"[^}]+?>(.*?)</h1>[^}]+?<dt>导演：[^}]+?<a[^>]+?>(.*?)" +
                "</a>[^}]+?主演：[^}]+?<dd>([^}]+?)</dd>[^}]+?地区：[^}]+?<dd>(.*?)</dd>[^}]+?年代：[^}]+?<dd>(.*?)" +
                "</dd>[^}]+?片长：[^}]+?<dd>(.*?)</dd>[^}]+?<p[^>]+?class=\"value\"><span><em>(.*?)</em>(.*?)" +
                "</span>[^}]+?\\((.*?)人评[^}]+?<p[^>]+?class=\"less\">([^}]+?)<";
        String actor = "<a[^>]+?>([^<]+?)</a>";
        String url1 = "testSpeed\\.test\\((.*?)\\)";
        String url_div="[\"']name[\"']:[\"']([^\"']*?)[\"'].*?link[\"']:[\"']([^\"']*?)[\"']";
        String url2="<li[^>]+?data-speed=[\"']slow[\"']><a[^>]+?href=[\"']([^\"']+?)[\"'][^}]+?<em>(.*?)</em>";
        r_360.put("main", main_regex);
        r_360.put("actor", actor);
        r_360.put("url1", url1);
        r_360.put("url_div", url_div);
        r_360.put("url2", url2);
        Map<String, String> r_youku = new HashMap<String, String>();
        main_regex = "<li[^>]+?class=\"show_title\">[^}]+?<a[^>]+?>(.*?)</a>[^}]+?<span[^>]+?class=\"pub\">\\((.*?)" +
                "\\)</span>[^}]+?<em[^>]+?class=\"num\">(.*?)</em>[^}]+?地区:[^}]+?<a[^>]+?>(.*?)" +
                "</a>[^}]+?类型:[^}]+?<a[^>]+?>(.*?)</a>[^}]+?<span[^>]+?class=\"short\"[^>]+?>(.*?)" +
                "</span>[^}]+?演员:[^}]+?<ul>(.*?)订阅他们[^}]+?评论:[^}]+?<span[^>]+?class=\"num\">(.*?)</span>";
        actor="<li[^>]+?class=\"act-name\">[^}]*?<a[^>]+?>(.*?)</a>";
        r_youku.put("main",main_regex);
        r_youku.put("actor",actor);
        Map<String, String> r_letv = new HashMap<String, String>();

        Map<String, String> r_sohu = new HashMap<String, String>();
        main_regex="<div[^>]+?class=\"location\"><a[^>]+?>(.*?)</a>[^}]+?导演：<a[^>]+?>(.*?)</a>[^}]+?主演：(.*?)" +
                "</li>[^}]+?年份：<a[^>]+?>(.*?)</a>[^}]+?产地：<a[^>]+?>(.*?)</a>[^}]+?时长：(.*?[秒分])[^}]+?类型：(.*?)" +
                "</li>[^}]+?简介：(.*?)<a";
        actor="<a[^>]+?>(.*?)</a>";
        r_sohu.put("main",main_regex);
        r_sohu.put("actor",actor);

        Map<String, String> r_tudou = new HashMap<String, String>();
        main_regex="<div[^>]+?class=\"pic\">[^}]+?<img[^>]+?lazysrc=\"([^\"]+?)" +
                "\"[^}]+?<h6[^>]+?class=\"a_title\"><a[^>]+?>(.*?)</a>[^}]+?导演：[^}]+?<a[^>]+?>(.*?)" +
                "</a>[^}]+?主演：</span>(.*?)</div>[^}]+?类型：</span>(.*?)</span>[^}]+?年代[:：]+?\\s*?</span><a[^>]+?>(.*?)" +
                "</a>[^}]+?地区：</span><a[^>]+?>(.*?)</a>";
        actor="<a[^>]+?>(.*?)</a>";
        String less="<script>[^}]+?adesc='([^}]+?),ashortDesc";
        r_tudou.put("main",main_regex);
        r_tudou.put("actor",actor);
        r_tudou.put("less",less);
        REGEX_POOL.put("360", r_360);
        REGEX_POOL.put("youku", r_youku);
        REGEX_POOL.put("letv", r_letv);
        REGEX_POOL.put("sohu", r_sohu);
        REGEX_POOL.put("tudou", r_tudou);
    }
}
