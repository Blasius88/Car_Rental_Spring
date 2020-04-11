package com.Car_Rental_Spring.service;

import com.Car_Rental_Spring.entity.MRoles;
import com.Car_Rental_Spring.entity.User;
import com.Car_Rental_Spring.repository.RoleDao;
import com.Car_Rental_Spring.repository.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userDao.findByLogin(username);
            List<MRoles> roles = roleDao.getRolesByUserId(Math.toIntExact(Long.valueOf(user.getUserId())));
            if (Long.valueOf(user.getUserId()) == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        user.getUserLogin(),
                        user.getUserPass(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getName_roles())
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}