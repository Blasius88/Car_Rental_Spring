package com.Car_Rental.config.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("token")
@Getter
@Setter
public class TokenConfiguration {

    private String sshSecret;

    private String expirationTime;
}
