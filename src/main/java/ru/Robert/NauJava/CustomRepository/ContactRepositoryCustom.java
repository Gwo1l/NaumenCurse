package ru.Robert.NauJava.CustomRepository;

import org.springframework.data.repository.query.Param;
import ru.Robert.NauJava.Entities.Contact;

import java.util.List;

public interface ContactRepositoryCustom {
    List<Contact> findByName(String name);
    List<Contact> findByCountry(String country);
}
