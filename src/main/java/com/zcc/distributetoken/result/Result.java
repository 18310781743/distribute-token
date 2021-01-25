
package com.zcc.distributetoken.result;


import java.io.Serializable;

public class Result<T>  implements Serializable {
    public static final int CODE_SYS_ERROR = 500;
    public static final int CODE_SUCCESS = 200;
    private static final Result SYS_ERROR = error(500, "系统异常");
    private Integer code = 200;
    private String message = "";
    private T data;

    private Result() {
    }

    public String getMessage() {
        return this.message;
    }

    public Integer getCode() {
        return this.code;
    }

    public T getData() {
        return this.data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return Integer.valueOf(200).equals(this.code);
    }

    public static Result sysError() {
        return SYS_ERROR;
    }


    public static <T> Result<T> error(Integer code, String message) {
        Result<T> rb = new Result();
        rb.code = code;
        rb.message = message;
        return rb;
    }

    public static <T> Result<T> success(T data) {
        Result<T> rb = new Result();
        rb.code = CODE_SUCCESS;
        rb.data = data;
        return rb;
    }
}
