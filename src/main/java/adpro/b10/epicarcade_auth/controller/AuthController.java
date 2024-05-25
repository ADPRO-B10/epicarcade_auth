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
import org.springframework.stereotype.Controller;

import java.util.Collections;

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
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        //If it exists
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        if (userRepository.existsByEmail(registerDto.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }

        //If it doesn't exist
        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //Check registered role
        String requestedRole = registerDto.getRole();
        Role roles = null;

        if (requestedRole.equals("ADMIN") || requestedRole.equals("1")) {
            roles = roleRepository.findByName("ADMIN").get();
        } if (requestedRole.equals("BUYER") || requestedRole.equals("2")) {
            roles = roleRepository.findByName("BUYER").get();
        } if (requestedRole.equals("SELLER") || requestedRole.equals("3")) {
            roles = roleRepository.findByName("SELLER").get();
        } else {
            roles = roleRepository.findByName("USER").get();
        }

        user.setRoles(Collections.singletonList(roles));
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}

