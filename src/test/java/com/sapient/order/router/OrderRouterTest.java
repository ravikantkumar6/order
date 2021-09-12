package com.sapient.order.router;

import com.sapient.order.dto.Order;
import com.sapient.order.util.BaseTestCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class OrderRouterTest extends BaseTestCase {

    @Test
    public void testGetAllOrder() {
        webTestClient
                .get().uri("/orders")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).value(greeting -> {
                    Assertions.assertThat(greeting.getMessage()).isEqualTo("Get All Order");
                });
    }

    @Test
    public void testSaveOrder() {
        webTestClient
                .post().uri("/order")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Order.class).value(greeting -> {
                    Assertions.assertThat(greeting.getMessage()).isEqualTo("Save Order");
                });
    }
}