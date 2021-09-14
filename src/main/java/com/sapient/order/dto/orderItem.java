package com.sapient.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class orderItem {
    @Id
    private long id;
    private String orderId;
    private String productId;
    private long quantity;
}
