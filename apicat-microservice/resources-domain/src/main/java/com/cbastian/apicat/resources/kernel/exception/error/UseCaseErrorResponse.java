package com.cbastian.apicat.resources.kernel.exception.error;

import com.cbastian.apicat.resources.kernel.domain.util.HeaderObjectInformationErrorResponse;
import com.cbastian.apicat.resources.kernel.domain.util.MessageObjectInformationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;


@Builder
@AllArgsConstructor
public class UseCaseErrorResponse extends Exception {

    private HeaderObjectInformationErrorResponse headers;

    private MessageObjectInformationResponse messageResponse;

    private List<UseCaseErrorResponseError> errors;


    public UseCaseErrorResponse(HeaderObjectInformationErrorResponse headers, MessageObjectInformationResponse messageResponse, UseCaseErrorResponseError error) {

        this.headers = headers;
        this.messageResponse = messageResponse;
        errors = getErrors();
        errors.add(error);

    }

    public HeaderObjectInformationErrorResponse getHeaders() {
        return headers;
    }


    public MessageObjectInformationResponse getMessageResponse() {
        return messageResponse;
    }

    public List<UseCaseErrorResponseError> getErrors() {
        if (this.errors == null)
            return new ArrayList<UseCaseErrorResponseError>();
        return errors;
    }
}
