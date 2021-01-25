package com.zcc.distributetoken.controller;

import cn.hutool.core.util.IdUtil;
import com.zcc.distributetoken.anootation.NeedLogin;
import com.zcc.distributetoken.constants.Constant;
import com.zcc.distributetoken.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@RestController
public class TestController {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("login")
    public Result<?> login(HttpServletRequest request, HttpServletResponse response){
        String token = IdUtil.simpleUUID();
        redisTemplate.opsForValue().set(token, "用户信息", 30 * 60, TimeUnit.SECONDS);
        Cookie c = new Cookie(Constant.TOKEN, token);
        response.addCookie(c);
        return Result.success("OK");
    }

    /**
     * 需要验证权限的接口  加上 NeedLogin 注解即可
     * @return
     */
    @RequestMapping("ppp")
    @NeedLogin
    public Result<?> ppp(){
        redisTemplate.opsForValue().set("name", "dsdsdds");
        return Result.success("OK");
    }

}
