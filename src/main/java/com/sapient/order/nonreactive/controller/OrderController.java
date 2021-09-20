package com.sapient.order.nonreactive.controller;

import com.sapient.order.dto.OrderHeader;
import com.sapient.order.nonreactive.service.OrderHeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderHeaderService orderHeaderService;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderHeader>> getOrderHeader(){
        return new ResponseEntity<List<OrderHeader>>(orderHeaderService.getAllOrderHeader(), HttpStatus.OK);
    }

    @PostMapping("order")
    public ResponseEntity<OrderHeader> createOrderHeader(@RequestBody OrderHeader orderHeader){
        return new ResponseEntity<OrderHeader>(orderHeaderService.createOrderHeader(orderHeader), HttpStatus.OK);
    }
}
