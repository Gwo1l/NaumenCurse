package ru.Robert.NauJava.utilities;

import ru.Robert.NauJava.CRUDRepositories.CountryRepository;
import ru.Robert.NauJava.CRUDRepositories.NoteRepository;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Entities.Country;
import ru.Robert.NauJava.Entities.Note;

import java.time.LocalDateTime;

public class Tool {
    public static Country createCountryAndSave(Long id, String countryName, String code, CountryRepository cr) {
        Country country = new Country(id, countryName, code);
        cr.save(country);
        return country;
    }

    public static Contact createContact(Long id, String name) {
        Contact cnt = new Contact();
        cnt.setId(id);
        cnt.setName(name);
        return cnt;
    }

    public static Note createNoteAndSave(Long id, LocalDateTime creationDate, String text, NoteRepository nr) {
        Note note = new Note(creationDate, text);
        Note nt = nr.save(note);
        return nt;
    }
}
