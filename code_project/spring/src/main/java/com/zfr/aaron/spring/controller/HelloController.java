package com.zfr.aaron.spring.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zfr.aaron.spring.annountaion.log.annountain.Log;
import com.zfr.aaron.spring.project.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 繁荣Aaron
 */
@Controller
public class HelloController {
    private static final Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    //对应的自定义注解，当方法上写这个注解时，就会进入切面类中
    @Log(title="哈喽模块",action="say哈喽")
    public @ResponseBody   String sayHello() {
        log.info("HelloController sayHello:{}","hello world!");
        return "hello";
    }

    /**
     *熔断机制
     @HystrixCommand(
             fallbackMethod = "getMsgFallback",
             threadPoolProperties = {  //10个核心线程池,超过20个的队列外的请求被拒绝; 当一切都是正常的时候，线程池一般仅会有1到2个线程激活来提供服务
                     @HystrixProperty(name = "coreSize", value = "10"),
                     @HystrixProperty(name = "maxQueueSize", value = "100"),
                     @HystrixProperty(name = "queueSizeRejectionThreshold", value = "20")},
             commandProperties = {
                     @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"), //命令执行超时时间
                     @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2"), //若干10s一个窗口内失败三次, 则达到触发熔断的最少请求量
                     @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "30000") //断路30s后尝试执行, 默认为5s
       })
     * @return
     */
    @GetMapping("/msg/get")
    @HystrixCommand(fallbackMethod = "getMsgFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2")})
    @ResponseBody
    public String getMsg() {

        //模拟远程调用，调用的是redis项目的地址
        byte[] bytes = HttpUtil.doGet("http://localhost:8000/test");

        System.out.println(new String(bytes));
        return "成功";
    }

    public String getMsgFallback() {
        return "已经熔断成功了！";
    }


}
