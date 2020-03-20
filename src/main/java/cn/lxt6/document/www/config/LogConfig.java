package cn.lxt6.document.www.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springboot 日志配置类
 * @author chenzy
 * @date 2019.12.17
 */
@Configuration
public class LogConfig implements WebMvcConfigurer {

    /**
     *  访问接口错误
     */
    @Bean
    public Logger errQuestLog(){
        return LoggerFactory.getLogger("err_quest_log");
    }
    /**
     * 客户端访问日志
     */
    @Bean
    public Logger clientLog(){
        return LoggerFactory.getLogger("client");
    }

    /**
     * 客户端访问日志
     */
    @Bean
    public Logger errMachineLog (){
        return LoggerFactory.getLogger("err_machine_log");
    }
    /**
     * 数据库错误日志
     */
    @Bean
    public Logger nbBatheDBError(){
        return LoggerFactory.getLogger("nbBatheDBError");
    }
    /**
     * 数据库错误日志
     */
    @Bean
    public Logger dbInfoLog(){
        return LoggerFactory.getLogger("dbinfo");
    }
    /**
     * .net访问日志日志
     */
    @Bean
    public Logger netServerLog(){
        return LoggerFactory.getLogger("netServer");
    }

    /**
     * NB蓝牙设备授权访问日志日志
     */
    @Bean
    public Logger nbBatheAuthLog(){
        return LoggerFactory.getLogger("nbBatheAuth");
    }

}
