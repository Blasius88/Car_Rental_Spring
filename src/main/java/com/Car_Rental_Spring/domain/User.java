package com.Car_Rental_Spring.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Date;
import java.util.Objects;

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

    public User() {
    }

    public User(String firstName, String lastName, String userLogin, String userPass, Date userCreated, Long idRole, String userEmail, String userPhone, String userCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userLogin = userLogin;
        this.userPass = userPass;
        this.userCreated = userCreated;
        this.idRole = idRole;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.userCity = userCity;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public void setUserCreated(Date userCreated) {
        this.userCreated = userCreated;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public Date getUserCreated() {
        return userCreated;
    }

    public Long getIdRole() {
        return idRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserCity() {
        return userCity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userLogin, user.userLogin) &&
                Objects.equals(userPass, user.userPass) &&
                Objects.equals(userCreated, user.userCreated) &&
                Objects.equals(idRole, user.idRole) &&
                Objects.equals(userEmail, user.userEmail) &&
                Objects.equals(userPhone, user.userPhone) &&
                Objects.equals(userCity, user.userCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, userLogin, userPass, userCreated, idRole, userEmail, userPhone, userCity);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


    @PostConstruct
    public void init() {
        System.out.println("Init method!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy!");
    }
}