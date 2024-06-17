package com.cbastian.apicat.resources.adapter.in.error;

import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse implements GenericResponse, Serializable {
    private HeaderObjectErrorResponse headers;
    private MessageObjectResponse messageResponse;
    private List<ErrorResponseError> errors;
}
