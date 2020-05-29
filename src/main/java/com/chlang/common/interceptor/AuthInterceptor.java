package com.chlang.common.interceptor;

import com.chlang.common.exception.PlatfromException;
import com.chlang.common.helper.JwtHelper;
import com.chlang.common.resp.ErrorCode;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * 认证拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {
    Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("--------进入拦截器--------");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headName = headerNames.nextElement();
            log.info(headName + ":" + request.getHeader(headName));
        }

        String token = request.getHeader("Authorization");
        //判断是否传入令牌
        if(token == null){
            throw new PlatfromException(ErrorCode.TOKEN_FAILED_ERROR,"无效的令牌");
        }
        BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
        JwtHelper jwtHelper = (JwtHelper) factory.getBean("jwtHelper");
        //判断令牌是否合法
        Claims claims = jwtHelper.verifyToken(token);

        //从redis中获取用户信息

        //将用户信息存储到request的attr中

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
