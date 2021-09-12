package com.sapient.order.handler;

import com.sapient.order.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class OrderHandler {

    public Mono<ServerResponse> getOrder(ServerRequest request) {
        log.info("Get Order");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Order(1, "work-001", "Dummy Order Place", 200)));
    }

    public Mono<ServerResponse> saveOrder(ServerRequest request) {
        log.info("Save Order");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(new Order(1, "work-001", "Dummy Order Place", 200)));
    }
}
