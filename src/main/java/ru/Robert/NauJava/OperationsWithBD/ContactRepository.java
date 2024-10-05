package ru.Robert.NauJava.OperationsWithBD;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.Robert.NauJava.Entities.Contact;

import java.util.*;

@Component
public class ContactRepository implements CrudRepository<Contact, Long> {
    private final HashMap<Long, Contact> contactContainer;
    private Long idAutoIncrement;

    @Autowired
    public ContactRepository(HashMap<Long, Contact> contactContainer) {
        this.contactContainer = contactContainer;
        idAutoIncrement = 1L;
    }

    @Override
    public void create(Contact entity) {
        contactContainer.put(idAutoIncrement,entity);
        idAutoIncrement++;
    }

    @Override
    public Contact read(Long id) {
        return contactContainer.get(id);
    }

    @Override
    public void update(Contact entity) {
        contactContainer.put(entity.getId(), entity);
    }

    @Override
    public void delete(Long id) {
        contactContainer.remove(id);
    }

}
