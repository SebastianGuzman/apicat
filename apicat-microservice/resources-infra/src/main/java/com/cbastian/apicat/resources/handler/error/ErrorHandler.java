package com.cbastian.apicat.resources.handler.error;

import com.cbastian.apicat.resources.usecase.service.time.TimeManagerService;
import com.cbastian.apicat.resources.adapter.in.error.ErrorResponse;
import com.cbastian.apicat.resources.adapter.in.error.ErrorResponseError;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@NoArgsConstructor
public class ErrorHandler {

    public static Mono<ResponseEntity<GenericResponse>> handlerError(final Throwable error) {
        return Mono.just(ResponseEntity.status(INTERNAL_SERVER_ERROR).body(unExpectedError()));
    }

    private static ErrorResponse unExpectedError() {
        return
                ErrorResponse
                        .builder()
                        .headers(
                                HeaderObjectErrorResponse
                                        .builder()
                                        .httpStatusCode(INTERNAL_SERVER_ERROR.value())
                                        .htttpStatusDesc(INTERNAL_SERVER_ERROR.name())
                                        .timestamp(new TimeManagerService().getInstantIsoFormat())
                                        .build()
                        )
                        .messageResponse(
                                MessageObjectResponse
                                        .builder()
                                        .responseCode(String.valueOf(INTERNAL_SERVER_ERROR.value()))
                                        .responseMessage(INTERNAL_SERVER_ERROR.name())
                                        .responseDetails(INTERNAL_SERVER_ERROR.getReasonPhrase())
                                        .build()
                        )
                        .errors(

                                getNullError(INTERNAL_SERVER_ERROR.name(), "Unknown Error")
                        )
                        .build();
    }

    private static List<ErrorResponseError> getNullError(String code, String detail) {
        List<ErrorResponseError> responseErrorsList = new ArrayList<ErrorResponseError>();
        responseErrorsList.add(ErrorResponseError
                .builder()
                .errorCode(code)
                .errorDetail(detail)
                .build());
        return responseErrorsList;
    }

}
