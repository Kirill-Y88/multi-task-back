package y88.kirill.multitaskback.dtos.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequestDTO {
    String login;
    String password;
}
