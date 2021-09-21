package com.sapient.order.model.request;

import com.sapient.order.model.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderRequest {
    private String OrderDetail;
    private Address address;
    private String productId;
    private String orderId;
    private OrderStatus orderStatus;
}
