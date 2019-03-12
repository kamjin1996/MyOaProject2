package com.qfedu.myoaproject2.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 为LayUI的页面展示(list)准备的分页查询vo
 *
 * @param <T> 子菜单下list的泛型 如resource user role等
 */
@Data
@Accessors(chain = true)
public class QueryVo<T> {
    private int code;
    private int count;
    private String msg;
    private List<T> data;

    public QueryVo() {

    }

    public QueryVo(int code) {
        this.code = code;
    }

    private QueryVo(int code, int count, String msg, List<T> data) {
        this.code = code;
        this.count = count;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 组成一个页面
     *
     * @param count 页面中所有的条目数
     * @param data  具体页面list数据
     * @param <T>   list数据类型
     * @return 此page对象将在controller作为json格式返回 对应着LayUI展示list类型页面的数据接口
     */
    public static <T> QueryVo<T> createPage(int count, List<T> data) {
        return new QueryVo<T>(0, count, "ok", data);
    }
}


