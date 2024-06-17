package com.cbastian.apicat.resources.adapter.in.searchbreadbyname.dto;

import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;

@Builder
@Getter
@AllArgsConstructor
public class SearchBreedByNameResponse extends ArrayList<SearchBreedByNameResponseCatBreadItem> implements GenericResponse, Serializable {

}
