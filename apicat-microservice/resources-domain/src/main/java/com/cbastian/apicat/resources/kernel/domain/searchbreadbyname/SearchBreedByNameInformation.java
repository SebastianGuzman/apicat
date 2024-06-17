package com.cbastian.apicat.resources.kernel.domain.searchbreadbyname;

import com.cbastian.apicat.resources.kernel.domain.util.AbstractInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.HeaderObjectInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.MessageObjectInformationResponse;

public class SearchBreedByNameInformation extends AbstractInformationResponse<SearchBreedByNameInformationPayload> {

        public SearchBreedByNameInformation(HeaderObjectInformationResponse headers, MessageObjectInformationResponse messageResponse, SearchBreedByNameInformationPayload data) {
            super(headers, messageResponse, data);
        }
}
