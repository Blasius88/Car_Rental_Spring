package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long > {
    List<User> findCityUser(String str);
    User findLoginAndPass (String str);
}
