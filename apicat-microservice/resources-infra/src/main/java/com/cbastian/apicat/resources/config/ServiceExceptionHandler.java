package com.cbastian.apicat.resources.config;

import com.cbastian.apicat.resources.adapter.in.error.ErrorResponse;
import com.cbastian.apicat.resources.adapter.in.error.ErrorResponseError;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import com.cbastian.apicat.resources.usecase.service.time.TimeManagerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    private final TimeManagerService timeManagerService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException argumenException, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return responseBuilder(status, argumentErrorToList(argumenException, status));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            ServletRequestBindingException bindingException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return responseBuilder(status, bindingErrorTolist(bindingException));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return responseBuilder(status, noReadableErrorToList(ex));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return responseBuilder(status, noHandlerFountErrorToList(ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return responseBuilder(status, handlerHttpMethodNotSupportedToList(ex));

    }

    private List<ErrorResponseError> handlerHttpMethodNotSupportedToList(HttpRequestMethodNotSupportedException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<ErrorResponseError>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.METHOD_NOT_ALLOWED.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;

    }

    private List<ErrorResponseError> noHandlerFountErrorToList(NoHandlerFoundException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<ErrorResponseError>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private List<ErrorResponseError> noReadableErrorToList(HttpMessageNotReadableException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<ErrorResponseError>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private List<ErrorResponseError> bindingErrorTolist(ServletRequestBindingException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<ErrorResponseError>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;

    }

    private List<ErrorResponseError> argumentErrorToList(MethodArgumentNotValidException ex, HttpStatus status) {
        return ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(defaultMessageSourceResolvable -> {
                    return ErrorResponseError
                            .builder()
                            .errorCode(String.valueOf(status.value()))
                            .errorDetail(defaultMessageSourceResolvable.getDefaultMessage())
                            .build();
                })
                .collect(Collectors.toList());
    }

    private ResponseEntity<Object> responseBuilder(HttpStatus status, List<ErrorResponseError> errorList) {
        return new ResponseEntity<Object>(
                ErrorResponse
                        .builder()
                        .headers(
                                HeaderObjectErrorResponse
                                        .builder()
                                        .httpStatusCode(status.value())
                                        .htttpStatusDesc(status.name())
                                        .timestamp(timeManagerService.getInstantIsoFormat())
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
                        .errors(
                                errorList
                        )
                        .build()
                , status);
    }


}