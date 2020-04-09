package com.Car_Rental_Spring;

import com.Car_Rental_Spring.config.core.PropertiesConfig;
import com.Car_Rental_Spring.config.core.DatabaseConfig;
import com.Car_Rental_Spring.config.core.JdbcTemplateConfig;
import com.Car_Rental_Spring.config.swagger.SwaggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableSwagger2
@EnableAspectJAutoProxy
@EnableJpaRepositories
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = {"com.Car_Rental_Spring"},
        exclude = {
                JacksonAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class
        })
@Import({
        DatabaseConfig.class,
        JdbcTemplateConfig.class,
        SwaggerConfig.class
})
public class ApplicationStarter extends SpringBootServletInitializer {

    public static void main (String[] args) {
        SpringApplication.run (ApplicationStarter.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory (DataSource dataSource, PropertiesConfig jpaProperties) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean ();
        em.setDataSource (dataSource);
        em.setPackagesToScan ("com.Car_Rental_Spring");
        em.setJpaProperties (jpaProperties.getProperties() );
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter ();
        em.setJpaVendorAdapter (vendorAdapter);
        return em;
    }

    @Bean(name = "entityManager")
    public EntityManager getEntityManager (EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager ();
    }

}
