package com.cbastian.apicat.resources.usecase.getbreadsbyid;

import com.cbastian.apicat.resources.kernel.command.getbreadsbyid.GetBreadsByIdCommand;
import com.cbastian.apicat.resources.kernel.domain.getbreadsbyid.GetBreadsByIdInformation;
import com.cbastian.apicat.resources.kernel.domain.getbreadsbyid.GetBreadsByIdInformationPayload;
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
public class GetBreadsByIdUseCase implements UseCase<GetBreadsByIdCommand, Either<UseCaseErrorResponse, GetBreadsByIdInformation>> {

    private final TheCatApiClientPort theCatApiClientPort;
    @Override
    public Either<UseCaseErrorResponse, GetBreadsByIdInformation> execute(GetBreadsByIdCommand command) {
        Either<Throwable,GetBreadsByIdInformation> response =
                Try.of( () -> {
                    CatBreadItem breed = theCatApiClientPort.getBreadById(command.getId()).block();

                    return buildSuccess(
                            breed,
                            GenericResponseCodes.TRANSACCION_EXITOSA,
                            "Consumo de servicio exitoso",
                            HttpStatus.OK,
                            command
                    );

                }).toEither();
        return response.isRight()?Either.right(response.get()):Either.left(buildErrorResponse(response.getLeft().getMessage() ));
    }

    private GetBreadsByIdInformation buildSuccess(CatBreadItem breadItem,
                                                  GenericResponseCodes code,
                                                  String detail,
                                                  HttpStatus httpStatus,
                                                  GetBreadsByIdCommand command) {
        return new GetBreadsByIdInformation(
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
                GetBreadsByIdInformationPayload
                        .builder()
                        .breads(breadItem)
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
