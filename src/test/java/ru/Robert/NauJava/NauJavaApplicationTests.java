package ru.Robert.NauJava;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.Robert.NauJava.CRUDRepositories.ContactRepository;
import ru.Robert.NauJava.CRUDRepositories.NoteRepository;
import ru.Robert.NauJava.CustomRepository.ContactRepositoryImpl;
import ru.Robert.NauJava.Services.NoteServiceImpl;

@SpringBootTest
class NauJavaApplicationTests {
	private ContactRepository contactRepository;
	private NoteRepository noteRepository;
	private ContactRepositoryImpl contactRepositoryImpl;
	private NoteServiceImpl noteServiceImpl;

	@Autowired
	public NauJavaApplicationTests(ContactRepository contactRepository, NoteRepository noteRepository,
								   ContactRepositoryImpl contactRepositoryImpl, NoteServiceImpl noteServiceImpl) {
		this.contactRepository = contactRepository;
		this.noteRepository = noteRepository;
		this.contactRepositoryImpl = contactRepositoryImpl;
		this.noteServiceImpl = noteServiceImpl;
	}

	@Test
	void contextLoads() {
	}

}
