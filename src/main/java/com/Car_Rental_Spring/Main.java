package com.Car_Rental_Spring;

import com.Car_Rental_Spring.confing.AppConfig;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = (UserDao) context.getBean("userDaoImpl");
        for (User user : userDao.findAll()) {
            System.out.println(user.toString());
        }
    }
}
