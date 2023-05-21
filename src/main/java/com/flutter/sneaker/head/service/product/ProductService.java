package com.flutter.sneaker.head.service.product;

import com.flutter.sneaker.head.controller.product.ProductRequest;
import com.flutter.sneaker.head.controller.product.ProductResponse;
import com.flutter.sneaker.head.infra.entity.ProductEntity;
import com.flutter.sneaker.head.infra.entity.ProductSizeEntity;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findByCategoryId(String categoryI, Integer pageNo, Integer pageSize);

    List<ProductResponse> findAll(Integer pageNo, Integer pageSize);

    ProductResponse getProductDetail(String productId);

    long countByCategoryId(String categoryId);

    ProductResponse upsertProduct(ProductRequest productRequest);

    ProductResponse toggleProduct(ProductRequest productRequest);

    void checkProductAmount(String productId, String sizeId, Integer quantity);

    void updateAvailableQuantity(String productId, Integer quantity);

    double getProductPrice(String productId, Integer quantity);

    ProductEntity findByProductId(String productId);

    ProductSizeEntity findByProductSizeId(String productSizeId);
}
