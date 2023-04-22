package com.flutter.sneaker.head.service.size;

import com.flutter.sneaker.head.infra.entity.SizeEntity;
import com.flutter.sneaker.head.infra.repo.SizeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService{

    private final SizeRepository sizeRepository;

    @Override
    public Integer getSizeBySizeId(String sizeId) {
        return sizeRepository.findBySizeId(sizeId)
                .orElse(new SizeEntity())
                .getSize();
    }
}