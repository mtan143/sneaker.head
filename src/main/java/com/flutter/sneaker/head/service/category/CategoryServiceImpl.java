package com.flutter.sneaker.head.service.category;

import com.flutter.sneaker.head.controller.category.CategoryResponse;
import com.flutter.sneaker.head.infra.entity.CategoryEntity;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.CategoryRepository;
import com.flutter.sneaker.head.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final ProductService productService;

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryResponse> categoryResponses = categoryRepository.findAll()
                .stream()
                .map(CategoryResponse::fromEntity)
                .collect(Collectors.toList());

        categoryResponses.forEach(category -> category.setTotalProduct(productService.countByCategoryId(category.getCategoryId())));

        return categoryResponses;
    }

    @Override
    public CategoryResponse findByCategoryId(String categoryId) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryId)
                .orElseThrow(() -> new DomainException(DomainErrorCode.CATEGORY_NOT_FOUND));
        CategoryResponse categoryResponse = CategoryResponse.fromEntity(categoryEntity);
        categoryResponse.setTotalProduct(productService.countByCategoryId(categoryEntity.getCategoryId()));
        return categoryResponse;
    }
}
