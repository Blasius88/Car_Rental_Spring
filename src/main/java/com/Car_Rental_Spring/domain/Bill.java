package com.Car_Rental_Spring.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode()
@ToString()
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_bill;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Order id_order;

    @Column
    private boolean status;


}