package com.Car_Rental;

import com.Car_Rental.config.core.DatabaseConfig;
import com.Car_Rental.config.core.JdbcTemplateConfig;
import com.Car_Rental.config.swagger.SwaggerConfig;
import com.Car_Rental.config.web.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@EnableSwagger2WebMvc
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.Car_Rental"},
        exclude = {
                JacksonAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@Import({
        DatabaseConfig.class,
        JdbcTemplateConfig.class,
        SwaggerConfig.class,
        WebConfig.class
})
public class ApplicationStarter extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStarter.class, args);
    }
}
