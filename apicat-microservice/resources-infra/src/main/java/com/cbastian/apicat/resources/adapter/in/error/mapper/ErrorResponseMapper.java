package com.cbastian.apicat.resources.adapter.in.error.mapper;

import com.cbastian.apicat.resources.adapter.in.error.ErrorResponse;
import com.cbastian.apicat.resources.adapter.in.error.ErrorResponseError;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectErrorResponse;
import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;

import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class ErrorResponseMapper {

    public static ErrorResponse useCaseErrorToResponse(UseCaseErrorResponse error) {
        return ErrorResponse
                .builder()
                .headers(
                        HeaderObjectErrorResponse
                                .builder()
                                .httpStatusCode(error.getHeaders().getHttpStatusCode())
                                .htttpStatusDesc(error.getHeaders().getHtttpStatusDesc())
                                .timestamp(error.getHeaders().getTimestamp())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(error.getMessageResponse().getResponseCode())
                                .responseMessage(error.getMessageResponse().getResponseMessage())
                                .responseDetails(error.getMessageResponse().getResponseDetail())
                                .build()
                )
                .errors(
                        error.getErrors()
                                .stream()
                                .map(object ->
                                        ErrorResponseError
                                                .builder()
                                                .errorCode(object.getErrorCode())
                                                .errorDetail(object.getErrorDetail())
                                                .build()
                                )
                                .collect(Collectors.toList())

                )
                .build();

    }




}
