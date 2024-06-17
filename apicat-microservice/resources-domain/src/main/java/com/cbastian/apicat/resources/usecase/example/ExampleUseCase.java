package com.cbastian.apicat.resources.usecase.example;

import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponse;
import com.cbastian.apicat.resources.usecase.UseCase;
import com.cbastian.apicat.resources.kernel.command.example.ExampleCommand;
import com.cbastian.apicat.resources.kernel.domain.example.ExampleInformationPayload;
import com.cbastian.apicat.resources.kernel.domain.util.HeaderObjectInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.util.MessageObjectInformationResponse;
import com.cbastian.apicat.resources.kernel.domain.example.ExampleInformation;
import io.vavr.control.Either;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Slf4j
public class ExampleUseCase implements UseCase<ExampleCommand, Either<UseCaseErrorResponse, ExampleInformation>> {


    @Override
    public Either<UseCaseErrorResponse, ExampleInformation> execute(ExampleCommand command) {
        return Either.right(buildSuccess(command,"Hello Team!"));
    }

    private ExampleInformation buildSuccess(ExampleCommand command,String messagePayload) {
        return new ExampleInformation(
                HeaderObjectInformationResponse
                        .builder()
                        .messageUuid(command.getMessageUuid())
                        .build(),
                MessageObjectInformationResponse
                        .builder()
                        .responseCode("200")
                        .responseMessage("OK")
                        .responseDetail("Some extra data")
                        .build(),
                ExampleInformationPayload
                        .builder()
                        .message(messagePayload)
                        .build()

                );
    }

}
