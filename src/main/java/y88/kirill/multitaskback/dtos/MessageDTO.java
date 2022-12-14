package y88.kirill.multitaskback.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import y88.kirill.multitaskback.models.Message;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MessageDTO {

    private Long id;
    private Long userFromId;
    private Long userToId;
    private String msg;
    private LocalDateTime createdAt;
    private Boolean downloaded;
    private Boolean read;

    public MessageDTO(Message message) {
        this.id = message.getId();
        this.userFromId = message.getUserFrom().getId();
        this.userToId = message.getUserTo().getId();
        this.msg = message.getMsg();
        this.createdAt = message.getCreatedAt();
        this.downloaded = message.getDownloaded();
        this.read = message.getRead();
    }
}
