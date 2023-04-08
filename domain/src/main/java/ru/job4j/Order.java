package ru.job4j;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ElementCollection
    @CollectionTable(name = "order_dishes",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "dish_id")
    @Column(name = "quantity")
    private Map<Integer, Integer> dishes;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private int paymentId;
    private int customerId;
}
