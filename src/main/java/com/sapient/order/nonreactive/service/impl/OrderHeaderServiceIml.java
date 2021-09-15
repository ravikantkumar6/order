package com.sapient.order.nonreactive.service.impl;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.nonreactive.service.OrderHeaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderHeaderServiceIml implements OrderHeaderService {

    @Override
    public List<OrderHeader> getAllOrderHeader() {
        return null;
    }
}
