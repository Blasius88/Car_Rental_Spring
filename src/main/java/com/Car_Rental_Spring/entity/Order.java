package com.Car_Rental_Spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"orderId","orderUserId", "orderCarId", "orderWorkerId"})
@ToString(exclude = {"orderId","orderUserId", "orderCarId", "orderWorkerId"})
@Entity
@Table(name = "m_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @OneToOne (fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User orderUserId;

    @OneToOne (fetch = FetchType.EAGER, targetEntity = Car_Model.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private Car_Model orderCarId;

    @OneToOne(fetch = FetchType.EAGER, targetEntity = WorkerUser.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_worker")
    private WorkerUser orderWorkerId;

    @Column(name = "rental_start")
    private Timestamp rentalStart;

    @Column(name = "rental_end")
    private Timestamp rentalEnd;

    @Column(name = "order_price")
    private Double orderPrice;
}
