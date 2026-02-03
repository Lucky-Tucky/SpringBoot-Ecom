package com.lucky.springEcom.Models.dto;

public record OrderItemRequest(int productId, int quantity , double price) {
    @Override
    public int productId() {
        return productId;
    }

    @Override
    public int quantity() {
        return quantity;
    }

    @Override
    public double price() {
        return price;
    }
}
