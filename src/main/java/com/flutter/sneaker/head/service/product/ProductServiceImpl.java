package com.flutter.sneaker.head.service.product;

import com.flutter.sneaker.head.controller.product.ProductRequest;
import com.flutter.sneaker.head.controller.product.ProductResponse;
import com.flutter.sneaker.head.controller.size.ProductSizeResponse;
import com.flutter.sneaker.head.infra.entity.ProductEntity;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.ProductRepository;
import com.flutter.sneaker.head.infra.repo.ProductSizeRepository;
import com.flutter.sneaker.head.service.size.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductSizeRepository productSizeRepository;

    private final SizeService sizeService;

    @Override
    public List<ProductResponse> findByCategoryId(String categoryId, Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<ProductResponse> productResponses = productRepository.findByCategoryId(categoryId, paging).stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());

        productResponses.forEach(productResponse -> productResponse
                .setSizes(getProductSizeResponses(productResponse.getProductId())));
        return productResponses;
    }

    @Override
    public List<ProductResponse> findAll(Integer pageNo, Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        List<ProductResponse> products = productRepository.findAll(paging)
                .stream()
                .map(ProductResponse::fromEntity)
                .collect(Collectors.toList());
        products.forEach(productResponse -> productResponse
                .setSizes(getProductSizeResponses(productResponse.getProductId())));
        return products;
    }

    @Override
    public ProductResponse getProductDetail(String productId) {
        ProductEntity productEntity = productRepository.findByProductId(productId)
                .orElseThrow(() -> new DomainException(DomainErrorCode.PRODUCT_NOT_FOUND));

        return ProductResponse.builder()
                .productId(productEntity.getProductId())
                .categoryId(productEntity.getCategoryId())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .availableQuantity(productEntity.getAvailableQuantity())
                .description(productEntity.getDescription())
                .url(productEntity.getUrl())
                .createdDate(productEntity.getCreatedDate())
                .sizes(getProductSizeResponses(productId))
                .build();
    }

    @Override
    public long countByCategoryId(String categoryId) {
        return productRepository.countByCategoryId(categoryId);
    }

    @Override
    public ProductResponse upsertProduct(ProductRequest productRequest) {
        ProductEntity product = productRepository.findByProductId(productRequest.getProductId())
                .orElse(new ProductEntity());

        if (Objects.isNull(product.getProductId())) {
            product.setProductId(UUID.randomUUID().toString());
            product.setCategoryId(productRequest.getCategoryId());
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            product.setQuantity(productRequest.getQuantity());
            product.setAvailableQuantity(productRequest.getQuantity());
            product.setDescription(productRequest.getDescription());
            product.setUrl(productRequest.getUrl());
            product.setCreatedDate(LocalDateTime.now());
        } else {
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            product.setAvailableQuantity(productRequest.getAvailableQuantity());
            product.setQuantity(productRequest.getQuantity());
            product.setDescription(productRequest.getDescription());
            product.setUrl(productRequest.getUrl());
        }
        product.setLastModifiedDate(LocalDateTime.now());

        return ProductResponse.fromEntity(productRepository.save(product));
    }

    @Override
    public ProductResponse toggleProduct(ProductRequest productRequest) {
        return null;
    }

    private List<ProductSizeResponse> getProductSizeResponses(String productId) {
        return productSizeRepository.findAllByProductId(productId)
                .stream()
                .map(productSize -> ProductSizeResponse.builder()
                        .size(sizeService.getSizeBySizeId(productSize.getSizeId()))
                        .availableQuantity(productSize.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }
}