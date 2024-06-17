package com.cbastian.apicat.resources.config;

import com.cbastian.apicat.resources.adapter.in.error.ErrorResponse;
import com.cbastian.apicat.resources.adapter.in.error.ErrorResponseError;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import com.cbastian.apicat.resources.usecase.service.time.TimeManagerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@AllArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final Logger LOG = Logger.getLogger(CustomAccessDeniedHandler.class.getName());


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse responseBody = responseBuilder(HttpStatus.FORBIDDEN, accesDeniedErrorToList(accessDeniedException));
        writeCustomResponse(response, responseBody);

    }

    private void writeCustomResponse(HttpServletResponse response, ErrorResponse error) {
        if (!response.isCommitted()) {
            try {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write(new ObjectMapper().writeValueAsString(error));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private List<ErrorResponseError> accesDeniedErrorToList(AccessDeniedException ex) {
        List<ErrorResponseError> responseErrors = new ArrayList<ErrorResponseError>();
        responseErrors.add(ErrorResponseError
                .builder()
                .errorCode(String.valueOf(HttpStatus.FORBIDDEN.value()))
                .errorDetail(ex.getMessage())
                .build());
        return responseErrors;
    }

    private ErrorResponse responseBuilder(HttpStatus status, List<ErrorResponseError> errorList) {
        return ErrorResponse
                .builder()
                .headers(
                        HeaderObjectErrorResponse
                                .builder()
                                .httpStatusCode(status.value())
                                .htttpStatusDesc(status.name())
                                .timestamp(new TimeManagerService().getInstantIsoFormat())
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
                .build();
    }

}