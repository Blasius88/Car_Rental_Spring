package com.Car_Rental_Spring;

import com.Car_Rental_Spring.confing.AppConfig;
import com.Car_Rental_Spring.domain.User;
import com.Car_Rental_Spring.repository.UserDao;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        start();
    }


    private static void start() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Scanner sc = new Scanner(System.in);
        UserDao userDao = (UserDao) context.getBean("userDaoImpl");
        for (User user : userDao.findAll()) {
            System.out.println(user.toString());
        }
        try {
            System.out.println("Вывод логина и пароля ");
            System.out.println(userDao.findLoginAndPass("ADMIN"));
//            User us = DataInput();
//            userDao.save(us);
//
            System.out.println("Введите город");
            String str = sc.next();
       
            for (User user : userDao.findCityUser(str)) {
                System.out.println(user.toString());
            }

            System.out.println("Введите Id для удаление ");
            Long num = sc.nextLong();
//            userDao.delete(num);
//
//            System.out.println("Введите Id для поиска");
//            num = sc.nextLong();
            System.out.println(userDao.findById(num));
//
//            System.out.println("Введите id для замены данных ");
//            num = sc.nextLong();

            for (User user : userDao.findAll()) {
                if (user.getUserId() == num) {
                    user = DataInput();
                    user.setUserId(num);
                    userDao.update(user);

                    break;
                }
            }

        } catch (Exception ex) {
            System.out.println("this is exception\n " + ex.toString());
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

    private static User DataInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите имя");
        String firstName = sc.next();

        System.out.println("Введите Фамилию");
        String lastName = sc.next();

        System.out.println("Введите login ");
        String login = sc.next();

        System.out.println("Введите pass ");
        String pass = sc.next();

        System.out.println("Введите id роль ");
        Long roleId = sc.nextLong();

        System.out.println("Введите email ");
        String e_mail = sc.next();

        System.out.println("Введите телефон");
        String phone = sc.next();

        System.out.println("Введите город");
        String city = sc.next();

        java.util.Date date = new java.util.Date();
        Date created = new java.sql.Date(date.getTime());

        User us = new User(firstName, lastName, login, pass, created, roleId, e_mail, phone, city);
        return us;
    }
}
