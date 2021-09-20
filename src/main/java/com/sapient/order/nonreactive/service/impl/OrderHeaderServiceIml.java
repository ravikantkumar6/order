package com.sapient.order.nonreactive.service.impl;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.nonreactive.service.OrderHeaderService;
import com.sapient.order.repository.OrderHeaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderHeaderServiceIml implements OrderHeaderService {

    private final OrderHeaderRepository orderHeaderRepository;
    @Override
    public List<OrderHeader> getAllOrderHeader() {
        return orderHeaderRepository.findAll();
    }

    @Override
    public OrderHeader createOrderHeader(OrderHeader orderHeader) {
        return null;
    }
}
