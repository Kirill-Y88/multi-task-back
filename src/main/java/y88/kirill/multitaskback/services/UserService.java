package y88.kirill.multitaskback.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import y88.kirill.multitaskback.dtos.UserDTO;
import y88.kirill.multitaskback.models.User;
import y88.kirill.multitaskback.models.UserRole;
import y88.kirill.multitaskback.repositories.UserRepository;
import y88.kirill.multitaskback.repositories.UserRoleRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByLogin(String login){
        return userRepository.findByLogin(login);
    }

    public List<User> findAllByUserRole(UserRole userRole){
        return userRepository.findAllByUserRole(userRole);
    }

    public List<User> findAllByUserRole(Long id){
        UserRole userRole = userRoleRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным id не найден"));
        return userRepository.findAllByUserRole(userRole);
    }

    public List<User> findAllByUserRole(String nameRole){
        UserRole userRole = userRoleRepository.findByTitle(nameRole).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным именем роли не найден"));
        return userRepository.findAllByUserRole(userRole);
    }

    public User saveUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    //реализация этапа аутентификации и авторизации по ЛОГИНУ
    @Override
    public UserDTO loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDTO(findByLogin(username).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным login роли не найден")));
    }

    public UserDTO loadUserByLoginAndPassword(String username, String password) throws UsernameNotFoundException {
        User user = findByLogin(username).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным login роли не найден"));
        System.out.println("роли юзера из сервиса = " + user.getUserRole().getTitle());
        if(user!= null){
            if(passwordEncoder.matches(password, user.getPassword())){
                return new UserDTO(user);
            }
        }
        return null;
    }

    public User convertToUserFromDTO(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setUserRole( userRoleRepository.findByTitle(userDTO.getUserRoleString()).orElseThrow());
        return user;
    }

    public boolean existsUserByLogin(String login){
        return userRepository.existsUserByLogin(login);
    }

    public boolean existsUserByEmail(String email){
        return userRepository.existsUserByEmail(email);
    }


}
