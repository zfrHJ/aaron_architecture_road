package com.zfr.aaron.spring.annountaion.log.aop;

import cn.hutool.json.JSONUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 繁荣Aaron
 */
@Aspect
@Component
public class AccessLogAop {

    private final Logger log = LoggerFactory.getLogger(AccessLogAop.class);

    /**
     * 自动扫描整个包
     */
    @Pointcut("execution(public * com.zfr.aaron.spring.controller.*.*(..))")
    public void accessLog() {
        // none
    }

    @Before("accessLog()")
    public void doBefore(JoinPoint joinPoint)  {

        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            log.info("url={}", request.getRequestURL());

            log.info("method={}", request.getMethod());

            log.info("ip={}", request.getRemoteAddr());

            log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

            Object[] paranValues = joinPoint.getArgs();
            String param = JSONUtil.toJsonStr(paranValues);
            log.info("param  ={}", param);

        } catch (Exception e) {
            log.error("doBefore exception={}", e);
        }

    }

}
