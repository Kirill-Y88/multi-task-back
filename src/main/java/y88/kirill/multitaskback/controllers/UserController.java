package y88.kirill.multitaskback.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;
import y88.kirill.multitaskback.dtos.UserDTO;
import y88.kirill.multitaskback.models.User;
import y88.kirill.multitaskback.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.findAll()
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/role_id/{id}")
    public List<UserDTO> getAllUsersByRoleId(@PathVariable Long id){
        return userService.findAllByUserRole(id)
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/role_title")
    public List<UserDTO> getAllUsersByRoleTitle(@RequestParam String title){
        return userService.findAllByUserRole(title)
                .stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id){
        return new UserDTO(userService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным id не найден")));
    }

    //TEST
    @GetMapping("/id")
    public UserDTO getUserById2(@RequestParam  Long id){
        return new UserDTO(userService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным id не найден")));
    }

    @GetMapping("/login/{login}")
    public UserDTO getUserByLogin(@PathVariable String login){
        return new UserDTO(userService.findByLogin(login).orElseThrow(()-> new ResourceNotFoundException("Пользователь с данным login не найден")));
    }

    @PostMapping("/createUser")
    public void createUser(
            @RequestParam(name = "login")String login,
            @RequestParam(name = "password")String password,
            @RequestParam(name = "name")String name,
            @RequestParam(name = "email")String email
    ){
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setEmail(email);
        userService.saveUser(user);
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
    }



}
