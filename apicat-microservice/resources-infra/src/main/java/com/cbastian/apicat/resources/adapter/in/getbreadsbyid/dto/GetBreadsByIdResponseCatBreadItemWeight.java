package com.cbastian.apicat.resources.adapter.in.getbreadsbyid.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public  class GetBreadsByIdResponseCatBreadItemWeight implements Serializable {
    private String imperial;
    private String metric;
}