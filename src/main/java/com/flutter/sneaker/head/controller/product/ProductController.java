package com.flutter.sneaker.head.controller.product;

import com.flutter.sneaker.head.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> findByCategoryId(@PathVariable String categoryId,
                                                  @RequestParam Integer pageNo,
                                                  @RequestParam Integer pageSize) {
        return productService.findByCategoryId(categoryId, pageNo, pageSize);
    }

    @GetMapping("/product/{productId}")
    public ProductResponse getProductDetail(@PathVariable String productId) {
        return productService.getProductDetail(productId);
    }

    @GetMapping
    public List<ProductResponse> getProducts(@RequestParam Integer pageNo,
                                             @RequestParam Integer pageSize) {
        return productService.findAll(pageNo, pageSize);
    }

    @PostMapping
    public ProductResponse upsert(@RequestBody ProductRequest productRequest) {
        return productService.upsertProduct(productRequest);
    }
}