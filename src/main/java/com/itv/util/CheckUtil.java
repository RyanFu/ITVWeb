package com.itv.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: xiajun
 * Date: 13-7-13
 * Time: 下午12:37
 * 检测参数合法性
 */
public class CheckUtil {
    public static boolean check(String arg){
        if(arg==null||arg.trim().equals("")){
            return false;
        }
        String regex="[-,;@'*#!`&()/\\\"+^]";
        Pattern p=Pattern.compile(regex);
        Matcher matcher = p.matcher(arg);
        return matcher.find();
    }
    public static String replaceAll(String arg){
        if(arg==null||arg.trim().equals("")){
            return null;
        }
        String regex="[-,;@'*#!`&()/\\\"+^\\s]";
        arg=arg.replaceAll(regex,"");
        return arg;
    }
}
