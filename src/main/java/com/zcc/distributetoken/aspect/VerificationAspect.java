package com.zcc.distributetoken.aspect;


import com.zcc.distributetoken.constants.Constant;
import com.zcc.distributetoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 接口是否需要登录切面验证
 */
@Component
@Aspect
@Slf4j
public class VerificationAspect {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Pointcut("@annotation(com.zcc.distributetoken.annotation.NeedLogin)")
    public void verification() {}

    @Around("verification()")
    public Object verification(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) requestAttributes
                .resolveReference(RequestAttributes.REFERENCE_REQUEST);
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0){
            throw new NotLoginException("用户未登录");
        }

        for (Cookie cookie : cookies) {
            if (Constant.TOKEN.equals(cookie.getName())){
                if (null != redisTemplate.opsForValue().get(cookie.getValue())){
                    return proceedingJoinPoint.proceed();
                }
            }
        }
        throw new NotLoginException("用户未登录");
    }

}
