package com.sapient.order.router;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.handler.OrderHandler;
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
            @RouterOperation(path = "/order", beanClass = OrderHandler.class, beanMethod = "saveOrder",
                    operation = @Operation(
                            operationId = "saveOrder",
                            requestBody = @RequestBody(required = true, description = "Enter Request body as Json Object",
                                    content = @Content(
                                            schema = @Schema(implementation = OrderHeader.class)))))
    })
    public RouterFunction<ServerResponse> route(OrderHandler orderHandler) {

        return RouterFunctions
                .route(GET("/orders").and(accept(MediaType.APPLICATION_JSON)), orderHandler::getOrder)
                .andRoute(POST("/order").and(contentType(MediaType.APPLICATION_JSON)).and(accept(MediaType.APPLICATION_JSON)), orderHandler::saveOrder);
    }
}
