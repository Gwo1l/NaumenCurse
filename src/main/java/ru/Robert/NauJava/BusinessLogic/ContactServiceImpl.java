package ru.Robert.NauJava.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.OperationsWithBD.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService{
    private final ContactRepository contactRepository;
    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public void createContact(String login,String number,
                              String country, String gender) {

        Contact contact = new Contact();
        contact.setName(login);
        contact.setPhoneNumber(number);
        contact.setCountry(country);
        contact.setGender(gender);

        contactRepository.create(contact);
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.read(id);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.delete(id);
    }

    @Override
    public void updateLogin(Long id, String newLogin) {
        Contact contact = contactRepository.read(id);
        contact.setName(newLogin);
        contactRepository.update(contact);
    }

    @Override
    public void updateNumber(Long id, String number) {
        Contact contact = contactRepository.read(id);
        contact.setPhoneNumber(number);
        contactRepository.update(contact);
    }
}
