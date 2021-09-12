package com.sapient.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private Integer id;
    private String OrderId;
    private String orderDescription;
    private Integer orderTotalAmount;

}
