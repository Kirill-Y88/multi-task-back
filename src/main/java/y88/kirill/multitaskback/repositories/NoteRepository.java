package y88.kirill.multitaskback.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import y88.kirill.multitaskback.models.Note;
import y88.kirill.multitaskback.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

    Optional<Note> findByTitle(String title);
    List<Note> findAllByUser(User user);

}
