package adpro.b10.epicarcade_auth.controller;

import adpro.b10.epicarcade_auth.dto.AuthResponseDTO;
import adpro.b10.epicarcade_auth.dto.LoginDto;
import adpro.b10.epicarcade_auth.model.Role;
import adpro.b10.epicarcade_auth.model.UserEntity;
import adpro.b10.epicarcade_auth.dto.RegisterDto;
import adpro.b10.epicarcade_auth.repository.RoleRepository;
import adpro.b10.epicarcade_auth.repository.UserRepository;
import adpro.b10.epicarcade_auth.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(), loginDto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token =jwtGenerator.generateToken(authentication);

        UserEntity loggedInUser = userRepository.findByEmail(loginDto.getEmail()).get();

        return new ResponseEntity<>(new AuthResponseDTO(
                loggedInUser.getId(),
                loggedInUser.getUsername(),
                loggedInUser.getEmail(),
                loggedInUser.getRoles().get(0).getName(),
                loggedInUser.getProfilePictureUrl(),
                token
        ), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDto registerDto) {
        Map<String, String> response = new HashMap<>();

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            response.put("message", "Username is already taken");
            return ResponseEntity.badRequest().body(response);
        }

        if (userRepository.existsByEmail(registerDto.getUsername())) {
            response.put("message", "Email is already taken");
            return ResponseEntity.badRequest().body(response);
        }

        //If it doesn't exist
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        if (registerDto.getProfilePictureUrl() != null){
            user.setProfilePictureUrl(registerDto.getProfilePictureUrl());
        } else {
            user.setProfilePictureUrl("https://ui-avatars.com/api/?name=" + user.getUsername() + "&background=random");
        }

        //Check registered role
        String requestedRole = registerDto.getRole();
        Role roles = switch (requestedRole) {
            case "ADMIN", "1" -> roleRepository.findByName("ADMIN").get();
            case "BUYER", "2" -> roleRepository.findByName("BUYER").get();
            case "SELLER", "3" -> roleRepository.findByName("SELLER").get();
            default -> roleRepository.findByName("USER").get();
        };

        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        response.put("message", "User registered successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

