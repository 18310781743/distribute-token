package com.zcc.distributetoken.handler;

import com.zcc.distributetoken.exception.NotLoginException;
import com.zcc.distributetoken.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static com.zcc.distributetoken.result.Result.CODE_SYS_ERROR;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(value = NotLoginException.class)
    @ResponseBody
    public Result<?> notLoginExceptionHandler(HttpServletRequest req, NotLoginException e){
        log.error("用户未登录");
        return Result.error(CODE_SYS_ERROR, e.getMessage());
    }
}
