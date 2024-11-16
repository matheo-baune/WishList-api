package wishlist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import wishlist.Utils;
import wishlist.configuration.JwtUtils;
import wishlist.dto.UserDTO;
import wishlist.entity.User;
import wishlist.mapper.UserMapper;
import wishlist.repository.UserRepository;
import wishlist.service.UserService;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;


    @GetMapping("/generate-permanent-token")
    public ResponseEntity<String> generatePermanentToken() {
        String token = jwtUtils.generatePermanentToken("root");
        return ResponseEntity.ok(token);
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username is already in use");
        }
        String salt = Utils.generateSalt();
        user.setSalt(salt);

        user.setPassword(Utils.encodePassword(user.getPassword(),salt));
        UserDTO userRegister = userService.createUser(user);
        String token = jwtUtils.generateToken(user.getUsername());
        //TODO - Revoir la construction du location /api devrait pas être ajouter à la main
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{id}")
                .buildAndExpand(userRegister.getId())
                .toUri();
        return ResponseEntity.created(location)
                .header("Set-Cookie",token)
                .body(userRegister);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try{
            User userDatabase = userRepository.findByUsername(user.getUsername());
            String passwordEncoded = Utils.encodePassword(user.getPassword(), userDatabase.getSalt());

            if(Utils.checkPassword(user.getPassword(),passwordEncoded)){
                UserDTO userDTO = userMapper.toDTO(userDatabase);
                String token = jwtUtils.generateToken(user.getUsername());
                return ResponseEntity.ok()
                        .header("Set-Cookie",token)
                        .body(userDTO);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body("Invalid username or password");
        }
    }
}
