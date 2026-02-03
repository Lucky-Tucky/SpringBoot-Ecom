package com.lucky.springEcom.Models;

import com.lucky.springEcom.Enum.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Builder
@Table(name = "orders")
public class Order {

    @Id
    private int id;

    @Column(unique = true)
    private String orderId;

    private String customerName;

    private String email;

    private Status status;

    private LocalDate orderDate;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

}
