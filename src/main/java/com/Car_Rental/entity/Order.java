package com.Car_Rental.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import lombok.*;
import org.hibernate.engine.internal.Collections;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "m_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long orderId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    @JsonIdentityReference(alwaysAsId = true)
    private User orderUserId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_car")
    private CarModel orderCarId;

    @Column(name = "rental_start")
    private Date rentalStart;

    @Column(name = "rental_start_time")
    private String rentalStartTime;

    @Column(name = "rental_end_time")
    private String rentalEndTime;

    @Column(name = "rental_end")
    private Date rentalEnd;

    @Column(name = "order_price")
    private Double orderPrice;
}
