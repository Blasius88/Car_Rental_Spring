package com.Car_Rental_Spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id_bill", "id_order"})
@ToString(exclude = {"id_bill", "id_order"})
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_bill;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "id_order")
    private Order id_order;

    @Column
    private boolean status;


}