package com.cbastian.apicat.resources.handler;

import com.cbastian.apicat.resources.adapter.in.error.ErrorResponse;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@NoArgsConstructor
@Slf4j
public class ResponseBuilder {

    public ResponseEntity<GenericResponse> mapResponseBuilder(GenericResponse T) {
        if (T instanceof ErrorResponse) {
            ErrorResponse response = Optional.of((ErrorResponse) T).get();
            return new ResponseEntity<GenericResponse>(response, HttpStatus.valueOf(response.getHeaders().getHttpStatusCode()));
        } else {
            return new ResponseEntity<GenericResponse>(T, HttpStatus.OK);
        }
    }


}