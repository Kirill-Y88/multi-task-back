package y88.kirill.multitaskback.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class MessageChat {
    private Long userId;
    private String userLogin;

    public MessageChat(Long userFromId, String userLogin) {
        this.userId = userFromId;
        this.userLogin = userLogin;

    }

}
