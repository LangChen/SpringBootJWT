package com.chlang.common.handler;

import com.chlang.common.exception.PlatfromException;
import com.chlang.common.resp.ErrorCode;
import com.chlang.common.resp.HttpCommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 出错处理器
 */
@ControllerAdvice
public class ErrorHandler {

    private Logger logger = LoggerFactory.getLogger(ErrorCode.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpCommonResult exceptionHandler(Exception e) {
        logger.error("未知异常，e:"+e.getMessage());
        e.printStackTrace();
        HttpCommonResult httpCommonResult = new HttpCommonResult();
        httpCommonResult.setCode(ErrorCode.UN_KNOW_ERROR);
        httpCommonResult.setMsg(e.getMessage());

        return httpCommonResult;
    }

    @ExceptionHandler(PlatfromException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public HttpCommonResult exceptionHandler(PlatfromException e) {
        logger.error("平台自定义异常，errorCode:"+e.getErrorCode()+" errorMsg:"+e.getErrorMsg());

        HttpCommonResult httpCommonResult = new HttpCommonResult();
        httpCommonResult.setCode(e.getErrorCode());
        httpCommonResult.setMsg(e.getMessage());
        return httpCommonResult;
    }

}
