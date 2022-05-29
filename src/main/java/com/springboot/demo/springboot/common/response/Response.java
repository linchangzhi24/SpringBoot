package com.springboot.demo.springboot.common.response;

public class Response {

    public static <T> ResponseInfo<T> success(T t) {
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setStatus(Status.SUCCESS.getStatus());
        responseInfo.setMessage(null);
        responseInfo.setData(t);
        return responseInfo;
    }

    public static <T> ResponseInfo<T> success(T t, String message) {
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setStatus(Status.SUCCESS.getStatus());
        responseInfo.setMessage(message);
        responseInfo.setData(t);
        return responseInfo;
    }

    public static <T> ResponseInfo<T> failure(int status) {
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setStatus(status);
        responseInfo.setMessage(null);
        responseInfo.setData(null);
        return responseInfo;
    }

    public static <T> ResponseInfo<T> failure(int status, String message) {
        ResponseInfo<T> responseInfo = new ResponseInfo<>();
        responseInfo.setStatus(status);
        responseInfo.setMessage(message);
        responseInfo.setData(null);
        return responseInfo;
    }

}
