package com.flutter.sneaker.head.service.size;

import com.flutter.sneaker.head.infra.entity.ProductSizeEntity;

public interface SizeService {

    Integer getSizeBySizeId(String sizeId);

    String getSizeIdBySize(Integer size);

    ProductSizeEntity findByProductIdAndSizeId(String productId, String sizeId);

    ProductSizeEntity saveProductSize(ProductSizeEntity productSizeEntity);
}
