package com.sapient.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@ToString
@Entity(name = "OrderItem")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private String productId;
    @Column
    private long quantity;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;
}
