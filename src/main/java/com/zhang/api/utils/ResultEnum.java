package com.zhang.api.utils;

public enum ResultEnum {

	 //这里是可以自己定义的，方便与前端交互即可
	SUCCESS(200,"成功"),
    NO_PERMISSION(211,"权限不足"),
    SERVER_ERROR(10000,"服务器异常"),
    AUTH_ERROR(10001,"认证失败"),
    PARAMS_ERROR(10002,"参数错误"),
    JSON_PARSE_ERROR(10003,"Json解析错误"),
    ILLEAGAL_STRING(15001,"非法字符串"),
    UNKNOW_ERROR(16000,"未知错误");
	
    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
