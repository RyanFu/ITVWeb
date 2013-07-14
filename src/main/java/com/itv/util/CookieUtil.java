package com.itv.util;

import org.apache.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * User: xiajun
 * Date: 13-7-14
 * Time: 下午4:21
 * 获取cookie中的用户数据
 */
public class CookieUtil {
    private final static Logger log=Logger.getLogger(CookieUtil.class);
    /**
     * 获取指定cookie中的值
     *
     * @return
     */
    public static String getCookieValue(HttpServletRequest request) {
        String value = null;
        if (request != null) {
            Cookie[] cookie = request.getCookies();
            if (cookie != null) {
                for (int i = 0; i < cookie.length; i++) {
                    Cookie c = cookie[i];
                    if ("user".equals(c.getName())) {
                        value = c.getValue();
                        break;
                    }
                }
            }
        }
        return value;
    }

    /**
     * 获取解码后的字符串
     * @param v
     * @return
     */
    public static String getDecodeValue(String v){
        if(v==null){
            return null;
        }
        String value=null;
        try{
            byte[] hex=Crypt.parseHexStr2Byte(v);
            value=new String(Crypt.decrypt(hex, "1234567887654321"));
        }catch (Exception e) {
            log.error("CookieUtil getValue: ",e);
        }
        return value;
    }
}
