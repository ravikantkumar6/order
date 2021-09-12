package com.sapient.order.repository;

import com.sapient.order.dto.Order;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<Order, Integer> {

}
