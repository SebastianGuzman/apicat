package com.cbastian.apicat.resources.adapter.out.webclients;

import com.cbastian.apicat.resources.adapter.out.webclients.client.TheCatApiClient;
import com.cbastian.apicat.resources.adapter.out.webclients.mapper.TheCatApiClientMapper;
import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import com.cbastian.apicat.resources.ports.webclients.TheCatApiClientPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class TheCatApiAdapter implements TheCatApiClientPort {

    private final String MSG_SUCCESS = "Consumo exitoso";
    private final String MSG_ERROR = "Error en servicio, por favor intente mas tarde";

    private final String MSG_ERROR_NOT_DATA_FOUND = "no se encontraron datos, por favor intente mas tarde";
    private final TheCatApiClient theCatApiClient;
    @Override
    public Flux<CatBreadItem> getBreads() {
        return theCatApiClient.getBreads()
                .map(TheCatApiClientMapper::mapToCatBreadItem)
                .doOnComplete(() -> log.info(MSG_SUCCESS))
                .doOnError(e -> log.error(MSG_ERROR))
                .onErrorResume(e ->
                        Flux.error(
                                new RuntimeException(MSG_ERROR)
                        ));
    }

    @Override
    public Mono<CatBreadItem> getBreadById(String id) {
        return theCatApiClient.getBreadById(id)
                .map(TheCatApiClientMapper::mapToCatBreadItem)
                .doOnSuccess(m->log.info(MSG_SUCCESS))
                .switchIfEmpty(Mono.error(new RuntimeException(MSG_ERROR_NOT_DATA_FOUND)))
                .doOnError(e->log.error(MSG_ERROR))
                .onErrorResume(e->
                        Mono.error(
                                new RuntimeException(MSG_ERROR)
                        )
                )
                ;
    }

    @Override
    public Flux<CatBreadItem> searchBreadByName(String name) {
        return theCatApiClient.searchBreadByName(name)
                .map(TheCatApiClientMapper::mapToCatBreadItem)
                .doOnComplete(() -> log.info(MSG_SUCCESS))
                .doOnError(e -> log.error(MSG_ERROR))
                .onErrorResume(e ->
                        Flux.error(
                                new RuntimeException(MSG_ERROR)
                        ));
    }
}

