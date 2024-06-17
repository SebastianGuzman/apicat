package com.cbastian.apicat.resources.usecase.getbreads;

import com.cbastian.apicat.resources.kernel.command.getbreads.GetBreadsCommand;
import com.cbastian.apicat.resources.kernel.domain.getbreads.GetBreadsInformation;
import com.cbastian.apicat.resources.kernel.domain.getbreads.GetBreadsInformationPayload;
import com.cbastian.apicat.resources.kernel.domain.util.*;
import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponse;
import com.cbastian.apicat.resources.kernel.exception.error.UseCaseErrorResponseError;
import com.cbastian.apicat.resources.ports.webclients.TheCatApiClientPort;
import com.cbastian.apicat.resources.usecase.UseCase;
import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class GetBreadsUseCase implements UseCase<GetBreadsCommand, Either<UseCaseErrorResponse, GetBreadsInformation>> {

    private final TheCatApiClientPort theCatApiClientPort;
    @Override
    public Either<UseCaseErrorResponse, GetBreadsInformation> execute(GetBreadsCommand command) {
        Either<Throwable,GetBreadsInformation> response =
                Try.of( () -> {
                    List<CatBreadItem> breedsList = theCatApiClientPort.getBreads()
                            .collectList()
                            .block();

                    return buildSuccess(
                            breedsList,
                            GenericResponseCodes.TRANSACCION_EXITOSA,
                            "Consumo de servicio exitoso",
                            HttpStatus.OK,
                            command
                    );

                }).toEither();
        return response.isRight()?Either.right(response.get()):Either.left(buildErrorResponse(response.getLeft().getMessage() ));
    }

    private GetBreadsInformation buildSuccess(List<CatBreadItem> breadItemsList,GenericResponseCodes code, String detail, HttpStatus httpStatus, GetBreadsCommand command) {
            return new GetBreadsInformation(
                    HeaderObjectInformationResponse
                            .builder()
                            .messageUuid(command.getMessageUuid())
                            .httpStatusCode(httpStatus.value())
                            .httpStatusDesc(httpStatus.name())
                            .build(),
                    MessageObjectInformationResponse
                            .builder()
                            .responseCode(code.getValue())
                            .responseMessage(code.getDescription())
                            .responseDetail(detail)
                            .build(),
                    GetBreadsInformationPayload
                            .builder()
                            .breads(breadItemsList)
                            .build()
                    );
    }

    private UseCaseErrorResponse buildErrorResponse(String message){//String message
        log.error("CreateClientUseCase:: {}",message);
        return UseCaseErrorResponse
                .builder()
                .errors(
                        List.of(UseCaseErrorResponseError
                                .builder()
                                .errorCode("99")
                                .errorDetail(message)
                                .build()
                        )
                )
                .messageResponse(
                        MessageObjectInformationResponse
                                .builder()
                                .responseMessage("Error en servidor, por favor intente mas tarde")
                                .build()
                )
                .headers(
                        HeaderObjectInformationErrorResponse
                                .builder()
                                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .htttpStatusDesc(HttpStatus.INTERNAL_SERVER_ERROR.name())
                                .build())
                .build();

    }

}
