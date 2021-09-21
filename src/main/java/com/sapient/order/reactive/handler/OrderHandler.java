package com.sapient.order.reactive.handler;

import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.reactive.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderService orderService;

    public Mono<ServerResponse> getOrder(ServerRequest request) {
        log.info("Get Order");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .bodyValue(orderService.getOrder());
    }

    public Mono<ServerResponse> saveOrder(ServerRequest request) {
        log.info("Save Order");
        return request
                .bodyToMono(OrderRequest.class)
                .flatMap(orderRequest -> Mono.fromCallable(() -> this.orderService.saveOrder(orderRequest, false)))
                .flatMap(orderHeader -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderHeader));
    }

    public Mono<ServerResponse> updateOrderHeader(ServerRequest request) {
        log.info("update Order");
        return request
                .bodyToMono(OrderRequest.class)
                .flatMap(orderRequest -> Mono.fromCallable(() -> this.orderService.updateOrderHeader(orderRequest, false)))
                .flatMap(orderHeader -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderHeader));
    }

    public Mono<ServerResponse> saveOrderEvent(ServerRequest request) {
        log.info("Save Order through Event");
        return request
                .bodyToMono(OrderRequest.class)
                .flatMap(orderRequest -> Mono.fromCallable(() -> this.orderService.saveOrder(orderRequest, true)))
                .flatMap(orderHeader -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderHeader));
    }

    public Mono<ServerResponse> updateOrderHeaderEvent(ServerRequest request) {
        log.info("update Order through Event");
        return request
                .bodyToMono(OrderRequest.class)
                .flatMap(orderRequest -> Mono.fromCallable(() -> this.orderService.updateOrderHeader(orderRequest, true)))
                .flatMap(orderHeader -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(orderHeader));
    }

}
