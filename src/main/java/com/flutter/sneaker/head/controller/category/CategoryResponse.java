package com.flutter.sneaker.head.controller.category;

import com.flutter.sneaker.head.infra.entity.CategoryEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {

    private String categoryId;
    private String categoryName;
    private long totalProduct;

    public static CategoryResponse fromEntity(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .categoryId(categoryEntity.getCategoryId())
                .categoryName(categoryEntity.getCategoryName())
                .build();
    }
}
