package org.example.springdatap.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String description;
    private Double price;
    private Integer stock;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private java.util.List<OrderItem> orderItems;
}