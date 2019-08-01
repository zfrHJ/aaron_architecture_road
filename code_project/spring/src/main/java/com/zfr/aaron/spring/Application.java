package com.zfr.aaron.spring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zfr
 * 启动类
 */
@SpringBootApplication
@MapperScan({"com.zfr.aaron.spring.mapper"})

public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
