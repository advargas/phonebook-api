package com.phonebook;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.phonebook.services.ContactService;

@Configuration
@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class,
        JpaRepositoriesAutoConfiguration.class})
@ComponentScan(basePackages={"com.phonebook.controllers"})
@PropertySource(value = "classpath:test.properties", ignoreResourceNotFound = false)
public class TestApplication {

	@Bean
    public ContactService getContactService(){
        return Mockito.mock(ContactService.class);
    }

}
