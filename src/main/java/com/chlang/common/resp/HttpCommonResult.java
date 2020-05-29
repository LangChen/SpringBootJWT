package com.chlang.common.resp;

/**
 * Http请求接口的结果
 */
public class HttpCommonResult {

    /**
     * 返回编码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String msg;
    /**
     * 返回的数据
     */
    private Object object;

    public HttpCommonResult(){}

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
