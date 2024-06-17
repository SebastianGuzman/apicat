package com.cbastian.apicat.resources.adapter.out.webclients.dto;

import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Builder
public class GetBreadsDtoResponse implements Serializable {
    private List<GetBreadsDtoResponseCatBreadItem> breeds;

    public List<GetBreadsDtoResponseCatBreadItem> getBreeds() {
        return breeds==null? new ArrayList<>():breeds;
    }
}
