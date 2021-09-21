package com.sapient.order.model;

import com.sapient.order.model.enums.Category;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@ToString
@Entity(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private static final long serialVersionUID = -558043294043707772L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private Double price;

    @Enumerated(EnumType.STRING)
    private Category category;
    @Column
    private LocalDateTime updatedDate;
    @Column
    private LocalDateTime createdDate;
}
