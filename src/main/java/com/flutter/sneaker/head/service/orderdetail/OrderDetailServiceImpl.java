package com.flutter.sneaker.head.service.orderdetail;

import com.flutter.sneaker.head.infra.entity.OrderDetailEntity;
import com.flutter.sneaker.head.infra.repo.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService{

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailEntity> findByOrderId(String orderId) {
        return orderDetailRepository.findAllByOrderId(orderId);
    }

    @Override
    public OrderDetailEntity saveOrderDetail(OrderDetailEntity orderDetailEntity) {
        return orderDetailRepository.save(orderDetailEntity);
    }
}
