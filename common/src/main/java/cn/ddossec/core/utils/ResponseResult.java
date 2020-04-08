package cn.ddossec.core.utils;

import java.util.HashMap;

public class ResponseResult extends HashMap {
    public static String SUCCESS_CODE = "200";
    public static String ERROR_CODE = "500";
    public static String DATA_KEY = "data";
    public static String MSG_KEY = "msg";

    private ResponseResult() {
    }

    public ResponseResult set(String key, Object object) {
        super.put(key, object);
        return this;
    }

    private static ResponseResult newResponseResult() {
        return new ResponseResult();
    }

    public static ResponseResult success() {
        return ResponseResult.newResponseResult()
                .set("code", ResponseResult.SUCCESS_CODE)
                .set(ResponseResult.MSG_KEY, "操作成功");
    }

    public static ResponseResult success(String msg) {

        return ResponseResult.newResponseResult()
                .set("code", ResponseResult.SUCCESS_CODE)
                .set(ResponseResult.MSG_KEY, msg);
    }

    public static ResponseResult success(String msg, Object object) {

        return ResponseResult.newResponseResult()
                .set("code", ResponseResult.SUCCESS_CODE)
                .set(ResponseResult.MSG_KEY, msg)
                .set(ResponseResult.DATA_KEY, object);
    }

    public ResponseResult data(Object obj) {
        return this.set("data", obj);
    }

    public static ResponseResult error() {
        return ResponseResult.newResponseResult()
                .set(ResponseResult.MSG_KEY, "操作失败")
                .set("code", ResponseResult.ERROR_CODE);
    }

    public static ResponseResult error(String msg) {
        return ResponseResult.newResponseResult()
                .set(ResponseResult.MSG_KEY, msg)
                .set("code", ResponseResult.ERROR_CODE);
    }

    public static ResponseResult error(String msg, Object object) {
        return ResponseResult.newResponseResult()
                .set(ResponseResult.MSG_KEY, msg)
                .set(ResponseResult.DATA_KEY, object)
                .set("code", ResponseResult.ERROR_CODE);
    }

}
