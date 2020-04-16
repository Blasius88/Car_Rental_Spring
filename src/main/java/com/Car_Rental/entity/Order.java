package com.Car_Rental.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(
        exclude = {
                "orderId",
                "orderUserId",
                "orderCarId",
        })
@ToString(
        exclude = {
                "orderId",
                "orderUserId",
                "orderCarId",
        })
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

    @OneToOne (fetch = FetchType.EAGER, targetEntity = CarModel.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car")
    private CarModel orderCarId;

       @Column(name = "rental_start")
    private Date rentalStart;

    @Column (name = "rental_start_time")
    private String rentalStartTime;


    @Column (name = "rental_end_time")
    private String rentalEndTime;

    @Column(name = "rental_end")
    private Date rentalEnd;

    @Column(name = "order_price")
    private Double orderPrice;
}
