package com.example.library.base;

/**
 * Created by hugy on 2018/3/13.
 */

public class BaseBean<T> {
    private String code;
    private String message;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
