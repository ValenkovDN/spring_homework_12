package ru.gbhw.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gbhw.notes.model.Note;

import java.util.Optional;

//Репозиторий заметок
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findNoteById(Long id);
}
