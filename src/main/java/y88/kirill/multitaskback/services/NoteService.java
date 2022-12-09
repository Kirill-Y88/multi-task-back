package y88.kirill.multitaskback.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import y88.kirill.multitaskback.dtos.NoteDTO;
import y88.kirill.multitaskback.models.Note;
import y88.kirill.multitaskback.models.User;
import y88.kirill.multitaskback.repositories.NoteRepository;
import y88.kirill.multitaskback.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    public Optional<Note> findByTitle(String title){
        return noteRepository.findByTitle(title);
    }

    public List<Note> findAll(){
        return noteRepository.findAll();
    }

    public List<Note> findAllByUser(Long userId){
        User user = userRepository.findById(userId).get();
        return noteRepository.findAllByUser(user);
    }

    public List<Note> findAllByUser(String login){
        User user = userRepository.findByLogin(login).get();//todo обработать
        return noteRepository.findAllByUser(user);
    }

    public long insertNote(NoteDTO noteDTO){
        noteRepository.insertNote(noteDTO.getTitle(), noteDTO.getContent(),  noteDTO.getUserId());
       return findLastNoteByUser(noteDTO.getUserId()).getId();
    }

    public int updateNote(NoteDTO noteDTO){
        return noteRepository.updateNote(noteDTO.getTitle(), noteDTO.getContent(),  noteDTO.getId());
    }

    public void deleteNoteById(Long noteId){
        noteRepository.deleteById(noteId);
    }

    public Note findLastNoteByUser(Long userId){
       return noteRepository.findLastNoteByUser(userId).get();
    }

    public Note convertToNoteFromDTO (NoteDTO noteDTO){
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        return note;
    }

}
