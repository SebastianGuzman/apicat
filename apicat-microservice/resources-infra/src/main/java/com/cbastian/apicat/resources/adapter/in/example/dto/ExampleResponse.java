package com.cbastian.apicat.resources.adapter.in.example.dto;

import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectResponse;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class ExampleResponse implements GenericResponse, Serializable {
    private static final long serialVersionUID = 1L;
    private HeaderObjectResponse headers;
    private MessageObjectResponse messageResponse;
    private ExampleResponseData data;
}