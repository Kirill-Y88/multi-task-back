package y88.kirill.multitaskback.dtos;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MessageDTOFull {
    private Long userFromId;
    private String userFromLogin;
    private List<MessageDTO> messageDTO;

    public MessageDTOFull(Long userFromId, String userFromLogin, List<MessageDTO> messageDTO) {
        this.userFromId = userFromId;
        this.userFromLogin = userFromLogin;
        this.messageDTO = messageDTO;
    }
}
