package com.sapient.order.reactive.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.order.dto.OrderDAO;
import com.sapient.order.model.OrderHeader;
import com.sapient.order.model.enums.OrderStatus;
import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.reactive.util.WebClientUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    public static final String INVENTORY_UPDATE = "inventory.update";
    private final OrderDAO orderDAO;
    private final WebClientUtil webClientUtil;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderHeader saveOrder(OrderRequest orderRequest, Boolean isEvent) {
        log.info("Saving Order Header");
        OrderHeader orderHeader = orderDAO.placeOrder(orderRequest);
        orderRequest.setOrderId(String.valueOf(orderHeader.getId()));
        orderRequest.setProductId(orderHeader.getOrderItems().get(0).getProductId());
        updateInventory(orderRequest, isEvent);
        Optional<OrderHeader> orderHeaderOptional = orderDAO.findById(Integer.valueOf(orderRequest.getOrderId()));
        return orderHeaderOptional.get();
    }

    public List<OrderHeader> getOrder() {
        return orderDAO.findAll();
    }

    public OrderHeader updateOrderHeader(OrderRequest orderRequest, Boolean isEvent) {
        Optional<OrderHeader> orderHeaderOptional = orderDAO.findById(Integer.valueOf(orderRequest.getOrderId()));
        OrderHeader orderHeader = orderHeaderOptional.get();
        orderHeader.setOrderStatus(orderRequest.getOrderStatus());
        orderHeader.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
        updateInventory(orderRequest, isEvent);
        return orderDAO.save(orderHeader);
    }

    private void updateInventory(OrderRequest orderRequest, Boolean isEvent) {
        if (orderRequest.getOrderStatus().compareTo(OrderStatus.SHIPPED) != 0) {
            if (isEvent) {
                sendInventoryUpdateEvent(orderRequest);
            } else {
                String url = "http://localhost:9002/api/inventory/v1/update/inventory";
                webClientUtil.getResponseEntityMono(url, HttpMethod.PUT, new HttpEntity<>(orderRequest), String.class);
            }
        }
    }

    private void sendInventoryUpdateEvent(OrderRequest orderRequest) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String message = mapper.writeValueAsString(orderRequest);
            kafkaTemplate.send(INVENTORY_UPDATE, message);
        } catch (JsonProcessingException e) {
            log.error("Exception occured in parsing the message: {}", orderRequest);
        }
    }

//    private void revertInventory(OrderRequest orderRequest, Boolean isEvent) {
//        if (orderRequest.getOrderStatus().compareTo(OrderStatus.CANCEL) == 0) {
//            if (isEvent) {
//
//            } else {
//                String url = "http://localhost:9002/api/inventory/v1/update/inventory";
//                webClientUtil.getResponseEntityMono(url, HttpMethod.PUT, new HttpEntity<>(orderRequest), String.class);
//            }
//        }
//
//    }
//}
}
