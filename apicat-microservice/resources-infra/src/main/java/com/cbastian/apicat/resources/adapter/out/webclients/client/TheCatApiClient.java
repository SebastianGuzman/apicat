package com.cbastian.apicat.resources.adapter.out.webclients.client;

import com.cbastian.apicat.resources.adapter.out.webclients.dto.GetBreadsDtoResponseCatBreadItem;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@AllArgsConstructor
public class TheCatApiClient {
    private final WebClient webClient;
    private final String URI_BREEDS = "/breeds";

    public Flux<GetBreadsDtoResponseCatBreadItem> getBreads() {
        log.info("Calling microservice");
        return webClient.get()
                .uri(URI_BREEDS)
                .exchange()
                .flatMapMany(clientResponse -> {
                    HttpStatus httpStatus = clientResponse.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        log.info("getBreads response {}", clientResponse);
                        return clientResponse.bodyToFlux(GetBreadsDtoResponseCatBreadItem.class);
                    } else {
                        log.info("getBreads clientResponse {}", clientResponse);
                        return Flux.error(new RuntimeException("Error, Client Information details:" ));
                    }
                })
                .doOnComplete(() -> log.info("Cat Api Log, Success "))
                .doOnError(error -> log.error(error.getMessage()));
    }

    public Mono<GetBreadsDtoResponseCatBreadItem> getBreadById(String id) {
        log.info("Calling microservice");
        return webClient.get()
                .uri(URI_BREEDS + "/" + id)
                .exchange()
                .flatMap(clientResponse -> {
                    HttpStatus httpStatus = clientResponse.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        log.info("getBreadById response {}", clientResponse);
                        return clientResponse.bodyToMono(GetBreadsDtoResponseCatBreadItem.class);
                    } else {
                        log.info("getBreadById clientResponse {}", clientResponse);
                        return Mono.error(new RuntimeException("Error, Client Information details:" ));
                    }
                })
                .doOnSuccess(success -> log.info("Cat Api Log, Success "))
                .doOnError(error -> log.error(error.getMessage()));
    }

    public Flux<GetBreadsDtoResponseCatBreadItem> searchBreadByName(String name) {
        log.info("Calling microservice");
        return webClient.get()
                .uri(URI_BREEDS + "/search?q=" + name +"&attach_image=1")
                .exchange()
                .flatMapMany(clientResponse -> {
                    HttpStatus httpStatus = clientResponse.statusCode();
                    if (httpStatus.is2xxSuccessful()) {
                        log.info("searchBreadByName response {}", clientResponse);
                        return clientResponse.bodyToFlux(GetBreadsDtoResponseCatBreadItem.class);
                    } else {
                        log.info("searchBreadByName clientResponse {}", clientResponse);
                        return Flux.error(new RuntimeException("Error, Client Information details:" ));
                    }
                })
                .doOnComplete(() -> log.info("Cat Api Log, Success "))
                .doOnError(error -> log.error(error.getMessage()));
    }
}
