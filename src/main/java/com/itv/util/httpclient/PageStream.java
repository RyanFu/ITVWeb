package com.itv.util.httpclient;

import com.itv.util.SaeHttpClient;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.message.BasicHeader;
import org.apache.log4j.Logger;

import java.nio.charset.Charset;

/**
 * 获取网页的内容
 *
 * @author xiajun
 */
public final class PageStream {
    private final static Logger log = Logger.getLogger(PageStream.class);

    /**
     * 获取网页的内容并转成string类型
     *
     * @param url 网页的url
     * @return String 网页内容
     */
    public final static String getPageString(String url, Charset charset) {
        log.debug("开始请求url: " + url);
        HttpGet httpget = new HttpGet(url);
        httpget.setHeaders(getHeader());
        HttpClient httpClient = HttpClientPool.getHttpClient();
        HttpResponse response = null;
        String info_ = null;
        try {
            response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String info = EntityCharsetUtils.toString(entity, charset);
                info_ = info.replaceAll("\n", "").replaceAll("\r","");
            }
        } catch (Exception e) {
            log.error("获取网页内容时出现异常." + url, e);
        }
        return info_;
    }

    /**
     * sae 上使用httpclient
     * @param url
     * @return
     */
    public final static String getPageString(String url){
        log.debug("开始请求url: " + url);
        SaeHttpClient fetchUrl = new SaeHttpClient("j0momny25z","y4zw3jl054x1yl4zlhhwj0zhymk330kh5lz10kw4");
        return fetchUrl.fetch(url);
    }

    /**
     * 返回请求头信息
     */
    public static Header[] getHeader() {
        Header h1 = new BasicHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:21.0) Gecko/20100101 Firefox/21.0");
        Header h2 = new BasicHeader("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
        Header h3 = new  BasicHeader("Accept","	text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        Header h4 = new BasicHeader("Connection", "keep-alive");
        Header header[] = {h1, h2,h3, h4};
        return header;
    }

    public static void main(String[] args) throws Exception {
    }
}
