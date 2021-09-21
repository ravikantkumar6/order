package com.sapient.order.model;

import com.sapient.order.model.enums.OrderStatus;
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

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderItem> orderItems;
}
