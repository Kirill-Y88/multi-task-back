package y88.kirill.multitaskback.dtos.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String name;
    private String login;
    private String email;
    private Long id;
}
