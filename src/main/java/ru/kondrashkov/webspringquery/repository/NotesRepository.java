package ru.kondrashkov.webspringquery.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kondrashkov.webspringquery.entity.Notes;
import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {

    List<Notes> findAll();

    @Query(value = "select * from notes where note like '%кошка%'", nativeQuery = true)
    List<Notes> findWhereNoteStartsFromCat();
}
