package y88.kirill.multitaskback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import y88.kirill.multitaskback.dtos.NoteDTO;
import y88.kirill.multitaskback.services.NoteService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public List<NoteDTO> getAll() {
        return noteService.findAll()
                .stream()
                .map(NoteDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/userId")
    public List<NoteDTO> getAllByUserId(@RequestParam Long userId) {
        return noteService.findAllByUser(userId)
                .stream()
                .map(NoteDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/save")
    public void insertNote(@RequestBody NoteDTO noteDTO){
        if(noteDTO.getId()==-1){
        noteService.insertNote(noteDTO);
        }else {
            noteService.updateNote((noteDTO));
        }
    }

    @DeleteMapping("/delete")
    public void deleteNote(@RequestParam Long noteId){
        noteService.deleteNoteById(noteId);
    }


}