package com.cbastian.apicat.resources.kernel.domain.getbreads;

import com.cbastian.apicat.resources.kernel.domain.util.AbstractInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.HeaderObjectInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.MessageObjectInformationResponse;

public class GetBreadsInformation extends AbstractInformationResponse<GetBreadsInformationPayload> {
    public GetBreadsInformation(HeaderObjectInformationResponse headers,
                                MessageObjectInformationResponse messageResponse,
                                GetBreadsInformationPayload data) {
        super(headers, messageResponse, data);
    }
}
