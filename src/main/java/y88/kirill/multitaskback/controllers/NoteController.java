package y88.kirill.multitaskback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import y88.kirill.multitaskback.dtos.NoteDTO;
import y88.kirill.multitaskback.exceptions.MTResponse;
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
    public ResponseEntity<?> insertNote(@RequestBody NoteDTO noteDTO){
        if(noteDTO.getId()==-1){
        long newNoteId = noteService.insertNote(noteDTO);
            return ResponseEntity.ok(new MTResponse(newNoteId, "Add new note"));
        }else {
            noteService.updateNote((noteDTO));
            return ResponseEntity.ok(new MTResponse(-1L, "update note"));
        }



    }

    @DeleteMapping("/delete")
    public void deleteNote(@RequestParam Long noteId){
        noteService.deleteNoteById(noteId);
    }


}