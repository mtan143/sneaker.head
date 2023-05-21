package com.flutter.sneaker.head.controller.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

    private String orderId;
    private String accountNumber;
    private String customerName;
    private String address;
    private String email;
    private String cellphone;
    private double totalPrice;
    private String status;
    private List<OrderDetailResponse> orderDetails;
    private LocalDateTime createdDate;
}
