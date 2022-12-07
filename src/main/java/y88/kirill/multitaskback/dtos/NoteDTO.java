package y88.kirill.multitaskback.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import y88.kirill.multitaskback.models.Note;

@Data
@NoArgsConstructor
public class NoteDTO {
    private Long id;
    private String title;
    private String content;
    private Long userId;


    public NoteDTO(Note note) {
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.userId = note.getUser().getId();
    }
}
