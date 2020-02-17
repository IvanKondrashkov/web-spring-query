package ru.kondrashkov.webspringquery.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kondrashkov.webspringquery.ResourceNotFoundException;
import ru.kondrashkov.webspringquery.entity.Notes;
import ru.kondrashkov.webspringquery.repository.NotesRepository;
import ru.kondrashkov.webspringquery.service.NotesService;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
public class NotesController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    public NotesService notesService;
    @PostMapping("/hello")
    public String okFind(@RequestParam("note") String note, Model model) {
        model.addAttribute("note", note);
        List<Notes> listNotes = notesService.findAll();
        model.addAttribute("listNotes",listNotes);
        for (Notes el:listNotes) {
            model.addAttribute(el.getHeader(), el.getNote());
        }
        List<Notes> listCat = notesService.findWhereNoteStartsFromCat();
        model.addAttribute("listCat", listCat);
        return null;
    }

    @Autowired
    private NotesRepository notesRepository;

    @GetMapping("/notes")
    public List<Notes> getNotes() {
        return notesRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public Notes getNotesById(@PathVariable Long id) {
        Optional<Notes> notes = notesRepository.findById(id);
        return notes.orElseThrow(() -> new ResourceNotFoundException("Notes not found.Id = " + id));
    }

    @PostMapping("/notes")
    public Notes createNotes(@Valid @RequestBody Notes notes) {
        return notesRepository.save(notes);
    }

    @PutMapping("/notes{notesId}")
    public Notes updateNotes(@PathVariable Long notesId, @Valid @RequestBody Notes notesRequest) throws ParseException {

        return notesRepository.findById(notesId)
                .map(notes -> {
                    notes.setHeader(notesRequest.getHeader());
                    notes.setNote(notesRequest.getNote());
                    return notesRepository.save(notes);
                }).orElseThrow(() -> new ResourceNotFoundException("Notes not found with id = " + notesId));
    }

    @DeleteMapping("/notes/{notesId}")
    public ResponseEntity<?> deleteNotes(@PathVariable Long notesId) {
        return notesRepository.findById(notesId)
                .map(notes -> {
                    notesRepository.delete(notes);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Notes not found with id = " + notesId));
    }
}
