package com.flutter.sneaker.head.service.category;

import com.flutter.sneaker.head.controller.category.CategoryRequest;
import com.flutter.sneaker.head.controller.category.CategoryResponse;
import com.flutter.sneaker.head.infra.entity.CategoryEntity;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.CategoryRepository;
import com.flutter.sneaker.head.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
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

    @Override
    public CategoryResponse upsert(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(categoryRequest.getCategoryId())
                .orElse(new CategoryEntity());

        if (Objects.isNull(categoryEntity.getCategoryId())) {
            categoryEntity.setCategoryId(UUID.randomUUID().toString());
            categoryEntity.setCategoryName(categoryRequest.getCategoryName());
            categoryEntity.setQuantityProduct(categoryRequest.getTotalProduct());
            categoryEntity.setCreatedDate(LocalDateTime.now());
        } else {
            categoryEntity.setCategoryName(categoryRequest.getCategoryName());
            categoryEntity.setQuantityProduct(categoryRequest.getTotalProduct());
        }
        categoryEntity.setLastModifiedDate(LocalDateTime.now());

        return CategoryResponse.fromEntity(categoryRepository.save(categoryEntity));
    }

    @Override
    public CategoryResponse toggleCategory(CategoryRequest categoryRequest) {
        return null;
    }
}
