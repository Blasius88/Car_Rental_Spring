package com.Car_Rental_Spring.confing.core;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Configuration
@ConfigurationProperties("datasource")
public class DatabaseConfig {

    @Value("${driverName}")
    private String driverName;

    @Value("${url}")
    private String url;

    @Value("${login}")
    private String login;

    @Value("${password")
    private String password;

    @Value("${initialSize}")
    private String initialSize;

    @Value("{maxActive}")
    private String maxActive;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setInitialSize(Integer.valueOf(Objects.requireNonNull(initialSize)));
        dataSource.setUsername(login);
        dataSource.setMaxActive(Integer.valueOf(Objects.requireNonNull(maxActive)));
        return dataSource;
    }
}
