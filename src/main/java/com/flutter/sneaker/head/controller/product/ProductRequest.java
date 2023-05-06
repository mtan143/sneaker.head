package com.flutter.sneaker.head.controller.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    private String productId;
    private String categoryId;
    private String name;
    private Integer price;
    private Integer availableQuantity;
    private Integer quantity;
    private String description;
    private String url;
}
