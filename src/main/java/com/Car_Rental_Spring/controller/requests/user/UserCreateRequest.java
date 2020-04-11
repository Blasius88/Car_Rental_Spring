package com.Car_Rental_Spring.controller.requests.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicUpdate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@DynamicUpdate
@Builder
public class UserCreateRequest {

    @NotNull
    @Size(min = 1, max = 100)
    private String userFirstName;

    @Size(min = 1, max = 100)
    private String userLastName;

    @Size(min = 1, max = 100)
    private String login;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private Date Created;

    @NotNull
    private Long IdRole;

    @Email
    private String Email;

    @Size (min = 9)
    private String Phone;

    @NotNull
    private String City;
}
