package com.sapient.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Entity(name = "OrderHeader")
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeader implements Serializable {
    private static final long serialVersionUID = -558043294043707772L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String OrderDetail;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;

    @OneToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderItem> orderItems;

}
