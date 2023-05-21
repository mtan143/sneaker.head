package com.flutter.sneaker.head.controller.order;

import com.flutter.sneaker.head.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(orderRequest);
    }

    @PostMapping("/status")
    public OrderResponse updateOrderStatus(@RequestParam String orderId, @RequestParam String orderStatus) {
        return orderService.updateStatus(orderId, orderStatus);
    }

    @GetMapping
    public OrderResponse getOrderDetail(@RequestParam String orderId) {
        return orderService.getOrderDetailByOrderId(orderId);
    }

    @GetMapping("/list")
    public List<OrderResponse> getAll() {
        return orderService.getAll();
    }
}
