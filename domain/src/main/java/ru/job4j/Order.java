package ru.job4j;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ElementCollection
    @CollectionTable(name = "order_dishes", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "dish_id")
    private List<Integer> dishIds;
    private String status;
    private int paymentId;
    private int kitchenId;
    private int deliveryId;
}
