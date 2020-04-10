package com.Car_Rental_Spring.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"orderId","orderUserId", "orderCarId", "orderWorkerId"})
@ToString(exclude = {"orderId","orderUserId", "orderCarId", "orderWorkerId"})
@Entity
@Table(name = "m_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int orderId;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User orderUserId;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car")
    private Car_Model orderCarId;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_worker")
    private WorkerUser orderWorkerId;

    @Column(name = "rental_start")
    private String rentalStart;

    @Column(name = "rental_end")
    private String rentalEnd;
}
