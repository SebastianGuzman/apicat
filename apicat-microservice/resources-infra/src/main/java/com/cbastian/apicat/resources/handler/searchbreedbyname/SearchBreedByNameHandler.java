package com.cbastian.apicat.resources.handler.searchbreedbyname;

import com.cbastian.apicat.resources.adapter.in.error.mapper.ErrorResponseMapper;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.ResponseBuilder;
import com.cbastian.apicat.resources.handler.error.ErrorHandler;
import com.cbastian.apicat.resources.handler.searchbreedbyname.mapper.SearchBreedByNameMapper;
import com.cbastian.apicat.resources.kernel.command.searchbreadbyname.SearchBreedByNameCommand;
import com.cbastian.apicat.resources.usecase.searchbreedbyname.SearchBreedByNameUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SearchBreedByNameHandler {
    private final SearchBreedByNameUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String authorization, String messageUid, String query) {
        return Mono.just(
                        useCase.execute(buildCommand( authorization, messageUid, query ))
                                .fold(
                                        ErrorResponseMapper::useCaseErrorToResponse,
                                        SearchBreedByNameMapper::informationToResponse
                                )
                )
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private SearchBreedByNameCommand buildCommand(String authorization, String messageUid, String query){
        return SearchBreedByNameMapper.requestToCommand(authorization, messageUid, query);
    }
}
