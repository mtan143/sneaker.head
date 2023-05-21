package com.flutter.sneaker.head.controller.product;

import com.flutter.sneaker.head.controller.size.SizeRequest;
import lombok.Builder;
import lombok.Data;

import java.util.List;

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
    private List<SizeRequest> sizes;
    private String url;
}
