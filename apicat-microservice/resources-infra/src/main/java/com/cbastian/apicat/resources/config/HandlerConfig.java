package com.cbastian.apicat.resources.config;


import com.cbastian.apicat.resources.handler.example.ExampleHandler;
import com.cbastian.apicat.resources.handler.getbreads.GetBreadsHandler;
import com.cbastian.apicat.resources.handler.getbreadsbyid.GetBreadsByIdHandler;
import com.cbastian.apicat.resources.handler.searchbreedbyname.SearchBreedByNameHandler;
import com.cbastian.apicat.resources.usecase.example.ExampleUseCase;
import com.cbastian.apicat.resources.usecase.getbreads.GetBreadsUseCase;
import com.cbastian.apicat.resources.usecase.getbreadsbyid.GetBreadsByIdUseCase;
import com.cbastian.apicat.resources.usecase.searchbreedbyname.SearchBreedByNameUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerConfig {


    @Bean
    public ExampleHandler getAccountListInformationHandler(final ExampleUseCase useCase) {
        return new ExampleHandler(useCase);
    }

    @Bean
    public GetBreadsHandler getBreadsHandler(final GetBreadsUseCase useCase) {
        return new GetBreadsHandler(useCase);
    }

    @Bean
    public GetBreadsByIdHandler getBreadsByIdHandler(final GetBreadsByIdUseCase useCase) {
        return new GetBreadsByIdHandler(useCase);
    }

    @Bean
    public SearchBreedByNameHandler getSearchBreedByNameHandler(final SearchBreedByNameUseCase useCase) {
        return new SearchBreedByNameHandler(useCase);
    }



}
