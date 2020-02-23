package com.Car_Rental_Spring.controller;

import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/user/alluser")
    public String userUser(Map<String, Object> model) {
        List<User> user = userDao.findAll();
        model.put("id", user);
        return "user";
    }


    /*GET localhost:8081/user/all*/
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String printHello(ModelMap model) {
//        model.addAttribute("user",
//                userDao.findById(Long.valueOf(id))
//        );
//
        model.addAttribute("userName",
                userDao.findAll().stream()
                        .map(User::getFirstName)
                        .collect(Collectors.joining(","))
        );
        return "hello";
    }


}
