package com.lucky.springEcom.Services;

import com.lucky.springEcom.Enum.Status;
import com.lucky.springEcom.Models.Order;
import com.lucky.springEcom.Models.OrderItem;
import com.lucky.springEcom.Models.Product;
import com.lucky.springEcom.Models.dto.OrderItemResponse;
import com.lucky.springEcom.Models.dto.OrderRequest;
import com.lucky.springEcom.Models.dto.OrderResponse;
import com.lucky.springEcom.Repository.OrderRepository;
import com.lucky.springEcom.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public OrderResponse placeOrder(OrderRequest request){

        Order order = new Order();

        order.setOrderId(UUID.randomUUID().toString());
        order.setCustomerName(request.customerName());
        order.setOrderDate(LocalDate.now());
        order.setStatus(Status.PLACED);
        order.setEmail(request.email());

        List<OrderItem> orderItems = new ArrayList<>();

        request.items().forEach((item)->{

           Product product = productRepository.findById(item.productId())
                   .orElseThrow(RuntimeException::new);

           int quantity = item.quantity();

           if(product.getStockQuantity()-quantity>0){
                product.setStockQuantity(product.getStockQuantity()-quantity);
           }else{
               throw new RuntimeException("Stock Quantity is not Valid!");
           }

           double price = item.price() * quantity;

           OrderItem orderItem = OrderItem.builder()
                   .product(product)
                   .totalPrices(price)
                   .quantity(quantity)
                   .order(order)
                   .build();

           orderItems.add(orderItem);

        });

        order.setOrderItemList(orderItems);

        Order saved_order = orderRepository.save(order);

        return MaptoOrderResponse(saved_order);

    }

    public List<OrderItemResponse> getAllOrders() {
        return List.of(null);
    }


    public OrderResponse MaptoOrderResponse(Order order){

        return OrderResponse.builder()
                .costumerName(order.getCustomerName())
                .orderID(String.valueOf(order.getId()))
                .orderDate(order.getOrderDate())
                .email(order.getEmail())
                .status(order.getStatus().toString())
                .items(order.getOrderItemList().stream()
                        .map(this::MapToOrderItemResponse)
                        .toList())
                .build();

    }

    public OrderItemResponse MapToOrderItemResponse(OrderItem orderItem){
        return OrderItemResponse.builder()
                .productName(orderItem.getProduct().getName())
                .totalPrice(orderItem.getTotalPrices())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
