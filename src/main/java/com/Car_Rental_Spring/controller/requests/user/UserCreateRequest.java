package com.Car_Rental_Spring.controller.requests.user;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@EqualsAndHashCode
@ToString
public class UserCreateRequest {

    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    private String userFirstName;

    @Size(min = 1, max = 100)
    private String userLastName;

    @Size(min = 1, max = 100)
    private String login;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 20)
    private String password;

    private Date Created;

    @NotNull
    @NotEmpty
    private Long IdRole;

    @Email
    private String Email;

    @Size (min = 9)
    private String Phone;

    @NotNull
    @NotEmpty
    private String City;
}
