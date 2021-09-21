package com.sapient.order.reactive.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.reactive.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderEventListener {
    public static final String ORDER_UPDATE = "order.update";

    private final OrderService orderService;

    @KafkaListener(topics = ORDER_UPDATE, groupId = "order")
    public void createShipping(ConsumerRecord<String, Message> message) {
        try {
            log.info("Create Shipping : " + message.value());
            ObjectMapper mapper = new ObjectMapper();
            orderService.updateOrderHeader(mapper.readValue(String.valueOf(message.value()), OrderRequest.class), true);
        } catch (Exception ex) {
            log.error("Exception occured in parsing the message: " + ex);
        }
    }
}
