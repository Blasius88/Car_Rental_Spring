package com.Car_Rental_Spring.domain;

import lombok.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String userLogin;
    private String userPass;
    private Date userCreated;
    private Long idRole;
    private String userEmail;
    private String userPhone;
    private String userCity;
}