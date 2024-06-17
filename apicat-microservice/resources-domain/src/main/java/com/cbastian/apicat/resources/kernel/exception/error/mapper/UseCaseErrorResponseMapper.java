package com.cbastian.apicat.resources.kernel.exception.error.mapper;

import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponse;
import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponseError;
import com.cbastian.apicat.resources.kernel.domain.util.HeaderObjectInformationErrorResponse;
import com.cbastian.apicat.resources.kernel.domain.util.MessageObjectInformationResponse;

public class UseCaseErrorResponseMapper {
    public static UseCaseErrorResponse buildErrorResponse(Integer httpStatusCode, String htttpStatusDesc, String timestamp, String responseCode, String responseMessage, String responseDetail, String errorCode, String errorDetail) {

        return new UseCaseErrorResponse(
                HeaderObjectInformationErrorResponse
                        .builder()
                        .httpStatusCode(httpStatusCode)
                        .htttpStatusDesc(htttpStatusDesc)
                        .timestamp(timestamp)
                        .build(),
                MessageObjectInformationResponse
                        .builder()
                        .responseCode(responseCode)
                        .responseMessage(responseMessage)
                        .responseDetail(responseDetail)
                        .build(),
                UseCaseErrorResponseError
                        .builder()
                        .errorCode(errorCode)
                        .errorDetail(errorDetail)
                        .build()
        );
    }
}
