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
public class GetBreadsDtoResponseCatBreadItem implements Serializable {
    private GetBreadsDtoResponseCatBreadsItemWeight weight;
    private String id;
    private String name;
    private String temperament;
    private String origin;
    private String country_codes;
    private String country_code;
    private String description;
    private String life_span;
    private int indoor;
    private int adaptability;
    private int affection_level;
    private int child_friendly;
    private int cat_friendly;
    private int dog_friendly;
    private int energy_level;
    private int grooming;
    private int health_issues;
    private int intelligence;
    private int shedding_level;
    private int social_needs;
    private int stranger_friendly;
    private int vocalisation;
    private int experimental;
    private int hairless;
    private int natural;
    private int rare;
    private int rex;
    private int suppressed_tail;
    private int short_legs;
    private String wikipedia_url;
    private int hypoallergenic;
    private String reference_image_id;

}