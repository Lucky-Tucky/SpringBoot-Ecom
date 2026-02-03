package com.lucky.springEcom.Controllers;

import com.lucky.springEcom.Models.dto.OrderItemRequest;
import com.lucky.springEcom.Models.dto.OrderItemResponse;
import com.lucky.springEcom.Models.dto.OrderRequest;
import com.lucky.springEcom.Models.dto.OrderResponse;
import com.lucky.springEcom.Services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OrderController {

    private OrderService orderService;

    @PostMapping("/order/place")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest){

        OrderResponse orderResponse = orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderItemResponse>> getAllOrders(){

         List<OrderItemResponse> orderItemResponse = orderService.getAllOrders();
         return new ResponseEntity<>(orderItemResponse , HttpStatus.OK);
    }
}
