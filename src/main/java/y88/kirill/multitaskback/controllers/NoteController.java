package y88.kirill.multitaskback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import y88.kirill.multitaskback.dtos.NoteDTO;

import y88.kirill.multitaskback.models.Note;
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

    @GetMapping("/user")
    public List<NoteDTO> getAllByUserId(@RequestParam Long userId) {
        return noteService.findAllByUser(userId)
                .stream()
                .map(NoteDTO::new)
                .collect(Collectors.toList());
    }




}