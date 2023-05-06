package com.flutter.sneaker.head.controller.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    private String categoryId;
    private String categoryName;
    private int totalProduct;
}
