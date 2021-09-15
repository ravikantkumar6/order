package com.sapient.order.reactive.repository;

import com.sapient.order.dto.OrderHeader;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHeaderRepository extends ReactiveCrudRepository<OrderHeader, Integer> {

}