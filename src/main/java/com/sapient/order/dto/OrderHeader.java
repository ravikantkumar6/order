package com.sapient.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Table("OrderHeader")
@NoArgsConstructor
@AllArgsConstructor
public class OrderHeader implements Serializable {
    private static final long serialVersionUID = -558043294043707772L;
    @Id
    private Integer id;
    @Column
    private String orderId;
    @Column
    private String OrderDetail;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;

}
