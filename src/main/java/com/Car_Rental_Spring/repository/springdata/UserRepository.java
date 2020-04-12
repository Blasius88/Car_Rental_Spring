package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    @Query("select u.userId from User u where u.userId =:id")
    User findByIdUser (Long id);
}