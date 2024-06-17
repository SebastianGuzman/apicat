package com.cbastian.apicat.resources.handler.getbreads.mapper;

import com.cbastian.apicat.resources.adapter.in.getbreads.dto.GetBreadsResponse;
import com.cbastian.apicat.resources.adapter.in.getbreads.dto.GetBreadsResponseCatBreadItem;
import com.cbastian.apicat.resources.adapter.in.getbreads.dto.GetBreadsResponseCatBreadItemWeight;
import com.cbastian.apicat.resources.kernel.command.getbreads.GetBreadsCommand;
import com.cbastian.apicat.resources.kernel.domain.getbreads.GetBreadsInformation;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class GetBreadsMapper {

    public static GetBreadsCommand requestToCommand(String authorization, String messageUid) {
        return GetBreadsCommand.builder()
                .authorization(authorization)
                .messageUuid(messageUid)
                .build();
    }

    public static GetBreadsResponse informationToResponse(GetBreadsInformation info) {
        GetBreadsResponse response = new GetBreadsResponse();
        response.addAll(info
                .getData()
                .getBreads()
                .stream()
                .map(bread -> GetBreadsResponseCatBreadItem
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
                        .weight(GetBreadsResponseCatBreadItemWeight
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
    /*
    public static GetBreadsResponse informationToResponse(GetBreadsInformation info) {
        return GetBreadsResponse
                .builder()
                .breeds(info
                        .getData()
                        .getBreads()
                        .stream()
                        .map(bread -> GetBreadsResponseCatBreadItem
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
                                .weight(GetBreadsResponseCatBreadItemWeight
                                        .builder()
                                        .imperial(bread.getWeight().getImperial())
                                        .metric(bread.getWeight().getMetric())
                                        .build()
                                )
                                .build())
                        .collect(Collectors.toList())
                )
                .build();
    }*/
}
