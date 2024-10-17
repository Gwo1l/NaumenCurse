package ru.Robert.NauJava;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.CRUDRepositories.CountryRepository;
import ru.Robert.NauJava.CRUDRepositories.NoteRepository;
import ru.Robert.NauJava.CustomRepository.ContactRepositoryCustom;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Entities.Country;
import ru.Robert.NauJava.Entities.Note;
import ru.Robert.NauJava.Services.NoteService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static ru.Robert.NauJava.utilities.Tool.*;

@SpringBootTest
public class ContactTest {
    private ContactRepository contactRepository;
    private CountryRepository countryRepository;
    private ContactRepositoryCustom contactRepositoryCustom;
    private NoteRepository noteRepository;
    private final NoteService noteService;
    @Autowired
    public ContactTest(ContactRepository contactRepository, ContactRepositoryCustom contactRepositoryCustom,
                       CountryRepository countryRepository, NoteRepository noteRepository, NoteService noteService) {
        this.contactRepositoryCustom = contactRepositoryCustom;
        this.contactRepository = contactRepository;
        this.countryRepository = countryRepository;
        this.noteRepository = noteRepository;
        this.noteService = noteService;
    }

    @Test
    public void testFindByNameAndIdBetween() {
        String contactName1 = UUID.randomUUID().toString();
        String contactNameMain = UUID.randomUUID().toString();
        String contactName3 = UUID.randomUUID().toString();
        Long minId = 1L;
        Long maxID = 3L;

        Contact contact1 = createContact(1L, contactName1);
        contactRepository.save(contact1);

        Contact contactMain = createContact(2L, contactNameMain);
        contactRepository.save(contactMain);

        Contact contact3 = createContact(3L, contactName3);
        contactRepository.save(contact3);

        Contact foundContact = contactRepository.findByNameAndIdBetween(contactNameMain, minId, maxID).getFirst();

        Assertions.assertNotNull(foundContact);
        Assertions.assertEquals(contactMain.getId(), foundContact.getId());
        Assertions.assertEquals(contactNameMain, foundContact.getName());
    }

    @Test
    public void testFindByCountry() {
        String contactName = UUID.randomUUID().toString();
        Contact contact = createContact(1L, contactName);
        Country countryRussia = createCountryAndSave(1L, "Russia", "+7", countryRepository);
        contact.setCountry(countryRussia);
        contactRepository.save(contact);

        Contact foundContact = contactRepository.findByCountry("Russia").getFirst();

        Assertions.assertNotNull(foundContact);
        Assertions.assertEquals(contact.getId(), foundContact.getId());
        Assertions.assertEquals(contactName, foundContact.getName());
    }

    @Test
    public void testCriteriaAPIFindByNameAndIdBetween() {
        String contactName1 = UUID.randomUUID().toString();
        String contactNameMain = UUID.randomUUID().toString();
        String contactName3 = UUID.randomUUID().toString();
        Long minId = 1L;
        Long maxID = 3L;

        Contact contact1 = createContact(1L, contactName1);
        contactRepository.save(contact1);

        Contact contactMain = createContact(2L, contactNameMain);
        contactRepository.save(contactMain);

        Contact contact3 = createContact(3L, contactName3);
        contactRepository.save(contact3);

        Contact foundContact = contactRepositoryCustom.findByNameAndIdBetween(contactNameMain, minId, maxID)
                .getFirst();

        Assertions.assertNotNull(foundContact);
        Assertions.assertEquals(contactMain.getId(), foundContact.getId());
        Assertions.assertEquals(contactNameMain, foundContact.getName());
    }

    @Test
    public void testCriteriaAPIFindByCountry() {
        String contactName = UUID.randomUUID().toString();
        Contact contact = createContact(1L, contactName);
        Country countryRussia = createCountryAndSave(1L, "Russia", "+7", countryRepository);
        contact.setCountry(countryRussia);
        contactRepository.save(contact);

        Contact foundContact = contactRepositoryCustom.findByCountry("Russia").getFirst();

        Assertions.assertNotNull(foundContact);
        Assertions.assertEquals(contact.getId(), foundContact.getId());
        Assertions.assertEquals(contactName, foundContact.getName());
    }




}
