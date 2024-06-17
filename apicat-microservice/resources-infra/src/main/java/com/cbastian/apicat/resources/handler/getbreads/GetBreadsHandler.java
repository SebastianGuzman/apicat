package com.cbastian.apicat.resources.handler.getbreads;

import com.cbastian.apicat.resources.adapter.in.error.mapper.ErrorResponseMapper;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.ResponseBuilder;
import com.cbastian.apicat.resources.handler.error.ErrorHandler;
import com.cbastian.apicat.resources.handler.getbreads.mapper.GetBreadsMapper;
import com.cbastian.apicat.resources.kernel.command.getbreads.GetBreadsCommand;
import com.cbastian.apicat.resources.usecase.getbreads.GetBreadsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class GetBreadsHandler {
    private final GetBreadsUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String authorization, String messageUid ){
        return Mono.just(
                        useCase.execute(buildCommand( authorization, messageUid ))
                                .fold(
                                        ErrorResponseMapper::useCaseErrorToResponse,
                                        GetBreadsMapper::informationToResponse
                                )
                )
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private GetBreadsCommand buildCommand(String authorization, String messageUid){
        return GetBreadsMapper.requestToCommand(authorization, messageUid);
    }

}