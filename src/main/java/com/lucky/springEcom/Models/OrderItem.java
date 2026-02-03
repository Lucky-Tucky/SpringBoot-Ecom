package com.lucky.springEcom.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    public Product product;

    private int quantity;

    private double totalPrices;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
