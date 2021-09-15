package com.sapient.order.reactive.handler;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.reactive.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    public Mono<ServerResponse> getOrder(ServerRequest request) {
        log.info("Get Order");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(orderService.getOrder(), OrderHeader.class);
    }

    public Mono<ServerResponse> saveOrder(ServerRequest request) {
        log.info("Save Order");
        return request.bodyToMono(OrderHeader.class).flatMap(order -> orderService.saveOrder(order))
                .flatMap(orderHeader -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromValue(orderHeader)));
    }
}
