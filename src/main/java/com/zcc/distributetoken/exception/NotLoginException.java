package com.zcc.distributetoken.exception;

/**
 * @Description
 * @Author zcc.zhu
 * @CreateTime 2020-12-16 14:57
 */
public class NotLoginException extends RuntimeException{
    public NotLoginException(String message) {
        super(message);
    }
}