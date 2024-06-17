package com.cbastian.apicat.resources.kernel.domain.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@Getter
public class MessageObjectInformationResponse implements Serializable {

    private String responseCode;
    private String responseMessage;
    private String responseDetail;

}
