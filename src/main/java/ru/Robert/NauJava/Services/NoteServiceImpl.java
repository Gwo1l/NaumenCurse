package ru.Robert.NauJava.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.CRUDRepositories.NoteRepository;
import ru.Robert.NauJava.Entities.Contact;
import ru.Robert.NauJava.Entities.Note;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final ContactRepository contactRepository;
    private final PlatformTransactionManager transactionManager;
    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, ContactRepository contactRepository,
                           PlatformTransactionManager transactionManager)
    {
        this.noteRepository = noteRepository;
        this.contactRepository = contactRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteNote(String noteText) {
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try
        {
            List<Contact> contacts = contactRepository.findByNote(noteText);
            for (Contact contact : contacts)
            {
                contactRepository.delete(contact);
            }
            noteRepository.deleteByText(noteText);
            transactionManager.commit(status);
        }
        catch (DataAccessException ex)
        {
            transactionManager.rollback(status);
            throw ex;
        }
    }

}
