package ru.Robert.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.CustomRepository.ContactRepositoryImpl;
import ru.Robert.NauJava.Entities.Contact;

import java.util.UUID;

@SpringBootTest
public class ContactTest {
    private ContactRepository contactRepository;
    private ContactRepositoryImpl contactRepositoryImpl;
    @Autowired
    public ContactTest(ContactRepository contactRepository, ContactRepositoryImpl contactRepositoryImpl) {
        this.contactRepository = contactRepository;
        this.contactRepositoryImpl = contactRepositoryImpl;
    }

    @Test
    @Transactional
    @Rollback
    public void TestFindContactByNameAndIdBetween() {
        String contactName = UUID.randomUUID().toString();
        Long minId = 1L;
        Long maxID = 2L;
// создание пользователя
        Contact contact = new Contact();
        contact.setName(contactName);
        contactRepository.save(contact);
// поиск пользователя по имени
        Contact foundContact = contactRepository.findByNameAndIdBetween(contactName, minId, maxID).getFirst();
// проверки
        Assertions.assertNotNull(foundContact);
        Assertions.assertEquals(contact.getId(), foundContact.getId());
        Assertions.assertEquals(contactName, foundContact.getName());
    }


}
