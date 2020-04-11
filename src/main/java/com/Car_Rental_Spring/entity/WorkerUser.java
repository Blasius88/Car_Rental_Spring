package com.Car_Rental_Spring.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"id_worker", "id_user"})
@ToString(exclude = {"id_worker", "id_user"})
@Entity
@Table(name = "worker")
public class WorkerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id_worker;

    @Column
    private String nameWork;

    @JsonBackReference
    @ManyToOne (fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn (nullable = true, name = "id_user")
    User id_user;

    @Column
    private double percentage_of_salary;

    @Column
    private double salary;
}