package com.cloudable.blog.config.exception;

import com.cloudable.blog.BlogApplication;
import com.collapseunion.commonutils.customexceptions.BusinessException;
import com.collapseunion.commonutils.customexceptions.ExceptionEnum;
import com.collapseunion.commonutils.globalresult.Result;
import com.collapseunion.commonutils.globalresult.ResultCode;
import com.collapseunion.commonutils.globalresult.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局异常处理类
 *
 * @author CloudSen
 */
@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局统一处理自定义RuntimeException异常
     *
     * @param e BusinessException及其子类
     * @return 全局统一返回对象
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<BusinessException> businessExceptionHandler(BusinessException e) {
        log.error("[ ERROR ] Exception Occurred: {}", ExceptionUtils.getFullStackTrace(e));
        ExceptionEnum exceptionEnum = ExceptionEnum.parseExceptionClass(e.getClass());
        ResultCode resultCode = exceptionEnum.getResultCode();
        return ResultUtil.error(resultCode.getMessage(), resultCode.getCode(), BlogApplication.APPLICATION_NAME);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        return ResultUtil.error(e.getMessage(), BlogApplication.APPLICATION_NAME);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        return ResultUtil.error(e.getMessage(), BlogApplication.APPLICATION_NAME);
    }

    /**
     * 其他异常
     *
     * @param e 其他异常
     * @return 全局统一返回对象
     */
    @ExceptionHandler(value = Exception.class)
    public Result<Exception> exceptionHandler(Exception e) {
        log.error("[ ERROR ] Exception Occurred: {}", ExceptionUtils.getFullStackTrace(e));
        return ResultUtil.error(e.toString(), BlogApplication.APPLICATION_NAME);
    }
}
