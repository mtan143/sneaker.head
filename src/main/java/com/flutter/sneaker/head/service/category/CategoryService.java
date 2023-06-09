package com.flutter.sneaker.head.service.category;

import com.flutter.sneaker.head.controller.category.CategoryRequest;
import com.flutter.sneaker.head.controller.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAll();

    CategoryResponse findByCategoryId(String categoryId);

    CategoryResponse upsert(CategoryRequest categoryRequest);

    CategoryResponse toggleCategory(CategoryRequest categoryRequest);
}
