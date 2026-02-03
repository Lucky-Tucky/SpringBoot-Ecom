package com.lucky.springEcom.Repository;

import com.lucky.springEcom.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order , Integer> {

    Optional<Order> findByOrderId(String orderId);
}
