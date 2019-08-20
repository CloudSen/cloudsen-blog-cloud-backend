package com.collapseunion.commonutils;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

/**
 * <p>
 * JSON工具类
 * </p>
 * <p>
 * 提供json=>object，object=>json，json=>map
 * </p>
 *
 * @author CloudSen
 */
public class JsonUtil {

    /**
     * <p>
     * object => json
     * </p>
     *
     * @param obj 目标对象
     * @return json string
     */
    public static String obj2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    /**
     * <p>
     * json => object
     * </p>
     *
     * @param jsonString json string
     * @param tClass     目标类型
     * @param <T>        目标类型
     * @return 目标对象
     */
    public static <T> T json2Obj(String jsonString, Class<T> tClass) {
        return JSON.parseObject(jsonString, tClass);
    }

    /**
     * <p>
     * json => map
     * </p>
     *
     * @param jsonString json string
     * @return MAP
     */
    public static Map json2Map(String jsonString) {
        return JSON.parseObject(jsonString, Map.class);
    }

    /**
     * <p>
     * map => obj
     * </p>
     *
     * @param map    Map
     * @param tClass 目标类型
     * @param <T>    目标类型
     * @return 目标对象
     */
    public static <T> T map2Obj(Map<?, ?> map, Class<T> tClass) {
        return JSON.parseObject(JSON.toJSONString(map), tClass);
    }

    /**
     * <p>
     * map => mybatis page
     * </p>
     *
     * @param map    Map
     * @param tClass page
     * @param <T>    目标类型
     * @return page
     */
    public static <T> Page<T> map2MybatisPage(Map<?, ?> map, Class<T> tClass) {
        Page tempPage = JSON.parseObject(JSON.toJSONString(map), Page.class);
        return new Page<T>()
                .setCurrent(tempPage.getCurrent())
                .setOptimizeCountSql(tempPage.optimizeCountSql())
                .setSearchCount(tempPage.isSearchCount())
                .setSize(tempPage.getSize())
                .setTotal(tempPage.getTotal());
    }
}
