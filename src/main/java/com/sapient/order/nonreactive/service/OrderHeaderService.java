package com.sapient.order.nonreactive.service;

import com.sapient.order.model.OrderHeader;
import com.sapient.order.model.request.OrderRequest;

import java.util.List;

public interface OrderHeaderService {
    List<OrderHeader> getAllOrderHeader();

    OrderHeader createOrderHeader(OrderRequest orderRequest);

    OrderHeader updateOrderHeader(OrderRequest orderRequest);
}
