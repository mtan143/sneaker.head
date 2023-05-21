package com.flutter.sneaker.head.infra.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_size")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class ProductSizeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "product_size_id")
    private String productSizeId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "size_id")
    private String sizeId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}
