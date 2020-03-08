package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long > {
    List<User> findCityUser(String str);
    User findByLogin(String login);
    User findLoginAndPass (String str);
    List<User> search(String str, Integer limit, Integer offset);
}
