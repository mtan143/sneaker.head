package com.flutter.sneaker.head.controller.category;

import com.flutter.sneaker.head.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse findByCategoryId(@PathVariable String categoryId) {
        return categoryService.findByCategoryId(categoryId);
    }
}
