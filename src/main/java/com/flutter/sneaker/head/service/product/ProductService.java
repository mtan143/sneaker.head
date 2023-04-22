package com.flutter.sneaker.head.service.product;

import com.flutter.sneaker.head.controller.product.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findByCategoryId(String categoryI, Integer pageNo, Integer pageSize);

    List<ProductResponse> findAll(Integer pageNo, Integer pageSize);

    ProductResponse getProductDetail(String productId);

    long countByCategoryId(String categoryId);
}
