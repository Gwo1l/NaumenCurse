package ru.Robert.NauJava.CRUDRepositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.Robert.NauJava.Entities.Note;

public interface NoteRepository extends CrudRepository<Note, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Note n WHERE n.id = :id")
    void deleteById(@Param("id") Long id);

    @Query("SELECT n FROM Note n WHERE n.text = :text")
    Note findByText(@Param("text") String text);
}
