package com.zfr.aaron.spring.project.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @desc 主要解决
 * @JsonProperty("d_ding_dan_hao")private String orderNo;
 *  主动使用 对象转json时 实体属性不能转成相应的JsonProperty
 *
 */
@Slf4j
public class MyJackSonUtils {

    private static ObjectMapper mapper;

    static {
        mapper = generateMapper(JsonInclude.Include.ALWAYS);
    }



    private MyJackSonUtils() {

    }


    /**
     * 将json通过类型转换成对象
     *
     * <pre>
     *     {@link MyJackSonUtils  MyJackSonUtils}.fromJson("{\"username\":\"username\", \"password\":\"password\"}", User.class);
     * </pre>
     *
     * @param json json字符串
     * @param clazz 泛型类型
     * @return 返回对象
     * @throws IOException
     */
    public static <T> T fromJson(String json, Class<T> clazz)  {
        try {
            return clazz.equals(String.class) ? (T) json : mapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("MyJackSonUtils方法出错"+e.toString(), e);
        }
        return null;
    }

    /**
     * 将json通过类型转换成对象
     *
     * <pre>
     *     {@link  MyJackSonUtils  MyJackSonUtils}.fromJson("[{\"username\":\"username\", \"password\":\"password\"}, {\"username\":\"username\", \"password\":\"password\"}]", new TypeReference&lt;List&lt;User&gt;&gt;);
     * </pre>
     *
     * @param json json字符串
     * @param typeReference 引用类型
     * @return 返回对象
     * @throws IOException
     */
    public static <T> T fromJson(String json, TypeReference<?> typeReference) {
        try {
            return (T) (typeReference.getType().equals(String.class) ? json : mapper.readValue(json, typeReference));
        } catch (IOException e) {
            log.error("MyJackSonUtils方法出错"+e.toString(), e);
        }
        return null;
    }

    /**
     * 将对象转换成json
     *
     * <pre>
     *     {@link  MyJackSonUtils  MyJackSonUtils}.toJson(user);
     * </pre>
     *
     * @param src 对象
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src) {
        try {
            return src instanceof String ? (String) src : mapper.writeValueAsString(src);
        } catch (JsonProcessingException e) {
            log.error("MyJackSonUtils方法出错"+e.toString(), e);
        }
        return null;
    }

    /**
     * 将对象转换成json, 可以设置输出属性
     *
     * <pre>
     *     {@link  MyJackSonUtils  MyJackSonUtils}.toJson(user, {@link JsonInclude Inclusion.ALWAYS});
     * </pre>
     *
     * {@link JsonInclude Inclusion 对象枚举}
     * <ul>
     *     <li>{@link JsonInclude Inclusion.ALWAYS 全部列入}</li>
     *     <li>{@link JsonInclude Inclusion.NON_DEFAULT 字段和对象默认值相同的时候不会列入}</li>
     *     <li>{@link JsonInclude Inclusion.NON_EMPTY 字段为NULL或者""的时候不会列入}</li>
     *     <li>{@link JsonInclude Inclusion.NON_NULL 字段为NULL时候不会列入}</li>
     * </ul>
     *
     * @param src 对象
     * @param inclusion 传入一个枚举值, 设置输出属性
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src, JsonInclude.Include inclusion){
        if (src instanceof String) {
            return (String) src;
        } else {
            ObjectMapper customMapper = generateMapper(inclusion);
            try {
                return customMapper.writeValueAsString(src);
            } catch (JsonProcessingException e) {
                log.error("MyJackSonUtils方法出错"+e.toString(), e);
            }
        }
        return null;
    }

    /**
     * 将对象转换成json, 传入配置对象
     *
     * <pre>
     *     {@link ObjectMapper ObjectMapper} mapper = new ObjectMapper();
     *     mapper.setSerializationInclusion({@link JsonInclude Inclusion.ALWAYS});
     *     mapper.configure({@link Feature Feature.FAIL_ON_UNKNOWN_PROPERTIES}, false);
     *     mapper.configure({@link Feature Feature.FAIL_ON_NUMBERS_FOR_ENUMS}, true);
     *     mapper.setDateFormat(new {@link SimpleDateFormat SimpleDateFormat}("yyyy-MM-dd HH:mm:ss"));
     *     {@link  MyJackSonUtils  MyJackSonUtils}.toJson(user, mapper);
     * </pre>
     *
     * {@link ObjectMapper ObjectMapper}
     *
     * @see ObjectMapper
     *
     * @param src 对象
     * @param mapper 配置对象
     * @return 返回json字符串
     * @throws IOException
     */
    public static <T> String toJson(T src, ObjectMapper mapper) {
        if (null != mapper) {
            if (src instanceof String) {
                return (String) src;
            } else {
                try {
                    return mapper.writeValueAsString(src);
                } catch (JsonProcessingException e) {
                    log.error("MyJackSonUtils方法出错"+e.toString(), e);
                }
            }
        } else {
            return null;
        }
        return null;
    }



    /**
     * json string convert to map
     */
    public static <Object> Map<String, Object> json2map(String jsonStr)
             {
                 try {
                     return mapper.readValue(jsonStr, Map.class);
                 } catch (IOException e) {
                     log.error("MyJackSonUtils方法出错"+e.toString(), e);
                 }
                 return null;
             }

    /**
     * json string convert to map with javaBean
     */
    public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz)
           {
               Map<String, Map<String, Object>> map = null;
               try {
                   map = mapper.readValue(jsonStr,
                           new TypeReference<Map<String, T>>() {
                           });
               } catch (IOException e) {
                   log.error("MyJackSonUtils方法出错"+e.toString(), e);
               }
               Map<String, T> result = new HashMap<>(map.entrySet().size());
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * json array string convert to list with javaBean
     */
    public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz)
             {
                 List<Map<String, Object>> list = null;
                 try {
                     list = mapper.readValue(jsonArrayStr,
                             new TypeReference<List<T>>() {
                             });
                 } catch (IOException e) {
                     log.error("MyJackSonUtils方法出错"+e.toString(), e);
                 }
                 List<T> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            result.add(map2pojo(map, clazz));
        }
        return result;
    }

    /**
     * map convert to javaBean
     */
    public static <T> T map2pojo(Map map, Class<T> clazz) {
        return mapper.convertValue(map, clazz);
    }


    /**
     * 返回{@link ObjectMapper ObjectMapper}对象, 用于定制性的操作
     *
     * @return {@link ObjectMapper ObjectMapper}对象
     */
    public static ObjectMapper mapper() {
        return mapper;
    }

    /**
     * 通过Inclusion创建ObjectMapper对象
     *
     * {@link JsonInclude Inclusion 对象枚举}
     * <ul>
     *     <li>{@link JsonInclude Inclusion.ALWAYS 全部列入}</li>
     *     <li>{@link JsonInclude Inclusion.NON_DEFAULT 字段和对象默认值相同的时候不会列入}</li>
     *     <li>{@link JsonInclude Inclusion.NON_EMPTY 字段为NULL或者""的时候不会列入}</li>
     *     <li>{@link JsonInclude Inclusion.NON_NULL 字段为NULL时候不会列入}</li>
     * </ul>
     *
     * @param inclusion 传入一个枚举值, 设置输出属性
     * @return 返回ObjectMapper对象
     */
    private static ObjectMapper generateMapper(JsonInclude.Include inclusion) {

        ObjectMapper customMapper = new ObjectMapper();

        // 设置输出时包含属性的风格
        customMapper.setSerializationInclusion(inclusion);

        // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性//设置实体无属性和json串属性对应时不会出错
        customMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        customMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //customMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        // 禁止使用int代表Enum的order()來反序列化Enum,非常危險
        customMapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        //设置可用单引号
        customMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        //设置字段可以不用双引号包括
        customMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 所有日期格式都统一为以下样式
        customMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        return customMapper;
    }

    /**
     * 传入json字符串和key，获得key的value
     * @param jsonString
     * @param key
     * @return
     */
    public static String getJsonStringByKey(String jsonString, String key) {
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        return jsonObject.getString(key);
    }


    public static <T> T getTokenResult(String resultString, Class<T> clazz) {
        String result = MyJackSonUtils.getJsonStringByKey(resultString, "result");
        T t = MyJackSonUtils.fromJson(result, clazz);
        return t;
    }

    public static <T> List<T> getTokenListResult(String resultString, Class<T> clazz) {
        String result = MyJackSonUtils.getJsonStringByKey(resultString, "result");
        List<T> t = MyJackSonUtils.json2list(result,clazz);
        return t;
    }
}