package com.Car_Rental.config.web;


import com.Car_Rental.util.convert.user.UserChangeRequestConverter;
import com.Car_Rental.util.convert.user.UserCreateRequestConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new UserCreateRequestConverter(passwordEncoder));
    registry.addConverter(new UserChangeRequestConverter());
  }
}