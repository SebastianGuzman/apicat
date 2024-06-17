package com.cbastian.apicat.resources.config;


import com.cbastian.apicat.resources.adapter.in.error.ErrorResponse;
import com.cbastian.apicat.resources.adapter.in.error.ErrorResponseError;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Collections;


@RestControllerAdvice
public class CustomErrorTokenHandler {


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ExpiredJwtException exception) {
        return ResponseEntity.badRequest().body(
                responseBuilder(
                        HttpStatus.UNAUTHORIZED,
                        ErrorResponseError.builder()
                                .errorCode(HttpStatus.UNAUTHORIZED.toString())
                                .errorDetail("El token expiro").build())
        );
    }

    private ErrorResponse responseBuilder(HttpStatus status, ErrorResponseError message) {
        return ErrorResponse.builder()
                .headers(
                        HeaderObjectErrorResponse
                                .builder()
                                .httpStatusCode(HttpStatus.UNAUTHORIZED.value())
                                .htttpStatusDesc(HttpStatus.UNAUTHORIZED.toString())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()))
                                .responseMessage(status.name())
                                .responseDetails(status.getReasonPhrase())
                                .build()
                )
                .errors(Collections.singletonList(message))
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body(
                responseGlobalErrorBuilder(
                        HttpStatus.BAD_REQUEST,
                        ErrorResponseError.builder()
                                .errorCode(HttpStatus.BAD_REQUEST.toString())
                                .errorDetail(ex.getMessage()).build())
        );
    }

    private ErrorResponse responseGlobalErrorBuilder(HttpStatus status, ErrorResponseError message) {
        return ErrorResponse.builder()
                .headers(
                        HeaderObjectErrorResponse
                                .builder()
                                .httpStatusCode(status.value())
                                .htttpStatusDesc(status.name())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(String.valueOf(status.value()))
                                .responseMessage(status.name())
                                .responseDetails(status.getReasonPhrase())
                                .build()
                )
                .errors(Collections.singletonList(message))
                .build();
    }

}
