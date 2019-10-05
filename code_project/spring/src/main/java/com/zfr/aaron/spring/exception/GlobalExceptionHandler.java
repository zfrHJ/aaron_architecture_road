package com.zfr.aaron.spring.exception;

import com.zfr.aaron.spring.entity.RspDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 全局异常捕获
 * @author zfr
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static int DUPLICATE_KEY_CODE = 1001;
    private static int PARAM_FAIL_CODE = 1002;
    private static int VALIDATION_CODE = 1003;

  /*  *//**
     * 处理自定义异常
     *//*
    @ExceptionHandler(BizException.class)
    public RspDTO handleRRException(BizException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(e.getCode(), e.getMessage());
    }*/

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RspDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(PARAM_FAIL_CODE, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public RspDTO handleValidationException(ValidationException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(VALIDATION_CODE, e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public RspDTO handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(PARAM_FAIL_CODE, e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public RspDTO handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public RspDTO handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(DUPLICATE_KEY_CODE, "数据重复，请检查后提交");
    }


    @ExceptionHandler(Exception.class)
    public RspDTO handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return new RspDTO(500, "系统繁忙,请稍后再试");
    }

}
