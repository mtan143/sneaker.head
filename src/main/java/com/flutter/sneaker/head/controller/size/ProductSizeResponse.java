package com.flutter.sneaker.head.controller.size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSizeResponse {

    private Integer size;
    private Integer availableQuantity;
}
