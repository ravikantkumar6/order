package com.sapient.order.router;

import com.sapient.order.handler.OrderHandler;
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
            @RouterOperation(path = "/order", beanClass = OrderHandler.class, beanMethod = "saveOrder")
    })
    public RouterFunction<ServerResponse> route(OrderHandler orderHandler) {

        return RouterFunctions
                .route(GET("/orders").and(accept(MediaType.APPLICATION_JSON)), orderHandler::getOrder)
                .andRoute(POST("/order").and(accept(MediaType.APPLICATION_JSON)), orderHandler::saveOrder);
    }
}
