package y88.kirill.multitaskback.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import y88.kirill.multitaskback.dtos.NoteDTO;
import y88.kirill.multitaskback.models.Note;
import y88.kirill.multitaskback.models.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface NoteRepository extends JpaRepository<Note,Long> {

    Optional<Note> findByTitle(String title);
    List<Note> findAllByUser(User user);

    @Modifying
    @Query(value = "INSERT into notes (title, content,user_id) VALUES (:title,:content,:user_id)", nativeQuery = true)
    int insertNote(@Param("title") String title,
                    @Param("content")String content,
                    @Param("user_id")Long user_id);

    @Modifying
    @Query(value = "update notes set  title = :title, content = :content WHERE id =:id", nativeQuery = true)
    int updateNote(@Param("title") String title,
                    @Param("content")String content,
                    @Param("id")Long id);

    void deleteById(Long id);

}
