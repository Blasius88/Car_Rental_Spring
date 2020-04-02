package com.Car_Rental_Spring.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.Date;

@Data
@Setter
@Getter
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {
        "userId", "roles", "professions"
})
@ToString(exclude = {
        "roles", "professions"
})
@NamedQuery(name = "m_users_multiple_ids_search", query = "select tu from User tu where tu.userId in (:userIds)")
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
    private String userLogin;

    @Column(name = "pass")
    private String userPass;

    @Column(name = "created")
    private Date userCreated;

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "phone")
    private String userPhone;

    @Column (name ="city")
    private String userCity;

    @JsonManagedReference
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private MRoles role;

}