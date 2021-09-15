package com.sapient.order.reactive.router;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.util.BaseTestCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class OrderHeaderRouterTest extends BaseTestCase {

    @Test
    public void testGetAllOrder() {
        webTestClient
                .get().uri("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderHeader.class).value(greeting -> {
                    Assertions.assertThat(greeting.getOrderId()).isGreaterThan("0");
                });
    }

    @Test
    public void testSaveOrder() {
        webTestClient
                .post().uri("/order")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(OrderHeader.class).value(greeting -> {
                    Assertions.assertThat(greeting.getOrderId()).isEqualTo("0");
                });
    }
}