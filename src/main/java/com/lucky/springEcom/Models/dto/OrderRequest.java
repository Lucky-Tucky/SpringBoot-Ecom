package com.lucky.springEcom.Models.dto;

import java.util.List;


public record OrderRequest(String customerName , String email , List<OrderItemRequest> items) {
    @Override
    public String customerName() {
        return customerName;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public List<OrderItemRequest> items() {
        return items;
    }
}
