package y88.kirill.multitaskback.dtos.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import y88.kirill.multitaskback.dtos.UserDTO;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private UserDTO user;
//    private String name;
//    private String login;
//    private String email;
//    private Long id;
}
