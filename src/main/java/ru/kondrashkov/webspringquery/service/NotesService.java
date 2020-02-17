package ru.kondrashkov.webspringquery.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kondrashkov.webspringquery.entity.Notes;
import ru.kondrashkov.webspringquery.repository.NotesRepository;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    private final NotesRepository notesRepository;

    public NotesService(NotesRepository notesRepository) {
        this.notesRepository = notesRepository;
    }

    public void createNotes(Notes notes) {
        notesRepository.save(notes);
    }

    public List<Notes> findAll() {
        return notesRepository.findAll();
    }

    public List<Notes> findWhereNoteStartsFromCat() {
        return notesRepository.findWhereNoteStartsFromCat();
    }
}