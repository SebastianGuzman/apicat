package com.cbastian.apicat.resources.adapter.in.getbreadsbyid;

import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.getbreadsbyid.GetBreadsByIdHandler;
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
public class GetBreadsByIdAdapter {
    private final GetBreadsByIdHandler handler;

    @GetMapping(value = "/breeds/{id}")
    //@PreAuthorize("hasAnyAuthority('SCOPE_example')")
    public Mono<ResponseEntity<GenericResponse>> getBreedsById(
            @RequestHeader(name = "Authorization") @NotEmpty(message = "Authorization cannot be empty") String authorization,
            @RequestHeader(name = "message-uuid", required = false) String messageUuid,
            @PathVariable @Pattern(regexp = "^[a-zA-Z]{4}$", message = "id must be exactly 4 letters") String id) {

        return handler.execute(authorization, messageUuid, id);
    }
}