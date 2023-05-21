package com.flutter.sneaker.head.service.order;

import com.flutter.sneaker.head.controller.order.OrderRequest;
import com.flutter.sneaker.head.controller.order.OrderResponse;

import java.util.List;

public interface OrderService {

    String placeOrder(OrderRequest orderRequest);

    OrderResponse updateStatus(String orderId, String status);

    OrderResponse getOrderDetailByOrderId(String orderId);

    List<OrderResponse> getAll();
}
