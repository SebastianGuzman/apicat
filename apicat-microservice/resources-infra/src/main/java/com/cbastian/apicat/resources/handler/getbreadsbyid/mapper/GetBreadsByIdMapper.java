package com.cbastian.apicat.resources.handler.getbreadsbyid.mapper;

import com.cbastian.apicat.resources.adapter.in.getbreadsbyid.dto.GetBreadsByIdResponse;
import com.cbastian.apicat.resources.adapter.in.getbreadsbyid.dto.GetBreadsByIdResponseCatBreadItemWeight;
import com.cbastian.apicat.resources.kernel.command.getbreadsbyid.GetBreadsByIdCommand;
import com.cbastian.apicat.resources.kernel.domain.getbreadsbyid.GetBreadsByIdInformation;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GetBreadsByIdMapper {
    public static GetBreadsByIdCommand requestToCommand(String authorization, String messageUid, String id) {
        return GetBreadsByIdCommand.builder()
                .authorization(authorization)
                .messageUuid(messageUid)
                .id(id)
                .build();
    }

    public static GetBreadsByIdResponse informationToResponse(GetBreadsByIdInformation info) {
        return GetBreadsByIdResponse.builder()
                .name(info.getData().getBreads().getName())
                .adaptability(info.getData().getBreads().getAdaptability())
                .affection_level(info.getData().getBreads().getAffection_level())
                .cat_friendly(info.getData().getBreads().getCat_friendly())
                .country_codes(info.getData().getBreads().getCountry_codes())
                .id(info.getData().getBreads().getId())
                .description(info.getData().getBreads().getDescription())
                .dog_friendly(info.getData().getBreads().getDog_friendly())
                .energy_level(info.getData().getBreads().getEnergy_level())
                .grooming(info.getData().getBreads().getGrooming())
                .health_issues(info.getData().getBreads().getHealth_issues())
                .intelligence(info.getData().getBreads().getIntelligence())
                .life_span(info.getData().getBreads().getLife_span())
                .shedding_level(info.getData().getBreads().getShedding_level())
                .social_needs(info.getData().getBreads().getSocial_needs())
                .stranger_friendly(info.getData().getBreads().getStranger_friendly())
                .temperament(info.getData().getBreads().getTemperament())
                .vocalisation(info.getData().getBreads().getVocalisation())
                .weight(GetBreadsByIdResponseCatBreadItemWeight.builder()
                        .imperial(info.getData().getBreads().getWeight().getImperial())
                        .metric(info.getData().getBreads().getWeight().getMetric())
                        .build()
                ).build();
    }
}
