package com.cbastian.apicat.resources.kernel.domain.searchbreadbyname;

import com.cbastian.apicat.resources.kernel.domain.util.InformationPayload;
import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchBreedByNameInformationPayload implements Serializable, InformationPayload {
    private List<CatBreadItem> breads;

    public List<CatBreadItem> getBreads() {
        return breads==null? new ArrayList<>():breads;
    }
}
