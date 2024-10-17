package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Entities.Note;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    List<Contact> findByNameAndIdBetween(String name, Long minId, Long maxId);

    @Query("SELECT c FROM Contact c WHERE c.country.name = :country")
    List<Contact> findByCountry(@Param("country") String country);

    @Query("SELECT c FROM Contact c WHERE c.note.text = :note")
    List<Contact> findByNote(@Param("note") String note);
}
