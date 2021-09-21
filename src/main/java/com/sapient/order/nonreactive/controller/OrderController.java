package com.sapient.order.nonreactive.controller;

import com.sapient.order.model.OrderHeader;
import com.sapient.order.model.request.OrderRequest;
import com.sapient.order.nonreactive.service.OrderHeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OrderController {

    private final OrderHeaderService orderHeaderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderHeader>> getOrderHeader() {
        return new ResponseEntity<List<OrderHeader>>(orderHeaderService.getAllOrderHeader(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderHeader> createOrderHeader(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<OrderHeader>(orderHeaderService.createOrderHeader(orderRequest), HttpStatus.OK);
    }

    @PutMapping("/order")
    public ResponseEntity<OrderHeader> updateOrderHeader(@RequestBody OrderRequest orderRequest) {
        return new ResponseEntity<OrderHeader>(orderHeaderService.updateOrderHeader(orderRequest), HttpStatus.OK);
    }
}
