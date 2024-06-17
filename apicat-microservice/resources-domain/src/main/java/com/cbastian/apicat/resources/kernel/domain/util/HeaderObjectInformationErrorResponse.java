package com.cbastian.apicat.resources.kernel.domain.util;

import com.cbastian.apicat.resources.usecase.service.time.TimeManagerService;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;

@Builder
@AllArgsConstructor
public class HeaderObjectInformationErrorResponse implements Serializable {

    private Integer httpStatusCode;
    private String htttpStatusDesc;
    private String timestamp;

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getHtttpStatusDesc() {
        return htttpStatusDesc;
    }

    public String getTimestamp() {
        return timestamp == null ? (new TimeManagerService().getInstantIsoFormat()) : timestamp;
    }

}
