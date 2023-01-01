package com.kdao.light.advice;


import cn.dev33.satoken.exception.SaTokenException;
import com.kdao.light.common.exception.BaseKnownException;
import com.kdao.light.common.utils.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常统一处理
 *
 * @author Echosong
 * @version V1.0
 * @date 2019年9月11日
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandleAdvice {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultVO handleException(Exception e, HttpServletRequest request, HttpServletResponse response) {
        log.error("异常{}", e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            //验证码参数
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            FieldError fieldError = ex.getBindingResult().getFieldError();
            return ResultVO.error(2000, fieldError.getDefaultMessage());
        }
        if (e instanceof SaTokenException) {
            //登录或者无权限
            return ResultVO.error(3000, e.getMessage());
        }
        //自定义抛异常
        if(e instanceof BaseKnownException){
            return  ResultVO.error(((BaseKnownException) e).getCode(), e.getMessage());
        }

        return ResultVO.error(e.getMessage());
    }
}
