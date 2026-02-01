package com.lucky.springEcom.Models.dto;

public record OrderItemRequest(long productId, int quantity , double price) {
}
