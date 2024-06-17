package com.cbastian.apicat.resources.adapter.in.searchbreadbyname;

import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.searchbreedbyname.SearchBreedByNameHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;




@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/apicat/v1")
@RequiredArgsConstructor
@Validated
@EnableCaching
@Slf4j
public class SearchBreedByNameAdapter {
    private final SearchBreedByNameHandler handler;

    @GetMapping(value = "/breeds/search")
    //@PreAuthorize("hasAnyAuthority('SCOPE_example')")
    public Mono<ResponseEntity<GenericResponse>> searchBreedsByName(
            @RequestHeader(name = "Authorization") @NotEmpty(message = "Authorization cannot be empty") String authorization,
            @RequestHeader(name = "message-uuid", required = false) String messageUuid,
            @RequestParam @Pattern(regexp = "^[a-zA-Z]*$", message = "q must only contain letters") String q) {


        return handler.execute(authorization, messageUuid, q);
    }
}