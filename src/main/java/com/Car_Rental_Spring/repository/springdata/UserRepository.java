package com.Car_Rental_Spring.repository.springdata;

import com.Car_Rental_Spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRepository extends CrudRepository<User, Long>, JpaRepository<User, Long> {

    @Query(value = "select u from User u", countQuery = "select count(u) from User u")
    List<User> qweqweqweqw();

    @Query(value = "select u.* from m_users u", nativeQuery = true)
    List<User> nativeQuery();

    @Query("select u from User u where u.userId > :lowerId")
    List<User> qweqweqweqw1(String lowerId);

    @Query("select u.userId, r.Name_roles from User u join MRoles r on u.userId = r.user.userId where u.userId > :lowerId")
    List<Object[]> qweqweqweqw2(@Param("lowerId") String qweqwe);

    @Query("select u.userId from User u where u.userId > :lowerId")
    List<Object[]> qweqweqweqw4(@Param("lowerId") String qweqwe);

    @Query("select u.userId,  r.Name_roles from User u, MRoles r where u.userId > :lowerId")
    List<Object[]> qweqweqweqw5(@Param("lowerId") String qweqwe);


}