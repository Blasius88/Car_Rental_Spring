package com.Car_Rental_Spring.domain.hibernate;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collections;
import java.util.Set;

@Entity
@Table(name = "m_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TestUser {

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
    private String pass;

    @Column(name = "created")
    private Date created;

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column (name = "city")
    private String city;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "m_user")
    @JoinColumn(name = "m_roles_id")
    private Set<HibernateRole> roles = Collections.emptySet();
    ;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "m_user",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "m_roles_id")
    )
    private Set<HibernateProfession> professions = Collections.emptySet();

}
