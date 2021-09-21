package com.sapient.order.reactive.router;

import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.reactive.handler.OrderHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration(proxyBeanMethods = false)
public class OrderRouter {

    @Bean
    @RouterOperations({@RouterOperation(path = "/orders", beanClass = OrderHandler.class, beanMethod = "getOrder"),
            @RouterOperation(path = "/save/order", beanClass = OrderHandler.class, beanMethod = "saveOrder",
                    operation = @Operation(
                            operationId = "saveOrderHeaders",
                            requestBody = @RequestBody(required = true, description = "Enter Request body as Json Object",
                                    content = @Content(
                                            schema = @Schema(implementation = OrderRequest.class))))),
            @RouterOperation(path = "/update/order", beanClass = OrderHandler.class, beanMethod = "updateOrderHeader",
                    operation = @Operation(
                            operationId = "updateOrderHeader",
                            requestBody = @RequestBody(required = true, description = "Enter Request body as Json Object",
                                    content = @Content(
                                            schema = @Schema(implementation = OrderRequest.class))))),
            @RouterOperation(path = "/save/order/event", beanClass = OrderHandler.class, beanMethod = "saveOrderEvent",
                    operation = @Operation(
                            operationId = "saveOrderHeaderThroughEvent",
                            requestBody = @RequestBody(required = true, description = "Enter Request body as Json Object",
                                    content = @Content(
                                            schema = @Schema(implementation = OrderRequest.class))))),
            @RouterOperation(path = "/update/order/event", beanClass = OrderHandler.class, beanMethod = "updateOrderHeaderEvent",
                    operation = @Operation(
                            operationId = "updateOrderHeaderThroughEvent",
                            requestBody = @RequestBody(required = true, description = "Enter Request body as Json Object",
                                    content = @Content(
                                            schema = @Schema(implementation = OrderRequest.class)))))
    })
    public RouterFunction<ServerResponse> route(OrderHandler orderHandler) {

        return RouterFunctions
                .route(GET("/orders").and(accept(MediaType.APPLICATION_JSON)), orderHandler::getOrder)
                .andRoute(POST("/save/order").and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON)), orderHandler::saveOrder)
                .andRoute(PUT("/update/order").and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON)), orderHandler::updateOrderHeader)
                .andRoute(POST("/save/order/event").and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON)), orderHandler::saveOrderEvent)
                .andRoute(PUT("/update/order/event").and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON)), orderHandler::updateOrderHeaderEvent);
    }
}
