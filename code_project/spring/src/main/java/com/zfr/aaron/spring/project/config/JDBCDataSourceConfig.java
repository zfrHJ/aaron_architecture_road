package com.zfr.aaron.spring.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author zfr
 *
 */
@Configuration
public class JDBCDataSourceConfig {
    @Value("${spring.datasource.driver-class-name}")
    private  String jdbc ;
    @Value("${spring.datasource.url}")
    private  String url;
    @Value("${spring.datasource.username}")
    private  String user;
    @Value("${spring.datasource.password}")
    private  String password;

    /*	 * 数据库连接	 */
    @Bean(name = "jdbcConnection")
    public  Connection getConnection() { // 获得数据库连接
        Connection conn = null;
         try {
             Class.forName(jdbc);
             String[] us = url.split("\\?");
             url = us[0]+"?characterEncoding=utf-8&useSSL=false";
             conn = DriverManager.getConnection(url, user, password);
         } catch (Exception e) {
             e.printStackTrace();
             return null;
         }
         return conn;
     }

     public static void CloseConnection(Connection oraConn) {
         try {
             if (oraConn != null)
             oraConn.close();
         } catch (Exception E) {
                oraConn = null;
         }
     }

}
