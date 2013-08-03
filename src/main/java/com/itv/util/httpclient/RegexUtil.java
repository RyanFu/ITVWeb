package com.itv.util.httpclient;

import com.itv.pojo.MovieBean;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: xiajun
 * Date: 13-7-31
 * Time: 下午2:14
 */
public class RegexUtil {
    /**
     * 获取url所对应的域名
     *
     * @param url
     * @return
     */
    public static String domain(String url) {
        if (url == null) {
            return null;
        }
        Pattern p = Pattern.compile("^http[s]?://([^/]+?)/");
        Matcher m = p.matcher(url);
        String domain = null;
        while (m.find()) {
            domain = m.group(1);
        }
        if (domain != null) {
            if ("v.360.cn".equalsIgnoreCase(domain)) {
                return "360";
            } else if ("tv.sohu.com".equalsIgnoreCase(domain)) {
                return "sohu";
            } else if ("v.youku.com".equalsIgnoreCase(domain)) {
                return "youku";
            } else if ("www.letv.com".equalsIgnoreCase(domain)) {
                return "letv";
            } else if ("www.tudou.com".equalsIgnoreCase(domain)) {
                return "tudou";
            }
        }
        return null;
    }

    /**
     * 获取视频信息
     *
     * @param info
     * @return
     */
    public static MovieBean getMovie(String info, String domain, String url) {
        if (info == null || "".equals(info)) {
            return null;
        }
        Map<String, String> map = RegexPool.REGEX_POOL.get(domain);
        Long stime = System.currentTimeMillis();
        Pattern p = Pattern.compile(map.get("main"));
        Matcher m = p.matcher(info);
        MovieBean mb = new MovieBean();
        if ("360".equals(domain)) {
            movie_info_360(m, mb);
            mb.setActor(getActor(mb.getActor(), map));
            mb.setSupplies(getSupplies(info, map, mb));
            mb.setSupplierUrl(url);
        } else if ("youku".equals(domain)) {
            movie_info_youku(m, mb);
            mb.setActor(getActor(mb.getActor(), map));
            mb.setSupplies("{\"name\":\"优酷\",\"link\":\"" + url + "\"}");
            mb.setSupplierUrl(url);
            mb.setSuppliesCountl(1);
        } else if ("tudou".equals(domain)) {
            movie_info_tudou(m, mb);
            mb.setActor(getActor(mb.getActor(), map));
            mb.setTypeName(getActor(mb.getTypeName(), map));//类型和主演的获取方式一样
            mb.setLess(getLess(info,map));
            mb.setSupplies("{\"name\":\"土豆\",\"link\":\"" + url + "\"}");
            mb.setSupplierUrl(url);
            mb.setSuppliesCountl(1);
        }else if ("sohu".equals(domain)) {
            movie_info_soho(m, mb);
            mb.setActor(getActor(mb.getActor(),map));
            mb.setTypeName(getActor(mb.getTypeName(),map));
            mb.setSupplies("{\"name\":\"搜狐\",\"link\":\"" + url + "\"}");
            mb.setSupplierUrl(url);
            mb.setSuppliesCountl(1);
        }
        System.out.println("获取视频信息的正则消耗:" + (System.currentTimeMillis() - stime) + "毫秒");
        return mb;
    }

    private static void movie_info_360(Matcher m, MovieBean mb) {
        while (m.find()) {
            mb.setImgUrl(m.group(1));
            mb.setName(m.group(2));
            mb.setDirector(m.group(3));
            mb.setActor(m.group(4));
            mb.setArea(m.group(5));
            mb.setYear(m.group(6));
            mb.setDuration(m.group(7));
            mb.setValue(Float.parseFloat(m.group(8) + m.group(9)));
            mb.setRatingCount(Integer.parseInt(m.group(10)));
            mb.setLess(m.group(11));
        }
    }

    private static void movie_info_youku(Matcher m, MovieBean mb) {
        while (m.find()) {
            mb.setName(m.group(1));
            mb.setYear(m.group(2));
            mb.setValue(Float.parseFloat(m.group(3)));
            mb.setArea(m.group(4));
            mb.setTypeName(m.group(5));
            mb.setLess(m.group(6));
            mb.setActor(m.group(7));
            mb.setRatingCount(Integer.parseInt(m.group(8).replaceAll(",", "")));
        }
    }

    private static void movie_info_tudou(Matcher m, MovieBean mb) {
        while (m.find()) {
            mb.setImgUrl(m.group(1));
            mb.setName(m.group(2));
            mb.setDirector(m.group(3));
            mb.setActor(m.group(4));
            mb.setTypeName(m.group(5));
            mb.setYear(m.group(6));
            mb.setArea(m.group(7));
        }
    }

    private static void movie_info_soho(Matcher m, MovieBean mb){
        while (m.find()) {
            mb.setName(m.group(1));
            mb.setDirector(m.group(2));
            mb.setActor(m.group(3));
            mb.setYear(m.group(4));
            mb.setArea(m.group(5));
            mb.setDuration(m.group(6));
            mb.setTypeName(m.group(7));
            mb.setLess(m.group(8));
        }
    }
    /**
     * 获取主演信息
     *
     * @param info
     * @param map
     * @return
     */
    private static String getActor(String info, Map<String, String> map) {
        if (info == null) {
            return null;
        }
        long st = System.currentTimeMillis();
        String regex_ = map.get("actor");
        Pattern p = Pattern.compile(regex_);
        Matcher m = p.matcher(info);
        StringBuffer actor = new StringBuffer();
        while (m.find()) {
            actor.append(m.group(1));
            actor.append(" ");
        }
        System.out.println("获取主演的正则耗费:" + (System.currentTimeMillis() - st) + "毫秒");
        return actor.toString().trim();
    }

    /**
     * 获取播放地址
     * 360专属
     *
     * @param info
     * @param mb
     * @return
     */
    private static String getSupplies(String info, Map<String, String> map, MovieBean mb) {
        if (info == null) {
            return null;
        }
        String regex_ = map.get("url1");
        Pattern p = Pattern.compile(regex_);
        Matcher m = p.matcher(info);
        if (m.find()) {
            int count = 0;
            String str = m.group(1);
            regex_ = map.get("url_div");
            p = Pattern.compile(regex_);
            m = p.matcher(str);
            StringBuffer sb = new StringBuffer("[");
            while (m.find()) {
                sb.append("{\"name\":\"");
                sb.append(decodeUnicode(m.group(1)));
                sb.append("\",\"link\":\"");
                sb.append(m.group(2));
                sb.append("\"},");
                count++;
            }
            sb.replace(sb.length() - 1, sb.length(), "");
            sb.append("]");
            mb.setSuppliesCountl(count);
            return sb.toString().replaceAll("\\\\", "");
        } else {
            regex_ = map.get("url2");
            p = Pattern.compile(regex_);
            m = p.matcher(info);
            while (m.find()) {
                mb.setSuppliesCountl(1);
                return "{\"name\":\"" + m.group(2) + "\",\"link\":\"" + m.group(1) + "\"}";
            }
        }
        return null;
    }

    private static String getLess(String info, Map<String, String> map) {
        if (info == null) {
            return null;
        }
        String regex_ = map.get("less");
        Pattern p = Pattern.compile(regex_);
        Matcher m = p.matcher(info);
        String less=null;
        if (m.find()) {
            less=m.group(1);
        }
        return less;
    }

    /**
     * unicode 转中文
     *
     * @param dataStr
     * @return
     */
    public static String decodeUnicode(String dataStr) {
        if (dataStr.equals("") || dataStr.indexOf("\\u") < 0) {
            return dataStr;
        }
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        int i = dataStr.indexOf("\\u");
        if (i > 0) {
            buffer.append(dataStr.substring(0, i));
            dataStr = dataStr.substring(i);
        }
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, start + 2 + 4);
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16);
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}
