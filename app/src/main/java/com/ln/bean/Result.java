package com.ln.bean;

import com.google.gson.annotations.SerializedName;

/**
 * description:
 * Created by liNan on 2017/5/18 11:04
 */

public class Result<T> {
    @SerializedName("error_code")
    public int code;
    public String message;
    public T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
