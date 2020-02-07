package com.Car_Rental_Spring.repository;

import com.Car_Rental_Spring.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {
        User findOne (Long Id);
}
