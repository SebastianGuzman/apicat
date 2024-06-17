package com.cbastian.apicat.resources.kernel.domain.getbreadsbyid;

import com.cbastian.apicat.resources.kernel.domain.util.InformationPayload;
import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetBreadsByIdInformationPayload implements Serializable, InformationPayload {
    private CatBreadItem breads;
}
