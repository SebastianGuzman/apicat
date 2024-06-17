package com.cbastian.apicat.resources.config.webclients;

import com.cbastian.apicat.resources.adapter.out.webclients.client.TheCatApiClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TheCatApiClientConfig {
    @Value("${clients.cat-api.host}")
    private String webClientCustomer;

    @Bean
    public WebClient getTheCatApiClient(){
        return WebClient.create(webClientCustomer);
    }

    @Bean
    public TheCatApiClient getTheCatApiClientConf(@Qualifier("getTheCatApiClient") WebClient webClient){
        return new TheCatApiClient(webClient);
    }

}


/*
*


    @Bean
    public WebClient getPersonaCliente(){
        return WebClient.create(webClientCustomer);
    }

    @Bean
    public MicroservicePersonaClienteClient getMicroservicePersonaClienteClient(@Qualifier("getPersonaCliente") WebClient webClient){
        return new MicroservicePersonaClienteClient(webClient);
    }
* */