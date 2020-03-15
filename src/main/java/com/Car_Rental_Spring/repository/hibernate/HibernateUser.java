package com.Car_Rental_Spring.repository.hibernate;

import com.Car_Rental_Spring.domain.hibernate.TestUser;
import com.Car_Rental_Spring.repository.GenericDao;

public interface HibernateUser extends GenericDao<TestUser, Long> {
}
