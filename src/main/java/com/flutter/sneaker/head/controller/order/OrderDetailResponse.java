package com.flutter.sneaker.head.controller.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailResponse {
    private String productName;
    private Integer size;
    private Integer quantity;
    private double price;
}
