package com.cbastian.apicat.resources.adapter.in.searchbreadbyname.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public  class SearchBreedByNameResponseCatBreadItemWeight implements Serializable {
    private String imperial;
    private String metric;
}