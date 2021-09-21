package com.sapient.order.nonreactive.service.impl;

import com.sapient.order.dto.OrderDAO;
import com.sapient.order.model.OrderHeader;
import com.sapient.order.model.enums.Operation;
import com.sapient.order.model.enums.OrderStatus;
import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.nonreactive.service.OrderHeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderHeaderServiceIml implements OrderHeaderService {

    private final OrderDAO orderDAO;
    private final RestTemplate restTemplate;

    @Override
    public List<OrderHeader> getAllOrderHeader() {
        return orderDAO.findAll();
    }

    @Override
    public OrderHeader createOrderHeader(OrderRequest orderRequest) {
        OrderHeader orderHeader = orderDAO.placeOrder(orderRequest);
        orderRequest.setOrderId(String.valueOf(orderHeader.getId()));
        orderRequest.setProductId(orderHeader.getOrderItems().get(0).getProductId());
        String url = "http://localhost:9002/api/inventory/v1/rest/inventory/operation/" + Operation.UPDATE;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(orderRequest), String.class);
        Optional<OrderHeader> orderHeaderOptional = orderDAO.findById(Integer.valueOf(orderRequest.getOrderId()));
        return orderHeaderOptional.get();
    }

    @Override
    public OrderHeader updateOrderHeader(OrderRequest orderRequest) {
        Optional<OrderHeader> orderHeaderOptional = orderDAO.findById(Integer.valueOf(orderRequest.getOrderId()));
        OrderHeader orderHeader = orderHeaderOptional.get();
        orderHeader.setOrderStatus(orderRequest.getOrderStatus());
        orderHeader.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
        if (orderRequest.getOrderStatus().compareTo(OrderStatus.CANCEL) == 0) {
            String url = "http://localhost:9002/api/inventory/v1/rest/inventory/operation/" + Operation.CANCEL;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(orderRequest), String.class);
        }
        return orderDAO.save(orderHeader);
    }

}
