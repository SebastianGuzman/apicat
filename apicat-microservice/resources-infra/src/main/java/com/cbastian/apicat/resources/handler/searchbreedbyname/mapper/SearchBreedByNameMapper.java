package com.cbastian.apicat.resources.handler.searchbreedbyname.mapper;

import com.cbastian.apicat.resources.adapter.in.searchbreadbyname.dto.SearchBreedByNameResponse;
import com.cbastian.apicat.resources.adapter.in.searchbreadbyname.dto.SearchBreedByNameResponseCatBreadItem;
import com.cbastian.apicat.resources.adapter.in.searchbreadbyname.dto.SearchBreedByNameResponseCatBreadItemWeight;
import com.cbastian.apicat.resources.kernel.command.searchbreadbyname.SearchBreedByNameCommand;
import com.cbastian.apicat.resources.kernel.domain.searchbreadbyname.SearchBreedByNameInformation;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class SearchBreedByNameMapper {
    public static SearchBreedByNameCommand requestToCommand(String authorization, String messageUid, String query) {
        return SearchBreedByNameCommand.builder()
                .authorization(authorization)
                .messageUuid(messageUid)
                .name(query)
                .build();
    }

    public static SearchBreedByNameResponse informationToResponse(SearchBreedByNameInformation info) {
        SearchBreedByNameResponse response = new SearchBreedByNameResponse();
        response.addAll(info
                .getData()
                .getBreads()
                .stream()
                .map(bread -> SearchBreedByNameResponseCatBreadItem
                        .builder()
                        .name(bread.getName())
                        .adaptability(bread.getAdaptability())
                        .affection_level(bread.getAffection_level())
                        .cat_friendly(bread.getCat_friendly())
                        .country_codes(bread.getCountry_codes())
                        .id(bread.getId())
                        .description(bread.getDescription())
                        .dog_friendly(bread.getDog_friendly())
                        .energy_level(bread.getEnergy_level())
                        .grooming(bread.getGrooming())
                        .health_issues(bread.getHealth_issues())
                        .intelligence(bread.getIntelligence())
                        .life_span(bread.getLife_span())
                        .shedding_level(bread.getShedding_level())
                        .social_needs(bread.getSocial_needs())
                        .stranger_friendly(bread.getStranger_friendly())
                        .temperament(bread.getTemperament())
                        .vocalisation(bread.getVocalisation())
                        .weight(SearchBreedByNameResponseCatBreadItemWeight
                                .builder()
                                .imperial(bread.getWeight().getImperial())
                                .metric(bread.getWeight().getMetric())
                                .build()
                        )
                        .build())
                .collect(Collectors.toList())
        );
        return response;
    }
}
