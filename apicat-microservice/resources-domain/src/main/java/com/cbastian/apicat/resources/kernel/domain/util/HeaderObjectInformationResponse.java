package com.cbastian.apicat.resources.kernel.domain.util;

import com.cbastian.apicat.resources.usecase.service.time.TimeManagerService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
@AllArgsConstructor
public class HeaderObjectInformationResponse implements Serializable {

    private String messageUuid;
    private String requestDatetime;
    private String requestAppId;
    private Integer httpStatusCode;
    private String httpStatusDesc;

    public String getRequestDatetime() {
        return requestDatetime == null ? (new TimeManagerService().getInstantIsoFormat()) : requestDatetime;
    }

}
