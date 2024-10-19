package ru.Robert.NauJava;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.CRUDRepositories.NoteRepository;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Entities.Country;
import ru.Robert.NauJava.Entities.Note;
import ru.Robert.NauJava.Services.NoteService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.Robert.NauJava.utilities.Tool.*;

@SpringBootTest
public class TransactionTest {
    private ContactRepository contactRepository;
    private NoteRepository noteRepository;
    private NoteService noteService;

    @Autowired
    public TransactionTest(ContactRepository contactRepository, NoteRepository noteRepository, NoteService noteService) {
        this.contactRepository = contactRepository;
        this.noteRepository = noteRepository;
        this.noteService = noteService;
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteNote() {
        String contactName = UUID.randomUUID().toString();
        Contact contact = createContact(1L, contactName);
        Note note = createNoteAndSave(1L, LocalDateTime.now(), "hi", noteRepository);
        contact.setNote(note);
        contactRepository.save(contact);

        noteService.deleteNote("hi");

        Optional<Note> foundNote = noteRepository.findById(note.getId());
        Assertions.assertTrue(foundNote.isEmpty());

        Optional<Contact> foundContact = contactRepository.findById(contact.getId());
        Assertions.assertTrue(foundContact.isEmpty());
    }

    @Test
    public void testNegativeCaseDeleteNote() {
        String contactName = UUID.randomUUID().toString();
        Contact contact = createContact(1L, contactName);
        Note note = createNoteAndSave(1L, LocalDateTime.now(), "hi", noteRepository);
        contact.setNote(note);
        contactRepository.save(contact);

        noteService.deleteNote("ih");

        Optional<Note> foundNote = noteRepository.findById(note.getId());
        Assertions.assertFalse(foundNote.isEmpty());

        Optional<Contact> foundContact = contactRepository.findById(contact.getId());
        Assertions.assertFalse(foundContact.isEmpty());
    }
}
