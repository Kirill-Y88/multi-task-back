package y88.kirill.multitaskback.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        User user = userRepository.findByLogin(login).get();
        return noteRepository.findAllByUser(user);
    }


}
