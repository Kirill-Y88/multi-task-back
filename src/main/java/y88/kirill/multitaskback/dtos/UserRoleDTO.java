package y88.kirill.multitaskback.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import y88.kirill.multitaskback.models.UserRole;


@Data
@NoArgsConstructor
public class UserRoleDTO implements GrantedAuthority {

    private Long id;
    private String title;

    public UserRoleDTO(UserRole userRole) {
        this.id = userRole.getId();
        this.title = userRole.getTitle();
    }

    public UserRoleDTO(String role) {
        this.title = role;
    }

    @Override
    public String getAuthority() {
        return title;
    }
}
