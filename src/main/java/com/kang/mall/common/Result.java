package com.kang.mall.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yikang
 * ClassName: Result
 * Description: 返回结果封装类
 * Create Date: 2021/1/18 17:18
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private int code;
    private String message;
    private T body;

    public Result(int code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
        this.body = null;
    }

    public static Result ok() {
        return new Result(200, "success");
    }

    public static Result ok(String message) {
        return new Result(200, message);
    }

    public static <T> Result<T> ok(T data) {
        return new Result(200, "success", data);
    }

    public static <T> Result<T> ok(String message, T data) {
        return new Result(200, message, data);
    }

    public static Result error() {
        return new Result(500, "error");
    }

    public static Result error(String message) {
        return new Result(500, message);
    }

    public static <T> Result<T> error(T data) {
        return new Result(500, "error", data);
    }

    public static <T> Result<T> error(String message, T data) {
        return new Result(500, message, data);
    }
}
