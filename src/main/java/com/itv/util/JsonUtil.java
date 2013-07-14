package com.itv.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itv.pojo.Supplie;
import javassist.bytecode.LineNumberAttribute;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 处理json数据工具类
 * User: xiajun
 * Date: 13-6-30
 * Time: 上午9:32
 */
public class JsonUtil<T> {
    private final static ObjectMapper mapper=new ObjectMapper();
    static{
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * 将json串转换成java对象
     * @param json json 串
     * @return
     * @throws IOException
     */
    public static List<LinkedHashMap<String, String>> parseSupplie(String json) throws IOException {
        if(json!=null&&!json.equals("")){
            if(json.indexOf("[")==0){
                return mapper.readValue(json,ArrayList.class);
            }else{
                return parseObj(json);
            }
        }else{
            return null;
        }
    }
    private static List<LinkedHashMap<String, String>> parseObj(String json) throws IOException {
        Supplie su = mapper.readValue(json, Supplie.class);
        List<LinkedHashMap<String, String>> list=null;
        if(su!=null){
            list=new ArrayList<LinkedHashMap<String, String>>();
            LinkedHashMap<String,String> map=new LinkedHashMap<String, String>(4);
            map.put("name",su.getName());
            map.put("link",su.getLink());
            list.add(map);
        }
        return list;
    }
    public static String parseStr(List list) throws IOException {
        StringWriter sw=new StringWriter();
        mapper.writeValue(sw,list);
        return sw.toString();
    }
    /**
     * 获取ObjectMapper实例
     * @return
     */
    public static ObjectMapper getInstance(){
        return mapper;
    }
}
