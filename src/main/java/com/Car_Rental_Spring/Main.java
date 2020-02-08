package com.Car_Rental_Spring;

import com.Car_Rental_Spring.confing.AppConfig;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        start();
    }


    private static void start() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserDao userDao = (UserDao) context.getBean("userDaoImpl");
        for (User user : userDao.findAll()) {
            System.out.println(user.toString());
        }
        System.out.println("Введите Id ");
        Scanner sc = new Scanner(System.in);
        Long num = sc.nextLong();
        userDao.delete(num);
        System.out.println("Введите Id ");
        num = sc.nextLong();
        try {
            System.out.println(userDao.findOne(num));
        } catch (Exception ex) {
            System.out.println("this id not found");
        } finally {
            System.out.println("Продолжить выполнение прораммы: \n1 - да\n2 - нет  ");
            int num1 = sc.nextInt();
            switch (num1) {
                case 1:
                    start();
                    break;
                case 2:
                    break;
            }
        }

    }
}
