package com.zfr.aaron.spring.project.config;

import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixConfig {
    /**
     *  下载Jar包地址
     *  https://bintray.com/kennedyoliveira/maven/standalone-hystrix-dashboard/1.5.6
     *  运行
     *  java -jar standalone-hystrix-dashboard-1.5.6-all.jar &
     *  输入地址
     *   http://localhost:8080/hystrix.stream
     *  查看
     *
     Closed：熔断器关闭状态，调用失败次数积累，到了阈值（或一定比例）则启动熔断机制；
     Open：熔断器打开状态，此时对下游的调用都内部直接返回错误，不走网络，但设计了一个时钟选项，默认的时钟达到了一定时间（这个时间一般设置成平均故障处理时间，也就是MTTR），到了这个时间，进入半熔断状态；
     Half-Open：半熔断状态，允许定量的服务请求，如果调用都成功（或一定比例）则认为恢复了，关闭熔断器，否则认为还没好，又回到熔断器打开状态；
     */
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet() {
        return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
    }

    /**
     * AspectJ aspect to process methods which annotated with {@link HystrixCommand} annotation.
     *
     * {@link HystrixCommand} annotation used to specify some methods which should be processes as hystrix commands.
     */
    @Bean
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }


}
