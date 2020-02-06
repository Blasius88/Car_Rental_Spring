package com.Car_Rental_Spring.repositiry;

import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repositiry.impl.UserDaoImpl;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {
        List <Long> findOne (List<User> users);
}
