package com.Car_Rental.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"idWorker", "idUser"})
@ToString(exclude = {"idWorker", "idUser"})
@Entity
@Table(name = "worker")
public class WorkerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idWorker;

    @JsonBackReference
    @ManyToOne (fetch = FetchType.EAGER, targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn (nullable = true, name = "id_user")
    User idUser;

    @Column(name ="percentage_of_salary")
    private double percentageOfSalary;

}