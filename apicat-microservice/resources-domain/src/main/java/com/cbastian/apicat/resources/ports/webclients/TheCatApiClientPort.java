package com.cbastian.apicat.resources.ports.webclients;

import com.cbastian.apicat.resources.kernel.domain.util.models.CatBreadItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TheCatApiClientPort {
    Flux<CatBreadItem> getBreads();

    Mono<CatBreadItem> getBreadById(String id);

    Flux<CatBreadItem> searchBreadByName(String name);
}
