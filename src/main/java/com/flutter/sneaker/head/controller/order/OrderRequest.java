package com.flutter.sneaker.head.controller.order;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@Builder
public class OrderRequest {

    @Nullable
    private String accountNumber;

    @Nullable
    private String customerName;

    @Nullable
    private String customerAddress;

    @Nullable
    private String customerEmail;

    @Nullable
    private String customerPhone;

    private double totalPrice;

    private List<OrderDetailRequest> orderDetailRequests;
}
