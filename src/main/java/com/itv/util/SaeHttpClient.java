package com.itv.util;

import com.itv.util.httpclient.EntityCharsetUtils;
import com.sina.sae.fetchurl.BinaryData;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class SaeHttpClient
{
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    private static final String PUT = "PUT";
    private static final String HEAD = "HEAD";
    private static final String OPTIONS = "OPTIONS";
    private static final String TRACE = "TRACE";
    private static final int MAX_REDIRECT_NUM = 5;
    private static final int MAX_SEND_DATA = 10485760;
    private static Logger logger = Logger.getLogger(SaeHttpClient.class.getName());
    private final String accessKey;
    private final String secretKey;
    private String method = "GET";

    private final HttpClient httpclient = FetchurlUtil.wrapClient(new DefaultHttpClient());

    private final Map<String, String> headers = new HashMap();

    private final Map<String, ArrayList<String>> reduplicateHeaders = new HashMap();
    private HttpRequestBase http;
    private final Set<String> forbidHead = new HashSet();

    private Map<String, String> addHttpHeaders = new HashMap();

    private Map<String, ArrayList<String>> reduplicateAddHttpHeaders = new HashMap();
    private Map<String, String> postMap;
    private boolean isPost = false;

    private int saeStatusCode = 0;

    private int httpStatusCode = 200;

    private String statusMsg = "";

    private boolean isAllowRedirect = true;

    private List<BinaryData> binaryList = new ArrayList();

    private String body = "";

    private String characterEncode = "UTF-8";

    private boolean isTrunacated = false;

    public SaeHttpClient()
    {
        Random rand = new Random();
        this.accessKey = Integer.toHexString(rand.nextInt());
        this.secretKey = Long.toHexString(rand.nextLong());
        initForbidHeaders();
    }

    public SaeHttpClient(String accessKey, String secretKey)
    {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        initForbidHeaders();
    }

    private void initForbidHeaders()
    {
        this.forbidHead.add("content-length");
        this.forbidHead.add("host");
        this.forbidHead.add("vary");
        this.forbidHead.add("via");
        this.forbidHead.add("x-forwarded-for");
        this.forbidHead.add("fetchUrl");
        this.forbidHead.add("accessKey");
        this.forbidHead.add("timeStamp");
        this.forbidHead.add("signature");
        this.forbidHead.add("allowTruncated");
        this.forbidHead.add("connectTimeout");
        this.forbidHead.add("sendTimeout");
        this.forbidHead.add("readTimeout");
    }

    private HttpRequestBase choiceHttpMethod(String method, String url)
    {
        String httpMethod = method.toUpperCase();
        if ("GET".equals(httpMethod))
            return new HttpGet(url);
        if ("POST".equals(httpMethod))
            return new HttpPost(url);
        if ("DELETE".equals(httpMethod))
            return new HttpDelete(url);
        if ("PUT".equals(httpMethod))
            return new HttpPut(url);
        if ("HEAD".equals(httpMethod))
            return new HttpHead(url);
        if ("OPTIONS".equals(httpMethod))
            return new HttpOptions(url);
        if ("TRACE".equals(httpMethod)) {
            return new HttpTrace(url);
        }
        return new HttpGet(url);
    }

    private void genPostRequest(HttpRequestBase httpBase)
    {
        if ((httpBase instanceof HttpPost))
            try {
                HttpPost post = (HttpPost)httpBase;

                if ((this.postMap != null) && (this.binaryList.size() == 0)) {
                    List nvps = new ArrayList();
                    Set entry = this.postMap.entrySet();
                    Iterator iter = entry.iterator();
                    while (iter.hasNext()) {
                        Map.Entry entryValue = (Map.Entry)iter.next();
                        String name = (String)entryValue.getKey();
                        String value = (String)entryValue.getValue();
                        nvps.add(new BasicNameValuePair(name, value));
                    }
                    if ((null == this.characterEncode) || ("".equals(this.characterEncode))) {
                        this.characterEncode = "UTF-8";
                    }
                    post.setEntity(new UrlEncodedFormEntity(nvps, this.characterEncode));
                }

                if (this.binaryList.size() > 0) {
                    MultipartEntity entity = new MultipartEntity();
                    Iterator binaryIter = this.binaryList.iterator();
                    int size = 0;
                    while (binaryIter.hasNext()) {
                        BinaryData bd = (BinaryData)binaryIter.next();
                        ByteArrayBody bab = null;
                        if (this.isTrunacated) {
                            if (size + bd.getPostData().length > 10485760) {
                                bab = new ByteArrayBody(bd.getPostData(), bd.getFileName());
                                logger.warn("send data is over 10M");
                                break;
                            }
                            size += bd.getPostData().length;
                            bab = new ByteArrayBody(bd.getPostData(), bd.getFileName());
                        }
                        else {
                            bab = new ByteArrayBody(bd.getPostData(), bd.getFileName());
                            entity.addPart(bd.getInputParameterName(), bab);
                        }
                    }

                    if (this.postMap != null) {
                        Set entry = this.postMap.entrySet();
                        Iterator iter = entry.iterator();
                        while (iter.hasNext()) {
                            Map.Entry entryValue = (Map.Entry)iter.next();
                            String name = (String)entryValue.getKey();
                            String value = (String)entryValue.getValue();
                            entity.addPart(name, new StringBody(value));
                        }
                    }
                    post.setEntity(entity);
                }
            } catch (UnsupportedEncodingException e) {
                logger.error("gen post request header failure.", e);
            }
        else
            logger.error("current http method not post!");
    }

    private void parseResponseStatus(HttpResponse response)
    {
        StatusLine line = response.getStatusLine();
        int statusCode = line.getStatusCode();
        String reasonPhrase = line.getReasonPhrase();
        if (200 == statusCode) {
            this.saeStatusCode = 0;
        } else {
            this.saeStatusCode = statusCode;
            this.httpStatusCode = statusCode;
        }
        if ("OK".equals(reasonPhrase))
            this.statusMsg = "";
        else
            this.statusMsg = reasonPhrase;
    }

    private void debugRequestHeader(HttpRequestBase http)
    {
        logger.debug(new StringBuilder().append("request url = ").append(http.getRequestLine().getUri()).toString());
        logger.debug(new StringBuilder().append("request method = ").append(http.getRequestLine().getMethod()).toString());
        logger.debug(new StringBuilder().append("request http version = ").append(http.getRequestLine().getProtocolVersion()).toString());
        Header[] headers = http.getAllHeaders();
        logger.debug("request headers:");
        for (Header h : headers)
            logger.debug(new StringBuilder().append(h.getName()).append(" : ").append(h.getValue()).toString());
    }

    private void debugPostData(HttpEntity entity)
    {
        try
        {
            logger.debug(EntityUtils.toString(entity));
        } catch (ParseException e) {
            logger.error("debug failure", e);
        } catch (IOException e) {
            logger.error("debug failure", e);
        }
    }

    private HttpEntity reFetch(String redirectUrl)
    {
        try
        {
            boolean isRedirect = true;
            HttpResponse response = null;
            for (int i = 0; (i < 5) && (isRedirect); i++) {
                HttpGet get = new HttpGet(redirectUrl);
                genHttpHeader(get, redirectUrl);

                if (logger.isDebugEnabled()) {
                    debugRequestHeader(get);
                }
                response = new DefaultHttpClient().execute(get);
                Header[] responseHeaders = response.getAllHeaders();
                if (logger.isDebugEnabled()) {
                    for (Header h : responseHeaders) {
                        logger.debug(new StringBuilder().append(h.getName()).append(":").append(h.getValue()).toString());
                    }
                }
                for (Header h : responseHeaders) {
                    if ("Location".equals(h.getName())) {
                        redirectUrl = h.getValue();
                    } else if ("location".equals(h.getName())) {
                        redirectUrl = h.getValue();
                    } else {
                        isRedirect = false;
                        break;
                    }
                }
            }
            if (response != null) {
                Header[] responseHeaders = response.getAllHeaders();
                this.headers.clear();
                for (Header h : responseHeaders) {
                    if (this.headers.containsKey(h.getName())) {
                        ArrayList list = (ArrayList)this.reduplicateHeaders.get(h.getName());
                        if (list == null) {
                            list = new ArrayList();
                            String v = (String)this.headers.get(h.getName());
                            list.add(v);
                        }
                        list.add(h.getValue());
                        this.reduplicateHeaders.put(h.getName(), list);

                        this.headers.put(h.getName(), null);
                    } else {
                        this.headers.put(h.getName(), h.getValue());
                    }
                }
            }
            parseResponseStatus(response);
            HttpEntity entity = response.getEntity();

            if (logger.isDebugEnabled()) {
                debugPostData(entity);
            }
            return entity;
        } catch (ClientProtocolException e) {
            logger.error("refetch failure.", e);
        } catch (IOException e) {
            logger.error("refetch failure.", e);
        }
        return null;
    }

    private void genAddHttpHeader(HttpRequestBase http)
    {
        Set entry = this.addHttpHeaders.entrySet();
        Iterator iter = entry.iterator();
        while (iter.hasNext()) {
            Map.Entry entryValue = (Map.Entry)iter.next();
            String name = (String)entryValue.getKey();
            String value = (String)entryValue.getValue();
            if (value != null) {
                http.setHeader(name, value);
            }

        }

        Iterator iter2 = this.reduplicateAddHttpHeaders.entrySet().iterator();
        String n;
        while (iter.hasNext()) {
            Map.Entry ent = (Map.Entry)iter2.next();
            n = (String)ent.getKey();
            ArrayList<String> v = (ArrayList)ent.getValue();
            for (String s : v)
                http.setHeader(n, s);
        }
    }

    private String checkProtocol(String url)
    {
        try
        {
            if (url == null) {
                throw new RuntimeException("url is null");
            }
            String urlLowerCase = url.toLowerCase();
            if ((urlLowerCase.startsWith("http://")) || (urlLowerCase.startsWith("https://"))) {
                return url;
            }
            return new StringBuilder().append("http://").append(url).toString();
        }
        catch (Exception e)
        {
        }
        return url;
    }

    public String fetch(String url)
    {
        try
        {
            url = checkProtocol(url);
            this.http = choiceHttpMethod(this.method, url);
            genHttpHeader(this.http, url);
            genAddHttpHeader(this.http);
            if (this.isPost) {
                genPostRequest(this.http);
            }

            if (logger.isDebugEnabled()) {
                debugRequestHeader(this.http);
            }

            HttpResponse response = this.httpclient.execute(this.http);
            parseResponseStatus(response);
            Header[] responseHeaders = response.getAllHeaders();
            for (Header h : responseHeaders) {
                if (this.headers.containsKey(h.getName())) {
                    ArrayList list = (ArrayList)this.reduplicateHeaders.get(h.getName());
                    if (list == null) {
                        list = new ArrayList();
                        String v = (String)this.headers.get(h.getName());
                        list.add(v);
                    }
                    list.add(h.getValue());
                    this.reduplicateHeaders.put(h.getName(), list);

                    this.headers.put(h.getName(), null);
                } else {
                    this.headers.put(h.getName(), h.getValue());
                }
                if (logger.isDebugEnabled()) {
                    logger.debug(new StringBuilder().append(h.getName()).append(":").append(h.getValue()).toString());
                }
            }
            HttpEntity entity = null;

            if (this.isAllowRedirect) {
                String redirect1 = (String)this.headers.get("Location");
                String redirect2 = (String)this.headers.get("location");
                if ((null != redirect1) && (!"".equals(redirect1)))
                    entity = reFetch(redirect1);
                else if ((null != redirect2) && (!"".equals(redirect2)))
                    entity = reFetch((String)this.headers.get("location"));
                else
                    entity = response.getEntity();
            }
            else {
                entity = response.getEntity();
            }
            this.body = EntityCharsetUtils.toString(entity, null);
            this.body =this.body.replaceAll("\n", "").replaceAll("\r","");
            return this.body;
        } catch (ParseException e) {
            logger.error("fetch failure.", e);
        } catch (IOException e) {
            logger.error("fetch failure.", e);
        }
        return "";
    }

    private void genHttpHeader(HttpRequestBase http, String url)
    {
        http.setHeader("FetchUrl", url);
        http.setHeader("AccessKey", this.accessKey);
        String timeStamp = Long.toString(System.currentTimeMillis() / 1000L);
        http.setHeader("TimeStamp", timeStamp);
        String content = new StringBuilder().append("FetchUrl").append(url).append("TimeStamp").append(timeStamp).append("AccessKey").append(this.accessKey).toString();
        String signature = FetchurlUtil.calcSignature(content, this.secretKey);
        http.setHeader("Signature", signature);
        http.setHeader("User-Agent", "SAE Online Platform");
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public void setHttpAuth(String username, String password)
    {
        String content = new StringBuilder().append(username).append(":").append(password).toString();
        String encodeContent = FetchurlUtil.encodeBase64(content);
        this.addHttpHeaders.put("Authorization", new StringBuilder().append("Basic ").append(encodeContent).toString());
    }

    public void setCookie(String name, String value) {
        String cookieValue = new StringBuilder().append(name).append("=").append(value).append(";").toString();
        if (!this.addHttpHeaders.containsKey(name)) {
            this.addHttpHeaders.put("Cookie", cookieValue);
        } else {
            ArrayList values = (ArrayList)this.reduplicateAddHttpHeaders.get(name);
            if (values == null) {
                values = new ArrayList();
                String v = (String)this.addHttpHeaders.get("Cookie");
                values.add(v);
            }
            values.add(value);
            this.reduplicateAddHttpHeaders.put(name, values);
            this.addHttpHeaders.put(name, null);
        }
    }

    public void setCookies(Map<String, String> maps)
    {
        Set entry = maps.entrySet();
        Iterator iter = entry.iterator();
        StringBuilder cookieResult = new StringBuilder();
        while (iter.hasNext()) {
            Map.Entry value = (Map.Entry)iter.next();
            String cookieName = (String)value.getKey();
            String cookieValue = (String)value.getValue();
            cookieResult.append(cookieName).append(":").append(cookieValue).append(";");
        }

        if (cookieResult.length() > 0) {
            cookieResult.deleteCharAt(cookieResult.length() - 1);
        }
        setCookie("Cookie", cookieResult.toString());
    }

    public void setHeader(String name, String value)
    {
        String trimName = name.trim();
        if (!checkIsForbid(trimName))
            if (!this.addHttpHeaders.containsKey(name)) {
                this.addHttpHeaders.put(name, value);
            } else {
                ArrayList values = (ArrayList)this.reduplicateAddHttpHeaders.get(name);
                if (values == null) {
                    values = new ArrayList();
                    String v = (String)this.addHttpHeaders.get(name);
                    values.add(v);
                }
                values.add(value);
                this.reduplicateAddHttpHeaders.put(name, values);
                this.addHttpHeaders.put(name, null);
            }
    }

    private boolean checkIsForbid(String header)
    {
        String lowerCase = header.toLowerCase();
        boolean isForbid = this.forbidHead.contains(lowerCase);
        return isForbid;
    }

    public void setReadTimeout(int timeout)
    {
        this.addHttpHeaders.put("ReadTimeout", Integer.toString(timeout));
    }

    public void setSendTimeout(int timeout)
    {
        this.addHttpHeaders.put("HEAD_SENDTIMEOUT", Integer.toString(timeout));
    }

    public void setAllowTrunc(boolean allow)
    {
        this.addHttpHeaders.put("AllowTruncated", Boolean.toString(allow));
        this.isTrunacated = allow;
    }

    public void setAllowRedirect(boolean allow)
    {
        this.addHttpHeaders.put("redirect", Boolean.toString(allow));
        this.isAllowRedirect = allow;
    }

    public Map<String, String> responseHeaders()
    {
        HashMap map = new HashMap();
        Iterator iter = this.headers.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            if (entry.getValue() != null) {
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public Map<String, ArrayList<String>> responseReduplicateHeaders()
    {
        return new HashMap(this.reduplicateHeaders);
    }

    @Deprecated
    public String responseCookies()
    {
        String value = (String)this.headers.get("Set-Cookie");
        if (value == null) {
            ArrayList<String> cookies = (ArrayList)this.reduplicateHeaders.get("Set-Cookie");
            StringBuilder sb = new StringBuilder();
            for (String s : cookies) {
                sb.append(s).append('\t');
            }
            sb = sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
        return value;
    }

    public String[] responseCookieArray()
    {
        ArrayList cookies = (ArrayList)this.reduplicateHeaders.get("Set-Cookie");
        String[] arr = new String[cookies.size()];
        for (int i = 0; i < cookies.size(); i++) {
            arr[i] = ((String)cookies.get(i));
        }
        return arr;
    }

    public int getHttpCode()
    {
        return this.httpStatusCode;
    }

    public int getErrno()
    {
        return this.saeStatusCode;
    }

    public String getErrmsg()
    {
        return this.statusMsg;
    }

    public void clean()
    {
        this.headers.clear();
        this.reduplicateHeaders.clear();
        this.addHttpHeaders.clear();
        this.reduplicateAddHttpHeaders.clear();
        this.isPost = false;
        this.postMap.clear();
        this.saeStatusCode = 0;
        this.httpStatusCode = 200;
    }

    public String body()
    {
        return this.body;
    }

    public void setPostData(Map<String, String> maps)
    {
        setPostData(maps, this.characterEncode);
    }

    public void setPostData(Map<String, String> maps, String characterEncode)
    {
        setMethod("POST");
        this.postMap = maps;
        this.isPost = true;
        this.characterEncode = characterEncode;
        if (logger.isDebugEnabled())
            logger.debug(new StringBuilder().append("post data:").append(maps).toString());
    }

    public void setPostData(BinaryData data)
    {
        this.binaryList.add(data);
        setMethod("POST");
        this.isPost = true;
        if (logger.isDebugEnabled())
            logger.debug(new StringBuilder().append("post data:").append(data).toString());
    }
}