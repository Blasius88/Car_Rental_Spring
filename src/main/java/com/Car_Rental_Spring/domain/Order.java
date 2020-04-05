package com.Car_Rental_Spring.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode()
@ToString()
@Entity
@Table(name = "m_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @Column(name = "id_user")
    private Long orderUserId;

    @Column(name = "id_car")
    private Long orderCarId;

    @Column(name = "id_worker")
    private Long orderWorkerId;

    @Column(name = "rental_start")
    private String rentalStart;

    @Column(name = "rental_end")
    private String rentalEnd;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id")
//    @JsonBackReference
//    private Bill bill;
}
