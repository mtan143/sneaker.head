package com.flutter.sneaker.head.service.orderdetail;

import com.flutter.sneaker.head.infra.entity.OrderDetailEntity;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetailEntity> findByOrderId(String orderId);

    OrderDetailEntity saveOrderDetail(OrderDetailEntity orderDetailEntity);
}
