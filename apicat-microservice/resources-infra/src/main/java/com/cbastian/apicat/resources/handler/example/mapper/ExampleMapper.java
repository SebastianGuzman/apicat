package com.cbastian.apicat.resources.handler.example.mapper;

import com.cbastian.apicat.resources.kernel.command.example.ExampleCommand;
import com.cbastian.apicat.resources.adapter.in.example.dto.ExampleResponse;
import com.cbastian.apicat.resources.adapter.in.example.dto.ExampleResponseData;
import com.cbastian.apicat.resources.adapter.in.util.HeaderObjectResponse;
import com.cbastian.apicat.resources.adapter.in.util.MessageObjectResponse;
import com.cbastian.apicat.resources.kernel.domain.example.ExampleInformation;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ExampleMapper {

    public static ExampleCommand requestToCommand(String authorization, String messageUuid){
        return ExampleCommand
                .builder()
                .authorization(authorization)
                .messageUuid(messageUuid)
                .build();
    }

    public static ExampleResponse informationToResponse(ExampleInformation info){
        return ExampleResponse
                .builder()
                .headers(
                        HeaderObjectResponse
                                .builder()
                                .messageUid(info.getHeaders().getMessageUuid())
                                .requestDatetime(info.getHeaders().getRequestDatetime())
                                .requestAppId(info.getHeaders().getRequestAppId())
                                .build()
                )
                .messageResponse(
                        MessageObjectResponse
                                .builder()
                                .responseCode(info.getMessageResponse().getResponseCode())
                                .responseMessage(info.getMessageResponse().getResponseMessage())
                                .responseDetails(info.getMessageResponse().getResponseDetail())
                                .build()
                )
                .data(
                        ExampleResponseData
                                .builder()
                                .message(info.getData().getMessage())
                                .build()
                )
                .build();
    }
}
