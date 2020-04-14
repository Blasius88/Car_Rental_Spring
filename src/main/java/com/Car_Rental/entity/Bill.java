package com.Car_Rental.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(
        exclude = {
                "idBill",
                "idOrder"
        })
@ToString(
        exclude = {
                "idBill",
                "idOrder"
        })
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idBill;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Order.class, cascade = CascadeType.ALL)
    @JoinColumn (name = "id_order")
    private Order idOrder;

    @Column
    private boolean status;


}