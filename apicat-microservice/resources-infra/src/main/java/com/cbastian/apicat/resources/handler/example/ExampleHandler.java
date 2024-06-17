package com.cbastian.apicat.resources.handler.example;

import com.cbastian.apicat.resources.handler.ResponseBuilder;
import com.cbastian.apicat.resources.adapter.in.error.mapper.ErrorResponseMapper;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.error.ErrorHandler;
import com.cbastian.apicat.resources.handler.example.mapper.ExampleMapper;
import com.cbastian.apicat.resources.kernel.command.example.ExampleCommand;
import com.cbastian.apicat.resources.usecase.example.ExampleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ExampleHandler {

    private final ExampleUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String authorization, String messageUid) {
        return Mono.just(
                        useCase.execute(buildCommand(authorization,messageUid))
                                .fold(
                                        ErrorResponseMapper::useCaseErrorToResponse,
                                        ExampleMapper::informationToResponse
                                )
                )
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);

    }

    private ExampleCommand buildCommand(String authorization, String messageUid) {
        return ExampleMapper.requestToCommand(authorization,messageUid);
    }
}
