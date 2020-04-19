package com.Car_Rental.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {
        "userId"
})
@ToString(exclude = {
        "role"
})
@Entity
@Table(name = "m_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "login")
    private String login;

    @Column(name = "pass")
    private String password;

    @Column(name = "created")
    private Date userCreated;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "phone")
    private String userPhone;

    @Column (name ="city")
    private String userCity;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @JsonManagedReference
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Roles.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_role")
    private Roles role;

}