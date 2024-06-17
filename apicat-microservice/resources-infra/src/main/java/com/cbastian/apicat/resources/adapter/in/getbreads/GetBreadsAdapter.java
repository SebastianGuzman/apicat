package com.cbastian.apicat.resources.adapter.in.getbreads;

import com.cbastian.apicat.resources.adapter.in.util.GenericResponse;
import com.cbastian.apicat.resources.handler.getbreads.GetBreadsHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotEmpty;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/apicat/v1")
@RequiredArgsConstructor
@Validated
@EnableCaching
@Slf4j
public class GetBreadsAdapter {
    private final GetBreadsHandler handler;

    @GetMapping(value = "/breeds")
    //@PreAuthorize("hasAnyAuthority('SCOPE_example')")
    public Mono<ResponseEntity<GenericResponse>> getUserAccountInformation(
            @RequestHeader(name = "Authorization") @NotEmpty(message = "Authorization cannot be empty") String authorization,
            @RequestHeader(name = "message-uuid", required = false) String messageUuid) {

        return handler.execute(authorization, messageUuid);
    }
}
