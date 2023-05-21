package com.flutter.sneaker.head.controller.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailRequest {
    private String productId;
    private Integer size;
    private Integer quantity;
}
