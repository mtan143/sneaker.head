package com.flutter.sneaker.head.service.size;

import com.flutter.sneaker.head.infra.entity.ProductSizeEntity;
import com.flutter.sneaker.head.infra.entity.SizeEntity;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.ProductSizeRepository;
import com.flutter.sneaker.head.infra.repo.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService{

    private final SizeRepository sizeRepository;

    private final ProductSizeRepository productSizeRepository;

    @Override
    public Integer getSizeBySizeId(String sizeId) {
        return sizeRepository.findBySizeId(sizeId)
                .orElse(new SizeEntity())
                .getSize();
    }

    @Override
    public String getSizeIdBySize(Integer size) {
        return sizeRepository.findBySize(size)
                .orElse(new SizeEntity())
                .getSizeId();
    }

    @Override
    public ProductSizeEntity findByProductIdAndSizeId(String productId, String sizeId) {
        return productSizeRepository.findByProductIdAndSizeId(productId, sizeId)
                .orElseThrow(() -> new DomainException(DomainErrorCode.PRODUCT_SIZE_NOT_FOUND));
    }

    @Override
    public ProductSizeEntity saveProductSize(ProductSizeEntity productSizeEntity) {
        return productSizeRepository.save(productSizeEntity);
    }
}