package com.cbastian.apicat.resources.adapter.out.webclients.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetBreadsDtoResponseCatBreadsItemWeight implements Serializable {
    private String imperial;
    private String metric;
}