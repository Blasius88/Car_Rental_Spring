package com.Car_Rental_Spring.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Date;
import java.util.Objects;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String pass;
    private Date created;
    private Long id_role;
    private String email;
    private String phone;
    private String city;

    public User() {
    }

    public User(Long id, String firstName, String lastName, String login, String pass, Date created, Long id_role, String email, String phone, String city) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.pass = pass;
        this.created = created;
        this.id_role = id_role;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public Date getCreated() {
        return created;
    }

    public Long getId_role() {
        return id_role;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(login, user.login) &&
                Objects.equals(pass, user.pass) &&
                Objects.equals(created, user.created) &&
                Objects.equals(id_role, user.id_role) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(city, user.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, login, pass, created, id_role, email, phone, city);
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