package com.cbastian.apicat.resources.kernel.domain.getbreadsbyid;

import com.cbastian.apicat.resources.kernel.domain.util.AbstractInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.HeaderObjectInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.MessageObjectInformationResponse;


public class GetBreadsByIdInformation extends AbstractInformationResponse<GetBreadsByIdInformationPayload> {

    public GetBreadsByIdInformation(HeaderObjectInformationResponse headers, MessageObjectInformationResponse messageResponse, GetBreadsByIdInformationPayload data) {
        super(headers, messageResponse, data);
    }
}
