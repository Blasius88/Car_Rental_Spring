package com.Car_Rental.controller.requests.authentication;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor

@ApiModel(description = "Object for user authentication")
public class AuthenticationRequest implements Serializable {

    @ApiModelProperty(required = true, dataType = "string", notes = "user login")
    private String login;

    @ApiModelProperty(required = true, dataType = "string", notes = "user password")
    private String password;
}
