package com.cbastian.apicat.resources.adapter.in.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageObjectResponse implements Serializable {
    private String responseCode;
    private String responseMessage;
    private String responseDetails;

}