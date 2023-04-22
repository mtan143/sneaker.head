package com.flutter.sneaker.head.controller.product;

import com.flutter.sneaker.head.controller.size.ProductSizeResponse;
import com.flutter.sneaker.head.infra.entity.ProductEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ProductResponse {

    private String productId;
    private String categoryId;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer availableQuantity;
    private String description;
    private String url;
    private LocalDateTime createdDate;
    private List<ProductSizeResponse> sizes;

    public static ProductResponse fromEntity(ProductEntity entity) {
        return ProductResponse.builder()
                .name(entity.getName())
                .productId(entity.getProductId())
                .categoryId(entity.getCategoryId())
                .price(entity.getPrice())
                .quantity(entity.getQuantity())
                .availableQuantity(entity.getAvailableQuantity())
                .description(entity.getDescription())
                .url(entity.getUrl())
                .createdDate(entity.getCreatedDate())
                .build();
    }
}
