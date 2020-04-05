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

    @Column
    private Long id_order;

    @Column
    private boolean status;
//
//    @JsonManagedReference
//    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
//    private Order order;
}