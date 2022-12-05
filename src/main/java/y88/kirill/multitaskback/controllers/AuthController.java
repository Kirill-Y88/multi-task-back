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


//     try {
//         authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword()));
//     }catch (BadCredentialsException e){
//         return new ResponseEntity<>(new WTGError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
//     }

        UserDTO userDTO = userService.loadUserByLoginAndPassword(jwtRequestDTO.getLogin(), jwtRequestDTO.getPassword());
        String token = jwtTokenGenerate.generateToken(userDTO);
        return ResponseEntity.ok(new JwtResponseDTO(token, userDTO.getName(), userDTO.getLogin(), userDTO.getEmail(), userDTO.getId()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
        if(userService.existsUserByLogin(userDTO.getLogin())){
            return ResponseEntity.of(Optional.of(new ResponseEntity(HttpStatus.BAD_REQUEST)));
        }
        if(userService.existsUserByEmail(userDTO.getEmail())){
            return ResponseEntity.of(Optional.of(new ResponseEntity(HttpStatus.BAD_REQUEST)));
        }

        User user = userService.convertToUserFromDTO(userDTO);
        userService.saveUser(user);
        return ResponseEntity.ok(new UserDTO(user));
    }





}
