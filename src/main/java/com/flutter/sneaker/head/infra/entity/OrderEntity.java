package com.flutter.sneaker.head.infra.entity;

import com.flutter.sneaker.head.infra.enumeration.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "last_modified_date")
    private LocalDateTime lastModifiedDate;
}
