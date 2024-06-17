package com.cbastian.apicat.resources.handler.getbreadsbyid;

import com.cbastian.apicat.resources.adapter.in.error.mapper.ErrorResponseMapper;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.ResponseBuilder;
import com.cbastian.apicat.resources.handler.error.ErrorHandler;
import com.cbastian.apicat.resources.handler.getbreadsbyid.mapper.GetBreadsByIdMapper;
import com.cbastian.apicat.resources.kernel.command.getbreadsbyid.GetBreadsByIdCommand;
import com.cbastian.apicat.resources.usecase.getbreadsbyid.GetBreadsByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;



@RequiredArgsConstructor
public class GetBreadsByIdHandler {
    private final GetBreadsByIdUseCase useCase;

    public Mono<ResponseEntity<GenericResponse>> execute(String authorization, String messageUid, String id) {
        return Mono.just(
                        useCase.execute(buildCommand(authorization, messageUid, id))
                                .fold(
                                        ErrorResponseMapper::useCaseErrorToResponse,
                                        GetBreadsByIdMapper::informationToResponse
                                )
                )
                .map(genericResponse -> new ResponseBuilder().mapResponseBuilder(genericResponse))
                .switchIfEmpty(Mono.just(ResponseEntity.noContent().build()))
                .onErrorResume(ErrorHandler::handlerError);
    }

    private GetBreadsByIdCommand buildCommand(String authorization, String messageUid, String id) {
        return GetBreadsByIdMapper.requestToCommand(authorization, messageUid, id);
    }
}
