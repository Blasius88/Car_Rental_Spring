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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User orderUserId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_car")
    private Car_Model orderCarId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_worker")
    private WorkerUser orderWorkerId;

    @Column(name = "rental_start")
    private String rentalStart;

    @Column(name = "rental_end")
    private String rentalEnd;

}
