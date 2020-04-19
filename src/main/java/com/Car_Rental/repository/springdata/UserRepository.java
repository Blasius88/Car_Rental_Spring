package com.Car_Rental.repository.springdata;

import com.Car_Rental.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    Optional<User> findUserByLogin(String login);

    User findUserByConfirmationToken(String token);


    @Modifying
    @Query("select u.userId from User u where u.userId =:id")
    User findByIdUser (Long id);
}