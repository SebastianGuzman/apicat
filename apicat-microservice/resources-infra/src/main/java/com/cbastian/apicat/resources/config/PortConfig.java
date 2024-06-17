package com.cbastian.apicat.resources.config;


import com.cbastian.apicat.resources.adapter.out.webclients.TheCatApiAdapter;
import com.cbastian.apicat.resources.adapter.out.webclients.client.TheCatApiClient;
import com.cbastian.apicat.resources.ports.webclients.TheCatApiClientPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortConfig {
    @Bean
    public TheCatApiClientPort theCatApiClientPort(final TheCatApiClient theCatApiClient){
        return new TheCatApiAdapter(theCatApiClient);
    }

    /*Add another ports configuration here*/

}
