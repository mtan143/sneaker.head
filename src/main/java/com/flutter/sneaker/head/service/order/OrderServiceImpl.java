package com.flutter.sneaker.head.service.order;

import com.flutter.sneaker.head.controller.order.OrderDetailRequest;
import com.flutter.sneaker.head.controller.order.OrderDetailResponse;
import com.flutter.sneaker.head.controller.order.OrderRequest;
import com.flutter.sneaker.head.controller.order.OrderResponse;
import com.flutter.sneaker.head.infra.entity.*;
import com.flutter.sneaker.head.infra.enumeration.OrderStatus;
import com.flutter.sneaker.head.infra.exception.DomainErrorCode;
import com.flutter.sneaker.head.infra.exception.DomainException;
import com.flutter.sneaker.head.infra.repo.OrderDetailRepository;
import com.flutter.sneaker.head.infra.repo.OrderRepository;
import com.flutter.sneaker.head.service.account.AccountService;
import com.flutter.sneaker.head.service.orderdetail.OrderDetailService;
import com.flutter.sneaker.head.service.product.ProductService;
import com.flutter.sneaker.head.service.size.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final OrderDetailService orderDetailService;

    private final AccountService accountService;

    private final ProductService productService;

    private final SizeService sizeService;

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public String placeOrder(OrderRequest orderRequest) {

        OrderEntity orderEntity;

        //check account
        if (!Objects.isNull(orderRequest.getAccountNumber())) {
            accountService.checkAccountEligibility(orderRequest.getAccountNumber());

            AccountEntity accountEntity = accountService.findByAccountNumber(orderRequest.getAccountNumber());
            orderEntity = OrderEntity.builder()
                    .orderId(UUID.randomUUID().toString())
                    .accountNumber(orderRequest.getAccountNumber())
                    .totalPrice(orderRequest.getTotalPrice())
                    .status(OrderStatus.CONFIRMING)
                    .customerName(accountEntity.getAccountName())
                    .address(accountEntity.getAddress())
                    .email(accountEntity.getEmail())
                    .cellphone(accountEntity.getCellphone())
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
        } else {
            orderEntity = OrderEntity.builder()
                    .orderId(UUID.randomUUID().toString())
                    .accountNumber(orderRequest.getAccountNumber())
                    .totalPrice(orderRequest.getTotalPrice())
                    .status(OrderStatus.CONFIRMING)
                    .customerName(orderRequest.getCustomerName())
                    .address(orderRequest.getCustomerAddress())
                    .email(orderRequest.getCustomerEmail())
                    .cellphone(orderRequest.getCustomerPhone())
                    .createdDate(LocalDateTime.now())
                    .lastModifiedDate(LocalDateTime.now())
                    .build();
        }

        if (Objects.isNull(orderRequest.getAccountNumber()) && Objects.isNull(orderRequest.getCustomerEmail())) {
            throw new DomainException(DomainErrorCode.ORDER_INFO_MISSING);
        }

        //check product amount
        checkProductAmount(orderRequest.getOrderDetailRequests());

        //check total price matching
        checkTotalPrice(orderRequest.getOrderDetailRequests(), orderRequest.getTotalPrice());

        //insert order entity
        OrderEntity order = null;
        try {
            order = orderRepository.save(orderEntity);
        } catch (Exception e) {
            log.error("error when save order entity", e);
        }

        //insert order detail
        orderRequest.getOrderDetailRequests().forEach(
                orderDetail -> {
                    String sizeId = sizeService.getSizeIdBySize(orderDetail.getSize());
                    ProductEntity productEntity = productService.findByProductId(orderDetail.getProductId());
                    ProductSizeEntity productSizeEntity = sizeService.findByProductIdAndSizeId(orderDetail.getProductId(), sizeId);
                    OrderDetailEntity orderDetailEntity = OrderDetailEntity.builder()
                            .orderDetailId(UUID.randomUUID().toString())
                            .orderId(orderEntity.getOrderId())
                            .productId(orderDetail.getProductId())
                            .productSizeId(productSizeEntity.getProductSizeId())
                            .quantity(orderDetail.getQuantity())
                            .price(productEntity.getPrice())
                            .createdDate(LocalDateTime.now())
                            .lastModifiedDate(LocalDateTime.now())
                            .build();
                    try {
                        orderDetailRepository.save(orderDetailEntity);
                    } catch (Exception exception) {
                        log.error("error when save order detail", exception);
                    }
                }
        );

        return Objects.isNull(order) ? "Have a problem when place an order" : order.getOrderId();
    }

    @Override
    public OrderResponse updateStatus(String orderId, String status) {

        OrderEntity orderEntity = this.findByOrderId(orderId);
        List<OrderDetailEntity> orderDetailEntityList = orderDetailService.findByOrderId(orderEntity.getOrderId());

        // TODO: if success, update product amount
        if (OrderStatus.valueOf(status.toUpperCase()).equals(OrderStatus.FINISHED)) {

            //update product size amount
            try {
                orderDetailEntityList.forEach(orderDetail -> {
                    ProductSizeEntity productSizeEntity = productService.findByProductSizeId(orderDetail.getProductSizeId());
                    Integer quantity = productSizeEntity.getQuantity();
                    productSizeEntity.setQuantity(quantity - orderDetail.getQuantity());
                    sizeService.saveProductSize(productSizeEntity);

                    //update product total quantity
                    productService.updateAvailableQuantity(orderDetail.getProductId(), orderDetail.getQuantity());
                });
            } catch (Exception exception) {
                log.error("error when update amount", exception);
            }
        }

        //TODO: update order status
        updateOrderStatus(orderEntity, status);
        return getOrderDetailByOrderId(orderId);
    }

    @Override
    public OrderResponse getOrderDetailByOrderId(String orderId) {

        OrderEntity orderEntity = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new DomainException(DomainErrorCode.ORDER_NOT_FOUND));

        List<OrderDetailResponse> orderDetails = orderDetailService.findByOrderId(orderEntity.getOrderId())
            .stream().map(orderDetailEntity -> {
                    ProductSizeEntity productSizeEntity = productService.findByProductSizeId(orderDetailEntity.getProductSizeId());
                    ProductEntity productEntity = productService.findByProductId(productSizeEntity.getProductId());
                    Integer size = sizeService.getSizeBySizeId(productSizeEntity.getSizeId());
                    return OrderDetailResponse.builder()
                        .price(orderDetailEntity.getPrice())
                        .quantity(orderDetailEntity.getQuantity())
                        .productName(productEntity.getName())
                        .size(size)
                        .build();
                }
            )
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .orderId(orderEntity.getOrderId())
                .accountNumber(StringUtils.defaultString(orderEntity.getAccountNumber(), StringUtils.EMPTY))
                .customerName(orderEntity.getCustomerName())
                .address(orderEntity.getAddress())
                .email(orderEntity.getEmail())
                .cellphone(orderEntity.getCellphone())
                .totalPrice(orderEntity.getTotalPrice())
                .status(orderEntity.getStatus().name())
                .createdDate(orderEntity.getCreatedDate())
                .orderDetails(orderDetails)
                .build();
    }

    @Override
    public List<OrderResponse> getAll() {
        List<OrderResponse> orderResponses = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orderEntity -> orderResponses
                        .add(getOrderDetailByOrderId(orderEntity.getOrderId())));

        return orderResponses;
    }

    private void checkProductAmount(List<OrderDetailRequest> orderDetailRequests) {
        orderDetailRequests.forEach(
                product -> {
                    String sizeId = sizeService.getSizeIdBySize(product.getSize());
                    productService.checkProductAmount(product.getProductId(), sizeId, product.getQuantity());
                }
        );
    }

    private OrderEntity findByOrderId(String orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new DomainException(DomainErrorCode.ORDER_NOT_FOUND));
    }

    private void checkTotalPrice(List<OrderDetailRequest> orderDetailRequests, double totalPrice) {

        Double actualPrice = orderDetailRequests.stream()
                .map(orderDetailRequest -> productService.getProductPrice(orderDetailRequest.getProductId(), orderDetailRequest.getQuantity()))
                .reduce(0.0, Double::sum);

        if (Double.compare(actualPrice, totalPrice) != 0) {
            throw new DomainException(DomainErrorCode.TOTAL_PRICE_NOT_MATCH);
        }
    }

    private void updateOrderStatus(OrderEntity order, String status) {
        try {
            order.setStatus(OrderStatus.valueOf(StringUtils.upperCase(status)));
            order.setLastModifiedDate(LocalDateTime.now());
            orderRepository.save(order);
        } catch (Exception exception) {
            log.error("error when update order status", exception);
        }
    }
}
