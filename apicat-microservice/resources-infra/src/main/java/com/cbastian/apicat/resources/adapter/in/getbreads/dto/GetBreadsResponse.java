package com.cbastian.apicat.resources.adapter.in.getbreads.dto;

import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;

@Builder
@AllArgsConstructor
public class GetBreadsResponse extends ArrayList<GetBreadsResponseCatBreadItem> implements GenericResponse, Serializable {
    /*private List<GetBreadsResponseCatBreadItem> breeds;

    public List<GetBreadsResponseCatBreadItem> getBreeds() {
        return breeds==null? new ArrayList<>():breeds;
    }*/
}
