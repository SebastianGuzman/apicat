package com.cbastian.apicat.resources.usecase.searchbreedbyname;

import com.cbastian.apicat.resources.kernel.command.searchbreadbyname.SearchBreedByNameCommand;
import com.cbastian.apicat.resources.kernel.domain.searchbreadbyname.SearchBreedByNameInformation;
import com.cbastian.apicat.resources.kernel.domain.searchbreadbyname.SearchBreedByNameInformationPayload;
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

public class SearchBreedByNameUseCase implements UseCase<SearchBreedByNameCommand, Either<UseCaseErrorResponse, SearchBreedByNameInformation>> {

    private final TheCatApiClientPort theCatApiClientPort;
    @Override
    public Either<UseCaseErrorResponse, SearchBreedByNameInformation> execute(SearchBreedByNameCommand command) {
        Either<Throwable,SearchBreedByNameInformation> response =
                Try.of( () -> {
                    List<CatBreadItem> breedsList = theCatApiClientPort.searchBreadByName(command.getName())
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

    private SearchBreedByNameInformation buildSuccess(List<CatBreadItem> breadItemsList,GenericResponseCodes code, String detail, HttpStatus httpStatus, SearchBreedByNameCommand command) {
        return new SearchBreedByNameInformation(
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
                SearchBreedByNameInformationPayload
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