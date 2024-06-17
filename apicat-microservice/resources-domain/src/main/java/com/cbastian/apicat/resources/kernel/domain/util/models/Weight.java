package com.cbastian.apicat.resources.kernel.domain.util.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public  class Weight implements Serializable {
    private String imperial;
    private String metric;
}