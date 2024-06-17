package com.cbastian.apicat.resources.config;

import com.cbastian.apicat.resources.usecase.service.time.TimeManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public TimeManagerService getTimeManagerService(){
        return new TimeManagerService();
    }

}
