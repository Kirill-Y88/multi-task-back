package y88.kirill.multitaskback.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import y88.kirill.multitaskback.models.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO implements UserDetails {

    private Long id;
    private String login;
    private String email;
    private String password;
    private String name;
    private String userRoleString;

    public UserDTO(User user){
        this.id = user.getId();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.name = user.getName();
        this.userRoleString = user.getUserRole().getTitle();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<UserRoleDTO> userRoleDTOS = new ArrayList<>();
        userRoleDTOS.add(new UserRoleDTO(userRoleString));
        return userRoleDTOS;
    }

//    @Override
//    public String getPassword() {
//        return null;
//    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
