package com.cbastian.apicat.resources.kernel.domain.example;

import com.cbastian.apicat.resources.kernel.domain.util.InformationPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExampleInformationPayload implements Serializable, InformationPayload {

    private String message;

}
