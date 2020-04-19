package com.Car_Rental.service;

import com.Car_Rental.controller.requests.authentication.UserPrincipal;
import com.Car_Rental.entity.Roles;
import com.Car_Rental.entity.User;
import com.Car_Rental.repository.hibernate.RoleDao;
import com.Car_Rental.repository.hibernate.UserDao;
import com.Car_Rental.repository.springdata.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userDao.findUserByLogin(username.toLowerCase())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + username)
                );

        return UserPrincipal.create(user);
    }


    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );

        return UserPrincipal.create(user);
    }
}