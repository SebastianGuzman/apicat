package com.cbastian.apicat.resources.adapter.out.webclients.mapper;

import com.cbastian.apicat.resources.adapter.out.webclients.dto.GetBreadsDtoResponseCatBreadItem;
import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import com.cbastian.apicat.resources.kernel.domain.util.models.Weight;

public class TheCatApiClientMapper {
    public static CatBreadItem mapToCatBreadItem(GetBreadsDtoResponseCatBreadItem catBreadItem) {
        return CatBreadItem
                .builder()
                .cat_friendly(catBreadItem.getCat_friendly())
                .id(catBreadItem.getId())
                .adaptability(catBreadItem.getAdaptability())
                .affection_level(catBreadItem.getAffection_level())
                .child_friendly(catBreadItem.getChild_friendly())
                .description(catBreadItem.getDescription())
                .country_codes(catBreadItem.getCountry_codes())
                .country_code(catBreadItem.getCountry_code())
                .dog_friendly(catBreadItem.getDog_friendly())
                .energy_level(catBreadItem.getEnergy_level())
                .experimental(catBreadItem.getExperimental())
                .grooming(catBreadItem.getGrooming())
                .hairless(catBreadItem.getHairless())
                .health_issues(catBreadItem.getHealth_issues())
                .hypoallergenic(catBreadItem.getHypoallergenic())
                .indoor(catBreadItem.getIndoor())
                .intelligence(catBreadItem.getIntelligence())
                .life_span(catBreadItem.getLife_span())
                .name(catBreadItem.getName())
                .natural(catBreadItem.getNatural())
                .origin(catBreadItem.getOrigin())
                .rare(catBreadItem.getRare())
                .reference_image_id(catBreadItem.getReference_image_id())
                .rex(catBreadItem.getRex())
                .shedding_level(catBreadItem.getShedding_level())
                .short_legs(catBreadItem.getShort_legs())
                .social_needs(catBreadItem.getSocial_needs())
                .stranger_friendly(catBreadItem.getStranger_friendly())
                .suppressed_tail(catBreadItem.getSuppressed_tail())
                .temperament(catBreadItem.getTemperament())
                .vocalisation(catBreadItem.getVocalisation())
                .weight(Weight
                        .builder()
                        .imperial(catBreadItem.getWeight().getImperial())
                        .metric(catBreadItem.getWeight().getMetric())
                        .build()
                )
                .wikipedia_url(catBreadItem.getWikipedia_url())
                .build();
    }
}
