package com.lucky.springEcom.Models.dto;

import lombok.Builder;

@Builder
public record OrderItemResponse(String productName , int quantity , Double totalPrice) {
}
