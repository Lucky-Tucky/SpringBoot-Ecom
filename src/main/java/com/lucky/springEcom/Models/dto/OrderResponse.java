package com.lucky.springEcom.Models.dto;

import java.time.LocalDate;
import java.util.List;

public record OrderResponse(String orderID , String costumerName , String email , String status
        , LocalDate orderDate , List<OrderItemResponse> items) {
}
