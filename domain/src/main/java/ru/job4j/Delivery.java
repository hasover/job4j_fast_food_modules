package ru.job4j;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "deliveries")
@Data
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int orderId;
    private String address;
    private String email;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private int deliveryDriverId;
}
