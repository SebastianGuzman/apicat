package com.cbastian.apicat.resources.config;


import com.cbastian.apicat.resources.ports.webclients.TheCatApiClientPort;
import com.cbastian.apicat.resources.usecase.example.ExampleUseCase;
import com.cbastian.apicat.resources.usecase.getbreads.GetBreadsUseCase;
import com.cbastian.apicat.resources.usecase.getbreadsbyid.GetBreadsByIdUseCase;
import com.cbastian.apicat.resources.usecase.searchbreedbyname.SearchBreedByNameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public ExampleUseCase getExampleUseCase() {
        return new ExampleUseCase(
        );
    }

    @Bean
    public GetBreadsUseCase getGetBreadsUseCase(final TheCatApiClientPort theCatApiClientPort) {
        return new GetBreadsUseCase(theCatApiClientPort);
    }

    @Bean
    public GetBreadsByIdUseCase getGetBreadsByIdUseCase(final TheCatApiClientPort theCatApiClientPort) {
        return new GetBreadsByIdUseCase(theCatApiClientPort);
    }

    @Bean
    public SearchBreedByNameUseCase getSearchBreedByNameUseCase(final TheCatApiClientPort theCatApiClientPort) {
        return new SearchBreedByNameUseCase(theCatApiClientPort);
    }


}
