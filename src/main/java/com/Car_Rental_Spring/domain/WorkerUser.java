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
@Table(name = "worker")
public class WorkerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_worker;

    @Column
    private String nameWork;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User id_user;

    @Column
    private double percentage_of_salary;

    @Column
    private double salary;
}