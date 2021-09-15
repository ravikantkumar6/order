package com.sapient.order.reactive.service;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.reactive.repository.OrderHeaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderHeaderRepository orderHeaderRepository;

    public Mono<OrderHeader> saveOrder(OrderHeader orderHeader) {
        log.info("Saving Order Header");
        orderHeader.setOrderId("Ord-"+ (int)( Math.random() * 100000));
        if(orderHeader.getCreatedDate() == null) {
            orderHeader.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            orderHeader.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
        }
        return orderHeaderRepository.save(orderHeader);
    }

    public Flux<OrderHeader> getOrder() {
        return orderHeaderRepository.findAll();
    }
}