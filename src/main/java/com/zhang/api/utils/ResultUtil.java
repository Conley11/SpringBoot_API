package com.zhang.api.utils;

public class ResultUtil {

    /**成功且带数据**/
    public static ApiResult success(Object object){
    	ApiResult result = new ApiResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static ApiResult success(){

        return success(null);
    }
    /**失败**/
    public static ApiResult error(Integer code,String msg){
    	ApiResult result = new ApiResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
