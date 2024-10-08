package ru.Robert.NauJava.DB;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.Robert.NauJava.Entities.Contact;

import java.util.HashMap;

@Configuration
public class Config {
    @Bean
    public HashMap<Long, Contact> contactContainer() {
        return new HashMap<>();
    }
}
