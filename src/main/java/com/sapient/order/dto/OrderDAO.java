package com.sapient.order.dto;

import com.sapient.order.model.OrderHeader;
import com.sapient.order.model.OrderItem;
import com.sapient.order.model.Product;
import com.sapient.order.model.enums.OrderStatus;
import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.repository.OrderHeaderRepository;
import com.sapient.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDAO {
    private final OrderHeaderRepository orderHeaderRepository;
    private final ProductRepository productRepository;

    public List<OrderHeader> findAll() {
        return orderHeaderRepository.findAll();
    }

    public Optional<OrderHeader> findById(Integer id) {
        return orderHeaderRepository.findById(id);
    }

    public OrderHeader placeOrder(OrderRequest orderRequest) {
        OrderHeader orderHeader = new OrderHeader();
        List<Product> products = productRepository.findRandamProduct();
        products.forEach(product -> {
            orderHeader.setOrderDetail(orderRequest.getOrderDetail());
            orderHeader.setOrderStatus(OrderStatus.CREATED);
            orderHeader.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            orderHeader.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(String.valueOf(product.getId()));
            orderItem.setQuantity(1);
            orderItem.setUpdatedDate(LocalDateTime.now(ZoneOffset.UTC));
            orderItem.setCreatedDate(LocalDateTime.now(ZoneOffset.UTC));
            List<OrderItem> orderItems = new ArrayList<>();
            orderItems.add(orderItem);
            orderHeader.setOrderItems(orderItems);
        });
        return orderHeaderRepository.save(orderHeader);
    }

    public OrderHeader save(OrderHeader orderHeader) {
        return orderHeaderRepository.save(orderHeader);
    }
}
