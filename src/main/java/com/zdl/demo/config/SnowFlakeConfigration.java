package com.zdl.demo.config;

import com.zdl.demo.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnowFlakeConfigration {

    @Value("${snowflake.dataCenterId}")
    private long dataCenterId; //数据中心
    @Value("${snowflake.machineId}")
    private long machineId; //机器标识

    @Bean
    public SnowFlake SnowFlake() {
        return new SnowFlake(dataCenterId, machineId);
    }
}
