package com.Car_Rental.service;

import com.Car_Rental.entity.Roles;
import com.Car_Rental.entity.User;
import com.Car_Rental.repository.hibernate.RoleDao;
import com.Car_Rental.repository.hibernate.UserDao;
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
            List<Roles> roles = roleDao.getRolesByUserId(Math.toIntExact(Long.valueOf(user.getUserId())));
            if (Long.valueOf(user.getUserId()) == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        user.getUserLogin(),
                        user.getUserPass(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getNameRoles())
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}