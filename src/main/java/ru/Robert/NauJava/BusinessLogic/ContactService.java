package ru.Robert.NauJava.BusinessLogic;

import ru.Robert.NauJava.Entities.Contact;

public interface ContactService {
    void createContact(Long id, String login, String number,
                       String country, String gender);
    Contact findById(Long id);
    void deleteById(Long id);
    void updateLogin(Long id, String newLogin);
    void updateNumber(Long id, String number);
}
