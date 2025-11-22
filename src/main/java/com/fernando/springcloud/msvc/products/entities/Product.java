package com.fernando.springcloud.msvc.products.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name ="products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    //@Column(name="tbl_price_product")
    private BigDecimal price;
    @Column (name="create_at")
    private LocalDate createAt;
    @Transient
    private int port;



}
