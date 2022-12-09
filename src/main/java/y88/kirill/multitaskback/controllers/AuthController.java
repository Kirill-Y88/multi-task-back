package y88.kirill.multitaskback.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import y88.kirill.multitaskback.authorization.JwtTokenGenerate;
import y88.kirill.multitaskback.dtos.UserDTO;
import y88.kirill.multitaskback.dtos.authorization.JwtRequestDTO;
import y88.kirill.multitaskback.dtos.authorization.JwtResponseDTO;
import y88.kirill.multitaskback.exceptions.MTResponse;
import y88.kirill.multitaskback.models.User;
import y88.kirill.multitaskback.services.UserService;


import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenGenerate jwtTokenGenerate;
 //   private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequestDTO jwtRequestDTO){
        System.out.printf("\n logGin =%s  passWword =%s \n", jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword());
//     try {
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword()));
//     }catch (BadCredentialsException e){
//         return new ResponseEntity<>(new WTGError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
//     }

        UserDTO userDTO = userService.loadUserByLoginAndPassword(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword());
        System.out.println(" \n UserDTO login=" + userDTO.getLogin());
        String token = jwtTokenGenerate.generateToken(userDTO);
        return ResponseEntity.ok(new JwtResponseDTO(token, userDTO));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
        if(userService.existsUserByLogin(userDTO.getLogin())){
            return ResponseEntity.of(Optional.of(new MTResponse(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким login уже существует")));
        }
        if(userService.existsUserByEmail(userDTO.getEmail())){
            return ResponseEntity.of(Optional.of(new MTResponse(HttpStatus.BAD_REQUEST.value(), "Пользователь с таким email уже существует")));
        }
        userDTO.setUserRoleString("ROLE_USER");
        User user = userService.convertToUserFromDTO(userDTO);
        userService.saveUser(user);
        UserDTO userDTOForToken = userService.loadUserByLoginAndPassword(userDTO.getUsername(), userDTO.getPassword());
        String token = jwtTokenGenerate.generateToken(userDTOForToken);

        return ResponseEntity.ok(new JwtResponseDTO(token, userDTOForToken));
    }





}
