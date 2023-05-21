package com.flutter.sneaker.head.controller.size;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SizeRequest {
    private Integer size;
    private Integer amount;
}
